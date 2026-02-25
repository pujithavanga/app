
package com.simats.prediagnostic

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.AbnormalRed
import com.simats.prediagnostic.ui.theme.BorderlineYellow
import com.simats.prediagnostic.ui.theme.DarkBlue
import com.simats.prediagnostic.ui.theme.LightMint
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrediagnosisReportScreen(onNavigateBack: () -> Unit, onDownloadReportClicked: () -> Unit, onBookConsultClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Prediagnosis Report") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(onClick = onDownloadReportClicked, modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Download, contentDescription = "Download Report")
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Download Report")
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = onBookConsultClicked, colors = ButtonDefaults.buttonColors(containerColor = Teal), modifier = Modifier.weight(1f)) {
                        Text("Book Consult")
                    }
                }
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = true,
                        onClick = { }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Search, contentDescription = "Screening") },
                        label = { Text("Screening") },
                        selected = false,
                        onClick = { }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.History, contentDescription = "History") },
                        label = { Text("History") },
                        selected = false,
                        onClick = { }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = false,
                        onClick = { }
                    )
                }
            }

        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                HealthSummaryCard()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ParameterAnalysisSection()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                DeeperRiskPredictionSection()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                AiAnalysisInsightsSection()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                PremiumDiagnosticTestsSection()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HealthMonitoringServiceSection()
            }
        }
    }
}

@Composable
fun HealthSummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkBlue)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Health Screening", color = Color.White, style = MaterialTheme.typography.titleMedium)
            Text("AI-driven insights", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Report Date", color = Color.Gray)
                Text("45 yrs Male", color = Color.Gray)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Feb 4, 2024", color = Color.White, fontWeight = FontWeight.Bold)
                Text("UID: 2324-3321", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ParameterAnalysisSection() {
    var expandedItem by remember { mutableStateOf<String?>(null) }

    Column {
        Text("Parameter Analysis", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        ParameterCard("Vital Signs", "2 issues found", icon = Icons.Default.MonitorHeart, isExpanded = expandedItem == "Vital Signs", onClick = { expandedItem = if (expandedItem == "Vital Signs") null else "Vital Signs" }) {
            VitalSignsDetails()
        }
        ParameterCard("Blood Test", "2 issues found", icon = Icons.Default.Bloodtype, isExpanded = expandedItem == "Blood Test", onClick = { expandedItem = if (expandedItem == "Blood Test") null else "Blood Test" }) {
            BloodTestDetails()
        }
        ParameterCard("Liver & Kidney", "2 issues found", icon = Icons.Default.Thermostat, isExpanded = expandedItem == "Liver & Kidney", onClick = { expandedItem = if (expandedItem == "Liver & Kidney") null else "Liver & Kidney" }) {
            LiverAndKidneyDetails()
        }
        ParameterCard("Hormonal Panel", "3 issues found", icon = Icons.Default.Thermostat, isExpanded = expandedItem == "Hormonal Panel", onClick = { expandedItem = if (expandedItem == "Hormonal Panel") null else "Hormonal Panel" }) {
            HormonalPanelDetails()
        }
        ParameterCard("Neurological", "1 issue found", icon = Icons.Default.Psychology, isExpanded = expandedItem == "Neurological", onClick = { expandedItem = if (expandedItem == "Neurological") null else "Neurological" }) {
            NeurologicalDetails()
        }
        ParameterCard("Lifestyle", "2 issues found", icon = Icons.Default.Balance, isExpanded = expandedItem == "Lifestyle", onClick = { expandedItem = if (expandedItem == "Lifestyle") null else "Lifestyle" }) {
            LifestyleDetails()
        }
    }
}

@Composable
fun ParameterCard(title: String, subtitle: String, icon: ImageVector, isExpanded: Boolean, onClick: () -> Unit, content: @Composable () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clickable(onClick = onClick)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null)
                    Spacer(modifier = Modifier.size(16.dp))
                    Column {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(subtitle, color = Color.Gray)
                    }
                }
                Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null)
            }
            if (isExpanded) {
                content()
            }
        }
    }
}

