package com.advert.tools

fun String.insertAt(index: Int, stringToInsert: String): String {
    require(index >= 0 && index <= length) { "Index is out of bounds." }
    val sub_1 = substring(0, index)
    val sub_2 = substring(index)
    return  sub_1 + stringToInsert + sub_2
}