package com.realsil.sdk.dfu.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.core.internal.view.SupportMenu;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.BluetoothUuid;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.core.DfuProfile;
import com.realsil.sdk.dfu.core.gatt.GattDfuProfile;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import kotlin.UByte;
import kotlin.UShort;

/* JADX INFO: loaded from: classes3.dex */
public class DfuHelper extends DfuAdapter implements GattDfuProfile {
    public static volatile DfuHelper mInstance;
    public GlobalGatt Le;
    public BluetoothGatt Me;
    public BluetoothGattCharacteristic Ne;
    public BluetoothGattCharacteristic Oe;
    public BluetoothGattCharacteristic Pe;
    public BluetoothGattCharacteristic Qe;
    public BluetoothGattCharacteristic Re;
    public BluetoothGattCharacteristic Se;
    public BluetoothGattCharacteristic Te;
    public BluetoothGattCharacteristic Ue;
    public List<BluetoothGattCharacteristic> Ve;
    public BluetoothGattCharacteristic We;
    public boolean Xe = false;
    public Object Ye = new Object();
    public boolean Ze = true;
    public Runnable _e = new Runnable() { // from class: com.realsil.sdk.dfu.utils.DfuHelper.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (DfuHelper.this.mLock) {
                ZLogger.d("wait to pair device");
                try {
                    DfuHelper.this.mLock.wait(15000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
            }
            try {
                Thread.sleep(800L);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            if (DfuHelper.this.E()) {
                ZLogger.d("wait discover service commplete");
                synchronized (DfuHelper.this.mLock) {
                    try {
                        DfuHelper.this.mLock.wait(30000L);
                    } catch (InterruptedException e4) {
                        e4.printStackTrace();
                        ZLogger.e(e4.toString());
                    }
                }
                if (DfuHelper.this.mState == 263) {
                    ZLogger.w("discoverServices timeout");
                    DfuHelper.this.disconnect();
                }
            }
        }
    };
    public Runnable af = new Runnable() { // from class: com.realsil.sdk.dfu.utils.DfuHelper.3
        @Override // java.lang.Runnable
        public void run() {
            if (DfuHelper.this.E()) {
                ZLogger.d("wait discover service commplete");
                synchronized (DfuHelper.this.mLock) {
                    try {
                        DfuHelper.this.mLock.wait(30000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        ZLogger.e(e2.toString());
                    }
                }
                if (DfuHelper.this.mState == 263) {
                    ZLogger.w("discoverServices timeout");
                    DfuHelper.this.disconnect();
                }
            }
        }
    };
    public Runnable bf = new Runnable() { // from class: com.realsil.sdk.dfu.utils.DfuHelper.4
        @Override // java.lang.Runnable
        public void run() {
            Thread thread;
            DfuHelper dfuHelper = DfuHelper.this;
            int i = dfuHelper.mState;
            if (i == 262) {
                dfuHelper.ye = dfuHelper.getBondState(dfuHelper.Ae);
                ZLogger.v(">> mBondState: " + DfuHelper.this.ye);
                DfuHelper dfuHelper2 = DfuHelper.this;
                if (dfuHelper2.ye != 11) {
                    new Thread(dfuHelper2.af).start();
                    return;
                } else {
                    ZLogger.d("BOND_BONDING: wait to discover service");
                    thread = new Thread(DfuHelper.this._e);
                }
            } else {
                if (i != 261) {
                    ZLogger.d("ignore state:" + DfuHelper.this.mState);
                    return;
                }
                ZLogger.d("STATE_PROCESS_PAIRING_REQUEST: wait to discover service");
                thread = new Thread(DfuHelper.this._e);
            }
            thread.start();
        }
    };
    public Handler mHandler = new Handler(Looper.getMainLooper());
    public BluetoothGattCallback cf = new BluetoothGattCallback() { // from class: com.realsil.sdk.dfu.utils.DfuHelper.5
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (GattDfuProfile.Dfu.DFU_CONTROL_POINT_CHARACTERISTIC.equals(bluetoothGattCharacteristic.getUuid())) {
                if (value == null || value.length < 2) {
                    ZLogger.w("notification data invalid");
                    return;
                }
                int i = value[0] & UByte.MAX_VALUE;
                int i2 = value[1] & UByte.MAX_VALUE;
                byte b2 = value[2];
                ZLogger.v(String.format("responseType = %02X , requestOpCode = %02X", Integer.valueOf(i), Integer.valueOf(i2)));
                if (i == 16 && i2 == 13) {
                    if (b2 == 1) {
                        DfuHelper.this.getOtaDeviceInfo().parse(value, 3);
                    } else {
                        ZLogger.e("Get temp dev info failed");
                    }
                    DfuHelper.this.D();
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            OtaDeviceInfo otaDeviceInfo;
            int i2;
            OtaDeviceInfo otaDeviceInfo2;
            int i3;
            OtaDeviceInfo otaDeviceInfo3;
            int i4;
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            if (i != 0) {
                if (!GattDfuProfile.Ota.OTA_DEVICE_INFO_CHARACTERISTIC_UUID.equals(uuid)) {
                    ZLogger.d("ignore exctption when read other info");
                    return;
                } else {
                    if (DfuHelper.this.isPreparing()) {
                        DfuHelper.this.a(DfuException.ConnectionException(5));
                        return;
                    }
                    return;
                }
            }
            byte[] value = bluetoothGattCharacteristic.getValue();
            int length = value != null ? value.length : 0;
            if (GattDfuProfile.Ota.OTA_DEVICE_INFO_CHARACTERISTIC_UUID.equals(uuid)) {
                DfuHelper.this.getOtaDeviceInfo().parse(value);
            } else if (GattDfuProfile.Ota.OTA_DEVICE_MAC_CHARACTERISTIC_UUID.equals(uuid)) {
                if (length > 0) {
                    ByteBuffer byteBufferWrap = ByteBuffer.wrap(value);
                    byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                    if (length >= 6) {
                        byte[] bArr = new byte[6];
                        byteBufferWrap.get(bArr, 0, 6);
                        DfuHelper.this.getOtaDeviceInfo().setDeviceMac(bArr);
                    }
                }
            } else if (GattDfuProfile.Ota.OTA_PATCH_VERSION_CHARACTERISTIC_UUID.equals(uuid)) {
                if (length > 0) {
                    ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(value);
                    byteBufferWrap2.order(ByteOrder.LITTLE_ENDIAN);
                    if (length == 2) {
                        otaDeviceInfo3 = DfuHelper.this.getOtaDeviceInfo();
                        i4 = byteBufferWrap2.getShort(0);
                    } else if (length >= 4) {
                        otaDeviceInfo3 = DfuHelper.this.getOtaDeviceInfo();
                        i4 = byteBufferWrap2.getInt(0);
                    }
                    otaDeviceInfo3.setPatchVersion(i4 & SupportMenu.USER_MASK);
                }
            } else if (GattDfuProfile.Ota.OTA_APP_VERSION_CHARACTERISTIC_UUID.equals(uuid)) {
                if (length > 0) {
                    ByteBuffer byteBufferWrap3 = ByteBuffer.wrap(value);
                    byteBufferWrap3.order(ByteOrder.LITTLE_ENDIAN);
                    if (length == 2) {
                        otaDeviceInfo2 = DfuHelper.this.getOtaDeviceInfo();
                        i3 = byteBufferWrap3.getShort(0) & UShort.MAX_VALUE;
                    } else if (length >= 4) {
                        otaDeviceInfo2 = DfuHelper.this.getOtaDeviceInfo();
                        i3 = byteBufferWrap3.getInt(0);
                    }
                    otaDeviceInfo2.setAppVersion(i3);
                }
            } else if (GattDfuProfile.Ota.OTA_PATCH_EXTENSION_VERSION_CHARACTERISTIC_UUID.equals(uuid)) {
                ByteBuffer byteBufferWrap4 = ByteBuffer.wrap(value);
                byteBufferWrap4.order(ByteOrder.LITTLE_ENDIAN);
                if (length == 1) {
                    otaDeviceInfo = DfuHelper.this.getOtaDeviceInfo();
                    i2 = byteBufferWrap4.get(0);
                } else if (length == 2) {
                    otaDeviceInfo = DfuHelper.this.getOtaDeviceInfo();
                    i2 = byteBufferWrap4.getShort(0) & UShort.MAX_VALUE;
                }
                otaDeviceInfo.setPatchExtensionVersion(i2);
            } else if (GattDfuProfile.Bas.BAS_READ_CHARACTERITIC.equals(uuid)) {
                int i5 = value[0] & UByte.MAX_VALUE;
                ZLogger.v("current battery: " + i5);
                DfuHelper.this.getOtaDeviceInfo().setBatteryLevel(i5);
            } else if (GattDfuProfile.Dis.DIS_PNP_ID_CHARACTERISTIC.equals(uuid)) {
                ZLogger.v("PNP_ID: " + DataConverter.bytes2Hex(value));
                DfuHelper.this.getOtaDeviceInfo().setPnpId(value);
            } else {
                int shortValue = BluetoothUuid.toShortValue(uuid);
                if (shortValue >= 65504 && shortValue <= 65519) {
                    DfuHelper.this.getOtaDeviceInfo().appendImageVersionBytes(value);
                } else if (shortValue >= 65472 && shortValue <= 65487) {
                    DfuHelper.this.getOtaDeviceInfo().appendDebugCharacteristicInfo(shortValue, value);
                }
            }
            DfuHelper.this.D();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i == 0) {
                if (i2 == 2) {
                    DfuHelper dfuHelper = DfuHelper.this;
                    dfuHelper.Me = dfuHelper.Le.getBluetoothGatt(DfuHelper.this.Ae);
                    if (bluetoothGatt != null) {
                        bluetoothGatt.getDevice().getBondState();
                        DfuHelper dfuHelper2 = DfuHelper.this;
                        if (dfuHelper2.mState != 262) {
                            dfuHelper2.e(262);
                            if (DfuHelper.this.mHandler == null) {
                                ZLogger.w("mHandler == null");
                                return;
                            }
                            ZLogger.d("delay to discover service for : 1600");
                            ZLogger.d("postDelayed:" + DfuHelper.this.mHandler.postDelayed(DfuHelper.this.bf, (long) 1600));
                            return;
                        }
                        return;
                    }
                } else if (i2 != 0) {
                    return;
                } else {
                    DfuHelper.this.disconnect();
                }
            }
            s();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("onDescriptorWrite: ");
                sb.append(i);
                ZLogger.v(sb.toString());
                synchronized (DfuHelper.this.Ye) {
                    DfuHelper.this.Ze = true;
                    DfuHelper.this.Ye.notifyAll();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                ZLogger.e(e2.toString());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            BluetoothGattService service;
            UUID dfuServiceUuid;
            DfuHelper dfuHelper = DfuHelper.this;
            int i2 = dfuHelper.mState;
            if (i2 == 513) {
                ZLogger.d("ignore, when it is ota processing");
                return;
            }
            if (i != 0) {
                ZLogger.w("service discovery failed !!!");
                if (DfuHelper.this.isPreparing()) {
                    DfuHelper.this.a(DfuException.ConnectionException(1));
                    return;
                }
                return;
            }
            dfuHelper.e(264);
            if (i2 == 263) {
                DfuHelper.this.B();
            }
            ConnectParams connectParams = DfuHelper.this.Be;
            if (connectParams != null) {
                service = bluetoothGatt.getService(connectParams.getOtaServiceUuid());
                dfuServiceUuid = DfuHelper.this.Be.getDfuServiceUuid();
            } else {
                service = bluetoothGatt.getService(GattDfuProfile.Ota.OTA_SERVICE);
                dfuServiceUuid = GattDfuProfile.Dfu.DFU_SERVICE;
            }
            BluetoothGattService service2 = bluetoothGatt.getService(dfuServiceUuid);
            BluetoothGattService service3 = bluetoothGatt.getService(GattDfuProfile.Bas.BATTERY_SERVICE);
            BluetoothGattService service4 = bluetoothGatt.getService(GattDfuProfile.Dis.DEVICE_INFORMATION_SERVICE);
            DfuHelper.this.Ve = new ArrayList();
            DfuHelper.this.Vc = new OtaDeviceInfo(2);
            ConnectParams connectParams2 = DfuHelper.this.Be;
            if ((connectParams2 == null || !DfuProfile.TARGET_DEVICE_NAME_NO_TEMP.equals(connectParams2.getLocalName())) && !DfuHelper.this.c(service)) {
                return;
            }
            DfuHelper.this.b(service2);
            DfuHelper.this.d(service3);
            DfuHelper.this.a(service4);
            DfuHelper.this.D();
        }

        public final void s() {
            if (DfuHelper.this.isPreparing()) {
                DfuHelper.this.a(DfuException.ConnectionException(0));
            } else {
                DfuHelper.this.e(1025);
            }
        }
    };

    public DfuHelper(Context context) {
        this.mContext = context;
        C();
    }

    public DfuHelper(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        this.mContext = context;
        addDfuHelperCallback(dfuHelperCallback);
        C();
    }

    public static DfuHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DfuHelper.class) {
                if (mInstance == null) {
                    mInstance = new DfuHelper(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public static DfuHelper getInstance(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        if (mInstance == null) {
            synchronized (DfuHelper.class) {
                if (mInstance == null) {
                    mInstance = new DfuHelper(context.getApplicationContext(), dfuHelperCallback);
                }
            }
        }
        return mInstance;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void C() {
        super.C();
        this.Le = GlobalGatt.getInstance();
        if (this.Le == null) {
            GlobalGatt.initial(this.mContext);
            this.Le = GlobalGatt.getInstance();
        }
    }

    public final void D() {
        List<BluetoothGattCharacteristic> list;
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        List<BluetoothGattCharacteristic> list2 = this.Ve;
        if (list2 != null && list2.size() > 0) {
            if (this.Ve.contains(this.Pe)) {
                ZLogger.v("attempt to read device info....");
                boolean zA = a(this.Pe);
                ZLogger.d("readDeviceInfo:" + zA);
                this.Ve.remove(this.Pe);
                if (zA) {
                    return;
                }
            } else {
                if (!this.Ve.contains(this.Oe)) {
                    if (this.Ve.contains(this.Qe)) {
                        if (getOtaDeviceInfo().otaVersion == 0) {
                            ZLogger.v("attempt to read app version....");
                            boolean zA2 = a(this.Qe);
                            ZLogger.d("readDeviceInfo:" + zA2);
                            this.Ve.remove(this.Qe);
                            if (zA2) {
                                return;
                            }
                        } else {
                            list = this.Ve;
                            bluetoothGattCharacteristic = this.Qe;
                            list.remove(bluetoothGattCharacteristic);
                        }
                    } else if (this.Ve.contains(this.Re)) {
                        if (getOtaDeviceInfo().otaVersion == 0) {
                            ZLogger.v("attempt to read patch version....");
                            boolean zA3 = a(this.Re);
                            ZLogger.d("readDeviceInfo:" + zA3);
                            this.Ve.remove(this.Re);
                            if (zA3) {
                                return;
                            }
                        } else {
                            list = this.Ve;
                            bluetoothGattCharacteristic = this.Re;
                            list.remove(bluetoothGattCharacteristic);
                        }
                    } else if (this.Ve.contains(this.Se)) {
                        if (getOtaDeviceInfo().otaVersion == 0) {
                            ZLogger.v("attempt to read patch extension version....");
                            boolean zA4 = a(this.Se);
                            ZLogger.d("readDeviceInfo:" + zA4);
                            this.Ve.remove(this.Se);
                            if (zA4) {
                                return;
                            }
                        } else {
                            list = this.Ve;
                            bluetoothGattCharacteristic = this.Se;
                            list.remove(bluetoothGattCharacteristic);
                        }
                    } else if (this.Ve.contains(this.Te)) {
                        ZLogger.v("attempt to read battery level ....");
                        boolean zA5 = a(this.Te);
                        ZLogger.d("readDeviceInfo:" + zA5);
                        this.Ve.remove(this.Te);
                        if (zA5) {
                            return;
                        }
                    } else if (this.Ve.contains(this.Ue)) {
                        ZLogger.v("attempt to read PnP_ID ....");
                        boolean zA6 = a(this.Ue);
                        ZLogger.d("readDeviceInfo:" + zA6);
                        this.Ve.remove(this.Ue);
                        if (zA6) {
                            return;
                        }
                    } else {
                        if (this.Ve.contains(this.We)) {
                            this.Xe = true;
                            new Thread(new Runnable() { // from class: com.realsil.sdk.dfu.utils.DfuHelper.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    DfuHelper dfuHelper = DfuHelper.this;
                                    dfuHelper.b(dfuHelper.Me, DfuHelper.this.We, true);
                                    if (DfuHelper.this.Ze) {
                                        boolean zF = DfuHelper.this.F();
                                        ZLogger.d("readTempDeviceInfo:" + zF);
                                        DfuHelper.this.Ve.remove(DfuHelper.this.We);
                                        if (zF) {
                                            return;
                                        }
                                        DfuHelper.this.D();
                                    }
                                }
                            }).start();
                            return;
                        }
                        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = this.Ve.get(0);
                        int shortValue = BluetoothUuid.toShortValue(bluetoothGattCharacteristic2.getUuid());
                        ZLogger.v(String.format("uuidShortValue=0x%4x", Integer.valueOf(shortValue)));
                        if (shortValue >= 65472 && shortValue <= 65487) {
                            ZLogger.d("attempt to read debug info....");
                            boolean zA7 = a(bluetoothGattCharacteristic2);
                            ZLogger.d("readDeviceInfo:" + zA7);
                            this.Ve.remove(bluetoothGattCharacteristic2);
                            if (zA7) {
                                return;
                            }
                        } else if (shortValue < 65504 || shortValue > 65519) {
                            ZLogger.d("no more characteristic to read");
                            ZLogger.d(this.D, getOtaDeviceInfo().toString());
                            this.Ve.clear();
                        } else if (getOtaDeviceInfo().otaVersion == 0) {
                            this.Ve.remove(bluetoothGattCharacteristic2);
                        } else {
                            ZLogger.d("attempt to read image version....");
                            boolean zA8 = a(bluetoothGattCharacteristic2);
                            ZLogger.d("readDeviceInfo:" + zA8);
                            this.Ve.remove(bluetoothGattCharacteristic2);
                            if (zA8) {
                                return;
                            }
                        }
                    }
                    D();
                    return;
                }
                ZLogger.v("attempt to read device mac....");
                boolean zA9 = a(this.Oe);
                ZLogger.d("readDeviceInfo:" + zA9);
                this.Ve.remove(this.Oe);
                if (zA9) {
                    return;
                }
            }
            D();
            return;
        }
        ZLogger.d(this.D, getOtaDeviceInfo().toString());
        e(512);
    }

    public final boolean E() {
        boolean zDiscoverServices;
        if (this.mState == 263) {
            ZLogger.w("discoverServices already started");
            return false;
        }
        e(263);
        if (this.Me != null) {
            ZLogger.v("discoverServices...");
            zDiscoverServices = this.Me.discoverServices();
        } else {
            ZLogger.w("mBtGatt == null");
            zDiscoverServices = false;
        }
        if (zDiscoverServices) {
            return true;
        }
        ZLogger.w("discoverServices failed");
        if (isPreparing()) {
            a(DfuException.ConnectionException(1));
        }
        return false;
    }

    public final boolean F() {
        if (this.Me == null || this.We == null) {
            ZLogger.w("mBtGatt is null maybe disconnected just now");
            return false;
        }
        ZLogger.v("attempt to read temp device info ....: ");
        this.We.setValue(new byte[]{13});
        boolean zWriteCharacteristic = this.Me.writeCharacteristic(this.We);
        ZLogger.v("writeCharacteristic:" + zWriteCharacteristic);
        this.Xe = zWriteCharacteristic ^ true;
        return zWriteCharacteristic;
    }

    public final void a(BluetoothGattService bluetoothGattService) {
        if (bluetoothGattService == null) {
            ZLogger.w("DEVICE_INFORMATION_SERVICE not found");
            return;
        }
        ZLogger.d("find DEVICE_INFORMATION_SERVICE: " + GattDfuProfile.Dis.DEVICE_INFORMATION_SERVICE.toString());
        this.Ue = bluetoothGattService.getCharacteristic(GattDfuProfile.Dis.DIS_PNP_ID_CHARACTERISTIC);
        if (this.Ue == null) {
            ZLogger.w("DIS_PNP_ID_CHARACTERISTIC not found");
            return;
        }
        ZLogger.d("find DIS_PNP_ID_CHARACTERISTIC: " + GattDfuProfile.Dis.DIS_PNP_ID_CHARACTERISTIC.toString());
        this.Ve.add(this.Ue);
    }

    public final boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.Me == null || bluetoothGattCharacteristic == null) {
            ZLogger.w("mBtGatt is null maybe disconnected just now");
            return false;
        }
        ZLogger.v(this.D, "readCharacteristic:" + bluetoothGattCharacteristic.getUuid());
        return this.Me.readCharacteristic(bluetoothGattCharacteristic);
    }

    public final boolean a(String str) {
        e(260);
        return this.Le.connect(str, this.cf);
    }

    public final void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        int properties = bluetoothGattCharacteristic.getProperties();
        if ((properties & 16) == 0) {
            ZLogger.w("check properties failed: " + properties);
            this.Ze = false;
            return;
        }
        ZLogger.v("setCharacteristicNotification() - uuid: " + bluetoothGattCharacteristic.getUuid() + " enabled: " + z);
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(GattDfuProfile.CLIENT_CHARACTERISTIC_CONFIG);
        if (descriptor != null) {
            boolean z2 = descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] > 0 && descriptor.getValue()[1] == 0;
            ZLogger.v("current cccd state: " + z2);
            if (z && z2) {
                this.Ze = true;
                ZLogger.w("cccd already enabled");
                return;
            }
            if (!z && !z2) {
                ZLogger.w("cccd already disable");
                this.Ze = true;
                return;
            }
            descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            if (bluetoothGatt.writeDescriptor(descriptor)) {
                synchronized (this.Ye) {
                    ZLogger.d("wait write Characteristic Notification 15000ms");
                    try {
                        this.Ze = false;
                        this.Ye.wait(30000L);
                    } catch (InterruptedException e2) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("wait writeDescriptor interrupted: ");
                        sb.append(e2.toString());
                        ZLogger.e(sb.toString());
                    }
                }
            }
        }
    }

