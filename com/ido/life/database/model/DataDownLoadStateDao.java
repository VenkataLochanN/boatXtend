package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.life.syncdownload.DataDownLoadParamsConvert;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class DataDownLoadStateDao extends AbstractDao<DataDownLoadState, Long> {
    public static final String TABLENAME = "DATA_DOWN_LOAD_STATE";
    private final DataDownLoadParamsConvert ParamsMapConverter;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property DataName = new Property(2, String.class, "DataName", false, "DATA_NAME");
        public static final Property DownloadState = new Property(3, Integer.TYPE, "DownloadState", false, "DOWNLOAD_STATE");
        public static final Property TaskId = new Property(4, Integer.TYPE, "TaskId", false, "TASK_ID");
        public static final Property ParamsMap = new Property(5, String.class, "ParamsMap", false, "PARAMS_MAP");
        public static final Property Url = new Property(6, String.class, "Url", false, "URL");
        public static final Property DownloadTimeStamp = new Property(7, Long.TYPE, "DownloadTimeStamp", false, "DOWNLOAD_TIME_STAMP");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public DataDownLoadStateDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.ParamsMapConverter = new DataDownLoadParamsConvert();
    }

    public DataDownLoadStateDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.ParamsMapConverter = new DataDownLoadParamsConvert();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"DATA_DOWN_LOAD_STATE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATA_NAME\" TEXT,\"DOWNLOAD_STATE\" INTEGER NOT NULL ,\"TASK_ID\" INTEGER NOT NULL ,\"PARAMS_MAP\" TEXT,\"URL\" TEXT,\"DOWNLOAD_TIME_STAMP\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DATA_DOWN_LOAD_STATE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DataDownLoadState dataDownLoadState) {
        databaseStatement.clearBindings();
        Long id = dataDownLoadState.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, dataDownLoadState.getUserId());
        String dataName = dataDownLoadState.getDataName();
        if (dataName != null) {
            databaseStatement.bindString(3, dataName);
        }
        databaseStatement.bindLong(4, dataDownLoadState.getDownloadState());
        databaseStatement.bindLong(5, dataDownLoadState.getTaskId());
        Map<String, String> paramsMap = dataDownLoadState.getParamsMap();
        if (paramsMap != null) {
            databaseStatement.bindString(6, this.ParamsMapConverter.convertToDatabaseValue2(paramsMap));
        }
        String url = dataDownLoadState.getUrl();
        if (url != null) {
            databaseStatement.bindString(7, url);
        }
        databaseStatement.bindLong(8, dataDownLoadState.getDownloadTimeStamp());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DataDownLoadState dataDownLoadState) {
        sQLiteStatement.clearBindings();
        Long id = dataDownLoadState.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, dataDownLoadState.getUserId());
        String dataName = dataDownLoadState.getDataName();
        if (dataName != null) {
            sQLiteStatement.bindString(3, dataName);
        }
        sQLiteStatement.bindLong(4, dataDownLoadState.getDownloadState());
        sQLiteStatement.bindLong(5, dataDownLoadState.getTaskId());
        Map<String, String> paramsMap = dataDownLoadState.getParamsMap();
        if (paramsMap != null) {
            sQLiteStatement.bindString(6, this.ParamsMapConverter.convertToDatabaseValue2(paramsMap));
        }
        String url = dataDownLoadState.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(7, url);
        }
        sQLiteStatement.bindLong(8, dataDownLoadState.getDownloadTimeStamp());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(DataDownLoadState dataDownLoadState) {
        super.attachEntity(dataDownLoadState);
        dataDownLoadState.__setDaoSession(this.daoSession);
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
    public DataDownLoadState readEntity(Cursor cursor, int i) {
        DataDownLoadState dataDownLoadState = new DataDownLoadState();
        readEntity(cursor, dataDownLoadState, i);
        return dataDownLoadState;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DataDownLoadState dataDownLoadState, int i) {
        int i2 = i + 0;
        dataDownLoadState.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        dataDownLoadState.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        dataDownLoadState.setDataName(cursor.isNull(i3) ? null : cursor.getString(i3));
        dataDownLoadState.setDownloadState(cursor.getInt(i + 3));
        dataDownLoadState.setTaskId(cursor.getInt(i + 4));
        int i4 = i + 5;
        dataDownLoadState.setParamsMap(cursor.isNull(i4) ? null : this.ParamsMapConverter.convertToEntityProperty(cursor.getString(i4)));
        int i5 = i + 6;
        dataDownLoadState.setUrl(cursor.isNull(i5) ? null : cursor.getString(i5));
        dataDownLoadState.setDownloadTimeStamp(cursor.getLong(i + 7));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(DataDownLoadState dataDownLoadState, long j) {
        dataDownLoadState.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(DataDownLoadState dataDownLoadState) {
        if (dataDownLoadState != null) {
            return dataDownLoadState.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DataDownLoadState dataDownLoadState) {
        return dataDownLoadState.getId() != null;
    }
}