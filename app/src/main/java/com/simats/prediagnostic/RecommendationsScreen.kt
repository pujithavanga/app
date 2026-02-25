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

data class Recommendation(val title: String, val description: String, val priority: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationsScreen(onNavigateBack: () -> Unit, onViewPreventiveTipsClicked: () -> Unit) {
    val recommendations = listOf(
        Recommendation("Schedule Cardiology Consultation", "Within 2 weeks for comprehensive evaluation", "high"),
        Recommendation("Monitor Blood Pressure", "Check twice daily and log readings", "high"),
        Recommendation("Dietary Modifications", "Reduce sodium, increase fiber intake", "medium"),
        Recommendation("Increase Physical Activity", "30 minutes moderate exercise, 5x weekly", "medium"),
        Recommendation("Stress Management", "Practice relaxation techniques daily", "low")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recommendations") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)) {
            LinearProgressIndicator(progress = { 2f / 6f }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("STEP 2 OF 6", style = MaterialTheme.typography.labelSmall)
                Text("Recommendations", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Based on your results, here are personalized recommendations prioritized by importance.")
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(recommendations.size) { index ->
                    RecommendationCard(recommendations[index])
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Button(onClick = onViewPreventiveTipsClicked, modifier = Modifier.fillMaxWidth()) {
                Text("View Preventive Tips")
            }
        }
    }
}

@Composable
fun RecommendationCard(recommendation: Recommendation) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(recommendation.title, fontWeight = FontWeight.Bold)
                Text(recommendation.description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Text(
                recommendation.priority,
                color = when (recommendation.priority) {
                    "high" -> Color.Red
                    "medium" -> Color(0xFFFFA000)
                    else -> Color.Green
                },
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationsScreenPreview() {
    PrediagnosticTheme {
        RecommendationsScreen(onNavigateBack = {}, onViewPreventiveTipsClicked = {})
    }
}
