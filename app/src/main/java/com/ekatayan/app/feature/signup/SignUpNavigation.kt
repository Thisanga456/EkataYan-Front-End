package com.ekatayan.app.feature.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SIGN_UP_ROUTE = "signup"

fun NavGraphBuilder.signUpScreen(onLoginClick: () -> Unit) {
    composable(SIGN_UP_ROUTE) { SignUpRoute(onLoginClick = onLoginClick) }
}
