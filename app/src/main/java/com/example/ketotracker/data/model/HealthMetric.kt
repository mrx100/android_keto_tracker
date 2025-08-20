package com.example.ketotracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Index

/**
 * Entity representing health metrics tracking data
 * Stores weight, glucose, ketones, blood pressure and other health indicators
 */
@Entity(
    tableName = "health_metrics",
    indices = [Index(value = ["date"], unique = true)]
)
data class HealthMetric(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String, // Format: yyyy-MM-dd or custom unique ID
    
    @ColumnInfo(name = "date")
    val date: String, // Format: yyyy-MM-dd
    
    @ColumnInfo(name = "weight_kg")
    val weightKg: Double?,
    
    @ColumnInfo(name = "waist_cm")
    val waistCm: Double?,
    
    @ColumnInfo(name = "glucose_mg_dl")
    val glucoseMgDl: Double?,
    
    @ColumnInfo(name = "ketones_mmol_l")
    val ketonesMmolL: Double?,
    
    @ColumnInfo(name = "bp_systolic")
    val bpSystolic: Int?,
    
    @ColumnInfo(name = "bp_diastolic")
    val bpDiastolic: Int?,
    
    @ColumnInfo(name = "pulse_bpm")
    val pulseBpm: Int?,
    
    @ColumnInfo(name = "notes")
    val notes: String?,
    
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()
) {
    /**
     * Calculates BMI (Body Mass Index) using the provided height
     * @param heightM Height in meters
     * @return BMI value or null if weight is not available
     */
    fun calculateBMI(heightM: Double): Double? {
        return weightKg?.let { weight ->
            weight / (heightM * heightM)
        }
    }
    
    /**
     * Calculates GKI (Glucose Ketone Index)
     * Formula: (glucose in mmol/L) / (ketones in mmol/L)
     * Note: Glucose needs to be converted from mg/dL to mmol/L (divide by 18)
     * @return GKI value multiplied by 100 for display, or null if glucose or ketones unavailable
     */
    fun calculateGKI(): Double? {
        return if (glucoseMgDl != null && ketonesMmolL != null && ketonesMmolL > 0) {
            val glucoseMmolL = glucoseMgDl / 18.0
            val gki = glucoseMmolL / ketonesMmolL
            (gki * 100) // Multiply by 100 as done in the original HTML
        } else {
            null
        }
    }
    
    /**
     * Formats blood pressure as "sys/dia" string
     * @return Formatted blood pressure string or null if both values unavailable
     */
    fun getFormattedBloodPressure(): String? {
        return when {
            bpSystolic != null && bpDiastolic != null -> "$bpSystolic/$bpDiastolic"
            bpSystolic != null -> "$bpSystolic/-"
            bpDiastolic != null -> "-/$bpDiastolic"
            else -> null
        }
    }
}