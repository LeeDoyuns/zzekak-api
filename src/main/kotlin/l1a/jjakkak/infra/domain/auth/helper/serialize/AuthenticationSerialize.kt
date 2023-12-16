package l1a.jjakkak.infra.domain.auth.helper.serialize

import l1a.jjakkak.core.domain.auth.Authentication
import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity

interface AuthenticationSerialize {
    fun Authentication.toEntity() =
        AuthenticationEntity(
            providerId = "",
            type = type
        )
}