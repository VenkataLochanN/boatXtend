package com.realsil.sdk.dfu.image;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.ImageVersionInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.utils.DfuUtils;
import g.a;
import g.b;
import g.c;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class BinFactory {
    public static final int ERR_EQUAL_VERSION = 0;
    public static final int ERR_LOW_VERSION = -1;
    public static final int ERR_SUCCESS = 1;
    public static final String FILE_SUFFIX = "BIN";

    /* JADX WARN: Removed duplicated region for block: B:50:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.realsil.sdk.dfu.model.BinInfo a(int r17, java.lang.String r18, int r19, com.realsil.sdk.dfu.model.OtaDeviceInfo r20, boolean r21, boolean r22, boolean r23) throws com.realsil.sdk.dfu.DfuException {
        /*
            Method dump skipped, instruction units count: 674
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.image.BinFactory.a(int, java.lang.String, int, com.realsil.sdk.dfu.model.OtaDeviceInfo, boolean, boolean, boolean):com.realsil.sdk.dfu.model.BinInfo");
    }

    public static boolean a(int i, int i2) throws DfuException {
        if (i2 <= 3) {
            if (i <= 3) {
                return true;
            }
            throw new DfuException("ic conflict", 4102);
        }
        if (i2 == i) {
            return true;
        }
        throw new DfuException("ic conflict", 4102);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int checkPackImageVersion(int r11, com.realsil.sdk.dfu.image.BaseBinInputStream r12, com.realsil.sdk.dfu.model.OtaDeviceInfo r13) {
        /*
            Method dump skipped, instruction units count: 736
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.image.BinFactory.checkPackImageVersion(int, com.realsil.sdk.dfu.image.BaseBinInputStream, com.realsil.sdk.dfu.model.OtaDeviceInfo):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int checkSingleImageVersion(com.realsil.sdk.dfu.image.BaseBinInputStream r16, com.realsil.sdk.dfu.model.OtaDeviceInfo r17) {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.image.BinFactory.checkSingleImageVersion(com.realsil.sdk.dfu.image.BaseBinInputStream, com.realsil.sdk.dfu.model.OtaDeviceInfo):int");
    }

    public static boolean checkVersion(int i, int i2, int i3, int i4, int i5) {
        ZLogger.v(String.format(Locale.US, "checkVersion: version1=%08X(%d) %s, version2=%08X(%d) %s", Integer.valueOf(i), Integer.valueOf(i), DfuUtils.convertVersion2Str(i2, i, i5), Integer.valueOf(i3), Integer.valueOf(i3), DfuUtils.convertVersion2Str(i4, i3, i5)));
        if (i2 <= 0) {
            return i4 <= 0 ? i > i3 : i5 == 1 ? i > ((i3 >> 24) & 255) : i5 == 6 ? i > (((i3 >> 8) & 255) | (i3 & 255)) : i5 == 2 ? i > (i3 & 255) : i5 == 3 ? i > ((i3 >> 27) & 31) : i5 == 5 ? i > ((i3 >> 21) & 2047) : i5 == 4 ? i > i3 : i5 == 7 ? i > i3 : i > i3;
        }
        if (i4 <= 0) {
            return i5 == 1 ? ((i >> 24) & 255) > i3 : i5 == 6 ? ((i & 255) | ((i >> 8) & 255)) > i3 : i5 == 2 ? (i & 255) > i3 : i5 == 3 ? ((i >> 27) & 31) > i3 : i5 == 5 ? ((i >> 21) & 2047) > i3 : i5 == 4 ? i > i3 : i5 == 7 ? i > i3 : i > i3;
        }
        if (i5 == 1) {
            int i6 = i & 255;
            int i7 = i3 & 255;
            if (i6 > i7) {
                return true;
            }
            if (i6 == i7) {
                int i8 = (i >> 8) & 255;
                int i9 = (i3 >> 8) & 255;
                if (i8 > i9) {
                    return true;
                }
                if (i8 == i9) {
                    int i10 = (i >> 16) & 255;
                    int i11 = (i3 >> 16) & 255;
                    if (i10 > i11) {
                        return true;
                    }
                    if (i10 == i11 && ((i >> 24) & 255) > ((i3 >> 24) & 255)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (i5 == 6) {
            int i12 = i & 255;
            int i13 = i3 & 255;
            if (i12 > i13) {
                return true;
            }
            return i12 == i13 && ((i >> 8) & 255) > ((i3 >> 8) & 255);
        }
        if (i5 == 2) {
            int i14 = (i >> 24) & 255;
            int i15 = (i3 >> 24) & 255;
            if (i14 > i15) {
                return true;
            }
            if (i14 == i15) {
                int i16 = (i >> 16) & 255;
                int i17 = (i3 >> 16) & 255;
                if (i16 > i17) {
                    return true;
                }
                if (i16 == i17) {
                    int i18 = (i >> 8) & 255;
                    int i19 = (i3 >> 8) & 255;
                    if (i18 > i19) {
                        return true;
                    }
                    if (i18 == i19 && (i & 255) > (i3 & 255)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (i5 == 3) {
            int i20 = i & 15;
            int i21 = i3 & 15;
            if (i20 > i21) {
                return true;
            }
            if (i20 == i21) {
                int i22 = (i >> 4) & 255;
                int i23 = (i3 >> 4) & 255;
                if (i22 > i23) {
                    return true;
                }
                if (i22 == i23) {
                    int i24 = (i >> 12) & 32767;
                    int i25 = (i3 >> 12) & 32767;
                    if (i24 > i25) {
                        return true;
                    }
                    if (i24 == i25 && ((i >> 27) & 31) > ((i3 >> 27) & 31)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (i5 != 5) {
            return i5 == 4 ? i > i3 : i5 == 7 ? i > i3 : i > i3;
        }
        int i26 = i & 15;
        int i27 = i3 & 15;
        if (i26 > i27) {
            return true;
        }
        if (i26 == i27) {
            int i28 = (i >> 4) & 255;
            int i29 = (i3 >> 4) & 255;
            if (i28 > i29) {
                return true;
            }
            if (i28 == i29) {
                int i30 = (i >> 12) & 511;
                int i31 = (i3 >> 12) & 511;
                if (i30 > i31) {
                    return true;
                }
                if (i30 == i31 && ((i >> 21) & 32767) > ((i3 >> 21) & 32767)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<BaseBinInputStream> loadImageBinFile(String str, int i, OtaDeviceInfo otaDeviceInfo, boolean z) {
        return loadImageFile(new LoadParams.Builder().setPrimaryIcType(3).setFilePath(str).setFileIndicator(i).setOtaDeviceInfo(otaDeviceInfo).setVersionCheckEnabled(z).build());
    }

    public static BinInfo loadImageBinInfo(LoadParams loadParams) {
        if (loadParams == null) {
            return null;
        }
        return a(loadParams.getPrimaryIcType(), loadParams.getFilePath(), loadParams.getFileIndicator(), loadParams.getOtaDeviceInfo(), loadParams.isVersionCheckEnabled(), loadParams.isIcCheckEnabled(), loadParams.v());
    }

    public static BinInfo loadImageBinInfo(String str, OtaDeviceInfo otaDeviceInfo, boolean z) {
        return loadImageBinInfo(new LoadParams.Builder().setPrimaryIcType(3).setFilePath(str).setOtaDeviceInfo(otaDeviceInfo).setVersionCheckEnabled(z).build());
    }

    public static List<BaseBinInputStream> loadImageFile(LoadParams loadParams) {
        BinInfo binInfoLoadImageBinInfo = loadImageBinInfo(loadParams);
        if (binInfoLoadImageBinInfo != null) {
            return binInfoLoadImageBinInfo.supportBinInputStreams;
        }
        return null;
    }

    public static BaseBinInputStream openInputStream(int i, String str) throws DfuException {
        ZLogger.v(String.format(Locale.US, "filePath=%s, icType=%d", str, Integer.valueOf(i)));
        FileInputStream fileInputStream = new FileInputStream(str);
        if (i <= 3) {
            b bVar = new b(fileInputStream);
            if (bVar.icType <= 3) {
                return bVar;
            }
            throw new DfuException("ic conflict", 4102);
        }
        if (i == 4) {
            a aVar = new a(fileInputStream);
            byte b2 = aVar.icType;
            if (b2 == 4 || b2 == 6 || b2 == 7 || b2 == 8) {
                return aVar;
            }
            throw new DfuException("ic conflict", 4102);
        }
        if (i == 6) {
            a aVar2 = new a(fileInputStream);
            byte b3 = aVar2.icType;
            if (b3 == 4 || b3 == 6 || b3 == 7 || b3 == 8) {
                return aVar2;
            }
            throw new DfuException("ic conflict", 4102);
        }
        if (i == 7) {
            a aVar3 = new a(fileInputStream);
            byte b4 = aVar3.icType;
            if (b4 == 4 || b4 == 6 || b4 == 7 || b4 == 8) {
                return aVar3;
            }
            throw new DfuException("ic conflict", 4102);
        }
        if (i == 8) {
            a aVar4 = new a(fileInputStream);
            byte b5 = aVar4.icType;
            if (b5 == 4 || b5 == 6 || b5 == 7 || b5 == 8) {
                return aVar4;
            }
            throw new DfuException("ic conflict", 4102);
        }
        if (i == 5) {
            c cVar = new c(fileInputStream);
            byte b6 = cVar.icType;
            if (b6 == 5 || b6 == 9) {
                return cVar;
            }
            throw new DfuException("ic conflict", 4102);
        }
        if (i != 9) {
            return null;
        }
        c cVar2 = new c(fileInputStream);
        byte b7 = cVar2.icType;
        if (b7 == 5 || b7 == 9) {
            return cVar2;
        }
        throw new DfuException("ic conflict", 4102);
    }

    public static BaseBinInputStream openInputStream(String str, int i, int i2) {
        try {
            ZLogger.v(String.format(Locale.US, "filePath=%s, startAddr=%d, icType=%d", str, Integer.valueOf(i), Integer.valueOf(i2)));
            FileInputStream fileInputStream = new FileInputStream(str);
            fileInputStream.skip(i);
            if (i2 <= 3) {
                return new b(fileInputStream);
            }
            if (i2 != 4 && i2 != 6 && i2 != 7 && i2 != 8) {
                if (i2 == 5 || i2 == 9) {
                    return new c(fileInputStream);
                }
                return null;
            }
            return new a(fileInputStream);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int getImageVersion(OtaDeviceInfo otaDeviceInfo, int i) {
        int i2;
        if (otaDeviceInfo != null && (i2 = otaDeviceInfo.otaVersion) != 0 && i2 == 1) {
            for (ImageVersionInfo imageVersionInfo : otaDeviceInfo.getExistImageVersionInfos()) {
                int i3 = otaDeviceInfo.icType;
                if (i3 <= 3) {
                    if (imageVersionInfo.getBitNumber() == i) {
                        return imageVersionInfo.getVersion();
                    }
                } else if (i3 == 5 || i3 == 9) {
                    if (imageVersionInfo.getBitNumber() == i || imageVersionInfo.getBitNumber() == i + 16) {
                        return imageVersionInfo.getVersion();
                    }
                } else if (i3 == 4 || i3 == 6 || i3 == 7 || i3 == 8) {
                    if (imageVersionInfo.getBitNumber() == i || imageVersionInfo.getBitNumber() == i + 16) {
                        return imageVersionInfo.getVersion();
                    }
                }
            }
        }
        return 0;
    }
}