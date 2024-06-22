package com.zzekak.domain.address.response

import com.zzekak.domain.address.model.Coordinate

internal data class CoordinateResponse(
    val x: String,
    val y: String
) {
    companion object {
        fun from(src: Coordinate): CoordinateResponse = CoordinateResponse(x = src.x, y = src.y)
    }
}
