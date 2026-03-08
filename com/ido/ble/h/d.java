package com.ido.ble.h;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.LocalDataManager;
import com.ido.ble.common.TimeUtil;
import com.ido.ble.common.k;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.gps.model.GPSInfo;
import com.ido.ble.gps.model.GpsDataReply;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.gps.model.GpsStatus;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class d {
    private static List<HealthGpsItem> a(GpsDataReply gpsDataReply) {
        double dA;
        ArrayList arrayList = new ArrayList();
        for (String str : gpsDataReply.items) {
            if (str.contains(AppInfo.DELIM)) {
                String[] strArrSplit = str.split(AppInfo.DELIM);
                double dA2 = 0.0d;
                try {
                    dA = c.a(strArrSplit[0], "E");
                    try {
                        dA2 = c.a(strArrSplit[1], "N");
                    } catch (Exception e2) {
                        e = e2;
                        LogTool.b(com.ido.ble.logs.a.j, "[handleGpsItemData]" + e.toString());
                    }
                } catch (Exception e3) {
                    e = e3;
                    dA = 0.0d;
                }
                HealthGpsItem healthGpsItem = new HealthGpsItem();
                healthGpsItem.setDate(Long.valueOf(gpsDataReply.date));
                healthGpsItem.setLongitude(Double.valueOf(dA));
                healthGpsItem.setLatitude(Double.valueOf(dA2));
                arrayList.add(healthGpsItem);
            }
        }
        LogTool.d(com.ido.ble.logs.a.j, "[handleGpsItemData] size = " + arrayList.size());
        if (arrayList.size() > 0) {
            com.ido.ble.gps.database.a.a(arrayList);
        }
        return arrayList;
    }

    private static void a(int i) {
        GpsCallBack.a(i == 0);
    }

    public static void a(int i, int i2, int i3) {
        StringBuilder sb;
        String str;
        String string;
        if (i == 20) {
            GpsCallBack.b(i3);
        }
        if (i == 21) {
            if (i2 == 0) {
                GpsCallBack.d();
                return;
            } else {
                GpsCallBack.a(i2);
                return;
            }
        }
        if (i == 158) {
            if (i2 != 0) {
                GpsCallBack.a(false);
                return;
            }
            return;
        }
        if (i == 313) {
            if (i2 != 0) {
                GpsCallBack.a((GpsHotStartParam) null);
                return;
            }
            return;
        }
        if (i == 653) {
            LogTool.d(com.ido.ble.logs.a.j, "sync gps finished, error = " + i2);
            if (i2 == 0) {
                GpsCallBack.f();
                return;
            } else {
                GpsCallBack.e();
                return;
            }
        }
        if (i == 5530) {
            if (i2 == 0) {
                if (i3 == 0 || i3 == 3) {
                    GpsCallBack.c();
                    return;
                } else if (i3 == 1) {
                    GpsCallBack.b();
                    return;
                } else {
                    if (i3 == 2) {
                        GpsCallBack.a();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        switch (i) {
            case 15:
                GpsCallBack.e(i3);
                break;
            case 16:
                if (i2 == 0) {
                    GpsCallBack.h();
                    string = "onTranAGpsFileFinish success error = 0";
                } else {
                    SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
                    if (i2 == 24 || i2 == 25) {
                        GpsCallBack.a(i2, Integer.valueOf(i3));
                        sb = new StringBuilder();
                        str = "onTranAGpsFileFailed erroe :";
                    } else if (supportFunctionInfo == null || !supportFunctionInfo.v3_support_data_tran_get_new_error_code) {
                        GpsCallBack.d(i2);
                        sb = new StringBuilder();
                        str = "onTranAGpsFileFailed use error =";
                    } else {
                        GpsCallBack.d(i3);
                        sb = new StringBuilder();
                        sb.append("onTranAGpsFileFailed use value =");
                        sb.append(i3);
                        string = sb.toString();
                    }
                    sb.append(str);
                    sb.append(i2);
                    string = sb.toString();
                }
                LogTool.d(com.ido.ble.logs.a.j, string);
                break;
            case 17:
                GpsCallBack.c(i3);
                break;
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 6004) {
            b(bArr);
        }
        switch (i) {
            case 155:
                a(i2);
                break;
            case 156:
                a(bArr);
                break;
            case 157:
                f(bArr);
                break;
            case 158:
                b(i2);
                break;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.W0 /* 312 */:
                        c(bArr);
                        break;
                    case com.veryfit.multi.nativeprotocol.b.X0 /* 313 */:
                        e(bArr);
                        break;
                    case com.veryfit.multi.nativeprotocol.b.Y0 /* 314 */:
                        d(bArr);
                        break;
                }
                break;
        }
    }

    private static void a(byte[] bArr) {
        GpsCallBack.a((ControlGpsReply) k.c(com.ido.ble.common.c.d(bArr), ControlGpsReply.class));
    }

    private static void b(int i) {
        GpsCallBack.b(i == 0);
    }

    private static void b(byte[] bArr) {
        List<String> list;
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.j, "[handleReplyGpsData] json data =" + strD);
        GpsDataReply gpsDataReply = (GpsDataReply) k.c(strD, GpsDataReply.class);
        if (gpsDataReply == null) {
            LogTool.b(com.ido.ble.logs.a.j, "[handleReplyGpsData] gpsDataReply is null");
            return;
        }
        if (gpsDataReply.year == 0 || (list = gpsDataReply.items) == null || list.size() == 0) {
            LogTool.b(com.ido.ble.logs.a.j, "[handleReplyGpsData] gpsDataReply.year = 0");
            return;
        }
        int i = gpsDataReply.year;
        if (i < 2000) {
            gpsDataReply.year = i + 2000;
        }
        gpsDataReply.date = TimeUtil.dateToStamp(gpsDataReply.year, gpsDataReply.month, gpsDataReply.day, gpsDataReply.hour, gpsDataReply.minute, gpsDataReply.second);
        HealthGps healthGps = new HealthGps();
        healthGps.setYear(Integer.valueOf(gpsDataReply.year));
        healthGps.setMonth(Integer.valueOf(gpsDataReply.month));
        healthGps.setDay(Integer.valueOf(gpsDataReply.day));
        healthGps.setHour(Integer.valueOf(gpsDataReply.hour));
        healthGps.setMinute(Integer.valueOf(gpsDataReply.minute));
        healthGps.setSecond(Integer.valueOf(gpsDataReply.second));
        healthGps.setData_interval(Integer.valueOf(gpsDataReply.data_interval));
        healthGps.setDate(Long.valueOf(gpsDataReply.date));
        com.ido.ble.gps.database.a.a(healthGps);
        LogTool.d(com.ido.ble.logs.a.j, "[handleReplyGpsData] gpsDataReply is " + gpsDataReply.toString());
        GpsCallBack.a(healthGps, a(gpsDataReply), false);
    }

    private static void c(byte[] bArr) {
        GpsCallBack.a((GPSInfo) k.c(com.ido.ble.common.c.d(bArr), GPSInfo.class));
    }

    public static boolean c(int i) {
        if (i == 20 || i == 21 || i == 653 || i == 5530 || i == 6004) {
            return true;
        }
        switch (i) {
            case 15:
            case 16:
            case 17:
                return true;
            default:
                switch (i) {
                    case 155:
                    case 156:
                    case 157:
                    case 158:
                        return true;
                    default:
                        switch (i) {
                            case com.veryfit.multi.nativeprotocol.b.W0 /* 312 */:
                            case com.veryfit.multi.nativeprotocol.b.X0 /* 313 */:
                            case com.veryfit.multi.nativeprotocol.b.Y0 /* 314 */:
                                return true;
                            default:
                                return false;
                        }
                }
        }
    }

    private static void d(byte[] bArr) {
        GpsStatus gpsStatus = (GpsStatus) k.c(com.ido.ble.common.c.d(bArr), GpsStatus.class);
        if (gpsStatus != null) {
            GpsCallBack.a(gpsStatus);
        }
    }

    private static void e(byte[] bArr) {
        GpsCallBack.a((GpsHotStartParam) k.c(com.ido.ble.common.c.d(bArr), GpsHotStartParam.class));
    }

    private static void f(byte[] bArr) {
        GpsCallBack.a((ConnParamReply) k.c(com.ido.ble.common.c.d(bArr), ConnParamReply.class));
    }
}