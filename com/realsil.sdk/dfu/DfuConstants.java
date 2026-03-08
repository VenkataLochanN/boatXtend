package com.realsil.sdk.dfu;

/* JADX INFO: loaded from: classes3.dex */
public class DfuConstants {
    public static final int AES_ENCRYPTION_SIZE = 16;
    public static final int AES_SECRET_KEY_LENGTH = 32;
    public static final int BANK_INFO_0 = 0;
    public static final int BANK_INFO_1 = 1;
    public static final byte BANK_INFO_NOT_SUPPORTED = 15;
    public static final int COMMAND_RETRANS_INTERVAL = 1000;
    public static final byte DFU_RESPONSE_BUFFER_CHECK_LENGTH_ERROR = 6;
    public static final byte DFU_RESPONSE_CRC_ERROR = 5;
    public static final byte DFU_RESPONSE_DATA_SIZE_EXCEEDS_LIMIT = 4;
    public static final byte DFU_RESPONSE_FLASH_ERASE_ERROR = 8;
    public static final byte DFU_RESPONSE_FLASH_WRITE_ERROR = 7;
    public static final byte DFU_RESPONSE_INVALID_PARAM = 2;
    public static final byte DFU_RESPONSE_NOT_SUPPORTED = -2;
    public static final byte DFU_RESPONSE_OPERATION_FAILED = 3;
    public static final byte DFU_RESPONSE_SUCCESS = 1;
    public static final int DFU_UPLOAD_IMAGE_TIMEOUT = 60000;
    public static final int GATT_HEADER_SIZE = 3;
    public static final int GATT_MAX_RESEND_TIMES = 3;
    public static final int IC_BBLITE = 8;
    public static final int IC_BBLITE_ANC = 6;
    public static final int IC_BBPRO = 4;
    public static final int IC_BBPRO2 = 7;
    public static final int IC_BEE1 = 3;
    public static final int IC_BEE2 = 5;
    public static final int IC_BEE2_DCUT = 9;
    public static final int MANUFACTURER_ID_REALTEK = 93;
    public static final int MAX_BUFFER_CHECK_MTU_SIZE = 256;
    public static final int MAX_BUFFER_CHECK_PACKET_SIZE = 16;
    public static final int MAX_BUFFER_CHECK_RETRANS_TIMES = 3;
    public static final int MAX_CALLBACK_LOCK_WAIT_TIME = 15000;
    public static final int MAX_CONNECTION_LOCK_TIMEOUT = 32000;
    public static final int MAX_CONNECTION_RETRY_TIMES = 3;
    public static final int MAX_CONNECTION_RETRY_TIMES_FOR_FULL_FUNCTION = 2;
    public static final int MAX_NOTIFICATION_LOCK_WAIT_TIME = 10000;
    public static final int OTA_MODE_AUTOMATIC = 255;
    public static final int OTA_MODE_NORMAL_FUNCTION = 0;
    public static final int OTA_MODE_SILENT_EXTEND_FLASH = 17;
    public static final int OTA_MODE_SILENT_FUNCTION = 16;
    public static final int OTA_MODE_SILENT_NO_TEMP = 18;
    public static final int PROCESS_STATE_BUSY_MASK = 512;
    public static final int PROCESS_STATE_IDLE_MASK = 256;
    public static final int PROGRESS_ABORTED = 259;
    public static final int PROGRESS_ABORT_PROCESSING = 525;
    public static final int PROGRESS_ACTIVE_IMAGE_AND_RESET = 524;
    public static final int PROGRESS_CONNECT_OTA_REMOTE = 520;
    public static final int PROGRESS_CONNECT_REMOTE = 516;
    public static final int PROGRESS_DOWNLOAD_FIRMWARE = 526;
    public static final int PROGRESS_HAND_OVER_PROCESSING = 522;
    public static final int PROGRESS_IMAGE_ACTIVE_SUCCESS = 258;
    public static final int PROGRESS_INITIALIZE = 513;
    public static final int PROGRESS_ORIGIN = 257;
    public static final int PROGRESS_PENDING_ACTIVE_IMAGE = 523;
    public static final int PROGRESS_PREPARE_OTA_ENVIRONMENT = 517;
    public static final int PROGRESS_PROCESSING_ERROR = 260;
    public static final int PROGRESS_REMOTE_ENTER_OTA = 518;
    public static final int PROGRESS_SCAN_OTA_REMOTE = 519;
    public static final int PROGRESS_SCAN_REMOTE = 515;
    public static final int PROGRESS_STARTED = 514;
    public static final int PROGRESS_START_DFU_PROCESS = 521;
    public static final long SCAN_PERIOD = 30000;
    public static final int SPEED_LEVEL_AUTOMATIC = 0;
    public static final int SPEED_LEVEL_SLOW = 1;
    public static final int SPEED_LEVEL_SLOW_X2 = 2;
    public static final int SPEED_LEVEL_SLOW_X3 = 3;
    public static final int SPEED_LEVEL_SLOW_X4 = 4;
    public static final int SPEED_LEVEL_SLOW_X5 = 5;
    public static final int SPEED_LEVEL_SLOW_X6 = 6;
    public static final int STATE_CLOSED = 1280;
    public static final int STATE_CONNECTED = 512;
    public static final int STATE_CONNECTED_AND_READY = 514;
    public static final int STATE_CONNECTING = 256;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 1024;
    public static final int STATE_DISCOVER_SERVICE = 513;

