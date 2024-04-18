package com.advert

import com.advert.plugins.configureRouting
import com.advert.plugins.configureSerialization
import io.ktor.server.application.*

/*
// 3-ий урок КЛИЕНТ НА СЕРВЕРЕ

suspend fun main() {
    //System.setProperty("webdriver.chrome.driver", "C://chromium//chromedriver-win64//chromedriver.exe")


    // Создание экземпляра веб-драйвера
    //val driver: WebDriver = ChromeDriver()

    val driver = chromeDriverInit()

    // Установка размера окна
    val dimension = Dimension(1180, 820)
    driver.manage().window().size = dimension
    // Открытие веб-страницы
    driver.get("https://www.citilink.ru/catalog/smartfony/")

    val wait = WebDriverWait(driver, 30) // Устанавливаем ожидание до 30 секунд
    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")))
    // Явное ожидание появления элемента
    //val divElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.css-1t3hvtt")))
    // filter names
    val divElements: List<WebElement?> = driver.findElements(By.cssSelector("[data-meta-name='FilterDropdown']"))
    //val divElement: WebElement? = driver.findElement(By.cssSelector("[data-meta-name='FilterListGroupsLayout'] > div:nth-of-type(2) [data-meta-name='FilterDropdown__title'] "))

/*
    try {
        val script = "document.querySelector('[data-meta-is-opened]').dataset.metaIsOpened = 'true';"
        (driver as JavascriptExecutor).executeScript(script)
    }catch (e:Exception){
        println("ohnoo")
    }
    Thread.sleep(1000)
*/
    val map = mutableMapOf<String,List<String>?>()
    for (element in divElements) {

        // titles
        val title = element!!.getAttribute("data-meta-value")
        map.put(title,null)

        // открыть фильтр
        if(element.getAttribute("data-meta-is-opened")=="false"){
            (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true);window.scrollBy(0, -100);", element)
           // (driver as JavascriptExecutor).executeScript("window.scrollBy(0, -100);")
            element.click()
        }


        // октрыть весь список
        try {

            val button = element.findElement(By.cssSelector("button[data-meta-name='FilterGroup__show-all']"))
            if (button != null){
                if(button.tagName.equals("button"))
                {
                    // Выполняем скрипт прокрутки до элемента
                    (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true);window.scrollBy(0, -100);", button)
                   // (driver as JavascriptExecutor).executeScript("window.scrollBy(0, -100);")

                    button.click()
                }
            }
        }
        catch (e: NoSuchElementException){
            println("Элемент не найден")
        }

        // values
        val childElement: List<WebElement> = element.findElements(By.cssSelector("[data-meta-name='FilterGroupLayout'] [data-meta-name='FilterLabel']"))
        if (!childElement.isEmpty()){
            val values = mutableListOf<String>()

            for (value in childElement){
                values.add(value.text)
            }
            map[title] = values
        }


            //println("Текст дочернего элемента: ${element?.text}")
    }

    //val text = divElement?.text
    //val pageSource: String = driver.pageSource
   // println(pageSource)
    // Закрытие браузера
    driver.quit()

}
fun chromeDriverInit(): ChromeDriver {
    //System.setProperty("webdriver.chrome.driver", Paths.get("chromium//chromedriver-win64//chromedriver.exe").toAbsolutePath().toString())
    System.setProperty("webdriver.chrome.driver", "C://chromium//chromedriver-win64//chromedriver.exe")
    //val chromePrefs = HashMap<String, Any>()
    //val downloadPath = File("downloads").absolutePath
    //chromePrefs["download.default_directory"] = downloadPath

    val options = ChromeOptions()
    options.addArguments("--remote-allow-origins=*")
    //options.setExperimentalOption("prefs", chromePrefs)
    //options.setCapability("downloadPath", downloadPath)
    //options.setCapability(ChromeOptions.CAPABILITY, this)
   // options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
   // options.setAcceptInsecureCerts(true)
    //options.setCapability("networkConnectionEnabled", true)
  //  options.setPageLoadStrategy(PageLoadStrategy.NORMAL)

    val driver = ChromeDriver(options)
    //driver.capabilities.getCapability("networkConnectionEnabled").let { println("networkConnectionEnabled ===: $it") }
    //driver.setLogLevel(Level.ALL)

/*
    try {
        driver.get("https://www.redbus.in/")
        driver.manage().run {
            addCookie(Cookie("token", "LhHL+mCvsy", "site.ru", "/", Date(2024,8,23), true, false))
            addCookie(
                Cookie(
                    "mutoken",
                    "znA7DNWJ47NgLte5gxk1IDYE6X3y7uT5x2jC9zwm4jPndk3lWjhAWLFUzlT9m0R",
                    "site.ru",
                    "/",
                    Date(2024,8,23),
                    true,
                    true
                )
            ) // sameSite = lax
        }
    } catch (e: Exception) {
        println(e.localizedMessage)
    }
    */
    return driver
}
*/

// 2-ой урок СОЗДАНИЕ МАРШРУТОВ

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}



// 1-ый урок
/*
fun main() {
    embeddedServer(Netty, port = 3000, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
}


/*
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("https://manserpatrice.medium.com/parse-html-with-jsoup-in-kotlin-69ab7fe4cb28")
    println(response.status)
    client.close()
*/
    /* "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36" */
    /*
    val url = "https://www.ozon.ru/category/elektronika-15500/"
    val doc = Jsoup.connect(url)
        .userAgent("Chrome/81.0.4044.138")
        .timeout(5000)
        .cookie("someCookie", "someValue")
        .referrer("http://google.com")
        .header("someHeader", "blabla")
        .get()
    println(doc)

     */

   // val ozonParser = OzonModule()
    val stlnkParser = SitilinkModule()

    //val processor1 = ListingProcessor(ozonParser)
    val processor2 = ListingProcessor(stlnkParser)

    val filterValue = "студии"
   // val parsedListings1 = processor1.processListings(filterValue)
    val parsedListings2 = processor2.getParameters()



 */
