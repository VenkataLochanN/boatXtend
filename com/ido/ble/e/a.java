package com.ido.ble.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.e.e;
import com.ido.ble.logs.LogTool;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f4363g = "HIDConnectManager";
    private static a i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4365a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BluetoothDevice f4366b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d f4367c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final BroadcastReceiver f4368d = new C0076a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BluetoothProfile.ServiceListener f4369e = new b();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final UUID f4362f = UUID.fromString("00001812-0000-1000-8000-00805f9b34fb");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static Handler f4364h = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: com.ido.ble.e.a$a, reason: collision with other inner class name */
    class C0076a extends BroadcastReceiver {
        C0076a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String action = intent.getAction();
            LogTool.d(a.f4363g, "onReceive mac is " + bluetoothDevice.getAddress() + ";action=" + action);
            if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                str = "onReceive ACTION_ACL_CONNECTED ";
            } else {
                if (!action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
                        LogTool.d(a.f4363g, "onReceive bondState is " + intExtra);
                        if (intExtra == 12) {
                            a.this.e();
                            LogTool.d(a.f4363g, "createBond success, mac is" + bluetoothDevice.getAddress());
                            if (bluetoothDevice.getAddress().equals(a.this.f4365a)) {
                                if (a.this.f4367c != null) {
                                    a.this.f4367c.a(bluetoothDevice.getAddress());
                                    a.this.f4367c = null;
                                }
                                a.this.a(bluetoothDevice);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                str = "onReceive ACTION_ACL_DISCONNECTED ";
            }
            LogTool.d(a.f4363g, str);
        }
    }

    class b implements BluetoothProfile.ServiceListener {
        b() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            LogTool.d(a.f4363g, "[onServiceConnected] profile=" + i);
            int iB = a.b();
            LogTool.d(a.f4363g, "[onServiceConnected] getInputDeviceHiddenConstant=" + iB);
            if (i == iB) {
                try {
                    if (a.this.f4366b != null) {
                        LogTool.d(a.f4363g, "[onServiceConnected] getMethod(\"connect\")=" + iB);
                        bluetoothProfile.getClass().getMethod("connect", BluetoothDevice.class).invoke(bluetoothProfile, a.this.f4366b);
                    }
                } catch (Exception e2) {
                    LogTool.b(a.f4363g, "[onServiceConnected] " + e2.getMessage());
                }
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            LogTool.d(a.f4363g, "[onServiceDisconnected] profile=" + i);
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothDevice f4372a;

        c(BluetoothDevice bluetoothDevice) {
            this.f4372a = bluetoothDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.d(a.f4363g, "[startBondTimeOut] after create bond.not receive broadcast");
            a aVar = a.this;
            BluetoothDevice bluetoothDeviceC = aVar.c(aVar.f4365a);
            if (bluetoothDeviceC == null) {
                LogTool.d(a.f4363g, "[startBondTimeOut] retry createBond.");
                this.f4372a.createBond();
            } else {
                LogTool.d(a.f4363g, "[startBondTimeOut] check bonded status is true");
                a.this.a(bluetoothDeviceC);
            }
        }
    }

    public interface d {
        void a(String str);
    }

    private a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(f4363g, "[connectIfBonded] mac is " + bluetoothDevice.getAddress());
        this.f4366b = bluetoothDevice;
        try {
            BluetoothAdapter.getDefaultAdapter().getProfileProxy(com.ido.ble.b.b(), this.f4369e, b());
        } catch (Exception e2) {
            LogTool.b(f4363g, "[connectIfBonded] " + e2.getMessage());
        }
    }

    public static boolean a(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt.getService(f4362f) == null) {
            return false;
        }
        if (!e.c(bluetoothGatt.getDevice().getAddress())) {
            return true;
        }
        LogTool.d(f4363g, "[isNeedCreateBond] has paired. mac is " + bluetoothGatt.getDevice().getAddress());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b() {
        Field[] fields = BluetoothProfile.class.getFields();
        int length = fields.length;
        for (int i2 = 0; i2 < length; i2++) {
            Field field = fields[i2];
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers) && Modifier.isFinal(modifiers)) {
                try {
                    if (field.getName().equals("INPUT_DEVICE")) {
                        return field.getInt(null);
                    }
                    continue;
                } catch (Exception e2) {
                    LogTool.b(f4363g, "[getInputDeviceHiddenConstant] " + e2.getMessage());
                }
            }
        }
        return -1;
    }

    private void b(BluetoothDevice bluetoothDevice) {
        f4364h.postDelayed(new c(bluetoothDevice), DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
    }

    private void b(String str) {
        List<BluetoothDevice> connectedDevices = ((BluetoothManager) com.ido.ble.common.e.a().getSystemService("bluetooth")).getConnectedDevices(7);
        if (connectedDevices == null || connectedDevices.size() == 0) {
            LogTool.d(f4363g, "[createBond] deviceList is null");
            return;
        }
        for (BluetoothDevice bluetoothDevice : connectedDevices) {
            if (str.equals(bluetoothDevice.getAddress())) {
                LogTool.d(f4363g, "[createBond] to create bond. result = " + bluetoothDevice.createBond());
                b(bluetoothDevice);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothDevice c(String str) {
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (bluetoothDevice.getAddress().equals(str)) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    public static a c() {
        if (i == null) {
            i = new a();
            i.d();
        }
        return i;
    }

    private void d() {
        LogTool.d(f4363g, "init ");
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        com.ido.ble.b.b().registerReceiver(this.f4368d, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        f4364h.removeCallbacksAndMessages(null);
    }

    public void a(BluetoothDevice bluetoothDevice, d dVar) {
        LogTool.d(f4363g, "[connect1] start, address=" + bluetoothDevice.getAddress());
        this.f4365a = bluetoothDevice.getAddress();
        this.f4367c = dVar;
        LogTool.d(f4363g, "[connect1] create bond.");
        bluetoothDevice.createBond();
    }

    public void a(String str) {
        LogTool.d(f4363g, "[connect] start, address=" + str);
        this.f4365a = str;
        BluetoothDevice bluetoothDeviceC = c(str);
        if (bluetoothDeviceC != null) {
            LogTool.d(f4363g, "[connect] has bonded.");
            a(bluetoothDeviceC);
        } else {
            LogTool.d(f4363g, "[connect] not bonded.");
            b(str);
        }
    }
}