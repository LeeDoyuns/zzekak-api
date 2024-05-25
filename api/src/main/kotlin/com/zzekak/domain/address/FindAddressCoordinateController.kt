package com.zzekak.domain.address

import com.zzekak.ApiUrl
import com.zzekak.domain.address.request.CoordinateSearchRequest
import com.zzekak.domain.address.usecase.FindAddressCoordinateUseCase
import l1a.com.zzekak.domain.address.response.CoordinateResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface FindAddressCoordinateController {
    @GetMapping(ApiUrl.ADDRESS_COORDINATE_SEARCH)
    fun findAddressCoordinate(
        @ModelAttribute request: CoordinateSearchRequest
    ): CoordinateResponse
}

@RestController
internal class FindAddressCoordinateControllerImpl(
    private val findAddressCoordinateUseCase: FindAddressCoordinateUseCase
) : FindAddressCoordinateController {
    override fun findAddressCoordinate(request: CoordinateSearchRequest): CoordinateResponse =
        findAddressCoordinateUseCase.findCoordinateByAddressInfo(request.toDomain())
            .run { CoordinateResponse.from(this) }
}
