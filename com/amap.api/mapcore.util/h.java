package com.amap.api.mapcore.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: AmapDelegateListenerManager.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Integer, a> f1184a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: AmapDelegateListenerManager.java */
    private class a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List<T> f1185a = Collections.synchronizedList(new ArrayList());

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public T f1186b = null;

        public a() {
        }
    }

    public <T> void a(Integer num, T t) {
        ConcurrentHashMap<Integer, a> concurrentHashMap;
        if (t == null || (concurrentHashMap = this.f1184a) == null) {
            return;
        }
        try {
            a aVar = concurrentHashMap.get(num);
            if (aVar == null) {
                aVar = new a();
                this.f1184a.putIfAbsent(num, aVar);
            }
            if (aVar.f1185a == null || aVar.f1185a.contains(t)) {
                return;
            }
            aVar.f1185a.add(t);
        } catch (Throwable unused) {
        }
    }

    public <T> void b(Integer num, T t) {
        ConcurrentHashMap<Integer, a> concurrentHashMap;
        a aVar;
        if (t == null || (concurrentHashMap = this.f1184a) == null) {
            return;
        }
        try {
            if (!concurrentHashMap.containsKey(num) || (aVar = this.f1184a.get(num)) == null || aVar.f1185a == null || !aVar.f1185a.contains(t)) {
                return;
            }
            aVar.f1185a.remove(t);
        } catch (Throwable unused) {
        }
    }

    public <T> void a(Integer num) {
        a aVar;
        try {
            if (!this.f1184a.containsKey(num) || (aVar = this.f1184a.get(num)) == null || aVar.f1185a == null) {
                return;
            }
            aVar.f1185a.clear();
        } catch (Throwable unused) {
        }
    }

    public <T> List<T> a(int i) {
        try {
            a aVar = this.f1184a.get(Integer.valueOf(i));
            if (aVar != null) {
                return aVar.f1185a;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public <T> void a(int i, T t) {
        ConcurrentHashMap<Integer, a> concurrentHashMap = this.f1184a;
        if (concurrentHashMap == null) {
            return;
        }
        try {
            a aVar = concurrentHashMap.get(Integer.valueOf(i));
            if (aVar == null) {
                aVar = new a();
                this.f1184a.putIfAbsent(Integer.valueOf(i), aVar);
            }
            if (aVar.f1186b == t) {
                return;
            }
            b(Integer.valueOf(i), aVar.f1186b);
            aVar.f1186b = t;
            a(Integer.valueOf(i), t);
        } catch (Throwable unused) {
        }
    }

    public <T> void a() {
        ConcurrentHashMap<Integer, a> concurrentHashMap = this.f1184a;
        if (concurrentHashMap == null) {
            return;
        }
        try {
            Iterator<Map.Entry<Integer, a>> it = concurrentHashMap.entrySet().iterator();
            while (it.hasNext()) {
                a value = it.next().getValue();
                value.f1185a.clear();
                value.f1186b = null;
            }
            this.f1184a.clear();
        } catch (Throwable unused) {
        }
    }
}