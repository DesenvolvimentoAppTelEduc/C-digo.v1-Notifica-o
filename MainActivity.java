package com.example.gabrieldepaula.teleduc;
import android.app.Notification;
import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    private int numMessages = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.btclick);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                displayNotification();
            }
        });
    }
    protected void displayNotification() {
        //Aparece que a notificação foi iniciada no console do Android Studio.
        Log.i("Start", "notification");

      /* Invoking the default notification service */
        NotificationCompat.Builder  mBuilder =
                new NotificationCompat.Builder(this);
        //Titulo da notificação, icone, conteúdo e titulo do conteúdo da notificação.
        mBuilder.setContentTitle("Você tem uma nova mensagem!");
        mBuilder.setContentText("Você recebeu um arquivo, clique para ver.");
        mBuilder.setTicker("Você tem uma nova mensagem!");
        //fecha a notificação depois se ser clicada.
        mBuilder.setAutoCancel(true);
        mBuilder.setSmallIcon(R.drawable.icone_teleduc);
        //Intenção para trocar de classe(Tela)
        Intent notificationIntent = new Intent(this, NotificationReceiverActivity.class);
        //Atualizar a notificação em tempo real.
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);
        //Inicia a notificação.
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notificationID, mBuilder.build());
    }
}
