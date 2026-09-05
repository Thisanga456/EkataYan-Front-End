package com.ekatayan.app.feature.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NotificationDetailRoute(
    notificationId: Int,
    viewModel: NotificationsViewModel,
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val notification = uiState.notifications.find { it.id == notificationId }
    LaunchedEffect(notificationId) { viewModel.markAsRead(notificationId) }

    NotificationDetailScreen(
        notification = notification,
        onBackClick = onBackClick,
    )
}
