# Keto Tracker - Installation Guide

## Prerequisites

### System Requirements
- **Android Device**: Android 7.0 (API level 24) or higher
- **RAM**: Minimum 2GB recommended
- **Storage**: At least 50MB free space
- **Permissions**: Storage access for export/import functionality

### Development Requirements (For building from source)
- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **Gradle**: 8.2.1 or newer
- **Kotlin**: 1.9.22 or newer
- **JDK**: Java 8 or newer
- **Android SDK**: API level 34 (target), minimum API level 24

## Installation Options

### Option 1: APK Installation (Recommended for Users)

1. **Download the APK**
   ```bash
   # Download from releases page or build locally
   # File: keto-tracker-v1.0.0.apk
   ```

2. **Enable Unknown Sources** (if needed)
   - Go to Settings > Security
   - Enable "Unknown Sources" or "Install unknown apps"
   - Select your file manager and allow installation

3. **Install the APK**
   - Open the downloaded APK file
   - Tap "Install" when prompted
   - Wait for installation to complete
   - Tap "Open" to launch the app

### Option 2: Build from Source (For Developers)

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/keto-tracker-android.git
   cd keto-tracker-android
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory
   - Select the `android_keto_tracker` folder

3. **Sync Project**
   - Android Studio will automatically sync Gradle
   - Wait for all dependencies to download
   - Resolve any sync issues if prompted

4. **Build the Project**
   ```bash
   # Using command line
   ./gradlew assembleDebug
   
   # Or using Android Studio
   # Build > Make Project (Ctrl+F9)
   ```

5. **Install on Device**
   ```bash
   # Connect Android device with USB debugging enabled
   ./gradlew installDebug
   
   # Or use Android Studio
   # Run > Run 'app' (Shift+F10)
   ```

## First-Time Setup

### 1. Grant Permissions
When first opening the app, you'll be prompted to grant:
- **Storage Permission**: Required for export/import functionality
- These permissions are optional but recommended for full functionality

### 2. Initial Configuration
1. **Set User Preferences**
   - Navigate to Settings tab
   - Enter your height (for BMI calculations)
   - Set target weight (optional)
   - Adjust carb limit if different from 20g

2. **Food Database**
   - The app comes pre-loaded with common keto foods
   - No additional setup required
   - You can add custom foods as needed

### 3. Verify Installation
1. **Check All Tabs**
   - Dashboard: Should show zero values initially
   - Food: Should show food search functionality
   - Health: Should show metric input form
   - Charts: Should show empty charts
   - Settings: Should show all export/import options

2. **Test Basic Functionality**
   - Add a test food entry
   - Check dashboard updates
   - Verify calculations are working

## Troubleshooting

### Common Issues

**Installation Failed**
- Ensure you have sufficient storage space
- Check that Android version is 7.0 or higher
- Try redownloading the APK file

**App Crashes on Startup**
- Clear app data: Settings > Apps > Keto Tracker > Storage > Clear Data
- Restart your device
- Reinstall the application

**Database Issues**
- Clear app data to reset database
- Check available storage space
- Ensure app has storage permissions

**Export/Import Not Working**
- Grant storage permissions
- Check file format (CSV for individual exports, JSON for full backup)
- Ensure sufficient storage space

### Build Issues (Developers)

**Gradle Sync Failed**
```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

**Dependency Issues**
```bash
# Update dependencies
./gradlew --refresh-dependencies
```

**Hilt Compilation Errors**
- Ensure all classes are properly annotated
- Check for circular dependencies
- Clean and rebuild project

**Room Database Issues**
- Verify entity annotations
- Check DAO method signatures
- Ensure proper database version handling

## Performance Optimization

### Recommended Settings
- **Enable Developer Options** (for debugging only)
- **Disable animations** if device is slow
- **Clear cache** periodically

### Storage Management
- **Regular Backups**: Export data periodically
- **Clean Old Data**: Remove unnecessary old entries
- **Monitor Storage**: Keep at least 100MB free space

## Security Considerations

### Data Privacy
- All data is stored locally on your device
- No data is transmitted to external servers
- Export files contain personal health information

### Backup Recommendations
- **Regular Exports**: Export data weekly/monthly
- **Secure Storage**: Store backup files securely
- **Multiple Copies**: Keep backups in multiple locations

## Support

If you encounter issues not covered in this guide:

1. **Check the Documentation**: Review the usage guide
2. **Search Issues**: Look for similar problems in the repository
3. **Create Issue**: Report bugs with detailed steps to reproduce
4. **Community Support**: Join discussions for help from other users

## Next Steps

After successful installation:
1. Read the [Usage Guide](USAGE.md) for detailed feature explanations
2. Explore the app's features systematically
3. Set up your first food entries and health metrics
4. Configure regular data backup routine