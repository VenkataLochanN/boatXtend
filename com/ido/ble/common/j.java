package com.ido.ble.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static double f4227a = 1024.0d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static double f4228b = f4227a * 1024.0d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static double f4229c = f4228b * 1024.0d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static double f4230d = f4229c * 1024.0d;

    public static File a(File file, String... strArr) {
        if (file == null) {
            throw new NullPointerException("directorydirectory must not be null");
        }
        if (strArr == null) {
            throw new NullPointerException("names must not be null");
        }
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            File file2 = new File(file, strArr[i]);
            i++;
            file = file2;
        }
        return file;
    }

    public static File a(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("names must not be null");
        }
        File file = null;
        for (String str : strArr) {
            file = file == null ? new File(str) : new File(file, str);
        }
        return file;
    }

    public static FileOutputStream a(File file, boolean z) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        }
        return new FileOutputStream(file, z);
    }

    private static void a(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (file.isDirectory()) {
            return;
        }
        throw new IllegalArgumentException(file + " is not a directory");
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.io.File r13, java.io.File r14) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            r1.<init>(r13)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            java.nio.channels.FileChannel r13 = r1.getChannel()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L48
            r1.<init>(r14)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L48
            java.nio.channels.FileChannel r14 = r1.getChannel()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L48
            r0 = 67076096(0x3ff8000, float:1.501694E-36)
            long r8 = r13.size()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3d
            r1 = 0
            r10 = r1
        L1d:
            int r1 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r1 >= 0) goto L2b
            long r5 = (long) r0     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3d
            r2 = r13
            r3 = r10
            r7 = r14
            long r1 = r2.transferTo(r3, r5, r7)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3d
            long r10 = r10 + r1
            goto L1d
        L2b:
            if (r13 == 0) goto L35
            r13.close()     // Catch: java.io.IOException -> L31
            goto L35
        L31:
            r13 = move-exception
            r13.printStackTrace()
        L35:
            if (r14 == 0) goto L6a
            goto L62
        L38:
            r0 = move-exception
            r12 = r0
            r0 = r13
            r13 = r12
            goto L6c
        L3d:
            r0 = move-exception
            r12 = r0
            r0 = r13
            r13 = r12
            goto L53
        L42:
            r14 = move-exception
            r12 = r0
            r0 = r13
            r13 = r14
            r14 = r12
            goto L6c
        L48:
            r14 = move-exception
            r12 = r0
            r0 = r13
            r13 = r14
            r14 = r12
            goto L53
        L4e:
            r13 = move-exception
            r14 = r0
            goto L6c
        L51:
            r13 = move-exception
            r14 = r0
        L53:
            r13.printStackTrace()     // Catch: java.lang.Throwable -> L6b
            if (r0 == 0) goto L60
            r0.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r13 = move-exception
            r13.printStackTrace()
        L60:
            if (r14 == 0) goto L6a
        L62:
            r14.close()     // Catch: java.io.IOException -> L66
            goto L6a
        L66:
            r13 = move-exception
            r13.printStackTrace()
        L6a:
            return
        L6b:
            r13 = move-exception
        L6c:
            if (r0 == 0) goto L76
            r0.close()     // Catch: java.io.IOException -> L72
            goto L76
        L72:
            r0 = move-exception
            r0.printStackTrace()
        L76:
            if (r14 == 0) goto L80
            r14.close()     // Catch: java.io.IOException -> L7c
            goto L80
        L7c:
            r14 = move-exception
            r14.printStackTrace()
        L80:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.common.j.a(java.io.File, java.io.File):void");
    }

    public static void a(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof Reader) {
                ((Reader) obj).close();
            } else if (obj instanceof Writer) {
                ((Writer) obj).close();
            } else if (obj instanceof InputStream) {
                ((InputStream) obj).close();
            } else if (obj instanceof OutputStream) {
                ((OutputStream) obj).close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean a(String str, Object obj) throws Throwable {
        boolean zCreateNewFile;
        FileOutputStream fileOutputStream;
        File file = new File(str);
        if (file.exists()) {
            zCreateNewFile = true;
        } else {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                zCreateNewFile = false;
            }
        }
        if (!zCreateNewFile) {
            return zCreateNewFile;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    a(fileOutputStream);
                    return zCreateNewFile;
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream2 = fileOutputStream;
                    e.printStackTrace();
                    a(fileOutputStream2);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    public static boolean a(String str, String str2) {
        boolean zCreateNewFile;
        File file = new File(str);
        if (file.exists()) {
            zCreateNewFile = true;
        } else {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                zCreateNewFile = false;
            }
        }
        if (!zCreateNewFile) {
            return zCreateNewFile;
        }
        try {
            FileWriter fileWriter = new FileWriter(str);
            fileWriter.write(str2);
            fileWriter.flush();
            fileWriter.close();
            return zCreateNewFile;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static void b(File file) throws IOException {
        StringBuilder sb;
        String str;
        if (!file.exists()) {
            sb = new StringBuilder();
            sb.append(file);
            str = " does not exist";
        } else {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles == null) {
                    throw new IOException("Failed to list contents of " + file);
                }
                IOException e2 = null;
                for (File file2 : fileArrListFiles) {
                    try {
                        e(file2);
                    } catch (IOException e3) {
                        e2 = e3;
                    }
                }
                if (e2 != null) {
                    throw e2;
                }
                return;
            }
            sb = new StringBuilder();
            sb.append(file);
            str = " is not a directory";
        }
        sb.append(str);
        sb.toString();
    }

    public static boolean b(String str) {
        return new File(str).exists();
    }

    public static Object c(String str) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        Object obj;
        File file = new File(str);
        FileInputStream fileInputStream2 = null;
        Object object = null;
        fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    object = objectInputStream.readObject();
                    objectInputStream.close();
                    a(fileInputStream);
                    return object;
                } catch (Exception e2) {
                    e = e2;
                    obj = object;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    a(fileInputStream2);
                    return obj;
                } catch (Throwable th2) {
                    th = th2;
                    a(fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                fileInputStream = fileInputStream2;
                th = th3;
            }
        } catch (Exception e3) {
            e = e3;
            obj = null;
        }
    }

    public static void c(File file) throws IOException {
        if (file.exists()) {
            b(file);
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        }
    }

    public static String d(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(line);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean d(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                b(file);
            }
        } catch (Exception unused) {
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    public static void e(File file) throws IOException {
        if (file.isDirectory()) {
            c(file);
            return;
        }
        boolean zExists = file.exists();
        if (file.delete()) {
            return;
        }
        if (zExists) {
            throw new IOException("Unable to delete file: " + file);
        }
        throw new FileNotFoundException("File does not exist: " + file);
    }

    public static void f(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            throw new IOException("File " + file + " exists and is not a directory. Unable to create directory.");
        }
        if (file.mkdirs() || file.isDirectory()) {
            return;
        }
        throw new IOException("Unable to create directory " + file);
    }

    public static FileInputStream g(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        }
        if (file.canRead()) {
            return new FileInputStream(file);
        }
        throw new IOException("File '" + file + "' cannot be read");
    }

    public static FileOutputStream h(File file) {
        return a(file, false);
    }

    public static long i(File file) {
        if (file.exists()) {
            return file.isDirectory() ? j(file) : file.length();
        }
        throw new IllegalArgumentException(file + " does not exist");
    }

    public static long j(File file) {
        a(file);
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return 0L;
        }
        long jI = 0;
        for (File file2 : fileArrListFiles) {
            jI += i(file2);
            if (jI < 0) {
                break;
            }
        }
        return jI;
    }
}