package com.baidu.location.b;

import android.content.SharedPreferences;
import com.baidu.location.Jni;
import com.baidu.location.a.v;
import com.baidu.location.g.j;
import com.baidu.location.g.k;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private static e i;
    private static final String k = j.f2498a + "/conlts.dat";
    private static int l = -1;
    private static int m = -1;
    private static int n = 0;
    private a j = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2232a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2233b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2234c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2235d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2236e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2237f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2238g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2239h = false;

    class a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f2240a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2241b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2242c = false;

        public a() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            Map<String, Object> map;
            String str;
            this.f2489h = k.e();
            this.i = 2;
            String strEncode = Jni.encode(this.f2240a);
            this.f2240a = null;
            if (this.f2241b) {
                map = this.k;
                str = "grid";
            } else {
                map = this.k;
                str = "conf";
            }
            map.put("qt", str);
            this.k.put("req", strEncode);
        }

        public void a(String str, boolean z) {
            if (this.f2242c) {
                return;
            }
            this.f2242c = true;
            this.f2240a = str;
            this.f2241b = z;
            ExecutorService executorServiceC = v.a().c();
            if (z) {
                a(executorServiceC, true, "loc.map.baidu.com");
            } else if (executorServiceC != null) {
                a(executorServiceC, k.f2505f);
            } else {
                c(k.f2505f);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
        @Override // com.baidu.location.g.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(boolean r2) {
            /*
                r1 = this;
                if (r2 == 0) goto L17
                java.lang.String r2 = r1.j
                if (r2 == 0) goto L17
                boolean r2 = r1.f2241b
                if (r2 == 0) goto L12
                com.baidu.location.b.e r2 = com.baidu.location.b.e.this
                byte[] r0 = r1.m
                com.baidu.location.b.e.a(r2, r0)
                goto L1d
            L12:
                com.baidu.location.b.e r2 = com.baidu.location.b.e.this
                java.lang.String r0 = r1.j
                goto L1a
            L17:
                com.baidu.location.b.e r2 = com.baidu.location.b.e.this
                r0 = 0
            L1a:
                com.baidu.location.b.e.a(r2, r0)
            L1d:
                java.util.Map<java.lang.String, java.lang.Object> r2 = r1.k
                if (r2 == 0) goto L26
                java.util.Map<java.lang.String, java.lang.Object> r2 = r1.k
                r2.clear()
            L26:
                r2 = 0
                r1.f2242c = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.e.a.a(boolean):void");
        }
    }

    private e() {
    }

    public static e a() {
        if (i == null) {
            i = new e();
        }
        return i;
    }

    private void a(int i2) {
        this.f2232a = (i2 & 1) == 1;
        this.f2233b = (i2 & 2) == 2;
        this.f2234c = (i2 & 4) == 4;
        this.f2235d = (i2 & 8) == 8;
        this.f2237f = (i2 & 65536) == 65536;
        this.f2238g = (i2 & 131072) == 131072;
        if ((i2 & 16) == 16) {
            this.f2236e = false;
        }
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        boolean z = true;
        try {
            if (jSONObject.has("ipen") && jSONObject.getInt("ipen") == 0) {
                z = false;
            }
            int i2 = jSONObject.has("ipvt") ? jSONObject.getInt("ipvt") : 14400000;
            int i3 = jSONObject.has("ipvn") ? jSONObject.getInt("ipvn") : 10;
            SharedPreferences.Editor editorEdit = com.baidu.location.f.getServiceContext().getSharedPreferences("MapCoreServicePre", 0).edit();
            editorEdit.putBoolean("ipLocInfoUpload", z);
            editorEdit.putInt("ipValidTime", i2);
            editorEdit.putInt("ipLocInfoUploadTimesPerDay", i3);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr) {
        int i2 = 0;
        if (bArr != null) {
            try {
                if (bArr.length < 640) {
                    k.x = false;
                    k.u = k.s + 0.025d;
                    k.t = k.r - 0.025d;
                } else {
                    k.x = true;
                    k.t = Double.longBitsToDouble(((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[1]) & 255) << 8) | (((long) bArr[0]) & 255));
                    k.u = Double.longBitsToDouble(((((long) bArr[15]) & 255) << 56) | ((((long) bArr[14]) & 255) << 48) | ((((long) bArr[13]) & 255) << 40) | ((((long) bArr[12]) & 255) << 32) | ((((long) bArr[11]) & 255) << 24) | ((((long) bArr[10]) & 255) << 16) | ((((long) bArr[9]) & 255) << 8) | (255 & ((long) bArr[8])));
                    k.w = new byte[com.veryfit.multi.nativeprotocol.b.O2];
                    while (i2 < 625) {
                        k.w[i2] = bArr[i2 + 16];
                        i2++;
                    }
                }
                i2 = 1;
            } catch (Exception unused) {
                return;
            }
        }
        if (i2 != 0) {
            g();
        }
    }

    private boolean a(String str) {
        if (str == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("ipconf")) {
                try {
                    a(jSONObject.getJSONObject("ipconf"));
                } catch (Exception unused) {
                }
            }
            int i2 = Integer.parseInt(jSONObject.getString("ver"));
            if (i2 <= k.y) {
                return false;
            }
            k.y = i2;
            if (jSONObject.has("gps")) {
                String[] strArrSplit = jSONObject.getString("gps").split("\\|");
                if (strArrSplit.length > 10) {
                    if (strArrSplit[0] != null && !strArrSplit[0].equals("")) {
                        k.z = Float.parseFloat(strArrSplit[0]);
                    }
                    if (strArrSplit[1] != null && !strArrSplit[1].equals("")) {
                        k.A = Float.parseFloat(strArrSplit[1]);
                    }
                    if (strArrSplit[2] != null && !strArrSplit[2].equals("")) {
                        k.B = Float.parseFloat(strArrSplit[2]);
                    }
                    if (strArrSplit[3] != null && !strArrSplit[3].equals("")) {
                        k.C = Float.parseFloat(strArrSplit[3]);
                    }
                    if (strArrSplit[4] != null && !strArrSplit[4].equals("")) {
                        k.D = Integer.parseInt(strArrSplit[4]);
                    }
                    if (strArrSplit[5] != null && !strArrSplit[5].equals("")) {
                        k.E = Integer.parseInt(strArrSplit[5]);
                    }
                    if (strArrSplit[6] != null && !strArrSplit[6].equals("")) {
                        k.F = Integer.parseInt(strArrSplit[6]);
                    }
                    if (strArrSplit[7] != null && !strArrSplit[7].equals("")) {
                        k.G = Integer.parseInt(strArrSplit[7]);
                    }
                    if (strArrSplit[8] != null && !strArrSplit[8].equals("")) {
                        k.H = Integer.parseInt(strArrSplit[8]);
                    }
                    if (strArrSplit[9] != null && !strArrSplit[9].equals("")) {
                        k.I = Integer.parseInt(strArrSplit[9]);
                    }
                    if (strArrSplit[10] != null && !strArrSplit[10].equals("")) {
                        k.J = Integer.parseInt(strArrSplit[10]);
                    }
                }
            }
            if (jSONObject.has("up")) {
                String[] strArrSplit2 = jSONObject.getString("up").split("\\|");
                if (strArrSplit2.length > 3) {
                    if (strArrSplit2[0] != null && !strArrSplit2[0].equals("")) {
                        k.K = Float.parseFloat(strArrSplit2[0]);
                    }
                    if (strArrSplit2[1] != null && !strArrSplit2[1].equals("")) {
                        k.L = Float.parseFloat(strArrSplit2[1]);
                    }
                    if (strArrSplit2[2] != null && !strArrSplit2[2].equals("")) {
                        k.M = Float.parseFloat(strArrSplit2[2]);
                    }
                    if (strArrSplit2[3] != null && !strArrSplit2[3].equals("")) {
                        k.N = Float.parseFloat(strArrSplit2[3]);
                    }
                }
            }
            if (jSONObject.has("wf")) {
                String[] strArrSplit3 = jSONObject.getString("wf").split("\\|");
                if (strArrSplit3.length > 3) {
                    if (strArrSplit3[0] != null && !strArrSplit3[0].equals("")) {
                        k.O = Integer.parseInt(strArrSplit3[0]);
                    }
                    if (strArrSplit3[1] != null && !strArrSplit3[1].equals("")) {
                        k.Q = Float.parseFloat(strArrSplit3[1]);
                    }
                    if (strArrSplit3[2] != null && !strArrSplit3[2].equals("")) {
                        k.R = Integer.parseInt(strArrSplit3[2]);
                    }
                    if (strArrSplit3[3] != null && !strArrSplit3[3].equals("")) {
                        k.S = Float.parseFloat(strArrSplit3[3]);
                    }
                }
            }
            if (jSONObject.has("ab")) {
                String[] strArrSplit4 = jSONObject.getString("ab").split("\\|");
                if (strArrSplit4.length > 3) {
                    if (strArrSplit4[0] != null && !strArrSplit4[0].equals("")) {
                        k.T = Float.parseFloat(strArrSplit4[0]);
                    }
                    if (strArrSplit4[1] != null && !strArrSplit4[1].equals("")) {
                        k.U = Float.parseFloat(strArrSplit4[1]);
                    }
                    if (strArrSplit4[2] != null && !strArrSplit4[2].equals("")) {
                        k.V = Integer.parseInt(strArrSplit4[2]);
                    }
                    if (strArrSplit4[3] != null && !strArrSplit4[3].equals("")) {
                        k.W = Integer.parseInt(strArrSplit4[3]);
                    }
                }
            }
            if (jSONObject.has("zxd")) {
                String[] strArrSplit5 = jSONObject.getString("zxd").split("\\|");
                if (strArrSplit5.length > 4) {
                    if (strArrSplit5[0] != null && !strArrSplit5[0].equals("")) {
                        k.as = Float.parseFloat(strArrSplit5[0]);
                    }
                    if (strArrSplit5[1] != null && !strArrSplit5[1].equals("")) {
                        k.at = Float.parseFloat(strArrSplit5[1]);
                    }
                    if (strArrSplit5[2] != null && !strArrSplit5[2].equals("")) {
                        k.au = Integer.parseInt(strArrSplit5[2]);
                    }
                    if (strArrSplit5[3] != null && !strArrSplit5[3].equals("")) {
                        k.av = Integer.parseInt(strArrSplit5[3]);
                    }
                    if (strArrSplit5[4] != null && !strArrSplit5[4].equals("")) {
                        k.aw = Integer.parseInt(strArrSplit5[4]);
                    }
                }
            }
            if (jSONObject.has("gpc")) {
                String[] strArrSplit6 = jSONObject.getString("gpc").split("\\|");
                if (strArrSplit6.length > 5) {
                    if (strArrSplit6[0] != null && !strArrSplit6[0].equals("")) {
                        if (Integer.parseInt(strArrSplit6[0]) > 0) {
                            k.ab = true;
                        } else {
                            k.ab = false;
                        }
                    }
                    if (strArrSplit6[1] != null && !strArrSplit6[1].equals("")) {
                        if (Integer.parseInt(strArrSplit6[1]) > 0) {
                            k.ac = true;
                        } else {
                            k.ac = false;
                        }
                    }
                    if (strArrSplit6[2] != null && !strArrSplit6[2].equals("")) {
                        k.ad = Integer.parseInt(strArrSplit6[2]);
                    }
                    if (strArrSplit6[3] != null && !strArrSplit6[3].equals("")) {
                        k.af = Integer.parseInt(strArrSplit6[3]);
                    }
                    if (strArrSplit6[4] != null && !strArrSplit6[4].equals("")) {
                        int i3 = Integer.parseInt(strArrSplit6[4]);
                        if (i3 > 0) {
                            k.al = i3;
                            k.ah = k.al * 1000 * 60;
                            k.am = k.ah >> 2;
                        } else {
                            k.p = false;
                        }
                    }
                    if (strArrSplit6[5] != null && !strArrSplit6[5].equals("")) {
                        k.ao = Integer.parseInt(strArrSplit6[5]);
                    }
                }
            }
            if (jSONObject.has("shak")) {
                String[] strArrSplit7 = jSONObject.getString("shak").split("\\|");
                if (strArrSplit7.length > 2) {
                    if (strArrSplit7[0] != null && !strArrSplit7[0].equals("")) {
                        k.ap = Integer.parseInt(strArrSplit7[0]);
                    }
                    if (strArrSplit7[1] != null && !strArrSplit7[1].equals("")) {
                        k.aq = Integer.parseInt(strArrSplit7[1]);
                    }
                    if (strArrSplit7[2] != null && !strArrSplit7[2].equals("")) {
                        k.ar = Float.parseFloat(strArrSplit7[2]);
                    }
                }
            }
            if (jSONObject.has("dmx")) {
                k.an = jSONObject.getInt("dmx");
            }
            return true;
        } catch (Exception unused2) {
            return false;
        }
    }

    private void b(int i2) {
        File file = new File(k);
        if (!file.exists()) {
            i();
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(4L);
            int i3 = randomAccessFile.readInt();
            int i4 = randomAccessFile.readInt();
            randomAccessFile.seek((i3 * n) + 128);
            byte[] bytes = (com.baidu.location.g.b.f2460e + (char) 0).getBytes();
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes, 0, bytes.length);
            randomAccessFile.writeInt(i2);
            if (i4 == n) {
                randomAccessFile.seek(8L);
                randomAccessFile.writeInt(i4 + 1);
            }
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        int i2;
        m = -1;
        if (str != null) {
            try {
                if (a(str)) {
                    f();
                }
            } catch (Exception unused) {
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("ctr")) {
                    m = Integer.parseInt(jSONObject.getString("ctr"));
                }
            } catch (Exception unused2) {
            }
            try {
                j();
                if (m != -1) {
                    i2 = m;
                    b(m);
                } else {
                    i2 = l != -1 ? l : -1;
                }
                if (i2 != -1) {
                    a(i2);
                }
            } catch (Exception unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String str = "&ver=" + k.y + "&usr=" + com.baidu.location.g.b.a().b() + "&app=" + com.baidu.location.g.b.f2460e + "&prod=" + com.baidu.location.g.b.f2461f;
        if (this.j == null) {
            this.j = new a();
        }
        if (k.b()) {
            return;
        }
        this.j.a(str, false);
    }

    private void f() {
        String str = j.f2498a + "/config.dat";
        byte[] bytes = String.format(Locale.CHINA, "{\"ver\":\"%d\",\"gps\":\"%.1f|%.1f|%.1f|%.1f|%d|%d|%d|%d|%d|%d|%d\",\"up\":\"%.1f|%.1f|%.1f|%.1f\",\"wf\":\"%d|%.1f|%d|%.1f\",\"ab\":\"%.2f|%.2f|%d|%d\",\"gpc\":\"%d|%d|%d|%d|%d|%d\",\"zxd\":\"%.1f|%.1f|%d|%d|%d\",\"shak\":\"%d|%d|%.1f\",\"dmx\":%d}", Integer.valueOf(k.y), Float.valueOf(k.z), Float.valueOf(k.A), Float.valueOf(k.B), Float.valueOf(k.C), Integer.valueOf(k.D), Integer.valueOf(k.E), Integer.valueOf(k.F), Integer.valueOf(k.G), Integer.valueOf(k.H), Integer.valueOf(k.I), Integer.valueOf(k.J), Float.valueOf(k.K), Float.valueOf(k.L), Float.valueOf(k.M), Float.valueOf(k.N), Integer.valueOf(k.O), Float.valueOf(k.Q), Integer.valueOf(k.R), Float.valueOf(k.S), Float.valueOf(k.T), Float.valueOf(k.U), Integer.valueOf(k.V), Integer.valueOf(k.W), Integer.valueOf(k.ab ? 1 : 0), Integer.valueOf(k.ac ? 1 : 0), Integer.valueOf(k.ad), Integer.valueOf(k.af), Long.valueOf(k.al), Integer.valueOf(k.ao), Float.valueOf(k.as), Float.valueOf(k.at), Integer.valueOf(k.au), Integer.valueOf(k.av), Integer.valueOf(k.aw), Integer.valueOf(k.ap), Integer.valueOf(k.aq), Float.valueOf(k.ar), Integer.valueOf(k.an)).getBytes();
        try {
            File file = new File(str);
            if (!file.exists()) {
                File file2 = new File(j.f2498a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(0L);
            randomAccessFile2.writeBoolean(true);
            randomAccessFile2.seek(2L);
            randomAccessFile2.writeInt(bytes.length);
            randomAccessFile2.write(bytes);
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    private void g() {
        try {
            File file = new File(j.f2498a + "/config.dat");
            if (!file.exists()) {
                File file2 = new File(j.f2498a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(1L);
            randomAccessFile2.writeBoolean(true);
            randomAccessFile2.seek(1024L);
            randomAccessFile2.writeDouble(k.t);
            randomAccessFile2.writeDouble(k.u);
            randomAccessFile2.writeBoolean(k.x);
            if (k.x && k.w != null) {
                randomAccessFile2.write(k.w);
            }
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    private void h() {
        try {
            File file = new File(j.f2498a + "/config.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(2L);
                    int i2 = randomAccessFile.readInt();
                    byte[] bArr = new byte[i2];
                    randomAccessFile.read(bArr, 0, i2);
                    a(new String(bArr));
                }
                randomAccessFile.seek(1L);
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(1024L);
                    k.t = randomAccessFile.readDouble();
                    k.u = randomAccessFile.readDouble();
                    k.x = randomAccessFile.readBoolean();
                    if (k.x) {
                        k.w = new byte[com.veryfit.multi.nativeprotocol.b.O2];
                        randomAccessFile.read(k.w, 0, com.veryfit.multi.nativeprotocol.b.O2);
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception unused) {
        }
        if (k.p) {
            boolean z = com.baidu.location.f.isServing;
        }
    }

    private void i() {
        try {
            File file = new File(k);
            if (file.exists()) {
                return;
            }
            File file2 = new File(j.f2498a);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file.createNewFile()) {
                file = null;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(128);
            randomAccessFile.writeInt(0);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    private void j() {
        try {
            File file = new File(k);
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(4L);
                int i2 = randomAccessFile.readInt();
                if (i2 > 3000) {
                    randomAccessFile.close();
                    n = 0;
                    i();
                    return;
                }
                int i3 = randomAccessFile.readInt();
                randomAccessFile.seek(128L);
                byte[] bArr = new byte[i2];
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        break;
                    }
                    randomAccessFile.seek((i2 * i4) + 128);
                    int i5 = randomAccessFile.readInt();
                    if (i5 > 0 && i5 < i2) {
                        randomAccessFile.read(bArr, 0, i5);
                        int i6 = i5 - 1;
                        if (bArr[i6] == 0) {
                            String str = new String(bArr, 0, i6);
                            com.baidu.location.g.b.a();
                            if (str.equals(com.baidu.location.g.b.f2460e)) {
                                l = randomAccessFile.readInt();
                                n = i4;
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    i4++;
                }
                if (i4 == i3) {
                    n = i3;
                }
                randomAccessFile.close();
            }
        } catch (Exception unused) {
        }
    }

    public void b() {
        h();
    }

    public void c() {
    }

    public void d() {
        if (System.currentTimeMillis() - com.baidu.location.g.c.a().d() > 604800000) {
            com.baidu.location.g.c.a().c(System.currentTimeMillis());
            com.baidu.location.f.a.a().postDelayed(new f(this), 1000L);
        }
    }
}