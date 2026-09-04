package com.ekatayan.app.feature.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ekatayan.app.feature.notifications.NotificationsUiState
import kotlinx.coroutines.flow.StateFlow

const val PROFILE_ROUTE = "profile"

fun NavGraphBuilder.profileScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onTripsClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onNotificationClick: () -> Unit,
    notificationsUiState: StateFlow<NotificationsUiState>,
) {
    composable(PROFILE_ROUTE) {
        ProfileRoute(onBackClick, onHomeClick, onTripsClick, onPlannerClick, onExpensesClick, onSettingsClick, onNotificationClick, notificationsUiState)
    }
}
