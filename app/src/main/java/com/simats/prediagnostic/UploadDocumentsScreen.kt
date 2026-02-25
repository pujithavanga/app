package com.simats.prediagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadDocumentsScreen(onNavigateBack: () -> Unit, onContinueClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upload Documents") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Upload medical reports or test results")
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.PhotoCamera, contentDescription = "Take Photo")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Take Photo")
                }
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.CloudUpload, contentDescription = "Upload File")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Upload File")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row {
                OutlinedButton(onClick = onNavigateBack) {
                    Text("Skip")
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Button(onClick = onContinueClicked) {
                    Text("Continue")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadDocumentsScreenPreview() {
    PrediagnosticTheme {
        UploadDocumentsScreen(onNavigateBack = {}, onContinueClicked = {})
    }
}
