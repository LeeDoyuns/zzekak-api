package com.zzekak.domain.address.model

import com.fasterxml.jackson.annotation.JsonProperty

internal data class PathFindResponse (
    @JsonProperty("geocoder_status")
    val geocoded_waypoints: String,
//    @JsonProperty("routes")
//    val routes: Routes
)

