package org.sixelasavir.product.book.repository

import kotlinx.coroutines.flow.Flow
import org.sixelasavir.product.book.model.Book

interface BookRepository {
    fun getBooks(): Flow<List<Book>>
    fun getBestSellers(): Flow<List<String>>
}