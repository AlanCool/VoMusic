package com.example.zvt_110.vomusic.activitys;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.Utils;
import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.utils.UserUtils;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",true);
    }

    public void onChangeClick(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));
    }

    public void onLogoutClick(View view) {
        UserUtils.logout(this);
    }
}
