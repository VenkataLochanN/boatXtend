package com.realsil.sdk.dfu;

/* JADX INFO: loaded from: classes3.dex */
public class DfuException extends Exception {
    public static final int CONNECTION_GATT_CONNECT_FAIL = 0;
    public static final int ERROR_AES_SECRET_KEY_INVALID = 4113;
    public static final int ERROR_BATTERY_LEVEL_LOW = 269;
    public static final int ERROR_BLUEDROID_MASK = 1024;
    public static final int ERROR_BUFFER_CHECK_REACH_MAX_RETRY_TIMES = 275;
    public static final int ERROR_CANNOT_FIND_DEVICE = 265;
    public static final int ERROR_CONNECTION_MASK = 2048;
    public static final int ERROR_CONNECTION_TIMEOUT = 260;
    public static final int ERROR_CONNECT_DEVICE_FAILED = 256;
    public static final int ERROR_CONNECT_ERROR = 264;
    public static final int ERROR_DEVICE_ADDRESS_INVALID = 4112;
    public static final int ERROR_DFU_ABORTED = 4128;
    public static final int ERROR_DFU_CONFIG_INVALID = 4114;
    public static final int ERROR_DFU_SPP_OTA_NOT_SUPPORTED = 281;
    public static final int ERROR_DFU_SPP_RWS_NOT_READY = 282;
    public static final int ERROR_ENTER_OTA_MODE_FAILED = 280;
    public static final int ERROR_FILE_IO_EXCEPTION = 257;
    public static final int ERROR_GATT_DISCOVER_SERVICE_FAILED = 258;
    public static final int ERROR_GATT_INVALID_PDU = 1028;
    public static final int ERROR_IMAGE_FILE_LOAD_FAILED = 4097;
    public static final int ERROR_IMAGE_FILE_NOT_EXIST = 4100;
    public static final int ERROR_IMAGE_FILE_PATH_INVALID = 4098;
    public static final int ERROR_IMAGE_IC_TYPE_CONFLICT = 4102;
    public static final int ERROR_IMAGE_INVALID_VERSION = 4101;
    public static final int ERROR_IMAGE_SUFFIX_INVALID = 4099;
    public static final int ERROR_LOCK_WAIT_INTERRUPTED = 259;
    public static final int ERROR_MASK = 256;
    public static final int ERROR_NA = 0;
    public static final int ERROR_NOTIFICATION_NO_RESPONSE = 767;
    public static final int ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS = 263;
    public static final int ERROR_NO_SERVICE_FOUND_OR_LOSS = 262;
    public static final int ERROR_OPCODE_RESPONSE_NOT_SUPPORTED = 766;
    public static final int ERROR_READ_APP_INFO_ERROR = 271;
    public static final int ERROR_READ_DEVICE_INFO_ERROR = 270;
    public static final int ERROR_READ_IMAGE_VERSION_FAILED = 273;
    public static final int ERROR_READ_PATCH_INFO_ERROR = 272;
    public static final int ERROR_READ_REMOTE_MAC_ADDR = 277;
    public static final int ERROR_REMOTE_MASK = 512;
    public static final int ERROR_REQUEST_MTU_NO_CALLBACK = 276;
    public static final int ERROR_SEND_COMMAND_FAIL = 279;
    public static final int ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES = 268;
    public static final int ERROR_SEND_COMMAND_WITH_NO_CALLBACK = 261;
    public static final int ERROR_USER_NOT_ACTIVE_IMAGE_ERROR = 274;
    public static final int ERROR_VERSION_CHECK_LOW = 278;
    public static final int ERROR_WRITE_CHARAC_ERROR = 267;
    public static final int ERROR_WRITE_CHARAC_NOTIFY_ERROR = 266;
    public static final long serialVersionUID = -6901728550661937942L;
    public int errCode;
    public int errType;

    public interface Connection {
        public static final int OTA_GATT_CONNECTION_FAIL = 0;
        public static final int OTA_GATT_DISCOVERY_FAIL = 1;
        public static final int OTA_GET_CHARA_FAIL = 4;
        public static final int OTA_GET_SERVICE_FAIL = 2;
        public static final int OTA_OTA_SERVICE_LOSS = 3;
        public static final int OTA_READ_CHAR_ERROR = 5;
        public static final int OTA_SPP_CONNECTION_FAIL = 6;
    }

    public static class Type {
        public static final int CONNECTION = 0;
        public static final int OTA = 65536;
    }

    public DfuException(String str, int i) {
        this(str, 65536, i);
    }

    public DfuException(String str, int i, int i2) {
        super(str);
        this.errType = i;
        this.errCode = i2;
    }

    public static DfuException ConnectionException(int i) {
        return new DfuException(null, 0, i);
    }

    public int getErrCode() {
        return this.errCode;
    }

    public int getErrType() {
        return this.errType;
    }

    public int getErrorNumber() {
        return this.errCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("[0x%02X : 0x%04X] %s", Integer.valueOf(this.errType), Integer.valueOf(this.errCode), super.getMessage());
    }
}