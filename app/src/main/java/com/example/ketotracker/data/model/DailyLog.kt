package com.example.ketotracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Entity representing a daily food log entry
 * Tracks food consumption with quantity and calculated nutritional values
 */
@Entity(
    tableName = "daily_logs",
    foreignKeys = [
        ForeignKey(
            entity = FoodItem::class,
            parentColumns = ["name"],
            childColumns = ["food_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["food_name"]), Index(value = ["date"])]
)
data class DailyLog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    
    @ColumnInfo(name = "food_name")
    val foodName: String,
    
    @ColumnInfo(name = "quantity_grams")
    val quantityGrams: Double,
    
    @ColumnInfo(name = "total_carbs")
    val totalCarbs: Double,
    
    @ColumnInfo(name = "total_calories")
    val totalCalories: Double,
    
    @ColumnInfo(name = "date")
    val date: String, // Format: yyyy-MM-dd
    
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()
)