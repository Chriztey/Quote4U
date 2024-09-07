package com.chris.quote4u.module

import com.chris.quote4u.repository.QuoteRepo
import com.chris.quote4u.repository.QuoteRepoImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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

}