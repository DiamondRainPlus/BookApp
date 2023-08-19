package com.yagmurceliksoy.bookapp.data.model

data class GetBooksResponse (
    val code: Int?,
    val message: String?,
    val books: List<Book>?
)