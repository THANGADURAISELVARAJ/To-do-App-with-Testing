package com.alvin.to_doappwithtesting.ui.addAndEdit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alvin.to_doappwithtesting.databinding.ActivityAddEditBinding
import com.alvin.to_doappwithtesting.ui.viewmodel.AddAndEditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding

    private val addAndEditViewModel by viewModels<AddAndEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit quote"
            binding.editTextQuoteText.setText(intent?.getStringExtra(EXTRA_TEXT))
            binding.editTextQuoteAuthor.setText(intent?.getStringExtra(EXTRA_AUTHOR))

        } else {
            title = "Add new quote"
        }
        binding.buttonSaveQuote.setOnClickListener {
            validate()
        }

        addAndEditViewModel.navigateToMainActivity.observe(this) {
            if (it) {
                val data = Intent().apply {
                    putExtra(EXTRA_TEXT, binding.editTextQuoteText.text.toString())
                    putExtra(EXTRA_AUTHOR, binding.editTextQuoteAuthor.text.toString())
                    if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                        putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
                    }
                }
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        }

    }

    private fun validate() {
        val value = addAndEditViewModel.validation(
            binding.editTextQuoteText.text.toString(),
            binding.editTextQuoteAuthor.text.toString()
        )
        if (value.isNotEmpty()) {
            Toast.makeText(
                this,
                value,
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    companion object {
        const val EXTRA_ID = "QUOTE_ID"
        const val EXTRA_TEXT = "QUOTE_TEXT"
        const val EXTRA_AUTHOR = "QUOTE_AUTHOR"
    }
}