package models.test

import org.openqa.selenium.WebDriver
import com.advert.plugins.Parameters
import org.openqa.selenium.Dimension
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.Proxy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import routes.getUser
import java.util.*
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


class ListingProcessor(private val parser: BaseParseVictim) {

    suspend fun processListings(filterValue: Map<String,String>):String?{
        // создание поискового драйвера
        val driver = chromeDriverInit()

        return parser.getResult(filterValue, driver)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private suspend fun chromeDriverInit(): ChromeDriver { //System.setProperty("webdriver.chrome.driver", Paths.get("chromium//chromedriver-win64//chromedriver.exe").toAbsolutePath().toString())
        System.setProperty("webdriver.chrome.driver", "C://chromium//chromedriver-win64//chromedriver.exe")

        val options = ChromeOptions()
        options.addArguments("--remote-allow-origins=*")
        options.addArguments("--headless")

        // Подключение профиля Dolphin к драйверу
        //val user = getUser()
       // options.addArguments("--remote-debugging-port=${user["port"]}");
        val driver = ChromeDriver(options)


        //options.setProxy(getProxy())
        //options.setCapability("proxy",getProxy())
        options.addArguments("--disable-blink-features=AutomationControlled")
        options.addArguments("--disable-extensions")
        options.setExperimentalOption("useAutomationExtension", false)
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"))
        //options.setExperimentalOption("prefs", chromePrefs)
        //options.setCapability("downloadPath", downloadPath)
        options.setCapability(ChromeOptions.CAPABILITY, this)
         options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
         options.setAcceptInsecureCerts(true)
        options.setCapability("networkConnectionEnabled", true)
          options.setPageLoadStrategy(PageLoadStrategy.NORMAL)

        // Установка пользовательского агента для Chrome
        //options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, например Gecko) Chrome/123.0.0.0 Safari/537.36 OPR/109.0.0.0")




        val dimension = Dimension(1180, 820)
        driver.manage().window().size = dimension
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
        driver.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})")
        return driver
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