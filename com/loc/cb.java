package com.loc;

import android.util.Base64;
import java.nio.charset.StandardCharsets;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: compiled from: CollectionUploader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cb {
    public static boolean a(byte[] bArr) {
        String str;
        if (bArr == null) {
            return false;
        }
        byte[] bArr2 = null;
        try {
            di diVar = new di();
            diVar.f4986b.put("Content-Type", DfuBaseService.MIME_TYPE_OCTET_STREAM);
            diVar.f4986b.put("aps_c_src", Base64.encodeToString(("lc_" + ((int) de.a())).getBytes(), 2));
            diVar.f4986b.put("aps_c_key", Base64.encodeToString((de.c() + "*" + de.f()).getBytes(), 2));
            diVar.f4988d = bArr;
            if (bs.f4881a) {
                str = "http://cgicol.amap.com/collection/collectData?src=baseCol&ver=v74&";
            } else {
                str = (bs.f4882b ? "https://" : "http://") + "cgicol.amap.com/collection/collectData?src=baseCol&ver=v74&";
            }
            diVar.f4985a = str;
            dj djVarA = cv.a().a(diVar);
            if (djVarA != null && djVarA.f4990a == 200) {
                bArr2 = djVarA.f4992c;
            }
            if (bArr2 != null) {
                return "true".equals(new String(bArr2, StandardCharsets.UTF_8));
            }
            return false;
        } catch (Exception e2) {
            dg.a(e2);
            return false;
        }
    }
}