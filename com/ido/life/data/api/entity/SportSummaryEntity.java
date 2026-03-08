package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.SportSummary;

/* JADX INFO: loaded from: classes2.dex */
public class SportSummaryEntity extends BaseEntity {
    private SportSummary result;

    public SportSummary getResult() {
        return this.result;
    }

    public void setResult(SportSummary sportSummary) {
        this.result = sportSummary;
    }

    public String toString() {
        return "SportSummaryEntity{result=" + this.result + '}';
    }
}