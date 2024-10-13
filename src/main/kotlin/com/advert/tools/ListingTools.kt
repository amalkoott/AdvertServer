package com.advert.tools

import java.io.File


fun getPageRemoveSoon(site:String): File {
    // docker
    //val filePath = "/app/examples/${site}"

    val filePath = "src/main/resources/examples/${site}"
    val input = File(filePath)

    return input
}