@Composable
fun VitalSignsDetails() {
    Column(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        VitalSignItem("Systolic BP", "128", "mmHg", "Ref: < 120", "BORDERLINE")
        VitalSignItem("Diastolic BP", "82", "mmHg", "Ref: < 80", "BORDERLINE")
        VitalSignItem("Heart Rate", "76", "bpm", "Ref: 60-100", "NORMAL")
        VitalSignItem("Temperature", "98.4", "°F", "Ref: 97-99", "NORMAL")
        VitalSignItem("Respiratory Rate", "16", "bpm", "Ref: 12-20", "NORMAL")
        VitalSignItem("SpO2", "97", "%", "Ref: 95-100", "NORMAL")
    }
}

@Composable
fun BloodTestDetails() {
    Column(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        VitalSignItem("Hemoglobin", "13.2", "g/dL", "Ref: 13.5-17.5", "BORDERLINE")
        VitalSignItem("Hematocrit", "40.5", "%", "Ref: 41-50", "BORDERLINE")
        VitalSignItem("RBC Count", "4.6", "M/µL", "Ref: 4.5-5.9", "NORMAL")
        VitalSignItem("WBC Count", "7800", "/µL", "Ref: 4500-11000", "NORMAL")
        VitalSignItem("Platelets", "228000", "/µL", "Ref: 150k-450k", "NORMAL")
    }
}

@Composable
fun LiverAndKidneyDetails() {
    Column(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        VitalSignItem("ALT", "42", "U/L", "Ref: 7-56", "NORMAL")
        VitalSignItem("AST", "35", "U/L", "Ref: 10-40", "NORMAL")
        VitalSignItem("ALP", "78", "U/L", "Ref: 44-147", "NORMAL")
        VitalSignItem("GGT", "52", "U/L", "Ref: 9-48", "BORDERLINE")
        VitalSignItem("Bilirubin", "0.9", "mg/dL", "Ref: 0.1-1.2", "NORMAL")
        VitalSignItem("Albumin", "4.1", "g/dL", "Ref: 3.4-5.4", "NORMAL")
        VitalSignItem("Creatinine", "1.2", "mg/dL", "Ref: 0.7-1.3", "NORMAL")
        VitalSignItem("BUN", "18", "mg/dL", "Ref: 6-20", "NORMAL")
        VitalSignItem("eGFR", "82", "mL/min", "Ref: > 90", "BORDERLINE")
        VitalSignItem("Uric Acid", "7.1", "mg/dL", "Ref: 3.5-7.2", "NORMAL")
    }
}

@Composable
fun HormonalPanelDetails() {
    Column(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        VitalSignItem("TSH", "4.2", "mIU/L", "Ref: 0.4-4.0", "BORDERLINE")
        VitalSignItem("Free T3", "2.8", "pg/mL", "Ref: 2.3-4.2", "NORMAL")
        VitalSignItem("Free T4", "1.1", "ng/dL", "Ref: 0.8-1.8", "NORMAL")
        VitalSignItem("Vitamin D", "16", "ng/mL", "Ref: 30-100", "ABNORMAL")
        VitalSignItem("Vitamin B12", "310", "pg/mL", "Ref: 200-900", "NORMAL")
        VitalSignItem("Ferritin", "28", "ng/mL", "Ref: 30-400", "BORDERLINE")
        VitalSignItem("Calcium", "9.0", "mg/dL", "Ref: 8.5-10.5", "NORMAL")
    }
}

@Composable
fun NeurologicalDetails() {
    Column(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        VitalSignItem("MMSE Score", "28", "/30", "Ref: > 24", "NORMAL")
        VitalSignItem("PHQ-9", "6", "/27", "Ref: < 5", "BORDERLINE")
        VitalSignItem("GAD-7", "4", "/21", "Ref: < 5", "NORMAL")
    }
}

@Composable
fun LifestyleDetails() {
    Column(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        VitalSignItem("Height", "170", "cm", "Ref: -", "NORMAL")
        VitalSignItem("Weight", "82", "kg", "Ref: -", "NORMAL")
        VitalSignItem("BMI", "28.4", "kg/m²", "Ref: 18.5-24.9", "BORDERLINE")
        VitalSignItem("Waist Circ.", "96", "cm", "Ref: < 94", "BORDERLINE")
    }
}

