package com.alvin.to_doappwithtesting.repo.local.quoterepo.usecase

import androidx.lifecycle.LiveData
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes

interface QuotesRepository {

    suspend fun insert(quote: Quotes)

    suspend fun update(quote: Quotes)

    suspend fun delete(quote: Quotes)

    fun getAllQuotes(): LiveData<List<Quotes>>

    fun getQuoteLikedCount(): LiveData<Int>
}