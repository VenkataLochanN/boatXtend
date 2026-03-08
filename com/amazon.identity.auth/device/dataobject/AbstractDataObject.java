package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import com.amazon.identity.auth.device.datastore.AbstractDataSource;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDataObject {
    public static final int NON_EXISTENT_ROW = -1;
    private long rowId = -1;

    public abstract boolean equals(Object obj);

    public abstract <K extends AbstractDataObject> AbstractDataSource<K> getDataSource(Context context);

    public abstract ContentValues getValuesForInsert();

    public long getRowId() {
        return this.rowId;
    }

    public void setRowId(long j) {
        this.rowId = j;
    }

    public long insert(Context context) {
        return getDataSource(context).insertRow(this);
    }

    public boolean update(Context context) {
        return getDataSource(context).updateRow(getRowId(), getValuesForInsert());
    }

    public boolean insertOrUpdate(Context context) {
        if (getRowId() == -1) {
            return getDataSource(context).insertRow(this) != -1;
        }
        return getDataSource(context).updateRow(getRowId(), getValuesForInsert());
    }

    public boolean delete(Context context) {
        boolean zDeleteRow = getDataSource(context).deleteRow(getRowId());
        if (zDeleteRow) {
            setRowId(-1L);
        }
        return zDeleteRow;
    }

    public String toString() {
        return "rowid = " + getRowId() + "|" + getValuesForInsert().toString();
    }

    protected boolean areObjectsEqual(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}