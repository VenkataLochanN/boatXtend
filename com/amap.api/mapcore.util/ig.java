package com.amap.api.mapcore.util;

import com.bumptech.glide.load.Key;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: DiskLruCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class ig implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final File f1321e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final File f1322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final File f1323g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final File f1324h;
    private final int i;
    private long j;
    private final int k;
    private Writer m;
    private int p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Pattern f1317a = Pattern.compile("[a-z0-9_-]{1,120}");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f1318b = Charset.forName("US-ASCII");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Charset f1319c = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final ThreadFactory r = new ThreadFactory() { // from class: com.amap.api.mapcore.util.ig.1

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f1325a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.f1325a.getAndIncrement());
        }
    };

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static ThreadPoolExecutor f1320d = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), r);
    private static final OutputStream t = new OutputStream() { // from class: com.amap.api.mapcore.util.ig.3
        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }
    };
    private long l = 0;
    private int n = 1000;
    private final LinkedHashMap<String, c> o = new LinkedHashMap<>(0, 0.75f, true);
    private long q = 0;
    private final Callable<Void> s = new Callable<Void>() { // from class: com.amap.api.mapcore.util.ig.2
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (ig.this) {
                if (ig.this.m == null) {
                    return null;
                }
                ig.this.m();
                if (ig.this.k()) {
                    ig.this.j();
                    ig.this.p = 0;
                }
                return null;
            }
        }
    };

    public void a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > 10000) {
            i = 10000;
        }
        this.n = i;
    }

    public static void a() {
        ThreadPoolExecutor threadPoolExecutor = f1320d;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        f1320d.shutdown();
    }

    public static ThreadPoolExecutor b() {
        try {
            if (f1320d == null || f1320d.isShutdown()) {
                f1320d = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), r);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f1320d;
    }

    private ig(File file, int i, int i2, long j) {
        this.f1321e = file;
        this.i = i;
        this.f1322f = new File(file, "journal");
        this.f1323g = new File(file, "journal.tmp");
        this.f1324h = new File(file, "journal.bkp");
        this.k = i2;
        this.j = j;
    }

    public static ig a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        ig igVar = new ig(file, i, i2, j);
        if (igVar.f1322f.exists()) {
            try {
                igVar.h();
                igVar.i();
                igVar.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(igVar.f1322f, true), f1318b));
                return igVar;
            } catch (Throwable unused) {
                igVar.f();
            }
        }
        file.mkdirs();
        ig igVar2 = new ig(file, i, i2, j);
        igVar2.j();
        return igVar2;
    }

    private void h() throws IOException {
        ih ihVar = new ih(new FileInputStream(this.f1322f), f1318b);
        try {
            String strA = ihVar.a();
            String strA2 = ihVar.a();
            String strA3 = ihVar.a();
            String strA4 = ihVar.a();
            String strA5 = ihVar.a();
            if (!"libcore.io.DiskLruCache".equals(strA) || !"1".equals(strA2) || !Integer.toString(this.i).equals(strA3) || !Integer.toString(this.k).equals(strA4) || !"".equals(strA5)) {
                throw new IOException("unexpected journal header: [" + strA + ", " + strA2 + ", " + strA4 + ", " + strA5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    d(ihVar.a());
                    i++;
                } catch (EOFException unused) {
                    this.p = i - this.o.size();
                    a(ihVar);
                    return;
                }
            }
        } catch (Throwable th) {
            a(ihVar);
            throw th;
        }
    }

    private void d(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.o.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        c cVar = this.o.get(strSubstring);
        if (cVar == null) {
            cVar = new c(strSubstring);
            this.o.put(strSubstring, cVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            cVar.f1341d = true;
            cVar.f1342e = null;
            cVar.a(strArrSplit);
            return;
        }
        if (iIndexOf2 != -1 || iIndexOf != 5 || !str.startsWith("DIRTY")) {
            if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith("READ")) {
                return;
            }
            throw new IOException("unexpected journal line: " + str);
        }
        cVar.f1342e = new a(cVar);
    }

    private void i() throws IOException {
        b(this.f1323g);
        Iterator<c> it = this.o.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.f1342e == null) {
                while (i < this.k) {
                    this.l += next.f1340c[i];
                    i++;
                }
            } else {
                next.f1342e = null;
                while (i < this.k) {
                    b(next.a(i));
                    b(next.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() throws IOException {
        if (this.m != null) {
            this.m.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f1323g), f1318b));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write("1");
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write(Integer.toString(this.i));
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write(Integer.toString(this.k));
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            for (c cVar : this.o.values()) {
                if (cVar.f1342e != null) {
                    bufferedWriter.write("DIRTY " + cVar.f1339b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + cVar.f1339b + cVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f1322f.exists()) {
                a(this.f1322f, this.f1324h, true);
            }
            a(this.f1323g, this.f1322f, false);
            this.f1324h.delete();
            this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f1322f, true), f1318b));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    private static void b(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            b(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized b a(String str) throws IOException {
        l();
        e(str);
        c cVar = this.o.get(str);
        if (cVar == null) {
            return null;
        }
        if (!cVar.f1341d) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.k];
        for (int i = 0; i < this.k; i++) {
            try {
                inputStreamArr[i] = new FileInputStream(cVar.a(i));
            } catch (FileNotFoundException unused) {
                for (int i2 = 0; i2 < this.k && inputStreamArr[i2] != null; i2++) {
                    a(inputStreamArr[i2]);
                }
                return null;
            }
        }
        this.p++;
        this.m.append((CharSequence) ("READ " + str + '\n'));
        if (k()) {
            b().submit(this.s);
        }
        return new b(str, cVar.f1343f, inputStreamArr, cVar.f1340c);
    }

    public a b(String str) throws IOException {
        return a(str, -1L);
    }

    private synchronized a a(String str, long j) throws IOException {
        l();
        e(str);
        c cVar = this.o.get(str);
        if (j != -1 && (cVar == null || cVar.f1343f != j)) {
            return null;
        }
        if (cVar == null) {
            cVar = new c(str);
            this.o.put(str, cVar);
        } else if (cVar.f1342e != null) {
            return null;
        }
        a aVar = new a(cVar);
        cVar.f1342e = aVar;
        this.m.write("DIRTY " + str + '\n');
        this.m.flush();
        return aVar;
    }

    public File c() {
        return this.f1321e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(a aVar, boolean z) throws IOException {
        c cVar = aVar.f1328b;
        if (cVar.f1342e != aVar) {
            throw new IllegalStateException();
        }
        if (z && !cVar.f1341d) {
            for (int i = 0; i < this.k; i++) {
                if (!aVar.f1329c[i]) {
                    aVar.b();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!cVar.b(i).exists()) {
                    aVar.b();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.k; i2++) {
            File fileB = cVar.b(i2);
            if (z) {
                if (fileB.exists()) {
                    File fileA = cVar.a(i2);
                    fileB.renameTo(fileA);
                    long j = cVar.f1340c[i2];
                    long length = fileA.length();
                    cVar.f1340c[i2] = length;
                    this.l = (this.l - j) + length;
                }
            } else {
                b(fileB);
            }
        }
        this.p++;
        cVar.f1342e = null;
        if (cVar.f1341d | z) {
            cVar.f1341d = true;
            this.m.write("CLEAN " + cVar.f1339b + cVar.a() + '\n');
            if (z) {
                long j2 = this.q;
                this.q = 1 + j2;
                cVar.f1343f = j2;
            }
        } else {
            this.o.remove(cVar.f1339b);
            this.m.write("REMOVE " + cVar.f1339b + '\n');
        }
        this.m.flush();
        if (this.l > this.j || k()) {
            b().submit(this.s);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        int i = this.p;
        return i >= 2000 && i >= this.o.size();
    }

    public synchronized boolean c(String str) throws IOException {
        l();
        e(str);
        c cVar = this.o.get(str);
        if (cVar != null && cVar.f1342e == null) {
            for (int i = 0; i < this.k; i++) {
                File fileA = cVar.a(i);
                if (fileA.exists() && !fileA.delete()) {
                    throw new IOException("failed to delete " + fileA);
                }
                this.l -= cVar.f1340c[i];
                cVar.f1340c[i] = 0;
            }
            this.p++;
            this.m.append((CharSequence) ("REMOVE " + str + '\n'));
            this.o.remove(str);
            if (k()) {
                b().submit(this.s);
            }
            return true;
        }
        return false;
    }

    public synchronized boolean d() {
        return this.m == null;
    }

    private void l() {
        if (this.m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void e() throws IOException {
        l();
        m();
        this.m.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.m == null) {
            return;
        }
        for (c cVar : new ArrayList(this.o.values())) {
            if (cVar.f1342e != null) {
                cVar.f1342e.b();
            }
        }
        m();
        this.m.close();
        this.m = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() throws IOException {
        while (true) {
            if (this.l <= this.j && this.o.size() <= this.n) {
                return;
            } else {
                c(this.o.entrySet().iterator().next().getKey());
            }
        }
    }

    public void f() throws IOException {
        close();
        a(this.f1321e);
    }

    private void e(String str) {
        if (f1317a.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class b implements Closeable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f1334b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long f1335c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final InputStream[] f1336d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final long[] f1337e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f1334b = str;
            this.f1335c = j;
            this.f1336d = inputStreamArr;
            this.f1337e = jArr;
        }

        public InputStream a(int i) {
            return this.f1336d[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (InputStream inputStream : this.f1336d) {
                ig.a(inputStream);
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c f1328b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean[] f1329c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1330d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1331e;

        private a(c cVar) {
            this.f1328b = cVar;
            this.f1329c = cVar.f1341d ? null : new boolean[ig.this.k];
        }

        public OutputStream a(int i) throws IOException {
            FileOutputStream fileOutputStream;
            C0012a c0012a;
            if (i < 0 || i >= ig.this.k) {
                throw new IllegalArgumentException("Expected index " + i + " to be greater than 0 and less than the maximum value count of " + ig.this.k);
            }
            synchronized (ig.this) {
                if (this.f1328b.f1342e != this) {
                    throw new IllegalStateException();
                }
                if (!this.f1328b.f1341d) {
                    this.f1329c[i] = true;
                }
                File fileB = this.f1328b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(fileB);
                } catch (FileNotFoundException unused) {
                    ig.this.f1321e.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(fileB);
                    } catch (FileNotFoundException unused2) {
                        return ig.t;
                    }
                }
                c0012a = new C0012a(fileOutputStream);
            }
            return c0012a;
        }

        public void a() throws IOException {
            if (this.f1330d) {
                ig.this.a(this, false);
                ig.this.c(this.f1328b.f1339b);
            } else {
                ig.this.a(this, true);
            }
            this.f1331e = true;
        }

        public void b() throws IOException {
            ig.this.a(this, false);
        }

        /* JADX INFO: renamed from: com.amap.api.mapcore.util.ig$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DiskLruCache.java */
        private class C0012a extends FilterOutputStream {
            private C0012a(OutputStream outputStream) {
                super(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    a.this.f1330d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.this.f1330d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    a.this.f1330d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    a.this.f1330d = true;
                }
            }
        }
    }

    public static void a(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                a(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    private final class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f1339b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long[] f1340c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1341d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private a f1342e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f1343f;

        private c(String str) {
            this.f1339b = str;
            this.f1340c = new long[ig.this.k];
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.f1340c) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String[] strArr) throws IOException {
            if (strArr.length != ig.this.k) {
                throw b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.f1340c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(ig.this.f1321e, this.f1339b + "." + i);
        }

        public File b(int i) {
            return new File(ig.this.f1321e, this.f1339b + "." + i + ".tmp");
        }
    }
}