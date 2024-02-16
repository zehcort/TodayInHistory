package com.zehcort.todayinhistory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zehcort.todayinhistory.TodayInHistoryApp
import com.zehcort.todayinhistory.viewmodels.HomeViewModel
import com.zehcort.todayinhistory.views.composables.screens.HomeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val getDateFact = (LocalContext.current.applicationContext as TodayInHistoryApp).serviceLocator.provideGetDateFactUseCase()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(route = Route.Home.route) {
            HomeScreen(viewModel = HomeViewModel(getDateFact = getDateFact))
        }
    }
}