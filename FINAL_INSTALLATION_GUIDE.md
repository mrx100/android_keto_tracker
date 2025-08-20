# Keto Tracker Android App - Installation Guide

## Overview
This is a fully functional Android Keto Tracker app based on the original HTML prototypes. The app includes:

- **Working Data Persistence**: All data is saved to Room database and survives app restarts
- **Complete Food Tracking**: Add food items, track quantities, calculate carbs/calories
- **Health Metrics**: Track weight, glucose, ketones, blood pressure with BMI/GKI calculations
- **Real-time Charts**: Visualize your health and nutrition trends
- **Dashboard**: Overview of daily intake and health metrics
- **Settings**: Export/import data, manage preferences

## Key Features Implemented

### ✅ Food Tracking
- Pre-loaded food database with 13 common keto foods
- Add daily food entries with custom quantities
- Real-time carb and calorie calculation
- Visual progress indicator for 20g carb limit
- Delete individual entries or clear all for new day

### ✅ Health Metrics
- Track weight, waist measurements
- Blood glucose and ketone monitoring
- Blood pressure and pulse recording
- Automatic BMI calculation (using 1.73m default height)
- Automatic GKI calculation (Glucose Ketone Index)
- Daily notes support

### ✅ Charts & Analytics
- Weight trend visualization
- Glucose and ketone level charts
- GKI trend tracking
- Blood pressure monitoring
- Daily carb intake graphs
- Interactive charts with date filtering

### ✅ Dashboard
- Today's carb/calorie summary
- Latest health metrics display
- Quick action buttons
- Visual ketosis status indicator

### ✅ Settings
- Profile configuration (height, carb limits)
- Data export/import functionality
- Clear individual data types
- Complete data reset with confirmation
- Theme preferences

## Technical Specifications

### Requirements
- **Target SDK**: 34 (Android 14)
- **Minimum SDK**: 24 (Android 7.0)
- **Architecture**: MVVM with Jetpack Compose
- **Database**: Room with SQLite
- **Dependency Injection**: Hilt
- **Charts**: YCharts library for Compose

### Dependencies (Latest 2025 Versions)
- Jetpack Compose BOM 2024.08.00
- Room 2.6.1
- Hilt 2.51.1
- Navigation Compose 2.7.7
- Kotlin Coroutines 1.8.1
- YCharts 2.1.0 for interactive charts

## Installation Steps

### Prerequisites
1. **Android Studio**: Latest version (Hedgehog or newer)
2. **JDK**: Version 8 or higher
3. **Android SDK**: API level 34 installed
4. **Device/Emulator**: Android 7.0+ (API 24+)

### Build Instructions

1. **Open Project**
   ```bash
   cd /workspace/android_keto_tracker
   # Open in Android Studio or use command line
   ```

2. **Sync Dependencies**
   - Android Studio will automatically sync Gradle
   - Or run: `./gradlew build`

3. **Build APK**
   ```bash
   ./gradlew assembleDebug
   ```
   APK will be generated in: `app/build/outputs/apk/debug/`

4. **Install on Device**
   ```bash
   ./gradlew installDebug
   ```
   Or manually install the APK file

### Build Verification

**Test these features after installation:**

1. **Food Tracking Test**
   - Navigate to Food tab
   - Tap + button to add food
   - Select "Avocado" and enter "100g"
   - Verify it shows 1.8g carbs, 160 calories
   - Check dashboard shows updated totals

2. **Health Metrics Test**
   - Go to Health tab
   - Enter weight: 80kg
   - Enter glucose: 90 mg/dL
   - Enter ketones: 2.0 mmol/L
   - Save and verify GKI calculation shows ~25

3. **Data Persistence Test**
   - Add some food entries and health data
   - Close app completely
   - Reopen app
   - Verify all data is still present

4. **Charts Test**
   - Go to Charts tab
   - Verify charts appear after adding some data
   - Test period filters (Daily/Weekly/Monthly)

## Database Schema

The app uses Room database with these tables:

### food_items
- Primary food database with nutritional info per 100g
- Pre-populated with 13 keto-friendly foods

### daily_logs
- Individual food consumption entries
- Links to food_items with quantity and calculated totals

### health_metrics
- Daily health measurements
- One record per date with all metrics

## Troubleshooting

### Common Issues

1. **Build Errors**
   - Ensure Android SDK 34 is installed
   - Check JDK version compatibility
   - Clean and rebuild: `./gradlew clean build`

2. **App Crashes**
   - Check device API level (minimum 24)
   - Verify Hilt annotation processor is working
   - Check Logcat for specific errors

3. **Charts Not Displaying**
   - Ensure data exists in database
   - Charts require at least 2 data points
   - Check network permissions if using external chart libraries

4. **Database Issues**
   - Clear app data to reset database
   - Default food items will be restored on first run

### Performance Notes

- Database operations use coroutines for smooth UI
- Charts are optimized for up to 50 data points
- Large datasets are paginated automatically
- Background processing for calculations

## App Structure

```
com.example.ketotracker/
├── data/
│   ├── local/ (Room DAOs)
│   ├── model/ (Data entities)
│   └── repository/ (Data layer)
├── di/ (Hilt modules)
├── ui/
│   ├── dashboard/
│   ├── food/
│   ├── health/
│   ├── charts/
│   ├── settings/
│   ├── navigation/
│   └── theme/
└── KetoTrackerApplication.kt
```

## Default Food Database

The app comes pre-loaded with these keto-friendly foods:

| Food Item | Carbs/100g | Calories/100g |
|-----------|------------|---------------|
| Walnüsse | 7.0g | 654 |
| Eier (ganz) | 0.6g | 155 |
| Butter | 0.1g | 717 |
| Rindfleisch (fett) | 0.0g | 250 |
| Hähnchenbrust | 0.0g | 165 |
| Avocado | 1.8g | 160 |
| Brokkoli | 4.4g | 34 |
| Käse (Cheddar) | 1.3g | 403 |
| Olivenöl | 0.0g | 884 |
| Sahne (30%) | 3.0g | 290 |
| Lachs | 0.0g | 208 |
| Magerquark | 3.6g | 67 |
| 10% Joghurt Natur | 4.7g | 110 |

## Success Criteria

✅ **App builds successfully**  
✅ **All screens are functional**  
✅ **Data persists between app restarts**  
✅ **Food tracking with real calculations**  
✅ **Health metrics with BMI/GKI**  
✅ **Interactive charts display**  
✅ **Settings actually work**  
✅ **No placeholder implementations**  

The app is ready for immediate use and testing!

## Contact
For issues or questions about this implementation, refer to the development documentation in the project.