package l1a.jjakkak.infra.domain.user.helper.serialize

import l1a.jjakkak.core.domain.user.AuthenticationCommand
import l1a.jjakkak.infra.domain.user.entity.AuthenticationEntity

interface AuthenticationSerialize {
    fun AuthenticationCommand.toEntity() =
        AuthenticationEntity(
            authenticationId = id.value,
            type = type,
        )
}