package com.baidu.location.a;

import android.app.ActivityManager;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import androidx.core.view.ViewCompat;
import com.baidu.location.Jni;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.util.DateUtil;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.DebugKt;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f2078f = "0";
    private static d j;
    private Handler I;
    private int k = 1;
    private double l = 0.699999988079071d;
    private String m = "3G|4G";
    private int n = 1;
    private int o = 307200;
    private int p = 15;
    private int q = 1;
    private double r = 3.5d;
    private double s = 3.0d;
    private double t = 0.5d;
    private int u = 300;
    private int v = 60;
    private int w = 0;
    private int x = 60;
    private int y = 0;
    private long z = 0;
    private a A = null;
    private boolean B = false;
    private boolean C = false;
    private int D = 0;
    private float E = 0.0f;
    private float F = 0.0f;
    private long G = 0;
    private int H = 500;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f2079a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Location f2080b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Location f2081c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    StringBuilder f2082d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f2083e = 0;
    private byte[] J = new byte[4];
    private byte[] K = null;
    private int L = 0;
    private List<Byte> M = null;
    private boolean N = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f2084g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    double f2085h = 116.22345545d;
    double i = 40.245667323d;

    class a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f2086a = null;

        public a() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.f2489h = "http://loc.map.baidu.com/cc.php";
            String strEncode = Jni.encode(this.f2086a);
            this.f2086a = null;
            this.k.put("q", strEncode);
        }

        public void a(String str) {
            this.f2086a = str;
            b(v.a().c());
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    jSONObject.put("prod", com.baidu.location.g.b.f2460e);
                    jSONObject.put("uptime", System.currentTimeMillis());
                    d.this.e(jSONObject.toString());
                } catch (Exception unused) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    private d() {
        this.I = null;
        this.I = new Handler();
    }

    public static d a() {
        if (j == null) {
            j = new d();
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(File file, String str) {
        String string = UUID.randomUUID().toString();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", "utf-8");
            httpURLConnection.setRequestProperty("connection", "close");
            httpURLConnection.setRequestProperty("Content-Type", FileUploadBase.MULTIPART_FORM_DATA + ";boundary=" + string);
            if (file == null || !file.exists()) {
                return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            }
            OutputStream outputStream = httpURLConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("--");
            stringBuffer.append(string);
            stringBuffer.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            stringBuffer.append("Content-Disposition: form-data; name=\"location_dat\"; filename=\"" + file.getName() + "\"" + IOUtils.LINE_SEPARATOR_WINDOWS);
            StringBuilder sb = new StringBuilder();
            sb.append("Content-Type: application/octet-stream; charset=utf-8");
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            stringBuffer.append(sb.toString());
            stringBuffer.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            dataOutputStream.write(stringBuffer.toString().getBytes());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                dataOutputStream.write(bArr, 0, i);
            }
            fileInputStream.close();
            dataOutputStream.write(IOUtils.LINE_SEPARATOR_WINDOWS.getBytes());
            dataOutputStream.write(("--" + string + "--" + IOUtils.LINE_SEPARATOR_WINDOWS).getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();
            int responseCode = httpURLConnection.getResponseCode();
            outputStream.close();
            httpURLConnection.disconnect();
            this.y += 400;
            c(this.y);
            return responseCode == 200 ? "1" : AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        } catch (MalformedURLException | IOException unused) {
            return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
    }

    private boolean a(String str, Context context) {
        boolean z = false;
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.processName.equals(str)) {
                        int i = runningAppProcessInfo.importance;
                        if (i == 200 || i == 100) {
                            z = true;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return z;
    }

    private byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24)};
    }

    private byte[] a(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte bNextInt = (byte) new Random().nextInt(255);
        byte bNextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[bytes.length + 2];
        int length = bytes.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) (bytes[i] ^ bNextInt);
            i++;
            i2++;
        }
        bArr[i2] = bNextInt;
        bArr[i2 + 1] = bNextInt2;
        return bArr;
    }

    private String b(String str) {
        Calendar calendar = Calendar.getInstance();
        return String.format(str, Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)));
    }

    private void b(int i) {
        byte[] bArrA = a(i);
        for (int i2 = 0; i2 < 4; i2++) {
            this.M.add(Byte.valueOf(bArrA[i2]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Location location) {
        c(location);
        h();
    }

    private void c() {
        if (this.N) {
            return;
        }
        this.N = true;
        d(com.baidu.location.g.b.f2460e);
        j();
        d();
    }

    private void c(int i) {
        if (i == 0) {
            return;
        }
        try {
            File file = new File(com.baidu.location.g.j.f2498a + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(com.baidu.location.g.j.f2498a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2L);
                randomAccessFile.writeInt(0);
                randomAccessFile.seek(8L);
                byte[] bytes = "1980_01_01:0".getBytes();
                randomAccessFile.writeInt(bytes.length);
                randomAccessFile.write(bytes);
                randomAccessFile.seek(200L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.seek(800L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(8L);
            byte[] bytes2 = (b("%d_%02d_%02d") + ":" + i).getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    private void c(Location location) {
        if (System.currentTimeMillis() - this.f2079a < this.H || location == null) {
            return;
        }
        if (location != null && location.hasSpeed() && location.getSpeed() > this.E) {
            this.E = location.getSpeed();
        }
        try {
            if (this.M == null) {
                this.M = new ArrayList();
                i();
                d(location);
            } else {
                e(location);
            }
        } catch (Exception unused) {
        }
        this.L++;
    }

    private void c(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(DebugKt.DEBUG_PROPERTY_VALUE_ON)) {
                    this.k = jSONObject.getInt(DebugKt.DEBUG_PROPERTY_VALUE_ON);
                }
                if (jSONObject.has("bash")) {
                    this.l = jSONObject.getDouble("bash");
                }
                if (jSONObject.has("net")) {
                    this.m = jSONObject.getString("net");
                }
                if (jSONObject.has("tcon")) {
                    this.n = jSONObject.getInt("tcon");
                }
                if (jSONObject.has("tcsh")) {
                    this.o = jSONObject.getInt("tcsh");
                }
                if (jSONObject.has("per")) {
                    this.p = jSONObject.getInt("per");
                }
                if (jSONObject.has("chdron")) {
                    this.q = jSONObject.getInt("chdron");
                }
                if (jSONObject.has("spsh")) {
                    this.r = jSONObject.getDouble("spsh");
                }
                if (jSONObject.has("acsh")) {
                    this.s = jSONObject.getDouble("acsh");
                }
                if (jSONObject.has("stspsh")) {
                    this.t = jSONObject.getDouble("stspsh");
                }
                if (jSONObject.has("drstsh")) {
                    this.u = jSONObject.getInt("drstsh");
                }
                if (jSONObject.has("stper")) {
                    this.v = jSONObject.getInt("stper");
                }
                if (jSONObject.has("nondron")) {
                    this.w = jSONObject.getInt("nondron");
                }
                if (jSONObject.has("nondrper")) {
                    this.x = jSONObject.getInt("nondrper");
                }
                if (jSONObject.has("uptime")) {
                    this.z = jSONObject.getLong("uptime");
                }
                k();
            } catch (JSONException unused) {
            }
        }
    }

    private void d() {
        String[] strArrSplit = "7.7.2".split("\\.");
        int length = strArrSplit.length;
        byte[] bArr = this.J;
        bArr[0] = 0;
        bArr[1] = 0;
        bArr[2] = 0;
        bArr[3] = 0;
        if (length >= 4) {
            length = 4;
        }
        for (int i = 0; i < length; i++) {
            try {
                this.J[i] = (byte) (Integer.valueOf(strArrSplit[i]).intValue() & 255);
            } catch (Exception unused) {
            }
        }
        this.K = a(com.baidu.location.g.b.f2460e + ":" + com.baidu.location.g.b.a().f2466c);
    }

    private void d(Location location) {
        this.f2083e = System.currentTimeMillis();
        b((int) (location.getTime() / 1000));
        b((int) (location.getLongitude() * 1000000.0d));
        b((int) (location.getLatitude() * 1000000.0d));
        int i = !location.hasBearing() ? 1 : 0;
        int i2 = !location.hasSpeed() ? 1 : 0;
        this.M.add(Byte.valueOf(i > 0 ? (byte) 32 : (byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & (-33))));
        this.M.add(Byte.valueOf(i2 > 0 ? ByteCompanionObject.MIN_VALUE : (byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & ByteCompanionObject.MAX_VALUE)));
        this.f2080b = location;
    }

    private void d(String str) {
        try {
            File file = new File(com.baidu.location.g.j.f2498a + "/grtcf.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2L);
                int i = randomAccessFile.readInt();
                randomAccessFile.seek(8L);
                int i2 = randomAccessFile.readInt();
                byte[] bArr = new byte[i2];
                randomAccessFile.read(bArr, 0, i2);
                String str2 = new String(bArr);
                int i3 = 1;
                if (str2.contains(b("%d_%02d_%02d")) && str2.contains(":")) {
                    try {
                        String[] strArrSplit = str2.split(":");
                        if (strArrSplit.length > 1) {
                            this.y = Integer.valueOf(strArrSplit[1]).intValue();
                        }
                    } catch (Exception unused) {
                    }
                }
                while (true) {
                    if (i3 > i) {
                        break;
                    }
                    randomAccessFile.seek(i3 * 2048);
                    int i4 = randomAccessFile.readInt();
                    byte[] bArr2 = new byte[i4];
                    randomAccessFile.read(bArr2, 0, i4);
                    String str3 = new String(bArr2);
                    if (str != null && str3.contains(str)) {
                        c(str3);
                        break;
                    }
                    i3++;
                }
                randomAccessFile.close();
            }
        } catch (Exception unused2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0119 A[PHI: r2
  0x0119: PHI (r2v21 byte) = (r2v18 byte), (r2v30 byte) binds: [B:37:0x0117, B:32:0x0101] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e(android.location.Location r22) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.d.e(android.location.Location):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        try {
            File file = new File(com.baidu.location.g.j.f2498a + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(com.baidu.location.g.j.f2498a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2L);
                randomAccessFile.writeInt(0);
                randomAccessFile.seek(8L);
                byte[] bytes = "1980_01_01:0".getBytes();
                randomAccessFile.writeInt(bytes.length);
                randomAccessFile.write(bytes);
                randomAccessFile.seek(200L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.seek(800L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(2L);
            int i = randomAccessFile2.readInt();
            int i2 = 1;
            while (i2 <= i) {
                randomAccessFile2.seek(i2 * 2048);
                int i3 = randomAccessFile2.readInt();
                byte[] bArr = new byte[i3];
                randomAccessFile2.read(bArr, 0, i3);
                if (new String(bArr).contains(com.baidu.location.g.b.f2460e)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 >= i) {
                randomAccessFile2.seek(2L);
                randomAccessFile2.writeInt(i2);
            }
            randomAccessFile2.seek(i2 * 2048);
            byte[] bytes2 = str.getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    private boolean e() throws Throwable {
        RandomAccessFile randomAccessFile;
        FileChannel fileChannel = null;
        FileLock fileLockTryLock = null;
        fileChannel = null;
        RandomAccessFile randomAccessFile2 = null;
        boolean z = false;
        try {
            try {
                File file = new File(com.baidu.location.g.k.i() + File.separator + "gflk.dat");
                if (!file.exists()) {
                    file.createNewFile();
                }
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
            }
            try {
                FileChannel channel = randomAccessFile.getChannel();
                try {
                    fileLockTryLock = channel.tryLock();
                } catch (Exception unused2) {
                    z = true;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = channel;
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (Exception unused3) {
                            throw th;
                        }
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
                if (fileLockTryLock != null) {
                    fileLockTryLock.release();
                }
                if (channel != null) {
                    channel.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Exception unused4) {
                randomAccessFile2 = randomAccessFile;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception unused5) {
        }
        return z;
    }

    private boolean f() {
        if (this.B) {
            if (!this.C) {
                if (this.E >= this.t) {
                    return true;
                }
                this.C = true;
                this.D = 0;
                this.D += this.p;
                return true;
            }
            if (this.E >= this.t) {
                this.D = 0;
                this.C = false;
                return true;
            }
            this.D += this.p;
            if (this.D <= this.u || System.currentTimeMillis() - this.G > this.v * 1000) {
                return true;
            }
        } else {
            if (this.E >= this.r || this.F >= this.s) {
                this.B = true;
                return true;
            }
            if (this.w == 1 && System.currentTimeMillis() - this.G > this.x * 1000) {
                return true;
            }
        }
        return false;
    }

    private void g() {
        this.M = null;
        this.f2083e = 0L;
        this.L = 0;
        this.f2080b = null;
        this.f2081c = null;
        this.E = 0.0f;
        this.F = 0.0f;
    }

    private void h() {
        if (this.f2083e == 0 || System.currentTimeMillis() - this.f2083e < this.p * 1000) {
            return;
        }
        if (com.baidu.location.f.getServiceContext().getSharedPreferences("loc_navi_mode", 4).getBoolean("is_navi_on", false)) {
            g();
            return;
        }
        if (this.n == 1 && !f()) {
            g();
            return;
        }
        if (com.baidu.location.g.b.f2460e.equals("com.ubercab.driver")) {
            if (e()) {
                g();
                return;
            }
        } else if (!a(com.baidu.location.g.b.f2460e, com.baidu.location.f.getServiceContext())) {
            g();
            return;
        }
        List<Byte> list = this.M;
        if (list != null) {
            int size = list.size();
            this.M.set(0, Byte.valueOf((byte) (size & 255)));
            this.M.set(1, Byte.valueOf((byte) ((65280 & size) >> 8)));
            this.M.set(3, Byte.valueOf((byte) (this.L & 255)));
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = this.M.get(i).byteValue();
            }
            File file = new File(com.baidu.location.g.k.k(), "baidu/tempdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (file.exists()) {
                File file2 = new File(file, "intime.dat");
                if (file2.exists()) {
                    file2.delete();
                }
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                    bufferedOutputStream.write(bArr);
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    new f(this).start();
                } catch (Exception unused) {
                }
            }
            g();
            this.G = System.currentTimeMillis();
        }
    }

    private void i() {
        List<Byte> list;
        byte b2;
        this.M.add((byte) 0);
        this.M.add((byte) 0);
        if (f2078f.equals(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE)) {
            list = this.M;
            b2 = -82;
        } else {
            list = this.M;
            b2 = -66;
        }
        list.add(Byte.valueOf(b2));
        this.M.add((byte) 0);
        this.M.add(Byte.valueOf(this.J[0]));
        this.M.add(Byte.valueOf(this.J[1]));
        this.M.add(Byte.valueOf(this.J[2]));
        this.M.add(Byte.valueOf(this.J[3]));
        int length = this.K.length;
        this.M.add(Byte.valueOf((byte) ((length + 1) & 255)));
        for (int i = 0; i < length; i++) {
            this.M.add(Byte.valueOf(this.K[i]));
        }
    }

    private void j() {
        if (System.currentTimeMillis() - this.z > DateUtil.DAY) {
            if (this.A == null) {
                this.A = new a();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(com.baidu.location.g.b.a().a(false));
            stringBuffer.append(com.baidu.location.a.a.a().d());
            this.A.a(stringBuffer.toString());
        }
        k();
    }

    private void k() {
    }

    public void a(Location location) {
        if (!this.N) {
            c();
        }
        boolean z = ((double) com.baidu.location.b.d.a().f()) < this.l * 100.0d;
        if (this.k == 1 && z && this.m.contains(com.baidu.location.e.d.a(com.baidu.location.e.b.a().e()))) {
            if (this.n != 1 || this.y <= this.o) {
                this.I.post(new e(this, location));
            }
        }
    }

    public void b() {
        if (this.N) {
            this.N = false;
            g();
        }
    }
}