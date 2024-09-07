package com.chris.quote4u.datasource

import kotlinx.serialization.Serializable

@Serializable
data class QuoteData(
    val id: String,
    // The quotation text
    val content: String,
    // The full name of the author
    val author: String
)
