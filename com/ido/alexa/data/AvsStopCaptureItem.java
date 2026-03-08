package com.ido.alexa.data;

/* JADX INFO: loaded from: classes2.dex */
public class AvsStopCaptureItem extends AvsItem {
    private String requestId;

    public String getRequestId() {
        return this.requestId;
    }

    public AvsStopCaptureItem(String str) {
        super(str);
    }

    public AvsStopCaptureItem(String str, String str2) {
        super(str);
        this.requestId = str2;
    }
}