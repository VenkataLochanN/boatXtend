package com.amap.api.mapcore.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DBOperation.java */
/* JADX INFO: loaded from: classes.dex */
public class hr {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Map<Class<? extends hq>, hq> f1284d = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private hu f1285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SQLiteDatabase f1286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private hq f1287c;

    private boolean a(Annotation annotation) {
        return annotation != null;
    }

    public hr(Context context, hq hqVar) {
        try {
            this.f1285a = new hu(context.getApplicationContext(), hqVar.b(), null, hqVar.c(), hqVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f1287c = hqVar;
    }

    public <T> void a(String str, Class<T> cls) {
        synchronized (this.f1287c) {
            String strA = a(a((Class) cls));
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            this.f1286b = b(false);
            if (this.f1286b == null) {
                return;
            }
            try {
                this.f1286b.delete(strA, str, null);
            } catch (Throwable th) {
                try {
                    hk.a(th, "dbs", "dld");
                    if (this.f1286b != null) {
                        this.f1286b.close();
                    }
                } catch (Throwable th2) {
                    if (this.f1286b != null) {
                        this.f1286b.close();
                        this.f1286b = null;
                    }
                    throw th2;
                }
            }
            if (this.f1286b != null) {
                this.f1286b.close();
                this.f1286b = null;
            }
        }
    }

    public <T> void a(String str, Object obj, boolean z) {
        synchronized (this.f1287c) {
            if (obj == null) {
                return;
            }
            hs hsVarA = a((Class) obj.getClass());
            String strA = a(hsVarA);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            ContentValues contentValuesA = a(obj, hsVarA);
            if (contentValuesA == null) {
                return;
            }
            this.f1286b = b(z);
            if (this.f1286b == null) {
                return;
            }
            try {
                this.f1286b.update(strA, contentValuesA, str, null);
            } catch (Throwable th) {
                try {
                    if (z) {
                        th.printStackTrace();
                    } else {
                        hk.a(th, "dbs", "udd");
                    }
                    if (this.f1286b != null) {
                        this.f1286b.close();
                    }
                } finally {
                    if (this.f1286b != null) {
                        this.f1286b.close();
                        this.f1286b = null;
                    }
                }
            }
            if (this.f1286b != null) {
                this.f1286b.close();
                this.f1286b = null;
            }
        }
    }

    public <T> void a(String str, Object obj) {
        a(str, obj, false);
    }

    public void a(Object obj, String str) {
        synchronized (this.f1287c) {
            List listB = b(str, obj.getClass());
            if (listB == null || listB.size() == 0) {
                a(obj);
            } else {
                a(str, obj);
            }
        }
    }

    public <T> void a(T t) {
        a((Object) t, false);
    }

    public <T> void a(T t, boolean z) {
        synchronized (this.f1287c) {
            this.f1286b = b(z);
            if (this.f1286b == null) {
                return;
            }
            try {
                a(this.f1286b, t);
            } catch (Throwable th) {
                try {
                    hk.a(th, "dbs", "itd");
                    if (this.f1286b != null) {
                        this.f1286b.close();
                    }
                } catch (Throwable th2) {
                    if (this.f1286b != null) {
                        this.f1286b.close();
                        this.f1286b = null;
                    }
                    throw th2;
                }
            }
            if (this.f1286b != null) {
                this.f1286b.close();
                this.f1286b = null;
            }
        }
    }

    private <T> void a(SQLiteDatabase sQLiteDatabase, T t) {
        ContentValues contentValuesA;
        hs hsVarA = a((Class) t.getClass());
        String strA = a(hsVarA);
        if (TextUtils.isEmpty(strA) || t == null || sQLiteDatabase == null || (contentValuesA = a(t, hsVarA)) == null) {
            return;
        }
        sQLiteDatabase.insert(strA, null, contentValuesA);
    }

    public <T> void a(List<T> list) {
        String str;
        String str2;
        synchronized (this.f1287c) {
            if (list != null) {
                if (list.size() != 0) {
                    this.f1286b = b(false);
                    if (this.f1286b == null) {
                        return;
                    }
                    try {
                        this.f1286b.beginTransaction();
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            a(this.f1286b, it.next());
                        }
                        this.f1286b.setTransactionSuccessful();
                        try {
                            this.f1286b.close();
                            this.f1286b = null;
                        } catch (Throwable th) {
                            th = th;
                            str = "dbs";
                            str2 = "ild";
                            hk.a(th, str, str2);
                        }
                    } catch (Throwable th2) {
                        try {
                            hk.a(th2, "dbs", "ild");
                            try {
                                if (this.f1286b.inTransaction()) {
                                    this.f1286b.endTransaction();
                                }
                            } catch (Throwable th3) {
                                hk.a(th3, "dbs", "ild");
                            }
                            try {
                                this.f1286b.close();
                                this.f1286b = null;
                            } catch (Throwable th4) {
                                th = th4;
                                str = "dbs";
                                str2 = "ild";
                                hk.a(th, str, str2);
                            }
                        } finally {
                            try {
                            } catch (Throwable th5) {
                                hk.a(th5, "dbs", "ild");
                            }
                            if (this.f1286b.inTransaction()) {
                                this.f1286b.endTransaction();
                                try {
                                    this.f1286b.close();
                                    this.f1286b = null;
                                    throw th;
                                } catch (Throwable th6) {
                                    hk.a(th6, "dbs", "ild");
                                }
                            }
                            this.f1286b.close();
                            this.f1286b = null;
                            throw th;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0052 A[Catch: all -> 0x005a, DONT_GENERATE, TRY_LEAVE, TryCatch #7 {all -> 0x005a, blocks: (B:23:0x004e, B:25:0x0052), top: B:107:0x004e, outer: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bf A[Catch: all -> 0x00c7, FINALLY_INSNS, TRY_LEAVE, TryCatch #3 {all -> 0x00c7, blocks: (B:65:0x00bb, B:67:0x00bf), top: B:99:0x00bb, outer: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e6 A[Catch: all -> 0x00ee, TRY_LEAVE, TryCatch #10 {all -> 0x00ee, blocks: (B:79:0x00e2, B:81:0x00e6), top: B:111:0x00e2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.hr.a(java.lang.String, java.lang.Class, boolean):java.util.List");
    }

    public <T> List<T> b(String str, Class<T> cls) {
        return a(str, (Class) cls, false);
    }

    private <T> T a(Cursor cursor, Class<T> cls, hs hsVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Field[] fieldArrA = a((Class<?>) cls, hsVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T tNewInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : fieldArrA) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ht.class);
            if (annotation != null) {
                ht htVar = (ht) annotation;
                int iB = htVar.b();
                int columnIndex = cursor.getColumnIndex(htVar.a());
                switch (iB) {
                    case 1:
                        field.set(tNewInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        break;
                    case 2:
                        field.set(tNewInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        break;
                    case 3:
                        field.set(tNewInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        break;
                    case 4:
                        field.set(tNewInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        break;
                    case 5:
                        field.set(tNewInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        break;
                    case 6:
                        field.set(tNewInstance, cursor.getString(columnIndex));
                        break;
                    case 7:
                        field.set(tNewInstance, cursor.getBlob(columnIndex));
                        break;
                }
            }
        }
        return tNewInstance;
    }

    private void a(Object obj, Field field, ContentValues contentValues) {
        Annotation annotation = field.getAnnotation(ht.class);
        if (annotation == null) {
            return;
        }
        ht htVar = (ht) annotation;
        try {
            switch (htVar.b()) {
                case 1:
                    contentValues.put(htVar.a(), Short.valueOf(field.getShort(obj)));
                    break;
                case 2:
                    contentValues.put(htVar.a(), Integer.valueOf(field.getInt(obj)));
                    break;
                case 3:
                    contentValues.put(htVar.a(), Float.valueOf(field.getFloat(obj)));
                    break;
                case 4:
                    contentValues.put(htVar.a(), Double.valueOf(field.getDouble(obj)));
                    break;
                case 5:
                    contentValues.put(htVar.a(), Long.valueOf(field.getLong(obj)));
                    break;
                case 6:
                    contentValues.put(htVar.a(), (String) field.get(obj));
                    break;
                case 7:
                    contentValues.put(htVar.a(), (byte[]) field.get(obj));
                    break;
                default:
                    return;
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    private ContentValues a(Object obj, hs hsVar) {
        ContentValues contentValues = new ContentValues();
        for (Field field : a(obj.getClass(), hsVar.b())) {
            field.setAccessible(true);
            a(obj, field, contentValues);
        }
        return contentValues;
    }

    private Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (z) {
            return cls.getSuperclass().getDeclaredFields();
        }
        return cls.getDeclaredFields();
    }

    private SQLiteDatabase a(boolean z) {
        try {
            if (this.f1286b == null) {
                this.f1286b = this.f1285a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (!z) {
                hk.a(th, "dbs", "grd");
            } else {
                th.printStackTrace();
            }
        }
        return this.f1286b;
    }

    private SQLiteDatabase b(boolean z) {
        try {
            if (this.f1286b == null || this.f1286b.isReadOnly()) {
                if (this.f1286b != null) {
                    this.f1286b.close();
                }
                this.f1286b = this.f1285a.getWritableDatabase();
            }
        } catch (Throwable th) {
            hk.a(th, "dbs", "gwd");
        }
        return this.f1286b;
    }

    private <T> String a(hs hsVar) {
        if (hsVar == null) {
            return null;
        }
        return hsVar.a();
    }

    private <T> hs a(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(hs.class);
        if (a(annotation)) {
            return (hs) annotation;
        }
        return null;
    }
}