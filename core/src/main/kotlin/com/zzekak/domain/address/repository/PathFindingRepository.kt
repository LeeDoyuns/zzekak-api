package com.zzekak.domain.address.repository

import l1a.jjakkak.core.domain.address.model.PathFindResponse
import org.springframework.stereotype.Service

@Service
interface PathFindingRepository {
    //길찾기(경로탐색)
    fun findPath(strtLocX: String, strtLocY: String, endLocX: String, endLocY: String): PathFindResponse
}
