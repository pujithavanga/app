package com.simats.prediagnostic

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

data class PrediagnosisModule(
    val name: String,
    val conditions: Int,
    val icon: ImageVector,
    val backgroundColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrediagnosisModulesScreen(
    onNavigateBack: () -> Unit,
    onModuleClicked: (String) -> Unit,
    onViewRecommendationsClicked: () -> Unit
) {
    val modules = remember {
        listOf(
            PrediagnosisModule("Metabolic & Endocrine", 12, Icons.Default.Gavel, Color(0xFFE3F2FD)),
            PrediagnosisModule("Cardiovascular", 12, Icons.Default.Favorite, Color(0xFFFCE4EC)),
            PrediagnosisModule("Neurological & Mental", 12, Icons.Default.Psychology, Color(0xFFF3E5F5)),
            PrediagnosisModule("Cancer & Oncology", 12, Icons.Default.LocalFireDepartment, Color(0xFFFFF3E0)),
            PrediagnosisModule("Respiratory", 8, Icons.Default.BubbleChart, Color(0xFFE0F7FA)),
            PrediagnosisModule("Liver & GI", 8, Icons.Default.Grass, Color(0xFFEDE7F6)),
            PrediagnosisModule("Kidney & Urinary", 8, Icons.Default.WaterDrop, Color(0xFFE3F2FD)),
            PrediagnosisModule("Blood Disorders", 7, Icons.Default.BubbleChart, Color(0xFFFCE4EC)),
            PrediagnosisModule("Bone & Joint", 8, Icons.Default.Accessibility, Color(0xFFE8F5E9)),
            PrediagnosisModule("Autoimmune", 7, Icons.Default.AdminPanelSettings, Color(0xFFE3F2FD)),
            PrediagnosisModule("Infectious Disease", 6, Icons.Default.Coronavirus, Color(0xFFFFFDE7)),
            PrediagnosisModule("Skin & Dermatology", 5, Icons.Default.WbSunny, Color(0xFFFCE4EC))
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prediagnosis Modules") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Column {
                BottomSummary()
                Button(
                    onClick = onViewRecommendationsClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("View Recommendations")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                var searchQuery by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search categories...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("12 CATEGORIES", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(modules) { module ->
                ModuleCard(module = module, onClick = { onModuleClicked(module.name) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ModuleCard(module: PrediagnosisModule, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(module.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(module.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(module.name, fontWeight = FontWeight.Bold)
                Text("${module.conditions} conditions", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
        }
    }
}

@Composable
fun BottomSummary() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("109", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("MODULES", style = MaterialTheme.typography.labelSmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("13", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("CONDITIONS", style = MaterialTheme.typography.labelSmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("AI", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text("POWERED", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrediagnosisModulesScreenPreview() {
    PrediagnosticTheme {
        PrediagnosisModulesScreen(onNavigateBack = {}, onModuleClicked = {}, onViewRecommendationsClicked = {})
    }
}
