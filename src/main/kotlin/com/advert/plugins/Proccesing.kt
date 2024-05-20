package com.advert.plugins

import com.advert.ListingProcessor
import com.advert.plugins.parsers.AvitoParseModule
import com.advert.plugins.parsers.CianParseModule
import com.advert.plugins.parsers.DomclickParseModule
import io.ktor.server.util.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

//import routes.Parameters
//import routes.RequestParams

// обработка запросов клиента


// функция, которая принимает JSON с указанной категорией (и запускает парсер)
// проверяет значения json-ов на соответствие всего и вся
suspend fun processing(params: Map<String,String?>):String?{
    // создаем парсеры на запрос клиента
    val cian = CianParseModule()
    val dm = DomclickParseModule()
    //val avito = AvitoParseModule()

    // архитектурная хрень для работы шаблона
    val cianProcessor = ListingProcessor(cian)
    val dmProcessor = ListingProcessor(dm)
    //val avitoProcessor = ListingProcessor(avito)

    // собираем из каждого парсера результат в одну JsonArray-строку
    val parseResult = mapOf(
        "cian" to cianProcessor.processListings(params),
        "dm" to dmProcessor.processListings(params),
       // "avito" to avitoProcessor.processListings(params),
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
                    // todo чекнуть жоска
                    val newObj: MutableMap<String, String> = Json.decodeFromString(obj.toString())
                    val url:String = obj.jsonObject["url"].toString().replace("\"","") + it.jsonObject["url"].toString().replace("\"","")
                    newObj["url"] = url
                    jsonHashMap[key] = Json.encodeToJsonElement(newObj)
                    println("duplicate")
                }
            }catch (e:Exception){
                //jsonHashMap.put(key, it)
            }
        }

    }
    res = JsonArray(jsonHashMap.values.toList())
    //val saleResult = JsonArray(mutableListOf(Json.encodeToJsonElement(sale_flat_1), Json.encodeToJsonElement(sale_flat_2)))

    /*
        parseResult.forEach{(parser,result)->
            if (result.isNullOrEmpty()) return@forEach
            temp.put()
        }
        */
    val jsonArrayString = Json.encodeToString(res)

    return jsonArrayString

    /*

    */
}



/*

Квартиры
{
  "deal_type": "купить",
  "renovation": "БезОтделки"
  "category": "апартаменты",
  "priceMin": 50000.0,
  "priceMax": 100000.0,
  "city": "Москва",
  "includeWords": "новый дом",
  "excludeWords": "ремонт",
  "priceType": false,
  "footageMin": 40,
  "footageMax": 70,
  "floorMin": 2,
  "floorMax": 15,
  "ceilingHeight": 2,
  "locationType": "район",
  "locationValue": "Центральный",
  "material": "кирпич",
  "year": "2015",
  "layout": true,
  "sellerType": "Агент",
  "roomCount": 2,
  "flatType": "Квартира",
  "balcony": true,
  "floorType": "НеПервый",
  "travelTime": 15,
  "travelTimeType": true,
  "toilet": true
}


 */


/*
 Квартиры съем
{
 "deal_type": "снять",
  "category": "Недвижимость",
  "priceMin": 30000.0,
  "priceMax": 50000.0,
  "city": "Санкт-Петербург",
  "includeWords": "мебель, бытовая техника",
  "excludeWords": "ремонт",
  "priceType": true,
  "footageMin": 50,
  "footageMax": 80,
  "floorMin": 3,
  "floorMax": 10,
  "ceilingHeight": 1,
  "locationType": true,
  "locationValue": "Приморская",
  "material": "панель",
  "year": "2000",
  "layout": true,
  "sellerType": "Собственник",
  "roomCount": 2,
  "flatType": "Апартамены",
  "balcony": true,
  "floorType": "НеПоследний",
  "travelTime": 10,
  "travelTimeType": true,
  "toilet": true,
  "rent": true,
  "rentDateFrom": "01.05.2024",
  "rentDateTo": "01.05.2025",
  "guestCount": 2,
  "rules": "Дети",
  "paymentsRules": "БезЗалога"
}


 */

/*
Загород
{
  "category": "дом",
  "priceMin": 100000.0,
  "priceMax": 200000.0,
  "city": "Киев",
  "includeWords": "сад, лес",
  "excludeWords": "ремонт",
  "priceType": true,
  "footageMin": 100,
  "footageMax": 300,
  "floorMin": 1,
  "floorMax": 2,
  "ceilingHeight": 2,
  "locationType": "район",
  "locationValue": "Подол",
  "material": "кирпич",
  "year": "2010",
  "layout": true,
  "sellerType": "Собственник",
  "roomCount": null,
  "houseType": "коттедж",
  "footageArea": 1000,
  "timeToCity": 30,
  "toilet": true
}


 */

/*
Загород съем
{
  "category": "дом",
  "priceMin": 100000.0,
  "priceMax": 200000.0,
  "city": "Москва",
  "includeWords": "сад, лес",
  "excludeWords": "ремонт",
  "priceType": true,
  "footageMin": 100,
  "footageMax": 300,
  "floorMin": 1,
  "floorMax": 2,
  "ceilingHeight": 2,
  "locationType": "район",
  "locationValue": "Таганский",
  "material": "кирпич",
  "year": "2010",
  "layout": true,
  "sellerType": "Собственник",
  "roomCount": null,
  "houseType": "коттедж",
  "footageArea": 1000,
  "timeToCity": 30,
  "toilet": true,
  "rent": true,
  "rentDateFrom": "2024-05-01",
  "rentDateTo": "2025-05-01",
  "guestCount": 4,
  "rules": "Животные",
  "paymentsRules": "БезКомиссии"
}
 */