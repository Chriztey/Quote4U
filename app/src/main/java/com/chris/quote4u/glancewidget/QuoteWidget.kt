package com.chris.quote4u.glancewidget

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
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
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.currentState
import androidx.glance.layout.padding
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



object QuoteWidget: GlanceAppWidget() {


    val indexKey = intPreferencesKey("index")

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val repo = QuoteWidgetRepoImple.get(context)




        provideContent {
            Scaffold(
                backgroundColor = GlanceTheme.colors.widgetBackground,
                titleBar = {
                    Text(
                        modifier = GlanceModifier.fillMaxWidth().padding(4.dp),
                        text = "Quote4U",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = GlanceTheme.colors.onSurface
                        )
                    )
                }
            ) {
                val quotes = repo.loadSavedQuotes().collectAsState(initial = emptyList())
                val randomIndex = (0.. quotes.value.size ).random()
                val index = currentState(key = indexKey) ?: randomIndex

                Log.d("randomIndex", index.toString())

                //if (quotes.value !== emptyList<SavedQuoteData>()) {
                    quotes.value.forEach {
                        if (quotes.value.indexOf(it) == index) {
                            WidgetContent(
                                quoteDisplay = it.quote,
                                listSize = quotes.value,
                                showingIndex = index
                                )
                        }
                    }
//                } else {
//                    WidgetContent(quoteDisplay = "You haven't favorited any quote yet ..", emptyList(), index)
//                }
            }
        }
    }
}

@Composable
fun WidgetContent(
    quoteDisplay: String,
    listSize: List<SavedQuoteData>,
    showingIndex: Int
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

        Button(text = "Shuffle",
            onClick = if (6 > showingIndex) {
                actionRunCallback(shuffleQuoteActionCallBack::class.java)
            } else {
                actionRunCallback(resetIndexCallBack::class.java)
            } )


    }

}


object shuffleQuoteActionCallBack : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        //val shuffleNumber = (0..listSize.size).random()


        updateAppWidgetState(context, glanceId) { prefs ->
            val currentIndex = prefs[QuoteWidget.indexKey]
            if (currentIndex != null) {
                prefs[QuoteWidget.indexKey] = currentIndex + 1
            } else {
                prefs[QuoteWidget.indexKey] = 0
            }
        }

        QuoteWidget.update(context, glanceId)

    }
}

object resetIndexCallBack : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {

        updateAppWidgetState(context, glanceId) { prefs ->
           prefs[QuoteWidget.indexKey] = 0

        }

        QuoteWidget.update(context, glanceId)

    }
}
