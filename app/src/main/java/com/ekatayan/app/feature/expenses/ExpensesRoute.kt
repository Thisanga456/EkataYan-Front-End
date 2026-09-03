package com.ekatayan.app.feature.expenses

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ExpensesRoute(
    onHomeClick: () -> Unit,
    onTripsClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: ExpensesViewModel = hiltViewModel(),
) = ExpensesScreen(viewModel.uiState, onHomeClick, onTripsClick, onPlannerClick, onProfileClick)
