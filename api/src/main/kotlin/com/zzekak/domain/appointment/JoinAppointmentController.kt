/*
 * Zzekak
 * Sir.LOIN Intellectual property. All rights reserved.
 */
package com.zzekak.domain.appointment

import com.zzekak.ApiUrl
import com.zzekak.PathVariable.APPOINTMENT
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.response.FindAppointmentContent
import com.zzekak.domain.appointment.usecase.JoinAppointmentUseCase
import com.zzekak.domain.user.UserId
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * @since 2024-06-22
 */
internal interface JoinAppointmentController {
    @PutMapping(ApiUrl.JOIN_APPOINTMENT)
    fun join(
        @AuthenticationPrincipal userId: UUID,
        @PathVariable(APPOINTMENT) appointmentId: UUID
    ): FindAppointmentContent
}

@RestController
internal class JoinAppointmentControllerImpl(
    val joinAppointmentUseCase: JoinAppointmentUseCase
) : JoinAppointmentController {
    override fun join(
        @AuthenticationPrincipal userId: UUID,
        @PathVariable(APPOINTMENT) appointmentId: UUID
    ): FindAppointmentContent =
        joinAppointmentUseCase.join(UserId(userId), AppointmentId(appointmentId))
            .run { FindAppointmentContent.from(this) }
}
