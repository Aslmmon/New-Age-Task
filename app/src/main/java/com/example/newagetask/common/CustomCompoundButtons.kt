package com.example.newagetask.common

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.newagetask.R

@SuppressLint("Recycle")
class CustomCompoundButtons @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private val TAG = CustomToolbar::class.simpleName


    init {
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.custom_compound_button,
            this,
            true
        )

        val writtenText = context.obtainStyledAttributes(attrs, R.styleable.CustomCompoundButtons).getString(R.styleable.CustomCompoundButtons_android_text)
        findViewById<TextView>(R.id.tv_custom_button_title).text = writtenText
        val image = context.obtainStyledAttributes(attrs, R.styleable.CustomCompoundButtons).getDrawable(R.styleable.CustomCompoundButtons_android_src)
        findViewById<ImageView>(R.id.image).setImageDrawable(image)


    }
}