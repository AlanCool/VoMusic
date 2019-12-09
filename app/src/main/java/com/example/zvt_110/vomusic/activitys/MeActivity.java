package com.example.zvt_110.vomusic.activitys;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.helps.RealmHelper;
import com.example.zvt_110.vomusic.helps.UserHelper;
import com.example.zvt_110.vomusic.model.UserModel;
import com.example.zvt_110.vomusic.utils.UserUtils;

public class MeActivity extends BaseActivity {

    private TextView tv_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();

        tv_userName = findViewById(R.id.tv_userName);


        tv_userName.setText("用户名：" + UserHelper.getInstance().getPhone());

    }

    private void initView() {
        initNavBar(true, "个人中心", true);
    }

    public void onChangeClick(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    public void onLogoutClick(View view) {
        UserUtils.logout(this);
    }
}
