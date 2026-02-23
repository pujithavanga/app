
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MedicalHistoryScreen(onNavigateBack: () -> Unit, onContinueClicked: () -> Unit) {
    var medications by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    val chronicConditions = remember {
        listOf(
            "Diabetes", "Hypertension", "Heart Disease", "Asthma",
            "Cancer", "Thyroid", "Arthritis", "None"
        )
    }
    val selectedConditions = remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Medical History") },
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
            Text(text = "Chronic Conditions", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Select all that apply", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chronicConditions.forEach { condition ->
                    val isSelected = selectedConditions.value.contains(condition)
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            val newSelection = selectedConditions.value.toMutableSet()
                            if (isSelected) {
                                newSelection.remove(condition)
                            } else {
                                newSelection.add(condition)
                            }
                            selectedConditions.value = newSelection
                        },
                        label = { Text(condition) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Current Medications", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "List any medications you take", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = medications,
                onValueChange = { medications = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g., Metformin 500mg, Lisinopril 10mg...") },
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Allergies", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Food, drug, or environmental", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = allergies,
                onValueChange = { allergies = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g., Penicillin, Peanuts, Pollen...") },
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicalHistoryScreenPreview() {
    PrediagnosticTheme {
        MedicalHistoryScreen(onNavigateBack = {}, onContinueClicked = {})
    }
}
