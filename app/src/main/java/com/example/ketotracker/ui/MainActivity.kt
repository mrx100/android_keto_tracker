package com.example.ketotracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ketotracker.ui.navigation.BottomNavigationBar
import com.example.ketotracker.ui.navigation.KetoNavHost
import com.example.ketotracker.ui.theme.KetoTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity for the Keto Tracker app
 * Sets up the navigation and theme
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            KetoTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KetoTrackerApp()
                }
            }
        }
    }
}

@Composable
fun KetoTrackerApp() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        KetoNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}