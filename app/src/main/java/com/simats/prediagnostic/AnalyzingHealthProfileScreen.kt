package com.simats.prediagnostic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme
import com.simats.prediagnostic.ui.theme.Teal
import kotlinx.coroutines.delay

@Composable
fun AnalyzingHealthProfileScreen(onAnalysisComplete: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    LaunchedEffect(Unit) {
        while (progress < 1f) {
            delay(500)
            progress += 0.1f
        }
        delay(1000) // Wait a bit after completion
        onAnalysisComplete()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A192E))
                .padding(paddingValues)
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.size(64.dp), color = Teal)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Analyzing Health Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cross-referencing 98 parameters against 109 clinical disease models...",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(32.dp))
            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier.fillMaxWidth(),
                color = Teal,
                trackColor = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${(animatedProgress * 100).toInt()}% COMPLETE",
                fontSize = 12.sp,
                color = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalyzingHealthProfileScreenPreview() {
    PrediagnosticTheme {
        AnalyzingHealthProfileScreen(onAnalysisComplete = {})
    }
}
