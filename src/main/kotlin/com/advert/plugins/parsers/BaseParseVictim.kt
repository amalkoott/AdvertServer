package com.advert.plugins.parsers

import kotlinx.serialization.json.JsonElement
import org.openqa.selenium.WebDriver
import java.io.File

interface BaseParseVictim {
    fun getUrl(parameters: Map<String, String?>):String?
    fun getSiteName():String
    fun getResult(parameters: Map<String,String>, driver: WebDriver): String?
    fun parsePage(document: String): List<JsonElement>?
    fun parsePage(document: File): List<JsonElement>?
}