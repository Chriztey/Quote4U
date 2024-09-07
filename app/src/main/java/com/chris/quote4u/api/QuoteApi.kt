package com.chris.quote4u.api

import com.chris.quote4u.datasource.QuoteData
import retrofit2.http.GET

interface QuoteApi {
    @GET("random")
    suspend fun getRandomQuote() : List<QuoteData>
}