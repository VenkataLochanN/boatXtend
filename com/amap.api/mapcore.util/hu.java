package com.amap.api.mapcore.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: compiled from: DB.java */
/* JADX INFO: loaded from: classes.dex */
public class hu extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f1288b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f1289c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private hq f1290a;

    public hu(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, hq hqVar) {
        super(context, str, cursorFactory, i);
        this.f1290a = hqVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f1290a.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f1290a.a(sQLiteDatabase, i, i2);
    }
}