package org.sixelasavir.product.book.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sixelasavir.product.book.model.Book
import org.sixelasavir.product.book.repository.BookRepository

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _booksBestSellers: MutableLiveData<List<Book>> = MutableLiveData()
    val booksBestSellers: LiveData<List<Book>>
        get() = _booksBestSellers

    private val _booksHistory: MutableLiveData<List<Book>> = MutableLiveData()
    val booksHistory: LiveData<List<Book>>
        get() = _booksHistory

    private val _booksScience: MutableLiveData<List<Book>> = MutableLiveData()
    val booksScience: LiveData<List<Book>>
        get() = _booksScience

    private val _booksBusiness: MutableLiveData<List<Book>> = MutableLiveData()
    val booksBusiness: LiveData<List<Book>>
        get() = _booksBusiness

    fun loadBooks(): Job = viewModelScope.launch {
        repository.getBestSellers().collect { bestSellers ->
            repository.getBooks().collect { books ->

                val booksBestSellers = arrayListOf<Book>()
                val booksHistory = arrayListOf<Book>()
                val booksScience = arrayListOf<Book>()
                val booksBusiness = arrayListOf<Book>()

                for (itemBook: Book in books) {
                    for (bestSeller: String in bestSellers) {
                        if (bestSeller.equals(itemBook.isbn, true)) {
                            booksBestSellers.add(itemBook)
                        }
                    }
                    when {
                        itemBook.genre.equals(HISTORY, true) -> booksHistory.add(itemBook)
                        itemBook.genre.equals(SCIENCE, true) -> booksScience.add(itemBook)
                        itemBook.genre.equals(BUSINESS, true) -> booksBusiness.add(itemBook)
                    }
                }

                _booksBestSellers.postValue(booksBestSellers)
                _booksHistory.postValue(booksHistory)
                _booksScience.postValue(booksScience)
                _booksBusiness.postValue(booksBusiness)
            }
        }
    }

    private companion object {
        const val HISTORY = "history"
        const val SCIENCE = "science"
        const val BUSINESS = "business"
    }
}
