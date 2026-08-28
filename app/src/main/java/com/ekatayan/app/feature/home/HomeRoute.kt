package com.ekatayan.app.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeRoute(
    onPlannerClick: () -> Unit,
    onTripsClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onSearchSubmit = viewModel::onSearchSubmit,
        onPlannerClick = onPlannerClick,
        onTripsClick = onTripsClick,
        onExpensesClick = onExpensesClick,
        onProfileClick = onProfileClick,
    )
}
