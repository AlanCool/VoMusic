package com.example.zvt_110.vomusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.nio.file.Path;

public class MediaPlayHelp {

    private static MediaPlayHelp instance;
    private Context mContext;
    private MediaPlayer mediaPlayer;
    private onMediaPlayerHelperListener onMediaPlayerHelperListener;
    private String mPath;

    public void setOnMediaPlayerHelperListener(MediaPlayHelp.onMediaPlayerHelperListener onMediaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    public static MediaPlayHelp getInstance(Context context) {
        if (instance == null) {
            synchronized (MediaPlayHelp.class) {
                if (instance == null) {
                    instance = new MediaPlayHelp(context);
                }
            }
        }
        return instance;
    }

    public MediaPlayHelp(Context context) {
        mContext = context;
        mediaPlayer = new MediaPlayer();
    }

    public void setPath(String path) {
        /*
        1 若音乐在播放，重置音乐
        2 设置播放路径
        3 准备播放设置
         */

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }

        try {
            mediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (onMediaPlayerHelperListener != null) {
                    onMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
        mPath=path;
    }

    public String getpath(){
        return mPath;
    }

    public void start() {
        if (mediaPlayer.isPlaying()) return;
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public interface onMediaPlayerHelperListener {
        void onPrepared(MediaPlayer mp);
    }
}
