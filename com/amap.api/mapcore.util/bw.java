package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: compiled from: UnZipFile.java */
/* JADX INFO: loaded from: classes.dex */
public class bw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f306a;

    /* JADX INFO: compiled from: UnZipFile.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f308a = false;
    }

    /* JADX INFO: compiled from: UnZipFile.java */
    public interface c {
        void a();

        void a(long j);
    }

    public bw(bt btVar, bs bsVar) {
        this.f306a = new b(btVar, bsVar);
    }

    public void a() {
        b bVar = this.f306a;
        if (bVar != null) {
            bVar.f();
        }
    }

    public void b() {
        b bVar = this.f306a;
        if (bVar != null) {
            a(bVar);
        }
    }

    private static void a(b bVar) {
        if (bVar == null) {
            return;
        }
        final bs bsVarD = bVar.d();
        if (bsVarD != null) {
            bsVarD.q();
        }
        String strA = bVar.a();
        String strB = bVar.b();
        if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strB)) {
            if (bVar.e().f308a) {
                if (bsVarD != null) {
                    bsVarD.s();
                    return;
                }
                return;
            } else {
                if (bsVarD != null) {
                    bsVarD.r();
                    return;
                }
                return;
            }
        }
        File file = new File(strA);
        if (!file.exists()) {
            if (bVar.e().f308a) {
                if (bsVarD != null) {
                    bsVarD.s();
                    return;
                }
                return;
            } else {
                if (bsVarD != null) {
                    bsVarD.r();
                    return;
                }
                return;
            }
        }
        File file2 = new File(strB);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        c cVar = new c() { // from class: com.amap.api.mapcore.util.bw.1
            @Override // com.amap.api.mapcore.util.bw.c
            public void a(long j) {
                try {
                    if (bsVarD != null) {
                        bsVarD.a(j);
                    }
                } catch (Exception unused) {
                }
            }

            @Override // com.amap.api.mapcore.util.bw.c
            public void a() {
                bs bsVar = bsVarD;
                if (bsVar != null) {
                    bsVar.r();
                }
            }
        };
        try {
            if (bVar.e().f308a && bsVarD != null) {
                bsVarD.s();
            }
            a(file, file2, cVar, bVar);
            if (bVar.e().f308a) {
                if (bsVarD != null) {
                    bsVarD.s();
                }
            } else if (bsVarD != null) {
                bsVarD.b(bVar.c());
            }
        } catch (Throwable unused) {
            if (bVar.e().f308a) {
                if (bsVarD != null) {
                    bsVarD.s();
                }
            } else if (bsVarD != null) {
                bsVarD.r();
            }
        }
    }

    private static void a(File file, File file2, c cVar, b bVar) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        a aVarE = bVar.e();
        long size = 0;
        if (cVar != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32());
                ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        if (aVarE != null && aVarE.f308a) {
                            zipInputStream.closeEntry();
                            zipInputStream.close();
                            checkedInputStream.close();
                            fileInputStream.close();
                            break;
                        }
                        if (!nextEntry.isDirectory()) {
                            if (!a(nextEntry.getName())) {
                                cVar.a();
                                break;
                            } else {
                                stringBuffer.append(nextEntry.getName());
                                stringBuffer.append(";");
                            }
                        }
                        size += nextEntry.getSize();
                        zipInputStream.closeEntry();
                    } else {
                        break;
                    }
                }
                bVar.a(stringBuffer.toString());
                zipInputStream.close();
                checkedInputStream.close();
                fileInputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        FileInputStream fileInputStream2 = new FileInputStream(file);
        CheckedInputStream checkedInputStream2 = new CheckedInputStream(fileInputStream2, new CRC32());
        ZipInputStream zipInputStream2 = new ZipInputStream(checkedInputStream2);
        a(file2, zipInputStream2, size, cVar, aVarE);
        zipInputStream2.close();
        checkedInputStream2.close();
        fileInputStream2.close();
    }

    private static void a(File file, ZipInputStream zipInputStream, long j, c cVar, a aVar) throws Exception {
        int iA = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            if (aVar != null && aVar.f308a) {
                zipInputStream.closeEntry();
                return;
            }
            String str = file.getPath() + File.separator + nextEntry.getName();
            if (!a(nextEntry.getName())) {
                if (cVar != null) {
                    cVar.a();
                    return;
                }
                return;
            } else {
                File file2 = new File(str);
                a(file2);
                if (nextEntry.isDirectory()) {
                    file2.mkdirs();
                } else {
                    iA += a(file2, zipInputStream, iA, j, cVar, aVar);
                }
                zipInputStream.closeEntry();
            }
        }
    }

    private static boolean a(String str) {
        return (str.contains("..") || str.contains("/") || str.contains("\\") || str.contains("%")) ? false : true;
    }

    private static int a(File file, ZipInputStream zipInputStream, long j, long j2, c cVar, a aVar) throws Exception {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bArr = new byte[1024];
        int i = 0;
        while (true) {
            int i2 = zipInputStream.read(bArr, 0, 1024);
            if (i2 != -1) {
                if (aVar != null && aVar.f308a) {
                    bufferedOutputStream.close();
                    return i;
                }
                bufferedOutputStream.write(bArr, 0, i2);
                i += i2;
                if (j2 > 0 && cVar != null) {
                    long j3 = ((((long) i) + j) * 100) / j2;
                    if (aVar == null || !aVar.f308a) {
                        cVar.a(j3);
                    }
                }
            } else {
                bufferedOutputStream.close();
                return i;
            }
        }
    }

    private static void a(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            return;
        }
        a(parentFile);
        parentFile.mkdir();
    }

    /* JADX INFO: compiled from: UnZipFile.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f309a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f310b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private bs f311c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private a f312d = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f313e;

        public b(bt btVar, bs bsVar) {
            this.f311c = null;
            this.f309a = btVar.B();
            this.f310b = btVar.C();
            this.f311c = bsVar;
        }

        public void a(String str) {
            if (str.length() > 1) {
                this.f313e = str;
            }
        }

        public String a() {
            return this.f309a;
        }

        public String b() {
            return this.f310b;
        }

        public String c() {
            return this.f313e;
        }

        public bs d() {
            return this.f311c;
        }

        public a e() {
            return this.f312d;
        }

        public void f() {
            this.f312d.f308a = true;
        }
    }
}