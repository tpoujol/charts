package com.github.tehras.charts.bar.renderer.label

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.TextUnit
import com.github.tehras.charts.bar.renderer.DrawLocation

actual class SimpleValueDrawer actual constructor(
    actual val drawLocation: DrawLocation,
    actual val labelTextSize: TextUnit,
    actual val labelTextColor: Color
) : LabelDrawer {
    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) {
        TODO("Not yet implemented")
    }
}