package com.chris.quote4u.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chris.quote4u.datasource.InitialData
import com.chris.quote4u.datasource.QuoteData
import com.chris.quote4u.datasource.QuoteFetchState
import com.chris.quote4u.repository.QuoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val quoteRepo: QuoteRepo
): ViewModel() {

    private val _quoteFetchState = MutableStateFlow<QuoteFetchState>(QuoteFetchState.Success)
    val quoteFetchState: StateFlow<QuoteFetchState> = _quoteFetchState.asStateFlow()

    private val _randomQuote = MutableStateFlow(InitialData())
    val randomQuote: StateFlow<InitialData> = _randomQuote.asStateFlow()

    init {
        getRandomQuote()
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

//            _randomQuote.update {
//                current -> current.copy(
//                    randomQuote = randomQuote
//                )
//            }
        }

    }

}