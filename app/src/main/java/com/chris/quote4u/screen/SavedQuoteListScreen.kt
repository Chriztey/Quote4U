package com.chris.quote4u.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chris.quote4u.R
import com.chris.quote4u.component.BottomDrawerOpener
import com.chris.quote4u.component.BottomDrawerSheet
import com.chris.quote4u.component.DeleteDialogBox
import com.chris.quote4u.datasource.SavedQuoteData
import com.chris.quote4u.viewmodel.QuotesViewModel

@Composable

fun SavedQuoteListScreen(
    navigateToHome: () -> Unit,
    navigateToItemPage: (Int) -> Unit
) {

    val context = LocalContext.current

    val viewModel = hiltViewModel<QuotesViewModel>()
    val savedQuoteList by viewModel.savedQuoteList.collectAsState()

    var selectedQuote by remember {
        mutableStateOf<SavedQuoteData>(SavedQuoteData(quote = "", author = ""))
    }

    var openDrawer by remember {
        mutableStateOf(false)
    }

    var openDeleteDialog by remember {
        mutableStateOf(false)
    }

    Scaffold (
        modifier = Modifier.fillMaxSize()
    ) { innerpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues = innerpadding
                )) {
            Column(
                modifier = Modifier

                    .fillMaxSize()
                    .align(Alignment.TopCenter)
                    //.verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.background),
                //color = MaterialTheme.colorScheme.background
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    textAlign = TextAlign.Center,
                    text = "Quote4U",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Saved Quote",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                
                    LazyColumn(
                        modifier = Modifier.padding(bottom = 48.dp)
                    ) {
                        if (savedQuoteList.isNotEmpty()) {
                            items (savedQuoteList) { quote ->
                                ListItemCard(
                                    alignment = if (savedQuoteList.indexOf(quote)%2 == 0 ) {
                                        Alignment.TopStart
                                    } else {Alignment.TopEnd},
                                    onDeleteButtonClick = {
                                        selectedQuote = quote
                                        openDeleteDialog = true },
                                    quote = quote.quote,
                                    viewItem = {navigateToItemPage(quote.id)}
                                )
                            }
                        } else {
                            
                            item { 
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(64.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                 Text(
                                     style = MaterialTheme.typography.titleLarge,
                                     textAlign = TextAlign.Center,
                                     fontWeight = FontWeight.Bold,
                                     color = MaterialTheme.colorScheme.onPrimary,
                                     text = "You haven't add any quote yet...")
                                }
                            }
                            
                        }
                        
                       
                    }



                



            }

            BottomDrawerOpener(
                modifier = Modifier.align(Alignment.BottomCenter),
                openDrawer = {openDrawer = true}
            )

            if (openDrawer) {
                BottomDrawerSheet(
                    onButtonClick = {navigateToHome()},
                    onDismissBottomSheet = {openDrawer = false},
                    buttonText = "Back to Home"
                )
            }

            if (openDeleteDialog) {
                DeleteDialogBox(
                    dismissDialog = {openDeleteDialog = false},
                    confirmButton = {
                        viewModel.deleteSavedQuote(selectedQuote)
                        openDeleteDialog = false
                        Toast.makeText(context,"Item removed",Toast.LENGTH_SHORT).show()}
                )
            }
        }
    }

}

@Composable
fun ListItemCard(
    alignment: Alignment,
    onDeleteButtonClick: () -> Unit,
    quote: String,
    viewItem: () -> Unit = {}
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        Card(
            modifier = Modifier
                .align(alignment)
                .fillMaxWidth(0.85f)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .clickable { viewItem() },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = quote)
        }



        IconButton(
            modifier = Modifier.align(alignment),
            onClick = { onDeleteButtonClick() }) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(32.dp)
                    .align(alignment),

                painter = painterResource(id = R.drawable.cross_delete), contentDescription = "")
        }
    }
}