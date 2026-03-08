package com.loc;

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
/* JADX INFO: loaded from: classes3.dex */
public final class ao implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final File f4741e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final File f4742f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final File f4743g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final File f4744h;
    private long j;
    private Writer m;
    private int p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Pattern f4737a = Pattern.compile("[a-z0-9_-]{1,120}");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f4738b = Charset.forName("US-ASCII");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Charset f4739c = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final ThreadFactory r = new ThreadFactory() { // from class: com.loc.ao.1

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f4745a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.f4745a.getAndIncrement());
        }
    };

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static ThreadPoolExecutor f4740d = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), r);
    private static final OutputStream t = new OutputStream() { // from class: com.loc.ao.3
        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
        }
    };
    private long l = 0;
    private int n = 1000;
    private final LinkedHashMap<String, c> o = new LinkedHashMap<>(0, 0.75f, true);
    private long q = 0;
    private final Callable<Void> s = new Callable<Void>() { // from class: com.loc.ao.2
        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (ao.this) {
                if (ao.this.m == null) {
                    return null;
                }
                ao.this.l();
                if (ao.this.j()) {
                    ao.this.i();
                    ao.e(ao.this);
                }
                return null;
            }
        }
    };
    private final int i = 1;
    private final int k = 1;

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c f4748b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean[] f4749c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f4750d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f4751e;

        /* JADX INFO: renamed from: com.loc.ao$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DiskLruCache.java */
        private class C0133a extends FilterOutputStream {
            private C0133a(OutputStream outputStream) {
                super(outputStream);
            }

            /* synthetic */ C0133a(a aVar, OutputStream outputStream, byte b2) {
                this(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }
        }

        private a(c cVar) {
            this.f4748b = cVar;
            this.f4749c = cVar.f4761d ? null : new boolean[ao.this.k];
        }

        /* synthetic */ a(ao aoVar, c cVar, byte b2) {
            this(cVar);
        }

        static /* synthetic */ boolean c(a aVar) {
            aVar.f4750d = true;
            return true;
        }

        public final OutputStream a() throws IOException {
            FileOutputStream fileOutputStream;
            C0133a c0133a;
            if (ao.this.k <= 0) {
                throw new IllegalArgumentException("Expected index 0 to be greater than 0 and less than the maximum value count of " + ao.this.k);
            }
            synchronized (ao.this) {
                if (this.f4748b.f4762e != this) {
                    throw new IllegalStateException();
                }
                byte b2 = 0;
                if (!this.f4748b.f4761d) {
                    this.f4749c[0] = true;
                }
                File fileB = this.f4748b.b(0);
                try {
                    fileOutputStream = new FileOutputStream(fileB);
                } catch (FileNotFoundException unused) {
                    ao.this.f4741e.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(fileB);
                    } catch (FileNotFoundException unused2) {
                        return ao.t;
                    }
                }
                c0133a = new C0133a(this, fileOutputStream, b2);
            }
            return c0133a;
        }

        public final void b() throws IOException {
            if (this.f4750d) {
                ao.this.a(this, false);
                ao.this.c(this.f4748b.f4759b);
            } else {
                ao.this.a(this, true);
            }
            this.f4751e = true;
        }

        public final void c() throws IOException {
            ao.this.a(this, false);
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class b implements Closeable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f4754b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long f4755c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final InputStream[] f4756d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final long[] f4757e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f4754b = str;
            this.f4755c = j;
            this.f4756d = inputStreamArr;
            this.f4757e = jArr;
        }

        /* synthetic */ b(ao aoVar, String str, long j, InputStream[] inputStreamArr, long[] jArr, byte b2) {
            this(str, j, inputStreamArr, jArr);
        }

        public final InputStream a() {
            return this.f4756d[0];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            for (InputStream inputStream : this.f4756d) {
                ao.a(inputStream);
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    private final class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f4759b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long[] f4760c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f4761d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private a f4762e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f4763f;

        private c(String str) {
            this.f4759b = str;
            this.f4760c = new long[ao.this.k];
        }

        /* synthetic */ c(ao aoVar, String str, byte b2) {
            this(str);
        }

        private static IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        static /* synthetic */ void a(c cVar, String[] strArr) throws IOException {
            if (strArr.length != ao.this.k) {
                throw a(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    cVar.f4760c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw a(strArr);
                }
            }
        }

        static /* synthetic */ boolean a(c cVar) {
            cVar.f4761d = true;
            return true;
        }

        public final File a(int i) {
            return new File(ao.this.f4741e, this.f4759b + "." + i);
        }

        public final String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.f4760c) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        public final File b(int i) {
            return new File(ao.this.f4741e, this.f4759b + "." + i + ".tmp");
        }
    }

    private ao(File file, long j) {
        this.f4741e = file;
        this.f4742f = new File(file, "journal");
        this.f4743g = new File(file, "journal.tmp");
        this.f4744h = new File(file, "journal.bkp");
        this.j = j;
    }

    public static ao a(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
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
        ao aoVar = new ao(file, j);
        if (aoVar.f4742f.exists()) {
            try {
                aoVar.g();
                aoVar.h();
                aoVar.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aoVar.f4742f, true), f4738b));
                return aoVar;
            } catch (Throwable unused) {
                aoVar.d();
            }
        }
        file.mkdirs();
        ao aoVar2 = new ao(file, j);
        aoVar2.i();
        return aoVar2;
    }

    public static void a() {
        ThreadPoolExecutor threadPoolExecutor = f4740d;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        f4740d.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(a aVar, boolean z) throws IOException {
        c cVar = aVar.f4748b;
        if (cVar.f4762e != aVar) {
            throw new IllegalStateException();
        }
        if (z && !cVar.f4761d) {
            for (int i = 0; i < this.k; i++) {
                if (!aVar.f4749c[i]) {
                    aVar.c();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!cVar.b(i).exists()) {
                    aVar.c();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.k; i2++) {
            File fileB = cVar.b(i2);
            if (!z) {
                a(fileB);
            } else if (fileB.exists()) {
                File fileA = cVar.a(i2);
                fileB.renameTo(fileA);
                long j = cVar.f4760c[i2];
                long length = fileA.length();
                cVar.f4760c[i2] = length;
                this.l = (this.l - j) + length;
            }
        }
        this.p++;
        cVar.f4762e = null;
        if (cVar.f4761d || z) {
            c.a(cVar);
            this.m.write("CLEAN " + cVar.f4759b + cVar.a() + '\n');
            if (z) {
                long j2 = this.q;
                this.q = 1 + j2;
                cVar.f4763f = j2;
            }
        } else {
            this.o.remove(cVar.f4759b);
            this.m.write("REMOVE " + cVar.f4759b + '\n');
        }
        this.m.flush();
        if (this.l > this.j || j()) {
            f().submit(this.s);
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

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private static void b(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    private synchronized a d(String str) throws IOException {
        k();
        e(str);
        c cVar = this.o.get(str);
        byte b2 = 0;
        if (cVar == null) {
            cVar = new c(this, str, b2);
            this.o.put(str, cVar);
        } else if (cVar.f4762e != null) {
            return null;
        }
        a aVar = new a(this, cVar, b2);
        cVar.f4762e = aVar;
        this.m.write("DIRTY " + str + '\n');
        this.m.flush();
        return aVar;
    }

    static /* synthetic */ int e(ao aoVar) {
        aoVar.p = 0;
        return 0;
    }

    private static void e(String str) {
        if (f4737a.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    private static ThreadPoolExecutor f() {
        try {
            if (f4740d == null || f4740d.isShutdown()) {
                f4740d = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), r);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f4740d;
    }

    private void g() throws IOException {
        String strA;
        String strSubstring;
        ap apVar = new ap(new FileInputStream(this.f4742f), f4738b);
        try {
            String strA2 = apVar.a();
            String strA3 = apVar.a();
            String strA4 = apVar.a();
            String strA5 = apVar.a();
            String strA6 = apVar.a();
            if (!"libcore.io.DiskLruCache".equals(strA2) || !"1".equals(strA3) || !Integer.toString(this.i).equals(strA4) || !Integer.toString(this.k).equals(strA5) || !"".equals(strA6)) {
                throw new IOException("unexpected journal header: [" + strA2 + ", " + strA3 + ", " + strA5 + ", " + strA6 + "]");
            }
            byte b2 = 0;
            int i = 0;
            while (true) {
                try {
                    strA = apVar.a();
                    int iIndexOf = strA.indexOf(32);
                    if (iIndexOf == -1) {
                        throw new IOException("unexpected journal line: " + strA);
                    }
                    int i2 = iIndexOf + 1;
                    int iIndexOf2 = strA.indexOf(32, i2);
                    if (iIndexOf2 == -1) {
                        strSubstring = strA.substring(i2);
                        if (iIndexOf == 6 && strA.startsWith("REMOVE")) {
                            this.o.remove(strSubstring);
                        }
                        i++;
                    } else {
                        strSubstring = strA.substring(i2, iIndexOf2);
                    }
                    c cVar = this.o.get(strSubstring);
                    if (cVar == null) {
                        cVar = new c(this, strSubstring, b2);
                        this.o.put(strSubstring, cVar);
                    }
                    if (iIndexOf2 != -1 && iIndexOf == 5 && strA.startsWith("CLEAN")) {
                        String[] strArrSplit = strA.substring(iIndexOf2 + 1).split(" ");
                        c.a(cVar);
                        cVar.f4762e = null;
                        c.a(cVar, strArrSplit);
                    } else if (iIndexOf2 == -1 && iIndexOf == 5 && strA.startsWith("DIRTY")) {
                        cVar.f4762e = new a(this, cVar, b2);
                    } else if (iIndexOf2 != -1 || iIndexOf != 4 || !strA.startsWith("READ")) {
                        break;
                    }
                    i++;
                } catch (EOFException unused) {
                    this.p = i - this.o.size();
                    a(apVar);
                    return;
                }
            }
            throw new IOException("unexpected journal line: " + strA);
        } catch (Throwable th) {
            a(apVar);
            throw th;
        }
    }

    private void h() throws IOException {
        a(this.f4743g);
        Iterator<c> it = this.o.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.f4762e == null) {
                while (i < this.k) {
                    this.l += next.f4760c[i];
                    i++;
                }
            } else {
                next.f4762e = null;
                while (i < this.k) {
                    a(next.a(i));
                    a(next.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void i() throws IOException {
        if (this.m != null) {
            this.m.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f4743g), f4738b));
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
                bufferedWriter.write(cVar.f4762e != null ? "DIRTY " + cVar.f4759b + '\n' : "CLEAN " + cVar.f4759b + cVar.a() + '\n');
            }
            bufferedWriter.close();
            if (this.f4742f.exists()) {
                a(this.f4742f, this.f4744h, true);
            }
            a(this.f4743g, this.f4742f, false);
            this.f4744h.delete();
            this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f4742f, true), f4738b));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        int i = this.p;
        return i >= 2000 && i >= this.o.size();
    }

    private void k() {
        if (this.m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() throws IOException {
        while (true) {
            if (this.l <= this.j && this.o.size() <= this.n) {
                return;
            } else {
                c(this.o.entrySet().iterator().next().getKey());
            }
        }
    }

    public final synchronized b a(String str) throws IOException {
        k();
        e(str);
        c cVar = this.o.get(str);
        if (cVar == null) {
            return null;
        }
        if (!cVar.f4761d) {
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
        if (j()) {
            f().submit(this.s);
        }
        return new b(this, str, cVar.f4763f, inputStreamArr, cVar.f4760c, (byte) 0);
    }

    public final void a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > 10000) {
            i = 10000;
        }
        this.n = i;
    }

    public final a b(String str) throws IOException {
        return d(str);
    }

    public final File b() {
        return this.f4741e;
    }

    public final synchronized void c() throws IOException {
        k();
        l();
        this.m.flush();
    }

    public final synchronized boolean c(String str) throws IOException {
        k();
        e(str);
        c cVar = this.o.get(str);
        if (cVar != null && cVar.f4762e == null) {
            for (int i = 0; i < this.k; i++) {
                File fileA = cVar.a(i);
                if (fileA.exists() && !fileA.delete()) {
                    throw new IOException("failed to delete " + fileA);
                }
                this.l -= cVar.f4760c[i];
                cVar.f4760c[i] = 0;
            }
            this.p++;
            this.m.append((CharSequence) ("REMOVE " + str + '\n'));
            this.o.remove(str);
            if (j()) {
                f().submit(this.s);
            }
            return true;
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (this.m == null) {
            return;
        }
        for (c cVar : new ArrayList(this.o.values())) {
            if (cVar.f4762e != null) {
                cVar.f4762e.c();
            }
        }
        l();
        this.m.close();
        this.m = null;
    }

    public final void d() throws IOException {
        close();
        b(this.f4741e);
    }
}