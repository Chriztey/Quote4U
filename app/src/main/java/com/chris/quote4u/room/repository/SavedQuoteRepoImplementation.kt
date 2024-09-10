package com.chris.quote4u.room.repository

import com.chris.quote4u.datasource.SavedQuoteData
import com.chris.quote4u.room.dao.QuoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedQuoteRepoImplementation @Inject constructor(
    private val quoteDao: QuoteDao
): SavedQuoteRepo {
    override fun getAllSavedQuote(): Flow<List<SavedQuoteData>> {
       return quoteDao.getAllSavedQuote()
    }

    override suspend fun insertQuote(savedQuoteData: SavedQuoteData) {
        quoteDao.insert(savedQuoteData)
    }

    override suspend fun deleteQuote(savedQuoteData: SavedQuoteData) {
        quoteDao.delete(savedQuoteData)
    }
}