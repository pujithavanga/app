
package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyAndConsentScreen(onNavigateBack: () -> Unit, onCompleteSetupClicked: () -> Unit) {
    val protectionPoints = remember {
        listOf(
            "End-to-end encryption for all health data",
            "HIPAA compliant data storage",
            "You control who sees your information",
            "Data never sold to third parties",
            "Right to delete your data anytime"
        )
    }
    val (agreeToTerms, setAgreeToTerms) = remember { mutableStateOf(false) }
    val (consentToData, setConsentToData) = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Privacy & Consent") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onCompleteSetupClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = agreeToTerms && consentToData
            ) {
                Text("Complete Setup", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle, // Placeholder icon
                contentDescription = "Privacy Shield",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(128.dp) // Placeholder size
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Your Data is Protected", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            protectionPoints.forEach { point ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Text(text = point, modifier = Modifier.padding(start = 8.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "By continuing, you agree to our Terms of Service and Privacy Policy. Your health data will be used to provide personalized screening recommendations and improve our AI models (anonymized).",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = agreeToTerms, onCheckedChange = setAgreeToTerms)
                Text("I agree to the Terms of Service and Privacy Policy")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = consentToData, onCheckedChange = setConsentToData)
                Text("I consent to my anonymized data being used to improve AI screening accuracy")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyAndConsentScreenPreview() {
    PrediagnosticTheme {
        PrivacyAndConsentScreen(onNavigateBack = {}, onCompleteSetupClicked = {})
    }
}
