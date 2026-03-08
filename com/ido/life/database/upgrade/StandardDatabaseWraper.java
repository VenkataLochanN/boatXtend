package com.ido.life.database.upgrade;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.List;
import org.greenrobot.greendao.database.EncryptedDatabase;

/* JADX INFO: loaded from: classes2.dex */
public class StandardDatabaseWraper implements StandardDatabaseInterface {
    private EncryptedDatabase mEncryptedDatabase;
    private SQLiteDatabase mSQLiteDatabase;

    public StandardDatabaseWraper(EncryptedDatabase encryptedDatabase) {
        this.mEncryptedDatabase = encryptedDatabase;
    }

    public StandardDatabaseWraper(SQLiteDatabase sQLiteDatabase) {
        this.mSQLiteDatabase = sQLiteDatabase;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public SQLiteDatabase getSQLiteDatabase() {
        return this.mSQLiteDatabase;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public EncryptedDatabase getEncryptedDatabase() {
        return this.mEncryptedDatabase;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public Cursor query(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            return encryptedDatabase.getSQLiteDatabase().query(str, null, null, null, null, null, null);
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase != null) {
            return sQLiteDatabase.query(str, null, null, null, null, null, null);
        }
        return null;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            return encryptedDatabase.getSQLiteDatabase().query(str, strArr, str2, strArr2, str3, str4, str5);
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase != null) {
            return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5);
        }
        return null;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public boolean dropTable(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"");
        sb.append(str);
        sb.append("\"");
        String string = sb.toString();
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            encryptedDatabase.getSQLiteDatabase().execSQL(string);
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase == null) {
            return true;
        }
        sQLiteDatabase.execSQL(string);
        return true;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public boolean execSql(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            encryptedDatabase.getSQLiteDatabase().execSQL(str);
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase == null) {
            return true;
        }
        sQLiteDatabase.execSQL(str);
        return true;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public void beginTransaction() {
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            encryptedDatabase.beginTransaction();
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.beginTransaction();
        }
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public void setTransactionSuccessful() {
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            encryptedDatabase.setTransactionSuccessful();
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.setTransactionSuccessful();
        }
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public void endTransaction() {
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            encryptedDatabase.endTransaction();
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public long insert(String str, String str2, ContentValues contentValues) {
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            return encryptedDatabase.getSQLiteDatabase().insert(str, str2, contentValues);
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase != null) {
            return sQLiteDatabase.insert(str, str2, contentValues);
        }
        return -1L;
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public void insert(String str, String str2, List<ContentValues> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (ContentValues contentValues : list) {
            EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
            if (encryptedDatabase != null) {
                encryptedDatabase.getSQLiteDatabase().insert(str, str2, contentValues);
            }
            SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.insert(str, str2, contentValues);
            }
        }
    }

    @Override // com.ido.life.database.upgrade.StandardDatabaseInterface
    public int delete(String str, String str2, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        EncryptedDatabase encryptedDatabase = this.mEncryptedDatabase;
        if (encryptedDatabase != null) {
            return encryptedDatabase.getSQLiteDatabase().delete(str, str2, strArr);
        }
        SQLiteDatabase sQLiteDatabase = this.mSQLiteDatabase;
        if (sQLiteDatabase != null) {
            return sQLiteDatabase.delete(str, str2, strArr);
        }
        return 0;
    }
}