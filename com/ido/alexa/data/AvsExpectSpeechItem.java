package com.ido.alexa.data;

/* JADX INFO: loaded from: classes2.dex */
public class AvsExpectSpeechItem extends AvsItem {
    long timeoutInMiliseconds;

    public AvsExpectSpeechItem() {
        this(null, 2000L);
    }

    public AvsExpectSpeechItem(String str, long j) {
        super(str);
        this.timeoutInMiliseconds = j;
    }

    public long getTimeoutInMiliseconds() {
        return this.timeoutInMiliseconds;
    }
}