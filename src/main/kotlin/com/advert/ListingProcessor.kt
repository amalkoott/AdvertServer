package com.advert

import com.advert.plugins.parsers.BaseParseVictim
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.util.*
import kotlinx.serialization.json.JsonElement
import models.User
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.Dimension
import org.openqa.selenium.Proxy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.net.URLEncoder
import java.util.*
import kotlin.io.encoding.ExperimentalEncodingApi


fun encode(url: String) = URLEncoder.encode(url, "UTF-8")
 @OptIn(InternalAPI::class)
 suspend fun getPage(url: String): String? {
    // val searchUrl = "www.avito.ru"
     val encodedUrl = encode("https://spb.cian.ru/cat.php?currency=2&deal_type=sale&engine_version=2&maxprice=5000000&object_type%5B0%5D=1&offer_type=flat&region=2&room2=1")// encode(url)

     println(url)
     println(encodedUrl)

/* // TEST
     val random = (0..4).random()
     val urls = arrayOf<String>("https://youla.ru/sankt-peterburg/nedvijimost",
         "https://www.avito.ru/engels/kvartiry/prodam-ASgBAgICAUSSA8YQ?context=H4sIAAAAAAAA_0q0MrSqLraysFJKK8rPDUhMT1WyLrYyt1JKTixJzMlPV7KuBQQAAP__dhSE3CMAAAA",
         "https://spb.domclick.ru/search?deal_type=sale&category=living&offer_type=complex&offset=0",
         "https://spb.domclick.ru/search?deal_type=sale&category=living&offer_type=flat&offer_type=layout",
         "https://spb.domclick.ru/search?deal_type=rent&category=living&offer_type=house&offset=0")
     val searchUrl = urls[random]
     println("\nSEARCHING_URL:\n$searchUrl\n")

     //val godUrl = "https://spb.cian.ru/cat.php?deal_type=sale&engine_version=2&house_material%5B0%5D=1&kitchen_stove=gas&minsu_r=1&offer_type=flat&region=2&repair%5B0%5D=4&room1=1&room2=1&room_type=1&windows_type=1"
    //  val apiUrl = "https://api.zenrows.com/v1/?apikey=70361d6bc6e9dfd03c506ad17a5d8db975e9d62b&url=${encode(godUrl)}&js_render=true&wait=5000"
 */

     val apiKey = "843c3583225f953f8d69d8b49653379725c847b2" //70361d6bc6e9dfd03c506ad17a5d8db975e9d62b
     val apiUrl = "https://api.zenrows.com/v1/?apikey=$apiKey&url=${encodedUrl}&js_render=true&wait=5000&premium_proxy=true&css_extractor=%257B%2522scripts%2522%253A%2522body%2520%253E%2520script%255Btype%253D%27text%252Fjavascript%27%255D%2522%257D"
     try {
         val client = HttpClient {
             install(HttpTimeout) {
                 requestTimeoutMillis = 240000 // 4 min
             }
         }
         val res = client.get(apiUrl).body<String>()
         return res//.call.response//.content.readUTF8Line()//.toString()
     }catch (e:Exception){
         println(e.message)
     }
     return null
}


