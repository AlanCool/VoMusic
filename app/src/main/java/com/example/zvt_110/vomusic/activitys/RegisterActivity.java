package com.example.zvt_110.vomusic.activitys;


import android.os.Bundle;
import android.view.View;

import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.utils.UserUtils;
import com.example.zvt_110.vomusic.views.InputView;

import io.realm.Realm;

public class RegisterActivity extends BaseActivity {

    private InputView input_phone, input_password, input_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    public void initView() {
        initNavBar(true, "注册", false);
        input_phone = fd(R.id.input_registerphone);
        input_password = fd(R.id.input_registerpassword);
        input_password_confirm = fd(R.id.input_registerpassword_comfirm);


    }


    public void onRegisterClick(View view) {

        String phone = input_phone.getInputStr();
        String password = input_password.getInputStr();
        String password_comfirm = input_password_confirm.getInputStr();
        boolean result = UserUtils.registerUser(this, phone, password, password_comfirm);
        if (!result) return;
        onBackPressed();
    }
}
