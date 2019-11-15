package com.example.zvt_110.vomusic.activitys;

import android.os.Bundle;


import com.example.zvt_110.vomusic.R;

import com.example.zvt_110.vomusic.adapters.MusicGridAdapter;
import com.example.zvt_110.vomusic.adapters.MusicListAdapter;
import com.example.zvt_110.vomusic.views.GridSpaceItemDecoration;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvGrid, mRvList;
    private MusicGridAdapter musicGridAdapter;
    private MusicListAdapter musicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        initNavBar(false, "VoMusic", true);

        mRvGrid = fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvGrid));
        mRvGrid.setNestedScrollingEnabled(false);
        musicGridAdapter = new MusicGridAdapter(this);
        mRvGrid.setAdapter(musicGridAdapter);

        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        musicListAdapter=new MusicListAdapter(this,mRvList);
        mRvList.setAdapter(musicListAdapter);

    }


}
