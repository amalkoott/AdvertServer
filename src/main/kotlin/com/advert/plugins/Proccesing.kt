package com.advert.plugins

import com.advert.ListingProcessor
import com.advert.plugins.parsers.AvitoParseModule
import com.advert.plugins.parsers.CianParseModule
import com.advert.plugins.parsers.DomclickParseModule
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

suspend fun processing(params: Map<String,String?>):String?{
    // создаем парсеры на запрос клиента
    val dealType = if (!params["dealType"].toBoolean()) "sale" else "rent"
    val livingType = when (params["livingType"].toString()){
        "Вторичка" -> "flat"
        "Новостройка" -> "layout"
        "Дом, дача" -> "house"
        "Комната" -> "room"
        else -> return null
    }
    val cian = CianParseModule()
    val dm = DomclickParseModule()
    val avito = AvitoParseModule()

    // архитектурная хрень для работы шаблона
    val cianProcessor = ListingProcessor(cian)
    val dmProcessor = ListingProcessor(dm)
    val avitoProcessor = ListingProcessor(avito)

    // собираем из каждого парсера результат в одну JsonArray-строку
    val parseResult = mapOf(
        //"cian" to cianProcessor.testProcessListings("${dealType}_${livingType}"),
        //"cian_2" to cianProcessor.testProcessListings("sale_flat"),
        //"cian_3" to cianProcessor.testProcessListings("rent_room"),
        //"cian_4" to cianProcessor.testProcessListings("sale_room"),
        //"cian_5" to cianProcessor.testProcessListings("rent_house"),
        //"cian_6" to cianProcessor.testProcessListings("sale_house"),

        //"dm_1" to dmProcessor.testProcessListings("rent_flat"),
        //"dm_2" to dmProcessor.testProcessListings("sale_flat"),
        //"dm_3" to dmProcessor.testProcessListings("rent_room"),
        //"dm_4" to dmProcessor.testProcessListings("sale_room"),
        //"dm_5" to dmProcessor.testProcessListings("rent_house"),
        //"dm_6" to dmProcessor.testProcessListings("sale_house"),

       // "dm_sale_house" to dmProcessor.testProcessListings("sale_house",params),
        //"dm_sale_flat" to dmProcessor.testProcessListings("sale_flat",params),
        //"dm_sale_room" to dmProcessor.testProcessListings("sale_room",params),
        //"dm_rent_house" to dmProcessor.testProcessListings("rent_house",params),
        //"dm_rent_flat" to dmProcessor.testProcessListings("rent_flat",params),
        //"dm_rent_room" to dmProcessor.testProcessListings("rent_room",params),
        //"dm_zenrows" to dmProcessor.testProcessListings("json_zenrows",params),

        //"cian_sale_house" to cianProcessor.testProcessListings("sale_house",params),
        //"cian_sale_flat" to cianProcessor.testProcessListings("sale_flat",params),
        //"cian_sale_room" to cianProcessor.testProcessListings("sale_room",params),
        //"cian_rent_house" to cianProcessor.testProcessListings("rent_house",params),
       // "cian_rent_flat" to cianProcessor.testProcessListings("rent_flat",params),
     //   "cian_rent_room" to cianProcessor.testProcessListings("rent_room",params),
        "cian" to cianProcessor.processListings(params),
        "dm" to dmProcessor.processListings(params),
        //"avito" to avitoProcessor.processListings(params),
    )
    /*
    val cianResult = cianProcessor.processListings(params)
    //val dmResult = dmProcessor.processListings(params)
    //val avitoResult = avitoProcessor.processListings(params)
*/

    println(parseResult.size)
    val temp = mutableMapOf<String,String?>()

    var res: JsonArray? = null
    val jsonHashMap = mutableMapOf<String,JsonElement>()
    parseResult.forEach{(parser, adList) ->
        adList?.forEach{
            val key = it.jsonObject["hash"].toString().replace("\"","")
            val obj = jsonHashMap.get(key)
            try {
                if(obj == null){
                    jsonHashMap.put(key, it)
                }else{
                    // по одному и тому же ключу были получены разные объекты (т.е. одинаковые хеши -> одни и те же объявления)
                    // обработка дубликатов - добавляем к объявлению еще одну ссылку с другого сайта
                    val newObj: MutableMap<String, String> = Json.decodeFromString(obj.toString())
                    val url:String = obj.jsonObject["url"].toString().replace("\"","") + ' ' + it.jsonObject["url"].toString().replace("\"","")
                    newObj["url"] = url
                    jsonHashMap[key] = Json.encodeToJsonElement(newObj)
                    println("duplicate\nold:\n${obj}\n\nnew:\n${jsonHashMap.get(key)}")
                }
            }catch (e:Exception){
            }
        }

    }
    res = JsonArray(jsonHashMap.values.toList())

    val jsonArrayString = Json.encodeToString(res)

    return jsonArrayString
}