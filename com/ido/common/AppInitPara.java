package com.ido.common;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AppInitPara {
    private Context appContext;
    private String normalLogFileDir = "";
    private int logFileSaveDays = 2;
    private List<String> mouldLogDirPathList = new ArrayList();

    public Context getAppContext() {
        return this.appContext;
    }

    public AppInitPara setAppContext(Context context) {
        this.appContext = context;
        return this;
    }

    public String getNormalLogFileDir() {
        return this.normalLogFileDir;
    }

    public AppInitPara setNormalLogFileDir(String str) {
        this.normalLogFileDir = str;
        return this;
    }

    public int getLogFileSaveDays() {
        return this.logFileSaveDays;
    }

    public AppInitPara setLogFileSaveDays(int i) {
        this.logFileSaveDays = i;
        return this;
    }

    public List<String> getMouldLogDirPathList() {
        return this.mouldLogDirPathList;
    }

    public AppInitPara setMouldLogDirPathList(List<String> list) {
        this.mouldLogDirPathList.clear();
        this.mouldLogDirPathList.addAll(list);
        return this;
    }
}