package com.simats.prediagnostic

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class Sender {
    USER, AI
}

data class ChatMessage(
    val message: String,
    val sender: Sender,
    val timestamp: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiHealthAssistantScreen(onNavigateBack: () -> Unit) {
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                "Hello Sarah! I'm your AI Health Assistant. I see your current risk level is assessed as medium. How can I help you today?",
                Sender.AI,
                "12:01 AM"
            )
        )
    }
    val suggestions = remember { listOf("Get Prescription", "Check Risk Level", "Symptom Analysis") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Health Assistant") },
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
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { message ->
                    MessageBubble(message)
                }
            }

            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                suggestions.forEach { suggestion ->
                    SuggestionChip(
                        onClick = {
                            messages.add(
                                ChatMessage(
                                    suggestion,
                                    Sender.USER,
                                    SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
                                )
                            )
                            coroutineScope.launch {
                                delay(1000)
                                messages.add(getAiResponse(suggestion))
                                listState.animateScrollToItem(messages.size - 1)
                            }
                        },
                        label = { Text(suggestion) }
                    )
                }
            }

            MessageInput(onSendMessage = { messageText ->
                messages.add(
                    ChatMessage(
                        messageText,
                        Sender.USER,
                        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
                    )
                )
                coroutineScope.launch {
                    delay(1000)
                    messages.add(getAiResponse(messageText))
                    listState.animateScrollToItem(messages.size - 1)
                }
            })
        }
    }
    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size - 1)
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.sender == Sender.USER) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(if (message.sender == Sender.USER) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surfaceVariant)
                .padding(12.dp)
        ) {
            Column {
                Text(message.message)
                Text(
                    message.timestamp,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun MessageInput(onSendMessage: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Type a message...") },
            shape = RoundedCornerShape(24.dp)
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        IconButton(
            onClick = {
                if (text.isNotBlank()) {
                    onSendMessage(text)
                    text = ""
                }
            },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Teal)
        ) {
            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = Color.White)
        }
    }
}

fun getAiResponse(message: String): ChatMessage {
    val response = when {
        "prescription" in message.lowercase() -> "I can help with that. Please confirm your pharmacy details."
        "risk level" in message.lowercase() -> "Your current risk level is assessed as medium. This is based on your latest health screening results."
        "symptom" in message.lowercase() -> "I can help with that. Please describe your symptoms in detail."
        else -> "I'm sorry, I can only provide information on a limited set of topics. Please try one of the suggestions."
    }
    return ChatMessage(
        response,
        Sender.AI,
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
    )
}

@Preview(showBackground = true)
@Composable
fun AiHealthAssistantScreenPreview() {
    PrediagnosticTheme {
        AiHealthAssistantScreen(onNavigateBack = {})
    }
}
