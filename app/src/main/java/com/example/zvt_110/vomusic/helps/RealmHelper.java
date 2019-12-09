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

    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.commitTransaction();
    }

    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    public boolean validateUser(String phone, String password) {
        boolean result = false;
        RealmQuery<UserModel> queryResult = mRealm.where(UserModel.class);
        queryResult = queryResult.equalTo("phone", phone).equalTo("password", password);
        UserModel userModel = queryResult.findFirst();
        if (userModel != null) {
            result = true;
        }
        return result;

    }

    public UserModel getUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel = query.equalTo("phone", UserHelper.getInstance().getPhone()).findFirst();
        return userModel;
    }

    public void changePassword(String password) {
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }


}
