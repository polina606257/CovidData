package com.example.coviddata.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import kotlinx.coroutines.*


class RefreshDataService : Service() {
    val repository = CovidApp.repository

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        doStartForeground()
        GlobalScope.launch {
            while(true){
                val worldData = async{repository.getWorldData()} //5 sec
                val countriesData = async{repository.getAllCountriesData()}//7 sec
                Log.d("myLog", "refresh completed")
                delay(10000)
            }
        }
        return START_STICKY
    }

    private fun doStartForeground(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val chanelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                createNotificationChannel()
            else{
                ""
            }
        val notificationBuilder = NotificationCompat.Builder(this, chanelId)
        val notification = notificationBuilder
            .setOngoing(true)
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setPriority(PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()

        startForeground(101, notification)
    }


    private fun createNotificationChannel(): String{
        val channelId = "my_service"
        val channelName = "My Background Service"
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_HIGH)
        chan.lightColor = Color.BLUE
        chan.importance = NotificationManager.IMPORTANCE_NONE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}