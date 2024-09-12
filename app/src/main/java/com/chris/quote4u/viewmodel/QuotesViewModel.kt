package com.chris.quote4u.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chris.quote4u.datasource.InitialData
import com.chris.quote4u.datasource.QuoteData
import com.chris.quote4u.datasource.QuoteFetchState
import com.chris.quote4u.datasource.SavedQuoteData
import com.chris.quote4u.repository.QuoteRepo
import com.chris.quote4u.room.repository.SavedQuoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val quoteRepo: QuoteRepo,
    private val saveQuoteRepo: SavedQuoteRepo
): ViewModel() {

    private val _quoteFetchState = MutableStateFlow<QuoteFetchState>(QuoteFetchState.Success)
    val quoteFetchState: StateFlow<QuoteFetchState> = _quoteFetchState.asStateFlow()

    private val _randomQuote = MutableStateFlow(InitialData())
    val randomQuote: StateFlow<InitialData> = _randomQuote.asStateFlow()



    init {
        getRandomQuote()
        checkQuote()
    }


    fun getRandomQuote() {

        viewModelScope.launch {
            quoteRepo.getRandomQuote(
                callback = {
                    _quoteFetchState.value = it
                },
                result = {
                    _randomQuote.update {
                        current -> current.copy(
                            randomQuote = it[0]
                        )
                    }
                }
            )

            checkQuote()

        }

    }

    suspend fun saveQuote (quote: SavedQuoteData) {
        saveQuoteRepo.insertQuote(quote)
    }

    suspend fun deleteQuote(quote: String, author: String) {
        saveQuoteRepo.deleteQuote(
            saveQuoteRepo.getSavedQuote(quote,author)
        )
    }

    fun checkQuote(){
         viewModelScope.launch {

             val checking = randomQuote.value.randomQuote?.let {
                 saveQuoteRepo.getSavedQuote(
                     quote = it.quote,
                     author = it.author
                 )
             } != null
             _randomQuote.update {
                     current -> current.copy(
                 isQuoteFav = checking
                     )
             }
        }
    }

    fun savedUnsavedQuote() {
        _randomQuote.update {
            current -> current.copy(
                isQuoteFav = !current.isQuoteFav
            )
        }
    }

}

fun QuoteData.toSavedQuoteData(): SavedQuoteData {
    return SavedQuoteData(
        quote = quote,
        author = author
    )
}