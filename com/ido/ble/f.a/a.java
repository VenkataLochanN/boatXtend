package com.ido.ble.f.a;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4412a = "MIGRATION HELPER - CLASS DOESN'T MATCH WITH THE CURRENT PARAMETERS";

    a() {
    }

    private String a(Class<?> cls) throws Exception {
        if (cls.equals(String.class)) {
            return "TEXT";
        }
        if (cls.equals(Long.class) || cls.equals(Integer.class) || cls.equals(Long.TYPE) || cls.equals(Integer.TYPE)) {
            return "INTEGER  DEFAULT 0";
        }
        if (cls.equals(Boolean.class)) {
            return "BOOLEAN";
        }
        throw new Exception(f4412a.concat(" - Class: ").concat(cls.toString()));
    }

    private static List<String> a(Database database, String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = database.rawQuery("SELECT * FROM " + str + " limit 1", null);
                if (cursorRawQuery != null) {
                    arrayList = new ArrayList(Arrays.asList(cursorRawQuery.getColumnNames()));
                }
            } catch (Exception e2) {
                Log.v(str, e2.getMessage(), e2);
                e2.printStackTrace();
                if (cursorRawQuery != null) {
                }
            }
            return arrayList;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    private static void a(Database database, String str, boolean z, Class<? extends AbstractDao<?, ?>>... clsArr) {
        if (clsArr.length < 1) {
            return;
        }
        try {
            for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
                cls.getDeclaredMethod(str, Database.class, Boolean.TYPE).invoke(null, database, Boolean.valueOf(z));
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    private void a(Database database, boolean z, Class<? extends AbstractDao<?, ?>>... clsArr) {
        if (clsArr != null) {
            a(database, "createTable", z, clsArr);
        }
    }

    private void b(Database database, boolean z, Class<? extends AbstractDao<?, ?>>... clsArr) {
        if (clsArr != null) {
            a(database, "dropTable", z, clsArr);
        }
    }

    private void c(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str = daoConfig.tablename;
            String strConcat = str.concat("_TEMP");
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ");
            sb.append(strConcat);
            sb.append(" (");
            String str2 = "";
            int i = 0;
            while (true) {
                Property[] propertyArr = daoConfig.properties;
                if (i >= propertyArr.length) {
                    break;
                }
                String str3 = propertyArr[i].columnName;
                if (a(database, str).contains(str3)) {
                    arrayList.add(str3);
                    String strA = null;
                    try {
                        try {
                            strA = a(daoConfig.properties[i].type);
                        } catch (Exception unused) {
                        }
                    } catch (Exception unused2) {
                    }
                    sb.append(str2);
                    sb.append(str3);
                    sb.append(" ");
                    sb.append(strA);
                    if (daoConfig.properties[i].primaryKey) {
                        sb.append(" PRIMARY KEY");
                    }
                    str2 = AppInfo.DELIM;
                }
                i++;
            }
            sb.append(");");
            if (arrayList.size() == 0) {
                return;
            }
            database.execSQL(sb.toString());
            database.execSQL("INSERT INTO " + strConcat + " (" + TextUtils.join(AppInfo.DELIM, arrayList) + ") SELECT " + TextUtils.join(AppInfo.DELIM, arrayList) + " FROM " + str + ";");
        }
    }

    private void d(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str = daoConfig.tablename;
            String strConcat = str.concat("_TEMP");
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                Property[] propertyArr = daoConfig.properties;
                if (i >= propertyArr.length) {
                    break;
                }
                String str2 = propertyArr[i].columnName;
                if (a(database, strConcat).contains(str2)) {
                    arrayList.add(str2);
                } else {
                    StringBuilder sb = new StringBuilder();
                    try {
                        sb.append("ALTER TABLE " + strConcat + " ADD COLUMN " + str2 + " " + a(daoConfig.properties[i].type));
                        database.execSQL(sb.toString());
                        arrayList.add(str2);
                    } catch (Exception e2) {
                        LogTool.b("DBMigHelper", e2.getMessage());
                    }
                }
                i++;
            }
            if (arrayList.size() == 0) {
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("DROP TABLE IF EXISTS ");
            sb2.append(strConcat);
            sb2.append(";");
            database.execSQL("INSERT INTO " + str + " (" + TextUtils.join(AppInfo.DELIM, arrayList) + ") SELECT " + TextUtils.join(AppInfo.DELIM, arrayList) + " FROM " + strConcat + ";");
            database.execSQL(sb2.toString());
        }
    }

    public void a(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        a(database, true, clsArr);
    }

    public void b(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        c(database, clsArr);
        b(database, true, clsArr);
        a(database, false, clsArr);
        d(database, clsArr);
    }
}