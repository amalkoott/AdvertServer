package com.advert.plugins.parsers

import com.advert.constant.Constants.cianCities
import com.advert.constant.Constants.cianUrlConstructor
import kotlinx.serialization.json.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File


class CianParseModule: ParseVictim() {
    override var URL: String = "https://www.cian.ru/cat.php?currency=2&engine_version=2"
    override var queryParam: String = ""
    override var categories: List<String> = listOf()


    override fun getSiteName(): String {
        return "cian"
    }
    override fun getUrl(parameters: Map<String, String?>): String?{
        if(cianCities[parameters["city"]] != null){
            URL += getUrlFromJSON(parameters) + cianCities[parameters["city"]]
            return URL
        } else return null
    }
    override fun getResult(parameters: Map<String, String>, driver: WebDriver): String? {
        // формируем URL
        if(cianCities[parameters["city"]] != null){
            URL += getUrlFromJSON(parameters) + cianCities[parameters["city"]]
        } else return null

        // получаем web-страничку по URL
        driver.get(URL)

        val wait = WebDriverWait(driver, 10) // Устанавливаем ожидание до 30 секунд
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")))

        // разбираем web-страничку в json-строку
        val result = driver.pageSource

        driver.quit()
        return result.toString()
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
    // парсер с json
    private fun getResult(doc: Document): List<JsonElement>{
        try {
            val test: String = doc.getElementsByTag("body").text().replace("\\\\u002F","/").replace("\\\\\"","'").replace("\\\"","\"").replace("\\\'","\'")
            // достаем jsonArray между "offers" и "paginationUrls"
            val subString = '[' + test.substringAfter("\\\"offers\\\":[").substringBefore("],\\\"paginationUrls\\\"") + ']'
            val jsonAds = Json.parseToJsonElement(subString).jsonArray

            return getRentFromJson(jsonAds)
        }catch (e: Exception){
            println("Something error for <${doc.location()}>! Error message:\n${e.message}")
            return emptyList()
        }
    }

    private fun getRentFromJson(jsonAds: JsonArray): List<JsonElement>{
        var result = mutableListOf<JsonElement>()

        jsonAds.forEach{
            val ad = it.jsonObject

            val url =  ad["fullUrl"].toString().replace("\"","")// + path (это ссылка)

            // это можно в title собират  house        object info (инфа с метражем)
            val title = ad["formattedFullInfo"].toString().replace("\"","")

            val description = ad["description"].toString().replace("\"","") // + description

            val location = ad["geo"]!!.jsonObject["userInput"].toString().replace("\"","").replace("Россия, ", "") //         + address

            val subways = ad["geo"]!!.jsonObject["undergrounds"]!!.jsonArray
            var travel =  ""// ????
            if (subways!!.size != 0){
                subways.forEach{
                    val sub = it.jsonObject
                    val travelType = if (sub["transportType"].toString() == "walk") "пешком" else "транспортом"
                    travel += "м. ${sub["name"].toString().replace("\"","")}, ${sub["time"]} мин. $travelType; "
                }
            }

            val coord = ad["geo"]!!.jsonObject["coordinates"]!!.jsonObject
            val coordinates = "${coord["lng"]} ${coord["lat"]}"  //         + location (координаты)

            val price = ad["bargainTerms"]!!.jsonObject["price"].toString().replace("\"","") // + price
            var priceInfo = ad["formattedAdditionalInfo"].toString().replace("\"","")

            val photos = ad["photos"]!!.jsonArray
            var img = ""
            photos.forEach{
                val uri = it.jsonObject["fullUrl"].toString().replace("\"","")
                img += "${uri} "
            }

            val publishedDate = ad["creationDate"].toString().replace("\"","") // "publishedDate":"2024-04-29T13:24:08+00:00",

            val updatedDate = ad["updatedDate"]?.toString()?.replace("\"","") // "updatedDate":"2024-04-29T19:28:06.909845+00:00",

            val area = ad["totalArea"].toString().replace("\"","")
            val floor = ad["floorNumber"].toString().replace("\"","")
            val floors = ad["building"]!!.jsonObject["floorsCount"].toString().replace("\"","")
            val rooms = ad["roomsCount"].toString().replace("\"","")
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
            resultAd["updatedDate"] = ""
            resultAd["hash"] = hash.toString() // по площади, этажу/этажности, кол-во комнат, адрес, цена
            result.add(Json.encodeToJsonElement(resultAd))

        }

        return result
    }


    fun getUrlFromJSON(parameters: Map<String, String?>):String{
        var result = ""

        // false - купить, true - снять
        // если снимаем, то object_type не добавляем, если покупаем, то добавляем
        if (!parameters["dealType"].toBoolean()) result += cianUrlConstructor["Купить"]
        else result += cianUrlConstructor["Снять"]
            //result += urlConstructor["Купить"+parameters["category"]]

        var priceType = ""
        if(!parameters["priceType"].toBoolean()) priceType = "m2=1"

        var travelTimeType = ""
        if(parameters["travelType"].toBoolean()) travelTimeType = "&only_foot=2" else travelTimeType = "&only_foot=-2"

        for (key in parameters.keys){
            if(parameters[key] != null){
                var add = "${cianUrlConstructor[parameters[key]]}"

                // если ничего не добаилось из категориальных параметров -> пытаемся добавить значимые параметры
                if(add == "null")
                {
                    if(key == "priceMin") { result += "&minprice=${parameters[key]}"; continue}
                    if(key == "priceMax") { result += "&maxprice=${parameters[key]}"; continue}
                    if(key == "minArea") { result += "&minarea=${parameters[key]}"; continue}
                    if(key == "maxArea") { result += "&maxarea=${parameters[key]}"; continue}
                    if(key == "minFloor") { result += "&minfloor=${parameters[key]}"; continue}
                    if(key == "maxFloor") { result += "&maxfloor=${parameters[key]}"; continue}
                    if(key == "cell") { result += "&min_ceiling_height=${parameters[key]}"; continue}
                    if(key == "minYear"){  result += "&min_house_year=${parameters[key]}"; continue}
                    if(key == "maxYear") { result += "&max_house_year=${parameters[key]}"; continue}
                    if(key == "travelTime") { result += "${travelTimeType}&foot_min=${parameters[key]}"; continue}

                    // boolean parameters
                    if(key == "toiletType"){
                        if(parameters["toiletType"].toBoolean()) result+="&minsu_s=1" else result+="&minsu_r=1"
                        continue
                    }
                    if(key == "apart"){
                        if(parameters["apart"].toBoolean()) result+="&apartment=1" else result+="&only_flat=1"
                        continue
                    }

                    if(key == "rentType"){
                        if(parameters["rentType"].toBoolean()) result+="&type=4" else result+="&type=2"
                        continue
                    }

                    if(key == "room" || key == "amenities") {
                        val array = (parameters[key] as String).split(" ").toTypedArray()
                        for(item in array){
                        result += cianUrlConstructor[item]
                        }
                        continue
                    }
                }
                else result += add
            }
        }
        return result
    }
}