package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.ThirdLogin;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdLoginEntity extends BaseEntity {
    public List<ThirdLogin> result;

    public List<ThirdLogin> getResult() {
        return this.result;
    }

    public void setResult(List<ThirdLogin> list) {
        this.result = list;
    }
}