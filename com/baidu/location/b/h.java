package com.baidu.location.b;

import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.baidu.location.Jni;
import com.baidu.location.a.v;
import com.baidu.location.g.k;
import com.bumptech.glide.load.Key;
import com.google.android.material.timepicker.TimeModel;
import com.ido.life.util.DateUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Object f2247a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static h f2248b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2249c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2250d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2251e = 24;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f2252f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f2253g = 0;

    private class a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2255b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2256c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private JSONArray f2257d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private JSONArray f2258e = null;

        a() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.f2489h = k.g();
            this.k.clear();
            this.k.put("qt", "cltrw");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, this.f2257d);
                jSONObject.put("frt", this.f2256c);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String strEncodeOfflineLocationUpdateRequest = Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            this.k.put("cltr[0]", "" + strEncodeOfflineLocationUpdateRequest);
            this.k.put("cfg", 1);
            this.k.put("info", Jni.encode(com.baidu.location.g.b.a().c()));
            this.k.put("trtm", String.format(Locale.CHINA, TimeModel.NUMBER_FORMAT, Long.valueOf(System.currentTimeMillis())));
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            JSONObject jSONObject;
            boolean z2;
            if (z && this.j != null) {
                try {
                    jSONObject = new JSONObject(this.j);
                    z2 = true;
                } catch (Exception unused) {
                    jSONObject = null;
                    z2 = false;
                }
                if (z2 && jSONObject != null) {
                    try {
                        jSONObject.put("tt", System.currentTimeMillis());
                        jSONObject.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, this.f2258e);
                        try {
                            File file = new File(h.this.f2250d, "wcnf.dat");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                            bufferedWriter.write(com.baidu.android.bbalbs.common.a.b.a(jSONObject.toString().getBytes(), Key.STRING_CHARSET_NAME));
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
            this.f2255b = false;
        }

        public void a(boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
            if (this.f2255b) {
                return;
            }
            this.f2255b = true;
            if (z) {
                this.f2256c = 1;
            } else {
                this.f2256c = 0;
            }
            this.f2257d = jSONArray;
            this.f2258e = jSONArray2;
            ExecutorService executorServiceC = v.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, k.g());
            } else {
                c(k.g());
            }
        }
    }

    private class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2259a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2260b;

        b(String str, int i) {
            this.f2259a = null;
            this.f2260b = 0;
            this.f2259a = str;
            this.f2260b = i;
        }
    }

    public static h a() {
        h hVar;
        synchronized (f2247a) {
            if (f2248b == null) {
                f2248b = new h();
            }
            hVar = f2248b;
        }
        return hVar;
    }

    private Object a(Object obj, String str) throws Exception {
        return obj.getClass().getField(str).get(obj);
    }

    private List<b> a(List<WifiConfiguration> list) {
        int iIntValue;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (WifiConfiguration wifiConfiguration : list) {
            String str = wifiConfiguration.SSID;
            try {
                iIntValue = ((Integer) a(wifiConfiguration, "numAssociation")).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
                iIntValue = 0;
            }
            if (iIntValue > 0 && str != null) {
                arrayList.add(new b(str, iIntValue));
            }
        }
        return arrayList;
    }

    private void a(boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        if (this.f2252f == null) {
            this.f2252f = new a();
        }
        if (k.b()) {
            return;
        }
        this.f2252f.a(z, jSONArray, jSONArray2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00df A[Catch: Exception -> 0x01c7, TryCatch #2 {Exception -> 0x01c7, blocks: (B:4:0x0010, B:50:0x00df, B:51:0x00e5, B:53:0x00f7, B:56:0x0109, B:58:0x010f, B:59:0x011d, B:61:0x0123, B:89:0x01c3, B:64:0x0144, B:66:0x014a, B:68:0x0151, B:70:0x0157, B:71:0x015d, B:73:0x0163, B:75:0x0183, B:82:0x0199, B:83:0x019e, B:46:0x00d6), top: B:98:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f7 A[Catch: Exception -> 0x01c7, TryCatch #2 {Exception -> 0x01c7, blocks: (B:4:0x0010, B:50:0x00df, B:51:0x00e5, B:53:0x00f7, B:56:0x0109, B:58:0x010f, B:59:0x011d, B:61:0x0123, B:89:0x01c3, B:64:0x0144, B:66:0x014a, B:68:0x0151, B:70:0x0157, B:71:0x015d, B:73:0x0163, B:75:0x0183, B:82:0x0199, B:83:0x019e, B:46:0x00d6), top: B:98:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d() {
        /*
            Method dump skipped, instruction units count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.h.d():void");
    }

    public void b() {
        if (this.f2249c == null) {
            this.f2249c = new i(this);
        }
        this.f2250d = k.i();
    }

    public void c() {
        Handler handler;
        if (System.currentTimeMillis() - this.f2253g <= DateUtil.HOUR || (handler = this.f2249c) == null) {
            return;
        }
        handler.sendEmptyMessage(1);
        this.f2253g = System.currentTimeMillis();
    }
}