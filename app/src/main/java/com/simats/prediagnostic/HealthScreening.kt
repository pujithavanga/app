
package com.simats.prediagnostic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun HealthScreeningScreen(onNavigateBack: () -> Unit, onCategoryClicked: () -> Unit) {
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
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Select a Category", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
                Text("Choose a category to explore conditions")
            }
            // TODO: Add search bar
            item {
                CategoryCard("Metabolic & Endocrine", "13 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Cardiovascular", "15 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Neurological & Mental", "18 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Cancer & Oncology", "18 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Respiratory", "16 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Liver & GI", "18 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Kidney & Urinary", "9 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Bone & Muscle", "7 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Genetic & Inherited", "8 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Deficiency & Nutrition", "8 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Blood & Immune", "8 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Developmental & Pediatric", "9 conditions", onClick = onCategoryClicked)
            }
            item {
                CategoryCard("Lifestyle & Risk", "6 conditions", onClick = onCategoryClicked)
            }
        }
    }
}

@Composable
fun CategoryCard(name: String, count: String, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(name, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                Text(count)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Go")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HealthScreeningScreenPreview() {
    PrediagnosticTheme {
        HealthScreeningScreen(onNavigateBack = {}, onCategoryClicked = {})
    }
}
