package com.loc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: AbstractBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class bt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    bv f4883a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ByteBuffer f4884b;

    bt(int i) {
        this.f4884b = ByteBuffer.allocate(i);
        this.f4884b.order(ByteOrder.LITTLE_ENDIAN);
        this.f4883a = new bv(this.f4884b);
    }

    public final bt a() {
        this.f4883a.a(this.f4884b);
        return this;
    }
}