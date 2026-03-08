package com.loc;

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
import java.util.Map;

/* JADX INFO: compiled from: DBOperation.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ae {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Map<Class<? extends ad>, ad> f4729d = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ah f4730a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SQLiteDatabase f4731b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ad f4732c;

    public ae(Context context, ad adVar) {
        try {
            this.f4730a = new ah(context.getApplicationContext(), adVar.a(), adVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f4732c = adVar;
    }

    private static ContentValues a(Object obj, af afVar) {
        ContentValues contentValues = new ContentValues();
        for (Field field : a(obj.getClass(), afVar.b())) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ag.class);
            if (annotation != null) {
                ag agVar = (ag) annotation;
                switch (agVar.b()) {
                    case 1:
                        contentValues.put(agVar.a(), Short.valueOf(field.getShort(obj)));
                        break;
                    case 2:
                        contentValues.put(agVar.a(), Integer.valueOf(field.getInt(obj)));
                        break;
                    case 3:
                        contentValues.put(agVar.a(), Float.valueOf(field.getFloat(obj)));
                        break;
                    case 4:
                        contentValues.put(agVar.a(), Double.valueOf(field.getDouble(obj)));
                        break;
                    case 5:
                        contentValues.put(agVar.a(), Long.valueOf(field.getLong(obj)));
                        break;
                    case 6:
                        contentValues.put(agVar.a(), (String) field.get(obj));
                        break;
                    case 7:
                        try {
                            contentValues.put(agVar.a(), (byte[]) field.get(obj));
                        } catch (IllegalAccessException e2) {
                            e2.printStackTrace();
                        }
                        break;
                }
            }
        }
        return contentValues;
    }

    private SQLiteDatabase a() {
        try {
            if (this.f4731b == null) {
                this.f4731b = this.f4730a.getReadableDatabase();
            }
        } catch (Throwable th) {
            y.a(th, "dbs", "grd");
        }
        return this.f4731b;
    }

    public static synchronized ad a(Class<? extends ad> cls) throws IllegalAccessException, InstantiationException {
        if (f4729d.get(cls) == null) {
            f4729d.put(cls, cls.newInstance());
        }
        return f4729d.get(cls);
    }

    private static <T> T a(Cursor cursor, Class<T> cls, af afVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Object objValueOf;
        Field[] fieldArrA = a((Class<?>) cls, afVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T tNewInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : fieldArrA) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ag.class);
            if (annotation != null) {
                ag agVar = (ag) annotation;
                int iB = agVar.b();
                int columnIndex = cursor.getColumnIndex(agVar.a());
                switch (iB) {
                    case 1:
                        objValueOf = Short.valueOf(cursor.getShort(columnIndex));
                        break;
                    case 2:
                        objValueOf = Integer.valueOf(cursor.getInt(columnIndex));
                        break;
                    case 3:
                        objValueOf = Float.valueOf(cursor.getFloat(columnIndex));
                        break;
                    case 4:
                        objValueOf = Double.valueOf(cursor.getDouble(columnIndex));
                        break;
                    case 5:
                        objValueOf = Long.valueOf(cursor.getLong(columnIndex));
                        break;
                    case 6:
                        objValueOf = cursor.getString(columnIndex);
                        break;
                    case 7:
                        field.set(tNewInstance, cursor.getBlob(columnIndex));
                        continue;
                }
                field.set(tNewInstance, objValueOf);
            }
        }
        return tNewInstance;
    }

    private static <T> String a(af afVar) {
        if (afVar == null) {
            return null;
        }
        return afVar.a();
    }

    private static Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        return z ? cls.getSuperclass().getDeclaredFields() : cls.getDeclaredFields();
    }

    private SQLiteDatabase b() {
        try {
            if (this.f4731b == null || this.f4731b.isReadOnly()) {
                if (this.f4731b != null) {
                    this.f4731b.close();
                }
                this.f4731b = this.f4730a.getWritableDatabase();
            }
        } catch (Throwable th) {
            y.a(th, "dbs", "gwd");
        }
        return this.f4731b;
    }

    private static <T> af b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(af.class);
        if (annotation != null) {
            return (af) annotation;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:? A[Catch: all -> 0x00e6, SYNTHETIC, TryCatch #6 {, blocks: (B:4:0x0003, B:6:0x0014, B:7:0x001a, B:9:0x001e, B:28:0x0060, B:27:0x0059, B:21:0x0045, B:63:0x00be, B:45:0x008f, B:38:0x0077, B:56:0x00a5, B:77:0x00e3, B:76:0x00dc, B:70:0x00c8, B:78:0x00e4, B:51:0x0097, B:53:0x00a0, B:18:0x0040, B:35:0x0072, B:67:0x00c3, B:22:0x004c, B:24:0x0050, B:71:0x00cf, B:73:0x00d3), top: B:94:0x0003, inners: #0, #2, #3, #5, #7, #9, #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050 A[Catch: all -> 0x0058, TRY_LEAVE, TryCatch #9 {all -> 0x0058, blocks: (B:22:0x004c, B:24:0x0050), top: B:99:0x004c, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d3 A[Catch: all -> 0x00db, TRY_LEAVE, TryCatch #11 {all -> 0x00db, blocks: (B:71:0x00cf, B:73:0x00d3), top: B:103:0x00cf, outer: #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ae.a(java.lang.String, java.lang.Class):java.util.List");
    }

    public final void a(Object obj, String str) {
        synchronized (this.f4732c) {
            if (a(str, obj.getClass()).size() == 0) {
                synchronized (this.f4732c) {
                    this.f4731b = b();
                    if (this.f4731b != null) {
                        try {
                            SQLiteDatabase sQLiteDatabase = this.f4731b;
                            af afVarB = b(obj.getClass());
                            String strA = a(afVarB);
                            if (!TextUtils.isEmpty(strA) && sQLiteDatabase != null) {
                                sQLiteDatabase.insert(strA, null, a(obj, afVarB));
                            }
                        } catch (Throwable th) {
                            try {
                                y.a(th, "dbs", "itd");
                                if (this.f4731b != null) {
                                    this.f4731b.close();
                                    this.f4731b = null;
                                }
                            } finally {
                            }
                        }
                        if (this.f4731b != null) {
                            this.f4731b.close();
                            this.f4731b = null;
                        }
                    }
                }
            } else {
                synchronized (this.f4732c) {
                    af afVarB2 = b(obj.getClass());
                    String strA2 = a(afVarB2);
                    if (!TextUtils.isEmpty(strA2)) {
                        ContentValues contentValuesA = a(obj, afVarB2);
                        this.f4731b = b();
                        if (this.f4731b != null) {
                            try {
                                this.f4731b.update(strA2, contentValuesA, str, null);
                            } catch (Throwable th2) {
                                try {
                                    y.a(th2, "dbs", "udd");
                                    if (this.f4731b != null) {
                                        this.f4731b.close();
                                        this.f4731b = null;
                                    }
                                } finally {
                                }
                            }
                            if (this.f4731b != null) {
                                this.f4731b.close();
                                this.f4731b = null;
                            }
                        }
                    }
                }
            }
        }
    }
}