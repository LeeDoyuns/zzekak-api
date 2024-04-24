package l1a.jjakkak.api

object ApiUrl {
    // Docs
    const val DOCS = "/docs"

    //HealthCheck
    const val HEALTH_CHECK = "/healthCheck"

    // User
    const val USER = "/user"
    const val USER_JOIN_OR_LOGIN = "$USER/joinOrLogin"
    const val USER_WITHDRAWAL = "$USER/withdrawal"
    const val USER_TOKEN_REFRESH = "$USER/token/refresh"

    // Appointment
    const val APPOINTMENT = "/appointment"
    const val CREATE_APPOINTMENT = "$APPOINTMENT/create"

    // address
    const val ADDRESS = "/address"
    const val ADDRESS_SEARCH = "$ADDRESS/search"

    private const val ADDRESS_COORDINATE = "$ADDRESS/coordinate"
    const val ADDRESS_COORDINATE_SEARCH = "$ADDRESS_COORDINATE/search"

    // message
    const val MESSAGE = "/message"
    const val MESSAGE_SEND = "$MESSAGE/send"

    //kakao_notification
    const val KAKAO_MESSAGE = "/kakaoMessage"
    const val KAKAO_MESSAGE_SEND = "$KAKAO_MESSAGE/send"
}