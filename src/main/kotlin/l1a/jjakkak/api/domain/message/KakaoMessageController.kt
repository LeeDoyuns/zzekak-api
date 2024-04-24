package l1a.jjakkak.api.domain.message

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.api.domain.message.request.KakaoMessageRequest
import l1a.jjakkak.api.domain.message.response.KakaoMessageResponse
import l1a.jjakkak.core.domain.message.usecase.KakaoMessageUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 카카오 알림톡 발송 Controller
 */

@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
internal interface KakaoMessageController{
    @PostMapping(ApiUrl.KAKAO_MESSAGE_SEND)
    fun sendKakaoNotification(@RequestBody req: KakaoMessageRequest): KakaoMessageResponse
}
class KakaoMessageControllerImpl(val useCase: KakaoMessageUseCase) :  KakaoMessageController{
    override fun sendKakaoNotification(req: KakaoMessageRequest): KakaoMessageResponse {
        TODO("Not yet implemented")
    }
}