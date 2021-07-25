package org.sixelasavir.product.book

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.sixelasavir.product.book.api.BookApi
import org.sixelasavir.product.book.api.ClientBuilder
import org.sixelasavir.product.book.repository.BookRepository
import org.sixelasavir.product.book.repository.BookRepositoryImp
import org.sixelasavir.product.book.viewModel.BookViewModel

class MainApplication : Application() {
    private val api = ClientBuilder.service(BookApi::class)
    private val appModule = module {
        single<BookRepository> { BookRepositoryImp(api) }
        viewModel { BookViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            koin.loadModules(listOf(appModule), true)
        }
    }
}
