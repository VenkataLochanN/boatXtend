package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.mapcore.util.gj;
import com.amap.api.mapcore.util.l;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.ido.life.constants.Constants;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthTask.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    WeakReference<IAMapDelegate> f1495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1496b;

    public k(Context context, IAMapDelegate iAMapDelegate) {
        this.f1495a = null;
        this.f1496b = context;
        this.f1495a = new WeakReference<>(iAMapDelegate);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        JSONObject jSONObjectOptJSONObject3;
        gs gsVarE;
        JSONObject jSONObjectOptJSONObject4;
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                go.a().a(this.f1496b);
                StringBuilder sb = new StringBuilder();
                sb.append("14S");
                sb.append(";");
                sb.append("11K");
                sb.append(";");
                sb.append(Constants.USA_CODE);
                sb.append(";");
                sb.append("14M");
                sb.append(";");
                sb.append("14L");
                sb.append(";");
                sb.append("16V");
                sb.append(";");
                sb.append("14Z");
                sb.append(";");
                sb.append("154");
                sb.append(";");
                sb.append("156");
                sb.append(";");
                sb.append("15C");
                sb.append(";");
                sb.append("16G");
                sb.append(";");
                sb.append("17W");
                sb.append(";");
                sb.append("17E");
                try {
                    if (this.f1495a != null && this.f1495a.get() != null) {
                        IAMapDelegate iAMapDelegate = this.f1495a.get();
                        if (iAMapDelegate.getAMapExtraInterfaceManager() != null) {
                            String strB = iAMapDelegate.getAMapExtraInterfaceManager().b();
                            if (!TextUtils.isEmpty(strB)) {
                                if (strB.indexOf(";") == 0) {
                                    sb.append(strB);
                                } else {
                                    sb.append(";");
                                    sb.append(strB);
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
                String string = sb.toString();
                string.replaceAll(";;", ";");
                gj.b bVarA = gj.a(this.f1496b, er.e(), string, null);
                boolean z = true;
                if (gj.f1056a != 1 && bVarA != null && this.f1495a != null && this.f1495a.get() != null) {
                    Message messageObtainMessage = this.f1495a.get().getMainHandler().obtainMessage();
                    messageObtainMessage.what = 2;
                    if (bVarA.f1067c != null) {
                        messageObtainMessage.obj = bVarA.f1067c;
                    }
                    this.f1495a.get().getMainHandler().sendMessage(messageObtainMessage);
                }
                if (bVarA != null && bVarA.f1070f != null && (jSONObjectOptJSONObject4 = bVarA.f1070f.optJSONObject("154")) != null && gj.a(jSONObjectOptJSONObject4.getString("able"), true)) {
                    String strOptString = jSONObjectOptJSONObject4.optString("mc");
                    String strOptString2 = jSONObjectOptJSONObject4.optString("si");
                    if (!TextUtils.isEmpty(strOptString)) {
                        eh.a(this.f1496b, "approval_number", "mc", (Object) strOptString);
                    }
                    if (!TextUtils.isEmpty(strOptString2)) {
                        eh.a(this.f1496b, "approval_number", "si", (Object) strOptString2);
                    }
                }
                if (bVarA != null && bVarA.f1071g != null && (gsVarE = er.e()) != null) {
                    gsVarE.a(bVarA.f1071g.f1073a);
                }
                if (bVarA != null) {
                    a(bVarA);
                }
                if (bVarA != null) {
                    try {
                        if (bVarA.f1070f != null && (jSONObjectOptJSONObject = bVarA.f1070f.optJSONObject("14M")) != null && jSONObjectOptJSONObject.has("able") && gj.a(jSONObjectOptJSONObject.getString("able"), true)) {
                            if (System.currentTimeMillis() - eh.a(this.f1496b, "Map3DCache", "time", (Long) 0L).longValue() > (jSONObjectOptJSONObject.has("time") ? Math.max(60, jSONObjectOptJSONObject.getInt("time")) : 2592000) * 1000 && this.f1495a != null && this.f1495a.get() != null) {
                                this.f1495a.get().clearTileCache();
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (bVarA != null && bVarA.f1070f != null) {
                    try {
                        JSONObject jSONObjectOptJSONObject5 = bVarA.f1070f.optJSONObject("14L");
                        if (jSONObjectOptJSONObject5 != null && jSONObjectOptJSONObject5.has("able")) {
                            boolean zA = gj.a(jSONObjectOptJSONObject5.getString("able"), false);
                            if (this.f1495a != null && this.f1495a.get() != null) {
                                IAMapDelegate iAMapDelegate2 = this.f1495a.get();
                                if (zA) {
                                    z = false;
                                }
                                iAMapDelegate2.setHideLogoEnble(z);
                            }
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (bVarA != null && bVarA.f1070f != null && (jSONObjectOptJSONObject3 = bVarA.f1070f.optJSONObject("156")) != null) {
                    ea.a(gj.a(jSONObjectOptJSONObject3.optString("able"), false));
                }
                if (bVarA != null && bVarA.f1070f != null) {
                    a(this.f1496b, er.e(), bVarA);
                }
                if (bVarA != null && bVarA.f1070f != null && (jSONObjectOptJSONObject2 = bVarA.f1070f.optJSONObject("15C")) != null) {
                    final boolean zA2 = gj.a(jSONObjectOptJSONObject2.optString("able"), false);
                    final String strOptString3 = jSONObjectOptJSONObject2.optString("logo_day_url");
                    final String strOptString4 = jSONObjectOptJSONObject2.optString("logo_day_md5");
                    final String strOptString5 = jSONObjectOptJSONObject2.optString("logo_night_url");
                    final String strOptString6 = jSONObjectOptJSONObject2.optString("logo_night_md5");
                    final String strOptString7 = jSONObjectOptJSONObject2.optString("logo_day_ipv6_url");
                    final String strOptString8 = jSONObjectOptJSONObject2.optString("logo_night_ipv6_url");
                    eq.a().a(new Runnable() { // from class: com.amap.api.mapcore.util.k.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString3)) {
                                boolean z2 = zA2;
                                String str = AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME;
                                String str2 = strOptString3;
                                String str3 = strOptString4;
                                String str4 = strOptString7;
                                if (z2) {
                                    l.d dVar = new l.d(str2, str3, str4, str);
                                    dVar.a("amap_web_logo", "md5_day");
                                    new l(k.this.f1496b, dVar, er.e()).a();
                                }
                                if (k.this.f1495a != null && k.this.f1495a.get() != null) {
                                    k.this.f1495a.get().changeLogoIconStyle(str, z2, 0);
                                }
                            }
                            if (TextUtils.isEmpty(strOptString6) || TextUtils.isEmpty(strOptString5)) {
                                return;
                            }
                            boolean z3 = zA2;
                            String str5 = AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME;
                            String str6 = strOptString5;
                            String str7 = strOptString6;
                            String str8 = strOptString8;
                            if (z3) {
                                l.d dVar2 = new l.d(str6, str7, str8, str5);
                                dVar2.a("amap_web_logo", "md5_night");
                                new l(k.this.f1496b, dVar2, er.e()).a();
                            }
                            if (k.this.f1495a == null || k.this.f1495a.get() == null) {
                                return;
                            }
                            k.this.f1495a.get().changeLogoIconStyle(str5, z3, 1);
                        }
                    });
                }
                if (bVarA != null) {
                    try {
                        if (bVarA.f1070f != null && this.f1495a != null && this.f1495a.get() != null) {
                            IAMapDelegate iAMapDelegate3 = this.f1495a.get();
                            if (iAMapDelegate3.getAMapExtraInterfaceManager() != null) {
                                iAMapDelegate3.getAMapExtraInterfaceManager().a(bVarA.f1070f);
                            }
                        }
                    } catch (Throwable unused2) {
                    }
                }
                if (bVarA != null && bVarA.f1070f != null) {
                    b(bVarA.f1070f);
                }
                if (bVarA != null && bVarA.f1070f != null) {
                    c(bVarA.f1070f);
                }
                if (bVarA != null && bVarA.f1070f != null) {
                    a(bVarA.f1070f);
                }
                hn.a(this.f1496b, er.e());
                interrupt();
                if (this.f1495a == null || this.f1495a.get() == null) {
                    return;
                }
                this.f1495a.get().setRunLowFrame(false);
            }
        } catch (Throwable th3) {
            interrupt();
            hn.c(th3, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th3.printStackTrace();
            ey.b(ex.f798e, "auth exception " + th3.getMessage());
        }
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            ey.a(jSONObject.optJSONObject("17E"));
        } catch (Throwable unused) {
        }
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("16G");
            boolean zA = gj.a(jSONObjectOptJSONObject.optString("able", ""), false);
            boolean zA2 = gj.a(jSONObjectOptJSONObject.optString("removeCache", ""), false);
            boolean zA3 = gj.a(jSONObjectOptJSONObject.optString("uploadInfo", ""), false);
            ei.a(zA);
            ei.b(zA2);
            ei.c(zA3);
        } catch (Throwable unused) {
        }
    }

    private void a(Context context, gs gsVar, gj.b bVar) {
        if (bVar == null || bVar.f1070f == null) {
            return;
        }
        try {
            JSONObject jSONObjectOptJSONObject = bVar.f1070f.optJSONObject("16V");
            boolean zA = gj.a(jSONObjectOptJSONObject.optString("di", ""), false);
            String strOptString = jSONObjectOptJSONObject.optString("dis", "");
            boolean zA2 = gj.a(jSONObjectOptJSONObject.optString("able", ""), false);
            boolean zA3 = gj.a(jSONObjectOptJSONObject.optString("isFilter", ""), true);
            if (!zA || gt.f(strOptString)) {
                hv.a(gsVar).a(context, zA2, zA3);
            }
        } catch (Throwable unused) {
        }
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            eh.a(this.f1496b, "amap_param", "overlay_use_old_type", (Object) Boolean.valueOf(gj.a(jSONObject.optJSONObject("17W").optString("able", ""), false) ? false : true));
        } catch (Throwable unused) {
        }
    }

    private void a(gj.b bVar) {
        try {
            gj.b.a aVar = bVar.f1071g;
            if (aVar != null) {
                eo.a(this.f1496b, "maploc", "ue", Boolean.valueOf(aVar.f1073a));
                JSONObject jSONObject = aVar.f1075c;
                int iOptInt = jSONObject.optInt("fn", 1000);
                int iOptInt2 = jSONObject.optInt("mpn", 0);
                if (iOptInt2 > 500) {
                    iOptInt2 = 500;
                }
                if (iOptInt2 < 30) {
                    iOptInt2 = 30;
                }
                iz.a(iOptInt, gj.a(jSONObject.optString("igu"), false));
                eo.a(this.f1496b, "maploc", "opn", Integer.valueOf(iOptInt2));
            }
        } catch (Throwable th) {
            hn.c(th, "AuthUtil", "loadConfigDataUploadException");
        }
    }

    @Override // java.lang.Thread
    public void interrupt() {
        super.interrupt();
    }
}