package com.zzekak.domain.address

import com.zzekak.ApiUrl
import com.zzekak.domain.address.response.SearchAddressResponse
import com.zzekak.domain.address.usecase.FindAddressUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface FindAddressController {
    @GetMapping(ApiUrl.ADDRESS_SEARCH)
    fun findAddress(
        @RequestParam keyword: String
    ): SearchAddressResponse
}

@RestController
internal class FindAddressControllerImpl(
    private val findAddressUseCase: FindAddressUseCase
) : FindAddressController {
    override fun findAddress(keyword: String): SearchAddressResponse =
        findAddressUseCase.findAddressByKeyword(keyword).run { SearchAddressResponse.from(this) }
}
