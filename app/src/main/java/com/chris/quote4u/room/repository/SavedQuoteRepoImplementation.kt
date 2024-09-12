package com.chris.quote4u.room.repository

import com.chris.quote4u.datasource.SavedQuoteData
import com.chris.quote4u.room.dao.QuoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedQuoteRepoImplementation @Inject constructor(
    private val quoteDao: QuoteDao
): SavedQuoteRepo {
    override suspend fun getSavedQuote(quote: String, author: String): SavedQuoteData {
        return quoteDao.getQuote(quote, author)
    }


    override fun getAllSavedQuote(): Flow<List<SavedQuoteData>> {
       return quoteDao.getAllSavedQuote()
    }

    override fun getQuoteByID(id: Int): Flow<SavedQuoteData >{
        return quoteDao.getQuoteByID(id)
    }

    override suspend fun insertQuote(savedQuoteData: SavedQuoteData) {
        quoteDao.insert(savedQuoteData)
    }

    override suspend fun deleteQuote(savedQuoteData: SavedQuoteData) {
        quoteDao.delete(savedQuoteData)
    }
}