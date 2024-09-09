package com.chris.quote4u.repository

import com.chris.quote4u.datasource.QuoteData
import com.chris.quote4u.datasource.QuoteFetchState

interface QuoteRepo {
    suspend fun getRandomQuote(
        callback: (QuoteFetchState) -> Unit,
        result: (List<QuoteData>) -> Unit
    )
}