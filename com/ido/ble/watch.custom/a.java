package com.ido.ble.watch.custom;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.LocalDataManager;
import com.ido.ble.common.j;
import com.ido.ble.common.k;
import com.ido.ble.common.n;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateFileMakeConfig;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String i = "WATCH_PLATE_AUTO_SET";
    private static final String j = "ido_watch_plate_data.iwf";
    private static final String k = "ido_watch_plate_data.iwf.lz";
    private static a l;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private WatchPlateSetConfig f4654b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4655c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private FileTransferConfig f4658f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4653a = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4656d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4657e = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f4659g = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private WatchPlateCallBack.IOperateCallBack f4660h = new C0088a();

    /* JADX INFO: renamed from: com.ido.ble.watch.custom.a$a, reason: collision with other inner class name */
    class C0088a implements WatchPlateCallBack.IOperateCallBack {
        C0088a() {
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onDeletePlate(boolean z) {
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetCurrentPlate(String str) {
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetDialPlateParam(DialPlateParam dialPlateParam) {
            if (a.this.f4657e) {
                LogTool.d(a.i, "onGetDialPlateParam is_SDK_use = true");
                a.this.f4657e = false;
                a.this.s();
                if (dialPlateParam != null) {
                    a.this.b(dialPlateParam.usable_max_download_space_size);
                } else {
                    LogTool.b(a.i, "dialPlateParam == null");
                    a.this.b();
                }
            }
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetPlateFileInfo(WatchPlateFileInfo watchPlateFileInfo) {
            if (!a.this.f4656d || a.this.f4654b == null || a.this.f4654b.isOnlyTranslateWatchFile) {
                return;
            }
            a.this.a(watchPlateFileInfo.fileNameList);
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetScreenInfo(WatchPlateScreenInfo watchPlateScreenInfo) {
            a.this.b(watchPlateScreenInfo);
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onSetPlate(boolean z) {
            if (!a.this.f4656d || a.this.f4654b == null || a.this.f4654b.isOnlyTranslateWatchFile) {
                return;
            }
            a.this.a(z);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.b(a.i, "get free size time out.");
            a.this.i();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.b(a.i, "get screen info time out.");
            a.this.e();
        }
    }

    class d implements IFileTransferListener {
        d() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onFailed(String str) {
            LogTool.b(a.i, "translatePlateFile failed, " + str);
            a.this.a(str);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onProgress(int i) {
            a.this.a(i);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onStart() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onSuccess() {
            if (a.this.f4653a && a.this.f4654b != null) {
                if (a.this.f4654b.isOnlyTranslateWatchFile) {
                    a.this.d();
                    return;
                } else {
                    a.this.k();
                    return;
                }
            }
            LogTool.b(a.i, "[translatePlateFile].onSuccess, isDoing=" + a.this.f4653a + ",watchPlateSetConfig is null");
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.e();
        }
    }

    private a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        LogTool.d(i, "progress = " + i2);
        WatchPlateSetConfig watchPlateSetConfig = this.f4654b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateProgress] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onProgress(i2);
        } else {
            LogTool.b(i, "[autoSetPlateProgress] watchPlateSetConfig.stateListener is null");
        }
    }

    private void a(WatchPlateScreenInfo watchPlateScreenInfo) {
        LogTool.d(i, "start makePlateFile");
        WatchPlateFileMakeConfig watchPlateFileMakeConfig = new WatchPlateFileMakeConfig();
        watchPlateFileMakeConfig.format = watchPlateScreenInfo.format;
        watchPlateFileMakeConfig.filePath = this.f4655c;
        watchPlateFileMakeConfig.outFileName = j;
        watchPlateFileMakeConfig.blockSize = watchPlateScreenInfo.blockSize;
        com.ido.ble.i.a.a.a(watchPlateFileMakeConfig);
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = ","
            java.lang.String r1 = "WATCH_PLATE_AUTO_SET"
            com.ido.ble.watch.custom.WatchPlateSetConfig r2 = r6.f4654b     // Catch: java.lang.Exception -> Lbb
            if (r2 != 0) goto Le
            java.lang.String r7 = "[autoSetPlateFailed] watchPlateSetConfig is null"
            com.ido.ble.logs.LogTool.b(r1, r7)     // Catch: java.lang.Exception -> Lbb
            return
        Le:
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lbb
            if (r2 != 0) goto Lc4
            com.ido.ble.watch.custom.WatchPlateSetConfig r2 = r6.f4654b     // Catch: java.lang.Exception -> Lbb
            com.ido.ble.watch.custom.callback.WatchPlateCallBack$ISetPlatErrorCallback r2 = r2.errorCallback     // Catch: java.lang.Exception -> Lbb
            if (r2 == 0) goto Lc4
            java.lang.String r2 = "transfer progress return code ="
            boolean r2 = r7.startsWith(r2)     // Catch: java.lang.Exception -> Lbb
            if (r2 == 0) goto Lc4
            boolean r2 = r7.contains(r0)     // Catch: java.lang.Exception -> Lbb
            java.lang.String r3 = "="
            r4 = 1
            if (r2 == 0) goto L99
            java.lang.String[] r7 = r7.split(r0)     // Catch: java.lang.Exception -> Lbb
            if (r7 == 0) goto L93
            int r0 = r7.length     // Catch: java.lang.Exception -> Lbb
            r2 = 2
            if (r0 != r2) goto L93
            r0 = 0
            r0 = r7[r0]     // Catch: java.lang.Exception -> Lbb
            java.lang.String[] r0 = r0.split(r3)     // Catch: java.lang.Exception -> Lbb
            r0 = r0[r4]     // Catch: java.lang.Exception -> Lbb
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> Lbb
            r5 = -1000(0xfffffffffffffc18, float:NaN)
            if (r2 != 0) goto L5d
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Exception -> Lbb
            boolean r2 = android.text.TextUtils.isDigitsOnly(r0)     // Catch: java.lang.Exception -> Lbb
            if (r2 == 0) goto L5d
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> Lbb
            com.ido.ble.watch.custom.WatchPlateSetConfig r2 = r6.f4654b     // Catch: java.lang.Exception -> Lbb
            com.ido.ble.watch.custom.callback.WatchPlateCallBack$ISetPlatErrorCallback r2 = r2.errorCallback     // Catch: java.lang.Exception -> Lbb
            r2.onFailed(r0)     // Catch: java.lang.Exception -> Lbb
            goto L5e
        L5d:
            r0 = r5
        L5e:
            r7 = r7[r4]     // Catch: java.lang.Exception -> Lbb
            if (r0 == r5) goto Lc4
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lbb
            if (r2 != 0) goto Lc4
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Exception -> Lbb
            java.lang.String r2 = "value = "
            boolean r2 = r7.startsWith(r2)     // Catch: java.lang.Exception -> Lbb
            if (r2 == 0) goto Lc4
            java.lang.String[] r7 = r7.split(r3)     // Catch: java.lang.Exception -> Lbb
            r7 = r7[r4]     // Catch: java.lang.Exception -> Lbb
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lbb
            if (r2 != 0) goto Lc4
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Exception -> Lbb
            boolean r2 = android.text.TextUtils.isDigitsOnly(r7)     // Catch: java.lang.Exception -> Lbb
            if (r2 == 0) goto Lc4
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> Lbb
            com.ido.ble.gps.callback.GpsCallBack.a(r0, r7)     // Catch: java.lang.Exception -> Lbb
            goto Lc4
        L93:
            java.lang.String r7 = "error format  is Mismatch!"
            com.ido.ble.logs.LogTool.d(r1, r7)     // Catch: java.lang.Exception -> Lbb
            goto Lc4
        L99:
            java.lang.String[] r7 = r7.split(r3)     // Catch: java.lang.Exception -> Lbb
            r7 = r7[r4]     // Catch: java.lang.Exception -> Lbb
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lbb
            if (r0 != 0) goto Lc4
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Exception -> Lbb
            boolean r0 = android.text.TextUtils.isDigitsOnly(r7)     // Catch: java.lang.Exception -> Lbb
            if (r0 == 0) goto Lc4
            com.ido.ble.watch.custom.WatchPlateSetConfig r0 = r6.f4654b     // Catch: java.lang.Exception -> Lbb
            com.ido.ble.watch.custom.callback.WatchPlateCallBack$ISetPlatErrorCallback r0 = r0.errorCallback     // Catch: java.lang.Exception -> Lbb
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> Lbb
            r0.onFailed(r7)     // Catch: java.lang.Exception -> Lbb
            goto Lc4
        Lbb:
            r7 = move-exception
            r7.printStackTrace()
            java.lang.String r7 = "[autoSetPlateFailed] watchPlateSetConfig errorCallback process error"
            com.ido.ble.logs.LogTool.b(r1, r7)
        Lc4:
            r6.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.watch.custom.a.a(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<String> list) {
        String str;
        if (list == null || list.size() == 0) {
            str = "watchReturnPlateListData = null";
        } else {
            LogTool.d(i, "watchReturnPlateListData = " + k.a(list));
            boolean z = false;
            Iterator<String> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (f().equals(it.next())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                u();
                return;
            }
            str = "set failed, isExists = false, getCurrentPlateUniqueID=" + f();
        }
        LogTool.b(i, str);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        LogTool.d(i, "watchReturnSetPlateResult = " + z);
        if (z) {
            d();
        } else {
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(i, "failed");
        WatchPlateSetConfig watchPlateSetConfig = this.f4654b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateFailed] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onFailed();
        } else {
            LogTool.b(i, "[autoSetPlateFailed] watchPlateSetConfig.stateListener is null");
        }
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        FileTransferConfig fileTransferConfig = this.f4658f;
        if (fileTransferConfig == null) {
            LogTool.b(i, "mFileTransferConfig == null");
            b();
        } else if (i2 >= fileTransferConfig.oriSize) {
            LogTool.d(i, "freeSize >= mFileTransferConfig.oriSize, start to tran");
            com.ido.ble.file.transfer.b.g().b(this.f4658f);
        } else {
            LogTool.d(i, "freeSize < mFileTransferConfig.oriSize, fail to tran");
            a("transfer progress return code = 21");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(WatchPlateScreenInfo watchPlateScreenInfo) {
        t();
        if (watchPlateScreenInfo == null) {
            LogTool.b(i, "get screen info failed.");
            b();
        } else {
            LogTool.d(i, "get screen info ok");
            a(watchPlateScreenInfo);
        }
    }

    private boolean b(WatchPlateSetConfig watchPlateSetConfig) {
        String str;
        if (watchPlateSetConfig == null) {
            str = "config is null .";
        } else if (TextUtils.isEmpty(watchPlateSetConfig.filePath)) {
            str = "config. file path is null .";
        } else if (!new File(watchPlateSetConfig.filePath).exists()) {
            str = "config. file is not exists";
        } else if (TextUtils.isEmpty(watchPlateSetConfig.uniqueID)) {
            str = "config. uniqueID is null";
        } else {
            if (watchPlateSetConfig.stateListener != null) {
                LogTool.d(i, "config is " + watchPlateSetConfig.toString());
                return true;
            }
            str = "config. state listener is null";
        }
        LogTool.b(i, str);
        return false;
    }

    private void c() {
        LogTool.d(i, "start");
        WatchPlateSetConfig watchPlateSetConfig = this.f4654b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateStart] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onStart();
        } else {
            LogTool.b(i, "[autoSetPlateStart] watchPlateSetConfig.stateListener is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogTool.d(i, "success");
        WatchPlateSetConfig watchPlateSetConfig = this.f4654b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateSuccess] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onSuccess();
        } else {
            LogTool.b(i, "[autoSetPlateSuccess] watchPlateSetConfig.stateListener is null");
        }
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f4653a) {
            LogTool.d(i, "timeout");
            WatchPlateSetConfig watchPlateSetConfig = this.f4654b;
            if (watchPlateSetConfig == null) {
                LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig is null");
                return;
            }
            WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
            if (iAutoSetPlateCallBack != null) {
                iAutoSetPlateCallBack.onFailed();
            } else {
                LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig.stateListener is null");
            }
            o();
        }
    }

    private String f() {
        if (this.f4654b == null) {
            LogTool.b(i, "getCurrentPlateUniqueID, watchPlateSetConfig is null");
            return "";
        }
        return this.f4654b.uniqueID + ".iwf";
    }

    private Handler g() {
        if (this.f4659g == null) {
            this.f4659g = new Handler(Looper.getMainLooper());
        }
        return this.f4659g;
    }

    private void h() {
        this.f4657e = true;
        LogTool.d(i, "start get free size");
        com.ido.ble.i.a.a.z();
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d(i, "getFreeSizeTimeOut");
        WatchPlateSetConfig watchPlateSetConfig = this.f4654b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onFailed();
        } else {
            LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig.stateListener is null");
        }
        o();
    }

    public static a j() {
        if (l == null) {
            l = new a();
        }
        return l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogTool.d(i, "start getPlateList");
        this.f4656d = true;
        p();
        com.ido.ble.i.a.a.V();
    }

    private void l() {
        LogTool.d(i, "start get screen info");
        com.ido.ble.i.a.a.W();
        r();
    }

    private void m() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            LogTool.d(i, "supportFunctionInfo == null,start to tran direct");
            com.ido.ble.file.transfer.b.g().b(this.f4658f);
        } else if (supportFunctionInfo.V3_support_watch_capacity_size_display) {
            LogTool.d(i, "translatePlateFile V3_support_watch_capacity_size_display = true");
            h();
        } else {
            LogTool.d(i, "translatePlateFile V3_support_watch_capacity_size_display = false,start to tran");
            com.ido.ble.file.transfer.b.g().b(this.f4658f);
        }
    }

    private void n() {
        com.ido.ble.watch.custom.callback.a.c().a(this.f4660h);
    }

    private void o() {
        this.f4653a = false;
        this.f4656d = false;
        this.f4657e = false;
        this.f4654b = null;
        this.f4655c = null;
        this.f4658f = null;
        Handler handler = this.f4659g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        w();
    }

    private void p() {
        g().postDelayed(new e(), 30000L);
    }

    private void q() {
        g().postDelayed(new b(), 15000L);
    }

    private void r() {
        g().postDelayed(new c(), 20000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        Handler handler = this.f4659g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void t() {
        Handler handler = this.f4659g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void u() {
        LogTool.d(i, "start toSetPlate");
        com.ido.ble.i.a.a.c(f());
    }

    private void v() {
        LogTool.d(i, "start translatePlateFile");
        FileTransferConfig defaultWatchPlateFileConfig = FileTransferConfig.getDefaultWatchPlateFileConfig(f() + ".lz", this.f4655c + k, new d());
        WatchPlateSetConfig watchPlateSetConfig = this.f4654b;
        if (watchPlateSetConfig != null) {
            int i2 = watchPlateSetConfig.PRN;
            if (i2 > 0) {
                defaultWatchPlateFileConfig.PRN = i2;
            }
            defaultWatchPlateFileConfig.maxRetryTimes = this.f4654b.maxRetryTimes;
        }
        defaultWatchPlateFileConfig.oriSize = (int) new File(this.f4655c + j).length();
        this.f4658f = defaultWatchPlateFileConfig;
        LogTool.d(i, "translatePlateFile iwfFile.length = " + defaultWatchPlateFileConfig.oriSize);
        m();
    }

    private void w() {
        com.ido.ble.watch.custom.callback.a.c().b(this.f4660h);
    }

    private boolean x() {
        LogTool.d(i, "start unzip file");
        this.f4655c = this.f4654b.filePath.replace(new File(this.f4654b.filePath).getName(), "") + "watchFileTemp" + File.separator;
        try {
            j.b(new File(this.f4655c));
            return n.b(this.f4654b.filePath, this.f4655c);
        } catch (IOException e2) {
            LogTool.b(i, e2.getMessage());
            return false;
        }
    }

    public long a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        LogTool.d(i, " start get dial plat size, format = " + i2);
        try {
            File file = new File(str);
            String name = file.getName();
            String str2 = file.getParent() + File.separator + name.substring(0, name.lastIndexOf(".")) + File.separator;
            File file2 = new File(str2);
            String[] list = file2.list();
            if (!file2.exists() || (list != null && list.length == 0)) {
                LogTool.d(i, "getDialPlatSize start unzip file");
                n.b(str, str2);
            }
            if (TextUtils.isEmpty(file.getParent())) {
                return 0L;
            }
            return u.b(str2, r7 + File.separator + r2 + ".iwf", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            LogTool.d(i, "error to get dial plat size");
            return 0L;
        }
    }

    public void a() {
        LogTool.b(i, "stopByUser. ");
        com.ido.ble.file.transfer.b.g().a();
        o();
    }

    public void a(WatchPlateSetConfig watchPlateSetConfig) {
        if (this.f4653a) {
            LogTool.b(i, "is in doing state, ignore this action ...");
            return;
        }
        this.f4653a = true;
        this.f4654b = watchPlateSetConfig;
        c();
        if (!b(watchPlateSetConfig)) {
            b();
            return;
        }
        n();
        if (x()) {
            LogTool.d(i, "unzip ok .");
            l();
        } else {
            LogTool.b(i, "unzip file failed .");
            b();
        }
    }
}