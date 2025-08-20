package com.example.ketotracker.ui.food

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Food screen for managing food database and logging daily food intake
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    viewModel: FoodViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Add Food Dialog
    if (uiState.showAddFoodDialog) {
        AddFoodDialog(
            foodItems = uiState.allFoodItems,
            selectedFood = uiState.selectedFoodItem,
            quantity = uiState.quantity,
            onFoodSelected = viewModel::selectFoodItem,
            onQuantityChanged = viewModel::updateQuantity,
            onConfirm = {
                viewModel.addFoodEntry()
            },
            onDismiss = {
                viewModel.showAddFoodDialog(false)
            }
        )
    }
    
    // Error Snackbar
    uiState.errorMessage?.let { error ->
        LaunchedEffect(error) {
            // Show snackbar or handle error
            viewModel.clearErrorMessage()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Food Tracking",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            FloatingActionButton(
                onClick = { viewModel.showAddFoodDialog(true) },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Food")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Today's Summary
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
                    text = "Today's Intake",
                    style = MaterialTheme.typography.titleLarge
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Carbs: ${String.format("%.1f", uiState.totalCarbs)}g")
                    Text("Calories: ${String.format("%.0f", uiState.totalCalories)} kcal")
                }
                
                // Carb limit progress
                LinearProgressIndicator(
                    progress = { (uiState.totalCarbs / 20f).coerceAtMost(1f) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = if (uiState.totalCarbs <= 20) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.error
                )
                
                Text(
                    text = "Ketosis limit: 20g carbs ${if (uiState.totalCarbs > 20) "- EXCEEDED!" else ""}",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (uiState.totalCarbs <= 20) 
                        MaterialTheme.colorScheme.onSurfaceVariant 
                    else 
                        MaterialTheme.colorScheme.error
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Today's Food Entries
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Today's Entries",
                style = MaterialTheme.typography.titleMedium
            )
            
            if (uiState.todaysFoodEntries.isNotEmpty()) {
                TextButton(
                    onClick = { /* Clear all entries for new day */ }
                ) {
                    Text("Clear All")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        if (uiState.todaysFoodEntries.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No food entries today.\nTap + to add your first meal!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.todaysFoodEntries) { entry ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = entry.foodName,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.weight(1f)
                                )
                                
                                IconButton(
                                    onClick = { viewModel.deleteFoodEntry(entry) }
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Delete entry",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                            
                            Text(
                                text = "${String.format("%.0f", entry.quantityGrams)}g",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${String.format("%.1f", entry.totalCarbs)}g carbs",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "${String.format("%.0f", entry.totalCalories)} kcal",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodDialog(
    foodItems: List<com.example.ketotracker.data.model.FoodItem>,
    selectedFood: com.example.ketotracker.data.model.FoodItem?,
    quantity: String,
    onFoodSelected: (com.example.ketotracker.data.model.FoodItem) -> Unit,
    onQuantityChanged: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add Food Entry",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Food Selection Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedFood?.name ?: "Select food item",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Food Item") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        foodItems.forEach { food ->
                            DropdownMenuItem(
                                text = { 
                                    Column {
                                        Text(food.name)
                                        Text(
                                            text = "${food.carbsPer100g}g carbs, ${food.caloriesPer100g.toInt()} kcal per 100g",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                },
                                onClick = {
                                    onFoodSelected(food)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Quantity Input
                OutlinedTextField(
                    value = quantity,
                    onValueChange = onQuantityChanged,
                    label = { Text("Quantity (grams)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                
                // Preview calculation
                selectedFood?.let { food ->
                    val quantityValue = quantity.toDoubleOrNull() ?: 0.0
                    if (quantityValue > 0) {
                        val carbs = (food.carbsPer100g / 100.0) * quantityValue
                        val calories = (food.caloriesPer100g / 100.0) * quantityValue
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "This will add:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text("${String.format("%.1f", carbs)}g carbs")
                                Text("${String.format("%.0f", calories)} calories")
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Button(
                        onClick = onConfirm,
                        enabled = selectedFood != null && quantity.toDoubleOrNull() != null && quantity.toDoubleOrNull()!! > 0
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
}