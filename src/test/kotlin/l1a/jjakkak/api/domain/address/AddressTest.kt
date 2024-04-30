package l1a.jjakkak.api.domain.address

import l1a.jjakkak.api.domain.address.response.AddressResponse
import l1a.jjakkak.core.domain.address.model.SearchedAddress
import l1a.jjakkak.core.domain.address.usecase.FindAddressUseCase
import l1a.jjakkak.infra.domain.address.model.AddressObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.http.server.reactive.MockServerHttpResponse
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URLEncoder

@SpringBootTest
//@Transactional
@WithMockUser("user1")
@AutoConfigureMockMvc
class AddressTest(
) {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var addrController: FindAddressController

    @Autowired
    private lateinit var useCase: FindAddressUseCase


    @DisplayName("주소찾기 api 조회")
    @Test
    fun addressTest(){
        //given
        val addr: String = "테헤란로 501"

        //when
        var addrResult = useCase.findAddressByKeyword(addr)

        //then
        println(addrResult)
    }

    @DisplayName("길찾기 api 조회")
    @Test
    fun findPath(){
        //given

        //when

        //than

    }
}
