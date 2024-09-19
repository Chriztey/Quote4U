package com.chris.quote4u.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chris.quote4u.R
import com.chris.quote4u.component.BottomDrawerOpener
import com.chris.quote4u.component.BottomDrawerSheet
import com.chris.quote4u.component.DeleteDialogBox
import com.chris.quote4u.viewmodel.QuotesViewModel

@Composable
fun ViewSavedQuoteScreen(
    quoteId: Int,
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit
) {

    val context = LocalContext.current

    val viewModel = hiltViewModel<QuotesViewModel>()
    val selectedQuote by viewModel.selectedQuote.collectAsState()

    LaunchedEffect(selectedQuote) {
        viewModel.getQuoteByID(quoteId)
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
                .padding(
                    paddingValues = innerpadding
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        text = "Quote4U",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                    ) {

                        Card(
                            modifier = Modifier
                                .padding(vertical = 43.dp, horizontal = 16.dp)
                                .sizeIn(minWidth = 320.dp, minHeight = 186.dp),
                            shape = RectangleShape,
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary)
                        ) {

                            selectedQuote?.let {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 32.dp, horizontal = 16.dp),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    text = it.quote
                                )

                                Spacer(modifier = Modifier.height(48.dp))

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 16.dp, bottom = 8.dp),
                                    text = it.author,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.End
                                )

                            }
                        }

                        Box {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .sizeIn(maxWidth = 350.dp, maxHeight = 100.dp),
                                painter = painterResource(id = R.drawable.tape),
                                alignment = Alignment.TopEnd,
                                contentDescription = ""
                            )
                        }
                    }

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(48.dp)
                            .clickable {
                                openDeleteDialog = true
                            },
                        painter = painterResource(id = R.drawable.cross_delete),
                        contentDescription = "shuffle"
                    )
                }
            }

            BottomDrawerOpener(modifier = Modifier.align(Alignment.BottomCenter)) {
                openDrawer = true
            }


            if (openDrawer) {
                BottomDrawerSheet(
                    onButtonClick = {navigateToHome()},
                    onDismissBottomSheet = {openDrawer = false},
                    buttonText = "Back to Home"
                )
            }

            if(openDeleteDialog) {
                DeleteDialogBox(
                    dismissDialog = {openDeleteDialog = false},
                    confirmButton = {
                        selectedQuote?.let { viewModel.deleteSavedQuote(it) }
                        Toast.makeText(context,"Item removed",Toast.LENGTH_SHORT).show()
                        openDeleteDialog = false
                        navigateBack()
                    }
                )
            }

        }
    }


}