package com.realsil.sdk.dfu.image;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class SubFileInfo {
    public static final int HEADER_SIZE = 12;
    public int binId;
    public int bitNumber;
    public int downloadAddr;
    public String filePath;
    public int icType;
    public int reserved;
    public int size;
    public int startAddr;
    public int version = -1;

    public SubFileInfo(int i, String str, int i2, int i3, int i4, int i5, int i6) {
        this.icType = i;
        this.filePath = str;
        this.bitNumber = i2;
        this.startAddr = i3;
        this.downloadAddr = i4;
        this.size = i5;
        this.reserved = i6;
    }

    public static SubFileInfo builder(int i, String str, int i2, int i3, byte[] bArr) {
        SubFileInfo subFileInfo = new SubFileInfo(i, str, i2, i3, ((bArr[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[2] << 16) & 16711680) | ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[0] & UByte.MAX_VALUE), ((bArr[7] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[6] << 16) & 16711680) | ((bArr[5] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[4] & UByte.MAX_VALUE), 0);
        subFileInfo.parse();
        ZLogger.v(subFileInfo.toString());
        return subFileInfo;
    }

    public BaseBinInputStream getBinInputStream(int i) {
        return BinFactory.openInputStream(this.filePath, this.startAddr, i);
    }

    public final void parse() {
        String string;
        try {
            BaseBinInputStream binInputStream = getBinInputStream(this.icType);
            if (binInputStream != null) {
                this.version = binInputStream.getImageVersion();
                this.binId = binInputStream.getBinId();
                binInputStream.close();
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            string = e2.toString();
            ZLogger.e(string);
        } catch (IOException e3) {
            e3.printStackTrace();
            string = e3.toString();
            ZLogger.e(string);
        } catch (BufferUnderflowException e4) {
            e4.printStackTrace();
            string = e4.toString();
            ZLogger.e(string);
        }
    }

    public String toString() {
        return String.format(Locale.US, "icType=0x%02X, bitNumber=%d, binId=0x%04X, startAddr=%d, downloadAddr=0x%08x, size(include mp header+data)=0x%08x(%d), reserved=%d", Integer.valueOf(this.icType), Integer.valueOf(this.bitNumber), Integer.valueOf(this.binId), Integer.valueOf(this.startAddr), Integer.valueOf(this.downloadAddr), Integer.valueOf(this.size), Integer.valueOf(this.size), Integer.valueOf(this.reserved));
    }
}