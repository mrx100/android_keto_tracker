package com.example.ketotracker.ui.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ketotracker.data.model.DailyLog
import com.example.ketotracker.data.model.FoodItem
import com.example.ketotracker.data.repository.DailyLogRepository
import com.example.ketotracker.data.repository.FoodItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class FoodUiState(
    val totalCarbs: Double = 0.0,
    val totalCalories: Double = 0.0,
    val todaysFoodEntries: List<DailyLog> = emptyList(),
    val allFoodItems: List<FoodItem> = emptyList(),
    val selectedFoodItem: FoodItem? = null,
    val quantity: String = "100",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showAddFoodDialog: Boolean = false
)

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val dailyLogRepository: DailyLogRepository,
    private val foodItemRepository: FoodItemRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(FoodUiState())
    val uiState: StateFlow<FoodUiState> = _uiState.asStateFlow()
    
    private val today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
    
    init {
        loadFoodData()
        initializeDefaultFoods()
    }
    
    private fun loadFoodData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                // Combine today's food entries and daily summary
                combine(
                    dailyLogRepository.getDailyLogsByDate(today),
                    foodItemRepository.getAllFoodItems()
                ) { todaysEntries, allFoods ->
                    val totalCarbs = todaysEntries.sumOf { it.totalCarbs }
                    val totalCalories = todaysEntries.sumOf { it.totalCalories }
                    
                    _uiState.value = _uiState.value.copy(
                        totalCarbs = totalCarbs,
                        totalCalories = totalCalories,
                        todaysFoodEntries = todaysEntries,
                        allFoodItems = allFoods,
                        isLoading = false,
                        errorMessage = null
                    )
                }.collect()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to load food data: ${e.message}"
                )
            }
        }
    }
    
    private fun initializeDefaultFoods() {
        viewModelScope.launch {
            try {
                foodItemRepository.initializeDefaultFoodItems()
            } catch (e: Exception) {
                // Log error but don't show to user
            }
        }
    }
    
    fun selectFoodItem(foodItem: FoodItem) {
        _uiState.value = _uiState.value.copy(selectedFoodItem = foodItem)
    }
    
    fun updateQuantity(quantity: String) {
        _uiState.value = _uiState.value.copy(quantity = quantity)
    }
    
    fun showAddFoodDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showAddFoodDialog = show)
    }
    
    fun addFoodEntry() {
        val currentState = _uiState.value
        val selectedFood = currentState.selectedFoodItem
        val quantityValue = currentState.quantity.toDoubleOrNull()
        
        if (selectedFood == null || quantityValue == null || quantityValue <= 0) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Please select a food item and enter a valid quantity"
            )
            return
        }
        
        viewModelScope.launch {
            try {
                val totalCarbs = (selectedFood.carbsPer100g / 100.0) * quantityValue
                val totalCalories = (selectedFood.caloriesPer100g / 100.0) * quantityValue
                
                val dailyLog = DailyLog(
                    foodName = selectedFood.name,
                    quantityGrams = quantityValue,
                    totalCarbs = totalCarbs,
                    totalCalories = totalCalories,
                    date = today
                )
                
                dailyLogRepository.insertDailyLog(dailyLog)
                
                _uiState.value = _uiState.value.copy(
                    selectedFoodItem = null,
                    quantity = "100",
                    showAddFoodDialog = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Failed to add food entry: ${e.message}"
                )
            }
        }
    }
    
    fun deleteFoodEntry(dailyLog: DailyLog) {
        viewModelScope.launch {
            try {
                dailyLogRepository.deleteDailyLog(dailyLog)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Failed to delete food entry: ${e.message}"
                )
            }
        }
    }
    
    fun clearErrorMessage() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
    
    fun clearAllTodaysEntries() {
        viewModelScope.launch {
            try {
                dailyLogRepository.deleteDailyLogsByDate(today)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Failed to clear today's entries: ${e.message}"
                )
            }
        }
    }
}