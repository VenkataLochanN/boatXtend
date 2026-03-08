package com.autonavi.base.amap.mapcore.maploader;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.dp;
import com.amap.api.mapcore.util.er;
import com.amap.api.mapcore.util.ex;
import com.amap.api.mapcore.util.ey;
import com.amap.api.mapcore.util.gi;
import com.amap.api.mapcore.util.gl;
import com.amap.api.mapcore.util.gm;
import com.amap.api.mapcore.util.gs;
import com.amap.api.mapcore.util.hn;
import com.amap.api.mapcore.util.il;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.ae.gmap.GLMapEngine;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class AMapLoader implements il.a {
    private static final int GET_METHOD = 0;
    private static final String NETWORK_RESPONSE_CODE_STRING = "网络异常状态码：";
    private static String mDiu;
    private il downloadManager;
    private volatile boolean isCanceled = false;
    public boolean isFinish = false;
    ADataRequestParam mDataRequestParam;
    private int mEngineID;
    GLMapEngine mGLMapEngine;
    private boolean mRequestCancel;

    public static class ADataRequestParam {
        public byte[] enCodeString;
        public long handler;
        public int nCompress;
        public int nRequestType;
        public String requestBaseUrl;
        public String requestUrl;
    }

    public static class AMapGridDownloadRequest extends dp {
        private final Context mContext;
        private byte[] postEntityBytes;
        private String sUrl;
        private String userAgent;

        @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
        public Map<String, String> getParams() {
            return null;
        }

        @Override // com.amap.api.mapcore.util.iq
        public boolean isSupportIPV6() {
            return true;
        }

        public AMapGridDownloadRequest(Context context, String str, String str2) {
            this.mContext = context;
            this.sUrl = str;
            this.userAgent = str2;
        }

        @Override // com.amap.api.mapcore.util.iq
        public Map<String, String> getRequestHead() {
            gs gsVarE = er.e();
            String strB = gsVarE != null ? gsVarE.b() : null;
            String strF = gi.f(this.mContext);
            try {
                strF = URLEncoder.encode(strF, Key.STRING_CHARSET_NAME);
            } catch (Throwable unused) {
            }
            Hashtable hashtable = new Hashtable(16);
            hashtable.put("User-Agent", this.userAgent);
            hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", strB, "3dmap"));
            hashtable.put("x-INFO", gl.a(this.mContext));
            hashtable.put("key", strF);
            hashtable.put("logversion", "2.1");
            return hashtable;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getURL() {
            return this.sUrl;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getIPV6URL() {
            return er.a(getURL());
        }

        public void setPostEntityBytes(byte[] bArr) {
            this.postEntityBytes = bArr;
        }

        @Override // com.amap.api.mapcore.util.iq
        public byte[] getEntityBytes() {
            return this.postEntityBytes;
        }
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onDownload(byte[] bArr, long j) {
        GLMapEngine gLMapEngine;
        ADataRequestParam aDataRequestParam;
        if (bArr == null || (gLMapEngine = this.mGLMapEngine) == null || (aDataRequestParam = this.mDataRequestParam) == null) {
            return;
        }
        gLMapEngine.receiveNetData(this.mEngineID, aDataRequestParam.handler, bArr, bArr.length);
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onStop() {
        ADataRequestParam aDataRequestParam;
        GLMapEngine gLMapEngine = this.mGLMapEngine;
        if (gLMapEngine == null || (aDataRequestParam = this.mDataRequestParam) == null) {
            return;
        }
        gLMapEngine.netStop(this.mEngineID, aDataRequestParam.handler, -1);
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onFinish() {
        ADataRequestParam aDataRequestParam;
        GLMapEngine gLMapEngine = this.mGLMapEngine;
        if (gLMapEngine == null || (aDataRequestParam = this.mDataRequestParam) == null) {
            return;
        }
        gLMapEngine.finishDownLoad(this.mEngineID, aDataRequestParam.handler);
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onException(Throwable th) {
        ADataRequestParam aDataRequestParam;
        ADataRequestParam aDataRequestParam2;
        int iIndexOf;
        int i = -1;
        try {
            String str = new String(th.getMessage().getBytes(Key.STRING_CHARSET_NAME), Key.STRING_CHARSET_NAME);
            if (!TextUtils.isEmpty(str) && (iIndexOf = str.indexOf(NETWORK_RESPONSE_CODE_STRING)) != -1) {
                i = Integer.parseInt(str.substring(iIndexOf + 8));
            }
            int i2 = i;
            GLMapEngine gLMapEngine = this.mGLMapEngine;
            if (gLMapEngine != null && (aDataRequestParam2 = this.mDataRequestParam) != null) {
                gLMapEngine.netError(this.mEngineID, aDataRequestParam2.handler, -1, i2);
            }
        } catch (Throwable unused) {
            GLMapEngine gLMapEngine2 = this.mGLMapEngine;
            if (gLMapEngine2 != null && (aDataRequestParam = this.mDataRequestParam) != null) {
                gLMapEngine2.netError(this.mEngineID, aDataRequestParam.handler, -1, -1);
            }
        }
        hn.c(th, "AMapLoader", "download onException");
        ey.b(ex.f798e, "map loader exception " + th.getMessage());
    }

    public AMapLoader(int i, GLMapEngine gLMapEngine, ADataRequestParam aDataRequestParam) {
        this.mEngineID = 0;
        this.mRequestCancel = false;
        this.mDataRequestParam = aDataRequestParam;
        this.mEngineID = i;
        this.mGLMapEngine = gLMapEngine;
        this.mRequestCancel = false;
    }

    public void doRequest() {
        if (this.mRequestCancel) {
            return;
        }
        String str = this.mDataRequestParam.requestBaseUrl;
        String str2 = this.mDataRequestParam.requestUrl;
        if (!str.endsWith("?")) {
            str = str + "?";
        }
        String requestParams = getRequestParams(str2.replaceAll(";", getEncodeRequestParams(";").toString()), str != null && str.contains("http://m5.amap.com/"), this.mDataRequestParam.nRequestType);
        StringBuffer stringBuffer = new StringBuffer();
        if (this.mDataRequestParam.nRequestType == 0) {
            stringBuffer.append(requestParams);
            stringBuffer.append("&csid=" + UUID.randomUUID().toString());
        } else {
            stringBuffer.append("csid=" + UUID.randomUUID().toString());
        }
        try {
            AMapGridDownloadRequest aMapGridDownloadRequest = new AMapGridDownloadRequest(this.mGLMapEngine.getContext(), str + generateQueryString(this.mGLMapEngine.getContext(), stringBuffer.toString()), this.mGLMapEngine.getUserAgent());
            aMapGridDownloadRequest.setConnectionTimeout(30000);
            aMapGridDownloadRequest.setSoTimeout(30000);
            if (this.mDataRequestParam.nRequestType != 0) {
                aMapGridDownloadRequest.setPostEntityBytes(requestParams.getBytes(Key.STRING_CHARSET_NAME));
            }
            this.downloadManager = new il(aMapGridDownloadRequest, 0L, -1L, MapsInitializer.getProtocol() == 2);
            this.downloadManager.a(this);
        } finally {
            try {
            } finally {
            }
        }
    }

    public void doCancel() {
        this.mRequestCancel = true;
        if (this.downloadManager == null || this.isCanceled) {
            return;
        }
        synchronized (this.downloadManager) {
            try {
                this.isCanceled = true;
                this.downloadManager.a();
                this.mGLMapEngine.setMapLoaderToTask(this.mEngineID, this.mDataRequestParam.handler, null);
            } finally {
            }
        }
    }

    private String getEncodeRequestParams(String str) {
        try {
            return URLEncoder.encode(str, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String getDeviceId(Context context) {
        if (context != null) {
            return gm.y(context);
        }
        return null;
    }

    protected String getRequestParams(String str, boolean z, int i) {
        if (mDiu == null) {
            mDiu = getDeviceId(this.mGLMapEngine.getContext());
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        if (z) {
            stringBuffer.append("&channel=amap7&div=GNaviMap");
            stringBuffer.append("&diu=");
            stringBuffer.append(mDiu);
        } else {
            stringBuffer.append("&channel=amapapi");
            stringBuffer.append("&div=GNaviMap");
            stringBuffer.append("&diu=");
            stringBuffer.append(mDiu);
        }
        return stringBuffer.toString();
    }

    private String generateQueryString(Context context, String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        String strF = gi.f(this.mGLMapEngine.getContext());
        try {
            strF = URLEncoder.encode(strF, Key.STRING_CHARSET_NAME);
        } catch (Throwable unused) {
        }
        stringBuffer.append("&key=");
        stringBuffer.append(strF);
        String strSortReEncoderParams = sortReEncoderParams(stringBuffer.toString());
        String strA = gl.a();
        stringBuffer.append("&ts=" + strA);
        stringBuffer.append("&scode=" + gl.a(context, strA, strSortReEncoderParams));
        stringBuffer.append("&dip=");
        stringBuffer.append("16300");
        return stringBuffer.toString();
    }

    private String sortReEncoderParams(String str) {
        String[] strArrSplit = str.split("&");
        Arrays.sort(strArrSplit);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : strArrSplit) {
            stringBuffer.append(strReEncoder(str2));
            stringBuffer.append("&");
        }
        String string = stringBuffer.toString();
        return string.length() > 1 ? (String) string.subSequence(0, string.length() - 1) : str;
    }

    private String strReEncoder(String str) {
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
}