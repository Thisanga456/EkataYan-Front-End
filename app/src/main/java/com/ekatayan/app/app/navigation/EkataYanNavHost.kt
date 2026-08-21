package com.ekatayan.app.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ekatayan.app.feature.expenses.EXPENSES_ROUTE
import com.ekatayan.app.feature.expenses.expensesScreen
import com.ekatayan.app.feature.home.HOME_ROUTE
import com.ekatayan.app.feature.home.homeScreen
import com.ekatayan.app.feature.planner.PLANNER_ROUTE
import com.ekatayan.app.feature.planner.plannerScreen
import com.ekatayan.app.feature.profile.PROFILE_ROUTE
import com.ekatayan.app.feature.profile.profileScreen
import com.ekatayan.app.feature.signup.SIGN_UP_ROUTE
import com.ekatayan.app.feature.signup.signUpScreen
import com.ekatayan.app.feature.trips.TRIPS_ROUTE
import com.ekatayan.app.feature.trips.tripsScreen

@Composable
fun EkataYanNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = SIGN_UP_ROUTE,
        modifier = modifier,
    ) {
        signUpScreen(onLoginClick = { navController.navigate(HOME_ROUTE) })
        homeScreen(
            onPlannerClick = { navController.navigate(PLANNER_ROUTE) },
            onTripsClick = { navController.navigate(TRIPS_ROUTE) },
            onExpensesClick = { navController.navigate(EXPENSES_ROUTE) },
            onProfileClick = { navController.navigate(PROFILE_ROUTE) },
        )
        plannerScreen(onBackClick = navController::navigateUp)
        tripsScreen(onBackClick = navController::navigateUp)
        expensesScreen(onBackClick = navController::navigateUp)
        profileScreen(onBackClick = navController::navigateUp)
    }
}
