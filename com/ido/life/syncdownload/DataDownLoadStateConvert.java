package com.ido.life.syncdownload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: compiled from: DataDownLoadStateConvert.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00042\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\n"}, d2 = {"Lcom/ido/life/syncdownload/DataDownLoadStateConvert;", "Lorg/greenrobot/greendao/converter/PropertyConverter;", "", "", "", "()V", "convertToDatabaseValue", "entityProperty", "convertToEntityProperty", "databaseValue", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DataDownLoadStateConvert implements PropertyConverter<List<? extends Integer>, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public /* bridge */ /* synthetic */ String convertToDatabaseValue(List<? extends Integer> list) {
        return convertToDatabaseValue2((List<Integer>) list);
    }

    /* JADX INFO: renamed from: convertToDatabaseValue, reason: avoid collision after fix types in other method */
    public String convertToDatabaseValue2(List<Integer> entityProperty) {
        if (entityProperty == null) {
            return "";
        }
        String json = new Gson().toJson(entityProperty);
        Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(entityProperty)");
        return json;
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public List<Integer> convertToEntityProperty(String databaseValue) {
        try {
            String str = databaseValue;
            if (str == null || str.length() == 0) {
                return CollectionsKt.emptyList();
            }
            Object objFromJson = new Gson().fromJson(databaseValue, new TypeToken<List<? extends Integer>>() { // from class: com.ido.life.syncdownload.DataDownLoadStateConvert.convertToEntityProperty.1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(objFromJson, "Gson().fromJson(database…oken<List<Int>>(){}.type)");
            return (List) objFromJson;
        } catch (Exception e2) {
            e2.printStackTrace();
            return CollectionsKt.emptyList();
        }
    }
}