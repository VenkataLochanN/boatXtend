package com.tencent.bugly.proguard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StringBuilder f5780a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5781b;

    private void a(String str) {
        for (int i = 0; i < this.f5781b; i++) {
            this.f5780a.append('\t');
        }
        if (str != null) {
            StringBuilder sb = this.f5780a;
            sb.append(str);
            sb.append(": ");
        }
    }

    public z(StringBuilder sb, int i) {
        this.f5781b = 0;
        this.f5780a = sb;
        this.f5781b = i;
    }

    public static boolean a(File file, String str, long j, boolean z) {
        if (file == null) {
            return false;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            boolean zA = a(bufferedWriter, str.toCharArray(), str.length(), file.length(), j);
            bufferedWriter.close();
            return zA;
        } catch (Throwable th) {
            y.a(th);
            return false;
        }
    }

    public z a(boolean z, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append(z ? 'T' : 'F');
        sb.append('\n');
        return this;
    }

    public z a(byte b2, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append((int) b2);
        sb.append('\n');
        return this;
    }

    public z a(char c2, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append(c2);
        sb.append('\n');
        return this;
    }

    public z a(short s, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append((int) s);
        sb.append('\n');
        return this;
    }

    public z a(int i, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append(i);
        sb.append('\n');
        return this;
    }

    private static boolean a(Writer writer, char[] cArr, int i, long j, long j2) {
        if (j >= j2) {
            return false;
        }
        try {
            if (((long) (i << 1)) + j <= j2) {
                writer.write(cArr, 0, i);
            } else {
                writer.write(cArr, 0, (int) ((j2 - j) / 2));
            }
            writer.flush();
            return true;
        } catch (IOException e2) {
            y.a(e2);
            return false;
        }
    }

    public z a(long j, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append(j);
        sb.append('\n');
        return this;
    }

    public z a(float f2, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append(f2);
        sb.append('\n');
        return this;
    }

    public z a(double d2, String str) {
        a(str);
        StringBuilder sb = this.f5780a;
        sb.append(d2);
        sb.append('\n');
        return this;
    }

    public z a(String str, String str2) {
        a(str2);
        if (str == null) {
            this.f5780a.append("null\n");
        } else {
            StringBuilder sb = this.f5780a;
            sb.append(str);
            sb.append('\n');
        }
        return this;
    }

    public static void a(String str, String str2, String str3, long j) {
        try {
            int i = 0;
            for (File file : a(str, str2, str3, j, false, null)) {
                y.c("File %s is to be deleted.", file.getName());
                if (file.delete()) {
                    i++;
                }
            }
            y.c("Number of overdue trace files that has deleted: " + i, new Object[0]);
        } catch (Throwable th) {
            y.a(th);
        }
    }

    public z a(byte[] bArr, String str) {
        a(str);
        if (bArr == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (bArr.length == 0) {
            StringBuilder sb = this.f5780a;
            sb.append(bArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(bArr.length);
        sb2.append(", [\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        for (byte b2 : bArr) {
            zVar.a(b2, (String) null);
        }
        a(']', (String) null);
        return this;
    }

    private static List<File> a(String str, final String str2, final String str3, long j, boolean z, Comparator<File> comparator) {
        ArrayList arrayList = new ArrayList();
        if (str2 == null || str3 == null) {
            y.d("prefix %s and/or postfix %s is null.", str2, str3);
            return arrayList;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return arrayList;
        }
        try {
            File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.tencent.bugly.proguard.z.1
                @Override // java.io.FilenameFilter
                public final boolean accept(File file2, String str4) {
                    return str4 != null && str4.startsWith(str2) && str4.endsWith(str3);
                }
            });
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                return a(fileArrListFiles, str2, str3, 0L, jCurrentTimeMillis - j);
            }
            return arrayList;
        } catch (Throwable th) {
            y.a(th);
            return arrayList;
        }
    }

    public z a(short[] sArr, String str) {
        a(str);
        if (sArr == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (sArr.length == 0) {
            StringBuilder sb = this.f5780a;
            sb.append(sArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(sArr.length);
        sb2.append(", [\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        for (short s : sArr) {
            zVar.a(s, (String) null);
        }
        a(']', (String) null);
        return this;
    }

    public z a(int[] iArr, String str) {
        a(str);
        if (iArr == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (iArr.length == 0) {
            StringBuilder sb = this.f5780a;
            sb.append(iArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(iArr.length);
        sb2.append(", [\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        for (int i : iArr) {
            zVar.a(i, (String) null);
        }
        a(']', (String) null);
        return this;
    }

    public z a(long[] jArr, String str) {
        a(str);
        if (jArr == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (jArr.length == 0) {
            StringBuilder sb = this.f5780a;
            sb.append(jArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(jArr.length);
        sb2.append(", [\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        for (long j : jArr) {
            zVar.a(j, (String) null);
        }
        a(']', (String) null);
        return this;
    }

    private static List<File> a(File[] fileArr, String str, String str2, long j, long j2) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            long jA = a(file.getName(), str, str2);
            if (jA >= 0 && j <= jA && jA <= j2) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    public z a(float[] fArr, String str) {
        a(str);
        if (fArr == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (fArr.length == 0) {
            StringBuilder sb = this.f5780a;
            sb.append(fArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(fArr.length);
        sb2.append(", [\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        for (float f2 : fArr) {
            zVar.a(f2, (String) null);
        }
        a(']', (String) null);
        return this;
    }

    public static long a(String str, String str2, String str3) {
        if (str == null) {
            y.d("File name is null.", new Object[0]);
            return -1L;
        }
        try {
            if (str.startsWith(str2) && str.endsWith(str3)) {
                return Long.parseLong(str.substring(str2.length(), str.indexOf(str3)));
            }
        } catch (Throwable th) {
            y.a(th);
        }
        return -1L;
    }

    public z a(double[] dArr, String str) {
        a(str);
        if (dArr == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (dArr.length == 0) {
            StringBuilder sb = this.f5780a;
            sb.append(dArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(dArr.length);
        sb2.append(", [\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        for (double d2 : dArr) {
            zVar.a(d2, (String) null);
        }
        a(']', (String) null);
        return this;
    }

    public static boolean a(String str, String str2, int i) {
        boolean z = true;
        y.c("rqdp{  sv sd start} %s", str);
        if (str2 != null && str2.trim().length() > 0) {
            File file = new File(str);
            try {
                if (!file.exists()) {
                    if (file.getParentFile() != null) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                }
                long j = i;
                if (file.length() >= j) {
                    z = false;
                }
                return a(file, str2, j, z);
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    public <K, V> z a(Map<K, V> map, String str) {
        a(str);
        if (map == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (map.isEmpty()) {
            StringBuilder sb = this.f5780a;
            sb.append(map.size());
            sb.append(", {}\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(map.size());
        sb2.append(", {\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        z zVar2 = new z(this.f5780a, this.f5781b + 2);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            zVar.a('(', (String) null);
            zVar2.a(entry.getKey(), (String) null);
            zVar2.a(entry.getValue(), (String) null);
            zVar.a(')', (String) null);
        }
        a('}', (String) null);
        return this;
    }

    public <T> z a(T[] tArr, String str) {
        a(str);
        if (tArr == null) {
            this.f5780a.append("null\n");
            return this;
        }
        if (tArr.length == 0) {
            StringBuilder sb = this.f5780a;
            sb.append(tArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f5780a;
        sb2.append(tArr.length);
        sb2.append(", [\n");
        z zVar = new z(this.f5780a, this.f5781b + 1);
        for (T t : tArr) {
            zVar.a(t, (String) null);
        }
        a(']', (String) null);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> z a(Collection<T> collection, String str) {
        if (collection == null) {
            a(str);
            this.f5780a.append("null\t");
            return this;
        }
        return a(collection.toArray(), str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> z a(T t, String str) {
        if (t == 0) {
            this.f5780a.append("null\n");
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((Collection) t, str);
        } else if (t instanceof j) {
            a((j) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new b("write object error: unsupport type.");
        }
        return this;
    }

    public z a(j jVar, String str) {
        a('{', str);
        if (jVar == null) {
            StringBuilder sb = this.f5780a;
            sb.append('\t');
            sb.append("null");
        } else {
            jVar.a(this.f5780a, this.f5781b + 1);
        }
        a('}', (String) null);
        return this;
    }
}