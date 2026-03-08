package com.ido.life.database.model;

import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX INFO: loaded from: classes2.dex */
public class AtomicIntegerConverter implements PropertyConverter<AtomicInteger, Integer> {
    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public AtomicInteger convertToEntityProperty(Integer num) {
        return new AtomicInteger(num.intValue());
    }

    @Override // org.greenrobot.greendao.converter.PropertyConverter
    public Integer convertToDatabaseValue(AtomicInteger atomicInteger) {
        return Integer.valueOf(atomicInteger.get());
    }
}