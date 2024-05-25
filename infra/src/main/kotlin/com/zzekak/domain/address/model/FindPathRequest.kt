package l1a.jjakkak.infra.domain.address.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

@Serializable
internal data class FindPathRequest (
    val origin: String,
    val destination: String,
    val mode: String,
    val transitMode: String,
    val key: String
){
    constructor(origin: String, destination: String, key: String) : this(
        origin = origin,
        destination = destination,
        mode = "transit",
        key = key,
        transitMode = ""
    )

    fun returnToJSON(): JsonObject {
        return Json.encodeToJsonElement(this).jsonObject
    }
}
