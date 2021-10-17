package com.example.tubespw_mehtravelling.PushNotif;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class MyApplication extends Application {
    public static final String CHANNEL_1_ID = "Channel1";

    public void onCreate(){
        super.onCreate();
//        FirebaseMessaging.getInstance().subscribeToTopic("sample_notification");
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }
}

