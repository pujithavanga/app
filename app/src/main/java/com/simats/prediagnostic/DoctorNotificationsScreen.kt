package com.simats.prediagnostic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
fun DoctorNotificationsScreen(onNavigateBack: () -> Unit, onNotificationClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            FilterTabs()
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val notifications = getDoctorNotifications()
                items(notifications) { notification ->
                    NotificationItem(notification, onNotificationClicked)
                }
            }
        }
    }
}

@Composable
private fun FilterTabs() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("All (5)", "High (2)", "Medium (1)", "Low (1)")
    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, text ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = { Text(text) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Composable
private fun NotificationItem(notification: DoctorNotification, onNotificationClicked: () -> Unit) {
    val (icon, color) = when (notification.type) {
        "High Risk Case" -> Pair(Icons.Default.Error, Color.Red)
        "Medium Risk Case" -> Pair(Icons.Default.Warning, Color.Yellow)
        "Low Risk Case" -> Pair(Icons.Default.CheckCircle, Color.Green)
        else -> Pair(Icons.Default.Info, Color.Gray)
    }
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onNotificationClicked() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.5f)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(notification.type, fontWeight = FontWeight.Bold)
                    if (notification.risk != null) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(color.copy(alpha = 0.1f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(notification.risk, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Text(notification.message, style = MaterialTheme.typography.bodyMedium)
                Text(notification.time, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}

data class DoctorNotification(
    val type: String,
    val message: String,
    val time: String,
    val risk: String?
)

private fun getDoctorNotifications() = listOf(
    DoctorNotification("High Risk Case", "Patient Rahul Sharma - Blood smear analysis requires immediate review", "12 minutes ago", "High"),
    DoctorNotification("Medium Risk Case", "Patient Priya Patel - Tissue biopsy case assigned to you", "1 hour ago", "Medium"),
    DoctorNotification("Low Risk Case", "Patient Amit Kumar - Routine urine analysis completed", "2 hours ago", "Low"),
    DoctorNotification("High Risk Case", "Patient Vikram Singh - CT scan review pending", "8 hours ago", "High"),
    DoctorNotification("System Update", "New AI diagnostic features now available", "1 day ago", null)
)

@Preview(showBackground = true)
@Composable
fun DoctorNotificationsScreenPreview() {
    PrediagnosticTheme {
        DoctorNotificationsScreen(onNavigateBack = {}, onNotificationClicked = {})
    }
}
