package l1a.jjakkak.infra.domain.user.helper.deserialize

import l1a.jjakkak.core.domain.user.AuthenticationId
import l1a.jjakkak.core.domain.user.AuthenticationQuery
import l1a.jjakkak.infra.domain.user.entity.AuthenticationEntity

interface AuthenticationDeserialize {
    fun AuthenticationEntity.toDomain(): AuthenticationQuery =
        AuthenticationQuery.create(
            id = AuthenticationId(authenticationId),
            type = type,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
}