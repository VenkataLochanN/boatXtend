package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: AndroidAssets.java */
/* JADX INFO: loaded from: classes.dex */
public class dr {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static dr f665b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f666a;

    public static String a(String str) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        InputStream inputStreamB = f665b.b(str);
        if (inputStreamB == null) {
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStreamB, "utf-8"));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            sb.append('\n');
            return sb.toString();
        }
        bufferedReader.close();
        return sb.toString();
    }

    public static void a(Context context) {
        f665b = new dr(context);
    }

    private dr(Context context) {
        this.f666a = context;
    }

    public InputStream b(String str) {
        try {
            return this.f666a.getAssets().open(str);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}