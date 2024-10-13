package routes

import com.advert.plugins.processing
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.*
import getTestResult
import toJsonFormat


fun Route.clientRouting(){

//curl -X GET http://127.0.0.1:3000/search?parameters=%7B%22category%22%3A%22%D0%9D%D0%B5%D0%B4%D0%B2%D0%B8%D0%B6%D0%B8%D0%BC%D0%BE%D1%81%D1%82%D1%8C%22%2C%22city%22%3A%22%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3%22%2C%22dealType%22%3A%22false%22%2C%22livingType%22%3A%22%D0%92%D1%82%D0%BE%D1%80%D0%B8%D1%87%D0%BA%D0%B0%22%2C%22priceType%22%3A%22false%22%2C%22room%22%3A%22null1%201%20%22%7D -H "Content-Type: application/json"

    route("/"){
        get{
            call.respondText("Server is working successful! Hello!", status = HttpStatusCode.OK)
        }
    }

    route("/test"){
        get{
            // если Map не пустой, то начинаем парсить
                val parameters: Map<String,String?> = mapOf<String,String?>("city" to "Санкт-Петербург")
                try {
                    // получаем итоговую JsonArray-строку, которую отправляем на клиента
                    val response = getTestResult(parameters)
                    call.respondText(response!!,ContentType.Application.Json, status = HttpStatusCode.OK)
                }catch (e:Exception){
                    call.respondText("Something was wrong: /TEST_SEARCH", status = HttpStatusCode.InternalServerError)
                }
        }
    }

    route("/testsearch"){
        get{
            val map = mapOf<String, String>(
                "https://example.com/result1" to "Result 1",
                "https://example.com/result2" to "Result 2",
                "https://example.com/result3" to "Result 3",
                "https://example.com/result4" to "Result 4",
                "https://example.com/result5" to "Result 5",
                "https://example.com/result6" to "Result 6",
                "https://example.com/result7" to "Result 7",
                "https://example.com/result8" to "Result 8",
                "https://example.com/result9" to "Result 9",
                "https://example.com/result10" to "Result 10"
            )

            val json = Json.encodeToJsonElement(map)
            call.respond(HttpStatusCode.OK, json.jsonObject)
        }
    }
    route("/search") {
        get{
            val requestParam = call.request.queryParameters["parameters"]!!
            val modifiedString = requestParam.toJsonFormat()
            println("Parameters is $modifiedString")
            val parameters: Map<String, String> = Json.decodeFromString(modifiedString)
            if(parameters.isNotEmpty()){
                try {
                    println("Start scrapping...")
                    val response = processing(parameters)
                    println("Scrapping was successful!\nResponse size is ${response?.length}, response is $response")
                    call.respondText(response!!,ContentType.Application.Json, status = HttpStatusCode.OK)
                }catch (e:Exception){
                    println("Scrapping was failure!")
                    call.respondText("Something was wrong: /SEARCH", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}
