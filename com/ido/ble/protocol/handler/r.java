package com.ido.ble.protocol.handler;

import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.ido.ble.callback.QueryStatusCallBack;
import com.ido.ble.callback.SetPressCalibrationCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.AlarmV3CmdParaWrapper;
import com.ido.ble.protocol.model.BloodPressureAdjustDeviceReplyInfo;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.PressCalibrationReplyInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class r {
    r() {
    }

    public static void a(int i, int i2, int i3) {
        SettingCallBack.SettingType settingType;
        if (i == 150) {
            settingType = SettingCallBack.SettingType.WEATHER_SWITCH;
        } else if (i == 151) {
            settingType = SettingCallBack.SettingType.QUICK_SPORT_MODE;
        } else if (i == 3) {
            settingType = SettingCallBack.SettingType.ALARM;
        } else if (i == 154) {
            settingType = SettingCallBack.SettingType.SCREEN_BRIGHTNESS;
        } else if (i == 171) {
            settingType = SettingCallBack.SettingType.MENU_LIST_SET;
        } else {
            if (i == 188 || i == 190) {
                return;
            }
            if (i == 5011) {
                settingType = SettingCallBack.SettingType.MUSIC_CONTROL_INFO;
            } else if (i != 5013) {
                switch (i) {
                    case 101:
                        settingType = SettingCallBack.SettingType.LONG_SIT;
                        break;
                    case 102:
                        settingType = SettingCallBack.SettingType.ANTI_LOST_MODE;
                        break;
                    case 103:
                        settingType = SettingCallBack.SettingType.FIND_PHONE_SWITCH;
                        break;
                    case 104:
                        settingType = SettingCallBack.SettingType.TIME;
                        break;
                    case 105:
                        settingType = SettingCallBack.SettingType.GOAL;
                        break;
                    default:
                        switch (i) {
                            case 107:
                                settingType = SettingCallBack.SettingType.USER_INFO;
                                break;
                            case 108:
                                settingType = SettingCallBack.SettingType.UNIT;
                                break;
                            case 109:
                                settingType = SettingCallBack.SettingType.HAND_MODE;
                                break;
                            default:
                                switch (i) {
                                    case 111:
                                        settingType = SettingCallBack.SettingType.NOTICE_REMINDER_SWITCH_STATUS;
                                        break;
                                    case 112:
                                        settingType = SettingCallBack.SettingType.HEART_RATE_INTERVAL;
                                        break;
                                    case 113:
                                        settingType = SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE;
                                        break;
                                    case 114:
                                        settingType = SettingCallBack.SettingType.UP_HAND_GESTURE;
                                        break;
                                    case 115:
                                        settingType = SettingCallBack.SettingType.ONE_KEY_RESET;
                                        break;
                                    case 116:
                                        settingType = SettingCallBack.SettingType.NOT_DISTURB;
                                        break;
                                    case 117:
                                        settingType = SettingCallBack.SettingType.MUSIC_SWITCH;
                                        break;
                                    case 118:
                                        settingType = SettingCallBack.SettingType.DISPLAY_MODE;
                                        break;
                                    case 119:
                                        settingType = SettingCallBack.SettingType.ONE_KEY_SOS;
                                        break;
                                    default:
                                        switch (i) {
                                            case 124:
                                                settingType = SettingCallBack.SettingType.DIAL_PLATE;
                                                break;
                                            case 125:
                                                settingType = SettingCallBack.SettingType.SHORTCUT;
                                                break;
                                            case 126:
                                                settingType = SettingCallBack.SettingType.BLOOD_ADJUST;
                                                break;
                                            default:
                                                switch (i) {
                                                    case 165:
                                                        settingType = SettingCallBack.SettingType.WALK_REMINDER;
                                                        break;
                                                    case 166:
                                                        settingType = SettingCallBack.SettingType.BREATHE_TRAIN;
                                                        break;
                                                    case 167:
                                                        settingType = SettingCallBack.SettingType.ACTIVITY_SWITCH;
                                                        break;
                                                    case 168:
                                                        settingType = SettingCallBack.SettingType.DRINK_WATER_REMINDER;
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 184:
                                                                settingType = SettingCallBack.SettingType.SLEEP_MONITORING;
                                                                break;
                                                            case 185:
                                                                settingType = SettingCallBack.SettingType.NIGHT_TEMPERATURE_MONITORING;
                                                                break;
                                                            case com.veryfit.multi.nativeprotocol.b.A0 /* 186 */:
                                                            default:
                                                                return;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
            } else {
                settingType = SettingCallBack.SettingType.SPORT_SORT_V3;
            }
        }
        a(i2, settingType);
    }

    private static void a(int i, SettingCallBack.SettingType settingType) {
        SettingCallBack.a(Boolean.valueOf(i == 0), settingType, null);
    }

    public static void a(int i, byte[] bArr, int i2) {
        String strD;
        SettingCallBack.SettingType settingType;
        String strD2;
        SettingCallBack.SettingType settingType2;
        boolean zValueOf;
        SettingCallBack.SettingType settingType3;
        if (i == 126) {
            BloodPressureAdjustDeviceReplyInfo bloodPressureAdjustDeviceReplyInfo = (BloodPressureAdjustDeviceReplyInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), BloodPressureAdjustDeviceReplyInfo.class);
            if (bloodPressureAdjustDeviceReplyInfo != null) {
                QueryStatusCallBack.onQueryBloodAdjustResult(bloodPressureAdjustDeviceReplyInfo.toResult());
                return;
            }
            return;
        }
        if (i == 5052) {
            strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set Menstruation HistoricalData JsonString] " + strD);
            settingType = SettingCallBack.SettingType.MENSTRUATION_HISTORICAL_DATA;
        } else {
            if (i != 5053) {
                if (i == 128) {
                    PressCalibrationReplyInfo pressCalibrationReplyInfo = (PressCalibrationReplyInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), PressCalibrationReplyInfo.class);
                    if (pressCalibrationReplyInfo != null) {
                        SetPressCalibrationCallBack.onSetPressCalibrationResult(pressCalibrationReplyInfo.toResult());
                        return;
                    }
                    return;
                }
                if (i == 5010) {
                    String strD3 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle Device Return V3HeartRateMode JsonString] " + strD3);
                    HeartRateMeasureModeV3 heartRateMeasureModeV3 = (HeartRateMeasureModeV3) new Gson().fromJson(strD3, HeartRateMeasureModeV3.class);
                    if (heartRateMeasureModeV3 != null) {
                        SettingCallBack.a(true, SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3, heartRateMeasureModeV3);
                        return;
                    } else {
                        zValueOf = false;
                        settingType3 = SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3;
                    }
                } else {
                    if (i == 5011) {
                        String strD4 = com.ido.ble.common.c.d(bArr);
                        LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle Device Return MusicControlInfo JsonString] " + strD4);
                        d(strD4, SettingCallBack.SettingType.MUSIC_CONTROL_INFO);
                        return;
                    }
                    if (i != 5017) {
                        if (i == 5013) {
                            a(i2, SettingCallBack.SettingType.SPORT_SORT_V3);
                            return;
                        }
                        if (i == 408) {
                            strD2 = com.ido.ble.common.c.d(bArr);
                            LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle Device Return restore factory JsonString] " + strD2);
                            settingType2 = SettingCallBack.SettingType.RESTORE_FACTORY;
                        } else {
                            if (i == 174) {
                                String strD5 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle Device Return set phone voice JsonString] " + strD5);
                                c(strD5, SettingCallBack.SettingType.PHONE_VOICE);
                                return;
                            }
                            if (i == 5020) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle Device Return set quick reply info JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.QUICK_REPLY_INFO;
                            } else if (i == 177) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set medicine reminder JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.MEDICINE_REMINDER;
                            } else if (i == 175) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set wash hand reminder JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.WASH_HAND_REMINDER;
                            } else if (i == 6500) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set weather city name JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.WEATHER_CITY_NAME;
                            } else if (i == 184) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set sleep monitoring para JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.SLEEP_MONITORING;
                            } else if (i == 185) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set night temperature monitoring para JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.NIGHT_TEMPERATURE_MONITORING;
                            } else if (i == 186) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set fitness guidance para JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.FITNESS_GUIDANCE;
                            } else if (i == 188) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set Device Unread Reminder para JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.DEVICE_UNREAD_REMINDER;
                            } else if (i == 5047) {
                                strD = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set Walk realTime Reminder para JsonString] " + strD);
                                settingType = SettingCallBack.SettingType.WALK_REAL_TIME_REMINDER;
                            } else if (i == 190) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set schedule reminder switch para JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.SCHEDULE_REMINDER_SWITCH;
                            } else if (i == 183) {
                                strD2 = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set noise para JsonString] " + strD2);
                                settingType2 = SettingCallBack.SettingType.NOISE;
                            } else if (i == 5046) {
                                strD = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set world time JsonString] " + strD);
                                settingType = SettingCallBack.SettingType.WORLD_TIME;
                            } else if (i == 182) {
                                strD = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set heart rate smart JsonString] " + strD);
                                settingType = SettingCallBack.SettingType.HEART_RATE_SMART;
                            } else if (i == 511) {
                                strD = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set Icon Information JsonString] " + strD);
                                settingType = SettingCallBack.SettingType.ICON_INFORMATION_NOTICE;
                            } else {
                                if (i != 129) {
                                    return;
                                }
                                strD = com.ido.ble.common.c.d(bArr);
                                LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set stop find phone JsonString] " + strD);
                                settingType = SettingCallBack.SettingType.STOP_FIND_PHONE;
                            }
                        }
                        b(strD2, settingType2);
                        return;
                    }
                    String strD6 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle Device Return setAlarmV3 JsonString] " + strD6);
                    AlarmV3CmdParaWrapper.AlarmSetResponse alarmSetResponse = (AlarmV3CmdParaWrapper.AlarmSetResponse) new Gson().fromJson(strD6, AlarmV3CmdParaWrapper.AlarmSetResponse.class);
                    if (alarmSetResponse == null) {
                        zValueOf = false;
                    } else {
                        zValueOf = Boolean.valueOf(alarmSetResponse.status == 0);
                    }
                    settingType3 = SettingCallBack.SettingType.ALARM_V3;
                }
                SettingCallBack.a(zValueOf, settingType3, null);
                return;
            }
            strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle set WatchDial Order JsonString] " + strD);
            settingType = SettingCallBack.SettingType.WATCH_DIAL_ORDER;
        }
        a(strD, settingType);
    }

    private static void a(String str, SettingCallBack.SettingType settingType) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("err_code");
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.f4637e + e2.getMessage());
            iOptInt = -1;
        }
        SettingCallBack.a(Boolean.valueOf(iOptInt == 0), settingType, null);
    }

    static boolean a(int i) {
        if (i == 128 || i == 129 || i == 150 || i == 151 || i == 174 || i == 175 || i == 5010 || i == 5011 || i == 5046 || i == 5047) {
            return true;
        }
        switch (i) {
            case 3:
            case 154:
            case 171:
            case 177:
            case 188:
            case com.veryfit.multi.nativeprotocol.b.D0 /* 190 */:
            case 408:
            case 511:
            case 5013:
            case com.veryfit.multi.nativeprotocol.b.u3 /* 5017 */:
            case 5020:
            case com.veryfit.multi.nativeprotocol.b.k4 /* 6500 */:
                return true;
            default:
                switch (i) {
                    case 100:
                    case 101:
                    case 102:
                    case 103:
                    case 104:
                    case 105:
                    case 106:
                    case 107:
                    case 108:
                    case 109:
                        return true;
                    default:
                        switch (i) {
                            case 111:
                            case 112:
                            case 113:
                            case 114:
                            case 115:
                            case 116:
                            case 117:
                            case 118:
                            case 119:
                            case 120:
                            case 121:
                            case 122:
                            case 123:
                            case 124:
                            case 125:
                            case 126:
                                return true;
                            default:
                                switch (i) {
                                    case 165:
                                    case 166:
                                    case 167:
                                    case 168:
                                        return true;
                                    default:
                                        switch (i) {
                                            case 182:
                                            case 183:
                                            case 184:
                                            case 185:
                                            case com.veryfit.multi.nativeprotocol.b.A0 /* 186 */:
                                                return true;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
        }
    }

    private static void b(String str, SettingCallBack.SettingType settingType) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("is_success");
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.f4637e + e2.getMessage());
            iOptInt = 0;
        }
        SettingCallBack.a(Boolean.valueOf(iOptInt == 1), settingType, null);
    }

    private static void c(String str, SettingCallBack.SettingType settingType) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("state");
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.f4637e + e2.getMessage());
            iOptInt = 0;
        }
        SettingCallBack.a(Boolean.valueOf(iOptInt == 1), settingType, null);
    }

    private static void d(String str, SettingCallBack.SettingType settingType) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt(NotificationCompat.CATEGORY_STATUS);
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.f4637e + e2.getMessage());
            iOptInt = 0;
        }
        SettingCallBack.a(Boolean.valueOf(iOptInt == 1), settingType, null);
    }
}