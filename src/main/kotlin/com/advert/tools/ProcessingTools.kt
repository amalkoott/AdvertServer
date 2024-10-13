package com.advert.tools

import com.advert.ListingProcessor
import com.advert.plugins.parsers.CianParseModule
import com.advert.plugins.parsers.DomclickParseModule
import kotlinx.serialization.json.*

suspend fun getParseResult(params: Map<String,String?>): Map<String, List<JsonElement>?>{
    // создаем парсеры на запрос клиента
    val cian = CianParseModule()
    val dm = DomclickParseModule()
    // todo val avito = AvitoParseModule()

    // архитектурная хрень для работы шаблона
    val cianProcessor = ListingProcessor(cian)
    val dmProcessor = ListingProcessor(dm)
    // todo val avitoProcessor = ListingProcessor(avito)

    // собираем из каждого парсера результат в одну JsonArray-строку
    return mapOf(
        "cian" to cianProcessor.processListings(params),
        "dm" to dmProcessor.processListings(params),
        //todo "avito" to avitoProcessor.processListings(params),
    )

}

fun getResult(parseResult: Map<String, List<JsonElement>?>): JsonArray{

    val jsonHashMap = mutableMapOf<String, JsonElement>()
    parseResult.forEach{(parser, adList) ->
        adList?.forEach{
            val key = it.jsonObject["hash"].toString().replace("\"","")
            val obj = jsonHashMap[key]
            try {
                if(obj == null){
                    jsonHashMap[key] = it
                }else{
                    // по одному и тому же ключу были получены разные объекты (т.е. одинаковые хеши -> одни и те же объявления)
                    // обработка дубликатов - добавляем к объявлению еще одну ссылку с другого сайта
                    val newObj: MutableMap<String, String> = Json.decodeFromString(obj.toString())
                    val url:String = obj.jsonObject["url"].toString().replace("\"","") + ' ' + it.jsonObject["url"].toString().replace("\"","")

                    newObj["url"] = url
                    jsonHashMap[key] = Json.encodeToJsonElement(newObj)
                    // println("duplicate\nold:\n${obj}\n\nnew:\n${jsonHashMap.get(key)}")
                }
            }catch (_:Exception){
            }
        }

    }
    return JsonArray(jsonHashMap.values.toList())

}