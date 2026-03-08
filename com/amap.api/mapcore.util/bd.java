package com.amap.api.mapcore.util;

import android.content.Context;
import com.ido.common.utils.FileDialDefinedUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: OfflineMapDataVerify.java */
/* JADX INFO: loaded from: classes.dex */
public class bd extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private bp f256b;

    public bd(Context context) {
        this.f255a = context;
        this.f256b = bp.a(context);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private bk a(File file) throws Throwable {
        String strA = er.a(file);
        bk bkVar = new bk();
        bkVar.b(strA);
        return bkVar;
    }

    private void a() {
        bk bkVarA;
        String strC;
        int iIndexOf;
        boolean z;
        String strC2;
        int iIndexOf2;
        String strC3;
        int iIndexOf3;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<bk> arrayListA = this.f256b.a();
        a(arrayList, "vmap/");
        a(arrayList, "map/");
        b(arrayList, "map/");
        ArrayList<String> arrayListB = b();
        for (bk bkVar : arrayListA) {
            if (bkVar != null && bkVar.d() != null) {
                if (bkVar.l == 4 || bkVar.l == 7) {
                    boolean zContains = arrayList.contains(bkVar.i());
                    if (!zContains && (strC = bx.c(bkVar.g())) != null && (iIndexOf = arrayList.indexOf(strC)) != -1) {
                        arrayList.set(iIndexOf, bkVar.i());
                        zContains = true;
                    }
                    if (!zContains) {
                        this.f256b.b(bkVar);
                    }
                } else if (bkVar.l == 0 || bkVar.l == 1) {
                    z = arrayListB.contains(bkVar.f()) || arrayListB.contains(bkVar.i());
                    if (!z && (strC2 = bx.c(bkVar.g())) != null && (iIndexOf2 = arrayListB.indexOf(strC2)) != -1) {
                        arrayListB.set(iIndexOf2, bkVar.i());
                        z = true;
                    }
                    if (!z) {
                        this.f256b.b(bkVar);
                    }
                } else if (bkVar.l == 3 && bkVar.h() != 0) {
                    z = arrayListB.contains(bkVar.f()) || arrayListB.contains(bkVar.i());
                    if (!z && (strC3 = bx.c(bkVar.g())) != null && (iIndexOf3 = arrayListB.indexOf(strC3)) != -1) {
                        arrayListB.set(iIndexOf3, bkVar.i());
                        z = true;
                    }
                    if (!z) {
                        this.f256b.b(bkVar);
                    }
                }
            }
        }
        for (String str : arrayList) {
            if (!a(str, arrayListA) && (bkVarA = a(str)) != null) {
                this.f256b.a(bkVarA);
            }
        }
        ba baVarA = ba.a(this.f255a);
        if (baVarA != null) {
            baVarA.a((ArrayList<bk>) null);
        }
    }

    private bk a(String str) {
        if (str.equals("quanguo")) {
            str = "quanguogaiyaotu";
        }
        ba baVarA = ba.a(this.f255a);
        if (baVarA == null) {
            return null;
        }
        String strF = baVarA.f(str);
        File[] fileArrListFiles = new File(er.c(this.f255a)).listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        bk bkVarA = null;
        for (File file : fileArrListFiles) {
            if (((file.getName().contains(strF) || file.getName().contains(str)) && file.getName().endsWith(".zip.tmp.dt")) && (bkVarA = a(file)) != null && bkVarA.d() != null) {
                return bkVarA;
            }
        }
        return bkVarA;
    }

    private boolean a(String str, ArrayList<bk> arrayList) {
        Iterator<bk> it = arrayList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().i())) {
                return true;
            }
        }
        return false;
    }

    private void a(ArrayList<String> arrayList, String str) {
        File[] fileArrListFiles;
        String name;
        int iLastIndexOf;
        File file = new File(er.b(this.f255a) + str);
        if (file.exists() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.getName().endsWith(".dat") && (iLastIndexOf = (name = file2.getName()).lastIndexOf(46)) > -1 && iLastIndexOf < name.length()) {
                    String strSubstring = name.substring(0, iLastIndexOf);
                    if (!arrayList.contains(strSubstring)) {
                        arrayList.add(strSubstring);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0090 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(java.util.ArrayList<java.lang.String> r14, java.lang.String r15) {
        /*
            r13 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            android.content.Context r1 = r13.f255a
            java.lang.String r1 = com.amap.api.mapcore.util.er.a(r1)
            r0.append(r1)
            r0.append(r15)
            java.io.File r15 = new java.io.File
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            boolean r0 = r15.exists()
            if (r0 != 0) goto L21
            return
        L21:
            java.io.File[] r15 = r15.listFiles()
            if (r15 != 0) goto L28
            return
        L28:
            int r0 = r15.length
            r1 = 0
            r2 = r1
        L2b:
            if (r2 >= r0) goto L93
            r3 = r15[r2]
            boolean r4 = r3.isDirectory()
            if (r4 == 0) goto L90
            java.lang.String r4 = r3.getName()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L90
            java.lang.String[] r3 = r3.list()
            if (r3 == 0) goto L90
            int r5 = r3.length
            r6 = 1
            if (r5 >= r6) goto L4a
            goto L90
        L4a:
            boolean r5 = r14.contains(r4)
            if (r5 != 0) goto L90
            java.lang.String r5 = "a0"
            boolean r5 = r4.equals(r5)
            java.lang.String r7 = "m1.ans"
            if (r5 == 0) goto L6a
            int r5 = r3.length
            r8 = r1
        L5c:
            if (r8 >= r5) goto L8a
            r9 = r3[r8]
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L67
            goto L8b
        L67:
            int r8 = r8 + 1
            goto L5c
        L6a:
            int r5 = r3.length
            r8 = r1
            r9 = r8
            r10 = r9
        L6e:
            if (r8 >= r5) goto L85
            r11 = r3[r8]
            boolean r12 = r7.equals(r11)
            if (r12 == 0) goto L79
            r9 = r6
        L79:
            java.lang.String r12 = "m3.ans"
            boolean r11 = r12.equals(r11)
            if (r11 == 0) goto L82
            r10 = r6
        L82:
            int r8 = r8 + 1
            goto L6e
        L85:
            if (r9 == 0) goto L8a
            if (r10 == 0) goto L8a
            goto L8b
        L8a:
            r6 = r1
        L8b:
            if (r6 == 0) goto L90
            r14.add(r4)
        L90:
            int r2 = r2 + 1
            goto L2b
        L93:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.bd.b(java.util.ArrayList, java.lang.String):void");
    }

    private ArrayList<String> b() {
        File[] fileArrListFiles;
        String name;
        int iLastIndexOf;
        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(er.c(this.f255a));
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null) {
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.getName().endsWith(FileDialDefinedUtil.FILE_ZIP) && (iLastIndexOf = (name = file2.getName()).lastIndexOf(46)) > -1 && iLastIndexOf < name.length()) {
                arrayList.add(name.substring(0, iLastIndexOf));
            }
        }
        return arrayList;
    }
}