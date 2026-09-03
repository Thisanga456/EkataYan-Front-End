package com.ekatayan.app.feature.notifications

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NotificationsViewModelTest {
    @Test
    fun openingNotificationMarksOnlyThatNotificationAsRead() {
        val viewModel = NotificationsViewModel()

        viewModel.markAsRead(1)

        assertFalse(viewModel.uiState.value.notifications.single { it.id == 1 }.isUnread)
        assertTrue(viewModel.uiState.value.notifications.single { it.id == 2 }.isUnread)
        assertEquals(2, viewModel.uiState.value.unreadCount)
    }

    @Test
    fun readingEveryUnreadNotificationClearsGlobalIndicator() {
        val viewModel = NotificationsViewModel()
        viewModel.uiState.value.notifications.forEach { viewModel.markAsRead(it.id) }

        assertEquals(0, viewModel.uiState.value.unreadCount)
        assertFalse(viewModel.uiState.value.hasUnreadNotifications)
    }
}
