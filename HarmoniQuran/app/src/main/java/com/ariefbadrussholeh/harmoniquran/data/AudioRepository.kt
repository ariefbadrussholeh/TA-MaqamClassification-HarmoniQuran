package com.ariefbadrussholeh.harmoniquran.data

import com.ariefbadrussholeh.harmoniquran.model.LocalAudio
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioRepository @Inject constructor(
    private val localMediaDataSource: LocalMediaDataSource,
    private val audioManager: AudioManager,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun loadAudioFiles(query: String): List<LocalAudio> = withContext(ioDispatcher) {
        return@withContext localMediaDataSource.loadAudioFiles(query)
    }

    suspend fun loadAudioByContentId(id: String): LocalAudio? = withContext(ioDispatcher) {
        return@withContext localMediaDataSource.loadAudioById(id)
    }

    suspend fun loadAudioAmplitudes(
        localAudio: LocalAudio
    ): List<Int> = withContext(ioDispatcher) {
        return@withContext audioManager.getAmplitudes(localAudio.path)
    }

    suspend fun loadAudioAmplitudesById(
        localAudio: LocalAudio
    ): List<Int> = withContext(ioDispatcher) {
        return@withContext audioManager.getAmplitudesById(localAudio.id.toInt())
    }
}