package com.zzekak.domain.address.model

import com.zzekak.core.domain.address.model.SearchedAddress

internal data class AddressResponse(
    val results: Results?
)

internal data class Results(
    val common: Common?,
    val juso: List<Juso>?
)

internal data class Common(
    val errorMessage: String?,
    val countPerPage: String?,
    val totalCount: String?,
    val errorCode: String?,
    val currentPage: String?,
)

// 해당 클래스는 좌표검색과 공용으로 사용하므로 기본값을 빈값으로 한다.
internal data class Juso(
    val detBdNmList: String = "",
    val engAddr: String = "",
    val rn: String = "",
    val emdNm: String = "",
    val zipNo: String = "",
    val roadAddrPart2: String = "",
    val emdNo: String = "",
    val sggNm: String = "",
    val jibunAddr: String = "",
    val siNm: String = "",
    val roadAddrPart1: String = "",
    val bdNm: String = "",
    val admCd: String = "",
    val udrtYn: String = "",
    val lnbrMnnm: String = "",
    val roadAddr: String = "",
    val lnbrSlno: String = "",
    val buldMnnm: String = "",
    val bdKdcd: String = "",
    val liNm: String = "",
    val rnMgtSn: String = "",
    val mtYn: String = "",
    val bdMgtSn: String = "",
    val buldSlno: String = "",
    // x좌표
    val entX: String = "0.0",
    // y좌표
    val entY: String = "0.0",
)

data class AddressObject(
    override val cityOrProvince: String,
    override val districtOrCity: String,
    override val postalCode: String,
    override val jibunAddress: String,
    override val roadAddress: String,
    override val administrativeCode: String,
    override val roadNameCode: String,
    override val undergroundIndicator: String,
    override val buildingMainNumber: String,
    override val buildingSubNumber: String,
) : SearchedAddress
