package com.example.zvt_110.vomusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.activitys.LoginActivity;
import com.example.zvt_110.vomusic.helps.RealmHelper;
import com.example.zvt_110.vomusic.model.UserModel;

import java.util.List;

public class UserUtils {
    public static boolean validateLogin(Context context, String phone, String password) {

        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "密码为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }


    public static boolean registerUser(Context context, String phone, String password, String passwordconfirm) {
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password != passwordconfirm && StringUtils.isEmpty(password)) {
            Toast.makeText(context, "密码不一致或密码为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (UserUtils.userExistFromPhone(phone)){
            Toast.makeText(context, "号码已存在", Toast.LENGTH_SHORT).show();
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        UserUtils.saveUser(userModel);
        return true;
    }

    private static void saveUser(UserModel userModel) {
        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveUser(userModel);
        realmHelper.close();
    }

    public static boolean userExistFromPhone(String phone) {
        boolean result = false;

        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> allUser = realmHelper.getAllUser();
        for (UserModel userModel : allUser) {
            if (userModel.getPhone().equals(phone)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
