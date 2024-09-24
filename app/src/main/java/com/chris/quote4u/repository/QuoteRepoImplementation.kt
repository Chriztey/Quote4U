package com.chris.quote4u.repository

import android.util.Log
import com.chris.quote4u.api.QuoteApi
import com.chris.quote4u.datasource.QuoteData
import com.chris.quote4u.datasource.QuoteFetchState
import javax.inject.Inject

class QuoteRepoImplementation @Inject constructor(
    private val quoteApi: QuoteApi
): QuoteRepo {
    override suspend fun getRandomQuote(
        callback: (QuoteFetchState) -> Unit,
        result: (List<QuoteData>) -> Unit
    ){
        Log.d("Fetch", "Loading")

        callback(QuoteFetchState.Loading)

        try {
            result(quoteApi.getRandomQuote())
            callback(QuoteFetchState.Success)
        } catch (
            e: Exception
        ) {
            Log.d("Fetch", e.message.toString())
            callback(QuoteFetchState.Error(e.message.toString()))
        }

    }
}