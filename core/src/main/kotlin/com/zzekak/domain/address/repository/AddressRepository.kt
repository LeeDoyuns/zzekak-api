package com.zzekak.domain.address.repository

import com.zzekak.core.domain.address.model.AdditionalAddressInfo
import com.zzekak.core.domain.address.model.Coordinate
import com.zzekak.core.domain.address.model.SearchedAddress
import org.springframework.stereotype.Service

@Service
interface AddressRepository {
    // 주소검색
    fun findAddressByKeyword(keyword: String): List<SearchedAddress>

    // 좌표검ㅅ색
    fun findCoordinateByAddressInfo(addrObj: AdditionalAddressInfo): Coordinate
}
