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
public class MessageEntityDao extends AbstractDao<MessageEntity, Long> {
    public static final String TABLENAME = "Message";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property Type = new Property(2, Integer.TYPE, "Type", false, Intents.WifiConnect.TYPE);
        public static final Property SubType = new Property(3, Integer.TYPE, "SubType", false, "SUB_TYPE");
        public static final Property StartDayMonday = new Property(4, String.class, "StartDayMonday", false, "START_DAY_MONDAY");
        public static final Property StartDaySaturday = new Property(5, String.class, "StartDaySaturday", false, "START_DAY_SATURDAY");
        public static final Property StartDaySunday = new Property(6, String.class, "StartDaySunday", false, "START_DAY_SUNDAY");
        public static final Property MondayGenerate = new Property(7, Boolean.TYPE, "MondayGenerate", false, "MONDAY_GENERATE");
        public static final Property SaturdayGenerate = new Property(8, Boolean.TYPE, "SaturdayGenerate", false, "SATURDAY_GENERATE");
        public static final Property SundayGenerate = new Property(9, Boolean.TYPE, "SundayGenerate", false, "SUNDAY_GENERATE");
        public static final Property HasRead = new Property(10, Boolean.TYPE, "HasRead", false, "HAS_READ");
        public static final Property CreateTime = new Property(11, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property DescType = new Property(12, Integer.TYPE, "DescType", false, "DESC_TYPE");
        public static final Property UpdateTime = new Property(13, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
        public static final Property HasUpload = new Property(14, Boolean.TYPE, "HasUpload", false, "HAS_UPLOAD");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public MessageEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MessageEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"Message\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"TYPE\" INTEGER NOT NULL ,\"SUB_TYPE\" INTEGER NOT NULL ,\"START_DAY_MONDAY\" TEXT,\"START_DAY_SATURDAY\" TEXT,\"START_DAY_SUNDAY\" TEXT,\"MONDAY_GENERATE\" INTEGER NOT NULL ,\"SATURDAY_GENERATE\" INTEGER NOT NULL ,\"SUNDAY_GENERATE\" INTEGER NOT NULL ,\"HAS_READ\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"DESC_TYPE\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL ,\"HAS_UPLOAD\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"Message\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, MessageEntity messageEntity) {
        databaseStatement.clearBindings();
        Long id = messageEntity.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, messageEntity.getUserId());
        databaseStatement.bindLong(3, messageEntity.getType());
        databaseStatement.bindLong(4, messageEntity.getSubType());
        String startDayMonday = messageEntity.getStartDayMonday();
        if (startDayMonday != null) {
            databaseStatement.bindString(5, startDayMonday);
        }
        String startDaySaturday = messageEntity.getStartDaySaturday();
        if (startDaySaturday != null) {
            databaseStatement.bindString(6, startDaySaturday);
        }
        String startDaySunday = messageEntity.getStartDaySunday();
        if (startDaySunday != null) {
            databaseStatement.bindString(7, startDaySunday);
        }
        databaseStatement.bindLong(8, messageEntity.getMondayGenerate() ? 1L : 0L);
        databaseStatement.bindLong(9, messageEntity.getSaturdayGenerate() ? 1L : 0L);
        databaseStatement.bindLong(10, messageEntity.getSundayGenerate() ? 1L : 0L);
        databaseStatement.bindLong(11, messageEntity.getHasRead() ? 1L : 0L);
        databaseStatement.bindLong(12, messageEntity.getCreateTime());
        databaseStatement.bindLong(13, messageEntity.getDescType());
        databaseStatement.bindLong(14, messageEntity.getUpdateTime());
        databaseStatement.bindLong(15, messageEntity.getHasUpload() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, MessageEntity messageEntity) {
        sQLiteStatement.clearBindings();
        Long id = messageEntity.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, messageEntity.getUserId());
        sQLiteStatement.bindLong(3, messageEntity.getType());
        sQLiteStatement.bindLong(4, messageEntity.getSubType());
        String startDayMonday = messageEntity.getStartDayMonday();
        if (startDayMonday != null) {
            sQLiteStatement.bindString(5, startDayMonday);
        }
        String startDaySaturday = messageEntity.getStartDaySaturday();
        if (startDaySaturday != null) {
            sQLiteStatement.bindString(6, startDaySaturday);
        }
        String startDaySunday = messageEntity.getStartDaySunday();
        if (startDaySunday != null) {
            sQLiteStatement.bindString(7, startDaySunday);
        }
        sQLiteStatement.bindLong(8, messageEntity.getMondayGenerate() ? 1L : 0L);
        sQLiteStatement.bindLong(9, messageEntity.getSaturdayGenerate() ? 1L : 0L);
        sQLiteStatement.bindLong(10, messageEntity.getSundayGenerate() ? 1L : 0L);
        sQLiteStatement.bindLong(11, messageEntity.getHasRead() ? 1L : 0L);
        sQLiteStatement.bindLong(12, messageEntity.getCreateTime());
        sQLiteStatement.bindLong(13, messageEntity.getDescType());
        sQLiteStatement.bindLong(14, messageEntity.getUpdateTime());
        sQLiteStatement.bindLong(15, messageEntity.getHasUpload() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(MessageEntity messageEntity) {
        super.attachEntity(messageEntity);
        messageEntity.__setDaoSession(this.daoSession);
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
    public MessageEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = i + 4;
        String string = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        String string2 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        return new MessageEntity(lValueOf, j, i3, i4, string, string2, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.getShort(i + 7) != 0, cursor.getShort(i + 8) != 0, cursor.getShort(i + 9) != 0, cursor.getShort(i + 10) != 0, cursor.getLong(i + 11), cursor.getInt(i + 12), cursor.getLong(i + 13), cursor.getShort(i + 14) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, MessageEntity messageEntity, int i) {
        int i2 = i + 0;
        messageEntity.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        messageEntity.setUserId(cursor.getLong(i + 1));
        messageEntity.setType(cursor.getInt(i + 2));
        messageEntity.setSubType(cursor.getInt(i + 3));
        int i3 = i + 4;
        messageEntity.setStartDayMonday(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        messageEntity.setStartDaySaturday(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        messageEntity.setStartDaySunday(cursor.isNull(i5) ? null : cursor.getString(i5));
        messageEntity.setMondayGenerate(cursor.getShort(i + 7) != 0);
        messageEntity.setSaturdayGenerate(cursor.getShort(i + 8) != 0);
        messageEntity.setSundayGenerate(cursor.getShort(i + 9) != 0);
        messageEntity.setHasRead(cursor.getShort(i + 10) != 0);
        messageEntity.setCreateTime(cursor.getLong(i + 11));
        messageEntity.setDescType(cursor.getInt(i + 12));
        messageEntity.setUpdateTime(cursor.getLong(i + 13));
        messageEntity.setHasUpload(cursor.getShort(i + 14) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(MessageEntity messageEntity, long j) {
        messageEntity.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(MessageEntity messageEntity) {
        if (messageEntity != null) {
            return messageEntity.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(MessageEntity messageEntity) {
        return messageEntity.getId() != null;
    }
}