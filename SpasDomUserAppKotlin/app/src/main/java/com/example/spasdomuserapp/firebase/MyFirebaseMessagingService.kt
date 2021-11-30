package com.example.spasdomuserapp.firebase


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.database.UserPreferences
import com.example.spasdomuserapp.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val NOTIFICATION_CHANNEL_ID = "com.example.spasdomuserapp"
const val NOTIFICATION_ID = 100

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private lateinit var userPreferences: UserPreferences

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onCreate() {
        super.onCreate()
        val componentName = ComponentName(
            applicationContext,
            MyFirebaseMessagingService::class.java
        )

        userPreferences = UserPreferences(this)

        applicationContext.packageManager.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        serviceScope.launch {
            userPreferences.saveFcmToken(p0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (remoteMessage.data.isNotEmpty()) {
            val title = remoteMessage.data["title"]
            val body = remoteMessage.data["body"]
            showNotification(applicationContext, title, body)
        } else {
            val title = remoteMessage.notification!!.title
            val body = remoteMessage.notification!!.body
            showNotification(applicationContext, title, body)
        }

        super.onMessageReceived(remoteMessage)
    }


    private fun showNotification(context: Context, title: String?, message: String?) {
        val intent = Intent(context, MainActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("notification_title", title)
        intent.putExtra("notification_message", message)

        val pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification: Notification

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_notification_important)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setWhen(System.currentTimeMillis())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(title).build()

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, title, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.notify(NOTIFICATION_ID, notification)
        } else {
            // TODO(Doesn't show notification when app is killed)
            notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_important)
                .setAutoCancel(true)
                .setContentText(message)
                .setContentIntent(pi)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(title).build()

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }
}


data class MyNotification(
    val title: String?,
    val message: String?
)