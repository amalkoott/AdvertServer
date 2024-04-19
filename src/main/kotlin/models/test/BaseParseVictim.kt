package models.test

import com.advert.plugins.Parameters
import kotlinx.serialization.json.JsonObject
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver

interface BaseParseVictim {
    fun getResult(parameters: Map<String,String>, driver: WebDriver): String?
    fun getFilter(doc: Document):String
}