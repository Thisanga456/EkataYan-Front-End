package com.ekatayan.app.feature.expenses

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ekatayan.app.feature.notifications.NotificationsUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ExpensesRoute(
    onHomeClick: () -> Unit,
    onTripsClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onNotificationClick: () -> Unit,
    notificationsUiState: StateFlow<NotificationsUiState>,
    viewModel: ExpensesViewModel = hiltViewModel(),
) {
    val notificationState by notificationsUiState.collectAsStateWithLifecycle()
    ExpensesScreen(viewModel.uiState, onHomeClick, onTripsClick, onPlannerClick, onProfileClick, onSettingsClick, onNotificationClick, notificationState.hasUnreadNotifications)
}
