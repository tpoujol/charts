package com.github.tehras.charts.bar.renderer.label

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.renderer.DrawLocation

expect class SimpleValueDrawer(
  drawLocation: DrawLocation = DrawLocation.Inside,
  labelTextSize: TextUnit = 12.sp,
  labelTextColor: Color = Color.Black
): LabelDrawer {
  val drawLocation: DrawLocation
  val labelTextSize: TextUnit
  val labelTextColor: Color
}

