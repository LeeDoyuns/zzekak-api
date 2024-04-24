package l1a.jjakkak.api.domain.message.request

import com.auth0.jwt.JWT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import l1a.jjakkak.api.config.exception.ExceptionEnum
import l1a.jjakkak.api.config.exception.ZzekakException
import l1a.jjakkak.core.domain.appointment.model.AppointmentId
import l1a.jjakkak.core.domain.message.model.SmsRequest
import java.util.*

@JsonDeserialize
data class MessageRequest(
    @JsonProperty("receiver")
    val receiver: String,    //수신번호. 다중발송의 경우 콤마(,)로 구분
    @JsonProperty("msg")
    val msg: String,        //내용
    @JsonProperty("token")
    val token: String,
    @JsonProperty("appointmentId")
    val appointmentId: AppointmentId
) {
    fun toModel(): SmsRequest = SmsRequest(
        msg = msg,
        receiver = receiver,
        userId = decodeToken(token),
        appointmentId = appointmentId
    )


    private fun decodeToken(token: String): UUID = try{
        UUID.fromString(JWT.decode(token).subject)
    }catch(e: Exception){
        throw ZzekakException(ExceptionEnum.ILLEGAL_TOKEN)
    }
}