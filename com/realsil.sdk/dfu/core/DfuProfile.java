package com.realsil.sdk.dfu.core;

/* JADX INFO: loaded from: classes3.dex */
public class DfuProfile {
    public static final byte BUSY_MODE_BUSY = 1;
    public static final byte BUSY_MODE_IDLE = 0;
    public static final byte ENSURE_BUFFER_ACTIVE = 0;
    public static final byte ENSURE_BUFFER_INACTIVE = 1;
    public static final int IMAGE_INDICATOR_EXIST_BANK_0 = 1;
    public static final int IMAGE_INDICATOR_EXIST_BANK_1 = 2;
    public static final int IMAGE_INDICATOR_MASK = 3;
    public static final int IMAGE_INDICATOR_NOT_EXIST = 0;
    public static final int IMAGE_INDICATOR_STANDALONE = 3;
    public static final String NO_TEMP_MANUFACTURER_ADDR = "00:E0:12:34:77:88";
    public static final byte RESET_MODE_NORMAL = 16;
    public static final byte RESET_MODE_OTA = 1;
    public static final String TARGET_DEVICE_NAME = "BeeTgt";
    public static final String TARGET_DEVICE_NAME_NO_TEMP = "BeeTgt02";
    public static final int UNIT_4K = 4096;

    public static class OTA_VERSION_CODES {
        public static final int BASE = 0;
        public static final int CUPCAKE = 1;
        public static final int NO_TEMP = 2;
    }
}