package l1a.jjakkak.core.domain.message.model

import l1a.jjakkak.api.domain.message.dto.MessageTypeDto
import l1a.jjakkak.core.domain.appointment.model.AppointmentId
import org.springframework.beans.factory.annotation.Value
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class SmsRequest(
    val receiver: String,    //수신번호. 다중발송의 경우 콤마(,)로 구분
    val msg: String,        //내용
    val userId: UUID,       //발송자id
    val appointmentId: AppointmentId,
    val type: String = MessageTypeDto.SMS.code,
) {
}