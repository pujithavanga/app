package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal

data class Date(val day: String, val date: String, val month: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAppointmentScreen(onNavigateBack: () -> Unit, doctorName: String, onConfirmBookingClicked: () -> Unit) {
    val dates = listOf(
        Date("Mon", "13", "Jan"),
        Date("Tue", "14", "Jan"),
        Date("Wed", "15", "Jan"),
        Date("Thu", "16", "Jan"),
        Date("Fri", "17", "Jan")
    )
    val times = listOf("9:00 AM", "10:00 AM", "11:00 AM", "2:00 PM", "3:00 PM", "9:00 PM")

    var selectedDate by remember { mutableStateOf(dates[2]) }
    var selectedTime by remember { mutableStateOf(times[1]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Appointment") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, modifier = Modifier.weight(0.2f))
                    Column(modifier = Modifier.weight(0.8f)) {
                        Text(doctorName, fontWeight = FontWeight.Bold)
                        Text("Cardiologist", style = MaterialTheme.typography.bodySmall)
                        Row {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                            Text("4.8 / 128 reviews", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Select Date", style = MaterialTheme.typography.titleMedium)
            LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                items(dates) {
                    DateCard(date = it, isSelected = it == selectedDate, onClick = { selectedDate = it })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Select Time", style = MaterialTheme.typography.titleMedium)
            LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                items(times) {
                    TimeCard(time = it, isSelected = it == selectedTime, onClick = { selectedTime = it })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Icon(Icons.Default.CalendarToday, contentDescription = null)
                    Column {
                        Text("Selected Appointment")
                        Text("${selectedDate.month} ${selectedDate.date}, 2026 at $selectedTime", fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onConfirmBookingClicked, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Teal)) {
                Text("Confirm Booking")
            }
        }
    }
}

@Composable
fun DateCard(date: Date, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) Teal else Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(date.day, color = if (isSelected) Color.White else Color.Black)
            Text(date.date, fontWeight = FontWeight.Bold, color = if (isSelected) Color.White else Color.Black)
        }
    }
}

@Composable
fun TimeCard(time: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) Teal else Color.White)
    ) {
        Text(time, modifier = Modifier.padding(16.dp), color = if (isSelected) Color.White else Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun BookAppointmentScreenPreview() {
    PrediagnosticTheme {
        BookAppointmentScreen(onNavigateBack = {}, doctorName = "Dr. Sarah Chen", onConfirmBookingClicked = {})
    }
}
