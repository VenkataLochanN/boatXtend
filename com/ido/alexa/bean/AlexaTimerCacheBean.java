package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaTimerCacheBean {
    private long scheduledTimeMillis;
    private String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public long getScheduledTimeMillis() {
        return this.scheduledTimeMillis;
    }

    public void setScheduledTimeMillis(long j) {
        this.scheduledTimeMillis = j;
    }

    public String toString() {
        return "AlexaTimerCacheBean{token='" + this.token + "', scheduledTimeMillis=" + this.scheduledTimeMillis + '}';
    }
}