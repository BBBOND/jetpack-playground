package com.bbbond.playground.activity

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.bbbond.playground.DarkColors
import com.bbbond.playground.LightColors
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Page()
        }
    }
}

@Composable
fun Page() {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColors else LightColors
    ) {
        ProvideWindowInsets {
            rememberSystemUiController().setStatusBarColor(
                MaterialTheme.colors.primaryVariant,
                darkIcons = isSystemInDarkTheme()
            )
            Scaffold(
                topBar = {
                    Header()
                },
                content = {
                    Content()
                },
                bottomBar = {
                    BottomBar()
                }
            )
        }
    }
}

@Composable
fun Header() {
    TopAppBar(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.statusBars,
            applyStart = true,
            applyTop = true,
            applyEnd = true,
        ),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        val currentActivity = (LocalContext.current as? Activity)
        IconButton(onClick = {
            currentActivity?.finish()
        }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
        Text(
            "Compose",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        )
        IconButton(onClick = {}, enabled = false) {
//            Icon(Icons.Filled.More, contentDescription = "More")
        }
    }
}

@Composable
fun Content() {
    Column {
        Test()
        Test()
        Test()
        Test()
    }
}

@Composable
fun BottomBar() {
    Spacer(
        Modifier
            .navigationBarsHeight()
            .fillMaxWidth()
    )
}

@Composable
fun Test() {
    Text("测试")
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewContent() {
    Page()
}