package com.ekatayan.app.feature.planner

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun PlannerRoute(
    onBackClick: () -> Unit,
    viewModel: PlannerViewModel = hiltViewModel(),
) {
    PlannerScreen(title = viewModel.title, onBackClick = onBackClick)
}
