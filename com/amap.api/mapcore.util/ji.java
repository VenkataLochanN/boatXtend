package com.amap.api.mapcore.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* JADX INFO: compiled from: StatisticsPubDataStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class ji extends jj {
    public ji() {
    }

    public ji(jj jjVar) {
        super(jjVar);
    }

    @Override // com.amap.api.mapcore.util.jj
    protected byte[] a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
        stringBuffer.append(" ");
        stringBuffer.append(UUID.randomUUID().toString());
        stringBuffer.append(" ");
        if (stringBuffer.length() != 53) {
            return new byte[0];
        }
        byte[] bArrA = gt.a(stringBuffer.toString());
        byte[] bArr2 = new byte[bArrA.length + bArr.length];
        System.arraycopy(bArrA, 0, bArr2, 0, bArrA.length);
        System.arraycopy(bArr, 0, bArr2, bArrA.length, bArr.length);
        return bArr2;
    }
}