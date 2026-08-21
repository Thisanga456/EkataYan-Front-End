package com.ekatayan.app.feature.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val PROFILE_ROUTE = "profile"

fun NavGraphBuilder.profileScreen(onBackClick: () -> Unit) {
    composable(PROFILE_ROUTE) { ProfileRoute(onBackClick = onBackClick) }
}
