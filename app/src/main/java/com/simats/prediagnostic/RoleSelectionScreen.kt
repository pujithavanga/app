package com.simats.prediagnostic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@Composable
fun RoleSelectionScreen(onPatientClicked: () -> Unit, onDoctorClicked: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "How will you use PreDiagnosis AI?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Select your role to personalize your experience",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(32.dp))
            RoleCard(
                title = "I'm a Patient",
                description = "Screen symptoms, track health, get recommendations",
                icon = { modifier, color -> PersonIcon(modifier, color) },
                onClick = onPatientClicked
            )
            Spacer(modifier = Modifier.height(16.dp))
            RoleCard(
                title = "I'm a Doctor",
                description = "Manage patients, review screenings, collaborate",
                icon = { modifier, color -> StethoscopeIcon(modifier, color) },
                onClick = onDoctorClicked
            )
        }
    }
}

@Composable
fun RoleCard(
    title: String,
    description: String,
    icon: @Composable (Modifier, Color) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon(Modifier.size(48.dp), MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoleSelectionScreenPreview() {
    PrediagnosticTheme {
        RoleSelectionScreen(onPatientClicked = {}, onDoctorClicked = {})
    }
}
