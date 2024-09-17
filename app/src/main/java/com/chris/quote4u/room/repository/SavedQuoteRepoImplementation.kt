package com.chris.quote4u.room.repository

import androidx.glance.appwidget.action.actionRunCallback
import com.chris.quote4u.datasource.SavedQuoteData
import com.chris.quote4u.glancewidget.QuoteWidgetRepoImple
import com.chris.quote4u.glancewidget.resetIndexCallBack
import com.chris.quote4u.room.dao.QuoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedQuoteRepoImplementation @Inject constructor(
    private val quoteDao: QuoteDao,
    private val quoteWidgetRepoImple: QuoteWidgetRepoImple
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
        quoteWidgetRepoImple.quoteAdded()
        actionRunCallback(resetIndexCallBack::class.java)
    }

    override suspend fun deleteQuote(savedQuoteData: SavedQuoteData) {
        quoteDao.delete(savedQuoteData)
        quoteWidgetRepoImple.quoteDeleted()
        actionRunCallback(resetIndexCallBack::class.java)
    }
}