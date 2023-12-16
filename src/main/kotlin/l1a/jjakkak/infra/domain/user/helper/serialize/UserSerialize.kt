package l1a.jjakkak.infra.domain.user.helper.serialize

import l1a.jjakkak.core.domain.user.User
import l1a.jjakkak.infra.domain.auth.helper.serialize.AuthenticationSerialize
import l1a.jjakkak.infra.domain.user.entity.UserEntity


interface UserSerialize: AuthenticationSerialize {
    fun User.toEntity() =
        UserEntity(
            userId = id,
            authenticationEntity = authentication.toEntity()
        )

}