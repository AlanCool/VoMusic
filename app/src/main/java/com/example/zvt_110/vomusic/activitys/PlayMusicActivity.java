package com.example.zvt_110.vomusic.activitys;

import androidx.appcompat.app.AppCompatActivity;
import jp.wasabeef.glide.transformations.BlurTransformation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.helps.MediaPlayHelp;
import com.example.zvt_110.vomusic.helps.RealmHelper;
import com.example.zvt_110.vomusic.model.MusicModel;
import com.example.zvt_110.vomusic.views.PlayMusicView;

public class PlayMusicActivity extends BaseActivity {
    private ImageView mIvbg;
    private PlayMusicView mPlayMusicView;

    public static final String MUSIC_ID = "musicId";

    private String musicId;
    private RealmHelper realmHelper;
    private MusicModel musicModel;
    private TextView tv_name, tv_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();

    }

    private void initData() {
        musicId = getIntent().getStringExtra(MUSIC_ID);
        realmHelper = new RealmHelper();
        musicModel = realmHelper.getMusic(musicId);

    }

    private void initView() {

        mIvbg = fd(R.id.mIvbg);
        tv_name = fd(R.id.tv_name);
        tv_author = fd(R.id.tv_author);
        Glide.with(this).load(musicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10))).into(mIvbg);

        tv_name.setText(musicModel.getName());
        tv_author.setText(musicModel.getAuthor());


        mPlayMusicView = findViewById(R.id.playMusicView);
        mPlayMusicView.setMusic(musicModel);
       // mPlayMusicView.setMusicIcon();
        mPlayMusicView.playMusic(musicModel.getPath());


    }


    public void onBackClick(View view) {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelper.close();
    }
}
