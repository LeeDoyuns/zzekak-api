package l1a.jjakkak.core.domain.auth

import java.time.Instant

interface SocialAuthentication {
    val providerId: String
    val socialType: SocialType
    val createdAt: Instant

    companion object {
        fun create(
            providerId: String,
            socialType: SocialType,
            createdAt: Instant
        ): SocialAuthentication =
            SocialAuthenticationImpl(
                providerId = providerId,
                socialType = socialType,
                createdAt = createdAt
            )
    }
}

internal class SocialAuthenticationImpl(
    override val providerId: String,
    override val socialType: SocialType,
    override val createdAt: Instant
) : SocialAuthentication
