package com.ido.life.database.upgrade;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.database.DatabaseUpgradeUtil;
import com.ido.life.database.GreenDaoManager;
import com.ido.life.database.model.DaoSession;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseUpgrateDataBase implements DatabaseUpgradeUtil.UpgrateDataBase {
    protected void printAndSave(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDataBaseUpgradePath(), str);
    }

    protected DaoSession getDaoSession() {
        return GreenDaoManager.getInstance().getDaoSession();
    }

    protected List<ContentValues> getTableValue(String str, StandardDatabaseWraper standardDatabaseWraper) {
        Cursor cursorQuery;
        if (TextUtils.isEmpty(str) || standardDatabaseWraper == null || (cursorQuery = standardDatabaseWraper.query(str)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursorQuery.moveToNext()) {
            int columnCount = cursorQuery.getColumnCount();
            if (columnCount > 0) {
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = cursorQuery.getColumnName(i);
                    int type = cursorQuery.getType(i);
                    if (type != 0) {
                        if (type == 1) {
                            int i2 = cursorQuery.getInt(i);
                            long j = cursorQuery.getLong(i);
                            if (i2 != j) {
                                contentValues.put(columnName, Long.valueOf(j));
                            } else {
                                contentValues.put(columnName, Integer.valueOf(i2));
                            }
                        } else if (type == 2) {
                            float f2 = cursorQuery.getFloat(i);
                            double d2 = cursorQuery.getDouble(i);
                            if (f2 != d2) {
                                contentValues.put(columnName, Double.valueOf(d2));
                            } else {
                                contentValues.put(columnName, Float.valueOf(f2));
                            }
                        } else if (type == 3) {
                            contentValues.put(columnName, cursorQuery.getString(i));
                        } else if (type == 4) {
                            contentValues.put(columnName, cursorQuery.getBlob(i));
                        }
                    }
                }
                arrayList.add(contentValues);
            }
        }
        try {
            cursorQuery.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }
}