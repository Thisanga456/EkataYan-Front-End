package com.ekatayan.app.feature.trips

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun TripsRoute(
    onBackClick: () -> Unit,
    viewModel: TripsViewModel = hiltViewModel(),
) {
    TripsScreen(title = viewModel.title, onBackClick = onBackClick)
}