class ListingProcessor(private val parser: BaseParseVictim) {
    // результат - список объявлений (list)
    // объявления - словарь Map<String,String> ("url" to "...", "images" to "...", ...)
    suspend fun processListings(filterValue: Map<String,String?>):List<JsonElement>?{//List<Map<String,String?>>?{
        /*
        // создание поискового драйвера
       // val driver = chromeDriverInit()

        //testUserAgent(driver)
         */

        // генерация поискового-URL
        val url: String? = parser.getUrl(filterValue)
        var resultPage: String? = null

        // получение результата
        if (url != null){
            try {
                // получение странички по URL (через ZenRows)
               // resultPage = getPage(url)
              //  println(resultPage)
              // return parser.parsePage(resultPage!!)

                // тестовый вариант

                return parser.parsePage(getPageRemoveSoon(parser.getSiteName()))

            }catch (e: NullPointerException){
                println("Page not found!!!")
                return null
            }
        }
        return null
    }
// body > script:nth-child(13)
    fun getPageRemoveSoon(site:String): File{
        val filePath = "src/main/resources/examples/${site}_rent.html"
        val input = File(filePath)

        return input
    }
    fun getJsonRemoveSoon(site:String): File{
        val filePath = "src/main/resources/examples/cian_rent.html"
        val input = File(filePath)

        return input
    }
    @OptIn(ExperimentalEncodingApi::class)
    private suspend fun chromeDriverInit(): ChromeDriver { //System.setProperty("webdriver.chrome.driver", Paths.get("chromium//chromedriver-win64//chromedriver.exe").toAbsolutePath().toString())
        System.setProperty("webdriver.chrome.driver", "C://chromium//chromedriver-win64//chromedriver.exe")

        // window size
        val width = 1180
        val height = 820

        // options
        val options = ChromeOptions()
        options.addArguments("--remote-allow-origins=*")

        //options.addArguments("--headless")

        // убираем флаг навигации до установки настроек
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"))

        // отключение флага автоматической проверки
        options.addArguments("--disable-blink-features=AutomationControlled")

        options.setExperimentalOption("useAutomationExtension", false)

        /*
        //options.setExperimentalOption("useAutomationExtension", false)
        //#For ChromeDriver version 79.0.3945.16 or over

        */

        // Подключение профиля Dolphin к драйверу
        //val user = getUser()
       // options.addArguments("--remote-debugging-port=${user["port"]}");

        // Установка пользовательского агента для Chrome
        options.addArguments("window-size=$width,$height")
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")

        //options.setProxy(getProxy())
        //options.setCapability("proxy",getProxy())
       // options.addArguments("--disable-extensions")
        //options.setExperimentalOption("prefs", chromePrefs)
        //options.setCapability("downloadPath", downloadPath)
        //options.setCapability(ChromeOptions.CAPABILITY, this)
         //options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
         //options.setAcceptInsecureCerts(true)
        //options.setCapability("networkConnectionEnabled", true)

        // загрузка страницы NORMAL - ожидание полной загрузки
         // options.setPageLoadStrategy(PageLoadStrategy.NORMAL)

        // время ожидания неявной стратегии определения местоположения элемента на странице
        //options.setImplicitWaitTimeout(Duration.ofMillis(100))

        // папочка, где все хранится про юзера
      //  options.addArguments("--user-data-dir=src/main/userdata/1")

        // Установка пользовательского агента для Chrome
        //options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, например Gecko) Chrome/123.0.0.0 Safari/537.36 OPR/109.0.0.0")


        val driver = ChromeDriver(options)
        // установка размера окна
        val dimension = Dimension(width, height)
        driver.manage().window().size = dimension

        // меняем значение навигатора на неопределенное
        driver.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})")

        return driver
    }
    // создание юзера:
    // - один юзер - один прокси
    // -
    private fun user(){
        val users = listOf<User>(
           // User(userAgent = )
        )
    }
    // ротация UA
    private fun getUserAgent(){
        /*


        // ротация ЮА
        user_agent_list = [
            'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36',
            'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36',
            'Mozilla/5.0 (Macintosh; Intel Mac OS X 13_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15',
        ]

        url = 'https://httpbin.org/headers'
        for i in range(1, 4):
            user_agent = random.choice(user_agent_list)
            headers = {'User-Agent': user_agent}
            response = requests.get(url, headers=headers)
            received_ua = response.json()['headers']['User-Agent']

            print("Request #%d\nUser-Agent Sent: %s\nUser-Agent Received: %s\n" % (i, user_agent, received_ua))

         */
    }
    private fun testUserAgent(driver: ChromeDriver){
        //тест отправки верного юзерагента

        //headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36"}

       // # You can cian_rent.html if your web scraper is sending the correct header by sending a request to HTTPBin
        driver.get("https://httpbin.org/headers")
        print(driver.pageSource)
    }
    private fun getCookie(){

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
    }

    private fun getProxy(): Proxy{

       // 194.190.90.73:63090:dAajS3EE:CL5n3dUf
        // Set the proxy details
        val proxyAddress = "194.190.90.73"
        val proxyPort = 63090
        val proxyUsername = "dAajS3EE"
        val proxyPassword = "CL5n3dUf"


        // Create a Proxy object and set the HTTP and SSL proxies
        val proxy: Proxy = Proxy()

        proxy.setHttpProxy("$proxyAddress:$proxyPort")
        proxy.setSslProxy("$proxyAddress:$proxyPort")

        /*
        // Set proxy authentication
        val proxyAuth = "$proxyUsername:$proxyPassword"
       // proxy.proxyType = Proxy.ProxyType.MANUAL

        // Указываем адрес и порт прокси-сервера
        proxy.setHttpProxy("$proxyAuth@$proxyAddress:$proxyPort")
        proxy.setSslProxy("$proxyAuth@$proxyAddress:$proxyPort")
*/
            /*
        val proxy = Proxy()
        // Устанавливаем URL PAC-файла для прокси-автонастройки
        val pacUrl = "https://$proxyUsername:$proxyPassword@$proxyAddress:$proxyPort"
        proxy.setHttpProxy(pacUrl)
*/

            //val proxy = Proxy()
        proxy.setHttpProxy ("$proxyAddress:$proxyPort")
        proxy.setSslProxy("$proxyAddress:$proxyPort")
        proxy.proxyType = Proxy.ProxyType.MANUAL

        return proxy
    }
}