# Keto Tracker - Usage Guide

A comprehensive guide to using all features of the Keto Tracker Android app.

## Quick Start

### First Steps
1. **Install the app** following the [Installation Guide](INSTALLATION.md)
2. **Grant storage permissions** when prompted
3. **Set your preferences** in the Settings tab
4. **Add your first food entry** to test the system
5. **Record health metrics** to start tracking progress

## Main Interface

### Bottom Navigation
The app uses a bottom navigation bar with five main sections:
- 🏠 **Dashboard**: Overview and quick actions
- 🍽️ **Food**: Daily food tracking
- 📊 **Health**: Health metrics entry
- 📈 **Charts**: Data visualization
- ⚙️ **Settings**: Configuration and data management

## 🏠 Dashboard

### Overview
The dashboard provides a quick overview of your current status and key metrics.

### Features

**Ketosis Status Card**
- Displays current ketosis status based on carb intake
- Shows visual gauge with carb consumption vs 20g limit
- Color-coded status:
  - 🟢 Green: In ketosis (≤20g carbs)
  - 🟡 Yellow: Close to limit (20-30g carbs)
  - 🔴 Red: Over limit (>30g carbs)

**Today's Summary**
- Total carbs consumed today
- Total calories consumed today
- Updates automatically when you add food entries

**Latest Health Metrics**
- Most recent weight and BMI
- Latest ketone levels and GKI
- Quick overview of your progress

**Quick Actions**
- "Add Food" button → navigates to Food tab
- "Add Health Metric" → navigates to Health tab

### Usage Tips
- Check dashboard first thing each morning
- Use quick action buttons for fast data entry
- Monitor ketosis gauge throughout the day

## 🍽️ Food Tracking

### Daily Food Log

**Date Selection**
- Tap the date button to select different days
- Defaults to current day
- Can track food for past or future dates

**Adding Food Entries**
1. **Search for Food**
   - Type in the search field
   - Select from dropdown suggestions
   - Uses pre-loaded food database

2. **Set Quantity**
   - Enter amount in grams
   - Defaults to 100g
   - Supports decimal values (e.g., 85.5g)

3. **Review Calculations**
   - Carbs and calories calculated automatically
   - Based on per-100g values from database
   - Updates in real-time as you change quantity

4. **Add to Log**
   - Tap "Add Food" button
   - Entry appears in daily log immediately
   - Daily totals update automatically

**Managing Food Entries**
- **Edit**: Tap pencil icon to modify quantity
- **Delete**: Tap trash icon to remove entry
- **Clear All**: Remove all entries for the day

### Food Database

**Pre-loaded Foods**
The app includes common keto foods:
- Nuts (walnuts, almonds, etc.)
- Proteins (eggs, chicken, beef, fish)
- Fats (butter, oils, avocado)
- Low-carb vegetables (broccoli, spinach)
- Dairy (cheese, cream)

**Adding Custom Foods**
- Currently handled through import functionality
- Plan to add in-app food creation in future updates

**Data Export/Import**
- Export current food database to CSV
- Import foods from CSV file
- Useful for sharing between devices or backing up

### Tips for Accurate Tracking
- **Weigh Your Food**: Use a kitchen scale for accuracy
- **Check Serving Sizes**: Convert to grams if needed
- **Log Immediately**: Don't wait until end of day
- **Include Everything**: Even small amounts add up
- **Double-check Entries**: Verify calculations look correct

## 📊 Health Metrics

### Recording Health Data

**Date Selection**
- Tap date field to select measurement date
- One entry per date (updates existing if present)

**Metrics You Can Track**
- **Weight (kg)**: Body weight
- **Waist (cm)**: Waist circumference
- **Glucose (mg/dL)**: Blood glucose level
- **Ketones (mmol/L)**: Blood or urine ketone level
- **Blood Pressure**: Systolic and diastolic
- **Pulse (bpm)**: Heart rate
- **Notes**: Additional observations

**Automatic Calculations**
- **BMI**: Calculated from weight and height (set in preferences)
- **GKI**: Glucose Ketone Index from glucose and ketone values
- Updates in real-time as you enter values

### Understanding Your Numbers

**BMI Categories**
- <18.5: Underweight
- 18.5-24.9: Normal weight
- 25.0-29.9: Overweight
- ≥30.0: Obese

**GKI Interpretation**
- <1.0: Therapeutic ketosis
- 1.0-3.0: Optimal ketosis
- 3.0-6.0: Light ketosis
- 6.0-9.0: Low ketosis
- >9.0: Not in ketosis

**Ketone Levels**
- 0.0-0.5 mmol/L: Not in ketosis
- 0.5-1.5 mmol/L: Light ketosis
- 1.5-3.0 mmol/L: Optimal ketosis
- >3.0 mmol/L: High ketosis (monitor carefully)

### Best Practices
- **Consistent Timing**: Measure at same time daily
- **Morning Measurements**: Most consistent results
- **Track Trends**: Focus on patterns, not daily fluctuations
- **Include Context**: Use notes for relevant information

## 📈 Charts and Analytics

