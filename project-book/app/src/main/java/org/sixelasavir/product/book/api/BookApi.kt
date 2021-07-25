package org.sixelasavir.product.book.api

import org.sixelasavir.product.book.model.BodyResult
import org.sixelasavir.product.book.model.CustomResponse
import retrofit2.http.GET

interface BookApi {

    @GET("books.json")
    suspend fun getBooks(): CustomResponse<BodyResult>

    @GET("best_sellers.json")
    suspend fun getBestSellers(): CustomResponse<BodyResult>
}
