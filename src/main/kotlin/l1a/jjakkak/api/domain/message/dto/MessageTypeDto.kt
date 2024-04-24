package l1a.jjakkak.api.domain.message.dto

enum class MessageTypeDto(
    val code: String
) {
    SMS(code = "M"),
    KAKAO(code = "K");

}