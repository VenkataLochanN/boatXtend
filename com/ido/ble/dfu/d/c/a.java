package com.ido.ble.dfu.d.c;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.ActivityCompat;
import com.ido.ble.bluetooth.DeviceConnectService;
import com.ido.ble.common.e;
import com.ido.ble.common.m;
import com.ido.ble.logs.LogTool;
import com.ido.life.util.DateUtil;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f4281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4282b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BroadcastReceiver f4283c = new C0068a();

    /* JADX INFO: renamed from: com.ido.ble.dfu.d.c.a$a, reason: collision with other inner class name */
    class C0068a extends BroadcastReceiver {
        C0068a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                if (intExtra == 10) {
                    LogTool.d(com.ido.ble.dfu.a.f4245a, "[ReOpenPhoneBluetoothSwitchTask] off");
                    a.this.b();
                } else if (intExtra == 12) {
                    LogTool.d(com.ido.ble.dfu.a.f4245a, "[ReOpenPhoneBluetoothSwitchTask] on");
                    a.this.c();
                }
            }
        }
    }

    class b implements m.b {
        b() {
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[ReOpenPhoneBluetoothSwitchTask] task time out.");
            a.this.c();
        }
    }

    class c implements m.b {
        c() {
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[ReOpenPhoneBluetoothSwitchTask] start enable....");
            BluetoothAdapter.getDefaultAdapter().enable();
        }
    }

    public interface d {
        void a();
    }

    private boolean a() {
        return ActivityCompat.checkSelfPermission(e.a(), "android.permission.BLUETOOTH_ADMIN") == 0 && ActivityCompat.checkSelfPermission(e.a(), "android.permission.BLUETOOTH") == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        m.a(new c(), BootloaderScanner.TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[ReOpenPhoneBluetoothSwitchTask] finished");
        m.a(this.f4282b);
        e.a().unregisterReceiver(this.f4283c);
        e.a().startService(new Intent(e.a(), (Class<?>) DeviceConnectService.class));
        this.f4281a.a();
    }

    private void d() {
        if (!a()) {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[ReOpenPhoneBluetoothSwitchTask] no permission.");
            c();
        } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            BluetoothAdapter.getDefaultAdapter().disable();
        } else {
            BluetoothAdapter.getDefaultAdapter().enable();
        }
    }

    public void a(d dVar) {
        this.f4281a = dVar;
        e.a().stopService(new Intent(e.a(), (Class<?>) DeviceConnectService.class));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        e.a().registerReceiver(this.f4283c, intentFilter);
        this.f4282b = m.a(new b(), DateUtil.MINUTE);
        d();
    }
}