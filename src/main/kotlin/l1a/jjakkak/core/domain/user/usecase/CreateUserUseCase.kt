package l1a.jjakkak.core.domain.user.usecase

import l1a.jjakkak.core.domain.user.User
import org.springframework.stereotype.Service

interface CreateUserUseCase {
    fun createUser(): User
}

@Service
internal class CreateUserUseCaseImpl(): CreateUserUseCase {
    override fun createUser(): User {
        TODO("유저 생성 및 이미 생성된 유저라면 예외")
    }
}