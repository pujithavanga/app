package com.simats.prediagnostic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(onNavigateBack: () -> Unit, onSaveChanges: () -> Unit) {
    var fullName by remember { mutableStateOf("Sarah Johnson") }
    var email by remember { mutableStateOf("sarah.j@email.com") }
    var phone by remember { mutableStateOf("+1 (555) 123-4567") }
    var dob by remember { mutableStateOf("13/03/1984") }
    var gender by remember { mutableStateOf("Female") }
    var emergencyContact by remember { mutableStateOf("+1 (555) 987-6543") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                )
            }
            item { OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth()) }
            item { OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth()) }
            item { OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth()) }
            item { OutlinedTextField(value = dob, onValueChange = { dob = it }, label = { Text("Date of Birth") }, modifier = Modifier.fillMaxWidth()) }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Gender", fontWeight = FontWeight.Medium)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip(selected = gender == "Male", onClick = { gender = "Male" }, label = { Text("Male") })
                        FilterChip(selected = gender == "Female", onClick = { gender = "Female" }, label = { Text("Female") })
                        FilterChip(selected = gender == "Other", onClick = { gender = "Other" }, label = { Text("Other") })
                    }
                }
            }
            item { OutlinedTextField(value = emergencyContact, onValueChange = { emergencyContact = it }, label = { Text("Emergency Contact") }, modifier = Modifier.fillMaxWidth()) }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = onNavigateBack, modifier = Modifier.weight(1f)) { Text("Cancel") }
                    Button(onClick = onSaveChanges, modifier = Modifier.weight(1f)) { Text("Save Changes") }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    PrediagnosticTheme {
        EditProfileScreen(onNavigateBack = {}, onSaveChanges = {})
    }
}
