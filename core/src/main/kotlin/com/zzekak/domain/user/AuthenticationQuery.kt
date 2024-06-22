package com.zzekak.domain.user

import com.zzekak.domain.common.IdTypeString
import java.time.Instant

@JvmInline
value class AuthenticationId(override val value: String) : IdTypeString

data class AuthenticationCommand(
    val id: AuthenticationId,
    val type: AuthenticationType
)

data class AuthenticationQuery(
    val id: AuthenticationId,
    val type: AuthenticationType,
    val createdAt: Instant,
    val updatedAt: Instant
)
