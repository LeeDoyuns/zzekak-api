package l1a.jjakkak.api.domain.message

import l1a.jjakkak.api.ApiUrl

import l1a.jjakkak.api.domain.message.request.MessageRequest
import l1a.jjakkak.api.domain.message.response.MessageResponse
import l1a.jjakkak.core.domain.message.usecase.MessageUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * SMS 발송 Controller
 */

@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE])
internal interface MessageController{
    @PostMapping(ApiUrl.MESSAGE_SEND)
    fun sendSMS(@RequestBody request: MessageRequest): MessageResponse
}

@RestController
internal class MessageControllerImpl(val useCase: MessageUseCase) : MessageController {
    override fun sendSMS(request: MessageRequest): MessageResponse =
        useCase.sendSms(request.toModel()).run {MessageResponse.from(this)}


}