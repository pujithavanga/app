package com.simats.prediagnostic

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.LightMint
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalScreeningScreen(
    onNavigateBack: () -> Unit,
    onLifestyleClicked: () -> Unit,
    onRunAnalysisClicked: () -> Unit
) {
    var expandedItem by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var isReportUploaded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Medical Screening") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                UploadReportCard(isReportUploaded = isReportUploaded, onBrowseClicked = {
                    isReportUploaded = true
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Report uploaded successfully!")
                    }
                })
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                DataCompletionCard()
            }
            item {
                ExpandableCard(
                    title = "Vital Signs",
                    subtitle = "Step 1 of 7",
                    icon = Icons.Default.MonitorHeart,
                    isExpanded = expandedItem == "Vital Signs",
                    onClick = { expandedItem = if (expandedItem == "Vital Signs") null else "Vital Signs" }
                ) {
                    VitalSignsForm()
                }
            }
            item {
                ExpandableCard(
                    title = "Blood Tests",
                    subtitle = "Step 2 of 7",
                    icon = Icons.Default.Bloodtype,
                    isExpanded = expandedItem == "Blood Tests",
                    onClick = { expandedItem = if (expandedItem == "Blood Tests") null else "Blood Tests" }
                ) {
                    BloodTestsForm()
                }
            }
            item {
                ExpandableCard(
                    title = "Biochemical Panel",
                    subtitle = "Step 3 of 7",
                    icon = Icons.Default.Thermostat,
                    isExpanded = expandedItem == "Biochemical Panel",
                    onClick = { expandedItem = if (expandedItem == "Biochemical Panel") null else "Biochemical Panel" }
                ) {
                    BiochemicalPanelForm()
                }
            }
            item {
                ExpandableCard(
                    title = "Neurological & Mental Health",
                    subtitle = "Step 4 of 7",
                    icon = Icons.Default.Psychology,
                    isExpanded = expandedItem == "Neurological & Mental Health",
                    onClick = { expandedItem = if (expandedItem == "Neurological & Mental Health") null else "Neurological & Mental Health" }
                ) {
                    NeurologicalAndMentalHealthForm()
                }
            }
            item {
                ExpandableCard(
                    title = "Liver & Kidney Function",
                    subtitle = "Step 5 of 7",
                    icon = Icons.Default.Thermostat,
                    isExpanded = expandedItem == "Liver & Kidney Function",
                    onClick = { expandedItem = if (expandedItem == "Liver & Kidney Function") null else "Liver & Kidney Function" }
                ) {
                    LiverAndKidneyFunctionForm()
                }
            }
            item {
                ExpandableCard(
                    title = "Hormonal & Deficiency Panel",
                    subtitle = "Step 6 of 7",
                    icon = Icons.Default.Thermostat,
                    isExpanded = expandedItem == "Hormonal & Deficiency Panel",
                    onClick = { expandedItem = if (expandedItem == "Hormonal & Deficiency Panel") null else "Hormonal & Deficiency Panel" }
                ) {
                    HormonalAndDeficiencyPanelForm()
                }
            }
            item {
                ExpandableCard(
                    title = "Lifestyle & Genetic History",
                    subtitle = "Step 7 of 7",
                    icon = Icons.Default.Thermostat,
                    isExpanded = expandedItem == "Lifestyle & Genetic History",
                    onClick = onLifestyleClicked
                ) {}
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onRunAnalysisClicked, modifier = Modifier.fillMaxWidth()) {
                    Text("Run AI Analysis")
                }
            }
        }
    }
}

@Composable
fun UploadReportCard(isReportUploaded: Boolean, onBrowseClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Teal, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightMint)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Upload Report", style = MaterialTheme.typography.titleMedium)
            Text("Upload your lab reports to auto-fill parameters")
            Icon(Icons.Default.UploadFile, contentDescription = "Upload", tint = Teal)
            if (isReportUploaded) {
                Text("Report Uploaded Successfully!", color = Teal, fontWeight = FontWeight.Bold)
            } else {
                Text("Tap to Upload Report")
            }
            Text("PDF, JPG, PNG supported")
            Row {
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text("Camera")
                }
                Button(onClick = onBrowseClicked) {
                    Text("Browse")
                }
            }
        }
    }
}

