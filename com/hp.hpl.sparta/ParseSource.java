package com.hp.hpl.sparta;

/* JADX INFO: loaded from: classes2.dex */
public interface ParseSource {
    public static final ParseLog DEFAULT_LOG = new DefaultLog();
    public static final int MAXLOOKAHEAD = 71;

    int getLineNumber();

    String getSystemId();

    String toString();
}