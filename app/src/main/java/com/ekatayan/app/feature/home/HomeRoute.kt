package com.ekatayan.app.feature.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeRoute(
    onPlannerClick: () -> Unit,
    onTripsClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        title = viewModel.title,
        onPlannerClick = onPlannerClick,
        onTripsClick = onTripsClick,
        onExpensesClick = onExpensesClick,
        onProfileClick = onProfileClick,
    )
}
