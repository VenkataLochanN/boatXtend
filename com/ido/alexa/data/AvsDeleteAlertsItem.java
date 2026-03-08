package com.ido.alexa.data;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsDeleteAlertsItem extends AvsItem {
    List<String> tokens;

    public AvsDeleteAlertsItem(List<String> list) {
        this.tokens = new ArrayList();
        this.tokens = list;
    }

    public List<String> getTokens() {
        return this.tokens;
    }
}