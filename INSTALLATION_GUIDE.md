# Keto Tracker Android App - Installation Guide

## Quick Start

### Prerequisites
- Android Studio (Latest version recommended)
- Java 11 or higher
- Android device or emulator (API 24+)

### Step 1: Open Project
1. Launch Android Studio
2. Select "Open an Existing Project"
3. Navigate to the `android_keto_tracker` folder
4. Click "OK" and wait for Gradle sync

### Step 2: Build and Run
1. Connect Android device or start emulator
2. Click the "Run" button (green triangle) in Android Studio
3. Select your target device
4. App will install and launch automatically

## Detailed Setup Instructions

### Development Environment Setup

#### Install Android Studio
1. Download from [developer.android.com](https://developer.android.com/studio)
2. Install with default settings
3. Ensure Android SDK is properly configured
4. Install Android SDK Platform 34 (Android 14)

#### Gradle Build
```bash
# Clean build (if needed)
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

### Device Requirements

#### Minimum Requirements
- **OS**: Android 7.0 (API 24) or higher
- **RAM**: 2GB minimum, 4GB recommended
- **Storage**: 50MB free space
- **CPU**: ARM64 or x86_64

#### Recommended Requirements
- **OS**: Android 10 (API 29) or higher
- **RAM**: 4GB or more
- **Storage**: 100MB free space
- **Screen**: 5" or larger for optimal experience

## First Launch Setup

### 1. App Initialization
- App will create local database on first launch
- 13 default keto foods automatically loaded
- No internet connection required

### 2. User Profile Setup
1. Go to Settings tab
2. Enter your height (for BMI calculations)
3. Set carb limit (default: 20g for ketosis)
4. Save preferences

### 3. Start Tracking

#### Add Your First Food Entry
1. Tap **Food** tab at bottom
2. Tap **+** (Add) button
3. Select food from dropdown (e.g., "Avocado")
4. Enter quantity in grams (e.g., "100")
5. Tap **Add** - calories and carbs calculated automatically

#### Record Health Metrics
1. Tap **Health** tab
2. Enter current date (defaults to today)
3. Add measurements:
   - Weight (kg)
   - Blood glucose (mg/dL)
   - Ketones (mmol/L)
   - Blood pressure (optional)
4. BMI and GKI calculated automatically
5. Tap **Save**

### 4. Monitor Progress
- **Dashboard**: View today's totals and ketosis status
- **Charts**: Analyze trends over time
- **Settings**: Export data or adjust preferences

## Features Overview

### 📱 Main Screens
1. **Dashboard**: Overview with ketosis gauge
2. **Food**: Track daily intake
3. **Health**: Record vital metrics
4. **Charts**: Data visualization
5. **Settings**: App configuration

### 🥑 Default Food Database
Pre-loaded with 13 keto-friendly foods:
- Walnüsse (7g carbs, 654 cal)
- Eier/Eggs (0.6g carbs, 155 cal)
- Butter (0.1g carbs, 717 cal)
- Rindfleisch/Beef (0g carbs, 250 cal)
- Hähnchenbrust/Chicken (0g carbs, 165 cal)
- Avocado (1.8g carbs, 160 cal)
- Brokkoli (4.4g carbs, 34 cal)
- Käse/Cheese (1.3g carbs, 403 cal)
- Olivenöl/Olive Oil (0g carbs, 884 cal)
- Sahne/Cream (3g carbs, 290 cal)
- Lachs/Salmon (0g carbs, 208 cal)
- Magerquark (3.6g carbs, 67 cal)
- Joghurt Natur (4.7g carbs, 110 cal)

### 🧮 Automatic Calculations
- **BMI**: Body Mass Index from weight and height
- **GKI**: Glucose Ketone Index for ketosis monitoring
- **Daily Totals**: Carbs and calories from all food entries
- **Ketosis Status**: Visual gauge showing progress toward 20g limit

## Troubleshooting

### Build Issues

#### Gradle Sync Failed
```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

#### OutOfMemory Error
1. In Android Studio: File → Settings → Build → Gradle
2. Set Gradle JVM arguments: `-Xmx4g -XX:MaxPermSize=2g`
3. Restart Android Studio

### Runtime Issues

#### App Crashes on Launch
1. Check Android version (must be API 24+)
2. Clear app data: Settings → Apps → Keto Tracker → Storage → Clear Data
3. Reinstall the app

#### Data Not Saving
1. Check storage permissions
2. Ensure device has sufficient storage space
3. Try force-closing and reopening the app

#### Food Database Empty
1. Clear app data to trigger database recreation
2. Default foods should load automatically
3. If issue persists, reinstall the app

## Data Management

### Local Storage
- All data stored locally on device
- No cloud synchronization required
- Database file: `/data/data/com.example.ketotracker/databases/`

### Backup/Export (Future Feature)
- Export to CSV format
- Import previous data
- Manual backup to external storage

## Performance Tips

### Optimal Usage
- Close unused apps before launching
- Keep device storage above 1GB free
- Update Android OS for best performance

### Battery Optimization
- App uses minimal background processing
- No location tracking or continuous monitoring
- Disable battery optimization if app gets killed frequently

## Support

### Common Questions

**Q: Can I add custom foods?**
A: Food database management planned for future version

**Q: Does the app work offline?**
A: Yes, completely offline. No internet required.

**Q: How accurate are the calculations?**
A: BMI and GKI use standard medical formulas. Food data from nutritional databases.

**Q: Can I track multiple days?**
A: Yes, all historical data is saved and accessible.

### Getting Help
- Check this installation guide first
- Review troubleshooting section
- Ensure device meets minimum requirements
- Try clean install if issues persist

---

**Version**: 1.0.0  
**Last Updated**: August 2025  
**Compatible**: Android 7.0+ (API 24-34)