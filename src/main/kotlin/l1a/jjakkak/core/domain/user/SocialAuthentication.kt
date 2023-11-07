package l1a.jjakkak.core.domain.user

interface SocialAuthentication {

    enum class Type(val code: String) {
        KAKAO(code = "ka"),
        APPLE(code = "ap");
    }
}