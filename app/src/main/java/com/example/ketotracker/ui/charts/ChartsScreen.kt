package com.example.ketotracker.ui.charts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Charts screen for visualizing health and food tracking data
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartsScreen(
    viewModel: ChartsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Charts & Analytics",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        // Time Period Selector
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Time Period",
                    style = MaterialTheme.typography.titleMedium
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = uiState.selectedPeriod == "Daily",
                        onClick = { viewModel.updatePeriod("Daily") },
                        label = { Text("Daily") },
                        modifier = Modifier.weight(1f)
                    )
                    
                    FilterChip(
                        selected = uiState.selectedPeriod == "Weekly",
                        onClick = { viewModel.updatePeriod("Weekly") },
                        label = { Text("Weekly") },
                        modifier = Modifier.weight(1f)
                    )
                    
                    FilterChip(
                        selected = uiState.selectedPeriod == "Monthly",
                        onClick = { viewModel.updatePeriod("Monthly") },
                        label = { Text("Monthly") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Weight & BMI Chart
            if (uiState.weightData.isNotEmpty()) {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Weight Trend",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        WeightChart(
                            weightData = uiState.weightData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
            
            // Glucose & Ketones Chart
            if (uiState.glucoseData.isNotEmpty() || uiState.ketoneData.isNotEmpty()) {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Glucose & Ketones",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        GlucoseKetonesChart(
                            glucoseData = uiState.glucoseData,
                            ketoneData = uiState.ketoneData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
            
            // GKI Chart
            if (uiState.gkiData.isNotEmpty()) {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "GKI (Glucose Ketone Index)",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        GKIChart(
                            gkiData = uiState.gkiData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
            
            // Blood Pressure Chart
            if (uiState.systolicData.isNotEmpty() || uiState.diastolicData.isNotEmpty()) {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Blood Pressure Trend",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        BloodPressureChart(
                            systolicData = uiState.systolicData,
                            diastolicData = uiState.diastolicData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
            
            // Carb Intake Chart
            if (uiState.carbData.isNotEmpty()) {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Daily Carb Intake",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        CarbIntakeChart(
                            carbData = uiState.carbData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
            
            // Show message if no data
            if (uiState.weightData.isEmpty() && 
                uiState.glucoseData.isEmpty() && 
                uiState.ketoneData.isEmpty() && 
                uiState.carbData.isEmpty()) {
                Card {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No data available yet.\nStart tracking your health metrics and food intake!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WeightChart(
    weightData: List<Pair<String, Double>>,
    modifier: Modifier = Modifier
) {
    if (weightData.isEmpty()) return
    
    val points = weightData.mapIndexed { index, (date, weight) ->
        Point(index.toFloat(), weight.toFloat())
    }
    
    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(Color.Transparent)
        .steps(weightData.size - 1)
        .labelData { i -> 
            if (i < weightData.size) {
                val date = LocalDate.parse(weightData[i].first)
                date.format(DateTimeFormatter.ofPattern("MM/dd"))
            } else ""
        }
        .labelAndAxisLinePadding(15.dp)
        .build()
    
    val yAxisData = AxisData.Builder()
        .steps(5)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val minWeight = weightData.minOf { it.second }
            val maxWeight = weightData.maxOf { it.second }
            val range = maxWeight - minWeight
            val value = minWeight + (range * i / 5)
            "${value.toInt()}kg"
        }
        .build()
    
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    LineStyle(color = androidx.compose.ui.graphics.Color.Blue),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.Transparent
    )
    
    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}

@Composable
fun GlucoseKetonesChart(
    glucoseData: List<Pair<String, Double>>,
    ketoneData: List<Pair<String, Double>>,
    modifier: Modifier = Modifier
) {
    if (glucoseData.isEmpty() && ketoneData.isEmpty()) return
    
    // Combine and sort data by date
    val allDates = (glucoseData.map { it.first } + ketoneData.map { it.first }).distinct().sorted()
    
    val glucosePoints = allDates.mapIndexedNotNull { index, date ->
        glucoseData.find { it.first == date }?.let { Point(index.toFloat(), it.second.toFloat()) }
    }
    
    val ketonePoints = allDates.mapIndexedNotNull { index, date ->
        ketoneData.find { it.first == date }?.let { Point(index.toFloat(), (it.second * 20).toFloat()) } // Scale ketones for visibility
    }
    
    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(Color.Transparent)
        .steps(allDates.size - 1)
        .labelData { i -> 
            if (i < allDates.size) {
                val date = LocalDate.parse(allDates[i])
                date.format(DateTimeFormatter.ofPattern("MM/dd"))
            } else ""
        }
        .labelAndAxisLinePadding(15.dp)
        .build()
    
    val yAxisData = AxisData.Builder()
        .steps(5)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val maxGlucose = if (glucoseData.isNotEmpty()) glucoseData.maxOf { it.second } else 0.0
            val value = (maxGlucose * i / 5).toInt()
            "${value}"
        }
        .build()
    
    val lines = mutableListOf<Line>()
    
    if (glucosePoints.isNotEmpty()) {
        lines.add(
            Line(
                dataPoints = glucosePoints,
                LineStyle(color = androidx.compose.ui.graphics.Color.Red),
                IntersectionPoint(),
                SelectionHighlightPoint(),
                ShadowUnderLine(),
                SelectionHighlightPopUp()
            )
        )
    }
    
    if (ketonePoints.isNotEmpty()) {
        lines.add(
            Line(
                dataPoints = ketonePoints,
                LineStyle(color = androidx.compose.ui.graphics.Color.Green),
                IntersectionPoint(),
                SelectionHighlightPoint(),
                ShadowUnderLine(),
                SelectionHighlightPopUp()
            )
        )
    }
    
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(lines = lines),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.Transparent
    )
    
    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}

@Composable
fun GKIChart(
    gkiData: List<Pair<String, Double>>,
    modifier: Modifier = Modifier
) {
    if (gkiData.isEmpty()) return
    
    val points = gkiData.mapIndexed { index, (date, gki) ->
        Point(index.toFloat(), gki.toFloat())
    }
    
    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(Color.Transparent)
        .steps(gkiData.size - 1)
        .labelData { i -> 
            if (i < gkiData.size) {
                val date = LocalDate.parse(gkiData[i].first)
                date.format(DateTimeFormatter.ofPattern("MM/dd"))
            } else ""
        }
        .labelAndAxisLinePadding(15.dp)
        .build()
    
    val yAxisData = AxisData.Builder()
        .steps(5)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val maxGKI = gkiData.maxOf { it.second }
            val value = (maxGKI * i / 5).toInt()
            "$value"
        }
        .build()
    
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    LineStyle(color = androidx.compose.ui.graphics.Color.Magenta),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.Transparent
    )
    
    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}

@Composable
fun BloodPressureChart(
    systolicData: List<Pair<String, Int>>,
    diastolicData: List<Pair<String, Int>>,
    modifier: Modifier = Modifier
) {
    if (systolicData.isEmpty() && diastolicData.isEmpty()) return
    
    val allDates = (systolicData.map { it.first } + diastolicData.map { it.first }).distinct().sorted()
    
    val systolicPoints = allDates.mapIndexedNotNull { index, date ->
        systolicData.find { it.first == date }?.let { Point(index.toFloat(), it.second.toFloat()) }
    }
    
    val diastolicPoints = allDates.mapIndexedNotNull { index, date ->
        diastolicData.find { it.first == date }?.let { Point(index.toFloat(), it.second.toFloat()) }
    }
    
    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(Color.Transparent)
        .steps(allDates.size - 1)
        .labelData { i -> 
            if (i < allDates.size) {
                val date = LocalDate.parse(allDates[i])
                date.format(DateTimeFormatter.ofPattern("MM/dd"))
            } else ""
        }
        .labelAndAxisLinePadding(15.dp)
        .build()
    
    val yAxisData = AxisData.Builder()
        .steps(5)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val value = 60 + (i * 20) // Scale from 60 to 160
            "$value"
        }
        .build()
    
    val lines = mutableListOf<Line>()
    
    if (systolicPoints.isNotEmpty()) {
        lines.add(
            Line(
                dataPoints = systolicPoints,
                LineStyle(color = androidx.compose.ui.graphics.Color.Red),
                IntersectionPoint(),
                SelectionHighlightPoint(),
                ShadowUnderLine(),
                SelectionHighlightPopUp()
            )
        )
    }
    
    if (diastolicPoints.isNotEmpty()) {
        lines.add(
            Line(
                dataPoints = diastolicPoints,
                LineStyle(color = androidx.compose.ui.graphics.Color.Blue),
                IntersectionPoint(),
                SelectionHighlightPoint(),
                ShadowUnderLine(),
                SelectionHighlightPopUp()
            )
        )
    }
    
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(lines = lines),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.Transparent
    )
    
    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}

@Composable
fun CarbIntakeChart(
    carbData: List<Pair<String, Double>>,
    modifier: Modifier = Modifier
) {
    if (carbData.isEmpty()) return
    
    val points = carbData.mapIndexed { index, (date, carbs) ->
        Point(index.toFloat(), carbs.toFloat())
    }
    
    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(Color.Transparent)
        .steps(carbData.size - 1)
        .labelData { i -> 
            if (i < carbData.size) {
                val date = LocalDate.parse(carbData[i].first)
                date.format(DateTimeFormatter.ofPattern("MM/dd"))
            } else ""
        }
        .labelAndAxisLinePadding(15.dp)
        .build()
    
    val yAxisData = AxisData.Builder()
        .steps(5)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val maxCarbs = carbData.maxOf { it.second }.coerceAtLeast(20.0)
            val value = (maxCarbs * i / 5).toInt()
            "${value}g"
        }
        .build()
    
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    LineStyle(color = androidx.compose.ui.graphics.Color.Cyan),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.Transparent
    )
    
    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}