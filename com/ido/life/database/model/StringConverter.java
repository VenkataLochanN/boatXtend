package com.ido.life.database.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.utils.GsonUtil;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class StringConverter implements PropertyConverter<List<String>, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public List<String> convertToEntityProperty(String str) {
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<String>>() { // from class: com.ido.life.database.model.StringConverter.1
            }.getType());
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(List<String> list) {
        try {
            return GsonUtil.toJson(list);
        } catch (Exception unused) {
            return "";
        }
    }
}