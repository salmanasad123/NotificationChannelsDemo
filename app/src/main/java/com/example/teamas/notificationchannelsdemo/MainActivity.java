package com.example.teamas.notificationchannelsdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText message;
    private Button sendNotificationOnChannel1;
    private Button sendNotificationOnChannel2;
    private NotificationManagerCompat notificationManagerCompat; // this compat wraps around notification manager and has some checks for backward compatibility


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        title = findViewById(R.id.et_title);
        message = findViewById(R.id.et_message);
        sendNotificationOnChannel1 = findViewById(R.id.btn_notify_channel1);
        sendNotificationOnChannel2 = findViewById(R.id.btn_notify_channel2);

        sendNotificationOnChannel1.setOnClickListener(this);
        sendNotificationOnChannel2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_notify_channel1:
                Notification notificationForChannel1 = new NotificationCompat.Builder(MainActivity.this, NotificationApp.CHANNEL_ID_1)
                        .setAutoCancel(true)
                        .setContentTitle(title.getText().toString())
                        .setContentText(message.getText().toString())
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        // set options we set in notification channel here too if we don't have api 26 (oreo)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .build();
                // when we use the same id to send notification again it will override the old notification
                notificationManagerCompat.notify(1, notificationForChannel1);
                break;

            case R.id.btn_notify_channel2:

                Notification notificationForChannel2 = new NotificationCompat.Builder(MainActivity.this, NotificationApp.CHANNEL_ID_2)
                        .setAutoCancel(true)
                        .setContentTitle(title.getText().toString())
                        .setContentText(message.getText().toString())
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .build();

                notificationManagerCompat.notify(2, notificationForChannel2);
                break;

        }
    }
}
