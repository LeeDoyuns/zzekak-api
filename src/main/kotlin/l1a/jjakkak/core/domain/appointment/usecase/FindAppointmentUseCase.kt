package l1a.jjakkak.core.domain.appointment.usecase

import l1a.jjakkak.core.domain.appointment.model.AppointmentQuery
import l1a.jjakkak.core.domain.appointment.repository.AppointmentRepository
import l1a.jjakkak.core.domain.user.UserId
import org.springframework.stereotype.Service

@Service
class FindAppointmentUseCase(
    val appointmentRepository: AppointmentRepository
) {
    fun findAll(userId: UserId): List<AppointmentQuery> = appointmentRepository.findAllByUserId(userId)
}