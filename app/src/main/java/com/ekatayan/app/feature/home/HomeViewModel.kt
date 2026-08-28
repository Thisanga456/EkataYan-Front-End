package com.ekatayan.app.feature.home

import androidx.lifecycle.ViewModel
import com.ekatayan.app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(mockHomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onSearchSubmit() = Unit
}

internal fun mockHomeUiState() = HomeUiState(
    user = User(name = "Zendaya", profileImageRes = null),
    recommendedDestinations = listOf(
        RecommendedDestination(1, "Polonnaruwa", "Explore Sri Lanka's majestic ancient capital and its remarkable heritage.", R.drawable.polonnaruwa),
        RecommendedDestination(2, "Nine Arch Bridge", "Walk through Ella's misty hills to this iconic railway landmark.", R.drawable.nine_arch_bridge),
        RecommendedDestination(3, "Sigiriya", "Experience one of Sri Lanka's most iconic ancient landmarks.", R.drawable.sigiriya),
    ),
    upcomingTrip = UpcomingTrip(1, "Anuradhapura", "26 Aug 2026", "6 Days", R.drawable.anuradhapura),
    weather = WeatherInfo("Colombo", 29, "Sunny", 60, WeatherType.SUNNY, R.drawable.colombo),
    popularDestinations = listOf(
        PopularDestination(1, "Sigiriya", R.drawable.sigiriya),
        PopularDestination(2, "Nine Arch Bridge", R.drawable.nine_arch_bridge),
        PopularDestination(3, "Kandy", R.drawable.kandy),
        PopularDestination(4, "Polonnaruwa", R.drawable.polonnaruwa),
        PopularDestination(5, "Galle", R.drawable.galle),
    ),
)
