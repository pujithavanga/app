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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateBack: () -> Unit) {
    var pushNotifications by remember { mutableStateOf(true) }
    var emailUpdates by remember { mutableStateOf(false) }
    var smsReminders by remember { mutableStateOf(true) }
    var healthTips by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
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
            item { Text("Notifications", fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { SwitchRow("Push Notifications", pushNotifications) { pushNotifications = it } }
            item { SwitchRow("Email Updates", emailUpdates) { emailUpdates = it } }
            item { SwitchRow("SMS Reminders", smsReminders) { smsReminders = it } }
            item { SwitchRow("Health Tips", healthTips) { healthTips = it } }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { Text("Preferences", fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { SettingsItem(label = "Language", value = "English", onClick = {}) }
            item { SettingsItem(label = "Theme", value = "Light", onClick = {}) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { Text("About", fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { SettingsItem(label = "App Version", value = "2.1.0", onClick = {}) }
            item { SettingsItem(label = "Terms of Service", onClick = {}) }
            item { SettingsItem(label = "Privacy Policy", onClick = {}) }
            item { SettingsItem(label = "Help & Support", onClick = {}) }
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

@Composable
private fun SettingsItem(label: String, value: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(value, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    PrediagnosticTheme {
        SettingsScreen(onNavigateBack = {})
    }
}
