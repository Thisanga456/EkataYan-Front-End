package com.ekatayan.app.feature.expenses

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val EXPENSES_ROUTE = "expenses"

fun NavGraphBuilder.expensesScreen(
    onHomeClick: () -> Unit,
    onTripsClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    composable(EXPENSES_ROUTE) { ExpensesRoute(onHomeClick, onTripsClick, onPlannerClick, onProfileClick) }
}
