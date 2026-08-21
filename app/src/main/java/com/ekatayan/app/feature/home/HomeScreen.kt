package com.ekatayan.app.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ekatayan.app.core.designsystem.component.PlaceholderAction
import com.ekatayan.app.core.designsystem.component.PlaceholderScreen

@Composable
fun HomeScreen(
    title: String,
    onPlannerClick: () -> Unit,
    onTripsClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PlaceholderScreen(
        title = title,
        modifier = modifier,
        actions = listOf(
            PlaceholderAction("Planner", onPlannerClick),
            PlaceholderAction("Trips", onTripsClick),
            PlaceholderAction("Expenses", onExpensesClick),
            PlaceholderAction("Profile", onProfileClick),
        ),
    )
}
