package com.ido.life.database.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.utils.GsonUtil;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class LifeCycleItemConverter implements PropertyConverter<List<List<Integer>>, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public List<List<Integer>> convertToEntityProperty(String str) {
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<List<Integer>>>() { // from class: com.ido.life.database.model.LifeCycleItemConverter.1
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(List<List<Integer>> list) {
        try {
            return GsonUtil.toJson(list);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}