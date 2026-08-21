package com.ekatayan.app.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ekatayan.app.app.navigation.EkataYanNavHost

@Composable
fun EkataYanApp() {
    val navController = rememberNavController()
    EkataYanNavHost(navController = navController)
}
