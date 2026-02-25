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
fun ReportDetailsScreen(
    reportName: String,
    onNavigateBack: () -> Unit,
    onDownloadClicked: () -> Unit,
    onCompareClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(onClick = onDownloadClicked, modifier = Modifier.weight(1f)) {
                    Text("Download")
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Button(onClick = onCompareClicked, modifier = Modifier.weight(1f)) {
                    Text("Compare")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            item { ReportSummaryCard(reportName) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { KeyFindingsCard() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { RecommendationsCard() }
        }
    }
}

@Composable
private fun ReportSummaryCard(reportName: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(reportName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            Text("January 22, 2026", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("45%", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)
                    Text("Risk Score")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Medium Risk", fontWeight = FontWeight.Bold, color = Color(0xFFFFA000))
                    Text("Risk Level")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("87%", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)
                    Text("AI Confidence")
                }
            }
        }
    }
}

@Composable
private fun KeyFindingsCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Key Findings", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("• Elevated risk indicators based on reported symptoms")
            Text("• Lifestyle factors contributing to moderate risk")
            Text("• Family history suggests monitoring is advisable")
            Text("• Blood pressure readings within borderline range")
        }
    }
}

@Composable
private fun RecommendationsCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Recommendations", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("• Schedule cardiology consultation within 2 weeks")
            Text("• Monitor blood pressure daily")
            Text("• Reduce sodium intake")
            Text("• Increase physical activity")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportDetailsScreenPreview() {
    PrediagnosticTheme {
        ReportDetailsScreen("Cardiac Screening", {}, {}, {})
    }
}
