package com.realsil.sdk.dfu.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class ImageVersionInfo implements Parcelable {
    public static final Parcelable.Creator<ImageVersionInfo> CREATOR = new Parcelable.Creator<ImageVersionInfo>() { // from class: com.realsil.sdk.dfu.model.ImageVersionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageVersionInfo createFromParcel(Parcel parcel) {
            return new ImageVersionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageVersionInfo[] newArray(int i) {
            return new ImageVersionInfo[i];
        }
    };
    public int Td;
    public int bitNumber;
    public int version;

    public ImageVersionInfo(int i, int i2, int i3) {
        this.bitNumber = i;
        this.Td = i2;
        this.version = i3;
    }

    public ImageVersionInfo(Parcel parcel) {
        this.bitNumber = parcel.readInt();
        this.Td = parcel.readInt();
        this.version = parcel.readInt();
    }

    public static List<ImageVersionInfo> compose(int i, byte[] bArr) {
        ZLogger.v(String.format("imageVersionIndicator=0x%08X", Integer.valueOf(i)));
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = (i >> (i3 * 2)) & 3;
            if (i4 == 0) {
                ImageVersionInfo imageVersionInfo = new ImageVersionInfo(i3, i4, 0);
                ZLogger.v(imageVersionInfo.toString());
                arrayList.add(imageVersionInfo);
            } else {
                int i5 = i2 + 3;
                if (i5 >= bArr.length) {
                    break;
                }
                ImageVersionInfo imageVersionInfo2 = new ImageVersionInfo(i3, i4, ((bArr[i2 + 2] << 16) & 16711680) | ((bArr[i5] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i2 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[i2] & UByte.MAX_VALUE));
                ZLogger.v(imageVersionInfo2.toString());
                arrayList.add(imageVersionInfo2);
                i2 += 4;
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getBitNumber() {
        return this.bitNumber;
    }

    public int getIndication() {
        return this.Td;
    }

    public int getVersion() {
        return this.version;
    }

    public void setBitNumber(int i) {
        this.bitNumber = i;
    }

    public void setIndication(int i) {
        this.Td = i;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String toString() {
        return String.format(Locale.US, "bitNumber=%d\n", Integer.valueOf(this.bitNumber)) + String.format("indication=0x%02X\n", Integer.valueOf(this.Td)) + String.format(Locale.US, "version=0x%08X(%d)\n", Integer.valueOf(this.version), Integer.valueOf(this.version));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.bitNumber);
        parcel.writeInt(this.Td);
        parcel.writeInt(this.version);
    }
}