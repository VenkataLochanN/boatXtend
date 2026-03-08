package com.ido.life.syncdownload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: compiled from: DataDownLoadParamsConvert.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00032\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J\u001e\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\t"}, d2 = {"Lcom/ido/life/syncdownload/DataDownLoadParamsConvert;", "Lorg/greenrobot/greendao/converter/PropertyConverter;", "", "", "()V", "convertToDatabaseValue", "entityProperty", "convertToEntityProperty", "databaseValue", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DataDownLoadParamsConvert implements PropertyConverter<Map<String, ? extends String>, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public /* bridge */ /* synthetic */ String convertToDatabaseValue(Map<String, ? extends String> map) {
        return convertToDatabaseValue2((Map<String, String>) map);
    }

    /* JADX INFO: renamed from: convertToDatabaseValue, reason: avoid collision after fix types in other method */
    public String convertToDatabaseValue2(Map<String, String> entityProperty) {
        if (entityProperty == null) {
            return "";
        }
        String json = new Gson().toJson(entityProperty);
        Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(entityProperty)");
        return json;
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public Map<String, String> convertToEntityProperty(String databaseValue) {
        Object objFromJson;
        try {
            String str = databaseValue;
            if (str == null || str.length() == 0) {
                objFromJson = new LinkedHashMap();
            } else {
                objFromJson = new Gson().fromJson(databaseValue, new TypeToken<Map<String, ? extends String>>() { // from class: com.ido.life.syncdownload.DataDownLoadParamsConvert.convertToEntityProperty.1
                }.getType());
                Intrinsics.checkExpressionValueIsNotNull(objFromJson, "Gson().fromJson(database…String,String>>(){}.type)");
            }
            return (Map) objFromJson;
        } catch (Exception e2) {
            e2.printStackTrace();
            return new LinkedHashMap();
        }
    }
}