package com.alvin.to_doappwithtesting.ui.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.alvin.to_doappwithtesting.repo.local.quoterepo.FakeQuoteRepoTest
import com.alvin.to_doappwithtesting.utils.FakeQuotesList
import com.alvin.to_doappwithtesting.utils.observeAndroidLiveDataValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class QuotesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var quotesViewModel: QuotesViewModel

    @Before
    fun setUp() {
        quotesViewModel = QuotesViewModel(FakeQuoteRepoTest())
    }

    @Test
    fun insertQuoteIntoFakeRepo_returnTrue() {
        quotesViewModel.insertQuote(FakeQuotesList.fakeQuoteItem())
        val quoteList = quotesViewModel.getAllQuotes().observeAndroidLiveDataValue()
        assertThat(quoteList).contains(FakeQuotesList.fakeQuoteItem())
    }

    @Test
    fun deleteQuoteIntoFakeRepo_returnTrue() {
        quotesViewModel.insertQuote(FakeQuotesList.fakeQuoteItem())
        quotesViewModel.delete(FakeQuotesList.fakeQuoteItem())
        val quoteList = quotesViewModel.getAllQuotes().observeAndroidLiveDataValue()
        assertThat(quoteList).doesNotContain(FakeQuotesList.fakeQuoteItem())
    }
}