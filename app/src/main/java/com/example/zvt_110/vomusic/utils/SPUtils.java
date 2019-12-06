package com.example.zvt_110.vomusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zvt_110.vomusic.constans.SPConstants;


public class SPUtils {

    public boolean spSaveUser(Context context, String phone) {
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPConstants.SP_KEY_PHONE, phone);
        boolean result = editor.commit();
        return result;
    }

}
