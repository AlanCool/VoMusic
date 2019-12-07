package com.example.zvt_110.vomusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.zvt_110.vomusic.constans.SPConstants;
import com.example.zvt_110.vomusic.helps.UserHelper;


public class SPUtils {

    public static boolean spSaveUser(Context context, String phone) {
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPConstants.SP_KEY_PHONE, phone);
        boolean result = editor.commit();
        return result;
    }

    public static boolean isLoginUser(Context context) {
        boolean result = false;
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = sp.getString(SPConstants.SP_KEY_PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            result = true;
            UserHelper.getInstance().setPhone(phone);
        }
        return result;
    }

    public static boolean removeUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPConstants.SP_KEY_PHONE);
        boolean result = editor.commit();
        return result;
    }

}
