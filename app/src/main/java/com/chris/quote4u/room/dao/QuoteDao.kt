package com.chris.quote4u.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.chris.quote4u.datasource.SavedQuoteData
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (savedQuoteData: SavedQuoteData)

    @Delete
    suspend fun delete (savedQuoteData: SavedQuoteData)

    @Query("SELECT * from savedQuotes WHERE quote = :quote AND author = :author")
    suspend fun getQuote(quote: String, author: String): SavedQuoteData

    @Query("SELECT * from savedQuotes")
    fun getAllSavedQuote(): Flow<List<SavedQuoteData>>

}