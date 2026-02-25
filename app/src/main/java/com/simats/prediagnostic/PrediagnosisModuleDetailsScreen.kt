package com.simats.prediagnostic

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

enum class RiskLevel(val displayName: String, val color: Color, val backgroundColor: Color) {
    HIGH_RISK("HIGH RISK", Color.White, Color(0xFFD32F2F)),
    MEDIUM_RISK("MEDIUM RISK", Color.Black, Color(0xFFFBC02D)),
    LOW_RISK("LOW RISK", Color.Black, Color(0xFF388E3C)),
    OPTIMAL_RISK("OPTIMAL RISK", Color.White, Color(0xFF1976D2))
}

data class Condition(
    val name: String,
    val params: Int,
    val accuracy: Int,
    val icon: ImageVector,
    val iconBackgroundColor: Color,
    val riskLevel: RiskLevel
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrediagnosisModuleDetailsScreen(
    moduleName: String,
    onNavigateBack: () -> Unit,
    onViewRecommendationsClicked: () -> Unit
) {
    val conditions = remember(moduleName) { getConditionsForModule(moduleName) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(moduleName) },
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
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
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
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Search conditions...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("${conditions.size} MODULES", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(conditions) { condition ->
                ConditionCard(condition)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ConditionCard(condition: Condition) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                    .background(condition.iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(condition.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(condition.name, fontWeight = FontWeight.Bold)
                Text(
                    "${condition.params} params • ${condition.accuracy}% accuracy",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            RiskTag(condition.riskLevel)
        }
    }
}

@Composable
fun RiskTag(riskLevel: RiskLevel) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(riskLevel.backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = riskLevel.displayName,
            color = riskLevel.color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun getConditionsForModule(moduleName: String): List<Condition> {
    val metabolicConditions = listOf(
        Condition("Diabetes", 12, 95, Icons.Default.Bloodtype, Color(0xFFE3F2FD), RiskLevel.HIGH_RISK),
        Condition("Prediabetes", 8, 82, Icons.Default.Opacity, Color(0xFFE3F2FD), RiskLevel.MEDIUM_RISK),
        Condition("Type 1 Diabetes", 12, 78, Icons.Default.Opacity, Color(0xFFE3F2FD), RiskLevel.LOW_RISK),
        Condition("Type 2 Diabetes", 12, 95, Icons.Default.Opacity, Color(0xFFE3F2FD), RiskLevel.HIGH_RISK),
        Condition("Gestational Diabetes", 8, 91, Icons.Default.PregnantWoman, Color(0xFFE3F2FD), RiskLevel.LOW_RISK),
        Condition("Insulin Resistance", 8, 82, Icons.Default.SyncProblem, Color(0xFFE3F2FD), RiskLevel.MEDIUM_RISK),
        Condition("Metabolic Syndrome", 11, 85, Icons.Default.Hub, Color(0xFFE3F2FD), RiskLevel.MEDIUM_RISK),
        Condition("Obesity", 7, 90, Icons.Default.NoMeals, Color(0xFFE3F2FD), RiskLevel.HIGH_RISK),
        Condition("Thyroid Disorders", 8, 91, Icons.Default.Assessment, Color(0xFFE3F2FD), RiskLevel.MEDIUM_RISK),
        Condition("Hypothyroidism", 8, 82, Icons.Default.Assessment, Color(0xFFE3F2FD), RiskLevel.MEDIUM_RISK),
        Condition("Hyperthyroidism", 8, 82, Icons.Default.Assessment, Color(0xFFE3F2FD), RiskLevel.LOW_RISK),
        Condition("PCOS", 12, 75, Icons.Default.Grain, Color(0xFFE3F2FD), RiskLevel.MEDIUM_RISK),
        Condition("Hormonal Imbalance", 12, 90, Icons.Default.Balance, Color(0xFFE3F2FD), RiskLevel.MEDIUM_RISK)
    )

    val cardiovascularConditions = listOf(
        Condition("Heart Attack", 15, 92, Icons.Default.Favorite, Color(0xFFFCE4EC), RiskLevel.HIGH_RISK),
        Condition("Coronary Artery Disease", 12, 88, Icons.Default.FavoriteBorder, Color(0xFFFCE4EC), RiskLevel.HIGH_RISK),
        Condition("Hypertension", 8, 94, Icons.AutoMirrored.Filled.ShowChart, Color(0xFFFCE4EC), RiskLevel.MEDIUM_RISK),
        Condition("Hypotension", 8, 89, Icons.AutoMirrored.Filled.TrendingDown, Color(0xFFFCE4EC), RiskLevel.LOW_RISK),
        Condition("Atherosclerosis", 12, 85, Icons.Default.HorizontalRule, Color(0xFFFCE4EC), RiskLevel.MEDIUM_RISK),
        Condition("Arrhythmia", 12, 88, Icons.Default.MonitorHeart, Color(0xFFFCE4EC), RiskLevel.LOW_RISK),
        Condition("Heart Failure", 15, 85, Icons.Default.HeartBroken, Color(0xFFFCE4EC), RiskLevel.MEDIUM_RISK),
        Condition("Cardiomyopathy", 15, 88, Icons.Default.Favorite, Color(0xFFFCE4EC), RiskLevel.LOW_RISK),
        Condition("Stroke", 15, 87, Icons.Default.Bolt, Color(0xFFFCE4EC), RiskLevel.OPTIMAL_RISK),
        Condition("Ischemic Stroke", 12, 88, Icons.Default.Bolt, Color(0xFFFCE4EC), RiskLevel.MEDIUM_RISK),
        Condition("Hemorrhagic Stroke", 12, 80, Icons.Default.Bolt, Color(0xFFFCE4EC), RiskLevel.LOW_RISK),
        Condition("Transient Ischemic Attack", 12, 80, Icons.Default.Bolt, Color(0xFFFCE4EC), RiskLevel.MEDIUM_RISK),
        Condition("Blood Clot Disorders", 8, 84, Icons.Default.Bloodtype, Color(0xFFFCE4EC), RiskLevel.MEDIUM_RISK)
    )

    val neurologicalConditions = listOf(
        Condition("Autism", 12, 78, Icons.Default.Psychology, Color(0xFFF3E5F5), RiskLevel.LOW_RISK)
    )

    val placeholderConditions = listOf(
        Condition("Sample Condition 1", 10, 85, Icons.AutoMirrored.Filled.Help, Color(0xFFE0F7FA), RiskLevel.MEDIUM_RISK),
        Condition("Sample Condition 2", 15, 90, Icons.AutoMirrored.Filled.Help, Color(0xFFE0F7FA), RiskLevel.LOW_RISK)
    )

    return when (moduleName) {
        "Metabolic & Endocrine" -> metabolicConditions
        "Cardiovascular" -> cardiovascularConditions
        "Neurological & Mental" -> neurologicalConditions
        else -> placeholderConditions
    }
}

@Preview(showBackground = true)
@Composable
fun PrediagnosisModuleDetailsScreenPreview() {
    PrediagnosticTheme {
        PrediagnosisModuleDetailsScreen(
            moduleName = "Metabolic & Endocrine",
            onNavigateBack = {},
            onViewRecommendationsClicked = {}
        )
    }
}
