package com.ekatayan.app.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ekatayan.app.core.designsystem.theme.EkataTextPrimary

@Composable
fun HeaderActions(
    onNotificationClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        IconButton(onClick = onNotificationClick, modifier = Modifier.size(38.dp)) {
            Icon(
                imageVector = Icons.Default.NotificationsNone,
                contentDescription = "Notifications",
                tint = EkataTextPrimary,
                modifier = Modifier.size(21.dp),
            )
        }
        IconButton(onClick = onSettingsClick, modifier = Modifier.size(38.dp)) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = EkataTextPrimary,
                modifier = Modifier.size(21.dp),
            )
        }
    }
}
