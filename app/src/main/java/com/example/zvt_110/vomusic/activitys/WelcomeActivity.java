package com.example.zvt_110.vomusic.activitys;

import android.content.Intent;

import android.os.Bundle;

import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.utils.UserUtils;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private Timer mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        final boolean isLogin= UserUtils.validateUserLogin(this);
        mTime = new Timer();
        mTime.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isLogin){
                    toMian();
                }else {
                    toLoginActivity();
                }
            }
        }, 3 * 1000);
    }

    private void toMian() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
