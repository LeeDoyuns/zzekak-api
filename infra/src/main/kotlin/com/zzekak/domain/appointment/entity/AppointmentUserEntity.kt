package com.zzekak.domain.appointment.entity

import com.zzekak.domain.address.entity.AppointmentAddressEntity
import com.zzekak.domain.appointment.entity.AppointmentUserEntity.Companion.TABLE_USER_ADDRESS
import com.zzekak.domain.common.entity.AuditableBase
import com.zzekak.domain.user.entity.UserEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = TABLE_USER_ADDRESS)
class AppointmentUserEntity(
    @EmbeddedId
    val id: AppointmentUserId,
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("appointmentId")
    @JoinColumn(name = AppointmentEntity.COLUMN_APPOINTMENT_ID)
    val appointment: AppointmentEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = UserEntity.COLUMN_USER_ID)
    val user: UserEntity,
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = COLUMN_DEPARTURE_ADDRESS)
    var departureAddress: AppointmentAddressEntity
) : AuditableBase() {
    companion object {
        const val TABLE_USER_ADDRESS = "appointment_user"
        const val COLUMN_DEPARTURE_ADDRESS = "departure_address_id"
    }
}

@Embeddable
class AppointmentUserId(
    var appointmentId: UUID,
    var userId: UUID
) : Serializable
