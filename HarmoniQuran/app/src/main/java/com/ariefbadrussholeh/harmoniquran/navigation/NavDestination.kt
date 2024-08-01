package com.ariefbadrussholeh.harmoniquran.navigation

sealed class NavDestination (val route: String) {

    object Home: NavDestination("home")

    object AudioList: NavDestination("audio-list")

    object AudioPreview: NavDestination("audio-preview/{id}") {
        const val ContentIdArg = "id"
        fun createRoute(id: String) = "audio-preview/$id"
    }

    object Result: NavDestination("result/{id}") {
        const val ContentIdArg = "id"
        fun createRoute(id: String) = "result/$id"
    }

    object AboutMaqam: NavDestination("about-maqam/{id}") {
        const val ContentIdArg = "id"
        fun createRoute(id: String) = "about-maqam/$id"
    }

    object AudioMaqamPreview : NavDestination("audio-maqam-preview/{id}/{title}/{text}") {
        const val ContentIdArg = "id"
        const val ContentTitleArg = "title"
        const val ContentTextArg = "text"
        fun createRoute(id: String, title: String, text: String) = "audio-maqam-preview/$id/$title/$text"
    }
}