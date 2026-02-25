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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.simats.prediagnostic.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreeningReportScreen(
    onNavigateBack: () -> Unit,
    onDownloadReportClicked: () -> Unit,
    onBookConsultClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Prediagnosis Report") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(onClick = onDownloadReportClicked, modifier = Modifier.weight(1f)) {
                    Text("Download Report")
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Button(onClick = onBookConsultClicked, modifier = Modifier.weight(1f)) {
                    Text("Book Consult")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            item { HealthSummary() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { ParameterAnalysis() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { CommonRiskPredictions() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { AiAnalysisInsights() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { ProactiveRecommendations() }
        }
    }
}

@Composable
private fun HealthSummary() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A192E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Health Screening", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("AI-Powered Analysis", color = Color.LightGray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Patient Name", color = Color.LightGray, fontSize = 12.sp)
                    Text("Rishab Kumar", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Age", color = Color.LightGray, fontSize = 12.sp)
                    Text("41 yrs Male", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Date", color = Color.LightGray, fontSize = 12.sp)
                    Text("Feb 8, 2024", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Ref ID", color = Color.LightGray, fontSize = 12.sp)
                    Text("SIM-PRED-20240208-1234", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun ParameterAnalysis() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Parameter Analysis", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            // This would be a section with expandable items, which is complex for a single file.
            // For now, we'll just show a placeholder.
            Text("Vital Signs, Blood Tests, etc.")
        }
    }
}

@Composable
private fun CommonRiskPredictions() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Common Risk Predictions", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            RiskPredictionItem("Diabetes", "High Risk", 0.85f, Color.Red)
            RiskPredictionItem("Type 2 Diabetes", "High Risk", 0.85f, Color.Red)
            RiskPredictionItem("Obesity", "High Risk", 0.85f, Color.Red)
            RiskPredictionItem("Heart Attack", "High Risk", 0.85f, Color.Red)
            RiskPredictionItem("Coronary Artery Disease", "High Risk", 0.70f, Color.Red)
            RiskPredictionItem("Prediabetes", "Medium Risk", 0.55f, Color.Yellow)
        }
    }
}

@Composable
private fun RiskPredictionItem(name: String, risk: String, value: Float, color: Color) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Info, contentDescription = null, tint = color)
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold)
            Text(risk, fontSize = 12.sp, color = color)
        }
        LinearProgressIndicator(progress = { value }, color = color)
    }
}

@Composable
private fun AiAnalysisInsights() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("AI Analysis Insights", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("• Elevated fasting glucose (114 mg/dL) and HbA1c (6.0%) indicate prediabetes or early Type 2 Diabetes. Risk Score: 0.85.")
            Spacer(modifier = Modifier.height(8.dp))
            Text("• LDL cholesterol (135 mg/dL) and triglycerides (160 mg/dL) are borderline high, indicating an elevated cardiometabolic risk.")
        }
    }
}

@Composable
private fun ProactiveRecommendations() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Proactive Recommendations", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Further Diagnostic Tests:", fontWeight = FontWeight.Medium)
            Text("• Post-prandial glucose test (PPG) to confirm pre-diabetes state.")
            Text("• Lipid sub-fraction test (eg. LDL-P, Lp(a)) for deeper cardiovascular risk profiling.")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Health Monitoring Advice:", fontWeight = FontWeight.Medium)
            Text("• Monitor fasting blood glucose weekly for 4 weeks.")
            Text("• Look into options for a continuous glucose monitor (CGM) to understand glucose patterns.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HealthScreeningReportScreenPreview() {
    PrediagnosticTheme {
        HealthScreeningReportScreen(onNavigateBack = {}, onDownloadReportClicked = {}, onBookConsultClicked = {})
    }
}
