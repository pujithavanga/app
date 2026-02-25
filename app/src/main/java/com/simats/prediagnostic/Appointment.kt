package com.simats.prediagnostic

import androidx.compose.runtime.Composable

enum class AppointmentStatus { UPCOMING, PAST, CANCELLED }

data class Appointment(
    val doctorName: String,
    val specialty: String,
    val date: String,
    val time: String,
    val status: AppointmentStatus,
    val progress: Float,
    val isVideo: Boolean,
    val isConfirmed: Boolean,
    val riskLevel: String? = null,
    val icon: @Composable () -> Unit
)
