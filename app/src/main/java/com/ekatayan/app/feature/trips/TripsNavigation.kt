package com.ekatayan.app.feature.trips

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val TRIPS_ROUTE = "trips"

fun NavGraphBuilder.tripsScreen(
    onHomeClick: () -> Unit,
    onTripsClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    composable(TRIPS_ROUTE) {
        TripsRoute(onHomeClick, onTripsClick, onPlannerClick, onExpensesClick, onProfileClick)
    }
}
