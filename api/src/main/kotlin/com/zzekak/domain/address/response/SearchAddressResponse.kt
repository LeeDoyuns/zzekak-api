package com.zzekak.domain.address.response

import com.zzekak.core.domain.address.model.SearchedAddress

internal data class SearchAddressResponse(
    val content: List<SearchedAddressContent>
) {
    companion object {
        fun from(src: Collection<SearchedAddress>): SearchAddressResponse =
            SearchAddressResponse(
                content = src.map { SearchedAddressContent.from(it) },
            )
    }
}
