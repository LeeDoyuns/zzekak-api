package l1a.jjakkak.core.domain.user.repository

import l1a.jjakkak.core.domain.user.User

interface UserRepository {
    fun createUser(user: User): User
}