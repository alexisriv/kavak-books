package org.sixelasavir.product.book.model

import com.google.gson.annotations.SerializedName

data class BodyResult(
    @SerializedName("books") val books: List<Book>?,
    @SerializedName("best_sellers") val all: List<String>?
)
