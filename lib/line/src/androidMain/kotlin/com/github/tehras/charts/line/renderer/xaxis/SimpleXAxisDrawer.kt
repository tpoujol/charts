package com.github.tehras.charts.line.renderer.xaxis

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.github.tehras.charts.common.utils.toLegacyInt

actual class SimpleXAxisDrawer actual constructor(
    actual val labelTextSize: TextUnit,
    actual val labelTextColor: Color,
    /** 1 means we draw everything. 2 means we draw every other, and so on. */
    actual val labelRatio: Int,
    actual val axisLineThickness: Dp,
    actual val axisLineColor: Color
) : XAxisDrawer {

    private val axisLinePaint = Paint().apply {
        isAntiAlias = true
        color = axisLineColor
        style = PaintingStyle.Stroke
    }

    private val textPaint = android.graphics.Paint().apply {
        isAntiAlias = true
        color = labelTextColor.toLegacyInt()
    }

    override fun requiredHeight(drawScope: DrawScope): Float {
        return with(drawScope) {
            (3f / 2f) * (labelTextSize.toPx() + axisLineThickness.toPx())
        }
    }

    override fun drawAxisLine(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect
    ) {
        with(drawScope) {
            val lineThickness = axisLineThickness.toPx()
            val y = drawableArea.top + (lineThickness / 2f)

            canvas.drawLine(
                p1 = Offset(
                    x = drawableArea.left,
                    y = y
                ),
                p2 = Offset(
                    x = drawableArea.right,
                    y = y
                ),
                paint = axisLinePaint.apply {
                    strokeWidth = lineThickness
                }
            )
        }
    }

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        labels: List<String>
    ) {
        with(drawScope) {
            val labelPaint = textPaint.apply {
                textSize = labelTextSize.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
            }

            val labelIncrements = drawableArea.width / (labels.size - 1)
            labels.forEachIndexed { index, label ->
                if (index.rem(labelRatio) == 0) {
                    val x = drawableArea.left + (labelIncrements * (index))
                    val y = drawableArea.bottom

                    canvas.nativeCanvas.drawText(label, x, y, labelPaint)
                }
            }
        }
    }
}
