package com.ekatayan.app.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    onWishlistClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onTripsClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    composable(HOME_ROUTE) {
        HomeRoute(onWishlistClick, onPlannerClick, onTripsClick, onExpensesClick, onProfileClick)
    }
}
