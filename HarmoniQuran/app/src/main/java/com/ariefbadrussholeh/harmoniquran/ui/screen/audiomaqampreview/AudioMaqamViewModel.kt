package com.ariefbadrussholeh.harmoniquran.ui.screen.audiomaqampreview

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ariefbadrussholeh.harmoniquran.android.AudioPlaybackManager
import com.ariefbadrussholeh.harmoniquran.data.AudioRepository
import com.ariefbadrussholeh.harmoniquran.model.LocalAudio
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiomaqampreview.model.AudioMaqamPreviewUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AudioMaqamViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val audioRepository: AudioRepository,
    private val playbackManager: AudioPlaybackManager,
) : ViewModel() {

    var uiState: AudioMaqamPreviewUIState by mutableStateOf(AudioMaqamPreviewUIState())
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
        playbackManager.seekTo(0)
        uiState = uiState.copy(isPlaying = false, progress = 0f, duration = "00:00:00")
    }

    fun loadAudio(resourceId: Int, title: String) {
        viewModelScope.launch {
            try {
                currentLocalAudio = rawToLocalAudio(resourceId, title)
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

    private fun rawToLocalAudio(resourceId: Int, title: String): LocalAudio {
        val uri = Uri.parse("android.resource://${context.packageName}/${resourceId}")
        val assetFileDescriptor: AssetFileDescriptor = context.resources.openRawResourceFd(resourceId)
        val fileSize: Long = assetFileDescriptor.length
        val mediaPlayer = MediaPlayer.create(context, resourceId)
        val duration = mediaPlayer.duration.toLong()
        mediaPlayer.release()

        return LocalAudio(
            id = resourceId.toString(),
            path = uri.toString(),
            name = title,
            duration = duration,
            size = fileSize,
            uri = uri
        )
    }

    private suspend fun loadAudioAmplitudes(localAudio: LocalAudio) {
        try {
            val amplitudes = audioRepository.loadAudioAmplitudesById(localAudio)
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