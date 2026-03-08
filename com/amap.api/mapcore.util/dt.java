package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.tencent.bugly.BuglyStrategy;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: BaseTileProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class dt implements TileProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Random f668a = new Random();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f669b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f670c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private MapConfig f671d;

    public dt(int i, int i2, MapConfig mapConfig) {
        this.f669b = i;
        this.f670c = i2;
        this.f671d = mapConfig;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public final Tile getTile(int i, int i2, int i3) {
        try {
            byte[] bArrA = a(i, i2, i3, this.f671d != null ? this.f671d.getMapLanguage() : "zh_cn");
            if (bArrA == null) {
                return NO_TILE;
            }
            return Tile.obtain(this.f669b, this.f670c, bArrA);
        } catch (IOException unused) {
            return NO_TILE;
        }
    }

    private byte[] a(int i, int i2, int i3, String str) throws IOException {
        try {
            return new a(i, i2, i3, str).makeHttpRequestWithInterrupted();
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileWidth() {
        return this.f669b;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileHeight() {
        return this.f670c;
    }

    /* JADX INFO: compiled from: BaseTileProvider.java */
    class a extends dp {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f673e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f674f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f675g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f676h;
        private String i;

        @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
        public Map<String, String> getParams() {
            return null;
        }

        public a(int i, int i2, int i3, String str) {
            this.i = "";
            this.f673e = i;
            this.f674f = i2;
            this.f675g = i3;
            this.f676h = str;
            this.i = e();
            setProxy(gr.a(u.f1791a));
            setConnectionTimeout(5000);
            setSoTimeout(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH);
        }

        @Override // com.amap.api.mapcore.util.iq
        public Map<String, String> getRequestHead() {
            Hashtable hashtable = new Hashtable(16);
            hashtable.put("User-Agent", m.f1685c);
            hashtable.put("Accept-Encoding", "gzip");
            hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", "7.6.0", "3dmap"));
            hashtable.put("x-INFO", gl.a(u.f1791a));
            hashtable.put("key", gi.f(u.f1791a));
            hashtable.put("logversion", "2.1");
            return hashtable;
        }

        private String a() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=");
            stringBuffer.append(gi.f(u.f1791a));
            stringBuffer.append("&channel=amapapi");
            if (ek.a(this.f673e, this.f674f, this.f675g) || this.f675g < 6) {
                stringBuffer.append("&z=");
                stringBuffer.append(this.f675g);
                stringBuffer.append("&x=");
                stringBuffer.append(this.f673e);
                stringBuffer.append("&y=");
                stringBuffer.append(this.f674f);
                stringBuffer.append("&lang=en&size=1&scale=1&style=7");
            } else if (MapsInitializer.isLoadWorldGridMap()) {
                stringBuffer.append("&x=");
                stringBuffer.append(this.f673e);
                stringBuffer.append("&y=");
                stringBuffer.append(this.f674f);
                stringBuffer.append("&z=");
                stringBuffer.append(this.f675g);
                stringBuffer.append("&ds=0");
                stringBuffer.append("&dpitype=webrd");
                stringBuffer.append("&lang=");
                stringBuffer.append(this.f676h);
                stringBuffer.append("&scale=2");
            }
            String string = stringBuffer.toString();
            String strA = a(string);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(string);
            String strA2 = gl.a();
            stringBuffer2.append("&ts=" + strA2);
            stringBuffer2.append("&scode=" + gl.a(u.f1791a, strA2, strA));
            return stringBuffer2.toString();
        }

        private String a(String str) {
            String[] strArrSplit = str.split("&");
            Arrays.sort(strArrSplit);
            StringBuffer stringBuffer = new StringBuffer();
            for (String str2 : strArrSplit) {
                stringBuffer.append(b(str2));
                stringBuffer.append("&");
            }
            String string = stringBuffer.toString();
            return string.length() > 1 ? (String) string.subSequence(0, string.length() - 1) : str;
        }

        private String b(String str) {
            if (str == null) {
                return str;
            }
            try {
                return URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                hn.c(e2, "AbstractProtocalHandler", "strReEncoder");
                return "";
            } catch (Exception e3) {
                hn.c(e3, "AbstractProtocalHandler", "strReEncoderException");
                return "";
            }
        }

        private String e() {
            if (ek.a(this.f673e, this.f674f, this.f675g) || this.f675g < 6) {
                return String.format(Locale.US, "http://wprd0%d.is.autonavi.com/appmaptile?", Integer.valueOf((dt.this.f668a.nextInt(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH) % 4) + 1));
            }
            if (MapsInitializer.isLoadWorldGridMap()) {
                return "http://restsdk.amap.com/v4/gridmap?";
            }
            return null;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getURL() {
            if (TextUtils.isEmpty(this.i)) {
                return null;
            }
            return this.i + a();
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getIPV6URL() {
            String url = getURL();
            return (url == null || !url.contains("http://restsdk.amap.com/v4/gridmap?")) ? url : er.a(url);
        }

        @Override // com.amap.api.mapcore.util.iq
        public boolean isSupportIPV6() {
            String url = getURL();
            return url != null && url.contains("http://restsdk.amap.com/v4/gridmap?");
        }
    }
}