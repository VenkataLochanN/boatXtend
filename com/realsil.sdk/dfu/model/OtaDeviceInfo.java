package com.realsil.sdk.dfu.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.bluetooth.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.utils.DfuUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class OtaDeviceInfo implements Parcelable {
    public static final int AES_MODE_16_FIRST = 0;
    public static final int AES_MODE_16_N = 1;
    public static final int BANK_INDICATOR_0 = 0;
    public static final int BANK_INDICATOR_1 = 1;
    public static final int BANK_INDICATOR_F = 15;
    public static final Parcelable.Creator<OtaDeviceInfo> CREATOR = new Parcelable.Creator<OtaDeviceInfo>() { // from class: com.realsil.sdk.dfu.model.OtaDeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaDeviceInfo createFromParcel(Parcel parcel) {
            return new OtaDeviceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaDeviceInfo[] newArray(int i) {
            return new OtaDeviceInfo[i];
        }
    };
    public static final int MECHANISM_ALL_IN_ONE = 2;
    public static final int MECHANISM_ALL_IN_ONE_WITH_BUFFER = 3;
    public static final int MECHANISM_DEFAULT = 1;
    public static final int MECHANISM_ONE_BY_ONE = 1;
    public static final int UPDATE_INDICATOR_BANK_0 = 1;
    public static final int UPDATE_INDICATOR_BANK_1 = 2;
    public static final int UPDATE_INDICATOR_STANDALONE = 0;
    public static final int UPDATE_MULTI_AT_A_TIME = 1;
    public static final int UPDATE_ONE_BY_ONE = 0;
    public boolean Ud;
    public int Vd;
    public int Wd;
    public int Xd;
    public int Yd;
    public boolean Zd;
    public int _d;
    public boolean ae;
    public int appData0;
    public int appData1;
    public int appData2;
    public int appData3;
    public int appFreeBank;
    public boolean bankEnabled;
    public boolean be;
    public int ce;
    public boolean de;
    public int ee;
    public boolean fe;
    public byte[] ge;
    public byte[] he;
    public int icType;
    public int ie;
    public int imageVersionIndicator;
    public byte[] imageVersionValues;
    public int je;
    public int ke;
    public int le;
    public int maxBufferchecksize;
    public List<ImageVersionInfo> me;
    public int mode;
    public List<CharacteristicInfo> ne;
    public int oe;
    public int otaTempBufferSize;
    public int otaVersion;
    public int patchFreeBank;
    public int secureVersion;
    public int updateBankIndicator;

    public OtaDeviceInfo() {
        this.icType = 3;
        this.otaVersion = 0;
        this.appFreeBank = 0;
        this.patchFreeBank = 0;
        this.mode = 2;
        this.ce = 1;
        this.ee = 0;
        this.maxBufferchecksize = 256;
        this.otaTempBufferSize = 0;
        this.imageVersionIndicator = 0;
        this.updateBankIndicator = 0;
        this.le = 1;
    }

    public OtaDeviceInfo(int i) {
        this.icType = 3;
        this.otaVersion = 0;
        this.appFreeBank = 0;
        this.patchFreeBank = 0;
        this.mode = 2;
        this.ce = 1;
        this.ee = 0;
        this.maxBufferchecksize = 256;
        this.otaTempBufferSize = 0;
        this.imageVersionIndicator = 0;
        this.updateBankIndicator = 0;
        this.le = 1;
        setMode(i);
        setImageVersionValues(null);
    }

    public OtaDeviceInfo(Parcel parcel) {
        this.icType = 3;
        this.otaVersion = 0;
        this.appFreeBank = 0;
        this.patchFreeBank = 0;
        this.mode = 2;
        this.ce = 1;
        this.ee = 0;
        this.maxBufferchecksize = 256;
        this.otaTempBufferSize = 0;
        this.imageVersionIndicator = 0;
        this.updateBankIndicator = 0;
        this.le = 1;
        this.Ud = parcel.readByte() != 0;
        this.Vd = parcel.readInt();
        this.Wd = parcel.readInt();
        this.Xd = parcel.readInt();
        this.Yd = parcel.readInt();
        this.Zd = parcel.readByte() != 0;
        this._d = parcel.readInt();
        this.icType = parcel.readInt();
        this.otaVersion = parcel.readInt();
        this.secureVersion = parcel.readInt();
        this.appFreeBank = parcel.readInt();
        this.patchFreeBank = parcel.readInt();
        this.mode = parcel.readInt();
        this.ae = parcel.readByte() != 0;
        this.be = parcel.readByte() != 0;
        this.ce = parcel.readInt();
        this.de = parcel.readByte() != 0;
        this.ee = parcel.readInt();
        this.fe = parcel.readByte() != 0;
        this.maxBufferchecksize = parcel.readInt();
        this.otaTempBufferSize = parcel.readInt();
        this.ge = parcel.createByteArray();
        this.he = parcel.createByteArray();
        this.ie = parcel.readInt();
        this.je = parcel.readInt();
        this.ke = parcel.readInt();
        this.appData0 = parcel.readInt();
        this.appData1 = parcel.readInt();
        this.appData2 = parcel.readInt();
        this.appData3 = parcel.readInt();
        this.imageVersionIndicator = parcel.readInt();
        this.updateBankIndicator = parcel.readInt();
        this.le = parcel.readInt();
        this.bankEnabled = parcel.readByte() != 0;
        this.imageVersionValues = parcel.createByteArray();
        this.me = parcel.createTypedArrayList(ImageVersionInfo.CREATOR);
        this.ne = parcel.createTypedArrayList(CharacteristicInfo.CREATOR);
        this.oe = parcel.readInt();
    }

    public void appendDebugCharacteristicInfo(int i, byte[] bArr) {
        if (this.ne == null) {
            this.ne = new ArrayList();
        }
        this.ne.add(new CharacteristicInfo(i, bArr));
    }

    public void appendImageVersionBytes(byte[] bArr) {
        byte[] bArr2 = this.imageVersionValues;
        if (bArr2 == null || bArr2.length <= 0) {
            this.imageVersionValues = bArr;
        } else {
            byte[] bArr3 = new byte[bArr2.length + bArr.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, 0, bArr3, this.imageVersionValues.length, bArr.length);
            this.imageVersionValues = bArr3;
        }
        x();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAesEncryptMode() {
        return this.ce;
    }

    public int getAppVersion() {
        int i = this.otaVersion;
        if (i == 0) {
            return this.je;
        }
        if (i != 1) {
            return 0;
        }
        for (ImageVersionInfo imageVersionInfo : getExistImageVersionInfos()) {
            int i2 = this.icType;
            if (i2 <= 3) {
                if (imageVersionInfo.getBitNumber() == 1 || imageVersionInfo.getBitNumber() == 2) {
                    return imageVersionInfo.getVersion();
                }
            } else if (i2 == 5 || i2 == 9) {
                if (imageVersionInfo.getBitNumber() == 5 || imageVersionInfo.getBitNumber() == 21) {
                    return imageVersionInfo.getVersion();
                }
            } else if (i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8) {
                if (imageVersionInfo.getBitNumber() == 5 || imageVersionInfo.getBitNumber() == 21) {
                    return imageVersionInfo.getVersion();
                }
            }
        }
        return 0;
    }

    public int getBatteryLevel() {
        return this._d;
    }

    public List<CharacteristicInfo> getDebugCharacteristicInfos() {
        return this.ne;
    }

    public byte[] getDeviceMac() {
        return this.ge;
    }

    public List<ImageVersionInfo> getExistImageVersionInfos() {
        ArrayList arrayList = new ArrayList();
        List<ImageVersionInfo> list = this.me;
        if (list != null && list.size() > 0) {
            for (ImageVersionInfo imageVersionInfo : this.me) {
                if (imageVersionInfo.getIndication() != 0) {
                    arrayList.add(imageVersionInfo);
                }
            }
        }
        return arrayList;
    }

    public List<ImageVersionInfo> getImageVersionInfos() {
        return this.me;
    }

    public int getNoTempImageId() {
        return this.oe;
    }

    public int getPatchExtensionVersion() {
        return this.ke;
    }

    public int getPatchVersion() {
        int i = this.otaVersion;
        if (i == 0) {
            return this.ie;
        }
        if (i != 1) {
            if (i == 2) {
                return this.ie;
            }
            return 0;
        }
        for (ImageVersionInfo imageVersionInfo : getExistImageVersionInfos()) {
            int i2 = this.icType;
            if (i2 <= 3) {
                if (imageVersionInfo.getBitNumber() == 0 || imageVersionInfo.getBitNumber() == 16) {
                    return imageVersionInfo.getVersion();
                }
            } else if (i2 == 5 || i2 == 9) {
                if (imageVersionInfo.getBitNumber() == 4 || imageVersionInfo.getBitNumber() == 20) {
                    return imageVersionInfo.getVersion();
                }
            } else if (i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8) {
                if (imageVersionInfo.getBitNumber() == 4 || imageVersionInfo.getBitNumber() == 20) {
                    return imageVersionInfo.getVersion();
                }
            }
        }
        return 0;
    }

    public int getProductId() {
        return this.Xd;
    }

    public int getProductVersion() {
        return this.Yd;
    }

    public byte[] getRwsSecondaryAddr() {
        return this.he;
    }

    public int getUpdateImageFlag() {
        return this.ee;
    }

    public int getUpdateMechanism() {
        return this.le;
    }

    public int getVendorId() {
        return this.Wd;
    }

    public int getVendorIdSource() {
        return this.Vd;
    }

    public boolean isAesEncryptEnabled() {
        return this.be;
    }

    public boolean isBasSupported() {
        return this.Zd;
    }

    public boolean isBufferCheckEnabled() {
        return this.ae;
    }

    public boolean isCopyImageEnabled() {
        return this.de;
    }

    public boolean isDisSupported() {
        return this.Ud;
    }

    public boolean isRwsEnabled() {
        return this.fe;
    }

    public void parse(byte[] bArr) {
        parse(bArr, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parse(byte[] r13, int r14) {
        /*
            Method dump skipped, instruction units count: 387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.model.OtaDeviceInfo.parse(byte[], int):void");
    }

    public void setAppVersion(int i) {
        this.je = i;
    }

    public void setBatteryLevel(int i) {
        this.Zd = true;
        this._d = i;
    }

    public void setDeviceMac(byte[] bArr) {
        this.ge = bArr;
    }

    public void setImageVersionValues(byte[] bArr) {
        this.imageVersionValues = bArr;
        x();
    }

    public void setMode(int i) {
        this.mode = i;
        this.ae = (i & 1) != 0;
        this.be = ((i & 2) >> 1) != 0;
        this.ce = (i & 4) >> 2;
        this.de = ((i & 8) >> 3) != 0;
        this.ee = (i & 16) >> 4;
        this.fe = ((i & 32) >> 5) != 0;
        y();
    }

    public void setNoTempImageId(int i) {
        this.oe = i;
    }

    public void setPatchExtensionVersion(int i) {
        this.ke = i;
    }

    public void setPatchVersion(int i) {
        this.ie = i;
    }

    public void setPnpId(byte[] bArr) {
        if (bArr == null || bArr.length < 7) {
            this.Ud = false;
            this.Vd = 0;
            this.Wd = 0;
            this.Xd = 0;
            this.Yd = 0;
            return;
        }
        this.Ud = true;
        this.Vd = bArr[0];
        this.Wd = (bArr[2] << 8) | bArr[1];
        this.Xd = (bArr[4] << 8) | bArr[3];
        this.Yd = bArr[5] | (bArr[6] << 8);
    }

    public void setProductId(int i) {
        this.Xd = i;
    }

    public void setProductVersion(int i) {
        this.Yd = i;
    }

    public void setRwsSecondaryAddr(byte[] bArr) {
        this.he = bArr;
    }

    public void setUpdateMechanism(int i) {
        this.le = i;
    }

    public void setVendorId(int i) {
        this.Wd = i;
    }

    public void setVendorIdSource(int i) {
        this.Vd = i;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfo:\n");
        boolean z = this.Ud;
        if (z) {
            sb.append("PnP_ID:\n");
            sb.append(String.format(Locale.US, "\tvendorIdSource=0x%04X(%d)\n", Integer.valueOf(this.Vd), Integer.valueOf(this.Wd)));
            sb.append(String.format(Locale.US, "\tvendorId=0x%04X(%d)\n", Integer.valueOf(this.Wd), Integer.valueOf(this.Wd)));
            sb.append(String.format(Locale.US, "\tproductId=0x%04X(%d)\n", Integer.valueOf(this.Xd), Integer.valueOf(this.Xd)));
            str = String.format(Locale.US, "\tproductVersion=0x%04X(%d)\n", Integer.valueOf(this.Yd), Integer.valueOf(this.Yd));
        } else {
            str = String.format("DIS=%b\n", Boolean.valueOf(z));
        }
        sb.append(str);
        boolean z2 = this.Zd;
        sb.append(z2 ? String.format(Locale.US, "batteryLevel=0x%02X(%d)\n", Integer.valueOf(this._d), Integer.valueOf(this._d)) : String.format("BAS=%b\n", Boolean.valueOf(z2)));
        sb.append(String.format("icType=0x%02X\n", Integer.valueOf(this.icType)));
        sb.append(String.format("otaVersion=0x%02X\n", Integer.valueOf(this.otaVersion)));
        sb.append(String.format("deviceMac: %s\n", BluetoothHelper.formatAddressPositive(this.ge)));
        sb.append(String.format("mode=0x%02X\n", Integer.valueOf(this.mode)));
        sb.append(String.format("\tbufferCheckEnabled=%b\n", Boolean.valueOf(this.ae)));
        sb.append(String.format("\taesEncryptEnabled=%b\n", Boolean.valueOf(this.be)));
        sb.append(String.format("\taesEncryptMode=0x%02X\n", Integer.valueOf(this.ce)));
        sb.append(String.format("\tcopyImageEnabled=%b\n", Boolean.valueOf(this.de)));
        sb.append(String.format("\tupdateImageFlag=0x%02X\n", Integer.valueOf(this.ee)));
        sb.append(String.format("\trwsEnabled=%b\n", Boolean.valueOf(this.fe)));
        if (this.fe) {
            sb.append(String.format("rwsSecondaryAddr: %s\n", BluetoothHelper.formatAddressPositive(this.he)));
        }
        sb.append(String.format(Locale.US, "maxBufferchecksize=0x%04X(%d)\n", Integer.valueOf(this.maxBufferchecksize), Integer.valueOf(this.maxBufferchecksize)));
        sb.append(String.format(Locale.US, "otaTempBufferSize=0x%02X(%d)\n", Integer.valueOf(this.otaTempBufferSize), Integer.valueOf(this.otaTempBufferSize)));
        sb.append(String.format("mUpdateMechanism=0x%02X\n", Integer.valueOf(this.le)));
        sb.append(String.format("otaVersion=0x%02X\n", Integer.valueOf(this.otaVersion)));
        int i = this.otaVersion;
        if (i == 0) {
            sb.append(String.format(Locale.US, "\tpatchVersion=0x%04X\n", Integer.valueOf(this.ie)));
            sb.append(String.format(Locale.US, "\tpatchFreeBank=0x%02X\n", Integer.valueOf(this.patchFreeBank)));
            sb.append(String.format(Locale.US, "\tappVersion=0x%04X\n", Integer.valueOf(this.je)));
            sb.append(String.format("\tappFreeBank=0x%02X\n", Integer.valueOf(this.appFreeBank)));
            sb.append(String.format(Locale.US, "\tpatchExtensionVersion=%d\n", Integer.valueOf(this.ke)));
            if (this.icType > 3) {
                sb.append(String.format(Locale.US, "\tappData0=%d\n", Integer.valueOf(this.appData0)));
                sb.append(String.format(Locale.US, "\tappData1=%d\n", Integer.valueOf(this.appData1)));
                sb.append(String.format(Locale.US, "\tappData2=%d\n", Integer.valueOf(this.appData2)));
                str2 = String.format(Locale.US, "\tappData3=%d\n", Integer.valueOf(this.appData3));
                sb.append(str2);
            }
        } else {
            if (i == 1) {
                sb.append(String.format(Locale.US, "\tsecureVersion=0x%04X(%d)\n", Integer.valueOf(this.secureVersion), Integer.valueOf(this.secureVersion)));
                sb.append(String.format(Locale.US, "\timageVersionIndicator=0x%08X\n", Integer.valueOf(this.imageVersionIndicator)));
                str2 = String.format(Locale.US, "\tupdateBankIndicator=0x%02X(%d)\n", Integer.valueOf(this.updateBankIndicator), Integer.valueOf(this.updateBankIndicator));
            } else if (i == 2) {
                sb.append(String.format(Locale.US, "\tnoTempImageId=0x%04X\n", Integer.valueOf(this.oe)));
                str2 = String.format(Locale.US, "\tpatchVersion=0x%04X\n", Integer.valueOf(this.ie));
            }
            sb.append(str2);
        }
        sb.append(String.format("bankEnabled=%b\n", Boolean.valueOf(this.bankEnabled)));
        List<CharacteristicInfo> list = this.ne;
        if (list != null && list.size() > 0) {
            for (CharacteristicInfo characteristicInfo : this.ne) {
                sb.append(String.format(Locale.US, "\t0x%04X: (%s)\n", Integer.valueOf(characteristicInfo.key), DfuUtils.formatLinkKey(characteristicInfo.value)));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.Ud ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.Vd);
        parcel.writeInt(this.Wd);
        parcel.writeInt(this.Xd);
        parcel.writeInt(this.Yd);
        parcel.writeByte(this.Zd ? (byte) 1 : (byte) 0);
        parcel.writeInt(this._d);
        parcel.writeInt(this.icType);
        parcel.writeInt(this.otaVersion);
        parcel.writeInt(this.secureVersion);
        parcel.writeInt(this.appFreeBank);
        parcel.writeInt(this.patchFreeBank);
        parcel.writeInt(this.mode);
        parcel.writeByte(this.ae ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.be ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.ce);
        parcel.writeByte(this.de ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.ee);
        parcel.writeByte(this.fe ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.maxBufferchecksize);
        parcel.writeInt(this.otaTempBufferSize);
        parcel.writeByteArray(this.ge);
        parcel.writeByteArray(this.he);
        parcel.writeInt(this.ie);
        parcel.writeInt(this.je);
        parcel.writeInt(this.ke);
        parcel.writeInt(this.appData0);
        parcel.writeInt(this.appData1);
        parcel.writeInt(this.appData2);
        parcel.writeInt(this.appData3);
        parcel.writeInt(this.imageVersionIndicator);
        parcel.writeInt(this.updateBankIndicator);
        parcel.writeInt(this.le);
        parcel.writeByte(this.bankEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByteArray(this.imageVersionValues);
        parcel.writeTypedList(this.me);
        parcel.writeTypedList(this.ne);
        parcel.writeInt(this.oe);
    }

    public final void x() {
        ImageVersionInfo imageVersionInfo;
        int i;
        ZLogger.v(String.format("imageVersionIndicator = 0x%08x, imageVersionValues = %s", Integer.valueOf(this.imageVersionIndicator), DataConverter.bytes2Hex(this.imageVersionValues)));
        this.updateBankIndicator = 0;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = (this.imageVersionIndicator >> (i3 * 2)) & 3;
            if (i4 == 0) {
                imageVersionInfo = new ImageVersionInfo(i3, i4, 0);
            } else {
                if (i4 == 1) {
                    if (this.updateBankIndicator == 0) {
                        this.updateBankIndicator = 2;
                    }
                } else if (i4 == 2) {
                    this.updateBankIndicator = 1;
                }
                byte[] bArr = this.imageVersionValues;
                if (bArr == null || (i = i2 + 3) >= bArr.length) {
                    ZLogger.d(false, "imageVersionInfos loss, offset=" + i2);
                    imageVersionInfo = new ImageVersionInfo(i3, i4, 0);
                } else {
                    ImageVersionInfo imageVersionInfo2 = new ImageVersionInfo(i3, i4, ((bArr[i2 + 2] << 16) & 16711680) | ((bArr[i] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i2 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[i2] & UByte.MAX_VALUE));
                    ZLogger.v(false, imageVersionInfo2.toString());
                    arrayList.add(imageVersionInfo2);
                    i2 += 4;
                }
            }
            ZLogger.v(false, imageVersionInfo.toString());
            arrayList.add(imageVersionInfo);
        }
        this.me = arrayList;
        this.bankEnabled = this.updateBankIndicator != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        if (r3.otaTempBufferSize != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x001f, code lost:
    
        if (r3.otaTempBufferSize != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0021, code lost:
    
        r3.le = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0025, code lost:
    
        r3.le = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0008, code lost:
    
        if (r3.ee == 1) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y() {
        /*
            r3 = this;
            int r0 = r3.otaVersion
            r1 = 2
            r2 = 1
            if (r0 != 0) goto Lb
            int r0 = r3.ee
            if (r0 != r2) goto L28
            goto L25
        Lb:
            if (r0 != r2) goto L16
            int r0 = r3.ee
            if (r0 != r2) goto L28
            int r0 = r3.otaTempBufferSize
            if (r0 == 0) goto L25
            goto L21
        L16:
            if (r0 != r1) goto L19
            goto L28
        L19:
            int r0 = r3.ee
            if (r0 != r2) goto L28
            int r0 = r3.otaTempBufferSize
            if (r0 == 0) goto L25
        L21:
            r0 = 3
            r3.le = r0
            goto L2a
        L25:
            r3.le = r1
            goto L2a
        L28:
            r3.le = r2
        L2a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.model.OtaDeviceInfo.y():void");
    }
}