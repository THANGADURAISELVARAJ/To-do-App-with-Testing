package com.alvin.to_doappwithtesting.di

import android.content.Context
import androidx.room.Room
import com.alvin.to_doappwithtesting.constants.RoomConstants.DATABASE_NAME
import com.alvin.to_doappwithtesting.data.local.QuoteDataBase
import com.alvin.to_doappwithtesting.data.local.QuotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QuoteDataBase {
        return Room.databaseBuilder(context, QuoteDataBase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideShoppingDao(quoteDataBase: QuoteDataBase): QuotesDao {
        return quoteDataBase.quotesDao()
    }
}