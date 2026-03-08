package com.baidu.location.indoor.mapversion.c;

import android.content.Context;
import android.location.Location;
import com.baidu.location.BDLocation;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f2647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f2648b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2649c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f2651e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f2652f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2650d = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f2653g = CoordinateType.GCJ02;
    private d j = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private HashMap<String, d> f2654h = new HashMap<>();
    private HashMap<String, double[][]> i = new HashMap<>();

    /* JADX INFO: renamed from: com.baidu.location.indoor.mapversion.c.a$a, reason: collision with other inner class name */
    public static class C0026a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f2655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double f2656b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public double f2657c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double f2658d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f2659e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f2660f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f2661g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public double f2662h;

        public C0026a(String str) {
            a(str);
        }

        public void a(String str) {
            String[] strArrSplit = str.trim().split("\\|");
            this.f2655a = Double.valueOf(strArrSplit[0]).doubleValue();
            this.f2656b = Double.valueOf(strArrSplit[1]).doubleValue();
            this.f2657c = Double.valueOf(strArrSplit[2]).doubleValue();
            this.f2658d = Double.valueOf(strArrSplit[3]).doubleValue();
            this.f2659e = Double.valueOf(strArrSplit[4]).doubleValue();
            this.f2660f = Double.valueOf(strArrSplit[5]).doubleValue();
            this.f2661g = Double.valueOf(strArrSplit[6]).doubleValue();
            this.f2662h = Double.valueOf(strArrSplit[7]).doubleValue();
        }
    }

    private class b extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2664b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2665c;

        public b(String str, String str2) {
            this.f2664b = str;
            this.f2665c = str2;
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x011d A[Catch: Exception -> 0x0127, TRY_LEAVE, TryCatch #0 {Exception -> 0x0127, blocks: (B:3:0x0001, B:5:0x0012, B:8:0x001b, B:12:0x005c, B:14:0x0073, B:15:0x0093, B:17:0x0099, B:18:0x009d, B:20:0x00ad, B:29:0x0115, B:31:0x011d, B:21:0x00c7, B:24:0x00d2, B:27:0x0101, B:11:0x005a, B:7:0x0018), top: B:37:0x0001 }] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 305
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.mapversion.c.a.b.run():void");
        }
    }

    public interface c {
        void a(boolean z, String str);
    }

    public static class d implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2666a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2667b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public C0026a f2668c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public C0026a f2670e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public short[][] f2672g;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public C0026a f2669d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public C0026a f2671f = this.f2669d;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f2673h = CoordinateType.GCJ02;

        public d(String str) {
            this.f2666a = str;
        }

        public double a(double d2) {
            return (d2 + this.f2671f.f2658d) * this.f2671f.f2657c;
        }

        public C0026a a() {
            return this.f2671f;
        }

        public void a(String str) {
            C0026a c0026a;
            if (str != null) {
                this.f2673h = str.toLowerCase();
                if (this.f2673h.startsWith(CoordinateType.WGS84)) {
                    c0026a = this.f2668c;
                } else if (this.f2673h.startsWith(BDLocation.BDLOCATION_GCJ02_TO_BD09)) {
                    c0026a = this.f2670e;
                } else if (!this.f2673h.startsWith(CoordinateType.GCJ02)) {
                    return;
                } else {
                    c0026a = this.f2669d;
                }
                this.f2671f = c0026a;
            }
        }

        public double b(double d2) {
            return (d2 + this.f2671f.f2660f) * this.f2671f.f2659e;
        }

        public void b(String str) {
            String[] strArrSplit = str.split("\\t");
            this.f2667b = strArrSplit[1];
            this.f2668c = new C0026a(strArrSplit[2]);
            this.f2670e = new C0026a(strArrSplit[3]);
            this.f2669d = new C0026a(strArrSplit[4]);
            this.f2671f = this.f2669d;
            this.f2672g = (short[][]) Array.newInstance((Class<?>) short.class, (int) this.f2671f.f2661g, (int) this.f2671f.f2662h);
            for (int i = 0; i < this.f2671f.f2661g; i++) {
                for (int i2 = 0; i2 < this.f2671f.f2662h; i2++) {
                    this.f2672g[i][i2] = (short) (strArrSplit[5].charAt((((int) this.f2671f.f2662h) * i) + i2) - '0');
                }
            }
        }

        public double c(double d2) {
            return (d2 / this.f2671f.f2657c) - this.f2671f.f2658d;
        }

        public double d(double d2) {
            return (d2 / this.f2671f.f2659e) - this.f2671f.f2660f;
        }
    }

    private a(Context context) {
        this.f2651e = "rn";
        this.f2651e = new File(context.getCacheDir(), this.f2651e).getAbsolutePath();
    }

    public static a a() {
        return f2647a;
    }

    public static a a(Context context) {
        if (f2647a == null) {
            f2647a = new a(context);
        }
        return f2647a;
    }

    public static String a(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(map);
            String string = new BigInteger(1, messageDigest.digest()).toString(16);
            fileInputStream.close();
            for (int length = 32 - string.length(); length > 0; length--) {
                string = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + string;
            }
            return string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2) {
        return d(str) + "_" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2) {
        try {
            File file = new File(this.f2651e + "/" + a(str, str2));
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        String str = this.f2649c;
        if (str == null) {
            return false;
        }
        File fileF = f(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!com.baidu.location.indoor.mapversion.c.d.a(fileF, byteArrayOutputStream)) {
            return false;
        }
        this.f2654h.clear();
        this.i.clear();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.split("\\t")[1].split("_")[0].equals("geo")) {
                    j(line);
                } else {
                    d dVar = new d(this.f2649c);
                    dVar.b(line);
                    dVar.a(this.f2653g);
                    this.f2654h.put(dVar.f2667b.toLowerCase(), dVar);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return true;
        }
        bufferedReader.close();
        return true;
    }

    private String e(String str) {
        File file = new File(this.f2651e);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles(new com.baidu.location.indoor.mapversion.c.b(this, str));
            if (fileArrListFiles != null && fileArrListFiles.length == 1) {
                String[] strArrSplit = fileArrListFiles[0].getName().split("_");
                if (strArrSplit.length < 2) {
                    return null;
                }
                return strArrSplit[1];
            }
            for (int i = 0; fileArrListFiles != null && i < fileArrListFiles.length; i++) {
                fileArrListFiles[i].delete();
            }
        }
        return null;
    }

    private File f(String str) {
        return new File(this.f2651e + "/" + a(str, e(str)));
    }

    private boolean g(String str) {
        File fileF = f(str);
        return fileF.exists() && fileF.length() > 0;
    }

    private boolean h(String str) {
        return System.currentTimeMillis() - f(str).lastModified() > 1296000000;
    }

    private ArrayList<Double> i(String str) {
        double dDoubleValue;
        ArrayList<Double> arrayList = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == ',') {
                int i2 = i + 1;
                i += 2;
                dDoubleValue = Integer.valueOf(str.substring(i2, i)).intValue();
            } else if (str.charAt(i) == '.') {
                int i3 = i + 1;
                i += 4;
                dDoubleValue = Double.valueOf(str.substring(i3, i)).doubleValue();
            } else {
                int i4 = i + 2;
                double dIntValue = Integer.valueOf(str.substring(i, i4)).intValue();
                i = i4;
                dDoubleValue = dIntValue;
            }
            arrayList.add(Double.valueOf(dDoubleValue));
        }
        return arrayList;
    }

    private void j(String str) {
        String[] strArrSplit = str.split("\\t");
        String lowerCase = strArrSplit[1].split("_")[1].toLowerCase();
        try {
            if (this.f2654h.containsKey(lowerCase)) {
                ArrayList<Double> arrayListI = i(strArrSplit[5]);
                int length = this.f2654h.get(lowerCase).f2672g.length;
                int length2 = this.f2654h.get(lowerCase).f2672g[0].length;
                double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2;
                    for (int i4 = 0; i4 < length2; i4++) {
                        if (this.f2654h.get(lowerCase).f2672g[i][i4] <= 0 || this.f2654h.get(lowerCase).f2672g[i][i4] == 9) {
                            dArr[i][i4] = 0.0d;
                        } else {
                            dArr[i][i4] = arrayListI.get(i3).doubleValue();
                            i3++;
                        }
                    }
                    i++;
                    i2 = i3;
                }
                this.i.put(lowerCase.toLowerCase(), dArr);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void k(String str) {
        if (this.f2650d) {
            return;
        }
        this.f2650d = true;
        this.f2652f = new b(str, e(str));
        this.f2652f.start();
    }

    public void a(double d2, double d3) {
        if (this.j == null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            Location.distanceBetween(d3, d2, d3, d2 + 0.01d, fArr);
            double d4 = ((double) fArr[0]) / 0.01d;
            Location.distanceBetween(d3, d2, d3 + 0.01d, d2, fArr2);
            double d5 = ((double) fArr2[0]) / 0.01d;
            this.j = new d("outdoor");
            d dVar = this.j;
            dVar.f2667b = "out";
            dVar.f2671f = new C0026a("0|1.0|" + d4 + "|" + (-d2) + "|" + d5 + "|" + (-d3) + "|0|0");
        }
    }

    public void a(String str) {
        this.f2653g = str;
    }

    public void a(String str, c cVar) {
        String str2 = this.f2649c;
        if (str2 == null || !str.equals(str2)) {
            this.f2648b = cVar;
            if (!g(str) || h(str)) {
                k(str);
                return;
            }
            this.f2649c = str;
            d();
            c cVar2 = this.f2648b;
            if (cVar2 != null) {
                cVar2.a(true, "OK");
            }
        }
    }

    public d b(String str) {
        return this.f2654h.get(str.toLowerCase());
    }

    public void b() {
        this.f2654h.clear();
        this.i.clear();
        this.f2649c = null;
        this.f2650d = false;
    }

    public d c() {
        return this.j;
    }

    public double[][] c(String str) {
        return this.i.get(str.toLowerCase());
    }
}