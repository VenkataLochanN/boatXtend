package com.realsil.sdk.dfu.utils;

import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class ConnectParams {
    public String Cd;
    public String address;
    public boolean ue;
    public int ve = 1;
    public UUID we = UUID.fromString("0000d0ff-3c17-d293-8e48-14fe2e4da212");
    public UUID xe = UUID.fromString("00006287-3c17-d293-8e48-14fe2e4da212");

    public static class Builder {
        public ConnectParams te = new ConnectParams();

        public Builder address(String str) {
            this.te.setAddress(str);
            return this;
        }

        public ConnectParams build() {
            return this.te;
        }

        public Builder dfuServiceUuid(UUID uuid) {
            this.te.setDfuServiceUuid(uuid);
            return this;
        }

        public Builder hid(boolean z) {
            this.te.setHid(z);
            return this;
        }

        public Builder localName(String str) {
            this.te.setLocalName(str);
            return this;
        }

        public Builder otaServiceUuid(UUID uuid) {
            this.te.setOtaServiceUuid(uuid);
            return this;
        }

        public Builder reconnectTimes(int i) {
            this.te.setReconnectTimes(i);
            return this;
        }
    }

    public String getAddress() {
        return this.address;
    }

    public UUID getDfuServiceUuid() {
        return this.xe;
    }

    public String getLocalName() {
        return this.Cd;
    }

    public UUID getOtaServiceUuid() {
        return this.we;
    }

    public int getReconnectTimes() {
        return this.ve;
    }

    public boolean isHid() {
        return this.ue;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setDfuServiceUuid(UUID uuid) {
        this.xe = uuid;
    }

    public void setHid(boolean z) {
        this.ue = z;
    }

    public void setLocalName(String str) {
        this.Cd = str;
    }

    public void setOtaServiceUuid(UUID uuid) {
        this.we = uuid;
    }

    public void setReconnectTimes(int i) {
        this.ve = i;
    }
}