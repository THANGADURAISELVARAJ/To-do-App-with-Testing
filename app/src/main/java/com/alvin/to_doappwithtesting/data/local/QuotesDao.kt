package com.alvin.to_doappwithtesting.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alvin.to_doappwithtesting.data.models.quotes.Quotes

@Dao
interface QuotesDao {

    @Query("SELECT * FROM Quotes ORDER BY id DESC")
    fun getAllQuotes(): LiveData<List<Quotes>>

    @Query("SELECT sum(likeCount) FROM Quotes")
    fun getQuotesLikeCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quotes)

    @Update
    suspend fun updateQuote(quote: Quotes)

    @Delete
    suspend fun deleteQuote(quote: Quotes)
}