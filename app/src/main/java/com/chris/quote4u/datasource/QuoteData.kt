package com.chris.quote4u.datasource

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface QuoteFetchState {
    object Success : QuoteFetchState
    object Loading : QuoteFetchState
    data class Error(val message: String) : QuoteFetchState
}



@Serializable
data class QuoteData(
    @SerialName("q")
    val quote: String,
    @SerialName("a")
    val author: String
//    @SerialName("_id")
//    val id: String,
//    // The quotation text
//    val content: String,
//    // The full name of the author
//    val author: String
)

data class InitialData (
    val randomQuote: QuoteData? = null,
    val isQuoteFav: Boolean = false
)
