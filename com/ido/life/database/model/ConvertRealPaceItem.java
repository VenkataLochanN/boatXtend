package com.ido.life.database.model;

import com.ido.common.utils.GsonUtil;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ConvertRealPaceItem implements PropertyConverter<SportRealTimePace, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public SportRealTimePace convertToEntityProperty(String str) {
        try {
            return (SportRealTimePace) GsonUtil.fromJson(str, SportRealTimePace.class);
        } catch (Exception unused) {
            return (SportRealTimePace) GsonUtil.fromJson(str, SportRealTimePace.class);
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(SportRealTimePace sportRealTimePace) {
        try {
            return GsonUtil.toJson(sportRealTimePace);
        } catch (Exception unused) {
            return "";
        }
    }
}