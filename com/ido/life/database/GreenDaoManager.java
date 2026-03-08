package com.ido.life.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.VeryFitApp;
import com.ido.life.database.model.DaoMaster;
import com.ido.life.database.model.DaoSession;
import com.ido.life.database.upgrade.StandardDatabaseWraper;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.EncryptedDatabase;
import org.greenrobot.greendao.query.QueryBuilder;

/* JADX INFO: loaded from: classes2.dex */
public class GreenDaoManager {
    public static final String DB_NAME = "very_fit.db";
    private static final String PASSWORD = "MYdJ9BaaET5KcObzpEGmEbg27BdZ4n";
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static volatile GreenDaoManager mGreenDaoManager;
    private Context context;
    private DaoMaster.DevOpenHelper mHelper;

    public static GreenDaoManager getInstance() {
        if (mGreenDaoManager == null) {
            synchronized (GreenDaoManager.class) {
                mGreenDaoManager = new GreenDaoManager();
            }
        }
        return mGreenDaoManager;
    }

    public void init(Context context) {
        this.context = context;
    }

    private DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            this.mHelper = new DaoMaster.DevOpenHelper(this.context, DB_NAME, null) { // from class: com.ido.life.database.GreenDaoManager.1
                @Override // org.greenrobot.greendao.database.DatabaseOpenHelper, android.database.sqlite.SQLiteOpenHelper
                public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                    if (GreenDaoManager.this.processDataBaseUpgrade(new StandardDatabaseWraper(sQLiteDatabase), i, i2)) {
                        return;
                    }
                    super.onUpgrade(sQLiteDatabase, i, i2);
                }

                @Override // com.ido.life.database.model.DaoMaster.DevOpenHelper, org.greenrobot.greendao.database.DatabaseOpenHelper
                public void onUpgrade(Database database, int i, int i2) {
                    if ((database instanceof EncryptedDatabase) && GreenDaoManager.this.processDataBaseUpgrade(new StandardDatabaseWraper((EncryptedDatabase) database), i, i2)) {
                        return;
                    }
                    super.onUpgrade(database, i, i2);
                }
            };
            mDaoMaster = new DaoMaster(getWritableDatabase());
            CommonLogUtil.printAndSave("very_fit.db 数据库加密");
        }
        return mDaoMaster;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processDataBaseUpgrade(StandardDatabaseWraper standardDatabaseWraper, int i, int i2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDataBaseUpgradePath(), "开始处理数据库升级oldVersion=" + i + ",newVersion=" + i2);
        try {
            return DatabaseUpgradeUtil.upgradeDatabase(standardDatabaseWraper, i, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private Database getWritableDatabase() {
        try {
            return this.mHelper.getEncryptedWritableDb(PASSWORD);
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                CommonLogUtil.printAndSave("very_fit.db 打开数据库抛异常了，尝试加密后再打开");
                DBEncrypt.getInstences().encrypt(this.context, DB_NAME, PASSWORD);
                return this.mHelper.getEncryptedWritableDb(PASSWORD);
            } catch (Exception e3) {
                e3.printStackTrace();
                this.context.getDatabasePath(DB_NAME).delete();
                VeryFitApp.getApp().logout();
                CommonLogUtil.printAndSave("very_fit.db 打开数据库抛异常了，秘钥不对，删除数据库重建,并且重新登录");
                this.mHelper = new DaoMaster.DevOpenHelper(this.context, DB_NAME, null);
                return this.mHelper.getEncryptedWritableDb(PASSWORD);
            }
        }
    }

    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    public void setDebug(boolean z) {
        QueryBuilder.LOG_SQL = z;
        QueryBuilder.LOG_VALUES = z;
    }

    public void closeDataBase() {
        closeHelper();
        closeDaoSession();
    }

    public void closeDaoSession() {
        DaoSession daoSession = mDaoSession;
        if (daoSession != null) {
            daoSession.clear();
            mDaoSession = null;
        }
    }

    public void closeHelper() {
        DaoMaster.DevOpenHelper devOpenHelper = this.mHelper;
        if (devOpenHelper != null) {
            devOpenHelper.close();
            this.mHelper = null;
        }
    }
}