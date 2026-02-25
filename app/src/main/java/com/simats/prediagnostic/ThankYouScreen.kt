package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal
import kotlinx.coroutines.delay

@Composable
fun ThankYouScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = "Success", tint = Teal)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Thank You!", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Your feedback has been submitted successfully.", color = Color.Gray)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onTimeout) {
                Text("Back to Home")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThankYouScreenPreview() {
    PrediagnosticTheme {
        ThankYouScreen { }
    }
}
