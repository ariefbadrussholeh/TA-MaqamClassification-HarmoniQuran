package com.ariefbadrussholeh.harmoniquran.ui.screen.result

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.navigation.NavDestination
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomElevatedIconButton
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomTopAppBar
import com.ariefbadrussholeh.harmoniquran.ui.screen.result.model.ResultUIState
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme

@Composable
fun ResultRoute(
    viewModel: ResultViewModel,
    navController: NavController,
    contentId: String,
) {
    LaunchedEffect(contentId) {
        viewModel.classifyAudio(contentId)
    }

    BackHandler(onBack = {
        navController.navigate(NavDestination.AudioList.route) {
            popUpTo(NavDestination.AudioList.route) {
                inclusive = true
            }
        }
    })

    ResultScreen(
        uiState = viewModel.uiState,
        onBackClicked = {
            navController.navigate(NavDestination.AudioList.route) {
                popUpTo(NavDestination.AudioList.route) {
                    inclusive = true
                }
            }
        },
        onAboutMaqamClicked = { id: String ->
            navController.navigate(NavDestination.AboutMaqam.createRoute(id))
        }
    )
}

@Composable
fun ResultScreen(
    uiState: ResultUIState,
    onBackClicked: (() -> Unit),
    onAboutMaqamClicked: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Hasil",
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
            if (uiState.isProcessing) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Memproses audio ...")
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp, vertical = 32.dp)
                ) {
                    val result = uiState.classificationResult
                        .toList()
                        .sortedByDescending { (_, value) -> value }
                        .toMap()

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Maqam terdeteksi",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = result.entries.first().key,
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = String.format("%.2f", result.entries.first().value * 100) + "%",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp)
                    ) {
                        Text(
                            text = "Terdeteksi maqam lainnya:",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        result.entries.toList().subList(1, 7).forEach { entry ->
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = entry.key,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = String.format("%.2f", entry.value * 100) + "%",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            onAboutMaqamClicked(result.entries.first().key)
                        }) {
                            Text(text = "Tentang maqam " + result.entries.first().key)
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize() 
                    ) {
                        CustomElevatedIconButton(
                            icon = Icons.Default.Replay,
                            size = 48.dp,
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            onClick = onBackClicked
                        )
                    }
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
            val dummyClassificationResult = mapOf(
                "Bayati" to 0.8432f,
                "Hijaz" to 0.04234f,
                "Nahawand" to 0.023423f,
                "Rast" to 0.02342f,
                "Saba" to 0.042342f,
                "Sika" to 0.0234234f,
                "Jiharkah" to 0.92342f
            )

            ResultScreen(
                uiState = ResultUIState(
                    isProcessing = false,
                    classificationResult = dummyClassificationResult
                ),
                onBackClicked = {},
                onAboutMaqamClicked = {}
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
            val dummyClassificationResult = mapOf(
                "Bayati" to 0.8432f,
                "Hijaz" to 0.04234f,
                "Nahawand" to 0.023423f,
                "Rast" to 0.02342f,
                "Saba" to 0.042342f,
                "Sika" to 0.0234234f,
                "Jiharkah" to 0.92342f
            )

            ResultScreen(
                uiState = ResultUIState(
                    isProcessing = false,
                    classificationResult = dummyClassificationResult
                ),
                onBackClicked = {},
                onAboutMaqamClicked = {}
            )
        }
    }
}