### Available Charts
- **Weight Progress**: Track weight loss/gain over time
- **BMI Trend**: Monitor BMI changes
- **Glucose Levels**: Blood sugar patterns
- **Ketone Levels**: Ketosis consistency
- **GKI Trend**: Combined glucose/ketone metric
- **Blood Pressure**: Cardiovascular health

### Time Periods
- **Daily**: Individual daily values
- **Weekly**: Weekly averages
- **Monthly**: Monthly averages

### Chart Features
- **Interactive**: Tap points for specific values
- **Zoom**: Pinch to zoom in/out
- **Pan**: Drag to scroll through time periods
- **Legend**: Toggle data series on/off

### Interpreting Your Data
- **Look for Trends**: Overall direction more important than daily fluctuations
- **Correlate Metrics**: Compare weight loss with ketone levels
- **Identify Patterns**: Weekly cycles, monthly trends
- **Set Goals**: Use charts to track progress toward targets

## ⚙️ Settings and Data Management

### User Preferences

**Personal Information**
- **Height**: Used for BMI calculations
- **Target Weight**: Goal setting (optional)
- **Carb Limit**: Daily carbohydrate limit (default: 20g)

**How to Update**
1. Go to Settings tab
2. Scroll to "User Preferences" section
3. Modify values as needed
4. Tap "Save" to apply changes

### Data Export

**Food Database Export**
- **Format**: CSV file
- **Content**: All foods with nutritional information
- **Use Cases**: Backup, sharing, external analysis

**Health Metrics Export**
- **Format**: CSV file
- **Content**: All recorded health metrics with dates
- **Includes**: Calculated BMI and GKI values

**Complete Data Export**
- **Format**: JSON file
- **Content**: All app data (foods, entries, metrics)
- **Use Cases**: Full backup, device migration

**Export Process**
1. Navigate to Settings tab
2. Tap desired export option
3. Choose save location
4. File saved to device storage

### Data Import

**Supported Formats**
- **CSV**: For individual data types
- **JSON**: For complete app data

**Import Process**
1. Navigate to Settings tab
2. Tap desired import option
3. Select file from device storage
4. Confirm import operation
5. Data merged with existing information

**Import Considerations**
- **Backup First**: Export current data before importing
- **File Format**: Ensure correct CSV/JSON format
- **Data Validation**: Invalid entries are skipped
- **Duplicate Handling**: Existing data may be overwritten

### Data Management Best Practices

**Regular Backups**
- Export data weekly or monthly
- Store backups in multiple locations
- Test restore process periodically

**Data Cleanup**
- Review and delete test entries
- Remove duplicate or incorrect data
- Keep notes updated and relevant

**Privacy and Security**
- Data stored locally on device only
- Export files contain personal information
- Secure backup file storage recommended

## Advanced Features

### Bulk Operations
- **Clear All Foods**: Remove all entries for a specific day
- **Clear All Data**: Reset entire app (use with caution)

### Data Analysis
- **Export to Spreadsheet**: Analyze data in Excel/Google Sheets
- **Correlation Analysis**: Compare different metrics
- **Progress Reports**: Generate custom reports

## Troubleshooting

### Common Issues

**Incorrect Calculations**
- Verify food database values
- Check quantity units (grams)
- Ensure height is set correctly for BMI

**Missing Data**
- Check selected date
- Verify data entry was saved
- Look for data in export files

**Performance Issues**
- Clear app cache
- Restart application
- Free up device storage

**Export/Import Problems**
- Check file permissions
- Verify file format
- Ensure sufficient storage space

### Getting Help

1. **Check Documentation**: Review this guide thoroughly
2. **Search Issues**: Look for similar problems online
3. **Contact Support**: Report bugs or request features
4. **Community Forums**: Connect with other users

## Tips for Success

### Daily Routine
1. **Morning**: Check dashboard, record weight if measuring
2. **Throughout Day**: Log food immediately after eating
3. **Evening**: Review daily totals, add health metrics
4. **Weekly**: Check charts for progress trends

### Consistency Tips
- **Set Reminders**: Use phone alarms for measurements
- **Make it Habit**: Log food before eating
- **Stay Honest**: Record everything, even "cheats"
- **Focus on Trends**: Don't stress over daily fluctuations

### Maximizing Benefits
- **Regular Monitoring**: Check ketosis status frequently
- **Data Analysis**: Use charts to identify patterns
- **Goal Setting**: Set realistic, measurable targets
- **Celebrate Progress**: Acknowledge improvements

## Frequently Asked Questions

**Q: How accurate are the carb counts?**
A: Values are based on USDA nutritional data, but can vary by brand and preparation method.

**Q: Can I track net carbs instead of total carbs?**
A: The app currently tracks total carbs. You can manually adjust values if needed.

**Q: What if I can't find a food in the database?**
A: You can import custom foods via CSV or use the closest available option.

**Q: How often should I weigh myself?**
A: Daily weighing provides more data points, but weekly can reduce stress from fluctuations.

**Q: Is my data private?**
A: Yes, all data is stored locally on your device only.

**Q: Can I use this app for other diets?**
A: While designed for keto, it can track any low-carb diet by adjusting the carb limit.

For additional questions or support, please refer to the project documentation or contact support through the appropriate channels.