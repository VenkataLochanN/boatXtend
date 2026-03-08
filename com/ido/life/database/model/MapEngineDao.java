package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class MapEngineDao extends AbstractDao<MapEngine, Long> {
    public static final String TABLENAME = "MAP_ENGINE";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property MapEngine = new Property(2, Integer.TYPE, "MapEngine", false, "MAP_ENGINE");
        public static final Property HasUpload = new Property(3, Boolean.TYPE, "HasUpload", false, "HAS_UPLOAD");
        public static final Property CreateTime = new Property(4, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(5, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public MapEngineDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MapEngineDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"MAP_ENGINE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MAP_ENGINE\" INTEGER NOT NULL ,\"HAS_UPLOAD\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"MAP_ENGINE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, MapEngine mapEngine) {
        databaseStatement.clearBindings();
        Long id = mapEngine.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, mapEngine.getUserId());
        databaseStatement.bindLong(3, mapEngine.getMapEngine());
        databaseStatement.bindLong(4, mapEngine.getHasUpload() ? 1L : 0L);
        databaseStatement.bindLong(5, mapEngine.getCreateTime());
        databaseStatement.bindLong(6, mapEngine.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, MapEngine mapEngine) {
        sQLiteStatement.clearBindings();
        Long id = mapEngine.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, mapEngine.getUserId());
        sQLiteStatement.bindLong(3, mapEngine.getMapEngine());
        sQLiteStatement.bindLong(4, mapEngine.getHasUpload() ? 1L : 0L);
        sQLiteStatement.bindLong(5, mapEngine.getCreateTime());
        sQLiteStatement.bindLong(6, mapEngine.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(MapEngine mapEngine) {
        super.attachEntity(mapEngine);
        mapEngine.__setDaoSession(this.daoSession);
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
    public MapEngine readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        return new MapEngine(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 1), cursor.getInt(i + 2), cursor.getShort(i + 3) != 0, cursor.getLong(i + 4), cursor.getLong(i + 5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, MapEngine mapEngine, int i) {
        int i2 = i + 0;
        mapEngine.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        mapEngine.setUserId(cursor.getLong(i + 1));
        mapEngine.setMapEngine(cursor.getInt(i + 2));
        mapEngine.setHasUpload(cursor.getShort(i + 3) != 0);
        mapEngine.setCreateTime(cursor.getLong(i + 4));
        mapEngine.setUpdateTime(cursor.getLong(i + 5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(MapEngine mapEngine, long j) {
        mapEngine.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(MapEngine mapEngine) {
        if (mapEngine != null) {
            return mapEngine.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(MapEngine mapEngine) {
        return mapEngine.getId() != null;
    }
}