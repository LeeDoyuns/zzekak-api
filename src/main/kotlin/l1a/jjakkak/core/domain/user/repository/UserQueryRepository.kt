package l1a.jjakkak.core.domain.user.repository

import l1a.jjakkak.core.domain.user.AuthenticationId
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.UserQuery
import java.util.UUID

interface UserQueryRepository {
    fun findById(userId: UserId): UserQuery?

    fun getById(userId: UserId): UserQuery

    fun findUserByAuthenticationIdAndIsRemoved(
        authenticationId: AuthenticationId,
        isRemoved: Boolean
    ): UserQuery?

    fun findUserByUserIdAndIsRemoved(
        userId: UUID,
        isRemoved: Boolean
    ): UserQuery?
}
