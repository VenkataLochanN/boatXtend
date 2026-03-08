package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.graphics.PointF;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class GpsColorsDao extends AbstractDao<GpsColors, Long> {
    public static final String TABLENAME = "GPS_COLORS";
    private final ConvertListColorItem colorsConverter;
    private DaoSession daoSession;
    private final ConvertListPointItem pointFListConverter;
    private final ConvertListItem positionConverter;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property DateTime = new Property(1, String.class, "dateTime", false, "DATE_TIME");
        public static final Property Colors = new Property(2, String.class, "colors", false, "COLORS");
        public static final Property Position = new Property(3, String.class, "position", false, "POSITION");
        public static final Property PointFList = new Property(4, String.class, "pointFList", false, "POINT_FLIST");
        public static final Property UserId = new Property(5, Long.TYPE, "userId", false, "USER_ID");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public GpsColorsDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.colorsConverter = new ConvertListColorItem();
        this.positionConverter = new ConvertListItem();
        this.pointFListConverter = new ConvertListPointItem();
    }

    public GpsColorsDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.colorsConverter = new ConvertListColorItem();
        this.positionConverter = new ConvertListItem();
        this.pointFListConverter = new ConvertListPointItem();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"GPS_COLORS\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DATE_TIME\" TEXT,\"COLORS\" TEXT,\"POSITION\" TEXT,\"POINT_FLIST\" TEXT,\"USER_ID\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"GPS_COLORS\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, GpsColors gpsColors) {
        databaseStatement.clearBindings();
        Long id = gpsColors.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String dateTime = gpsColors.getDateTime();
        if (dateTime != null) {
            databaseStatement.bindString(2, dateTime);
        }
        List<Integer> colors = gpsColors.getColors();
        if (colors != null) {
            databaseStatement.bindString(3, this.colorsConverter.convertToDatabaseValue(colors));
        }
        List<Float> position = gpsColors.getPosition();
        if (position != null) {
            databaseStatement.bindString(4, this.positionConverter.convertToDatabaseValue(position));
        }
        List<PointF> pointFList = gpsColors.getPointFList();
        if (pointFList != null) {
            databaseStatement.bindString(5, this.pointFListConverter.convertToDatabaseValue(pointFList));
        }
        databaseStatement.bindLong(6, gpsColors.getUserId());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, GpsColors gpsColors) {
        sQLiteStatement.clearBindings();
        Long id = gpsColors.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String dateTime = gpsColors.getDateTime();
        if (dateTime != null) {
            sQLiteStatement.bindString(2, dateTime);
        }
        List<Integer> colors = gpsColors.getColors();
        if (colors != null) {
            sQLiteStatement.bindString(3, this.colorsConverter.convertToDatabaseValue(colors));
        }
        List<Float> position = gpsColors.getPosition();
        if (position != null) {
            sQLiteStatement.bindString(4, this.positionConverter.convertToDatabaseValue(position));
        }
        List<PointF> pointFList = gpsColors.getPointFList();
        if (pointFList != null) {
            sQLiteStatement.bindString(5, this.pointFListConverter.convertToDatabaseValue(pointFList));
        }
        sQLiteStatement.bindLong(6, gpsColors.getUserId());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(GpsColors gpsColors) {
        super.attachEntity(gpsColors);
        gpsColors.__setDaoSession(this.daoSession);
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
    public GpsColors readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        List<Integer> listConvertToEntityProperty = cursor.isNull(i4) ? null : this.colorsConverter.convertToEntityProperty(cursor.getString(i4));
        int i5 = i + 3;
        List<Float> listConvertToEntityProperty2 = cursor.isNull(i5) ? null : this.positionConverter.convertToEntityProperty(cursor.getString(i5));
        int i6 = i + 4;
        return new GpsColors(lValueOf, string, listConvertToEntityProperty, listConvertToEntityProperty2, cursor.isNull(i6) ? null : this.pointFListConverter.convertToEntityProperty(cursor.getString(i6)), cursor.getLong(i + 5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, GpsColors gpsColors, int i) {
        int i2 = i + 0;
        gpsColors.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        gpsColors.setDateTime(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        gpsColors.setColors(cursor.isNull(i4) ? null : this.colorsConverter.convertToEntityProperty(cursor.getString(i4)));
        int i5 = i + 3;
        gpsColors.setPosition(cursor.isNull(i5) ? null : this.positionConverter.convertToEntityProperty(cursor.getString(i5)));
        int i6 = i + 4;
        gpsColors.setPointFList(cursor.isNull(i6) ? null : this.pointFListConverter.convertToEntityProperty(cursor.getString(i6)));
        gpsColors.setUserId(cursor.getLong(i + 5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(GpsColors gpsColors, long j) {
        gpsColors.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(GpsColors gpsColors) {
        if (gpsColors != null) {
            return gpsColors.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(GpsColors gpsColors) {
        return gpsColors.getId() != null;
    }
}