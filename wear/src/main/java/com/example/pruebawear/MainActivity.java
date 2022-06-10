package com.example.pruebawear;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private Button wButton = null;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent;
    private NotificationCompat.Builder notification;
    private NotificationCompat.Builder notification2;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel = "Mi_Canal";
    int idNotification = 001;
    private  NotificationCompat.BigTextStyle bigTextStyle;
    String longText = "Without BigStyle, only a single line of text would be visible" +
            "Any additional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wButton= findViewById(R.id.wButton);

        intent = new Intent(MainActivity.this, MainActivity.class);

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();

        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        wButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                Timer t = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        String name = "Notification 2";
                        NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                        nm.createNotificationChannel(notificationChannel);

                        pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                        notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Mi notification")
                                .setContentText("Mi Segunda notificacion wear")
                                .extend(wearableExtender);
                        nm.notify(idNotification, notification.build());
                    }
                };
                t.schedule(tt, 6000);

                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notification";

                NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

             /*   notification = new NotificationCompat.Builder(MainActivity.this,idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notificación Wear")
                        .setContentText(longText)
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender)
                        .setVibrate(new long[]{100,200,300,400,500,400,300,200,400})
                        .setStyle(bigTextStyle); */

                notification = new NotificationCompat.Builder( MainActivity.this,idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Mi Notificación")
                        .setContentText("Mi Primera Notificación wear")
                        .extend(wearableExtender);


               nm.notify(idNotification,notification.build());

                // Build a new notification, which informs the user that the system
                // handled their interaction with the previous notification.
             /*   notification2 = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("Esta es la segunda notificación que se muestra")
                        ;

                // Issue the new notification.
                nm.notify(idNotification, notification2.build()); */


            }
        });

    }
}