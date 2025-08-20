package com.example.ketotracker.data.repository

import com.example.ketotracker.data.model.DailyLog
import com.example.ketotracker.data.local.DailyLogDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository interface for daily log operations
 */
interface DailyLogRepository {
    fun getAllDailyLogs(): Flow<List<DailyLog>>
    fun getDailyLogsByDate(date: String): Flow<List<DailyLog>>
    suspend fun getDailyLogById(id: Long): DailyLog?
    fun getDailyLogsByDateRange(startDate: String, endDate: String): Flow<List<DailyLog>>
    suspend fun insertDailyLog(dailyLog: DailyLog): Long
    suspend fun insertAllDailyLogs(dailyLogs: List<DailyLog>)
    suspend fun updateDailyLog(dailyLog: DailyLog)
    suspend fun deleteDailyLog(dailyLog: DailyLog)
    suspend fun deleteDailyLogById(id: Long)
    suspend fun deleteDailyLogsByDate(date: String)
    suspend fun deleteAllDailyLogs()
    suspend fun getDailySummary(date: String): DailyLogDao.DailySummary?
    fun getDailySummariesByDateRange(startDate: String, endDate: String): Flow<List<DailyLogDao.DailySummaryWithDate>>
    fun getMostConsumedFoods(): Flow<List<DailyLogDao.FoodConsumptionStats>>
    fun getWeeklyCarbTrend(): Flow<List<DailyLogDao.DailyCarbs>>
}

/**
 * Implementation of DailyLogRepository
 */
@Singleton
class DailyLogRepositoryImpl @Inject constructor(
    private val dailyLogDao: DailyLogDao
) : DailyLogRepository {
    
    override fun getAllDailyLogs(): Flow<List<DailyLog>> {
        return dailyLogDao.getAllDailyLogs()
    }
    
    override fun getDailyLogsByDate(date: String): Flow<List<DailyLog>> {
        return dailyLogDao.getDailyLogsByDate(date)
    }
    
    override suspend fun getDailyLogById(id: Long): DailyLog? {
        return dailyLogDao.getDailyLogById(id)
    }
    
    override fun getDailyLogsByDateRange(startDate: String, endDate: String): Flow<List<DailyLog>> {
        return dailyLogDao.getDailyLogsByDateRange(startDate, endDate)
    }
    
    override suspend fun insertDailyLog(dailyLog: DailyLog): Long {
        return dailyLogDao.insertDailyLog(dailyLog)
    }
    
    override suspend fun insertAllDailyLogs(dailyLogs: List<DailyLog>) {
        dailyLogDao.insertAllDailyLogs(dailyLogs)
    }
    
    override suspend fun updateDailyLog(dailyLog: DailyLog) {
        dailyLogDao.updateDailyLog(dailyLog)
    }
    
    override suspend fun deleteDailyLog(dailyLog: DailyLog) {
        dailyLogDao.deleteDailyLog(dailyLog)
    }
    
    override suspend fun deleteDailyLogById(id: Long) {
        dailyLogDao.deleteDailyLogById(id)
    }
    
    override suspend fun deleteDailyLogsByDate(date: String) {
        dailyLogDao.deleteDailyLogsByDate(date)
    }
    
    override suspend fun deleteAllDailyLogs() {
        dailyLogDao.deleteAllDailyLogs()
    }
    
    override suspend fun getDailySummary(date: String): DailyLogDao.DailySummary? {
        return dailyLogDao.getDailySummary(date)
    }
    
    override fun getDailySummariesByDateRange(startDate: String, endDate: String): Flow<List<DailyLogDao.DailySummaryWithDate>> {
        return dailyLogDao.getDailySummariesByDateRange(startDate, endDate)
    }
    
    override fun getMostConsumedFoods(): Flow<List<DailyLogDao.FoodConsumptionStats>> {
        return dailyLogDao.getMostConsumedFoods()
    }
    
    override fun getWeeklyCarbTrend(): Flow<List<DailyLogDao.DailyCarbs>> {
        return dailyLogDao.getWeeklyCarbTrend()
    }
}