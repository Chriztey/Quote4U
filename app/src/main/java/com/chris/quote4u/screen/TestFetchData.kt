package com.chris.quote4u.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import coil.fetch.FetchResult
import com.chris.quote4u.datasource.QuoteFetchState
import com.chris.quote4u.viewmodel.QuotesViewModel

@Composable
fun TestFetchData() {

    val viewModel = hiltViewModel<QuotesViewModel>()
    val randomQuote by viewModel.randomQuote.collectAsState()
    val fetchingState by viewModel.quoteFetchState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { viewModel.getRandomQuote() }) {
            Text(text = "Get Quote")
        }

        if (randomQuote.randomQuote != null) {
            Text(
                color = Color.Red,
                style = MaterialTheme.typography.displayLarge,
                text = randomQuote.randomQuote?.quote?: "no data")
        }




    }

    if (fetchingState == QuoteFetchState.Loading) {
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

}