    public final void b(BluetoothGattService bluetoothGattService) {
        this.De = new ArrayList();
        if (bluetoothGattService == null) {
            this.We = null;
            this.De.add(new OtaModeInfo(0));
            ZLogger.w(this.D, "not find DFU_SERVICE_UUID = " + GattDfuProfile.Dfu.DFU_SERVICE);
            return;
        }
        ZLogger.d(this.D, "find DFU_SERVICE_UUID = " + bluetoothGattService.getUuid());
        ConnectParams connectParams = this.Be;
        if (connectParams == null || !DfuProfile.TARGET_DEVICE_NAME_NO_TEMP.equals(connectParams.getLocalName())) {
            BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(GattDfuProfile.Dfu.DFU_EXTEND_FLASH_CHARACTERISTIC);
            if (characteristic == null) {
                ZLogger.w(this.D, "DFU_EXTEND_FLASH_CHARACTERISTIC not found");
                this.De.add(new OtaModeInfo(16));
                if (this.Ne != null) {
                    this.De.add(new OtaModeInfo(0));
                    return;
                }
                return;
            }
            ZLogger.d(this.D, "find DFU_EXTEND_FLASH_CHARACTERISTIC = " + GattDfuProfile.Dfu.DFU_EXTEND_FLASH_CHARACTERISTIC);
            this.De.add(new OtaModeInfo(17));
            ZLogger.d(BluetoothGattImpl.parseProperty2(characteristic.getProperties()));
            return;
        }
        this.De.add(new OtaModeInfo(18));
        this.We = bluetoothGattService.getCharacteristic(GattDfuProfile.Dfu.DFU_CONTROL_POINT_CHARACTERISTIC);
        if (this.We == null) {
            ZLogger.w("not found DFU_CONTROL_POINT_CHARACTERISTIC: " + GattDfuProfile.Dfu.DFU_CONTROL_POINT_CHARACTERISTIC);
            return;
        }
        ZLogger.d("find DFU_CONTROL_POINT_CHARACTERISTIC: " + GattDfuProfile.Dfu.DFU_CONTROL_POINT_CHARACTERISTIC);
        this.We.setWriteType(2);
        ZLogger.d(BluetoothGattImpl.parseProperty2(this.We.getProperties()));
        this.Ve.add(this.We);
    }

