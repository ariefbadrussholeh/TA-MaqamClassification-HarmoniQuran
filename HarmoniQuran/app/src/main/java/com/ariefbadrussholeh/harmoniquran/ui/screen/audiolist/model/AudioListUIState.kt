package com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.model

import com.ariefbadrussholeh.harmoniquran.model.PermissionsState

data class AudioListUiState(
    val permissionsState: PermissionsState = PermissionsState.Unknown,
    val searchQuery: String = "",
    val audioFiles: List<AudioItemUIState> = emptyList(),
    val isLoadingAudios: Boolean = false
)