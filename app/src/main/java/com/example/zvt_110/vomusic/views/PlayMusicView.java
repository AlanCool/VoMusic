package com.example.zvt_110.vomusic.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.helps.MediaPlayHelp;
import com.example.zvt_110.vomusic.model.MusicModel;
import com.example.zvt_110.vomusic.services.MusicService;

import java.security.PublicKey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private View playMusicView;
    private Animation playMusicAnim, playNeedleAnim, stopNeedleAnim;
    private FrameLayout flPlayMusic;
    private ImageView iv_needle, iv_play;
    private boolean isPlaying, isBindService;
    private Intent mServiceIntent;
    private ImageView mIvcon, mIvNeedle, mIvPlay;
    private MusicModel musicModel;
    private MusicService.MusicBinder mMusicBind;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        playMusicView = LayoutInflater.from(mContext).inflate(R.layout.play_music_view, this, false);
        flPlayMusic = playMusicView.findViewById(R.id.fl_play_music);
        flPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });
        mIvcon = playMusicView.findViewById(R.id.civ_icon);
        iv_needle = playMusicView.findViewById(R.id.iv_needle);
        iv_play = playMusicView.findViewById(R.id.iv_play);
        playMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        playNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        stopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);
        //playMusic("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");

        addView(playMusicView);


    }

    public void playMusic() {
        isPlaying = true;
        flPlayMusic.setAnimation(playMusicAnim);
        iv_needle.setAnimation(playNeedleAnim);
        iv_play.setVisibility(View.GONE);

        startMusicService();

    }

    public void stopMusic() {
        isPlaying = false;
        flPlayMusic.clearAnimation();
        iv_needle.setAnimation(stopNeedleAnim);
        iv_play.setVisibility(View.VISIBLE);
        mMusicBind.stopMusic();


    }

    public void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic();
        }
    }

    public void setMusicIcon() {
        Glide.with(mContext).load(musicModel.getPoster()).into(mIvcon);
    }


    public void setMusic(MusicModel musicModel) {
        this.musicModel = musicModel;
        setMusicIcon();
    }

    private void startMusicService() {
        if (mServiceIntent == null) {
            mServiceIntent = new Intent(mContext, MusicService.class);
            mContext.startService(mServiceIntent);
        }

        if (!isBindService) {
            isBindService = true;
            mContext.bindService(mServiceIntent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    public void destory() {
        if (isBindService) {
            isBindService = false;
            mContext.unbindService(conn);
        }
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicBind = (MusicService.MusicBinder) service;
            mMusicBind.setMusic(musicModel);
            mMusicBind.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
