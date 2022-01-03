package com.github.tehras.charts.line.renderer.xaxis

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

actual class SimpleXAxisDrawer actual constructor(
    actual val labelTextSize: TextUnit,
    actual val labelTextColor: Color,
    /** 1 means we draw everything. 2 means we draw every other, and so on. */
    actual val labelRatio: Int,
    actual val axisLineThickness: Dp,
    actual val axisLineColor: Color
) : XAxisDrawer {
    override fun requiredHeight(drawScope: DrawScope): Float {
        TODO("Not yet implemented")
    }

    override fun drawAxisLine(drawScope: DrawScope, canvas: Canvas, drawableArea: Rect) {
        TODO("Not yet implemented")
    }

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        labels: List<String>
    ) {
        TODO("Not yet implemented")
    }
}