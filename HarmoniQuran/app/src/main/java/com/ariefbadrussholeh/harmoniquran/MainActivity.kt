package com.ariefbadrussholeh.harmoniquran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ariefbadrussholeh.harmoniquran.ui.HarmoniQuranSampleApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarmoniQuranSampleApp()
        }
    }
}