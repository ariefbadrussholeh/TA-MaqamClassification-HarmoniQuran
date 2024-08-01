package com.ariefbadrussholeh.harmoniquran.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ariefbadrussholeh.harmoniquran.ui.screen.aboutmaqam.AboutMaqamRoute
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.AudioListRoute
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiomaqampreview.AudioMaqamPreviewRoute
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiopreview.AudioPreviewRoute
import com.ariefbadrussholeh.harmoniquran.ui.screen.home.HomeRoute
import com.ariefbadrussholeh.harmoniquran.ui.screen.result.ResultRoute

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: NavDestination = NavDestination.Home,
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier.then(modifier),
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(NavDestination.Home.route) {
            HomeRoute(navController = navHostController)
        }
        composable(NavDestination.AudioList.route) {
            AudioListRoute(hiltViewModel(), navHostController)
        }
        composable(NavDestination.AudioPreview.route) {
            val contentId = it.arguments
                ?.getString(NavDestination.AudioPreview.ContentIdArg)
                ?.let(::requireNotNull)
                .orEmpty()
            AudioPreviewRoute(hiltViewModel(), navHostController, contentId)
        }
        composable(NavDestination.Result.route) {
            val contentId = it.arguments
                ?.getString(NavDestination.Result.ContentIdArg)
                ?.let(::requireNotNull)
                .orEmpty()
            ResultRoute(hiltViewModel(), navHostController, contentId)
        }
        composable(NavDestination.AboutMaqam.route) {
            val contentId = it.arguments
                ?.getString(NavDestination.AboutMaqam.ContentIdArg)
                ?.let(::requireNotNull)
                .orEmpty()
            AboutMaqamRoute(navHostController, contentId)
        }
        composable(NavDestination.AudioMaqamPreview.route) {
            val contentId = it.arguments
                ?.getString(NavDestination.AudioMaqamPreview.ContentIdArg)
                ?.let(::requireNotNull)
                .orEmpty()
            val contentTitle = it.arguments
                ?.getString(NavDestination.AudioMaqamPreview.ContentTitleArg)
                ?.let(::requireNotNull)
                .orEmpty()
            val contentText = it.arguments
                ?.getString(NavDestination.AudioMaqamPreview.ContentTextArg)
                ?.let(::requireNotNull)
                .orEmpty()
            AudioMaqamPreviewRoute(hiltViewModel(), navHostController, contentId.toInt(), contentTitle, contentText.split(","))
        }
    }

}