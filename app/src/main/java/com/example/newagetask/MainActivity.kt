package com.example.newagetask

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.newagetask.common.CustomToolbar
import com.example.newagetask.common.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun provideMainLayout() = R.layout.activity_main


    fun changeToolbarName(toolbarTitle: String) {
        findViewById<CustomToolbar>(R.id.custom_toolbar).changeToolbarName(toolbarTitle)
    }

    fun checkVisibilityForBackButton() {
        findViewById<CustomToolbar>(R.id.custom_toolbar).setVisibilityOfBackButton(false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}