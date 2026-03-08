package com.amap.api.mapcore.util;

import androidx.core.app.NotificationCompat;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceConstant;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CoreUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class gc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String[] f1010a = {"com.amap.api.trace", "com.amap.api.trace.core"};

    public static void a(String str, String str2) throws fz {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("errcode")) {
                a(jSONObject.getInt("errcode"), jSONObject.getString("errmsg"), str2);
                return;
            }
            if (jSONObject.has(NotificationCompat.CATEGORY_STATUS) && jSONObject.has("infocode")) {
                String string = jSONObject.getString(NotificationCompat.CATEGORY_STATUS);
                int i = jSONObject.getInt("infocode");
                if ("1".equals(string)) {
                    return;
                }
                String string2 = jSONObject.getString("info");
                if (AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(string)) {
                    a(i, string2, str2);
                }
            }
        } catch (JSONException unused) {
            throw new fz(AMapException.ERROR_PROTOCOL);
        }
    }

    protected static void a(int i, String str, String str2) throws fz {
        if (i != 0) {
            switch (i) {
                case 10000:
                    return;
                case 10001:
                    throw new fz("用户key不正确或过期");
                case 10002:
                    throw new fz("请求服务不存在");
                case 10003:
                    throw new fz("访问已超出日访问量");
                case HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_ROLE_TYPE_ERROR /* 10004 */:
                    throw new fz("用户访问过于频繁");
                case HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_USER_ID_NULL /* 10005 */:
                    throw new fz("用户IP无效");
                case HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_DRIVER_ID_NULL /* 10006 */:
                    throw new fz("用户域名无效");
                case HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_BAIDUMAP_NULL /* 10007 */:
                    throw new fz("用户签名未通过");
                case HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_RESULT_NULL /* 10008 */:
                    throw new fz("用户MD5安全码未通过");
                case HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_RESULT_PARSER_FAILED /* 10009 */:
                    throw new fz("请求key与绑定平台不符");
                case 10010:
                    throw new fz("IP访问超限");
                case 10011:
                    throw new fz("服务不支持https请求");
                case 10012:
                    throw new fz("权限不足，服务请求被拒绝");
                case 10013:
                    throw new fz("开发者删除了key，key被删除后无法正常使用");
                default:
                    switch (i) {
                        case 20000:
                            throw new fz("请求参数非法");
                        case 20001:
                            throw new fz("缺少必填参数");
                        case 20002:
                            throw new fz("请求协议非法");
                        case 20003:
                            throw new fz("其他未知错误");
                        default:
                            switch (i) {
                                case 30000:
                                    throw new fz("请求服务响应错误");
                                case 30001:
                                    throw new fz("引擎返回数据异常");
                                case 30002:
                                    throw new fz("服务端请求链接超时");
                                case 30003:
                                    throw new fz("读取服务结果超时");
                                default:
                                    throw new fz(str);
                            }
                    }
            }
        }
    }

    public static int a(List<LatLng> list) {
        int i = 0;
        if (list == null || list.size() == 0) {
            return 0;
        }
        int iCalculateLineDistance = 0;
        while (i < list.size() - 1) {
            LatLng latLng = list.get(i);
            i++;
            LatLng latLng2 = list.get(i);
            if (latLng == null || latLng2 == null) {
                break;
            }
            iCalculateLineDistance = (int) (iCalculateLineDistance + AMapUtils.calculateLineDistance(latLng, latLng2));
        }
        return iCalculateLineDistance;
    }
}