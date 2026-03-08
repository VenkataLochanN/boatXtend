package com.ido.common.utils;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: loaded from: classes2.dex */
public class IOUtil {
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                Log.d(th.getMessage(), th.getMessage());
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable th) {
                Log.d(th.getMessage(), th.getMessage());
            }
        }
    }

    public static byte[] readBytes(InputStream inputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    closeQuietly(byteArrayOutputStream);
                    return byteArray;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(byteArrayOutputStream);
            throw th;
        }
    }

    public static byte[] readBytes(InputStream inputStream, long j, int i) throws IOException {
        if (j > 0) {
            while (j > 0) {
                long jSkip = inputStream.skip(j);
                if (jSkip <= 0) {
                    break;
                }
                j -= jSkip;
            }
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) inputStream.read();
        }
        return bArr;
    }

    public static String readStr(InputStream inputStream) throws IOException {
        return readStr(inputStream, Key.STRING_CHARSET_NAME);
    }

    public static String readStr(InputStream inputStream, String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            str = Key.STRING_CHARSET_NAME;
        }
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[1024];
        while (true) {
            int i = inputStreamReader.read(cArr);
            if (i >= 0) {
                sb.append(cArr, 0, i);
            } else {
                return sb.toString();
            }
        }
    }

    public static void writeStr(OutputStream outputStream, String str) throws IOException {
        writeStr(outputStream, str, Key.STRING_CHARSET_NAME);
    }

    public static void writeStr(OutputStream outputStream, String str, String str2) throws IOException {
        if (TextUtils.isEmpty(str2)) {
            str2 = Key.STRING_CHARSET_NAME;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, str2);
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (!(outputStream instanceof BufferedOutputStream)) {
            outputStream = new BufferedOutputStream(outputStream);
        }
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    public static boolean deleteFileOrDir(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                deleteFileOrDir(file2);
            }
        }
        return file.delete();
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}