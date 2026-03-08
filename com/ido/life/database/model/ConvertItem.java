package com.ido.life.database.model;

import com.ido.common.utils.GsonUtil;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ConvertItem implements PropertyConverter<SportItem, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public SportItem convertToEntityProperty(String str) {
        try {
            return (SportItem) GsonUtil.fromJson(str, SportItem.class);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(SportItem sportItem) {
        try {
            return GsonUtil.toJson(sportItem);
        } catch (Exception unused) {
            return "";
        }
    }
}