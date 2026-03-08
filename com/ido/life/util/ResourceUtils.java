package com.ido.life.util;

import android.content.Context;
import android.content.res.Resources;
import com.boat.Xtend.two.R;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.constants.LanguageCodeHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResourceUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004J\u0019\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004J\u0016\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004¨\u0006\u0016"}, d2 = {"Lcom/ido/life/util/ResourceUtils;", "", "()V", "getLanguageAbb", "", "languageType", "", "getMipmapResId", AppMeasurementSdk.ConditionalUserProperty.NAME, "getResIdByName", "type", "getResourceByLanguage", "Landroid/content/res/Resources;", "context", "Landroid/content/Context;", "language", "getStringArray", "", "(Ljava/lang/String;)[Ljava/lang/String;", "restoreSystemResource", "", "setResourceByLanguage", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ResourceUtils {
    public static final ResourceUtils INSTANCE = new ResourceUtils();

    private ResourceUtils() {
    }

    public final int getResIdByName(String name, String type) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Context context = IdoApp.getAppContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    public final int getMipmapResId(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return getResIdByName(name, "mipmap");
    }

    public final String[] getStringArray(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        int resIdByName = getResIdByName(name, "array");
        if (resIdByName <= 0) {
            return new String[0];
        }
        String[] stringArray = IdoApp.getStringArray(resIdByName);
        return stringArray != null ? stringArray : new String[0];
    }

    public final synchronized Resources getResourceByLanguage(Context context, String language) {
        Resources resources;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        CommonLogUtil.printAndSave("getResourceByLanguage language = " + language);
        Context language2 = LanguageManager.setLanguage(context, language);
        Intrinsics.checkExpressionValueIsNotNull(language2, "LanguageManager.setLanguage(context, language)");
        resources = language2.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "LanguageManager.setLangu…text, language).resources");
        return resources;
    }

    public final synchronized void setResourceByLanguage(Context context, String language) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        CommonLogUtil.printAndSave("setResourceByLanguage language = " + language);
        Context language2 = LanguageManager.setLanguage(context, language);
        Intrinsics.checkExpressionValueIsNotNull(language2, "LanguageManager.setLanguage(context, language)");
        language2.getResources();
    }

    public final synchronized void restoreSystemResource(Context context, String language) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(language, "language");
        String string = getResourceByLanguage(context, language).getString(R.string.language_cn);
        Intrinsics.checkExpressionValueIsNotNull(string, "getResourceByLanguage(co…ing(R.string.language_cn)");
        CommonLogUtil.printAndSave("恢复系统默认语言：" + language + ", language_cn = " + string);
    }

    public final String getLanguageAbb(int languageType) {
        String languageCodeByDeviceCode = LanguageCodeHelper.getLanguageCodeByDeviceCode(languageType);
        Intrinsics.checkExpressionValueIsNotNull(languageCodeByDeviceCode, "LanguageCodeHelper.getLa…yDeviceCode(languageType)");
        return languageCodeByDeviceCode;
    }
}