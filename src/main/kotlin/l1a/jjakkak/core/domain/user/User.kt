package l1a.jjakkak.core.domain.user

import l1a.jjakkak.core.domain.auth.Authentication
import java.time.Instant
import java.util.UUID

interface User {
    val id: UUID
    val authentication: Authentication
    val createdAt: Instant
    val updatedAt: Instant

    companion object {
        fun create(
            id: UUID,
            authentication: Authentication,
            createdAt: Instant,
            updatedAt: Instant
        ): User = UserImpl(
            id = id,
            authentication = authentication,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}

internal data class UserImpl(
    override val id: UUID,
    override val authentication: Authentication,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : User