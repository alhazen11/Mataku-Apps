package com.apps.mataku.utils


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.util.Log
import com.apps.mataku.R
import com.apps.mataku.ui.splash.SplashActivity
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        var body: String? = null

        if (remoteMessage!!.getData() != null) {
            body = remoteMessage.getData()["body"].toString()
        }

        var id: String? = null

        // get data that server passed (if it passed any at all)
        if (remoteMessage.getData().size > 0) {
            id = remoteMessage.getData()["id"]
        }
        var title: String? = null

        // get data that server passed (if it passed any at all)
        if (remoteMessage!!.getData() != null) {
            title = remoteMessage.getData()["title"].toString()
        }
        Log.d("testn",""+remoteMessage.getData()+title+body)
        sendNotification(body!!, title!!)
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(messageBody: String, title: String) {
        val intent = Intent(this, SplashActivity::class.java)
        intent.putExtra("go_to_notif", "in")
        intent.action = UUID.randomUUID().toString()

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Intent.FLAG_ACTIVITY_SINGLE_TOP or
        val pendingIntent = PendingIntent.getActivity(this, Random().nextInt(), intent,
            PendingIntent.FLAG_CANCEL_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_mata)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher))
            .setColor(ContextCompat.getColor(this, R.color.colorAccent))
            .setContentTitle(title)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.WHITE, 3000, 3000)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(Random().nextInt() /* ID of notification */, notificationBuilder.build())
    }

    companion object {

        private val TAG = "SmartHimaIIDService"
    }
}
