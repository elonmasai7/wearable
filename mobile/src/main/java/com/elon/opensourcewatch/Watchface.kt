package com.elon.opensourcewatch

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.wearable.watchface.CanvasWatchFaceService
import android.support.wearable.watchface.WatchFaceStyle
import android.view.SurfaceHolder
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import java.util.*

class WatchFaceService : CanvasWatchFaceService() {
    override fun onCreateEngine(): Engine {
        return Engine()
    }

    inner class Engine : CanvasWatchFaceService.Engine() {
        private val updateTimeMessageId = 0
        private val interactiveUpdateRateMs = 1000
        private val calendar: Calendar = Calendar.getInstance()
        private val backgroundPaint: Paint = Paint().apply {
            color = Color.BLACK
        }
        private val textPaint: Paint = Paint().apply {
            color = Color.WHITE
            textSize = 40f
            isAntiAlias = true
        }
        private val updateTimeHandler: Handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.what == updateTimeMessageId) {
                    invalidate()
                    if (isVisible && !isInAmbientMode) {
                        val timeMs = SystemClock.uptimeMillis()
                        val delayMs = interactiveUpdateRateMs - (timeMs % interactiveUpdateRateMs)
                        this.sendEmptyMessageDelayed(updateTimeMessageId, delayMs)
                    }
                }
            }
        }

        override fun onCreate(holder: SurfaceHolder?) {
            super.onCreate(holder)
            setWatchFaceStyle(
                WatchFaceStyle.Builder(this@WatchFaceService)
                    .setShowSystemUiTime(false)
                    .build()
            )
        }

        override fun onDraw(canvas: Canvas?, bounds: android.graphics.Rect?) {
            canvas?.drawRect(0f, 0f, bounds!!.width().toFloat(), bounds.height().toFloat(), backgroundPaint)
            calendar.timeInMillis = System.currentTimeMillis()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val text = String.format("%02d:%02d", hour, minute)
            canvas?.drawText(text, bounds.exactCenterX(), bounds.exactCenterY(), textPaint)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            if (visible) {
                updateTimeHandler.sendEmptyMessage(updateTimeMessageId)
            } else {
                updateTimeHandler.removeMessages(updateTimeMessageId)
            }
        }
    }
}