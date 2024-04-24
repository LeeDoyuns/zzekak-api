package l1a.jjakkak.core.domain.message.model

class SmsResponse(
    val result_code: Int,
    val message: String,
    val msg_id: Int,
    val success_cnt: Int,
    val error_cnt: Int,
    val msg_type: String
) {
}