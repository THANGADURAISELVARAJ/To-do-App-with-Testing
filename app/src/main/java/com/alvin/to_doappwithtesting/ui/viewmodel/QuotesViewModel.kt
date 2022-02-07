package com.alvin.to_doappwithtesting.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes
import com.alvin.to_doappwithtesting.repo.local.quoterepo.usecase.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val repository: QuotesRepository) :
    ViewModel() {

    fun insertQuote(quote: Quotes) = viewModelScope.launch {
        repository.insert(quote)
    }

    fun updateQuote(quote: Quotes) = viewModelScope.launch {
        repository.update(quote)
    }

    fun delete(quote: Quotes) = viewModelScope.launch {
        repository.delete(quote)
    }

    fun getAllQuotes() = repository.getAllQuotes()

    fun getQuoteLikes() = repository.getQuoteLikedCount()

}