package com.chris.quote4u.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chris.quote4u.datasource.QuoteData
import com.chris.quote4u.datasource.SavedQuoteData
import com.chris.quote4u.room.dao.QuoteDao

@Database(entities = [SavedQuoteData::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

}