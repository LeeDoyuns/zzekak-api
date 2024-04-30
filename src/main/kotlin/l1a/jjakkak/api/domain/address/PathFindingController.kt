package l1a.jjakkak.api.domain.address

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.api.domain.address.response.FindPathResponse
import l1a.jjakkak.core.domain.address.usecase.PathFindingUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 길찾기 API 연동
 * */
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface PathFindingController {
    @GetMapping(ApiUrl.PATH_FINDING)
    fun findAddress(
        @RequestParam(name = "출발지x좌표") strtLocX: String,
        @RequestParam(name = "출발지 y좌표") strtLocY: String,
        @RequestParam(name = "도착지 x좌표") endLocX: String,
        @RequestParam(name = "도착지 y좌표") endLocY: String
    ): FindPathResponse
}
@RestController
internal class PathFindingControllerImpl(
    private val pathFindingUsecase: PathFindingUseCase
) : PathFindingController {
    override fun findAddress(strtLocX: String, strtLocY: String, endLocX: String, endLocY: String): FindPathResponse
     = pathFindingUsecase.findPath(strtLocX, strtLocY, endLocX, endLocY).run { FindPathResponse.from(this) }
}
