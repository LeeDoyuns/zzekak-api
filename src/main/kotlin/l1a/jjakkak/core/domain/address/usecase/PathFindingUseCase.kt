package l1a.jjakkak.core.domain.address.usecase

import l1a.jjakkak.core.domain.address.model.PathFindResponse
import l1a.jjakkak.core.domain.address.repository.PathFindingRepository
import org.springframework.stereotype.Service

@Service
class PathFindingUseCase(
    private val findPathRepository: PathFindingRepository
) {
    fun findPath(strtLocX: String, strtLocY: String, endLocX: String, endLocY: String): PathFindResponse
     = findPathRepository.findPath(strtLocX, strtLocY, endLocX, endLocY)

}
