package com.advert.plugins

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import java.util.*
import javax.xml.crypto.Data

object RequestParams : JsonContentPolymorphicSerializer<Parameters>(Parameters::class) {
    override fun selectDeserializer(content: JsonElement) = when {
        ("houseType" in content.jsonObject && "rent" in content.jsonObject) -> HouseRent.serializer()
        ("flatType" in content.jsonObject && "rent" in content.jsonObject) -> FlatRent.serializer()
        ("houseType" in content.jsonObject) -> House.serializer()
        ("flatType" in content.jsonObject ) -> Flat.serializer()
        else -> Transport.serializer()
    }
}

interface Parameters {
    val category: String
    val priceMin: Float
    val priceMax: Float
    val city: String
    val includeWords: String
    val excludeWords: String
}

interface RealEstate : Parameters {
     val priceType: Boolean// true - за все, false - за м2
     val footageMin: Int
     val footageMax: Int
     val floorMin: Byte
     val floorMax: Byte
     val ceilingHeight: Byte // 0 - 2.5, 1 - 2.7, 2 - 3, 3 - 3.5
     val locationType: Boolean// 0 - метро/ 1 -район
     val locationValue: String
     val material: String
     val year: String // год постройки
     val layout: Boolean // 1 - смежная, 0 - изолированная
     val sellerType: Seller
     val renovation: RenovationType
    val roomCount: Byte//(0,1,2,3,4,5,null), 0 - студия, null - свободная
}

interface RentRealEstate {
    val rent: Boolean // 0 - купить, 1 - снять
    val rentDateFrom: String // null - покупаем, а не снимаем
    val rentDateTo: String
    val guestCount: Byte
    val rules: Rules
    val paymentsRules: PaymentRules
}
enum class RenovationType{
    БезРемонта,
    Косметический,
    Евро,
    Дизайнерский,
    БезОтделки,
    Черновая,
    Предчистовая,
    Чистовая
}
enum class Rules{
    Дети,
    Животные,
    Курение
}
enum class PaymentRules{
    БезЗалога,
    БезКомиссии
}
enum class FlatType{
    Неважно,
    Квартира,
    Апартаменты
}
enum class Seller{
    Агент,
    Собственник
}
enum class FloorType{
    НеПервый,
    НеПоследний,
    ТолькоПоследний
}

@Serializable
data class Flat(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,

    override val priceType: Boolean, // true - за все, false - за м2
    override val footageMin: Int,
    override val footageMax: Int,
    override val floorMin: Byte,
    override val floorMax: Byte,
    override val ceilingHeight: Byte, // 0 - 2.5, 1 - 2.7, 2 - 3, 3 - 3.5
    override val locationType: Boolean,// метро/район
    override  val locationValue: String,
    override  val material: String,
    override  val year: String, // год постройки
    override  val layout: Boolean, // 1 - смежная, 0 - изолированная
    override val sellerType: Seller,
    override val renovation: RenovationType,
    override val roomCount: Byte,//(0,1,2,3,4,5,null), 0 - студия, null - свободная

    val flatType: FlatType,
    val balcony: Boolean, // 0 - balkon, 1 - lodzhia
    val floorType: FloorType, //(не первый\не последний\только последний)
     val travelTime: Byte,
     val travelTimeType: Boolean, // 0 - пешком, 1 - транспорт
     val toilet: Boolean, // 0 - слитный, 1 - раздельный
) : RealEstate

@Serializable
data class FlatRent(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,

    override val priceType: Boolean, // true - за все, false - за м2
    override val footageMin: Int,
    override val footageMax: Int,
    override val floorMin: Byte,
    override val floorMax: Byte,
    override val ceilingHeight: Byte, // 0 - 2.5, 1 - 2.7, 2 - 3, 3 - 3.5
    override val locationType: Boolean,// метро/район
    override  val locationValue: String,
    override  val material: String,
    override  val year: String, // год постройки
    override  val layout: Boolean, // 1 - смежная, 0 - изолированная
    override val sellerType: Seller,
    override val renovation: RenovationType,
    override val roomCount: Byte,//(0,1,2,3,4,5,null), 0 - студия, null - свободная

    override val rent: Boolean,
    override val rentDateFrom: String,
    override val rentDateTo: String,
    override val guestCount: Byte,
    override val rules: Rules,
    override val paymentsRules: PaymentRules,

    val flatType: FlatType,
    val balcony: Boolean, // 0 - balkon, 1 - lodzhia
    val floorType: FloorType, //(не первый\не последний\только последний)
    val travelTime: Byte,
    val travelTimeType: Boolean, // 0 - пешком, 1 - транспорт
    val toilet: Boolean, // 0 - слитный, 1 - раздельный


):RentRealEstate,RealEstate//, Flat(category,priceMin,priceMax,city,includeWords,excludeWords,priceType,footageMin,footageMax,floorMin,floorMax,ceilingHeight,locationType,locationValue,material,year,layout,sellerType,roomCount,flatType,balcony,floorType,travelTime,travelTimeType,toilet)

