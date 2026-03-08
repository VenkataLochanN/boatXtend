package com.ido.life.data.me.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.IdoApp;
import com.ido.common.env.LanguagePreference;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.api.AccountApi;
import com.ido.life.data.api.entity.MultilLanguageEntity;
import com.ido.life.data.listener.ApiCallback;
import com.ido.life.module.user.lang.LanguageActivity;
import com.ido.life.util.MultiLangUtil;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Callback;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageManager {
    private static final String KEY_APP_LANGUAGE = "app_language";
    private static final String TAG = "LanguageManager";

    private static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static void persistenceLanguage(Context context, String str) {
        getPref(context).edit().putString("app_language", str).apply();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String toLanguage(java.util.Locale r11) {
        /*
            java.lang.String r0 = r11.getLanguage()
            java.lang.String r11 = r11.getCountry()
            int r1 = r0.hashCode()
            r2 = 3201(0xc81, float:4.486E-42)
            r3 = 0
            java.lang.String r4 = "en"
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = -1
            r9 = 2
            r10 = 1
            if (r1 == r2) goto L5f
            r2 = 3241(0xca9, float:4.542E-42)
            if (r1 == r2) goto L57
            r2 = 3246(0xcae, float:4.549E-42)
            if (r1 == r2) goto L4d
            r2 = 3276(0xccc, float:4.59E-42)
            if (r1 == r2) goto L43
            r2 = 3383(0xd37, float:4.74E-42)
            if (r1 == r2) goto L39
            r2 = 3886(0xf2e, float:5.445E-42)
            if (r1 == r2) goto L2e
            goto L69
        L2e:
            java.lang.String r1 = "zh"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L69
            r1 = r5
            goto L6a
        L39:
            java.lang.String r1 = "ja"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L69
            r1 = r6
            goto L6a
        L43:
            java.lang.String r1 = "fr"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L69
            r1 = r7
            goto L6a
        L4d:
            java.lang.String r1 = "es"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L69
            r1 = r9
            goto L6a
        L57:
            boolean r1 = r0.equals(r4)
            if (r1 == 0) goto L69
            r1 = r3
            goto L6a
        L5f:
            java.lang.String r1 = "de"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L69
            r1 = r10
            goto L6a
        L69:
            r1 = r8
        L6a:
            if (r1 == 0) goto Lb9
            if (r1 == r10) goto Lb9
            if (r1 == r9) goto Lb9
            if (r1 == r7) goto Lb9
            if (r1 == r6) goto Lb9
            if (r1 == r5) goto L7b
            r11.hashCode()
            r0 = r4
            goto Lb9
        L7b:
            int r0 = r11.hashCode()
            r1 = 2307(0x903, float:3.233E-42)
            if (r0 == r1) goto L9f
            r1 = 2466(0x9a2, float:3.456E-42)
            if (r0 == r1) goto L95
            r1 = 2691(0xa83, float:3.771E-42)
            if (r0 == r1) goto L8c
            goto La9
        L8c:
            java.lang.String r0 = "TW"
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto La9
            goto Laa
        L95:
            java.lang.String r0 = "MO"
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto La9
            r3 = r9
            goto Laa
        L9f:
            java.lang.String r0 = "HK"
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto La9
            r3 = r10
            goto Laa
        La9:
            r3 = r8
        Laa:
            if (r3 == 0) goto Lb5
            if (r3 == r10) goto Lb5
            if (r3 == r9) goto Lb5
            java.lang.String r11 = "zh_CN"
        Lb3:
            r0 = r11
            goto Lb9
        Lb5:
            java.lang.String r11 = "zh_TW"
            goto Lb3
        Lb9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.data.me.remote.LanguageManager.toLanguage(java.util.Locale):java.lang.String");
    }

    public static Context setLanguage(Context context) {
        return setLanguage(context, getLanguage(context));
    }

    public static Context setLanguage(Context context, String str) {
        persistenceLanguage(context, str);
        return LanguageUtil.updateResources(context, str);
    }

    public static String getLanguage(Context context) {
        return getPref(context).getString("app_language", "auto");
    }

    public static String getLang() {
        return toLanguage(LanguageUtil.getLocale(IdoApp.getAppContext().getResources())).toLowerCase();
    }

    public static void getAppMultiLang(String str, LanguageActivity.FinishCallBack finishCallBack) {
        if (TextUtils.isEmpty(str)) {
            if (finishCallBack != null) {
                finishCallBack.onFailed();
                return;
            }
            return;
        }
        if ("auto".contentEquals(str)) {
            str = toLanguage(Locale.getDefault());
        }
        Log.d(TAG, "getAppMultiLang() --- 当前去下载解析的语言code： " + str);
        String languageVersion = LanguagePreference.getInstance(str).getLanguageVersion(IdoApp.getAppContext(), AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        AccountApi.api.getMultiLanguage(str, languageVersion).enqueue(getLanguageCallBack(finishCallBack, languageVersion, str));
    }

    private static Callback<MultilLanguageEntity> getLanguageCallBack(final LanguageActivity.FinishCallBack finishCallBack, final String str, final String str2) {
        return new ApiCallback<MultilLanguageEntity>() { // from class: com.ido.life.data.me.remote.LanguageManager.1
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(MultilLanguageEntity multilLanguageEntity) {
                LanguageActivity.FinishCallBack finishCallBack2;
                LanguageActivity.FinishCallBack finishCallBack3;
                String json = GsonUtil.toJson(multilLanguageEntity.getResult());
                Log.d(LanguageManager.TAG, "getLanguageCallBack() 请求下载成功后返回的result： " + json);
                if (!json.equals("null")) {
                    LanguageManager.stringToJson(str, GsonUtil.toJson(multilLanguageEntity.getResult()), finishCallBack);
                    return;
                }
                boolean langJavaBean = TextUtils.equals(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, str) ? MultiLangUtil.getLangJavaBean(com.ido.life.util.LanguageManager.getLanguagePath(str2), str, str2) : true;
                if (langJavaBean && (finishCallBack3 = finishCallBack) != null) {
                    finishCallBack3.onSuccess();
                } else {
                    if (langJavaBean || (finishCallBack2 = finishCallBack) == null) {
                        return;
                    }
                    finishCallBack2.onFailed();
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                Log.d(LanguageManager.TAG, "onError: " + str3);
            }
        };
    }

    public static void stringToJson(String str, String str2, final LanguageActivity.FinishCallBack finishCallBack) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject(str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        String strOptString = jSONObject.optString("langFileUrl", null);
        String strOptString2 = jSONObject.optString("langCode", null);
        final String strOptString3 = jSONObject.optString("langFileVersion", null);
        if (!AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.contentEquals(str) && TextUtils.equals(str, strOptString3)) {
            if (finishCallBack != null) {
                finishCallBack.onSuccess();
                return;
            }
            return;
        }
        CommonLogUtil.d(TAG, "stringToJson() 解析result的结果：langFileUrl:" + strOptString + " langCode:" + strOptString2 + "langFileVersion:" + strOptString3);
        try {
            jSONObject2 = new JSONObject(strOptString2);
        } catch (JSONException e3) {
            e3.printStackTrace();
            jSONObject2 = null;
        }
        String strOptString4 = jSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME, null);
        final String strOptString5 = jSONObject2.optString(AuthorizationResponseParser.CODE, null);
        CommonLogUtil.d(TAG, "stringToJson() 解析langCode的结果：name:" + strOptString4 + " code:" + strOptString5);
        DownloadManager.download(strOptString, com.ido.life.util.LanguageManager.getLanguagePath(strOptString5), new DownloadManager.DownloadListener() { // from class: com.ido.life.data.me.remote.LanguageManager.2
            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadProgress(int i) {
            }

            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadStart() {
            }

            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadFinish(String str3) {
                LanguageActivity.FinishCallBack finishCallBack2;
                LanguageActivity.FinishCallBack finishCallBack3;
                Log.d(LanguageManager.TAG, "DownloadManager.DownloadListener() onDownloadFinish: 保存的路径 " + str3);
                boolean langJavaBean = MultiLangUtil.getLangJavaBean(str3, strOptString3, strOptString5);
                if (langJavaBean && (finishCallBack3 = finishCallBack) != null) {
                    finishCallBack3.onSuccess();
                } else {
                    if (langJavaBean || (finishCallBack2 = finishCallBack) == null) {
                        return;
                    }
                    finishCallBack2.onFailed();
                }
            }

            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadFailed(int i, String str3) {
                Log.d(LanguageManager.TAG, "onDownloadFailed: " + str3);
            }
        });
    }
}