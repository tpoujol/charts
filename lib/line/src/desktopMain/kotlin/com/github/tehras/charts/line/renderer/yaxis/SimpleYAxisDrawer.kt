package com.github.tehras.charts.line.renderer.yaxis

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.github.tehras.charts.common.utils.LabelFormatter
import org.jetbrains.skia.Font
import org.jetbrains.skia.TextLine
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

    private val textPaint = Paint().apply {
        this.color = labelTextColor
        this.isAntiAlias = true
    }

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
        val minLabelHeight = (labelTextSize.toPx() * labelRatio.toFloat())
        val totalHeight = drawableArea.height
        val labelCount = max((drawableArea.height / minLabelHeight).roundToInt(), 2)

        // There is no text alignment in compose desktop for the time being, so we first compute
        // the width of the smallest value
        val txLineSmallestValue = TextLine.make(
            labelValueFormatter(minValue),
            Font(null, size = labelTextSize.toPx())
        )
        val smallestValueWidth = txLineSmallestValue.width

        for (i in 0..labelCount) {
            val value = minValue + (i * ((maxValue - minValue) / labelCount))
            val txLine = TextLine.make(
                labelValueFormatter(value),
                Font(null, size = labelTextSize.toPx())
            )

            // For each label, we offset the current value in accordance with the width of the
            // smallest value
            val x = drawableArea.right - axisLineThickness.toPx() - txLine.width -
                    smallestValueWidth * 0.2f


            val y =
                drawableArea.bottom - (i * (totalHeight / labelCount)) + (txLine.height / 2f)

            canvas.nativeCanvas.drawTextLine(txLine, x, y, textPaint.asFrameworkPaint())
        }
    }
}