    public final boolean b(String str) {
        BluetoothDevice remoteDevice;
        try {
            remoteDevice = this.qc.getRemoteDevice(str);
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
            remoteDevice = null;
        }
        return connectHid(remoteDevice);
    }

    public final boolean c(BluetoothGattService bluetoothGattService) {
        if (bluetoothGattService == null) {
            ZLogger.w("OTA_SERVICE not found:");
            if (!isPreparing()) {
                return false;
            }
            a(DfuException.ConnectionException(3));
            return false;
        }
        ZLogger.d(this.D, "find OTA_SERVICE = " + bluetoothGattService.getUuid());
        this.Ne = bluetoothGattService.getCharacteristic(GattDfuProfile.Ota.OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC);
        if (this.Ne == null) {
            ZLogger.w("OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC not found");
        } else {
            ZLogger.d(this.D, "find OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC = " + GattDfuProfile.Ota.OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC);
        }
        this.Oe = bluetoothGattService.getCharacteristic(GattDfuProfile.Ota.OTA_DEVICE_MAC_CHARACTERISTIC_UUID);
        if (this.Oe == null) {
            ZLogger.w("OTA_DEVICE_MAC_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.d(this.D, "find OTA_DEVICE_MAC_CHARACTERISTIC_UUID = " + GattDfuProfile.Ota.OTA_DEVICE_MAC_CHARACTERISTIC_UUID);
            this.Ve.add(this.Oe);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.Oe.getProperties()));
        }
        this.Re = bluetoothGattService.getCharacteristic(GattDfuProfile.Ota.OTA_PATCH_VERSION_CHARACTERISTIC_UUID);
        if (this.Re == null) {
            ZLogger.w("OTA_READ_PATCH_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.d(this.D, "find OTA_READ_PATCH_CHARACTERISTIC_UUID = " + GattDfuProfile.Ota.OTA_PATCH_VERSION_CHARACTERISTIC_UUID);
            this.Ve.add(this.Re);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.Re.getProperties()));
        }
        this.Qe = bluetoothGattService.getCharacteristic(GattDfuProfile.Ota.OTA_APP_VERSION_CHARACTERISTIC_UUID);
        if (this.Qe == null) {
            ZLogger.w("OTA_APP_VERSION_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.d(this.D, "find OTA_APP_VERSION_CHARACTERISTIC_UUID = " + GattDfuProfile.Ota.OTA_APP_VERSION_CHARACTERISTIC_UUID);
            this.Ve.add(this.Qe);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.Qe.getProperties()));
        }
        this.Se = bluetoothGattService.getCharacteristic(GattDfuProfile.Ota.OTA_PATCH_EXTENSION_VERSION_CHARACTERISTIC_UUID);
        if (this.Se == null) {
            ZLogger.w("OTA_READ_PATCH_EXTENSION_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.d(this.D, "find OTA_READ_PATCH_EXTENSION_CHARACTERISTIC_UUID = " + GattDfuProfile.Ota.OTA_PATCH_EXTENSION_VERSION_CHARACTERISTIC_UUID);
            this.Ve.add(this.Se);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.Se.getProperties()));
        }
        this.Pe = bluetoothGattService.getCharacteristic(GattDfuProfile.Ota.OTA_DEVICE_INFO_CHARACTERISTIC_UUID);
        if (this.Pe == null) {
            ZLogger.w("OTA_DEVICE_INFO_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.d(this.D, "find OTA_DEVICE_INFO_CHARACTERISTIC_UUID = " + GattDfuProfile.Ota.OTA_DEVICE_INFO_CHARACTERISTIC_UUID);
            this.Ve.add(this.Pe);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.Pe.getProperties()));
        }
        int i = GattDfuProfile.Ota.OTA_DEBUG_CHARACTERISTIC_UUID_MIN;
        while (true) {
            if (i > 65487) {
                break;
            }
            UUID uuidFromShortValue = BluetoothUuid.fromShortValue(i);
            BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuidFromShortValue);
            if (characteristic == null) {
                ZLogger.w("not found debug characteristic:" + uuidFromShortValue.toString());
                break;
            }
            ZLogger.d(this.D, "find debug characteristic: " + uuidFromShortValue.toString());
            this.Ve.add(characteristic);
            i++;
        }
        for (int i2 = GattDfuProfile.Ota.OTA_IMAGE_VERSION_CHARACTERISTIC_UUID_MIN; i2 <= 65519; i2++) {
            UUID uuidFromShortValue2 = BluetoothUuid.fromShortValue(i2);
            BluetoothGattCharacteristic characteristic2 = bluetoothGattService.getCharacteristic(uuidFromShortValue2);
            if (characteristic2 == null) {
                ZLogger.w(this.D, "not found image version characteristic:" + uuidFromShortValue2.toString());
                return true;
            }
            ZLogger.d(this.D, "find image version characteristic: " + uuidFromShortValue2.toString());
            this.Ve.add(characteristic2);
        }
        return true;
    }

    public int checkBatteryLevel(int i, int i2) {
        if (this.Te == null) {
            return 0;
        }
        int batteryLevel = getOtaDeviceInfo().getBatteryLevel();
        if (getOtaDeviceInfo().icType <= 3 && i2 == 1) {
            batteryLevel = (((batteryLevel * 2) - 210) * 100) / 90;
        }
        if (batteryLevel <= i) {
            return DfuException.ERROR_BATTERY_LEVEL_LOW;
        }
        if (batteryLevel <= 110 || batteryLevel > 140) {
            return 0;
        }
        return DfuException.ERROR_BATTERY_LEVEL_LOW;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean connectDevice(ConnectParams connectParams) {
        boolean zA;
        if (!super.connectDevice(connectParams)) {
            return false;
        }
        if (this.Be.getAddress() == null) {
            ZLogger.w("address is null");
            return false;
        }
        String str = this.Ae;
        if (str != null && (Build.VERSION.SDK_INT < 19 ? !equals(str, this.Be.getAddress()) : !Objects.equals(str, this.Be.getAddress()))) {
            this.Le.unRegisterCallback(this.Ae, this.cf);
            this.Le.close(this.Ae);
        }
        BluetoothDevice remoteDevice = null;
        try {
            remoteDevice = this.qc.getRemoteDevice(this.Be.getAddress());
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
        }
        this.ze = remoteDevice;
        this.Ae = this.Be.getAddress();
        this.ve = this.Be.getReconnectTimes();
        this.ye = getBondState(this.Ae);
        ZLogger.v(this.D, String.format(Locale.US, ">> mBondState: %d", Integer.valueOf(this.ye)));
        if (!this.Be.isHid() || !(zA = b(this.Ae))) {
            zA = a(this.Ae);
        }
        if (!zA) {
            e(1026);
        }
        return zA;
    }

    public final boolean connectHid(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (!BluetoothProfileManager.getInstance().isProfileSupported(4)) {
            ZLogger.w("HID_HOST not supported");
            return false;
        }
        int bondState = bluetoothDevice.getBondState();
        if (bondState != 12) {
            ZLogger.d(this.D, "connect with not bond device, bond first, current state: " + bondState);
            e(259);
            return bluetoothDevice.createBond();
        }
        if (isHogpConnect(bluetoothDevice.getAddress())) {
            ZLogger.d("hogp already connected");
            return a(bluetoothDevice.getAddress());
        }
        if (BluetoothDeviceImpl.removeBond(bluetoothDevice)) {
            ZLogger.d("remove bond first");
            e(258);
            return false;
        }
        ZLogger.d("remove bond failed");
        e(257);
        return BluetoothProfileManager.getInstance().connectHid(bluetoothDevice);
    }

    public final void d(BluetoothGattService bluetoothGattService) {
        if (bluetoothGattService == null) {
            ZLogger.w("BATTERY_SERVICE not found");
            return;
        }
        ZLogger.d(this.D, "find BATTERY_SERVICE: " + GattDfuProfile.Bas.BATTERY_SERVICE.toString());
        this.Te = bluetoothGattService.getCharacteristic(GattDfuProfile.Bas.BAS_READ_CHARACTERITIC);
        if (this.Te == null) {
            ZLogger.w("BAS_READ_CHARACTERITIC not found");
            return;
        }
        ZLogger.d(this.D, "find BAS_READ_CHARACTERITIC: " + GattDfuProfile.Bas.BAS_READ_CHARACTERITIC.toString());
        this.Ve.add(this.Te);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void destroy() {
        super.destroy();
        GlobalGatt globalGatt = this.Le;
        if (globalGatt != null) {
            globalGatt.unRegisterCallback(this.Ae, this.cf);
        }
        BluetoothProfileManager bluetoothProfileManager = this.rc;
        if (bluetoothProfileManager != null) {
            bluetoothProfileManager.closeProfileProxy(4);
        }
        mInstance = null;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void disconnect() {
        String str;
        String str2;
        String str3 = this.Ae;
        if (str3 == null) {
            str = "no device registed";
        } else {
            GlobalGatt globalGatt = this.Le;
            if (globalGatt != null) {
                if (!globalGatt.isConnected(str3)) {
                    str2 = "already disconnected";
                } else {
                    if (this.Le.isCallbackRegisted(this.Ae, this.cf)) {
                        e(1024);
                        this.Le.close(this.Ae);
                        this.Me = null;
                    }
                    str2 = "no gatt callback registed";
                }
                ZLogger.v(str2);
                e(1025);
                this.Me = null;
            }
            str = "mGlobalGatt == null";
        }
        ZLogger.d(str);
        e(1025);
        this.Me = null;
    }

    public boolean isHogpConnect(BluetoothDevice bluetoothDevice) {
        return BluetoothProfileManager.getInstance().getConnectionState(4, bluetoothDevice) == 2;
    }

    public boolean isHogpConnect(String str) {
        BluetoothDevice remoteDevice;
        try {
            remoteDevice = this.qc.getRemoteDevice(str);
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
            remoteDevice = null;
        }
        return remoteDevice != null && BluetoothProfileManager.getInstance().getConnectionState(4, remoteDevice) == 2;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void processBondStateChanged(int i) {
        switch (i) {
            case 10:
                ZLogger.v(this.D, "BOND_NONE");
                if (this.mState == 258 && this.ze != null) {
                    ZLogger.v(this.D, "createBond");
                    this.ze.createBond();
                    break;
                }
                break;
            case 11:
                ZLogger.v(this.D, "BOND_BONDING");
                break;
            case 12:
                ZLogger.v(this.D, "BOND_BONDED");
                if (this.mState != 259) {
                    B();
                } else if (this.ze != null) {
                    if (!isHogpConnect(this.Ae)) {
                        ZLogger.d("hid not connect");
                        e(257);
                        BluetoothProfileManager.getInstance().connectHid(this.ze);
                    } else {
                        ZLogger.d("hid already connected");
                        a(this.Ae);
                    }
                }
                break;
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void processHidStateChanged(int i) {
        boolean z;
        String str;
        super.processHidStateChanged(i);
        if (i == 0) {
            ZLogger.v(this.D, " Braodcast: RCU Disconnected!");
            if (this.mState == 257) {
                a(DfuException.ConnectionException(0));
                return;
            }
            return;
        }
        if (i == 1) {
            z = this.D;
            str = "RCU Connecting!";
        } else {
            if (i == 2) {
                ZLogger.v(this.D, "RCU Connected!");
                if (this.mState == 257) {
                    ZLogger.v(this.D, "connect gatt: " + this.Ae);
                    e(260);
                    this.Le.connect(this.Ae, this.cf);
                    return;
                }
                return;
            }
            if (i != 3) {
                return;
            }
            z = this.D;
            str = " Braodcast: RCU Disconnecting!";
        }
        ZLogger.v(z, str);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void reconnect() {
        super.reconnect();
        Handler handler = this.Ee;
        if (handler != null) {
            handler.postDelayed(this.Fe, 1000L);
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean startOtaProcedure(DfuConfig dfuConfig, boolean z) {
        if (!super.startOtaProcedure(dfuConfig, z)) {
            return false;
        }
        e(513);
        GlobalGatt globalGatt = this.Le;
        if (globalGatt != null) {
            globalGatt.unRegisterCallback(this.Ae, this.cf);
        }
        boolean zA = this.Ie.a(dfuConfig);
        if (!zA) {
            e(2048);
        }
        return zA;
    }
}