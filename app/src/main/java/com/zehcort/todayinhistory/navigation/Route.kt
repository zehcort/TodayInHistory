package com.zehcort.todayinhistory.navigation

sealed class Route(val route: String) {
    data object Home : Route("Home")
}