package l1a.jjakkak.core.domain.user.usecase

import l1a.jjakkak.core.domain.user.User

interface GetUserUseCase {
    fun getUser(): User
}