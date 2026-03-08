package com.ido.life.module.home;

import android.database.Cursor;
import android.graphics.Color;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.RadiusProgressBar;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.HomeCard;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.home.fitness.FitnessHelperKt;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0001\u001a\u000e\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e\u001a\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u001a\u0010\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010\u0015\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010\u0016\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010\u0017\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010\u0018\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010\u0019\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010\u001a\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001\u001a\u0016\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001\u001a\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0006\u0010#\u001a\u00020\u0001\u001a\u001a\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0001\u001a\u0006\u0010&\u001a\u00020\u0003\u001a\u001a\u0010'\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0001\u001a\u000e\u0010)\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010*\u001a\u00020\u00102\b\u0010+\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0001\u001a\u0016\u0010-\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0001\u001a\u0010\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010/\u001a\b\u0012\u0004\u0012\u000200012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00102\u001a\u00020\t\u001a\u0010\u00103\u001a\u0004\u0018\u0001042\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u00105\u001a\u0004\u0018\u0001002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0001\u001a$\u00106\u001a\b\u0012\u0004\u0012\u000207012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001\u001a\u0018\u00108\u001a\u0004\u0018\u0001002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0001\u001a\u0010\u00109\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0010\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0006\u0010<\u001a\u00020\u0003\u001a\u0006\u0010=\u001a\u00020\u0003\u001a\u0006\u0010>\u001a\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"TAG", "", "adjustHomeCard", "", "userId", "", "calculateMensState", "", "mensCycle", "", "mensLength", "mensCycleStartDate", "checkAndFixViseItem", "volumeData", "Lcom/ido/life/database/model/HealthVolumeData;", "closeCursor", "", "cursor", "Landroid/database/Cursor;", "deviceSupportMenstrual", "deviceMac", "deviceSupportMenstrualNotifyFlag", "deviceSupportOxy", "deviceSupportOxygenUptake", "deviceSupportPregnancyRemind", "deviceSupportPressure", "deviceSupportVolume", "getDayCount", "startDate", "endDate", "getDeviceInfo", "Lcom/ido/life/data/api/entity/DeviceListEntity$DeviceInfo;", "getOxygenUpdatePropertyList", "", "Lcom/ido/life/customview/RadiusProgressBar$DividerProperty;", "getToday", "hasLifeCycle", "month", "hasLogin", "hasOxygenUpdateData", "date", "historyDataPullSuccess", "printAndSave", "message", "tag", "queryHistoryDataDownloadSuccess", "dataName", "queryLatestLifeCycle", "Lcom/ido/life/database/model/LifeCycleItemBean;", "", "dataCount", "queryLatestWholeLifeCycle", "Lcom/ido/life/module/home/WholeLifeCycleInfo;", "queryLifeCycle", "queryOxygenByDateArea", "Lcom/ido/life/database/model/SportHealth;", "queryPreLifeCycle", "queryRecentAmbientVolume", "queryRecentHeartRate", "Lcom/ido/life/database/model/ServerHeartRateDayData;", "supportActiveCalorieTarget", "supportActiveTimeTarget", "supportWalkTarget", "app_release"}, k = 2, mv = {1, 1, 16})
public final class HomeHelperKt {
    private static final String TAG = "HomeHelper";

