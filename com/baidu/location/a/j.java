package com.baidu.location.a;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.map.device.token.Token;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class j implements LBSAuthManagerListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Object f2126a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static j f2127b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2128c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2129d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f2130e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2131f = null;

    public static j a() {
        j jVar;
        synchronized (f2126a) {
            if (f2127b == null) {
                f2127b = new j();
            }
            jVar = f2127b;
        }
        return jVar;
    }

    public static String b(Context context) {
        try {
            return LBSAuthManager.getInstance(context).getPublicKey(context);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c(Context context) {
        try {
            return LBSAuthManager.getInstance(context).getMCode();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(Context context) {
        this.f2129d = context;
        LBSAuthManager.getInstance(this.f2129d).authenticate(false, "lbs_locsdk", null, this);
        this.f2130e = System.currentTimeMillis();
    }

    public boolean b() {
        int i = this.f2128c;
        boolean z = i == 0 || i == 602 || i == 601 || i == -10 || i == -11;
        if (this.f2129d != null) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.f2130e;
            if (!z ? jCurrentTimeMillis < 0 || jCurrentTimeMillis > DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT : jCurrentTimeMillis > DateUtil.DAY) {
                LBSAuthManager.getInstance(this.f2129d).authenticate(false, "lbs_locsdk", null, this);
                this.f2130e = System.currentTimeMillis();
            }
        }
        return z;
    }

    @Override // com.baidu.lbsapi.auth.LBSAuthManagerListener
    public void onAuthResult(int i, String str) {
        this.f2128c = i;
        if (this.f2128c == 0) {
            Log.i(com.baidu.location.g.a.f2452a, "LocationAuthManager Authentication AUTHENTICATE_SUCC");
        } else {
            Log.i(com.baidu.location.g.a.f2452a, "LocationAuthManager Authentication Error errorcode = " + i + " , msg = " + str);
        }
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getString(Token.KEY_TOKEN) != null) {
                    this.f2131f = jSONObject.getString(Token.KEY_TOKEN);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}