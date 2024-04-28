package l1a.jjakkak.api.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import l1a.jjakkak.core.domain.user.AuthenticationId
import l1a.jjakkak.core.domain.user.AuthenticationQuery
import l1a.jjakkak.core.domain.user.AuthenticationType
import java.time.Instant

@JsonSerialize
internal data class AuthenticationContent(
    @JsonProperty("id")
    val id: AuthenticationId,
    @JsonProperty("type")
    val type: AuthenticationType,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
    val updatedAt: Instant
) {
    companion object {
        fun from(src: AuthenticationQuery) =
            with(src) {
                AuthenticationContent(
                    id = id,
                    type = type,
                    createdAt = createdAt,
                    updatedAt = updatedAt
                )
            }
    }
}
