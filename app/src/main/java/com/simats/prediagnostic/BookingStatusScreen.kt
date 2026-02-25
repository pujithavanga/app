package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingStatusScreen(onNavigateBack: () -> Unit, doctorName: String, onViewAppointmentsClicked: () -> Unit, onBackToHomeClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Status") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = "Success", tint = Teal)
            Text("Booking Confirmed!", style = MaterialTheme.typography.titleLarge)
            Text("Your appointment has been scheduled successfully.")
            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Status")
                        Text("Confirmed", color = Color.Green)
                    }
                    Text(doctorName, fontWeight = FontWeight.Bold)
                    Text("Cardiologist")
                    Text("Metro General Hospital")
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Text("Date: Jan 15, 2026")
                        Text("Time: 10:00 AM")
                    }
                    Text("Video consultation via App")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Appointment Progress")
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        ProgressItem("Booked", true)
                        ProgressItem("Confirmed", true)
                        ProgressItem("In Progress", false)
                        ProgressItem("Completed", false)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onViewAppointmentsClicked, modifier = Modifier.fillMaxWidth()) {
                Text("View My Appointments")
            }
            OutlinedButton(onClick = onBackToHomeClicked, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Home")
            }
        }
    }
}

@Composable
fun ProgressItem(text: String, isComplete: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(if (isComplete) Icons.Filled.Circle else Icons.Outlined.Circle, contentDescription = null, tint = if (isComplete) Teal else Color.Gray)
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun BookingStatusScreenPreview() {
    PrediagnosticTheme {
        BookingStatusScreen(onNavigateBack = {}, doctorName = "Dr. Sarah Chen", onViewAppointmentsClicked = {}, onBackToHomeClicked = {})
    }
}
