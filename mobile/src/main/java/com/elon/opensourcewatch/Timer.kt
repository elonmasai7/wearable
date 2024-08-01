package com.elon.opensourcewatch

import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerScreen() {
    var timeLeft by remember { mutableStateOf(60 * 1000L) } // 60 seconds
    var timer: CountDownTimer? = null
    var isRunning by remember { mutableStateOf(false) }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
            }

            override fun onFinish() {
                isRunning = false
            }
        }.start()
        isRunning = true
    }

    fun stopTimer() {
        timer?.cancel()
        isRunning = false
    }

    fun resetTimer() {
        stopTimer()
        timeLeft = 60 * 1000L
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = (timeLeft / 1000).toString(),
            fontSize = 48.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { if (isRunning) stopTimer() else startTimer() }) {
                Text(if (isRunning) "Stop" else "Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { resetTimer() }) {
                Text("Reset")
            }
        }
    }
}

fun remember(function: () -> Unit): Any {

}
