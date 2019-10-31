package com.example.zvt_110.vomusic.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zvt_110.vomusic.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    public void initView(){
        initNavBar(true,"注册",false);
    }


    public void onRegisterClick(View view) {
    }
}