    public static String parseAppBankInfo(int i) {
        return i != 0 ? i != 1 ? "Not Supported" : "Bank 1" : "Bank 0";
    }

    public static String parseConnectionState(int i) {
        if (i == 0) {
            return "STATE_DISCONNECTED";
        }
        if (i == 256) {
            return "STATE_CONNECTING";
        }
        if (i == 1024) {
            return "STATE_DISCONNECTING";
        }
        if (i == 1280) {
            return "STATE_CLOSED";
        }
        switch (i) {
            case 512:
                return "STATE_CONNECTED";
            case 513:
                return "STATE_DISCOVER_SERVICE";
            case 514:
                return "STATE_CONNECTED_AND_READY";
            default:
                return "Unknown: " + i;
        }
    }

    public static String parseDfuStatus(int i) {
        switch (i) {
            case 1:
                return "0x01-SUCCESS";
            case 2:
                return "0x02-INVALID_PARAM";
            case 3:
                return "0x03-OPERATION_FAILED";
            case 4:
                return "0x04-DATA_SIZE_EXCEEDS_LIMIT";
            case 5:
                return "0x05-CRC_ERROR";
            case 6:
                return "0x06-BUFFER_CHECK_LENGTH_ERROR";
            case 7:
                return "0x07-FLASH_WRITE_ERROR";
            case 8:
                return "0x08-FLASH_ERASE_ERROR";
            default:
                return String.format("Unknown error (0x%02X)", Integer.valueOf(i));
        }
    }

    public static String parseIcType(int i) {
        if (i <= 3) {
            return "Bee1";
        }
        if (i == 4) {
            return "BBPro";
        }
        if (i == 5) {
            return "Bee2";
        }
        if (i == 6) {
            return "BBLite ANC";
        }
        if (i == 7) {
            return "bbpro2";
        }
        if (i == 8) {
            return "BBLite";
        }
        if (i == 9) {
            return "Bee2 D-cut";
        }
        return "Unknown ic " + i;
    }

    public static String parseOtaMode(int i) {
        if (i == 0) {
            return "NORMAL_FUNCTION";
        }
        switch (i) {
            case 16:
                return "SILENT_FUNCTION";
            case 17:
                return "SILENT_EXTEND_FLASH";
            case 18:
                return "SILENT_NO_TEMP";
            default:
                return "Unknown (" + i + ")";
        }
    }

    public static String parseOtaState(int i) {
        switch (i) {
            case 257:
                return "PROGRESS_ORIGIN";
            case 258:
                return "PROGRESS_IMAGE_ACTIVE_SUCCESS";
            case 259:
                return "PROGRESS_ABORTED";
            case 260:
                return "PROGRESS_PROCESSING_ERROR";
            default:
                switch (i) {
                    case 513:
                        return "PROGRESS_INITIALIZE";
                    case 514:
                        return "PROGRESS_STARTED";
                    case 515:
                        return "PROGRESS_SCAN_REMOTE";
                    case 516:
                        return "PROGRESS_CONNECT_REMOTE";
                    case 517:
                        return "PROGRESS_PREPARE_OTA_ENVIRONMENT";
                    case 518:
                        return "PROGRESS_REMOTE_ENTER_OTA";
                    case 519:
                        return "PROGRESS_SCAN_OTA_REMOTE";
                    case 520:
                        return "PROGRESS_CONNECT_OTA_REMOTE";
                    case PROGRESS_START_DFU_PROCESS /* 521 */:
                        return "PROGRESS_START_DFU_PROCESS";
                    case PROGRESS_HAND_OVER_PROCESSING /* 522 */:
                        return "PROGRESS_HAND_OVER_PROCESSING";
                    case PROGRESS_PENDING_ACTIVE_IMAGE /* 523 */:
                        return "PROGRESS_PENDING_ACTIVE_IMAGE";
                    case PROGRESS_ACTIVE_IMAGE_AND_RESET /* 524 */:
                        return "PROGRESS_ACTIVE_IMAGE_AND_RESET";
                    case PROGRESS_ABORT_PROCESSING /* 525 */:
                        return "PROGRESS_ABORT_PROCESSING";
                    case PROGRESS_DOWNLOAD_FIRMWARE /* 526 */:
                        return "PROGRESS_DOWNLOAD_FIRMWARE";
                    default:
                        return String.format("Unknown: 0x%04X", Integer.valueOf(i));
                }
        }
    }

    public static String parsePatchBankInfo(int i) {
        return i != 0 ? i != 1 ? "Not Supported" : "Bank 1" : "Bank 0";
    }
}