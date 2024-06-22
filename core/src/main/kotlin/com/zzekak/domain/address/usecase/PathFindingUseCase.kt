package com.zzekak.domain.address.usecase

import com.zzekak.domain.address.model.SearchedPathResponse
import com.zzekak.domain.address.repository.PathFindingRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class PathFindingUseCase(
    private val findPathRepository: PathFindingRepository
) {
    fun findPath(
        strtLocX: String,
        strtLocY: String,
        endLocX: String,
        endLocY: String,
        appointmentTime: ZonedDateTime
    ): SearchedPathResponse = findPathRepository.findPath(strtLocX, strtLocY, endLocX, endLocY, appointmentTime)
}
