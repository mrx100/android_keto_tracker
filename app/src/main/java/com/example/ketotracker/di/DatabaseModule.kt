package com.example.ketotracker.di

import android.content.Context
import androidx.room.Room
import com.example.ketotracker.data.local.*
import com.example.ketotracker.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for database-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {
    
    companion object {
        /**
         * Database callback to initialize default food items
         */
        private val databaseCallback = object : androidx.room.RoomDatabase.Callback() {
            override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                super.onCreate(db)
                // Initialize default food items from the original HTML
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Walnüsse', 7.0, 654.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Eier (ganz)', 0.6, 155.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Butter', 0.1, 717.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Rindfleisch (fett)', 0.0, 250.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Hähnchenbrust', 0.0, 165.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Avocado', 1.8, 160.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Brokkoli', 4.4, 34.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Käse (Cheddar)', 1.3, 403.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Olivenöl', 0.0, 884.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Sahne (30%)', 3.0, 290.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Lachs', 0.0, 208.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('Magerquark', 3.6, 67.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
                db.execSQL("INSERT INTO food_items (name, carbs_per_100g, calories_per_100g, created_at, updated_at) VALUES ('10% Joghurt Natur', 4.7, 110.0, ${System.currentTimeMillis()}, ${System.currentTimeMillis()})")
            }
        }
        
        /**
         * Provides the Room database instance
         */
        @Provides
        @Singleton
        fun provideKetoDatabase(
            @ApplicationContext context: Context
        ): KetoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                KetoDatabase::class.java,
                KetoDatabase.DATABASE_NAME
            )
                .addCallback(object : androidx.room.RoomDatabase.Callback() {
                    override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Database creation callback - can be used for initial data setup
                    }
                })
                .fallbackToDestructiveMigration() // For development - remove in production
                .addCallback(databaseCallback)
                .build()
        }
        
        /**
         * Provides FoodItemDao
         */
        @Provides
        fun provideFoodItemDao(database: KetoDatabase): FoodItemDao {
            return database.foodItemDao()
        }
        
        /**
         * Provides DailyLogDao
         */
        @Provides
        fun provideDailyLogDao(database: KetoDatabase): DailyLogDao {
            return database.dailyLogDao()
        }
        
        /**
         * Provides HealthMetricDao
         */
        @Provides
        fun provideHealthMetricDao(database: KetoDatabase): HealthMetricDao {
            return database.healthMetricDao()
        }
    }
    
    /**
     * Binds FoodItemRepository implementation
     */
    @Binds
    @Singleton
    abstract fun bindFoodItemRepository(
        foodItemRepositoryImpl: FoodItemRepositoryImpl
    ): FoodItemRepository
    
    /**
     * Binds DailyLogRepository implementation
     */
    @Binds
    @Singleton
    abstract fun bindDailyLogRepository(
        dailyLogRepositoryImpl: DailyLogRepositoryImpl
    ): DailyLogRepository
    
    /**
     * Binds HealthMetricRepository implementation
     */
    @Binds
    @Singleton
    abstract fun bindHealthMetricRepository(
        healthMetricRepositoryImpl: HealthMetricRepositoryImpl
    ): HealthMetricRepository
}