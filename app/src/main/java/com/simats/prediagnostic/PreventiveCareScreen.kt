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
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

data class Exercise(val days: String, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreventiveCareScreen(onNavigateBack: () -> Unit, onContinueClicked: () -> Unit) {
    val lifestyleChanges = listOf(
        "Aim for 7-8 hours of quality sleep each night",
        "Reduce processed food and sugar intake",
        "Stay hydrated with 8+ glasses of water daily",
        "Limit alcohol consumption",
        "Quit smoking if applicable"
    )
    val exercisePlan = listOf(
        Exercise("Mon/Wed/Fri", "30 min brisk walking"),
        Exercise("Tue/Thu", "20 min strength training"),
        Exercise("Sat", "45 min swimming or cycling"),
        Exercise("Sun", "Rest or gentle stretching")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preventive Care") },
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
            LinearProgressIndicator(progress = { 3f / 6f }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("STEP 3 OF 6", style = MaterialTheme.typography.labelSmall)
                Text("Preventive Care", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Prevention is Key", style = MaterialTheme.typography.titleLarge)
                            Text("Small changes can make a big difference")
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { Text("Lifestyle Changes", style = MaterialTheme.typography.titleMedium) }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                items(lifestyleChanges.size) { index ->
                    Row(modifier = Modifier.padding(vertical = 4.dp)) {
                        Icon(Icons.Default.Check, contentDescription = null)
                        Text(lifestyleChanges[index], modifier = Modifier.padding(start = 8.dp))
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { Text("Exercise Plan", style = MaterialTheme.typography.titleMedium) }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                items(exercisePlan.size) { index ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(exercisePlan[index].days)
                        Text(exercisePlan[index].description)
                    }
                }
            }
            Button(onClick = onContinueClicked, modifier = Modifier.fillMaxWidth()) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreventiveCareScreenPreview() {
    PrediagnosticTheme {
        PreventiveCareScreen(onNavigateBack = {}, onContinueClicked = {})
    }
}
