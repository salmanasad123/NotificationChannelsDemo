package com.example.teamas.notificationchannelsdemo;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationApp extends Application {

    // this class wraps our whole application, This is mainly used for initialization of anything that we need
    // before our first activity runs, eg global or static methods
    // Specialized tasks that need to run before the creation of your first activity
    //Global initialization that needs to be shared across all components (crash reporting, persistence)

    public static final String CHANNEL_ID_1 = "channel1";
    public static final String CHANNEL_ID_2 = "channel2";


    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel1 = new NotificationChannel(
                    CHANNEL_ID_1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel1.enableLights(true);
            notificationChannel1.setLightColor(R.color.colorAccent);
            notificationChannel1.enableVibration(true);
            notificationChannel1.setDescription("This is channel 1");

            NotificationChannel notificationChannel2 = new NotificationChannel(
                    CHANNEL_ID_2,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel2.enableLights(true);
            notificationChannel2.setLightColor(R.color.colorPrimary);
            notificationChannel2.enableVibration(false);
            notificationChannel2.setDescription("This is channel 2");


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel1);
                notificationManager.createNotificationChannel(notificationChannel2);
            }
        }
    }

}
