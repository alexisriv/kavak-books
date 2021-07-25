package org.sixelasavir.product.book.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

object ClientBuilder {

    private const val BASE_URL = "https://raw.githubusercontent.com/ejgteja/files/main/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T : Any> service(tClass: KClass<T>): T = retrofit.create(tClass.java)
}
