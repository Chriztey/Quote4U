package com.chris.quote4u.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chris.quote4u.R
import com.chris.quote4u.component.BottomDrawerOpener
import com.chris.quote4u.component.BottomDrawerSheet

@Composable
@Preview
fun SavedQuoteListScreen() {

    var openDrawer by remember {
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
                    .verticalScroll(rememberScrollState())
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

                ListItemCard(Alignment.TopStart)

                ListItemCard(Alignment.TopEnd)

            }

            BottomDrawerOpener(
                modifier = Modifier.align(Alignment.BottomCenter),
                openDrawer = {openDrawer = true}
            )

            if (openDrawer) {
                BottomDrawerSheet(
                    onDismissBottomSheet = {openDrawer = false},
                    buttonText = "Back to Home"
                )
            }
        }
    }

}

@Composable
fun ListItemCard(
    alignment: Alignment
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        Card(
            modifier = Modifier
                .align(alignment)
                .fillMaxWidth(0.85f)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "aabhasshsdhsdhsdjshdjsdhjdhsdjshdjdhsakslasklaskalskaslkaslkslaskalskalskaslakaskalsklask")
        }



        IconButton(
            modifier = Modifier.align(alignment),
            onClick = { /*TODO*/ }) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(32.dp)
                    .align(alignment),

                painter = painterResource(id = R.drawable.cross_delete), contentDescription = "")
        }
    }
}