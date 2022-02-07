package com.alvin.to_doappwithtesting.repo.local.quoterepo.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alvin.to_doappwithtesting.data.local.QuoteDataBase
import com.alvin.to_doappwithtesting.data.local.QuotesDao
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes
import com.alvin.to_doappwithtesting.utils.observeAndroidLiveDataValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class QuotesRepositoryImplTest {


    @Inject
    @Named("test_db")
    lateinit var quotesDb: QuoteDataBase

    @Inject
    @Named("quotes_dao")
    lateinit var quotesDao: QuotesDao

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    /*@get:Rule
    var chain = RuleChain
        .outerRule(InstantTaskExecutorRule())
        .around(HiltAndroidRule(this))*/

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun insertQuoteDataToDb_ReturnTrue() = runBlocking {
        val quote = Quotes(1, "alvin", "alvin1", false, 1)
        quotesDao.insertQuote(quote)
        val quoteList = quotesDao.getAllQuotes().observeAndroidLiveDataValue()
        assertThat(quoteList).isNotEmpty()
    }


    @Test
    fun insertQuoteDataToDb_ReturnFalse() = runBlocking {
        val quote = Quotes(1, "alvin", "alvin1", false, 1)
        quotesDao.insertQuote(quote)
        val quoteList = quotesDao.getAllQuotes().observeAndroidLiveDataValue()
        assertThat(quoteList).contains(quote)
    }

    @Test
    fun deleteQuoteDataToDb_ReturnTrue() = runBlocking {
        val quote = Quotes(1, "alvin", "alvin1", false, 1)
        quotesDao.insertQuote(quote)
        quotesDao.deleteQuote(quote)
        val quoteList = quotesDao.getAllQuotes().observeAndroidLiveDataValue()
        assertThat(quoteList).isEmpty()
    }

    @Test
    fun updateQuoteDataToDb_ReturnTrue() = runBlocking {
        val insertQuoteItem = Quotes(1, "alvin", "alvin1", false, 1)
        val updateQuoteItem = Quotes(1, "alvin", "alvin2", false, 1)

        quotesDao.insertQuote(insertQuoteItem)
        quotesDao.updateQuote(updateQuoteItem)
        val quoteList = quotesDao.getAllQuotes().observeAndroidLiveDataValue()
        assertThat(quoteList[0].author).isEqualTo("alvin2")
    }


    @After
    fun close() {
        quotesDb.close()
    }

}