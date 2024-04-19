package routes

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

val client = HttpClient(){
    val accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNzg0NTc1YzhjODlkOTA0YzEzOWQ3ZjkxMjkzODdiNWY0MDUzZWZiMWU3YmU3NGQyYmE4ZmM4YzJiYmNkYWI0MGRhYTI5YWIyODA4MjAzZmYiLCJpYXQiOjE3MTM0MzAyMDguNTEzNDgsIm5iZiI6MTcxMzQzMDIwOC41MTM0ODIsImV4cCI6MTcyMTIwNjIwOC41MDMzNTQsInN1YiI6IjMyOTk3MzAiLCJzY29wZXMiOltdfQ.pX0Gdc0odDZDrQYWuHX30L9THXxuq2BQ5yltOAEqoLZsOubppHHx6ekEJcyBLTgE4C1iNw16EPAHxZM3kk_DuBaroLfrK-Dbr0ZaGrm5qJ62XIX-ilyJrZWKTywltUIcZ6Vo6HdDYZDh99cFrk74a-IHXLclbcppCy1sHBiUFqq4mM9v_Hjuk_4aE-Zcled_jEnndW51BhvRvRu7D_TUphQ0MBErHPDSkJXHv9mbj3GHKAE_S2KJzxkEcCJ2lFH-Bos79yOcn_1of41-lZioT2__I_1xW0-MQlE6BEIqdQ6HVl0KRVMrODB5HtM-vBI8syOdcaudXwj97u8tLVTvf6P_FR6YQN-wwjUpjWQJjbjbqST9kmwLHci58zju4NXJ9rF3lEEliYFXNTulMmKlpFtHHdspRBKSVtkkfQuByJ9ntqBrMqosnt4nHgTnEbiaaWGEIDTMO81MzBct6kONv5eOk0Q73BPFMCZMNTrET7K8HGVOtjBoOt65ZBID7UKfl57krYU22PMAyEc0plXRLUcjNH63Blg15YgGPr6yFZkLvFOAzk17f3veBKQKsTS3k_uJ6HPJRGpuUKFLxte92DWGeNDAW7m9lM0rKzqG5Bah5FC1Uvz95XyrBfn9R0zTreQYkwY8BVTN1KwXto0h-MOZSegspVqxwZILMew4jB0"
    install(Auth) {
        bearer {
            loadTokens {
                // Load tokens from a local storage and return them as the 'BearerTokens' instance
                BearerTokens(accessToken,"accessToken")
            }
        }
    }
}

suspend fun getUser(): Map<String,String>{
    var user_id = "341858542"
    var req = "http://localhost:3001/v1.0/browser_profiles/${user_id}/start?automation=1"

    //val req = "https://dolphin-anty-api.com/browser_profiles/${user_id}"
    val response: HttpResponse = client.get(urlString = req)
    //val user: Map<String, String> = Json.decodeFromString(response.bodyAsText())
    val browser: JsonObject = Json.decodeFromString(response.bodyAsText())
    val temp: JsonObject = Json.decodeFromJsonElement(browser["automation"]!!)
    val port = Json.encodeToString(temp["port"])

    //val cookiesResponse: HttpResponse = client.get("https://sync.dolphin-anty-api.com/?actionType=getCookies&browserProfileId=${user_id}")
   // val cookies:JsonObject = Json.decodeFromString(cookiesResponse.bodyAsText())
    //println(temp["port"])

    //val proxyResponse:  HttpResponse = client.get("https://dolphin-anty-api.com/proxy")
    //val proxy = proxyResponse.bodyAsText()

    val result = mapOf<String,String>(
        "port" to port,
      //  "cookies" to Json.encodeToString(cookies)
    )
    return result
}

fun Route.serverRouting() {
    route("/") {
        get {
            call.respondText("Сервер работает")
        }
    }
}