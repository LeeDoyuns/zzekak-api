package l1a.jjakkak.core.domain.user.message

import l1a.jjakkak.core.domain.user.AuthenticationType

data class JoinOrLoginMessage(
    val token: String,
    val type: AuthenticationType
)
