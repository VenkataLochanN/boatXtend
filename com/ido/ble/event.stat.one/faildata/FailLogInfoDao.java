package com.ido.ble.event.stat.one.faildata;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.ble.data.manage.database.DaoSession;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class FailLogInfoDao extends AbstractDao<c, Long> {
    public static final String TABLENAME = "FAIL_LOG_INFO";

    public static class Properties {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property f4400a = new Property(0, Long.class, "id", true, "_id");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Property f4401b = new Property(1, String.class, "detail", false, "DETAIL");

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Property f4402c = new Property(2, String.class, "time", false, "TIME");
    }

    public FailLogInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FailLogInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void a(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"FAIL_LOG_INFO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DETAIL\" TEXT,\"TIME\" TEXT UNIQUE );");
    }

    public static void b(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"FAIL_LOG_INFO\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Long getKey(c cVar) {
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final Long updateKeyAfterInsert(c cVar, long j) {
        cVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void readEntity(Cursor cursor, c cVar, int i) {
        int i2 = i + 0;
        cVar.a(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        cVar.a(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        cVar.b(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void bindValues(SQLiteStatement sQLiteStatement, c cVar) {
        sQLiteStatement.clearBindings();
        Long lB = cVar.b();
        if (lB != null) {
            sQLiteStatement.bindLong(1, lB.longValue());
        }
        String strA = cVar.a();
        if (strA != null) {
            sQLiteStatement.bindString(2, strA);
        }
        String strC = cVar.c();
        if (strC != null) {
            sQLiteStatement.bindString(3, strC);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void bindValues(DatabaseStatement databaseStatement, c cVar) {
        databaseStatement.clearBindings();
        Long lB = cVar.b();
        if (lB != null) {
            databaseStatement.bindLong(1, lB.longValue());
        }
        String strA = cVar.a();
        if (strA != null) {
            databaseStatement.bindString(2, strA);
        }
        String strC = cVar.c();
        if (strC != null) {
            databaseStatement.bindString(3, strC);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean hasKey(c cVar) {
        return cVar.b() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public c readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        return new c(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }
}