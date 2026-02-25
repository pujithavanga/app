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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicalAnalysisReportScreen(
    onNavigateBack: () -> Unit,
    onViewFullReportClicked: () -> Unit,
    onNewScreeningClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clinical Analysis Report") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Health Assessment", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("25", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                                Text("Score")
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Low Risk", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                                Text("Risk Level")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Status: Within Healthy Parameters", style = MaterialTheme.typography.bodyMedium)
                        Text("This analysis is based on the provided health data. For a complete picture, consult with a healthcare professional.", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Button(onClick = onViewFullReportClicked, modifier = Modifier.fillMaxWidth()) {
                    Text("View Full Report")
                }
            }
            item {
                OutlinedButton(onClick = onNewScreeningClicked, modifier = Modifier.fillMaxWidth()) {
                    Text("New Screening")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClinicalAnalysisReportScreenPreview() {
    PrediagnosticTheme {
        ClinicalAnalysisReportScreen(onNavigateBack = {}, onViewFullReportClicked = {}, onNewScreeningClicked = {})
    }
}
