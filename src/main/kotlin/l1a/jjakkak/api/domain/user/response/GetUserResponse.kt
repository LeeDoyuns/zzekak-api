package l1a.jjakkak.api.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.UserQuery
import java.time.Instant

@JsonSerialize
internal data class GetUserResponse(
    @JsonProperty("id")
    val id: UserId,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("authentication")
    val authentication: AuthenticationContent,
    @JsonProperty("agreement")
    val agreement: AgreementContent,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
    val updatedAt: Instant,
) {
    companion object {
        fun from(src: UserQuery) =
            with(src) {
                GetUserResponse(
                    id = id,
                    name = name,
                    authentication = AuthenticationContent.from(authentication),
                    agreement = AgreementContent.from(agreement),
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }
    }
}
