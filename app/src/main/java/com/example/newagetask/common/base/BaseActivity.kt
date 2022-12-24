package com.example.newagetask.common.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.newagetask.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAdView
import dagger.hilt.android.AndroidEntryPoint


const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
const val NATIVE_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private var adLoader:AdLoader?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppTheme()
        setContentView(provideMainLayout())

        initializeAds()

    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, AD_UNIT_ID, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
        listenForEventsOfLoadedAd()
    }

    fun showNativeAds(nativeAdLayoutId:Int){
        adLoader = AdLoader.Builder(this, NATIVE_UNIT_ID)
            .forNativeAd { nativeAd ->
                // Assumes that your ad layout is in a file call native_ad_layout.xml
                // in the res/layout folder
                if (isDestroyed){
                    nativeAd.destroy()
                    return@forNativeAd
                }
                val adView = layoutInflater
                    .inflate(nativeAdLayoutId, null) as NativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
//                populateNativeAdView(nativeAd, adView)
//                // Assumes you have a placeholder FrameLayout in your View layout
//                // (with id ad_frame) where the ad is to be placed.
//                ad_frame.removeAllViews()
//                ad_frame.addView(adView)
            }.build()
        adLoader?.loadAd(AdRequest.Builder().build())
    }



    private fun listenForEventsOfLoadedAd() {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.e("TAG", "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.e("TAG", "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.e("TAG", "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.e("TAG", "Ad showed fullscreen content.")
            }
        }
    }

    private fun initializeAds() {
        MobileAds.initialize(this) {}
        loadAds()
    }

    fun showInterstitialAdd(){
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
    }

    fun stopInterstitialAdd(){
        if (mInterstitialAd != null) {
            mInterstitialAd
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
    }

    protected abstract fun provideMainLayout() :Int
    private fun setAppTheme() = setTheme(R.style.Theme_NewAgeTask)

}