package com.ido.life.database.model;

import com.ido.common.utils.GsonUtil;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ConvertSwimSwolf implements PropertyConverter<SportSwimSwolf, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public SportSwimSwolf convertToEntityProperty(String str) {
        try {
            return (SportSwimSwolf) GsonUtil.fromJson(str, SportSwimSwolf.class);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(SportSwimSwolf sportSwimSwolf) {
        try {
            return GsonUtil.toJson(sportSwimSwolf);
        } catch (Exception unused) {
            return "";
        }
    }
}