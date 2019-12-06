package com.example.zvt_110.vomusic.helps;

import com.example.zvt_110.vomusic.model.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelper {

    private Realm mRealm;

    public RealmHelper() {
        mRealm = Realm.getDefaultInstance();

    }

    public void close(){
        if (mRealm!=null && !mRealm.isClosed()){
            mRealm.close();
        }
    }

    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.commitTransaction();
    }

    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query=mRealm.where(UserModel.class);
        RealmResults<UserModel> results=query.findAll();
        return results;
    }


}
