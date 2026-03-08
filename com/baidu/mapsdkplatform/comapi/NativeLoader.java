package com.baidu.mapsdkplatform.comapi;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class NativeLoader {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f3392b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static NativeLoader f3395e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3391a = NativeLoader.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Set<String> f3393c = new HashSet();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Set<String> f3394d = new HashSet();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static a f3396f = a.ARMEABI;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f3397g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f3398h = null;

    /* JADX INFO: Access modifiers changed from: private */
    enum a {
        ARMEABI("armeabi"),
        ARMV7("armeabi-v7a"),
        ARM64("arm64-v8a"),
        X86("x86"),
        X86_64("x86_64");


        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f3405f;

        a(String str) {
            this.f3405f = str;
        }

        public String a() {
            return this.f3405f;
        }
    }

    private NativeLoader() {
    }

    private String a() {
        return 8 <= Build.VERSION.SDK_INT ? f3392b.getPackageCodePath() : "";
    }

    private String a(a aVar) {
        return "lib/" + aVar.a() + "/";
    }

    private void a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, i);
                }
            } finally {
            }
        }
        fileOutputStream.flush();
        try {
            inputStream.close();
        } catch (IOException e2) {
            Log.e(f3391a, "Close InputStream error", e2);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e3) {
            Log.e(f3391a, "Close OutputStream error", e3);
        }
    }

    private void a(Throwable th) {
        Log.e(f3391a, "loadException", th);
        for (String str : f3394d) {
            Log.e(f3391a, str + " Failed to load.");
        }
    }

    static void a(boolean z, String str) {
        f3397g = z;
        f3398h = str;
    }

    private boolean a(String str) {
        try {
            synchronized (f3393c) {
                if (f3393c.contains(str)) {
                    return true;
                }
                System.loadLibrary(str);
                synchronized (f3393c) {
                    f3393c.add(str);
                }
                return true;
            }
        } catch (Throwable unused) {
            return b(str);
        }
    }

    private boolean a(String str, a aVar) throws Throwable {
        ZipFile zipFile;
        File file = new File(b(), str);
        if (file.exists() && file.length() > 0) {
            return true;
        }
        String str2 = a(aVar) + str;
        ZipFile zipFile2 = null;
        String strA = !f3397g ? a() : f3398h;
        if (strA != null) {
            try {
                if (!strA.isEmpty()) {
                    try {
                        zipFile = new ZipFile(strA);
                        try {
                            ZipEntry entry = zipFile.getEntry(str2);
                            if (entry == null) {
                                try {
                                    zipFile.close();
                                } catch (IOException e2) {
                                    Log.e(f3391a, "Release file failed", e2);
                                }
                                return false;
                            }
                            a(zipFile.getInputStream(entry), new FileOutputStream(new File(b(), str)));
                            try {
                                zipFile.close();
                            } catch (IOException e3) {
                                Log.e(f3391a, "Release file failed", e3);
                            }
                            return true;
                        } catch (Exception e4) {
                            e = e4;
                            zipFile2 = zipFile;
                            Log.e(f3391a, "Copy library file error", e);
                            if (zipFile2 != null) {
                                try {
                                    zipFile2.close();
                                } catch (IOException e5) {
                                    Log.e(f3391a, "Release file failed", e5);
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            if (zipFile != null) {
                                try {
                                    zipFile.close();
                                } catch (IOException e6) {
                                    Log.e(f3391a, "Release file failed", e6);
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e7) {
                        e = e7;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                zipFile = zipFile2;
            }
        }
        return false;
    }

    private boolean a(String str, String str2) {
        return !a(str2, a.ARMV7) ? b(str, str2) : f(str2, str);
    }

    private String b() {
        File file = new File(f3392b.getFilesDir(), "libs");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    private boolean b(String str) {
        String strMapLibraryName = System.mapLibraryName(str);
        synchronized (f3393c) {
            if (f3393c.contains(str)) {
                return true;
            }
            int i = d.f3481a[f3396f.ordinal()];
            boolean zD = i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? false : d(str, strMapLibraryName) : e(str, strMapLibraryName) : b(str, strMapLibraryName) : a(str, strMapLibraryName) : c(str, strMapLibraryName);
            synchronized (f3393c) {
                f3393c.add(str);
            }
            return zD;
        }
    }

    private boolean b(String str, String str2) {
        if (a(str2, a.ARMEABI)) {
            return f(str2, str);
        }
        Log.e(f3391a, "found lib" + str + ".so error");
        return false;
    }

    private static a c() {
        String str = Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0];
        if (str == null) {
            return a.ARMEABI;
        }
        if (str.contains("arm") && str.contains("v7")) {
            f3396f = a.ARMV7;
        }
        if (str.contains("arm") && str.contains("64")) {
            f3396f = a.ARM64;
        }
        if (str.contains("x86")) {
            f3396f = str.contains("64") ? a.X86_64 : a.X86;
        }
        return f3396f;
    }

    private boolean c(String str, String str2) {
        return !a(str2, a.ARM64) ? a(str, str2) : f(str2, str);
    }

    private boolean d(String str, String str2) {
        return !a(str2, a.X86) ? a(str, str2) : f(str2, str);
    }

    private boolean e(String str, String str2) {
        return !a(str2, a.X86_64) ? d(str, str2) : f(str2, str);
    }

    private boolean f(String str, String str2) {
        try {
            System.load(new File(b(), str).getAbsolutePath());
            synchronized (f3393c) {
                f3393c.add(str2);
            }
            return true;
        } catch (Throwable th) {
            synchronized (f3394d) {
                f3394d.add(str2);
                a(th);
                return false;
            }
        }
    }

    public static synchronized NativeLoader getInstance() {
        if (f3395e == null) {
            f3395e = new NativeLoader();
            f3396f = c();
        }
        return f3395e;
    }

    public static void setContext(Context context) {
        f3392b = context;
    }

    public synchronized boolean loadLibrary(String str) {
        if (!f3397g) {
            return a(str);
        }
        if (f3398h == null || f3398h.isEmpty()) {
            Log.e(f3391a, "Given custom so file path is null, please check!");
            return false;
        }
        return b(str);
    }
}