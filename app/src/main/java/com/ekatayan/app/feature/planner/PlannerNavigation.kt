package com.ekatayan.app.feature.planner

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val PLANNER_ROUTE = "planner"

fun NavGraphBuilder.plannerScreen(onBackClick: () -> Unit) {
    composable(PLANNER_ROUTE) { PlannerRoute(onBackClick = onBackClick) }
}
