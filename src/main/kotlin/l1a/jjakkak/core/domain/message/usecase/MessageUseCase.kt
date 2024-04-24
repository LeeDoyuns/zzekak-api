package l1a.jjakkak.core.domain.message.usecase

import l1a.jjakkak.core.domain.message.model.SmsRequest
import l1a.jjakkak.core.domain.message.model.SmsResponse
import l1a.jjakkak.core.domain.message.repository.MessageRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MessageUseCase(
    private val messageRepository: MessageRepository,
    @Value("\${lia.aligo.key}")  //문자발송 apikey
    val key: String,
    @Value("\${lia.aligo.msg-type}")  //msgType
    val msg_type: String,
    @Value("\${lia.aligo.user-id}")  //userId
    val user_id: String,
    @Value("\${lia.aligo.testmode-yn}")  //testModeYn
    val testmode_yn: String,
    val sender: String,
    val title: String = "",
    val rdate: LocalDate = LocalDate.now(),
    val rtime: String = ""
) {
    fun sendSms(model: SmsRequest): SmsResponse {

        //발송내역 db저장

        //실제발송 api

        //결과 저장.

    }

    fun sendKakaoMessage(){}
}