package l1a.jjakkak.core.domain.user.repository

import l1a.jjakkak.core.domain.user.UserCommand
import l1a.jjakkak.core.domain.user.UserId

interface UserCommandRepository {
    fun save(user: UserCommand): UserCommand

    fun findById(userId: UserId): UserCommand?

    fun getById(userId: UserId): UserCommand = findById(userId) ?: throw NoSuchElementException("User not found")
}
