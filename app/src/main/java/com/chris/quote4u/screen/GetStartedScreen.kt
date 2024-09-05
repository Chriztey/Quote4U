package com.chris.quote4u.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chris.quote4u.R
import com.chris.quote4u.component.OutlinedTransparentButton

@Composable
fun GetStartedScreen (
    modifier: Modifier
) {

    Surface (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colorScheme.background
    ) {
        Box{
            Image(
                modifier = Modifier.align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.corner_paper),
                contentDescription = ""
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 209.dp),
                // .align(Alignment.Center),
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),

                    //.sizeIn(maxHeight = 373.dp, maxWidth = 366.dp),
                    painter = painterResource(id = R.drawable.get_inspo),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.height(156.dp))

                OutlinedTransparentButton(text = "Get Started")

            }


        }
    }

}
