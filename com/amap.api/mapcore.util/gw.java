package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.realsil.sdk.dfu.DfuException;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: compiled from: AdiuStorageModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class gw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1148a = gt.c("SU2hhcmVkUHJlZmVyZW5jZUFkaXU");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static gw f1149f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<String> f1150b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1151c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f1152d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Handler f1153e;

    public static gw a(Context context) {
        if (f1149f == null) {
            synchronized (gw.class) {
                if (f1149f == null) {
                    f1149f = new gw(context);
                }
            }
        }
        return f1149f;
    }

    private gw(Context context) {
        this.f1152d = context.getApplicationContext();
        if (Looper.myLooper() == null) {
            this.f1153e = new a(Looper.getMainLooper(), this);
        } else {
            this.f1153e = new a(this);
        }
    }

    public void a(String str) {
        this.f1151c = str;
    }

    public void b(String str) {
        List<String> list = this.f1150b;
        if (list != null) {
            list.clear();
            this.f1150b.add(str);
        }
        a(str, DfuException.ERROR_READ_IMAGE_VERSION_FAILED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.amap.api.mapcore.util.gw$1] */
    public synchronized void a(final String str, final int i) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread() { // from class: com.amap.api.mapcore.util.gw.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    String strB = hc.b(str);
                    if (TextUtils.isEmpty(strB)) {
                        return;
                    }
                    if ((i & 1) > 0) {
                        try {
                            if (Build.VERSION.SDK_INT >= 23) {
                                Settings.System.putString(gw.this.f1152d.getContentResolver(), gw.this.f1151c, strB);
                            } else {
                                Settings.System.putString(gw.this.f1152d.getContentResolver(), gw.this.f1151c, strB);
                            }
                        } catch (Exception unused) {
                        }
                    }
                    if ((i & 16) > 0) {
                        gy.a(gw.this.f1152d, gw.this.f1151c, strB);
                    }
                    if ((i & 256) > 0) {
                        SharedPreferences.Editor editorEdit = gw.this.f1152d.getSharedPreferences(gw.f1148a, 0).edit();
                        editorEdit.putString(gw.this.f1151c, strB);
                        if (Build.VERSION.SDK_INT >= 9) {
                            editorEdit.apply();
                        } else {
                            editorEdit.commit();
                        }
                    }
                }
            }.start();
        } else {
            String strB = hc.b(str);
            if (!TextUtils.isEmpty(strB)) {
                if ((i & 1) > 0) {
                    try {
                        if (Build.VERSION.SDK_INT >= 23) {
                            Settings.System.putString(this.f1152d.getContentResolver(), this.f1151c, strB);
                        } else {
                            Settings.System.putString(this.f1152d.getContentResolver(), this.f1151c, strB);
                        }
                    } catch (Exception unused) {
                    }
                }
                if ((i & 16) > 0) {
                    gy.a(this.f1152d, this.f1151c, strB);
                }
                if ((i & 256) > 0) {
                    SharedPreferences.Editor editorEdit = this.f1152d.getSharedPreferences(f1148a, 0).edit();
                    editorEdit.putString(this.f1151c, strB);
                    if (Build.VERSION.SDK_INT >= 9) {
                        editorEdit.apply();
                    } else {
                        editorEdit.commit();
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AdiuStorageModel.java */
    private static final class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<gw> f1157a;

        a(gw gwVar) {
            this.f1157a = new WeakReference<>(gwVar);
        }

        a(Looper looper, gw gwVar) {
            super(looper);
            this.f1157a = new WeakReference<>(gwVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            gw gwVar = this.f1157a.get();
            if (gwVar == null || message == null || message.obj == null) {
                return;
            }
            gwVar.a((String) message.obj, message.what);
        }
    }
}