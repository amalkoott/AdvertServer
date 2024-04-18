package routes

import com.advert.plugins.RequestParams
import com.advert.plugins.processing
import com.example.models.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

/*
 что клиент умеет делать с сервером?
 - запрашивать параметры фильтров при запуске (get)
 - запрашивать поиск по по параметрами (get с параметрами)
*/


fun Route.clientRouting(){
    route("/") {
        get{
            call.respondText("Сервер работает")
        }
    }
    route("/filters") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
    }

    /// curl -X POST http://127.0.0.1:8080/search  -H "Content-Type: application/json"  -d "{ \"category\": \"Квартиры\",\"price\": 19.99,\"parameters\": {\"floor\": 12,\"footage\": 336}}"

    /// curl -X POST http://127.0.0.1:8080/search  -H "Content-Type: application/json"  -d "{\"price\": 1200,\"floor\": 12}"

    /// curl -X POST http://127.0.0.1:8080/search -H "Content-Type: application/json"  -d "{\"category\":\"дом\",\"priceMin\":100000.0,\"priceMax\":200000.0,\"city\":\"Москва\",\"includeWords\":\"сад, лес\",\"excludeWords\":\"ремонт\",\"priceType\":true,\"footageMin\":100,\"footageMax\":300,\"floorMin\":1,\"floorMax\":2,\"ceilingHeight\":2,\"locationType\":\"район\",\"locationValue\":\"Таганский\",\"material\":\"кирпич\",\"year\":\"2010\",\"layout\":true,\"sellerType\":\"Собственник\",\"roomCount\":null,\"houseType\":\"коттедж\",\"footageArea\":1000,\"timeToCity\":30,\"toilet\":true,\"rent\":true,\"rentDateFrom\":\"2024-05-01\",\"rentDateTo\":\"2025-05-01\",\"guestCount\":\"2\",\"rules\":\"smoking\",\"paymentsRules\":\"rent\"}"

    /*
Flat
   curl -X POST http://127.0.0.1:8080/search -H "Content-Type: application/json" -d "{\"category\":\"Недвижимость\",\"priceMin\":100000.0,\"priceMax\":200000.0,\"city\":\"Москва\",\"includeWords\":\"школа, парк\",\"excludeWords\":\"ремонт\",\"priceType\":true,\"footageMin\":50,\"footageMax\":100,\"floorMin\":1,\"floorMax\":5,\"ceilingHeight\":2,\"locationType\":true,\"locationValue\":\"Таганская\",\"material\":\"кирпич\",\"year\":\"2020\",\"layout\":true,\"sellerType\":\"Собственник\",\"roomCount\":3,\"flatType\":\"Неважно\",\"balcony\":true,\"floorType\":\"НеПоследний\",\"travelTime\":15,\"travelTimeType\":true,\"toilet\":true}"

FlatRent
curl -X POST http://127.0.0.1:8080/search -H "Content-Type: application/json" -d "{\"category\":\"Недвижимость\",\"priceMin\":30000.0,\"priceMax\":50000.0,\"city\":\"Санкт-Петербург\",\"includeWords\":\"мебель, бытовая техника\",\"excludeWords\":\"ремонт\",\"priceType\":true,\"footageMin\":50,\"footageMax\":80,\"floorMin\":3,\"floorMax\":10,\"ceilingHeight\":1,\"locationType\":true,\"locationValue\":\"Приморская\",\"material\":\"панель\",\"year\":\"2000\",\"layout\":true,\"sellerType\":\"Собственник\",\"roomCount\":2,\"flatType\":\"Апартаменты\",\"balcony\":true,\"floorType\":\"НеПоследний\",\"travelTime\":10,\"travelTimeType\":true,\"toilet\":true,\"rent\":true,\"rentDateFrom\":\"01.05.2024\",\"rentDateTo\":\"01.05.2025\",\"guestCount\":2,\"rules\":\"Дети\",\"paymentsRules\":\"БезЗалога\"}"

curl -X POST http://127.0.0.1:8080/search -H "Content-Type: application/json" -d "{\"category\":\"Вторичка\",\"priceMin\":\"30000.0\",\"priceMax\":\"50000.0\",\"city\":\"Санкт-Петербург\",\"includeWords\":\"мебель, бытовая техника\",\"excludeWords\":\"ремонт\",\"priceType\":\"true\",\"footageMin\":\"50\",\"footageMax\":\"80\",\"floorMin\":\"3\",\"floorMax\":\"10\",\"ceilingHeight\":\"1\",\"locationType\":\"true\",\"locationValue\":\"Приморская\",\"material\":\"панель\",\"year\":\"2000\",\"layout\":\"true\",\"sellerType\":\"Собственник\",\"roomCount\":\"2\",\"flatType\":\"Апартаменты\",\"balcony\":\"true\",\"floorType\":\"НеПоследний\",\"travelTime\":\"10\",\"travelType\":\"true\",\"toilet\":\"true\",\"rent\":\"true\",\"rentDateFrom\":\"01.05.2024\",\"rentDateTo\":\"01.05.2025\",\"guestCount\":\"2\",\"rules\":\"Дети\"}"

curl -X POST http://127.0.0.1:8080/search -H "Content-Type: application/json" -d "{\"category\":\"Вторичка\",\"priceMin\":\"25000\",\"priceMax\":\"42000\",\"city\":\"Санкт-Петербург\",\"dealType\":\"Снять\",\"rentType\":\"Долго\",\"toilet\":\"false\",\"amenities\":\"Дети Животные\",\"repair\":\"Косметический\",\"rooms\":\"3\"}"


House

HouseRent

    */
    /// curl -X POST http://127.0.0.1:8080/search  -H "Content-Type: application/json"  -d "{\"category\": \"Квартиры\", \"count\": 3}"

    // СЦЕНАРИЙ:
    // - клиент отправляет POST-запрос с параметрами
    // - параметры передаются на сервер и обрабатываются
    // - клиент получает ответ, который зависит от параметров

    route("/search") {
        post {

            try {
                //val requestParams = Json.decodeFromString(RequestParams, call.receiveText())
                //var text = JSONObject(call.receiveText())
                //println(text)
                val map: Map<String, String> = Json.decodeFromString(call.receiveText())
                val result = processing(map)

                //val result = processing()

                if (result != null) {
                    call.respondText(result.toString())
                }
            }
            catch (e: BadRequestException){
                call.respondText(e.message!!)
            }
            catch (e: Exception){
                call.respondText(e.message!!)
            }


            //call.respondText(call.receiveText())

            //call.respond(mapOf("result" to result))

            //val customer = call.receive<Customer>()
            //customerStorage.add(customer)
            //call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
    }
}