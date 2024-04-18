package models.test

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Serializable
class Set() {
}


fun main() {
    val json = Json.encodeToString(Set())
}