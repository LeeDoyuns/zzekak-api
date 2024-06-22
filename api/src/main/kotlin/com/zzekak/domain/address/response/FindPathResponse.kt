package com.zzekak.domain.address.response

import com.zzekak.domain.address.model.SearchedPathResponse

class FindPathResponse(
    val content: SearchedPathResponse
) {
    companion object {
        fun from(src: SearchedPathResponse): FindPathResponse =
            FindPathResponse(
                content = src,
            )
    }
}
