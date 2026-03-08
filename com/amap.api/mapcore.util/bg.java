package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: OfflineMapRemoveTask.java */
/* JADX INFO: loaded from: classes.dex */
public class bg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f266a;

    public bg(Context context) {
        this.f266a = context;
    }

    public void a(az azVar) {
        b(azVar);
    }

    private boolean b(az azVar) {
        if (azVar != null) {
            String pinyin = azVar.getPinyin();
            boolean zA = a(pinyin, this.f266a, "vmap/");
            if (pinyin.equals("quanguogaiyaotu")) {
                pinyin = "quanguo";
            }
            boolean z = true;
            boolean z2 = a(pinyin, this.f266a, "map/") || zA;
            if (!b(bx.c(azVar.getUrl()), this.f266a, "map/") && !z2) {
                z = false;
            }
            if (z) {
                azVar.i();
                return z;
            }
            azVar.h();
        }
        return false;
    }

    private boolean a(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strB = er.b(context);
        try {
            File file = new File(strB + str2 + str + ".dat");
            if (file.exists() && !bx.b(file)) {
                bx.a("deleteDownload delete some thing wrong!");
                return false;
            }
            try {
                bx.b(strB + str2);
                bx.b(str, context);
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            } catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            return false;
        } catch (Exception e5) {
            e5.printStackTrace();
            return false;
        }
    }

    private boolean b(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strA = er.a(context);
        try {
            File file = new File(strA + str2 + str);
            if (file.exists() && !bx.b(file)) {
                bx.a("deleteDownload delete some thing wrong!");
                return false;
            }
            try {
                bx.b(strA + str2);
                bx.b(str, context);
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            } catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            return false;
        } catch (Exception e5) {
            e5.printStackTrace();
            return false;
        }
    }
}