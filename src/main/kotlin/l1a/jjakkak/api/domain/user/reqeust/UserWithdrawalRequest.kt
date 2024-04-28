package l1a.jjakkak.api.domain.user.reqeust

import com.auth0.jwt.JWT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import l1a.jjakkak.api.config.exception.ExceptionEnum
import l1a.jjakkak.api.config.exception.ZzekakException
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.message.WithdrawalMessage
import java.util.UUID

@JsonDeserialize
data class UserWithdrawalRequest(
    @JsonProperty("token")
    val token: String,
) {
    fun toMessage(): WithdrawalMessage =
        WithdrawalMessage(
            userId = UserId(decodeToken(token)),
        )

    private fun decodeToken(token: String): UUID =
        try {
            UUID.fromString(JWT.decode(token).subject)
        } catch (e: Exception) {
            throw ZzekakException(ExceptionEnum.ILLEGAL_TOKEN)
        }
}
