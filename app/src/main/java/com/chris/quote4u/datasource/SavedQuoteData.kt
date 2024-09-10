package com.chris.quote4u.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedQuotes")
data class SavedQuoteData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quote: String,
    val author: String,
)