package com.example.zvt_110.vomusic.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zvt_110.vomusic.R;
import com.example.zvt_110.vomusic.adapters.MusicListAdapter;
import com.example.zvt_110.vomusic.helps.RealmHelper;
import com.example.zvt_110.vomusic.model.AlbumModel;

public class AlbumListActivity extends BaseActivity {

    public static final String ALBUM_ID = "albumId";

    private MusicListAdapter musicListAdapter;
    private RecyclerView mRvList;
    private String mAlbumId;
    private RealmHelper realmHelper;
    private AlbumModel mAlbumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initData();
        initView();
    }

    private void initData() {
        mAlbumId = getIntent().getStringExtra(ALBUM_ID);
        realmHelper = new RealmHelper();
        mAlbumModel = realmHelper.getAlbum(mAlbumId);
    }

    private void initView() {
        initNavBar(true, "歌单歌曲", false);

        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        musicListAdapter = new MusicListAdapter(this, mRvList,mAlbumModel.getList());
        mRvList.setAdapter(musicListAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelper.close();
    }
}
