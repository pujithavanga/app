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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiskDetailsScreen(onNavigateBack: () -> Unit, onViewProbabilityAnalysisClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Risk Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("● Low Risk", color = Color.Green, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Low Risk Level", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                            Text("Your health assessment indicates low concern based on clinical parameters and lifestyle factors.", textAlign = TextAlign.Center)
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("What This Means", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Text("A low risk score indicates your symptoms appear manageable at this time.", modifier = Modifier.padding(16.dp))
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Contributing Factors", style = MaterialTheme.typography.titleLarge)
                    }
                }
                item { FactorCard("Reported Symptoms", "Multiple symptoms identified", "Low Impact") }
                item { FactorCard("Severity Level", "Severity rated 5/10", "Medium Impact") }
                item { FactorCard("Duration", "Symptoms persisting for Today", "Low Impact") }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onViewProbabilityAnalysisClicked, modifier = Modifier.fillMaxWidth()) {
                Text("View Probability Analysis")
            }
        }
    }
}

@Composable
fun FactorCard(title: String, subtitle: String, impact: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Text(
                impact,
                color = when (impact) {
                    "Low Impact" -> Color.Green
                    "Medium Impact" -> Color(0xFFFFA000)
                    else -> Color.Red
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RiskDetailsScreenPreview() {
    PrediagnosticTheme {
        RiskDetailsScreen(onNavigateBack = {}, onViewProbabilityAnalysisClicked = {})
    }
}
