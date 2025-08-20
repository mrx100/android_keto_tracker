package com.example.ketotracker.data.local

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ketotracker.data.model.DailyLog
import com.example.ketotracker.data.model.FoodItem
import com.example.ketotracker.data.model.HealthMetric

/**
 * Room Database class for the Keto Tracker application
 * Defines the database configuration and serves as the main access point
 */
@Database(
    entities = [
        FoodItem::class,
        DailyLog::class,
        HealthMetric::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class KetoDatabase : RoomDatabase() {
    
    abstract fun foodItemDao(): FoodItemDao
    abstract fun dailyLogDao(): DailyLogDao
    abstract fun healthMetricDao(): HealthMetricDao
    
    companion object {
        const val DATABASE_NAME = "keto_tracker_database"
        
        /**
         * Migration from version 1 to 2 (example for future use)
         * Add new migrations here as the database evolves
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Example migration - add new column
                // database.execSQL("ALTER TABLE food_items ADD COLUMN protein_per_100g REAL DEFAULT 0.0")
            }
        }
    }
}

/**
 * Type converters for Room database
 * Handles conversion of complex data types for database storage
 */
class Converters {
    
    /**
     * Convert timestamp to Long for storage
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Long? {
        return value
    }
    
    /**
     * Convert Long to timestamp
     */
    @TypeConverter
    fun dateToTimestamp(timestamp: Long?): Long? {
        return timestamp
    }
    
    /**
     * Convert List<String> to String for storage (if needed in future)
     */
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }
    
    /**
     * Convert String to List<String>
     */
    @TypeConverter
    fun toStringList(value: String): List<String> {
        return if (value.isBlank()) emptyList() else value.split(",")
    }
}