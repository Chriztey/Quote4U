package com.chris.quote4u.repository

import com.chris.quote4u.datasource.QuoteData

interface QuoteRepo {
    suspend fun getRandomQuote(): List<QuoteData>
}