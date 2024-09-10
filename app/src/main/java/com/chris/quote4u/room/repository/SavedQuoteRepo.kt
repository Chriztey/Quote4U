package com.chris.quote4u.room.repository

import com.chris.quote4u.datasource.SavedQuoteData
import kotlinx.coroutines.flow.Flow

interface SavedQuoteRepo {

    fun getAllSavedQuote(): Flow<List<SavedQuoteData>>

    suspend fun insertQuote (savedQuoteData: SavedQuoteData)

    suspend fun deleteQuote (savedQuoteData: SavedQuoteData)

}