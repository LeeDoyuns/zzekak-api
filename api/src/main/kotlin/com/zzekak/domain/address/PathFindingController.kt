package com.zzekak.domain.address

import com.zzekak.ApiUrl
import com.zzekak.domain.address.response.FindPathResponse
import com.zzekak.domain.address.usecase.PathFindingUseCase
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

/**
 * 길찾기 API 연동
 * */
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface PathFindingController {
    @Operation(summary = "길찾기 API- 소요시간 조회", description = "약속시간을 기준으로 그 이전에 도착하는 초단시간을 탐색함. 대중교통 기준.")
    @GetMapping(ApiUrl.PATH_FINDING)
    fun findAddress(
        @RequestParam(name = "출발지 x좌표") strtLocX: String,
        @RequestParam(name = "출발지 y좌표") strtLocY: String,
        @RequestParam(name = "도착지 x좌표") endLocX: String,
        @RequestParam(name = "도착지 y좌표") endLocY: String,
        @RequestParam(name = "약속 시간(ZoneDateTime - 2002-06-18T20:30+09:00[Asia/Seoul])") appointmentTime: ZonedDateTime
    ): FindPathResponse
}

@RestController
internal class PathFindingControllerImpl(
    private val pathFindingUsecase: PathFindingUseCase
) : PathFindingController {
    override fun findAddress(
        strtLocX: String,
        strtLocY: String,
        endLocX: String,
        endLocY: String,
        appointmentTime: ZonedDateTime
    ): FindPathResponse =
        pathFindingUsecase.findPath(
            strtLocX,
            strtLocY,
            endLocX,
            endLocY,
            appointmentTime,
        ).run { FindPathResponse.from(this) }
}
