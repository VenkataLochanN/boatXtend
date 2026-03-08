package com.ido.life.database.model;

import com.ido.common.utils.GsonUtil;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ConvertPaceItem implements PropertyConverter<SportItemPace, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public SportItemPace convertToEntityProperty(String str) {
        try {
            return (SportItemPace) GsonUtil.fromJson(str, SportItemPace.class);
        } catch (Exception unused) {
            return (SportItemPace) GsonUtil.fromJson(str, SportItemPace.class);
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(SportItemPace sportItemPace) {
        try {
            return GsonUtil.toJson(sportItemPace);
        } catch (Exception unused) {
            return "";
        }
    }
}