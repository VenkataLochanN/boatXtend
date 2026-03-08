package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: HeaderAddStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bg extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f4854a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4855b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ak f4856e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Object[] f4857f;

    public bg(Context context, bk bkVar, ak akVar, String str, Object... objArr) {
        super(bkVar);
        this.f4854a = context;
        this.f4855b = str;
        this.f4856e = akVar;
        this.f4857f = objArr;
    }

    private String b() {
        try {
            return String.format(u.c(this.f4855b), this.f4857f);
        } catch (Throwable th) {
            th.printStackTrace();
            ab.b(th, "ofm", "gpj");
            return "";
        }
    }

    @Override // com.loc.bk
    protected final byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        String strA = u.a(bArr);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return u.a("{\"pinfo\":\"" + u.a(this.f4856e.b(u.a(b()))) + "\",\"els\":[" + strA + "]}");
    }
}