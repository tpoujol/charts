package com.github.tehras.charts.bar.renderer.label

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import com.github.tehras.charts.bar.renderer.DrawLocation
import org.jetbrains.skia.Font
import org.jetbrains.skia.Paint
import org.jetbrains.skia.TextLine

actual class SimpleValueDrawer actual constructor(
    actual val drawLocation: DrawLocation,
    actual val labelTextSize: TextUnit,
    actual val labelTextColor: Color
) : LabelDrawer {
    private val _labelTextArea: Float? = null
    private val paint = Paint().apply {
        this.color = labelTextColor.toArgb()
    }


    override fun requiredAboveBarHeight(drawScope: DrawScope): Float = when (drawLocation){
        DrawLocation.Inside -> (3f / 2) * labelTextHeight(drawScope)
        DrawLocation.Outside,
        DrawLocation.XAxis -> 0f
    }

    private fun labelTextHeight(drawScope: DrawScope) = with(drawScope) {
        _labelTextArea ?: ((3f / 2f) * labelTextSize.toPx())
    }

    override fun requiredXAxisHeight(drawScope: DrawScope): Float = when (drawLocation) {
        DrawLocation.Inside -> labelTextHeight(drawScope)
        DrawLocation.Outside,
        DrawLocation.XAxis -> 0f
    }

    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) {
        val txLine = with(drawScope){
            TextLine.make(label, Font(typeface = null, size = labelTextSize.toPx()))
        }
        val xCenter = barArea.left + (barArea.width / 2) - txLine.width / 2

        val yCenter = with(drawScope){
             when (drawLocation) {
                DrawLocation.Inside -> (barArea.top + barArea.bottom) / 2
                DrawLocation.Outside -> (barArea.top) - (labelTextSize.toPx()) / 2
                DrawLocation.XAxis -> barArea.bottom + labelTextHeight(drawScope)
            }
        }



        canvas.nativeCanvas.drawTextLine(txLine, xCenter, yCenter, paint)
    }
}