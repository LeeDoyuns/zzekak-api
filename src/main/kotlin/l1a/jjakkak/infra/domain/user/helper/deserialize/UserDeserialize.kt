package l1a.jjakkak.infra.domain.user.helper.deserialize

import l1a.jjakkak.core.domain.user.User
import l1a.jjakkak.infra.domain.auth.helper.deserialize.AuthenticationDeserialize
import l1a.jjakkak.infra.domain.user.entity.UserEntity

interface UserDeserialize: AuthenticationDeserialize {
    fun UserEntity.toDomain(): User =
        User.create(
            id = userId,
            authentication = authenticationEntity.toDomain(),
            createdAt = createdAt!!,
            updatedAt = updatedAt!!
        )
}