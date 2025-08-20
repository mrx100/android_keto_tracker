package com.example.ketotracker.data.repository

import com.example.ketotracker.data.model.HealthMetric
import com.example.ketotracker.data.local.HealthMetricDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository interface for health metric operations
 */
interface HealthMetricRepository {
    fun getAllHealthMetrics(): Flow<List<HealthMetric>>
    suspend fun getHealthMetricById(id: String): HealthMetric?
    suspend fun getHealthMetricByDate(date: String): HealthMetric?
    fun getHealthMetricsByDateRange(startDate: String, endDate: String): Flow<List<HealthMetric>>
    suspend fun insertHealthMetric(healthMetric: HealthMetric)
    suspend fun insertAllHealthMetrics(healthMetrics: List<HealthMetric>)
    suspend fun updateHealthMetric(healthMetric: HealthMetric)
    suspend fun deleteHealthMetric(healthMetric: HealthMetric)
    suspend fun deleteHealthMetricById(id: String)
    suspend fun deleteAllHealthMetrics()
    suspend fun getLatestWeight(): HealthMetric?
    fun getWeightEntries(): Flow<List<HealthMetric>>
    fun getGlucoseEntries(): Flow<List<HealthMetric>>
    fun getKetoneEntries(): Flow<List<HealthMetric>>
    fun getGKIEntries(): Flow<List<HealthMetric>>
    fun getBloodPressureEntries(): Flow<List<HealthMetric>>
    fun getWaistEntries(): Flow<List<HealthMetric>>
    fun getPulseEntries(): Flow<List<HealthMetric>>
    suspend fun getLatestHealthSummary(): HealthMetricDao.HealthSummary?
    fun getHealthMetricsForLastDays(days: Int): Flow<List<HealthMetric>>
}

/**
 * Implementation of HealthMetricRepository
 */
@Singleton
class HealthMetricRepositoryImpl @Inject constructor(
    private val healthMetricDao: HealthMetricDao
) : HealthMetricRepository {
    
    override fun getAllHealthMetrics(): Flow<List<HealthMetric>> {
        return healthMetricDao.getAllHealthMetrics()
    }
    
    override suspend fun getHealthMetricById(id: String): HealthMetric? {
        return healthMetricDao.getHealthMetricById(id)
    }
    
    override suspend fun getHealthMetricByDate(date: String): HealthMetric? {
        return healthMetricDao.getHealthMetricByDate(date)
    }
    
    override fun getHealthMetricsByDateRange(startDate: String, endDate: String): Flow<List<HealthMetric>> {
        return healthMetricDao.getHealthMetricsByDateRange(startDate, endDate)
    }
    
    override suspend fun insertHealthMetric(healthMetric: HealthMetric) {
        healthMetricDao.insertHealthMetric(healthMetric)
    }
    
    override suspend fun insertAllHealthMetrics(healthMetrics: List<HealthMetric>) {
        healthMetricDao.insertAllHealthMetrics(healthMetrics)
    }
    
    override suspend fun updateHealthMetric(healthMetric: HealthMetric) {
        val updatedMetric = healthMetric.copy(timestamp = System.currentTimeMillis())
        healthMetricDao.updateHealthMetric(updatedMetric)
    }
    
    override suspend fun deleteHealthMetric(healthMetric: HealthMetric) {
        healthMetricDao.deleteHealthMetric(healthMetric)
    }
    
    override suspend fun deleteHealthMetricById(id: String) {
        healthMetricDao.deleteHealthMetricById(id)
    }
    
    override suspend fun deleteAllHealthMetrics() {
        healthMetricDao.deleteAllHealthMetrics()
    }
    
    override suspend fun getLatestWeight(): HealthMetric? {
        return healthMetricDao.getLatestWeight()
    }
    
    override fun getWeightEntries(): Flow<List<HealthMetric>> {
        return healthMetricDao.getWeightEntries()
    }
    
    override fun getGlucoseEntries(): Flow<List<HealthMetric>> {
        return healthMetricDao.getGlucoseEntries()
    }
    
    override fun getKetoneEntries(): Flow<List<HealthMetric>> {
        return healthMetricDao.getKetoneEntries()
    }
    
    override fun getGKIEntries(): Flow<List<HealthMetric>> {
        return healthMetricDao.getGKIEntries()
    }
    
    override fun getBloodPressureEntries(): Flow<List<HealthMetric>> {
        return healthMetricDao.getBloodPressureEntries()
    }
    
    override fun getWaistEntries(): Flow<List<HealthMetric>> {
        return healthMetricDao.getWaistEntries()
    }
    
    override fun getPulseEntries(): Flow<List<HealthMetric>> {
        return healthMetricDao.getPulseEntries()
    }
    
    override suspend fun getLatestHealthSummary(): HealthMetricDao.HealthSummary? {
        return healthMetricDao.getLatestHealthSummary()
    }
    
    override fun getHealthMetricsForLastDays(days: Int): Flow<List<HealthMetric>> {
        return healthMetricDao.getHealthMetricsForLastDays(days)
    }
}