package l1a.jjakkak.core.domain.user

import l1a.jjakkak.core.domain.auth.SocialAuthentication
import java.time.Instant
import java.util.UUID

interface User {
    val id: UUID
    val authentication: SocialAuthentication
    val createdAt: Instant

    companion object {
        fun create(
            id: UUID,
            authentication: SocialAuthentication,
            createdAt: Instant
        ): User = UserImpl(
            id = id,
            authentication = authentication,
            createdAt = createdAt
        )
    }
}

internal data class UserImpl(
    override val id: UUID,
    override val authentication: SocialAuthentication,
    override val createdAt: Instant
) : User