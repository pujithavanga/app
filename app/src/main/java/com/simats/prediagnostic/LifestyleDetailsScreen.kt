
package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LifestyleDetailsScreen(onNavigateBack: () -> Unit, onContinueClicked: () -> Unit) {
    val smokingStatusOptions = remember { listOf("Never", "Former", "Current", "Occasional") }
    val alcoholConsumptionOptions = remember { listOf("None", "Occasional", "Moderate", "Heavy") }
    val exerciseFrequencyOptions = remember { listOf("Sedentary", "1-2x/week", "3-4x/week", "Daily") }
    val dietTypeOptions = remember { listOf("Regular", "Vegetarian", "Vegan", "Keto", "Mediterranean") }

    val selectedSmokingStatus = remember { mutableStateOf<String?>(null) }
    val selectedAlcoholConsumption = remember { mutableStateOf<String?>(null) }
    val selectedExerciseFrequency = remember { mutableStateOf<String?>(null) }
    val selectedDietType = remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lifestyle Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onContinueClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Continue", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Smoking Status
            Text(text = "Smoking Status", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                smokingStatusOptions.forEach { option ->
                    FilterChip(
                        selected = selectedSmokingStatus.value == option,
                        onClick = { selectedSmokingStatus.value = option },
                        label = { Text(option) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Alcohol Consumption
            Text(text = "Alcohol Consumption", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                alcoholConsumptionOptions.forEach { option ->
                    FilterChip(
                        selected = selectedAlcoholConsumption.value == option,
                        onClick = { selectedAlcoholConsumption.value = option },
                        label = { Text(option) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Exercise Frequency
            Text(text = "Exercise Frequency", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                exerciseFrequencyOptions.forEach { option ->
                    FilterChip(
                        selected = selectedExerciseFrequency.value == option,
                        onClick = { selectedExerciseFrequency.value = option },
                        label = { Text(option) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Diet Type
            Text(text = "Diet Type", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                dietTypeOptions.forEach { option ->
                    FilterChip(
                        selected = selectedDietType.value == option,
                        onClick = { selectedDietType.value = option },
                        label = { Text(option) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LifestyleDetailsScreenPreview() {
    PrediagnosticTheme {
        LifestyleDetailsScreen(onNavigateBack = {}, onContinueClicked = {})
    }
}
