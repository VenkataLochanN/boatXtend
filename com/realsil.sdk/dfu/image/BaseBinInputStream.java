package com.realsil.sdk.dfu.image;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.RtkDfu;
import g.e;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import kotlin.UByte;
import kotlin.UShort;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseBinInputStream extends BufferedInputStream {
    public static final int HEADER_SIZE = 12;
    public static final int IMAGE_SIZE_MECHANISM_IMAGE_HEADER = 0;
    public static final int IMAGE_SIZE_MECHANISM_MP_HEADER_DATA_LENGTH = 1;
    public static final int IMAGE_SIZE_MECHANISM_MP_HEADER_IMAGE_SIZE = 2;
    public static int MPHEADER_PARSE_MARK = 1;
    public static final int MP_HEADER_BUF_SIZE = 512;
    public static final int OVA_VERSION_CURRENT = 1;
    public static final int OVA_VERSION_INIT = 0;
    public static final int PACKET_SIZE_DEF = 20;
    public int Af;
    public boolean Dc;
    public int binId;
    public byte icType;
    public final byte[] jf;
    public List<e> kf;
    public int lf;
    public int mf;
    public final byte[] nf;
    public int of;
    public int otaVersion;
    public boolean pf;
    public int qf;
    public int rf;
    public int secureVersion;
    public int sf;
    public int tf;
    public String uf;
    public int vf;
    public byte wf;
    public short xf;
    public int yf;
    public final int zf;
    public static int MPHEADER_PARSE_HEADER = 0;
    public static int MPHEADER_PARSE_FORMAT = MPHEADER_PARSE_HEADER;

    public BaseBinInputStream(InputStream inputStream) {
        this(inputStream, 20);
    }

    public BaseBinInputStream(InputStream inputStream, int i) throws IOException {
        super(new BufferedInputStream(inputStream));
        this.Dc = false;
        this.otaVersion = 0;
        this.sf = 0;
        this.yf = 255;
        this.Dc = RtkDfu.DEBUG_ENABLE;
        this.zf = i;
        this.jf = new byte[512];
        this.nf = new byte[12];
        this.Af = 0;
        parse();
    }

    public static int toUnsigned(short s) {
        return s & UShort.MAX_VALUE;
    }

    public boolean H() {
        List<e> list = this.kf;
        return list != null && list.size() > 0;
    }

    public final void I() {
        int i;
        if (this.Dc) {
            ZLogger.v("mpHeaderBuf=" + DataConverter.bytes2Hex(this.jf));
        }
        this.kf = e.a(this.jf);
        List<e> list = this.kf;
        if (list == null || list.size() <= 0) {
            ZLogger.w("not found mp header");
            return;
        }
        for (e eVar : this.kf) {
            byte[] bArrA = eVar.a();
            if (bArrA != null && bArrA.length > 0) {
                int iB = eVar.b();
                if (iB != 1) {
                    if (iB != 2) {
                        if (iB == 3) {
                            this.uf = e(bArrA);
                        } else if (iB != 4) {
                            switch (iB) {
                                case 17:
                                    this.otaVersion = bArrA[0] & UByte.MAX_VALUE;
                                    continue;
                                case 18:
                                    if (bArrA.length >= 2) {
                                        this.of = ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                                        this.pf = true;
                                    } else {
                                        continue;
                                    }
                                    break;
                                case 19:
                                    if (bArrA.length >= 4) {
                                        this.vf = ((bArrA[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArrA[2] << 16) & 16711680) | ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                                    } else {
                                        continue;
                                    }
                                    break;
                                case 20:
                                    if (bArrA.length >= 4) {
                                        this.mf = ((bArrA[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArrA[2] << 16) & 16711680) | ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                                        if (this.sf < 2) {
                                            this.sf = 2;
                                            i = this.mf;
                                        }
                                    }
                                    break;
                                case 21:
                                    if (bArrA.length >= 2) {
                                        this.secureVersion = ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                                    } else {
                                        continue;
                                    }
                                    break;
                                case 22:
                                    if (bArrA.length >= 4) {
                                        this.qf = ((bArrA[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArrA[2] << 16) & 16711680) | ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                                    } else {
                                        continue;
                                    }
                                    break;
                            }
                            this.rf = i - 12;
                        } else if (bArrA.length >= 4) {
                            this.lf = ((bArrA[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArrA[2] << 16) & 16711680) | ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                            if (this.sf < 1) {
                                this.sf = 1;
                                i = this.lf;
                                this.rf = i - 12;
                            }
                        }
                    } else if (bArrA.length >= 4) {
                        this.tf = ((bArrA[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArrA[2] << 16) & 16711680) | ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                    }
                } else if (bArrA.length >= 2) {
                    this.binId = ((bArrA[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArrA[0] & UByte.MAX_VALUE);
                }
            }
        }
        ZLogger.d(String.format(Locale.US, "MpHeader: binId=0x%04x, binVersion=0x%04x, partNumber=%s, mpDataLength=0x%08x(%d), otaVersion=0x%02x,  mImageSizeMechanism=0x%02x", Integer.valueOf(this.binId), Integer.valueOf(this.tf), this.uf, Integer.valueOf(this.lf), Integer.valueOf(this.lf), Integer.valueOf(this.otaVersion), Integer.valueOf(this.sf)));
        if (this.otaVersion > 0) {
            ZLogger.d(String.format(Locale.US, "MpHeader: imageId=0x%04x, flashAddr=0x%08x, mpImageSize=0x%08x(%d), secureVersion=0x%04x, imageVersion=0x%08x", Integer.valueOf(this.of), Integer.valueOf(this.vf), Integer.valueOf(this.mf), Integer.valueOf(this.mf), Integer.valueOf(this.secureVersion), Integer.valueOf(this.qf)));
        }
    }

    public void J() {
        if (this.Dc) {
            ZLogger.v("headBuf=" + DataConverter.bytes2Hex(this.nf));
        }
    }

    public final String e(byte[] bArr) {
        int length = bArr.length;
        for (int length2 = bArr.length - 1; length2 >= 0; length2--) {
            if (bArr[length2] == -1 || bArr[length2] == 0) {
                length--;
            }
        }
        try {
            return new String(bArr, 0, length, "ascii");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int getBinId() {
        return this.binId;
    }

    public int getBinVersion() {
        return this.tf;
    }

    public int getFlashAddr() {
        return this.vf;
    }

    public byte[] getHeaderBuf() {
        return this.nf;
    }

    public byte getIcType() {
        return this.icType;
    }

    public int getImageId() {
        return this.of;
    }

    public int getImageSize() {
        return this.rf;
    }

    public int getImageVersion() {
        return this.qf;
    }

    public int getOtaVersion() {
        return this.otaVersion;
    }

    public int getSecureVersion() {
        return this.secureVersion;
    }

    public final void parse() throws IOException {
        if (MPHEADER_PARSE_FORMAT == MPHEADER_PARSE_MARK && markSupported()) {
            ZLogger.v(this.Dc, "markSupported");
            mark(0);
            read(this.nf, 0, 12);
            if (this.Dc) {
                ZLogger.d(">> headBuf: " + DataConverter.bytes2Hex(this.nf));
            }
            System.arraycopy(this.nf, 0, this.jf, 0, 12);
            read(this.jf, 12, 500);
            I();
            if (!H()) {
                ZLogger.v("reset ...");
                reset();
            }
            read(this.nf, 0, 12);
        } else {
            read(this.nf, 0, 12);
            byte[] bArr = this.nf;
            if (bArr[0] == 1 && bArr[1] == 0 && bArr[2] == 2) {
                System.arraycopy(bArr, 0, this.jf, 0, 12);
                read(this.jf, 12, 500);
                I();
                read(this.nf, 0, 12);
            }
        }
        J();
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() {
        throw new UnsupportedOperationException("Use readPacket() method instead");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, bArr.length);
    }

    public int read(byte[] bArr, int i) throws IOException {
        int i2 = read(bArr, 0, i);
        if (i2 > 0) {
            this.Af += i2;
        }
        return i2;
    }

    public int readPacket(byte[] bArr) {
        return read(bArr, this.zf);
    }

    public int remainNumInPackets(int i) {
        int iRemainSizeInBytes = remainSizeInBytes();
        return (iRemainSizeInBytes / i) + (iRemainSizeInBytes % i > 0 ? 1 : 0);
    }

    public int remainSizeInBytes() {
        return this.rf - this.Af;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        super.reset();
        this.Af = 0;
    }
}