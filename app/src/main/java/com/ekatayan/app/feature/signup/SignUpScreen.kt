package com.ekatayan.app.feature.signup

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekatayan.app.R

private val SkyBlue = Color(0xFF2398EB)
private val LinkBlue = Color(0xFF006FCF)
private val BrandGreen = Color(0xFF126527)
private val BrandBlue = Color(0xFF0A7B9C)
private val FieldBorder = Color.Black.copy(alpha = 0.4f)
private val SocialBorder = Color(0xFFF5E8E8)
private val SignUpContentWidth = 303.dp

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityClick: () -> Unit,
    onConfirmPasswordVisibilityClick: () -> Unit,
    onTermsAcceptedChange: (Boolean) -> Unit,
    onSignUpClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        SignUpBackdrop()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .imePadding(),
            contentPadding = PaddingValues(top = 52.dp, bottom = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item(contentType = "header") {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SignUpHeader()
                }
            }
            item(contentType = "form") {
                SignUpForm(
                    uiState = uiState,
                    onNameChange = onNameChange,
                    onEmailChange = onEmailChange,
                    onPhoneNumberChange = onPhoneNumberChange,
                    onPasswordChange = onPasswordChange,
                    onConfirmPasswordChange = onConfirmPasswordChange,
                    onPasswordVisibilityClick = onPasswordVisibilityClick,
                    onConfirmPasswordVisibilityClick = onConfirmPasswordVisibilityClick,
                    onTermsAcceptedChange = onTermsAcceptedChange,
                    onSignUpClick = onSignUpClick,
                )
            }
            item(contentType = "form_divider_space") { Spacer(Modifier.height(4.dp)) }
            item(contentType = "divider") { OrDivider() }
            item(contentType = "google") {
                SocialButton(
                    text = stringResource(R.string.signup_google),
                    icon = R.drawable.signup_google,
                    onClick = onGoogleClick,
                )
            }
            item(contentType = "social_space") { Spacer(Modifier.height(11.dp)) }
            item(contentType = "apple") {
                SocialButton(
                    text = stringResource(R.string.signup_apple),
                    icon = R.drawable.signup_apple,
                    onClick = onAppleClick,
                )
            }
            item(contentType = "login_space") { Spacer(Modifier.height(12.dp)) }
            item(contentType = "login") { LoginPrompt(onLoginClick = onLoginClick) }
        }
    }
}

@Composable
private fun SignUpBackdrop() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.signup_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        LanguageSelector(
            modifier = Modifier
                .safeDrawingPadding()
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 13.dp),
        )
    }
}

@Composable
private fun SignUpHeader() {
    Image(
        painter = painterResource(R.drawable.signup_logo),
        contentDescription = stringResource(R.string.signup_brand),
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(width = 123.dp, height = 150.dp),
    )
    Spacer(Modifier.height(6.dp))
    BrandLockup()
    Spacer(Modifier.height(14.dp))
    Text(
        text = stringResource(R.string.signup_title),
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
    )
    Spacer(Modifier.height(3.dp))
    Text(
        text = stringResource(R.string.signup_subtitle),
        color = Color.Black.copy(alpha = 0.75f),
        fontSize = 13.sp,
        lineHeight = 19.sp,
    )
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun LanguageSelector(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(50.dp), ambientColor = Color.Black.copy(alpha = 0.25f))
            .clip(RoundedCornerShape(50.dp))
            .background(Color.White)
            .clickable { }
            .padding(horizontal = 5.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.signup_globe),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = stringResource(R.string.signup_language),
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 4.dp),
        )
        Image(
            painter = painterResource(R.drawable.signup_chevron),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 7.dp, end = 1.dp)
                .size(width = 16.dp, height = 7.dp),
        )
    }
}

