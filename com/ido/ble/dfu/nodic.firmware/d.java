package com.ido.ble.dfu.nodic.firmware;

import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
class d {
    d() {
    }

    static CheckNewVersionResponse a(String str) {
        LogTool.d(b.f4361a, str);
        try {
            return (CheckNewVersionResponse) k.c(str, CheckNewVersionResponse.class);
        } catch (Exception e2) {
            LogTool.b(b.f4361a, e2.toString());
            return null;
        }
    }
}