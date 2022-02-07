package com.alvin.to_doappwithtesting.repo.local.quoterepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes
import com.alvin.to_doappwithtesting.repo.local.quoterepo.usecase.QuotesRepository

class FakeQuoteRepoTest : QuotesRepository {

    private val allQuoteListLiveData = MutableLiveData<List<Quotes>>()
    private val totalQuoteLikeCountLiveData = MutableLiveData<Int>()

    private val allQuoteList = mutableListOf<Quotes>()

    private fun updateLiveData() {
        allQuoteListLiveData.postValue(allQuoteList)
        totalQuoteLikeCountLiveData.postValue(getLikesCount())
    }

    override suspend fun insert(quote: Quotes) {
        allQuoteList.add(quote)
        updateLiveData()
    }

    override suspend fun update(quote: Quotes) {
        allQuoteList.add(quote)
        updateLiveData()
    }

    override suspend fun delete(quote: Quotes) {
        allQuoteList.remove(quote)
        updateLiveData()
    }

    override fun getAllQuotes(): LiveData<List<Quotes>> {
        return allQuoteListLiveData
    }

    override fun getQuoteLikedCount(): LiveData<Int> {
        totalQuoteLikeCountLiveData.postValue(getLikesCount())
        return totalQuoteLikeCountLiveData
    }

    private fun getLikesCount(): Int {
        var count = 0
        allQuoteList.forEach {
            count = it.likeCount
        }
        return count
    }
}