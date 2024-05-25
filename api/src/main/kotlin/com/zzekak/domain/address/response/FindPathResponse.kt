package com.zzekak.domain.address.response

import l1a.jjakkak.core.domain.address.model.PathFindResponse

class FindPathResponse {
    companion object {
        fun from(src: PathFindResponse): FindPathResponse =
            FindPathResponse()
    }
}
