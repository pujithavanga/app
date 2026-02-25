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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyAndDataScreen(onNavigateBack: () -> Unit) {
    var aiTrainingState by remember { mutableStateOf(true) }
    var researchState by remember { mutableStateOf(false) }
    var analyticsState by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Privacy & Data") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Shield, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        Column {
                            Text("Your Data is Protected", fontWeight = FontWeight.Bold)
                            Text("HIPAA compliant • End-to-end encrypted", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { Text("Data Usage", fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                SwitchRow("AI Model Training (Anonymized)", checked = aiTrainingState) { aiTrainingState = it }
            }
            item {
                SwitchRow("Research Contributions", checked = researchState) { researchState = it }
            }
            item {
                SwitchRow("Third-party Analytics", checked = analyticsState) { analyticsState = it }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { Text("Data Management", fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                Card(modifier = Modifier.fillMaxWidth().clickable { /* TODO */ }) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Download, contentDescription = null)
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Download My Data")
                            Text("Get a copy of all your data", style = MaterialTheme.typography.bodySmall)
                        }
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { /* TODO */ },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE4EC))
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Delete My Account", color = Color.Red)
                            Text("Permanently remove all data", style = MaterialTheme.typography.bodySmall, color = Color.Red.copy(alpha = 0.8f))
                        }
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
private fun SwitchRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyAndDataScreenPreview() {
    PrediagnosticTheme {
        PrivacyAndDataScreen(onNavigateBack = {})
    }
}
