package com.ariefbadrussholeh.harmoniquran.ui.screen.audiomaqampreview

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.navigation.NavDestination
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomElevatedIconButton
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomTopAppBar
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiomaqampreview.model.AudioMaqamPreviewUIState
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiopreview.model.AudioPreviewUIState
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme
import com.linc.audiowaveform.AudioWaveform
import com.linc.audiowaveform.infiniteHorizontalGradient
import com.linc.audiowaveform.model.AmplitudeType
import com.linc.audiowaveform.model.WaveformAlignment

@Composable
fun AudioMaqamPreviewRoute(
    viewModel: AudioMaqamViewModel,
    navController: NavController,
    contentId: Int,
    contentTitle: String,
    contentText: List<String>,
) {
    LaunchedEffect(contentId) {
        viewModel.loadAudio(contentId, contentTitle)
    }
    AudioPreviewScreen(
        contentTitle = contentTitle,
        contentText = contentText,
        uiState = viewModel.uiState,
        onPlayClicked = viewModel::updatePlaybackState,
        onProgressChange = viewModel::updateProgress,
        onBackClicked = {
            viewModel.stopPlayback()
            navController.popBackStack()
        }
    )
}

@Composable
fun AudioPreviewScreen(
    contentTitle: String,
    contentText: List<String>,
    uiState: AudioMaqamPreviewUIState,
    onPlayClicked: () -> Unit,
    onProgressChange: (Float) -> Unit,
    onBackClicked: (() -> Unit),
) {
    var scrollEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = contentTitle,
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
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(contentText) { text ->
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Text(
                                text = text + "\u06DD",
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp, 8.dp)
                            )
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .alpha(0.75f)
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
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
                        height = 72.dp,
                        onProgressChange = {
                            scrollEnabled = false
                            onProgressChange(it)
                        },
                        onProgressChangeFinished = {
                            scrollEnabled = true
                        }
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
                contentText = listOf(
                    "أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيم ",
                    "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ ",
                    "اَلرَّحْمٰنُۙ ",
                    "عَلَّمَ الْقُرْاٰنَۗ ",
                    "خَلَقَ الْاِنْسَانَۙ ",
                    "عَلَّمَهُ الْبَيَانَ "
                ),
                contentTitle = "contentTilte",
                uiState = AudioMaqamPreviewUIState(
                    audioDisplayName = "audioDisplayName",
                    isPlaying = true
                ),
                onPlayClicked = {},
                onProgressChange = {},
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
                contentText = listOf(
                    "أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيم ",
                    "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ ",
                    "اَلرَّحْمٰنُۙ ",
                    "عَلَّمَ الْقُرْاٰنَۗ ",
                    "خَلَقَ الْاِنْسَانَۙ ",
                    "عَلَّمَهُ الْبَيَانَ "
                ),
                contentTitle = "contentTilte",
                uiState = AudioMaqamPreviewUIState(
                    audioDisplayName = "audioDisplayName",
                    isPlaying = true
                ),
                onPlayClicked = {},
                onProgressChange = {},
                onBackClicked = {}
            )
        }
    }
}