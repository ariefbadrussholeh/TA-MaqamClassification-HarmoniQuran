package com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist

import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.extension.getReadStoragePermission
import com.ariefbadrussholeh.harmoniquran.model.PermissionsState
import com.ariefbadrussholeh.harmoniquran.ui.components.AudioItem
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomTopAppBar
import com.ariefbadrussholeh.harmoniquran.ui.components.SearchField
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.model.AudioItemUIState
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.model.AudioListUiState
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme

@Composable
fun AudioListRoute(
    viewModel: AudioListViewModel,
    navController: NavController
) {
    LaunchedEffect(viewModel.navDestination) {
        viewModel.navDestination?.let(navController::navigate)
        viewModel.clearNavDestination()
    }
    AudioListScreen(
        uiState = viewModel.uiState,
        onPermissionsGranted = viewModel::updatePermissionsState,
        onQueryChange = viewModel::updateSearchQuery,
        onBackClicked = {
            navController.popBackStack()
        }
    )
}

@Composable
fun AudioListScreen(
    uiState: AudioListUiState,
    onPermissionsGranted: (Boolean) -> Unit,
    onQueryChange: (String) -> Unit,
    onBackClicked: (() -> Unit)
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onPermissionsGranted(isGranted)
        }
    )
    LaunchedEffect(Unit) {
        launcher.launch(getReadStoragePermission())
    }
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Daftar Audio",
                onBackClicked = onBackClicked
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
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

            when(uiState.permissionsState) {
                PermissionsState.Unknown ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                PermissionsState.Granted ->
                    AudioList(uiState = uiState, onQueryChange = onQueryChange)
                PermissionsState.Denied ->
                    PermissionsRationale(onGrantClick = {
                        launcher.launch(getReadStoragePermission())
                    })
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedVisibility(visible = uiState.isLoadingAudios && uiState.audioFiles.isEmpty()) {
                    CircularProgressIndicator()
                }
            }



        }
    }
}

@Composable
private fun PermissionsRationale(
    modifier: Modifier = Modifier,
    onGrantClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.8F),
            text = stringResource(id = R.string.read_permissions_rationale),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onGrantClick) {
            Text(text = stringResource(id = R.string.grant_permissions))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AudioList(
    modifier: Modifier = Modifier,
    uiState: AudioListUiState,
    onQueryChange: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader {
            SearchField(value = uiState.searchQuery, onValueChange = onQueryChange)
        }
        items(items = uiState.audioFiles, key = { it.id }) {
            AudioItem(itemState = it)
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
            val dummyAudioItems = List(10) { index ->
                AudioItemUIState(
                    id = "id_$index",
                    displayName = "display_name_$index",
                    size = "${index * 10} MB",
                    onClick = {}
                )
            }

            AudioListScreen(
                uiState = AudioListUiState(
                    permissionsState = PermissionsState.Granted,
                    audioFiles = dummyAudioItems
                ),
                onPermissionsGranted = {},
                onQueryChange = {},
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
            val dummyAudioItems = List(10) { index ->
                AudioItemUIState(
                    id = "id_$index",
                    displayName = "display_name_$index",
                    size = "${index * 10} MB",
                    onClick = {}
                )
            }

            AudioListScreen(
                uiState = AudioListUiState(
                    permissionsState = PermissionsState.Granted,
                    audioFiles = dummyAudioItems
                ),
                onPermissionsGranted = {},
                onQueryChange = {},
                onBackClicked = {}
            )
        }
    }
}