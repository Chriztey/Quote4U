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
    ){
        Log.d("Fetch", "Loading")

        try {
            val q = quoteApi.getRandomQuote()
            Log.d("Fetch", q[0].q)
        } catch (
            e: Exception
        ) {
            Log.d("Fetch", e.message.toString())
        }




//        var  data = QuoteData("asas","asas", "")
//        callback(QuoteFetchState.Loading)
//
//        try {
//            data = quoteApi.getRandomQuote()
//            callback(QuoteFetchState.Success)
//
//        } catch (e: Exception) {
//            callback(QuoteFetchState.Error(e.message ?: "Unknown error"))
//        }
//
//        return data

    }
}