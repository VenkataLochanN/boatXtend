package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.Feedback;

/* JADX INFO: loaded from: classes2.dex */
public class FeedbackEntity extends BaseEntity {
    private Feedback result;

    public Feedback getResult() {
        return this.result;
    }

    public void setResult(Feedback feedback) {
        this.result = feedback;
    }

    public String toString() {
        return "FeedbackEntity{result=" + this.result + '}';
    }
}