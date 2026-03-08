package com.ido.life.database.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.utils.GsonUtil;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ConvertListColorItem implements PropertyConverter<List<Integer>, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public List<Integer> convertToEntityProperty(String str) {
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<Integer>>() { // from class: com.ido.life.database.model.ConvertListColorItem.1
            }.getType());
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(List<Integer> list) {
        try {
            return GsonUtil.toJson(list);
        } catch (Exception unused) {
            return "";
        }
    }
}