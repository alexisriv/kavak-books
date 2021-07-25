package org.sixelasavir.product.book.model

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val genre: String,
    val img: String
)
