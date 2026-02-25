
package com.simats.prediagnostic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.DarkBlue
import com.simats.prediagnostic.ui.theme.LightMint
import com.simats.prediagnostic.ui.theme.LightYellow
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadMedicalReportScreen(onNavigateBack: () -> Unit, onRunAnalysisClicked: () -> Unit) {
    var uploadSuccess by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health Screening") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (uploadSuccess) {
                Card(
                    modifier = Modifier.fillMaxWidth().clickable(onClick = onRunAnalysisClicked),
                    shape = RoundedCornerShape(0.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkBlue)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            BrainIcon(modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.size(8.dp))
                            Text("Run AI Analysis", color = Color.White)
                        }
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Go", tint = Color.White)
                    }
                }
            } else {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = false,
                        onClick = { }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Search, contentDescription = "Screening") },
                        label = { Text("Screening") },
                        selected = true,
                        onClick = { }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.History, contentDescription = "History") },
                        label = { Text("History") },
                        selected = false,
                        onClick = { }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = false,
                        onClick = { }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Upload Medical Report", style = MaterialTheme.typography.titleLarge)
            Text("Upload your lab report and our AI will analyze all health parameters instantly.", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(32.dp))

            if (uploadSuccess) {
                UploadSuccessView()
            } else {
                UploadFileView(onUploadClicked = { uploadSuccess = true })
            }
        }
    }
}

@Composable
fun UploadFileView(onUploadClicked: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Teal, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = LightMint)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(Icons.Default.UploadFile, contentDescription = "Upload", tint = Teal, modifier = Modifier.size(48.dp))
                Text("Tap to Upload")
                Text("Support for PDF, JPG, PNG", style = MaterialTheme.typography.bodySmall)
                Button(onClick = onUploadClicked, colors = ButtonDefaults.buttonColors(containerColor = Teal)) {
                    Text("Browse Files")
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text("OR IMPORT FROM")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(onClick = { }) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Camera")
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("Camera")
            }
            OutlinedButton(onClick = { }) {
                Icon(Icons.Default.Cloud, contentDescription = "Cloud")
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("Cloud")
            }
        }
    }
}

@Composable
fun UploadSuccessView() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = LightMint)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Teal),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Success", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Column {
                        Text("Upload Successful", fontWeight = FontWeight.Bold)
                        Text("Lab_Report_Jun2024.pdf", style = MaterialTheme.typography.bodySmall)
                    }
                }
                TextButton(onClick = { /*TODO*/ }) {
                    Text("Replace")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Extraction Summary", fontWeight = FontWeight.Bold)
                    Text("100% Complete", color = Teal, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    SummaryItem("44", "Extracted", LightMint)
                    SummaryItem("7", "Validated", LightMint)
                    SummaryItem("0", "Missing", LightYellow)
                }
            }
        }
        Text("All parameters have been extracted from your report. Tap below to run AI analysis.", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
fun SummaryItem(count: String, label: String, color: Color) {
    Card(
        modifier = Modifier.size(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(count, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(label, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadMedicalReportScreenPreview() {
    PrediagnosticTheme {
        UploadMedicalReportScreen(onNavigateBack = {}, onRunAnalysisClicked = {})
    }
}
