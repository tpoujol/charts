package com.github.tehras.charts.line.renderer.xaxis

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

expect class SimpleXAxisDrawer(
  labelTextSize: TextUnit = 12.sp,
  labelTextColor: Color = Color.Black,
  /** 1 means we draw everything. 2 means we draw every other, and so on. */
  labelRatio: Int = 1,
  axisLineThickness: Dp = 1.dp,
  axisLineColor: Color = Color.Black
) : XAxisDrawer {
  val labelTextSize: TextUnit
  val labelTextColor: Color
  /** 1 means we draw everything. 2 means we draw every other, and so on. */
  val labelRatio: Int
  val axisLineThickness: Dp
  val axisLineColor: Color

}