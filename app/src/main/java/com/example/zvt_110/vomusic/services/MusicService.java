package com.example.zvt_110.vomusic.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.telephony.mbms.StreamingServiceInfo;

import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.activitys.WelcomeActivity;
import com.example.zvt_110.vomusic.helps.MediaPlayHelp;
import com.example.zvt_110.vomusic.model.MusicModel;

import androidx.annotation.RequiresApi;

public class MusicService extends Service {

    public static final int NOTIFICATION_ID = 1;
    private MediaPlayHelp mMediaPlayHelp;
    private MusicModel mMusicModel;

    public MusicService() {
    }

    public class MusicBinder extends Binder {
        public void setMusic(MusicModel musicModel) {
            mMusicModel = musicModel;
            startForeground();
        }

        public void playMusic() {
            if (mMediaPlayHelp.getpath() != null
                    && mMediaPlayHelp.getpath().equals(mMusicModel.getPath())
            ) {
                mMediaPlayHelp.start();
            } else {
                mMediaPlayHelp.setPath(mMusicModel.getPath());
                mMediaPlayHelp.setOnMediaPlayerHelperListener(new MediaPlayHelp.onMediaPlayerHelperListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayHelp.start();
                    }

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopSelf();
                    }
                });
            }
        }

        public void stopMusic() {
            mMediaPlayHelp.pause();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayHelp = MediaPlayHelp.getInstance(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }



    private void startForeground() {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, WelcomeActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = createNotificationChannel();
            notification = new Notification.Builder(this, channel.getId())
                    .setContentTitle(mMusicModel.getName())
                    .setContentText(mMusicModel.getAuthor())
                    .setSmallIcon(R.mipmap.me)
                    .setContentIntent(pendingIntent)
                    .build();
                    NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(channel);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notification = new Notification.Builder(this)
                        .setContentTitle(mMusicModel.getName())
                        .setContentText(mMusicModel.getAuthor())
                        .setSmallIcon(R.mipmap.me)
                        .setContentIntent(pendingIntent)
                        .build();
            }
        }

        startForeground(NOTIFICATION_ID, notification);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel() {
        String channelId = "one";
        String channelName = "oneName";
        String Description = "VoMusic";
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(Description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        channel.setShowBadge(false);
        return channel;

    }
}
