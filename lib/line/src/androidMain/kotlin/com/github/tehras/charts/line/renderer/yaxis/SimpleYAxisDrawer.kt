package com.github.tehras.charts.line.renderer.yaxis

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.github.tehras.charts.common.utils.LabelFormatter
import com.github.tehras.charts.common.utils.toLegacyInt
import kotlin.math.max
import kotlin.math.roundToInt

actual class SimpleYAxisDrawer actual constructor(
    actual val labelTextSize: TextUnit,
    actual val labelTextColor: Color,
    actual val labelRatio: Int,
    actual val labelValueFormatter: LabelFormatter,
    actual val axisLineThickness: Dp,
    actual val axisLineColor: Color
) : YAxisDrawer {

    private val axisLinePaint = Paint().apply {
        isAntiAlias = true
        color = axisLineColor
        style = PaintingStyle.Stroke
    }
    private val textPaint = android.graphics.Paint().apply {
        isAntiAlias = true
        color = labelTextColor.toLegacyInt()
    }
    private val textBounds = android.graphics.Rect()

    override fun drawAxisLine(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect
    ) = with(drawScope) {
        val lineThickness = axisLineThickness.toPx()
        val x = drawableArea.right - (lineThickness / 2f)

        canvas.drawLine(
            p1 = Offset(
                x = x,
                y = drawableArea.top
            ),
            p2 = Offset(
                x = x,
                y = drawableArea.bottom
            ),
            paint = axisLinePaint.apply {
                strokeWidth = lineThickness
            }
        )
    }

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float
    ) = with(drawScope) {
        val labelPaint = textPaint.apply {
            textSize = labelTextSize.toPx()
            textAlign = android.graphics.Paint.Align.RIGHT
        }
        val minLabelHeight = (labelTextSize.toPx() * labelRatio.toFloat())
        val totalHeight = drawableArea.height
        val labelCount = max((drawableArea.height / minLabelHeight).roundToInt(), 2)

        for (i in 0..labelCount) {
            val value = minValue + (i * ((maxValue - minValue) / labelCount))

            val label = labelValueFormatter(value)
            val x =
                drawableArea.right - axisLineThickness.toPx() - (labelTextSize.toPx() / 2f)

            labelPaint.getTextBounds(label, 0, label.length, textBounds)

            val y =
                drawableArea.bottom - (i * (totalHeight / labelCount)) + (textBounds.height() / 2f)

            canvas.nativeCanvas.drawText(label, x, y, labelPaint)
        }
    }
}