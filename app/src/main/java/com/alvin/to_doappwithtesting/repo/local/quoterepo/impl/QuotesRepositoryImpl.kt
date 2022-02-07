package com.alvin.to_doappwithtesting.repo.local.quoterepo.impl

import com.alvin.to_doappwithtesting.data.local.QuotesDao
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes
import com.alvin.to_doappwithtesting.repo.local.quoterepo.usecase.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesRepositoryImpl @Inject constructor(private val quotesDao: QuotesDao) :
    QuotesRepository {

    override suspend fun insert(quote: Quotes) {
        withContext(Dispatchers.IO) {
            quotesDao.insertQuote(quote)
        }
    }

    override suspend fun update(quote: Quotes) {
        withContext(Dispatchers.IO) {
            quotesDao.updateQuote(quote)
        }
    }

    override suspend fun delete(quote: Quotes) {
        withContext(Dispatchers.IO) {
            quotesDao.deleteQuote(quote)
        }
    }

    override fun getAllQuotes() = quotesDao.getAllQuotes()

    override fun getQuoteLikedCount() = quotesDao.getQuotesLikeCount()

}