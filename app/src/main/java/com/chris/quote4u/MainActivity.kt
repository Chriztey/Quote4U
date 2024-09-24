package com.chris.quote4u

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.chris.quote4u.datasource.QuoteFetchState
import com.chris.quote4u.navigation.AppNavigationHost
import com.chris.quote4u.screen.GetStartedScreen
import com.chris.quote4u.screen.HomeScreen
import com.chris.quote4u.screen.SavedQuoteListScreen
import com.chris.quote4u.screen.SplashScreen
import com.chris.quote4u.screen.TestFetchData
import com.chris.quote4u.screen.ViewSavedQuoteScreen
import com.chris.quote4u.viewmodel.OnBoardViewModel
import com.example.compose.Quote4UTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<OnBoardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isReady.value != QuoteFetchState.Success
            }
        }
        setContent {
            Quote4UTheme {
                //HomeScreen()
                //SavedQuoteListScreen()
                //ViewSavedQuoteScreen()
                AppNavigationHost()
                //SplashScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Quote4UTheme {
        Greeting("Android")
    }
}