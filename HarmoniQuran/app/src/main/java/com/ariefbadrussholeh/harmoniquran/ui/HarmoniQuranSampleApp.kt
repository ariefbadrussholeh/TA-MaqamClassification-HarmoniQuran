package com.ariefbadrussholeh.harmoniquran.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ariefbadrussholeh.harmoniquran.navigation.AppNavGraph
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme

@Composable
fun HarmoniQuranSampleApp() {
    HarmoniQuranTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavGraph(navHostController = rememberNavController())
        }
    }
}