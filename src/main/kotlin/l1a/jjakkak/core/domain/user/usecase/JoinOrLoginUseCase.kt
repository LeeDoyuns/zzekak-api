package l1a.jjakkak.core.domain.user.usecase

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import l1a.jjakkak.core.domain.user.Agreement
import l1a.jjakkak.core.domain.user.AuthToken
import l1a.jjakkak.core.domain.user.AuthenticationCommand
import l1a.jjakkak.core.domain.user.AuthenticationId
import l1a.jjakkak.core.domain.user.AuthenticationType
import l1a.jjakkak.core.domain.user.Token
import l1a.jjakkak.core.domain.user.UserCommand
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.message.JoinOrLoginMessage
import l1a.jjakkak.core.domain.user.repository.AuthRepository
import l1a.jjakkak.core.domain.user.repository.UserCommandRepository
import l1a.jjakkak.core.domain.user.repository.UserQueryRepository
import l1a.jjakkak.core.domain.user.usecase.common.JwtMixin
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID

interface JoinOrLoginUseCase {
    fun joinOrLogin(message: JoinOrLoginMessage): Token
}

@Service
internal class JoinOrLoginUseCaseImpl(
    val userCommandRepo: UserCommandRepository,
    val userQueryRepo: UserQueryRepository,
    val authRepo: AuthRepository,
    @Value("\${lia.auth.private-key-path}")
    private val privateKey: String,
    @Value("\${lia.auth.public-key-path}")
    private val publicKey: String,
    @Value("\${lia.auth.social-login-app-key}")
    private val appKey: String
) : JoinOrLoginUseCase, JwtMixin, InitializingBean {
    lateinit var algorithm: Algorithm

    override fun afterPropertiesSet() {
        algorithm = Algorithm.RSA256(getPublicKey(publicKey), getPrivateKey(privateKey))
    }

    override fun joinOrLogin(message: JoinOrLoginMessage): Token {
        val (token, type) = message

        val decodedToken = type.decode(token)

        type.validate(
            token = token,
            appKey = appKey,
            rsaPublicKeyInfo = type.getRSAPublicKeyInfo(decodedToken),
        )

        val user =
            userQueryRepo
                .findUserByAuthenticationIdAndIsRemoved(AuthenticationId(decodedToken.payload.sub), false)?.id // 삭제여부 컬럼 추가
                ?: createUser(decodedToken, type).run { userCommandRepo.save(this).id }

        val (accessToken, refreshToken) = createToken(user.value, algorithm)

        return Token(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    private fun createUser(
        authToken: AuthToken,
        type: AuthenticationType
    ) = UserCommand.create(
        id = UserId(UUID.randomUUID()),
        name = "",
        authentication =
            AuthenticationCommand.create(
                id = AuthenticationId(authToken.payload.sub),
                type = type,
            ),
        agreement = Agreement.EMPTY,
    )

    private fun AuthenticationType.getRSAPublicKeyInfo(authToken: AuthToken): AuthRepository.RSAPublicKeyInfo =
        when (this) {
            AuthenticationType.KAKAO -> authRepo.getKakaoLoginPublicKey()
            AuthenticationType.APPLE -> authRepo.getAppleLoginPublicKey()
        }
            .find { it.kid == authToken.header.kid }
            ?: throw JWTVerificationException("Not Found Kakao PublicKeyInfo")
}
