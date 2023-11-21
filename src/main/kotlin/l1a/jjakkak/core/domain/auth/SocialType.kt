package l1a.jjakkak.core.domain.auth

enum class SocialType(val code: String) {
    KAKAO(code = "ka"),
    APPLE(code = "ap");

    companion object {
        fun from(code: String): SocialType =
            SocialType.values().firstOrNull { it.code == code }
                ?: throw IllegalArgumentException("$code is illegal Argument [SocialType]")
    }
}