package com.example.zvt_110.vomusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.activitys.LoginActivity;

public class UserUtils {
    public static boolean validateLogin(Context context,String phone,String password){

        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context,"密码为空",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static void logout(Context context){
        Intent intent=new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

}
