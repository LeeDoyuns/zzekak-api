package com.zzekak.domain.address.request

import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId
import java.util.UUID

internal data class AddressRequest(
    val cityOrProvince: String,
    val districtOrCity: String,
    val postalCode: String,
    val jibunAddress: String,
    val roadAddress: String,
    val x: String,
    val y: String,
    val undergroundYn: String,
    val buildingName: String
) {
    fun toDomain(): AppointmentAddress =
        AppointmentAddress.create(
            id = AppointmentAddressId(UUID.randomUUID()),
            cityOrProvince = cityOrProvince,
            districtOrCity = districtOrCity,
            postalCode = postalCode,
            roadAddress = roadAddress,
            x = x,
            y = y,
            buildingName = buildingName,
            undergroundYn = undergroundYn,
            jibunAddress = jibunAddress,
        )
}
