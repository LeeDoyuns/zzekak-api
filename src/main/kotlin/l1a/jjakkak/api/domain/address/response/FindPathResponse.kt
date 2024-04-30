package l1a.jjakkak.api.domain.address.response

import l1a.jjakkak.infra.domain.address.model.PathFindResponse

class FindPathResponse {
    companion object {
        fun from(src: PathFindResponse): FindPathResponse =
            FindPathResponse()
    }
}
