package org.sixelasavir.product.book.repository

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.sixelasavir.product.book.api.BookApi
import org.sixelasavir.product.book.model.Book

class BookRepositoryImp(private val api: BookApi) : BookRepository {
    override fun getBooks(): Flow<List<Book>> = flow {
        api.getBooks().results.books?.let {
            emit(it)
        } ?: emit(arrayListOf<Book>())
    }.flowOn(IO)

    override fun getBestSellers(): Flow<List<String>> = flow {
        api.getBestSellers().results.all?.let {
            emit(it)
        } ?: emit(arrayListOf<String>())
    }.flowOn(IO)
}

