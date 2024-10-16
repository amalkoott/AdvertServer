package com.advert.plugins.parsers

import com.advert.constant.Constants
import com.advert.tools.insertAt
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File

class DomclickParseModule:  ParseVictim() {
    override var URL: String = "https://domclick.ru/"
    override var queryParam: String = ""
    override var categories: List<String> = listOf()

    override fun getUrl(parameters: Map<String, String?>): String? {
        if(Constants.dmCities[parameters["city"]] != null){
            URL = URL.insertAt(8,Constants.dmCities[parameters["city"]]!!)
        } else return null

        URL += "search" + getUrlFromJSON(parameters)
        return URL
    }

    override fun getSiteName(): String{
        return "dm"
    }

    override fun getResult(parameters: Map<String,String>, driver: WebDriver): String? {
        // если города на сайте нет - поиска тоже нет
        if(Constants.dmCities[parameters["city"]] != null){
            URL = URL.insertAt(8,Constants.dmCities[parameters["city"]]!!)
        } else return null

        URL += "search" + getUrlFromJSON(parameters)

        // получаем web-страничку по URL
        driver.get(URL)

        val wait = WebDriverWait(driver, 10) // Устанавливаем ожидание до 30 секунд
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")))

        // разбираем web-страничку в json-строку
        val ads = driver.findElement(By.className("mainContent")).findElements(By.cssSelector("div:nth-of-type(1) > div:nth-of-type(2) > section > section > div:nth-of-type(1) > [data-e2e-id='offers-list__item'] > div > div > div:nth-of-type(1)"))

        val result = getJson(ads)
        driver.quit()
        return result
    }

    override fun parsePage(html: String): List<JsonElement>? {
        try {
            val doc: Document = Jsoup.parse(html, "UTF-8")
            return getResult(doc)
        }catch (e:Exception){
            println(e.message)
            return null
        }
    }

    override fun parsePage(html: File): List<JsonElement>? {
        try {
            val doc: Document = Jsoup.parse(html, "UTF-8")
            return getResult(doc)
        }catch (e:Exception){
            println(e.message)
            return null
        }
    }

    private fun getResult(doc: Document):List<JsonElement>?{
        // достаем блок с жисончиками
        // для zenrows
        val response = doc.select("body").text().split("\\\"search\\\":{\\\"pages\\\":{\\\"0\\\":")

        // если локалка, то вместо \\\ берется \
        val parts = response[1].split("]},\\\"csi\\\"")
        val jsonString = parts[0].replace("\\\\u002F","/").replace("\\\\\"","'").replace("\\\"","\"").replace("\\\'","\'") + ']'//.split(';')
        println(jsonString)
        // получаем объявления в жисончике
        val jsonAdverts = Json.parseToJsonElement(jsonString).jsonArray//.jsonObject["search"]!!.jsonObject["pages"]!!.jsonObject["0"]

        // определяем тип сделки и делаем все как надо
        var obj = jsonAdverts[0].jsonObject
        var result: List<JsonElement>? = null
        val value = obj["dealType"].toString().replace("\"","")+obj["offerType"].toString().replace("\"","")
        when(value){
            "rentflat" -> result = getRentFlatJson(jsonAdverts)
            "renthouse" -> result = getRentHouseJson(jsonAdverts)
            "rentroom" -> result = getRentRoomJson(jsonAdverts)
            "saleflat" -> result = getSaleFlatJson(jsonAdverts)
            "salehouse" -> result = getSaleHouseJson(jsonAdverts)
            "saleroom" -> result = getSaleRoomJson(jsonAdverts)
            // todo salelayout
            else -> {result = emptyList()
            }
        }
        return result
    }

