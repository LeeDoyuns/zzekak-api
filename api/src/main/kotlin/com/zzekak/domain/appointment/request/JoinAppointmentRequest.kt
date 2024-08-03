package com.zzekak.domain.appointment.request

import com.zzekak.domain.address.request.AddressRequest

/**
 * @since 2024-07-28
 */
internal data class JoinAppointmentRequest(
    val appointDepartureAddress: AddressRequest
)
