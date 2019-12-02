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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.helps.MediaPlayHelp;
import com.example.zvt_110.vomusic.views.PlayMusicView;

public class PlayMusicActivity extends BaseActivity {
    private ImageView mIvbg;
    private PlayMusicView mPlayMusicView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

    }

    private void initView() {

        mIvbg = fd(R.id.mIvbg);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574178173426&di=69017d211f5165af608cbab5a28ccab2&imgtype=jpg&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D645549606%2C1881985083%26fm%3D214%26gp%3D0.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10))).into(mIvbg);

        mPlayMusicView=findViewById(R.id.playMusicView);
        mPlayMusicView.playMusic("");


    }


    public void onBackClick(View view) {
        onBackPressed();
    }
}
