package com.example.ketotracker.data.local

import androidx.room.*
import com.example.ketotracker.data.model.DailyLog
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for DailyLog entity
 * Defines database operations for daily food consumption tracking
 */
@Dao
interface DailyLogDao {
    
    @Query("SELECT * FROM daily_logs ORDER BY date DESC, timestamp DESC")
    fun getAllDailyLogs(): Flow<List<DailyLog>>
    
    @Query("SELECT * FROM daily_logs WHERE date = :date ORDER BY timestamp DESC")
    fun getDailyLogsByDate(date: String): Flow<List<DailyLog>>
    
    @Query("SELECT * FROM daily_logs WHERE id = :id")
    suspend fun getDailyLogById(id: Long): DailyLog?
    
    @Query("SELECT * FROM daily_logs WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC, timestamp DESC")
    fun getDailyLogsByDateRange(startDate: String, endDate: String): Flow<List<DailyLog>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyLog(dailyLog: DailyLog): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDailyLogs(dailyLogs: List<DailyLog>)
    
    @Update
    suspend fun updateDailyLog(dailyLog: DailyLog)
    
    @Delete
    suspend fun deleteDailyLog(dailyLog: DailyLog)
    
    @Query("DELETE FROM daily_logs WHERE id = :id")
    suspend fun deleteDailyLogById(id: Long)
    
    @Query("DELETE FROM daily_logs WHERE date = :date")
    suspend fun deleteDailyLogsByDate(date: String)
    
    @Query("DELETE FROM daily_logs")
    suspend fun deleteAllDailyLogs()
    
    /**
     * Get total carbs and calories for a specific date
     */
    @Query("""
        SELECT 
            SUM(total_carbs) as totalCarbs, 
            SUM(total_calories) as totalCalories 
        FROM daily_logs 
        WHERE date = :date
    """)
    suspend fun getDailySummary(date: String): DailySummary?
    
    /**
     * Get daily summaries for a date range
     */
    @Query("""
        SELECT 
            date,
            SUM(total_carbs) as totalCarbs, 
            SUM(total_calories) as totalCalories 
        FROM daily_logs 
        WHERE date BETWEEN :startDate AND :endDate
        GROUP BY date
        ORDER BY date DESC
    """)
    fun getDailySummariesByDateRange(startDate: String, endDate: String): Flow<List<DailySummaryWithDate>>
    
    /**
     * Get most consumed foods (for suggestions)
     */
    @Query("""
        SELECT food_name, COUNT(*) as count, AVG(quantity_grams) as avgQuantity
        FROM daily_logs 
        GROUP BY food_name 
        ORDER BY count DESC 
        LIMIT 10
    """)
    fun getMostConsumedFoods(): Flow<List<FoodConsumptionStats>>
    
    /**
     * Get weekly carb trend
     */
    @Query("""
        SELECT date, SUM(total_carbs) as totalCarbs
        FROM daily_logs 
        WHERE date >= date('now', '-7 days')
        GROUP BY date
        ORDER BY date ASC
    """)
    fun getWeeklyCarbTrend(): Flow<List<DailyCarbs>>
    
    /**
     * Data classes for query results
     */
    data class DailySummary(
        val totalCarbs: Double?,
        val totalCalories: Double?
    )
    
    data class DailySummaryWithDate(
        val date: String,
        val totalCarbs: Double?,
        val totalCalories: Double?
    )
    
    data class FoodConsumptionStats(
        val foodName: String,
        val count: Int,
        val avgQuantity: Double
    )
    
    data class DailyCarbs(
        val date: String,
        val totalCarbs: Double?
    )
}