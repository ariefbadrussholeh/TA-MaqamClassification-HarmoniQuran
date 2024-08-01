package com.ariefbadrussholeh.harmoniquran

import android.app.Application
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HarmoniQuranApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if( !Python.isStarted() ) {
            Python.start( AndroidPlatform( this ) )
        }
    }
}