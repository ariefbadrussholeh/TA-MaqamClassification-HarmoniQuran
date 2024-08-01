package com.ariefbadrussholeh.harmoniquran.ui.screen.audiopreview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ariefbadrussholeh.harmoniquran.android.AudioPlaybackManager
import com.ariefbadrussholeh.harmoniquran.data.AudioRepository
import com.ariefbadrussholeh.harmoniquran.model.LocalAudio
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiopreview.model.AudioPreviewUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AudioPreviewViewModel @Inject constructor(
    private val audioRepository: AudioRepository,
    private val playbackManager: AudioPlaybackManager
) : ViewModel() {

    var uiState: AudioPreviewUIState by mutableStateOf(AudioPreviewUIState())
        private set

    private var currentLocalAudio: LocalAudio? = null

    init {
        playbackManager.initializeController()
    }

    override fun onCleared() {
        super.onCleared()
        playbackManager.releaseController()
    }

    fun updateProgress(progress: Float) {
        val position = currentLocalAudio?.duration?.times(progress)?.toLong() ?: 0L
        playbackManager.seekTo(position)
        uiState = uiState.copy(progress = progress)
    }

    fun updatePlaybackState() {
        when {
            uiState.isPlaying -> playbackManager.pause()
            else -> playbackManager.play()
        }
    }

    fun stopPlayback() {
        playbackManager.pause()
        playbackManager.seekTo(0) // Optional: Reset playback position to the start
        uiState = uiState.copy(isPlaying = false, progress = 0f, duration = "00:00:00")
    }

    fun loadAudio(contentId: String) {
        viewModelScope.launch {
            try {
                currentLocalAudio = audioRepository.loadAudioByContentId(contentId) ?: return@launch
                currentLocalAudio?.let(playbackManager::setAudio)
                launch { currentLocalAudio?.let { loadAudioAmplitudes(it) } }
                launch { observePlaybackEvents() }
                uiState = uiState.copy(
                    audioDisplayName = currentLocalAudio?.nameWithoutExtension.orEmpty(),
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun loadAudioAmplitudes(localAudio: LocalAudio) {
        try {
            val amplitudes = audioRepository.loadAudioAmplitudes(localAudio)
            uiState = uiState.copy(amplitudes = amplitudes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun observePlaybackEvents() {
        playbackManager.events.collectLatest {
            when(it) {
                is AudioPlaybackManager.Event.PositionChanged -> updatePlaybackProgress(it.position)
                is AudioPlaybackManager.Event.PlayingChanged -> updatePlayingState(it.isPlaying)
            }
        }
    }

    private fun updatePlaybackProgress(position: Long) {
        val audio = currentLocalAudio ?: return
        val progress = position.toFloat() / audio.duration
        val formattedDuration = formatDuration((progress * audio.duration).toLong())
        uiState = uiState.copy(progress = progress, duration = formattedDuration)
    }

    private fun updatePlayingState(isPlaying: Boolean) {
        uiState = uiState.copy(isPlaying = isPlaying)
    }

    private fun formatDuration(durationInMillis: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(durationInMillis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMillis) % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}