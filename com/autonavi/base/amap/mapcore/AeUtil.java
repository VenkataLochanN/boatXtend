package com.autonavi.base.amap.mapcore;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.amap.api.mapcore.util.er;
import com.amap.api.mapcore.util.ex;
import com.amap.api.mapcore.util.ey;
import com.amap.api.mapcore.util.gh;
import com.amap.api.mapcore.util.gs;
import com.amap.api.mapcore.util.hn;
import com.amap.api.mapcore.util.hv;
import com.amap.api.mapcore.util.hx;
import com.amap.api.mapcore.util.jr;
import com.amap.api.mapcore.util.js;
import com.amap.api.mapcore.util.jv;
import com.autonavi.amap.mapcore.MsgProcessor;
import com.autonavi.base.ae.gmap.GLMapEngine;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class AeUtil {
    public static final String CONFIGNAME = "GNaviConfig.xml";
    public static final boolean IS_AE = true;
    public static final String RESZIPNAME = "res.zip";
    public static final String ROOTPATH = "/amap/";
    public static final String ROOT_DATA_PATH_NAME = "data_v6";
    public static final String ROOT_DATA_PATH_OLD_NAME = "data";
    public static final String SO_FILENAME = "AMapSDK_MAP_v7_6_0";
    public static final String SO_FILENAME_NAVI = "AMapSDK_NAVI_v6_5_0";
    public static boolean isNaviSoLoaded = false;

    public static boolean loadLib(Context context) {
        try {
            String str = SO_FILENAME;
            if (jv.f1490a) {
                str = jv.f1491b;
                if (isNaviSoLoaded) {
                    return false;
                }
            }
            System.loadLibrary(str);
            if (jv.f1490a) {
                isNaviSoLoaded = true;
            }
            return true;
        } catch (Throwable th) {
            hn.c(th, "AeUtil", "loadLib");
            er.a(th);
            ey.b(ex.f796c, "load so failed " + th.getMessage());
            return false;
        }
    }

    public static void initCrashHandle(Context context, boolean z) {
        gs gsVarE;
        hx.a().a(context);
        if (!hv.a(er.e()).a(context) || (gsVarE = er.e()) == null) {
            return;
        }
        MsgProcessor.nativeInitInfo(context, hv.a(gsVarE).b(context), gsVarE.a(), gsVarE.b(), gsVarE.c(), gsVarE.g());
    }

    public static GLMapEngine.InitParam initResource(final Context context) {
        final String mapBaseStorage = FileUtil.getMapBaseStorage(context);
        String str = mapBaseStorage + "/" + ROOT_DATA_PATH_NAME + "/";
        File file = new File(mapBaseStorage);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                jr.a(1).a(new js() { // from class: com.autonavi.base.amap.mapcore.AeUtil.1
                    @Override // com.amap.api.mapcore.util.js
                    public void runTask() {
                        AeUtil.loadEngineRes(mapBaseStorage, context);
                    }
                });
            } catch (gh e2) {
                e2.printStackTrace();
            }
        } else {
            loadEngineRes(mapBaseStorage, context);
        }
        GLMapEngine.InitParam initParam = new GLMapEngine.InitParam();
        byte[] fileContentsFromAssets = FileUtil.readFileContentsFromAssets(context, "ae/GNaviConfig.xml");
        initParam.mRootPath = mapBaseStorage;
        if (fileContentsFromAssets != null) {
            try {
                initParam.mConfigContent = new String(fileContentsFromAssets, "utf-8");
                if (!initParam.mConfigContent.contains(ROOT_DATA_PATH_NAME)) {
                    throw new Exception("GNaviConfig.xml 和数据目录data_v6不匹配");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        initParam.mOfflineDataPath = str + "/map/";
        initParam.mP3dCrossPath = str;
        return initParam;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0035 -> B:35:0x0060). Please report as a decompilation issue!!! */
    public static void loadEngineRes(String str, Context context) {
        File file = new File(str, "res");
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        if (checkEngineRes(file)) {
            return;
        }
        ?? Open = 0;
        Open = 0;
        Open = 0;
        try {
            try {
                try {
                    try {
                        Open = context.getAssets().open("ae/res.zip");
                        FileUtil.decompress(Open, file.getAbsolutePath());
                        Open = Open;
                        if (Open != 0) {
                            Open.close();
                            Open = Open;
                        }
                    } catch (Throwable th) {
                        if (Open != 0) {
                            try {
                                Open.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    er.a(e3);
                    if (Open != 0) {
                        Open.close();
                        Open = Open;
                    }
                }
            } catch (OutOfMemoryError e4) {
                e4.printStackTrace();
                er.a(e4);
                if (Open != 0) {
                    Open.close();
                    Open = Open;
                }
            }
        } catch (IOException e5) {
            e5.printStackTrace();
            Open = e5;
        }
    }

    private static boolean checkEngineRes(File file) {
        File[] fileArrListFiles = file.listFiles();
        return fileArrListFiles != null && fileArrListFiles.length > 0;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0044 -> B:59:0x0062). Please report as a decompilation issue!!! */
    public static void readAssetsFileAndSave(String str, String str2, Context context) {
        InputStream inputStreamOpen;
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                inputStreamOpen = context.getAssets().open(str);
                try {
                    File file = new File(str2);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStreamOpen = null;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStreamOpen.read(bArr, 0, 1024);
                    if (i <= 0) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                fileOutputStream.close();
            } catch (Throwable th3) {
                fileOutputStream2 = fileOutputStream;
                th = th3;
                try {
                    th.printStackTrace();
                    if (inputStreamOpen != null) {
                        try {
                            inputStreamOpen.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileOutputStream2 == null) {
                    } else {
                        fileOutputStream2.close();
                    }
                } finally {
                }
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}