    public static final boolean checkAndFixViseItem(HealthVolumeData volumeData) {
        int i;
        Object objValueOf;
        Intrinsics.checkParameterIsNotNull(volumeData, "volumeData");
        String items = volumeData.getItems();
        if (items == null || items.length() == 0) {
            return false;
        }
        try {
            List list = (List) new Gson().fromJson(volumeData.getItems(), new TypeToken<List<? extends List<? extends Integer>>>() { // from class: com.ido.life.module.home.HomeHelperKt$checkAndFixViseItem$itemList$1
            }.getType());
            List list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            List<List> list3 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            int i2 = 0;
            int i3 = 0;
            int i4 = -1;
            int i5 = -1;
            int i6 = -1;
            int i7 = -1;
            for (List list4 : list3) {
                int iIntValue = ((Number) list4.get(0)).intValue();
                int iIntValue2 = ((Number) list4.get(1)).intValue();
                arrayList.add(CollectionsKt.mutableListOf(Integer.valueOf(iIntValue2), Integer.valueOf(iIntValue)));
                if (1 <= iIntValue && 120 >= iIntValue) {
                    int iMax = Math.max(i4, iIntValue);
                    if (i5 != -1) {
                        iIntValue = Math.min(iIntValue, i5);
                    }
                    i4 = iMax;
                    i5 = iIntValue;
                } else {
                    i2++;
                }
                if (1 <= iIntValue2 && 120 >= iIntValue2) {
                    int iMax2 = Math.max(i7, iIntValue2);
                    if (i6 != -1) {
                        iIntValue2 = Math.min(iIntValue2, i6);
                    }
                    i7 = iMax2;
                    i = i3;
                    objValueOf = Unit.INSTANCE;
                    i6 = iIntValue2;
                } else {
                    i = i3 + 1;
                    objValueOf = Integer.valueOf(i3);
                }
                arrayList2.add(objValueOf);
                i3 = i;
            }
            if (i2 >= i3 + 10) {
                return false;
            }
            if (i4 == volumeData.getMaxValue() && i5 == volumeData.getMinValue()) {
                volumeData.setItems(GsonUtil.toJson(arrayList));
            } else {
                if (i7 == volumeData.getMaxValue() && i6 == volumeData.getMinValue()) {
                    return false;
                }
                if (i4 >= 0 && 120 >= i4 && i5 >= 0 && 120 >= i5 && (i7 < 0 || 120 < i7 || i6 < 0 || 120 < i6)) {
                    volumeData.setItems(GsonUtil.toJson(arrayList));
                } else {
                    if (i4 < 0 || 120 < i4 || i5 < 0 || 120 < i5) {
                        if (i7 >= 0 && 120 >= i7 && i6 >= 0 && 120 >= i6) {
                            return false;
                        }
                    }
                    if (i5 <= i6 && i2 >= i3) {
                        return false;
                    }
                    volumeData.setItems(GsonUtil.toJson(arrayList));
                }
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static final boolean deviceSupportMenstrual(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(str);
        return supportFunctionInfo != null && supportFunctionInfo.ex_main3_menstruation;
    }

    public static final boolean deviceSupportPregnancyRemind(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(str);
        return supportFunctionInfo != null && supportFunctionInfo.V3_v2_menstrual_remind_02_add_pregnancy;
    }

    public static final boolean deviceSupportMenstrualNotifyFlag(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(str);
        return supportFunctionInfo != null && supportFunctionInfo.V3_menstrual_add_notify_flag;
    }

    public static final boolean supportActiveCalorieTarget() {
        SupportFunctionInfo supportFunctionInfo;
        return BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null && supportFunctionInfo.ex_main3_calorie_goal;
    }

    public static final boolean supportActiveTimeTarget() {
        SupportFunctionInfo supportFunctionInfo;
        return BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null && supportFunctionInfo.V3_set_mid_high_time_goal;
    }

    public static final boolean supportWalkTarget() {
        SupportFunctionInfo supportFunctionInfo;
        if (BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null) {
            return supportFunctionInfo.V3_set_walk_reminder_goal_time || supportFunctionInfo.support_set_fitness_guidance;
        }
        return false;
    }

    public static final DeviceListEntity.DeviceInfo getDeviceInfo(String deviceMac) {
        Intrinsics.checkParameterIsNotNull(deviceMac, "deviceMac");
        boolean z = true;
        if (deviceMac.length() == 0) {
            return null;
        }
        List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
        List<DeviceListEntity.DeviceInfo> list = deviceList;
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        if (z) {
            return null;
        }
        for (DeviceListEntity.DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null) {
                String mac = deviceInfo.getMac();
                Intrinsics.checkExpressionValueIsNotNull(mac, "device.mac");
                if (deviceMac.contentEquals(mac)) {
                    List<DeviceInfo> deviceInfoList = SPHelper.getDeviceInfoList();
                    if (deviceInfoList != null) {
                        for (DeviceInfo deviceInfo2 : deviceInfoList) {
                            if (deviceInfo2 != null && TextUtils.equals(deviceInfo.getDeviceId(), deviceInfo2.getDeviceId())) {
                                if (TextUtils.isEmpty(deviceInfo2.getImageUrl2())) {
                                    deviceInfo.setImageUrl(deviceInfo2.getImageUrl());
                                } else {
                                    deviceInfo.setImageUrl(deviceInfo2.getImageUrl2());
                                }
                                deviceInfo.setImageUrl3(deviceInfo2.getImageUrl3());
                                deviceInfo.setFaceOffsetTop(deviceInfo2.getFaceOffsetTop());
                                deviceInfo.setFaceOffesetLeft(deviceInfo2.getFaceOffesetLeft());
                                deviceInfo.setFaceWidth(deviceInfo2.getFaceWidth());
                                deviceInfo.setFaceHeight(deviceInfo2.getFaceHeight());
                            }
                        }
                    }
                    return deviceInfo;
                }
            }
        }
        return null;
    }

    public static /* synthetic */ boolean hasLifeCycle$default(long j, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return hasLifeCycle(j, str);
    }

    public static final boolean hasLifeCycle(long j, String str) {
        String str2;
        String str3 = str;
        if (str3 == null || str3.length() == 0) {
            str2 = "\n       select MONTH from LifeCycle where USER_ID=" + j + " limit 1 \n    ";
        } else {
            str2 = "\n       select MONTH from LifeCycle where USER_ID=" + j + " and MONTH='" + str + "' limit 1 \n    ";
        }
        Cursor cursor = GreenDaoUtil.execSql(str2, null);
        Intrinsics.checkExpressionValueIsNotNull(cursor, "cursor");
        boolean z = cursor.getCount() > 0;
        closeCursor(cursor);
        return z;
    }

    public static final void closeCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    public static final boolean historyDataPullSuccess(long j) {
        if (j == -1) {
            return true;
        }
        List<DataDownLoadState> historyDataDownloadState = GreenDaoUtil.getHistoryDataDownloadState(j);
        if (historyDataDownloadState == null || historyDataDownloadState.size() == 0) {
            return false;
        }
        int size = historyDataDownloadState.size();
        for (int i = 0; i < size; i++) {
            DataDownLoadState item = historyDataDownloadState.get(i);
            Intrinsics.checkExpressionValueIsNotNull(item, "item");
            if (!TextUtils.equals(item.getDataName(), MessageEntity.class.getSimpleName()) && item.getDownloadState() != 3) {
                return false;
            }
        }
        return true;
    }

    public static final boolean queryHistoryDataDownloadSuccess(long j, String dataName) {
        Intrinsics.checkParameterIsNotNull(dataName, "dataName");
        List<DataDownLoadState> listQueryHistoryDataDownloadState = GreenDaoUtil.queryHistoryDataDownloadState(j, dataName);
        List<DataDownLoadState> list = listQueryHistoryDataDownloadState;
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (DataDownLoadState itemState : listQueryHistoryDataDownloadState) {
            Intrinsics.checkExpressionValueIsNotNull(itemState, "itemState");
            if (itemState.getDownloadState() != 3) {
                return false;
            }
        }
        return true;
    }

    public static final LifeCycleItemBean queryLatestLifeCycle(long j) {
        boolean z = true;
        List<LifeCycleItemBean> listQueryLatestLifeCycle = queryLatestLifeCycle(j, 1);
        List<LifeCycleItemBean> list = listQueryLatestLifeCycle;
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        if (z) {
            return null;
        }
        return (LifeCycleItemBean) CollectionsKt.first((List) listQueryLatestLifeCycle);
    }

    public static final WholeLifeCycleInfo queryLatestWholeLifeCycle(long j) {
        LifeCycleItemBean lifeCycleItemBeanQueryLatestLifeCycle = queryLatestLifeCycle(j);
        if (lifeCycleItemBeanQueryLatestLifeCycle != null) {
            Calendar calendar = Calendar.getInstance();
            try {
                List<List<Integer>> itemList = lifeCycleItemBeanQueryLatestLifeCycle.getItemList();
                Intrinsics.checkExpressionValueIsNotNull(itemList, "latestLifeCycle.itemList");
                if (itemList.size() > 1) {
                    CollectionsKt.sortWith(itemList, new Comparator<T>() { // from class: com.ido.life.module.home.HomeHelperKt$queryLatestWholeLifeCycle$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            List it = (List) t;
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            Integer num = (Integer) CollectionsKt.first(it);
                            List it2 = (List) t2;
                            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                            return ComparisonsKt.compareValues(num, (Integer) CollectionsKt.first(it2));
                        }
                    });
                }
                List<List<Integer>> itemList2 = lifeCycleItemBeanQueryLatestLifeCycle.getItemList();
                Intrinsics.checkExpressionValueIsNotNull(itemList2, "latestLifeCycle.itemList");
                List lastItemList = (List) CollectionsKt.last((List) itemList2);
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(lifeCycleItemBeanQueryLatestLifeCycle.getMonth(), DateUtil.DATE_FORMAT_YM_3));
                Intrinsics.checkExpressionValueIsNotNull(lastItemList, "lastItemList");
                if (Intrinsics.compare(((Number) CollectionsKt.first(lastItemList)).intValue(), 1) > 0) {
                    Object objFirst = CollectionsKt.first((List<? extends Object>) lastItemList);
                    Intrinsics.checkExpressionValueIsNotNull(objFirst, "lastItemList.first()");
                    calendar.set(5, ((Number) objFirst).intValue());
                    String mensStartDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                    Object objLast = CollectionsKt.last((List<? extends Object>) lastItemList);
                    Intrinsics.checkExpressionValueIsNotNull(objLast, "lastItemList.last()");
                    calendar.set(5, ((Number) objLast).intValue());
                    String mensEndDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                    Intrinsics.checkExpressionValueIsNotNull(mensStartDate, "mensStartDate");
                    Intrinsics.checkExpressionValueIsNotNull(mensEndDate, "mensEndDate");
                    return new WholeLifeCycleInfo(mensStartDate, mensEndDate, lifeCycleItemBeanQueryLatestLifeCycle.getMensesDays(), lifeCycleItemBeanQueryLatestLifeCycle.getMensesCycle(), lifeCycleItemBeanQueryLatestLifeCycle.getTimeStamp(), lifeCycleItemBeanQueryLatestLifeCycle.getUpload());
                }
                Object objLast2 = CollectionsKt.last((List<? extends Object>) lastItemList);
                Intrinsics.checkExpressionValueIsNotNull(objLast2, "lastItemList.last()");
                calendar.set(5, ((Number) objLast2).intValue());
                String mensEndDate2 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                calendar.set(5, 1);
                calendar.add(5, -1);
                LifeCycleItemBean lifeCycleItemBeanQueryLifeCycleItemBeanByDate = GreenDaoUtil.queryLifeCycleItemBeanByDate(j, DateUtil.format(calendar, DateUtil.DATE_FORMAT_YM_3));
                if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate != null) {
                    List<List<Integer>> itemList3 = lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getItemList();
                    if (!(itemList3 == null || itemList3.isEmpty())) {
                        List<List<Integer>> itemList4 = lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getItemList();
                        Intrinsics.checkExpressionValueIsNotNull(itemList4, "preLifeCycle.itemList");
                        if (itemList4.size() > 1) {
                            CollectionsKt.sortWith(itemList4, new Comparator<T>() { // from class: com.ido.life.module.home.HomeHelperKt$queryLatestWholeLifeCycle$$inlined$sortBy$2
                                @Override // java.util.Comparator
                                public final int compare(T t, T t2) {
                                    List it = (List) t;
                                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                    Integer num = (Integer) CollectionsKt.first(it);
                                    List it2 = (List) t2;
                                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                                    return ComparisonsKt.compareValues(num, (Integer) CollectionsKt.first(it2));
                                }
                            });
                        }
                        List<List<Integer>> itemList5 = lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getItemList();
                        Intrinsics.checkExpressionValueIsNotNull(itemList5, "preLifeCycle.itemList");
                        List preLifeCycleLastList = (List) CollectionsKt.last((List) itemList5);
                        Intrinsics.checkExpressionValueIsNotNull(preLifeCycleLastList, "preLifeCycleLastList");
                        Integer num = (Integer) CollectionsKt.last(preLifeCycleLastList);
                        int actualMaximum = calendar.getActualMaximum(5);
                        if (num != null && num.intValue() == actualMaximum) {
                            Object objFirst2 = CollectionsKt.first((List<? extends Object>) preLifeCycleLastList);
                            Intrinsics.checkExpressionValueIsNotNull(objFirst2, "preLifeCycleLastList.first()");
                            calendar.set(5, ((Number) objFirst2).intValue());
                        } else {
                            calendar.add(5, 1);
                        }
                        String mensStartDate2 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                        Intrinsics.checkExpressionValueIsNotNull(mensStartDate2, "mensStartDate");
                        Intrinsics.checkExpressionValueIsNotNull(mensEndDate2, "mensEndDate");
                        return new WholeLifeCycleInfo(mensStartDate2, mensEndDate2, lifeCycleItemBeanQueryLatestLifeCycle.getMensesDays(), lifeCycleItemBeanQueryLatestLifeCycle.getMensesCycle(), lifeCycleItemBeanQueryLatestLifeCycle.getTimeStamp(), lifeCycleItemBeanQueryLatestLifeCycle.getUpload());
                    }
                }
                calendar.add(5, 1);
                String mensStartDate3 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                Intrinsics.checkExpressionValueIsNotNull(mensStartDate3, "mensStartDate");
                Intrinsics.checkExpressionValueIsNotNull(mensEndDate2, "mensEndDate");
                return new WholeLifeCycleInfo(mensStartDate3, mensEndDate2, lifeCycleItemBeanQueryLatestLifeCycle.getMensesDays(), lifeCycleItemBeanQueryLatestLifeCycle.getMensesCycle(), lifeCycleItemBeanQueryLatestLifeCycle.getTimeStamp(), lifeCycleItemBeanQueryLatestLifeCycle.getUpload());
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static final List<LifeCycleItemBean> queryLatestLifeCycle(long j, int i) {
        ArrayList arrayList;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        String string;
        String str;
        List<List<Integer>> list;
        List<List<Integer>> list2;
        ArrayList arrayList2 = new ArrayList();
        Cursor cursor = GreenDaoUtil.execSql("\n       select * from LifeCycle where USER_ID=" + j + " order by MONTH desc\n    ", null);
        Intrinsics.checkExpressionValueIsNotNull(cursor, "cursor");
        if (cursor.getCount() > 0) {
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("USER_ID");
            int columnIndex3 = cursor.getColumnIndex("MONTH");
            int columnIndex4 = cursor.getColumnIndex("MENSES_CYCLE");
            int columnIndex5 = cursor.getColumnIndex("MENSES_DAYS");
            int columnIndex6 = cursor.getColumnIndex("SOURCE_MAC");
            int columnIndex7 = cursor.getColumnIndex("DEVICE_NAME");
            int columnIndex8 = cursor.getColumnIndex("ITEM_LIST");
            int columnIndex9 = cursor.getColumnIndex("TIME_STAMP");
            int columnIndex10 = cursor.getColumnIndex("MENSES_START_DAY");
            int columnIndex11 = cursor.getColumnIndex("OVULATION_DAY");
            int columnIndex12 = cursor.getColumnIndex("PREGNANCY_DAY_BEFORE_REMIND");
            int columnIndex13 = cursor.getColumnIndex("REMINDER_TIME");
            ArrayList arrayList3 = arrayList2;
            int columnIndex14 = cursor.getColumnIndex("UPLOAD");
            int columnIndex15 = cursor.getColumnIndex("NEED_SYNC_TO_DEVICE");
            while (true) {
                if (!cursor.moveToNext()) {
                    arrayList = arrayList3;
                    break;
                }
                try {
                    string = cursor.getString(columnIndex8);
                    str = string;
                } catch (Exception e2) {
                    e = e2;
                    i2 = columnIndex15;
                    i3 = columnIndex8;
                }
                if (str == null || str.length() == 0) {
                    continue;
                } else {
                    i3 = columnIndex8;
                    try {
                        i2 = columnIndex15;
                    } catch (Exception e3) {
                        e = e3;
                        i2 = columnIndex15;
                        i4 = columnIndex11;
                        i5 = columnIndex12;
                        i6 = columnIndex14;
                        i7 = columnIndex13;
                        arrayList = arrayList3;
                        e.printStackTrace();
                        arrayList3 = arrayList;
                        columnIndex15 = i2;
                        columnIndex13 = i7;
                        columnIndex14 = i6;
                        columnIndex12 = i5;
                        columnIndex11 = i4;
                        columnIndex8 = i3;
                    }
                    try {
                        list = (List) new Gson().fromJson(string, new TypeToken<List<? extends List<? extends Integer>>>() { // from class: com.ido.life.module.home.HomeHelperKt$queryLatestLifeCycle$itemList$1
                        }.getType());
                        list2 = list;
                    } catch (Exception e4) {
                        e = e4;
                        i4 = columnIndex11;
                        i5 = columnIndex12;
                        i6 = columnIndex14;
                        i7 = columnIndex13;
                        arrayList = arrayList3;
                        e.printStackTrace();
                        arrayList3 = arrayList;
                        columnIndex15 = i2;
                        columnIndex13 = i7;
                        columnIndex14 = i6;
                        columnIndex12 = i5;
                        columnIndex11 = i4;
                        columnIndex8 = i3;
                    }
                    if (list2 == null || list2.isEmpty()) {
                        columnIndex8 = i3;
                        columnIndex15 = i2;
                    } else {
                        LifeCycleItemBean lifeCycleItemBean = new LifeCycleItemBean();
                        lifeCycleItemBean.setId(Long.valueOf(cursor.getLong(columnIndex)));
                        i4 = columnIndex11;
                        int i8 = columnIndex12;
                        try {
                            lifeCycleItemBean.setUserId(cursor.getLong(columnIndex2));
                            lifeCycleItemBean.setMonth(cursor.getString(columnIndex3));
                            lifeCycleItemBean.setMensesCycle(cursor.getInt(columnIndex4));
                            lifeCycleItemBean.setMensesDays(cursor.getInt(columnIndex5));
                            lifeCycleItemBean.setSourceMac(cursor.getString(columnIndex6));
                            lifeCycleItemBean.setDeviceName(cursor.getString(columnIndex7));
                            lifeCycleItemBean.setItemList(list);
                            lifeCycleItemBean.setTimeStamp(cursor.getLong(columnIndex9));
                            lifeCycleItemBean.setMensesStartDay(cursor.getInt(columnIndex10));
                            lifeCycleItemBean.setOvulationDay(cursor.getInt(i4));
                            i5 = i8;
                            try {
                                lifeCycleItemBean.setPregnancyDayBeforeRemind(cursor.getInt(i5));
                                lifeCycleItemBean.setReminderTime(cursor.getString(columnIndex13));
                                i6 = columnIndex14;
                                try {
                                    lifeCycleItemBean.setUpload(cursor.getLong(i6) == 1);
                                    i7 = columnIndex13;
                                    try {
                                        lifeCycleItemBean.setNeedSyncToDevice(cursor.getLong(i2) == 1);
                                        i2 = i2;
                                        arrayList = arrayList3;
                                    } catch (Exception e5) {
                                        e = e5;
                                        i2 = i2;
                                        arrayList = arrayList3;
                                        e.printStackTrace();
                                        arrayList3 = arrayList;
                                        columnIndex15 = i2;
                                        columnIndex13 = i7;
                                        columnIndex14 = i6;
                                        columnIndex12 = i5;
                                        columnIndex11 = i4;
                                        columnIndex8 = i3;
                                    }
                                } catch (Exception e6) {
                                    e = e6;
                                    i7 = columnIndex13;
                                    arrayList = arrayList3;
                                    e.printStackTrace();
                                    arrayList3 = arrayList;
                                    columnIndex15 = i2;
                                    columnIndex13 = i7;
                                    columnIndex14 = i6;
                                    columnIndex12 = i5;
                                    columnIndex11 = i4;
                                    columnIndex8 = i3;
                                }
                            } catch (Exception e7) {
                                e = e7;
                                i6 = columnIndex14;
                                i7 = columnIndex13;
                                arrayList = arrayList3;
                                e.printStackTrace();
                                arrayList3 = arrayList;
                                columnIndex15 = i2;
                                columnIndex13 = i7;
                                columnIndex14 = i6;
                                columnIndex12 = i5;
                                columnIndex11 = i4;
                                columnIndex8 = i3;
                            }
                        } catch (Exception e8) {
                            e = e8;
                            i6 = columnIndex14;
                            i7 = columnIndex13;
                            arrayList = arrayList3;
                            i5 = i8;
                        }
                        try {
                            arrayList.add(lifeCycleItemBean);
                            if (arrayList.size() == i) {
                                break;
                            }
                        } catch (Exception e9) {
                            e = e9;
                            e.printStackTrace();
                        }
                        arrayList3 = arrayList;
                        columnIndex15 = i2;
                        columnIndex13 = i7;
                        columnIndex14 = i6;
                        columnIndex12 = i5;
                        columnIndex11 = i4;
                        columnIndex8 = i3;
                    }
                }
            }
        } else {
            arrayList = arrayList2;
        }
        closeCursor(cursor);
        return arrayList;
    }

    public static final LifeCycleItemBean queryLifeCycle(long j, String month) {
        Intrinsics.checkParameterIsNotNull(month, "month");
        Cursor cursor = GreenDaoUtil.execSql("\n       select * from LifeCycle where USER_ID=" + j + " and MONTH='" + month + "' order by MONTH desc\n    ", null);
        Intrinsics.checkExpressionValueIsNotNull(cursor, "cursor");
        if (cursor.getCount() > 0) {
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("USER_ID");
            int columnIndex3 = cursor.getColumnIndex("MONTH");
            int columnIndex4 = cursor.getColumnIndex("MENSES_CYCLE");
            int columnIndex5 = cursor.getColumnIndex("MENSES_DAYS");
            int columnIndex6 = cursor.getColumnIndex("SOURCE_MAC");
            int columnIndex7 = cursor.getColumnIndex("DEVICE_NAME");
            int columnIndex8 = cursor.getColumnIndex("ITEM_LIST");
            int columnIndex9 = cursor.getColumnIndex("TIME_STAMP");
            int columnIndex10 = cursor.getColumnIndex("MENSES_START_DAY");
            int columnIndex11 = cursor.getColumnIndex("OVULATION_DAY");
            int columnIndex12 = cursor.getColumnIndex("PREGNANCY_DAY_BEFORE_REMIND");
            int columnIndex13 = cursor.getColumnIndex("REMINDER_TIME");
            int columnIndex14 = cursor.getColumnIndex("UPLOAD");
            int columnIndex15 = cursor.getColumnIndex("NEED_SYNC_TO_DEVICE");
            if (cursor.moveToNext()) {
                try {
                    String string = cursor.getString(columnIndex8);
                    String str = string;
                    if (str == null || str.length() == 0) {
                        return null;
                    }
                    List<List<Integer>> list = (List) new Gson().fromJson(string, new TypeToken<List<? extends List<? extends Integer>>>() { // from class: com.ido.life.module.home.HomeHelperKt$queryLifeCycle$itemList$1
                    }.getType());
                    List<List<Integer>> list2 = list;
                    if (list2 == null || list2.isEmpty()) {
                        return null;
                    }
                    LifeCycleItemBean lifeCycleItemBean = new LifeCycleItemBean();
                    lifeCycleItemBean.setId(Long.valueOf(cursor.getLong(columnIndex)));
                    lifeCycleItemBean.setUserId(cursor.getLong(columnIndex2));
                    lifeCycleItemBean.setMonth(cursor.getString(columnIndex3));
                    lifeCycleItemBean.setMensesCycle(cursor.getInt(columnIndex4));
                    lifeCycleItemBean.setMensesDays(cursor.getInt(columnIndex5));
                    lifeCycleItemBean.setSourceMac(cursor.getString(columnIndex6));
                    lifeCycleItemBean.setDeviceName(cursor.getString(columnIndex7));
                    lifeCycleItemBean.setItemList(list);
                    lifeCycleItemBean.setTimeStamp(cursor.getLong(columnIndex9));
                    lifeCycleItemBean.setMensesStartDay(cursor.getInt(columnIndex10));
                    lifeCycleItemBean.setOvulationDay(cursor.getInt(columnIndex11));
                    lifeCycleItemBean.setPregnancyDayBeforeRemind(cursor.getInt(columnIndex12));
                    lifeCycleItemBean.setReminderTime(cursor.getString(columnIndex13));
                    lifeCycleItemBean.setUpload(cursor.getLong(columnIndex14) == 1);
                    lifeCycleItemBean.setNeedSyncToDevice(cursor.getLong(columnIndex15) == 1);
                    return lifeCycleItemBean;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        FitnessHelperKt.closeCursor(cursor);
        return null;
    }

    public static final LifeCycleItemBean queryPreLifeCycle(long j, String month) {
        Intrinsics.checkParameterIsNotNull(month, "month");
        Cursor cursor = GreenDaoUtil.execSql("\n       select * from LifeCycle where USER_ID=" + j + " and MONTH<'" + month + "' order by MONTH desc limit 1\n    ", null);
        Intrinsics.checkExpressionValueIsNotNull(cursor, "cursor");
        if (cursor.getCount() > 0) {
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("USER_ID");
            int columnIndex3 = cursor.getColumnIndex("MONTH");
            int columnIndex4 = cursor.getColumnIndex("MENSES_CYCLE");
            int columnIndex5 = cursor.getColumnIndex("MENSES_DAYS");
            int columnIndex6 = cursor.getColumnIndex("SOURCE_MAC");
            int columnIndex7 = cursor.getColumnIndex("DEVICE_NAME");
            int columnIndex8 = cursor.getColumnIndex("ITEM_LIST");
            int columnIndex9 = cursor.getColumnIndex("TIME_STAMP");
            int columnIndex10 = cursor.getColumnIndex("MENSES_START_DAY");
            int columnIndex11 = cursor.getColumnIndex("OVULATION_DAY");
            int columnIndex12 = cursor.getColumnIndex("PREGNANCY_DAY_BEFORE_REMIND");
            int columnIndex13 = cursor.getColumnIndex("REMINDER_TIME");
            int columnIndex14 = cursor.getColumnIndex("UPLOAD");
            int columnIndex15 = cursor.getColumnIndex("NEED_SYNC_TO_DEVICE");
            if (cursor.moveToNext()) {
                try {
                    String string = cursor.getString(columnIndex8);
                    String str = string;
                    if (str == null || str.length() == 0) {
                        return null;
                    }
                    List<List<Integer>> list = (List) new Gson().fromJson(string, new TypeToken<List<? extends List<? extends Integer>>>() { // from class: com.ido.life.module.home.HomeHelperKt$queryPreLifeCycle$itemList$1
                    }.getType());
                    List<List<Integer>> list2 = list;
                    if (list2 == null || list2.isEmpty()) {
                        return null;
                    }
                    LifeCycleItemBean lifeCycleItemBean = new LifeCycleItemBean();
                    lifeCycleItemBean.setId(Long.valueOf(cursor.getLong(columnIndex)));
                    lifeCycleItemBean.setUserId(cursor.getLong(columnIndex2));
                    lifeCycleItemBean.setMonth(cursor.getString(columnIndex3));
                    lifeCycleItemBean.setMensesCycle(cursor.getInt(columnIndex4));
                    lifeCycleItemBean.setMensesDays(cursor.getInt(columnIndex5));
                    lifeCycleItemBean.setSourceMac(cursor.getString(columnIndex6));
                    lifeCycleItemBean.setDeviceName(cursor.getString(columnIndex7));
                    lifeCycleItemBean.setItemList(list);
                    lifeCycleItemBean.setTimeStamp(cursor.getLong(columnIndex9));
                    lifeCycleItemBean.setMensesStartDay(cursor.getInt(columnIndex10));
                    lifeCycleItemBean.setOvulationDay(cursor.getInt(columnIndex11));
                    lifeCycleItemBean.setPregnancyDayBeforeRemind(cursor.getInt(columnIndex12));
                    lifeCycleItemBean.setReminderTime(cursor.getString(columnIndex13));
                    lifeCycleItemBean.setUpload(cursor.getLong(columnIndex14) == 1);
                    lifeCycleItemBean.setNeedSyncToDevice(cursor.getLong(columnIndex15) == 1);
                    return lifeCycleItemBean;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        FitnessHelperKt.closeCursor(cursor);
        return null;
    }

    public static final ServerHeartRateDayData queryRecentHeartRate(long j) {
        ServerHeartRateDayData serverHeartRateDayData = (ServerHeartRateDayData) null;
        Cursor cursorExecSql = GreenDaoUtil.execSql("\n    select DATE,LATEST_VALUE,TIMESTAMP from SERVER_HEART_RATE_DAY_DATA where USER_ID=" + j + " and LATEST_VALUE>0 order by DATE desc limit 1\n", null);
        if (cursorExecSql.moveToNext()) {
            serverHeartRateDayData = new ServerHeartRateDayData();
            serverHeartRateDayData.setUserId(j);
            serverHeartRateDayData.setDate(cursorExecSql.getString(0));
            serverHeartRateDayData.setLatestValue(cursorExecSql.getInt(1));
            serverHeartRateDayData.setTimestamp(cursorExecSql.getLong(2));
        }
        closeCursor(cursorExecSql);
        return serverHeartRateDayData;
    }

    public static final HealthVolumeData queryRecentAmbientVolume(long j) {
        HealthVolumeData healthVolumeData = (HealthVolumeData) null;
        Cursor cursorExecSql = GreenDaoUtil.execSql("\n       select DATE,LATEST_VALUE,TIMESTAMP from HEALTH_VOLUME_DATA where USER_ID=" + j + " and LATEST_VALUE>0 order by DATE desc limit 1\n    ", null);
        if (cursorExecSql.moveToNext()) {
            healthVolumeData = new HealthVolumeData();
            healthVolumeData.setUserId(j);
            healthVolumeData.setDate(cursorExecSql.getString(0));
            healthVolumeData.setLatestValue(cursorExecSql.getInt(1));
            healthVolumeData.timestamp = cursorExecSql.getLong(2);
        }
        FitnessHelperKt.closeCursor(cursorExecSql);
        return healthVolumeData;
    }

    public static final List<SportHealth> queryOxygenByDateArea(long j, String startDate, String endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(endDate, DateUtil.DATE_FORMAT_YMD));
        String str = simpleDateFormat.format(calendar.getTime());
        calendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        String str2 = simpleDateFormat.format(calendar.getTime());
        ArrayList arrayList = new ArrayList();
        Cursor cursorExecSql = GreenDaoUtil.execSql("\n        select DATE_TIME,VO2MAX from SPORT_HEALTH where USER_ID=" + j + " and DATE_TIME>='" + str2 + "' and DATE_TIME<='" + str + "' and VO2MAX>0 order by DATE_TIME desc\n    ", null);
        while (cursorExecSql.moveToNext()) {
            SportHealth sportHealth = new SportHealth();
            sportHealth.setDateTime(cursorExecSql.getString(0));
            sportHealth.setVo2max(cursorExecSql.getInt(1));
            arrayList.add(sportHealth);
        }
        FitnessHelperKt.closeCursor(cursorExecSql);
        return arrayList;
    }

    public static final int getDayCount(String startDate, String endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
        long timeInMillis = calendar.getTimeInMillis();
        calendar.setTime(DateUtil.string2Date(endDate, DateUtil.DATE_FORMAT_YMD));
        return (int) ((calendar.getTimeInMillis() - timeInMillis) / ((long) 86400000));
    }

    public static final boolean deviceSupportOxy(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(str);
        return supportFunctionInfo != null && supportFunctionInfo.ex_main3_v3_spo2_data;
    }

    public static final boolean deviceSupportPressure(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(str);
        return supportFunctionInfo != null && supportFunctionInfo.ex_main3_v3_pressure;
    }

    public static final boolean deviceSupportVolume(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(str);
        return supportFunctionInfo != null && supportFunctionInfo.V3_health_sync_noise;
    }

    public static final boolean deviceSupportOxygenUptake(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(str);
        return supportFunctionInfo != null && supportFunctionInfo.support_recover_time_and_vo2max;
    }

    public static final boolean adjustHomeCard(long j) {
        boolean z;
        boolean z2;
        BLEDevice lastConnectedDeviceInfo;
        HomeCard homeCardQueryHomeCardInfo = GreenDaoUtil.queryHomeCardInfo(j);
        if (homeCardQueryHomeCardInfo == null) {
            return false;
        }
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(j);
        ArrayList valueList = homeCardQueryHomeCardInfo.getValueList();
        ArrayList hideValueList = homeCardQueryHomeCardInfo.getHideValueList();
        if (valueList == null) {
            valueList = new ArrayList();
            homeCardQueryHomeCardInfo.setValueList(valueList);
        }
        if (hideValueList == null) {
            hideValueList = new ArrayList();
            homeCardQueryHomeCardInfo.setHideValueList(hideValueList);
        }
        String str = (String) null;
        if (BLEManager.isBind() && (lastConnectedDeviceInfo = LocalDataManager.getLastConnectedDeviceInfo()) != null) {
            str = lastConnectedDeviceInfo.mDeviceAddress;
        }
        boolean zContains = valueList.contains(1);
        boolean zContains2 = hideValueList.contains(1);
        if (zContains || zContains2) {
            z = false;
        } else {
            hideValueList.add(1);
            homeCardQueryHomeCardInfo.setUploadSuccess(false);
            z = true;
        }
        boolean zContains3 = valueList.contains(3);
        boolean zContains4 = hideValueList.contains(3);
        if (!zContains3 && !zContains4) {
            hideValueList.add(3);
            homeCardQueryHomeCardInfo.setUploadSuccess(false);
            z = true;
        }
        boolean zContains5 = valueList.contains(4);
        boolean zContains6 = hideValueList.contains(4);
        if (!zContains5 && !zContains6) {
            hideValueList.add(4);
            homeCardQueryHomeCardInfo.setUploadSuccess(false);
            z = true;
        }
        boolean zContains7 = valueList.contains(5);
        boolean zContains8 = hideValueList.contains(5);
        if (!zContains7 && !zContains8) {
            hideValueList.add(5);
            homeCardQueryHomeCardInfo.setUploadSuccess(false);
            z = true;
        }
        boolean zContains9 = valueList.contains(6);
        boolean zContains10 = hideValueList.contains(6);
        if (!zContains9 && !zContains10) {
            boolean z3 = (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getPressureCount() > 0) || GreenDaoUtil.queryPressureDataCount(j) > 0;
            if (z3 || deviceSupportPressure(str)) {
                if (z3) {
                    printAndSave("检测到用户有压力数据，即将显示压力卡片", TAG);
                } else {
                    printAndSave("检测到用户没有压力数据，但是最近绑定的设备支持压力检测(" + str + ")，即将显示压力卡片", TAG);
                }
                valueList.add(6);
                homeCardQueryHomeCardInfo.setUploadSuccess(false);
                z = true;
            }
        }
        boolean zContains11 = valueList.contains(7);
        boolean zContains12 = hideValueList.contains(7);
        if (!zContains11 && !zContains12) {
            boolean z4 = (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getBloodOxyCount() > 0) || GreenDaoUtil.getBloodCount(j) > 0;
            if (z4 || deviceSupportOxy(str)) {
                if (z4) {
                    printAndSave("检查到用户有血氧数据，即将显示血氧卡片", TAG);
                } else {
                    printAndSave("检查到用户没有血氧数据，但是用户最近绑定的设备支持血氧检测(" + str + ")，即将显示血氧卡片", TAG);
                }
                valueList.add(7);
                homeCardQueryHomeCardInfo.setUploadSuccess(false);
                z = true;
            }
        }
        boolean zContains13 = valueList.contains(8);
        boolean zContains14 = hideValueList.contains(8);
        if (!zContains13 && !zContains14) {
            hideValueList.add(8);
            homeCardQueryHomeCardInfo.setUploadSuccess(false);
        }
        boolean zContains15 = valueList.contains(11);
        boolean zContains16 = hideValueList.contains(11);
        if (!zContains15 && !zContains16) {
            boolean z5 = (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getNoiseCount() > 0) || GreenDaoUtil.getHealthVolumeCount(j) > 0;
            if (z5 || deviceSupportVolume(str)) {
                if (z5) {
                    printAndSave("检测到用户有音量数据，即将显示音量卡片", TAG);
                } else {
                    printAndSave("检测到用户没有音量数据，但是最近绑定的设备支持音量检测(" + str + "),即将显示音量卡片", TAG);
                }
                valueList.add(11);
                homeCardQueryHomeCardInfo.setUploadSuccess(false);
                z = true;
            }
        } else {
            boolean z6 = (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getNoiseCount() > 0) || GreenDaoUtil.getHealthVolumeCount(j) > 0;
            boolean zDeviceSupportVolume = deviceSupportVolume(str);
            if (!z6 && !zDeviceSupportVolume) {
                printAndSave("移除噪音卡片", TAG);
                if (valueList.contains(11)) {
                    valueList.remove((Object) 11);
                }
                if (hideValueList.contains(11)) {
                    hideValueList.remove((Object) 11);
                }
                homeCardQueryHomeCardInfo.setUploadSuccess(false);
                z = true;
            } else if (z6) {
                printAndSave("检测到用户有音量数据，不移除音量卡片", TAG);
            } else {
                printAndSave("检测到用户没有音量数据，但是最近绑定的设备支持音量检测(" + str + "),不移除音量卡片", TAG);
            }
        }
        boolean zContains17 = valueList.contains(12);
        boolean zContains18 = hideValueList.contains(12);
        if (!zContains17 && !zContains18) {
            boolean zHasOxygenUpdateData$default = hasOxygenUpdateData$default(j, null, 2, null);
            if (zHasOxygenUpdateData$default || deviceSupportOxygenUptake(str)) {
                if (zHasOxygenUpdateData$default) {
                    printAndSave("检测到用户有最大摄氧量数据，即将显示最大摄氧量卡片", TAG);
                } else {
                    printAndSave("检测到用户没有最大摄氧量，但是最近绑定的设备支持最大摄氧量检测(" + str + ")，即将显示最大摄氧量卡片", TAG);
                }
                valueList.add(12);
                homeCardQueryHomeCardInfo.setUploadSuccess(false);
                z2 = true;
            }
            z2 = z;
        } else {
            boolean zHasOxygenUpdateData$default2 = hasOxygenUpdateData$default(j, null, 2, null);
            boolean zDeviceSupportOxygenUptake = deviceSupportOxygenUptake(str);
            if (zHasOxygenUpdateData$default2 || zDeviceSupportOxygenUptake) {
                if (zHasOxygenUpdateData$default2) {
                    printAndSave("检测到用户有最大摄氧量数据，不移除最大摄氧量卡片", TAG);
                } else {
                    printAndSave("检测到用户没有最大摄氧量，但是最近绑定的设备支持最大摄氧量检测(" + str + ")，不移除最大摄氧量卡片", TAG);
                }
                z2 = z;
            } else {
                printAndSave("移除最大摄氧量卡片", TAG);
                if (valueList.contains(12)) {
                    valueList.remove((Object) 12);
                }
                if (hideValueList.contains(12)) {
                    hideValueList.remove((Object) 12);
                }
                homeCardQueryHomeCardInfo.setUploadSuccess(false);
                z2 = true;
            }
        }
        if (z2) {
            try {
                homeCardQueryHomeCardInfo.update();
            } catch (Exception unused) {
                GreenDaoUtil.addHomeCardInfo(homeCardQueryHomeCardInfo);
            }
        }
        return z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int[] calculateMensState(int r7, int r8, java.lang.String r9) {
        /*
            java.lang.String r0 = "mensCycleStartDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            r0 = 3
            int[] r1 = new int[r0]
            r1 = {x0068: FILL_ARRAY_DATA , data: [0, 0, 0} // fill-array
            java.lang.String r2 = getToday()
            int r9 = getDayCount(r9, r2)
            r2 = 1
            int r9 = r9 + r2
            int r9 = r9 % r7
            int r3 = r8 + 19
            r4 = 0
            if (r7 < r3) goto L1d
            r3 = r2
            goto L1e
        L1d:
            r3 = r4
        L1e:
            r5 = 2
            if (r2 <= r9) goto L22
            goto L2b
        L22:
            if (r8 < r9) goto L2b
            r1[r4] = r2
            r1[r2] = r9
            r1[r5] = r9
            goto L67
        L2b:
            if (r3 == 0) goto L3f
            int r8 = r8 + r2
            int r6 = r7 + (-19)
            if (r8 <= r9) goto L33
            goto L3f
        L33:
            if (r6 < r9) goto L3f
            r1[r4] = r5
            int r7 = r7 + (-18)
            int r7 = r7 - r9
            r1[r2] = r7
            r1[r5] = r9
            goto L67
        L3f:
            if (r3 == 0) goto L55
            int r8 = r7 + (-18)
            int r3 = r7 + (-10)
            if (r8 <= r9) goto L48
            goto L55
        L48:
            if (r3 < r9) goto L55
            r1[r4] = r0
            int r7 = r7 + (-19)
            int r7 = r9 - r7
            r1[r2] = r7
            r1[r5] = r9
            goto L67
        L55:
            r8 = 4
            if (r9 != 0) goto L5f
            r1[r4] = r8
            r1[r2] = r2
            r1[r5] = r9
            goto L67
        L5f:
            r1[r4] = r8
            int r7 = r7 - r9
            int r7 = r7 + r2
            r1[r2] = r7
            r1[r5] = r9
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.HomeHelperKt.calculateMensState(int, int, java.lang.String):int[]");
    }

    public static final String getToday() {
        String str = DateUtil.format(Calendar.getInstance(Locale.getDefault()), DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        return str;
    }

    public static final boolean hasLogin() {
        return RunTimeUtil.getInstance().hasLogin();
    }

    public static final List<RadiusProgressBar.DividerProperty> getOxygenUpdatePropertyList(long j) {
        ArrayList arrayList = new ArrayList();
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(j);
        if (userInfoQueryUserInfo == null) {
            return null;
        }
        int iCaluteAge = FitnessHelperKt.caluteAge(userInfoQueryUserInfo.getBirthday());
        if (userInfoQueryUserInfo.getGender() != 1) {
            if (iCaluteAge >= 0 && 29 >= iCaluteAge) {
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 32.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 32.0f, 36.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 36.0f, 39.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 39.0f, 43.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 43.0f, 49.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 49.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
            } else if (30 <= iCaluteAge && 39 >= iCaluteAge) {
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 30.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 30.0f, 34.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 34.0f, 37.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 37.0f, 42.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 42.0f, 47.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 47.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
            } else if (40 <= iCaluteAge && 49 >= iCaluteAge) {
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 29.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 29.0f, 33.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 33.0f, 36.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 36.0f, 39.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 39.0f, 45.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 45.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
            } else if (50 <= iCaluteAge && 59 >= iCaluteAge) {
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 26.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 26.0f, 30.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 30.0f, 33.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 33.0f, 36.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 36.0f, 41.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 41.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
            } else if (60 <= iCaluteAge && 69 >= iCaluteAge) {
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 24.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 24.0f, 27.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 27.0f, 30.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 30.0f, 32.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 32.0f, 37.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 37.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
            } else if (70 <= iCaluteAge && Integer.MAX_VALUE >= iCaluteAge) {
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 23.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 23.0f, 26.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 26.0f, 29.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 29.0f, 31.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 31.0f, 37.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
                arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 37.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
            }
        } else if (iCaluteAge >= 0 && 29 >= iCaluteAge) {
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 38.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 38.0f, 42.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 42.0f, 45.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 45.0f, 51.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 51.0f, 55.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 55.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
        } else if (30 <= iCaluteAge && 39 >= iCaluteAge) {
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 36.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 36.0f, 40.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 40.0f, 44.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 44.0f, 48.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 48.0f, 54.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 54.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
        } else if (40 <= iCaluteAge && 49 >= iCaluteAge) {
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 34.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 34.0f, 38.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 38.0f, 42.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 42.0f, 46.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 46.0f, 52.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 52.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
        } else if (50 <= iCaluteAge && 59 >= iCaluteAge) {
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 32.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 32.0f, 35.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 35.0f, 39.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 39.0f, 43.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 43.0f, 49.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 49.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
        } else if (60 <= iCaluteAge && 69 >= iCaluteAge) {
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 28.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 28.0f, 32.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 32.0f, 35.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 35.0f, 39.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 39.0f, 45.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 45.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
        } else if (70 <= iCaluteAge && Integer.MAX_VALUE >= iCaluteAge) {
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#E64C8B"), 0.0f, 25.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#9067F2"), 25.0f, 29.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#598EFF"), 29.0f, 32.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01B3FE"), 32.0f, 36.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#01CEFE"), 36.0f, 43.0f, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), null, null, 96, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00F2FF"), 43.0f, Integer.MAX_VALUE, 0.16666667f, LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), null, null, 96, null));
        }
        return arrayList;
    }

    public static /* synthetic */ boolean hasOxygenUpdateData$default(long j, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return hasOxygenUpdateData(j, str);
    }

    public static final boolean hasOxygenUpdateData(long j, String str) {
        String str2;
        String str3 = str;
        if (str3 == null || str3.length() == 0) {
            str2 = "\n       select VO2MAX from SPORT_HEALTH where USER_ID=" + j + " and  VO2MAX>0 limit 1\n    ";
        } else {
            str2 = "\n       select VO2MAX from SPORT_HEALTH where USER_ID=" + j + " and VO2MAX>0 and DATE_TIME='" + str + "' limit 1\n    ";
        }
        Cursor cursor = GreenDaoUtil.execSql(str2, null);
        Intrinsics.checkExpressionValueIsNotNull(cursor, "cursor");
        boolean z = cursor.getCount() > 0;
        FitnessHelperKt.closeCursor(cursor);
        return z;
    }

    public static /* synthetic */ void printAndSave$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        printAndSave(str, str2);
    }

    public static final void printAndSave(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str3 = str2;
        if (str3 == null || str3.length() == 0) {
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLogPath(), TAG, str);
        } else {
            LogPath logPathImpl2 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl2.getLogPath(), str2, str);
        }
    }
}