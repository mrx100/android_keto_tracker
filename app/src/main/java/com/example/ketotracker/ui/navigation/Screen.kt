package com.example.ketotracker.ui.navigation

/**
 * Navigation routes for the app
 */
sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Food : Screen("food")
    object Health : Screen("health")
    object Charts : Screen("charts")
    object Settings : Screen("settings")
    
    companion object {
        fun fromRoute(route: String?): Screen {
            return when (route?.substringBefore("?")) {
                Dashboard.route -> Dashboard
                Food.route -> Food
                Health.route -> Health
                Charts.route -> Charts
                Settings.route -> Settings
                null -> Dashboard
                else -> Dashboard
            }
        }
    }
}