package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* JADX INFO: compiled from: FileStorageModel.java */
/* JADX INFO: loaded from: classes.dex */
public class gy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1173a = gt.c("SYmFja3Vwcw");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f1174b = gt.c("SLmFkaXU");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f1175c = gt.c("JIw");

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r5v9 */
    public static synchronized void a(Context context, String str, String str2) {
        FileChannel channel;
        String strA = a(context, false);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        String str3 = str + f1175c + str2;
        ?? file = new File(strA + File.separator + f1173a);
        File file2 = new File((File) file, f1174b);
        FileLock fileLockTryLock = null;
        try {
            try {
                if (!file.exists() || file.isDirectory()) {
                    file.mkdirs();
                }
                file2.createNewFile();
                file = new RandomAccessFile(file2, "rws");
                try {
                    channel = file.getChannel();
                    try {
                        fileLockTryLock = channel.tryLock();
                        if (fileLockTryLock != null) {
                            channel.write(ByteBuffer.wrap(str3.getBytes(Key.STRING_CHARSET_NAME)));
                        }
                        if (fileLockTryLock != null) {
                            try {
                                fileLockTryLock.release();
                            } catch (IOException unused) {
                            }
                        }
                    } catch (Throwable unused2) {
                        if (fileLockTryLock != null) {
                            try {
                                fileLockTryLock.release();
                            } catch (IOException unused3) {
                            }
                        }
                        if (channel != null) {
                            channel.close();
                            file = file;
                        }
                        a(file);
                    }
                } catch (Throwable unused4) {
                    channel = null;
                }
            } catch (Throwable unused5) {
                channel = null;
                file = 0;
            }
            if (channel != null) {
                channel.close();
                file = file;
            }
        } catch (IOException unused6) {
        }
        a(file);
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    private static String a(Context context, boolean z) {
        StorageManager storageManager = Build.VERSION.SDK_INT >= 9 ? (StorageManager) context.getSystemService("storage") : null;
        try {
            Class<?> cls = Class.forName(gt.c("SYW5kcm9pZC5vcy5zdG9yYWdlLlN0b3JhZ2VWb2x1bWU"));
            Method method = storageManager.getClass().getMethod(gt.c("FZ2V0Vm9sdW1lTGlzdA"), new Class[0]);
            Method method2 = cls.getMethod(gt.c("ZZ2V0UGF0aA"), new Class[0]);
            Method method3 = cls.getMethod(gt.c("AaXNSZW1vdmFibGUK"), new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(objInvoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (z == ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return str;
                }
            }
            return null;
        } catch (Throwable unused) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }
}