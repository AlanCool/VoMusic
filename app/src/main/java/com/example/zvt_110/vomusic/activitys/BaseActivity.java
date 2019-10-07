package com.example.zvt_110.vomusic.activitys;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zvt_110.vomusic.R;

public class BaseActivity extends Activity {

    private ImageView mIvBack, mIvMe;
    private TextView mTvTitle;


    protected <T extends View> T fd(@IdRes int id) {
        return findViewById(id);
    }

    protected void initNavBar(boolean isBack, String title, boolean isShowMe) {
        mIvBack = fd(R.id.iv_back);
        mIvMe = fd(R.id.iv_me);
        mTvTitle = fd(R.id.tv_title);

        mIvBack.setVisibility(isBack ? View.VISIBLE : View.GONE);
        mIvMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        mTvTitle.setText(title);
    }
}
