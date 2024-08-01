package com.ariefbadrussholeh.harmoniquran.ui.screen.audiomaqampreview.model

data class AudioMaqamPreviewUIState(
    val audioDisplayName: String = "",
    val duration: String = "00:00:00",
    val amplitudes: List<Int> = emptyList(),
    val isPlaying: Boolean = false,
    val progress: Float = 0F
)