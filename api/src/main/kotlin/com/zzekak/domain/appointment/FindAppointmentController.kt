package com.zzekak.domain.appointment

import com.zzekak.ApiUrl
import com.zzekak.domain.appointment.response.FindAppointmentResponse
import com.zzekak.domain.appointment.usecase.FindAppointmentUseCase
import com.zzekak.domain.user.UserId
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface FindAppointmentController {
    @GetMapping(ApiUrl.APPOINTMENT)
    fun findAll(
        @AuthenticationPrincipal userId: UUID
    ): FindAppointmentResponse
}

@RestController
internal class FindAppointmentControllerImpl(
    private val findAppointmentUseCase: FindAppointmentUseCase
) : FindAppointmentController {
    override fun findAll(userId: UUID): FindAppointmentResponse =
        findAppointmentUseCase.findAll(UserId(userId)).run { FindAppointmentResponse.from(this) }
}
