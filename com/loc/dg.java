package com.loc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* JADX INFO: compiled from: ALLog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile dh f4984a;

    public static void a(Throwable th) {
        if (cv.f4937a) {
            Throwable cause = th;
            while (true) {
                if (cause == null) {
                    StringWriter stringWriter = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    th.printStackTrace(printWriter);
                    printWriter.flush();
                    stringWriter.toString();
                    break;
                }
                if (cause instanceof UnknownHostException) {
                    break;
                } else {
                    cause = cause.getCause();
                }
            }
            if (f4984a != null) {
                dh dhVar = f4984a;
            }
        }
    }
}