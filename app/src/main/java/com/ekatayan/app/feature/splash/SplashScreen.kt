package com.ekatayan.app.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Luggage
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekatayan.app.R
import com.ekatayan.app.core.designsystem.theme.EkataYanTheme
import kotlin.math.min

private val WelcomeBlue = Color(0xFF2398EB)
private val CardBorder = Color(0xFFE9E9E9)
private val IconTileBackground = Color(0xFFEAF5FF)
private val FeatureText = Color(0xFF17202A)
private val FeatureSecondaryText = Color(0xFF5F6975)

@Composable
fun SplashScreen(
    onGetStartedClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        // පූර්ණ තිරය පුරාම background image එක විහිදුවා සුදු පැහැති frame එක ඉවත් කිරීම
        Image(
            painter = painterResource(R.drawable.signup_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        val designWidth = 402f
        val designHeight = 874f
        val scale = min(maxWidth.value / designWidth, maxHeight.value / designHeight)

        Box(
            modifier = Modifier
                .size(designWidth.dp, designHeight.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .align(Alignment.Center),
        ) {
            Image(
                painter = painterResource(R.drawable.signup_logo),
                contentDescription = stringResource(R.string.welcome_logo_description),
                modifier = Modifier
                    .size(123.dp, 150.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 78.dp),
                contentScale = ContentScale.Fit,
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 228.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Ekata")
                        withStyle(SpanStyle(color = Color(0xFF126527))) { append("Yan") }
                    },
                    fontFamily = FontFamily.Serif,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    letterSpacing = (-1.1).sp,
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = Color(0xFF0A7B9C))) { append("AI") }
                        append(" Powered Travel Planning")
                    },
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }

            Text(
                text = stringResource(R.string.welcome_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 346.dp, start = 34.dp, end = 34.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color.Black.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
            )

            WelcomeFeatureCard(
                onGetStartedClick = onGetStartedClick,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 485.dp, start = 18.dp, end = 18.dp),
            )
        }
    }
}

@Composable
private fun WelcomeFeatureCard(
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(287.dp)
            .shadow(4.dp, RoundedCornerShape(15.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(horizontal = 23.dp, vertical = 23.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FeatureItem(Icons.Outlined.Luggage, R.string.welcome_trip_planner, R.string.welcome_trip_planner_subtitle)
            FeatureItem(Icons.Outlined.Groups, R.string.welcome_group_travel, R.string.welcome_group_travel_subtitle)
            FeatureItem(Icons.Outlined.AccountBalanceWallet, R.string.welcome_expense_manager, R.string.welcome_expense_manager_subtitle)
            FeatureItem(Icons.Outlined.CalendarMonth, R.string.welcome_all_in_one, R.string.welcome_all_in_one_subtitle)
        }

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FeatureItem(Icons.Outlined.LocationOn, R.string.welcome_explore_places, R.string.welcome_explore_places_subtitle)
            FeatureItem(Icons.Outlined.WbSunny, R.string.welcome_weather_alerts, R.string.welcome_weather_alerts_subtitle)
            FeatureItem(Icons.Outlined.NotificationsNone, R.string.welcome_smart_reminders, R.string.welcome_smart_reminders_subtitle)
            FeatureItem(Icons.Outlined.Security, R.string.welcome_safe_secure, R.string.welcome_safe_secure_subtitle)
        }

        Spacer(Modifier.height(22.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .shadow(4.dp, RoundedCornerShape(7.dp))
                .clip(RoundedCornerShape(7.dp))
                .background(WelcomeBlue)
                .clickable(onClick = onGetStartedClick),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.welcome_get_started),
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun FeatureItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: Int,
    subtitle: Int,
) {
    Column(
        modifier = Modifier.width(67.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(IconTileBackground),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = WelcomeBlue,
                modifier = Modifier.size(23.dp),
            )
        }
        Spacer(Modifier.height(5.dp))
        Text(
            text = stringResource(title),
            fontFamily = FontFamily.SansSerif,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 10.sp,
            color = FeatureText,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(subtitle),
            fontFamily = FontFamily.SansSerif,
            fontSize = 7.sp,
            lineHeight = 9.sp,
            color = FeatureSecondaryText,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, device = "spec:width=402dp,height=874dp,dpi=440")
@Composable
fun SplashScreenPreview() {
    EkataYanTheme {
        SplashScreen()
    }
}
