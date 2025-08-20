package com.example.ketotracker.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Dashboard screen showing overview of keto tracking data
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Keto Tracker Dashboard",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        // Today's Summary Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Today's Summary",
                    style = MaterialTheme.typography.titleLarge
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Carbs Progress
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Carbs: ${uiState.totalCarbs}g / 20g")
                    Text(
                        text = if (uiState.totalCarbs <= 20) "✓ Good" else "⚠ Over Limit",
                        color = if (uiState.totalCarbs <= 20) 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.error
                    )
                }
                
                // Progress bar for carbs
                LinearProgressIndicator(
                    progress = (uiState.totalCarbs / 50f).coerceAtMost(1f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = if (uiState.totalCarbs <= 20) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.error
                )
                
                Text("Calories: ${uiState.totalCalories} kcal")
            }
        }
        
        // Health Metrics Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Latest Health Metrics",
                    style = MaterialTheme.typography.titleLarge
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                if (uiState.latestHealthMetric != null) {
                    val healthMetric = uiState.latestHealthMetric
                    
                    healthMetric.weightKg?.let {
                        Text("Weight: ${it}kg")
                    }
                    
                    if (healthMetric.glucoseMgDl != null && healthMetric.ketonesMmolL != null) {
                        Text("Glucose: ${healthMetric.glucoseMgDl} mg/dL")
                        Text("Ketones: ${healthMetric.ketonesMmolL} mmol/L")
                        healthMetric.calculateGKI()?.let { gki ->
                            Text("GKI: ${gki.toInt()}")
                        }
                    }
                    
                    healthMetric.getFormattedBloodPressure()?.let {
                        Text("Blood Pressure: $it")
                    }
                } else {
                    Text(
                        text = "No health data yet. Add some in the Health tab!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        
        // Quick Actions Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleLarge
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { /* Navigate to Food screen */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Log Food")
                    }
                    
                    Button(
                        onClick = { /* Navigate to Health screen */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Log Health")
                    }
                }
            }
        }
    }
}