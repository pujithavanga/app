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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
fun ProbabilityAnalysisScreen(onNavigateBack: () -> Unit, onViewRecommendationsClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Probability Analysis") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Risk Probability", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(progress = { 0.95f }, modifier = Modifier.fillMaxWidth())
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("0% Low")
                    Text("50% Medium")
                    Text("100% High")
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Confidence Interval", fontWeight = FontWeight.Bold)
                        Text("Indicates the range your actual risk likely falls between.")
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("38%", style = MaterialTheme.typography.headlineMedium)
                                Text("Lower Bound")
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("52%", style = MaterialTheme.typography.headlineMedium)
                                Text("Upper Bound")
                            }
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Compared to Population", fontWeight = FontWeight.Bold)
                        // This is a simplified representation
                        Text("Your ongoing average: 45%")
                        Text("Peer average: 32%")
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("At Confidence 87% - This statement is based on a validated clinical model and your provided data.", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            item {
                Button(onClick = onViewRecommendationsClicked, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                    Text("View Recommendations")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProbabilityAnalysisScreenPreview() {
    PrediagnosticTheme {
        ProbabilityAnalysisScreen(onNavigateBack = {}, onViewRecommendationsClicked = {})
    }
}
