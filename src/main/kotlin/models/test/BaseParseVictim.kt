package models.test

import com.advert.plugins.Parameters
import kotlinx.serialization.json.JsonObject
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver

interface BaseParseVictim {
    fun getHtmlByQueryParam(parameters: Parameters): Document
    fun getResult(parameters: Parameters, driver: WebDriver): String?
    fun getResult(parameters: Map<String,String>, driver: WebDriver): String?
    fun getFilter(doc: Document):String
}