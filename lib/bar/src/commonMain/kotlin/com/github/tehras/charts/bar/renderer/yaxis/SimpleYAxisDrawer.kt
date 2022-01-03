package com.github.tehras.charts.bar.renderer.yaxis

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.common.utils.LabelFormatter
import com.github.tehras.charts.common.utils.SimpleFormatter

expect class SimpleYAxisDrawer(
  labelTextSize: TextUnit = 12.sp,
  labelTextColor: Color = Color.Black,
  labelRatio: Int = 3,
  labelValueFormatter: LabelFormatter = SimpleFormatter(),
  axisLineThickness: Dp = 1.dp,
  axisLineColor: Color = Color.Black
) : YAxisDrawer {

  val labelTextSize: TextUnit
  val labelTextColor: Color
  val labelRatio: Int
  val labelValueFormatter: LabelFormatter
  val axisLineThickness: Dp
  val axisLineColor: Color
}