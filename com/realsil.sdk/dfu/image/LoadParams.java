package com.realsil.sdk.dfu.image;

import com.realsil.sdk.dfu.model.OtaDeviceInfo;

/* JADX INFO: loaded from: classes3.dex */
public class LoadParams {
    public int _c;
    public int ad;
    public OtaDeviceInfo bd;
    public boolean cd;
    public boolean dd;
    public boolean ed;
    public String filePath;
    public boolean versionCheckEnabled;

    public static class Builder {
        public String Tc;
        public OtaDeviceInfo Vc;
        public int Sc = 3;
        public int Uc = -1;
        public boolean Wc = false;
        public boolean Xc = true;
        public boolean Yc = true;
        public boolean Zc = false;

        public LoadParams build() {
            return new LoadParams(this.Sc, this.Tc, this.Uc, this.Vc, this.Wc, this.Xc, this.Yc, this.Zc);
        }

        public Builder setBankCheckEnabled(boolean z) {
            this.Yc = z;
            return this;
        }

        public Builder setFileIndicator(int i) {
            this.Uc = i;
            return this;
        }

        public Builder setFilePath(String str) {
            this.Tc = str;
            return this;
        }

        public Builder setIcCheckEnabled(boolean z) {
            this.Xc = z;
            return this;
        }

        public Builder setIgnoreException(boolean z) {
            this.Zc = z;
            return this;
        }

        public Builder setOtaDeviceInfo(OtaDeviceInfo otaDeviceInfo) {
            this.Vc = otaDeviceInfo;
            return this;
        }

        public Builder setPrimaryIcType(int i) {
            this.Sc = i;
            return this;
        }

        public Builder setVersionCheckEnabled(boolean z) {
            this.Wc = z;
            return this;
        }
    }

    public LoadParams(int i, String str, int i2, OtaDeviceInfo otaDeviceInfo, boolean z, boolean z2, boolean z3, boolean z4) {
        this._c = i;
        this.filePath = str;
        this.ad = i2;
        this.bd = otaDeviceInfo;
        this.versionCheckEnabled = z;
        this.cd = z2;
        this.dd = z3;
        this.ed = z4;
    }

    public int getFileIndicator() {
        return this.ad;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public OtaDeviceInfo getOtaDeviceInfo() {
        return this.bd;
    }

    public int getPrimaryIcType() {
        return this._c;
    }

    public boolean isIcCheckEnabled() {
        return this.cd;
    }

    public boolean isVersionCheckEnabled() {
        return this.versionCheckEnabled;
    }

    public boolean v() {
        return this.dd;
    }
}