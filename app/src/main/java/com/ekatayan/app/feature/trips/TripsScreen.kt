package com.ekatayan.app.feature.trips

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ekatayan.app.core.designsystem.component.PlaceholderScreen

@Composable
fun TripsScreen(title: String, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    PlaceholderScreen(title = title, onBackClick = onBackClick, modifier = modifier)
}
