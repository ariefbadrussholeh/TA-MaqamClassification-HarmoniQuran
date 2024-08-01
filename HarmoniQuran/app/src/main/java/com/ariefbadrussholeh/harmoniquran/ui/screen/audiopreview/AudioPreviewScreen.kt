package com.ariefbadrussholeh.harmoniquran.ui.screen.audiopreview

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.navigation.NavDestination
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomElevatedIconButton
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomTopAppBar
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiopreview.model.AudioPreviewUIState
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme
import com.linc.audiowaveform.AudioWaveform
import com.linc.audiowaveform.infiniteHorizontalGradient
import com.linc.audiowaveform.model.AmplitudeType
import com.linc.audiowaveform.model.WaveformAlignment

@Composable
fun AudioPreviewRoute(
    viewModel: AudioPreviewViewModel,
    navController: NavController,
    contentId: String,
) {
    LaunchedEffect(contentId) {
        viewModel.loadAudio(contentId)
    }
    AudioPreviewScreen(
        uiState = viewModel.uiState,
        onPlayClicked = viewModel::updatePlaybackState,
        onProgressChange = viewModel::updateProgress,
        onConfirm = {
            viewModel.stopPlayback()
            navController.navigate(NavDestination.Result.createRoute(contentId))
        },
        onBackClicked = {
            viewModel.stopPlayback()
            navController.popBackStack()
        }
    )
}

@Composable
fun AudioPreviewScreen(
    uiState: AudioPreviewUIState,
    onPlayClicked: () -> Unit,
    onProgressChange: (Float) -> Unit,
    onConfirm: (() -> Unit),
    onBackClicked: (() -> Unit),
) {
    var scrollEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pratinjau Audio",
                onBackClicked = onBackClicked
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val patternResource = if (isSystemInDarkTheme()) {
                R.drawable.pattern_2_dark
            } else {
                R.drawable.pattern_2_light
            }
            Image(
                painter = painterResource(id = patternResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.05f)
            )
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = uiState.audioDisplayName
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = uiState.duration,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .alpha(0.75f)
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxWidth()
                        .weight(1.5f)
                ) {
                    AudioWaveform(
                        modifier = Modifier.fillMaxWidth(),
                        style = Fill,
                        waveformAlignment = WaveformAlignment.Center,
                        amplitudeType = AmplitudeType.Avg,
                        progressBrush = Brush.infiniteHorizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.tertiary
                            ),
                            width = 192.dp.value
                        ),
                        waveformBrush = SolidColor(Color.Gray),
                        spikeWidth = 3.dp,
                        progress = uiState.progress,
                        amplitudes = uiState.amplitudes,
                        height = 256.dp,
                        onProgressChange = {
                            scrollEnabled = false
                            onProgressChange(it)
                        },
                        onProgressChangeFinished = {
                            scrollEnabled = true
                        }
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.5f)
                ) {
                    CustomElevatedIconButton(
                        icon = Icons.Default.Close,
                        size = 42.dp,
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        onClick = onBackClicked
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CustomElevatedIconButton(
                        icon = if (uiState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        size = 56.dp,
                        containerColor = if (uiState.isPlaying) {
                            MaterialTheme.colorScheme.surfaceVariant
                        } else {
                            MaterialTheme.colorScheme.tertiaryContainer
                        },
                        contentColor = if (uiState.isPlaying) {
                            MaterialTheme.colorScheme.onSurfaceVariant 
                        } else {
                            MaterialTheme.colorScheme.onTertiaryContainer
                        },
                        onClick = onPlayClicked
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CustomElevatedIconButton(
                        icon = Icons.Default.Check,
                        size = 42.dp,
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        onClick = onConfirm
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightThemePreview() {
    HarmoniQuranTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            AudioPreviewScreen(
                uiState = AudioPreviewUIState(
                    audioDisplayName = "audioDisplayName",
                    isPlaying = true
                ),
                onPlayClicked = {},
                onProgressChange = {},
                onConfirm = {},
                onBackClicked = {}
            )
        }
    }
}
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkThemePreview() {
    HarmoniQuranTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            AudioPreviewScreen(
                uiState = AudioPreviewUIState(
                    audioDisplayName = "audioDisplayName",
                    isPlaying = true
                ),
                onPlayClicked = {},
                onProgressChange = {},
                onConfirm = {},
                onBackClicked = {}
            )
        }
    }
}