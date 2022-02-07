package com.alvin.to_doappwithtesting.di

import com.alvin.to_doappwithtesting.data.local.QuotesDao
import com.alvin.to_doappwithtesting.repo.local.quoterepo.impl.QuotesRepositoryImpl
import com.alvin.to_doappwithtesting.repo.local.quoterepo.usecase.QuotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideQuoteRepo(quotesDao: QuotesDao): QuotesRepository {
        return QuotesRepositoryImpl(quotesDao)
    }
}