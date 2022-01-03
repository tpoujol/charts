package com.github.tehras.charts.bar.renderer.yaxis

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.github.tehras.charts.common.utils.LabelFormatter

actual class SimpleYAxisDrawer actual constructor(
    actual val labelTextSize: TextUnit,
    actual val labelTextColor: Color,
    actual val labelRatio: Int,
    actual val labelValueFormatter: LabelFormatter,
    actual val axisLineThickness: Dp,
    actual val axisLineColor: Color
) : YAxisDrawer {
    override fun drawAxisLine(drawScope: DrawScope, canvas: Canvas, drawableArea: Rect) {

    }

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float
    ) {
        
    }

}