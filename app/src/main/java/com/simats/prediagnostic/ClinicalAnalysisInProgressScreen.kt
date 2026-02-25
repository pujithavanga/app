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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal
import kotlinx.coroutines.delay

@Composable
fun ClinicalAnalysisInProgressScreen(onAnalysisComplete: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000) // Simulate analysis time
        onAnalysisComplete()
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Teal)
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            StethoscopeIcon(modifier = Modifier.size(128.dp), color = Color.White)
            Spacer(modifier = Modifier.height(32.dp))
            Text("Clinical Analysis in Progress", style = MaterialTheme.typography.headlineSmall, color = Color.White, fontWeight = FontWeight.Bold)
            Text("Processing your health data...", style = MaterialTheme.typography.bodyLarge, color = Color.White.copy(alpha = 0.8f))
            Spacer(modifier = Modifier.height(32.dp))
            AnalysisStep(text = "Processing symptom data", isCompleted = true)
            AnalysisStep(text = "Evaluating lifestyle report", isCompleted = true)
            AnalysisStep(text = "Patient recognition details", isCompleted = false)
            AnalysisStep(text = "Generating recommendations", isCompleted = false)
            AnalysisStep(text = "Analyzing with clinical process", isCompleted = false)
        }
    }
}

@Composable
private fun AnalysisStep(text: String, isCompleted: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(if (isCompleted) Color.White else Color.White.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Teal)
            }
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(text, color = if (isCompleted) Color.White else Color.White.copy(alpha = 0.8f))
    }
}

@Preview(showBackground = true)
@Composable
fun ClinicalAnalysisInProgressScreenPreview() {
    PrediagnosticTheme {
        ClinicalAnalysisInProgressScreen { }
    }
}
