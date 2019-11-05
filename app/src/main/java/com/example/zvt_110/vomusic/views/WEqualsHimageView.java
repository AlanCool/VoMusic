package com.example.zvt_110.vomusic.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class WEqualsHimageView extends AppCompatImageView {
    public WEqualsHimageView(Context context) {
        super(context);
    }

    public WEqualsHimageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHimageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);


    }
}
