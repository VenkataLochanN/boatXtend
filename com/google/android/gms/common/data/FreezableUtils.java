package com.google.android.gms.common.data;

import com.baidu.location.indoor.c;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        c cVar = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            cVar.add(arrayList.get(i).freeze());
        }
        return cVar;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        c cVar = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e2 : eArr) {
            cVar.add(e2.freeze());
        }
        return cVar;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        c cVar = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            cVar.add(it.next().freeze());
        }
        return cVar;
    }
}