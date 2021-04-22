package api

import model.Category
import model.User
import model.Word
import kotlin.js.json

private const val HOST = "http://localhost:8083"

suspend fun authorization(username: String, password: String) : User{
    val json = json(
        "username" to username,
        "password" to password
    )
    val body = JSON.stringify(json)
    return postAndParseResult("$HOST/authorization", body, ::parseLoginOrRegisterResponse)
}

suspend fun notifyCorrectAnswer(wordId: Int) {
    val json = json(
        "wordId" to wordId
    )
    val body = JSON.stringify(json)
    postAndParseResult("$HOST/success", body, ::parseEmptyResponse)
}

suspend fun notifyIncorrectAnswer(wordId: Int) {
    val json = json(
        "wordId" to wordId
    )
    val body = JSON.stringify(json)
    postAndParseResult("$HOST/failure", body, ::parseEmptyResponse)
}

suspend fun fetchWordToLearn() : List<Word> {
    return mutableListOf(
        Word(1, "a", "a", "a"),
        Word(2, "b", "b", "b")
    )
}

suspend fun fetchCategories(): List<Category> {
//    return mutableListOf(
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0"),
//        Category("Films", "https://www.flaticon.com/svg/vstatic/svg/1077/1077340.svg?token=exp=1618745828~hmac=ec807739ee239d5df1a7d750e5c43fa0")
//    )

    return postAndParseResult(  "$HOST/categories", "", ::parseCategoriesResponse)
}

private fun parseWordResponse(json: dynamic) : List<Word> {
    val words = mutableListOf<Word>()
    for (jsonWord in json.words) {
        val word = Word(jsonWord.id, jsonWord.word, jsonWord.transcription, jsonWord.translation)
        words.add(word)
    }

    return words
}

private fun parseLoginOrRegisterResponse(json: dynamic): User {
    if (json.error != null) {
        throw (json.error)
    }

    return User(json.user.username, json.user.password)
}

private fun parseCategoriesResponse(json: dynamic): List<Category> {
    if (json.error != null) {
        throw (json.error)
    }

    return mutableListOf()
}

private fun parseEmptyResponse(json: dynamic): Unit {

}