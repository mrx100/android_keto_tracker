package com.example.ketotracker.data.local

import androidx.room.*
import com.example.ketotracker.data.model.HealthMetric
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for HealthMetric entity
 * Defines database operations for health metrics tracking
 */
@Dao
interface HealthMetricDao {
    
    @Query("SELECT * FROM health_metrics ORDER BY date DESC")
    fun getAllHealthMetrics(): Flow<List<HealthMetric>>
    
    @Query("SELECT * FROM health_metrics WHERE id = :id")
    suspend fun getHealthMetricById(id: String): HealthMetric?
    
    @Query("SELECT * FROM health_metrics WHERE date = :date")
    suspend fun getHealthMetricByDate(date: String): HealthMetric?
    
    @Query("SELECT * FROM health_metrics WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getHealthMetricsByDateRange(startDate: String, endDate: String): Flow<List<HealthMetric>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealthMetric(healthMetric: HealthMetric)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHealthMetrics(healthMetrics: List<HealthMetric>)
    
    @Update
    suspend fun updateHealthMetric(healthMetric: HealthMetric)
    
    @Delete
    suspend fun deleteHealthMetric(healthMetric: HealthMetric)
    
    @Query("DELETE FROM health_metrics WHERE id = :id")
    suspend fun deleteHealthMetricById(id: String)
    
    @Query("DELETE FROM health_metrics")
    suspend fun deleteAllHealthMetrics()
    
    /**
     * Get the latest weight entry
     */
    @Query("SELECT * FROM health_metrics WHERE weight_kg IS NOT NULL ORDER BY date DESC LIMIT 1")
    suspend fun getLatestWeight(): HealthMetric?
    
    /**
     * Get weight entries for trend analysis
     */
    @Query("SELECT * FROM health_metrics WHERE weight_kg IS NOT NULL ORDER BY date ASC")
    fun getWeightEntries(): Flow<List<HealthMetric>>
    
    /**
     * Get glucose entries for trend analysis
     */
    @Query("SELECT * FROM health_metrics WHERE glucose_mg_dl IS NOT NULL ORDER BY date ASC")
    fun getGlucoseEntries(): Flow<List<HealthMetric>>
    
    /**
     * Get ketone entries for trend analysis
     */
    @Query("SELECT * FROM health_metrics WHERE ketones_mmol_l IS NOT NULL ORDER BY date ASC")
    fun getKetoneEntries(): Flow<List<HealthMetric>>
    
    /**
     * Get entries with both glucose and ketones for GKI calculation
     */
    @Query("SELECT * FROM health_metrics WHERE glucose_mg_dl IS NOT NULL AND ketones_mmol_l IS NOT NULL ORDER BY date ASC")
    fun getGKIEntries(): Flow<List<HealthMetric>>
    
    /**
     * Get blood pressure entries
     */
    @Query("SELECT * FROM health_metrics WHERE bp_systolic IS NOT NULL OR bp_diastolic IS NOT NULL ORDER BY date ASC")
    fun getBloodPressureEntries(): Flow<List<HealthMetric>>
    
    /**
     * Get waist measurement entries
     */
    @Query("SELECT * FROM health_metrics WHERE waist_cm IS NOT NULL ORDER BY date ASC")
    fun getWaistEntries(): Flow<List<HealthMetric>>
    
    /**
     * Get pulse entries
     */
    @Query("SELECT * FROM health_metrics WHERE pulse_bpm IS NOT NULL ORDER BY date ASC")
    fun getPulseEntries(): Flow<List<HealthMetric>>
    
    /**
     * Get health metrics summary for dashboard
     */
    @Query("""
        SELECT 
            (SELECT weight_kg FROM health_metrics WHERE weight_kg IS NOT NULL ORDER BY date DESC LIMIT 1) as latestWeight,
            (SELECT glucose_mg_dl FROM health_metrics WHERE glucose_mg_dl IS NOT NULL ORDER BY date DESC LIMIT 1) as latestGlucose,
            (SELECT ketones_mmol_l FROM health_metrics WHERE ketones_mmol_l IS NOT NULL ORDER BY date DESC LIMIT 1) as latestKetones,
            (SELECT bp_systolic FROM health_metrics WHERE bp_systolic IS NOT NULL ORDER BY date DESC LIMIT 1) as latestSystolic,
            (SELECT bp_diastolic FROM health_metrics WHERE bp_diastolic IS NOT NULL ORDER BY date DESC LIMIT 1) as latestDiastolic,
            (SELECT pulse_bpm FROM health_metrics WHERE pulse_bpm IS NOT NULL ORDER BY date DESC LIMIT 1) as latestPulse
    """)
    suspend fun getLatestHealthSummary(): HealthSummary?
    
    /**
     * Get entries for the last N days
     */
    @Query("SELECT * FROM health_metrics WHERE date >= date('now', '-' || :days || ' days') ORDER BY date ASC")
    fun getHealthMetricsForLastDays(days: Int): Flow<List<HealthMetric>>
    
    /**
     * Data class for health summary
     */
    data class HealthSummary(
        val latestWeight: Double?,
        val latestGlucose: Double?,
        val latestKetones: Double?,
        val latestSystolic: Int?,
        val latestDiastolic: Int?,
        val latestPulse: Int?
    )
}