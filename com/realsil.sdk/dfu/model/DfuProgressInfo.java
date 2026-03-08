package com.realsil.sdk.dfu.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class DfuProgressInfo implements Parcelable {
    public static final Parcelable.Creator<DfuProgressInfo> CREATOR = new Parcelable.Creator<DfuProgressInfo>() { // from class: com.realsil.sdk.dfu.model.DfuProgressInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DfuProgressInfo createFromParcel(Parcel parcel) {
            return new DfuProgressInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DfuProgressInfo[] newArray(int i) {
            return new DfuProgressInfo[i];
        }
    };
    public int Fd;
    public int Gd;
    public int Hd;
    public int Id;
    public int Jd;
    public int Kd;
    public int Ld;
    public int Md;
    public int Nd;
    public int Od;
    public long Pd;
    public long Qd;
    public long Rd;
    public boolean kd;
    public int progress;
    public long startTime;
    public Throughput uc;

    public DfuProgressInfo() {
        this.progress = 0;
        this.Hd = 0;
        this.Id = 0;
        this.Jd = 0;
        this.Kd = 0;
        this.Hd = 0;
        this.Id = 0;
        this.Jd = 0;
        this.Kd = 0;
        this.Gd = 0;
        this.kd = false;
    }

    public DfuProgressInfo(Parcel parcel) {
        this.progress = 0;
        this.Hd = 0;
        this.Id = 0;
        this.Jd = 0;
        this.Kd = 0;
        this.Fd = parcel.readInt();
        this.Gd = parcel.readInt();
        this.progress = parcel.readInt();
        this.Hd = parcel.readInt();
        this.Id = parcel.readInt();
        this.Jd = parcel.readInt();
        this.Kd = parcel.readInt();
        this.Ld = parcel.readInt();
        this.Md = parcel.readInt();
        this.Nd = parcel.readInt();
        this.Od = parcel.readInt();
        this.startTime = parcel.readLong();
        this.Pd = parcel.readLong();
        this.Qd = parcel.readLong();
        this.Rd = parcel.readLong();
        this.uc = (Throughput) parcel.readParcelable(Throughput.class.getClassLoader());
        this.kd = parcel.readByte() != 0;
    }

    public void addBytesSent(int i) {
        setBytesSent(this.Gd + i);
        this.Nd += i;
    }

    public void addImageSizeInBytes(int i) {
        setImageSizeInBytes(this.Fd + i);
    }

    public void calThroughput() {
        long jMax = Math.max(0L, this.Pd - this.startTime);
        float f2 = jMax > 0 ? (this.Gd * 1000.0f) / jMax : 0.0f;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = ((long) this.Gd) - this.Rd;
        long j2 = jCurrentTimeMillis - this.Qd;
        float f3 = j2 > 0 ? (j * 1000.0f) / j2 : 0.0f;
        this.Qd = jCurrentTimeMillis;
        this.Rd = this.Gd;
        Throughput throughput = this.uc;
        if (throughput != null) {
            throughput.deltaTime = jMax;
            throughput.speed = f2;
            throughput.realSpeed = f3;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getActiveImageSize() {
        return this.Od;
    }

    public int getBytesSent() {
        return this.Gd;
    }

    public int getCurImageId() {
        return this.Ld;
    }

    public int getCurImageVersion() {
        return this.Md;
    }

    public int getCurrentFileIndex() {
        return this.Id;
    }

    public int getImageSizeInBytes() {
        return this.Fd;
    }

    public int getLastFileIndex() {
        return this.Jd;
    }

    public int getMaxFileCount() {
        return this.Hd;
    }

    public int getNextFileIndex() {
        return this.Kd;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getRemainSizeInBytes() {
        return this.Fd - this.Gd;
    }

    public Throughput getThroughput() {
        return this.uc;
    }

    public int getTotalBytesSent() {
        return this.Nd;
    }

    public void initialize(int i, int i2, int i3, boolean z) {
        this.Ld = i;
        this.Md = i2;
        this.Fd = i3;
        this.kd = z;
        setBytesSent(0);
        ZLogger.d(toString());
    }

    public boolean isFileSendOver() {
        return this.Gd >= this.Fd;
    }

    public boolean isLastImageFile() {
        return this.Kd >= this.Hd;
    }

    public void sendOver() {
        this.startTime = System.currentTimeMillis();
        ZLogger.v(toString());
        int i = this.Id;
        this.Jd = i;
        this.Kd = i + 1;
    }

    public void setActiveImageSize(int i) {
        this.Od = i;
    }

    public void setBytesSent(int i) {
        this.Gd = i;
        this.progress = (int) ((i * 100.0f) / this.Fd);
        this.Pd = System.currentTimeMillis();
        if (this.kd) {
            calThroughput();
        }
    }

    public void setCurrentFileIndex(int i) {
        this.Id = i;
    }

    public void setImageSizeInBytes(int i) {
        this.Fd = i;
    }

    public void setLastFileIndex(int i) {
        this.Jd = i;
    }

    public void setMaxFileCount(int i) {
        this.Hd = i;
    }

    public void setNextFileIndex(int i) {
        this.Kd = i;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        long j = this.startTime;
        this.Pd = j;
        this.Qd = j;
        this.Rd = 0L;
        this.uc = this.kd ? new Throughput(this.Fd, this.Gd) : null;
        ZLogger.v(toString());
    }

    public String toString() {
        return String.format(Locale.US, "image: %d/%d", Integer.valueOf(this.Id + 1), Integer.valueOf(this.Hd)) + String.format(Locale.US, "\t{imageId=0x%04X, version=0x%04X}", Integer.valueOf(this.Ld), Integer.valueOf(this.Md)) + String.format(Locale.US, "\tprogress: %d%%(%d/%d)", Integer.valueOf(this.progress), Integer.valueOf(this.Gd), Integer.valueOf(this.Fd));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Fd);
        parcel.writeInt(this.Gd);
        parcel.writeInt(this.progress);
        parcel.writeInt(this.Hd);
        parcel.writeInt(this.Id);
        parcel.writeInt(this.Jd);
        parcel.writeInt(this.Kd);
        parcel.writeInt(this.Ld);
        parcel.writeInt(this.Md);
        parcel.writeInt(this.Nd);
        parcel.writeInt(this.Od);
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.Pd);
        parcel.writeLong(this.Qd);
        parcel.writeLong(this.Rd);
        parcel.writeParcelable(this.uc, i);
        parcel.writeByte(this.kd ? (byte) 1 : (byte) 0);
    }
}