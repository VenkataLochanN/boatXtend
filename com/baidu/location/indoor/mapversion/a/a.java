package com.baidu.location.indoor.mapversion.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.location.Jni;
import com.baidu.location.indoor.mapversion.IndoorJni;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.dialog.CommonDialog;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f2605a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.baidu.location.indoor.mapversion.a.d f2606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ExecutorService f2607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private File f2608d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2609e;

    /* JADX INFO: renamed from: com.baidu.location.indoor.mapversion.a.a$a, reason: collision with other inner class name */
    private class RunnableC0025a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f2610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2611b;

        private RunnableC0025a() {
            this.f2611b = null;
        }

        /* synthetic */ RunnableC0025a(a aVar, com.baidu.location.indoor.mapversion.a.b bVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject;
            File file;
            String strC;
            if (this.f2611b != null && this.f2610a != null) {
                try {
                    try {
                        jSONObject = new JSONObject();
                        jSONObject.put("timestamp", System.currentTimeMillis());
                        jSONObject.put("manufacturer", Build.MANUFACTURER);
                        jSONObject.put("product", Build.PRODUCT);
                        jSONObject.put("brand", Build.BRAND);
                        jSONObject.put("model", Build.MODEL);
                        jSONObject.put(CommonDialog.EXTRA_GRAVITY, String.format(Locale.CHINESE, "%.5f,%.5f,%.5f", Float.valueOf(this.f2610a.f2620e[0]), Float.valueOf(this.f2610a.f2620e[1]), Float.valueOf(this.f2610a.f2620e[2])));
                        jSONObject.put("fov", this.f2610a.f2619d);
                        jSONObject.put("bid", this.f2610a.f2622g);
                        jSONObject.put("cu", com.baidu.location.g.b.a().f2466c != null ? com.baidu.location.g.b.a().f2466c : "");
                        IndoorJni.a(this.f2610a.f2618c, a.this.f2608d);
                        file = new File(a.this.f2608d, "compress.jpg");
                        strC = a.this.c(file.getAbsolutePath());
                    } finally {
                        a.this.f2609e = false;
                    }
                } catch (Error | Exception unused) {
                }
                if (strC != null) {
                    jSONObject.put("image", strC);
                    file.delete();
                    a.this.b(this.f2610a.f2618c);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f2611b).openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", FastJsonJsonView.DEFAULT_CONTENT_TYPE);
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    httpURLConnection.getOutputStream().write(jSONObject.toString().getBytes());
                    httpURLConnection.disconnect();
                }
            }
        }
    }

    private class b extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f2613a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2615c;

        b() {
            this.f2615c = false;
            this.f2615c = false;
        }

        private void b() {
            if (a.this.f2605a != null) {
                e eVar = new e();
                if (a.this.f2606b != null) {
                    eVar.i = a.this.f2606b.b();
                }
                eVar.f2623a = "param not enough";
                a.this.f2605a.a(false, eVar);
            }
        }

        private String c() {
            String strA;
            float[] fArr = this.f2613a.f2620e;
            if (fArr == null || this.f2613a.f2618c == null) {
                return null;
            }
            d dVar = this.f2613a;
            dVar.f2617b = BitmapFactory.decodeFile(dVar.f2618c);
            if (this.f2613a.f2617b == null || (strA = IndoorJni.a(a.this.f2608d, this.f2613a.f2617b, this.f2613a.f2619d, fArr)) == null) {
                return null;
            }
            try {
                if (!this.f2613a.f2617b.isRecycled()) {
                    this.f2613a.f2617b.recycle();
                }
            } catch (Error e2) {
                e2.printStackTrace();
            }
            String strC = a.this.c(strA);
            if (strC == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(com.baidu.location.g.b.a().a(true));
            sb.append("&bid=");
            sb.append(this.f2613a.f2622g);
            sb.append("&data_type=vps");
            sb.append("&coor=gcj02");
            if (this.f2613a.f2621f != null && !this.f2613a.f2621f.equalsIgnoreCase("")) {
                sb.append("&code=");
                sb.append(this.f2613a.f2621f);
            }
            sb.append("&img=");
            sb.append(strC);
            return sb.toString();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            String strC = c();
            if (strC == null) {
                b();
                this.f2615c = true;
                return;
            }
            String strEncodeTp4 = Jni.encodeTp4(strC);
            this.k = new HashMap();
            this.k.put("vps", strEncodeTp4 + "|tp=4");
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            e eVar;
            if (this.f2615c) {
                return;
            }
            com.baidu.location.indoor.mapversion.a.b bVar = null;
            if (!z || this.j == null) {
                eVar = null;
            } else {
                eVar = new e();
                if (a.this.f2606b != null) {
                    eVar.i = a.this.f2606b.b();
                }
                try {
                    z = eVar.a(new JSONObject(this.j));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (a.this.f2605a != null) {
                a.this.f2605a.a(z, eVar);
            }
            if (eVar != null) {
                if (eVar.j <= 0 || a.this.f2609e || z) {
                    a.this.b(this.f2613a.f2618c);
                    return;
                }
                if (TextUtils.isEmpty(eVar.k)) {
                    return;
                }
                RunnableC0025a runnableC0025a = new RunnableC0025a(a.this, bVar);
                runnableC0025a.f2611b = eVar.k;
                runnableC0025a.f2610a = this.f2613a;
                a.this.f2609e = true;
                a.this.f2607c.execute(runnableC0025a);
            }
        }
    }

    public interface c {
        void a(boolean z, e eVar);
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2616a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Bitmap f2617b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2618c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private double f2619d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float[] f2620e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f2621f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f2622g;

        public d a(double d2) {
            this.f2619d = d2;
            return this;
        }

        public d a(String str) {
            this.f2618c = str;
            return this;
        }

        public d a(boolean z) {
            this.f2616a = z;
            return this;
        }

        public d a(float[] fArr) {
            this.f2620e = (float[]) fArr.clone();
            return this;
        }

        public d b(String str) {
            this.f2622g = str;
            return this;
        }

        public d c(String str) {
            this.f2621f = str;
            return this;
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2623a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2624b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f2625c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f2626d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f2627e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f2628f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f2629g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f2630h;
        public String i;
        public int j = 0;
        public String k;

        public boolean a(JSONObject jSONObject) {
            this.f2624b = jSONObject.optString("bldg");
            this.f2625c = jSONObject.optString("floor");
            this.f2626d = jSONObject.optString("bldgid");
            String strOptString = jSONObject.optString("indoor");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(com.ido.ble.event.stat.one.d.G);
            if (jSONObjectOptJSONObject != null) {
                this.j = jSONObjectOptJSONObject.optInt("rpfg");
                this.k = jSONObjectOptJSONObject.optString("rpurl");
                String str = this.k;
                if (str != null) {
                    this.k = new String(Base64.decode(str, 0));
                }
            }
            if (strOptString == null || strOptString.equalsIgnoreCase(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE)) {
                this.f2623a = "定位失败";
                return false;
            }
            String strOptString2 = jSONObject.optString("radius");
            if (strOptString2 == null) {
                this.f2623a = "定位失败";
                return false;
            }
            this.f2629g = Double.valueOf(strOptString2).doubleValue();
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("point");
            if (jSONObjectOptJSONObject2 == null) {
                this.f2623a = "定位失败";
                return false;
            }
            String strOptString3 = jSONObjectOptJSONObject2.optString("x");
            String strOptString4 = jSONObjectOptJSONObject2.optString("y");
            if (strOptString3.equals("-1.000000") && strOptString4.equals("-1.000000")) {
                this.f2623a = "定位失败";
                return false;
            }
            this.f2623a = "";
            this.f2628f = Double.valueOf(strOptString3).doubleValue();
            this.f2627e = Double.valueOf(strOptString4).doubleValue();
            if (jSONObjectOptJSONObject == null) {
                return true;
            }
            this.f2630h = jSONObjectOptJSONObject.optString(AuthorizationResponseParser.CODE);
            return true;
        }
    }

    private void a(String str) {
        e eVar = new e();
        eVar.f2623a = str;
        com.baidu.location.indoor.mapversion.a.d dVar = this.f2606b;
        if (dVar != null) {
            eVar.i = dVar.b();
        }
        c cVar = this.f2605a;
        if (cVar != null) {
            cVar.a(false, eVar);
        }
    }

    private boolean a(String str, String str2) {
        com.baidu.location.indoor.mapversion.a.d dVar = this.f2606b;
        if (dVar == null || !dVar.a()) {
            return false;
        }
        com.baidu.location.indoor.mapversion.a.d dVar2 = this.f2606b;
        com.baidu.location.indoor.mapversion.a.c cVarA = dVar2 != null ? dVar2.a(str, str2) : null;
        if (cVarA == null) {
            return false;
        }
        e eVar = new e();
        eVar.f2624b = cVarA.a();
        eVar.f2626d = cVarA.b();
        eVar.f2625c = cVarA.d();
        eVar.f2628f = cVarA.e();
        eVar.f2627e = cVarA.f();
        eVar.f2630h = str2;
        com.baidu.location.indoor.mapversion.a.d dVar3 = this.f2606b;
        if (dVar3 != null) {
            eVar.i = dVar3.b();
        }
        c cVar = this.f2605a;
        if (cVar != null) {
            cVar.a(true, eVar);
        }
        return true;
    }

    private void b(d dVar) {
        b bVar = new b();
        bVar.f2613a = dVar;
        bVar.c("https://loc.map.baidu.com/ios_indoorloc");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(String str) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return Base64.encodeToString(bArr, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(d dVar) {
        if (dVar.f2622g == null) {
            a("no bid");
            return;
        }
        this.f2606b.a(dVar.f2622g);
        if (dVar.f2621f == null || dVar.f2616a || !a(dVar.f2622g, dVar.f2621f)) {
            b(dVar);
        }
    }
}