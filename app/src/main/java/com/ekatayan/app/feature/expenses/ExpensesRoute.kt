package com.ekatayan.app.feature.expenses

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ExpensesRoute(
    onBackClick: () -> Unit,
    viewModel: ExpensesViewModel = hiltViewModel(),
) {
    ExpensesScreen(title = viewModel.title, onBackClick = onBackClick)
}
