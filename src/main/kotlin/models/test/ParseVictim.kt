package models.test

abstract class ParseVictim: BaseParseVictim {
    /* Все модули конкретных сайтов должны уметь:
        - иметь у себя адрес сайта
        - иметь у себя параметры для запроса
        - иметь у себя список доступных категорий объявлений
    */
    abstract var URL: String
    abstract var queryParam: String
    abstract var categories: List<String>
    abstract fun setCategories()
}