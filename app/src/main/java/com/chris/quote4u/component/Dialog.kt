package com.chris.quote4u.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun DeleteDialogBox(
    dismissDialog: () -> Unit = {},
    confirmButton: () -> Unit = {}
) {
    AlertDialog(
        title = {Text("Remove")},
        text = { Text(text = "Do you want to remove selected quote from saved list ?")},
        onDismissRequest = { dismissDialog() },
        confirmButton = {
            Row {
                TextButton(onClick = { dismissDialog() }) {
                    Text("No")
                }
                TextButton(onClick = { confirmButton() }) {
                    Text("Yes")
                }

            }
        })
}