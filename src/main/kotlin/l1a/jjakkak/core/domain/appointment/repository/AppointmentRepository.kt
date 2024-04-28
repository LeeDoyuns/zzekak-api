package l1a.jjakkak.core.domain.appointment.repository

import l1a.jjakkak.core.domain.appointment.model.AppointmentCommand
import l1a.jjakkak.core.domain.appointment.model.AppointmentQuery
import l1a.jjakkak.core.domain.user.UserId

interface AppointmentRepository {
    fun save(appointmentCommand: AppointmentCommand): AppointmentQuery

    fun findAllByUserId(userId: UserId): List<AppointmentQuery>
}
