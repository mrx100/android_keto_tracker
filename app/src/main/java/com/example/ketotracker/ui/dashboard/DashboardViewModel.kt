package com.example.ketotracker.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ketotracker.data.model.HealthMetric
import com.example.ketotracker.data.repository.DailyLogRepository
import com.example.ketotracker.data.repository.HealthMetricRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class DashboardUiState(
    val totalCarbs: Double = 0.0,
    val totalCalories: Double = 0.0,
    val latestHealthMetric: HealthMetric? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dailyLogRepository: DailyLogRepository,
    private val healthMetricRepository: HealthMetricRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
    
    private val today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
    
    init {
        loadDashboardData()
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                // Load today's food summary
                val dailySummary = dailyLogRepository.getDailySummary(today)
                
                // Load latest health metric
                val latestHealth = healthMetricRepository.getLatestWeight() ?: 
                    healthMetricRepository.getAllHealthMetrics().first().firstOrNull()
                
                _uiState.value = _uiState.value.copy(
                    totalCarbs = dailySummary?.totalCarbs ?: 0.0,
                    totalCalories = dailySummary?.totalCalories ?: 0.0,
                    latestHealthMetric = latestHealth,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to load dashboard data: ${e.message}"
                )
            }
        }
    }
    
    fun refresh() {
        loadDashboardData()
    }
}