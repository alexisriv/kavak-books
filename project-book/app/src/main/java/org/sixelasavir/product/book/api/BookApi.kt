package org.sixelasavir.product.book.api

import org.sixelasavir.product.book.model.BestSeller
import org.sixelasavir.product.book.model.Book
import org.sixelasavir.product.book.model.CustomResponse
import retrofit2.http.GET

interface BookApi {

    @GET("books.json")
    suspend fun getBooks(): CustomResponse<List<Book>>

    @GET("best_sellers.json")
    suspend fun getBestSellers(): CustomResponse<BestSeller>
}
