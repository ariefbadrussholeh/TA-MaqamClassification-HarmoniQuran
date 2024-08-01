package com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.model

import com.ariefbadrussholeh.harmoniquran.extension.formatAsAudioDuration
import com.ariefbadrussholeh.harmoniquran.extension.formatAsFileSize
import com.ariefbadrussholeh.harmoniquran.model.LocalAudio

data class AudioItemUIState(
    val id: String,
    val displayName: String,
    val size: String,
    val onClick: () -> Unit
)

fun LocalAudio.toUiState(
    onClick: () -> Unit
) = AudioItemUIState(
    id = id,
    displayName = name,
    size = duration.formatAsAudioDuration + " | " + size.formatAsFileSize,
    onClick = onClick
)