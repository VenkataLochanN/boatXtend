package com.ido.life.module.user.country;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.data.me.remote.LanguageManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CountryManager {
    public static final String DEFAULT_COUNTRY_CODE = "86";
    private static final String TAG = CountryManager.class.getSimpleName();

    public static List<CountryInfo> makeResultCountry() {
        return getAllCountry();
    }

    private static List<CountryInfo> getAllCountry() {
        String[] stringArray = ResourceUtil.getResources().getStringArray(R.array.country_codes_all);
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            CountryInfo countryInfo = new CountryInfo();
            countryInfo.countryCode = str.replace("country_", "");
            countryInfo.countryName = ResourceUtil.getResources().getString(ResourceUtil.getResources().getIdentifier(str, "string", IdoApp.getAppContext().getPackageName()));
            arrayList.add(countryInfo);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, countryInfo.toString());
        }
        return arrayList;
    }

    private static List<CountryInfo> fromArrayToCountryList(int i) {
        String[] stringArray = ResourceUtil.getStringArray(i);
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            String[] strArrSplit = TextUtils.split(str, "_");
            CountryInfo countryInfo = new CountryInfo();
            LanguageManager.getLang();
            countryInfo.type = 32;
            countryInfo.countryName = strArrSplit[0];
            arrayList.add(countryInfo);
        }
        return arrayList;
    }

    public static List<CountryInfo> getStringToCN() {
        String[] stringArray = ResourceUtil.getResources().getStringArray(R.array.country_codes_cn);
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : stringArray) {
                CountryInfo countryInfo = new CountryInfo();
                String[] strArrSplit = str.split("_");
                if (strArrSplit.length > 1) {
                    countryInfo.countryName = strArrSplit[0];
                    countryInfo.countryCode = strArrSplit[1];
                }
                arrayList.add(countryInfo);
            }
            return arrayList;
        } catch (Exception e2) {
            e2.printStackTrace();
            return arrayList;
        }
    }
}