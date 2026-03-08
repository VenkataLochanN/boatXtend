package com.realsil.sdk.core.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.impl.BluetoothAdapterImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class RtkBluetoothManager {
    public static final int INDICATOR_ACL = 2;
    public static final int INDICATOR_BOND = 4;
    public static final int INDICATOR_BT = 1;
    public static final int INDICATOR_FULL = 255;
    public static RtkBluetoothManager mInstance;
    public BluetoothManager A;
    public BluetoothAdapter B;
    public String ga;
    public Context mContext;
    public Handler mHandler;
    public boolean u;
    public List<RtkBluetoothManagerCallback> v;
    public BluetoothBroadcastReceiver ba = null;
    public Object ca = new Object();
    public Object da = new Object();
    public boolean ea = false;
    public int K = 255;
    public boolean fa = false;
    public Runnable ha = new Runnable() { // from class: com.realsil.sdk.core.bluetooth.RtkBluetoothManager.1
        @Override // java.lang.Runnable
        public void run() {
            ZLogger.d("scan delay time reached");
            RtkBluetoothManager.this.d();
        }
    };

    public class BluetoothBroadcastReceiver extends BroadcastReceiver {
        public BluetoothBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                java.lang.String r6 = r7.getAction()
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = ">>"
                r0.append(r1)
                r0.append(r6)
                java.lang.String r0 = r0.toString()
                com.realsil.sdk.core.logger.ZLogger.d(r0)
                int r0 = r6.hashCode()
                r1 = 4
                r2 = 3
                r3 = 2
                r4 = 1
                switch(r0) {
                    case -1530327060: goto L4c;
                    case -223687943: goto L42;
                    case 1546533238: goto L38;
                    case 1652078734: goto L2e;
                    case 2116862345: goto L24;
                    default: goto L23;
                }
            L23:
                goto L56
            L24:
                java.lang.String r0 = "android.bluetooth.device.action.BOND_STATE_CHANGED"
                boolean r0 = r6.equals(r0)
                if (r0 == 0) goto L56
                r0 = r1
                goto L57
            L2e:
                java.lang.String r0 = "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED"
                boolean r0 = r6.equals(r0)
                if (r0 == 0) goto L56
                r0 = r3
                goto L57
            L38:
                java.lang.String r0 = "android.bluetooth.adapter.action.BLE_ACL_CONNECTED"
                boolean r0 = r6.equals(r0)
                if (r0 == 0) goto L56
                r0 = r4
                goto L57
            L42:
                java.lang.String r0 = "android.bluetooth.device.action.PAIRING_REQUEST"
                boolean r0 = r6.equals(r0)
                if (r0 == 0) goto L56
                r0 = r2
                goto L57
            L4c:
                java.lang.String r0 = "android.bluetooth.adapter.action.STATE_CHANGED"
                boolean r0 = r6.equals(r0)
                if (r0 == 0) goto L56
                r0 = 0
                goto L57
            L56:
                r0 = -1
            L57:
                if (r0 == 0) goto L8e
                if (r0 == r4) goto L88
                if (r0 == r3) goto L82
                if (r0 == r2) goto L7c
                if (r0 == r1) goto L76
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r0 = "action:"
                r7.append(r0)
                r7.append(r6)
                java.lang.String r6 = r7.toString()
                com.realsil.sdk.core.logger.ZLogger.d(r6)
                goto L93
            L76:
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager r6 = com.realsil.sdk.core.bluetooth.RtkBluetoothManager.this
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager.e(r6, r7)
                goto L93
            L7c:
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager r6 = com.realsil.sdk.core.bluetooth.RtkBluetoothManager.this
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager.d(r6, r7)
                goto L93
            L82:
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager r6 = com.realsil.sdk.core.bluetooth.RtkBluetoothManager.this
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager.c(r6, r7)
                goto L93
            L88:
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager r6 = com.realsil.sdk.core.bluetooth.RtkBluetoothManager.this
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager.b(r6, r7)
                goto L93
            L8e:
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager r6 = com.realsil.sdk.core.bluetooth.RtkBluetoothManager.this
                com.realsil.sdk.core.bluetooth.RtkBluetoothManager.a(r6, r7)
            L93:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.RtkBluetoothManager.BluetoothBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public RtkBluetoothManager(Context context) {
        this.u = false;
        this.mContext = context.getApplicationContext();
        this.u = RtkCore.DEBUG;
        initialize();
    }

    public static RtkBluetoothManager getInstance() {
        return mInstance;
    }

    public static void initial(Context context) {
        if (mInstance == null) {
            synchronized (RtkBluetoothManager.class) {
                if (mInstance == null) {
                    mInstance = new RtkBluetoothManager(context);
                }
            }
        }
    }

    public void addManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        if (this.v == null) {
            this.v = new CopyOnWriteArrayList();
        }
        if (this.v.contains(rtkBluetoothManagerCallback)) {
            return;
        }
        this.v.add(rtkBluetoothManagerCallback);
    }

    public final void b(Intent intent) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (this.u) {
            BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
            if (bluetoothClass != null) {
                ZLogger.d(String.format(Locale.US, "(0x%04X, major=(0x%04X)", Integer.valueOf(bluetoothClass.getDeviceClass()), Integer.valueOf(bluetoothClass.getMajorDeviceClass())));
            }
            ZLogger.d(String.format(Locale.US, "[%s(%s)", bluetoothDevice.getAddress(), bluetoothDevice.getName()));
        }
        List<RtkBluetoothManagerCallback> list = this.v;
        if (list != null) {
            Iterator<RtkBluetoothManagerCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onAclConnectionStateChanged(bluetoothDevice, true);
            }
        }
    }

    public final void c() {
        if (this.B == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return;
        }
        this.ba = new BluetoothBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        if ((this.K & 1) == 1) {
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        }
        if ((this.K & 2) == 2) {
            intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_CONNECTED);
            intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_DISCONNECTED);
        }
        if ((this.K & 4) == 4) {
            intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        }
        this.mContext.registerReceiver(this.ba, intentFilter);
    }

    public final void c(Intent intent) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (this.u) {
            BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
            if (bluetoothClass != null) {
                ZLogger.d(String.format(Locale.US, "(0x%04X, major=(0x%04X)", Integer.valueOf(bluetoothClass.getDeviceClass()), Integer.valueOf(bluetoothClass.getMajorDeviceClass())));
            }
            ZLogger.d(String.format(Locale.US, "[%s(%s)", bluetoothDevice.getAddress(), bluetoothDevice.getName()));
        }
        List<RtkBluetoothManagerCallback> list = this.v;
        if (list != null) {
            Iterator<RtkBluetoothManagerCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onAclConnectionStateChanged(bluetoothDevice, false);
            }
        }
    }

    public void close() {
        ZLogger.v("close()");
        Context context = this.mContext;
        if (context != null) {
            try {
                context.unregisterReceiver(this.ba);
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        }
    }

    public boolean createBond(String str) {
        boolean z;
        String str2;
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            z = this.u;
            str2 = "bluetooth is not enabled";
        } else {
            if (str != null) {
                ZLogger.d(this.u, "createBondMac=" + str);
                BluetoothDevice remoteDevice = this.B.getRemoteDevice(str);
                int bondState = remoteDevice.getBondState();
                if (bondState == 12) {
                    ZLogger.d("device already bonded: " + bondState);
                    return true;
                }
                ZLogger.d(this.u, "attempt to createBond, state=" + Integer.toString(bondState));
                return BluetoothDeviceImpl.createBond(remoteDevice);
            }
            z = this.u;
            str2 = "mac cannot be null";
        }
        ZLogger.d(z, str2);
        return false;
    }

    public boolean createBond(byte[] bArr) {
        return createBond(BluetoothHelper.convertMac(bArr));
    }

    public final void d(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
        int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra3 = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
        if (bluetoothDevice == null) {
            return;
        }
        if (this.u) {
            BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
            if (bluetoothClass != null) {
                ZLogger.d(String.format(Locale.US, "(0x%04X, major=(0x%04X)", Integer.valueOf(bluetoothClass.getDeviceClass()), Integer.valueOf(bluetoothClass.getMajorDeviceClass())));
            }
            ZLogger.d(String.format(Locale.US, "[%s(%s)/ %d -> %d", bluetoothDevice.getAddress(), bluetoothDevice.getName(), Integer.valueOf(intExtra2), Integer.valueOf(intExtra)));
        }
        List<RtkBluetoothManagerCallback> list = this.v;
        if (list != null) {
            Iterator<RtkBluetoothManagerCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onBondStateChanged(bluetoothDevice, intExtra3);
            }
        }
    }

    public final boolean d() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.ha);
        }
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not supported or disabled");
        }
        if (!this.B.isDiscovering()) {
            return true;
        }
        ZLogger.d(this.u, "stopInquiry()");
        return this.B.cancelDiscovery();
    }

    public boolean disableBT() {
        String str;
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null) {
            str = "BT is not initialized..!";
        } else {
            if (bluetoothAdapter.getState() == 10) {
                ZLogger.w("BT already OFF");
                return true;
            }
            boolean zDisable = this.B.disable();
            ZLogger.v(this.u, "disable BT " + zDisable);
            if (zDisable) {
                synchronized (this.da) {
                    try {
                        ZLogger.d(this.u, "wait BT disable...");
                        this.da.wait(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        ZLogger.e(e2.toString());
                    }
                }
            }
            if (!this.B.isEnabled()) {
                ZLogger.d(this.u, "BT disable success");
                return true;
            }
            str = "BT disable failed";
        }
        ZLogger.w(str);
        return false;
    }

    public final void e(Intent intent) {
        boolean z;
        String str;
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
        int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
        if (this.u) {
            ZLogger.d(String.format(Locale.US, " %d -> %d", Integer.valueOf(intExtra2), Integer.valueOf(intExtra)));
        }
        switch (intExtra) {
            case 10:
                ZLogger.d(this.u, "ACTION_STATE_CHANGED: STATE_OFF");
                synchronized (this.da) {
                    this.da.notifyAll();
                    break;
                }
                break;
            case 11:
                z = this.u;
                str = "ACTION_STATE_CHANGED: STATE_TURNING_ON";
                ZLogger.d(z, str);
                break;
            case 12:
                ZLogger.d(this.u, "ACTION_STATE_CHANGED: STATE_ON");
                synchronized (this.ca) {
                    this.ca.notifyAll();
                    break;
                }
                break;
            case 13:
                z = this.u;
                str = "ACTION_STATE_CHANGED: STATE_TURNING_OFF";
                ZLogger.d(z, str);
                break;
            default:
                z = this.u;
                str = "ACTION_STATE_CHANGED: " + intExtra;
                ZLogger.d(z, str);
                break;
        }
        List<RtkBluetoothManagerCallback> list = this.v;
        if (list != null) {
            Iterator<RtkBluetoothManagerCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onBluetoothStateChaned(null, intExtra);
            }
        }
    }

    public boolean enableBT() {
        String str;
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null) {
            str = "mBluetoothAdapter == null";
        } else {
            if (bluetoothAdapter.getState() == 12) {
                ZLogger.w(this.u, "BT already on");
                return true;
            }
            this.fa = false;
            ZLogger.v(this.u, "isNeedAutoEnableBt=" + this.fa);
            boolean zEnable = this.B.enable();
            ZLogger.v(this.u, "enable BT " + zEnable);
            if (zEnable) {
                synchronized (this.ca) {
                    try {
                        ZLogger.d(this.u, "wait BT enable...");
                        this.ca.wait(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        ZLogger.e(e2.toString());
                    }
                }
            }
            if (this.B.isEnabled()) {
                ZLogger.d(this.u, "BT enable success");
                return true;
            }
            str = "BT enable fail";
        }
        ZLogger.w(str);
        return false;
    }

    public final void f(Intent intent) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (this.u) {
            BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
            if (bluetoothClass != null) {
                ZLogger.d(String.format(Locale.US, "(0x%04X, major=(0x%04X)", Integer.valueOf(bluetoothClass.getDeviceClass()), Integer.valueOf(bluetoothClass.getMajorDeviceClass())));
            }
            ZLogger.d(String.format(Locale.US, "[%s(%s)", bluetoothDevice.getAddress(), bluetoothDevice.getName()));
        }
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
        ZLogger.v("android.bluetooth.device.extra.PAIRING_VARIANT>> " + BluetoothDeviceImpl.pairingVariantToString(intExtra) + " (" + intExtra + ")");
        if (bluetoothDevice.getBondState() == 12) {
            ZLogger.i("device already bonded: " + bluetoothDevice.getAddress());
        }
    }

    public final boolean initialize() {
        BluetoothAdapter defaultAdapter;
        String str;
        Context context = this.mContext;
        if (context != null) {
            if (this.B == null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.A = (BluetoothManager) context.getSystemService("bluetooth");
                    BluetoothManager bluetoothManager = this.A;
                    if (bluetoothManager == null) {
                        str = "Unable to initialize BluetoothManager.";
                    } else {
                        defaultAdapter = bluetoothManager.getAdapter();
                    }
                } else {
                    defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                }
                this.B = defaultAdapter;
                if (this.B == null) {
                    str = "Unable to obtain a BluetoothAdapter.";
                }
            }
            c();
            return true;
        }
        str = "not intialized";
        ZLogger.w(str);
        return false;
    }

    public boolean isBleEnabled() {
        BluetoothManager bluetoothManager = this.A;
        BluetoothAdapter adapter = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        return adapter != null && adapter.isEnabled();
    }

    public boolean isBleSupported() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public boolean pair(byte[] bArr) {
        if (this.B == null) {
            return false;
        }
        byte[] bArr2 = {bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]};
        ZLogger.d(this.u, "createBondMac=" + BluetoothHelper.convertMac(bArr));
        BluetoothDevice remoteDevice = this.B.getRemoteDevice(bArr2);
        int bondState = remoteDevice.getBondState();
        ZLogger.d(this.u, "attempt to createBond, state=" + Integer.toString(bondState));
        return remoteDevice.createBond();
    }

    public void removeManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        List<RtkBluetoothManagerCallback> list = this.v;
        if (list != null) {
            list.remove(rtkBluetoothManagerCallback);
        }
    }

    public boolean reset() {
        d();
        unBondAllDevices();
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not initialized");
            return false;
        }
        if (!bluetoothAdapter.isEnabled()) {
            return enableBT();
        }
        this.fa = true;
        ZLogger.v(this.u, "isNeedAutoEnableBt=" + this.fa);
        disableBT();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        if (this.B.isEnabled()) {
            ZLogger.d(this.u, "BT already enabled");
            return true;
        }
        boolean zEnableBT = enableBT();
        ZLogger.w(this.u, "enableBT: " + zEnableBT);
        return zEnableBT;
    }

    public void setInterruptPairRequest(boolean z) {
        this.ea = z;
    }

    public boolean setScanMode(int i, int i2) {
        if (i == 20 || i == 21) {
            ZLogger.d("SCAN_MODE_NONE or SCAN_MODE_CONNECTABLE");
            return BluetoothAdapterImpl.setScanMode(this.B, i, 0);
        }
        if (i != 23) {
            return true;
        }
        ZLogger.d("SCAN_MODE_CONNECTABLE_DISCOVERABLE");
        return BluetoothAdapterImpl.setScanMode(this.B, i, i2);
    }

    public boolean startDiscovery(int i) {
        return startDiscovery(i, null);
    }

    public boolean startDiscovery(int i, String str) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.ha);
        }
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not supported or disabled");
            return false;
        }
        if (this.B.isDiscovering()) {
            this.B.cancelDiscovery();
        }
        ZLogger.d(this.u, "address=" + str + " , timeout=" + i);
        this.ga = str;
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.postDelayed(this.ha, i * 1000);
        }
        this.B.startDiscovery();
        return true;
    }

    public boolean unBondAllDevices() {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not enabled");
            return false;
        }
        Set<BluetoothDevice> bondedDevices = this.B.getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            ZLogger.d("no bond device exist");
            return true;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            while (true) {
                int bondState = bluetoothDevice.getBondState();
                if (bondState == 10) {
                    ZLogger.d("already unbond: " + bluetoothDevice.getName());
                    break;
                }
                if (bondState == 11) {
                    boolean zCancelBondProcess = BluetoothDeviceImpl.cancelBondProcess(bluetoothDevice);
                    ZLogger.v(this.u, String.format(Locale.US, "cancelBondProcess: %s/%s, ret=%b", bluetoothDevice.getName(), bluetoothDevice.getAddress(), Boolean.valueOf(zCancelBondProcess)));
                    if (zCancelBondProcess) {
                        break;
                    }
                } else if (bondState == 12) {
                    boolean zRemoveBond = BluetoothDeviceImpl.removeBond(bluetoothDevice);
                    ZLogger.v(this.u, String.format(Locale.US, "removeBond: %s/%s, ret=%b", bluetoothDevice.getName(), bluetoothDevice.getAddress(), Boolean.valueOf(zRemoveBond)));
                    if (zRemoveBond) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return true;
    }

    public boolean unBondDevice(byte[] bArr) {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.d(this.u, "bluetooth is not enabled");
            return false;
        }
        String strConvertMac = BluetoothHelper.convertMac(bArr);
        ZLogger.d(this.u, "unBondDevice: " + strConvertMac);
        Set<BluetoothDevice> bondedDevices = this.B.getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            return true;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            ZLogger.d(this.u, "unBondDevice(): before " + bluetoothDevice.getName() + " mac= " + bluetoothDevice.getAddress());
            if (strConvertMac.compareToIgnoreCase(bluetoothDevice.getAddress()) == 0) {
                while (true) {
                    int bondState = bluetoothDevice.getBondState();
                    if (bondState == 10) {
                        ZLogger.d("removeBond finished");
                        return true;
                    }
                    if (bondState == 11) {
                        BluetoothDeviceImpl.cancelBondProcess(bluetoothDevice);
                    }
                    BluetoothDeviceImpl.removeBond(bluetoothDevice);
                }
            }
        }
        return true;
    }
}