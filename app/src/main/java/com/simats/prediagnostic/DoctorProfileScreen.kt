package com.simats.prediagnostic

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Cases
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorProfileScreen(
    onNavigateBack: () -> Unit,
    onEditProfileClicked: () -> Unit,
    onPrivacyAndDataClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onCasesClicked: () -> Unit,
    onPatientsClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(icon = { Icon(Icons.Default.Home, contentDescription = "Home") }, label = { Text("Home") }, selected = false, onClick = onHomeClicked)
                NavigationBarItem(icon = { Icon(Icons.Default.Cases, contentDescription = "Cases") }, label = { Text("Cases") }, selected = false, onClick = onCasesClicked)
                NavigationBarItem(icon = { Icon(Icons.Default.People, contentDescription = "Patients") }, label = { Text("Patients") }, selected = false, onClick = onPatientsClicked)
                NavigationBarItem(icon = { Icon(Icons.Default.Person, contentDescription = "Profile") }, label = { Text("Profile") }, selected = true, onClick = {})
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { DoctorProfileHeader(onEditProfileClicked) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { ProfessionalInformation() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { Statistics() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { AccountSettings(onPrivacyAndDataClicked, onSettingsClicked, onLogoutClicked) }
        }
    }
}

@Composable
private fun DoctorProfileHeader(onEditProfileClicked: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(
            Icons.Default.Person,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
        )
        Text("Guest User", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Pathology", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        OutlinedButton(onClick = onEditProfileClicked) {
            Text("Edit Profile")
        }
    }
}

@Composable
private fun ProfessionalInformation() {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Professional Information", fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.Email, label = "Email", value = "user@email.com")
        InfoRow(icon = Icons.Default.Phone, label = "Phone", value = "+1 (555) 000-0000")
        InfoRow(icon = Icons.Default.Cases, label = "Specialty", value = "Pathology")
        InfoRow(icon = Icons.Default.AdminPanelSettings, label = "License", value = "MD-123456")
        InfoRow(icon = Icons.Default.Home, label = "Hospital", value = "Metro General Hospital")
        InfoRow(icon = Icons.Default.Cases, label = "Experience", value = "12 years")
    }
}

@Composable
private fun Statistics() {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Statistics", fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Card(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("2,450+", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text("Cases Completed")
                }
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Card(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("98%", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Color.Green)
                    Text("Accuracy Rate")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorProfileScreenPreview() {
    PrediagnosticTheme {
        DoctorProfileScreen({}, {}, {}, {}, {}, {}, {}, {})
    }
}
