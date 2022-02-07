package com.alvin.to_doappwithtesting.di

import android.content.Context
import androidx.room.Room
import com.alvin.to_doappwithtesting.data.local.QuoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object HiltTestModules {

    @Provides
    @Named("test_db")
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context,
            QuoteDataBase::class.java
        ).allowMainThreadQueries().build()


    @Provides
    @Named("quotes_dao")
    fun provideQuoteDao(@Named("test_db") quoteDataBase: QuoteDataBase) =
        quoteDataBase.quotesDao()

}