package com.baidu.location.a;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.google.android.material.timepicker.TimeModel;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2112c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.baidu.location.e.h f2113a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public com.baidu.location.e.a f2114b = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2116e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2117f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2118g = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Handler f2115d = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f2119h = null;
    private String i = null;

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (com.baidu.location.f.isServing) {
                int i = message.what;
                if (i == 21) {
                    i.this.a(message);
                } else if (i == 62 || i == 63) {
                    i.this.a();
                }
            }
        }
    }

    class b extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f2121a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2122b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f2123c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f2124d = 0;

        public b() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.f2489h = com.baidu.location.g.k.e();
            if ((com.baidu.location.g.k.f2507h || com.baidu.location.g.k.j) && i.this.f2119h != null && i.this.i != null) {
                this.f2122b += String.format(Locale.CHINA, "&ki=%s&sn=%s", i.this.f2119h, i.this.i);
            }
            String strEncodeTp4 = Jni.encodeTp4(this.f2122b);
            this.f2122b = null;
            if (this.f2121a == null) {
                this.f2121a = w.b();
            }
            this.k.put("bloc", strEncodeTp4);
            if (this.f2121a != null) {
                this.k.put("up", this.f2121a);
            }
            this.k.put("trtm", String.format(Locale.CHINA, TimeModel.NUMBER_FORMAT, Long.valueOf(System.currentTimeMillis())));
        }

        public void a(String str, long j) {
            this.f2122b = str;
            this.f2124d = System.currentTimeMillis();
            this.f2123c = j;
            ExecutorService executorServiceB = v.a().b();
            if (com.baidu.location.g.k.b()) {
                a(executorServiceB, false, null);
            } else if (executorServiceB != null) {
                a(executorServiceB, com.baidu.location.g.k.f2505f);
            } else {
                c(com.baidu.location.g.k.f2505f);
            }
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            BDLocation bDLocation;
            Message messageObtainMessage;
            if (!z || this.j == null) {
                Message messageObtainMessage2 = i.this.f2115d.obtainMessage(63);
                messageObtainMessage2.obj = "HttpStatus error";
                messageObtainMessage2.sendToTarget();
            } else {
                try {
                    String str = this.j;
                    i.f2112c = str;
                    try {
                        bDLocation = new BDLocation(str);
                        if (bDLocation.getLocType() == 161) {
                            h.a().a(str);
                        }
                        bDLocation.setOperators(com.baidu.location.e.b.a().h());
                        if (n.a().d()) {
                            bDLocation.setDirection(n.a().e());
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(0);
                    }
                    this.f2121a = null;
                    if (bDLocation.getLocType() == 0 && bDLocation.getLatitude() == Double.MIN_VALUE && bDLocation.getLongitude() == Double.MIN_VALUE) {
                        messageObtainMessage = i.this.f2115d.obtainMessage(63);
                        messageObtainMessage.obj = "HttpStatus error";
                    } else {
                        long jCurrentTimeMillis = (System.currentTimeMillis() - this.f2124d) / 1000;
                        if (jCurrentTimeMillis < 0) {
                            jCurrentTimeMillis = 0;
                        }
                        if (this.f2123c < 0) {
                            this.f2123c = 0L;
                        }
                        bDLocation.setDelayTime(this.f2123c + jCurrentTimeMillis);
                        messageObtainMessage = i.this.f2115d.obtainMessage(21);
                        messageObtainMessage.obj = bDLocation;
                    }
                    messageObtainMessage.sendToTarget();
                } catch (Exception unused) {
                    Message messageObtainMessage22 = i.this.f2115d.obtainMessage(63);
                    messageObtainMessage22.obj = "HttpStatus error";
                    messageObtainMessage22.sendToTarget();
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    public String a(String str) {
        com.baidu.location.e.h hVar;
        String strM;
        if (this.f2119h == null) {
            this.f2119h = j.b(com.baidu.location.f.getServiceContext());
        }
        if (this.i == null) {
            this.i = j.c(com.baidu.location.f.getServiceContext());
        }
        com.baidu.location.e.a aVar = this.f2114b;
        if (aVar == null || !aVar.a()) {
            this.f2114b = com.baidu.location.e.b.a().f();
        }
        com.baidu.location.e.h hVar2 = this.f2113a;
        if (hVar2 == null || !hVar2.k()) {
            this.f2113a = com.baidu.location.e.i.a().p();
        }
        Location locationH = com.baidu.location.e.e.a().j() ? com.baidu.location.e.e.a().h() : null;
        com.baidu.location.e.a aVar2 = this.f2114b;
        if ((aVar2 == null || aVar2.d() || this.f2114b.c()) && (((hVar = this.f2113a) == null || hVar.a() == 0) && locationH == null)) {
            return null;
        }
        String strB = b();
        if (h.a().d() == -2) {
            strB = strB + "&imo=1";
        }
        int iC = com.baidu.location.g.k.c(com.baidu.location.f.getServiceContext());
        if (iC >= 0) {
            strB = strB + "&lmd=" + iC;
        }
        com.baidu.location.e.h hVar3 = this.f2113a;
        if ((hVar3 == null || hVar3.a() == 0) && (strM = com.baidu.location.e.i.a().m()) != null) {
            strB = strM + strB;
        }
        String str2 = strB;
        if (!this.f2117f) {
            return com.baidu.location.g.k.a(this.f2114b, this.f2113a, locationH, str2, 0);
        }
        this.f2117f = false;
        return com.baidu.location.g.k.a(this.f2114b, this.f2113a, locationH, str2, 0, true);
    }

    public abstract void a();

    public abstract void a(Message message);

    public String b() {
        String strD = com.baidu.location.a.a.a().d();
        String str = com.baidu.location.e.i.j() ? "&cn=32" : String.format(Locale.CHINA, "&cn=%d", Integer.valueOf(com.baidu.location.e.b.a().e()));
        if (Build.VERSION.SDK_INT >= 18) {
            String strD2 = com.baidu.location.g.k.d();
            if (!TextUtils.isEmpty(strD2)) {
                str = str + "&qcip6c=" + strD2;
            }
        }
        if (this.f2116e) {
            this.f2116e = false;
            String strS = com.baidu.location.e.i.a().s();
            if (!TextUtils.isEmpty(strS) && !strS.equals("02:00:00:00:00:00")) {
                str = String.format(Locale.CHINA, "%s&mac=%s", str, strS.replace(":", ""));
            }
            int i = Build.VERSION.SDK_INT;
        } else if (!this.f2118g) {
            String strE = w.e();
            if (strE != null) {
                str = str + strE;
            }
            this.f2118g = true;
        }
        return str + strD;
    }
}