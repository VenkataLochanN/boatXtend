package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.UserWeight;

/* JADX INFO: loaded from: classes2.dex */
public class UserWeightEntity extends BaseEntity {
    private UserWeight result;

    public UserWeight getResult() {
        return this.result;
    }

    public void setResult(UserWeight userWeight) {
        this.result = userWeight;
    }
}