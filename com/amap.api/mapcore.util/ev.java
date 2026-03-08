package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.amap.api.mapcore.util.ig;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.HashMap;
import kotlin.UByte;

/* JADX INFO: compiled from: ImageCache.java */
/* JADX INFO: loaded from: classes.dex */
public class ev {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Bitmap.CompressFormat f778a = Bitmap.CompressFormat.PNG;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ig f779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ed<String, Bitmap> f780c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f781d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Object f782e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f783f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private HashMap<String, WeakReference<Bitmap>> f784g;

    private ev(a aVar) {
        b(aVar);
    }

    public static ev a(a aVar) {
        return new ev(aVar);
    }

    private void b(a aVar) {
        this.f781d = aVar;
        if (this.f781d.f791f) {
            if (ew.a()) {
                this.f784g = new HashMap<>(64);
            }
            this.f780c = new ed<String, Bitmap>(this.f781d.f786a) { // from class: com.amap.api.mapcore.util.ev.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.amap.api.mapcore.util.ed
                public void a(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
                    if (!er.c() || ev.this.f784g == null || bitmap == null || bitmap.isRecycled()) {
                        return;
                    }
                    ev.this.f784g.put(str, new WeakReference(bitmap));
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.amap.api.mapcore.util.ed
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public int b(String str, Bitmap bitmap) {
                    int iA = ev.a(bitmap);
                    if (iA == 0) {
                        return 1;
                    }
                    return iA;
                }
            };
        }
        if (aVar.f793h) {
            a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004e A[Catch: all -> 0x0058, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0007, B:23:0x004e, B:24:0x0056, B:8:0x000f, B:17:0x0031, B:22:0x0049, B:20:0x003d), top: B:33:0x0003, inners: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.f782e
            monitor-enter(r0)
            com.amap.api.mapcore.util.ig r1 = r6.f779b     // Catch: java.lang.Throwable -> L58
            if (r1 == 0) goto Lf
            com.amap.api.mapcore.util.ig r1 = r6.f779b     // Catch: java.lang.Throwable -> L58
            boolean r1 = r1.d()     // Catch: java.lang.Throwable -> L58
            if (r1 == 0) goto L4e
        Lf:
            com.amap.api.mapcore.util.ev$a r1 = r6.f781d     // Catch: java.lang.Throwable -> L58
            java.io.File r1 = r1.f788c     // Catch: java.lang.Throwable -> L58
            com.amap.api.mapcore.util.ev$a r2 = r6.f781d     // Catch: java.lang.Throwable -> L58
            boolean r2 = r2.f792g     // Catch: java.lang.Throwable -> L58
            if (r2 == 0) goto L4e
            if (r1 == 0) goto L4e
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L31
            if (r2 == 0) goto L2e
            com.amap.api.mapcore.util.ev$a r2 = r6.f781d     // Catch: java.lang.Throwable -> L31
            boolean r2 = r2.i     // Catch: java.lang.Throwable -> L31
            if (r2 == 0) goto L31
            r6.b(r1)     // Catch: java.lang.Throwable -> L31
            r1.mkdir()     // Catch: java.lang.Throwable -> L31
            goto L31
        L2e:
            r1.mkdirs()     // Catch: java.lang.Throwable -> L31
        L31:
            long r2 = a(r1)     // Catch: java.lang.Throwable -> L58
            com.amap.api.mapcore.util.ev$a r4 = r6.f781d     // Catch: java.lang.Throwable -> L58
            long r4 = r4.f787b     // Catch: java.lang.Throwable -> L58
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L4e
            com.amap.api.mapcore.util.ev$a r2 = r6.f781d     // Catch: java.lang.Throwable -> L49
            long r2 = r2.f787b     // Catch: java.lang.Throwable -> L49
            r4 = 1
            com.amap.api.mapcore.util.ig r1 = com.amap.api.mapcore.util.ig.a(r1, r4, r4, r2)     // Catch: java.lang.Throwable -> L49
            r6.f779b = r1     // Catch: java.lang.Throwable -> L49
            goto L4e
        L49:
            com.amap.api.mapcore.util.ev$a r1 = r6.f781d     // Catch: java.lang.Throwable -> L58
            r2 = 0
            r1.f788c = r2     // Catch: java.lang.Throwable -> L58
        L4e:
            r1 = 0
            r6.f783f = r1     // Catch: java.lang.Throwable -> L58
            java.lang.Object r1 = r6.f782e     // Catch: java.lang.Throwable -> L58
            r1.notifyAll()     // Catch: java.lang.Throwable -> L58
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L58
            return
        L58:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L58
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ev.a():void");
    }

    private void b(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    public void a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null || bitmap.isRecycled()) {
            return;
        }
        ed<String, Bitmap> edVar = this.f780c;
        if (edVar != null) {
            edVar.a(str, bitmap);
        }
        synchronized (this.f782e) {
            if (this.f779b != null) {
                String strC = c(str);
                OutputStream outputStreamA = null;
                try {
                    ig.b bVarA = this.f779b.a(strC);
                    if (bVarA == null) {
                        ig.a aVarB = this.f779b.b(strC);
                        if (aVarB != null) {
                            outputStreamA = aVarB.a(0);
                            bitmap.compress(this.f781d.f789d, this.f781d.f790e, outputStreamA);
                            aVarB.a();
                            outputStreamA.close();
                        }
                    } else {
                        bVarA.a(0).close();
                    }
                } catch (Throwable unused) {
                    if (0 != 0) {
                    }
                }
                if (outputStreamA != null) {
                    try {
                        outputStreamA.close();
                    } catch (Throwable unused2) {
                    }
                }
            }
        }
    }

    public Bitmap a(String str) {
        Bitmap bitmapA;
        ed<String, Bitmap> edVar;
        HashMap<String, WeakReference<Bitmap>> map;
        WeakReference<Bitmap> weakReference;
        if (!er.c() || (map = this.f784g) == null || (weakReference = map.get(str)) == null) {
            bitmapA = null;
        } else {
            bitmapA = weakReference.get();
            if (bitmapA == null || bitmapA.isRecycled()) {
                bitmapA = null;
            }
            this.f784g.remove(str);
        }
        if (bitmapA == null && (edVar = this.f780c) != null) {
            bitmapA = edVar.a(str);
        }
        if (bitmapA == null || bitmapA.isRecycled()) {
            return null;
        }
        return bitmapA;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0038 A[EXC_TOP_SPLITTER, PHI: r2 r5
  0x0038: PHI (r2v1 android.graphics.Bitmap) = (r2v0 android.graphics.Bitmap), (r2v2 android.graphics.Bitmap) binds: [B:22:0x003d, B:18:0x0036] A[DONT_GENERATE, DONT_INLINE]
  0x0038: PHI (r5v5 java.io.InputStream) = (r5v4 java.io.InputStream), (r5v8 java.io.InputStream) binds: [B:22:0x003d, B:18:0x0036] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap b(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r5 = c(r5)
            java.lang.Object r0 = r4.f782e
            monitor-enter(r0)
        L7:
            boolean r1 = r4.f783f     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L11
            java.lang.Object r1 = r4.f782e     // Catch: java.lang.Throwable -> L7
            r1.wait()     // Catch: java.lang.Throwable -> L7
            goto L7
        L11:
            com.amap.api.mapcore.util.ig r1 = r4.f779b     // Catch: java.lang.Throwable -> L42
            r2 = 0
            if (r1 == 0) goto L40
            com.amap.api.mapcore.util.ig r1 = r4.f779b     // Catch: java.lang.Throwable -> L3c
            com.amap.api.mapcore.util.ig$b r5 = r1.a(r5)     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L35
            r1 = 0
            java.io.InputStream r5 = r5.a(r1)     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L36
            r1 = r5
            java.io.FileInputStream r1 = (java.io.FileInputStream) r1     // Catch: java.lang.Throwable -> L3d
            java.io.FileDescriptor r1 = r1.getFD()     // Catch: java.lang.Throwable -> L3d
            r3 = 2147483647(0x7fffffff, float:NaN)
            android.graphics.Bitmap r1 = com.amap.api.mapcore.util.et.a(r1, r3, r3, r4)     // Catch: java.lang.Throwable -> L3d
            r2 = r1
            goto L36
        L35:
            r5 = r2
        L36:
            if (r5 == 0) goto L40
        L38:
            r5.close()     // Catch: java.lang.Throwable -> L40
            goto L40
        L3c:
            r5 = r2
        L3d:
            if (r5 == 0) goto L40
            goto L38
        L40:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L42
            return r2
        L42:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L42
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ev.b(java.lang.String):android.graphics.Bitmap");
    }

    public void b() {
        HashMap<String, WeakReference<Bitmap>> map;
        if (er.c() && (map = this.f784g) != null) {
            map.clear();
        }
        ed<String, Bitmap> edVar = this.f780c;
        if (edVar != null) {
            edVar.a();
        }
        synchronized (this.f782e) {
            this.f783f = true;
            if (this.f779b != null && !this.f779b.d()) {
                try {
                    this.f779b.close();
                    b(a(u.f1791a, this.f781d.j, null));
                } catch (Throwable unused) {
                }
                this.f779b = null;
                a();
            }
        }
    }

    public void c() {
        synchronized (this.f782e) {
            if (this.f779b != null) {
                try {
                    this.f779b.e();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public void a(boolean z) {
        HashMap<String, WeakReference<Bitmap>> map;
        if (er.c() && (map = this.f784g) != null) {
            map.clear();
        }
        ed<String, Bitmap> edVar = this.f780c;
        if (edVar != null) {
            edVar.a();
        }
        synchronized (this.f782e) {
            if (this.f779b != null) {
                try {
                    if (!this.f779b.d()) {
                        if (z) {
                            this.f779b.f();
                        } else {
                            this.f779b.close();
                        }
                        this.f779b = null;
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* JADX INFO: compiled from: ImageCache.java */
    public static class a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public File f788c;
        public String j;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f786a = 5242880;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f787b = 10485760;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bitmap.CompressFormat f789d = ev.f778a;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f790e = 100;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f791f = true;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f792g = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f793h = false;
        public boolean i = true;

        public a(Context context, String str) {
            this.j = null;
            this.j = str;
            this.f788c = ev.a(context, str, null);
        }

        public a(Context context, String str, String str2) {
            this.j = null;
            this.j = str;
            this.f788c = ev.a(context, str, str2);
        }

        public void a(int i) {
            this.f786a = i;
        }

        public void a(long j) {
            if (j <= 0) {
                this.f792g = false;
            }
            this.f787b = j;
        }

        public void a(String str) {
            this.f788c = new File(str);
        }

        public void a(boolean z) {
            this.f791f = z;
        }

        public void b(boolean z) {
            this.f792g = z;
        }

        public void b(String str) {
            this.f788c = ev.a(u.f1791a, this.j, str);
        }
    }

    public static File a(Context context, String str, String str2) {
        String path;
        File fileA = a(context);
        if (("mounted".equals(Environment.getExternalStorageState()) || !d()) && fileA != null) {
            path = fileA.getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append(File.separator);
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(File.separator);
            sb.append(str2);
        }
        return new File(sb.toString());
    }

    public static String c(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("utf-8"));
            return a(messageDigest.digest());
        } catch (Throwable unused) {
            return String.valueOf(str.hashCode());
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static int a(Bitmap bitmap) {
        if (er.d()) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static boolean d() {
        if (er.b()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static File a(Context context) {
        try {
            if (er.a()) {
                return context.getExternalCacheDir();
            }
            return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static long a(File file) {
        if (er.b()) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }
}