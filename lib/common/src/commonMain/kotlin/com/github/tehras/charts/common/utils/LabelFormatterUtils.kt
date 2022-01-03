package com.github.tehras.charts.common.utils

import kotlin.math.round

/* We are forced to do this convoluted way with an interface and a class because there is a bug
in passing default argument to function parameters with the expect/actual system:
https://youtrack.jetbrains.com/issue/KT-45542
 */
fun interface LabelFormatter {
    operator fun invoke(value: Float): String
}

/**
 * This formatter will take a float value and return a String with the value rounded to the first
 * digit
 */
class SimpleFormatter: LabelFormatter {
    override fun invoke(value: Float): String {
        return "${round(value * 10) / 10}"
    }
}