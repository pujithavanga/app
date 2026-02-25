package com.simats.prediagnostic

import androidx.compose.runtime.mutableStateListOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Message(
    val text: String,
    val time: String,
    val isFromDoctor: Boolean
)

object ChatRepository {
    val messages = mutableStateListOf<Message>()

    fun sendMessage(text: String, isFromDoctor: Boolean) {
        if (text.isNotBlank()) {
            val time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
            messages.add(Message(text, time, isFromDoctor))
        }
    }

    init {
        if (messages.isEmpty()) {
            // Initial dummy messages for a conversation
            sendMessage("Hi Sarah, I've reviewed your latest screening results. The risk level is moderate, but I'd like to discuss a few symptoms.", true)
            sendMessage("Can you tell me more about the duration of the headaches?", true)
        }
    }
}
