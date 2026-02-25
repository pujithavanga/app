package com.simats.prediagnostic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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

data class Doctor(val name: String, val specialty: String, val experience: String, val rating: Float, val reviews: Int, val distance: Float, val isAvailable: Boolean, val icon: @Composable () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultDoctorScreen(onNavigateBack: () -> Unit, onDoctorClicked: (String) -> Unit) {
    val doctors = listOf(
        Doctor("Dr. Sarah Chen", "Cardiologist", "12 years experience", 4.8f, 123, 2.3f, true, { Icon(Icons.Default.FavoriteBorder, contentDescription = null) }),
        Doctor("Dr. Michael Roberts", "Internal Medicine", "8 years experience", 4.8f, 81, 3.1f, true, { HeartbeatIcon() }),
        Doctor("Dr. Emily Watson", "Cardiologist", "7-8 years experience", 4.7f, 158, 3.3f, false, { Icon(Icons.Default.FavoriteBorder, contentDescription = null) }),
        Doctor("Dr. James Park", "General Practitioner", "6-8 years experience", 4.9f, 210, 1.8f, true, { StethoscopeIcon() }),
        Doctor("Dr. Priya Sharma", "Neurologist", "9 years experience", 4.8f, 139, 2.8f, true, { BrainIcon() }),
        Doctor("Dr. David Kim", "Internal Medicine", "7 years experience", 4.6f, 112, 3.5f, true, { HeartbeatIcon() })
    )

    var searchQuery by remember { mutableStateOf("") }
    var selectedChip by remember { mutableStateOf("All") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consult Doctor") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search by name or specialty...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(horizontal = 8.dp)) {
                FilterChip(selected = selectedChip == "All", onClick = { selectedChip = "All" }, label = { Text("All") })
                FilterChip(selected = selectedChip == "Cardiologist", onClick = { selectedChip = "Cardiologist" }, label = { Text("Cardiologist") }, leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) })
                FilterChip(selected = selectedChip == "Internal Medicine", onClick = { selectedChip = "Internal Medicine" }, label = { Text("Internal Medicine") }, leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) })
            }
            Text("${doctors.size} doctors found", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(doctors) {
                    DoctorCard(it, onClick = { onDoctorClicked(it.name) })
                }
            }
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            doctor.icon()
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(doctor.name, fontWeight = FontWeight.Bold)
                Text(doctor.specialty, style = MaterialTheme.typography.bodySmall)
                Text(doctor.experience, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                    Text("${doctor.rating} (${doctor.reviews})", style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text("• ${doctor.distance} mi", style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(if (doctor.isAvailable) "Available" else "Busy", color = if (doctor.isAvailable) Color.Green else Color.Red)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ConsultDoctorScreenPreview() {
    PrediagnosticTheme {
        ConsultDoctorScreen(onNavigateBack = {}, onDoctorClicked = {})
    }
}
