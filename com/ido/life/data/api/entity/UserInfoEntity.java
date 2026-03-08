package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.UserInfo;

/* JADX INFO: loaded from: classes2.dex */
public class UserInfoEntity extends BaseEntity {
    private UserInfo result;

    public UserInfo getResult() {
        return this.result;
    }

    public void setResult(UserInfo userInfo) {
        this.result = userInfo;
    }
}