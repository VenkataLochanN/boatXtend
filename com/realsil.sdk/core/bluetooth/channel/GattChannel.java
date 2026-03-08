package com.realsil.sdk.core.bluetooth.channel;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.os.Build;
import com.realsil.sdk.core.bluetooth.BluetoothHelper;
import com.realsil.sdk.core.bluetooth.GattError;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class GattChannel extends Channel {
    public static final int STATE_CHARACTERISTIC_NOT_FOUND = 518;
    public static final int STATE_CONFIGURE_OK = 517;
    public static final int STATE_DISCOVER_SERVICE_FAILED = 519;
    public static final int STATE_DISCOVER_SERVICE_PROCESSING = 515;
    public static final int STATE_ENABLE_CCCD_PROCESSING = 516;
    public static final int STATE_REQUEST_MTU_PROCESSING = 514;
    public static final int STATE_SET_PHY_PROCESSING = 513;
    public BluetoothGatt ma;
    public final Object na;
    public volatile boolean oa;
    public volatile boolean pa;
    public boolean qa;
    public Map<UUID, BluetoothGattCharacteristic> ra;
    public List<UUID> sa;
    public final BluetoothGattCallback ta;
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString(GlobalGatt.CLIENT_CHARACTERISTIC_CONFIG);
    public static int ja = 400;
    public static int ka = 20;
    public static int la = ka;

    public GattChannel(Context context, IChannelCallback iChannelCallback) {
        super(context, iChannelCallback);
        this.na = new Object();
        this.oa = true;
        this.pa = false;
        this.ra = new HashMap();
        this.sa = new ArrayList();
        this.ta = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.bluetooth.channel.GattChannel.1
            public final void a() {
                GattChannel.this.a(515);
                ZLogger.i("Attempting to start service discovery:");
                if (GattChannel.this.ma.discoverServices()) {
                    return;
                }
                GattChannel.this.a(519);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                if (bluetoothGattCharacteristic.getValue() != null) {
                    bluetoothGattCharacteristic.getUuid();
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                bluetoothGattCharacteristic.getValue();
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
                ZLogger.i(false, String.format(Locale.US, "%d-%s >> %d", Integer.valueOf(i), GattError.parseConnectionError(i), Integer.valueOf(i2)));
                if (i2 != 2) {
                    GattChannel.this.a(0);
                    if (i2 == 0) {
                        if (GattChannel.this.ma != null) {
                            BluetoothGattImpl.refreshDeviceCache(GattChannel.this.ma, true);
                            GattChannel.this.ma.close();
                            GattChannel.this.ma = null;
                        }
                        ZLogger.d("Disconnected from GATT server.");
                        return;
                    }
                    return;
                }
                ZLogger.d("Connected to GATT server.");
                if (Build.VERSION.SDK_INT >= 26) {
                    GattChannel.this.a(513);
                    ZLogger.d("setPreferredPhy ...");
                    GattChannel.this.ma.setPreferredPhy(2, 2, 0);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    ZLogger.w("PHY not supported");
                }
                a();
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                UUID uuid = bluetoothGattDescriptor.getCharacteristic().getUuid();
                if (i != 0 || GattChannel.this.sa == null || GattChannel.this.sa.size() <= 0) {
                    return;
                }
                GattChannel.this.sa.remove(uuid);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                GattChannel.this.f();
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
                ZLogger.d(String.format(Locale.US, ">> mtu=%d, status=%d", Integer.valueOf(i), Integer.valueOf(i2)));
                if (GattChannel.this.U >= 515) {
                    ZLogger.d("ignore mtu response");
                    return;
                }
                if (i2 != 0 || i <= 3) {
                    GattChannel.la = GattChannel.ka;
                } else {
                    GattChannel.la = i - 3;
                }
                GattChannel.this.f();
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
                super.onPhyRead(bluetoothGatt, i, i2, i3);
                ZLogger.d(String.format(Locale.US, "txPhy=%d, rxPhy=%d,status=%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
                super.onPhyUpdate(bluetoothGatt, i, i2, i3);
                ZLogger.d(String.format(Locale.US, "txPhy=%d, rxPhy=%d,status=%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
                ZLogger.i(false, String.format(Locale.US, "%d-%s", Integer.valueOf(i), GattError.parseConnectionError(i)));
                if (i == 0) {
                    GattChannel.this.a(bluetoothGatt);
                } else {
                    GattChannel.this.a(518);
                }
            }
        };
    }

    public final void a(BluetoothGatt bluetoothGatt) {
        e();
    }

    public final boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        String str;
        boolean z2 = false;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null) {
            ZLogger.w("gatt == null || characteristic == null");
            return false;
        }
        int properties = bluetoothGattCharacteristic.getProperties();
        if ((properties & 16) == 0) {
            ZLogger.d("check properties failed: " + properties);
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
        if (descriptor == null) {
            ZLogger.w("can not find descriptor: " + CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
            return false;
        }
        if (descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] > 0 && descriptor.getValue()[1] == 0) {
            z2 = true;
        }
        ZLogger.d("current cccd state: " + z2);
        if (z && z2) {
            str = "cccd already enabled";
        } else {
            if (z || z2) {
                descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                return a(bluetoothGatt, descriptor);
            }
            str = "cccd already disable";
        }
        ZLogger.w(str);
        return true;
    }

    public final boolean a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (bluetoothGatt == null || bluetoothGattDescriptor == null) {
            ZLogger.d("gatt == null || descriptor == null");
            return false;
        }
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        int writeType = characteristic.getWriteType();
        characteristic.setWriteType(2);
        boolean zWriteDescriptor = bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
        characteristic.setWriteType(writeType);
        return zWriteDescriptor;
    }

    public void connectBondedDevice(BluetoothDevice bluetoothDevice) {
        if (this.qa && getConnectionState() != 256 && isConnected()) {
            ZLogger.d("create a direct connect to " + this.ia.getName() + ", addr=" + this.ia.getAddress());
            a(256);
            this.ma = bluetoothDevice.connectGatt(this.mContext, false, this.ta);
        }
    }

    public boolean connectDevice(BluetoothDevice bluetoothDevice) {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w(false, "BluetoothAdapter not initialized ");
            return false;
        }
        this.ia = bluetoothDevice;
        a(256);
        if (bluetoothDevice.getBondState() == 10 && this.qa) {
            ZLogger.v("createBond before connect");
            ZLogger.d("createBond()=" + BluetoothDeviceImpl.createBond(bluetoothDevice));
            return true;
        }
        ZLogger.v("device already bonded");
        ZLogger.v("create a new connect to " + bluetoothDevice.getName() + ", addr=" + bluetoothDevice.getAddress());
        this.ma = Build.VERSION.SDK_INT >= 23 ? bluetoothDevice.connectGatt(this.mContext, false, this.ta, 2) : bluetoothDevice.connectGatt(this.mContext, false, this.ta);
        return true;
    }

    public void disconnect() {
        String str;
        BluetoothDevice bluetoothDevice = this.ia;
        if (bluetoothDevice != null) {
            int connectionState = this.A.getConnectionState(bluetoothDevice, 7);
            ZLogger.d("cur_state = " + connectionState + " : " + BluetoothHelper.parseProfileState(connectionState));
            if (2 != connectionState) {
                if (connectionState != 0) {
                    if (1 == connectionState) {
                        a(256);
                        return;
                    }
                    return;
                }
                a(0);
            }
            if (this.ma != null) {
                ZLogger.d("Trying to disconnect the gatt server.");
                this.ma.disconnect();
                return;
            }
            str = "bluetoothGatt is null ";
        } else {
            str = "no device to disconnect";
        }
        ZLogger.w(str);
        a(0);
    }

    public final void e() {
        this.ra.clear();
        this.sa.clear();
    }

    public final void f() {
        if (this.sa.size() <= 0) {
            ZLogger.d("no more notification to send");
            a(517);
            return;
        }
        a(516);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.ra.get(this.sa.get(0));
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        ZLogger.d("attempt to enable " + uuid);
        boolean zA = a(this.ma, bluetoothGattCharacteristic, true);
        ZLogger.d("writeDescriptor: " + uuid + ", result=" + zA);
        if (zA) {
            return;
        }
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        f();
    }

    public BluetoothGatt getBluetoothGatt() {
        return this.ma;
    }

    public boolean isConnected() {
        return (this.U & 517) == 517;
    }

    public void release() {
        if (this.ma != null) {
            ZLogger.v("Trying to close the gatt.");
            this.ma.close();
            this.ma = null;
        }
    }
}