package com.ekatayan.app.feature.settings

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@Immutable
data class SettingsUiState(
    val userName: String = "Zendaya Holland",
    val userEmail: String = "Zendaya@gmail.com",
    @param:DrawableRes val profileImageResId: Int? = null,
    val selectedLanguage: String = "English (US)",
    val selectedCurrency: String = "LKR - Sri Lankan Rupees",
    val pushNotificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = false,
    val appVersion: String = "App Version 1.0.0",
)

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun setPushNotificationsEnabled(enabled: Boolean) {
        _uiState.update { it.copy(pushNotificationsEnabled = enabled) }
    }

    fun setDarkModeEnabled(enabled: Boolean) {
        _uiState.update { it.copy(darkModeEnabled = enabled) }
    }
}
