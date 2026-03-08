package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import java.util.Map;

/* JADX INFO: compiled from: NetManger.java */
/* JADX INFO: loaded from: classes.dex */
public class ip extends ij {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ip f1392c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private jr f1393d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler f1394e;

    public static ip b() {
        return a(true, 5);
    }

    public static ip a(boolean z) {
        return a(z, 5);
    }

    private static synchronized ip a(boolean z, int i) {
        try {
            if (f1392c == null) {
                f1392c = new ip(z, i);
            } else if (z && f1392c.f1393d == null) {
                f1392c.f1393d = jr.b(i);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            if (gm.b() == null) {
                gm.a(gz.a());
            }
        } catch (Throwable unused) {
        }
        return f1392c;
    }

    private ip(boolean z, int i) {
        if (z) {
            try {
                this.f1393d = jr.b(i);
            } catch (Throwable th) {
                hn.c(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.f1394e = new a(Looper.getMainLooper(), null);
        } else {
            this.f1394e = new a();
        }
    }

    /* JADX INFO: renamed from: com.amap.api.mapcore.util.ip$1, reason: invalid class name */
    /* JADX INFO: compiled from: NetManger.java */
    class AnonymousClass1 extends js {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ iq f1395a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f1396b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ir f1397c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ip f1398d;

        @Override // com.amap.api.mapcore.util.js
        public void runTask() {
            try {
                this.f1398d.a(this.f1398d.d(this.f1395a, this.f1396b), this.f1397c);
            } catch (gh e2) {
                this.f1398d.a(e2, this.f1397c);
            }
        }
    }

    @Override // com.amap.api.mapcore.util.ij
    public byte[] b(iq iqVar) throws gh {
        try {
            is isVarB = b(iqVar, false);
            if (isVarB != null) {
                return isVarB.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            hn.e().b(th, "NetManager", "makeSyncPostRequest");
            throw new gh(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] f(iq iqVar) throws gh {
        try {
            is isVarD = d(iqVar, false);
            if (isVarD != null) {
                return isVarD.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        }
    }

    public byte[] g(iq iqVar) throws gh {
        try {
            is isVarD = d(iqVar, true);
            if (isVarD != null) {
                return isVarD.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        }
    }

    public Map<String, String> c(iq iqVar, boolean z) throws gh {
        Map<String, String> mapB;
        int iA = in.a(2, iqVar);
        try {
            mapB = b(iqVar, z, iA);
        } catch (gh e2) {
            if (!in.a(iA)) {
                throw e2;
            }
            mapB = null;
        }
        if (mapB != null || !in.a(iA)) {
            return mapB;
        }
        try {
            return b(iqVar, z, 3);
        } catch (gh e3) {
            throw e3;
        }
    }

    public Map<String, String> b(iq iqVar, boolean z, int i) throws gh {
        try {
            c(iqVar);
            return new in(iqVar, z).a(iqVar.getURL(), iqVar.c(), iqVar.isIPRequest(), iqVar.getIPDNSName(), iqVar.getRequestHead(), iqVar.getParams(), iqVar.isIgnoreGZip(), i);
        } catch (gh e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new gh(AMapException.ERROR_UNKNOWN);
        }
    }

    public is d(iq iqVar, boolean z) throws gh {
        is isVarC;
        int iA = in.a(2, iqVar);
        try {
            isVarC = c(iqVar, z, iA);
        } catch (gh e2) {
            if (!in.a(iA)) {
                throw e2;
            }
            isVarC = null;
        }
        if ((isVarC != null && isVarC.f1402a != null && isVarC.f1402a.length > 0) || !in.a(iA)) {
            return isVarC;
        }
        try {
            return c(iqVar, z, 3);
        } catch (gh e3) {
            throw e3;
        }
    }

    public byte[] h(iq iqVar) throws gh {
        try {
            is isVarE = e(iqVar, false);
            if (isVarE != null) {
                return isVarE.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        }
    }

    public byte[] i(iq iqVar) throws gh {
        try {
            is isVarE = e(iqVar, true);
            if (isVarE != null) {
                return isVarE.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        }
    }

    public is e(iq iqVar, boolean z) throws gh {
        is isVarC;
        int iA = in.a(2, iqVar);
        try {
            isVarC = c(iqVar, z, iA);
        } catch (gh e2) {
            if (e2.f() || !in.a(iA)) {
                throw e2;
            }
            isVarC = null;
        }
        if ((isVarC != null && isVarC.f1402a != null && isVarC.f1402a.length > 0) || !in.a(iA)) {
            return isVarC;
        }
        try {
            return c(iqVar, z, 3);
        } catch (gh e3) {
            throw e3;
        }
    }

    public is c(iq iqVar, boolean z, int i) throws gh {
        try {
            c(iqVar);
            return new in(iqVar, z).b(iqVar.getURL(), iqVar.c(), iqVar.isIPRequest(), iqVar.getIPDNSName(), iqVar.getRequestHead(), iqVar.getParams(), iqVar.isIgnoreGZip(), i);
        } catch (gh e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new gh(AMapException.ERROR_UNKNOWN);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(gh ghVar, ir irVar) {
        iu iuVar = new iu();
        iuVar.f1407a = ghVar;
        iuVar.f1408b = irVar;
        Message messageObtain = Message.obtain();
        messageObtain.obj = iuVar;
        messageObtain.what = 1;
        this.f1394e.sendMessage(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(is isVar, ir irVar) {
        irVar.a(isVar.f1403b, isVar.f1402a);
        iu iuVar = new iu();
        iuVar.f1408b = irVar;
        Message messageObtain = Message.obtain();
        messageObtain.obj = iuVar;
        messageObtain.what = 0;
        this.f1394e.sendMessage(messageObtain);
    }

    /* JADX INFO: compiled from: NetManger.java */
    static class a extends Handler {
        /* synthetic */ a(Looper looper, AnonymousClass1 anonymousClass1) {
            this(looper);
        }

        private a(Looper looper) {
            super(looper);
        }

        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 0) {
                    ((iu) message.obj).f1408b.a();
                } else {
                    if (i != 1) {
                        return;
                    }
                    iu iuVar = (iu) message.obj;
                    iuVar.f1408b.a(iuVar.f1407a);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}