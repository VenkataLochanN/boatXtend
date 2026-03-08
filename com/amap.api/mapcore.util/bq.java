package com.amap.api.mapcore.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: compiled from: FileAccessI.java */
/* JADX INFO: loaded from: classes.dex */
class bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RandomAccessFile f291a;

    public bq() throws IOException {
        this("", 0L);
    }

    public bq(String str, long j) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e2) {
                hn.c(e2, "FileAccessI", "create");
                e2.printStackTrace();
            }
        }
        this.f291a = new RandomAccessFile(str, "rw");
        this.f291a.seek(j);
    }

    public synchronized int a(byte[] bArr) throws IOException {
        this.f291a.write(bArr);
        return bArr.length;
    }

    public void a() {
        RandomAccessFile randomAccessFile = this.f291a;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.f291a = null;
        }
    }
}