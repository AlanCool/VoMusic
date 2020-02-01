package com.example.zvt_110.vomusic.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.zvt_110.vomusic.helps.MediaPlayHelp;
import com.example.zvt_110.vomusic.model.MusicModel;

public class MusicService extends Service {

    private MediaPlayHelp mMediaPlayHelp;
    private MusicModel mMusicModel;

    public MusicService() {
    }

    public class MusicBinder extends Binder {
        public void setMusic(MusicModel musicModel) {
            mMusicModel = musicModel;
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
}
