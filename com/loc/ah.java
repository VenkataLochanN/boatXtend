package com.loc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: compiled from: DB.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ah extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f4733b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f4734c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ad f4735a;

    public ah(Context context, String str, ad adVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.f4735a = adVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f4735a.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}