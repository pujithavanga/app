package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationScreen(onNavigateBack: () -> Unit, onDownloadAndShareClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consultation") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            LinearProgressIndicator(progress = { 4f / 6f }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("STEP 4 OF 6", style = MaterialTheme.typography.labelSmall)
                Text("Consultation", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4))) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Case Review Summary", style = MaterialTheme.typography.titleLarge)
                            Text("Based on the analysis, this case requires attention.")
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { Text("Recommended Actions", style = MaterialTheme.typography.titleMedium) }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Cardiology Referral", style = MaterialTheme.typography.titleMedium)
                            Text("Heart and cardiovascular specialist")
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { Text("Key points for referral:", style = MaterialTheme.typography.titleMedium) }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    Column {
                        Text("• AI analysis results and risk factors")
                        Text("• Family history of heart conditions")
                        Text("• Current symptoms and their frequency")
                        Text("• Lifestyle and medication history")
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Text("Priority Level", modifier = Modifier.weight(1f))
                            Text("Moderate", color = Color(0xFFFFA000), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Button(onClick = onDownloadAndShareClicked, modifier = Modifier.fillMaxWidth()) {
                Text("Download & Share Report")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsultationScreenPreview() {
    PrediagnosticTheme {
        ConsultationScreen(onNavigateBack = {}, onDownloadAndShareClicked = {})
    }
}
