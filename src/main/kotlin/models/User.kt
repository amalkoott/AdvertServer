package models


class User(
    userAgent: String? = null,
    cookies: String? = null,
    proxyLogin: String? = null,
    proxyPassword: String? = null)
{
    /*

    Accept	Список MIME типов, которые ожидает клиент.
    Accept-Encoding	Список форматов сжатия данных, которые поддерживает клиент.
    Accept-Language	Определяет языковые предпочтения клиента.

    Referer
Содержит URL-адрес ресурса, из которого был запрошен обрабатываемый запрос. Если запрос поступил из закладки, прямого ввода адреса пользователем или с помощью других методов, при которых исходного ресурса нет, то этот заголовок отсутствует или имеет значение "about:blank".

Это ошибочное имя заголовка (referer, вместо referrer) было введено в спецификацию HTTP/0.9, и ошибка должна была быть сохранена в более поздних версиях протокола для совместимости.



     */
    val userAgent = ""
    val proxyLogin = ""
    val proxyPassword = ""
    val cookies = ""
}