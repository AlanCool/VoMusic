package com.example.zvt_110.vomusic.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataUtils {

    public static String getJsonFromAssets(Context context, String fileName) {
        //字符存储器
        StringBuilder stringBuilder = new StringBuilder();
        //Asset资源管理器
        AssetManager assetManager = context.getAssets();
        try {
            //返回指定的资源
            InputStream inputStream = assetManager.open(fileName);
            //流读取
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //存放到缓存区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            //循坏readline读取每一条数据
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
