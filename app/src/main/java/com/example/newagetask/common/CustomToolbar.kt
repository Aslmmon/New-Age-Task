package com.example.newagetask.common

import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.newagetask.MainActivity
import com.example.newagetask.R

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private val TAG = CustomToolbar::class.simpleName


    init {
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.custom_toolbar,
                this,
                true
            )
        findViewById<ImageView>(R.id.back_btn).setOnClickListener {
            val navHostFragment = (context as? MainActivity)?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
             navHostFragment?.navController?.popBackStack()
        }
    }
    fun changeToolbarName(toolbar:String){
        findViewById<TextView>(R.id.toolbar).text = toolbar
    }
    fun setVisibilityOfBackButton(visible: Boolean){
        if (visible) findViewById<ImageView>(R.id.back_btn).visibility = View.VISIBLE
        else findViewById<ImageView>(R.id.back_btn).visibility = View.GONE
    }
}