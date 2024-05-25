package com.zzekak.domain.address.usecase

import com.zzekak.core.domain.address.model.SearchedAddress
import com.zzekak.domain.address.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class FindAddressUseCase(
    private val addressRepository: AddressRepository
) {
    fun findAddressByKeyword(keyword: String): List<SearchedAddress> = addressRepository.findAddressByKeyword(keyword)
}
