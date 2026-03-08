package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.SportHealth;

/* JADX INFO: loaded from: classes2.dex */
public class SportHealthDetailEntity extends BaseEntity {
    private SportHealth result;

    public SportHealthDetailEntity(SportHealth sportHealth) {
        this.result = sportHealth;
    }

    public SportHealth getResult() {
        return this.result;
    }

    public void setResult(SportHealth sportHealth) {
        this.result = sportHealth;
    }

    public String toString() {
        return "SportHealthDetailEntity{result=" + this.result + '}';
    }
}