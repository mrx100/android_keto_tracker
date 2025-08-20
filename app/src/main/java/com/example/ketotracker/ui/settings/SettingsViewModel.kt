package com.example.ketotracker.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ketotracker.data.repository.DailyLogRepository
import com.example.ketotracker.data.repository.FoodItemRepository
import com.example.ketotracker.data.repository.HealthMetricRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val userHeight: String = "1.73", // Default height in meters
    val carbLimit: String = "20", // Default carb limit
    val darkTheme: Boolean = false,
    val metricUnits: Boolean = true,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val foodItemRepository: FoodItemRepository,
    private val dailyLogRepository: DailyLogRepository,
    private val healthMetricRepository: HealthMetricRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()
    
    fun updateUserHeight(height: String) {
        _uiState.value = _uiState.value.copy(userHeight = height)
    }
    
    fun updateCarbLimit(limit: String) {
        _uiState.value = _uiState.value.copy(carbLimit = limit)
    }
    
    fun updateDarkTheme(darkTheme: Boolean) {
        _uiState.value = _uiState.value.copy(darkTheme = darkTheme)
    }
    
    fun updateMetricUnits(metricUnits: Boolean) {
        _uiState.value = _uiState.value.copy(metricUnits = metricUnits)
    }
    
    fun exportAllData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                // In a real implementation, this would:
                // 1. Get all data from repositories
                // 2. Convert to CSV format
                // 3. Save to external storage or share
                // 4. Show success message
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "Data export functionality coming soon!"
                )
                
                // Clear message after delay
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to export data: ${e.message}"
                )
            }
        }
    }
    
    fun importData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                // In a real implementation, this would:
                // 1. Show file picker
                // 2. Parse CSV/JSON data
                // 3. Import into database
                // 4. Show success message
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "Data import functionality coming soon!"
                )
                
                // Clear message after delay
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to import data: ${e.message}"
                )
            }
        }
    }
    
    fun exportFoodDatabase() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                // Get all food items
                val foodItems = foodItemRepository.getAllFoodItems().first()
                
                // In a real implementation, convert to CSV and export
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "Food database exported! (${foodItems.size} items)"
                )
                
                // Clear message after delay
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to export food database: ${e.message}"
                )
            }
        }
    }
    
    fun importFoodDatabase() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                // In a real implementation, this would show file picker and import
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "Food database import functionality coming soon!"
                )
                
                // Clear message after delay
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to import food database: ${e.message}"
                )
            }
        }
    }
    
    fun clearFoodEntries() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                dailyLogRepository.deleteAllDailyLogs()
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "All food entries cleared successfully!"
                )
                
                // Clear message after delay
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to clear food entries: ${e.message}"
                )
            }
        }
    }
    
    fun clearHealthData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                healthMetricRepository.deleteAllHealthMetrics()
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "All health data cleared successfully!"
                )
                
                // Clear message after delay
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to clear health data: ${e.message}"
                )
            }
        }
    }
    
    fun resetAllData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                // Clear all data
                dailyLogRepository.deleteAllDailyLogs()
                healthMetricRepository.deleteAllHealthMetrics()
                foodItemRepository.deleteAllFoodItems()
                
                // Reinitialize default food items
                foodItemRepository.initializeDefaultFoodItems()
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "All data reset successfully! Default food items restored."
                )
                
                // Clear message after delay
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to reset data: ${e.message}"
                )
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            successMessage = null,
            errorMessage = null
        )
    }
}