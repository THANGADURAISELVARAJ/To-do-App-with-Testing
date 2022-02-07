package com.alvin.to_doappwithtesting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes

@Database(entities = [Quotes::class], version = 1, exportSchema = false)
abstract class QuoteDataBase : RoomDatabase() {

    abstract fun quotesDao(): QuotesDao

}