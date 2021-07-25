package org.sixelasavir.product.book.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.sixelasavir.product.book.R
import org.sixelasavir.product.book.model.Book

class BookAdapter(val books: List<Book>) : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.bookImageView)
        val title: TextView = view.findViewById(R.id.titleTextView)
        val author: TextView = view.findViewById(R.id.authorTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
            .let { view -> MyViewHolder(view) }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        books[position].let { book ->
            holder.apply {
                title.text = book.title
                author.text = book.author
                Glide.with(holder.photo)
                    .load(book.img)
                    .optionalCenterInside()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(holder.photo)
            }
        }
    }

    override fun getItemCount(): Int = books.size
}
