package com.amap.api.mapcore.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: LruCache.java */
/* JADX INFO: loaded from: classes.dex */
public class ed<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinkedHashMap<K, V> f715a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f718d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f719e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f720f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f721g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f722h;

    protected void a(boolean z, K k, V v, V v2) {
    }

    protected int b(K k, V v) {
        return 1;
    }

    protected V b(K k) {
        return null;
    }

    public ed(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f717c = i;
        this.f715a = new LinkedHashMap<>(0, 0.75f, true);
    }

    public final V a(K k) {
        V vPut;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f715a.get(k);
            if (v != null) {
                this.f721g++;
                return v;
            }
            this.f722h++;
            V vB = b(k);
            if (vB == null) {
                return null;
            }
            synchronized (this) {
                this.f719e++;
                vPut = this.f715a.put(k, vB);
                if (vPut != null) {
                    this.f715a.put(k, vPut);
                } else {
                    this.f716b += c(k, vB);
                }
            }
            if (vPut != null) {
                a(false, k, vB, vPut);
                return vPut;
            }
            a(this.f717c);
            return vB;
        }
    }

    public final V a(K k, V v) {
        V vPut;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f718d++;
            this.f716b += c(k, v);
            vPut = this.f715a.put(k, v);
            if (vPut != null) {
                this.f716b -= c(k, vPut);
            }
        }
        if (vPut != null) {
            a(false, k, vPut, v);
        }
        a(this.f717c);
        return vPut;
    }

    private void a(int i) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.f716b >= 0 && this.f715a.isEmpty()) {
                    int i2 = this.f716b;
                }
                if (this.f716b <= i) {
                    return;
                }
                Iterator<Map.Entry<K, V>> it = this.f715a.entrySet().iterator();
                Map.Entry<K, V> next = null;
                while (it.hasNext()) {
                    next = it.next();
                }
                if (next == null) {
                    return;
                }
                key = next.getKey();
                value = next.getValue();
                this.f715a.remove(key);
                this.f716b -= c(key, value);
                this.f720f++;
            }
            a(true, key, value, null);
        }
    }

    private int c(K k, V v) {
        int iB = b(k, v);
        if (iB >= 0) {
            return iB;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    public final void a() {
        a(-1);
    }

    public final synchronized String toString() {
        int i;
        i = this.f721g + this.f722h;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f717c), Integer.valueOf(this.f721g), Integer.valueOf(this.f722h), Integer.valueOf(i != 0 ? (this.f721g * 100) / i : 0));
    }
}