package org.sixelasavir.product.book.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.bestSellersRecycler
import kotlinx.android.synthetic.main.activity_main.bestSellersTextView
import kotlinx.android.synthetic.main.activity_main.businessRecycler
import kotlinx.android.synthetic.main.activity_main.businessTextView
import kotlinx.android.synthetic.main.activity_main.historyRecycler
import kotlinx.android.synthetic.main.activity_main.historyTextView
import kotlinx.android.synthetic.main.activity_main.scienceRecycler
import kotlinx.android.synthetic.main.activity_main.scienceTextView
import org.koin.android.viewmodel.ext.android.viewModel
import org.sixelasavir.product.book.R
import org.sixelasavir.product.book.model.Book
import org.sixelasavir.product.book.viewModel.BookViewModel

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        bookViewModel.loadBooks()
    }

    private fun setupView() {
        bookViewModel.booksBestSellers.observe(this, Observer(::uiBestSeller))
        bookViewModel.booksHistory.observe(this, Observer(::uiHistory))
        bookViewModel.booksScience.observe(this, Observer(::uiScience))
        bookViewModel.booksBusiness.observe(this, Observer(::uiBusiness))
    }

    private fun uiBestSeller(list: List<Book>) {
        bestSellersTextView.text = getString(R.string.best_sellers, list.size.toString())
        setupRecycler(bestSellersRecycler, list)
    }

    private fun uiHistory(list: List<Book>) {
        historyTextView.text = getString(R.string.history, list.size.toString())
        setupRecycler(historyRecycler, list)
    }

    private fun uiScience(list: List<Book>) {
        scienceTextView.text = getString(R.string.science, list.size.toString())
        setupRecycler(scienceRecycler, list)
    }

    private fun uiBusiness(list: List<Book>) {
        businessTextView.text = getString(R.string.business, list.size.toString())
        setupRecycler(businessRecycler, list)
    }

    private fun setupRecycler(recyclerView: RecyclerView, list: List<Book>) {
        recyclerView.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        recyclerView.adapter = BookAdapter(list)
    }
}