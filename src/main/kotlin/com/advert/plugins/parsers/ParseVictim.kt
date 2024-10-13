package com.advert.plugins.parsers


abstract class ParseVictim: BaseParseVictim {
    abstract var URL: String
    abstract var queryParam: String
    abstract var categories: List<String>
}