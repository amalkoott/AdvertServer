package models.test

import com.advert.plugins.Parameters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.jsoup.nodes.Document
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class CianParseModule: ParseVictim() {
    override var URL: String = "https://www.cian.ru/cat.php?currency=2&engine_version=2"
    override var queryParam: String = ""
    override var categories: List<String> = listOf()
    override fun setCategories() {
        TODO("Not yet implemented")
    }

    val cities = mapOf<String,String>(
        "Санкт-Петербург" to "&region=2",
        "Екатеринбург" to "ekaterinburg.",
        "Уфа" to "ufa.",
        "Краснодар" to "krasnodar.",
        "Тюмень" to "tyumen.",
        "Нижний Новгород" to "nn.",
        "Казань" to "kazan.",

        )
    private fun String.insertAt(index: Int, stringToInsert: String): String {
        require(index >= 0 && index <= length) { "Index is out of bounds." }
        return substring(0, index) + stringToInsert + substring(index)
    }
    override fun getResult(parameters: Map<String, String>, driver: WebDriver): String? {
        // формируем URL
        if(cities[parameters["city"]] != null){
            URL += getUrlFromJSON(parameters) + cities[parameters["city"]]
        } else return null

        // получаем web-страничку по URL
        driver.get(URL)

        val wait = WebDriverWait(driver, 10) // Устанавливаем ожидание до 30 секунд
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")))

        // разбираем web-страничку в json-строку
         // > [data-name='CardComponent'] > [data-testid='offer-card'] " ))
        //val result = getJson(driver.findElement(By.cssSelector("[data-name='SearchEngineResultsPage']")))// > div:nth-of-type(6)")))
        //val result = driver.findElement(By.cssSelector("#frontend-serp")).findElements(By.cssSelector("div > div:nth-of-type(6) > div"))
        val result = driver.pageSource
        // 4 div в контейнере div.data_name=SearchEngineResultsPage

        driver.quit()
        return result.toString()
    }

    fun getJson(resultDiv: WebElement?):String? {
        val ads: List<WebElement?>
        var result = mutableListOf<JsonElement>()
        try {
             ads = resultDiv!!.findElements(By.cssSelector("div > article[data-name='CardComponent'] > [data-testid='offer-card'] "))

            for (ad in ads){
                var resultAd = mutableMapOf<String,String>()
                val desciptionContainer = ad!!.findElement(By.cssSelector("[data-name='LinkArea']"))

                // достаем URL
                val url = desciptionContainer.findElement(By.tagName("a")).getAttribute("href")
                resultAd["url"]=url

                // достаем название
                val label = desciptionContainer.findElement(By.cssSelector("div:nth-of-type(1) > a > span > span")).text
                resultAd["label"]=label

                val divs: List<WebElement> = desciptionContainer.findElements(By.cssSelector("[data-name='LinkArea'] > div"))
                if (divs.size == 4){
                    // достаем местоположение
                    var travel = ""
                    for (item in desciptionContainer.findElements(By.cssSelector("div:nth-of-type(2) > div:nth-of-type(1) div"))){
                        travel += item.text
                    }
                    resultAd["travel"]=travel

                    var location = ""
                    for (a in desciptionContainer.findElements(By.cssSelector("div:nth-of-type(2) > div:nth-of-type(2) > a"))){
                        location += a.text
                    }
                    resultAd["location"]=location

                    // достаем цену
                    val price = desciptionContainer.findElement(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(1) > span > span")).text
                    val priceInfo = desciptionContainer.findElement(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(2) > p")).text
                    resultAd["price"]=price
                    resultAd["priceInfo"]=priceInfo

                    // достаем описание
                    val caption = desciptionContainer.findElement(By.cssSelector("div:nth-of-type(4) > [data-name='Description'] > p")).text
                    resultAd["caption"]=caption

                    // достаем картинки
                }else{
                    // достаем местоположение
                    var travel = ""
                    for (item in desciptionContainer.findElements(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(1) div"))){
                        travel += item.text
                    }
                    resultAd["travel"]=travel

                    var location = ""
                    for (a in desciptionContainer.findElements(By.cssSelector("div:nth-of-type(3) > div:nth-of-type(2) > a"))){
                        location += a.text
                    }
                    resultAd["location"]=location

                    // достаем цену
                    val price = desciptionContainer.findElement(By.cssSelector("div:nth-of-type(4) > div:nth-of-type(1) > span > span")).text
                    val priceInfo = desciptionContainer.findElement(By.cssSelector("div:nth-of-type(4) > div:nth-of-type(2) > p")).text
                    resultAd["price"]=price
                    resultAd["priceInfo"]=priceInfo

                    // достаем описание
                    val caption = desciptionContainer.findElement(By.cssSelector("div:nth-of-type(5) > [data-name='Description'] > p")).text
                    resultAd["caption"]=caption

                    // достаем картинки
                }
                result.add(Json.encodeToJsonElement(resultAd))
            }
        }catch (e:ArrayIndexOutOfBoundsException){
            println("Главный контейнер пустой!")
        }
        catch (e:Exception){
            println("Скорее всего, элемент не найден!")
        }
        finally {
            val json = JsonArray(result)
            return Json.encodeToString(json)
        }
    }

    val urlConstructor = mapOf<String,String>(
        "Снять" to "&deal_type=rent",
        "Посуточно" to "&type=2",
        "Долго" to "&type=-2",
        "Вторичка" to "&offer_type=flat",
        "Дом" to "offer_type=suburban",
        "Комната" to "&offer_type=flat&room0=1",

        "КупитьНовостройка" to "&offer_type=flat&object_type%5B0%5D=2",
        "КупитьВторичка" to "&offer_type=flat&object_type%5B0%5D=1",
        "КупитьДом" to "&offer_type=suburban&object_type%5B0%5D=1",

        //"Дом, дача" to "&category=living&offer_type=house",
        //"Таунхаус" to "&category=living&offer_type=townhouse",
        //"Коттедж" to "&category=living&offer_type=village",
        //"Участок" to "&category=living&offer_type=lot",
        //"Гараж" to "&category=garage&offer_type=garage",
        //"Бокс" to "&category=garage&offer_type=garage_box",
        //"Машиноместо" to "&category=garage&offer_type=parking_place",
        "БезЗалога" to "&pledge=0",
        "БезКомиссии" to "&zerocom=0",
        "БезРемонта" to "&repair=1",
        "Косметический" to "&repair=2",
        "Евро" to "&repair=3",
        "Дизайнерский" to "&repair=4",
        //"БезОтделки" to "&layout_renovation=no_renovation",
        "Черновая" to "&has_decoration=1",
        "Предчистовая" to "&has_decoration=1",
        "Чистовая" to "&has_decoration=1",
        "Монолитный" to "&house_material=2",
        "Кирпично-монолитный" to "&house_material=8",
        "Кирпичны" to "&house_material=1",
        "Панельный" to "&house_material=3",
        "Блочный" to "&house_material=4",
        "Деревянный" to "&house_material=5",
        "Сталинка" to "&house_material=6",
        "НеПервый" to "&is_first_floor=0",
        "НеПоследний" to "&floornl=1",
        "ТолькоПоследний" to "&floornl=0",
        "Собственник" to "&offer_seller_type=2",
        "Агент" to "&offer_seller_type=3",
        "Застройщик" to "&offer_seller_type=1",

        "Дети" to "&kids=1",
        "Животные" to "&pets=1",
        "Интернет" to "&internet=1",
        "Мебель" to "&mebel=1",
        "МебельНаКухне" to "&mebel_k=1",
        "Кондиционер" to "&conditioner=1",
        "Посудомойка" to "&dish_washer=1",
        "Телевизор" to "&tv=1",
        "Холодильник" to "&rfgr=1",

        "Студия" to "&room9=1",
        "1" to "&room1=1",
        "2" to "&room2=1",
        "3" to "&room3=1",
        "4+" to "&room4=1",

        "Посуточно" to "&type=2",
        "Надолго" to "&type=4",
        "НесколькоМесяцев" to "&type=3",
        "ОтГода" to "&type=-2",
    )

    fun getUrlFromJSON(parameters: Map<String, String>):String{
        var result = ""

        // если снимаем, то object_type не добавляем, если покупаем, то добавляем
        if (parameters["dealType"] == "Купить") result += urlConstructor["Купить"+parameters["category"]]

        var priceType = ""
        if(!parameters["priceType"].toBoolean()) priceType = "m2=1"

        var travelTimeType = ""
        if(parameters["travelType"].toBoolean()) travelTimeType = "&only_foot=2" else travelTimeType = "&only_foot=-2"

        for (key in parameters.keys){
            if(parameters[key] != null){
                var add = "${urlConstructor[parameters[key]]}"

                // если ничего не добаилось из категориальных параметров -> пытаемся добавить значимые параметры
                if(add == "null")
                {
                    if(key == "priceMin") { result += "&minprice=${parameters[key]}"; continue}
                    if(key == "priceMax") { result += "&maxprice=${parameters[key]}"; continue}
                    if(key == "footageMin") { result += "&minarea=${parameters[key]}"; continue}
                    if(key == "footageMax") { result += "&maxarea=${parameters[key]}"; continue}
                    //if(key == "") result += ""
                    if(key == "floorMin") { result += "&minfloor=${parameters[key]}"; continue}
                    if(key == "floorMax") { result += "&maxfloor=${parameters[key]}"; continue}
                    if(key == "ceilingHeight") { result += "&min_ceiling_height=${parameters[key]}"; continue}
                    if(key == "minYear"){  result += "&min_house_year=${parameters[key]}"; continue}
                    if(key == "maxYear") { result += "&max_house_year=${parameters[key]}"; continue}
                    if(key == "travelTime") { result += "${travelTimeType}&foot_min=${parameters[key]}"; continue}

                    // boolean parameters
                    if(key == "toilet"){
                        if(parameters["toilet"].toBoolean()) result+="&minsu_s=1" else result+="&minsu_r=1"
                        continue
                    }
                    if(key == "apartment"){
                        if(parameters["apartment"].toBoolean()) result+="&apartment=1" else result+="&only_flat=1"
                        continue
                    }

                    //if(parameters["balcony"] as Boolean) result+=""

                    if(key == "rooms" || key == "amenities") {
                        val array = (parameters[key] as String).split(" ").toTypedArray()
                        for(item in array){
                        result += urlConstructor[item]
                        // result += "&${key}=${item}"
                        }
                        continue
                    }
                }
                else result += add
                //}
            }
        }
        return result
    }

    override fun getFilter(doc: Document): String {
        TODO("Not yet implemented")
    }
}