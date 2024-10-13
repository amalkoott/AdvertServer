package com.advert

import com.advert.plugins.parsers.BaseParseVictim
import com.advert.tools.getPageRemoveSoon
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.util.*
import kotlinx.serialization.json.JsonElement
import org.openqa.selenium.Dimension
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.Proxy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.net.URLEncoder
import java.util.*
import kotlin.io.encoding.ExperimentalEncodingApi

fun encode(url: String) = URLEncoder.encode(url, "UTF-8")
 suspend fun getPage(url: String, parser: BaseParseVictim): String? {
     val encodedUrl = encode(url)

     val apiKey = "e96ca938c632739e05ad072b0af294f99d7d1ef4"
     var extractor = ""
     if(parser.getSiteName() == "dm") extractor = "&css_extractor=%257B%2522scripts%2522%253A%2522body%2520%253E%2520script%253Anot%28%255Bsrc%255D%29%253Anot%28%255Btype%255D%29%2522%257D"

     val apiUrl = "https://api.zenrows.com/v1/?apikey=$apiKey&url=${encodedUrl}&js_render=true&wait=5000&premium_proxy=true$extractor"//&css_extractor=$extractor"

     try {
         val client = HttpClient {
             install(HttpTimeout) {
                 requestTimeoutMillis = 240000 // 4 min
             }
         }
         val res = client.get(apiUrl).body<String>()
         return res
     }catch (e:Exception){
         println(e.message)
     }
     return null
}


class ListingProcessor(private val parser: BaseParseVictim) {
    // результат - список объявлений (list)
    suspend fun processListings(filterValue: Map<String,String?>):List<JsonElement>?{
        val url: String? = parser.getUrl(filterValue)
        var resultPage: String? = null

        // получение результата
        if (url != null){
            try {
                // получение странички по URL (через ZenRows)
                resultPage = getPage(url, parser)
                println(resultPage)
                return parser.parsePage(resultPage!!)

                // тестовый вариант ********
                // return parser.parsePage(getPageRemoveSoon(parser.getSiteName()))

            }catch (e: NullPointerException){
                println("Page not found!!!")
                return null
            }
        }
        return null
    }

    fun testProcessListings(param: String,params: Map<String,String?>):List<JsonElement>?{//List<Map<String,String?>>?{
        try {
            val url = parser.getUrl(params)

            val site = parser.getSiteName()
            val file = getPageRemoveSoon("$site/${site}_${param}.txt")
            if(param == "json_zenrows") println("ATTENTION!!!")
            return parser.parsePage(file)
        }catch (e: Exception){
            println(e.message)
        }
        return null
    }

}