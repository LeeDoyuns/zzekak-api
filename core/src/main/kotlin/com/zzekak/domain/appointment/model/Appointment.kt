package com.zzekak.domain.appointment.model

import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.common.IdTypeUUID
import com.zzekak.domain.user.UserId
import java.time.Instant
import java.util.UUID

@JvmInline
value class AppointmentId(override val value: UUID) : IdTypeUUID

sealed interface Appointment

/**
 * 연관 객체는 ID 의 형태로 관리
 * 저장을 할 때 사용한다.
 */
data class AppointmentCommand(
    val id: AppointmentId,
    val ownerId: UserId,
    val name: String,
    val address: AppointmentAddress,
    val appointmentTime: Instant,
    val participants: List<UserId>,
    val deleted: Boolean
) : Appointment {
    fun join(userId: UserId): AppointmentCommand = this.copy(participants = participants + userId)
}

/**
 * 조회 모델의 일종
 * 아직 데이터를 가져오는 목적이 다양하지 않아서 모든 데이터를 포함했지만,
 * 추후 용도에 따라 여러 조회 모델로 다변화될 수 있다.
 */
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
) : Appointment
