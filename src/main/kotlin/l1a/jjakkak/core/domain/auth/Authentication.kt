package l1a.jjakkak.core.domain.auth

import java.time.Instant

interface Authentication {
    val providerId: String
    val type: AuthenticationType
    val createdAt: Instant
    val updatedAt: Instant

    companion object {
        fun create(
            providerId: String,
            type: AuthenticationType,
            createdAt: Instant,
            updatedAt: Instant
        ): Authentication =
            AuthenticationImpl(
                providerId = providerId,
                type = type,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
    }
}

internal class AuthenticationImpl(
    override val providerId: String,
    override val type: AuthenticationType,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : Authentication
