package l1a.jjakkak.api.domain.address

import l1a.jjakkak.core.domain.address.usecase.FindAddressUseCase
import l1a.jjakkak.core.domain.address.usecase.PathFindingUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import java.time.ZoneId
import java.time.ZonedDateTime

@SpringBootTest
@WithMockUser("user1")
@AutoConfigureMockMvc
class AddressTest {
    @Autowired
    private lateinit var addrController: FindAddressController

    @Autowired
    private lateinit var useCase: FindAddressUseCase

    @Autowired
    private lateinit var pathUsecase: PathFindingUseCase

    @DisplayName("주소찾기 api 조회")
    @Test
    fun addressTest() {
        // given
        val addr: String = "테헤란로 501"

        // when
        var addrResult = useCase.findAddressByKeyword(addr)

        // then
        println(addrResult)
    }

    @DisplayName("길찾기 api 조회")
    @Test
    fun findPath() {
        // given
        val startX = "126.84788713801"
        val startY = "37.5311052079473"
        val endX = "127.056821251348"
        val endY = "37.5073809450356"
        val appointmentTime = ZonedDateTime.of(2024, 5, 5, 14, 10, 30, 0, ZoneId.of("Asia/Seoul"))
        // when
        var result = pathUsecase.findPath(startX, startY, endX, endY, appointmentTime)

        // then
        println(result)
    }
}
