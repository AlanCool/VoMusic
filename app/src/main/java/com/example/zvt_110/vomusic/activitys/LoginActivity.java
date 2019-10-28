package com.example.zvt_110.vomusic.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.utils.UserUtils;
import com.example.zvt_110.vomusic.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView mInputpassword,mInputphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initNavBar(false, "登陆", false);

        mInputphone=fd(R.id.input_phone);
        mInputpassword=fd(R.id.inout_password);
    }

    public void onRegistersClick(View view) {
    }

    public void onCommitClick(View view) {
        String phone=mInputphone.getInputStr();
        String password=mInputpassword.getInputStr();
        if (!UserUtils.validateLogin(this,phone,password)){
            return;
        }
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
