package com.chris.quote4u.repository

import com.chris.quote4u.api.QuoteApi
import com.chris.quote4u.datasource.QuoteData
import javax.inject.Inject

class QuoteRepoImplementation @Inject constructor(
    private val quoteApi: QuoteApi
): QuoteRepo {
    override suspend fun getRandomQuote(): List<QuoteData> {
        return quoteApi.getRandomQuote()
    }
}