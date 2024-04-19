package models.test

import com.advert.plugins.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import org.jsoup.nodes.Document
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class DomclickParseModule:  ParseVictim() {
    override var URL: String = "https://domclick.ru/"
    override var queryParam: String = ""
    override var categories: List<String> = listOf()
    val cities = mapOf<String,String>(
        "Санкт-Петербург" to "spb.",
        "Екатеринбург" to "ekaterinburg.",
        "Уфа" to "ufa.",
        "Краснодар" to "krasnodar.",
        "Тюмень" to "tyumen.",
        "Нижний Новгород" to "nn.",
        "Казань" to "kazan.",

    )
    override fun setCategories() {
        TODO("Not yet implemented")
    }
    override fun getResult(parameters: Map<String,String>, driver: WebDriver): String? {
        // если города на сайте нет - поиска тоже нет
        if(cities[parameters["city"]] != null){
            URL = URL.insertAt(8,cities[parameters["city"]]!!)
        } else return null

        URL += "search" + getUrlFromJSON(parameters)

        // получаем web-страничку по URL
        driver.get(URL)//"https://www.google.com/")

        val wait = WebDriverWait(driver, 10) // Устанавливаем ожидание до 30 секунд
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")))

        // разбираем web-страничку в json-строку
        // > [data-name='CardComponent'] > [data-testid='offer-card'] " ))
        val ads = driver.findElement(By.className("mainContent")).findElements(By.cssSelector("div:nth-of-type(1) > div:nth-of-type(2) > section > section > div:nth-of-type(1) > [data-e2e-id='offers-list__item'] > div > div > div:nth-of-type(1)"))

        val result = getJson(ads)

        // 4 div в контейнере div.data_name=SearchEngineResultsPage

        driver.quit()

        return result
    }
    override fun getFilter(doc: Document): String {
        TODO("Not yet implemented")
    }

    fun getJson(findElements: MutableList<WebElement>): String? {
        var result = mutableListOf<JsonElement>()

        for (ad in findElements){
            val descriptionDiv = ad.findElement(By.cssSelector("div:nth-of-type(2)"))

            // достаем url
            val url = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(2) > a")).getAttribute("href")

            // достаем цену
            val price = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(1) > div:nth-of-type(1)  > p:nth-of-type(1)")).text
            val priceInfo = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(1) > div:nth-of-type(1)  > p:nth-of-type(2)")).text

            // достаем название
            val titles: List<WebElement> = descriptionDiv.findElements(By.cssSelector("div:nth-of-type(2) > a:nth-of-type(1) > span"))
            var title = ""
            for (item in titles){
                title += "${item.text} "
            }

            // достаем адрес
            val address = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(2) > span")).text

            // достаем локацию до метро
            var location = ""
            val locations: List<WebElement> = descriptionDiv.findElements(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(3) > div:nth-of-type(1) > span"))
            for (item in locations){
                try {
                    location += "${item.text} "
                }catch(e: Exception){}
            }
            // достаем описание
            val description = descriptionDiv.findElement(By.cssSelector("div:nth-of-type(4)")).text


            // можем даже все картинки (их url) достать без перехода по ссылкам (охуеть ваще) ** но надо пощелкать по кнопочкам(((
            //val images: List<WebElement> = ad.findElements(By.cssSelector("div:nth-of-type(1) > div > div > div:nth-of-type(1) > div"))
        }


        var json = JsonArray(result)
        return Json.encodeToString(json)
    }

    val urlConstructor = mapOf<String,String>(
        "Снять" to "rent",
        "Купить" to "sale",
        "Вторичка" to "&category=living&offer_type=flat",
        "Новостройка" to "&category=living&offer_type=layout",
        "Комната" to "&category=living&offer_type=room",
        "Дом, дача" to "&category=living&offer_type=house",
        "Таунхаус" to "&category=living&offer_type=townhouse",
        "Коттедж" to "&category=living&offer_type=village",
        "Участок" to "&category=living&offer_type=lot",
        "Гараж" to "&category=garage&offer_type=garage",
        "Бокс" to "&category=garage&offer_type=garage_box",
        "Машиноместо" to "&category=garage&offer_type=parking_place",
        "БезЗалога" to "&has_deposit=0",
        "БезКомиссии" to "&has_commission=0",
        "БезРемонта" to "&renovation=without_repair",
        "Косметический" to "&renovation=standart",
        "Евро" to "&renovation=well_done",
        "Дизайнерский" to "&renovation=design",
        "БезОтделки" to "&layout_renovation=no_renovation",
        "Черновая" to "&layout_renovation=rough_finish",
        "Предчистовая" to "&layout_renovation=pre_finish",
        "Чистовая" to "&layout_renovation=fine_finish",
        "Монолитный" to "&wall_type=monolith",
        "Кирпично-монолитный" to "&wall_type=brick_monolith",
        "Кирпичны" to "&wall_type=brick",
        "Панельный" to "&wall_type=panel",
        "Блочный" to "&wall_type=block",
        "Деревянный" to "&wall_type=wood",
        "НеПервый" to "&floor_not_last=1",
        "НеПоследний" to "&floor_not_first=1",
        "ТолькоПоследний" to "&floor_last=1",
        "Собственник" to "&is_owner=1",
        "Дети" to "&with_children=1",
        "Животные" to "&with_animals=1",
        "Интернет" to "&amenities=internet",
        "Мебель" to "&amenities=living_furniture",
        "МебельНаКухне" to "&amenities=kitchen_furniture",
        "Кондиционер" to "&amenities=conditioner",
        "Посудомойка" to "&amenities=dish_washer",
        "Телевизор" to "&amenities=tv",
        "Холодильник" to "&amenities=refrigerator",
        "Студия" to "&rooms=st",
        "1" to "&rooms=1",
        "2" to "&rooms=2",
        "3" to "&rooms=3",
        "4+" to "&rooms=4",
    )
    /*
"locationType": "район",
  "locationValue": "Центральный",
  "city": "Москва",
  "includeWords": "новый дом",
  "excludeWords": "ремонт",
     */

    private fun getUrlFromJSON(parameters: Map<String, String>): String{
        var result = "?deal_type=${urlConstructor[parameters["dealType"]]}"

        var priceType = ""
        if(parameters["priceType"].toBoolean()) priceType = "square_price__"
        else {
            if(parameters["dealType"] == "Снять") priceType = "rent_price__"
                else  priceType = "sale_price__"

        }

        var travelTimeType = ""
        if(parameters["travelType"].toBoolean()) travelTimeType = "time_on_foot__" else travelTimeType = "time_by_car__"

        for (key in parameters.keys){
            if(parameters[key] != null){
                var add = ""
                if(key != "dealType") add = "${urlConstructor[parameters[key]]}"

                if(add == "null")
                {
                    if(key == "priceMin") { result += "&${priceType}gte=${parameters[key]}"; continue}
                    if(key == "priceMax") { result += "&${priceType}lte=${parameters[key]}"; continue}
                    if(key == "footageMin") { result += "&area__gte=${parameters[key]}"; continue}
                    if(key == "footageMax") { result += "&area__lte=${parameters[key]}"; continue}
                    //if(key == "") result += ""
                    if(key == "floorMin") { result += "&floor__gte=${parameters[key]}"; continue}
                    if(key == "floorMax") { result += "&floor__lte=${parameters[key]}"; continue}
                    if(key == "ceilingHeight"){  result += "&ceiling_height__gte=${parameters[key]}"; continue}
                    if(key == "minYear"){  result += "&build_year__gte=${parameters[key]}"; continue}
                    if(key == "maxYear") { result += "&build_year__lte=${parameters[key]}"; continue}
                    if(key == "travelTime") { result += "${travelTimeType}lte=${parameters[key]}"; continue}

                    // boolean parameters
                    if(key == "toilet"){
                        if(parameters["toilet"].toBoolean()) result+="&has_connected_bathrooms=1" else result+="&has_separated_bathrooms=1"
                        continue
                    }
                    if(key == "apartment"){
                        if(parameters["apartment"].toBoolean()) result+="&is_apartment=1" else result+="&is_apartment=0"
                        continue
                    }

                    //if(parameters["balcony"] as Boolean) result+=""

                    if(key == "rooms" || key == "amenities") {
                        val array = (parameters[key] as String).split(" ").toTypedArray()
                        for(item in array){
                            result += urlConstructor[item]
                        }
                    continue
                    }
                }
                else result += add

            }
        }

       // if(parameters["toilet"].toBoolean()) result+="&has_connected_bathrooms=1" else result+="&has_separated_bathrooms=1"
        //if(parameters["apartment"].toBoolean()) result+="&is_apartment=1" else result+="&is_apartment=0"
        ///if(parameters["balcony"] as Boolean) result+=""

        return result
            //@TODO(сделать проверку на RENT, чтобы в покупку не засовывались параметры RENT (если ввод ошибочный))
        // но если с клиента уже поступает нормально сфоримрованный JSON, нужно ли проверять?
    }
    private fun String.insertAt(index: Int, stringToInsert: String): String {
        require(index >= 0 && index <= length) { "Index is out of bounds." }
        val sub_1 = substring(0, index)
        val sub_2 = substring(index)
        return  sub_1 + stringToInsert + sub_2
    }

    val flatConstructorMap = mapOf<Any,Any>(
        "category" to mapOf<String,String>(
            "Вторичка" to "&category=living&offer_type=flat&",
            "Новостройка" to "&category=living&offer_type=layout&",
            "Комната" to "&category=living&offer_type=room&",
            "Дом, дача" to "&category=living&offer_type=house&",
            "Таунхаус" to "&category=living&offer_type=townhouse&",
            "Коттедж" to "&category=living&offer_type=village&",
            "Участок" to "&category=living&offer_type=lot&",
            "Гараж" to "&category=garage&offer_type=garage&",
            "Бокс" to "&category=garage&offer_type=garage_box&",
            "Машиноместо" to "&category=garage&offer_type=parking_place&",
        ),
        "paymentsRules" to mapOf<String,String>(
            "БезЗалога" to "has_deposit=0",
            "БезКомиссии" to "has_commission=0"
        ),
        "priceType" to mapOf<Boolean,String>(
            true to "&sale_price__",
            false to "&square_price__"
        ),
        "renovation" to mapOf<String,String>(
            "БезРемонта" to "&renovation=without_repair&",
            "Косметический" to "&renovation=standart&",
            "Евро" to "&renovation=well_done&",
            "Дизайнерский" to "&renovation=design&",
        )
    )
    private fun urlConstructorFlatDomclick(parameters: Flat, dealType: String): String{

       // var category = flatConstructorMap[parameters.category]

        var result = "deal_type=$dealType${flatConstructorMap[parameters.category]}"

        if (parameters.priceMin != null) result += "${flatConstructorMap[parameters.priceType]}gte=${parameters.priceMin}"
        if (parameters.priceMax != null) result += "${flatConstructorMap[parameters.priceType]}lte=${parameters.priceMax}"

        var te = flatConstructorMap["renovation"]

        return result
    }
    private fun urlConstructorFlatRentDomclick(parameters: FlatRent, dealType: String): String{

        var category = flatConstructorMap[parameters.category]

        var result = "deal_type=$dealType$category"
        if (parameters.priceMin != null) result += "rent_price__gte=${parameters.priceMin}"
        if (parameters.priceMax != null) result += "rent_price__lte=${parameters.priceMax}"


        return ""
    }


}