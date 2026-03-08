package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.amap.api.mapcore.util.dg;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.MyTrafficStyle;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.FileUtil;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.autonavi.base.amap.mapcore.tools.GLFileUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import kotlin.UByte;
import org.json.JSONObject;

/* JADX INFO: compiled from: AMapCustomStyleManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements dg.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IAMapDelegate f229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CustomMapStyleOptions f230b;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Context f236h;
    private boolean n;
    private boolean o;
    private dg s;
    private dg t;
    private boolean x;
    private a z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f231c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f232d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f233e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f234f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f235g = 1;
    private byte[] i = null;
    private byte[] j = null;
    private byte[] k = null;
    private byte[] l = null;
    private byte[] m = null;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private byte[] u = null;
    private byte[] v = null;
    private boolean w = false;
    private MyTrafficStyle y = new MyTrafficStyle();

    /* JADX INFO: compiled from: AMapCustomStyleManager.java */
    public interface a {
        void a(byte[] bArr);
    }

    public b(IAMapDelegate iAMapDelegate, Context context, boolean z) {
        this.n = false;
        this.o = false;
        this.x = false;
        this.f229a = iAMapDelegate;
        this.f236h = context;
        this.n = false;
        this.o = false;
        this.x = z;
    }

    public void a() {
        if (this.f230b == null || this.o) {
            return;
        }
        try {
            MapConfig mapConfig = this.f229a.getMapConfig();
            if (mapConfig == null) {
                return;
            }
            synchronized (this) {
                if (mapConfig.isHideLogoEnable() && this.f229a != null && this.f229a.getUiSettings() != null) {
                    if (this.f229a.getUiSettings().isLogoEnable()) {
                        if (this.f230b.isEnable()) {
                            if (this.q) {
                                this.f229a.getUiSettings().setLogoEnable(false);
                            }
                        } else {
                            this.f229a.getUiSettings().setLogoEnable(true);
                        }
                    } else if (!this.q) {
                        this.f229a.getUiSettings().setLogoEnable(true);
                    }
                }
                if (this.f231c) {
                    if (this.f230b.isEnable()) {
                        this.f229a.getGLMapEngine().setNativeMapModeAndStyle(this.f235g, 0, 0, 0, false, false, null);
                        mapConfig.setCustomStyleEnable(true);
                        this.f231c = false;
                    } else {
                        this.f229a.getGLMapEngine().setNativeMapModeAndStyle(this.f235g, mapConfig.getMapStyleMode(), mapConfig.getMapStyleTime(), mapConfig.getMapStyleState(), false, false, null);
                        this.q = false;
                        if (mapConfig.isCustomStyleEnable()) {
                            if (mapConfig.getMapStyleMode() == 0 && mapConfig.getMapStyleTime() == 0 && mapConfig.getMapStyleState() == 0) {
                                g();
                            }
                            h();
                            if (this.r) {
                                f();
                            }
                            mapConfig.setCustomStyleEnable(false);
                        }
                        this.f231c = false;
                        return;
                    }
                }
                if (this.f233e) {
                    String styleTexturePath = this.f230b.getStyleTexturePath();
                    if (this.f230b.getStyleTextureData() == null && !TextUtils.isEmpty(styleTexturePath)) {
                        this.f230b.setStyleTextureData(FileUtil.readFileContents(styleTexturePath));
                    }
                    if (this.f230b.getStyleTextureData() != null) {
                        this.w = true;
                        if (mapConfig.isProFunctionAuthEnable()) {
                            this.p = true;
                            this.f229a.getGLMapEngine().setCustomStyleTexture(this.f235g, this.f230b.getStyleTextureData());
                            mapConfig.setUseProFunction(true);
                        } else {
                            h();
                        }
                    } else {
                        h();
                        this.w = false;
                    }
                    this.f233e = false;
                }
                if (this.f232d) {
                    String styleDataPath = this.f230b.getStyleDataPath();
                    if (this.f230b.getStyleData() == null && !TextUtils.isEmpty(styleDataPath)) {
                        this.f230b.setStyleData(FileUtil.readFileContents(styleDataPath));
                    }
                    if (this.f230b.getStyleData() != null || this.u != null) {
                        if (this.m == null) {
                            this.m = a(FileUtil.readFileContentsFromAssets(this.f236h, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_STYLE_DATA_0_FOR_TEXTURE));
                        }
                        byte[] styleData = this.u != null ? this.u : this.f230b.getStyleData();
                        if (!c(styleData)) {
                            ds.a();
                        } else {
                            this.f229a.getGLMapEngine().setCustomStyleData(this.f235g, styleData, this.m);
                            this.q = true;
                            if (this.f229a != null) {
                                this.f229a.resetRenderTime();
                            }
                        }
                    } else if (this.q) {
                        this.f231c = true;
                        this.f230b.setEnable(false);
                    }
                    this.f232d = false;
                }
                if (this.f234f) {
                    String styleExtraPath = this.f230b.getStyleExtraPath();
                    if (this.f230b.getStyleExtraData() == null && !TextUtils.isEmpty(styleExtraPath)) {
                        this.f230b.setStyleExtraData(FileUtil.readFileContents(styleExtraPath));
                    }
                    if (this.f230b.getStyleExtraData() != null || this.v != null) {
                        byte[] styleExtraData = this.v != null ? this.v : this.f230b.getStyleExtraData();
                        if (styleExtraData != null) {
                            b(styleExtraData);
                            this.r = true;
                        }
                    }
                    this.f234f = false;
                }
            }
        } catch (Throwable th) {
            hn.c(th, "AMapCustomStyleManager", "updateStyle");
            er.a(th);
        }
    }

    private void b(byte[] bArr) {
        dk dkVarA;
        JSONObject jSONObjectOptJSONObject;
        if (bArr == null || (dkVarA = dn.a(bArr)) == null || dkVarA.a() == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(dkVarA.a());
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("bg");
            String strOptString = null;
            boolean zOptBoolean = true;
            if (jSONObjectOptJSONObject2 != null) {
                zOptBoolean = jSONObjectOptJSONObject2.optBoolean("visible", true);
                strOptString = jSONObjectOptJSONObject2.optString("lineColor", null);
            }
            a(strOptString, zOptBoolean);
            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("traffic");
            if (jSONObjectOptJSONObject3 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject3.optJSONObject("multiFillColors")) == null) {
                return;
            }
            int iA = dn.a(jSONObjectOptJSONObject.optString("smooth"));
            int iA2 = dn.a(jSONObjectOptJSONObject.optString("slow"));
            int iA3 = dn.a(jSONObjectOptJSONObject.optString("congested"));
            int iA4 = dn.a(jSONObjectOptJSONObject.optString("seriousCongested"));
            this.y.setSmoothColor(iA);
            this.y.setSlowColor(iA2);
            this.y.setCongestedColor(iA3);
            this.y.setSeriousCongestedColor(iA4);
            if (this.f229a == null || this.f229a.getGLMapEngine() == null) {
                return;
            }
            this.f229a.getGLMapEngine().setTrafficStyle(this.f235g, this.y.getSmoothColor(), this.y.getSlowColor(), this.y.getCongestedColor(), this.y.getSeriousCongestedColor(), true);
        } catch (Throwable th) {
            hn.c(th, "AMapCustomStyleManager", "setExtraStyle");
            er.a(th);
        }
    }

    private void a(String str, boolean z) {
        boolean z2;
        int iA = !TextUtils.isEmpty(str) ? dn.a(str) : Integer.MIN_VALUE;
        IAMapDelegate iAMapDelegate = this.f229a;
        if (iAMapDelegate == null || iAMapDelegate.getGLMapEngine() == null) {
            return;
        }
        if (this.k == null) {
            this.k = FileUtil.readFileContentsFromAssets(this.f236h, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_BACKGROUND_NAME);
        }
        if (this.k != null) {
            if (z) {
                z2 = iA == Integer.MIN_VALUE;
            } else {
                iA = 0;
                z2 = false;
            }
            this.f229a.getGLMapEngine().setBackgroundTexture(this.f235g, er.a((byte[]) this.k.clone(), 0, iA, z2));
        }
    }

    private void f() {
        IAMapDelegate iAMapDelegate = this.f229a;
        if (iAMapDelegate != null && iAMapDelegate.getGLMapEngine() != null && this.k != null) {
            this.f229a.getGLMapEngine().setBackgroundTexture(this.f235g, this.k);
        }
        IAMapDelegate iAMapDelegate2 = this.f229a;
        if (iAMapDelegate2 != null && iAMapDelegate2.getGLMapEngine() != null) {
            this.f229a.getGLMapEngine().setTrafficStyle(this.f235g, 0, 0, 0, 0, false);
        }
        this.r = false;
    }

    private boolean c(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            if (bArr.length < 8) {
                return false;
            }
            if (((bArr[4] & UByte.MAX_VALUE) | ((bArr[7] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[6] << 16) & 16711680) | ((bArr[5] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) != 2000) {
                return false;
            }
        } catch (Throwable th) {
            hn.c(th, "AMapCustomStyleManager", "checkData");
            er.a(th);
        }
        return true;
    }

    private void g() {
        if (this.x) {
            if (this.j == null) {
                this.j = a(FileUtil.readFileContentsFromAssets(this.f236h, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_STYLE_DATA_ABROAD));
            }
        } else if (this.j == null) {
            this.j = a(FileUtil.readFileContentsFromAssets(this.f236h, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_STYLE_DATA));
        }
        byte[] bArr = this.i;
        this.f229a.getGLMapEngine().setCustomStyleData(this.f235g, this.j, this.i);
        this.q = false;
    }

    private void h() {
        if (this.p) {
            if (this.l == null) {
                this.l = FileUtil.readFileContentsFromAssets(this.f236h, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_ICON_5_NAME_FOR_CUSTOM);
            }
            this.p = false;
            this.f229a.getGLMapEngine().setCustomStyleTexture(this.f235g, this.l);
        }
    }

    public void a(CustomMapStyleOptions customMapStyleOptions) {
        if (this.f230b == null || customMapStyleOptions == null) {
            return;
        }
        synchronized (this) {
            if (!this.n) {
                this.n = true;
                if (this.f230b.isEnable()) {
                    this.f231c = true;
                }
            }
            if (this.f230b.isEnable() != customMapStyleOptions.isEnable()) {
                this.f230b.setEnable(customMapStyleOptions.isEnable());
                this.f231c = true;
                ep.b(this.f236h, customMapStyleOptions.isEnable());
            }
            if (this.f230b.isEnable()) {
                if (!TextUtils.equals(this.f230b.getStyleId(), customMapStyleOptions.getStyleId())) {
                    this.f230b.setStyleId(customMapStyleOptions.getStyleId());
                    String styleId = this.f230b.getStyleId();
                    if (!TextUtils.isEmpty(styleId) && this.f229a != null && this.f229a.getMapConfig() != null && this.f229a.getMapConfig().isProFunctionAuthEnable()) {
                        if (this.s == null) {
                            if (this.x) {
                                this.s = new dg(this.f236h, this, 2, "abroad_sdk_json_sdk_700_zip");
                            } else {
                                this.s = new dg(this.f236h, this, 1, "sdk_700");
                            }
                        }
                        this.s.a(styleId);
                        this.s.b();
                        if (this.t == null) {
                            this.t = new dg(this.f236h, this, 0, null);
                        }
                        this.t.a(styleId);
                        this.t.b();
                    }
                }
                if (!TextUtils.equals(this.f230b.getStyleDataPath(), customMapStyleOptions.getStyleDataPath())) {
                    this.f230b.setStyleDataPath(customMapStyleOptions.getStyleDataPath());
                    this.f232d = true;
                }
                if (this.f230b.getStyleData() != customMapStyleOptions.getStyleData()) {
                    this.f230b.setStyleData(customMapStyleOptions.getStyleData());
                    this.f232d = true;
                }
                if (!TextUtils.equals(this.f230b.getStyleTexturePath(), customMapStyleOptions.getStyleTexturePath())) {
                    this.f230b.setStyleTexturePath(customMapStyleOptions.getStyleTexturePath());
                    this.f233e = true;
                }
                if (this.f230b.getStyleTextureData() != customMapStyleOptions.getStyleTextureData()) {
                    this.f230b.setStyleTextureData(customMapStyleOptions.getStyleTextureData());
                    this.f233e = true;
                }
                if (!TextUtils.equals(this.f230b.getStyleExtraPath(), customMapStyleOptions.getStyleExtraPath())) {
                    this.f230b.setStyleExtraPath(customMapStyleOptions.getStyleExtraPath());
                    this.f234f = true;
                }
                if (this.f230b.getStyleExtraData() != customMapStyleOptions.getStyleExtraData()) {
                    this.f230b.setStyleExtraData(customMapStyleOptions.getStyleExtraData());
                    this.f234f = true;
                }
                ep.a(this.f236h, true);
            } else {
                i();
                ep.a(this.f236h, false);
            }
        }
    }

    public void b() {
        if (this.f230b == null) {
            return;
        }
        synchronized (this) {
            if (this.f229a != null && this.f229a.getMapConfig() != null && !this.f229a.getMapConfig().isProFunctionAuthEnable()) {
                this.f230b.setStyleId(null);
                this.u = null;
                this.v = null;
            }
            this.f233e = true;
            this.f232d = true;
            if (this.r) {
                this.f234f = true;
            }
            this.f231c = true;
        }
    }

    public static byte[] a(byte[] bArr) {
        GZIPInputStream gZIPInputStream;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        } catch (Throwable th) {
            th = th;
            gZIPInputStream = null;
        }
        try {
            byte[] bArr2 = new byte[256];
            while (true) {
                int i = gZIPInputStream.read(bArr2);
                if (i >= 0) {
                    byteArrayOutputStream.write(bArr2, 0, i);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                er.a(th);
                th.printStackTrace();
                return null;
            } finally {
                GLFileUtil.closeQuietly(byteArrayOutputStream);
                GLFileUtil.closeQuietly(byteArrayInputStream);
                GLFileUtil.closeQuietly(gZIPInputStream);
            }
        }
    }

    @Override // com.amap.api.mapcore.util.dg.a
    public void a(byte[] bArr, int i) {
        MapConfig mapConfig;
        if (this.f230b != null) {
            synchronized (this) {
                if (this.f229a != null && (mapConfig = this.f229a.getMapConfig()) != null && mapConfig.isProFunctionAuthEnable()) {
                    mapConfig.setUseProFunction(true);
                    if (i == 1) {
                        this.u = bArr;
                        this.f232d = true;
                    } else if (i == 0) {
                        this.v = bArr;
                        this.f234f = true;
                    } else if (i == 2) {
                        String str = this.f230b.getStyleId() + "_sdk_700.data";
                        String str2 = this.f230b.getStyleId() + "_abroad_sdk.json";
                        Map<String, byte[]> mapUncompressToByteWithKeys = FileUtil.uncompressToByteWithKeys(bArr, new String[]{str, str2});
                        if (mapUncompressToByteWithKeys != null) {
                            byte[] bArr2 = mapUncompressToByteWithKeys.get(str);
                            if (bArr2 != null) {
                                this.u = bArr2;
                                this.f232d = true;
                            }
                            byte[] bArr3 = mapUncompressToByteWithKeys.get(str2);
                            if (bArr3 != null && this.z != null) {
                                this.z.a(bArr3);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(a aVar) {
        this.z = aVar;
    }

    public void c() {
        if (this.f230b == null) {
            this.f230b = new CustomMapStyleOptions();
        }
    }

    public boolean d() {
        return this.f230b != null;
    }

    public void e() {
        synchronized (this) {
            if (this.f230b != null) {
                this.f230b.setEnable(false);
                i();
                this.f231c = true;
            }
        }
    }

    private void i() {
        CustomMapStyleOptions customMapStyleOptions = this.f230b;
        if (customMapStyleOptions != null) {
            customMapStyleOptions.setStyleId(null);
            this.f230b.setStyleDataPath(null);
            this.f230b.setStyleData(null);
            this.f230b.setStyleTexturePath(null);
            this.f230b.setStyleTextureData(null);
            this.f230b.setStyleExtraData(null);
            this.f230b.setStyleExtraPath(null);
        }
    }
}