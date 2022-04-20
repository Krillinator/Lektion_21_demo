package com.krillinator.lektion_21_demo

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast

class RandomService : Service() {

    // Initialize Variables
    private var serviceLooper : Looper? = null
    private var serviceHandler : ServiceHandler? = null

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(5000)
            } catch (error: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        // Create new Thread
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()

            // Get Looper
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    // startService() triggers this function
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        //
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }

        // If killed - Restart from here
        return START_STICKY
    }

    // IS A MUST HAVE
    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }
}












