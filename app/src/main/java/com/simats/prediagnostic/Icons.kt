package com.simats.prediagnostic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun StethoscopeIcon(modifier: Modifier = Modifier, color: Color = LocalContentColor.current) {
    Icon(
        imageVector = Icons.Filled.MedicalServices,
        contentDescription = "Stethoscope",
        modifier = modifier,
        tint = color
    )
}

@Composable
fun BrainIcon(modifier: Modifier = Modifier, color: Color = LocalContentColor.current) {
    Icon(
        imageVector = Icons.Filled.Psychology,
        contentDescription = "Brain",
        modifier = modifier,
        tint = color
    )
}

@Composable
fun ShieldIcon(modifier: Modifier = Modifier, color: Color = LocalContentColor.current) {
    Icon(
        imageVector = Icons.Filled.Shield,
        contentDescription = "Shield",
        modifier = modifier,
        tint = color
    )
}

@Composable
fun PersonIcon(modifier: Modifier = Modifier, color: Color = LocalContentColor.current) {
    Icon(
        imageVector = Icons.Filled.Person,
        contentDescription = "Person",
        modifier = modifier,
        tint = color
    )
}

@Composable
fun PhoneIcon(modifier: Modifier = Modifier, color: Color = LocalContentColor.current) {
    Icon(
        imageVector = Icons.Filled.Phone,
        contentDescription = "Phone",
        modifier = modifier,
        tint = color
    )
}

@Composable
fun HeartbeatIcon(modifier: Modifier = Modifier, color: Color = LocalContentColor.current) {
    Icon(
        imageVector = Icons.Filled.MonitorHeart,
        contentDescription = "Heartbeat",
        modifier = modifier,
        tint = color
    )
}
