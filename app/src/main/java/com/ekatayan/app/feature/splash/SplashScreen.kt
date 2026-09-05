package com.ekatayan.app.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ekatayan.app.R
import com.ekatayan.app.core.designsystem.theme.EkataYanTheme

/**
 * Full-screen launch artwork for EkataYan.
 *
 * `splash.png` is the approved splash composition and contains the landscape,
 * logo, wordmark, and tagline as one asset. Keeping it intact preserves the
 * typography and spacing supplied in the design reference across screen sizes.
 */
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.splash),
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true, device = "spec:width=402dp,height=874dp,dpi=440")
@Composable
fun SplashScreenPreview() {
    EkataYanTheme {
        SplashScreen()
    }
}
