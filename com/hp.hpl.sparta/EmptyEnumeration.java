package com.hp.hpl.sparta;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: Document.java */
/* JADX INFO: loaded from: classes2.dex */
class EmptyEnumeration implements Enumeration {
    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return false;
    }

    EmptyEnumeration() {
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        throw new NoSuchElementException();
    }
}