@Serializable
data class House(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,

    override val priceType: Boolean, // true - за все, false - за м2
    override val footageMin: Int,
    override val footageMax: Int,
    override val floorMin: Byte,
    override val floorMax: Byte,
    override val ceilingHeight: Byte, // 0 - 2.5, 1 - 2.7, 2 - 3, 3 - 3.5
    override val locationType: Boolean,// метро/район
    override  val locationValue: String,
    override  val material: String,
    override  val year: String, // год постройки
    override  val layout: Boolean, // 1 - смежная, 0 - изолированная
    override val sellerType: Seller,
    override val renovation: RenovationType,
    override val roomCount: Byte,//(0,1,2,3,4,5,null), 0 - студия, null - свободная

    val houseType: String, // коттедж, дача и тд
    val footageArea: Int, // метраж участка
    val timeToCity: Byte,
    val toilet: Boolean, // 0 - улица, 1 - дом
) : RealEstate

@Serializable
data class HouseRent(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,

    override val priceType: Boolean, // true - за все, false - за м2
    override val footageMin: Int,
    override val footageMax: Int,
    override val floorMin: Byte,
    override val floorMax: Byte,
    override val ceilingHeight: Byte, // 0 - 2.5, 1 - 2.7, 2 - 3, 3 - 3.5
    override val locationType: Boolean,// метро/район
    override  val locationValue: String,
    override  val material: String,
    override  val year: String, // год постройки
    override  val layout: Boolean, // 1 - смежная, 0 - изолированная
    override val sellerType: Seller,
    override val renovation: RenovationType,
    override val roomCount: Byte,//(0,1,2,3,4,5,null), 0 - студия, null - свободная

    override val rent: Boolean,
    override val rentDateFrom: String,
    override val rentDateTo: String,
    override val guestCount: Byte,
    override val rules: Rules,
    override val paymentsRules: PaymentRules,

    val houseType: String, // коттедж, дача и тд
    val footageArea: Int, // метраж участка
    val timeToCity: Byte,
    val toilet: Boolean, // 0 - улица, 1 - дом


) : RealEstate, RentRealEstate

/*

- балкон\лоджия

- лифт (любой\еще и грузовой)



// недвижка
@Serializable
abstract class RealEstate(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,

   // val roomCount: Byte,//(0,1,2,3,4,5,null), 0 - студия, null - свободная

    //val floorType: String, //(не первый\не последний\только последний)

    //val travelTime: Byte,
    //val travelTimeType: Boolean, // 0 - пешком, 1 - транспорт
    //val toilet: Boolean, // 0 - слитный, 1 - раздельный
) : Parameters{
    abstract val priceType: Boolean// true - за все, false - за м2
    abstract val footageMin: Int
    abstract val footageMax: Int
    abstract val floorMin: Byte
    abstract val floorMax: Byte
    abstract val ceilingHeight: Byte // 0 - 2.5, 1 - 2.7, 2 - 3, 3 - 3.5
    abstract val locationType: String// метро/район
    abstract val locationValue: String
}

@Serializable
data class Flat(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,

    override val priceType: Boolean, // true - за все, false - за м2
    override val footageMin: Int,
    override val footageMax: Int,
    // val roomCount: Byte,//(0,1,2,3,4,5,null), 0 - студия, null - свободная
    override val floorMin: Byte,
    override val floorMax: Byte,
    //val floorType: String, //(не первый\не последний\только последний)
    override val ceilingHeight: Byte, // 0 - 2.5, 1 - 2.7, 2 - 3, 3 - 3.5
    override val locationType: String,// метро/район
    override  val locationValue: String,
    //val travelTime: Byte,
    //val travelTimeType: Boolean, // 0 - пешком, 1 - транспорт
    //val toilet: Boolean, // 0 - слитный, 1 - раздельный
) : RealEstate(category,priceMin,priceMax,city,includeWords,excludeWords)


 */

@Serializable
data class Transport(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,
    //@TODO параметры поиска для транспорта
) : Parameters

@Serializable
data class Electronics(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,
    //@TODO параметры поиска для электроники
) : Parameters

@Serializable
data class Service(
    override val category: String,
    override val priceMin: Float,
    override val priceMax: Float,
    override val city: String,
    override val includeWords: String,
    override val excludeWords: String,
    //@TODO параметры поиска для услуг
) : Parameters