@Composable
fun VitalSignItem(name: String, value: String, unit: String, ref: String, status: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(name)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(value, fontWeight = FontWeight.Bold)
            Text(unit, color = Color.Gray)
        }
        Text(ref, color = Color.Gray)
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = when (status) {
                "BORDERLINE" -> BorderlineYellow
                "ABNORMAL" -> AbnormalRed
                else -> LightMint
            })
        ) {
            Text(status, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), color = when (status) {
                "BORDERLINE" -> Color.Black
                "ABNORMAL" -> Color.Red
                else -> Color.Green
            })
        }
    }
}


@Composable
fun DeeperRiskPredictionSection() {
    Column {
        Text("Deeper Risk Predictions", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        RiskPredictionCard("Diabetes", "High Risk", "92% Accuracy", 0.8f, Color.Red)
        RiskPredictionCard("Type 2 Diabetes", "High Risk", "92% Accuracy", 0.8f, Color.Red)
        RiskPredictionCard("Anemia", "High Risk", "88% Accuracy", 0.6f, Color.Red)
        RiskPredictionCard("Heart Attack", "Medium Risk", "74% Accuracy", 0.4f, Color.Yellow)
        RiskPredictionCard("Coronary Artery Disease", "Low Risk", "60% Accuracy", 0.1f, Color.Green)
        RiskPredictionCard("Hypothyroid", "Low Risk", "54% Accuracy", 0.2f, Color.Green)
    }
}

@Composable
fun RiskPredictionCard(title: String, risk: String, accuracy: String, progress: Float, color: Color) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(risk, color = color, fontWeight = FontWeight.Bold)
            }
            LinearProgressIndicator(progress = { progress }, color = color, modifier = Modifier.fillMaxWidth())
            Text(accuracy, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun AiAnalysisInsightsSection() {
    Column {
        Text("AI Analysis & Insights", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Fasting blood glucose 114mg/dL and HbA1c 5.8% indicates Prediabetes, increasing Type 2 Diabetes risk to 65%.", modifier = Modifier.padding(bottom = 8.dp))
                Text("LDL cholesterol 120 mg/dL and triglycerides 160 mg/dL signal a moderate dyslipidemia risk, which combined with existing hypertension, may elevate future cardiovascular event possibilities to 35%.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Anemia (7 g/dL) with pale skin points to Iron deficiency Anemia. A diet rich in iron and Vitamin C may improve the condition.", modifier = Modifier.padding(bottom = 8.dp))
                Text("RBC of 3.5 with pale skin points to Iron deficiency Anemia. A diet rich in iron and Vitamin C may improve the condition.", modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}

@Composable
fun PremiumDiagnosticTestsSection() {
    Column {
        Text("Premium Diagnostic Tests", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Post Prandial Glucose Test (PPG) to evaluate prediabetes status.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Lipid and Glucose Test (PPG) to evaluate prediabetes status.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Thyroid Test (TSH) to evaluate prediabetes status.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Vitamin D test after 3 months of supplementation.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Carotid Doppler ultrasound for stenosis assessment.", modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}

@Composable
fun HealthMonitoringServiceSection() {
    Column {
        Text("Health Monitoring Service", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("One-on-One fasting blood glucose reading for 4 weeks", modifier = Modifier.padding(bottom = 8.dp))
                Text("Track blood pressure once daily, morning and night, to check the efficacy of the current medication.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Alternate day sleep tracking on smartwatch/band and manual notings.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Ambulatory ECG test for 2 weeks.", modifier = Modifier.padding(bottom = 8.dp))
                Text("Regular Ultrasound test for 4 weeks.", modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrediagnosisReportScreenPreview() {
    PrediagnosticTheme {
        PrediagnosisReportScreen(onNavigateBack = {}, onDownloadReportClicked = {}, onBookConsultClicked = {})
    }
}
