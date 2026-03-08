package com.realsil.sdk.dfu.core.gatt;

import com.realsil.sdk.core.bluetooth.GlobalGatt;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public interface GattDfuProfile {
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString(GlobalGatt.CLIENT_CHARACTERISTIC_CONFIG);

    public static class Bas {
        public static final UUID BATTERY_SERVICE = UUID.fromString("0000180F-0000-1000-8000-00805f9b34fb");
        public static final UUID BAS_READ_CHARACTERITIC = UUID.fromString("00002A19-0000-1000-8000-00805f9b34fb");
    }

    public static class Dfu {
        public static final int CONNECTION_PARAMETER_CONN_INTERVAL_MAX = 17;
        public static final int CONNECTION_PARAMETER_CONN_INTERVAL_MIN = 6;
        public static final int CONNECTION_PARAMETER_CONN_LATENCY = 0;
        public static final int CONNECTION_PARAMETER_SUPPERVISION_TIMEOUT = 500;
        public static final byte OPCODE_DFU_ACTIVE_IMAGE_RESET = 4;
        public static final byte OPCODE_DFU_CONNECTION_PARAMETER_UPDATE = 7;
        public static final byte OPCODE_DFU_COPY_IMAGE = 12;
        public static final byte OPCODE_DFU_DEVICE_INFO = 13;
        public static final byte OPCODE_DFU_ENABLE_BUFFER_CHECK_MODE = 9;
        public static final byte OPCODE_DFU_IN_BUSY = 8;
        public static final byte OPCODE_DFU_RECEIVE_FW_IMAGE = 2;
        public static final byte OPCODE_DFU_REPORT_BUFFER_CRC = 10;
        public static final byte OPCODE_DFU_REPORT_IC_TYPE = 11;
        public static final byte OPCODE_DFU_REPORT_TARGET_IMAGE_INFO = 6;
        public static final byte OPCODE_DFU_RESET_SYSTEM = 5;
        public static final byte OPCODE_DFU_START_DFU = 1;
        public static final byte OPCODE_DFU_VALIDATE_FW_IMAGE = 3;
        public static final int OTA_FUNCTION_BUFFER_CHECK = 1;
        public static final int OTA_FUNCTION_NORMAL = 0;
        public static final int PACKET_SIZE_START_DFU = 17;
        public static final byte RESPONSE_OPCODE_NOTIFICATION = 16;
        public static final UUID DFU_SERVICE = UUID.fromString("00006287-3c17-d293-8e48-14fe2e4da212");
        public static final UUID DFU_DATA_CHARACTERISTIC = UUID.fromString("00006387-3c17-d293-8e48-14fe2e4da212");
        public static final UUID DFU_CONTROL_POINT_CHARACTERISTIC = UUID.fromString("00006487-3c17-d293-8e48-14fe2e4da212");
        public static final UUID DFU_EXTEND_FLASH_CHARACTERISTIC = UUID.fromString("00006587-3c17-d293-8e48-14fe2e4da212");

        public static final class Bee1 {
            public static final byte OPCODE_DFU_CHECK_CURRENT_BUFFER = 11;
            public static final byte OPCODE_DFU_ENSURE_CURRENT_BUFFER = 12;
            public static final byte OPCODE_DFU_REPORT_CURRENT_BUFFER_SIZE = 10;
            public static final byte OPCODE_DFU_REPORT_OTA_FUNCTION_VERSION = 9;
        }
    }

    public static class Dis {
        public static final UUID DEVICE_INFORMATION_SERVICE = UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb");
        public static final UUID DIS_MANUFACTURER_NAME_CHARACTERISTIC = UUID.fromString("00002a29-0000-1000-8000-00805f9b34fb");
        public static final UUID DIS_HARDWARE_REVISION_CHARACTERISTIC = UUID.fromString("00002a29-0000-1000-8000-00805f9b34fb");
        public static final UUID DIS_SYSTEM_ID_CHARACTERISTIC = UUID.fromString("00002a23-0000-1000-8000-00805f9b34fb");
        public static final UUID DIS_PNP_ID_CHARACTERISTIC = UUID.fromString("00002a50-0000-1000-8000-00805f9b34fb");
    }

    public static class Ota {
        public static final byte OPCODE_ENTER_OTA_MODE_KEY = 1;
        public static final int OTA_DEBUG_CHARACTERISTIC_UUID_MAX = 65487;
        public static final int OTA_DEBUG_CHARACTERISTIC_UUID_MIN = 65472;
        public static final int OTA_IMAGE_VERSION_CHARACTERISTIC_UUID_MAX = 65519;
        public static final int OTA_IMAGE_VERSION_CHARACTERISTIC_UUID_MIN = 65504;
        public static final UUID OTA_SERVICE_V0 = UUID.fromString("0000ffd0-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_SERVICE = UUID.fromString("0000d0ff-3c17-d293-8e48-14fe2e4da212");
        public static final UUID OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC = UUID.fromString("0000ffd1-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_DEVICE_MAC_CHARACTERISTIC_UUID = UUID.fromString("0000ffd2-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_PATCH_VERSION_CHARACTERISTIC_UUID = UUID.fromString("0000ffd3-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_APP_VERSION_CHARACTERISTIC_UUID = UUID.fromString("0000ffd4-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_PATCH_EXTENSION_VERSION_CHARACTERISTIC_UUID = UUID.fromString("0000ffd5-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_TEST_MODE_CHARACTERISTIC_UUID = UUID.fromString("0000ffd8-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_DEVICE_INFO_CHARACTERISTIC_UUID = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
        public static final UUID OTA_IMAGE_COUNTER_CHARACTERISTIC_UUID = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
        public static final byte[] OPCODE_ENTER_OTA_MODE = {1};
    }
}