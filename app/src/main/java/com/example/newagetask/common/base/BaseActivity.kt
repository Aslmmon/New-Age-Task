package com.example.newagetask.common.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.newagetask.R
import com.example.newagetask.common.google_ads.GoogleAds
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Needed to be refactor of ADS to another separated Class
 */
@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var googleAds: GoogleAds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppTheme()
        setContentView(provideMainLayout())
        googleAds.initializeMobileAdsAndInterstitial(this)

    }


    fun showNativeAds(nativeAdLayoutId: Int, frame: FrameLayout) =
        googleAds.showNativeAds(nativeAdLayoutId, frame, this)


    fun showInterstitialAdd() = googleAds.showInterstitialAdd(context = this)

    protected abstract fun provideMainLayout(): Int
    private fun setAppTheme() = setTheme(R.style.Theme_NewAgeTask)

}