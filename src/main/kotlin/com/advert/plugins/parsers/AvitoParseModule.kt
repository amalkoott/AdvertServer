package com.advert.plugins.parsers

import kotlinx.serialization.json.JsonElement
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver
import java.io.File

// когда-нибудь и до авито руки дойдут...

class AvitoParseModule: ParseVictim() {
    override var URL: String = "https://www.ozon.ru/category/elektronika-15500/"
    override var queryParam: String = ""
    override var categories: List<String> = listOf()

    override fun getSiteName(): String {
        return "avito"
    }
    override fun getUrl(parameters: Map<String, String?>): String? {
        TODO("Not yet implemented")
    }

    override fun getResult(parameters: Map<String, String>, driver: WebDriver): String? {
        TODO("Not yet implemented")
    }

    override fun parsePage(document: String): List<JsonElement> {
        TODO("Not yet implemented")
    }

    override fun parsePage(document: File): List<JsonElement> {
        TODO("Not yet implemented")
    }

    fun getResult(doc: Document): String {
        TODO("Not yet implemented")
    }
}