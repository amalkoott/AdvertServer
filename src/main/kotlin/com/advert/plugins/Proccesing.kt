package com.advert.plugins

import kotlinx.serialization.json.Json
import models.test.*
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.*
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
//import routes.Parameters
//import routes.RequestParams

// обработка запросов клиента


// функция, которая принимает JSON с указанной категорией (и запускает парсер)
// проверяет значения json-ов на соответствие всего и вся
fun processing(params:Parameters): String? {
    try {
        val avito = AvitoParseModule()
        val cian = CianParseModule()

        val processor1 = ListingProcessor(avito)
        val processor2 = ListingProcessor(cian)

        // результат
        val parsedListings1 = processor1.processListings(params)
        val parsedListings2 = processor2.processListings(params)


        return "OK"
    }
    catch (e:NullPointerException){
        return "Категория не найдена"
    }
    catch (e:Exception){
        return "Непредвиденная ошибка при обработке параметров"
    }
}
fun processing(params: Map<String,String>){
    val avito = AvitoParseModule()
    val cian = CianParseModule()
    val domclick = DomclickParseModule()

    val processor1 = ListingProcessor(domclick)

    // результат
    val parsedListings1 = processor1.processListings(params)
}

/*

Квартиры
{
  "deal_type": "купить",
  "renovation": "БезОтделки"
  "category": "апартаменты",
  "priceMin": 50000.0,
  "priceMax": 100000.0,
  "city": "Москва",
  "includeWords": "новый дом",
  "excludeWords": "ремонт",
  "priceType": false,
  "footageMin": 40,
  "footageMax": 70,
  "floorMin": 2,
  "floorMax": 15,
  "ceilingHeight": 2,
  "locationType": "район",
  "locationValue": "Центральный",
  "material": "кирпич",
  "year": "2015",
  "layout": true,
  "sellerType": "Агент",
  "roomCount": 2,
  "flatType": "Квартира",
  "balcony": true,
  "floorType": "НеПервый",
  "travelTime": 15,
  "travelTimeType": true,
  "toilet": true
}


 */


/*
 Квартиры съем
{
 "deal_type": "снять",
  "category": "Недвижимость",
  "priceMin": 30000.0,
  "priceMax": 50000.0,
  "city": "Санкт-Петербург",
  "includeWords": "мебель, бытовая техника",
  "excludeWords": "ремонт",
  "priceType": true,
  "footageMin": 50,
  "footageMax": 80,
  "floorMin": 3,
  "floorMax": 10,
  "ceilingHeight": 1,
  "locationType": true,
  "locationValue": "Приморская",
  "material": "панель",
  "year": "2000",
  "layout": true,
  "sellerType": "Собственник",
  "roomCount": 2,
  "flatType": "Апартамены",
  "balcony": true,
  "floorType": "НеПоследний",
  "travelTime": 10,
  "travelTimeType": true,
  "toilet": true,
  "rent": true,
  "rentDateFrom": "01.05.2024",
  "rentDateTo": "01.05.2025",
  "guestCount": 2,
  "rules": "Дети",
  "paymentsRules": "БезЗалога"
}


 */

/*
Загород
{
  "category": "дом",
  "priceMin": 100000.0,
  "priceMax": 200000.0,
  "city": "Киев",
  "includeWords": "сад, лес",
  "excludeWords": "ремонт",
  "priceType": true,
  "footageMin": 100,
  "footageMax": 300,
  "floorMin": 1,
  "floorMax": 2,
  "ceilingHeight": 2,
  "locationType": "район",
  "locationValue": "Подол",
  "material": "кирпич",
  "year": "2010",
  "layout": true,
  "sellerType": "Собственник",
  "roomCount": null,
  "houseType": "коттедж",
  "footageArea": 1000,
  "timeToCity": 30,
  "toilet": true
}


 */

/*
Загород съем
{
  "category": "дом",
  "priceMin": 100000.0,
  "priceMax": 200000.0,
  "city": "Москва",
  "includeWords": "сад, лес",
  "excludeWords": "ремонт",
  "priceType": true,
  "footageMin": 100,
  "footageMax": 300,
  "floorMin": 1,
  "floorMax": 2,
  "ceilingHeight": 2,
  "locationType": "район",
  "locationValue": "Таганский",
  "material": "кирпич",
  "year": "2010",
  "layout": true,
  "sellerType": "Собственник",
  "roomCount": null,
  "houseType": "коттедж",
  "footageArea": 1000,
  "timeToCity": 30,
  "toilet": true,
  "rent": true,
  "rentDateFrom": "2024-05-01",
  "rentDateTo": "2025-05-01",
  "guestCount": 4,
  "rules": "Животные",
  "paymentsRules": "БезКомиссии"
}
 */