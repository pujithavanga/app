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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareResultsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compare Results") },
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
            item { RiskScoreTrend() }
            item { ImprovingTrend() }
            item { SideBySide() }
            item { FactorChanges() }
        }
    }
}

@Composable
private fun RiskScoreTrend() {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Risk Score Trend", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Chart coming soon", color = Color.Gray)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Dec 5", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text("Jan 8", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text("Jan 22", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}

@Composable
private fun ImprovingTrend() {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = "Improving trend", tint = Color(0xFF2E7D32))
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text("Improving Trend", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                Text("Your risk score decreased by 27% since December", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun SideBySide() {
    Column {
        Text("Side by Side", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ComparisonCard(date = "Jan 22, 2026", score = 45, change = "-27%", color = Color(0xFFF9A825), modifier = Modifier.weight(1f))
            ComparisonCard(date = "Dec 5, 2026", score = 72, change = null, color = Color(0xFFC62828), modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun ComparisonCard(date: String, score: Int, change: String?, color: Color, modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(progress = { score / 100f }, modifier = Modifier.size(80.dp), color = color, strokeWidth = 8.dp)
                Text("$score%", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (change != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ArrowDownward, contentDescription = "Change", tint = Color(0xFF2E7D32), modifier = Modifier.size(16.dp))
                    Text(change, color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun FactorChanges() {
    Column {
        Text("Factor Changes", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            FactorChangeItem("Lifestyle", "55%", "35%")
            FactorChangeItem("Symptoms", "80%", "45%")
            FactorChangeItem("Blood Pressure", "65%", "50%")
        }
    }
}

@Composable
private fun FactorChangeItem(factor: String, old: String, new: String) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(factor, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(old, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Arrow", tint = Color.Gray, modifier = Modifier.padding(horizontal = 8.dp))
                Text(new, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompareResultsScreenPreview() {
    PrediagnosticTheme {
        CompareResultsScreen(onNavigateBack = {})
    }
}
