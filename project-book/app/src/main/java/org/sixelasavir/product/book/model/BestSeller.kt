package org.sixelasavir.product.book.model

import com.google.gson.annotations.SerializedName

data class BestSeller(
    @SerializedName("best_sellers") val all: List<String>
)