@Composable
fun DataCompletionCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text("Data Completion", fontWeight = FontWeight.Bold)
                Text("Fill in as much information as you have available from your recent tests.")
            }
            Text("25%", color = Teal, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun VitalSignsForm() {
    Column(modifier = Modifier.padding(16.dp)) {
        FormTextField(label = "Systolic BP", value = "120", unit = "mmHg", normalRange = "Normal: < 120")
        FormTextField(label = "Diastolic BP", value = "80", unit = "mmHg", normalRange = "Normal: < 80")
        FormTextField(label = "Heart Rate", value = "77", unit = "bpm", normalRange = "Normal: 60-100")
        FormTextField(label = "Body Temperature", value = "98.6", unit = "°F", normalRange = "Normal: 97-99")
        FormTextField(label = "Respiratory Rate", value = "16", unit = "breaths/min", normalRange = "Normal: 12-20")
        FormTextField(label = "SpO2", value = "98", unit = "%", normalRange = "Normal: 95-100")
    }
}

@Composable
fun BloodTestsForm() {
    Column(modifier = Modifier.padding(16.dp)) {
        FormTextField(label = "Hemoglobin", value = "14.5", unit = "g/dL", normalRange = "Normal: 12-17")
        FormTextField(label = "Hematocrit", value = "42", unit = "%", normalRange = "Normal: 36-50")
        FormTextField(label = "RBC Count", value = "5.0", unit = "million/µL", normalRange = "Normal: 4.5-5.5")
        FormTextField(label = "WBC Count", value = "7000", unit = "cells/µL", normalRange = "Normal: 4500-11000")
        FormTextField(label = "Platelets", value = "250000", unit = "cells/µL", normalRange = "Normal: 150000-400000")
    }
}

@Composable
fun BiochemicalPanelForm() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Glucose Panel", fontWeight = FontWeight.Bold)
        FormTextField(label = "Fasting Glucose", value = "90", unit = "mg/dL", normalRange = "Normal: 70-100")
        FormTextField(label = "Post-Prandial Glucose", value = "120", unit = "mg/dL", normalRange = "Normal: < 140")
        FormTextField(label = "HbA1c", value = "5.4", unit = "%", normalRange = "Normal: < 5.7")
        Text("Lipid Profile", fontWeight = FontWeight.Bold)
        FormTextField(label = "Total Cholesterol", value = "180", unit = "mg/dL", normalRange = "Normal: < 200")
        FormTextField(label = "LDL", value = "90", unit = "mg/dL", normalRange = "Normal: < 100")
        FormTextField(label = "HDL", value = "55", unit = "mg/dL", normalRange = "Normal: > 40")
        FormTextField(label = "Triglycerides", value = "120", unit = "mg/dL", normalRange = "Normal: < 150")
        Text("Electrolytes", fontWeight = FontWeight.Bold)
        FormTextField(label = "Sodium", value = "140", unit = "mEq/L", normalRange = "Normal: 135-145")
        FormTextField(label = "Potassium", value = "4.2", unit = "mEq/L", normalRange = "Normal: 3.5-5.0")
    }
}

@Composable
fun NeurologicalAndMentalHealthForm() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Cognitive Assessment", fontWeight = FontWeight.Bold)
        FormTextField(label = "MMSE Score", value = "", unit = "/30", normalRange = "")
        FormTextField(label = "MoCA Score", value = "", unit = "/30", normalRange = "")
        FormTextField(label = "Glasgow Coma Scale", value = "Select...", unit = "", normalRange = "")
        Text("Mental Health Screening", fontWeight = FontWeight.Bold)
        FormTextField(label = "PHQ-9 Score", value = "", unit = "/27", normalRange = "")
        FormTextField(label = "GAD-7 Score", value = "", unit = "/21", normalRange = "")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("History of Depression")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("History of Anxiety")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("Sleep Disorders")
        }
        Text("Neurological Signs", fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("Tremors")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("Memory Loss")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("Coordination Issues")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("Numbness/Tingling")
        }
    }
}

