package com.example.zvt_110.vomusic.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private View playMusicView;
    private Animation playMusicAnim, playNeedleAnim, stopNeedleAnim;
    private FrameLayout flPlayMusic;
    private ImageView iv_needle, iv_play;
    private boolean isPlaying;
    private MediaPlayHelp mediaPlayHelp;
    private String mPath;
    private ImageView mIvcon, mIvNeedle, mIvPlay;
    private MusicModel musicModel;

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
        mIvcon = findViewById(R.id.civ_icon);
        iv_needle = playMusicView.findViewById(R.id.iv_needle);
        iv_play = playMusicView.findViewById(R.id.iv_play);
        playMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        playNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        stopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);
        //playMusic("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");

        addView(playMusicView);

        mediaPlayHelp = MediaPlayHelp.getInstance(mContext);
    }

    public void playMusic(String path) {
        mPath = path;
        isPlaying = true;
        flPlayMusic.setAnimation(playMusicAnim);
        iv_needle.setAnimation(playNeedleAnim);
        iv_play.setVisibility(View.GONE);

        if (mediaPlayHelp.getpath() != null
                && mediaPlayHelp.getpath().equals(path)
        ) {
            mediaPlayHelp.start();
        } else {
            mediaPlayHelp.setPath(path);
            mediaPlayHelp.setOnMediaPlayerHelperListener(new MediaPlayHelp.onMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayHelp.start();
                }
            });
        }

    }

    public void stopMusic() {
        isPlaying = false;
        flPlayMusic.clearAnimation();
        iv_needle.setAnimation(stopNeedleAnim);
        iv_play.setVisibility(View.VISIBLE);

        mediaPlayHelp.pause();
    }

    public void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(mPath);
        }
    }

    public void setMusicIcon(){
        Glide.with(mContext).load(musicModel.getPoster()).into(mIvcon);
    }

    public void setMusic(MusicModel musicModel) {
        this.musicModel = musicModel;
    }

}
