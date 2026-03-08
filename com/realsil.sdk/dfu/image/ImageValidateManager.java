package com.realsil.sdk.dfu.image;

import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ImageValidateManager {
    public static final int ERR_NA = 0;
    public static final int ERR_PACK_DUPLICATE_BANK = 18;
    public static final int ERR_PACK_LOSS = 16;
    public static final int ERR_PACK_LOSS_APP_IMAGE = 21;
    public static final int ERR_PACK_LOSS_EMPTY = 17;
    public static final int ERR_PACK_LOSS_OTA_HEADER = 19;
    public static final int ERR_PACK_LOSS_ROM_PATCH = 20;
    public static final int ERR_PACK_LOW_VERSION = 22;
    public static final int ERR_PACK_NT = 32;
    public static final int ERR_PACK_NT_BANK_SWITCH = 34;
    public static final int ERR_PACK_NT_OTA_HEADER = 33;
    public static final int ERR_SINGLE_NT = 48;

    public static int check(OtaDeviceInfo otaDeviceInfo, BinInfo binInfo) {
        int i;
        if (otaDeviceInfo == null || binInfo == null || (i = otaDeviceInfo.icType) <= 3) {
            return 0;
        }
        if (i != 5 && i != 9) {
            if (i == 4 || i == 6 || i == 7 || i == 8) {
            }
            return 0;
        }
        if (!binInfo.isPackFile) {
            if (otaDeviceInfo.updateBankIndicator != 0) {
                return 48;
            }
            List<BaseBinInputStream> list = binInfo.mBinInputStreams;
            if (list != null && list.size() > 0) {
                Iterator<BaseBinInputStream> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().getBinId() == 2048) {
                        return 33;
                    }
                }
            }
            return 0;
        }
        List<SubFileInfo> list2 = binInfo.subFileInfos;
        if (list2 == null || list2.size() <= 0) {
            return 17;
        }
        boolean z = false;
        boolean z2 = false;
        for (SubFileInfo subFileInfo : list2) {
            if (subFileInfo.binId == 2048) {
                if (subFileInfo.bitNumber < 16) {
                    z2 |= true;
                } else {
                    z |= true;
                }
            }
        }
        int i2 = otaDeviceInfo.updateBankIndicator;
        if (i2 == 2) {
            if (z && true) {
                return 0;
            }
            return z2 & true ? 18 : 34;
        }
        if (i2 != 1) {
            return ((z2 && true) || (z && true)) ? 33 : 0;
        }
        if (z2 && true) {
            return 0;
        }
        return z & true ? 18 : 34;
    }
}