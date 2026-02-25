package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.LightMint
import com.simats.prediagnostic.ui.theme.LightYellow
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppointmentsScreen(onNavigateBack: () -> Unit) {
    val allAppointments = listOf(
        Appointment("Dr. Sarah Chen", "Cardiologist", "Jan 23, 2026", "10:00 AM", AppointmentStatus.UPCOMING, 1f, true, true, icon = { HeartbeatIcon() }),
        Appointment("Dr. David Kim", "Internal Medicine", "Feb 02, 2026", "02:30 PM", AppointmentStatus.UPCOMING, 0.5f, false, false, icon = { StethoscopeIcon() }),
        Appointment("Dr. Sarah Chen", "Cardiologist", "Jan 15, 2026", "10:00 AM", AppointmentStatus.PAST, 1f, true, true, icon = { HeartbeatIcon() }),
        Appointment("Dr. Sarah Chen", "Cardiologist", "Jan 15, 2026", "10:00 AM", AppointmentStatus.CANCELLED, 1f, true, true, icon = { HeartbeatIcon() }),
    )

    var selectedTab by remember { mutableStateOf(AppointmentStatus.UPCOMING) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Appointments") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                FilterChip(selected = selectedTab == AppointmentStatus.UPCOMING, onClick = { selectedTab = AppointmentStatus.UPCOMING }, label = { Text("Upcoming") })
                FilterChip(selected = selectedTab == AppointmentStatus.PAST, onClick = { selectedTab = AppointmentStatus.PAST }, label = { Text("Past") })
                FilterChip(selected = selectedTab == AppointmentStatus.CANCELLED, onClick = { selectedTab = AppointmentStatus.CANCELLED }, label = { Text("Cancelled") })
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(allAppointments.filter { it.status == selectedTab }) {
                    AppointmentCard(it)
                }
            }
        }
    }
}

@Composable
fun AppointmentCard(appointment: Appointment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = if (appointment.status == AppointmentStatus.UPCOMING && !appointment.isConfirmed) LightYellow else LightMint)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(appointment.doctorName, fontWeight = FontWeight.Bold)
                    Text(appointment.specialty, style = MaterialTheme.typography.bodySmall)
                }
                Text(if (appointment.isVideo) "Video" else "In-Person", style = MaterialTheme.typography.bodySmall)
            }
            Text("${appointment.date}  ${appointment.time}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(if (appointment.isConfirmed) "Confirmed" else "Pending", fontWeight = FontWeight.Bold)
            LinearProgressIndicator(progress = { appointment.progress }, modifier = Modifier.fillMaxWidth())
            if (appointment.isConfirmed) {
                Text("Your appointment is confirmed", style = MaterialTheme.typography.bodySmall)
            } else {
                Text("Awaiting doctor confirmation", style = MaterialTheme.typography.bodySmall)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppointmentsScreenPreview() {
    PrediagnosticTheme {
        MyAppointmentsScreen(onNavigateBack = {})
    }
}
