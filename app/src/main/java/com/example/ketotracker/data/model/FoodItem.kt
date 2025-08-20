package com.example.ketotracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * Entity representing a food item in the food database
 * Stores nutritional information per 100g of the food item
 */
@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "carbs_per_100g")
    val carbsPer100g: Double,
    
    @ColumnInfo(name = "calories_per_100g")
    val caloriesPer100g: Double,
    
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)