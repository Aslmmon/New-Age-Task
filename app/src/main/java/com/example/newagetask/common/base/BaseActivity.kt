package com.example.newagetask.common.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.newagetask.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import dagger.hilt.android.AndroidEntryPoint


const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
const val NATIVE_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"
const val TAG="ad"

/**
 * Needed to be refactor of ADS to another separated Class
 */
@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    var nativeAd: NativeAd? = null
    private var mInterstitialAd: InterstitialAd? = null


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

    fun showNativeAds(nativeAdLayoutId:Int, frame:FrameLayout){

       val builder =  AdLoader.Builder(this, NATIVE_UNIT_ID)
        builder.forNativeAd {  nativeAd->
            var activityDestroyed = false
            activityDestroyed = isDestroyed
            if (activityDestroyed || isFinishing || isChangingConfigurations) {
                 nativeAd.destroy()
                return@forNativeAd
            }
            this.nativeAd?.destroy()
            this.nativeAd = nativeAd
            val adView = layoutInflater.inflate(nativeAdLayoutId, null,false) as NativeAdView
            populateUnifiedNativeAdView(nativeAd, adView)
            frame.removeAllViews()
            frame.addView(adView)

        }

        val adloader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                val error = """"   domain: ${loadAdError.domain}, code: ${loadAdError.code}, message: ${loadAdError.message}"""
                Toast.makeText(
                    this@BaseActivity, "Failed to load native ad with error $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).build()

        adloader.loadAd(AdRequest.Builder().build())

    }



    private fun listenForEventsOfLoadedAd() {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                Log.e(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                Log.e(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                Log.e(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.e(TAG, "Ad showed fullscreen content.")
            }
        }
    }

    private fun initializeAds() {
        MobileAds.initialize(this) {}
        loadAds()
    }

    private fun populateUnifiedNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)
        (adView.headlineView as TextView).text = nativeAd.headline

        if (nativeAd.body == null) {
            adView.bodyView?.visibility = View.INVISIBLE
        } else {
            adView.bodyView?.visibility = View.VISIBLE
            (adView.advertiserView as TextView).text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView?.visibility = View.INVISIBLE
        } else {
            adView.callToActionView?.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            adView.iconView?.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                nativeAd.icon?.drawable
            )
            adView.iconView?.visibility = View.VISIBLE
        }
        adView.setNativeAd(nativeAd)

    }


    fun showInterstitialAdd(){
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.")
        }
    }

    protected abstract fun provideMainLayout() :Int
    private fun setAppTheme() = setTheme(R.style.Theme_NewAgeTask)

}