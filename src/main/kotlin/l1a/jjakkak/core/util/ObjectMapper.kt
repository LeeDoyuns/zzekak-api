package l1a.jjakkak.core.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object ObjectMapper {
    val objectMapper = jacksonObjectMapper()
}