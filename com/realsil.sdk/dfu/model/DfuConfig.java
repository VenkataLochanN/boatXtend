package com.realsil.sdk.dfu.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.ido.ble.protocol.model.Sport100Type;

/* JADX INFO: loaded from: classes3.dex */
public class DfuConfig implements Parcelable {
    public static final int BATTERY_LEVEL_FORMAT_PERCENTAGE = 0;
    public static final int BATTERY_LEVEL_FORMAT_VALUE = 1;
    public static final int BUFFER_CHECK_ORIGINAL = 16;
    public static final int BUFFER_CHECK_SLOW_X1 = 1;
    public static final int BUFFER_CHECK_SLOW_X2 = 2;
    public static final int BUFFER_CHECK_SLOW_X3 = 3;
    public static final int BUFFER_CHECK_SLOW_X4 = 4;
    public static final int CHANNEL_TYPE_GATT = 0;
    public static final int CHANNEL_TYPE_SPP = 1;
    public static final int CHANNEL_TYPE_USB = 2;
    public static final long CONNECTION_PARAMETERS_UPDATE_TIMEOUT = 10000;
    public static final Parcelable.Creator<DfuConfig> CREATOR = new Parcelable.Creator<DfuConfig>() { // from class: com.realsil.sdk.dfu.model.DfuConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DfuConfig createFromParcel(Parcel parcel) {
            return new DfuConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DfuConfig[] newArray(int i) {
            return new DfuConfig[i];
        }
    };
    public static final int MAX_POWER_LEVER = 110;
    public static final int MIN_POWER_LEVER = 30;
    public static final int MIN_POWER_LEVER_FOR_HUAWEI = 140;
    public String Ad;
    public String Bd;
    public String Cd;
    public int Dd;
    public int Ed;
    public int _c;
    public int ad;
    public String address;
    public boolean cd;
    public String filePath;
    public int hd;
    public boolean jd;
    public boolean kd;
    public boolean ld;
    public int mc;
    public byte[] md;
    public int nd;
    public boolean od;
    public boolean pd;
    public int qd;
    public boolean rd;
    public int sd;
    public int td;
    public int ud;
    public boolean vd;
    public boolean versionCheckEnabled;
    public boolean wd;
    public int xd;
    public String yd;
    public String zd;

    public DfuConfig() {
        this(0);
    }

    public DfuConfig(int i) {
        this.mc = 0;
        this.hd = 0;
        this._c = 3;
        this.ad = -1;
        this.versionCheckEnabled = false;
        this.cd = true;
        this.jd = true;
        this.kd = false;
        this.ld = true;
        this.nd = 20;
        this.od = false;
        this.pd = false;
        this.qd = 0;
        this.rd = false;
        this.sd = 30;
        this.td = 0;
        this.ud = 16;
        this.vd = false;
        this.wd = false;
        this.xd = 93;
        this.yd = "0000d0ff-3c17-d293-8e48-14fe2e4da212";
        this.zd = "00006287-3c17-d293-8e48-14fe2e4da212";
        this.Ad = "00006387-3c17-d293-8e48-14fe2e4da212";
        this.Bd = "00006487-3c17-d293-8e48-14fe2e4da212";
        this.Dd = Sport100Type.SPORT_TYPE_MOUNTAINEERING_MACHINE;
        this.Ed = 2;
        this.hd = i;
    }

