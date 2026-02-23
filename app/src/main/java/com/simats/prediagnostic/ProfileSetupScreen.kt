package com.simats.prediagnostic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(onNavigateBack: () -> Unit, onContinueClicked: () -> Unit) {
    var fullName by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var emergencyContact by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile Setup") },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                ) {
                    PersonIcon(Modifier.size(60.dp), color = MaterialTheme.colorScheme.primary)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            ProfileTextField(value = fullName, onValueChange = { fullName = it }, label = "Full Name")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTextField(value = dob, onValueChange = { dob = it }, label = "Date of Birth", placeholder = "DD/MM/YYYY")
            Spacer(modifier = Modifier.height(16.dp))
            GenderSelector(selectedGender = gender, onGenderSelected = { gender = it })
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTextField(value = phone, onValueChange = { phone = it }, label = "Phone Number", placeholder = "+1 (555) 000-0000")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTextField(value = emergencyContact, onValueChange = { emergencyContact = it }, label = "Emergency Contact", placeholder = "Emergency contact number")
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onContinueClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Continue", modifier = Modifier.padding(vertical = 8.dp), color = Color.White)
            }
        }
    }
}

@Composable
private fun ProfileTextField(value: String, onValueChange: (String) -> Unit, label: String, placeholder: String = "") {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
    }
}

@Composable
private fun GenderSelector(selectedGender: String, onGenderSelected: (String) -> Unit) {
    val genders = listOf("Male", "Female", "Other")
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Gender", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            genders.forEach { gender ->
                val isSelected = selectedGender == gender
                OutlinedButton(
                    onClick = { onGenderSelected(gender) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.White,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text(text = gender)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSetupScreenPreview() {
    PrediagnosticTheme {
        ProfileSetupScreen(onNavigateBack = {}, onContinueClicked = {})
    }
}
