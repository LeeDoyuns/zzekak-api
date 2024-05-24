package l1a.jjakkak.infra.domain.address.repository

import l1a.jjakkak.core.domain.address.model.SearchedPathResponse
import l1a.jjakkak.core.domain.address.repository.PathFindingRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
class PathFindingRepositoryImpl(
    @Value("\${lia.api-key.google-api-key}")
    val googleApiKey: String,
) : PathFindingRepository {
    override fun findPath(
        strtLocX: String,
        strtLocY: String,
        endLocX: String,
        endLocY: String,
        appointmentTime: ZonedDateTime
    ): SearchedPathResponse
       = ConnectionModule(googleApiKey).findPath(strtLocX, strtLocY, endLocX, endLocY, appointmentTime)
}
