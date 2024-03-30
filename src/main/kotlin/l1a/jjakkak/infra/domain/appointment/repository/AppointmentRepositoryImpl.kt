package l1a.jjakkak.infra.domain.appointment.repository

import l1a.jjakkak.core.domain.address.model.AppointmentAddressId
import l1a.jjakkak.core.domain.address.model.AppointmentAddress
import l1a.jjakkak.core.domain.appointment.model.AppointmentCommand
import l1a.jjakkak.core.domain.appointment.model.AppointmentId
import l1a.jjakkak.core.domain.appointment.model.AppointmentQuery
import l1a.jjakkak.core.domain.appointment.repository.AppointmentRepository
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.infra.domain.address.entity.AppointmentAddressEntity
import l1a.jjakkak.infra.domain.appointment.dao.AppointmentEntityDao
import l1a.jjakkak.infra.domain.appointment.entity.AppointmentEntity
import l1a.jjakkak.infra.domain.user.dao.UserEntityDao
import l1a.jjakkak.infra.domain.user.entity.UserEntity
import l1a.jjakkak.infra.domain.user.helper.deserialize.UserDeserialize
import l1a.jjakkak.infra.domain.user.helper.serialize.UserSerialize
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class AppointmentRepositoryImpl(
    val dao: AppointmentEntityDao,
    val userDao: UserEntityDao
) : AppointmentRepository, UserSerialize, UserDeserialize {
    @Transactional
    override fun save(appointmentCommand: AppointmentCommand): AppointmentQuery {
        val existed = dao.findById(appointmentCommand.id)
        val existedUsers = userDao.findAllByIds(appointmentCommand.participants)

        val saved = dao.save(
            appointmentCommand.toEntity(
                existed = existed,
                participants = existedUsers
            )
        )

        return saved.toDomain()
    }

    private fun AppointmentCommand.toEntity(
        existed: AppointmentEntity?,
        participants: Collection<UserEntity>
    ) = AppointmentEntity(
        appointmentId = id.value,
        ownerId = ownerId.value,
        name = name,
        appointmentAddress = address.toEntity(existed?.appointmentAddress),
        appointmentTime = appointmentTime,
        participants = participants.toSet(),
        deleted = false
    )

    private fun AppointmentAddress.toEntity(existed: AppointmentAddressEntity?) =
        AppointmentAddressEntity(
            id = existed?.id ?: id.value,
            cityOrProvince = address.cityOrProvince,
            districtOrCity = address.districtOrCity,
            postalCode = address.postalCode,
            jibunAddress = address.jibunAddress,
            roadAddress = address.roadAddress,
            x = coordinate.x,
            y = coordinate.y
        )

    private fun AppointmentEntity.toDomain() =
        AppointmentQuery.create(
            id = AppointmentId(appointmentId),
            ownerId = UserId(ownerId),
            name = name,
            address = appointmentAddress.toDomain(),
            appointmentTime = appointmentTime,
            participants = participants.map { it.userId },
            createdAt = createdAt,
            updatedAt = updatedAt,
            deleted = deleted
        )

    private fun AppointmentAddressEntity.toDomain() =
        AppointmentAddress.create(
            id = AppointmentAddressId(id),
            cityOrProvince = cityOrProvince,
            districtOrCity = districtOrCity,
            postalCode = postalCode,
            jibunAddress = jibunAddress,
            roadAddress = roadAddress,
            x = x,
            y = y
        )
}