    public DfuConfig(Parcel parcel) {
        this.mc = 0;
        this.hd = 0;
        this._c = 3;
        this.ad = -1;
        this.versionCheckEnabled = false;
        this.cd = true;
        this.jd = true;
        this.kd = false;
        this.ld = true;
        this.nd = 20;
        this.od = false;
        this.pd = false;
        this.qd = 0;
        this.rd = false;
        this.sd = 30;
        this.td = 0;
        this.ud = 16;
        this.vd = false;
        this.wd = false;
        this.xd = 93;
        this.yd = "0000d0ff-3c17-d293-8e48-14fe2e4da212";
        this.zd = "00006287-3c17-d293-8e48-14fe2e4da212";
        this.Ad = "00006387-3c17-d293-8e48-14fe2e4da212";
        this.Bd = "00006487-3c17-d293-8e48-14fe2e4da212";
        this.Dd = Sport100Type.SPORT_TYPE_MOUNTAINEERING_MACHINE;
        this.Ed = 2;
        this.mc = parcel.readInt();
        this.hd = parcel.readInt();
        this._c = parcel.readInt();
        this.address = parcel.readString();
        this.filePath = parcel.readString();
        this.ad = parcel.readInt();
        this.versionCheckEnabled = parcel.readByte() != 0;
        this.cd = parcel.readByte() != 0;
        this.jd = parcel.readByte() != 0;
        this.kd = parcel.readByte() != 0;
        this.ld = parcel.readByte() != 0;
        this.md = parcel.createByteArray();
        this.nd = parcel.readInt();
        this.od = parcel.readByte() != 0;
        this.pd = parcel.readByte() != 0;
        this.qd = parcel.readInt();
        this.rd = parcel.readByte() != 0;
        this.sd = parcel.readInt();
        this.td = parcel.readInt();
        this.ud = parcel.readInt();
        this.vd = parcel.readByte() != 0;
        this.wd = parcel.readByte() != 0;
        this.xd = parcel.readInt();
        this.yd = parcel.readString();
        this.zd = parcel.readString();
        this.Ad = parcel.readString();
        this.Bd = parcel.readString();
        this.Cd = parcel.readString();
        this.Dd = parcel.readInt();
        this.Ed = parcel.readInt();
    }

