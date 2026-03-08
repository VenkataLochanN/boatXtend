package com.amazon.identity.auth.device.datastore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.AbstractDataObject;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDataSource<K extends AbstractDataObject> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DELETE_SUCCEEDED = 1;
    public static final int INSERT_FAILED = -1;
    private static final String LOG_TAG = AbstractDataSource.class.getName();
    private static final int UPDATE_SUCCEEDED = 1;
    protected SQLiteDatabase database;

    public abstract K cursorToObject(Cursor cursor);

    public abstract String[] getAllColumns();

    public abstract String getLogTag();

    public abstract String getTableName();

    public AbstractDataSource(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            throw new IllegalArgumentException("database can't be null!");
        }
        this.database = sQLiteDatabase;
    }

    public K findByRowId(long j) {
        return (K) findOneRow(new String[]{"rowid"}, new String[]{"" + j});
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public K findOneRow(java.lang.String[] r10, java.lang.String[] r11) throws java.lang.Throwable {
        /*
            r9 = this;
            r0 = 0
            java.lang.String r4 = getWhereClause(r10, r11)     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            android.database.sqlite.SQLiteDatabase r1 = r9.database     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            java.lang.String r2 = r9.getTableName()     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            java.lang.String[] r3 = r9.getAllColumns()     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            if (r10 == 0) goto L23
            r10.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L21 java.lang.Throwable -> L4e
            com.amazon.identity.auth.device.dataobject.AbstractDataObject r0 = r9.cursorToObject(r10)     // Catch: java.lang.IllegalArgumentException -> L21 java.lang.Throwable -> L4e
            goto L23
        L21:
            r11 = move-exception
            goto L2e
        L23:
            if (r10 == 0) goto L4d
        L25:
            r10.close()
            goto L4d
        L29:
            r11 = move-exception
            r10 = r0
            goto L4f
        L2c:
            r11 = move-exception
            r10 = r0
        L2e:
            java.lang.String r1 = r9.getLogTag()     // Catch: java.lang.Throwable -> L4e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e
            r2.<init>()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r3 = ""
            r2.append(r3)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r3 = r11.getMessage()     // Catch: java.lang.Throwable -> L4e
            r2.append(r3)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L4e
            com.amazon.identity.auth.map.device.utils.MAPLog.e(r1, r2, r11)     // Catch: java.lang.Throwable -> L4e
            if (r10 == 0) goto L4d
            goto L25
        L4d:
            return r0
        L4e:
            r11 = move-exception
        L4f:
            if (r10 == 0) goto L54
            r10.close()
        L54:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.datastore.AbstractDataSource.findOneRow(java.lang.String[], java.lang.String[]):com.amazon.identity.auth.device.dataobject.AbstractDataObject");
    }

    protected K findOneRowBySingleColumn(String str, String str2) {
        return (K) findOneRow(new String[]{str}, new String[]{str2});
    }

    public List<K> findAllRows(String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.database.query(getTableName(), getAllColumns(), getWhereClause(strArr, strArr2), null, null, null, null);
            } catch (IllegalArgumentException e2) {
                MAPLog.e(getLogTag(), "" + e2.getMessage(), e2);
                arrayList.clear();
                if (cursorQuery != null) {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.moveToFirst();
                while (!cursorQuery.isAfterLast()) {
                    AbstractDataObject abstractDataObjectCursorToObject = cursorToObject(cursorQuery);
                    if (abstractDataObjectCursorToObject != null) {
                        arrayList.add(abstractDataObjectCursorToObject);
                        cursorQuery.moveToNext();
                    } else {
                        throw new IllegalArgumentException("cursor contains invalid object!");
                    }
                    return arrayList;
                }
            }
            return arrayList;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    protected List<K> findAllRowsBySingleColumn(String str, String str2) {
        return findAllRows(new String[]{str}, new String[]{str2});
    }

    public List<K> findAllRows() {
        return findAllRows(null, null);
    }

    public long insertRow(K k) {
        if (k == null) {
            return -1L;
        }
        MAPLog.pii(LOG_TAG, "Insert Row table=" + getTableName(), "vals=" + k.getValuesForInsert());
        long jInsert = this.database.insert(getTableName(), null, k.getValuesForInsert());
        k.setRowId(jInsert);
        return jInsert;
    }

    public boolean deleteRow(long j) {
        SQLiteDatabase sQLiteDatabase = this.database;
        String tableName = getTableName();
        StringBuilder sb = new StringBuilder();
        sb.append("rowid = ");
        sb.append(j);
        return sQLiteDatabase.delete(tableName, sb.toString(), null) == 1;
    }

    public int deleteRows(String[] strArr, String[] strArr2) {
        try {
            return this.database.delete(getTableName(), getWhereClause(strArr, strArr2), null);
        } catch (IllegalArgumentException e2) {
            MAPLog.e(getLogTag(), "" + e2.getMessage(), e2);
            return 0;
        }
    }

    protected int deleteRowsBySingleColumn(String str, String str2) {
        return deleteRows(new String[]{str}, new String[]{str2});
    }

    public int deleteAllRows() {
        return deleteRows(null, null);
    }

    public boolean updateRow(long j, ContentValues contentValues) {
        if (contentValues == null) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = this.database;
        String tableName = getTableName();
        StringBuilder sb = new StringBuilder();
        sb.append("rowid = ");
        sb.append(j);
        return sQLiteDatabase.update(tableName, contentValues, sb.toString(), null) == 1;
    }

    private static String getWhereClause(String[] strArr, String[] strArr2) throws IllegalArgumentException {
        String str;
        if (strArr == null && strArr2 == null) {
            return null;
        }
        if (strArr == null || strArr2 == null) {
            throw new IllegalArgumentException("Both arguments have to be either null or not null!");
        }
        if (strArr.length != strArr2.length) {
            throw new IllegalArgumentException("selectionFields and selectionValues differ in length!");
        }
        int i = 0;
        String string = "";
        while (i < strArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(strArr[i]);
            if (strArr2[i] == null) {
                str = " IS NULL";
            } else {
                str = " = '" + strArr2[i] + "'";
            }
            sb.append(str);
            String string2 = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string2);
            sb2.append(i != strArr.length + (-1) ? " AND " : "");
            string = sb2.toString();
            i++;
        }
        return string;
    }

    public int getColumnIndex(Cursor cursor, int i) throws IllegalArgumentException {
        if (i < 0 || i >= getAllColumns().length) {
            throw new IllegalArgumentException("colIndex is out of bound!");
        }
        return cursor.getColumnIndexOrThrow(getAllColumns()[i]);
    }
}