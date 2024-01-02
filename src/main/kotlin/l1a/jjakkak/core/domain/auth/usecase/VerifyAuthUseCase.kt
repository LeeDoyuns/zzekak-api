package l1a.jjakkak.core.domain.auth.usecase

import l1a.jjakkak.core.domain.user.message.JoinOrLoginMessage
import org.springframework.stereotype.Service

interface VerifyAuthUseCase {
    fun verify(message: JoinOrLoginMessage)
}

@Service
class VerifyAuthUseCaseImpl(): VerifyAuthUseCase {
    override fun verify(message: JoinOrLoginMessage) {
        message.type.decode(message.token)
    }
}

