package com.example.newagetask.common

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffTwoDecimalPoints(): Double? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}

fun Context.navigateToGooglePlay() {
    try {
        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/")))
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}

fun Context.getScreenshotFromView(view: View): Bitmap? {
    var screenshot: Bitmap? = null
    try {
        screenshot = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(screenshot)
        canvas.drawColor(Color.WHITE)
        view.draw(canvas)
    } catch (e: Exception) {
        Log.e("error", "Failed to capture screenshot because:" + e.message)
    }
    return screenshot

}

fun Context?.saveBitmapToGallery(bitmap: Bitmap?) {
    val filename = "${System.currentTimeMillis()}.jpg"
    var fos: OutputStream? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this?.contentResolver?.also { resolver ->
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }
    } else {
        // These for devices running on android < Q
        val imagesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File(imagesDir, filename)
        fos = FileOutputStream(image)
    }

    fos?.use {
        // Finally writing the bitmap to the output stream that we opened
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
        Toast.makeText(this, "Captured View and saved to Gallery", Toast.LENGTH_SHORT).show()
    }
}
