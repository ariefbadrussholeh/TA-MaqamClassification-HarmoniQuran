package com.ariefbadrussholeh.harmoniquran.ui.screen.result.model

data class ResultUIState(
    val classificationResult: Map<String, Float> = emptyMap(),
    val isProcessing: Boolean = true,
    val errorMessage: String? = null
)