package com.autonavi.base.amap.mapcore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.amap.api.mapcore.util.er;
import com.amap.api.mapcore.util.ex;
import com.amap.api.mapcore.util.ey;
import com.amap.api.mapcore.util.hn;
import com.amap.api.maps.MapsInitializer;
import com.bumptech.glide.load.Key;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {
    private static final int BUFFER = 1024;
    private static final String FILE_PATH_ENTRY_BACK = "..";
    private static final String FILE_PATH_ENTRY_SEPARATOR = "/";
    private static final String FILE_PATH_ENTRY_SEPARATOR1 = "\\";
    private static final String FILE_PATH_ENTRY_SEPARATOR2 = "%";
    private static final String TAG = "FileUtil";
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    public static class UnZipFileBrake {
        public boolean mIsAborted = false;
    }

    public interface ZipCompressProgressListener {
        void onFinishProgress(long j);
    }

    public static void createNoMediaFileIfNotExist(String str) {
    }

    public static void copy(Context context, String str, File file) throws Exception {
        file.delete();
        InputStream inputStreamOpen = context.getAssets().open(str);
        byte[] bArr = new byte[inputStreamOpen.available()];
        inputStreamOpen.read(bArr);
        inputStreamOpen.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
    }

    public static boolean deleteFile(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isFile()) {
                    if (!fileArrListFiles[i].delete()) {
                        return false;
                    }
                } else {
                    if (!deleteFile(fileArrListFiles[i])) {
                        return false;
                    }
                    fileArrListFiles[i].delete();
                }
            }
        }
        file.delete();
        return true;
    }

    public static String getMapBaseStorage(Context context) {
        String string;
        if (context == null) {
            return null;
        }
        String str = Build.VERSION.SDK_INT > 18 ? "map_base_path_v44" : "map_base_path";
        SharedPreferences sharedPreferences = context.getSharedPreferences("base_path", 0);
        if (MapsInitializer.sdcardDir != null && MapsInitializer.sdcardDir.trim().length() > 0) {
            string = MapsInitializer.sdcardDir + File.separatorChar;
        } else {
            string = sharedPreferences.getString(str, "");
        }
        if (string != null && string.length() > 2) {
            File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory()) {
                if (checkCanWrite(file)) {
                    return string;
                }
                String str2 = context.getCacheDir().toString() + AeUtil.ROOTPATH;
                if (str2 != null && str2.length() > 2) {
                    File file2 = new File(str2);
                    if (!file2.exists()) {
                        file2.mkdir();
                    }
                    if (file2.isDirectory()) {
                        return str2;
                    }
                }
            }
        }
        String str3 = getExternalStroragePath(context) + AeUtil.ROOTPATH;
        if (str3 != null && str3.length() > 2) {
            File file3 = new File(str3);
            if (!file3.exists()) {
                file3.mkdir();
            }
            if (file3.isDirectory() && file3.canWrite()) {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString(str, str3);
                editorEdit.commit();
                createNoMediaFileIfNotExist(str3);
                return str3;
            }
        }
        String str4 = context.getCacheDir().toString() + AeUtil.ROOTPATH;
        if (str4 != null && str4.length() > 2) {
            File file4 = new File(str4);
            if (!file4.exists()) {
                file4.mkdir();
            }
            if (file4.isDirectory()) {
            }
        }
        return str4;
    }

    public static boolean checkCanWrite(File file) {
        if (file == null || !file.canWrite()) {
            return false;
        }
        File file2 = new File(file, "amap.tmp");
        try {
            file2.createNewFile();
            if (!file2.exists()) {
                return false;
            }
            try {
                file2.delete();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getExternalStroragePath(android.content.Context r17) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.base.amap.mapcore.FileUtil.getExternalStroragePath(android.content.Context):java.lang.String");
    }

    public static void writeDatasToFile(String str, byte[] bArr) {
        ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        try {
            if (bArr != null) {
                try {
                    if (bArr.length != 0) {
                        File file = new File(str);
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(bArr);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    public static byte[] readFileContents(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Throwable th) {
            hn.c(th, TAG, "readFileContents");
            er.a(th);
            ey.b(ex.f799f, "read file from disk failed " + th.getMessage());
            return null;
        }
    }

    public static void saveFile(String str, String str2, boolean z) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + FILE_PATH_ENTRY_SEPARATOR + str2);
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            fileOutputStream.write(str.getBytes("utf-8"));
            fileOutputStream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static byte[] readFileContentsFromAssets(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            int iAvailable = inputStreamOpen.available();
            if (iAvailable == 0) {
                return null;
            }
            byte[] bArr = new byte[iAvailable];
            for (int i = 0; i < iAvailable; i += inputStreamOpen.read(bArr, i, iAvailable - i)) {
            }
            inputStreamOpen.close();
            return bArr;
        } catch (IOException e2) {
            ey.b(ex.f799f, "read file from assets failed " + e2.getMessage());
            return null;
        } catch (OutOfMemoryError e3) {
            ey.b(ex.f799f, "read file from assets failed " + e3.getMessage());
            return null;
        }
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(indexOfLastSeparator(str) + 1);
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    public static boolean isSafeEntryName(String str) {
        return (str.contains(FILE_PATH_ENTRY_BACK) || str.contains(FILE_PATH_ENTRY_SEPARATOR) || str.contains(FILE_PATH_ENTRY_SEPARATOR1) || str.contains(FILE_PATH_ENTRY_SEPARATOR2)) ? false : true;
    }

    public static byte[] compress(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(str2));
            gZIPOutputStream.close();
        } catch (IOException e2) {
            Log.e("gzip compress error.", e2.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String uncompressToString(byte[] bArr) {
        return uncompressToString(bArr, Key.STRING_CHARSET_NAME);
    }

    public static String uncompressToString(byte[] bArr, String str) throws Throwable {
        InputStream zipInputStream;
        byte[] bArr2;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zipInputStream = getZipInputStream(bArr);
        } catch (IOException e2) {
            e = e2;
            zipInputStream = null;
        } catch (Throwable th) {
            th = th;
            zipInputStream = null;
        }
        if (zipInputStream != null) {
            try {
                try {
                    bArr2 = new byte[256];
                } catch (Throwable th2) {
                    th = th2;
                }
                while (true) {
                    int i = zipInputStream.read(bArr2);
                    if (i >= 0) {
                        byteArrayOutputStream.write(bArr2, 0, i);
                    } else {
                        String string = byteArrayOutputStream.toString(str);
                        safelyCloseFile(zipInputStream);
                        return string;
                    }
                    th = th2;
                    safelyCloseFile(zipInputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                Log.e("gzip compress error.", e.getMessage());
                safelyCloseFile(zipInputStream);
                return null;
            }
        }
        safelyCloseFile(zipInputStream);
        return null;
    }

    private static InputStream getZipInputStream(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        if (isGzip(bArr)) {
            return new GZIPInputStream(byteArrayInputStream);
        }
        return new ZipInputStream(byteArrayInputStream);
    }

    private static void safelyCloseFile(InputStream inputStream) {
        if (inputStream != null) {
            try {
                if (inputStream instanceof ZipInputStream) {
                    ((ZipInputStream) inputStream).closeEntry();
                }
                inputStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    private static byte[] readByteByStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static Map<String, byte[]> uncompressToByteWithKeys(byte[] bArr, String[] strArr) {
        InputStream zipInputStream;
        HashMap map = new HashMap();
        if (bArr == null || bArr.length == 0) {
            return map;
        }
        try {
            zipInputStream = getZipInputStream(bArr);
        } catch (Throwable th) {
            th = th;
            zipInputStream = null;
        }
        try {
            if (zipInputStream instanceof ZipInputStream) {
                ZipInputStream zipInputStream2 = (ZipInputStream) zipInputStream;
                while (true) {
                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    if (!nextEntry.isDirectory()) {
                        try {
                            String name = nextEntry.getName();
                            if (!isSafeEntryName(name)) {
                                Log.e("gzip compress error.", "gzip name contains ../ " + name);
                                return null;
                            }
                            int length = strArr.length;
                            int i = 0;
                            while (true) {
                                if (i < length) {
                                    String str = strArr[i];
                                    if (name.equals(str)) {
                                        byte[] byteByStream = readByteByStream(zipInputStream2);
                                        if (byteByStream != null) {
                                            map.put(str, byteByStream);
                                        }
                                    } else {
                                        i++;
                                    }
                                }
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    zipInputStream2.closeEntry();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                Log.e("gzip compress error.", th.getMessage());
                return map;
            } finally {
                safelyCloseFile(zipInputStream);
            }
        }
        return map;
    }

    public static Pair<String, byte[]> uncompressToByte(byte[] bArr) {
        InputStream zipInputStream;
        String name;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zipInputStream = getZipInputStream(bArr);
        } catch (Throwable th) {
            th = th;
            zipInputStream = null;
        }
        try {
            if (zipInputStream instanceof ZipInputStream) {
                name = ((ZipInputStream) zipInputStream).getNextEntry().getName();
                if (!isSafeEntryName(name)) {
                    Log.e("gzip compress error.", "gzip name contains ../ " + name);
                    return null;
                }
            } else {
                name = "";
            }
            if (zipInputStream != null) {
                byte[] bArr2 = new byte[256];
                while (true) {
                    int i = zipInputStream.read(bArr2);
                    if (i >= 0) {
                        byteArrayOutputStream.write(bArr2, 0, i);
                    } else {
                        return new Pair<>(name, byteArrayOutputStream.toByteArray());
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                Log.e("gzip compress error.", th.getMessage());
            } finally {
                safelyCloseFile(zipInputStream);
            }
        }
        return null;
    }

    public static boolean isGzip(byte[] bArr) {
        return ((bArr[1] & UByte.MAX_VALUE) | (bArr[0] << 8)) == 8075;
    }

    public static void decompress(InputStream inputStream, String str) throws Exception {
        decompress(inputStream, str, 0L, null);
    }

    private static void decompress(InputStream inputStream, String str, long j, ZipCompressProgressListener zipCompressProgressListener) throws Exception {
        CheckedInputStream checkedInputStream = new CheckedInputStream(inputStream, new CRC32());
        ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
        decompress(null, new File(str), zipInputStream, j, zipCompressProgressListener, null);
        zipInputStream.close();
        checkedInputStream.close();
    }

    private static void decompress(File file, File file2, ZipInputStream zipInputStream, long j, ZipCompressProgressListener zipCompressProgressListener, UnZipFileBrake unZipFileBrake) throws Exception {
        boolean z = false;
        int iDecompressFile = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            if (unZipFileBrake != null && unZipFileBrake.mIsAborted) {
                zipInputStream.closeEntry();
                return;
            }
            String name = nextEntry.getName();
            if (TextUtils.isEmpty(name) || !isSafeEntryName(name)) {
                break;
            }
            File file3 = new File(file2.getPath() + File.separator + name);
            fileProber(file3);
            if (nextEntry.isDirectory()) {
                file3.mkdirs();
            } else {
                iDecompressFile += decompressFile(file3, zipInputStream, iDecompressFile, j, zipCompressProgressListener, unZipFileBrake);
            }
            zipInputStream.closeEntry();
        }
        z = true;
        if (!z || file == null) {
            return;
        }
        try {
            file.delete();
        } catch (Exception unused) {
        }
    }

    private static void fileProber(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            return;
        }
        fileProber(parentFile);
        parentFile.mkdir();
    }

    private static int decompressFile(File file, ZipInputStream zipInputStream, long j, long j2, ZipCompressProgressListener zipCompressProgressListener, UnZipFileBrake unZipFileBrake) throws Exception {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bArr = new byte[1024];
        int i = 0;
        while (true) {
            int i2 = zipInputStream.read(bArr, 0, 1024);
            if (i2 != -1) {
                if (unZipFileBrake != null && unZipFileBrake.mIsAborted) {
                    bufferedOutputStream.close();
                    return i;
                }
                bufferedOutputStream.write(bArr, 0, i2);
                i += i2;
                if (j2 > 0 && zipCompressProgressListener != null) {
                    long j3 = ((((long) i) + j) * 100) / j2;
                    if (unZipFileBrake == null || !unZipFileBrake.mIsAborted) {
                        zipCompressProgressListener.onFinishProgress(j3);
                    }
                }
            } else {
                bufferedOutputStream.close();
                return i;
            }
        }
    }
}