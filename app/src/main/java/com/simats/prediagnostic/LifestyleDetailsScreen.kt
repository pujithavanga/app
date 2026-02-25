
package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LifestyleDetailsScreen(onNavigateBack: () -> Unit, onContinueClicked: () -> Unit) {
    val heightState = remember { mutableStateOf("") }
    val weightState = remember { mutableStateOf("") }
    val waistCircumferenceState = remember { mutableStateOf("") }
    val packYearsState = remember { mutableStateOf("") }

    val bmi = remember(heightState.value, weightState.value) {
        val height = heightState.value.toFloatOrNull()?.div(100) ?: 0f
        val weight = weightState.value.toFloatOrNull() ?: 0f
        if (height > 0 && weight > 0) {
            val bmiValue = weight / (height * height)
            DecimalFormat("#.##").format(bmiValue)
        } else {
            ""
        }
    }

    val smokingStatusOptions = remember { listOf("Never", "Former", "Current", "Occasional") }
    val alcoholConsumptionOptions = remember { listOf("None", "Occasional", "Moderate", "Heavy") }
    val physicalActivityOptions = remember { listOf("Sedentary", "1-2x/week", "3-4x/week", "Daily") }

    val selectedSmokingStatus = remember { mutableStateOf<String?>(null) }
    val selectedAlcoholConsumption = remember { mutableStateOf<String?>(null) }
    val selectedPhysicalActivity = remember { mutableStateOf<String?>(null) }

    val familyHistoryOptions = remember {
        listOf(
            "Diabetes", "Heart Disease", "Hypertension", "Cancer", "Stroke", "Alzheimer's", "Kidney Disease"
        )
    }
    val selectedFamilyHistory = remember { mutableStateMapOf<String, Boolean>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lifestyle & Genetic History") },
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
            // Physical Metrics
            Text(text = "Physical Metrics", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = heightState.value,
                onValueChange = { heightState.value = it },
                label = { Text("Height") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = { Text("cm") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = weightState.value,
                onValueChange = { weightState.value = it },
                label = { Text("Weight") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = { Text("kg") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = bmi,
                onValueChange = { /* Not editable */ },
                label = { Text("BMI") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                placeholder = { Text("Auto-calc") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = waistCircumferenceState.value,
                onValueChange = { waistCircumferenceState.value = it },
                label = { Text("Waist Circumference") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = { Text("cm") }
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Lifestyle Factors
            Text(text = "Lifestyle Factors", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Smoking Status", fontWeight = FontWeight.Medium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                smokingStatusOptions.forEach { option ->
                    FilterChip(
                        selected = selectedSmokingStatus.value == option,
                        onClick = { selectedSmokingStatus.value = option },
                        label = { Text(option) }
                    )
                }
            }
            if (selectedSmokingStatus.value in listOf("Current", "Former")) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = packYearsState.value,
                    onValueChange = { packYearsState.value = it },
                    label = { Text("Pack Years (if smoker)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = { Text("years") }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Alcohol Consumption", fontWeight = FontWeight.Medium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                alcoholConsumptionOptions.forEach { option ->
                    FilterChip(
                        selected = selectedAlcoholConsumption.value == option,
                        onClick = { selectedAlcoholConsumption.value = option },
                        label = { Text(option) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Physical Activity", fontWeight = FontWeight.Medium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                physicalActivityOptions.forEach { option ->
                    FilterChip(
                        selected = selectedPhysicalActivity.value == option,
                        onClick = { selectedPhysicalActivity.value = option },
                        label = { Text(option) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Family History
            Text(text = "Family History", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            familyHistoryOptions.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = selectedFamilyHistory[option] ?: false,
                        onCheckedChange = { selectedFamilyHistory[option] = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = option)
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
