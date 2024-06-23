package com.advert.plugins.parsers

import com.advert.plugins.parsers.BaseParseVictim

abstract class ParseVictim: BaseParseVictim {
    abstract var URL: String
    abstract var queryParam: String
    abstract var categories: List<String>
    abstract fun setCategories()
}