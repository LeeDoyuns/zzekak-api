package com.zzekak.domain.address.repository

import com.zzekak.core.domain.address.model.AdditionalAddressInfo
import com.zzekak.core.domain.address.model.Address
import com.zzekak.core.domain.address.model.Coordinate
import com.zzekak.core.domain.address.model.SearchedAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class AddressRepositoryImpl(
    // 주소검색 apikey
    @Value("\${lia.api-key.addr}")
    val aConfmKey: String,
    @Value("\${lia.api-key.coord}")
    val cConfmKey: String
) : AddressRepository {
    /**
     * juso.go.kr 에서 제공하는 API 의 결과 값을 파싱, [Address] 에 담아서 반환한다.
     */
    override fun findAddressByKeyword(keyword: String): List<SearchedAddress> =
        ConnectionModule(aConfmKey).searchAddr(keyword)

    override fun findCoordinateByAddressInfo(addrObj: AdditionalAddressInfo): Coordinate =
        ConnectionModule(cConfmKey).searchCoordinate(addrObj)
}
