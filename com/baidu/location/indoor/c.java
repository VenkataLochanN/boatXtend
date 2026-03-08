package com.baidu.location.indoor;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class c<T> extends ArrayList<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2515a;

    public c(int i) {
        this.f2515a = 0;
        this.f2515a = i;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(T t) {
        synchronized (this) {
            if (size() == this.f2515a) {
                remove(0);
            }
            add(size(), t);
        }
        return true;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        synchronized (this) {
            if (size() <= 3) {
                return;
            }
            int size = size() / 2;
            while (true) {
                int i = size - 1;
                if (size <= 0) {
                    return;
                }
                remove(0);
                size = i;
            }
        }
    }
}