@Composable
fun LiverAndKidneyFunctionForm() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Liver Function Tests", fontWeight = FontWeight.Bold)
        FormTextField(label = "ALT (SGPT)", value = "25", unit = "U/L", normalRange = "Normal: 7-56")
        FormTextField(label = "AST (SGOT)", value = "22", unit = "U/L", normalRange = "Normal: 10-40")
        FormTextField(label = "ALP", value = "65", unit = "U/L", normalRange = "Normal: 44-147")
        FormTextField(label = "GGT", value = "30", unit = "U/L", normalRange = "Normal: 9-48")
        FormTextField(label = "Bilirubin (Total)", value = "0.8", unit = "mg/dL", normalRange = "Normal: 0.1-1.2")
        FormTextField(label = "Albumin", value = "4.2", unit = "g/dL", normalRange = "Normal: 3.4-5.4")
        FormTextField(label = "Total Protein", value = "7.0", unit = "g/dL", normalRange = "Normal: 6.0-8.3")
        Text("Kidney Function Tests", fontWeight = FontWeight.Bold)
        FormTextField(label = "Creatinine", value = "0.9", unit = "mg/dL", normalRange = "Normal: 0.7-1.3")
        FormTextField(label = "BUN", value = "14", unit = "mg/dL", normalRange = "Normal: 6-20")
        FormTextField(label = "eGFR", value = "95", unit = "mL/min/1.73m²", normalRange = "Normal: > 90")
        FormTextField(label = "Uric Acid", value = "5.5", unit = "mg/dL", normalRange = "Normal: 3.5-7.2")
        Text("Urinalysis", fontWeight = FontWeight.Bold)
        FormTextField(label = "Protein", value = "Select...", unit = "", normalRange = "")
        FormTextField(label = "Glucose", value = "Select...", unit = "", normalRange = "")
    }
}

@Composable
fun HormonalAndDeficiencyPanelForm() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Thyroid Panel", fontWeight = FontWeight.Bold)
        FormTextField(label = "TSH", value = "2.5", unit = "mIU/L", normalRange = "Normal: 0.4-4.0")
        FormTextField(label = "Free T3", value = "3.2", unit = "pg/mL", normalRange = "Normal: 2.3-4.2")
        FormTextField(label = "Free T4", value = "1.4", unit = "ng/dL", normalRange = "Normal: 0.8-1.8")
        Text("Metabolic Hormones", fontWeight = FontWeight.Bold)
        FormTextField(label = "Insulin (Fasting)", value = "10", unit = "mIU/mL", normalRange = "")
        FormTextField(label = "Cortisol", value = "15", unit = "mcg/dL", normalRange = "")
        FormTextField(label = "HOMA-IR", value = "Calculated", unit = "", normalRange = "")
        Text("Vitamins & Minerals", fontWeight = FontWeight.Bold)
        FormTextField(label = "Vitamin D", value = "45", unit = "ng/mL", normalRange = "Normal: 30-100")
        FormTextField(label = "Vitamin B12", value = "600", unit = "pg/mL", normalRange = "Normal: 200-900")
        FormTextField(label = "Folate", value = "12", unit = "ng/mL", normalRange = "Normal: > 5.0")
        FormTextField(label = "Ferritin", value = "80", unit = "ng/mL", normalRange = "Normal: 12-300")
        FormTextField(label = "Serum Iron", value = "100", unit = "mcg/dL", normalRange = "Normal: 60-170")
        FormTextField(label = "Calcium", value = "9.5", unit = "mg/dL", normalRange = "Normal: 8.5-10.5")
    }
}

@Composable
fun FormTextField(label: String, value: String, unit: String, normalRange: String) {
    var text by remember { mutableStateOf(value) }
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, modifier = Modifier.weight(1f))
        Text(normalRange, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = { Text(unit) }
    )
}

@Composable
fun ExpandableCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isExpanded: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable(onClick = onClick)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null)
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    Column {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
                IconButton(onClick = onClick) {
                    Icon(if (isExpanded) Icons.Default.ChevronRight else Icons.Default.ChevronRight, contentDescription = null)
                }
            }
            if (isExpanded) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicalScreeningScreenPreview() {
    PrediagnosticTheme {
        MedicalScreeningScreen(onNavigateBack = {}, onLifestyleClicked = {}, onRunAnalysisClicked = {})
    }
}
