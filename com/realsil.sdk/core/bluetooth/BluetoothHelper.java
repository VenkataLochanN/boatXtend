package com.realsil.sdk.core.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.provider.Settings;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothHelper {
    public static final int BD_ADDR_LEN = 6;
    public static final int BD_UUID_LEN = 16;

    public static byte[] convertAddress(String str) {
        byte[] bArr = new byte[6];
        if (str != null) {
            bArr[0] = (byte) ((Character.digit(str.charAt(15), 16) * 16) + Character.digit(str.charAt(16), 16));
            bArr[1] = (byte) ((Character.digit(str.charAt(12), 16) * 16) + Character.digit(str.charAt(13), 16));
            bArr[2] = (byte) ((Character.digit(str.charAt(9), 16) * 16) + Character.digit(str.charAt(10), 16));
            bArr[3] = (byte) ((Character.digit(str.charAt(6), 16) * 16) + Character.digit(str.charAt(7), 16));
            bArr[4] = (byte) ((Character.digit(str.charAt(3), 16) * 16) + Character.digit(str.charAt(4), 16));
            bArr[5] = (byte) ((Character.digit(str.charAt(0), 16) * 16) + Character.digit(str.charAt(1), 16));
        } else {
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            bArr[3] = 0;
            bArr[4] = 0;
            bArr[5] = 0;
        }
        return bArr;
    }

    public static String convertMac(byte[] bArr) {
        StringBuilder sb;
        byte b2;
        StringBuilder sb2;
        byte b3;
        StringBuilder sb3;
        byte b4;
        StringBuilder sb4;
        byte b5;
        StringBuilder sb5;
        byte b6;
        if (bArr == null || bArr.length < 5) {
            return null;
        }
        if ((bArr[5] & UByte.MAX_VALUE) <= 15) {
            sb = new StringBuilder();
            sb.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            b2 = bArr[5];
        } else {
            sb = new StringBuilder();
            b2 = bArr[5];
        }
        sb.append(Integer.toHexString(b2 & UByte.MAX_VALUE).toUpperCase());
        sb.append(":");
        String string = sb.toString();
        if ((bArr[4] & UByte.MAX_VALUE) <= 15) {
            sb2 = new StringBuilder();
            sb2.append(string);
            sb2.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            b3 = bArr[4];
        } else {
            sb2 = new StringBuilder();
            sb2.append(string);
            b3 = bArr[4];
        }
        sb2.append(Integer.toHexString(b3 & UByte.MAX_VALUE).toUpperCase());
        sb2.append(":");
        String string2 = sb2.toString();
        if ((bArr[3] & UByte.MAX_VALUE) <= 15) {
            sb3 = new StringBuilder();
            sb3.append(string2);
            sb3.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            b4 = bArr[3];
        } else {
            sb3 = new StringBuilder();
            sb3.append(string2);
            b4 = bArr[3];
        }
        sb3.append(Integer.toHexString(b4 & UByte.MAX_VALUE).toUpperCase());
        sb3.append(":");
        String string3 = sb3.toString();
        if ((bArr[2] & UByte.MAX_VALUE) <= 15) {
            sb4 = new StringBuilder();
            sb4.append(string3);
            sb4.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            b5 = bArr[2];
        } else {
            sb4 = new StringBuilder();
            sb4.append(string3);
            b5 = bArr[2];
        }
        sb4.append(Integer.toHexString(b5 & UByte.MAX_VALUE).toUpperCase());
        sb4.append(":");
        String string4 = sb4.toString();
        if ((bArr[1] & UByte.MAX_VALUE) <= 15) {
            sb5 = new StringBuilder();
            sb5.append(string4);
            sb5.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            b6 = bArr[1];
        } else {
            sb5 = new StringBuilder();
            sb5.append(string4);
            b6 = bArr[1];
        }
        sb5.append(Integer.toHexString(b6 & UByte.MAX_VALUE).toUpperCase());
        sb5.append(":");
        String string5 = sb5.toString();
        if ((bArr[0] & UByte.MAX_VALUE) > 15) {
            return string5 + Integer.toHexString(bArr[0] & UByte.MAX_VALUE).toUpperCase();
        }
        return string5 + AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + Integer.toHexString(bArr[0] & UByte.MAX_VALUE).toUpperCase();
    }

    public static void enableBle() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.enable();
        }
    }

    public static String formatAddressNegatitive(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            return null;
        }
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[0]));
    }

    public static String formatAddressPositive(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            return null;
        }
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5]));
    }

    public static String getBluetoothAddress(Context context, BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null) {
            Class<?> cls = bluetoothAdapter.getClass();
            try {
                Class<?> cls2 = Class.forName("android.bluetooth.IBluetooth");
                Field declaredField = cls.getDeclaredField("mService");
                declaredField.setAccessible(true);
                Method method = cls2.getMethod("getAddress", new Class[0]);
                method.setAccessible(true);
                return (String) method.invoke(declaredField.get(bluetoothAdapter), new Object[0]);
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        } else if (context != null) {
            return Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
        }
        return null;
    }

    public static String getCharPermission(int i) {
        return BluetoothGattImpl.getCharPermission(i);
    }

    public static String getCharPropertie(int i) {
        return BluetoothGattImpl.getCharPropertie(i);
    }

    public static List<BluetoothDevice> getConnectedBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", null);
            declaredMethod.setAccessible(true);
            if (((Integer) declaredMethod.invoke(defaultAdapter, null)).intValue() == 2) {
                for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                    if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("connected:");
                        sb.append(bluetoothDevice.getName());
                        sb.append(", addr=");
                        sb.append(bluetoothDevice.getAddress());
                        ZLogger.v(sb.toString());
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static List<BluetoothDevice> getConnectedBluetoothDevices(int i) {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            if (defaultAdapter.getProfileConnectionState(i) == 2) {
                for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                    if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("connected:");
                        sb.append(bluetoothDevice.getName());
                        sb.append(", addr=");
                        sb.append(bluetoothDevice.getAddress());
                        ZLogger.v(sb.toString());
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static String getDescPermission(int i) {
        return BluetoothGattImpl.getDescPermission(i);
    }

    public static boolean isBleEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static boolean isBleSupported(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static String parseDeviceType(int i) {
        return BluetoothDeviceImpl.parseDeviceType(i);
    }

    public static String parseProfile(int i) {
        if (i == 16) {
            return "HEADSET_CLIENT";
        }
        if (i == 17) {
            return "PBAP_CLIENT";
        }
        switch (i) {
            case 1:
                return "HEADSET";
            case 2:
                return "A2DP";
            case 3:
                return "HEALTH";
            case 4:
                return "HID_HOST";
            case 5:
                return "PAN";
            case 6:
                return "PBAP";
            case 7:
                return "GATT";
            case 8:
                return "GATT_SERVER";
            case 9:
                return "MAP";
            case 10:
                return "SAP";
            case 11:
                return "A2DP_SINK";
            case 12:
                return "AVRCP_CONTROLLER";
            default:
                return "Unknown";
        }
    }

    public static String parseProfileState(int i) {
        if (i == 0) {
            return "0-BluetoothProfile.STATE_DISCONNECTED";
        }
        if (i == 1) {
            return "1-BluetoothProfile.STATE_CONNECTING";
        }
        if (i == 2) {
            return "2-BluetoothProfile.STATE_CONNECTED";
        }
        if (i == 3) {
            return "3-BluetoothProfile.STATE_DISCONNECTING";
        }
        return "UNKNOWN (" + i + ")";
    }

    public static List<String> parseProperty(int i) {
        return BluetoothGattImpl.parseProperty(i);
    }

    public static String parseProperty2(int i) {
        return BluetoothGattImpl.parseProperty2(i);
    }
}