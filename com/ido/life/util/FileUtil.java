package com.ido.life.util;

import android.text.TextUtils;
import com.ido.common.utils.FileSizeUtil;
import com.ido.common.utils.NumUtil;
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

/* JADX INFO: loaded from: classes3.dex */
public class FileUtil {
    private static double SIZE_KB = 1024.0d;
    private static double SIZE_MB = SIZE_KB * 1024.0d;
    private static double SIZE_GB = SIZE_MB * 1024.0d;
    private static double SIZE_TB = SIZE_GB * 1024.0d;

    public static File getFile(File file, String... strArr) {
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

    public static File getFile(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("names must not be null");
        }
        File file = null;
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else {
                file = new File(file, str);
            }
        }
        return file;
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
            return new FileInputStream(file);
        }
        throw new FileNotFoundException("File '" + file + "' does not exist");
    }

    public static FileOutputStream openOutputStream(File file, boolean z) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        }
        return new FileOutputStream(file, z);
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static void cleanDirectory(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + " is not a directory");
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("Failed to list contents of " + file);
        }
        IOException e2 = null;
        for (File file2 : fileArrListFiles) {
            try {
                forceDelete(file2);
            } catch (IOException e3) {
                e2 = e3;
            }
        }
        if (e2 != null) {
            throw e2;
        }
    }

    public static void deleteDirectory(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            deleteDirectory(new File(str));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void deleteDirectory(File file) throws IOException {
        if (file.exists()) {
            cleanDirectory(file);
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        boolean zExists = file.exists();
        if (file.delete()) {
            return;
        }
        if (!zExists) {
            throw new FileNotFoundException("File does not exist: " + file);
        }
        throw new IOException("Unable to delete file: " + file);
    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception unused) {
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    public static void forceMkdir(File file) throws IOException {
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

    public static long sizeOf(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (file.isDirectory()) {
            return sizeOfDirectory(file);
        }
        return file.length();
    }

    public static long sizeOfDirectory(File file) {
        checkDirectory(file);
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return 0L;
        }
        long jSizeOf = 0;
        for (File file2 : fileArrListFiles) {
            jSizeOf += sizeOf(file2);
            if (jSizeOf < 0) {
                break;
            }
        }
        return jSizeOf;
    }

    private static void checkDirectory(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (file.isDirectory()) {
            return;
        }
        throw new IllegalArgumentException(file + " is not a directory");
    }

    public static String readStringFromFile(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean writeStringToFile(String str, String str2) {
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

    public static boolean writeObjectToFile(String str, Object obj) throws Throwable {
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
                    closeStream(fileOutputStream);
                    return zCreateNewFile;
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream2 = fileOutputStream;
                    e.printStackTrace();
                    closeStream(fileOutputStream2);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    closeStream(fileOutputStream);
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

    public static boolean fileExists(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.FileInputStream, java.io.InputStream, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Object] */
    public static Object readObjectFromFile(String str) throws Throwable {
        Object obj;
        ?? fileInputStream;
        Throwable th;
        File file = new File(str);
        Object object = null;
        object = null;
        ?? r3 = 0;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    object = objectInputStream.readObject();
                    objectInputStream.close();
                    closeStream(fileInputStream);
                } catch (Exception e2) {
                    e = e2;
                    obj = object;
                    r3 = fileInputStream;
                    e.printStackTrace();
                    closeStream(r3);
                    object = obj;
                } catch (Throwable th2) {
                    th = th2;
                    closeStream(fileInputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                obj = null;
            }
            return object;
        } catch (Throwable th3) {
            fileInputStream = object;
            th = th3;
        }
    }

    public static boolean deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static void closeStream(Object obj) {
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

    /* JADX WARN: Removed duplicated region for block: B:58:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void fileCopy(java.io.File r13, java.io.File r14) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L54
            r1.<init>(r13)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L54
            java.nio.channels.FileChannel r13 = r1.getChannel()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L54
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
            r1.<init>(r14)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
            java.nio.channels.FileChannel r14 = r1.getChannel()     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
            r0 = 67076096(0x3ff8000, float:1.501694E-36)
            long r8 = r13.size()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L40
            r1 = 0
            r10 = r1
        L1d:
            int r1 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r1 >= 0) goto L2b
            long r5 = (long) r0     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L40
            r2 = r13
            r3 = r10
            r7 = r14
            long r1 = r2.transferTo(r3, r5, r7)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L40
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
            if (r14 == 0) goto L6d
            r14.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L3b:
            r0 = move-exception
            r12 = r0
            r0 = r13
            r13 = r12
            goto L6f
        L40:
            r0 = move-exception
            r12 = r0
            r0 = r13
            r13 = r12
            goto L56
        L45:
            r14 = move-exception
            r12 = r0
            r0 = r13
            r13 = r14
            r14 = r12
            goto L6f
        L4b:
            r14 = move-exception
            r12 = r0
            r0 = r13
            r13 = r14
            r14 = r12
            goto L56
        L51:
            r13 = move-exception
            r14 = r0
            goto L6f
        L54:
            r13 = move-exception
            r14 = r0
        L56:
            r13.printStackTrace()     // Catch: java.lang.Throwable -> L6e
            if (r0 == 0) goto L63
            r0.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r13 = move-exception
            r13.printStackTrace()
        L63:
            if (r14 == 0) goto L6d
            r14.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r13 = move-exception
            r13.printStackTrace()
        L6d:
            return
        L6e:
            r13 = move-exception
        L6f:
            if (r0 == 0) goto L79
            r0.close()     // Catch: java.io.IOException -> L75
            goto L79
        L75:
            r0 = move-exception
            r0.printStackTrace()
        L79:
            if (r14 == 0) goto L83
            r14.close()     // Catch: java.io.IOException -> L7f
            goto L83
        L7f:
            r14 = move-exception
            r14.printStackTrace()
        L83:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.FileUtil.fileCopy(java.io.File, java.io.File):void");
    }

    public static String sizeConverter(long j) {
        double d2 = j;
        double d3 = SIZE_KB;
        if (d2 < d3) {
            return j + FileSizeUtil.UNIT_BIT;
        }
        if (d2 / d3 > 0.0d && d2 / d3 < 1024.0d) {
            return NumUtil.format2Point(d2 / SIZE_KB) + "KB";
        }
        double d4 = SIZE_MB;
        if (d2 / d4 > 0.0d && d2 / d4 < 1024.0d) {
            return NumUtil.format2Point(d2 / SIZE_MB) + FileSizeUtil.UNIT_MB;
        }
        double d5 = SIZE_GB;
        if (d2 / d5 > 0.0d && d2 / d5 < 1024.0d) {
            return NumUtil.format2Point(d2 / SIZE_GB) + FileSizeUtil.UNIT_GB;
        }
        return NumUtil.format2Point(d2 / SIZE_TB) + "T";
    }

    public static String getApkFilePath(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("VeryFitPro_V");
        stringBuffer.append(str2);
        stringBuffer.append(".apk");
        return stringBuffer.toString();
    }

    public static int getMessageTransform(String str) {
        if (str != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    return str.length();
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }
}