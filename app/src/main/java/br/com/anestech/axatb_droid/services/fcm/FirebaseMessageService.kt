package br.com.anestech.axcalc.services.fcm

import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import br.com.anestech.axatb_droid.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.PendingIntent
import android.content.Intent
import br.com.anestech.axatb_droid.activity.MainActivity

import java.util.*

class FirebaseMessageService : FirebaseMessagingService() {

    val CHANNEL_ID = "axatb"
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        Log.d("@@@AxAtb", "From: " + remoteMessage!!.from)

        if (remoteMessage.notification != null) {

            try{
                val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                        .setColor(ContextCompat.getColor(applicationContext, R.color.primary))
                        .setContentTitle(remoteMessage.notification!!.title!!)
                        .setContentText(remoteMessage.notification!!.body!!)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSmallIcon(R.drawable.ic_notification)

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("open_ads", true)
                intent.action = "open_ads"

                val contentIntent = PendingIntent.getActivity(this, Random().nextInt(),
                        intent, PendingIntent.FLAG_UPDATE_CURRENT)

                mBuilder.setContentIntent(contentIntent)

                val notificationManager = NotificationManagerCompat.from(this)
                notificationManager.notify(Random().nextInt(), mBuilder.build())

            }catch (ex:Exception){
                ex.printStackTrace()
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("@@@AxAtb", "Message data payload: " + remoteMessage.data)
        }
    }

}
