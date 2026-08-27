package com.ekatayan.app.feature.login

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LoginRoute(
    onLogInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onPasswordVisibilityClick = viewModel::onPasswordVisibilityClick,
        onForgotPasswordClick = viewModel::onForgotPasswordClick,
        onLogInClick = onLogInClick,
        onGoogleClick = viewModel::onGoogleClick,
        onAppleClick = viewModel::onAppleClick,
        onSignUpClick = onSignUpClick,
    )
}
