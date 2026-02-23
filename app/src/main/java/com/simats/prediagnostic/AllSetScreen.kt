
package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@Composable
fun AllSetScreen(onContinueToHomeClicked: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All Set!", style = MaterialTheme.typography.headlineLarge)
            Button(onClick = onContinueToHomeClicked) {
                Text(text = "Continue to Home")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllSetScreenPreview() {
    PrediagnosticTheme {
        AllSetScreen(onContinueToHomeClicked = {})
    }
}
