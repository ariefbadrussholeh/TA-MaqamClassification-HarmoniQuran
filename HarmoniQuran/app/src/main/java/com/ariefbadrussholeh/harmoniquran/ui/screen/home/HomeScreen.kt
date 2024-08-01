package com.ariefbadrussholeh.harmoniquran.ui.screen.home


import android.app.Activity
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme
import com.ariefbadrussholeh.harmoniquran.ui.theme.appTitleFontFamily
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.navigation.NavDestination

@Composable
fun HomeRoute(
    navController: NavController
) {
    HomeScreen(
        navigateToAudioList = {
            navController.navigate(NavDestination.AudioList.route)
        },
        navigateToAboutMaqam = {
            navController.navigate(NavDestination.AboutMaqam.createRoute("Bayati"))
        }
    )
}

@Composable
fun HomeScreen(
    navigateToAudioList: (() -> Unit),
    navigateToAboutMaqam: (() -> Unit)
) {
    val logoResource = if (isSystemInDarkTheme()) {
        R.drawable.ic_harmoniquran_dark
    } else {
        R.drawable.ic_harmoniquran_light
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.tertiaryContainer
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.pattern_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.03f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = logoResource),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = stringResource(id = R.string.home_title),
                style = MaterialTheme.typography.displayMedium,
                fontFamily = appTitleFontFamily,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = stringResource(id = R.string.home_description),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(220.dp))
            ElevatedButton(
                onClick = navigateToAudioList,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btn_start))
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = navigateToAboutMaqam,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "TENTANG MAQAM")
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
            HomeScreen(
                navigateToAudioList = {},
                navigateToAboutMaqam = {}
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
            HomeScreen(
                navigateToAudioList = {},
                navigateToAboutMaqam = {}
            )
        }
    }
}