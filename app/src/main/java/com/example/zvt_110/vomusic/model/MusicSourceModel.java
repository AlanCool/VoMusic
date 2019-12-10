package com.example.zvt_110.vomusic.model;

import io.realm.RealmList;

public class MusicSourceModel {

    private RealmList<AlbumModel> album;
    private RealmList<MusicModel> hot;

    public RealmList<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<AlbumModel> album) {
        this.album = album;
    }

    public RealmList<MusicModel> getHot() {
        return hot;
    }

    public void setHot(RealmList<MusicModel> hot) {
        this.hot = hot;
    }
}