    public DfuConfig(String str, String str2) {
        this.mc = 0;
        this.hd = 0;
        this._c = 3;
        this.ad = -1;
        this.versionCheckEnabled = false;
        this.cd = true;
        this.jd = true;
        this.kd = false;
        this.ld = true;
        this.nd = 20;
        this.od = false;
        this.pd = false;
        this.qd = 0;
        this.rd = false;
        this.sd = 30;
        this.td = 0;
        this.ud = 16;
        this.vd = false;
        this.wd = false;
        this.xd = 93;
        this.yd = "0000d0ff-3c17-d293-8e48-14fe2e4da212";
        this.zd = "00006287-3c17-d293-8e48-14fe2e4da212";
        this.Ad = "00006387-3c17-d293-8e48-14fe2e4da212";
        this.Bd = "00006487-3c17-d293-8e48-14fe2e4da212";
        this.Dd = Sport100Type.SPORT_TYPE_MOUNTAINEERING_MACHINE;
        this.Ed = 2;
        this.address = str;
        this.filePath = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public int getBatteryLevelFormat() {
        return this.td;
    }

    public int getBufferCheckLevel() {
        return this.ud;
    }

    public int getChannelType() {
        return this.mc;
    }

    public int getControlSpeed() {
        return this.qd;
    }

    public String getDfuControlPointUuid() {
        return this.Bd;
    }

    public String getDfuDataUuid() {
        return this.Ad;
    }

    public String getDfuServiceUuid() {
        return this.zd;
    }

    public int getFileIndicator() {
        return this.ad;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getLocalName() {
        return this.Cd;
    }

    public int getLowBatteryThreshold() {
        return this.sd;
    }

    public int getManufacturerId() {
        return this.xd;
    }

    public int getMaxPacketSize() {
        return this.nd;
    }

    public String getOtaServiceUuid() {
        return this.yd;
    }

    public int getOtaWorkMode() {
        return this.hd;
    }

    public int getPrimaryIcType() {
        return this._c;
    }

    public int getPrimaryMtuSize() {
        return this.nd;
    }

    public byte[] getSecretKey() {
        return this.md;
    }

    public int getUsbEndPointInAddr() {
        return this.Dd;
    }

    public int getUsbEndPointOutAddr() {
        return this.Ed;
    }

    public boolean isAutomaticActiveEnabled() {
        return this.jd;
    }

    public boolean isBatteryCheckEnabled() {
        return this.rd;
    }

    public boolean isBondConnectionEnabled() {
        return this.vd;
    }

    public boolean isBreakpointResumeEnabled() {
        return this.ld;
    }

    public boolean isConParamUpdateLatencyEnabled() {
        return this.wd;
    }

    public boolean isIcCheckEnabled() {
        return this.cd;
    }

    public boolean isMtuUpdateEnabled() {
        return this.od;
    }

    public boolean isSpeedControlEnabled() {
        return this.pd;
    }

    public boolean isThroughputEnabled() {
        return this.kd;
    }

    public boolean isVersionCheckEnabled() {
        return this.versionCheckEnabled;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAutomaticActiveEnabled(boolean z) {
        this.jd = z;
    }

    public void setBatteryCheckEnabled(boolean z) {
        this.rd = z;
    }

    public void setBatteryLevelFormat(int i) {
        this.td = i;
    }

    public void setBondConnectionEnabled(boolean z) {
        this.vd = z;
    }

    public void setBreakpointResumeEnabled(boolean z) {
        this.ld = z;
    }

    public void setBufferCheckLevel(int i) {
        this.ud = i;
    }

    public void setChannelType(int i) {
        this.mc = i;
    }

    public void setConParamUpdateLatencyEnabled(boolean z) {
        this.wd = z;
    }

    @Deprecated
    public void setControlPointUuid(String str) {
        setDfuControlPointUuid(str);
    }

    public void setControlSpeed(int i) {
        this.qd = i;
    }

    @Deprecated
    public void setDataUuid(String str) {
        setDfuDataUuid(str);
    }

    public void setDfuControlPointUuid(String str) {
        this.Bd = str;
    }

    public void setDfuDataUuid(String str) {
        this.Ad = str;
    }

    public void setDfuServiceUuid(String str) {
        this.zd = str;
    }

    public void setFileIndicator(int i) {
        this.ad = i;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setIcCheckEnabled(boolean z) {
        this.cd = z;
    }

    public void setLocalName(String str) {
        this.Cd = str;
    }

    public void setLowBatteryThreshold(int i) {
        this.sd = i;
    }

    public void setManufacturerId(int i) {
        this.xd = i;
    }

    public void setMaxPacketSize(int i) {
        this.nd = i;
    }

    public void setMtuUpdateEnabled(boolean z) {
        this.od = z;
    }

    public void setOtaServiceUuid(String str) {
        this.yd = str;
    }

    public void setOtaWorkMode(int i) {
        this.hd = i;
    }

    public void setPrimaryIcType(int i) {
        this._c = i;
    }

    public void setPrimaryMtuSize(int i) {
        this.nd = i;
    }

    public void setSecretKey(byte[] bArr) {
        this.md = bArr;
    }

    public void setServiceUuid(String str) {
        setDfuServiceUuid(str);
    }

    public void setSpeedControlEnabled(boolean z) {
        this.pd = z;
    }

    public void setThroughputEnabled(boolean z) {
        this.kd = z;
    }

    public void setUsbEndPointInAddr(int i) {
        this.Dd = i;
    }

    public void setUsbEndPointOutAddr(int i) {
        this.Ed = i;
    }

    public void setVersionCheckEnabled(boolean z) {
        this.versionCheckEnabled = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x01db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.model.DfuConfig.toString():java.lang.String");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mc);
        parcel.writeInt(this.hd);
        parcel.writeInt(this._c);
        parcel.writeString(this.address);
        parcel.writeString(this.filePath);
        parcel.writeInt(this.ad);
        parcel.writeByte(this.versionCheckEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.cd ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.jd ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.kd ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.ld ? (byte) 1 : (byte) 0);
        parcel.writeByteArray(this.md);
        parcel.writeInt(this.nd);
        parcel.writeByte(this.od ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.pd ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.qd);
        parcel.writeByte(this.rd ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.sd);
        parcel.writeInt(this.td);
        parcel.writeInt(this.ud);
        parcel.writeByte(this.vd ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.wd ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.xd);
        parcel.writeString(this.yd);
        parcel.writeString(this.zd);
        parcel.writeString(this.Ad);
        parcel.writeString(this.Bd);
        parcel.writeString(this.Cd);
        parcel.writeInt(this.Dd);
        parcel.writeInt(this.Ed);
    }
}