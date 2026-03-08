package com.realsil.sdk.dfu.image;

import com.amazon.identity.auth.device.authorization.RegionUtil;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class BinIndicator {
    public static final int BANK_OFFSET = 16;
    public static final ArrayList<BinIndicator> BBPRO;
    public static final ArrayList<BinIndicator> BEE1 = new ArrayList<>();
    public static final ArrayList<BinIndicator> BEE2;
    public static final int INDICATOR_FULL = -1;
    public static final int INDICATOR_MASK = 1;
    public int bitNumber;
    public String description;
    public String flashLayoutName;
    public int subBinId;
    public boolean versionCheckEnabled;
    public int versionFormat;

    public static class BitNumber {

        public interface Bbpro {
            public static final int APP_IMAGE = 5;
            public static final int APP_UI_PARAMETER_FILE = 9;
            public static final int BACKUP_DATA = 15;
            public static final int DSP_APP_IMAGE = 7;
            public static final int DSP_SYSTEM_IMAGE = 6;
            public static final int DSP_UI_PARAMETER_FILE = 8;
            public static final int EXT_IMAGE_0 = 10;
            public static final int EXT_IMAGE_1 = 11;
            public static final int EXT_IMAGE_2 = 12;
            public static final int EXT_IMAGE_3 = 13;
            public static final int FACTORY_IAMGE = 14;
            public static final int OTA_HEADER_FILE = 2;
            public static final int ROM_PATCH_IMAGE = 4;
            public static final int SECURE_BOOT_LOADER_IMAGE = 3;
            public static final int SOCV_CONFIG_FILE = 0;
            public static final int SYSTEM_CONFIG_FILE = 1;
        }

        public interface Bee1 {
            public static final int APP_BANK_0_IMAGE = 1;
            public static final int APP_BANK_1_IMAGE = 2;
            public static final int CONFIG_FILE = 6;
            public static final int PATCH_EXTENSION_IMAGE = 4;
            public static final int PATCH_IMAGE = 0;
            public static final int USER_DATA = 3;
        }

        public interface Bee2 {
            public static final int APP_DATA1_FILE = 6;
            public static final int APP_DATA2_FILE = 7;
            public static final int APP_DATA3_FILE = 8;
            public static final int APP_DATA4_FILE = 9;
            public static final int APP_DATA5_FILE = 10;
            public static final int APP_DATA6_FILE = 11;
            public static final int APP_IMAGE = 5;
            public static final int OTA_HEADER_FILE = 2;
            public static final int ROM_PATCH_IMAGE = 4;
            public static final int SECURE_BOOT_LOADER_IMAGE = 3;
            public static final int SOCV_CONFIG_FILE = 0;
            public static final int SYSTEM_CONFIG_FILE = 1;
        }
    }

    public static class SubBinId {
        public static final int UNDEFINED = -1;

        public interface Bbpro {
            public static final int APP_DATA_PROMPT = 1026;
            public static final int APP_DATA_TONE = 1025;
            public static final int APP_IMAGE = 768;
            public static final int APP_UI_PARAMETER_FILE = 1024;
            public static final int BACKUP_DATA_1 = 2816;
            public static final int BACKUP_DATA_2 = 2817;
            public static final int DSP_APP_IMAGE = 1538;
            public static final int DSP_PATCH = 1537;
            public static final int DSP_SCENARIO2 = 1539;
            public static final int DSP_SYSTEM_IMAGE = 1280;
            public static final int DSP_UI_PARAMETER_FILE = 1040;
            public static final int EXT_IMAGE_0 = 2304;
            public static final int EXT_IMAGE_1 = 2305;
            public static final int EXT_IMAGE_2 = 2306;
            public static final int EXT_IMAGE_3 = 2307;
            public static final int FACTORY_IAMGE = 2560;
            public static final int OTA_HEADER_FILE = 2048;
            public static final int ROM_PATCH_IMAGE = 512;
            public static final int SECURE_BOOT_LOADER_IMAGE = 1792;
            public static final int SOCV_CONFIG_FILE = 257;
            public static final int SYSTEM_CONFIG_FILE = 256;
        }

        public interface Bee1 {
        }

        public interface Bee2 {
            public static final int APP_DATA1_FILE = 2305;
            public static final int APP_DATA2_FILE = 2306;
            public static final int APP_DATA3_FILE = 2307;
            public static final int APP_DATA4_FILE = 2308;
            public static final int APP_DATA5_FILE = 2309;
            public static final int APP_DATA6_FILE = 2310;
            public static final int APP_IMAGE = 768;
            public static final int OTA_HEADER_FILE = 2048;
            public static final int ROM_PATCH_IMAGE = 512;
            public static final int SECURE_BOOT_LOADER_IMAGE = 1792;
            public static final int SOCV_CONFIG_FILE = 257;
            public static final int SYSTEM_CONFIG_FILE = 256;
        }
    }

    public interface VersionFormat {
        public static final int FORMAT_B_8_8_8_8 = 2;
        public static final int FORMAT_L_0_0_8_8 = 6;
        public static final int FORMAT_L_32 = 4;
        public static final int FORMAT_L_4_8_15_5 = 3;
        public static final int FORMAT_L_4_8_9_11 = 5;
        public static final int FORMAT_L_8_8_8_8 = 1;
        public static final int FORMAT_UNIT16 = 7;
    }

    static {
        BEE1.add(new BinIndicator(0, "Patch image (Both MP and OTA)", null, -1, true, 7));
        BEE1.add(new BinIndicator(1, "App bank 0 image (Both MP and OTA)", null, -1, true, 7));
        BEE1.add(new BinIndicator(2, "APP bank 1 image (OTA)", null, -1, true, 7));
        BEE1.add(new BinIndicator(3, "User data (MP)", null, -1, true, 7));
        BEE1.add(new BinIndicator(4, "Patch extension image (Both MP and OTA)", null, -1, true, 7));
        BEE1.add(new BinIndicator(6, "Config file (MP)", null, -1, true, 7));
        BEE2 = new ArrayList<>();
        BEE2.add(new BinIndicator(0, "SOCV Config File", null, 257, true, 3));
        BEE2.add(new BinIndicator(1, "System Config File", null, 256, true, 3));
        BEE2.add(new BinIndicator(2, "OTA Header File", null, 2048, true, 2));
        BEE2.add(new BinIndicator(3, "Secure Boot Loader image", null, 1792, true, 3));
        BEE2.add(new BinIndicator(4, "ROM Patch Image", null, 512, true, 3));
        BEE2.add(new BinIndicator(5, "App Image", null, 768, true, 3));
        BEE2.add(new BinIndicator(6, "APP Data1 File", null, 2305, false, 3));
        BEE2.add(new BinIndicator(7, "APP Data2 File", null, 2306, false, 3));
        BEE2.add(new BinIndicator(8, "APP Data3 File", null, 2307, false, 3));
        BEE2.add(new BinIndicator(9, "APP Data4 File", null, SubBinId.Bee2.APP_DATA4_FILE, false, 3));
        BEE2.add(new BinIndicator(10, "APP Data5 File", null, SubBinId.Bee2.APP_DATA5_FILE, false, 3));
        BEE2.add(new BinIndicator(11, "APP Data6 File", null, SubBinId.Bee2.APP_DATA6_FILE, false, 3));
        BEE2.add(new BinIndicator(18, "OTA Header File", null, 2048, true, 2));
        BEE2.add(new BinIndicator(19, "Secure Boot Loader image", null, 1792, true, 3));
        BEE2.add(new BinIndicator(20, "ROM Patch Image", null, 512, true, 3));
        BEE2.add(new BinIndicator(21, "App Image", null, 768, true, 3));
        BEE2.add(new BinIndicator(22, "APP Data1 File", null, 2305, false, 3));
        BEE2.add(new BinIndicator(23, "APP Data2 File", null, 2306, false, 3));
        BEE2.add(new BinIndicator(24, "APP Data3 File", null, 2307, false, 3));
        BEE2.add(new BinIndicator(25, "APP Data4 File", null, SubBinId.Bee2.APP_DATA4_FILE, false, 3));
        BEE2.add(new BinIndicator(26, "APP Data5 File", null, SubBinId.Bee2.APP_DATA5_FILE, false, 3));
        BEE2.add(new BinIndicator(27, "APP Data6 File", null, SubBinId.Bee2.APP_DATA6_FILE, false, 3));
        BBPRO = new ArrayList<>();
        BBPRO.add(new BinIndicator(0, "SOCV Config File", null, 257, false, 1));
        BBPRO.add(new BinIndicator(1, "System Config File", null, 256, true, 1));
        BBPRO.add(new BinIndicator(2, "OTA Header File", null, 2048, true, 1));
        BBPRO.add(new BinIndicator(3, "Secure Boot Loader image", null, 1792, true, 3));
        BBPRO.add(new BinIndicator(4, "ROM Patch Image", null, 512, true, 3));
        BBPRO.add(new BinIndicator(5, "App Image", null, 768, true, 5));
        BBPRO.add(new BinIndicator(6, "DSP System Image", null, 1280, true, 6));
        BBPRO.add(new BinIndicator(7, "DSP APP Image", null, SubBinId.Bbpro.DSP_APP_IMAGE, true, 6));
        BBPRO.add(new BinIndicator(8, "DSP UI Parameter File", null, SubBinId.Bbpro.DSP_UI_PARAMETER_FILE, true, 6));
        BBPRO.add(new BinIndicator(9, "APP UI Parameter File", null, 1024, true, 2));
        BBPRO.add(new BinIndicator(10, "Ext Image 0", null, SubBinId.Bbpro.EXT_IMAGE_0, false, 1));
        BBPRO.add(new BinIndicator(11, "Ext Image 1", null, 2305, false, 1));
        BBPRO.add(new BinIndicator(12, "Ext Image 3", null, 2306, false, 1));
        BBPRO.add(new BinIndicator(13, "Ext Image 0", null, 2307, false, 1));
        BBPRO.add(new BinIndicator(14, "Factory Image", null, SubBinId.Bbpro.FACTORY_IAMGE, false, 1));
        BBPRO.add(new BinIndicator(15, "Backup Data", null, SubBinId.Bbpro.BACKUP_DATA_1, false, 1));
        BBPRO.add(new BinIndicator(18, "OTA Header File", null, 2048, true, 1));
        BBPRO.add(new BinIndicator(19, "Secure Boot Loader image", null, 1792, true, 3));
        BBPRO.add(new BinIndicator(20, "ROM Patch Image", null, 512, true, 3));
        BBPRO.add(new BinIndicator(21, "App Image", null, 768, true, 5));
        BBPRO.add(new BinIndicator(22, "DSP System Image", null, 1280, true, 6));
        BBPRO.add(new BinIndicator(23, "DSP APP Image", null, SubBinId.Bbpro.DSP_APP_IMAGE, true, 6));
        BBPRO.add(new BinIndicator(24, "DSP UI Parameter File", null, SubBinId.Bbpro.DSP_UI_PARAMETER_FILE, true, 6));
        BBPRO.add(new BinIndicator(25, "APP UI Parameter File", null, 1024, true, 1));
        BBPRO.add(new BinIndicator(26, "Ext Image 0", null, SubBinId.Bbpro.EXT_IMAGE_0, false, 1));
        BBPRO.add(new BinIndicator(27, "Ext Image 1", null, 2305, false, 1));
        BBPRO.add(new BinIndicator(28, "Ext Image 3", null, 2306, false, 1));
        BBPRO.add(new BinIndicator(29, "Ext Image 0", null, 2307, false, 1));
    }

    public BinIndicator(int i, String str, String str2, int i2) {
        this(i, str, str2, i2, true, 1);
    }

    public BinIndicator(int i, String str, String str2, int i2, boolean z) {
        this(i, str, str2, i2, z, 1);
    }

    public BinIndicator(int i, String str, String str2, int i2, boolean z, int i3) {
        this.versionCheckEnabled = true;
        this.versionFormat = 1;
        this.bitNumber = i;
        this.flashLayoutName = str;
        this.description = str2;
        this.subBinId = i2;
        this.versionCheckEnabled = z;
        this.versionFormat = i3;
    }

    public static boolean isIndicatorEnabled(int i, int i2) {
        return i == -1 || ((i >> i2) & 1) != 0;
    }

    public static String parseBitNumber(int i, int i2) {
        if (i <= 3) {
            for (BinIndicator binIndicator : BEE1) {
                if (binIndicator.bitNumber == i2) {
                    return binIndicator.flashLayoutName;
                }
            }
            return RegionUtil.REGION_STRING_NA;
        }
        if (i == 5 || i == 9) {
            for (BinIndicator binIndicator2 : BEE2) {
                if (binIndicator2.bitNumber == i2) {
                    return binIndicator2.flashLayoutName;
                }
            }
            return RegionUtil.REGION_STRING_NA;
        }
        if (i != 4 && i != 6 && i != 7 && i != 8) {
            return RegionUtil.REGION_STRING_NA;
        }
        for (BinIndicator binIndicator3 : BBPRO) {
            if (binIndicator3.bitNumber == i2) {
                return binIndicator3.flashLayoutName;
            }
        }
        return RegionUtil.REGION_STRING_NA;
    }

    public static String parseSubBinId(int i, int i2) {
        if (i <= 3) {
            for (BinIndicator binIndicator : BEE1) {
                if (binIndicator.subBinId == i2) {
                    return binIndicator.flashLayoutName;
                }
            }
            return RegionUtil.REGION_STRING_NA;
        }
        if (i == 5 || i == 9) {
            for (BinIndicator binIndicator2 : BEE2) {
                if (binIndicator2.subBinId == i2) {
                    return binIndicator2.flashLayoutName;
                }
            }
            return RegionUtil.REGION_STRING_NA;
        }
        if (i != 4 && i != 6 && i != 7 && i != 8) {
            return RegionUtil.REGION_STRING_NA;
        }
        for (BinIndicator binIndicator3 : BBPRO) {
            if (binIndicator3.subBinId == i2) {
                return binIndicator3.flashLayoutName;
            }
        }
        return RegionUtil.REGION_STRING_NA;
    }

    public String toString() {
        return String.format(Locale.US, "bitNumber=%d, flashLayoutName=%s, subBinId=0x%04X", Integer.valueOf(this.bitNumber), this.flashLayoutName, Integer.valueOf(this.subBinId)) + String.format(Locale.US, "versionCheckEnabled=%b, versionFormat=%d", Boolean.valueOf(this.versionCheckEnabled), Integer.valueOf(this.versionFormat));
    }
}