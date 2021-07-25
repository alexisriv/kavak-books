package org.sixelasavir.product.book.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import org.sixelasavir.product.book.R
import org.sixelasavir.product.book.R.id.*
import org.sixelasavir.product.book.model.Book
import org.sixelasavir.product.book.viewModel.BookViewModel

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setupView() {
        bookViewModel.booksBestSellers.observe(this, Observer(::uiBestSeller))
        bookViewModel.booksHistory.observe(this, Observer(::uiHistory))
        bookViewModel.booksScience.observe(this, Observer(::uiScience))
        bookViewModel.booksBusiness.observe(this, Observer(::uiBusiness))
    }

    private fun uiBestSeller(list: List<Book>) {
        findViewById<TextView>(bestSellersTextView).text =
            getString(R.string.best_sellers, list.size)
        findViewById<RecyclerView>(bestSellersRecycler).also { recyclerView ->
            setupRecycler(recyclerView, list)
        }
    }

    private fun uiHistory(list: List<Book>) {
        findViewById<TextView>(historyTextView).text =
            getString(R.string.history, list.size)
        findViewById<RecyclerView>(historyRecycler).also { recyclerView ->
            setupRecycler(recyclerView, list)
        }
    }

    private fun uiScience(list: List<Book>) {
        findViewById<TextView>(scienceTextView).text =
            getString(R.string.science, list.size)
        findViewById<RecyclerView>(scienceRecycler).also { recyclerView ->
            setupRecycler(recyclerView, list)
        }
    }

    private fun uiBusiness(list: List<Book>) {
        findViewById<TextView>(businessTextView).text =
            getString(R.string.business, list.size)
        findViewById<RecyclerView>(businessRecycler).also { recyclerView ->
            setupRecycler(recyclerView, list)
        }
    }

    private fun setupRecycler(recyclerView: RecyclerView, list: List<Book>) {
        recyclerView.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        recyclerView.adapter = BookAdapter(list)
    }
}