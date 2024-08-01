package com.ariefbadrussholeh.harmoniquran.ui.screen.result

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.Python
import com.ariefbadrussholeh.harmoniquran.data.AudioRepository
import com.ariefbadrussholeh.harmoniquran.ml.DeepANN
import com.ariefbadrussholeh.harmoniquran.ui.screen.result.model.ResultUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val audioRepository: AudioRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var uiState: ResultUIState by mutableStateOf(ResultUIState())
        private set

    suspend fun classifyAudio(contentId: String) = withContext(Dispatchers.IO) {
        try {
            val startTime = System.currentTimeMillis()

            val audio = audioRepository.loadAudioByContentId(contentId)
            val audioPath = audio?.path
            val featureNormalized = audioPath?.let { preprocessAudio(it) }

            val endTimePreprocessing = System.currentTimeMillis()
            val preprocessingTime = (endTimePreprocessing - startTime) / 1000.0
            Log.d("INFERENCE TIME", "Preprocessing time: $preprocessingTime s")

            if (featureNormalized != null) {
                val predictionStartTime = System.currentTimeMillis()

                val prediction = predictAudioClass(featureNormalized)

                val predictionEndTime = System.currentTimeMillis()
                val predictionTime = (predictionEndTime - predictionStartTime) / 1000.0
                Log.d("INFERENCE TIME", "Prediction time: $predictionTime s")

                uiState = uiState.copy(
                    isProcessing = false,
                    classificationResult = prediction
                )
            } else {
                uiState = uiState.copy(isProcessing = false, errorMessage = "Audio preprocessing failed")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            uiState = uiState.copy(isProcessing = false, errorMessage = e.message)
        }
    }

    private fun preprocessAudio(audioFilePath: String): FloatArray {
        val py = Python.getInstance()
        val audioModule = py.getModule("audio_preprocessing")
        val preprocessAudioFunc = audioModule["preprocess_audio"]
        val featureNormalized = preprocessAudioFunc?.call(audioFilePath)
        return featureNormalized?.toJava(FloatArray::class.java) ?: floatArrayOf()
    }

    private fun predictAudioClass(featureNormalized: FloatArray): Map<String, Float> {
        val model = DeepANN.newInstance(context)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 37), DataType.FLOAT32)
        val byteBuffer = ByteBuffer.allocateDirect(4 * featureNormalized.size).order(ByteOrder.nativeOrder())
        for (value in featureNormalized) {
            byteBuffer.putFloat(value)
        }
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        model.close()

        val maqams = arrayOf("Bayati", "Hijaz", "Jiharkah", "Nahawand", "Rast", "Saba", "Sikah")
        return maqams.zip(outputFeature0.floatArray.toList()).toMap()
    }
}