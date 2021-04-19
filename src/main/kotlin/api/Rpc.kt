package api

import kotlinx.coroutines.await
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import model.User
import org.w3c.dom.url.URLSearchParams
import kotlin.js.json

suspend fun <T> postAndParseResult(url: String, body: dynamic, parse: (dynamic) -> T): T =
    requestAndParseResult("POST", url, body, parse)

suspend fun <T> getAndParseResult(url: String, body: dynamic, parse: (dynamic) -> T): T =
    requestAndParseResult("GET", url, body, parse)

suspend fun <T> requestAndParseResult(method: String, url: String, body: dynamic, parse: (dynamic) -> T): T {
    console.log(method)
    console.log(body)
    val response = window.fetch(url, object: RequestInit {
        override var method: String? = method
        override var body: dynamic = body
//        override var credentials: RequestCredentials? = "same-origin".asDynamic()
        override var headers: dynamic = json(
            "Accept" to "application/json"
        )
    }).await()
    console.log(response)
    return parse(response.json().await())
}

class LoginOrRegisterFailedException(message: String) : Throwable(message)
