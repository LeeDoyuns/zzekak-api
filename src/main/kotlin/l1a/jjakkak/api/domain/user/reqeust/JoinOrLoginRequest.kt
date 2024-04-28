package l1a.jjakkak.api.domain.user.reqeust

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import l1a.jjakkak.api.domain.user.dto.AuthenticationTypeDto
import l1a.jjakkak.core.domain.user.AuthenticationType
import l1a.jjakkak.core.domain.user.message.JoinOrLoginMessage

@JsonDeserialize
internal data class JoinOrLoginRequest(
    @JsonProperty("token")
    val token: String,
    @JsonProperty("authenticationType")
    val authenticationType: AuthenticationTypeDto
) {
    fun toMessage(): JoinOrLoginMessage =
        JoinOrLoginMessage(
            token = token,
            type = AuthenticationType.from(authenticationType.code)
        )
}
