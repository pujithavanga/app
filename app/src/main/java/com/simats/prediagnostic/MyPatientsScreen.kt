package com.simats.prediagnostic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPatientsScreen(onPatientClicked: () -> Unit) {
    Scaffold {
        padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            item {
                Column(
                    modifier = Modifier
                        .background(Color(0xFF2C3E50)) // Dark blueish gray
                        .padding(16.dp)
                ) {
                    MyPatientsTopBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    FilterSection()
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    SearchBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("6 Total Patients", fontWeight = FontWeight.Bold)
                        TextButton(onClick = { /*TODO*/ }) {
                            Text("Sort by Name")
                        }
                    }
                }
            }

            val patients = getPatients()
            items(patients) { patient ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    PatientItem(patient, onPatientClicked)
                }
            }
        }
    }
}

@Composable
private fun MyPatientsTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("My Patients", fontWeight = FontWeight.Bold, color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Text("Patient directory and records", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
        }
        IconButton(onClick = { /* TODO */ }) {
            Icon(Icons.Default.People, contentDescription = "My Patients", tint = Color.White)
        }
    }
}

@Composable
private fun FilterSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip("All", "6", true)
        FilterChip("Active", "4", color = MaterialTheme.colorScheme.primary)
        FilterChip("Critical", "1", color = Color(0xFFC62828))
        FilterChip("Stable", "1", color = Color(0xFF2E7D32))
    }
}

@Composable
private fun RowScope.FilterChip(label: String, count: String, selected: Boolean = false, color: Color = MaterialTheme.colorScheme.primary) {
    val containerColor = if (selected) Color.White else color
    val contentColor = if (selected) Color.Black else Color.White
    Card(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = if (selected) BorderStroke(1.dp, Color.White) else null
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(count, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = contentColor)
            Text(label, fontSize = 12.sp, color = contentColor)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Search patients...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

private fun getPatients() = listOf(
    Patient("P001", "Rahul Sharma", "45 years • Male", "+91 98765 43210", "Mumbai, Maharashtra", "Last visit: Jan 22, 2024", "2 active cases", "Active", Color(0xFFE57373)),
    Patient("P002", "Priya Patel", "38 years • Female", "+91 98765 43211", "Ahmedabad, Gujarat", "Last visit: Jan 22, 2024", "1 active case", "Active", Color(0xFFFFB74D)),
    Patient("P003", "Amit Kumar", "62 years • Male", "+91 98765 43212", "Delhi, NCR", "Last visit: Jan 21, 2024", "0 active cases", "Stable", Color(0xFF81C784)),
    Patient("P004", "Sneha Reddy", "29 years • Female", "+91 98765 43213", "Hyderabad, Telangana", "Last visit: Jan 21, 2024", "1 active case", "Active", Color(0xFFFFB74D)),
    Patient("P005", "Vikram Singh", "61 years • Male", "+91 98765 43214", "Jaipur, Rajasthan", "Last visit: Jan 20, 2024", "3 active cases", "Critical", Color(0xFFE57373)),
    Patient("P006", "Ananya Desai", "34 years • Female", "+91 98765 43215", "Pune, Maharashtra", "Last visit: Jan 20, 2024", "1 active case", "Active", Color(0xFFFFB74D))
)

@Composable
private fun PatientItem(patient: Patient, onPatientClicked: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onPatientClicked() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, patient.color.copy(alpha = 0.5f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(patient.color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = "Patient", tint = patient.color)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                    Text(patient.id, fontSize = 12.sp, color = Color.Gray)
                    StatusTag(patient.status)
                }
                Text(patient.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Text(patient.details, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Phone, contentDescription = "Phone", modifier = Modifier.size(14.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(patient.phone, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Place, contentDescription = "Location", modifier = Modifier.size(14.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(patient.location, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(patient.lastVisit, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    Text(patient.cases, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun StatusTag(status: String) {
    val color = when (status) {
        "Critical" -> Color(0xFFC62828)
        "Active" -> MaterialTheme.colorScheme.primary
        else -> Color(0xFF2E7D32)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(status, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

data class Patient(
    val id: String,
    val name: String,
    val details: String,
    val phone: String,
    val location: String,
    val lastVisit: String,
    val cases: String,
    val status: String,
    val color: Color
)

@Preview(showBackground = true)
@Composable
fun MyPatientsScreenPreview() {
    PrediagnosticTheme {
        MyPatientsScreen(onPatientClicked = {})
    }
}
