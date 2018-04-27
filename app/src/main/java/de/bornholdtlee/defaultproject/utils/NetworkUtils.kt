package de.bornholdtlee.defaultproject.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils(private val context: Context) {

    var isWifiConnected: Boolean = false
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

            return networkInfo.isConnectedOrConnecting
        }

    var isNetworkAvailable: Boolean = false
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
}