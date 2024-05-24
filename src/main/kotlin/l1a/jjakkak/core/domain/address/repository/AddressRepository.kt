package l1a.jjakkak.core.domain.address.repository

import l1a.jjakkak.core.domain.address.model.SearchedAddress
import org.springframework.stereotype.Service

@Service
interface AddressRepository {
    // 주소검색
    fun findAddressByKeyword(keyword: String): SearchedAddress
}
