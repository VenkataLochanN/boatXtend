package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.load.Key;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.nio.ByteBuffer;
import kotlin.UByte;
import org.json.JSONObject;

/* JADX INFO: compiled from: MapParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class ku {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StringBuilder f1610a = new StringBuilder();

    public final kr a(String str, Context context, is isVar) {
        kr krVar = new kr("");
        krVar.setErrorCode(7);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS) || !jSONObject.has("info")) {
                this.f1610a.append("json is error " + str);
            }
            String string = jSONObject.getString(NotificationCompat.CATEGORY_STATUS);
            String string2 = jSONObject.getString("info");
            if (string.equals(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE)) {
                this.f1610a.append("auth fail:" + string2);
            }
        } catch (Throwable th) {
            this.f1610a.append("json exception error:" + th.getMessage());
            kg.a(th, "MapParser", "paseAuthFailurJson");
        }
        try {
            StringBuilder sb = this.f1610a;
            sb.append("#SHA1AndPackage#");
            sb.append(gi.e(context));
            String str2 = isVar.f1403b.get("gsid").get(0);
            if (!TextUtils.isEmpty(str2)) {
                StringBuilder sb2 = this.f1610a;
                sb2.append(" #gsid#");
                sb2.append(str2);
            }
            String str3 = isVar.f1404c;
            if (!TextUtils.isEmpty(str3)) {
                this.f1610a.append(" #csid#" + str3);
            }
        } catch (Throwable unused) {
        }
        krVar.setLocationDetail(this.f1610a.toString());
        if (this.f1610a.length() > 0) {
            StringBuilder sb3 = this.f1610a;
            sb3.delete(0, sb3.length());
        }
        return krVar;
    }

    public final kr a(byte[] bArr) {
        kr krVar;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        ByteBuffer byteBuffer = null;
        try {
            krVar = new kr("");
        } catch (Throwable th) {
            try {
                kr krVar2 = new kr("");
                krVar2.setErrorCode(5);
                this.f1610a.append("parser data error:" + th.getMessage());
                krVar2.setLocationDetail(this.f1610a.toString());
                krVar = krVar2;
            } finally {
                if (0 != 0) {
                    byteBuffer.clear();
                }
            }
        }
        if (bArr == null) {
            krVar.setErrorCode(5);
            this.f1610a.append("byte[] is null");
            krVar.setLocationDetail(this.f1610a.toString());
            this.f1610a.delete(0, this.f1610a.length());
            return krVar;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        if (byteBufferWrap.get() == 0) {
            krVar.b(String.valueOf((int) byteBufferWrap.getShort()));
            byteBufferWrap.clear();
            if (byteBufferWrap != null) {
                byteBufferWrap.clear();
            }
            return krVar;
        }
        krVar.setLongitude(kk.a(((double) byteBufferWrap.getInt()) / 1000000.0d));
        krVar.setLatitude(kk.a(((double) byteBufferWrap.getInt()) / 1000000.0d));
        krVar.setAccuracy(byteBufferWrap.getShort());
        krVar.c(String.valueOf((int) byteBufferWrap.get()));
        krVar.d(String.valueOf((int) byteBufferWrap.get()));
        if (byteBufferWrap.get() == 1) {
            byte[] bArr2 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr2);
            try {
                krVar.setCountry(new String(bArr2, Key.STRING_CHARSET_NAME));
            } catch (Throwable unused) {
            }
            byte[] bArr3 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr3);
            try {
                str = new String(bArr3, Key.STRING_CHARSET_NAME);
                try {
                    krVar.setProvince(str);
                } catch (Throwable unused2) {
                }
            } catch (Throwable unused3) {
                str = "";
            }
            byte[] bArr4 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr4);
            try {
                str2 = new String(bArr4, Key.STRING_CHARSET_NAME);
                try {
                    krVar.setCity(str2);
                } catch (Throwable unused4) {
                }
            } catch (Throwable unused5) {
                str2 = "";
            }
            byte[] bArr5 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr5);
            try {
                str3 = new String(bArr5, Key.STRING_CHARSET_NAME);
                try {
                    krVar.setDistrict(str3);
                } catch (Throwable unused6) {
                }
            } catch (Throwable unused7) {
                str3 = "";
            }
            byte[] bArr6 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr6);
            try {
                str4 = new String(bArr6, Key.STRING_CHARSET_NAME);
                try {
                    krVar.setStreet(str4);
                    krVar.setRoad(str4);
                } catch (Throwable unused8) {
                }
            } catch (Throwable unused9) {
                str4 = "";
            }
            byte[] bArr7 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr7);
            try {
                krVar.setNumber(new String(bArr7, Key.STRING_CHARSET_NAME));
            } catch (Throwable unused10) {
            }
            byte[] bArr8 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr8);
            try {
                str5 = new String(bArr8, Key.STRING_CHARSET_NAME);
                try {
                    krVar.setPoiName(str5);
                } catch (Throwable unused11) {
                }
            } catch (Throwable unused12) {
                str5 = "";
            }
            byte[] bArr9 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr9);
            try {
                krVar.setAoiName(new String(bArr9, Key.STRING_CHARSET_NAME));
            } catch (Throwable unused13) {
            }
            byte[] bArr10 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr10);
            try {
                str6 = new String(bArr10, Key.STRING_CHARSET_NAME);
                try {
                    krVar.setAdCode(str6);
                } catch (Throwable unused14) {
                }
            } catch (Throwable unused15) {
                str6 = "";
            }
            byte[] bArr11 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr11);
            try {
                krVar.setCityCode(new String(bArr11, Key.STRING_CHARSET_NAME));
            } catch (Throwable unused16) {
            }
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str2) && (!str.contains("市") || !str.equals(str2))) {
                sb.append(str2);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str3)) {
                sb.append(str3);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str4)) {
                sb.append(str4);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str5)) {
                if (!TextUtils.isEmpty(str6)) {
                    sb.append("靠近");
                }
                sb.append(str5);
                sb.append(" ");
            }
            Bundle bundle = new Bundle();
            bundle.putString("citycode", krVar.getCityCode());
            bundle.putString("desc", sb.toString());
            bundle.putString("adcode", krVar.getAdCode());
            krVar.setExtras(bundle);
            krVar.e(sb.toString());
            String adCode = krVar.getAdCode();
            krVar.setAddress((adCode == null || adCode.trim().length() <= 0) ? sb.toString() : sb.toString().replace(" ", ""));
        }
        byteBufferWrap.get(new byte[byteBufferWrap.get() & UByte.MAX_VALUE]);
        if (byteBufferWrap.get() == 1) {
            byteBufferWrap.getInt();
            byteBufferWrap.getInt();
            byteBufferWrap.getShort();
        }
        if (byteBufferWrap.get() == 1) {
            byte[] bArr12 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr12);
            try {
                krVar.setBuildingId(new String(bArr12, Key.STRING_CHARSET_NAME));
            } catch (Throwable unused17) {
            }
            byte[] bArr13 = new byte[byteBufferWrap.get() & UByte.MAX_VALUE];
            byteBufferWrap.get(bArr13);
            try {
                krVar.setFloor(new String(bArr13, Key.STRING_CHARSET_NAME));
            } catch (Throwable unused18) {
            }
        }
        if (byteBufferWrap.get() == 1) {
            byteBufferWrap.get();
            byteBufferWrap.getInt();
            byteBufferWrap.get();
        }
        if (byteBufferWrap.get() == 1) {
            krVar.setTime(byteBufferWrap.getLong());
        }
        byte[] bArr14 = new byte[byteBufferWrap.getShort()];
        byteBufferWrap.get(bArr14);
        try {
            krVar.a(new String(bArr14, Key.STRING_CHARSET_NAME));
        } catch (Throwable unused19) {
        }
        if (byteBufferWrap != null) {
            byteBufferWrap.clear();
        }
        if (this.f1610a.length() > 0) {
            StringBuilder sb2 = this.f1610a;
            sb2.delete(0, sb2.length());
        }
        return krVar;
    }
}