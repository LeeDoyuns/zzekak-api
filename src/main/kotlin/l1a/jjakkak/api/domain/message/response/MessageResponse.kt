package l1a.jjakkak.api.domain.message.response

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.jjakkak.core.domain.message.model.SmsResponse

internal data class MessageResponse(
    @JsonProperty("result_code")
    val result_code: Int,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("msg_id")
    val msg_id: Int,
    @JsonProperty("success_cnt")
    val success_cnt: Int,
    @JsonProperty("error_cnt")
    val error_cnt: Int,
    @JsonProperty("msg_type")
    val msg_type: String
) {
    companion object {
        fun from(res: SmsResponse) =
            MessageResponse(
                result_code = res.result_code,
                message = res.message,
                msg_id = res.msg_id,
                success_cnt = res.success_cnt,
                error_cnt = res.error_cnt,
                msg_type = res.msg_type
            )
    }

}