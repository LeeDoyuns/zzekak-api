package l1a.jjakkak.core.domain.user.usecase

import l1a.jjakkak.core.domain.user.User

interface CreateUserUseCase {
    fun createUser(): User
}