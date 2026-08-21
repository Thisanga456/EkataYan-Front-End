package com.ekatayan.app.feature.profile

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ProfileRoute(
    onBackClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    ProfileScreen(title = viewModel.title, onBackClick = onBackClick)
}
