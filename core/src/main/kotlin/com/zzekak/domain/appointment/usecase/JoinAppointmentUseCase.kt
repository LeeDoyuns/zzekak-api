/*
 * Zzekak
 * Sir.LOIN Intellectual property. All rights reserved.
 */
package com.zzekak.domain.appointment.usecase

import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.appointment.repository.AppointmentRepository
import com.zzekak.domain.user.UserId
import org.springframework.stereotype.Service

/**
 * @since 2024-06-22
 */
@Service
class JoinAppointmentUseCase(
    private val appointmentRepository: AppointmentRepository
) {
    fun join(
        userId: UserId,
        appointmentId: AppointmentId
    ): AppointmentQuery {
        val found =
            appointmentRepository.findBy(appointmentId, AppointmentCommand::class)
                ?: throw IllegalArgumentException("Appointment not found")

        return appointmentRepository.save(found.join(userId), AppointmentQuery::class)
    }
}
