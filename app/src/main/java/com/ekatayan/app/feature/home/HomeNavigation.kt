package com.ekatayan.app.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ekatayan.app.feature.notifications.NotificationsUiState
import kotlinx.coroutines.flow.StateFlow

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    onGroupHubClick: () -> Unit,
    onWishlistClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onTripsClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onNotificationClick: () -> Unit,
    notificationsUiState: StateFlow<NotificationsUiState>,
) {
    composable(HOME_ROUTE) {
        HomeRoute(onWishlistClick, onGroupHubClick, onPlannerClick, onTripsClick, onExpensesClick, onProfileClick, onSettingsClick, onNotificationClick, notificationsUiState)
    }
}
