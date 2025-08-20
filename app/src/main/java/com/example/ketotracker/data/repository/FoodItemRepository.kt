package com.example.ketotracker.data.repository

import com.example.ketotracker.data.model.FoodItem
import com.example.ketotracker.data.local.FoodItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository interface for food item operations
 */
interface FoodItemRepository {
    fun getAllFoodItems(): Flow<List<FoodItem>>
    suspend fun getFoodItemByName(name: String): FoodItem?
    fun searchFoodItems(query: String): Flow<List<FoodItem>>
    suspend fun insertFoodItem(foodItem: FoodItem)
    suspend fun insertAllFoodItems(foodItems: List<FoodItem>)
    suspend fun updateFoodItem(foodItem: FoodItem)
    suspend fun deleteFoodItem(foodItem: FoodItem)
    suspend fun deleteFoodItemByName(name: String)
    suspend fun deleteAllFoodItems()
    suspend fun getFoodItemCount(): Int
    fun getFoodItemsWithUsageCount(): Flow<List<FoodItemDao.FoodItemWithUsage>>
    suspend fun initializeDefaultFoodItems()
}

/**
 * Implementation of FoodItemRepository
 */
@Singleton
class FoodItemRepositoryImpl @Inject constructor(
    private val foodItemDao: FoodItemDao
) : FoodItemRepository {
    
    override fun getAllFoodItems(): Flow<List<FoodItem>> {
        return foodItemDao.getAllFoodItems()
    }
    
    override suspend fun getFoodItemByName(name: String): FoodItem? {
        return foodItemDao.getFoodItemByName(name)
    }
    
    override fun searchFoodItems(query: String): Flow<List<FoodItem>> {
        return foodItemDao.searchFoodItems(query)
    }
    
    override suspend fun insertFoodItem(foodItem: FoodItem) {
        foodItemDao.insertFoodItem(foodItem)
    }
    
    override suspend fun insertAllFoodItems(foodItems: List<FoodItem>) {
        foodItemDao.insertAllFoodItems(foodItems)
    }
    
    override suspend fun updateFoodItem(foodItem: FoodItem) {
        val updatedItem = foodItem.copy(updatedAt = System.currentTimeMillis())
        foodItemDao.updateFoodItem(updatedItem)
    }
    
    override suspend fun deleteFoodItem(foodItem: FoodItem) {
        foodItemDao.deleteFoodItem(foodItem)
    }
    
    override suspend fun deleteFoodItemByName(name: String) {
        foodItemDao.deleteFoodItemByName(name)
    }
    
    override suspend fun deleteAllFoodItems() {
        foodItemDao.deleteAllFoodItems()
    }
    
    override suspend fun getFoodItemCount(): Int {
        return foodItemDao.getFoodItemCount()
    }
    
    override fun getFoodItemsWithUsageCount(): Flow<List<FoodItemDao.FoodItemWithUsage>> {
        return foodItemDao.getFoodItemsWithUsageCount()
    }
    
    override suspend fun initializeDefaultFoodItems() {
        // Check if database is empty
        val count = getFoodItemCount()
        if (count == 0) {
            // Insert default food items from the original HTML
            val defaultFoodItems = listOf(
                FoodItem("Walnüsse", 7.0, 654.0),
                FoodItem("Eier (ganz)", 0.6, 155.0),
                FoodItem("Butter", 0.1, 717.0),
                FoodItem("Rindfleisch (fett)", 0.0, 250.0),
                FoodItem("Hähnchenbrust", 0.0, 165.0),
                FoodItem("Avocado", 1.8, 160.0),
                FoodItem("Brokkoli", 4.4, 34.0),
                FoodItem("Käse (Cheddar)", 1.3, 403.0),
                FoodItem("Olivenöl", 0.0, 884.0),
                FoodItem("Sahne (30%)", 3.0, 290.0),
                FoodItem("Lachs", 0.0, 208.0),
                FoodItem("Magerquark", 3.6, 67.0),
                FoodItem("10% Joghurt Natur", 4.7, 110.0)
            )
            insertAllFoodItems(defaultFoodItems)
        }
    }
}