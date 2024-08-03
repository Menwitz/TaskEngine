package com.menwitz.taskengine

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.app.Service
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class TaskEngineService : AccessibilityService() {

    companion object {
        private const val TAG = "TaskEngineService"
        private var INSTANCE: TaskEngineService? = null

        fun getInstance(): TaskEngineService? = INSTANCE
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        INSTANCE = this
        Log.d(TAG, "Service connected")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        INSTANCE = null
        return super.onUnbind(intent)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Handle accessibility events if needed
    }

    override fun onInterrupt() {
        // Handle service interruptions if needed
    }

    suspend fun executeGesture(gestureDescription: GestureDescription) {
        suspendCancellableCoroutine<Unit> { continuation ->
            try {
                dispatchGesture(gestureDescription, object : GestureResultCallback() {
                    override fun onCompleted(gestureDescription: GestureDescription?) {
                        continuation.resume(Unit)
                    }

                    override fun onCancelled(gestureDescription: GestureDescription?) {
                        Log.w(TAG, "Gesture cancelled: $gestureDescription")
                        continuation.resume(Unit)
                    }
                }, null)
            } catch (e: Exception) {
                Log.e(TAG, "Error executing gesture", e)
                continuation.resume(Unit)
            }
        }
    }
}
