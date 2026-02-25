package com.simats.prediagnostic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

data class ScreeningResult(
    val name: String,
    val date: String,
    val risk: String,
    val riskColor: Color,
    val score: Int,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreeningHistoryScreen(
    onScreeningClicked: (String) -> Unit
) {
    val screeningHistory = remember {
        listOf(
            ScreeningResult("Cardiac", "Jan 22, 2026", "Medium Risk", Color(0xFFFFA000), 452, Icons.Default.Favorite),
            ScreeningResult("General Health", "Jan 12, 2024", "Low Risk", Color(0xFF388E3C), 285, Icons.Default.HealthAndSafety),
            ScreeningResult("Diabetes", "Jan 9, 2026", "Medium Risk", Color(0xFFFFA000), 526, Icons.Default.Warning)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Total Screenings", style = MaterialTheme.typography.titleMedium)
                    Text("3", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Average Risk", style = MaterialTheme.typography.titleMedium)
                    Text("Medium Risk", color = Color(0xFFFFA000), fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("All Screenings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                TextButton(onClick = { /*TODO: Filter*/ }) {
                    Text("Filter")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(screeningHistory) { screening ->
            ScreeningHistoryCard(screening = screening, onClick = { onScreeningClicked(screening.name) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ScreeningHistoryCard(screening: ScreeningResult, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(screening.icon, contentDescription = null, modifier = Modifier.size(40.dp), tint = screening.riskColor)
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(screening.name, fontWeight = FontWeight.Bold)
                Text(screening.date, style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(screening.risk, fontWeight = FontWeight.Bold, color = screening.riskColor)
                Text(screening.score.toString(), style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreeningHistoryScreenPreview() {
    PrediagnosticTheme {
        ScreeningHistoryScreen(onScreeningClicked = {})
    }
}
