package com.ekatayan.app.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Luggage
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekatayan.app.core.designsystem.theme.EkataNavigationBackground
import com.ekatayan.app.core.designsystem.theme.EkataTextPrimary
import com.ekatayan.app.core.designsystem.theme.EkataTextSecondary

enum class AppBottomNavItem(val label: String, val icon: ImageVector) {
    HOME("Home", Icons.Default.Home),
    TRIPS("Trips", Icons.Outlined.Luggage),
    PLANNER("AI Planner", Icons.Default.AutoAwesome),
    EXPENSES("Expenses", Icons.Default.AccountBalanceWallet),
    PROFILE("Profile", Icons.Default.Person),
}

@Composable
fun AppBottomNavigation(
    selectedItem: AppBottomNavItem,
    onHomeClick: () -> Unit,
    onTripsClick: () -> Unit,
    onPlannerClick: () -> Unit,
    onExpensesClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val callbacks = listOf(onHomeClick, onTripsClick, onPlannerClick, onExpensesClick, onProfileClick)
    Row(
        modifier = modifier
            .padding(start = 14.dp, end = 14.dp, bottom = 14.dp)
            .fillMaxWidth()
            .height(69.dp)
            .shadow(9.dp, RoundedCornerShape(25.dp))
            .background(EkataNavigationBackground, RoundedCornerShape(25.dp))
            .padding(horizontal = 7.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppBottomNavItem.entries.forEachIndexed { index, item ->
            val selected = selectedItem == item
            Column(
                modifier = Modifier.weight(1f).clickable(onClick = callbacks[index]),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.size(34.dp).background(if (selected) Color.White else Color.Transparent, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (selected) EkataTextPrimary else EkataTextSecondary,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Text(
                    text = item.label,
                    fontSize = 9.sp,
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                    maxLines = 1,
                )
            }
        }
    }
}
