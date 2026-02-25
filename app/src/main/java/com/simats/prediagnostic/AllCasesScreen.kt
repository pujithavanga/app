package com.simats.prediagnostic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prediagnostic.ui.theme.PrediagnosticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCasesScreen(onCaseClicked: () -> Unit) {
    Scaffold {
        padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            item {
                Column(
                    modifier = Modifier
                        .background(Color(0xFF2C3E50)) // Dark blueish gray
                        .padding(16.dp)
                ) {
                    AllCasesTopBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    FilterSection()
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    SearchBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("6 Total Cases", fontWeight = FontWeight.Bold)
                        TextButton(onClick = { /*TODO*/ }) {
                            Text("Sort by Risk")
                            Icon(Icons.Default.FilterList, contentDescription = "Sort")
                        }
                    }
                }
            }

            val cases = getCases()
            items(cases) { case ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    CaseItem(case, onCaseClicked)
                }
            }
        }
    }
}

@Composable
private fun AllCasesTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("All Cases", fontWeight = FontWeight.Bold, color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Text("Manage and review patient cases", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
        }
        IconButton(onClick = { /* TODO */ }) {
            Icon(Icons.Default.Description, contentDescription = "New Case", tint = Color.White)
        }
    }
}

@Composable
private fun FilterSection() {
    Column {
        Text("Filter by Case Level", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.8f))
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip("All", "6", true)
            FilterChip("High", "2", color = Color(0xFFC62828))
            FilterChip("Medium", "2", color = Color(0xFFF9A825))
            FilterChip("Low", "2", color = Color(0xFF2E7D32))
        }
    }
}

@Composable
private fun RowScope.FilterChip(label: String, count: String, selected: Boolean = false, color: Color = MaterialTheme.colorScheme.primary) {
    val containerColor = if (selected) Color.White else color
    val contentColor = if (selected) Color.Black else Color.White
    Card(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = if (selected) BorderStroke(1.dp, Color.White) else null
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(count, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = contentColor)
            Text(label, fontSize = 12.sp, color = contentColor)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Search cases...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

private fun getCases() = listOf(
    Case("CID001", "High Risk", "Pending Review", "Rahul Sharma", "45 years • Male", "Blood Sensor Analysis", "Risk: TBD", "Jan 22, 2024", "08:22 AM"),
    Case("CID002", "Medium Risk", "In Progress", "Priya Patel", "38 years • Female", "Tissue Biopsy", "Risk: 0.52", "Jan 22, 2024", "10:15 AM"),
    Case("CID003", "Low Risk", "Completed", "Amit Kumar", "62 years • Male", "Urine Analysis", "Risk: 233", "Jan 21, 2024", "07:45 PM"),
    Case("CID004", "Medium Risk", "Completed", "Sneha Reddy", "29 years • Female", "X-Ray Analysis", "Risk: 532", "Jan 21, 2024", "11:30 AM"),
    Case("CID005", "High Risk", "Pending Review", "Vikram Singh", "61 years • Male", "CT Scan Review", "Risk: 823", "Jan 20, 2024", "03:30 PM"),
    Case("CID006", "Low Risk", "In Progress", "Ananya Desai", "34 years • Female", "MRI Analysis", "Risk: 189", "Jan 20, 2024", "01:15 PM")
)

@Composable
private fun CaseItem(case: Case, onCaseClicked: () -> Unit) {
    val riskColor = when (case.risk) {
        "High Risk" -> Color(0xFFE57373)
        "Medium Risk" -> Color(0xFFFFB74D)
        else -> Color(0xFF81C784)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCaseClicked() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, riskColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(case.id, fontSize = 12.sp, color = Color.Gray)
                    RiskTag(case.risk)
                }
                StatusTag(case.status)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(case.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
            Text(case.details, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text(case.analysis, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(case.riskScore, fontSize = 12.sp, color = Color.Gray)
                Text("•", fontSize = 12.sp, color = Color.Gray)
                Text(case.date, fontSize = 12.sp, color = Color.Gray)
                Text("•", fontSize = 12.sp, color = Color.Gray)
                Text(case.time, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
private fun RiskTag(risk: String) {
    val color = when (risk) {
        "High Risk" -> Color(0xFFC62828)
        "Medium Risk" -> Color(0xFFF9A825)
        else -> Color(0xFF2E7D32)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(risk, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun StatusTag(status: String) {
    val (color, text) = when (status) {
        "Pending Review" -> Pair(Color(0xFFF9A825), "Pending Review")
        "In Progress" -> Pair(MaterialTheme.colorScheme.primary, "In Progress")
        else -> Pair(Color(0xFF2E7D32), "Completed")
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

data class Case(
    val id: String,
    val risk: String,
    val status: String,
    val name: String,
    val details: String,
    val analysis: String,
    val riskScore: String,
    val date: String,
    val time: String
)

@Preview(showBackground = true)
@Composable
fun AllCasesScreenPreview() {
    PrediagnosticTheme {
        AllCasesScreen(onCaseClicked = {})
    }
}
