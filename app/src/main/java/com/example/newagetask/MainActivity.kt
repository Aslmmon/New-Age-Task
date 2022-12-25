package com.example.newagetask

import com.example.newagetask.common.CustomToolbar
import com.example.newagetask.common.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun provideMainLayout() = R.layout.activity_main


    fun changeToolbarName(toolbarTitle: String) {
        findViewById<CustomToolbar>(R.id.custom_toolbar).changeToolbarName(toolbarTitle)
    }

    fun isBackButtonVisible(isVisibile: Boolean) {
        findViewById<CustomToolbar>(R.id.custom_toolbar).setVisibilityOfBackButton(isVisibile)

    }

}