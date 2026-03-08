package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.king.zxing.Intents;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class NoticeAppEntityDao extends AbstractDao<NoticeAppEntity, Long> {
    public static final String TABLENAME = "NoticeApp";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property Type = new Property(1, Integer.TYPE, "Type", false, Intents.WifiConnect.TYPE);
        public static final Property PkgName = new Property(2, String.class, "pkgName", false, "PKG_NAME");
        public static final Property ReminderCounts = new Property(3, Long.TYPE, "reminderCounts", false, "REMINDER_COUNTS");
        public static final Property AppUpdateTime = new Property(4, Long.TYPE, "appUpdateTime", false, "APP_UPDATE_TIME");
        public static final Property AppName = new Property(5, String.class, "appName", false, "APP_NAME");
        public static final Property IsPush = new Property(6, Boolean.TYPE, "isPush", false, "IS_PUSH");
        public static final Property Mac = new Property(7, String.class, "mac", false, "MAC");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public NoticeAppEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public NoticeAppEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"NoticeApp\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TYPE\" INTEGER NOT NULL ,\"PKG_NAME\" TEXT,\"REMINDER_COUNTS\" INTEGER NOT NULL ,\"APP_UPDATE_TIME\" INTEGER NOT NULL ,\"APP_NAME\" TEXT,\"IS_PUSH\" INTEGER NOT NULL ,\"MAC\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"NoticeApp\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, NoticeAppEntity noticeAppEntity) {
        databaseStatement.clearBindings();
        Long id = noticeAppEntity.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, noticeAppEntity.getType());
        String pkgName = noticeAppEntity.getPkgName();
        if (pkgName != null) {
            databaseStatement.bindString(3, pkgName);
        }
        databaseStatement.bindLong(4, noticeAppEntity.getReminderCounts());
        databaseStatement.bindLong(5, noticeAppEntity.getAppUpdateTime());
        String appName = noticeAppEntity.getAppName();
        if (appName != null) {
            databaseStatement.bindString(6, appName);
        }
        databaseStatement.bindLong(7, noticeAppEntity.getIsPush() ? 1L : 0L);
        String mac = noticeAppEntity.getMac();
        if (mac != null) {
            databaseStatement.bindString(8, mac);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, NoticeAppEntity noticeAppEntity) {
        sQLiteStatement.clearBindings();
        Long id = noticeAppEntity.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, noticeAppEntity.getType());
        String pkgName = noticeAppEntity.getPkgName();
        if (pkgName != null) {
            sQLiteStatement.bindString(3, pkgName);
        }
        sQLiteStatement.bindLong(4, noticeAppEntity.getReminderCounts());
        sQLiteStatement.bindLong(5, noticeAppEntity.getAppUpdateTime());
        String appName = noticeAppEntity.getAppName();
        if (appName != null) {
            sQLiteStatement.bindString(6, appName);
        }
        sQLiteStatement.bindLong(7, noticeAppEntity.getIsPush() ? 1L : 0L);
        String mac = noticeAppEntity.getMac();
        if (mac != null) {
            sQLiteStatement.bindString(8, mac);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(NoticeAppEntity noticeAppEntity) {
        super.attachEntity(noticeAppEntity);
        noticeAppEntity.__setDaoSession(this.daoSession);
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

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public NoticeAppEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        long j = cursor.getLong(i + 3);
        long j2 = cursor.getLong(i + 4);
        int i5 = i + 5;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        boolean z = cursor.getShort(i + 6) != 0;
        int i6 = i + 7;
        return new NoticeAppEntity(lValueOf, i3, string, j, j2, string2, z, cursor.isNull(i6) ? null : cursor.getString(i6));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, NoticeAppEntity noticeAppEntity, int i) {
        int i2 = i + 0;
        noticeAppEntity.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        noticeAppEntity.setType(cursor.getInt(i + 1));
        int i3 = i + 2;
        noticeAppEntity.setPkgName(cursor.isNull(i3) ? null : cursor.getString(i3));
        noticeAppEntity.setReminderCounts(cursor.getLong(i + 3));
        noticeAppEntity.setAppUpdateTime(cursor.getLong(i + 4));
        int i4 = i + 5;
        noticeAppEntity.setAppName(cursor.isNull(i4) ? null : cursor.getString(i4));
        noticeAppEntity.setIsPush(cursor.getShort(i + 6) != 0);
        int i5 = i + 7;
        noticeAppEntity.setMac(cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(NoticeAppEntity noticeAppEntity, long j) {
        noticeAppEntity.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(NoticeAppEntity noticeAppEntity) {
        if (noticeAppEntity != null) {
            return noticeAppEntity.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(NoticeAppEntity noticeAppEntity) {
        return noticeAppEntity.getId() != null;
    }
}