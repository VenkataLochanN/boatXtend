package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.UserTargetNew;

/* JADX INFO: loaded from: classes2.dex */
public class UserTargetEntity extends BaseEntity {
    private UserTargetNew result;

    public UserTargetNew getResult() {
        return this.result;
    }

    public void setResult(UserTargetNew userTargetNew) {
        this.result = userTargetNew;
    }

    public String toString() {
        return "UserTargetEntity{result=" + this.result + '}';
    }
}