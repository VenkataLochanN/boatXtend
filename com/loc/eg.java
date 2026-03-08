package com.loc;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.amap.api.location.AMapLocationClientOption;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.module.user.country.CountryChooseActivity;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: Parser.java */
/* JADX INFO: loaded from: classes3.dex */
public final class eg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StringBuilder f5140a = new StringBuilder();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AMapLocationClientOption f5141b = new AMapLocationClientOption();

    private void a(ds dsVar, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb2.append(str);
            sb2.append(" ");
        }
        if (!TextUtils.isEmpty(str2) && (this.f5141b.getGeoLanguage() != AMapLocationClientOption.GeoLanguage.EN ? !str.contains("市") || !str.equals(str2) : !str2.equals(str))) {
            sb2.append(str2);
            sb2.append(" ");
        }
        if (!TextUtils.isEmpty(str3)) {
            sb2.append(str3);
            sb2.append(" ");
        }
        if (!TextUtils.isEmpty(str4)) {
            sb2.append(str4);
            sb2.append(" ");
        }
        if (!TextUtils.isEmpty(str5)) {
            sb2.append(str5);
            sb2.append(" ");
        }
        if (!TextUtils.isEmpty(str6)) {
            if (TextUtils.isEmpty(str7) || this.f5141b.getGeoLanguage() == AMapLocationClientOption.GeoLanguage.EN) {
                sb2.append("Near " + str6);
                sb = new StringBuilder("Near ");
                sb.append(str6);
            } else {
                sb2.append("靠近");
                sb2.append(str6);
                sb2.append(" ");
                sb = new StringBuilder("在");
                sb.append(str6);
                sb.append("附近");
            }
            dsVar.setDescription(sb.toString());
        }
        Bundle bundle = new Bundle();
        bundle.putString("citycode", dsVar.getCityCode());
        bundle.putString("desc", sb2.toString());
        bundle.putString("adcode", dsVar.getAdCode());
        dsVar.setExtras(bundle);
        dsVar.g(sb2.toString());
        String adCode = dsVar.getAdCode();
        dsVar.setAddress((adCode == null || adCode.trim().length() <= 0 || this.f5141b.getGeoLanguage() == AMapLocationClientOption.GeoLanguage.EN) ? sb2.toString() : sb2.toString().replace(" ", ""));
    }

    private static String b(String str) {
        return "[]".equals(str) ? "" : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.ds a(com.loc.ds r22, byte[] r23, com.loc.dm r24) {
        /*
            Method dump skipped, instruction units count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eg.a(com.loc.ds, byte[], com.loc.dm):com.loc.ds");
    }

    public final ds a(String str) {
        String strB;
        String str2;
        String str3;
        try {
            ds dsVar = new ds("");
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("regeocode");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("addressComponent");
            dsVar.setCountry(b(jSONObjectOptJSONObject2.optString(CountryChooseActivity.COUNTRY)));
            String strB2 = b(jSONObjectOptJSONObject2.optString("province"));
            dsVar.setProvince(strB2);
            String strB3 = b(jSONObjectOptJSONObject2.optString("citycode"));
            dsVar.setCityCode(strB3);
            String strOptString = jSONObjectOptJSONObject2.optString("city");
            if (!strB3.endsWith("010") && !strB3.endsWith("021") && !strB3.endsWith("022") && !strB3.endsWith("023")) {
                strB = b(strOptString);
                dsVar.setCity(strB);
            } else if (strB2 == null || strB2.length() <= 0) {
                strB = strOptString;
            } else {
                dsVar.setCity(strB2);
                strB = strB2;
            }
            if (TextUtils.isEmpty(strB)) {
                dsVar.setCity(strB2);
                str2 = strB2;
            } else {
                str2 = strB;
            }
            String strB4 = b(jSONObjectOptJSONObject2.optString("district"));
            dsVar.setDistrict(strB4);
            String strB5 = b(jSONObjectOptJSONObject2.optString("adcode"));
            dsVar.setAdCode(strB5);
            JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject("streetNumber");
            String strB6 = b(jSONObjectOptJSONObject3.optString("street"));
            dsVar.setStreet(strB6);
            dsVar.setRoad(strB6);
            String strB7 = b(jSONObjectOptJSONObject3.optString("number"));
            dsVar.setNumber(strB7);
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("pois");
            if (jSONArrayOptJSONArray.length() > 0) {
                String strB8 = b(jSONArrayOptJSONArray.getJSONObject(0).optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                dsVar.setPoiName(strB8);
                str3 = strB8;
            } else {
                str3 = null;
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("aois");
            if (jSONArrayOptJSONArray2.length() > 0) {
                dsVar.setAoiName(b(jSONArrayOptJSONArray2.getJSONObject(0).optString(AppMeasurementSdk.ConditionalUserProperty.NAME)));
            }
            a(dsVar, strB2, str2, strB4, strB6, strB7, str3, strB5);
            return dsVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final ds a(String str, Context context, aw awVar, dm dmVar) {
        ds dsVar = new ds("");
        dsVar.setErrorCode(7);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("#SHA1AndPackage#");
            stringBuffer.append(k.e(context));
            String str2 = awVar.f4810b.get("gsid").get(0);
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append("#gsid#");
                stringBuffer.append(str2);
            }
            String str3 = awVar.f4811c;
            if (!TextUtils.isEmpty(str3)) {
                stringBuffer.append("#csid#" + str3);
            }
        } catch (Throwable unused) {
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS) || !jSONObject.has("info")) {
                dmVar.f("#0702");
                StringBuilder sb = this.f5140a;
                sb.append("json is error:");
                sb.append(str);
                sb.append(stringBuffer);
                sb.append("#0702");
            }
            String string = jSONObject.getString(NotificationCompat.CATEGORY_STATUS);
            String string2 = jSONObject.getString("info");
            String string3 = jSONObject.getString("infocode");
            if (AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(string)) {
                dmVar.f("#0701");
                StringBuilder sb2 = this.f5140a;
                sb2.append("auth fail:");
                sb2.append(string2);
                sb2.append(stringBuffer);
                sb2.append("#0701");
                en.a(awVar.f4812d, string3, string2);
            }
        } catch (Throwable th) {
            dmVar.f("#0703");
            StringBuilder sb3 = this.f5140a;
            sb3.append("json exception error:");
            sb3.append(th.getMessage());
            sb3.append(stringBuffer);
            sb3.append("#0703");
            ej.a(th, "parser", "paseAuthFailurJson");
        }
        dsVar.setLocationDetail(this.f5140a.toString());
        if (this.f5140a.length() > 0) {
            StringBuilder sb4 = this.f5140a;
            sb4.delete(0, sb4.length());
        }
        return dsVar;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption == null) {
            this.f5141b = new AMapLocationClientOption();
        } else {
            this.f5141b = aMapLocationClientOption;
        }
    }
}