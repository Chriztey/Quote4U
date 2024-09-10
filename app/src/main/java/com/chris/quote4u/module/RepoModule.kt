package com.chris.quote4u.module

import android.content.Context
import androidx.room.Room
import com.chris.quote4u.repository.QuoteRepo
import com.chris.quote4u.repository.QuoteRepoImplementation
import com.chris.quote4u.room.QuoteDatabase
import com.chris.quote4u.room.repository.SavedQuoteRepo
import com.chris.quote4u.room.repository.SavedQuoteRepoImplementation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindQuoteRepo(
        quoteRepoImplementation: QuoteRepoImplementation
    ): QuoteRepo




    @Binds
    @Singleton
    abstract fun bindSavedQuoteRepo(
        savedQuoteRepoImplementation: SavedQuoteRepoImplementation
    ): SavedQuoteRepo

}