    private fun getRentFlatJson(jsonAdverts: JsonElement?):List<JsonElement>?{
        val result = mutableListOf<JsonElement>()

        jsonAdverts!!.jsonArray.forEach{
            try{
                val ad = it.jsonObject
                val url =  ad["path"].toString().replace("\"","")// + path (это ссылка)

                // это можно в title собират  house        object info (инфа с метражем)
                val info = ad["objectInfo"]!!.jsonObject
                val house = ad["house"]!!.jsonObject
                val roomsCount = info["rooms"]!!.toString()
                val room = if( roomsCount == "0") "Квартира-студия" else "$roomsCount-комн. квартира"
                val title = "${room}, ${info["area"]} кв.м., ${info["floor"]}/${house["floors"]} этаж"

                val description = ad["description"].toString().replace("\"","") // + description

                val location = ad["address"]!!.jsonObject["displayName"].toString().replace("\"","") //         + address

                val subways = ad["address"]!!.jsonObject["subways"]!!.jsonArray
                var travel =  ""// ????
                if (subways!!.size != 0){
                    subways.forEach{
                        val sub = it.jsonObject
                        val travelType = if (sub["remoteness"]!!.jsonObject["kind"].toString() == "1") "пешком" else "транспортом"
                        val travelTime = sub["remoteness"]!!.jsonObject["time"]
                        travel += "м. ${sub["name"].toString().replace("\"","")}, $travelTime мин. $travelType; "
                    }
                }


                val coordinates = "${ad["location"]!!.jsonObject["lat"]} ${ad["location"]!!.jsonObject["lon"]}"  //         + location (координаты)

                val price = ad["price"].toString().replace("\"","") // + price

                val priceInfo: String = if (ad["hasRentCommission"]!!.toString() == "true") "Комиссия" else "" // + hasRentComission (price info)

                val photos = ad["photos"]!!.jsonArray
                var img = ""
                photos.forEach{
                    val uri = it.jsonObject["url"].toString().replace("\"","")
                    img += "https://img.dmclk.ru${uri} "
                }


                val publishedDate = ad["publishedDate"].toString().replace("\"","") // "publishedDate":"2024-04-29T13:24:08+00:00",

                val updatedDate = ad["updatedDate"].toString().replace("\"","") // "updatedDate":"2024-04-29T19:28:06.909845+00:00",


                val area = ad["objectInfo"]!!.jsonObject["area"].toString().replace("\"","")
                val floor = ad["objectInfo"]!!.jsonObject["floor"].toString().replace("\"","")
                val floors = ad["house"]!!.jsonObject["floors"]!!.toString().replace("\"","")
                val rooms = ad["objectInfo"]!!.jsonObject["rooms"].toString().replace("\"","")
                val hashAddress = location.replace("улица", "").replace("проезд", "").replace("проспект", "").replace("аллея","").replace("бульвар","").replace("шоссе","").replace(" ","").replace(",","")
                val hash = (hashAddress + rooms + floors + floor + area + price).hashCode()


                val resultAd = mutableMapOf<String,String>()
                resultAd["url"]=url
                resultAd["title"]=title
                resultAd["travel"]=travel
                resultAd["location"]=location
                resultAd["price"]=price
                resultAd["priceInfo"]=priceInfo
                resultAd["caption"]=description
                resultAd["img"] = img
                resultAd["coordinates"] = coordinates
                resultAd["publishedDate"] = publishedDate
                resultAd["updatedDate"] = updatedDate
                resultAd["hash"] = hash.toString()
                result.add(Json.encodeToJsonElement(resultAd))
            }catch (e:Exception){
                println("Smth error in dm parse\n <${it.toString()}> \n\n" + e.message)
            }
        }
        return result
    }

    private fun getRentHouseJson(jsonAdverts: JsonElement?): List<JsonElement>? {
        val result = mutableListOf<JsonElement>()

        jsonAdverts!!.jsonArray.forEach{
            try{
                val ad = it.jsonObject
                val url =  ad["path"].toString().replace("\"","")// + path (это ссылка)

                val info = ad["objectInfo"]!!.jsonObject
                val house = ad["house"]!!.jsonObject//
                val roomsCount = info["rooms"]!!.toString()
                val room = if( roomsCount == "0") "Квартира-студия" else "$roomsCount-комн. квартира"
                val title = "Дом ${info["area"]} кв.м."//, ${info["floor"]}/${house["floors"]} этаж"

                val description = ad["description"].toString().replace("\"","") // + description

                val location = ad["address"]!!.jsonObject["displayName"].toString().replace("\"","") //         + address

                val subways = ad["address"]!!.jsonObject["subways"]!!.jsonArray
                var travel =  ""// ????
                if (subways!!.size != 0){
                    subways.forEach{
                        val sub = it.jsonObject
                        val travelType = if (sub["remoteness"]!!.jsonObject["kind"].toString() == "1") "пешком" else "транспортом"
                        val travelTime = sub["remoteness"]!!.jsonObject["time"]
                        travel += "м. ${sub["name"].toString().replace("\"","")}, $travelTime мин. $travelType; "
                    }
                }

                val coordinates = "${ad["location"]!!.jsonObject["lat"]} ${ad["location"]!!.jsonObject["lon"]}"  //         + location (координаты)
                val price = ad["price"].toString().replace("\"","") // + price
                val priceInfo: String = if (ad["hasRentCommission"]!!.toString() == "true") "Комиссия" else "" // + hasRentComission (price info)

                val photos = ad["photos"]!!.jsonArray
                var img = ""
                photos.forEach{
                    val uri = it.jsonObject["url"].toString().replace("\"","")
                    img += "https://img.dmclk.ru${uri} "
                }

                val publishedDate = ad["publishedDate"].toString().replace("\"","") // "publishedDate":"2024-04-29T13:24:08+00:00",
                val updatedDate = ad["updatedDate"].toString().replace("\"","") // "updatedDate":"2024-04-29T19:28:06.909845+00:00",

                val area = ad["objectInfo"]!!.jsonObject["area"].toString().replace("\"","")
                val floor = ad["objectInfo"]!!.jsonObject["floor"].toString().replace("\"","")
                val floors = ad["house"]!!.jsonObject["floors"]!!.toString().replace("\"","")
                val rooms = ad["objectInfo"]!!.jsonObject["rooms"].toString().replace("\"","")
                val hashAddress = location.replace("улица", "").replace("проезд", "").replace("проспект", "").replace("аллея","").replace("бульвар","").replace("шоссе","").replace(" ","").replace(",","")
                val hash = (hashAddress + rooms + floors + floor + area + price).hashCode()


                val resultAd = mutableMapOf<String,String>()
                resultAd["url"]=url
                resultAd["title"]=title
                resultAd["travel"]=travel
                resultAd["location"]=location
                resultAd["price"]=price
                resultAd["priceInfo"]=priceInfo
                resultAd["caption"]=description
                resultAd["img"] = img
                resultAd["coordinates"] = coordinates
                resultAd["publishedDate"] = publishedDate
                resultAd["updatedDate"] = updatedDate
                resultAd["hash"] = hash.toString()
                result.add(Json.encodeToJsonElement(resultAd))
            }catch (e:Exception){
                println("Smth error in dm parse\n <${it.toString()}> \n\n" + e.message)
            }
        }
        return result
    }

    private fun getRentRoomJson(jsonAdverts: JsonElement?):List<JsonElement>?{
        val result = mutableListOf<JsonElement>()

        jsonAdverts!!.jsonArray.forEach{
            try{
                val ad = it.jsonObject
                val url =  ad["path"].toString().replace("\"","")// + path (это ссылка)

                // это можно в title собират  house        object info (инфа с метражем)
                val info = ad["objectInfo"]!!.jsonObject
                val house = ad["house"]!!.jsonObject
                val roomsCount = info["rooms"]!!.toString()
                val room = if( roomsCount == "0") "студии" else "$roomsCount-к"
                val title = "Комната ${info["area"]} кв.м. в ${room}, ${info["floor"]}/${house["floors"]} этаж"

                val description = ad["description"].toString().replace("\"","") // + description

                val location = ad["address"]!!.jsonObject["displayName"].toString().replace("\"","") //         + address

                val subways = ad["address"]!!.jsonObject["subways"]!!.jsonArray
                var travel =  ""// ????
                if (subways!!.size != 0){
                    subways.forEach{
                        val sub = it.jsonObject
                        val travelType = if (sub["remoteness"]!!.jsonObject["kind"].toString() == "1") "пешком" else "транспортом"
                        val travelTime = sub["remoteness"]!!.jsonObject["time"]
                        travel += "м. ${sub["name"].toString().replace("\"","")}, $travelTime мин. $travelType; "
                    }
                }


                val coordinates = "${ad["location"]!!.jsonObject["lat"]} ${ad["location"]!!.jsonObject["lon"]}"  //         + location (координаты)

                val price = ad["price"].toString().replace("\"","") // + price

                val priceInfo: String = if (ad["hasRentCommission"]!!.toString() == "true") "Комиссия" else "" // + hasRentComission (price info)

                val photos = ad["photos"]!!.jsonArray
                var img = ""
                photos.forEach{
                    val uri = it.jsonObject["url"].toString().replace("\"","")
                    img += "https://img.dmclk.ru${uri} "
                }


                val publishedDate = ad["publishedDate"].toString().replace("\"","") // "publishedDate":"2024-04-29T13:24:08+00:00",

                val updatedDate = ad["updatedDate"].toString().replace("\"","") // "updatedDate":"2024-04-29T19:28:06.909845+00:00",


                val area = ad["objectInfo"]!!.jsonObject["area"].toString().replace("\"","")
                val floor = ad["objectInfo"]!!.jsonObject["floor"].toString().replace("\"","")
                val floors = ad["house"]!!.jsonObject["floors"]!!.toString().replace("\"","")
                val rooms = ad["objectInfo"]!!.jsonObject["rooms"].toString().replace("\"","")
                val hashAddress = location.replace("улица", "").replace("проезд", "").replace("проспект", "").replace("аллея","").replace("бульвар","").replace("шоссе","").replace(" ","").replace(",","")
                val hash = (hashAddress + rooms + floors + floor + area + price).hashCode()


                val resultAd = mutableMapOf<String,String>()
                resultAd["url"]=url
                resultAd["title"]=title
                resultAd["travel"]=travel
                resultAd["location"]=location
                resultAd["price"]=price
                resultAd["priceInfo"]=priceInfo
                resultAd["caption"]=description
                resultAd["img"] = img
                resultAd["coordinates"] = coordinates
                resultAd["publishedDate"] = publishedDate
                resultAd["updatedDate"] = updatedDate
                resultAd["hash"] = hash.toString()
                result.add(Json.encodeToJsonElement(resultAd))
            }catch (e:Exception){
                println("Smth error in dm parse\n <${it.toString()}> \n\n" + e.message)
            }
        }
        return result
    }

    private fun getSaleFlatJson(jsonAdverts: JsonElement?):List<JsonElement>?{
        val result = mutableListOf<JsonElement>()

        jsonAdverts!!.jsonArray.forEach{
            try{
                val ad = it.jsonObject
                val url =  ad["path"].toString().replace("\"","")// + path (это ссылка)

                // это можно в title собират  house        object info (инфа с метражем)
                val info = ad["objectInfo"]!!.jsonObject
                val house = ad["house"]!!.jsonObject
                val roomsCount = info["rooms"]!!.toString()
                val room = if( roomsCount == "0") "Квартира-студия" else "$roomsCount-комн. квартира"
                val title = "${room}, ${info["area"]} кв.м., ${info["floor"]}/${house["floors"]} этаж"

                val description = ad["description"].toString().replace("\"","") // + description

                val location = ad["address"]!!.jsonObject["displayName"].toString().replace("\"","") //         + address

                val subways = ad["address"]!!.jsonObject["subways"]!!.jsonArray
                var travel =  ""// ????
                if (subways!!.size != 0){
                    subways.forEach{
                        val sub = it.jsonObject
                        val travelType = if (sub["remoteness"]!!.jsonObject["kind"].toString() == "1") "пешком" else "транспортом"
                        val travelTime = sub["remoteness"]!!.jsonObject["time"]
                        travel += "м. ${sub["name"].toString().replace("\"","")}, $travelTime мин. $travelType; "
                    }
                }


                val coordinates = "${ad["location"]!!.jsonObject["lat"]} ${ad["location"]!!.jsonObject["lon"]}"  //         + location (координаты)

                val price = ad["price"].toString().replace("\"","") // + price

                val priceInfo: String = if (ad["hasRentCommission"]!!.toString() == "true") "Комиссия" else "" // + hasRentComission (price info)

                val photos = ad["photos"]!!.jsonArray
                var img = ""
                photos.forEach{
                    val uri = it.jsonObject["url"].toString().replace("\"","")
                    img += "https://img.dmclk.ru${uri} "
                }


                val publishedDate = ad["publishedDate"].toString().replace("\"","") // "publishedDate":"2024-04-29T13:24:08+00:00",

                val updatedDate = ad["updatedDate"].toString().replace("\"","") // "updatedDate":"2024-04-29T19:28:06.909845+00:00",


                val area = ad["objectInfo"]!!.jsonObject["area"].toString().replace("\"","")
                val floor = ad["objectInfo"]!!.jsonObject["floor"].toString().replace("\"","")
                val floors = ad["house"]!!.jsonObject["floors"]!!.toString().replace("\"","")
                val rooms = ad["objectInfo"]!!.jsonObject["rooms"].toString().replace("\"","")
                val hashAddress = location.replace("улица", "").replace("проезд", "").replace("проспект", "").replace("аллея","").replace("бульвар","").replace("шоссе","").replace(" ","").replace(",","")
                val hash = (hashAddress + rooms + floors + floor + area + price).hashCode()


                val resultAd = mutableMapOf<String,String>()
                resultAd["url"]=url
                resultAd["title"]=title
                resultAd["travel"]=travel
                resultAd["location"]=location
                resultAd["price"]=price
                resultAd["priceInfo"]=priceInfo
                resultAd["caption"]=description
                resultAd["img"] = img
                resultAd["coordinates"] = coordinates
                resultAd["publishedDate"] = publishedDate
                resultAd["updatedDate"] = updatedDate
                resultAd["hash"] = hash.toString()
                result.add(Json.encodeToJsonElement(resultAd))
            }catch (e:Exception){
                println("Smth error in dm parse\n <${it.toString()}> \n\n" + e.message)
            }
        }
        return result
    }

    private fun getSaleHouseJson(jsonAdverts: JsonElement?):List<JsonElement>?{
        val result = mutableListOf<JsonElement>()

        jsonAdverts!!.jsonArray.forEach{
            try{
                val ad = it.jsonObject
                val resultAd = mutableMapOf<String,String>()
                try {
                    val url =  ad["path"].toString().replace("\"","")// + path (это ссылка)
                    resultAd["url"]=url
                }catch (e:Exception){
                    println("Error for <path>\n${e.message}")
                }
                try {
                    // это можно в title собират  house        object info (инфа с метражем)
                    val info = ad["objectInfo"]!!.jsonObject
                    val house = ad["house"]!!.jsonObject
                    val roomsCount = info["rooms"]!!.toString()
                    val room = if( roomsCount == "0") "Квартира-студия" else "$roomsCount-комн. квартира"
                    val title = "Дом ${info["area"]} кв.м."//, ${info["floor"]}/${house["floors"]} этаж"

                    resultAd["title"]=title
                }catch (e:Exception){
                    println("Error for <title> in ${ad}\n${e.message}")
                }
                try {
                    val description = ad["description"].toString().replace("\"","") // + description

                    resultAd["caption"]=description
                }catch (e:Exception){
                    println("Error for <description> in ${ad}\n${e.message}")
                }
                try {
                    val location = ad["address"]!!.jsonObject["displayName"].toString().replace("\"","") //         + address

                    resultAd["location"]=location
                }catch (e:Exception){
                    println("Error for <address> in ${ad}\n${e.message}")
                }
                try {
                    val subways = ad["address"]!!.jsonObject["subways"]!!.jsonArray
                    var travel =  ""// ????
                    if (subways!!.size != 0){
                        subways.forEach{
                            val sub = it.jsonObject
                            val travelType = if (sub["remoteness"]!!.jsonObject["kind"].toString() == "1") "пешком" else "транспортом"
                            val travelTime = sub["remoteness"]!!.jsonObject["time"]
                            travel += "м. ${sub["name"].toString().replace("\"","")}, $travelTime мин. $travelType; "
                        }
                    }

                    resultAd["travel"]=travel
                }catch (e:Exception){
                    println("Error for <subways> in ${ad}\n${e.message}")
                }
                try {
                    val coordinates = "${ad["location"]!!.jsonObject["lat"]} ${ad["location"]!!.jsonObject["lon"]}"  //         + location (координаты)

                    resultAd["coordinates"] = coordinates
                }catch (e:Exception){
                    println("Error for <location>  in ${ad}\n${e.message}")
                }
                try {
                    val price = ad["price"].toString().replace("\"","") // + price

                    resultAd["price"]=price
                }catch (e:Exception){
                    println("Error for <price>  in ${ad}\n${e.message}")
                }
                try {
                    val priceInfo: String = if (ad["hasRentCommission"]!!.toString() == "true") "Комиссия" else "" // + hasRentComission (price info)

                    resultAd["priceInfo"]=priceInfo
                }catch (e:Exception){
                    println("Error for <hasRentCommission> in ${ad}\n${e.message}")
                }
                try {
                    val photos = ad["photos"]!!.jsonArray
                    var img = ""
                    photos.forEach{
                        val uri = it.jsonObject["url"].toString().replace("\"","")
                        img += "https://img.dmclk.ru${uri} "
                    }
                    resultAd["img"] = img
                }catch (e:Exception){
                    println("Error for <photos> in ${ad}\n${e.message}")
                }
                try {
                    val publishedDate = ad["publishedDate"].toString().replace("\"","") // "publishedDate":"2024-04-29T13:24:08+00:00",

                    resultAd["publishedDate"] = publishedDate
                }catch (e:Exception){
                    println("Error for <publishedDate>  in ${ad}\n${e.message}")
                }
                try {
                    val updatedDate = ad["updatedDate"].toString().replace("\"","") // "updatedDate":"2024-04-29T19:28:06.909845+00:00",

                    resultAd["updatedDate"] = updatedDate
                }catch (e:Exception){
                    println("Error for <updatedDate> in ${ad}\n${e.message}")
                }
                try {
                    val area = ad["objectInfo"]!!.jsonObject["area"].toString().replace("\"","")
                    val floor = ad["objectInfo"]!!.jsonObject["floor"].toString().replace("\"","")
                    val floors = ad["house"]!!.jsonObject["floors"]!!.toString().replace("\"","")
                    val rooms = ad["objectInfo"]!!.jsonObject["rooms"].toString().replace("\"","")
                    val hashAddress = ad["address"]!!.jsonObject["displayName"].toString().replace("\"","").replace("улица", "").replace("проезд", "").replace("проспект", "").replace("аллея","").replace("бульвар","").replace("шоссе","").replace(" ","").replace(",","")
                    val hash = (hashAddress + rooms + floors + floor + area + ad["price"].toString().replace("\"","")).hashCode()

                    resultAd["hash"] = hash.toString()
                }catch (e:Exception){
                    println("Error for {HASH} calculate with ${ad}\n\n${e.message}")
                }
                /*
                try {

                }catch (e:Exception){
                    println("Error for <>\n${e.message}")
                }
                 */
                if (resultAd.size == 12) result.add(Json.encodeToJsonElement(resultAd)) else println("Can't create the ad ${ad}\nIt's ${12-resultAd.size} param enough \n\n")
            }catch (e:Exception){
                println("Smth error in dm parse\n <${it.toString()}> \n\n" + e.message)
                return emptyList()
            }
        }
        return result
    }

    private fun getSaleRoomJson(jsonAdverts: JsonElement?):List<JsonElement>?{
        val result = mutableListOf<JsonElement>()

        jsonAdverts!!.jsonArray.forEach{
            try{
                val ad = it.jsonObject
                val url =  ad["path"].toString().replace("\"","")// + path (это ссылка)

                // это можно в title собират  house        object info (инфа с метражем)
                val info = ad["objectInfo"]!!.jsonObject
                val house = ad["house"]!!.jsonObject
                val roomsCount = info["rooms"]!!.toString()
                val room = if( roomsCount == "0") "студии" else "$roomsCount-к"
                val title = "Комната ${info["roomArea"]} кв.м. в ${room}, ${info["floor"]}/${house["floors"]} этаж"

                val description = ad["description"].toString().replace("\"","") // + description

                val location = ad["address"]!!.jsonObject["displayName"].toString().replace("\"","") //         + address

                val subways = ad["address"]!!.jsonObject["subways"]!!.jsonArray
                var travel =  ""// ????
                if (subways!!.size != 0){
                    subways.forEach{
                        val sub = it.jsonObject
                        val travelType = if (sub["remoteness"]!!.jsonObject["kind"].toString() == "1") "пешком" else "транспортом"
                        val travelTime = sub["remoteness"]!!.jsonObject["time"]
                        travel += "м. ${sub["name"].toString().replace("\"","")}, $travelTime мин. $travelType; "
                    }
                }


                val coordinates = "${ad["location"]!!.jsonObject["lat"]} ${ad["location"]!!.jsonObject["lon"]}"  //         + location (координаты)

                val price = ad["price"].toString().replace("\"","") // + price

                val priceInfo: String = if (ad["hasRentCommission"]!!.toString() == "true") "Комиссия" else "" // + hasRentComission (price info)

                val photos = ad["photos"]!!.jsonArray
                var img = ""
                photos.forEach{
                    val uri = it.jsonObject["url"].toString().replace("\"","")
                    img += "https://img.dmclk.ru${uri} "
                }


                val publishedDate = ad["publishedDate"].toString().replace("\"","") // "publishedDate":"2024-04-29T13:24:08+00:00",

                val updatedDate = ad["updatedDate"].toString().replace("\"","") // "updatedDate":"2024-04-29T19:28:06.909845+00:00",


                val area = ad["objectInfo"]!!.jsonObject["area"].toString().replace("\"","")
                val floor = ad["objectInfo"]!!.jsonObject["floor"].toString().replace("\"","")
                val floors = ad["house"]!!.jsonObject["floors"]!!.toString().replace("\"","")
                val rooms = ad["objectInfo"]!!.jsonObject["rooms"].toString().replace("\"","")
                val hashAddress = location.replace("улица", "").replace("проезд", "").replace("проспект", "").replace("аллея","").replace("бульвар","").replace("шоссе","").replace(" ","").replace(",","")
                val hash = (hashAddress + rooms + floors + floor + area + price).hashCode()


                val resultAd = mutableMapOf<String,String>()
                resultAd["url"]=url
                resultAd["title"]=title
                resultAd["travel"]=travel
                resultAd["location"]=location
                resultAd["price"]=price
                resultAd["priceInfo"]=priceInfo
                resultAd["caption"]=description
                resultAd["img"] = img
                resultAd["coordinates"] = coordinates
                resultAd["publishedDate"] = publishedDate
                resultAd["updatedDate"] = updatedDate
                resultAd["hash"] = hash.toString()
                result.add(Json.encodeToJsonElement(resultAd))
            }catch (e:Exception){
                println("Smth error in dm parse\n <${it.toString()}> \n\n" + e.message)
            }
        }
        return result
    }

    fun getJson(findElements: MutableList<WebElement>): String? {
        var result = mutableListOf<JsonElement>()

        for (ad in findElements){
            val descriptionDiv = ad.findElement(By.cssSelector("div:nth-of-type(2)"))

            // достаем url
            val url = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(2) > a")).getAttribute("href")

            // достаем цену
            val price = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(1) > div:nth-of-type(1)  > p:nth-of-type(1)")).text
            val priceInfo = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(1) > div:nth-of-type(1)  > p:nth-of-type(2)")).text

            // достаем название
            val titles: List<WebElement> = descriptionDiv.findElements(By.cssSelector("div:nth-of-type(2) > a:nth-of-type(1) > span"))
            var title = ""
            for (item in titles){
                title += "${item.text} "
            }

            // достаем адрес
            val address = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(2) > span")).text

            // достаем локацию до метро
            var location = ""
            val locations: List<WebElement> = descriptionDiv.findElements(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(3) > div:nth-of-type(1) > span"))
            for (item in locations){
                try {
                    location += "${item.text} "
                }catch(e: Exception){}
            }
            // достаем описание
            val description = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(4)")).text


            // можем даже все картинки (их url) достать без перехода по ссылкам (!!!) ** но надо пощелкать по кнопочкам(((
            val images: List<WebElement> = ad.findElements(By.cssSelector("div:nth-of-type(1) > div > div > div:nth-of-type(1) > div"))
        }


        var json = JsonArray(result)
        return Json.encodeToString(json)
    }

    private fun getUrlFromJSON(parameters: Map<String, String?>): String{
        //var result = "?deal_type=${urlConstructor[parameters["dealType"]]}"

        var result = if (parameters["dealType"].toBoolean()) "?deal_type=sale" else "?deal_type=rent"
        var priceType = ""
        if(parameters["priceType"].toBoolean()) priceType = "square_price__"
        else {
            if(parameters["dealType"] == "Снять") priceType = "rent_price__"
                else  priceType = "sale_price__"

        }

        var travelTimeType = ""
        if(parameters["travelType"].toBoolean()) travelTimeType = "time_on_foot__" else travelTimeType = "time_by_car__"

        for (key in parameters.keys){
            if(parameters[key] != null){
                var add = ""
                if(key != "dealType") add = "${Constants.dmUrlConstructor[parameters[key]]}"

                if(add == "null")
                {
                    if(key == "priceMin") { result += "&${priceType}gte=${parameters[key]}"; continue}
                    if(key == "priceMax") { result += "&${priceType}lte=${parameters[key]}"; continue}
                    if(key == "footageMin") { result += "&area__gte=${parameters[key]}"; continue}
                    if(key == "footageMax") { result += "&area__lte=${parameters[key]}"; continue}
                    //if(key == "") result += ""
                    if(key == "floorMin") { result += "&floor__gte=${parameters[key]}"; continue}
                    if(key == "floorMax") { result += "&floor__lte=${parameters[key]}"; continue}
                    if(key == "ceilingHeight"){  result += "&ceiling_height__gte=${parameters[key]}"; continue}
                    if(key == "minYear"){  result += "&build_year__gte=${parameters[key]}"; continue}
                    if(key == "maxYear") { result += "&build_year__lte=${parameters[key]}"; continue}
                    if(key == "travelTime") { result += "${travelTimeType}lte=${parameters[key]}"; continue}

                    // boolean parameters
                    if(key == "toilet"){
                        if(parameters["toilet"].toBoolean()) result+="&has_connected_bathrooms=1" else result+="&has_separated_bathrooms=1"
                        continue
                    }
                    if(key == "apartment"){
                        if(parameters["apartment"].toBoolean()) result+="&is_apartment=1" else result+="&is_apartment=0"
                        continue
                    }

                    //if(parameters["balcony"] as Boolean) result+=""

                    if(key == "rooms" || key == "amenities") {
                        val array = (parameters[key] as String).split(" ").toTypedArray()
                        for(item in array){
                            result += Constants.dmUrlConstructor[item]
                        }
                    continue
                    }
                }
                else result += add

            }
        }

        return result
            //@TODO(сделать проверку на RENT, чтобы в покупку не засовывались параметры RENT (если ввод ошибочный))
        // но если с клиента уже поступает нормально сфоримрованный JSON, нужно ли проверять?
    }




}