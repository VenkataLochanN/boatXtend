package com.ido.life.database.model;

import android.graphics.PointF;
import com.ido.common.utils.GsonUtil;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class ConvertListPointItem implements PropertyConverter<List<PointF>, String> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public List<PointF> convertToEntityProperty(String str) {
        try {
            return GsonUtil.analysisJsonObjectToList(str, PointF.class);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public String convertToDatabaseValue(List<PointF> list) {
        try {
            return GsonUtil.toJson(list);
        } catch (Exception unused) {
            return "";
        }
    }
}