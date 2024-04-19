package models.test

import com.advert.plugins.Parameters
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver

class AvitoParseModule:ParseVictim() {
    override var URL: String = "https://www.ozon.ru/category/elektronika-15500/"
    override var queryParam: String = ""
    override var categories: List<String> = listOf()
    override fun setCategories() {
        TODO("Not yet implemented")
    }

    override fun getResult(parameters: Map<String, String>, driver: WebDriver): String? {
        TODO("Not yet implemented")
    }

    fun getResult(doc: Document): String {
        TODO("Not yet implemented")
    }

    override fun getFilter(doc: Document): String {
        TODO("Not yet implemented")
    }
}