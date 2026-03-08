package com.realsil.sdk.core;

/* JADX INFO: loaded from: classes3.dex */
public class RtkConfigure {
    public boolean o;
    public boolean p;
    public String q;
    public int r;

    public static class Builder {
        public RtkConfigure mParams = new RtkConfigure();

        public RtkConfigure build() {
            return this.mParams;
        }

        public Builder debugEnabled(boolean z) {
            this.mParams.setDebugEnabled(z);
            return this;
        }

        public Builder globalLogLevel(int i) {
            this.mParams.setGlobalLogLevel(i);
            return this;
        }

        public Builder logTag(String str) {
            this.mParams.setLogTag(str);
            return this;
        }

        public Builder printLog(boolean z) {
            this.mParams.setPrintLog(z);
            return this;
        }
    }

    public RtkConfigure() {
        this.o = true;
        this.p = true;
        this.q = "Realtek";
        this.r = 1;
    }

    public int getGlobalLogLevel() {
        return this.r;
    }

    public String getLogTag() {
        return this.q;
    }

    public boolean isDebugEnabled() {
        return this.o;
    }

    public boolean isPrintLog() {
        return this.p;
    }

    public void setDebugEnabled(boolean z) {
        this.o = z;
    }

    public void setGlobalLogLevel(int i) {
        this.r = i;
    }

    public void setLogTag(String str) {
        this.q = str;
    }

    public void setPrintLog(boolean z) {
        this.p = z;
    }

    public String toString() {
        return String.format("debugEnabled=%b, printLog=%b, logTag=%s, globalLogLevel=0x%02X", Boolean.valueOf(this.o), Boolean.valueOf(this.p), this.q, Integer.valueOf(this.r));
    }
}