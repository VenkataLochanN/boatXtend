package com.ido.life.util;

import android.util.Log;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.TraceLocation;
import com.ido.life.module.sport.bean.LocationMessage;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SportQueryTrace {
    private TraceLocation posTraceLocation;
    private TraceLocation weight2;
    private List<TraceLocation> mListPoint = new ArrayList();
    final int CAR_MAX_SPEED = 22;
    private Boolean isFirst = true;
    private TraceLocation weight1 = new TraceLocation();
    private List<TraceLocation> w1TempList = new ArrayList();
    private List<TraceLocation> w2TempList = new ArrayList();
    private int w1Count = 0;
    private int posCount = 0;
    private int beginPos = 0;

    public LocationMessage filterPos(LocationMessage locationMessage) {
        String str;
        String str2;
        try {
            if (this.isFirst.booleanValue()) {
                this.isFirst = false;
                this.weight1.setLatitude(locationMessage.getLatitude());
                this.weight1.setLongitude(locationMessage.getLongitude());
                this.weight1.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                String str3 = "第一次 : ";
                TraceLocation traceLocation = new TraceLocation();
                traceLocation.setLatitude(locationMessage.getLatitude());
                traceLocation.setLongitude(locationMessage.getLongitude());
                traceLocation.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                this.w1TempList.add(traceLocation);
                this.w1Count++;
                Log.d("wsh", str3);
                return locationMessage;
            }
            String str4 = "非第一次 : ";
            if (this.weight2 == null) {
                long longFromDateStr = 22 * ((DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()) - this.weight1.getTime()) / 1000);
                float fCalculateLineDistance = AMapUtils.calculateLineDistance(new LatLng(this.weight1.getLatitude(), this.weight1.getLongitude()), new LatLng(locationMessage.getLatitude(), locationMessage.getLongitude()));
                String str5 = (str4 + "weight2=null : ") + "distance = " + fCalculateLineDistance + ",MaxDistance = " + longFromDateStr + " : ";
                if (fCalculateLineDistance > longFromDateStr) {
                    String str6 = str5 + "distance > MaxDistance当前点 距离大: 设置w2位新的点，并添加到w2TempList";
                    this.weight2 = new TraceLocation();
                    this.weight2.setLatitude(locationMessage.getLatitude());
                    this.weight2.setLongitude(locationMessage.getLongitude());
                    this.weight2.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                    this.w2TempList.add(this.weight2);
                    Log.d("wsh", str6);
                    return locationMessage;
                }
                String str7 = str5 + "distance < MaxDistance当前点 距离小 : 添加到w1TempList";
                TraceLocation traceLocation2 = new TraceLocation();
                traceLocation2.setLatitude(locationMessage.getLatitude());
                traceLocation2.setLongitude(locationMessage.getLongitude());
                traceLocation2.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                this.w1TempList.add(traceLocation2);
                this.w1Count++;
                this.weight1.setLatitude((this.weight1.getLatitude() * 0.2d) + (locationMessage.getLatitude() * 0.8d));
                this.weight1.setLongitude((this.weight1.getLongitude() * 0.2d) + (locationMessage.getLongitude() * 0.8d));
                this.weight1.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                this.weight1.setSpeed(locationMessage.speed);
                if (this.w1Count > 3) {
                    String str8 = str7 + " : 更新";
                    this.mListPoint.addAll(this.w1TempList);
                    this.w1TempList.clear();
                    Log.d("wsh", str8);
                    return locationMessage;
                }
                str = str7 + " w1Count<3: 不更新";
            } else {
                long longFromDateStr2 = ((DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()) - this.weight2.getTime()) / 1000) * 16;
                float fCalculateLineDistance2 = AMapUtils.calculateLineDistance(new LatLng(this.weight2.getLatitude(), this.weight2.getLongitude()), new LatLng(locationMessage.getLatitude(), locationMessage.getLongitude()));
                String str9 = (str4 + "weight2 != null : ") + "distance = " + fCalculateLineDistance2 + ",MaxDistance = " + longFromDateStr2 + " : ";
                if (fCalculateLineDistance2 > longFromDateStr2) {
                    String str10 = str9 + "当前点 距离大: weight2 更新";
                    this.w2TempList.clear();
                    this.weight2 = new TraceLocation();
                    this.weight2.setLatitude(locationMessage.getLatitude());
                    this.weight2.setLongitude(locationMessage.getLongitude());
                    this.weight2.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                    this.w2TempList.add(this.weight2);
                    Log.d("wsh", str10);
                    return null;
                }
                String str11 = str9 + "当前点 距离小: 添加到w2TempList";
                TraceLocation traceLocation3 = new TraceLocation();
                traceLocation3.setLatitude(locationMessage.getLatitude());
                traceLocation3.setLongitude(locationMessage.getLongitude());
                traceLocation3.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                this.w2TempList.add(traceLocation3);
                this.weight2.setLatitude((this.weight2.getLatitude() * 0.2d) + (locationMessage.getLatitude() * 0.8d));
                this.weight2.setLongitude((this.weight2.getLongitude() * 0.2d) + (locationMessage.getLongitude() * 0.8d));
                this.weight2.setTime(DateUtil.getLongFromDateStr(locationMessage.getCurrentTimeMillis()));
                this.weight2.setSpeed(locationMessage.speed);
                if (this.w2TempList.size() > 4) {
                    if (this.w1Count > 4) {
                        str2 = str11 + "w1Count > 4计算增加W1";
                        this.mListPoint.addAll(this.w1TempList);
                    } else {
                        str2 = str11 + "w1Count < 4计算丢弃W1";
                        this.w1TempList.clear();
                    }
                    String str12 = str2 + "w2TempList.size() > 4 : 更新到偏移点";
                    this.mListPoint.addAll(this.w2TempList);
                    this.w2TempList.clear();
                    this.weight1 = this.weight2;
                    this.weight2 = null;
                    Log.d("wsh", str12);
                    return locationMessage;
                }
                str = str11 + "w2TempList.size() < 4\r\n";
            }
            Log.d("wsh", str);
            return null;
        } catch (Throwable th) {
            Log.d("wsh", "");
            throw th;
        }
    }
}