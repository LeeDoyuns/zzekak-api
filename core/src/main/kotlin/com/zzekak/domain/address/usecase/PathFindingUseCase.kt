package com.zzekak.domain.address.usecase

import com.zzekak.domain.address.repository.PathFindingRepository
import l1a.jjakkak.core.domain.address.model.PathFindResponse
import org.springframework.stereotype.Service

@Service
class PathFindingUseCase(
    private val findPathRepository: PathFindingRepository
) {
    fun findPath(strtLocX: String, strtLocY: String, endLocX: String, endLocY: String): PathFindResponse
     = findPathRepository.findPath(strtLocX, strtLocY, endLocX, endLocY)

}
