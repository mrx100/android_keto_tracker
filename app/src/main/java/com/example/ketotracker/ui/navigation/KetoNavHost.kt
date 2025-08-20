package com.example.ketotracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ketotracker.ui.dashboard.DashboardScreen
import com.example.ketotracker.ui.food.FoodScreen
import com.example.ketotracker.ui.health.HealthScreen
import com.example.ketotracker.ui.charts.ChartsScreen
import com.example.ketotracker.ui.settings.SettingsScreen

/**
 * Navigation host for the main app screens
 */
@Composable
fun KetoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        
        composable(Screen.Food.route) {
            FoodScreen()
        }
        
        composable(Screen.Health.route) {
            HealthScreen()
        }
        
        composable(Screen.Charts.route) {
            ChartsScreen()
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}