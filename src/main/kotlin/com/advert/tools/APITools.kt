import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

fun String.toJsonFormat(): String{
    return this.split(",").joinToString(",") { part ->
        val keyValue = part.split(":")
        if (keyValue.size == 2) {
            val value = keyValue[1].trim()
            if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("\"") && value.endsWith("}"))) {
                "$part"
            } else {
                if(value[value.length - 1] == '}') {
                    "${keyValue[0]}: \"${value.dropLast(1)}\"}"
                } else{
                    "${keyValue[0]}: \"$value\""
                }
            }
        } else {
            part
        }
    }
}

fun getTestResult(params: Map<String,String?>):String?{
    val rent_flat_1 = mapOf<String,String?>(
        "hash" to "1000",
        "url" to "https://spb.cian.ru/rent/flat/300829506/",
        "sites" to "Циан",
        "title" to "2-комн. квартира, 50 м²",
        "price" to "40000",
        "address" to "Санкт-Петербург, р-н Кировский, Дачное, просп. Ветеранов, 21",
        "location" to "Проспект Ветеранов 6 мин.; Ленинский проспект 20 мин.;Автово 7 мин.",
        "description" to "Можно с животными! Арт. 63473445",
        "images" to "https://images.cdn-cian.ru/images/2147779551-1.jpg https://images.cdn-cian.ru/images/2147779563-1.jpg https://images.cdn-cian.ru/images/2147779600-1.jpg"
    )
    val rent_flat_2 = mapOf<String,String?>(
        "hash" to "1001",
        "url" to "https://spb.cian.ru/rent/flat/300781993/ https://www.avito.ru/ https://spb.domclick.ru/?from=topline2020",
        "sites" to "Авито, Циан, Домклик",
        "title" to "2-комн. квартира, 45,2 м²",
        "price" to "38000",
        "address" to "Санкт-Петербург, р-н Фрунзенский, № 72, просп. Славы, 40К3",
        "location" to "Проспект Славы 6 мин",
        "description" to "Уютная  теплая 2х комнатная  квартира в 5 минутах ходьбы от станции метро пр Славы. Дом находится в окружении многолетних деревьев. Комнаты не смежные.Окна выходят на разные стороны. Квартира в отлином состоянии  Ремот сделан в 2021 году. Полы- керамогранит на кухне и в прихожей. В комнатах -паркет и ламинат. Санузел совмещенный с выделенной душевой. Есть балкон. Район с развитой инфраструктурой. В пешей доступности д.сады, школы, аптеки, поликлиника, магазины и торговые центры.",
        "images" to "https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-prospekt-slavy-2146831973-1.jpg https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-prospekt-slavy-2146819549-1.jpg https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-prospekt-slavy-2146831966-1.jpg https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-prospekt-slavy-2146831962-1.jpg"
    )
    val rent_flat_3 = mapOf<String,String?>(
        "hash" to "1642",
        "url" to "https://spb.cian.ru/rent/flat/300919282/ https://www.avito.ru/",
        "sites" to "Циан, Авито",
        "title" to "2-комн. квартира, 44 м²",
        "price" to "31000",
        "address" to "Санкт-Петербург, р-н Калининский, Пискарёвка, дор. Кушелевская, 7к4",
        "location" to "Выборгская 8 мин",
        "description" to "Кирпично-монолитный дом ЖК \"Кантемировский\"  расположен в районе с развитой инфраструктурой, недалеко от метро Лесная. \nКвартира- ЕВРОДВУШКА сдается на длительный срок. Общая площадь с застекленной лоджией 46 кв.м., площадь комнаты 15 кв.м., кухни-гостиной 17 кв.м. Для комфортного проживания вся мебель и техника есть: два раскладных дивана, шкаф, комод, холодильник, стиральная машина, телевизор, микроволновая печь, стол, стулья, кухонный гарнитур.",
        "images" to "https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-doroga-kushelevskaya-2149320565-1.jpg https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-doroga-kushelevskaya-2149320569-1.jpg https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-doroga-kushelevskaya-2149320560-1.jpg https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-doroga-kushelevskaya-2149320561-1.jpg https://images.cdn-cian.ru/images/kvartira-sanktpeterburg-doroga-kushelevskaya-2149320571-1.jpg"
    )
    val rentResult = JsonArray(mutableListOf(Json.encodeToJsonElement(rent_flat_1), Json.encodeToJsonElement(rent_flat_2), Json.encodeToJsonElement(rent_flat_3)))

    val sale_flat_1 = mapOf<String,String?>(
        "hash" to "1460",
        "url" to "https://spb.domclick.ru/card/sale__flat__2056654341 https://spb.cian.ru/",
        "sites" to "Циан, Домклик",
        "title" to "Продаётся 2-комн. квартира, 83.1 м²",
        "price" to "10000000",
        "address" to "Санкт-Петербург, улица Кустодиева, 7к1",
        "location" to "Проспект Просвещения 33 мин.",
        "description" to "Квартира от Застройщика. Возможно кредитование по программам Господдержка-2020, Господдержка для семей с детьми, IT-ипотека. Жилой комплекс «Байрон» - истинный образец жилищного строительства комфорт-класса, современный, удобный и при этом уютный.\n" +
                "Здесь найдутся планировочные решения на любой вкус: европланировки с большой кухней-гостиной и традиционные, где гостиная спланирована отдельно, студии и многокомнатные квартиры.\n" +
                "Жилой комплекс расположен вдоль зеленой и тихой ул. Кустодиева в Выборгском районе Санкт-Петербурга, в квартале, ограниченном пр. Просвещения, ул. Кустодиева, ул. Руднева и Поэтическим бульваром.\n" +
                "Будущие жильцы по достоинству оценят спокойную, по-домашнему уютную и умиротворенную атмосферу этой части Выборгского района, в которой есть все для комфортной и гармоничной жизни. Здесь крупные парковые зоны и спокойные улочки, утопающие в зелени, где можно вести неторопливые прогулки на свежем воздухе; развита инфраструктура для велопрогулок: выделены дорожки и установлены вело-парковки; имеется множество объектов социальной инфраструктуры, обеспечивающих жителей всем необходимым для полноценной жизни.",
        "images" to "https://img.dmclk.ru/s1200x800q80/vitrina/e2/db/e2db33918b69d955546d3960db737519d47c8827.webp"
    )
    val sale_flat_2 = mapOf<String,String?>(
        "hash" to "1346",
        "url" to "https://www.avito.ru/sankt-peterburg/kvartiry/kvartira-studiya_248_m_1112_et._3551860487",
        "sites" to "Авито",
        "title" to "Квартира-студия, 24,8 м², 11/12 эт.",
        "price" to "4490000",
        "address" to "Санкт-Петербург, пр-т Ветеранов, 169к1 р-н Красносельский",
        "location" to null,
        "description" to "Арт. 56077361\nПродается светлая уютная студия в ЖК Солнечный город!\nОтличный вид из окон! Высокий этаж.\n Удобное расположение дома, рядом остановки общественного транспорта, магазины.\nКвартира с качественным ремонтом, полностью меблирована и с техникой!\nСделаны натяжные потолки и зональное освещение. Продуманы зоны хранения, в том числе на балконе.\nОтличный вариант для жизни и для инвестиций!",
        "images" to "https://20.img.avito.st/image/1/1.iV0Szra5JbQkZ-exDKy1dBFsJ7Ksb6e8ZGontqB7IbY.hoR8YeISSjRP_8ziZjtb-S4WrouaEOTcxER-WPZMT6k  https://60.img.avito.st/image/1/1.WYYAB7a59W82rjdqPmZlrwOl92m-pndndqP3bbKy8W0.hOXbzsUwUOduOHdqRzwosR0DPCdeAz0q95uDJ_tO36Q https://50.img.avito.st/image/1/1.xjDF67a5atnzQqjcxfD6GcZJaN97SujRs09o23debts.DH5J_I1qHwI491rehmSt8rXET6wcLl_8SupvWwizmTA https://10.img.avito.st/image/1/1.SDeklba55N6SPCbbuIB0Hqc35tgaNGbW0jHm3BYg4Nw.lqv6hHxtMGs7cYQciDFAyL1YZYZQp-5qiSFFMz9lBLs"
    )
    val saleResult = JsonArray(mutableListOf(Json.encodeToJsonElement(sale_flat_1), Json.encodeToJsonElement(sale_flat_2)))


    return if(params["dealType"]=="Снять") Json.encodeToString(saleResult) else Json.encodeToString(rentResult)
}