package com.example.ketotracker.data.local

import androidx.room.*
import com.example.ketotracker.data.model.FoodItem
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for FoodItem entity
 * Defines database operations for the food items database
 */
@Dao
interface FoodItemDao {
    
    @Query("SELECT * FROM food_items ORDER BY name ASC")
    fun getAllFoodItems(): Flow<List<FoodItem>>
    
    @Query("SELECT * FROM food_items WHERE name = :name")
    suspend fun getFoodItemByName(name: String): FoodItem?
    
    @Query("SELECT * FROM food_items WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchFoodItems(searchQuery: String): Flow<List<FoodItem>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItem(foodItem: FoodItem)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFoodItems(foodItems: List<FoodItem>)
    
    @Update
    suspend fun updateFoodItem(foodItem: FoodItem)
    
    @Delete
    suspend fun deleteFoodItem(foodItem: FoodItem)
    
    @Query("DELETE FROM food_items WHERE name = :name")
    suspend fun deleteFoodItemByName(name: String)
    
    @Query("DELETE FROM food_items")
    suspend fun deleteAllFoodItems()
    
    @Query("SELECT COUNT(*) FROM food_items")
    suspend fun getFoodItemCount(): Int
    
    /**
     * Get food items with their usage frequency from daily logs
     * Useful for showing most commonly used foods first
     */
    @Query("""
        SELECT f.*, COUNT(d.food_name) as usage_count 
        FROM food_items f 
        LEFT JOIN daily_logs d ON f.name = d.food_name 
        GROUP BY f.name 
        ORDER BY usage_count DESC, f.name ASC
    """)
    fun getFoodItemsWithUsageCount(): Flow<List<FoodItemWithUsage>>
    
    /**
     * Data class to include usage count with food item
     */
    data class FoodItemWithUsage(
        val name: String,
        val carbsPer100g: Double,
        val caloriesPer100g: Double,
        val createdAt: Long,
        val updatedAt: Long,
        val usageCount: Int
    )
}