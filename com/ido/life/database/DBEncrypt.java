package com.ido.life.database;

import android.content.Context;
import java.io.File;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
public class DBEncrypt {
    private static volatile DBEncrypt nInstance;
    private Boolean isOpen = true;

    public static DBEncrypt getInstences() {
        if (nInstance == null) {
            synchronized (DBEncrypt.class) {
                if (nInstance == null) {
                    nInstance = new DBEncrypt();
                }
            }
        }
        return nInstance;
    }

    public void encrypt(Context context, String str, String str2) {
        File databasePath = context.getDatabasePath(str);
        if (databasePath.exists() && this.isOpen.booleanValue()) {
            try {
                File fileCreateTempFile = File.createTempFile("sqlcipherutils", "tmp", context.getCacheDir());
                SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(databasePath.getAbsolutePath(), "", (SQLiteDatabase.CursorFactory) null, 0);
                sQLiteDatabaseOpenDatabase.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s';", fileCreateTempFile.getAbsolutePath(), str2));
                sQLiteDatabaseOpenDatabase.rawExecSQL("SELECT sqlcipher_export('encrypted')");
                sQLiteDatabaseOpenDatabase.rawExecSQL("DETACH DATABASE encrypted;");
                int version = sQLiteDatabaseOpenDatabase.getVersion();
                sQLiteDatabaseOpenDatabase.close();
                SQLiteDatabase sQLiteDatabaseOpenDatabase2 = SQLiteDatabase.openDatabase(fileCreateTempFile.getAbsolutePath(), str2, (SQLiteDatabase.CursorFactory) null, 0);
                sQLiteDatabaseOpenDatabase2.setVersion(version);
                sQLiteDatabaseOpenDatabase2.close();
                databasePath.delete();
                fileCreateTempFile.renameTo(databasePath);
                this.isOpen = false;
            } catch (Exception unused) {
                this.isOpen = false;
            }
        }
    }
}