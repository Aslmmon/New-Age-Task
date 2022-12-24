package com.example.newagetask.common

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffTwoDecimalPoints(): Double? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}