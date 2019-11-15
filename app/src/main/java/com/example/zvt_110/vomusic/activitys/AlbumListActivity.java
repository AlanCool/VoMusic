package com.example.zvt_110.vomusic.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {

    private MusicListAdapter musicListAdapter;
    private RecyclerView mRvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }

    private void initView() {
        initNavBar(true, "歌单歌曲", false);

        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        musicListAdapter=new MusicListAdapter(this,mRvList);
        mRvList.setAdapter(musicListAdapter);

    }
}
