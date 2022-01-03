package com.github.tehras.charts.common.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.toLegacyInt(): Int = this.toArgb()