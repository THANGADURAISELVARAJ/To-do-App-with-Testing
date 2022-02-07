package com.alvin.to_doappwithtesting.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes
import com.alvin.to_doappwithtesting.databinding.LayoutQuoteItemBinding
import com.alvin.to_doappwithtesting.ui.interfaces.QuoteAdapterClickListeners

@SuppressLint("NotifyDataSetChanged")
class QuoteAdapter(var listener: QuoteAdapterClickListeners) :
    RecyclerView.Adapter<QuoteViewHolder>() {

    private var quotes: List<Quotes> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            LayoutQuoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.bind(quote, listener)
    }

    override fun getItemCount(): Int = quotes.size

    fun setQuotes(quotes: List<Quotes>) {
        this.quotes = quotes
        notifyDataSetChanged()
    }
}


class QuoteViewHolder(private val binding: LayoutQuoteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(quote: Quotes, listener: QuoteAdapterClickListeners) {
        binding.apply {
            quoteText.text = quote.text
            quoteAuthor.text = ("Auth : ${quote.author}")
            quoteLikes.text = ("Likes : ${quote.likeCount}")


            binding.root.setOnClickListener {
                listener.itemClicked()
            }

            binding.ivDelete.setOnClickListener {
                listener.deleteClicked(quote)
            }
        }
    }
}