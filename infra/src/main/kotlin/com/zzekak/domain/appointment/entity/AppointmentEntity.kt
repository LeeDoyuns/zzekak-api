package com.zzekak.domain.appointment.entity

import com.zzekak.domain.address.entity.AppointmentAddressEntity
import com.zzekak.domain.appointment.entity.AppointmentEntity.Companion.TABLE_APPOINTMENT
import com.zzekak.domain.common.entity.AuditableBase
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = TABLE_APPOINTMENT)
class AppointmentEntity(
    @Id @Column(name = COLUMN_APPOINTMENT_ID)
    val appointmentId: UUID,
    @Column(name = COLUMN_OWNER_ID)
    var ownerId: UUID,
    @Column(name = COLUMN_NAME)
    var name: String,
    @JoinColumn(name = COLUMN_APPOINTMENT_ADDRESS_ID)
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var appointmentAddress: AppointmentAddressEntity,
    @Column(name = COLUMN_APPOINTMENT_TIME)
    var appointmentTime: Instant,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment", cascade = [CascadeType.ALL], orphanRemoval = true)
    var participants: MutableSet<AppointmentUserEntity> = mutableSetOf(),
    @Column(name = COLUMN_DELETED)
    var deleted: Boolean
) : AuditableBase() {
    companion object {
        const val TABLE_APPOINTMENT = "appointment"
        const val COLUMN_APPOINTMENT_ID = "appointment_id"
        const val COLUMN_OWNER_ID = "owner_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_APPOINTMENT_ADDRESS_ID = "appointment_address_id"
        const val COLUMN_APPOINTMENT_TIME = "appointment_time"
        const val COLUMN_DELETED = "deleted"
    }
}
