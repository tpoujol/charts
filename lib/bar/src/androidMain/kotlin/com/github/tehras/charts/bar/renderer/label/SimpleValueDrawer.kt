package com.github.tehras.charts.bar.renderer.label

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.TextUnit
import com.github.tehras.charts.bar.renderer.DrawLocation
import com.github.tehras.charts.common.utils.toLegacyInt

actual class SimpleValueDrawer actual constructor(
    actual val drawLocation: DrawLocation,
    actual val labelTextSize: TextUnit,
    actual val labelTextColor: Color
) : LabelDrawer {
    private val _labelTextArea: Float? = null
    private val paint = android.graphics.Paint().apply {
        this.textAlign = android.graphics.Paint.Align.CENTER
        this.color = labelTextColor.toLegacyInt()
    }

    override fun requiredAboveBarHeight(drawScope: DrawScope): Float = when (drawLocation) {
        DrawLocation.Outside -> (3f / 2f) * labelTextHeight(drawScope)
        DrawLocation.Inside,
        DrawLocation.XAxis -> 0f
    }

    override fun requiredXAxisHeight(drawScope: DrawScope): Float = when (drawLocation) {
        DrawLocation.XAxis -> labelTextHeight(drawScope)
        DrawLocation.Inside,
        DrawLocation.Outside -> 0f
    }

    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) = with(drawScope) {
        val xCenter = barArea.left + (barArea.width / 2)

        val yCenter = when (drawLocation) {
            DrawLocation.Inside -> (barArea.top + barArea.bottom) / 2
            DrawLocation.Outside -> (barArea.top) - labelTextSize.toPx() / 2
            DrawLocation.XAxis -> barArea.bottom + labelTextHeight(drawScope)
        }

        canvas.nativeCanvas.drawText(label, xCenter, yCenter, paint(drawScope))
    }

    private fun labelTextHeight(drawScope: DrawScope) = with(drawScope) {
        _labelTextArea ?: ((3f / 2f) * labelTextSize.toPx())
    }

    private fun paint(drawScope: DrawScope) = with(drawScope) {
        paint.apply {
            this.textSize = labelTextSize.toPx()
        }
    }
}