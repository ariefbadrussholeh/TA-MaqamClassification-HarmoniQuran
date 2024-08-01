package com.ariefbadrussholeh.harmoniquran.ui.screen.aboutmaqam

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.navigation.NavDestination
import com.ariefbadrussholeh.harmoniquran.ui.components.CustomTopAppBar
import com.ariefbadrussholeh.harmoniquran.ui.screen.aboutmaqam.model.ToneLevelData
import com.ariefbadrussholeh.harmoniquran.ui.screen.aboutmaqam.model.aboutMaqamData
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.model.AudioItemUIState
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme

@Composable
fun AboutMaqamRoute(
    navController: NavController,
    contentId: String
) {
    BackHandler {
        navController.navigate(NavDestination.Home.route) {
            popUpTo(NavDestination.Home.route) {
                inclusive = true
            }
        }
    }

    AboutMaqamScreen(
        onBackClicked = {
            navController.navigate(NavDestination.Home.route) {
                popUpTo(NavDestination.Home.route) {
                    inclusive = true
                }
            }
        },
        navController = navController,
        contentId = contentId
    )
}

@Composable
fun AboutMaqamScreen(
    onBackClicked: (() -> Unit),
    navController: NavController,
    contentId: String
) {
    var currentIndex = aboutMaqamData.indexOfFirst { it.title == contentId }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Tentang Maqam",
                onBackClicked = onBackClicked
            )
        },
        bottomBar = {
            CustomBottomAppBar(
                currentIndex = currentIndex,
                navController = navController
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
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    Text(
                        text = aboutMaqamData[currentIndex].title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Text(
                        text = aboutMaqamData[currentIndex].description
                    )
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Tingkatan nada " + aboutMaqamData[currentIndex].title + " dan penerepan dalam tilawah Q.S Ar-Rahman (55) :",
                        )
                        aboutMaqamData[currentIndex].toneLevels.forEach { toneLevel ->
                            ToneLevelItem(
                                data = toneLevel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToneLevelItem(
    data: ToneLevelData,
    navController: NavController,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    NavDestination.AudioMaqamPreview.createRoute(
                        id = data.audioPath.toString(),
                        title = data.title,
                        text = data.text.joinToString(",")
                    )
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data.description,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(4.dp),
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun CustomBottomAppBar(
    currentIndex: Int,
    navController: NavController
) {
    val previousIndex = if (currentIndex > 0) currentIndex - 1 else -1
    val nextIndex = if (currentIndex < aboutMaqamData.size - 1) currentIndex + 1 else -1

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        if (previousIndex != -1) {
            TextButton(
                onClick = {
                    navController.navigate(NavDestination.AboutMaqam.createRoute(aboutMaqamData[previousIndex].title))
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = aboutMaqamData[previousIndex].title)
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        Text(
            text = aboutMaqamData[currentIndex].title,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        if (nextIndex != -1) {
            TextButton(
                onClick = {
                    navController.navigate(NavDestination.AboutMaqam.createRoute(aboutMaqamData[nextIndex].title))
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = aboutMaqamData[nextIndex].title)
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun LightThemePreview() {
//    HarmoniQuranTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.surface
//        ) {
//            AboutMaqamScreen(
//                onBackClicked = {}
//            )
//        }
//    }
//}
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun DarkThemePreview() {
//    HarmoniQuranTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.surface
//        ) {
//            AboutMaqamScreen(
//                onBackClicked = {}
//            )
//        }
//    }
//}
