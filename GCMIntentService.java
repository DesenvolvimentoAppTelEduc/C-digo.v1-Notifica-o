package com.example.andre.teleducgcm;


import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class GcmIntentService extends IntentService {
    public static int NOTIFICATION_ID = 1;
    public static int contador = 1;
    public static String contadormateria = "";
    public static int contadormatematica = 0;
    public static int contadorestat = 0;
    public static String mensagemsub = "";




    public static String mensagem = "";

    private static final String TAG = "GcmIntentService";
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);



        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */
            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                Log.i(TAG, "Error: " + extras.toString());
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
                Log.i(TAG, "Deleted: " + extras.toString());
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                for (int i=0; i<5; i++) {
                    Log.i(TAG, "Working... " + (i+1)
                            + "/5 @ " + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
                // Post notification of received message.
                String notice= extras.getString("Notice");
                String group= extras.getString("group");
                sendNotification(notice, group);

                Log.i(TAG, "Received: " + extras.toString());
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String notice, String group) {
        if (contador==1){
            mensagem=" nova mensagem";
        } else {
            mensagem=" novas mensagens";
        }
        //if(group == "Matemática"){
            //contadormateria="Matemática";
           // contadormatematica++;
        //} else if (group=="Estatística"){
          //  contadormateria="Estatística";
          //  contadormatematica++;
       // }
       // if(contadormatematica==1){
       //     mensagemsub="Matemática mandou uma nova mensagem";
       // } else if(contadorestat==1){
       //     mensagemsub="Estatística mandou uma nova mensagem";
       // } else if(contadorestat==1&&contadormatematica==1){
          //  mensagemsub="Estatística e Matemática mandaram uma nova mensagem";
       // } else {
        //    mensagemsub="Estatística e Matemática mandaram uma novas mensagens";
        //}


        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, JanelaMensagem.class);
        notificationIntent.putExtra("notice",notice);
        notificationIntent.putExtra("group",group);
        //notificationIntent.putExtra("ConteudoMensagem",ConteudoMensagem);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        // .setSmallIcon(R.drawable.ic_stat_gcm)
                        .setContentTitle(contador+mensagem)
                        .setSmallIcon(R.drawable.icone_teleduc)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(notice))
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setVibrate(new long[] { 0, 100, 200, 300 })
                        .setSubText(group+ " mandou " +contador +mensagem)
                        .setContentText(group);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        contador++;
    }


}
