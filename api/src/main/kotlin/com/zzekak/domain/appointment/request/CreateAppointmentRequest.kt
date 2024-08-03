package com.zzekak.domain.appointment.request

import com.zzekak.domain.address.request.AddressRequest
import java.time.ZonedDateTime
import java.util.UUID

internal data class CreateAppointmentRequest(
    val name: String = "",
    val destinationAddress: AddressRequest,
    val appointmentTime: ZonedDateTime,
    val participants: List<ParticipantContent>
) {
    data class ParticipantContent(
        val userId: UUID,
        val departureAddress: AddressRequest
    )
}
