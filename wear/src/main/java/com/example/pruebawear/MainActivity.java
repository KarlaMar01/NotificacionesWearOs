package com.example.pruebawear;

import android.app.Activity;
import android.app.Notification;
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
import androidx.core.app.RemoteInput;

import com.example.pruebawear.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private Button wButton = null;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent,pendingIntent2;
    private NotificationCompat.Builder notification;
    private NotificationCompat.Builder notification2;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel = "Mi_Canal";
    int idNotification = 001;
    private  NotificationCompat.BigTextStyle bigTextStyle;
    String longText = "Without BigStyle, only a single line of text would be visible" +
            "Any additional text would not appear directly in the notification";
    private static final String KEY_TEXT_REPLY = "key_text_reply";

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

/*
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

 */
                // Key for the string that's delivered in the action's intent.


                String replyLabel = getResources().getString(R.string.reply_label);
                RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                        .setLabel(replyLabel)
                        .build();


                pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                // Create the reply action and add the remote input.
                NotificationCompat.Action action =
                        new NotificationCompat.Action.Builder(R.id.rectangles,
                                getString(R.string.label), pendingIntent)
                                .addRemoteInput(remoteInput)
                                .build();
                // Build the notification and add the action.

                notification = new NotificationCompat.Builder( MainActivity.this,idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Multi acción")
                        .setContentText("dos acciones")
                        .addAction(action)
                        .setContentIntent(pendingIntent);

                nm.notify(idNotification,notification.build());




            }
        });
/*
        //paginas
        List<Notification> pages = new ArrayList<Notification>();

        for(int i=1;i>3;i++){
            Notification nt
                    = new NotificationCompat.Builder(MainActivity.this,idChannel)
                    .setContentTitle("Pagina" + i)
                    .setContentText("Texto de la página")
                    .build();
            pages.add(nt);
        }


        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender().addPages(pages);

        Notification notification
                = new NotificationCompat.Builder(MainActivity.this,idChannel)
                .setContentTitle("Notificación multipagina")
                .setContentText("Esta es la primera Pagina")
                .build();
                 */

    }
}