package com.advert.plugins.parsers

import com.advert.plugins.Parameters
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver
import java.io.File

interface BaseParseVictim {
    fun getUrl(parameters: Map<String, String?>):String?
    fun getSiteName():String
    fun getResult(parameters: Map<String,String>, driver: WebDriver): String?
    fun parsePage(document: String): List<JsonElement>?
   // fun parsePage(document: File): List<Map<String,String?>>?
   fun parsePage(document: File): List<JsonElement>?
    fun getFilter(doc: Document):String
}