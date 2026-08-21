package com.ekatayan.app.feature.expenses

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ekatayan.app.core.designsystem.component.PlaceholderScreen

@Composable
fun ExpensesScreen(title: String, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    PlaceholderScreen(title = title, onBackClick = onBackClick, modifier = modifier)
}
