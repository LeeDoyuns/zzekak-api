package l1a.jjakkak.infra.domain.address.repository

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import l1a.jjakkak.api.config.exception.ExceptionEnum
import l1a.jjakkak.api.config.exception.ZzekakException
import l1a.jjakkak.core.domain.address.model.PathFindResponse
import l1a.jjakkak.core.domain.address.model.SearchedAddress
import l1a.jjakkak.infra.domain.address.model.AddressRequest
import l1a.jjakkak.infra.domain.address.model.AddressResponse
import l1a.jjakkak.infra.domain.address.model.Documents
import l1a.jjakkak.infra.domain.address.model.FindPathRequest
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriBuilder

internal class ConnectionModule(val confmKey: String) {
    private val addrUrl = "https://dapi.kakao.com/v2/local/search/address.json"
    private val pathFindUrl = "https://maps.googleapis.com/maps/api/directions/json"
    // 주소(addr)검색 / 좌표(coord)검색

    fun searchAddr(keyword: String): SearchedAddress {
        val addressObj = connectionJuso(keyword)
        val document: List<Documents> = addressObj.documents!!
        //document는 빈 배열로라도 내려온다. 비어있다면 SearchedAddress를 모두빈값으로 내려준다.
        if(document.isEmpty()) return SearchedAddress.createEmptyObject()

        var doc = document[0]
        var response = SearchedAddress.create(
            roadAddress = doc.roadAddress.addressName,
            jibunAddress = doc.address.addressName,
            buildingName = doc.roadAddress.buildingName,
            mountainYn = doc.address.mountainYn,
            postalCode = doc.roadAddress.zoneNo,
            hCode = doc.address.hCode,
            cityOrProvince = doc.address.region1DepthName,
            districtOrCity = doc.address.region2DepthName,
            undergroundYn = doc.roadAddress.undergroundYn,
            x = doc.x,
            y = doc.y
        )

        return response
    }

    // 주소 검색
    private fun connectionJuso(keyword: String): AddressResponse {
        val req: JsonObject = AddressRequest(keyword).returnToJSON()
        val response: AddressResponse = connectionSearchAddress(addrUrl, req)
        return response
    }

    fun findPath(strtLocX: String, strtLocY: String, endLocX: String, endLocY: String): PathFindResponse {
        var startCoordinate: String = "${strtLocY},${strtLocX}"
        var destinationCoordinate: String = "${endLocY},${endLocX}"

        var req: JsonObject = FindPathRequest(startCoordinate, destinationCoordinate, confmKey).returnToJSON()
        return connectionFindPath(pathFindUrl, req)
    }





    private fun connectionSearchAddress(
        url: String,
        req: JsonObject
    ): AddressResponse {
        val factory =
            DefaultUriBuilderFactory(url).apply {
                this.encodingMode = DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT
            }
        val webClient =
            WebClient.builder()
                .uriBuilderFactory(factory)
                .build()

        val response =
            webClient
                .get()
                .uri { uriBuilder: UriBuilder ->
                    var iter = req.keys.iterator()
                    while (iter.hasNext()) {
                        var key = iter.next()
                        uriBuilder.queryParam(key, req.get(key).toString().replace("\"", ""))
                    }
                    uriBuilder.build()
                }
                .header("Authorization", confmKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AddressResponse::class.java)
//                .bodyToMono(String::class.java)
                .onErrorMap { _ ->
                    throw ZzekakException(ExceptionEnum.SERVER_ERROR)
                }
                .block()
        return response!!
    }

    private fun connectionFindPath(url: String, req: JsonObject): JsonObject{
        val factory =
            DefaultUriBuilderFactory(url).apply {
                this.encodingMode = DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT
            }
        val webClient =
            WebClient.builder()
                .uriBuilderFactory(factory)
                .build()
        val stringResponse = webClient
                .get()
                .uri { uriBuilder: UriBuilder ->
                    var iter = req.keys.iterator()
                    while (iter.hasNext()) {
                        var key = iter.next()
                        uriBuilder.queryParam(key, req.get(key).toString().replace("\"", ""))
                    }
                    uriBuilder.build()
                }
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String::class.java)
                .onErrorMap { _ ->
                    throw ZzekakException(ExceptionEnum.SERVER_ERROR)
                }
        //jsonString을 json으로 변환. 너무 많은 jsonobject가 내려와서 class로 치환하지 않고 jsonObject에서 바로 꺼내는방향으로 진행한다.
        val json = Json { ignoreUnknownKeys = true }
        val jsonObject = json.parseToJsonElement(stringResponse.toString()).jsonObject


    }


}
