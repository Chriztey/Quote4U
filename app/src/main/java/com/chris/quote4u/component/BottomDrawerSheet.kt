package com.chris.quote4u.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chris.quote4u.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomDrawerSheet(
    onButtonClick: () -> Unit = {},
    onDismissBottomSheet: () -> Unit,
    buttonText: String
) {

    ModalBottomSheet(
        onDismissRequest = {
            onDismissBottomSheet()},
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ) {
        OutlinedTransparentButton(
            onClick = { onButtonClick() },
            mainColor = MaterialTheme.colorScheme.primary,
            text = buttonText)
    }
}


@Composable
fun BottomDrawerOpener(
    modifier: Modifier,
    openDrawer: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp, bottomEnd = 0.dp, bottomStart = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            onClick = { openDrawer() }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_calendar_view_day_24),
                contentDescription = "" )
        }
    }
}