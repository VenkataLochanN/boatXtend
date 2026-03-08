package com.ido.life.util;

import android.text.TextUtils;
import com.ido.common.IdoApp;
import com.ido.common.env.LanguagePreference;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MultiLangUtil {
    private static final String TAG = "MultiLangUtil";

    public static String getCurrentSystemLang() {
        return IdoApp.getAppContext().getResources().getConfiguration().locale.getLanguage();
    }

    public static boolean getLangJavaBean(String str, String str2, String str3) {
        Map<String, String> mapFromJsonToMap;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            CommonLogUtil.d(TAG, "文件路径 --- " + str);
            String stringFromFile = FileUtil.readStringFromFile(str);
            if (!TextUtils.isEmpty(stringFromFile) && (mapFromJsonToMap = GsonUtil.fromJsonToMap(stringFromFile)) != null && mapFromJsonToMap.size() > 0) {
                LanguagePreference.getInstance(str3).saveLanguage(IdoApp.getAppContext(), str2, mapFromJsonToMap);
                return true;
            }
        }
        return false;
    }
}