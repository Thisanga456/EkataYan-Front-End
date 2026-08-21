package com.ekatayan.app.feature.expenses

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val EXPENSES_ROUTE = "expenses"

fun NavGraphBuilder.expensesScreen(onBackClick: () -> Unit) {
    composable(EXPENSES_ROUTE) { ExpensesRoute(onBackClick = onBackClick) }
}