@Composable
private fun BrandLockup() {
    Text(
        text = buildAnnotatedString {
            append("Ekata")
            withStyle(SpanStyle(color = BrandGreen)) { append("Yan") }
        },
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontSize = 36.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.Bold,
    )
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = BrandBlue)) { append("AI") }
            append(" Powered Travel Planning")
        },
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun SignUpForm(
    uiState: SignUpUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityClick: () -> Unit,
    onConfirmPasswordVisibilityClick: () -> Unit,
    onTermsAcceptedChange: (Boolean) -> Unit,
    onSignUpClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .widthIn(max = SignUpContentWidth)
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE9E9E9), RoundedCornerShape(15.dp))
            .background(Color.White, RoundedCornerShape(15.dp))
            .padding(horizontal = 16.dp, vertical = 18.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(11.dp)) {
            SignUpTextField(
                value = uiState.name,
                onValueChange = onNameChange,
                placeholder = stringResource(R.string.signup_name),
                leadingIcon = R.drawable.signup_profile,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            SignUpTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                placeholder = stringResource(R.string.signup_email),
                leadingIcon = R.drawable.signup_email,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
            )
            SignUpTextField(
                value = uiState.phoneNumber,
                onValueChange = onPhoneNumberChange,
                placeholder = stringResource(R.string.signup_phone),
                leadingIcon = R.drawable.signup_phone,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next,
                ),
            )
            SignUpTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(R.string.signup_password),
                leadingIcon = R.drawable.signup_lock,
                isPassword = true,
                isPasswordVisible = uiState.isPasswordVisible,
                onPasswordVisibilityClick = onPasswordVisibilityClick,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
            )
            SignUpTextField(
                value = uiState.confirmPassword,
                onValueChange = onConfirmPasswordChange,
                placeholder = stringResource(R.string.signup_confirm_password),
                leadingIcon = R.drawable.signup_lock,
                isPassword = true,
                isPasswordVisible = uiState.isConfirmPasswordVisible,
                onPasswordVisibilityClick = onConfirmPasswordVisibilityClick,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            )
        }
        Spacer(Modifier.height(16.dp))
        TermsRow(
            checked = uiState.hasAcceptedTerms,
            onCheckedChange = onTermsAcceptedChange,
        )
        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .shadow(2.dp, RoundedCornerShape(9.dp))
                .clip(RoundedCornerShape(9.dp))
                .background(SkyBlue)
                .clickable(onClick = onSignUpClick),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.signup_action),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityClick: (() -> Unit)? = null,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodySmall.copy(
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = if (isPassword && !isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, FieldBorder, RoundedCornerShape(9.dp))
                    .padding(start = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 11.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Black.copy(alpha = 0.75f),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    innerTextField()
                }
                if (isPassword && onPasswordVisibilityClick != null) {
                    Image(
                        painter = painterResource(R.drawable.signup_eye),
                        contentDescription = stringResource(
                            if (isPasswordVisible) R.string.signup_hide_password
                            else R.string.signup_show_password,
                        ),
                        modifier = Modifier
                            .size(42.dp)
                            .padding(start = 10.dp, top = 13.dp, end = 14.dp, bottom = 13.dp)
                            .alpha(if (isPasswordVisible) 1f else 0.72f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onPasswordVisibilityClick,
                            ),
                    )
                } else {
                    Spacer(Modifier.width(14.dp))
                }
            }
        },
    )
}

@Composable
private fun TermsRow(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .heightIn(min = 32.dp)
            .clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(if (checked) SkyBlue else Color.White)
                .border(1.dp, if (checked) SkyBlue else FieldBorder, RoundedCornerShape(3.dp)),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Text(
                    text = "✓",
                    color = Color.White,
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Text(
            text = termsText(),
            color = Color.Black,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
        )
    }
}

@Composable
private fun termsText(): AnnotatedString = buildAnnotatedString {
    append(stringResource(R.string.signup_terms_prefix))
    withStyle(SpanStyle(color = LinkBlue)) { append(stringResource(R.string.signup_terms)) }
    append(stringResource(R.string.signup_terms_joiner))
    withStyle(SpanStyle(color = LinkBlue)) { append(stringResource(R.string.signup_privacy)) }
}

@Composable
private fun OrDivider() {
    Row(
        modifier = Modifier
            .widthIn(max = 281.dp)
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(1f).height(1.dp).background(Color.Black.copy(alpha = 0.45f)))
        Text(
            text = stringResource(R.string.signup_or),
            color = Color.Black.copy(alpha = 0.5f),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 14.dp),
        )
        Box(Modifier.weight(1f).height(1.dp).background(Color.Black.copy(alpha = 0.45f)))
    }
}

@Composable
private fun SocialButton(text: String, @DrawableRes icon: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .widthIn(max = SignUpContentWidth)
            .fillMaxWidth()
            .height(46.dp)
            .shadow(2.dp, RoundedCornerShape(9.dp))
            .clip(RoundedCornerShape(9.dp))
            .background(Color.White)
            .border(1.dp, SocialBorder, RoundedCornerShape(9.dp))
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = text,
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

@Composable
private fun LoginPrompt(onLoginClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.signup_existing),
            color = Color.Black,
            fontSize = 12.sp,
        )
        Text(
            text = stringResource(R.string.signup_login),
            color = LinkBlue,
            fontSize = 12.sp,
            modifier = Modifier.clickable(onClick = onLoginClick),
        )
    }
}
