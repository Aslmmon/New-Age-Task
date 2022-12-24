package com.example.newagetask.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffTwoDecimalPoints(): Double? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}

fun Context.navigateToGooglePlay(){
    try {
        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/")))
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
    }
}