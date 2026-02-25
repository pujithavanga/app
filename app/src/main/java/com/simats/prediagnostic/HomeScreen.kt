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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ControlPoint
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal

@Composable
fun HomeScreen(
    onHealthScreeningClicked: () -> Unit, 
    onMedicalScreeningClicked: () -> Unit, 
    onAiHealthChatClicked: () -> Unit, 
    onDoctorChatClicked: () -> Unit,
    onRecentActivityClicked: (String) -> Unit,
    onConsultDoctorClicked: () -> Unit,
    onNotificationsClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Welcome back, Guest", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Let's check on your health today", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = onNotificationsClicked) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                }
            }
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Teal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Your Health Score", color = Color.White, style = MaterialTheme.typography.bodyMedium)
                        Text(text = "78/100", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                        Text(text = "+2 from last month", color = Color.White, style = MaterialTheme.typography.bodySmall)
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
                    }
                }
            }
        }
        item {
            Text(text = "Quick Actions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                QuickActionCard("Health Screening", icon = { Icon(Icons.Default.Search, contentDescription = "Health Screening") }, onClick = onHealthScreeningClicked)
                QuickActionCard("Medical Screening", icon = { Icon(Icons.Default.PostAdd, contentDescription = "Medical Screening") }, onClick = onMedicalScreeningClicked)
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                QuickActionCard("AI Health Chat", icon = { Icon(Icons.Default.Forum, contentDescription = "AI Health Chat") }, onClick = onAiHealthChatClicked)
                QuickActionCard("Doctor Chat", icon = { Icon(Icons.Default.Forum, contentDescription = "Doctor Chat") }, onClick = onDoctorChatClicked)
            }
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth().clickable(onClick = onConsultDoctorClicked),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF59D)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Need a Consultation? Find and book appointments with specialists.", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth().clickable(onClick = onAiHealthChatClicked),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Forum, contentDescription = null, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = "AI Health Insight", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Based on your recent screenings, your cardiovascular health is improving. Tap to chat with AI for personalized advice.", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
        item {
            Text(text = "Recent Activity", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                RecentActivityItem("Diabetes Screening", "Jun 15, 2024", "Low Risk", Color(0xFFC8E6C9), icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = null) }, onClick = { onRecentActivityClicked("Diabetes Screening") })
                RecentActivityItem("Heart Disease Check", "Jun 12, 2024", "Medium Risk", Color(0xFFFFECB3), icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = null) }, onClick = { onRecentActivityClicked("Heart Disease Check") })
                RecentActivityItem("Thyroid Assessment", "Jun 10, 2024", "Normal", Color(0xFFB2EBF2), icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = null) }, onClick = { onRecentActivityClicked("Thyroid Assessment") })
            }
        }
        item {
                Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
                shape = RoundedCornerShape(16.dp)
            ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ControlPoint, contentDescription = null, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = "Daily Health Tip", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Stay hydrated! Drinking 8 glasses of water daily helps maintain optimal body function and supports your overall health.", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Composable
fun QuickActionCard(title: String, icon: @Composable () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier.size(140.dp, 100.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(32.dp)) {
                icon()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun RecentActivityItem(title: String, date: String, status: String, color: Color, icon: @Composable () -> Unit, onClick: () -> Unit) {
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                icon()
                Column {
                    Text(text = title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                    Text(text = date, style = MaterialTheme.typography.bodySmall)
                }
            }
            Text(text = status, color = color, fontWeight = FontWeight.Bold, modifier = Modifier.background(color.copy(alpha = 0.2f)).padding(horizontal = 8.dp, vertical = 4.dp).clip(RoundedCornerShape(8.dp)))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PrediagnosticTheme {
        HomeScreen(onHealthScreeningClicked = {}, onMedicalScreeningClicked = {}, onAiHealthChatClicked = {}, onDoctorChatClicked = {}, onRecentActivityClicked = {}, onConsultDoctorClicked = {}, onNotificationsClicked = {})
    }
}
