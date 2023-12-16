package l1a.jjakkak.infra.domain.auth.helper.deserialize

import l1a.jjakkak.core.domain.auth.Authentication
import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity

interface AuthenticationDeserialize {
    fun AuthenticationEntity.toDomain(): Authentication =
        Authentication.create(
            providerId = providerId,
            type = type,
            createdAt = createdAt!!,
            updatedAt = updatedAt!!
        )
}