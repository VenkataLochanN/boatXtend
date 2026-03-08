package com.ido.life.syncdownload;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.core.os.BundleKt;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.ido.life.util.eventbus.EventBusWrapper;
import com.ido.life.util.eventbus.IHandlerEventBus;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: DataDownLoadService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u000f\u0018\u0000 Z2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001ZB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\tH\u0002J\b\u0010!\u001a\u00020\u001dH\u0016J \u0010\"\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u001bH\u0002J \u0010%\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u001bH\u0002J\u0012\u0010'\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0012\u0010)\u001a\u0004\u0018\u00010\u00142\u0006\u0010*\u001a\u00020\u0011H\u0016J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110,2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00110,2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0018\u0010.\u001a\u00020\u001d2\u000e\u0010/\u001a\n\u0012\u0004\u0012\u000201\u0018\u000100H\u0016J\u0010\u00102\u001a\u0002032\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\b\u00104\u001a\u00020\u001dH\u0002J\b\u00105\u001a\u00020\u001dH\u0002J\b\u00106\u001a\u000203H\u0002J\u0014\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\b\u0010;\u001a\u00020\u001dH\u0016J\u0016\u0010<\u001a\u00020\u001d2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00110>H\u0016J\"\u0010?\u001a\u00020\u00062\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010@\u001a\u00020\u00062\u0006\u0010A\u001a\u00020\u0006H\u0016J\u0010\u0010B\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0010\u0010C\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0010\u0010D\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010G\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010H\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0010\u0010I\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0016\u0010J\u001a\u00020\u001d2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110LH\u0002J\u0014\u0010M\u001a\u00020\u001d2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110LJ\u0018\u0010N\u001a\u00020\u001d2\u0006\u0010O\u001a\u00020\u001b2\u0006\u0010E\u001a\u00020FH\u0016J\u001e\u0010N\u001a\u00020\u001d2\u0006\u0010O\u001a\u00020\u001b2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020F0,H\u0016J\u0010\u0010Q\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020FH\u0016J\u0018\u0010R\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020F2\u0006\u0010S\u001a\u00020\tH\u0016J\u0010\u0010T\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010U\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020FH\u0016J\u0018\u0010V\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020F2\u0006\u0010W\u001a\u00020\u0006H\u0016J\u0018\u0010X\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020F2\u0006\u0010Y\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00140\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Lcom/ido/life/syncdownload/DataDownLoadService;", "Landroid/app/Service;", "Lcom/ido/life/util/eventbus/IHandlerEventBus;", "Lcom/ido/life/syncdownload/IDataDownLoad;", "()V", "CORE_POOL_SIZE", "", "CPU_COUNT", "HOST_HEALTH", "", "kotlin.jvm.PlatformType", "HOST_USER", "HOST_USER_THIRD", "KEEP_ALIVE_SECONDS", "MAXIMUM_POOL_SIZ", "mDataPullConfigMap", "", "", "Lcom/ido/life/database/model/DataPullConfigInfo;", "mDownloadStateList", "Lcom/ido/life/database/model/DataDownLoadState;", "mEventBus", "Lcom/ido/life/util/eventbus/EventBusWrapper;", "mExecutor", "Ljava/util/concurrent/ThreadPoolExecutor;", "mLoginUserId", "mRootTaskGroup", "Lcom/ido/life/syncdownload/TaskGroup;", "allTaskComplete", "", "checkAndFixHomeTaskState", "userId", "taskTag", "clearDataDownloadState", "createOrContinueHistoryTask", "priority", "historyTaskGroup", "createOrContinueHomeTask", "homeTaskGroup", "createWeeklyReportTaskGroup", "getDataConfigInfo", "getDataDownloadState", "stateId", "getHistoryTaskList", "", "getHomeTaskList", "handleMessage", "msgEvent", "Lcom/ido/life/base/BaseMessage;", "", "historyDataAllComplete", "", "initConfig", "initExcutor", "isMainThread", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "onGroupTaskComplete", "taskIdSet", "", "onStartCommand", "flags", "startId", "reallyStartHistoryTask", "reallyStartHomeTask", "singleTaskExecutedFailed", "task", "Lcom/ido/life/syncdownload/NewTask;", "singleTaskExecutedSuccess", "startHistoryTask", "startHomeTask", "stopHistoryTask", "userIdList", "", "stopHomeTask", "submitTask", "taskGroup", "taskList", "taskExecutedCancel", "taskExecutedFailed", "errorMsg", "taskExecutedStart", "taskExecutedSuccess", "updateDataDownloadState", "state", "updateProgress", NotificationCompat.CATEGORY_PROGRESS, "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DataDownLoadService extends Service implements IHandlerEventBus, IDataDownLoad {
    public static final int HOME_TASK_ID = 0;
    public static final String LOAD_HISTORY = "load_history";
    public static final String LOAD_HOME_TASK = "load_home";
    public static final String TASK_ID = "task_id";
    public static final String USER_ID_LIST = "user_id_list";
    private EventBusWrapper mEventBus;
    private ThreadPoolExecutor mExecutor;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = DataDownLoadService.class.getSimpleName();
    private static List<ITaskExecutedCallBack> mTaskExecutedCallback = new ArrayList();
    private static List<ITaskExecutedTotalCallBack> mAllTaskExecutedCallback = new ArrayList();
    private final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private final int CORE_POOL_SIZE = Math.max(4, Math.min(this.CPU_COUNT - 1, 4));
    private final int MAXIMUM_POOL_SIZ = Integer.MAX_VALUE;
    private final int KEEP_ALIVE_SECONDS = 10;
    private TaskGroup mRootTaskGroup = new TaskGroup(this);
    private String HOST_USER = BaseUrl.HOST_USER;
    private String HOST_USER_THIRD = BaseUrl.HOST_USER_THIRD;
    private String HOST_HEALTH = BaseUrl.HOST_HEALTH;
    private Map<Long, DataPullConfigInfo> mDataPullConfigMap = new LinkedHashMap();
    private Map<Long, DataDownLoadState> mDownloadStateList = new LinkedHashMap();
    private long mLoginUserId = -1;

    @JvmStatic
    public static final void addAllTaskExecutedCallback(ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack) {
        INSTANCE.addAllTaskExecutedCallback(iTaskExecutedTotalCallBack);
    }

    @JvmStatic
    public static final void addTaskExecutedCallback(ITaskExecutedCallBack iTaskExecutedCallBack) {
        INSTANCE.addTaskExecutedCallback(iTaskExecutedCallBack);
    }

    @JvmStatic
    public static final void checkAndAjustDownloadStateWhenStartPull(boolean z) {
        INSTANCE.checkAndAjustDownloadStateWhenStartPull(z);
    }

    @JvmStatic
    private static final boolean checkCanStartTask() {
        return INSTANCE.checkCanStartTask();
    }

    @JvmStatic
    public static final void clearAllCallback() {
        INSTANCE.clearAllCallback();
    }

    @JvmStatic
    public static final void clearAllTaskExecutedCallback() {
        INSTANCE.clearAllTaskExecutedCallback();
    }

    @JvmStatic
    public static final void clearTaskExecutedCallback() {
        INSTANCE.clearTaskExecutedCallback();
    }

    @JvmStatic
    public static final void loadHistoryData(List<Long> list) {
        INSTANCE.loadHistoryData(list);
    }

    @JvmStatic
    public static final void loadHomeData(List<Long> list) {
        INSTANCE.loadHomeData(list);
    }

    @JvmStatic
    public static final void loadSingleHistoryData(long j) {
        INSTANCE.loadSingleHistoryData(j);
    }

    @JvmStatic
    public static final void loadSingleHomeData(long j) {
        INSTANCE.loadSingleHomeData(j);
    }

    @JvmStatic
    public static final void removeAllTaskExecutedCallback(ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack) {
        INSTANCE.removeAllTaskExecutedCallback(iTaskExecutedTotalCallBack);
    }

    @JvmStatic
    public static final void removeTaskExecutedCallback(ITaskExecutedCallBack iTaskExecutedCallBack) {
        INSTANCE.removeTaskExecutedCallback(iTaskExecutedCallBack);
    }

    @JvmStatic
    public static final void requestPullData(List<Long> list) {
        INSTANCE.requestPullData(list);
    }

    @JvmStatic
    public static final void startMultiUserTask(List<Long> list) {
        INSTANCE.startMultiUserTask(list);
    }

    @JvmStatic
    public static final void startSingleUserTask(long j) {
        INSTANCE.startSingleUserTask(j);
    }

    @JvmStatic
    public static final void stop() {
        INSTANCE.stop();
    }

    @JvmStatic
    public static final void stopMultiUserTask(List<Long> list, boolean z, boolean z2) {
        INSTANCE.stopMultiUserTask(list, z, z2);
    }

    @JvmStatic
    public static final void stopSingleUserTask(long j, boolean z, boolean z2) {
        INSTANCE.stopSingleUserTask(j, z, z2);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u000eH\u0007J\u0012\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011H\u0007J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\b\u0010\u001b\u001a\u00020\u001aH\u0003J\b\u0010\u001c\u001a\u00020\u0014H\u0007J\b\u0010\u001d\u001a\u00020\u0014H\u0007J\b\u0010\u001e\u001a\u00020\u0014H\u0007J\u0016\u0010\u001f\u001a\u00020\u00142\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\rH\u0007J\u0016\u0010\"\u001a\u00020\u00142\f\u0010#\u001a\b\u0012\u0004\u0012\u00020!0\rH\u0007J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020!H\u0007J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010%\u001a\u00020!H\u0007J\u0010\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u0006H\u0002J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u0006H\u0002J\u0012\u0010*\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u000eH\u0007J\u0012\u0010+\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011H\u0007J\u0016\u0010,\u001a\u00020\u00142\f\u0010-\u001a\b\u0012\u0004\u0012\u00020!0.H\u0007J\u001a\u0010/\u001a\u00020\u00142\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\rH\u0007J\u0010\u00100\u001a\u00020\u00142\u0006\u0010%\u001a\u00020!H\u0007J\b\u00101\u001a\u00020\u0014H\u0007J*\u00102\u001a\u00020\u00142\f\u0010#\u001a\b\u0012\u0004\u0012\u00020!0\r2\b\b\u0002\u00103\u001a\u00020\u001a2\b\b\u0002\u00104\u001a\u00020\u001aH\u0007J$\u00105\u001a\u00020\u00142\u0006\u0010%\u001a\u00020!2\b\b\u0002\u00103\u001a\u00020\u001a2\b\b\u0002\u00104\u001a\u00020\u001aH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002R\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0002¨\u00066"}, d2 = {"Lcom/ido/life/syncdownload/DataDownLoadService$Companion;", "", "()V", "HOME_TASK_ID", "", "LOAD_HISTORY", "", "LOAD_HOME_TASK", "TAG", "kotlin.jvm.PlatformType", "TASK_ID", "USER_ID_LIST", "mAllTaskExecutedCallback", "", "Lcom/ido/life/syncdownload/ITaskExecutedTotalCallBack;", "mAllTaskExecutedCallback$annotations", "mTaskExecutedCallback", "Lcom/ido/life/syncdownload/ITaskExecutedCallBack;", "mTaskExecutedCallback$annotations", "addAllTaskExecutedCallback", "", "callBack", "addTaskExecutedCallback", "callback", "checkAndAjustDownloadStateWhenStartPull", "willReLoad", "", "checkCanStartTask", "clearAllCallback", "clearAllTaskExecutedCallback", "clearTaskExecutedCallback", "loadHistoryData", "userIDList", "", "loadHomeData", "userIdList", "loadSingleHistoryData", "userId", "loadSingleHomeData", "printAndSave", "message", "printAndSaveError", "removeAllTaskExecutedCallback", "removeTaskExecutedCallback", "requestPullData", "taskIdList", "", "startMultiUserTask", "startSingleUserTask", "stop", "stopMultiUserTask", "stopHome", "stopHistory", "stopSingleUserTask", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        private static /* synthetic */ void mAllTaskExecutedCallback$annotations() {
        }

        @JvmStatic
        private static /* synthetic */ void mTaskExecutedCallback$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void startSingleUserTask(long userId) {
            startMultiUserTask(CollectionsKt.mutableListOf(Long.valueOf(userId)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final boolean checkCanStartTask() {
            if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
                printAndSave("没有网络,不需要从服务器下拉数据");
                return false;
            }
            if (!RunTimeUtil.getInstance().hasLogin()) {
                printAndSave("游客状态,不需要从服务器下拉数据");
                return false;
            }
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            if (GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId()) == null) {
                printAndSave("用户信息为空,不允许从服务器下拉数据");
                return false;
            }
            String token = AuthorizationPreference.getToken(IdoApp.getAppContext());
            if (!(token == null || token.length() == 0)) {
                return true;
            }
            printAndSave("token为空,不允许从服务器上下拉数据");
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void startMultiUserTask$default(Companion companion, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = (List) null;
            }
            companion.startMultiUserTask(list);
        }

        @JvmStatic
        public final void startMultiUserTask(List<Long> userIdList) {
            List<Long> list = userIdList;
            if (!(list == null || list.isEmpty()) && checkCanStartTask()) {
                if (AppUtil.isServiceRunning(IdoApp.getAppContext(), DataDownLoadService.class.getName())) {
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.REQUEST_PULL_ALL_DATA, userIdList));
                    return;
                }
                try {
                    Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) DataDownLoadService.class);
                    intent.putExtras(BundleKt.bundleOf(new Pair(DataDownLoadService.USER_ID_LIST, userIdList), new Pair(DataDownLoadService.LOAD_HOME_TASK, true), new Pair(DataDownLoadService.LOAD_HISTORY, true)));
                    IdoApp.getAppContext().startService(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @JvmStatic
        public final void loadSingleHomeData(long userId) {
            loadHomeData(CollectionsKt.mutableListOf(Long.valueOf(userId)));
        }

        @JvmStatic
        public final void loadHomeData(List<Long> userIdList) {
            Intrinsics.checkParameterIsNotNull(userIdList, "userIdList");
            if (userIdList.isEmpty()) {
                printAndSave("请求下拉历史数据，但是没有指定用户。");
                return;
            }
            Companion companion = this;
            if (companion.checkCanStartTask()) {
                if (AppUtil.isServiceRunning(IdoApp.getAppContext(), DataDownLoadService.class.getName())) {
                    companion.printAndSave("开始下拉首页数据，同时服务已经启动.");
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.REQUEST_START_PULL_HOME_DATA, userIdList));
                    return;
                }
                companion.printAndSave("开始下拉首页数据，同时服务未启动.");
                try {
                    Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) DataDownLoadService.class);
                    intent.putExtras(BundleKt.bundleOf(new Pair(DataDownLoadService.LOAD_HOME_TASK, true), new Pair(DataDownLoadService.USER_ID_LIST, userIdList)));
                    IdoApp.getAppContext().startService(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @JvmStatic
        public final void loadSingleHistoryData(long userId) {
            loadHistoryData(CollectionsKt.mutableListOf(Long.valueOf(userId)));
        }

        @JvmStatic
        public final void loadHistoryData(List<Long> userIDList) {
            Intrinsics.checkParameterIsNotNull(userIDList, "userIDList");
            if (userIDList.isEmpty()) {
                printAndSave("下拉历史数据，但是没有指定用户。");
                return;
            }
            Companion companion = this;
            if (companion.checkCanStartTask()) {
                if (AppUtil.isServiceRunning(IdoApp.getAppContext(), DataDownLoadService.class.getName())) {
                    companion.printAndSave("开始下拉历史数据，同时服务已经启动.");
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.REQUEST_START_PULL_HISTORY_DATA, userIDList));
                    return;
                }
                companion.printAndSave("开始下拉历史数据，同时服务未启动");
                try {
                    Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) DataDownLoadService.class);
                    intent.putExtras(BundleKt.bundleOf(new Pair(DataDownLoadService.LOAD_HISTORY, true), new Pair(DataDownLoadService.USER_ID_LIST, userIDList)));
                    IdoApp.getAppContext().startService(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @JvmStatic
        public final void stop() {
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), "停止下载服务");
            if (AppUtil.isServiceRunning(IdoApp.getAppContext(), DataDownLoadService.class.getName())) {
                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_STOP_DATA_DOWNLOAD_SERVICE));
                return;
            }
            try {
                IdoApp.getAppContext().stopService(new Intent(IdoApp.getAppContext(), (Class<?>) DataDownLoadService.class));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public static /* synthetic */ void stopSingleUserTask$default(Companion companion, long j, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            if ((i & 4) != 0) {
                z2 = true;
            }
            companion.stopSingleUserTask(j, z, z2);
        }

        @JvmStatic
        public final void stopSingleUserTask(long userId, boolean stopHome, boolean stopHistory) {
            stopMultiUserTask(CollectionsKt.mutableListOf(Long.valueOf(userId)), stopHome, stopHistory);
        }

        public static /* synthetic */ void stopMultiUserTask$default(Companion companion, List list, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            if ((i & 4) != 0) {
                z2 = true;
            }
            companion.stopMultiUserTask(list, z, z2);
        }

        @JvmStatic
        public final void stopMultiUserTask(List<Long> userIdList, boolean stopHome, boolean stopHistory) {
            Intrinsics.checkParameterIsNotNull(userIdList, "userIdList");
            printAndSave("停止下拉历史数据");
            if (AppUtil.isServiceRunning(IdoApp.getAppContext(), DataDownLoadService.class.getName())) {
                if (stopHome) {
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_STOP_PULL_HOME_DATA, userIdList));
                }
                if (stopHistory) {
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_STOP_PULL_HISTORY_DATA, userIdList));
                }
            }
        }

        @JvmStatic
        public final void requestPullData(List<Long> taskIdList) {
            Intrinsics.checkParameterIsNotNull(taskIdList, "taskIdList");
            if (taskIdList.isEmpty()) {
                return;
            }
            printAndSave("开始请求下拉数据(requestPullData) taskIdList=" + taskIdList);
            if (AppUtil.isServiceRunning(IdoApp.getAppContext(), DataDownLoadService.class.getName())) {
                EventBusHelper.post(new BaseMessage(Constants.EventConstants.REQUEST_PULL_DATA, taskIdList));
                return;
            }
            Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) DataDownLoadService.class);
            intent.putExtras(BundleKt.bundleOf(new Pair(DataDownLoadService.TASK_ID, CollectionsKt.toLongArray(taskIdList))));
            IdoApp.getAppContext().startService(intent);
        }

        @JvmStatic
        public final void checkAndAjustDownloadStateWhenStartPull(boolean willReLoad) {
            if (willReLoad) {
                GreenDaoUtil.execSql("\n                    update DATA_DOWN_LOAD_STATE set DOWNLOAD_STATE=1\n                     where DOWNLOAD_STATE not in (3,4)\n                ");
            } else {
                GreenDaoUtil.execSql("\n                    update DATA_DOWN_LOAD_STATE set DOWNLOAD_STATE=4\n                     where DOWNLOAD_STATE not in (3,4)\n                ");
            }
        }

        @JvmStatic
        public final void addTaskExecutedCallback(ITaskExecutedCallBack callback) {
            if (callback == null || DataDownLoadService.mTaskExecutedCallback.contains(callback)) {
                return;
            }
            DataDownLoadService.mTaskExecutedCallback.add(callback);
        }

        @JvmStatic
        public final void addAllTaskExecutedCallback(ITaskExecutedTotalCallBack callBack) {
            AtomicInteger hasDownloadCount;
            if (callBack == null || DataDownLoadService.mAllTaskExecutedCallback.contains(callBack)) {
                return;
            }
            printAndSave("addAllTaskExecutedCallback(callBack: " + callBack + ")-添加整体数据下拉进度监听回调");
            if (callBack.getUserId() != null) {
                Long userId = callBack.getUserId();
                if (userId == null) {
                    Intrinsics.throwNpe();
                }
                DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(userId.longValue());
                callBack.updateTaskProgress((dataPullConfigInfoQueryDataPullConfigInfo == null || (hasDownloadCount = dataPullConfigInfoQueryDataPullConfigInfo.getHasDownloadCount()) == null) ? 0.0f : hasDownloadCount.floatValue());
            }
            DataDownLoadService.mAllTaskExecutedCallback.add(callBack);
        }

        @JvmStatic
        public final void removeTaskExecutedCallback(ITaskExecutedCallBack callback) {
            if (callback != null && DataDownLoadService.mTaskExecutedCallback.contains(callback)) {
                printAndSave("removeTaskExecutedCallback(callback: " + callback + ")-添加移除数据下拉进度监听回调");
                DataDownLoadService.mTaskExecutedCallback.remove(callback);
            }
        }

        @JvmStatic
        public final void removeAllTaskExecutedCallback(ITaskExecutedTotalCallBack callBack) {
            if (callBack != null && DataDownLoadService.mAllTaskExecutedCallback.contains(callBack)) {
                printAndSave("removeAllTaskExecutedCallback(callBack: " + callBack + ")-移除整体数据下拉监听回调");
                DataDownLoadService.mAllTaskExecutedCallback.remove(callBack);
            }
        }

        @JvmStatic
        public final void clearTaskExecutedCallback() {
            printAndSave("clearTaskExecutedCallback()-清空所有部分数据下拉监听回调");
            DataDownLoadService.mTaskExecutedCallback.clear();
        }

        @JvmStatic
        public final void clearAllTaskExecutedCallback() {
            printAndSave("clearAllTaskExecutedCallback()-清空所有整体数据下拉监听回调");
            DataDownLoadService.mAllTaskExecutedCallback.clear();
        }

        @JvmStatic
        public final void clearAllCallback() {
            Companion companion = this;
            companion.printAndSave("clearAllCallback()-清空所有数据下拉监听回调");
            companion.clearTaskExecutedCallback();
            companion.clearAllTaskExecutedCallback();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void printAndSave(String message) {
            if (message.length() == 0) {
                return;
            }
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), DataDownLoadService.TAG, message);
        }

        private final void printAndSaveError(String message) {
            if (message.length() == 0) {
                return;
            }
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), DataDownLoadService.TAG, message);
        }
    }

    private final void initConfig() {
        if (StringsKt.endsWith$default(this.HOST_USER, "/", false, 2, (Object) null)) {
            String str = this.HOST_USER;
            int length = str.length() - 1;
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = str.substring(0, length);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            this.HOST_USER = strSubstring;
        }
        String HOST_USER_THIRD = this.HOST_USER_THIRD;
        Intrinsics.checkExpressionValueIsNotNull(HOST_USER_THIRD, "HOST_USER_THIRD");
        if (StringsKt.endsWith$default(HOST_USER_THIRD, "/", false, 2, (Object) null)) {
            String HOST_USER_THIRD2 = this.HOST_USER_THIRD;
            Intrinsics.checkExpressionValueIsNotNull(HOST_USER_THIRD2, "HOST_USER_THIRD");
            int length2 = this.HOST_USER_THIRD.length() - 1;
            if (HOST_USER_THIRD2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring2 = HOST_USER_THIRD2.substring(0, length2);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            this.HOST_USER_THIRD = strSubstring2;
        }
        String HOST_HEALTH = this.HOST_HEALTH;
        Intrinsics.checkExpressionValueIsNotNull(HOST_HEALTH, "HOST_HEALTH");
        if (StringsKt.endsWith$default(HOST_HEALTH, "/", false, 2, (Object) null)) {
            String HOST_HEALTH2 = this.HOST_HEALTH;
            Intrinsics.checkExpressionValueIsNotNull(HOST_HEALTH2, "HOST_HEALTH");
            int length3 = this.HOST_HEALTH.length() - 1;
            if (HOST_HEALTH2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring3 = HOST_HEALTH2.substring(0, length3);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            this.HOST_HEALTH = strSubstring3;
        }
        initExcutor();
        this.mEventBus = new EventBusWrapper();
        EventBusWrapper eventBusWrapper = this.mEventBus;
        if (eventBusWrapper != null) {
            eventBusWrapper.register(this);
        }
    }

    private final void initExcutor() {
        this.mExecutor = new ThreadPoolExecutor(this.CORE_POOL_SIZE, this.MAXIMUM_POOL_SIZ, this.KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.ido.life.syncdownload.DataDownLoadService.initExcutor.1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName(LanguageUtil.getLanguageText(R.string.app_name) + ' ' + System.currentTimeMillis());
                return thread;
            }
        });
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        TaskGroup taskGroup;
        Class<? extends BaseHistoryTaskListener> cls;
        TaskGroup taskGroupCreateWeeklyReportTaskGroup;
        INSTANCE.printAndSave("数据下拉服务启动成功。");
        initConfig();
        Bundle extras = intent != null ? intent.getExtras() : null;
        if (extras == null) {
            INSTANCE.printAndSave("没有指定需要下拉数据的用户，bundle=" + extras);
            return super.onStartCommand(intent, flags, startId);
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mLoginUserId = runTimeUtil.getUserId();
        long[] longArray = extras.getLongArray(TASK_ID);
        Object obj = extras.get(USER_ID_LIST);
        int i = Integer.MAX_VALUE;
        TaskGroup taskGroup2 = (TaskGroup) null;
        boolean z = extras.getBoolean(LOAD_HOME_TASK);
        boolean z2 = extras.getBoolean(LOAD_HISTORY);
        if (longArray != null) {
            INSTANCE.printAndSave("启动服务，添加用户请求下拉的任务。");
            TaskGroup taskGroup3 = new TaskGroup(this);
            Map<String, Class<? extends BaseHistoryTaskListener>> allHistoryTaskPropertyList = DataDownLoadConfigKt.getAllHistoryTaskPropertyList();
            int length = longArray.length;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            char c2 = 0;
            int i2 = Integer.MAX_VALUE;
            int i3 = 0;
            while (i3 < length) {
                long j = longArray[i3];
                DataDownLoadState dataDownloadStateById = GreenDaoUtil.getDataDownloadStateById(j);
                if (dataDownloadStateById != null && dataDownloadStateById.getDownloadState() != 3 && this.mRootTaskGroup.findTask(j) == null && (cls = allHistoryTaskPropertyList.get(dataDownloadStateById.getDataName())) != null) {
                    try {
                        Class<?>[] clsArr = new Class[1];
                        clsArr[c2] = Long.TYPE;
                        Constructor<? extends BaseHistoryTaskListener> declaredConstructor = cls.getDeclaredConstructor(clsArr);
                        Object[] objArr = new Object[1];
                        objArr[c2] = Long.valueOf(dataDownloadStateById.getUserId());
                        BaseHistoryTaskListener baseHistoryTaskListenerNewInstance = declaredConstructor.newInstance(objArr);
                        int i4 = i3 + 1;
                        Long id = dataDownloadStateById.getId();
                        Intrinsics.checkExpressionValueIsNotNull(id, "downloadState.id");
                        int i5 = i2 - 1;
                        try {
                            taskGroup3.addTask(baseHistoryTaskListenerNewInstance.getTask(i4, id.longValue(), dataDownloadStateById.getParamsMap(), null, i2));
                            dataDownloadStateById.setDownloadState(1);
                            try {
                                dataDownloadStateById.update();
                            } catch (Exception unused) {
                                GreenDaoUtil.addDataDownLoadStateRecord(dataDownloadStateById);
                            }
                            linkedHashSet.add(Long.valueOf(dataDownloadStateById.getUserId()));
                        } catch (Exception unused2) {
                        }
                        i2 = i5;
                    } catch (Exception unused3) {
                    }
                }
                i3++;
                c2 = 0;
            }
            if (!linkedHashSet.isEmpty()) {
                Iterator it = linkedHashSet.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    long jLongValue = ((Number) it.next()).longValue();
                    if (jLongValue == this.mLoginUserId) {
                        TaskGroup taskGroupCreateWeeklyReportTaskGroup2 = createWeeklyReportTaskGroup(jLongValue);
                        if (taskGroupCreateWeeklyReportTaskGroup2 != null && !taskGroupCreateWeeklyReportTaskGroup2.isAllComplete()) {
                            taskGroup3.addToLastTaskGroup(taskGroupCreateWeeklyReportTaskGroup2);
                        }
                    }
                }
            }
            taskGroup = taskGroup2;
            taskGroup2 = taskGroup3;
        } else {
            if (!(obj instanceof ArrayList)) {
                INSTANCE.printAndSave("没有指定需要下拉数据的用户。");
                return super.onStartCommand(intent, flags, startId);
            }
            ArrayList<Long> arrayList = (ArrayList) obj;
            if (arrayList.isEmpty()) {
                INSTANCE.printAndSave("用户ID列表为空。");
                return super.onStartCommand(intent, flags, startId);
            }
            INSTANCE.checkAndAjustDownloadStateWhenStartPull(true);
            if (z) {
                INSTANCE.printAndSave("启动服务，添加首页任务.");
                taskGroup = new TaskGroup(this);
                int i6 = Integer.MAX_VALUE;
                for (Long userId : arrayList) {
                    Intrinsics.checkExpressionValueIsNotNull(userId, "userId");
                    int iCreateOrContinueHomeTask = createOrContinueHomeTask(userId.longValue(), i6, taskGroup);
                    if (iCreateOrContinueHomeTask != i6) {
                        startHomeTask(userId.longValue());
                    }
                    i6 = iCreateOrContinueHomeTask;
                }
            } else {
                taskGroup = taskGroup2;
            }
            if (z2) {
                INSTANCE.printAndSave("启动服务，添加历史任务.");
                taskGroup2 = new TaskGroup(this);
                for (Long userId2 : arrayList) {
                    Intrinsics.checkExpressionValueIsNotNull(userId2, "userId");
                    int iCreateOrContinueHistoryTask = createOrContinueHistoryTask(userId2.longValue(), i, taskGroup2);
                    if (i != iCreateOrContinueHistoryTask) {
                        if (this.mLoginUserId == userId2.longValue() && (taskGroupCreateWeeklyReportTaskGroup = createWeeklyReportTaskGroup(userId2.longValue())) != null) {
                            taskGroup2.addToLastTaskGroup(taskGroupCreateWeeklyReportTaskGroup);
                        }
                        startHistoryTask(userId2.longValue());
                    }
                    i = iCreateOrContinueHistoryTask;
                }
            }
        }
        if (taskGroup != null && !taskGroup.isAllComplete()) {
            this.mRootTaskGroup.addToLastTaskGroup(taskGroup);
        }
        if (taskGroup2 != null && !taskGroup2.isAllComplete()) {
            this.mRootTaskGroup.addToLastTaskGroup(taskGroup2);
        }
        this.mRootTaskGroup.startTask(TaskGroup.INSTANCE.getEXECUTED_ONE_SHOT());
        return super.onStartCommand(intent, flags, startId);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int createOrContinueHomeTask(long r10, int r12, com.ido.life.syncdownload.TaskGroup r13) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.syncdownload.DataDownLoadService.createOrContinueHomeTask(long, int, com.ido.life.syncdownload.TaskGroup):int");
    }

    private final int createOrContinueHistoryTask(long userId, int priority, TaskGroup historyTaskGroup) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        int i;
        List<DataDownLoadState> listQueryHistoryDataDownloadState;
        List<DataDownLoadState> list;
        INSTANCE.printAndSave("创建或者继续历史任务。");
        int i2 = priority;
        for (Map.Entry<String, Class<? extends BaseHistoryTaskListener>> entry : DataDownLoadConfigKt.getHistoryTaskPropertyList(userId).entrySet()) {
            INSTANCE.printAndSave("创建或者继续历史任务：name=" + entry.getKey());
            int i3 = 0;
            BaseHistoryTaskListener baseHistoryTaskListenerNewInstance = entry.getValue().getConstructor(Long.TYPE).newInstance(Long.valueOf(userId));
            try {
            } catch (Exception e2) {
                e = e2;
            }
            try {
                listQueryHistoryDataDownloadState = GreenDaoUtil.queryHistoryDataDownloadState(userId, entry.getKey());
                list = listQueryHistoryDataDownloadState;
            } catch (Exception e3) {
                e = e3;
                i = i2;
                e.printStackTrace();
                i2 = i;
            }
            if (list == null || list.isEmpty()) {
                List<Map<String, String>> requestParams = baseHistoryTaskListenerNewInstance.getRequestParams(this.CORE_POOL_SIZE);
                List<Map<String, String>> list2 = requestParams;
                if (!(list2 == null || list2.isEmpty())) {
                    int size = requestParams.size();
                    i = i2;
                    while (i3 < size) {
                        try {
                            DataDownLoadState defaultTaskDownloadState = baseHistoryTaskListenerNewInstance.getDefaultTaskDownloadState();
                            defaultTaskDownloadState.setDownloadState(1);
                            int i4 = i3 + 1;
                            defaultTaskDownloadState.setTaskId(i4);
                            defaultTaskDownloadState.setParamsMap(requestParams.get(i3));
                            long jAddDataDownLoadStateRecord = GreenDaoUtil.addDataDownLoadStateRecord(defaultTaskDownloadState);
                            BaseDataDownloadTaskListener baseDataDownloadTaskListenerNewInstance = baseHistoryTaskListenerNewInstance.newInstance();
                            int taskId = defaultTaskDownloadState.getTaskId();
                            Map<String, String> map = requestParams.get(i3);
                            int i5 = i - 1;
                            try {
                                historyTaskGroup.addTask(baseDataDownloadTaskListenerNewInstance.getTask(taskId, jAddDataDownLoadStateRecord, map, null, i));
                                i = i5;
                                i3 = i4;
                            } catch (Exception e4) {
                                e = e4;
                                i = i5;
                                e.printStackTrace();
                                i2 = i;
                            }
                        } catch (Exception e5) {
                            e = e5;
                        }
                    }
                    i2 = i;
                }
            } else {
                int size2 = listQueryHistoryDataDownloadState.size();
                i = i2;
                while (i3 < size2) {
                    DataDownLoadState itemDownloadState = listQueryHistoryDataDownloadState.get(i3);
                    Intrinsics.checkExpressionValueIsNotNull(itemDownloadState, "itemDownloadState");
                    if (itemDownloadState.getDownloadState() != 3) {
                        TaskGroup taskGroup = this.mRootTaskGroup;
                        Long id = itemDownloadState.getId();
                        Intrinsics.checkExpressionValueIsNotNull(id, "itemDownloadState.id");
                        if (taskGroup.findTask(id.longValue()) == null) {
                            Long stateId = itemDownloadState.getId();
                            try {
                                itemDownloadState.setDownloadState(1);
                                itemDownloadState.update();
                            } catch (Exception unused) {
                                stateId = Long.valueOf(GreenDaoUtil.addDataDownLoadStateRecord(itemDownloadState));
                            }
                            BaseDataDownloadTaskListener baseDataDownloadTaskListenerNewInstance2 = baseHistoryTaskListenerNewInstance.newInstance();
                            int taskId2 = itemDownloadState.getTaskId();
                            Intrinsics.checkExpressionValueIsNotNull(stateId, "stateId");
                            int i6 = i - 1;
                            try {
                                historyTaskGroup.addTask(baseDataDownloadTaskListenerNewInstance2.getTask(taskId2, stateId.longValue(), itemDownloadState.getParamsMap(), null, i));
                                i = i6;
                            } catch (Exception e6) {
                                e = e6;
                                i = i6;
                                e.printStackTrace();
                                i2 = i;
                            }
                        }
                    }
                    i3++;
                }
                i2 = i;
            }
        }
        return i2;
    }

    @Override // com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<Object> msgEvent) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Integer numValueOf = msgEvent != null ? Integer.valueOf(msgEvent.getType()) : null;
        if (numValueOf != null && numValueOf.intValue() == 835) {
            INSTANCE.printAndSave("停止数据下载服务");
            this.mRootTaskGroup.stopAllTask();
            stopSelf();
            return;
        }
        boolean z = true;
        if (numValueOf != null && numValueOf.intValue() == 843) {
            if (msgEvent.getData() instanceof List) {
                try {
                    Object data = msgEvent.getData();
                    if (data == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                    }
                    List list = (List) data;
                    if (!list.isEmpty()) {
                        Map<String, Class<? extends BaseHistoryTaskListener>> allHistoryTaskPropertyList = DataDownLoadConfigKt.getAllHistoryTaskPropertyList();
                        TaskGroup taskGroup = new TaskGroup(this);
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        Iterator it = list.iterator();
                        int i = Integer.MAX_VALUE;
                        while (it.hasNext()) {
                            long jLongValue = ((Number) it.next()).longValue();
                            DataDownLoadState dataDownloadStateById = GreenDaoUtil.getDataDownloadStateById(jLongValue);
                            if (dataDownloadStateById != null && dataDownloadStateById.getDownloadState() != 3 && this.mRootTaskGroup.findTask(jLongValue) == null) {
                                Long stateId = dataDownloadStateById.getId();
                                dataDownloadStateById.setDownloadState(1);
                                try {
                                    dataDownloadStateById.update();
                                } catch (Exception unused) {
                                    stateId = Long.valueOf(GreenDaoUtil.addDataDownLoadStateRecord(dataDownloadStateById));
                                }
                                try {
                                    Class<? extends BaseHistoryTaskListener> cls = allHistoryTaskPropertyList.get(dataDownloadStateById.getDataName());
                                    if (cls == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    BaseHistoryTaskListener baseHistoryTaskListenerNewInstance = cls.getDeclaredConstructor(Long.TYPE).newInstance(Long.valueOf(dataDownloadStateById.getUserId()));
                                    int taskId = dataDownloadStateById.getTaskId();
                                    Intrinsics.checkExpressionValueIsNotNull(stateId, "stateId");
                                    int i2 = i - 1;
                                    try {
                                        taskGroup.addTask(baseHistoryTaskListenerNewInstance.getTask(taskId, stateId.longValue(), dataDownloadStateById.getParamsMap(), null, i));
                                        linkedHashSet.add(Long.valueOf(dataDownloadStateById.getUserId()));
                                    } catch (Exception unused2) {
                                    }
                                    i = i2;
                                } catch (Exception unused3) {
                                }
                            }
                        }
                        if (taskGroup.isComplete()) {
                            return;
                        }
                        if (!linkedHashSet.isEmpty()) {
                            Iterator it2 = linkedHashSet.iterator();
                            while (it2.hasNext()) {
                                long jLongValue2 = ((Number) it2.next()).longValue();
                                if (this.mLoginUserId == jLongValue2) {
                                    DataDownLoadState dataDownloadState = GreenDaoUtil.getDataDownloadState(jLongValue2, MessageEntity.class.getSimpleName(), 0);
                                    if (dataDownloadState != null) {
                                        TaskGroup taskGroup2 = this.mRootTaskGroup;
                                        Long id = dataDownloadState.getId();
                                        Intrinsics.checkExpressionValueIsNotNull(id, "weeklyState.id");
                                        taskGroup2.removeSingleTask(id.longValue());
                                    }
                                    TaskGroup taskGroupCreateWeeklyReportTaskGroup = createWeeklyReportTaskGroup(jLongValue2);
                                    if (taskGroupCreateWeeklyReportTaskGroup != null) {
                                        taskGroup.addToLastTaskGroup(taskGroupCreateWeeklyReportTaskGroup);
                                    }
                                }
                            }
                        }
                        boolean zIsAllComplete = this.mRootTaskGroup.isAllComplete();
                        this.mRootTaskGroup.findExecutingTaskGroup().addNextTaskGroup(taskGroup);
                        if (zIsAllComplete) {
                            this.mRootTaskGroup.startTask(TaskGroup.INSTANCE.getEXECUTED_ONE_SHOT());
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 911) {
            if (msgEvent.getData() instanceof List) {
                Object data2 = msgEvent.getData();
                if (data2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                }
                List list2 = (List) data2;
                List list3 = list2;
                if (list3 == null || list3.isEmpty()) {
                    return;
                }
                INSTANCE.printAndSave("收到下拉首页任务消息通知。");
                TaskGroup taskGroup3 = new TaskGroup(this);
                Iterator it3 = list2.iterator();
                while (it3.hasNext()) {
                    createOrContinueHomeTask(((Number) it3.next()).longValue(), Integer.MAX_VALUE, taskGroup3);
                }
                if (taskGroup3.isAllComplete()) {
                    return;
                }
                boolean zIsAllComplete2 = this.mRootTaskGroup.isAllComplete();
                this.mRootTaskGroup.findExecutingTaskGroup().addNextTaskGroup(taskGroup3);
                if (zIsAllComplete2) {
                    this.mRootTaskGroup.startTask(TaskGroup.INSTANCE.getEXECUTED_ONE_SHOT());
                    return;
                }
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 845) {
            if (msgEvent.getData() instanceof List) {
                Object data3 = msgEvent.getData();
                if (data3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                }
                List list4 = (List) data3;
                if (list4.isEmpty()) {
                    return;
                }
                INSTANCE.printAndSave("收到请求下拉历史数据任务消息通知");
                TaskGroup taskGroup4 = new TaskGroup(this);
                ArrayList arrayList = new ArrayList();
                Iterator it4 = list4.iterator();
                int i3 = Integer.MAX_VALUE;
                while (it4.hasNext()) {
                    long jLongValue3 = ((Number) it4.next()).longValue();
                    int iCreateOrContinueHistoryTask = createOrContinueHistoryTask(jLongValue3, i3, taskGroup4);
                    if (i3 != iCreateOrContinueHistoryTask) {
                        if (jLongValue3 == this.mLoginUserId) {
                            DataDownLoadState dataDownloadState2 = GreenDaoUtil.getDataDownloadState(jLongValue3, MessageEntity.class.getSimpleName(), 0);
                            if (dataDownloadState2 != null) {
                                TaskGroup taskGroup5 = this.mRootTaskGroup;
                                Long id2 = dataDownloadState2.getId();
                                Intrinsics.checkExpressionValueIsNotNull(id2, "weeklyState.id");
                                taskGroup5.removeSingleTask(id2.longValue());
                            }
                            TaskGroup taskGroupCreateWeeklyReportTaskGroup2 = createWeeklyReportTaskGroup(jLongValue3);
                            if (taskGroupCreateWeeklyReportTaskGroup2 != null) {
                                taskGroup4.addToLastTaskGroup(taskGroupCreateWeeklyReportTaskGroup2);
                            }
                        }
                        arrayList.add(Long.valueOf(jLongValue3));
                    }
                    i3 = iCreateOrContinueHistoryTask;
                }
                if (i3 == Integer.MAX_VALUE) {
                    return;
                }
                boolean zIsAllComplete3 = this.mRootTaskGroup.isAllComplete();
                this.mRootTaskGroup.findExecutingTaskGroup().addNextTaskGroup(taskGroup4);
                if (zIsAllComplete3) {
                    this.mRootTaskGroup.startTask(TaskGroup.INSTANCE.getEXECUTED_ONE_SHOT());
                }
                Iterator it5 = arrayList.iterator();
                while (it5.hasNext()) {
                    startHistoryTask(((Number) it5.next()).longValue());
                }
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 910) {
            INSTANCE.printAndSave("收到了下拉指定用户首页和历史数据任务");
            if (msgEvent.getData() instanceof List) {
                Object data4 = msgEvent.getData();
                if (data4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                }
                List list5 = (List) data4;
                List list6 = list5;
                if (list6 != null && !list6.isEmpty()) {
                    z = false;
                }
                if (z) {
                    return;
                }
                DataDownLoadService dataDownLoadService = this;
                TaskGroup taskGroup6 = new TaskGroup(dataDownLoadService);
                TaskGroup taskGroup7 = new TaskGroup(dataDownLoadService);
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                Iterator it6 = list5.iterator();
                while (it6.hasNext()) {
                    long jLongValue4 = ((Number) it6.next()).longValue();
                    TaskGroup taskGroup8 = new TaskGroup(dataDownLoadService);
                    TaskGroup taskGroup9 = new TaskGroup(dataDownLoadService);
                    int iCreateOrContinueHomeTask = createOrContinueHomeTask(jLongValue4, Integer.MAX_VALUE, taskGroup8);
                    int iCreateOrContinueHistoryTask2 = createOrContinueHistoryTask(jLongValue4, iCreateOrContinueHomeTask, taskGroup9);
                    if (iCreateOrContinueHomeTask != Integer.MAX_VALUE) {
                        taskGroup6.addToLastTaskGroup(taskGroup8);
                        arrayList3.add(Long.valueOf(jLongValue4));
                    }
                    if (iCreateOrContinueHistoryTask2 != iCreateOrContinueHomeTask) {
                        taskGroup7.addToLastTaskGroup(taskGroup9);
                        if (this.mLoginUserId == jLongValue4) {
                            DataDownLoadState dataDownloadState3 = GreenDaoUtil.getDataDownloadState(jLongValue4, MessageEntity.class.getSimpleName(), 0);
                            if (dataDownloadState3 != null) {
                                TaskGroup taskGroup10 = this.mRootTaskGroup;
                                Long id3 = dataDownloadState3.getId();
                                Intrinsics.checkExpressionValueIsNotNull(id3, "weeklyState.id");
                                taskGroup10.removeSingleTask(id3.longValue());
                            }
                            TaskGroup taskGroupCreateWeeklyReportTaskGroup3 = createWeeklyReportTaskGroup(jLongValue4);
                            if (taskGroupCreateWeeklyReportTaskGroup3 != null) {
                                taskGroup7.addToLastTaskGroup(taskGroupCreateWeeklyReportTaskGroup3);
                            }
                        }
                        arrayList2.add(Long.valueOf(jLongValue4));
                    }
                }
                boolean zIsAllComplete4 = this.mRootTaskGroup.isAllComplete();
                if (!taskGroup6.isAllComplete()) {
                    if (!taskGroup7.isAllComplete()) {
                        taskGroup6.addToLastTaskGroup(taskGroup7);
                    }
                    this.mRootTaskGroup.findExecutingTaskGroup().addNextTaskGroup(taskGroup6);
                } else if (!taskGroup7.isAllComplete()) {
                    this.mRootTaskGroup.findExecutingTaskGroup().addNextTaskGroup(taskGroup7);
                }
                if (zIsAllComplete4) {
                    this.mRootTaskGroup.startTask(TaskGroup.INSTANCE.getEXECUTED_ONE_SHOT());
                }
                Iterator it7 = arrayList2.iterator();
                while (it7.hasNext()) {
                    startHistoryTask(((Number) it7.next()).longValue());
                }
                Iterator it8 = arrayList3.iterator();
                while (it8.hasNext()) {
                    startHomeTask(((Number) it8.next()).longValue());
                }
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 912) {
            if (msgEvent.getData() instanceof List) {
                Object data5 = msgEvent.getData();
                if (data5 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                }
                stopHomeTask((List) data5);
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 913 && (msgEvent.getData() instanceof List)) {
            Object data6 = msgEvent.getData();
            if (data6 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
            }
            stopHistoryTask((List) data6);
        }
    }

    private final void startHomeTask(long userId) {
        if (!isMainThread()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C04521(userId, null), 2, null);
        } else {
            reallyStartHomeTask(userId);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$startHomeTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$startHomeTask$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $userId;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04521(long j, Continuation continuation) {
            super(2, continuation);
            this.$userId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04521 c04521 = DataDownLoadService.this.new C04521(this.$userId, completion);
            c04521.p$ = (CoroutineScope) obj;
            return c04521;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            DataDownLoadService.this.reallyStartHomeTask(this.$userId);
            return Unit.INSTANCE;
        }
    }

    private final void startHistoryTask(long userId) {
        if (!isMainThread()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C04511(userId, null), 2, null);
        } else {
            reallyStartHistoryTask(userId);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$startHistoryTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$startHistoryTask$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $userId;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04511(long j, Continuation continuation) {
            super(2, continuation);
            this.$userId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04511 c04511 = DataDownLoadService.this.new C04511(this.$userId, completion);
            c04511.p$ = (CoroutineScope) obj;
            return c04511;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            DataDownLoadService.this.reallyStartHistoryTask(this.$userId);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reallyStartHistoryTask(long userId) {
        INSTANCE.printAndSave("通知历史任务开始启动了。");
        int size = mAllTaskExecutedCallback.size();
        for (int i = 0; i < size; i++) {
            try {
                ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack = mAllTaskExecutedCallback.get(i);
                Long userId2 = iTaskExecutedTotalCallBack.getUserId();
                if (userId2 != null && userId2.longValue() == userId) {
                    iTaskExecutedTotalCallBack.startHistoryTask();
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reallyStartHomeTask(long userId) {
        INSTANCE.printAndSave("通知首页任务开始启动了。");
        int size = mAllTaskExecutedCallback.size();
        for (int i = 0; i < size; i++) {
            try {
                ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack = mAllTaskExecutedCallback.get(i);
                Long userId2 = iTaskExecutedTotalCallBack.getUserId();
                if (userId2 != null && userId2.longValue() == userId) {
                    iTaskExecutedTotalCallBack.startHomeTask();
                }
            } catch (Exception unused) {
            }
        }
    }

    private final boolean isMainThread() {
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
        String name = threadCurrentThread.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "Thread.currentThread().name");
        return "main".contentEquals(name);
    }

    private final TaskGroup createWeeklyReportTaskGroup(long userId) {
        Long lValueOf;
        long jLongValue;
        DataDownLoadState dataDownloadState = GreenDaoUtil.getDataDownloadState(userId, MessageEntity.class.getSimpleName(), 0);
        try {
            WeeklyReportTaskListener weeklyReportTaskListener = (WeeklyReportTaskListener) WeeklyReportTaskListener.class.getDeclaredConstructor(Long.TYPE).newInstance(Long.valueOf(userId));
            if (dataDownloadState == null) {
                dataDownloadState = weeklyReportTaskListener.getDefaultTaskDownloadState();
                dataDownloadState.setDownloadState(1);
                dataDownloadState.setTaskId(0);
                jLongValue = GreenDaoUtil.addDataDownLoadStateRecord(dataDownloadState);
            } else {
                dataDownloadState.setDownloadState(1);
                try {
                    dataDownloadState.update();
                    lValueOf = dataDownloadState.getId();
                    Intrinsics.checkExpressionValueIsNotNull(lValueOf, "weeklyState.id");
                } catch (Exception unused) {
                    lValueOf = Long.valueOf(GreenDaoUtil.addDataDownLoadStateRecord(dataDownloadState));
                }
                jLongValue = lValueOf.longValue();
            }
            long j = jLongValue;
            TaskGroup taskGroup = new TaskGroup(this);
            taskGroup.addTask(weeklyReportTaskListener.getTask(dataDownloadState.getTaskId(), j, dataDownloadState.getParamsMap(), null, Integer.MAX_VALUE));
            return taskGroup;
        } catch (Exception unused2) {
            return null;
        }
    }

    private final List<Long> getHistoryTaskList(long userId) {
        ArrayList arrayList = new ArrayList();
        List<DataDownLoadState> historyDataDownloadState = GreenDaoUtil.getHistoryDataDownloadState(userId);
        List<DataDownLoadState> list = historyDataDownloadState;
        if (!(list == null || list.isEmpty())) {
            for (DataDownLoadState itemState : historyDataDownloadState) {
                Intrinsics.checkExpressionValueIsNotNull(itemState, "itemState");
                if (itemState.getDownloadState() != 3 && itemState.getDownloadState() != 4) {
                    Long id = itemState.getId();
                    Intrinsics.checkExpressionValueIsNotNull(id, "itemState.id");
                    arrayList.add(id);
                }
            }
        }
        return arrayList;
    }

    private final List<Long> getHomeTaskList(long userId) {
        ArrayList arrayList = new ArrayList();
        List<DataDownLoadState> homeDataDownloadState = GreenDaoUtil.getHomeDataDownloadState(userId);
        List<DataDownLoadState> list = homeDataDownloadState;
        if (!(list == null || list.isEmpty())) {
            for (DataDownLoadState itemState : homeDataDownloadState) {
                Intrinsics.checkExpressionValueIsNotNull(itemState, "itemState");
                if (itemState.getDownloadState() != 3 && itemState.getDownloadState() != 4) {
                    Long id = itemState.getId();
                    Intrinsics.checkExpressionValueIsNotNull(id, "itemState.id");
                    arrayList.add(id);
                }
            }
        }
        return arrayList;
    }

    @Override // android.app.Service
    public void onDestroy() {
        INSTANCE.printAndSave("数据下拉服务已经停止.");
        EventBusWrapper eventBusWrapper = this.mEventBus;
        if (eventBusWrapper != null) {
            eventBusWrapper.unregister();
        }
        this.mRootTaskGroup.stopAllTask();
        INSTANCE.clearAllCallback();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void singleTaskExecutedSuccess(NewTask task) {
        if (task.getTaskInfo() == null) {
            return;
        }
        if ((!mAllTaskExecutedCallback.isEmpty()) && task.getListener() != null) {
            int size = mAllTaskExecutedCallback.size();
            for (int i = 0; i < size; i++) {
                try {
                    ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack = mAllTaskExecutedCallback.get(i);
                    Long userId = iTaskExecutedTotalCallBack.getUserId();
                    Task.Listenter listener = task.getListener();
                    if (listener == null) {
                        Intrinsics.throwNpe();
                    }
                    long userId2 = listener.getUserId();
                    if (userId != null && userId.longValue() == userId2) {
                        NewTask.NewTaskInfo taskInfo = task.getTaskInfo();
                        if (taskInfo == null) {
                            Intrinsics.throwNpe();
                        }
                        iTaskExecutedTotalCallBack.onTaskExecutedSuccess(taskInfo);
                    }
                } catch (Exception unused) {
                }
            }
        }
        if (!mTaskExecutedCallback.isEmpty()) {
            int size2 = mTaskExecutedCallback.size();
            for (int i2 = 0; i2 < size2; i2++) {
                try {
                    ITaskExecutedCallBack iTaskExecutedCallBack = mTaskExecutedCallback.get(i2);
                    List<Long> focusTaskList = iTaskExecutedCallBack.getFocusTaskList();
                    NewTask.NewTaskInfo taskInfo2 = task.getTaskInfo();
                    if (taskInfo2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (focusTaskList.remove(Long.valueOf(taskInfo2.getBuilder().getStateId()))) {
                        NewTask.NewTaskInfo taskInfo3 = task.getTaskInfo();
                        if (taskInfo3 == null) {
                            Intrinsics.throwNpe();
                        }
                        iTaskExecutedCallBack.onTaskExecutedSuccess(taskInfo3);
                        INSTANCE.printAndSave("关注任务执行成功，关注的类callBack=" + iTaskExecutedCallBack.getClass().getSimpleName() + ",任务信息taskInfo=" + task.getTaskInfo());
                        if (iTaskExecutedCallBack.getFocusTaskList().isEmpty()) {
                            iTaskExecutedCallBack.onTaskComplete();
                            INSTANCE.printAndSave("关注任务执行完成，关注的类callBack=" + iTaskExecutedCallBack.getClass().getSimpleName() + ",任务信息taskInfo=" + task.getTaskInfo());
                        }
                    }
                } catch (Exception unused2) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void singleTaskExecutedFailed(NewTask task) {
        if (task.getTaskInfo() == null || task.getListener() == null) {
            return;
        }
        if (!mAllTaskExecutedCallback.isEmpty()) {
            int size = mAllTaskExecutedCallback.size();
            for (int i = 0; i < size; i++) {
                try {
                    ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack = mAllTaskExecutedCallback.get(i);
                    Long userId = iTaskExecutedTotalCallBack.getUserId();
                    Task.Listenter listener = task.getListener();
                    if (listener == null) {
                        Intrinsics.throwNpe();
                    }
                    long userId2 = listener.getUserId();
                    if (userId != null && userId.longValue() == userId2) {
                        NewTask.NewTaskInfo taskInfo = task.getTaskInfo();
                        if (taskInfo == null) {
                            Intrinsics.throwNpe();
                        }
                        iTaskExecutedTotalCallBack.onTaskExecutedFailed(taskInfo);
                    }
                } catch (Exception unused) {
                }
            }
        }
        if (!mTaskExecutedCallback.isEmpty()) {
            int size2 = mTaskExecutedCallback.size();
            for (int i2 = 0; i2 < size2; i2++) {
                try {
                    ITaskExecutedCallBack iTaskExecutedCallBack = mTaskExecutedCallback.get(i2);
                    List<Long> focusTaskList = iTaskExecutedCallBack.getFocusTaskList();
                    NewTask.NewTaskInfo taskInfo2 = task.getTaskInfo();
                    if (taskInfo2 == null) {
                        Intrinsics.throwNpe();
                    }
                    focusTaskList.remove(Long.valueOf(taskInfo2.getBuilder().getStateId()));
                    NewTask.NewTaskInfo taskInfo3 = task.getTaskInfo();
                    if (taskInfo3 == null) {
                        Intrinsics.throwNpe();
                    }
                    NewTask.NewTaskInfo taskInfo4 = task.getTaskInfo();
                    if (taskInfo4 == null) {
                        Intrinsics.throwNpe();
                    }
                    iTaskExecutedCallBack.onTaskExecutedFailed(taskInfo3, taskInfo4.getBuilder().getErrorMsg());
                    INSTANCE.printAndSave("关注任务执行失败，关注的类callBack=" + iTaskExecutedCallBack.getClass().getSimpleName() + ",taskInfo=" + task.getTaskInfo());
                    if (iTaskExecutedCallBack.getFocusTaskList().isEmpty()) {
                        iTaskExecutedCallBack.onTaskComplete();
                        INSTANCE.printAndSave("关注的任务全部完成，关注的类callBack=" + iTaskExecutedCallBack.getClass().getSimpleName() + ",taskInfo=" + task.getTaskInfo());
                    }
                } catch (Exception unused2) {
                }
            }
        }
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public DataDownLoadState getDataDownloadState(long stateId) {
        DataDownLoadState dataDownLoadState = this.mDownloadStateList.get(Long.valueOf(stateId));
        if (dataDownLoadState != null) {
            return dataDownLoadState;
        }
        DataDownLoadState dataDownloadStateById = GreenDaoUtil.getDataDownloadStateById(stateId);
        if (dataDownloadStateById != null) {
            this.mDownloadStateList.put(Long.valueOf(stateId), dataDownloadStateById);
        }
        return dataDownloadStateById;
    }

    private final DataPullConfigInfo getDataConfigInfo(long userId) {
        DataPullConfigInfo dataPullConfigInfo = this.mDataPullConfigMap.get(Long.valueOf(userId));
        if (dataPullConfigInfo != null) {
            return dataPullConfigInfo;
        }
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(userId);
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return null;
        }
        this.mDataPullConfigMap.put(Long.valueOf(userId), dataPullConfigInfoQueryDataPullConfigInfo);
        return dataPullConfigInfoQueryDataPullConfigInfo;
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void updateDataDownloadState(NewTask task, int state) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        if (task.getTaskInfo() == null) {
            return;
        }
        NewTask.NewTaskInfo taskInfo = task.getTaskInfo();
        if (taskInfo == null) {
            Intrinsics.throwNpe();
        }
        DataDownLoadState dataDownloadStateById = GreenDaoUtil.getDataDownloadStateById(taskInfo.getBuilder().getStateId());
        if (dataDownloadStateById != null) {
            dataDownloadStateById.setDownloadState(state);
            try {
                dataDownloadStateById.update();
            } catch (Exception unused) {
                GreenDaoUtil.addDataDownLoadStateRecord(dataDownloadStateById);
            }
            if (state == 3) {
                long userId = dataDownloadStateById.getUserId();
                String dataName = dataDownloadStateById.getDataName();
                Intrinsics.checkExpressionValueIsNotNull(dataName, "downloadState.dataName");
                checkAndFixHomeTaskState(userId, dataName);
            }
        }
    }

    private final void checkAndFixHomeTaskState(long userId, String taskTag) {
        DataDownLoadState dataDownLoadStateQueryHomeDataDownloadState = GreenDaoUtil.queryHomeDataDownloadState(userId, taskTag);
        if (dataDownLoadStateQueryHomeDataDownloadState == null || dataDownLoadStateQueryHomeDataDownloadState.getDownloadState() == 3) {
            return;
        }
        List<DataDownLoadState> listQueryHistoryDataDownloadState = GreenDaoUtil.queryHistoryDataDownloadState(userId, taskTag);
        List<DataDownLoadState> list = listQueryHistoryDataDownloadState;
        boolean z = false;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<DataDownLoadState> it = listQueryHistoryDataDownloadState.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            DataDownLoadState loadState = it.next();
            Intrinsics.checkExpressionValueIsNotNull(loadState, "loadState");
            if (loadState.getDownloadState() != 3) {
                break;
            }
        }
        if (z) {
            dataDownLoadStateQueryHomeDataDownloadState.setDownloadState(3);
            try {
                dataDownLoadStateQueryHomeDataDownloadState.update();
            } catch (Exception unused) {
                GreenDaoUtil.addDataDownLoadStateRecord(dataDownLoadStateQueryHomeDataDownloadState);
            }
        }
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void clearDataDownloadState() {
        this.mDownloadStateList.clear();
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void submitTask(TaskGroup taskGroup, List<NewTask> taskList) {
        Intrinsics.checkParameterIsNotNull(taskGroup, "taskGroup");
        Intrinsics.checkParameterIsNotNull(taskList, "taskList");
        if (!taskList.isEmpty()) {
            for (NewTask newTask : taskList) {
                ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.submit(newTask);
                }
            }
        }
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void submitTask(TaskGroup taskGroup, NewTask task) {
        Intrinsics.checkParameterIsNotNull(taskGroup, "taskGroup");
        Intrinsics.checkParameterIsNotNull(task, "task");
        ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.submit(task);
        }
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void updateProgress(NewTask task, int progress) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        if (task.getListener() == null) {
            return;
        }
        Task.Listenter listener = task.getListener();
        if (listener == null) {
            Intrinsics.throwNpe();
        }
        DataPullConfigInfo dataConfigInfo = getDataConfigInfo(listener.getUserId());
        if (dataConfigInfo != null) {
            int iAddAndGet = dataConfigInfo.getHasDownloadCount().addAndGet(progress);
            Companion companion = INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("更新用户(");
            Task.Listenter listener2 = task.getListener();
            sb.append(listener2 != null ? Long.valueOf(listener2.getUserId()) : null);
            sb.append(")的数据下拉进度, progress=");
            sb.append(progress);
            sb.append(",hasDownloadCount=");
            sb.append(iAddAndGet);
            companion.printAndSave(sb.toString());
            try {
                dataConfigInfo.update();
            } catch (Exception unused) {
                GreenDaoUtil.addDataPullConfigInfo(dataConfigInfo);
                Map<Long, DataPullConfigInfo> map = this.mDataPullConfigMap;
                Task.Listenter listener3 = task.getListener();
                if (listener3 == null) {
                    Intrinsics.throwNpe();
                }
                map.remove(Long.valueOf(listener3.getUserId()));
            }
            if (!mAllTaskExecutedCallback.isEmpty()) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C04551(dataConfigInfo, iAddAndGet, null), 2, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$updateProgress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$updateProgress$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DataPullConfigInfo $configInfo;
        final /* synthetic */ int $newProgress;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04551(DataPullConfigInfo dataPullConfigInfo, int i, Continuation continuation) {
            super(2, continuation);
            this.$configInfo = dataPullConfigInfo;
            this.$newProgress = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04551 c04551 = new C04551(this.$configInfo, this.$newProgress, completion);
            c04551.p$ = (CoroutineScope) obj;
            return c04551;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            int size = DataDownLoadService.mAllTaskExecutedCallback.size();
            for (int i = 0; i < size; i++) {
                try {
                    ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack = (ITaskExecutedTotalCallBack) DataDownLoadService.mAllTaskExecutedCallback.get(i);
                    Long userId = iTaskExecutedTotalCallBack.getUserId();
                    long userId2 = this.$configInfo.getUserId();
                    if (userId != null && userId.longValue() == userId2) {
                        iTaskExecutedTotalCallBack.updateTaskProgress(this.$newProgress);
                    }
                } catch (Exception unused) {
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$taskExecutedSuccess$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$taskExecutedSuccess$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ NewTask $task;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04541(NewTask newTask, Continuation continuation) {
            super(2, continuation);
            this.$task = newTask;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04541 c04541 = DataDownLoadService.this.new C04541(this.$task, completion);
            c04541.p$ = (CoroutineScope) obj;
            return c04541;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C04541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                DataDownLoadService.this.updateDataDownloadState(this.$task, 3);
                return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01241(null), 2, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$taskExecutedSuccess$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: DataDownLoadService.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$taskExecutedSuccess$1$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01241(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01241 c01241 = C04541.this.new C01241(completion);
                c01241.p$ = (CoroutineScope) obj;
                return c01241;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                DataDownLoadService.this.singleTaskExecutedSuccess(C04541.this.$task);
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void taskExecutedSuccess(NewTask task) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(task, "task");
        BuildersKt__BuildersKt.runBlocking$default(null, new C04541(task, null), 1, null);
    }

    /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$taskExecutedFailed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$taskExecutedFailed$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ NewTask $task;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04531(NewTask newTask, Continuation continuation) {
            super(2, continuation);
            this.$task = newTask;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04531 c04531 = DataDownLoadService.this.new C04531(this.$task, completion);
            c04531.p$ = (CoroutineScope) obj;
            return c04531;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C04531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                DataDownLoadService.this.updateDataDownloadState(this.$task, 4);
                return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01231(null), 2, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$taskExecutedFailed$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: DataDownLoadService.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$taskExecutedFailed$1$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01231(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01231 c01231 = C04531.this.new C01231(completion);
                c01231.p$ = (CoroutineScope) obj;
                return c01231;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                DataDownLoadService.this.singleTaskExecutedFailed(C04531.this.$task);
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void taskExecutedFailed(NewTask task, String errorMsg) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
        BuildersKt__BuildersKt.runBlocking$default(null, new C04531(task, null), 1, null);
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void taskExecutedStart(NewTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        updateDataDownloadState(task, 2);
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void taskExecutedCancel(NewTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        updateDataDownloadState(task, 4);
    }

    /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$allTaskComplete$1, reason: invalid class name */
    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$allTaskComplete$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                DataDownLoadService.INSTANCE.printAndSave("所有任务都执行完成了");
                DataDownLoadService.INSTANCE.checkAndAjustDownloadStateWhenStartPull(false);
                return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01221(null), 2, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$allTaskComplete$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: DataDownLoadService.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$allTaskComplete$1$1", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01221(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01221 c01221 = new C01221(completion);
                c01221.p$ = (CoroutineScope) obj;
                return c01221;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                if (!DataDownLoadService.mAllTaskExecutedCallback.isEmpty()) {
                    int size = DataDownLoadService.mAllTaskExecutedCallback.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ((ITaskExecutedTotalCallBack) DataDownLoadService.mAllTaskExecutedCallback.get(i)).onAllTaskCompleted(false);
                        } catch (Exception unused) {
                        }
                    }
                }
                if (!DataDownLoadService.mTaskExecutedCallback.isEmpty()) {
                    Iterator it = DataDownLoadService.mTaskExecutedCallback.iterator();
                    while (it.hasNext()) {
                        ((ITaskExecutedCallBack) it.next()).onTaskComplete();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void allTaskComplete() throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(null), 1, null);
    }

    @Override // com.ido.life.syncdownload.IDataDownLoad
    public void onGroupTaskComplete(Set<Long> taskIdSet) {
        Intrinsics.checkParameterIsNotNull(taskIdSet, "taskIdSet");
        INSTANCE.printAndSave("组任务执行完成taskIdSet=" + taskIdSet);
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        if (!taskIdSet.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<T> it = taskIdSet.iterator();
            while (it.hasNext()) {
                DataDownLoadState dataDownloadStateById = GreenDaoUtil.getDataDownloadStateById(((Number) it.next()).longValue());
                if (dataDownloadStateById != null) {
                    linkedHashSet.add(Long.valueOf(dataDownloadStateById.getUserId()));
                    booleanRef.element = dataDownloadStateById.getTaskId() == 0;
                }
            }
            if (!linkedHashSet.isEmpty()) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass2(linkedHashSet, taskIdSet, booleanRef, null), 2, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.life.syncdownload.DataDownLoadService$onGroupTaskComplete$2, reason: invalid class name */
    /* JADX INFO: compiled from: DataDownLoadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.syncdownload.DataDownLoadService$onGroupTaskComplete$2", f = "DataDownLoadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $homeTask;
        final /* synthetic */ Set $taskIdSet;
        final /* synthetic */ Set $userIdSet;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Set set, Set set2, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$userIdSet = set;
            this.$taskIdSet = set2;
            this.$homeTask = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = DataDownLoadService.this.new AnonymousClass2(this.$userIdSet, this.$taskIdSet, this.$homeTask, completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            Iterator it = this.$userIdSet.iterator();
            while (it.hasNext()) {
                long jLongValue = ((Number) it.next()).longValue();
                if (DataDownLoadService.this.historyDataAllComplete(jLongValue)) {
                    DataDownLoadService.INSTANCE.printAndSave("组任务执行完成taskIdSet=" + this.$taskIdSet + "，同时历史任务执行完成.");
                    int size = DataDownLoadService.mAllTaskExecutedCallback.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack = (ITaskExecutedTotalCallBack) DataDownLoadService.mAllTaskExecutedCallback.get(i);
                            Long userId = iTaskExecutedTotalCallBack.getUserId();
                            if (userId != null && userId.longValue() == jLongValue) {
                                iTaskExecutedTotalCallBack.onAllTaskCompleted(this.$homeTask.element);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean historyDataAllComplete(long userId) {
        List<DataDownLoadState> historyDataDownloadState = GreenDaoUtil.getHistoryDataDownloadState(userId);
        List<DataDownLoadState> list = historyDataDownloadState;
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (DataDownLoadState loadState : historyDataDownloadState) {
            Intrinsics.checkExpressionValueIsNotNull(loadState, "loadState");
            if (loadState.getDownloadState() != 3 && loadState.getDownloadState() != 4) {
                return false;
            }
        }
        return true;
    }

    public final void stopHomeTask(List<Long> userIdList) {
        Intrinsics.checkParameterIsNotNull(userIdList, "userIdList");
        if (userIdList.isEmpty()) {
            return;
        }
        INSTANCE.printAndSave("用户主动停止下拉首页数据。");
        Iterator<T> it = userIdList.iterator();
        while (it.hasNext()) {
            List<Long> homeTaskList = getHomeTaskList(((Number) it.next()).longValue());
            List<Long> list = homeTaskList;
            if (!(list == null || list.isEmpty())) {
                this.mRootTaskGroup.removeTaskList(homeTaskList);
            }
        }
    }

    private final void stopHistoryTask(List<Long> userIdList) {
        if (userIdList.isEmpty()) {
            return;
        }
        INSTANCE.printAndSave("用户主动停止下拉历史数据。");
        Iterator<T> it = userIdList.iterator();
        while (it.hasNext()) {
            long jLongValue = ((Number) it.next()).longValue();
            List<Long> historyTaskList = getHistoryTaskList(jLongValue);
            List<Long> list = historyTaskList;
            if (!(list == null || list.isEmpty())) {
                this.mRootTaskGroup.removeTaskList(historyTaskList);
            }
            int size = mAllTaskExecutedCallback.size();
            for (int i = 0; i < size; i++) {
                try {
                    ITaskExecutedTotalCallBack iTaskExecutedTotalCallBack = mAllTaskExecutedCallback.get(i);
                    Long userId = iTaskExecutedTotalCallBack.getUserId();
                    if (userId != null && userId.longValue() == jLongValue) {
                        iTaskExecutedTotalCallBack.onAllTaskCompleted(false);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }
}