package com.baidu.mapsdkplatform.comapi.map;

import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3627a = w.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private v f3628b;

    w() {
    }

    void a(Message message) {
        if (message.what != 65289) {
            return;
        }
        int i = message.arg1;
        if (i != 12 && i != 101 && i != 102) {
            switch (i) {
            }
            return;
        }
        v vVar = this.f3628b;
        if (vVar != null) {
            vVar.a(message.arg1, message.arg2);
        }
    }

    void a(v vVar) {
        this.f3628b = vVar;
    }

    void b(v vVar) {
        this.f3628b = null;
    }
}