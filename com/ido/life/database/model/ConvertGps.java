package com.ido.life.database.model;

import com.ido.common.utils.GsonUtil;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ConvertGps implements PropertyConverter<SportGps, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public SportGps convertToEntityProperty(String str) {
        try {
            return (SportGps) GsonUtil.fromJson(str, SportGps.class);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(SportGps sportGps) {
        try {
            return GsonUtil.toJson(sportGps);
        } catch (Exception unused) {
            return "";
        }
    }
}