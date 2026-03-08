package com.realsil.sdk.dfu;

import a.a;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.realsil.sdk.core.bluetooth.BluetoothProfileCallback;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.core.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.Throughput;
import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class DfuService extends Service {
    public IBinder jc;
    public d.a nc;
    public e.a oc;
    public BluetoothManager pc;
    public BluetoothAdapter qc;
    public BluetoothProfileManager rc;
    public Throughput uc;
    public String kc = "";
    public RemoteCallbackList<a.b> mCallbacks = new RemoteCallbackList<>();
    public HashMap<String, a.b> lc = new HashMap<>();
    public int mc = 0;
    public boolean sc = false;
    public int tc = 257;
    public Handler vc = new b(Looper.getMainLooper());
    public BluetoothProfileCallback wc = new c();
    public DfuThreadCallback xc = new d();

    private class a extends a.AbstractBinderC0000a implements IBinder {
        public DfuService mService;

        public a(DfuService dfuService) {
            this.mService = dfuService;
        }

        @Override // a.a
        public boolean a(String str, a.b bVar) {
            if (bVar == null) {
                return false;
            }
            ZLogger.v("registerCallback: " + str);
            DfuService.this.mCallbacks.register(bVar);
            DfuService.this.lc.put(str, bVar);
            return DfuService.this.lc.get(str) != null;
        }

        @Override // a.a
        public boolean abort() {
            DfuService service = getService();
            return service != null && service.abort();
        }

        @Override // a.a
        public boolean activeImage(boolean z) {
            DfuService service = getService();
            return service != null && service.activeImage(z);
        }

        @Override // a.a
        public void b(String str, a.b bVar) {
            if (bVar != null) {
                DfuService.this.mCallbacks.unregister(bVar);
                DfuService.this.lc.remove(str);
            }
        }

        @Override // android.os.Binder, android.os.IBinder
        public void dump(FileDescriptor fileDescriptor, String[] strArr) {
        }

        @Override // android.os.Binder, android.os.IBinder
        public void dumpAsync(FileDescriptor fileDescriptor, String[] strArr) {
        }

        @Override // a.a
        public int getCurrentOtaState() {
            return DfuService.this.tc;
        }

        @Override // android.os.Binder, android.os.IBinder
        public String getInterfaceDescriptor() {
            return null;
        }

        public final DfuService getService() {
            DfuService dfuService = this.mService;
            if (dfuService != null) {
                return dfuService;
            }
            return null;
        }

        @Override // android.os.Binder, android.os.IBinder
        public boolean isBinderAlive() {
            return false;
        }

        @Override // android.os.Binder, android.os.IBinder
        public void linkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
        }

        @Override // android.os.Binder, android.os.IBinder
        public boolean pingBinder() {
            return false;
        }

        @Override // android.os.Binder, android.os.IBinder
        public IInterface queryLocalInterface(String str) {
            return null;
        }

        @Override // a.a
        public Throughput r() {
            return DfuService.this.uc;
        }

        @Override // a.a
        public boolean start(String str, DfuConfig dfuConfig) {
            DfuService service = getService();
            return service != null && service.start(str, dfuConfig);
        }

        @Override // android.os.Binder, android.os.IBinder
        public boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
            return false;
        }
    }

    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x000f A[PHI: r1
  0x000f: PHI (r1v1 int) = (r1v0 int), (r1v2 int), (r1v3 int), (r1v4 int) binds: [B:3:0x0003, B:5:0x0006, B:7:0x0009, B:9:0x000c] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r4) {
            /*
                r3 = this;
                int r0 = r4.what
                r1 = 1
                if (r0 == r1) goto Lf
                r1 = 2
                if (r0 == r1) goto Lf
                r1 = 3
                if (r0 == r1) goto Lf
                r1 = 4
                if (r0 == r1) goto Lf
                goto L16
            Lf:
                com.realsil.sdk.dfu.DfuService r0 = com.realsil.sdk.dfu.DfuService.this
                java.lang.Object r2 = r4.obj
                com.realsil.sdk.dfu.DfuService.a(r0, r1, r2)
            L16:
                super.handleMessage(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.DfuService.b.handleMessage(android.os.Message):void");
        }
    }

    public class c extends BluetoothProfileCallback {
        public c() {
        }

        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onHidStateChanged(BluetoothDevice bluetoothDevice, int i) {
            c.b bVar;
            super.onHidStateChanged(bluetoothDevice, i);
            if (DfuService.this.mc == 0) {
                if (DfuService.this.nc == null) {
                    return;
                } else {
                    bVar = DfuService.this.nc;
                }
            } else if (DfuService.this.oc == null) {
                return;
            } else {
                bVar = DfuService.this.oc;
            }
            bVar.a(bluetoothDevice, i);
        }
    }

    public class d extends DfuThreadCallback {
        public d() {
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onDeviceInfoChanged(OtaDeviceInfo otaDeviceInfo) {
            super.onDeviceInfoChanged(otaDeviceInfo);
            if (DfuService.this.vc != null) {
                DfuService.this.vc.sendMessage(DfuService.this.vc.obtainMessage(4, otaDeviceInfo));
            }
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onError(int i) {
            super.onError(i);
            DfuService.this.sc = false;
            if (DfuService.this.vc != null) {
                DfuService.this.vc.sendMessage(DfuService.this.vc.obtainMessage(2, Integer.valueOf(i)));
            }
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo, Throughput throughput) {
            super.onProgressChanged(dfuProgressInfo, throughput);
            DfuService.this.uc = throughput;
            if (DfuService.this.vc != null) {
                DfuService.this.vc.sendMessage(DfuService.this.vc.obtainMessage(3, dfuProgressInfo));
            }
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onStateChanged(int i, Throughput throughput) {
            super.onStateChanged(i, throughput);
            DfuService.this.tc = i;
            DfuService.this.uc = throughput;
            DfuService dfuService = DfuService.this;
            dfuService.sc = (dfuService.tc & 512) == 512;
            if (DfuService.this.vc != null) {
                DfuService.this.vc.sendMessage(DfuService.this.vc.obtainMessage(1, Integer.valueOf(i)));
            }
        }
    }

    public final void a(int i, Object obj) {
        a.b bVar = this.lc.get(this.kc);
        if (bVar == null) {
            return;
        }
        this.mCallbacks.beginBroadcast();
        try {
        } catch (RemoteException e2) {
            ZLogger.e(e2.toString());
        }
        if (i == 1) {
            bVar.onProcessStateChanged(((Integer) obj).intValue());
        } else if (i == 2) {
            bVar.onError(((Integer) obj).intValue());
        } else {
            if (i != 3) {
                if (i == 4) {
                    bVar.onTargetInfoChanged((OtaDeviceInfo) obj);
                }
                this.mCallbacks.finishBroadcast();
            }
            bVar.onProgressChanged((DfuProgressInfo) obj);
        }
        this.mCallbacks.finishBroadcast();
    }

    public boolean abort() {
        d.a aVar = this.nc;
        if (aVar != null) {
            aVar.u();
        }
        e.a aVar2 = this.oc;
        if (aVar2 == null) {
            return true;
        }
        aVar2.u();
        return true;
    }

    public boolean activeImage(boolean z) {
        if (this.mc == 0) {
            d.a aVar = this.nc;
            return aVar != null && aVar.a(z);
        }
        e.a aVar2 = this.oc;
        return aVar2 != null && aVar2.a(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean initialize() {
        /*
            r2 = this;
            android.bluetooth.BluetoothManager r0 = r2.pc
            r1 = 0
            if (r0 != 0) goto L19
            java.lang.String r0 = "bluetooth"
            java.lang.Object r0 = r2.getSystemService(r0)
            android.bluetooth.BluetoothManager r0 = (android.bluetooth.BluetoothManager) r0
            r2.pc = r0
            android.bluetooth.BluetoothManager r0 = r2.pc
            if (r0 != 0) goto L19
            java.lang.String r0 = "Unable to initialize BluetoothManager."
        L15:
            com.realsil.sdk.core.logger.ZLogger.w(r0)
            return r1
        L19:
            android.bluetooth.BluetoothManager r0 = r2.pc
            android.bluetooth.BluetoothAdapter r0 = r0.getAdapter()
            r2.qc = r0
            android.bluetooth.BluetoothAdapter r0 = r2.qc
            if (r0 != 0) goto L28
            java.lang.String r0 = "Unable to obtain a BluetoothAdapter."
            goto L15
        L28:
            r0 = 1
            java.lang.String r1 = "initialize success"
            com.realsil.sdk.core.logger.ZLogger.d(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.DfuService.initialize():boolean");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ZLogger.d(true, "onBind");
        return this.jc;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ZLogger.d(true, "onCreate()");
        this.jc = new a(this);
        this.rc = BluetoothProfileManager.getInstance();
        if (this.rc == null) {
            BluetoothProfileManager.initial(this);
            this.rc = BluetoothProfileManager.getInstance();
        }
        BluetoothProfileManager bluetoothProfileManager = this.rc;
        if (bluetoothProfileManager != null) {
            bluetoothProfileManager.addManagerCallback(this.wc);
        } else {
            ZLogger.w("BluetoothProfileManager not initialized");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ZLogger.d(true, "onDestroy()+");
        this.sc = false;
        this.tc = 257;
        BluetoothProfileManager bluetoothProfileManager = this.rc;
        if (bluetoothProfileManager != null) {
            bluetoothProfileManager.removeManagerCallback(this.wc);
        }
        ZLogger.d(true, "onDestroy()-");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        ZLogger.d(true, "onUnbind");
        return super.onUnbind(intent);
    }

    public boolean start(String str, DfuConfig dfuConfig) {
        String str2;
        Thread thread;
        if (str == null) {
            str2 = "the packageName is null";
        } else if (dfuConfig == null) {
            str2 = "dfuConfig is null";
        } else {
            boolean z = this.sc;
            if (z && (this.tc & 512) == 512) {
                str2 = String.format(Locale.US, "isInOtaProcess=%b, mProcessState=0x%04X", Boolean.valueOf(z), Integer.valueOf(this.tc));
            } else if (!initialize()) {
                str2 = "initialize failed";
            } else {
                if (this.lc.get(str) != null) {
                    this.tc = 257;
                    this.uc = null;
                    this.kc = str;
                    this.mc = dfuConfig.getChannelType();
                    ZLogger.v("mPackageName=" + this.kc);
                    int i = this.mc;
                    if (i == 0) {
                        this.nc = new d.a(this, this.xc, dfuConfig);
                        thread = this.nc;
                    } else if (i == 1) {
                        this.oc = new e.a(this, this.xc, dfuConfig);
                        thread = this.oc;
                    } else {
                        str2 = "unknown channel:" + this.mc;
                    }
                    thread.start();
                    return true;
                }
                str2 = "didn't find the special callback in the service";
            }
        }
        ZLogger.w(str2);
        return false;
    }
}