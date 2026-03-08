package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.BindAuth;
import com.ido.ble.protocol.model.BindEncrypted;
import com.ido.ble.protocol.model.BindPara;

/* JADX INFO: loaded from: classes2.dex */
class d {
    d() {
    }

    public static int a(BindAuth bindAuth) {
        return u.b(com.ido.ble.common.c.b(new Gson().toJson(bindAuth)), com.veryfit.multi.nativeprotocol.b.G0);
    }

    public static int a(BindEncrypted bindEncrypted) {
        return u.b(com.ido.ble.common.c.b(new Gson().toJson(bindEncrypted)), 204);
    }

    public static int a(BindPara bindPara) {
        return u.b(com.ido.ble.common.c.b(new Gson().toJson(bindPara)), 200);
    }

    public static void a() {
    }

    public static int b() {
        return u.a(com.veryfit.multi.nativeprotocol.b.j5, com.veryfit.multi.nativeprotocol.b.F0, 0, 0);
    }
}