package com.example.zvt_110.vomusic.activitys;


import android.os.Bundle;
import android.view.View;

import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.utils.UserUtils;
import com.example.zvt_110.vomusic.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView mold_password, mnew_password, mnewpassword_comfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true, "修改密码", false);
        mold_password = fd(R.id.input_old_password);
        mnew_password = fd(R.id.input_new_password);
        mnewpassword_comfirm = fd(R.id.input_newpassword_comfirm);
    }

    public void onChangePasswordClick(View view) {
        String oldPassword = mold_password.getInputStr();
        String newPassword = mnew_password.getInputStr();
        String newPasswordConfirm = mnewpassword_comfirm.getInputStr();
        boolean result = UserUtils.changePassword(this, oldPassword, newPassword, newPasswordConfirm);
        if (!result) {
            return;
        }

        UserUtils.logout(this);
    }
}
