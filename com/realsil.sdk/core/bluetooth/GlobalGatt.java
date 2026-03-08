package com.realsil.sdk.core.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class GlobalGatt {
    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG);
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static GlobalGatt mInstance;
    public BluetoothManager A;
    public BluetoothAdapter B;
    public volatile boolean W;
    public Context mContext;
    public boolean u;
    public final Object aa = new Object();
    public HashMap<String, BluetoothGatt> S = new HashMap<>();
    public HashMap<String, Integer> U = new HashMap<>();
    public HashMap<String, List<BluetoothGattCallback>> mCallbacks = new HashMap<>();
    public List<String> R = new CopyOnWriteArrayList();

    private class GattCallback extends BluetoothGattCallback {
        public GattCallback() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            String address = bluetoothGatt.getDevice().getAddress();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (GlobalGatt.this.u) {
                ZLogger.d(value != null ? String.format(Locale.US, "<< %s\n(%d)%s", bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)) : String.format(Locale.US, "<< %s", bluetoothGattCharacteristic.getUuid()));
            }
            Iterator it = ((List) GlobalGatt.this.mCallbacks.get(address)).iterator();
            while (it.hasNext()) {
                ((BluetoothGattCallback) it.next()).onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            ZLogger.d(GlobalGatt.this.u ? value != null ? String.format(Locale.US, "%s << %s\n:\t(%d)%s", GattError.parse(i), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)) : String.format(Locale.US, "%s << %s", GattError.parse(i), bluetoothGattCharacteristic.getUuid()) : value != null ? String.format(Locale.US, "%s << (%d)", GattError.parse(i), Integer.valueOf(value.length)) : String.format(Locale.US, "%s <<", GattError.parse(i)));
            synchronized (GlobalGatt.this.aa) {
                GlobalGatt.this.W = true;
                GlobalGatt.this.aa.notifyAll();
            }
            List list = (List) GlobalGatt.this.mCallbacks.get(bluetoothGatt.getDevice().getAddress());
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((BluetoothGattCallback) it.next()).onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (GlobalGatt.this.u) {
                ZLogger.d(value != null ? String.format(Locale.US, "%s << %s\n(%d)%s", GattError.parse(i), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)) : String.format(Locale.US, "%s << %s", GattError.parse(i), bluetoothGattCharacteristic.getUuid()));
            }
            synchronized (GlobalGatt.this.aa) {
                GlobalGatt.this.W = true;
                GlobalGatt.this.aa.notifyAll();
            }
            Iterator it = ((List) GlobalGatt.this.mCallbacks.get(address)).iterator();
            while (it.hasNext()) {
                ((BluetoothGattCallback) it.next()).onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            BluetoothDevice device = bluetoothGatt.getDevice();
            if (device == null) {
                return;
            }
            String address = device.getAddress();
            ZLogger.d(GlobalGatt.this.u ? String.format(Locale.US, "%s, status: %s , newState: %s", address, GattError.parseConnectionError(i), BluetoothHelper.parseProfileState(i2)) : String.format(Locale.US, "status: %s , newState: %s", GattError.parseConnectionError(i), BluetoothHelper.parseProfileState(i2)));
            if (i == 0) {
                boolean z = GlobalGatt.this.u;
                if (i2 == 2) {
                    ZLogger.v(z, "Connected to GATT server.");
                    GlobalGatt.this.U.put(address, 2);
                    GlobalGatt.this.S.put(address, bluetoothGatt);
                } else {
                    ZLogger.v(z, "Disconnected from GATT server.");
                    GlobalGatt.this.U.put(address, 0);
                }
            } else {
                GlobalGatt.this.U.put(address, 0);
            }
            List list = (List) GlobalGatt.this.mCallbacks.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((BluetoothGattCallback) it.next()).onConnectionStateChange(bluetoothGatt, i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            UUID uuid = bluetoothGattDescriptor.getCharacteristic().getUuid();
            byte[] value = bluetoothGattDescriptor.getValue();
            if (GlobalGatt.this.u) {
                ZLogger.d(value != null ? String.format(Locale.US, "%s>> {\nCharacteristic:%s\nDescriptor:%s\nvalue:(%d)%s\n}", GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)) : String.format(Locale.US, "%s>> {\nCharacteristic:%s\nDescriptor:%s}", GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid()));
            }
            synchronized (GlobalGatt.this.aa) {
                GlobalGatt.this.W = true;
                GlobalGatt.this.aa.notifyAll();
            }
            Iterator it = ((List) GlobalGatt.this.mCallbacks.get(address)).iterator();
            while (it.hasNext()) {
                ((BluetoothGattCallback) it.next()).onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(GlobalGatt.this.u ? String.format(Locale.US, "%s << mtu= %d, addr=%s", GattError.parse(i2), Integer.valueOf(i), address) : String.format(Locale.US, "%s << mtu= %d", GattError.parse(i2), Integer.valueOf(i)));
            Iterator it = ((List) GlobalGatt.this.mCallbacks.get(address)).iterator();
            while (it.hasNext()) {
                ((BluetoothGattCallback) it.next()).onMtuChanged(bluetoothGatt, i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(GlobalGatt.this.u ? String.format(Locale.US, "%s << addr=%s", GattError.parse(i), address) : String.format(Locale.US, "%s", GattError.parse(i)));
            List list = (List) GlobalGatt.this.mCallbacks.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((BluetoothGattCallback) it.next()).onServicesDiscovered(bluetoothGatt, i);
            }
        }
    }

    public GlobalGatt(Context context) {
        this.u = false;
        this.mContext = context;
        this.u = RtkCore.DEBUG;
        initialize();
    }

    public static GlobalGatt getInstance() {
        return mInstance;
    }

    public static synchronized void initial(Context context) {
        if (mInstance == null) {
            synchronized (GlobalGatt.class) {
                if (mInstance == null) {
                    mInstance = new GlobalGatt(context.getApplicationContext());
                }
            }
        }
    }

    public void close(String str) {
        disconnectGatt(str);
        closeGatt(str);
    }

    public void closeAll() {
        List<String> list = this.R;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<String> it = this.R.iterator();
        while (it.hasNext()) {
            close(it.next());
        }
    }

    public synchronized void closeGatt(String str) {
        if (str == null) {
            ZLogger.w("Invalid address");
            return;
        }
        ZLogger.v(this.u, "addr:=" + str);
        HashMap<String, BluetoothGatt> map = this.S;
        if (map != null && map.get(str) != null) {
            this.S.get(str).close();
            this.S.remove(str);
        }
        HashMap<String, List<BluetoothGattCallback>> map2 = this.mCallbacks;
        if (map2 != null) {
            map2.remove(str);
        }
        List<String> list = this.R;
        if (list != null && list.contains(str)) {
            this.R.remove(str);
        }
    }

    public boolean connect(String str, int i, BluetoothGattCallback bluetoothGattCallback) {
        if (this.B == null || str == null) {
            ZLogger.w(this.u, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
        if (this.R.contains(str)) {
            BluetoothGatt bluetoothGatt = this.S.get(str);
            if (isConnected(str)) {
                ZLogger.v(this.u, "already connected, addr=" + str);
                registerCallback(str, bluetoothGattCallback);
                if (bluetoothGattCallback != null) {
                    bluetoothGattCallback.onConnectionStateChange(bluetoothGatt, 0, 2);
                }
                return true;
            }
            if (bluetoothGatt != null) {
                registerCallback(str, bluetoothGattCallback);
                ZLogger.v(this.u, "re-connect previous device: " + str);
                if (!bluetoothGatt.connect()) {
                    ZLogger.w("reconnect failed.");
                    closeGatt(str);
                    return false;
                }
                this.U.put(str, 1);
                if (bluetoothGattCallback != null) {
                    bluetoothGattCallback.onConnectionStateChange(bluetoothGatt, 0, 1);
                }
                return true;
            }
        }
        BluetoothDevice remoteDevice = this.B.getRemoteDevice(str);
        if (remoteDevice == null) {
            ZLogger.w(this.u, "Device not found.  Unable to connect.");
            return false;
        }
        registerCallback(str, bluetoothGattCallback);
        ZLogger.v(this.u, "create connection to " + str);
        this.U.put(str, 1);
        BluetoothGatt bluetoothGattConnectGatt = Build.VERSION.SDK_INT >= 23 ? remoteDevice.connectGatt(this.mContext, false, new GattCallback(), i) : remoteDevice.connectGatt(this.mContext, false, new GattCallback());
        if (bluetoothGattConnectGatt == null) {
            ZLogger.w("BluetoothGatt not exist.  Unable to connect.");
        } else {
            this.S.put(str, bluetoothGattConnectGatt);
            this.R.add(str);
        }
        return true;
    }

    public boolean connect(String str, BluetoothGattCallback bluetoothGattCallback) {
        int i = Build.VERSION.SDK_INT;
        return connect(str, 2, bluetoothGattCallback);
    }

    public boolean disconnectGatt(String str) {
        BluetoothGatt bluetoothGatt = this.S.get(str);
        List<BluetoothGattCallback> list = this.mCallbacks.get(str);
        if (bluetoothGatt == null) {
            return false;
        }
        if (!isConnected(str)) {
            if (list == null || list.size() <= 0) {
                return true;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onConnectionStateChange(bluetoothGatt, 0, 0);
            }
            return true;
        }
        ZLogger.v(this.u, "disconnect : " + str);
        bluetoothGatt.disconnect();
        try {
            Thread.sleep(500L);
            return true;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return this.B;
    }

    public List<String> getBluetoothDeviceAddresss() {
        return this.R;
    }

    public BluetoothGatt getBluetoothGatt(String str) {
        return this.S.get(str);
    }

    public List<BluetoothGattCallback> getCallback(String str) {
        HashMap<String, List<BluetoothGattCallback>> map = this.mCallbacks;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public ArrayList<BluetoothDevice> getConnectDevices() {
        ArrayList<BluetoothDevice> arrayList = new ArrayList<>();
        for (String str : this.R) {
            if (isConnected(str)) {
                arrayList.add(getBluetoothGatt(str).getDevice());
            }
        }
        return arrayList;
    }

    public String getDeviceName(String str) {
        BluetoothGatt bluetoothGatt = this.S.get(str);
        if (bluetoothGatt != null) {
            return bluetoothGatt.getDevice().getName();
        }
        ZLogger.w(this.u, "no bluetoothGatt exist, addr=" + str);
        return null;
    }

    public List<BluetoothGattService> getSupportedGattServices(String str) {
        if (this.S.get(str) == null) {
            return null;
        }
        return this.S.get(str).getServices();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean initialize() {
        /*
            r3 = this;
            android.bluetooth.BluetoothManager r0 = r3.A
            r1 = 0
            if (r0 != 0) goto L1b
            android.content.Context r0 = r3.mContext
            java.lang.String r2 = "bluetooth"
            java.lang.Object r0 = r0.getSystemService(r2)
            android.bluetooth.BluetoothManager r0 = (android.bluetooth.BluetoothManager) r0
            r3.A = r0
            android.bluetooth.BluetoothManager r0 = r3.A
            if (r0 != 0) goto L1b
            java.lang.String r0 = "BLUETOOTH_SERVICE not supported."
        L17:
            com.realsil.sdk.core.logger.ZLogger.w(r0)
            return r1
        L1b:
            android.bluetooth.BluetoothAdapter r0 = r3.B
            if (r0 != 0) goto L2e
            android.bluetooth.BluetoothManager r0 = r3.A
            android.bluetooth.BluetoothAdapter r0 = r0.getAdapter()
            r3.B = r0
            android.bluetooth.BluetoothAdapter r0 = r3.B
            if (r0 != 0) goto L2e
            java.lang.String r0 = "BluetoothAdapter is not supported"
            goto L17
        L2e:
            java.lang.String r0 = "initialize success"
            com.realsil.sdk.core.logger.ZLogger.d(r0)
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.GlobalGatt.initialize():boolean");
    }

    public boolean isBluetoothSupported() {
        return this.B != null || initialize();
    }

    public boolean isCallbackRegisted(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        return callback != null && callback.contains(bluetoothGattCallback);
    }

    public boolean isConnected(String str) {
        Integer num = this.U.get(str);
        if (num == null) {
            return false;
        }
        return num.equals(2);
    }

    public boolean isHostConnected(String str) {
        BluetoothManager bluetoothManager = this.A;
        if (bluetoothManager == null) {
            ZLogger.w(this.u, "addr: " + str + ", mBluetoothManager == null");
            return false;
        }
        List<BluetoothDevice> connectedDevices = bluetoothManager.getConnectedDevices(7);
        if (connectedDevices != null) {
            Iterator<BluetoothDevice> it = connectedDevices.iterator();
            while (it.hasNext()) {
                if (it.next().getAddress().equals(str)) {
                    ZLogger.d(this.u, "addr: " + str + ", Connected.");
                    return true;
                }
            }
        }
        ZLogger.d(this.u, "addr: " + str + ", Disconnected.");
        return false;
    }

    public boolean readCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.B == null || this.S.get(str) == null) {
            ZLogger.w(this.u, "BluetoothAdapter not initialized or gatt is null");
            return false;
        }
        ZLogger.d(this.u, "raddr: " + str);
        return this.S.get(str).readCharacteristic(bluetoothGattCharacteristic);
    }

    public boolean readCharacteristicSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.W = false;
        if (!readCharacteristic(str, bluetoothGattCharacteristic)) {
            return false;
        }
        synchronized (this.aa) {
            try {
            } catch (InterruptedException e2) {
                ZLogger.e(this.u, e2.toString());
            }
            if (!this.W) {
                ZLogger.v(this.u, "wait for 3000ms");
                this.aa.wait(3000L);
                ZLogger.d(this.u, "wait time reached");
            }
        }
        return true;
    }

    public void registerCallback(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        if (callback != null) {
            if (!callback.contains(bluetoothGattCallback)) {
            }
            ZLogger.v(this.u, "addr: " + str + ", size = " + callback.size());
        }
        callback = new CopyOnWriteArrayList<>();
        callback.add(bluetoothGattCallback);
        this.mCallbacks.put(str, callback);
        ZLogger.v(this.u, "addr: " + str + ", size = " + callback.size());
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        String str2;
        if (this.B == null) {
            str2 = "BluetoothAdapter not initialized";
        } else {
            BluetoothGatt bluetoothGatt = this.S.get(str);
            if (bluetoothGatt == null) {
                str2 = "BluetoothGatt can not be null, addr=" + str;
            } else {
                ZLogger.d(this.u, "addr:=" + str + ", enabled=" + z);
                bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
                if (descriptor != null) {
                    if (z) {
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    } else {
                        descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    }
                    bluetoothGatt.writeDescriptor(descriptor);
                    return true;
                }
                str2 = "descriptor not found, uuid=" + uuid.toString();
            }
        }
        ZLogger.w(str2);
        return false;
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicIndication(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        String str2;
        if (this.B == null) {
            str2 = "BluetoothAdapter not initialized";
        } else {
            BluetoothGatt bluetoothGatt = this.S.get(str);
            if (bluetoothGatt == null) {
                str2 = "BluetoothGatt can not be null, addr=" + str;
            } else {
                ZLogger.d(this.u, "addr:=" + str + ", enabled=" + z);
                bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
                if (descriptor != null) {
                    if (z) {
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    } else {
                        descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    }
                    bluetoothGatt.writeDescriptor(descriptor);
                    return true;
                }
                str2 = "descriptor not found, uuid=" + uuid.toString();
            }
        }
        ZLogger.w(str2);
        return false;
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicNotification(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

    public boolean setCharacteristicNotificationSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        ZLogger.d(this.u, "addr:=" + str + ", enabled=" + z);
        this.W = false;
        if (!setCharacteristicNotification(str, bluetoothGattCharacteristic, uuid, z)) {
            return false;
        }
        synchronized (this.aa) {
            try {
            } catch (InterruptedException e2) {
                ZLogger.e(this.u, e2.toString());
            }
            if (!this.W) {
                ZLogger.d(this.u, "wait for 3000ms");
                this.aa.wait(3000L);
                ZLogger.d(this.u, "wait time reached");
            }
        }
        return true;
    }

    public void unRegisterAllCallback(String str) {
        if (this.mCallbacks.get(str) == null) {
            ZLogger.w(this.u, "mCallbacks.get(addr) == null");
            return;
        }
        ZLogger.d(this.u, "addr: " + str);
        this.mCallbacks.remove(str);
    }

    public void unRegisterCallback(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        if (callback == null) {
            ZLogger.d(this.u, "callback not registered, addr= " + str);
            return;
        }
        if (callback.contains(bluetoothGattCallback)) {
            ZLogger.v(this.u, "unregister a callback, addr= " + str);
            callback.remove(bluetoothGattCallback);
            this.mCallbacks.put(str, callback);
        }
    }

    public boolean writeCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.B == null || this.S.get(str) == null) {
            ZLogger.w(this.u, "BluetoothAdapter not initialized");
            return false;
        }
        ZLogger.v(this.u, "addr: " + str);
        return this.S.get(str).writeCharacteristic(bluetoothGattCharacteristic);
    }

    public synchronized boolean writeCharacteristicSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.W = false;
        if (!writeCharacteristic(str, bluetoothGattCharacteristic)) {
            return false;
        }
        synchronized (this.aa) {
            try {
            } catch (InterruptedException e2) {
                ZLogger.e(this.u, e2.toString());
            }
            if (!this.W) {
                ZLogger.d(this.u, "wait for 3000ms");
                this.aa.wait(3000L);
                ZLogger.d(this.u, "wait time reached");
            }
        }
        return true;
    }
}