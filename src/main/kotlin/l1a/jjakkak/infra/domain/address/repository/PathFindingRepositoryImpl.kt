package l1a.jjakkak.infra.domain.address.repository

import l1a.jjakkak.core.domain.address.model.PathFindResponse
import l1a.jjakkak.core.domain.address.repository.PathFindingRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class PathFindingRepositoryImpl(
    @Value("\${lia.auth.google-api-key}")
    val googleApiKey: String,
) : PathFindingRepository {
    override fun findPath(strtLocX: String, strtLocY: String, endLocX: String, endLocY: String): PathFindResponse
     = ConnectionModule(googleApiKey).findPath(strtLocX, strtLocY, endLocX, endLocY)
}
