package com.elon.opensourcewatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material.*
import com.example.wearablewatch.ui.theme.WearableWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearableWatchTheme {
                WatchApp()
            }
        }
    }
}


fun WatchApp() {
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        state = listState,
        anchorType = ScalingLazyListAnchorType.ItemCenter,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { WatchFaceScreen() }
        item { TimerScreen() }
        item { StopwatchScreen() }
    }
}

fun WatchFaceScreen() {
    Text(
        text = "Watch Face",
        style = MaterialTheme.typography.title1,
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    )
}


fun TimerScreen() {
    Text(
        text = "Timer",
        style = MaterialTheme.typography.title1,
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    )
}

fun StopwatchScreen() {
    Text(
        text = "Stopwatch",
        style = MaterialTheme.typography.title1,
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    )
}