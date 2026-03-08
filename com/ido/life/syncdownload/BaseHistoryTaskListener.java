package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: BaseHistoryTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JB\u0010\u0007\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\nJ\b\u0010\u000f\u001a\u00020\u0003H&J\u001a\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\bH\u0016J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\"\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0012\u001a\u00020\u0005H&J\u001c\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u0019\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\nH&J\"\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\nH\u0004¨\u0006\u001f"}, d2 = {"Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "Lcom/ido/life/syncdownload/BaseDataDownloadProgressTaskListener;", "userId", "", "taskCount", "", "(JI)V", "caluteRequestDate", "", "", "", "perMonth", "startTime", "endTime", "dateFormat", "getDataSize", "getDefaultParams", "getMaxDownloadDataCount", "threadCount", "getRequestParams", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", "response", "processData", FirebaseAnalytics.Param.CONTENT, "skipDate", "taskId", "date", "tag", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseHistoryTaskListener extends BaseDataDownloadProgressTaskListener {
    public abstract long getDataSize();

    public abstract List<Map<String, String>> getRequestParams(int threadCount);

    public abstract boolean processData(Task.TaskInfo taskInfo, String content);

    public /* synthetic */ BaseHistoryTaskListener(long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i2 & 2) != 0 ? 1 : i);
    }

    public BaseHistoryTaskListener(long j, int i) {
        super(j, i);
    }

    public List<Map<String, String>> getDefaultParams() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        calendar.add(1, -100);
        return CollectionsKt.mutableListOf(MapsKt.mutableMapOf(new Pair("start", DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD)), new Pair("end", str)));
    }

    public final int getMaxDownloadDataCount(int threadCount) {
        long dataSize = getDataSize();
        int i = threadCount <= 0 ? 3 : threadCount;
        if (dataSize <= 0) {
            dataSize = 1024;
        }
        if (threadCount <= 0 || getDataSize() <= 0) {
            return 0;
        }
        int iRoundToInt = MathKt.roundToInt(((((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()) + Runtime.getRuntime().freeMemory()) / 4.0f) / i) / dataSize);
        printAndSaveLog("threadNum=" + i + ",dataSize=" + dataSize + ",count=" + iRoundToInt);
        return iRoundToInt;
    }

    public static /* synthetic */ List caluteRequestDate$default(BaseHistoryTaskListener baseHistoryTaskListener, int i, long j, long j2, String str, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: caluteRequestDate");
        }
        if ((i2 & 1) != 0) {
            i = 1;
        }
        long j3 = (i2 & 2) != 0 ? 0L : j;
        long j4 = (i2 & 4) == 0 ? j2 : 0L;
        if ((i2 & 8) != 0) {
            str = DateUtil.DATE_FORMAT_YMD;
        }
        return baseHistoryTaskListener.caluteRequestDate(i, j3, j4, str);
    }

    public final List<Map<String, String>> caluteRequestDate(int perMonth, long startTime, long endTime, String dateFormat) {
        Intrinsics.checkParameterIsNotNull(dateFormat, "dateFormat");
        printAndSaveLog("caluteRequestDate(perMonth=" + perMonth + ",startTime=" + startTime + ",endTime=" + endTime + ",dateFormat=" + dateFormat + ')');
        ArrayList arrayList = new ArrayList();
        if (perMonth < 0 || startTime <= 0 || endTime <= 0) {
            return null;
        }
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
        startCalendar.setTimeInMillis(startTime);
        startCalendar.set(11, 0);
        startCalendar.set(12, 0);
        startCalendar.set(13, 0);
        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
        endCalendar.setTimeInMillis(endTime);
        endCalendar.set(11, 23);
        endCalendar.set(12, 59);
        endCalendar.set(13, 59);
        while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
            String startDate = DateUtil.format(startCalendar, dateFormat);
            startCalendar.set(5, 1);
            startCalendar.add(2, perMonth);
            startCalendar.add(5, -1);
            String endDate = DateUtil.format(startCalendar, dateFormat);
            printAndSaveLog("startDate=" + startDate + ",endDate=" + endDate);
            startCalendar.add(5, 1);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Intrinsics.checkExpressionValueIsNotNull(startDate, "startDate");
            linkedHashMap.put("start", startDate);
            Intrinsics.checkExpressionValueIsNotNull(endDate, "endDate");
            linkedHashMap.put("end", endDate);
            arrayList.add(linkedHashMap);
        }
        return arrayList;
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
        try {
            String str = response;
            if (str == null || str.length() == 0) {
                return true;
            }
            JSONObject jSONObject = new JSONObject(response);
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
            String string = jSONObject.getString("result");
            if (i == 200) {
                String str2 = string;
                if (!(str2 == null || str2.length() == 0)) {
                    return processData(taskInfo, string);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return true;
    }

    protected final boolean skipDate(int taskId, String date, String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        String str = date;
        if (str == null || str.length() == 0) {
            return true;
        }
        Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(tag);
        if (map == null) {
            return false;
        }
        String str2 = map.get(Long.valueOf(getUserId()));
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        String str3 = map.get(Long.valueOf(getUserId()));
        if (str3 == null) {
            Intrinsics.throwNpe();
        }
        String str4 = str3;
        if (date != null) {
            return date.contentEquals(str4);
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }
}