package com.zzekak.domain.address.model

internal data class CoordinateResponse(
    val x: String,
    val y: String
) {
    companion object {
        fun from(src: Coordinate): CoordinateResponse = CoordinateResponse(x = src.x, y = src.y)
    }
}
