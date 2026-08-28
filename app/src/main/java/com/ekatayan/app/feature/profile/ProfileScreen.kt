package com.ekatayan.app.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.BusinessCenter
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Blue = Color(0xFF3478F6)
private val Green = Color(0xFF27AE60)
private val Purple = Color(0xFF7256D9)
private val Orange = Color(0xFFF2A900)
private val Background = Color(0xFFF8F9FC)
private val DarkText = Color(0xFF20243A)
private val GreyText = Color(0xFF7B8191)

@Composable
fun ProfileScreen(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Profile",
                            fontSize = 28.sp,
                            color = Color.Black
                        )

                        Row {
                            Icon(
                                Icons.Outlined.Notifications,
                                contentDescription = "Notifications",
                                modifier = Modifier.size(25.dp)
                            )

                            Spacer(Modifier.width(18.dp))

                            Icon(
                                Icons.Outlined.Settings,
                                contentDescription = "Settings",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Surface(
                            modifier = Modifier.size(82.dp),
                            shape = CircleShape,
                            color = Color(0xFFE5E7EB)
                        ) {
                            Icon(
                                Icons.Outlined.Person,
                                contentDescription = "Profile",
                                modifier = Modifier.padding(18.dp),
                                tint = Color.Gray
                            )
                        }

                        Spacer(Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                "Zendaya Holland",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Spacer(Modifier.height(5.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Outlined.LocationOn,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(Modifier.width(3.dp))
                                Text(
                                    "Kandy, Sri Lanka",
                                    fontSize = 10.sp,
                                    color = GreyText
                                )
                            }

                            Spacer(Modifier.height(3.dp))

                            Text(
                                "zendaya@gmail.com",
                                fontSize = 10.sp,
                                color = GreyText
                            )
                        }

                        Text(
                            "✎ Edit Profile",
                            fontSize = 10.sp,
                            color = Blue,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { }
                                .padding(6.dp)
                        )
                    }
                }
            }

            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    shape = RoundedCornerShape(18.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ProfileStat(
                            Icons.Outlined.BusinessCenter,
                            "12",
                            "Trips",
                            Blue
                        )
                        ProfileStat(
                            Icons.Outlined.LocationOn,
                            "24",
                            "Places Visited",
                            Green
                        )
                        ProfileStat(
                            Icons.Outlined.Star,
                            "4.8",
                            "Avg Rating",
                            Orange
                        )
                        ProfileStat(
                            Icons.Outlined.People,
                            "8",
                            "Travel Buddies",
                            Purple
                        )
                    }
                }
            }

            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    shape = RoundedCornerShape(18.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Column {

                        ProfileItem(
                            Icons.Outlined.Person,
                            "Personal Information",
                            "Manage your personal details",
                            Blue
                        )

                        ProfileItem(
                            Icons.Outlined.CreditCard,
                            "Payment Methods",
                            "Cards, wallets & payment settings",
                            Green
                        )

                        ProfileItem(
                            Icons.Outlined.Notifications,
                            "Notifications",
                            "Manage your notification preferences",
                            Purple
                        )

                        ProfileItem(
                            Icons.Outlined.Lock,
                            "Privacy & Security",
                            "Control your privacy and security",
                            Orange
                        )

                        ProfileItem(
                            Icons.Outlined.Language,
                            "Language",
                            "Change app language",
                            Blue,
                            "English"
                        )

                        ProfileItem(
                            Icons.Outlined.HelpOutline,
                            "Help & Support",
                            "Get help and contact support",
                            Color(0xFFE91E63)
                        )

                        ProfileItem(
                            Icons.Outlined.ExitToApp,
                            "Log Out",
                            "Sign out from your account",
                            Color(0xFF36A69A)
                        )
                    }
                }
            }
        }

        BottomNavigation()
    }
}

@Composable
private fun ProfileStat(
    icon: ImageVector,
    value: String,
    label: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(78.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = color
        )

        Text(
            value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = DarkText
        )

        Text(
            label,
            fontSize = 8.sp,
            color = GreyText,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProfileItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    color: Color,
    trailing: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            modifier = Modifier.size(34.dp),
            shape = RoundedCornerShape(9.dp),
            color = color.copy(alpha = 0.10f)
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = color
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                title,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )

            Text(
                subtitle,
                fontSize = 9.sp,
                color = GreyText
            )
        }

        if (trailing != null) {
            Text(
                trailing,
                fontSize = 10.sp,
                color = GreyText
            )
        }

        Icon(
            Icons.Outlined.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 6.dp)
                .size(12.dp),
            tint = GreyText
        )
    }
}

@Composable
private fun BottomNavigation() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFE0E0E0)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 9.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            BottomItem(Icons.Outlined.Person, "Home")
            BottomItem(Icons.Outlined.BusinessCenter, "Trips")
            BottomItem(Icons.Outlined.TravelExplore, "AI Planner")
            BottomItem(Icons.Outlined.CreditCard, "Expenses")
            BottomItem(Icons.Outlined.Person, "Profile")
        }
    }
}

@Composable
private fun BottomItem(
    icon: ImageVector,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(58.dp)
    ) {
        Icon(
            icon,
            contentDescription = label,
            modifier = Modifier.size(21.dp),
            tint = Color.Black
        )

        Text(
            label,
            fontSize = 8.sp,
            color = Color.Black
        )
    }
}