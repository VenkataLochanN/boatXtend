package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile h f3842a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3843b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3844c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<g> f3845d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private g f3846e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f3847f;

    private h() {
    }

    public static h a() {
        if (f3842a == null) {
            synchronized (h.class) {
                if (f3842a == null) {
                    f3842a = new h();
                }
            }
        }
        return f3842a;
    }

    private boolean a(String str) {
        boolean zCreateNewFile = false;
        try {
            File file = new File(str + "/test.0");
            if (file.exists()) {
                file.delete();
            }
            zCreateNewFile = file.createNewFile();
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return zCreateNewFile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void c(Context context) {
        boolean z;
        Object[] objArr;
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            int i = 1;
            Method method2 = storageManager.getClass().getMethod("getVolumeState", String.class);
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Method method4 = cls.getMethod("getPath", new Class[0]);
            Object[] objArr2 = (Object[]) method.invoke(storageManager, new Object[0]);
            if (objArr2 != null) {
                int length = objArr2.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    Object obj = objArr2[i2];
                    String str = (String) method4.invoke(obj, new Object[0]);
                    if (str == null || str.length() <= 0) {
                        objArr = objArr2;
                    } else {
                        objArr = objArr2;
                        Object[] objArr3 = new Object[i];
                        objArr3[0] = str;
                        if ("mounted".equals(method2.invoke(storageManager, objArr3))) {
                            int i3 = !((Boolean) method3.invoke(obj, new Object[0])).booleanValue() ? i : 0;
                            if (Build.VERSION.SDK_INT <= 19 && a(str)) {
                                this.f3845d.add(new g(str, i3 ^ 1, i3 != 0 ? "内置存储卡" : "外置存储卡", context));
                            } else if (Build.VERSION.SDK_INT >= 19) {
                                if (new File(str + File.separator + "BaiduMapSDKNew").exists() && str.equals(context.getSharedPreferences("map_pref", 0).getString("PREFFERED_SD_CARD", ""))) {
                                    this.f3847f = str + File.separator + "BaiduMapSDKNew";
                                }
                            }
                        }
                    }
                    i2++;
                    objArr2 = objArr;
                    i = 1;
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    File[] externalFilesDirs = context.getExternalFilesDirs(null);
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(this.f3845d);
                    for (int i4 = 0; i4 < externalFilesDirs.length && externalFilesDirs[i4] != null; i4++) {
                        String absolutePath = externalFilesDirs[i4].getAbsolutePath();
                        Iterator<g> it = this.f3845d.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (absolutePath.startsWith(it.next().a())) {
                                    z = true;
                                    break;
                                }
                            } else {
                                z = false;
                                break;
                            }
                        }
                        String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                        if (str2 != null && !z && absolutePath.indexOf(str2) != -1) {
                            arrayList.add(new g(absolutePath, true, "外置存储卡", context));
                        }
                    }
                    this.f3845d.clear();
                    this.f3845d.addAll(arrayList);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void d(Context context) throws Throwable {
        Scanner scanner;
        String[] strArrSplit;
        String[] strArrSplit2;
        ArrayList<String> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Scanner scanner2 = null;
        try {
            try {
                File file = new File("/proc/mounts");
                if (file.exists()) {
                    scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        try {
                            String strNextLine = scanner.nextLine();
                            if (strNextLine.startsWith("/dev/block/vold/") && (strArrSplit2 = strNextLine.replace('\t', ' ').split(" ")) != null && strArrSplit2.length > 0) {
                                arrayList.add(strArrSplit2[1]);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            scanner2 = scanner;
                            e.printStackTrace();
                            if (scanner2 != null) {
                                scanner2.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            scanner2 = scanner;
                            if (scanner2 != null) {
                                scanner2.close();
                            }
                            throw th;
                        }
                    }
                    scanner.close();
                }
                File file2 = new File("/system/etc/vold.fstab");
                if (file2.exists()) {
                    scanner = new Scanner(file2);
                    while (scanner.hasNext()) {
                        String strNextLine2 = scanner.nextLine();
                        if (strNextLine2.startsWith("dev_mount") && (strArrSplit = strNextLine2.replace('\t', ' ').split(" ")) != null && strArrSplit.length > 0) {
                            String strSubstring = strArrSplit[2];
                            if (strSubstring.contains(":")) {
                                strSubstring = strSubstring.substring(0, strSubstring.indexOf(":"));
                            }
                            arrayList2.add(strSubstring);
                        }
                    }
                    scanner.close();
                }
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                this.f3845d.add(new g(absolutePath, false, "Auto", context));
                for (String str : arrayList) {
                    if (arrayList2.contains(str) && !str.equals(absolutePath)) {
                        File file3 = new File(str);
                        if (file3.exists() && file3.isDirectory() && file3.canWrite()) {
                            this.f3845d.add(new g(str, false, "Auto", context));
                        }
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void a(Context context) throws Throwable {
        if (this.f3843b) {
            return;
        }
        this.f3843b = true;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                c(context);
            } else {
                d(context);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (this.f3845d.size() > 0) {
                g gVar = null;
                int i = 0;
                for (g gVar2 : this.f3845d) {
                    if (new File(gVar2.b()).exists()) {
                        i++;
                        gVar = gVar2;
                    }
                }
                if (i == 0) {
                    this.f3846e = b(context);
                    if (this.f3846e == null) {
                        Iterator<g> it = this.f3845d.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            g next = it.next();
                            if (a(context, next)) {
                                this.f3846e = next;
                                break;
                            }
                        }
                    }
                } else if (i != 1) {
                    this.f3846e = b(context);
                } else if (a(context, gVar)) {
                    this.f3846e = gVar;
                }
                if (this.f3846e == null) {
                    this.f3846e = this.f3845d.get(0);
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            if (this.f3846e == null || !a(this.f3846e.a())) {
                this.f3844c = false;
                this.f3846e = new g(context);
                this.f3845d.clear();
                this.f3845d.add(this.f3846e);
                return;
            }
            File file = new File(this.f3846e.b());
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(this.f3846e.c());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2, ".nomedia");
            if (file3.exists()) {
                return;
            }
            file3.createNewFile();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public boolean a(Context context, g gVar) {
        String strA = gVar.a();
        if (!a(strA)) {
            return false;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("map_pref", 0).edit();
        editorEdit.putString("PREFFERED_SD_CARD", strA);
        return editorEdit.commit();
    }

    public g b() {
        return this.f3846e;
    }

    public g b(Context context) {
        String string = context.getSharedPreferences("map_pref", 0).getString("PREFFERED_SD_CARD", "");
        if (string == null || string.length() <= 0) {
            return null;
        }
        for (g gVar : this.f3845d) {
            if (gVar.a().equals(string)) {
                return gVar;
            }
        }
        return null;
    }
}