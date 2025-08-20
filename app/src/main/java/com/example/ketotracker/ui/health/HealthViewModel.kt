package com.example.ketotracker.ui.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ketotracker.data.model.HealthMetric
import com.example.ketotracker.data.repository.HealthMetricRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class HealthUiState(
    val date: String = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
    val weight: String = "",
    val waist: String = "",
    val glucose: String = "",
    val ketones: String = "",
    val systolic: String = "",
    val diastolic: String = "",
    val pulse: String = "",
    val notes: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

@HiltViewModel
class HealthViewModel @Inject constructor(
    private val healthMetricRepository: HealthMetricRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HealthUiState())
    val uiState: StateFlow<HealthUiState> = _uiState.asStateFlow()
    
    init {
        loadTodaysHealthData()
    }
    
    private fun loadTodaysHealthData() {
        viewModelScope.launch {
            try {
                val today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
                val existingEntry = healthMetricRepository.getHealthMetricByDate(today)
                
                existingEntry?.let { entry ->
                    _uiState.value = _uiState.value.copy(
                        weight = entry.weightKg?.toString() ?: "",
                        waist = entry.waistCm?.toString() ?: "",
                        glucose = entry.glucoseMgDl?.toString() ?: "",
                        ketones = entry.ketonesMmolL?.toString() ?: "",
                        systolic = entry.bpSystolic?.toString() ?: "",
                        diastolic = entry.bpDiastolic?.toString() ?: "",
                        pulse = entry.pulseBpm?.toString() ?: "",
                        notes = entry.notes ?: ""
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Failed to load today's health data: ${e.message}"
                )
            }
        }
    }
    
    fun updateWeight(weight: String) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }
    
    fun updateWaist(waist: String) {
        _uiState.value = _uiState.value.copy(waist = waist)
    }
    
    fun updateGlucose(glucose: String) {
        _uiState.value = _uiState.value.copy(glucose = glucose)
    }
    
    fun updateKetones(ketones: String) {
        _uiState.value = _uiState.value.copy(ketones = ketones)
    }
    
    fun updateSystolic(systolic: String) {
        _uiState.value = _uiState.value.copy(systolic = systolic)
    }
    
    fun updateDiastolic(diastolic: String) {
        _uiState.value = _uiState.value.copy(diastolic = diastolic)
    }
    
    fun updatePulse(pulse: String) {
        _uiState.value = _uiState.value.copy(pulse = pulse)
    }
    
    fun updateNotes(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }
    
    fun saveHealthEntry() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val currentState = _uiState.value
                val today = currentState.date
                
                val healthMetric = HealthMetric(
                    id = today, // Use date as ID for uniqueness
                    date = today,
                    weightKg = currentState.weight.toDoubleOrNull(),
                    waistCm = currentState.waist.toDoubleOrNull(),
                    glucoseMgDl = currentState.glucose.toDoubleOrNull(),
                    ketonesMmolL = currentState.ketones.toDoubleOrNull(),
                    bpSystolic = currentState.systolic.toIntOrNull(),
                    bpDiastolic = currentState.diastolic.toIntOrNull(),
                    pulseBpm = currentState.pulse.toIntOrNull(),
                    notes = currentState.notes.ifBlank { null }
                )
                
                healthMetricRepository.insertHealthMetric(healthMetric)
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "Health entry saved successfully!",
                    errorMessage = null
                )
                
                // Clear success message after 3 seconds
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(successMessage = null)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to save health entry: ${e.message}"
                )
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            successMessage = null
        )
    }
}