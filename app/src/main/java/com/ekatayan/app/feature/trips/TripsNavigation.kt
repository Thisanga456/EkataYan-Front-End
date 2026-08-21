package com.ekatayan.app.feature.trips

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val TRIPS_ROUTE = "trips"

fun NavGraphBuilder.tripsScreen(onBackClick: () -> Unit) {
    composable(TRIPS_ROUTE) { TripsRoute(onBackClick = onBackClick) }
}
