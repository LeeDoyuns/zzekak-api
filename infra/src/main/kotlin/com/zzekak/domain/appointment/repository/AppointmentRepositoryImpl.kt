package com.zzekak.domain.appointment.repository

import com.zzekak.domain.address.entity.AppointmentAddressEntity
import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId
import com.zzekak.domain.appointment.dao.AppointmentEntityDao
import com.zzekak.domain.appointment.entity.AppointmentEntity
import com.zzekak.domain.appointment.entity.AppointmentUserEntity
import com.zzekak.domain.appointment.entity.AppointmentUserId
import com.zzekak.domain.appointment.model.Appointment
import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.appointmentmission.dao.AppointmentMissionEntityDao
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionId
import com.zzekak.domain.mission.model.AppointmentMissionCommand
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.dao.UserEntityDao
import com.zzekak.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import kotlin.reflect.KClass

@Repository
internal class AppointmentRepositoryImpl(
    val dao: AppointmentEntityDao,
    val userDao: UserEntityDao,
    val appointmentMissionDao: AppointmentMissionEntityDao
) : AppointmentRepository {
    @Transactional
    override fun <T : Appointment> save(
        appointmentCommand: AppointmentCommand,
        returnType: KClass<out T>
    ): T {
        val existed = dao.findById(appointmentCommand.id)
        val existedUsers = userDao.findAllByIds(appointmentCommand.participants.map { it.userId })
        val saved =
            dao.save(
                appointmentCommand.toEntity(
                    existed = existed,
                    participantUserEntities = existedUsers,
                ),
            )

        // 약속 생성 시 자동으로 미션테이블 insert
        saved.participants.forEach { ptcp: AppointmentUserEntity ->
            val apntMisn =
                AppointmentMissionCommand(
                    appointmentId = AppointmentId(saved.appointmentId),
                    userId = UserId(ptcp.user.userId),
                    missionStepOneCompleteAt = null,
                    missionStepTwoCompleteAt = null,
                ).toMissionEntity(
                    appointment = saved,
                    participants = ptcp.user,
                    missionStepOneComplateAt = null,
                    missionStepTwoCompleteAt = null,
                )
            appointmentMissionDao.save(apntMisn)
        }
        return saved.toDomain(returnType)
    }

    @Transactional
    override fun <T : Appointment> findAllByUserId(
        userId: UserId,
        returnType: KClass<out T>
    ): List<T> = dao.findByUserId(userId).map { it.toDomain(returnType) }

    @Transactional
    override fun <T : Appointment> findBy(
        id: AppointmentId,
        returnType: KClass<out T>
    ): T? = dao.findById(id)?.toDomain(returnType)

    private fun AppointmentCommand.toEntity(
        existed: AppointmentEntity?,
        participantUserEntities: Collection<UserEntity>
    ) = existed?.apply {
        this.ownerId = this@toEntity.ownerId.value
        this.name = this@toEntity.name
        this.appointmentAddress = destinationAddress.toEntity(existed.appointmentAddress)
        this.appointmentTime = this@toEntity.appointmentTime
        this.participants = this@toEntity.toAppointmentUserEntity(this, participantUserEntities)
        this.deleted = this@toEntity.deleted
    } ?: AppointmentEntity(
        appointmentId = id.value,
        ownerId = ownerId.value,
        name = name,
        appointmentAddress = destinationAddress.toEntity(null),
        appointmentTime = appointmentTime,
        participants = mutableSetOf(),
        deleted = deleted,
    ).apply { this.participants = this@toEntity.toAppointmentUserEntity(this, participantUserEntities) }

    private fun AppointmentCommand.toAppointmentUserEntity(
        existed: AppointmentEntity,
        participants: Collection<UserEntity>
    ): Set<AppointmentUserEntity> =
        with(this.participants.associate { it.userId.value to it.departureAddress }) {
            participants.mapNotNull {
                AppointmentUserEntity(
                    id = AppointmentUserId(appointmentId = existed.appointmentId, userId = it.userId),
                    appointment = existed,
                    user = it,
                    departureAddress = this[it.userId]?.toEntity(null) ?: return@mapNotNull null,
                )
            }.toSet()
        }

    private fun AppointmentMissionCommand.toMissionEntity(
        appointment: AppointmentEntity,
        participants: UserEntity,
        missionStepOneComplateAt: Instant?,
        missionStepTwoCompleteAt: Instant?
    ): AppointmentMissionEntity =
        AppointmentMissionEntity(
            id =
                AppointmentMissionId(
                    appointmentId = appointment.appointmentId,
                    userId = participants.userId,
                    apntMisnId = null,
                ),
            appointment = appointment,
            user = participants,
            missionStepOneCompleteAt = missionStepOneComplateAt,
            missionStepTwoCompleteAt = missionStepTwoCompleteAt,
        )

    private fun AppointmentAddress.toEntity(existed: AppointmentAddressEntity?) =
        AppointmentAddressEntity(
            id = existed?.id ?: id.value,
            cityOrProvince = address.cityOrProvince,
            districtOrCity = address.districtOrCity,
            postalCode = address.postalCode,
            jibunAddress = address.jibunAddress,
            roadAddress = address.roadAddress,
            x = address.x,
            y = address.y,
        )

    @Suppress("UNCHECKED_CAST")
    private fun <T : Appointment> AppointmentEntity.toDomain(clazz: KClass<out T>): T =
        when (clazz) {
            AppointmentCommand::class ->
                AppointmentCommand(
                    id = AppointmentId(appointmentId),
                    ownerId = UserId(ownerId),
                    name = name,
                    destinationAddress = appointmentAddress.toDomain(),
                    appointmentTime = appointmentTime,
                    participants =
                        participants.map {
                            AppointmentCommand.Participant(
                                userId = UserId(it.user.userId),
                                departureAddress =
                                    AppointmentAddress(
                                        id = AppointmentAddressId(it.departureAddress.id),
                                        address = it.departureAddress.toDomain().address,
                                    ),
                            )
                        }.toSet(),
                    deleted = deleted,
                ) as T

            AppointmentQuery::class ->
                AppointmentQuery(
                    id = AppointmentId(appointmentId),
                    ownerId = UserId(ownerId),
                    name = name,
                    destinationAddress = appointmentAddress.toDomain(),
                    appointmentTime = appointmentTime,
                    participants = participants.map { UserId(it.user.userId) },
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    deleted = deleted,
                ) as T

            else -> throw IllegalArgumentException("Unsupported type: ${clazz.qualifiedName}")
        }

    private fun AppointmentAddressEntity.toDomain() =
        AppointmentAddress.create(
            id = AppointmentAddressId(id),
            cityOrProvince = cityOrProvince,
            districtOrCity = districtOrCity,
            postalCode = postalCode,
            jibunAddress = jibunAddress,
            roadAddress = roadAddress,
            x = x,
            y = y,
            undergroundYn = "N",
            buildingName = "",
        )

    // 미션 생성 관련 처리
//    private fun MissionCommand.toEntity() =
//        MissionEntity(
//            missionId = this.missionId,
//            missionContents = this.missionContents,
//            contentType = this.contentType,
//            createAt = ZonedDateTime.now().toInstant()
//        )
}
