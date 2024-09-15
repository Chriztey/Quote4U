package com.chris.quote4u.glancewidget

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.glance.Button

import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.chris.quote4u.MainActivity
import com.chris.quote4u.R
import com.chris.quote4u.room.repository.SavedQuoteRepo
import com.chris.quote4u.room.repository.SavedQuoteRepoImplementation
import com.chris.quote4u.viewmodel.QuotesViewModel
import com.chris.quote4u.viewmodel.toSavedQuoteData
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.lifecycle.Lifecycle
import com.chris.quote4u.datasource.SavedQuoteData
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class QuoteWidget: GlanceAppWidget() {


    

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val repo = QuoteWidgetRepoImple.get(context)


        provideContent {
            Scaffold(
                backgroundColor = GlanceTheme.colors.widgetBackground,
                titleBar = {
                    Text(text = "Quote4U")
                }
            ) {
                val quotes = repo.loadSavedQuotes().collectAsState(initial = emptyList())
                val randomIndex = (0..quotes.value.size).random()
                Log.d("randomIndex", randomIndex.toString())


                quotes.value.forEach {

                    if (quotes.value.indexOf(it) == randomIndex) {

                        WidgetContent(quoteDisplay = it.quote)

                        //Text(text = it.quote)
                    }


                }


                //Text(text = quotes.value[0].quote)

                //WidgetContent(quoteDisplay = )

            }
        }
    }
}

@Composable
fun WidgetContent(
    quoteDisplay: String
) {


    Column(
        modifier = GlanceModifier.fillMaxSize(),
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = GlanceModifier.fillMaxWidth(),
            text = quoteDisplay,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = GlanceTheme.colors.onSurface
            )
        )

        //Button(text = "Shuffle", onClick = { actionStartActivity<MainActivity>() })
    }

}
