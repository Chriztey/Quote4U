package com.chris.quote4u.glancewidget

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.chris.quote4u.datasource.SavedQuoteData
import com.chris.quote4u.room.dao.QuoteDao
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints

import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class QuoteWidgetRepoImple @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val quoteDao: QuoteDao
) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface QuoteWidgetRepositoryEntryPoint {
        fun widgetRepository(): QuoteWidgetRepoImple
    }

    companion object {
            fun get(applicationContext: Context): QuoteWidgetRepoImple{
                val widgetModelRepositoryEntryPoint: QuoteWidgetRepositoryEntryPoint =
                    EntryPoints.get(
                        applicationContext,
                        QuoteWidgetRepositoryEntryPoint::class.java
                    )
                return widgetModelRepositoryEntryPoint.widgetRepository()
            }
        }


    fun loadSavedQuotes(): Flow<List<SavedQuoteData>> {
        return quoteDao.getAllSavedQuote().distinctUntilChanged()
    }

    suspend fun quoteAdded() {
        QuoteWidget.updateAll(context = appContext)
    }

    suspend fun quoteDeleted() {
        QuoteWidget.updateAll(context = appContext)
    }
}

