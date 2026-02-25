package com.simats.prediagnostic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.DarkBlue
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@Composable
fun DoctorHomeScreen(
    onHomeClicked: () -> Unit,
    onCasesClicked: () -> Unit,
    onPatientsClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onNewAnalysisClicked: () -> Unit,
    onPatientChatClicked: (String) -> Unit,
    onPriorityCaseClicked: () -> Unit,
    onNotificationsClicked: () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = onHomeClicked
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Cases") },
                    label = { Text("Cases") },
                    selected = false,
                    onClick = onCasesClicked
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.People, contentDescription = "Patients") },
                    label = { Text("Patients") },
                    selected = false,
                    onClick = onPatientsClicked
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = onProfileClicked
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(padding)
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "Good morning,", color = Color.White.copy(alpha = 0.8f))
                            Text(text = "Dr. Doctor", style = MaterialTheme.typography.headlineMedium, color = Color.White, fontWeight = FontWeight.Bold)
                            Text(text = "Pathology Department", color = Color.White.copy(alpha = 0.8f))
                        }
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.White, modifier = Modifier.clickable(onClick = onNotificationsClicked))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Cases by Risk Level", color = Color.White, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        RiskLevelCard("High Risk", "3", Color.Red, Icons.Default.Warning)
                        RiskLevelCard("Medium Risk", "5", Color.Yellow, Icons.Default.Warning)
                        RiskLevelCard("Low Risk", "8", Color.Green, Icons.Default.Warning)
                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                            ActionCard("New Analysis", "Upload medical images", Icons.Default.ArrowUpward, Color(0xFFE3F2FD), onClick = onNewAnalysisClicked)
                            ActionCard("Patient Chat", "Messages & Consults", Icons.Default.ChatBubble, Color(0xFFE8F5E9), onClick = { onPatientChatClicked("Sarah Johnson") })
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                           Text(text = "Priority Cases", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                           Text(text = "View All", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        PriorityCaseCard("Rahul Sharma", "Blood Smear Analysis", "High Risk", Color(0xFFFCE4EC), "ID: 102", "Jan 22, 2024", "Pending Review", onClick = onPriorityCaseClicked)
                        Spacer(modifier = Modifier.height(16.dp))
                        PriorityCaseCard("Priya Patel", "Tissue Biopsy", "Medium Risk", Color(0xFFFFF8E1), "ID: 125", "Jan 22, 2024", "In Progress", onClick = onPriorityCaseClicked)
                        Spacer(modifier = Modifier.height(16.dp))
                        PriorityCaseCard("Amit Kumar", "Urine Analysis", "Low Risk", Color(0xFFE8F5E9), "ID: 240", "Jan 21, 2024", "Completed", onClick = onPriorityCaseClicked)
                        Spacer(modifier = Modifier.height(24.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF3F51B5))
                        ) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = "AI Diagnostic Assistant", fontWeight = FontWeight.Bold, color = Color.White)
                                    Text(text = "Get AI-powered insights and heatmap analysis for faster diagnosis", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                                }
                                Icon(Icons.Default.ArrowUpward, contentDescription = null, tint = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RiskLevelCard(level: String, count: String, color: Color, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(color.copy(alpha=0.2f)), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
        }
        Text(count, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(level, color = Color.White.copy(alpha=0.8f), fontSize = 12.sp)
    }
}

@Composable
fun ActionCard(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.size(150.dp, 120.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun PriorityCaseCard(name: String, analysis: String, risk: String, riskColor: Color, id: String, date: String, status: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = id, fontSize = 12.sp, color = Color.Gray)
                Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(riskColor).padding(horizontal = 8.dp, vertical = 4.dp)) {
                    Text(text = risk, fontWeight = FontWeight.Bold, color = if (risk == "High Risk") Color.Red else if (risk == "Medium Risk") Color.Yellow else Color.Green, fontSize = 12.sp)
                }
            }
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = analysis, color = Color.Gray, fontSize = 14.sp)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = date, fontSize = 12.sp, color = Color.Gray)
                Text(text = status, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorHomeScreenPreview() {
    PrediagnosticTheme {
        DoctorHomeScreen({}, {}, {}, {}, {}, {}, {}, {})
    }
}
