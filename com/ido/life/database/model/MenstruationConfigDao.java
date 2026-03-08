package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class MenstruationConfigDao extends AbstractDao<MenstruationConfig, Long> {
    public static final String TABLENAME = "MENSTRUATION_CONFIG";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property MensLength = new Property(2, Integer.TYPE, "MensLength", false, "MENS_LENGTH");
        public static final Property MensCycle = new Property(3, Integer.TYPE, "MensCycle", false, "MENS_CYCLE");
        public static final Property UpdateTimeStamp = new Property(4, Long.TYPE, "updateTimeStamp", false, "UPDATE_TIME_STAMP");
        public static final Property UploadSuccess = new Property(5, Boolean.TYPE, "uploadSuccess", false, "UPLOAD_SUCCESS");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public MenstruationConfigDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MenstruationConfigDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"MENSTRUATION_CONFIG\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MENS_LENGTH\" INTEGER NOT NULL ,\"MENS_CYCLE\" INTEGER NOT NULL ,\"UPDATE_TIME_STAMP\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"MENSTRUATION_CONFIG\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, MenstruationConfig menstruationConfig) {
        databaseStatement.clearBindings();
        Long id = menstruationConfig.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, menstruationConfig.getUserId());
        databaseStatement.bindLong(3, menstruationConfig.getMensLength());
        databaseStatement.bindLong(4, menstruationConfig.getMensCycle());
        databaseStatement.bindLong(5, menstruationConfig.getUpdateTimeStamp());
        databaseStatement.bindLong(6, menstruationConfig.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, MenstruationConfig menstruationConfig) {
        sQLiteStatement.clearBindings();
        Long id = menstruationConfig.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, menstruationConfig.getUserId());
        sQLiteStatement.bindLong(3, menstruationConfig.getMensLength());
        sQLiteStatement.bindLong(4, menstruationConfig.getMensCycle());
        sQLiteStatement.bindLong(5, menstruationConfig.getUpdateTimeStamp());
        sQLiteStatement.bindLong(6, menstruationConfig.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(MenstruationConfig menstruationConfig) {
        super.attachEntity(menstruationConfig);
        menstruationConfig.__setDaoSession(this.daoSession);
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
    public MenstruationConfig readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        return new MenstruationConfig(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 1), cursor.getInt(i + 2), cursor.getInt(i + 3), cursor.getLong(i + 4), cursor.getShort(i + 5) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, MenstruationConfig menstruationConfig, int i) {
        int i2 = i + 0;
        menstruationConfig.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        menstruationConfig.setUserId(cursor.getLong(i + 1));
        menstruationConfig.setMensLength(cursor.getInt(i + 2));
        menstruationConfig.setMensCycle(cursor.getInt(i + 3));
        menstruationConfig.setUpdateTimeStamp(cursor.getLong(i + 4));
        menstruationConfig.setUploadSuccess(cursor.getShort(i + 5) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(MenstruationConfig menstruationConfig, long j) {
        menstruationConfig.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(MenstruationConfig menstruationConfig) {
        if (menstruationConfig != null) {
            return menstruationConfig.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(MenstruationConfig menstruationConfig) {
        return menstruationConfig.getId() != null;
    }
}