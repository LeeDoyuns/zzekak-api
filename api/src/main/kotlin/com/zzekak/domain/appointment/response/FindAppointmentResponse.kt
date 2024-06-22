package com.zzekak.domain.appointment.response

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.common.response.AddressResponse
import com.zzekak.domain.user.UserId
import java.time.Instant

internal data class FindAppointmentResponse(
    val content: List<FindAppointmentContent>
) {
    companion object {
        fun from(src: Collection<AppointmentQuery>): FindAppointmentResponse =
            FindAppointmentResponse(
                content = src.map(FindAppointmentContent::from),
            )
    }
}

internal data class FindAppointmentContent(
    val id: AppointmentId,
    val appointmentName: String,
    val address: AddressResponse,
    val appointmentTime: Instant,
    val participants: List<UserId>,
) {
    companion object {
        fun from(src: AppointmentQuery): FindAppointmentContent =
            FindAppointmentContent(
                id = src.id,
                appointmentName = src.name,
                address = AddressResponse.from(src.address),
                appointmentTime = src.appointmentTime,
                participants = src.participants,
            )
    }
}
