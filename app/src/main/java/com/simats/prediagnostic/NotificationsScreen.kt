package com.simats.prediagnostic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

data class NotificationItem(
    val title: String,
    val description: String,
    val time: String,
    val icon: ImageVector,
    val iconColor: @Composable () -> Color,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onNavigateBack: () -> Unit,
    onScreeningClicked: (String) -> Unit,
    onHealthReminderClicked: () -> Unit
) {
    val notifications = remember {
        listOf(
            NotificationItem("Screening Complete", "Your cardiac screening results are ready", "2 hours ago", Icons.Default.Favorite, { Color.Red }) { onScreeningClicked("Cardiac Screening") },
            NotificationItem("Health Reminder", "Time for your monthly diabetes check", "6 hours ago", Icons.Default.Notifications, { MaterialTheme.colorScheme.primary }) { onHealthReminderClicked() },
            NotificationItem("Health Tip", "Stay hydrated! Aim for 8 glasses of water today.", "1 day ago", Icons.Default.Warning, { Color(0xFFFFA000) }) { }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(notifications) { notification ->
                NotificationCard(notification = notification)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { notification.onClick() }) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(notification.icon, contentDescription = null, tint = notification.iconColor(), modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(notification.title, fontWeight = FontWeight.Bold)
                Text(notification.description, style = MaterialTheme.typography.bodyMedium)
                Text(notification.time, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    PrediagnosticTheme {
        NotificationsScreen(onNavigateBack = {}, onScreeningClicked = {}, onHealthReminderClicked = {})
    }
}
