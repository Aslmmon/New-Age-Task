package com.example.newagetask.common.google_ads

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.*
import com.example.newagetask.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import javax.inject.Inject

const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
const val NATIVE_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"
const val TAG = "ad"

class GoogleAds @Inject constructor() {


    var nativeAdL: NativeAd? = null
    private var mInterstitialAd: InterstitialAd? = null

    fun initializeMobileAdsAndInterstitial(context: Context) {
        MobileAds.initialize(context) {}
        loadInterstitialAd(context)

    }

    private fun loadInterstitialAd(context: Context) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, AD_UNIT_ID, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                listenForEventsOfLoadedAd(context)
            }
        })
    }


    fun showInterstitialAdd(context: Context) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(context as Activity)
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.")
        }
    }

    private fun listenForEventsOfLoadedAd(context: Context) {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                Log.e(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                Log.e(TAG, "Ad dismissed fullscreen content.")
                /**
                 * set  mInterstitialAd = null if needed to Show Interstitial Ad Once and comment loadInterstitialAd
                 */
                // mInterstitialAd = null
                loadInterstitialAd(context = context)

            }

            override fun onAdImpression() {
                Log.e(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                mInterstitialAd = null
                Log.e(TAG, "Ad showed fullscreen content.")
            }
        }
    }

    fun showNativeAds(nativeAdLayoutId: Int, frame: FrameLayout, context: Context) {
        with(context as Activity) {
            val builder = AdLoader.Builder(context, NATIVE_UNIT_ID)
            builder.forNativeAd { nativeAd ->
                var activityDestroyed = false
                activityDestroyed = isDestroyed
                if (activityDestroyed || isFinishing || isChangingConfigurations) {
                    nativeAd.destroy()
                    return@forNativeAd
                }
                nativeAdL?.destroy()
                nativeAdL = nativeAd
                val adView = layoutInflater.inflate(nativeAdLayoutId, null, false) as NativeAdView
                populateUnifiedNativeAdView(nativeAd, adView)
                frame.removeAllViews()
                frame.addView(adView)

            }

            val adloader = builder.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    val error =
                        """"   domain: ${loadAdError.domain}, code: ${loadAdError.code}, message: ${loadAdError.message}"""
                }
            }).build()

            adloader.loadAd(AdRequest.Builder().build())
        }

    }


    private fun populateUnifiedNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        /**
         * populating native Ad View
         */
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
}