package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class SportCardDao extends AbstractDao<SportCard, Long> {
    public static final String TABLENAME = "SPORT_CARD";
    private final ListConvert ValueListConverter;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property ValueList = new Property(2, String.class, "ValueList", false, "VALUE_LIST");
        public static final Property UploadSuccess = new Property(3, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
        public static final Property CreateTime = new Property(4, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(5, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public SportCardDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.ValueListConverter = new ListConvert();
    }

    public SportCardDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.ValueListConverter = new ListConvert();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SPORT_CARD\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"VALUE_LIST\" TEXT,\"UPLOAD_SUCCESS\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SPORT_CARD\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SportCard sportCard) {
        databaseStatement.clearBindings();
        Long id = sportCard.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, sportCard.getUserId());
        List<Integer> valueList = sportCard.getValueList();
        if (valueList != null) {
            databaseStatement.bindString(3, this.ValueListConverter.convertToDatabaseValue(valueList));
        }
        databaseStatement.bindLong(4, sportCard.getUploadSuccess() ? 1L : 0L);
        databaseStatement.bindLong(5, sportCard.getCreateTime());
        databaseStatement.bindLong(6, sportCard.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SportCard sportCard) {
        sQLiteStatement.clearBindings();
        Long id = sportCard.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, sportCard.getUserId());
        List<Integer> valueList = sportCard.getValueList();
        if (valueList != null) {
            sQLiteStatement.bindString(3, this.ValueListConverter.convertToDatabaseValue(valueList));
        }
        sQLiteStatement.bindLong(4, sportCard.getUploadSuccess() ? 1L : 0L);
        sQLiteStatement.bindLong(5, sportCard.getCreateTime());
        sQLiteStatement.bindLong(6, sportCard.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(SportCard sportCard) {
        super.attachEntity(sportCard);
        sportCard.__setDaoSession(this.daoSession);
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
    public SportCard readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 2;
        return new SportCard(lValueOf, cursor.getLong(i + 1), cursor.isNull(i3) ? null : this.ValueListConverter.convertToEntityProperty(cursor.getString(i3)), cursor.getShort(i + 3) != 0, cursor.getLong(i + 4), cursor.getLong(i + 5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SportCard sportCard, int i) {
        int i2 = i + 0;
        sportCard.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        sportCard.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        sportCard.setValueList(cursor.isNull(i3) ? null : this.ValueListConverter.convertToEntityProperty(cursor.getString(i3)));
        sportCard.setUploadSuccess(cursor.getShort(i + 3) != 0);
        sportCard.setCreateTime(cursor.getLong(i + 4));
        sportCard.setUpdateTime(cursor.getLong(i + 5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SportCard sportCard, long j) {
        sportCard.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SportCard sportCard) {
        if (sportCard != null) {
            return sportCard.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SportCard sportCard) {
        return sportCard.getId() != null;
    }
}