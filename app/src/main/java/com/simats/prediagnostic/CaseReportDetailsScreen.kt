package com.simats.prediagnostic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaseReportDetailsScreen(onNavigateBack: () -> Unit, onHistoryClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report Details") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { PatientHeader() }
            item { AnalysisDetails() }
            item { ClinicalNotes() }
            item { KeyFindings() }
            item { ClinicalRecommendations() }
            item { ActionButtons(onHistoryClicked) }
        }
    }
}

@Composable
private fun PatientHeader() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text("CS001", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text("Rahul Sharma", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("45 years old - Male", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFE57373).copy(alpha = 0.1f))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text("HIGH PRIORITY", color = Color(0xFFC62828), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun AnalysisDetails() {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Analysis type", tint = Color(0xFFE57373), modifier = Modifier.size(40.dp))
                Column {
                    Text("Blood Smear Analysis", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                    Text("January 22, 2026", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                ScoreIndicator(78, "Risk Score", Color(0xFFC62828))
                RiskLevel("High Risk")
                ScoreIndicator(92, "AI Confidence", MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
private fun ScoreIndicator(score: Int, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(progress = { score / 100f }, modifier = Modifier.size(60.dp), color = color, strokeWidth = 6.dp)
            Text("$score%", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}

@Composable
private fun RiskLevel(level: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFE57373).copy(alpha = 0.1f))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(level, color = Color(0xFFC62828), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("Risk Level", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}

@Composable
private fun ClinicalNotes() {
    Column {
        Text("Clinical Notes", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Text("Patient presents with fatigue, night sweats, and unexplained weight loss over past 3 months. Family history of leukemia.", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
private fun KeyFindings() {
    Column {
        Text("Key Findings", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FindingItem("1", "Abnormal cell morphology detected in peripheral blood smear")
                FindingItem("2", "Elevated white blood cell count with atypical lymphocytes")
                FindingItem("3", "Presence of blast cells suggesting possible hematologic malignancy")
                FindingItem("4", "Recommend immediate bone marrow biopsy for confirmation")
            }
        }
    }
}

@Composable
private fun FindingItem(number: String, text: String) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(Color(0xFFF9A825).copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(number, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF9A825))
        }
        Text(text, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ClinicalRecommendations() {
    Column {
        Text("Clinical Recommendations", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                RecommendationItem("Urgent hematology consultation within 24-48 hours")
                RecommendationItem("Order complete blood count with differential")
                RecommendationItem("Schedule bone marrow biopsy")
                RecommendationItem("Consider flow cytometry for cell characterization")
            }
        }
    }
}

@Composable
private fun RecommendationItem(text: String) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(">", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(text)
    }
}

@Composable
private fun ActionButtons(onHistoryClicked: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Download, contentDescription = "Download")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text("Download")
        }
        Button(onClick = onHistoryClicked, modifier = Modifier.weight(1f)) {
            Text("View History")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaseReportDetailsScreenPreview() {
    PrediagnosticTheme {
        CaseReportDetailsScreen(onNavigateBack = {}, onHistoryClicked = {})
    }
}
