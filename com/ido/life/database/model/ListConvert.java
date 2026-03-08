package com.ido.life.database.model;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.utils.GsonUtil;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ListConvert implements PropertyConverter<List<Integer>, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public List<Integer> convertToEntityProperty(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<Integer>>() { // from class: com.ido.life.database.model.ListConvert.1
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(List<Integer> list) {
        if (list == null) {
            return null;
        }
        return GsonUtil.toJson(list);
    }
}