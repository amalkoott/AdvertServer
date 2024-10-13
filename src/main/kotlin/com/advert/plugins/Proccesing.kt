package com.advert.plugins

import com.advert.tools.getParseResult
import com.advert.tools.getResult
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

suspend fun processing(params: Map<String,String?>):String?{
    val parseResult = getParseResult(params)

    for (site in parseResult.values) {
        if (site!!.isEmpty()) return null
    }

    return Json.encodeToString(getResult(parseResult))
}