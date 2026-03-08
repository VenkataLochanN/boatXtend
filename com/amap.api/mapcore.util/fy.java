package com.amap.api.mapcore.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: ServiceUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class fy {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static AssetManager f995b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Resources f996c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Resources f997d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f998e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Context f999f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static String f1000g = "amap_resource";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f1001h = "1_0_0";
    private static String j = ".jar";
    private static String k = f1000g + f1001h + j;
    private static String i = ".png";
    private static String l = f1000g + f1001h + i;
    private static String m = "";
    private static String n = m + k;
    private static Resources.Theme o = null;
    private static Resources.Theme p = null;
    private static Field q = null;
    private static Field r = null;
    private static Activity s = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f994a = -1;

    public static boolean a(Context context) {
        try {
            f999f = context;
            File fileB = b(f999f);
            if (fileB != null) {
                m = fileB.getAbsolutePath() + "/";
            }
            n = m + k;
            if (!f998e) {
                return true;
            }
            if (!c(context)) {
                return false;
            }
            f995b = a(n);
            f996c = a(context, f995b);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return true;
    }

    private static File b(Context context) {
        File filesDir;
        try {
            if (context == null) {
                if (context != null) {
                    context.getFilesDir();
                }
                return null;
            }
            try {
                if (!Environment.getExternalStorageState().equals("mounted") || !Environment.getExternalStorageDirectory().canWrite()) {
                    filesDir = context.getFilesDir();
                } else {
                    filesDir = context.getExternalFilesDir("LBS");
                }
                if (filesDir == null && context != null) {
                    context.getFilesDir();
                }
                return filesDir;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (0 != 0 || context == null) {
                    return null;
                }
                return context.getFilesDir();
            }
        } catch (Throwable th) {
            if (0 == 0 && context != null) {
                context.getFilesDir();
            }
            throw th;
        }
    }

    public static Resources a() {
        Resources resources = f996c;
        return resources == null ? f999f.getResources() : resources;
    }

    private static Resources a(Context context, AssetManager assetManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics.setToDefaults();
        return new Resources(assetManager, displayMetrics, context.getResources().getConfiguration());
    }

    private static AssetManager a(String str) {
        AssetManager assetManager;
        Class<?> cls;
        try {
            cls = Class.forName("android.content.res.AssetManager");
            assetManager = (AssetManager) cls.getConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Throwable th) {
            th = th;
            assetManager = null;
        }
        try {
            cls.getDeclaredMethod("addAssetPath", String.class).invoke(assetManager, str);
        } catch (Throwable th2) {
            th = th2;
            hn.c(th, "ResourcesUtil", "getAssetManager(String apkPath)");
        }
        return assetManager;
    }

    private static boolean c(Context context) {
        d(context);
        InputStream inputStreamOpen = null;
        try {
            inputStreamOpen = context.getResources().getAssets().open(l);
            if (b(inputStreamOpen)) {
                return true;
            }
            e();
            OutputStream outputStreamA = a(inputStreamOpen);
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    hn.c(e2, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                }
            }
            if (outputStreamA != null) {
                outputStreamA.close();
            }
            return true;
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                hn.c(th, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        hn.c(e3, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                    }
                }
                return false;
            } finally {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        hn.c(e4, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                    }
                }
            }
        }
    }

    private static OutputStream a(InputStream inputStream) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(m, k));
        byte[] bArr = new byte[1024];
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 <= 0) {
                return fileOutputStream;
            }
            fileOutputStream.write(bArr, 0, i2);
        }
    }

    private static boolean b(InputStream inputStream) throws IOException {
        File file = new File(n);
        long length = file.length();
        int iAvailable = inputStream.available();
        if (!file.exists() || length != iAvailable) {
            return false;
        }
        inputStream.close();
        return true;
    }

    private static void e() {
        File[] fileArrListFiles = new File(m).listFiles(new a());
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file : fileArrListFiles) {
            file.delete();
        }
    }

    private static void d(Context context) {
        m = context.getFilesDir().getAbsolutePath();
        n = m + "/" + k;
    }

    public static View a(Context context, int i2, ViewGroup viewGroup) {
        XmlResourceParser xml = a().getXml(i2);
        if (!f998e) {
            return LayoutInflater.from(context).inflate(xml, viewGroup);
        }
        try {
            return LayoutInflater.from(new fx(context, f994a == -1 ? 0 : f994a, fy.class.getClassLoader())).inflate(xml, viewGroup);
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                hn.c(th, "ResourcesUtil", "selfInflate(Activity activity, int resource, ViewGroup root)");
                xml.close();
                return null;
            } finally {
                xml.close();
            }
        }
    }

    /* JADX INFO: compiled from: ServiceUtils.java */
    static class a implements FilenameFilter {
        a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(fy.f1001h);
            sb.append(fy.j);
            return str.startsWith(fy.f1000g) && !str.endsWith(sb.toString());
        }
    }
}