package com.osg.prctice02webview

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService;

open class FirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        //메세지를 수신했을 때
        Log.i("푸시", "무사히 수신함!!")

        var notiTitle = "타이틀 안옴"
        var notiBody = "바디도 안옴"

        if(p0.notification != null) {
            notiTitle = p0.notification?.title.toString()
            notiBody = p0.notification?.body.toString()
        }


        var notiManager = getSystemService(NotificationManager::class.java)
        var builder: NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("cha01",
                    "test channel",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notiManager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, "cha01")
        } else {
            builder = NotificationCompat.Builder(this, "")
        }

        builder.setSmallIcon(R.drawable.ic_android_black_24dp).setContentTitle(notiTitle).setContentText(notiBody)

        var notification = builder.build()

        notiManager.notify(10, notification)
    }

    override fun onNewToken(p0: String) {
        Log.i("refreshed token", p0)
    }
}