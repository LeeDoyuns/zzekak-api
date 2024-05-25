package com.zzekak.domain.address.usecase

import com.zzekak.core.domain.address.model.AdditionalAddressInfo
import com.zzekak.core.domain.address.model.Coordinate
import com.zzekak.domain.address.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class FindAddressCoordinateUseCase(
    val addressRepository: AddressRepository
) {
    fun findCoordinateByAddressInfo(additionalAddressInfo: AdditionalAddressInfo): Coordinate =
        addressRepository.findCoordinateByAddressInfo(additionalAddressInfo)
}
