package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.life.constants.Constants;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class AgreementPrivacyVersionDao extends AbstractDao<AgreementPrivacyVersion, Long> {
    public static final String TABLENAME = "AGREEMENT_PRIVACY_VERSION";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property USER_AGREEMENT = new Property(2, Long.class, Constants.USER_AGREEMENT, false, "USER__AGREEMENT");
        public static final Property PRIVACY_POLICY = new Property(3, Long.class, Constants.PRIVACY_POLICY, false, "PRIVACY__POLICY");
        public static final Property APP_HELP = new Property(4, Long.class, Constants.APP_HELP, false, "APP__HELP");
        public static final Property LINK_HELP = new Property(5, Long.class, Constants.LINK_HELP, false, "LINK__HELP");
        public static final Property HELP_EXPLAIN = new Property(6, Long.class, Constants.HELP_EXPLAIN, false, "HELP__EXPLAIN");
        public static final Property CreateTime = new Property(7, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(8, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public AgreementPrivacyVersionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public AgreementPrivacyVersionDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"AGREEMENT_PRIVACY_VERSION\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"USER__AGREEMENT\" INTEGER,\"PRIVACY__POLICY\" INTEGER,\"APP__HELP\" INTEGER,\"LINK__HELP\" INTEGER,\"HELP__EXPLAIN\" INTEGER,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"AGREEMENT_PRIVACY_VERSION\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, AgreementPrivacyVersion agreementPrivacyVersion) {
        databaseStatement.clearBindings();
        Long id = agreementPrivacyVersion.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, agreementPrivacyVersion.getUserId());
        Long user_agreement = agreementPrivacyVersion.getUSER_AGREEMENT();
        if (user_agreement != null) {
            databaseStatement.bindLong(3, user_agreement.longValue());
        }
        Long privacy_policy = agreementPrivacyVersion.getPRIVACY_POLICY();
        if (privacy_policy != null) {
            databaseStatement.bindLong(4, privacy_policy.longValue());
        }
        Long app_help = agreementPrivacyVersion.getAPP_HELP();
        if (app_help != null) {
            databaseStatement.bindLong(5, app_help.longValue());
        }
        Long link_help = agreementPrivacyVersion.getLINK_HELP();
        if (link_help != null) {
            databaseStatement.bindLong(6, link_help.longValue());
        }
        Long help_explain = agreementPrivacyVersion.getHELP_EXPLAIN();
        if (help_explain != null) {
            databaseStatement.bindLong(7, help_explain.longValue());
        }
        databaseStatement.bindLong(8, agreementPrivacyVersion.getCreateTime());
        databaseStatement.bindLong(9, agreementPrivacyVersion.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, AgreementPrivacyVersion agreementPrivacyVersion) {
        sQLiteStatement.clearBindings();
        Long id = agreementPrivacyVersion.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, agreementPrivacyVersion.getUserId());
        Long user_agreement = agreementPrivacyVersion.getUSER_AGREEMENT();
        if (user_agreement != null) {
            sQLiteStatement.bindLong(3, user_agreement.longValue());
        }
        Long privacy_policy = agreementPrivacyVersion.getPRIVACY_POLICY();
        if (privacy_policy != null) {
            sQLiteStatement.bindLong(4, privacy_policy.longValue());
        }
        Long app_help = agreementPrivacyVersion.getAPP_HELP();
        if (app_help != null) {
            sQLiteStatement.bindLong(5, app_help.longValue());
        }
        Long link_help = agreementPrivacyVersion.getLINK_HELP();
        if (link_help != null) {
            sQLiteStatement.bindLong(6, link_help.longValue());
        }
        Long help_explain = agreementPrivacyVersion.getHELP_EXPLAIN();
        if (help_explain != null) {
            sQLiteStatement.bindLong(7, help_explain.longValue());
        }
        sQLiteStatement.bindLong(8, agreementPrivacyVersion.getCreateTime());
        sQLiteStatement.bindLong(9, agreementPrivacyVersion.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(AgreementPrivacyVersion agreementPrivacyVersion) {
        super.attachEntity(agreementPrivacyVersion);
        agreementPrivacyVersion.__setDaoSession(this.daoSession);
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
    public AgreementPrivacyVersion readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        Long lValueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = i + 3;
        Long lValueOf3 = cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4));
        int i5 = i + 4;
        Long lValueOf4 = cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5));
        int i6 = i + 5;
        Long lValueOf5 = cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6));
        int i7 = i + 6;
        return new AgreementPrivacyVersion(lValueOf, j, lValueOf2, lValueOf3, lValueOf4, lValueOf5, cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)), cursor.getLong(i + 7), cursor.getLong(i + 8));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, AgreementPrivacyVersion agreementPrivacyVersion, int i) {
        int i2 = i + 0;
        agreementPrivacyVersion.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        agreementPrivacyVersion.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        agreementPrivacyVersion.setUSER_AGREEMENT(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 3;
        agreementPrivacyVersion.setPRIVACY_POLICY(cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
        int i5 = i + 4;
        agreementPrivacyVersion.setAPP_HELP(cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)));
        int i6 = i + 5;
        agreementPrivacyVersion.setLINK_HELP(cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
        int i7 = i + 6;
        agreementPrivacyVersion.setHELP_EXPLAIN(cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
        agreementPrivacyVersion.setCreateTime(cursor.getLong(i + 7));
        agreementPrivacyVersion.setUpdateTime(cursor.getLong(i + 8));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(AgreementPrivacyVersion agreementPrivacyVersion, long j) {
        agreementPrivacyVersion.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(AgreementPrivacyVersion agreementPrivacyVersion) {
        if (agreementPrivacyVersion != null) {
            return agreementPrivacyVersion.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(AgreementPrivacyVersion agreementPrivacyVersion) {
        return agreementPrivacyVersion.getId() != null;
    }
}