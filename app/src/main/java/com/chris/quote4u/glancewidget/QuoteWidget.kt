package com.chris.quote4u.glancewidget

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.components.FilledButton
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.color.ColorProviders
import androidx.glance.currentState
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontFamily
import androidx.glance.unit.ColorProvider
import androidx.lifecycle.Lifecycle
import com.chris.quote4u.datasource.SavedQuoteData
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.random.Random


object QuoteWidget: GlanceAppWidget() {

    val indexKey = intPreferencesKey("index")

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val repo = QuoteWidgetRepoImple.get(context)

        provideContent {
            Scaffold(
                backgroundColor = ColorProvider(Color(0,30,62)),
            ) {
                val quotes = repo.loadSavedQuotes().collectAsState(initial = emptyList())
                val randomIndex = (0.. quotes.value.size ).random()
                val index = currentState(key = indexKey) ?: randomIndex

                Log.d("randomIndex", index.toString())

                Column(
                    modifier = GlanceModifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    val max = quotes.value.size -1
                    Log.d("randomIndexSize", max.toString())


                    if (max >= 0 ) {
                        quotes.value.forEach {
                            if (quotes.value.indexOf(it) == index) {
                                WidgetContent(
                                    quoteDisplay = it.quote,
                                )
                            }
                        }
                    } else {
                        WidgetContent(quoteDisplay = "You haven't favorited any quote yet ..")
                    }

                    Spacer(
                        modifier = GlanceModifier.height(8.dp)
                    )


                    Column(
                        modifier = GlanceModifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        CircleIconButton(

                            backgroundColor = ColorProvider(Color.Transparent),
                            contentColor = ColorProvider(Color.Transparent),


                            imageProvider = ImageProvider(R.drawable.dice),
                            contentDescription = "",
                            onClick =
                            if (max > index) {
                                actionRunCallback(shuffleQuoteActionCallBack::class.java)
                            } else {
                                actionRunCallback(resetIndexCallBack::class.java)
                            }
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun WidgetContent(
    quoteDisplay: String,
) {
    Column(
        modifier = GlanceModifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 12.dp)
            ,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = GlanceModifier.fillMaxWidth(),
            text = quoteDisplay.lowercase(),
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                color = ColorProvider(Color.White)
            )
        )
        Spacer(modifier = GlanceModifier.height(8.dp))
    }

}


object shuffleQuoteActionCallBack : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
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


