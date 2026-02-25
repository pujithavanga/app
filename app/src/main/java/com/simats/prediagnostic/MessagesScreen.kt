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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

data class Conversation(
    val name: String,
    val specialty: String,
    val lastMessage: String,
    val timestamp: String,
    val unreadCount: Int,
    val icon: ImageVector,
    val iconBgColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    onNavigateBack: () -> Unit,
    onConversationClicked: (String) -> Unit
) {
    val conversations = remember {
        listOf(
            Conversation("AI Health Assistant", "Virtual Assistant", "Here is your current risk assessment ...", "Now", 0, Icons.AutoMirrored.Filled.Chat, Color(0xFFE0F7FA)),
            Conversation("Dr. Sarah Chen", "Cardiologist", "Can you tell me more about the ...", "09:31 AM", 2, Icons.Default.Favorite, Color(0xFFFCE4EC)),
            Conversation("Dr. David Kim", "Internal Medicine", "Your lab results look normal. Keep up...", "Yesterday", 0, Icons.Default.MedicalServices, Color(0xFFEDE7F6))
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Messages") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Search conversations...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(conversations) { conversation ->
                    ConversationCard(conversation = conversation, onClick = { onConversationClicked(conversation.name) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationCard(conversation: Conversation, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(conversation.iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(conversation.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(conversation.name, fontWeight = FontWeight.Bold)
                Text(conversation.specialty, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text(conversation.lastMessage, style = MaterialTheme.typography.bodyMedium)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(conversation.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                if (conversation.unreadCount > 0) {
                    BadgedBox(badge = { Badge { Text("${conversation.unreadCount}") } }) {}
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagesScreenPreview() {
    PrediagnosticTheme {
        MessagesScreen(onNavigateBack = {}, onConversationClicked = {})
    }
}
