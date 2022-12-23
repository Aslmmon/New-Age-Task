package com.example.newagetask.common

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.newagetask.R

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private val TAG = CustomToolbar::class.simpleName


    init {
        val rootView =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.custom_toolbar,
                this,
                true
            )
//        val backButton = rootView.findViewById<ImageView>(com.paysky.momogrow.R.id.iv_back)
//        val appBarText =
//            rootView.findViewById<TextView>(com.paysky.momogrow.R.id.tv_store_categories)
//        backButton.setOnClickListener {
//            (context as Activity).finish()
//        }
//        val text =
//            context.obtainStyledAttributes(attrs, com.paysky.momogrow.R.styleable.CustomAppBar)
//        appBarText.text = text.getString(com.paysky.momogrow.R.styleable.CustomAppBar_android_text)

    }
}