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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onEditProfileClicked: () -> Unit,
    onPrivacyAndDataClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onLogoutClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { ProfileHeader(onEditProfileClicked) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { PersonalInformation() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { MedicalSummary() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { AccountSettings(onPrivacyAndDataClicked, onSettingsClicked, onLogoutClicked) }
    }
}

@Composable
fun ProfileHeader(onEditProfileClicked: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            Icons.Default.Person,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Guest User", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Patient since Jan 2025", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onEditProfileClicked) {
            Text("Edit Profile")
        }
    }
}

@Composable
fun PersonalInformation() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Personal Information", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(icon = Icons.Default.Email, label = "Email", value = "user@email.com")
            InfoRow(icon = Icons.Default.Phone, label = "Phone", value = "+1 (555) 000-0000")
            InfoRow(icon = Icons.Default.Cake, label = "Age", value = "62 years old")
            InfoRow(icon = Icons.Default.Person, label = "Gender", value = "Not specified")
        }
    }
}

@Composable
fun MedicalSummary() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE4EC))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Medical Summary", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(icon = Icons.Default.Favorite, label = "Chronic Conditions", value = "None reported")
            Text("Medications: None reported")
            Text("Allergies: None reported")
        }
    }
}

@Composable
fun AccountSettings(
    onPrivacyAndDataClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onLogoutClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Account Settings", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        SettingsItem(icon = Icons.Default.AdminPanelSettings, label = "Privacy & Data", onClick = onPrivacyAndDataClicked)
        SettingsItem(icon = Icons.Default.Settings, label = "Settings", onClick = onSettingsClicked)
        SettingsItem(icon = Icons.AutoMirrored.Filled.ExitToApp, label = "Log Out", onClick = onLogoutClicked)
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Column {
            Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text(value, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun SettingsItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clickable { onClick() }) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(label, modifier = Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PrediagnosticTheme {
        ProfileScreen({}, {}, {}, {})
    }
}
