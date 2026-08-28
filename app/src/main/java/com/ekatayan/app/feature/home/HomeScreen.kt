package com.ekatayan.app.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekatayan.app.core.designsystem.theme.EkataBackground
import com.ekatayan.app.core.designsystem.theme.EkataYanTheme

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onNotificationClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onSearchQueryChange: (String) -> Unit = {},
    onSearchSubmit: () -> Unit = {},
    onMapsClick: () -> Unit = {},
    onWishlistClick: () -> Unit = {},
    onBookingClick: () -> Unit = {},
    onGroupHubClick: () -> Unit = {},
    onRecommendedDestinationClick: (Int) -> Unit = {},
    onUpcomingTripClick: () -> Unit = {},
    onPopularDestinationClick: (Int) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onTripsClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedNavItem by rememberSaveable { mutableIntStateOf(0) }
    Box(modifier.fillMaxSize().background(EkataBackground)) {
        if (uiState.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 112.dp),
            ) {
                item {
                    HeroSection(
                        user = uiState.user,
                        searchQuery = uiState.searchQuery,
                        onNotificationClick = onNotificationClick,
                        onSettingsClick = onSettingsClick,
                        onSearchQueryChange = onSearchQueryChange,
                        onSearchSubmit = onSearchSubmit,
                    )
                }
                item { QuickActions(onMapsClick, onWishlistClick, onBookingClick, onGroupHubClick) }
                uiState.errorMessage?.let { message ->
                    item { Text(message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(horizontal = 20.dp)) }
                }
                item { SectionTitle("Recommended For You") }
                item { RecommendedSection(uiState.recommendedDestinations, onRecommendedDestinationClick) }
                item { HomeInfoCards(uiState.upcomingTrip, uiState.weather, onUpcomingTripClick) }
                item { SectionTitle("Popular Destinations") }
                item { PopularDestinationsSection(uiState.popularDestinations, onPopularDestinationClick) }
            }
        }
        HomeBottomNavigation(
            selectedIndex = selectedNavItem,
            onItemSelected = { index ->
                selectedNavItem = index
                when (index) {
                    0 -> onHomeClick()
                    1 -> onTripsClick()
                    2 -> onPlannerClick()
                    3 -> onExpensesClick()
                    4 -> onProfileClick()
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 21.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 22.dp, bottom = 12.dp),
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EkataYanTheme(darkTheme = false) {
        HomeScreen(
            uiState = mockHomeUiState(),
            onTripsClick = {},
            onPlannerClick = {},
            onExpensesClick = {},
            onProfileClick = {},
        )
    }
}
