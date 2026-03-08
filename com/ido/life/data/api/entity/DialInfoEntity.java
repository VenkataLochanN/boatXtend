package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.data.api.entity.DialMarket;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class DialInfoEntity extends BaseEntity implements Serializable {
    private DialMarket.DialType.Dial result;

    public DialMarket.DialType.Dial getResult() {
        return this.result;
    }

    public void setResult(DialMarket.DialType.Dial dial) {
        this.result = dial;
    }
}