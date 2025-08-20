package com.example.ketotracker.ui.charts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ketotracker.data.model.HealthMetric
import com.example.ketotracker.data.repository.DailyLogRepository
import com.example.ketotracker.data.repository.HealthMetricRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChartDataPoint(
    val date: String,
    val value: Double
)

data class ChartsUiState(
    val selectedPeriod: String = "Daily",
    val weightData: List<Pair<String, Double>> = emptyList(),
    val bmiData: List<Pair<String, Double>> = emptyList(),
    val glucoseData: List<Pair<String, Double>> = emptyList(),
    val ketoneData: List<Pair<String, Double>> = emptyList(),
    val gkiData: List<Pair<String, Double>> = emptyList(),
    val systolicData: List<Pair<String, Int>> = emptyList(),
    val diastolicData: List<Pair<String, Int>> = emptyList(),
    val pulseData: List<Pair<String, Int>> = emptyList(),
    val carbData: List<Pair<String, Double>> = emptyList(),
    val calorieData: List<Pair<String, Double>> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class ChartsViewModel @Inject constructor(
    private val healthMetricRepository: HealthMetricRepository,
    private val dailyLogRepository: DailyLogRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ChartsUiState())
    val uiState: StateFlow<ChartsUiState> = _uiState.asStateFlow()
    
    // Assumed user height for BMI calculation - could be made configurable
    private val userHeightM = 1.73
    
    init {
        loadChartData()
    }
    
    private fun loadChartData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                // Load health metrics
                val healthMetrics = healthMetricRepository.getAllHealthMetrics().first()
                
                // Load daily food summaries for the last 30 days
                val startDate = java.time.LocalDate.now().minusDays(30).toString()
                val endDate = java.time.LocalDate.now().toString()
                val foodSummaries = dailyLogRepository.getDailySummariesByDateRange(startDate, endDate).first()
                
                // Process health data
                val weightData = healthMetrics.mapNotNull { metric ->
                    metric.weightKg?.let { weight ->
                        Pair(metric.date, weight)
                    }
                }
                
                val bmiData = healthMetrics.mapNotNull { metric ->
                    metric.calculateBMI(userHeightM)?.let { bmi ->
                        Pair(metric.date, bmi)
                    }
                }
                
                val glucoseData = healthMetrics.mapNotNull { metric ->
                    metric.glucoseMgDl?.let { glucose ->
                        Pair(metric.date, glucose)
                    }
                }
                
                val ketoneData = healthMetrics.mapNotNull { metric ->
                    metric.ketonesMmolL?.let { ketones ->
                        Pair(metric.date, ketones)
                    }
                }
                
                val gkiData = healthMetrics.mapNotNull { metric ->
                    metric.calculateGKI()?.let { gki ->
                        Pair(metric.date, gki)
                    }
                }
                
                val systolicData = healthMetrics.mapNotNull { metric ->
                    metric.bpSystolic?.let { systolic ->
                        Pair(metric.date, systolic)
                    }
                }
                
                val diastolicData = healthMetrics.mapNotNull { metric ->
                    metric.bpDiastolic?.let { diastolic ->
                        Pair(metric.date, diastolic)
                    }
                }
                
                val pulseData = healthMetrics.mapNotNull { metric ->
                    metric.pulseBpm?.let { pulse ->
                        Pair(metric.date, pulse)
                    }
                }
                
                // Process food data
                val carbData = foodSummaries.map { summary ->
                    Pair(summary.date, summary.totalCarbs ?: 0.0)
                }
                
                val calorieData = foodSummaries.map { summary ->
                    Pair(summary.date, summary.totalCalories ?: 0.0)
                }
                
                _uiState.value = _uiState.value.copy(
                    weightData = weightData,
                    bmiData = bmiData,
                    glucoseData = glucoseData,
                    ketoneData = ketoneData,
                    gkiData = gkiData,
                    systolicData = systolicData,
                    diastolicData = diastolicData,
                    pulseData = pulseData,
                    carbData = carbData,
                    calorieData = calorieData,
                    isLoading = false,
                    errorMessage = null
                )
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to load chart data: ${e.message}"
                )
            }
        }
    }
    
    fun updatePeriod(period: String) {
        _uiState.value = _uiState.value.copy(selectedPeriod = period)
        // In a full implementation, this would filter/aggregate data by period
        loadChartData()
    }
    
    fun refresh() {
        loadChartData()
    }
    
    fun clearErrorMessage() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}