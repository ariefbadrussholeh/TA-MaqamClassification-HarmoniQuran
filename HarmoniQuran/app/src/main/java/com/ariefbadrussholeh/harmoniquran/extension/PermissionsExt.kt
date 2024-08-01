package com.ariefbadrussholeh.harmoniquran.extension

import android.Manifest
import android.os.Build

fun getReadStoragePermission() = when {
    Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ->
        Manifest.permission.READ_EXTERNAL_STORAGE
    else -> Manifest.permission.READ_MEDIA_AUDIO
}