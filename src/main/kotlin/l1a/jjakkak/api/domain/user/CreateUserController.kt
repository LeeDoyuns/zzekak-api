package l1a.jjakkak.api.domain.user

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.core.domain.user.User
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

interface CreateUserController {
    @PostMapping(ApiUrl.USER_CREATE)
    fun createUser(): User
}

@RestController
class CreateUserControllerImpl(): CreateUserController {
    override fun createUser(): User {
        TODO("Not yet implemented")
    }

}