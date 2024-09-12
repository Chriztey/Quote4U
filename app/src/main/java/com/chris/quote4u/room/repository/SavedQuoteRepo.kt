package com.chris.quote4u.room.repository

import com.chris.quote4u.datasource.SavedQuoteData
import kotlinx.coroutines.flow.Flow

interface SavedQuoteRepo {

    suspend fun getSavedQuote(quote: String, author: String): SavedQuoteData

    fun getAllSavedQuote(): Flow<List<SavedQuoteData>>

    fun getQuoteByID(id: Int): Flow<SavedQuoteData>

    suspend fun insertQuote (savedQuoteData: SavedQuoteData)

    suspend fun deleteQuote (savedQuoteData: SavedQuoteData)

}