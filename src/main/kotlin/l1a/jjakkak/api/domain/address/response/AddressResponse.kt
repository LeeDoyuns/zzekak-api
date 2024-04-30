package l1a.jjakkak.api.domain.address.response

import l1a.jjakkak.core.domain.address.model.SearchedAddress

internal data class AddressResponse(val content: SearchedAddress) {
    companion object {
        fun from(src: SearchedAddress): AddressResponse =
            AddressResponse(
                content = src
            )
    }
}
