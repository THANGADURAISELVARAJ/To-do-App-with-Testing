package com.alvin.to_doappwithtesting.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes
import com.alvin.to_doappwithtesting.databinding.ActivityMainBinding
import com.alvin.to_doappwithtesting.ui.adapters.QuoteAdapter
import com.alvin.to_doappwithtesting.ui.addAndEdit.AddEditActivity
import com.alvin.to_doappwithtesting.ui.interfaces.QuoteAdapterClickListeners
import com.alvin.to_doappwithtesting.ui.viewmodel.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), QuoteAdapterClickListeners {
    private lateinit var binding: ActivityMainBinding

    private lateinit var quoteAdapter: QuoteAdapter

    private val quotesViewModel by viewModels<QuotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteAdapter = QuoteAdapter(this)

        binding.quotesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = quoteAdapter
        }

        binding.addQuoteFloatingButton.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivityForResult(intent, ADD_QUOTE_REQUEST_CODE)
        }


        quotesViewModel.getAllQuotes().observe(this) {
            quoteAdapter.setQuotes(it)
        }

        quotesViewModel.getQuoteLikes().observe(this) {
            it?.let {
                binding.tvTotalLikes.text = ("Total Likes : $it")
            } ?: run {
                binding.tvTotalLikes.text = ("Total Likes : 0")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ADD_QUOTE_REQUEST_CODE -> {
                data?.let {
                    val newNote = Quotes(
                        text = it.getStringExtra(AddEditActivity.EXTRA_TEXT)!!,
                        author = it.getStringExtra(AddEditActivity.EXTRA_AUTHOR)!!,
                        likeCount = 1
                    )
                    quotesViewModel.insertQuote(newNote)
                    Toast.makeText(this, "Quote saved!", Toast.LENGTH_SHORT).show()
                }

            }
            EDIT_QUOTE_REQUEST_CODE -> {
                data?.let {
                    val updateQuote = Quotes(
                        text = it.getStringExtra(AddEditActivity.EXTRA_TEXT)!!,
                        author = it.getStringExtra(AddEditActivity.EXTRA_AUTHOR)!!
                    )
                    quotesViewModel.updateQuote(updateQuote)
                    Toast.makeText(this, "Quote updated!", Toast.LENGTH_SHORT).show()
                }

            }
            else -> {
                Toast.makeText(this, "Not found!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val ADD_QUOTE_REQUEST_CODE = 1
        const val EDIT_QUOTE_REQUEST_CODE = 2
    }

    override fun deleteClicked(quote: Quotes) {
        quotesViewModel.delete(quote)
        Toast.makeText(this, "Quote deleted!", Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked() {
        //  quotesViewModel
    }
}
