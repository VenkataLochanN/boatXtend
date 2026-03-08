package com.loc;

import com.bumptech.glide.load.Key;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/* JADX INFO: compiled from: Table.java */
/* JADX INFO: loaded from: classes3.dex */
public class es {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ThreadLocal<CharsetDecoder> f5194b = new ThreadLocal<CharsetDecoder>() { // from class: com.loc.es.1
        @Override // java.lang.ThreadLocal
        protected final /* synthetic */ CharsetDecoder initialValue() {
            return Charset.forName(Key.STRING_CHARSET_NAME).newDecoder();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<Charset> f5193a = new ThreadLocal<Charset>() { // from class: com.loc.es.2
        @Override // java.lang.ThreadLocal
        protected final /* synthetic */ Charset initialValue() {
            return Charset.forName(Key.STRING_CHARSET_NAME);
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final ThreadLocal<CharBuffer> f5195c = new ThreadLocal<>();
}