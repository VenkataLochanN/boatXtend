package com.ido.life.database.upgrade;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import org.greenrobot.greendao.database.EncryptedDatabase;

/* JADX INFO: loaded from: classes2.dex */
public interface StandardDatabaseInterface {
    void beginTransaction();

    int delete(String str, String str2, String[] strArr);

    boolean dropTable(String str, boolean z);

    void endTransaction();

    boolean execSql(String str);

    EncryptedDatabase getEncryptedDatabase();

    SQLiteDatabase getSQLiteDatabase();

    long insert(String str, String str2, ContentValues contentValues);

    void insert(String str, String str2, List<ContentValues> list);

    Cursor query(String str);

    Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5);

    void setTransactionSuccessful();
}