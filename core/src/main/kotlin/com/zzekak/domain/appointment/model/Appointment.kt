package com.zzekak.domain.appointment.model

import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.common.IdTypeUUID
import com.zzekak.domain.user.UserId
import java.time.Instant
import java.util.UUID

@JvmInline
value class AppointmentId(override val value: UUID) : IdTypeUUID

data class AppointmentCommand(
    val id: AppointmentId,
    val ownerId: UserId,
    val name: String,
    val address: AppointmentAddress,
    val appointmentTime: Instant,
    val participants: List<UserId>,
    val deleted: Boolean
)

data class AppointmentQuery(
    val id: AppointmentId,
    val ownerId: UserId,
    val name: String,
    val address: AppointmentAddress,
    val appointmentTime: Instant,
    val participants: List<UserId>,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deleted: Boolean
)
