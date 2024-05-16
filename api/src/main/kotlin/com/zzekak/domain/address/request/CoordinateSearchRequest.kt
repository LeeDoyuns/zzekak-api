package com.zzekak.domain.address.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.zzekak.core.domain.address.model.AdditionalAddressInfo

internal data class CoordinateSearchRequest(
    @JsonProperty("administrativeCode")
    val administrativeCode: String,
    @JsonProperty("roadNameCode")
    val roadNameCode: String,
    @JsonProperty("undergroundIndicator")
    val undergroundIndicator: String,
    @JsonProperty("buildingMainNumber")
    val buildingMainNumber: String,
    @JsonProperty("buildingSubNumber")
    val buildingSubNumber: String
) {
    fun toDomain(): AdditionalAddressInfo =
        AdditionalAddressInfo.create(
            administrativeCode = administrativeCode,
            roadNameCode = roadNameCode,
            undergroundIndicator = undergroundIndicator,
            buildingMainNumber = buildingMainNumber,
            buildingSubNumber = buildingSubNumber,
        )
}