package com.ido.life.boatservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.ble.event.stat.one.d;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.DistenceDetailUploadBean;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.bean.ServerBean;
import com.ido.life.bean.ServerHeartRateDayUploadBean;
import com.ido.life.bean.UploadPressureBean;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.UploadMedal;
import com.ido.life.data.api.entity.UploadWeightBean;
import com.ido.life.data.api.entity.WeeklyReportStatusEntity;
import com.ido.life.data.api.entity.WeeklyReportStatusUploadEntity;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.HomeCard;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MapEngine;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportCard;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportHealthUpLoad;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.TempUnitSetting;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UploadNoiseHealthBean;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.net.BaseUrl;
import com.ido.life.net.JsonRequestBody;
import com.ido.life.syncdownload.BaseTaskListener;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.TimeUtil;
import com.ido.life.util.UnitUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.ido.life.util.eventbus.EventBusWrapper;
import com.ido.life.util.eventbus.IHandlerEventBus;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import no.nordicsemi.android.dfu.DfuBaseService;
import okhttp3.CacheControl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: DataUploadService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b2\u0018\u0000 u2\u00020\u00012\u00020\u0002:\fstuvwxyz{|}~B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00110\u0011H\u0002J\u0016\u0010\u001a\u001a\u00020\u00122\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0016\u0010\u001e\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001cH\u0002J\u0016\u0010!\u001a\u00020\u00122\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001cH\u0002J\u0016\u0010$\u001a\u00020\u00122\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u001cH\u0002J\u0016\u0010'\u001a\u00020\u00122\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u001cH\u0002J\u0016\u0010*\u001a\u00020\u00122\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u001cH\u0002J\u0016\u0010-\u001a\u00020\u00122\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020.0\u001cH\u0002J\u0012\u0010/\u001a\u0004\u0018\u00010\u00052\u0006\u00100\u001a\u00020\bH\u0002J\"\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0002J \u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u000206H\u0002J.\u00101\u001a\u0002022\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005082\u0006\u00104\u001a\u00020\u00052\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0002J,\u00101\u001a\u0002022\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005082\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u000206H\u0002J\u0016\u00109\u001a\u00020\u00122\f\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u001cH\u0002J\u001c\u0010<\u001a\u00020\u00122\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00110\u0011H\u0002J\u0016\u0010=\u001a\u00020\u00122\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u001cH\u0002J\u0016\u0010@\u001a\u00020\u00122\f\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u001cH\u0002J\u0016\u0010C\u001a\u00020\u00122\f\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\u001cH\u0002J\u0016\u0010F\u001a\u00020G2\f\u0010H\u001a\b\u0012\u0002\b\u0003\u0018\u00010IH\u0016J\u0014\u0010J\u001a\u0004\u0018\u00010K2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\b\u0010N\u001a\u00020GH\u0016J\"\u0010O\u001a\u00020\b2\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010P\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\bH\u0016J\u000e\u0010R\u001a\u00020G2\u0006\u0010S\u001a\u00020\u0005J\b\u0010T\u001a\u00020GH\u0002J\b\u0010U\u001a\u00020GH\u0002J\b\u0010V\u001a\u00020GH\u0002J\b\u0010W\u001a\u00020GH\u0002J\b\u0010X\u001a\u00020GH\u0002J\b\u0010Y\u001a\u00020GH\u0002J\b\u0010Z\u001a\u00020GH\u0002J\b\u0010[\u001a\u00020GH\u0002J\b\u0010\\\u001a\u00020GH\u0002J\b\u0010]\u001a\u00020GH\u0002J\b\u0010^\u001a\u00020GH\u0002J\b\u0010_\u001a\u00020GH\u0002J\b\u0010`\u001a\u00020GH\u0002J\b\u0010a\u001a\u00020GH\u0002J\b\u0010b\u001a\u00020GH\u0002J\b\u0010c\u001a\u00020GH\u0002J\b\u0010d\u001a\u00020GH\u0002J\b\u0010e\u001a\u00020GH\u0002J\b\u0010f\u001a\u00020GH\u0002J\b\u0010g\u001a\u00020GH\u0002J\b\u0010h\u001a\u00020GH\u0002J\b\u0010i\u001a\u00020GH\u0002J\b\u0010j\u001a\u00020GH\u0002J\b\u0010k\u001a\u00020GH\u0002J\b\u0010l\u001a\u00020GH\u0002J\b\u0010m\u001a\u00020GH\u0002J\b\u0010n\u001a\u00020GH\u0002J\b\u0010o\u001a\u00020GH\u0002J\b\u0010p\u001a\u00020GH\u0002J\b\u0010q\u001a\u00020GH\u0002J\b\u0010r\u001a\u00020GH\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u007f"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService;", "Landroid/app/Service;", "Lcom/ido/life/util/eventbus/IHandlerEventBus;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "UPLOAD_MAX_COUNT", "", "mAtomicCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "mEventBus", "Lcom/ido/life/util/eventbus/EventBusWrapper;", "mMaualUpload", "", "mNeedMessage", "mTaskList", "", "Lcom/ido/life/syncdownload/Task;", "mUploadSuccess", "mUserId", "", "convertToServerSleepUploadBean", "Lcom/ido/life/boatservice/DataUploadService$SleepDayUpload;", "sleepList", "Lcom/ido/life/database/model/ServerSleepDayData;", "getActiveTask", "activeList", "", "Lcom/ido/life/database/model/SportHealth;", "getActiveTimeTask", "activeTimeList", "Lcom/ido/life/database/model/ActiveTimeDayData;", "getAmbientNoiseTask", "heartList", "Lcom/ido/life/database/model/HealthVolumeData;", "getBloodOxyTask", "bloodOxyList", "Lcom/ido/life/database/model/ServerBloodOxyDayData;", "getCalorieTask", "calorieList", "Lcom/ido/life/database/model/CalorieDayData;", "getDistanceTask", "distanceList", "Lcom/ido/life/database/model/SportDistanceBean;", "getHeartRateTask", "Lcom/ido/life/database/model/ServerHeartRateDayData;", "getHomeCardKey", "cardType", "getNormalUpLoadRequest", "Lokhttp3/Request;", "header", "url", "body", "Lokhttp3/RequestBody;", "headerMap", "", "getPressureTask", "pressureList", "Lcom/ido/life/database/model/HealthPressure;", "getSleepTask", "getStepTask", "stepList", "Lcom/ido/life/database/model/StepDayData;", "getWalkTask", "walkList", "Lcom/ido/life/database/model/WalkDayData;", "getWeightUploadTask", "weightList", "Lcom/ido/life/database/model/WeightItemBean;", "handleMessage", "", "msgEvent", "Lcom/ido/life/base/BaseMessage;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "onStartCommand", "flags", "startId", "printAndSave", "message", "uploadActiveData", "uploadActiveTime", "uploadBloodOxy", "uploadCalorie", "uploadDistance", "uploadHealthData", "uploadHeartRate", "uploadMapEngineSetting", "uploadMensSetting", "uploadMenstrual", "uploadNoiseData", "uploadOtherData", "uploadPressure", "uploadPrivateData", "uploadPrivateSafeSetting", "uploadSleep", "uploadSport", "uploadSportCardList", "uploadStep", "uploadTempSetting", "uploadTimeFormatSetting", "uploadUnitSetting", "uploadUserHeader", "uploadUserHomeCardList", "uploadUserInfo", "uploadUserMedal", "uploadUserTarget", "uploadWalk", "uploadWeekStartSetting", "uploadWeeklyReportStatus", "uploadWeightRecordData", "ActiveTimeDayDataUpload", "CalorieDayDataUpload", "Companion", "DataUploadListenter", "MensConfigUploadBean", "ServerBloodOxyDayDataUpload", "ServerMenstrualUpload", "SleepDayDataUpload", "SleepDayItemUpload", "SleepDayUpload", "StepDayDataUpload", "WalkDayDataUpload", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DataUploadService extends Service implements IHandlerEventBus {
    private boolean mMaualUpload;
    private boolean mNeedMessage;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String NEED_CALLBACK = "need_callback";
    private static final String MANUAL_UPLOAD = "manual_upload";
    private final int UPLOAD_MAX_COUNT = 30;
    private final String TAG = DataUploadService.class.getSimpleName();
    private long mUserId = -1;
    private boolean mUploadSuccess = true;
    private List<Task> mTaskList = new ArrayList();
    private AtomicInteger mAtomicCount = new AtomicInteger(0);
    private EventBusWrapper mEventBus = new EventBusWrapper();

    private final String getHomeCardKey(int cardType) {
        switch (cardType) {
            case 1:
                return HomeCard.STEP_CARD;
            case 2:
            case 10:
            default:
                return null;
            case 3:
                return "SPORT_CARD";
            case 4:
                return HomeCard.SLEEP_CARD;
            case 5:
                return HomeCard.HEARTRATE_CARD;
            case 6:
                return HomeCard.PRESSURE_CARD;
            case 7:
                return HomeCard.BLOODOXY_CARD;
            case 8:
                return HomeCard.WEIGHT_CARD;
            case 9:
                return HomeCard.MENSES_CARD;
            case 11:
                return HomeCard.NOISE_CARD;
            case 12:
                return HomeCard.OXYGEN_UPTAKE_CARD;
        }
    }

    public static final String getMANUAL_UPLOAD() {
        Companion companion = INSTANCE;
        return MANUAL_UPLOAD;
    }

    public static final String getNEED_CALLBACK() {
        Companion companion = INSTANCE;
        return NEED_CALLBACK;
    }

    @JvmStatic
    public static final void start(boolean z, boolean z2) {
        INSTANCE.start(z, z2);
    }

    @JvmStatic
    public static final void stop() {
        INSTANCE.stop();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007J\b\u0010\u0010\u001a\u00020\fH\u0007R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$Companion;", "", "()V", "MANUAL_UPLOAD", "", "MANUAL_UPLOAD$annotations", "getMANUAL_UPLOAD", "()Ljava/lang/String;", "NEED_CALLBACK", "NEED_CALLBACK$annotations", "getNEED_CALLBACK", "start", "", "needCallback", "", "manualUpload", "stop", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void MANUAL_UPLOAD$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void NEED_CALLBACK$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getNEED_CALLBACK() {
            return DataUploadService.NEED_CALLBACK;
        }

        public final String getMANUAL_UPLOAD() {
            return DataUploadService.MANUAL_UPLOAD;
        }

        public static /* synthetic */ void start$default(Companion companion, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            if ((i & 2) != 0) {
                z2 = false;
            }
            companion.start(z, z2);
        }

        @JvmStatic
        public final void start(boolean needCallback, boolean manualUpload) {
            stop();
            try {
                Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) DataUploadService.class);
                intent.putExtra(getNEED_CALLBACK(), needCallback);
                intent.putExtra(getMANUAL_UPLOAD(), manualUpload);
                IdoApp.getAppContext().startService(intent);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), "DataUploadNewService", "fun start()  IdoApp.getAppContext().startService");
            } catch (Exception e2) {
                e2.printStackTrace();
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                String loginRegisterLogPath = logPathImpl2.getLoginRegisterLogPath();
                StringBuilder sb = new StringBuilder();
                sb.append("异常日志printStackTrace：");
                e2.printStackTrace();
                sb.append(Unit.INSTANCE);
                CommonLogUtil.printAndSave(loginRegisterLogPath, "DataUploadNewService", sb.toString());
            }
        }

        @JvmStatic
        public final void stop() {
            EventBusHelper.post(Constants.EventConstants.EVENT_STOP_DATA_UPLOAD_SERVICE);
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), "DataUploadNewService", "fun stop()  EVENT_STOP_DATA_UPLOAD_SERVICE");
        }
    }

    public DataUploadService() {
        this.mEventBus.register(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            this.mNeedMessage = intent.getBooleanExtra(NEED_CALLBACK, false);
            this.mMaualUpload = intent.getBooleanExtra(MANUAL_UPLOAD, false);
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mUserId = runTimeUtil.getUserId();
        printAndSave("是否需要发送通知mNeedMessage=" + this.mNeedMessage + ",mUserId=" + this.mUserId);
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "是否需要发送通知" + this.mNeedMessage);
        if (this.mUserId == -1) {
            printAndSave("用户处于游客状态,停止数据上传.");
            LogPath logPathImpl2 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "用户处于游客状态,停止数据上传.");
            stopSelf();
        } else {
            uploadPrivateData();
            uploadSport();
            uploadHealthData();
            uploadOtherData();
            List<Task> list = this.mTaskList;
            if (list == null || list.isEmpty()) {
                Object obj = SPUtils.get(Constants.APP_LOGIN_OUT, false);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                }
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (this.mNeedMessage || zBooleanValue) {
                    printAndSave("没有需要上传的数据,发送通知");
                    SPUtils.put(Constants.APP_LOGIN_OUT, false);
                    LogPath logPathImpl3 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
                    CommonLogUtil.printAndSave(logPathImpl3.getLoginRegisterLogPath(), this.TAG, "没有需要上传的数据,发送通知");
                    EventBusHelper.post(Constants.EventConstants.EVENT_QUIT_USER_DATA_UPLOAD_SUCCESS);
                } else if (this.mMaualUpload) {
                    String time = TimeUtil.formatTimeNoS(new Date(System.currentTimeMillis()));
                    Intrinsics.checkExpressionValueIsNotNull(time, "time");
                    String strReplace$default = StringsKt.replace$default(time, "-", "/", false, 4, (Object) null);
                    SPUtils.put(Constants.SYNC_DATA_TIME, strReplace$default);
                    printAndSave("手动同步数据 没有需要上传的数据,发送通知 结束同步:" + strReplace$default);
                    EventBusHelper.post(Constants.EventConstants.EVENT_TYPE_MANUAL_SYNC_DATA_SUCCESS);
                    LogPath logPathImpl4 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl4, "LogPathImpl.getInstance()");
                    CommonLogUtil.printAndSave(logPathImpl4.getLoginRegisterLogPath(), this.TAG, "手动同步数据 没有需要上传的数据,发送通知 结束同步:" + strReplace$default);
                } else {
                    printAndSave("没有需要上传的数据,不发送通知");
                    LogPath logPathImpl5 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl5, "LogPathImpl.getInstance()");
                    CommonLogUtil.printAndSave(logPathImpl5.getLoginRegisterLogPath(), this.TAG, "没有需要上传的数据,不发送通知");
                }
                stopSelf();
            } else {
                String time2 = TimeUtil.formatTimeNoS(new Date(System.currentTimeMillis()));
                Intrinsics.checkExpressionValueIsNotNull(time2, "time");
                String strReplace$default2 = StringsKt.replace$default(time2, "-", "/", false, 4, (Object) null);
                SPUtils.put(Constants.SYNC_DATA_TIME, strReplace$default2);
                printAndSave("手动同步数据 有需要上传的数据,发送通知 结束同步:" + strReplace$default2);
                LogPath logPathImpl6 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl6, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl6.getLoginRegisterLogPath(), this.TAG, "手动同步数据 有需要上传的数据,发送通知 结束同步:" + strReplace$default2);
                this.mAtomicCount.set(this.mTaskList.size());
                Iterator<T> it = this.mTaskList.iterator();
                while (it.hasNext()) {
                    ((Task) it.next()).startExecute();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private final void uploadPrivateData() {
        if (this.mMaualUpload) {
            printAndSave("手动上传,不上传隐私数据.");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "手动上传,不上传隐私数据.");
            return;
        }
        if (!RunTimeUtil.getInstance().enableUploadPrivateData()) {
            printAndSave("上传隐私数据开关处于关闭状态,停止上传隐私数据");
            LogPath logPathImpl2 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "上传隐私数据开关处于关闭状态,停止上传隐私数据");
            return;
        }
        uploadUserHeader();
        uploadUserMedal();
        uploadUserTarget();
        uploadWeightRecordData();
    }

    private final void uploadSport() {
        if (!RunTimeUtil.getInstance().enableUploadSportData()) {
            if (!this.mMaualUpload) {
                printAndSave("不是手动上传并且已经关闭了运动上传开关,不上传运动数据");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "不是手动上传并且已经关闭了运动上传开关,不上传运动数据");
                return;
            }
            printAndSave("用户关闭了运动上传开关,但是是手动触发的,需要上传运动数据");
            LogPath logPathImpl2 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "用户关闭了运动上传开关,但是是手动触发的,需要上传运动数据");
        }
        uploadStep();
        uploadDistance();
        uploadCalorie();
        uploadActiveTime();
        uploadWalk();
        uploadActiveData();
    }

    private final void uploadHealthData() {
        if (!RunTimeUtil.getInstance().enableUploadHealthData()) {
            if (!this.mMaualUpload) {
                printAndSave("用户关闭了健康数据上传开关并且是非手动上传,不上传健康数据");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "用户关闭了健康数据上传开关并且是非手动上传,不上传健康数据");
                return;
            }
            printAndSave("用户关闭了健康数据上传开关,但是是用户手动触发,上传健康数据");
            LogPath logPathImpl2 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "用户关闭了健康数据上传开关并且是非手动上传,不上传健康数据");
        }
        uploadHeartRate();
        uploadSleep();
        uploadBloodOxy();
        uploadPressure();
        uploadMenstrual();
        uploadNoiseData();
    }

    private final void uploadOtherData() {
        uploadPrivateSafeSetting();
        uploadUserHomeCardList();
        uploadSportCardList();
        uploadUnitSetting();
        uploadTimeFormatSetting();
        uploadTempSetting();
        uploadWeekStartSetting();
        uploadMapEngineSetting();
        uploadWeeklyReportStatus();
        uploadMensSetting();
    }

    private final void uploadWeightRecordData() {
        List<WeightItemBean> listQueryAllUnUploadWeightData = GreenDaoUtil.queryAllUnUploadWeightData(this.mUserId);
        List<WeightItemBean> list = listQueryAllUnUploadWeightData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的体重修改记录");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的体重修改记录");
            return;
        }
        int size = listQueryAllUnUploadWeightData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    WeightItemBean weightItemBean = listQueryAllUnUploadWeightData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(weightItemBean, "weightList[start]");
                    arrayList.add(weightItemBean);
                    i5++;
                }
                this.mTaskList.add(getWeightUploadTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getWeightUploadTask(listQueryAllUnUploadWeightData));
    }

    private final void uploadStep() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        List<StepDayData> allNotUploadStepDayData = localHealthDataManager.getAllNotUploadStepDayData();
        List<StepDayData> list = allNotUploadStepDayData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的步数数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的步数数据");
            return;
        }
        int size = allNotUploadStepDayData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    StepDayData stepDayData = allNotUploadStepDayData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(stepDayData, "stepList[start]");
                    arrayList.add(stepDayData);
                    i5++;
                }
                this.mTaskList.add(getStepTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getStepTask(allNotUploadStepDayData));
    }

    private final void uploadActiveTime() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        List<ActiveTimeDayData> allNotUploadActiveTimeDayData = localHealthDataManager.getAllNotUploadActiveTimeDayData();
        List<ActiveTimeDayData> list = allNotUploadActiveTimeDayData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的活动时长数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的活动时长数据");
            return;
        }
        int size = allNotUploadActiveTimeDayData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    ActiveTimeDayData activeTimeDayData = allNotUploadActiveTimeDayData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(activeTimeDayData, "activeTimeList[start]");
                    arrayList.add(activeTimeDayData);
                    i5++;
                }
                this.mTaskList.add(getActiveTimeTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getActiveTimeTask(allNotUploadActiveTimeDayData));
    }

    private final void uploadActiveData() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        List<SportHealth> allNotUploadActivityData = localHealthDataManager.getAllNotUploadActivityData();
        List<SportHealth> list = allNotUploadActivityData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的活动数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的活动数据");
            return;
        }
        for (SportHealth sportHealth : allNotUploadActivityData) {
            if (sportHealth != null) {
                sportHealth.setHr_data_vlaue_json(sportHealth.getHr_data_vlaue_json());
                sportHealth.setStepItem(sportHealth.getStepItem());
                sportHealth.setRangeItem(sportHealth.getRangeItem());
                sportHealth.setTimestamp(DateUtil.getLongFromDateStr(sportHealth.getDateTime()));
                if (sportHealth.getIsLocus() == 1) {
                    LocalHealthDataManager localHealthDataManager2 = LocalHealthDataManager.getInstance();
                    RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                    SportGpsData sportGpsData = localHealthDataManager2.getSportGpsData(runTimeUtil.getUserId(), DateUtil.getLongFromDateStr(sportHealth.getStartTime()));
                    if (sportGpsData != null) {
                        sportHealth.setGps(sportGpsData.getGpsData());
                    }
                }
                Log.d(this.TAG, "uploadActiveData: " + sportHealth);
            }
        }
        int size = allNotUploadActivityData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    SportHealth sportHealth2 = allNotUploadActivityData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(sportHealth2, "dataList[start]");
                    arrayList.add(sportHealth2);
                    i5++;
                }
                this.mTaskList.add(getActiveTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getActiveTask(allNotUploadActivityData));
    }

    private final void uploadDistance() {
        List<SportDistanceBean> listQueryAllUnUploadDistanceData = GreenDaoUtil.queryAllUnUploadDistanceData(this.mUserId);
        List<SportDistanceBean> list = listQueryAllUnUploadDistanceData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的距离数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的距离数据");
            return;
        }
        int size = listQueryAllUnUploadDistanceData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    SportDistanceBean sportDistanceBean = listQueryAllUnUploadDistanceData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(sportDistanceBean, "dataList[start]");
                    arrayList.add(sportDistanceBean);
                    i5++;
                }
                this.mTaskList.add(getDistanceTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getDistanceTask(listQueryAllUnUploadDistanceData));
    }

    private final void uploadHeartRate() {
        List<ServerHeartRateDayData> allNotUploadHeartRateDayData = LocalHealthDataManager.getInstance().getAllNotUploadHeartRateDayData(this.mUserId);
        List<ServerHeartRateDayData> list = allNotUploadHeartRateDayData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的心率数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的心率数据");
            return;
        }
        int size = allNotUploadHeartRateDayData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    ServerHeartRateDayData serverHeartRateDayData = allNotUploadHeartRateDayData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(serverHeartRateDayData, "dataList[start]");
                    arrayList.add(serverHeartRateDayData);
                    i5++;
                }
                this.mTaskList.add(getHeartRateTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getHeartRateTask(allNotUploadHeartRateDayData));
    }

    private final void uploadSleep() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        List<ServerSleepDayData> allNotUploadSleepDayData = localHealthDataManager.getAllNotUploadSleepDayData();
        List<ServerSleepDayData> list = allNotUploadSleepDayData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的睡眠数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的睡眠数据");
            return;
        }
        int size = allNotUploadSleepDayData.size();
        ArrayList arrayList = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < size; i++) {
            ServerSleepDayData serverSleepDayData = allNotUploadSleepDayData.get(i);
            if (serverSleepDayData != null) {
                if (serverSleepDayData == null) {
                    Intrinsics.throwNpe();
                }
                List list2 = (List) linkedHashMap.get(serverSleepDayData.getDate());
                if (list2 == null) {
                    List<ServerSleepDayData> listMutableListOf = CollectionsKt.mutableListOf(serverSleepDayData);
                    arrayList.add(listMutableListOf);
                    String date = serverSleepDayData.getDate();
                    Intrinsics.checkExpressionValueIsNotNull(date, "item!!.date");
                    linkedHashMap.put(date, listMutableListOf);
                } else {
                    list2.add(serverSleepDayData);
                }
            }
        }
        int size2 = arrayList.size();
        int i2 = this.UPLOAD_MAX_COUNT;
        if (size2 > i2) {
            int i3 = (size2 / i2) + (size2 % i2 == 0 ? 0 : 1);
            for (int i4 = 0; i4 < i3; i4++) {
                ArrayList arrayList2 = new ArrayList();
                int i5 = this.UPLOAD_MAX_COUNT;
                int i6 = i4 * i5;
                int i7 = i5 + i6;
                while (i6 < i7 && i6 < size2) {
                    arrayList2.add(arrayList.get(i6));
                    i6++;
                }
                this.mTaskList.add(getSleepTask(arrayList2));
            }
            return;
        }
        this.mTaskList.add(getSleepTask(arrayList));
    }

    private final void uploadCalorie() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        List<CalorieDayData> allNotUploadCalorieDayData = localHealthDataManager.getAllNotUploadCalorieDayData();
        List<CalorieDayData> list = allNotUploadCalorieDayData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的卡路里数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的卡路里数据");
            return;
        }
        int size = allNotUploadCalorieDayData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    CalorieDayData calorieDayData = allNotUploadCalorieDayData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(calorieDayData, "dataList[start]");
                    arrayList.add(calorieDayData);
                    i5++;
                }
                this.mTaskList.add(getCalorieTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getCalorieTask(allNotUploadCalorieDayData));
    }

    private final void uploadBloodOxy() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        List<ServerBloodOxyDayData> allNotUploadBloodOxyData = localHealthDataManager.getAllNotUploadBloodOxyData();
        List<ServerBloodOxyDayData> list = allNotUploadBloodOxyData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的血氧数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的血氧数据");
            return;
        }
        int size = allNotUploadBloodOxyData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    ServerBloodOxyDayData serverBloodOxyDayData = allNotUploadBloodOxyData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(serverBloodOxyDayData, "dataList[start]");
                    arrayList.add(serverBloodOxyDayData);
                    i5++;
                }
                this.mTaskList.add(getBloodOxyTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getBloodOxyTask(allNotUploadBloodOxyData));
    }

    private final void uploadPressure() {
        List<HealthPressure> listQueryAllUnUploadPressureData = GreenDaoUtil.queryAllUnUploadPressureData(this.mUserId);
        List<HealthPressure> list = listQueryAllUnUploadPressureData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的压力数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的压力数据");
            return;
        }
        int size = listQueryAllUnUploadPressureData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    HealthPressure healthPressure = listQueryAllUnUploadPressureData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(healthPressure, "dataList[start]");
                    arrayList.add(healthPressure);
                    i5++;
                }
                this.mTaskList.add(getPressureTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getPressureTask(listQueryAllUnUploadPressureData));
    }

    private final void uploadWalk() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        List<WalkDayData> allNotUploadWalkData = localHealthDataManager.getAllNotUploadWalkData();
        List<WalkDayData> list = allNotUploadWalkData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的走动数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的走动数据");
            return;
        }
        int size = allNotUploadWalkData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    WalkDayData walkDayData = allNotUploadWalkData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(walkDayData, "dataList[start]");
                    arrayList.add(walkDayData);
                    i5++;
                }
                this.mTaskList.add(getWalkTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getWalkTask(allNotUploadWalkData));
    }

    private final void uploadMenstrual() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        final List<LifeCycleItemBean> allUploadLifeCycleBean = GreenDaoUtil.getAllUploadLifeCycleBean(runTimeUtil.getUserId());
        List<LifeCycleItemBean> list = allUploadLifeCycleBean;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的生理周期数据");
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(BaseUrl.URL_NAME, BaseUrl.URL_NAME_HEALTH);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(linkedHashMap, "http://cloud.user.veryfitplus.com//api/menstrual/v2/upload", GsonUtil.toJson(new ServerBean.LifeCycleUploadBean(allUploadLifeCycleBean)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadMenstrual.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                printAndSaveLog("生理周期数据上传成功。");
                for (LifeCycleItemBean itemBean : allUploadLifeCycleBean) {
                    try {
                        Intrinsics.checkExpressionValueIsNotNull(itemBean, "itemBean");
                        itemBean.setUpload(true);
                        itemBean.update();
                    } catch (Exception unused) {
                        GreenDaoUtil.insertLifeCycle(itemBean);
                    }
                }
                return true;
            }
        }, 0, 4, null));
    }

    private final void uploadMensSetting() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        final MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(runTimeUtil.getUserId());
        if (menstruationConfigQueryMenstruationConfig == null || menstruationConfigQueryMenstruationConfig.getUploadSuccess()) {
            printAndSave("没有需要上传的生理周期配置数据");
            return;
        }
        MensConfigUploadBean mensConfigUploadBean = new MensConfigUploadBean(menstruationConfigQueryMenstruationConfig.getMensLength(), menstruationConfigQueryMenstruationConfig.getMensCycle());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("timestamp", String.valueOf(menstruationConfigQueryMenstruationConfig.getUpdateTimeStamp()));
        String json = GsonUtil.toJson(mensConfigUploadBean);
        Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtil.toJson(value)");
        linkedHashMap.put("value", json);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(BaseUrl.URL_NAME, BaseUrl.URL_NAME_USER);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(linkedHashMap2, "http://cloud.user.veryfitplus.com/userapi/prefs/menses/config/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadMensSetting.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                printAndSaveLog("生理周期配置数据上传成功");
                menstruationConfigQueryMenstruationConfig.setUploadSuccess(true);
                try {
                    menstruationConfigQueryMenstruationConfig.update();
                } catch (Exception unused) {
                    GreenDaoUtil.addMenstruationConfig(menstruationConfigQueryMenstruationConfig);
                }
                return true;
            }
        }, 0, 4, null));
    }

    private final void uploadNoiseData() {
        List<HealthVolumeData> allNotUploadAmbientVolumeDayData = GreenDaoUtil.getAllNotUploadAmbientVolumeDayData(this.mUserId);
        List<HealthVolumeData> list = allNotUploadAmbientVolumeDayData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的环境音量数据");
            return;
        }
        int size = allNotUploadAmbientVolumeDayData.size();
        int i = this.UPLOAD_MAX_COUNT;
        if (size > i) {
            int i2 = (size / i) + (size % i == 0 ? 0 : 1);
            for (int i3 = 0; i3 < i2; i3++) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.UPLOAD_MAX_COUNT;
                int i5 = i3 * i4;
                int i6 = i4 + i5;
                while (i5 < i6 && i5 < size) {
                    HealthVolumeData healthVolumeData = allNotUploadAmbientVolumeDayData.get(i5);
                    Intrinsics.checkExpressionValueIsNotNull(healthVolumeData, "dataList[start]");
                    arrayList.add(healthVolumeData);
                    i5++;
                }
                this.mTaskList.add(getAmbientNoiseTask(arrayList));
            }
            return;
        }
        this.mTaskList.add(getAmbientNoiseTask(allNotUploadAmbientVolumeDayData));
    }

    private final void uploadUserMedal() {
        final List<UserMedalInfo> listQueryAllUnUploadMedalData = GreenDaoUtil.queryAllUnUploadMedalData(this.mUserId);
        List<UserMedalInfo> list = listQueryAllUnUploadMedalData;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的勋章数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的勋章数据");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (UserMedalInfo medalInfo : listQueryAllUnUploadMedalData) {
            Intrinsics.checkExpressionValueIsNotNull(medalInfo, "medalInfo");
            arrayList.add(new UploadMedal.UploadMedalItem(medalInfo.getMedalId(), medalInfo.getDate()));
        }
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/medal/V2/save", GsonUtil.toJson(new UploadMedal(arrayList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadUserMedal.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("勋章数据上传成功");
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "勋章数据上传成功");
                List list2 = listQueryAllUnUploadMedalData;
                if (!(list2 == null || list2.isEmpty())) {
                    for (UserMedalInfo it : listQueryAllUnUploadMedalData) {
                        try {
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            it.setUploadSuccess(true);
                            it.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("勋章数据上传失败:" + taskInfo);
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "勋章数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    private final void uploadUserHeader() {
        final UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(this.mUserId);
        if (userInfoQueryUserInfo != null && !userInfoQueryUserInfo.isServerImageUrl() && !TextUtils.isEmpty(userInfoQueryUserInfo.getAvatarUrl())) {
            File file = new File(userInfoQueryUserInfo.getAvatarUrl());
            if (file.exists() && file.isFile()) {
                MultipartBody partBody = new MultipartBody.Builder().addPart(MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM), file))).build();
                List<Task> list = this.mTaskList;
                Intrinsics.checkExpressionValueIsNotNull(partBody, "partBody");
                list.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/userbody/update/avatarUrl", partBody)), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadUserHeader.1
                    @Override // com.ido.life.syncdownload.Task.Listenter
                    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                        UserInfo userInfo;
                        String str = response;
                        if (!(str == null || str.length() == 0) && (userInfo = userInfoQueryUserInfo) != null && !userInfo.isServerImageUrl()) {
                            try {
                                userInfoQueryUserInfo.setAvatarUrl(new JSONObject(response).getString("result"));
                                userInfoQueryUserInfo.setServerImageUrl(true);
                                userInfoQueryUserInfo.update();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        return true;
                    }

                    @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
                    public void onAllTaskComplete() {
                        super.onAllTaskComplete();
                        DataUploadService.this.printAndSave("用户头像数据上传成功");
                        LogPath logPathImpl = LogPathImpl.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "用户头像数据上传成功");
                        DataUploadService.this.uploadUserInfo();
                    }

                    @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
                    public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                        DataUploadService.this.printAndSave("用户头像数据上传失败:" + taskInfo);
                        LogPath logPathImpl = LogPathImpl.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "用户头像数据上传失败:" + taskInfo);
                    }
                }, 0, 4, null));
                return;
            }
            uploadUserInfo();
            return;
        }
        uploadUserInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.UserInfo] */
    public final void uploadUserInfo() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryUserInfo(this.mUserId);
        if (((UserInfo) objectRef.element) == null || ((UserInfo) objectRef.element).isUploadSuccess()) {
            printAndSave("没有需要上传的用户信息数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的用户信息数据");
            return;
        }
        UserInfo userInfoM27clone = ((UserInfo) objectRef.element).m27clone();
        Intrinsics.checkExpressionValueIsNotNull(userInfoM27clone, "userInfo.clone()");
        if (userInfoM27clone.getHeightUnit() == 2) {
            userInfoM27clone.setHeight(UnitUtil.inch2Cm(userInfoM27clone.getHeight()));
        }
        if (userInfoM27clone.getWeightUnit() == 2) {
            userInfoM27clone.setWeight(MathKt.roundToInt(UnitUtil.getPound2Kg(userInfoM27clone.getWeight())));
        }
        userInfoM27clone.setWeightUnit(1);
        userInfoM27clone.setHeightUnit(1);
        if (!TextUtils.isEmpty(userInfoM27clone.getBirthday())) {
            String birthday = userInfoM27clone.getBirthday();
            Intrinsics.checkExpressionValueIsNotNull(birthday, "uploadUserInfo.birthday");
            userInfoM27clone.setBirthday(new Regex("/").replace(birthday, "-"));
        }
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/userbody/update/userinfo", GsonUtil.toJson(userInfoM27clone))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadUserInfo.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户信息数据上传成功");
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "用户信息数据上传成功");
                if (((UserInfo) objectRef.element) != null) {
                    try {
                        ((UserInfo) objectRef.element).setUploadSuccess(true);
                        ((UserInfo) objectRef.element).update();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户信息数据上传失败:" + taskInfo);
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "用户信息数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.PrivateSafeSetting] */
    private final void uploadPrivateSafeSetting() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryPrivateSafeSetting(this.mUserId);
        if (((PrivateSafeSetting) objectRef.element) == null || ((PrivateSafeSetting) objectRef.element).getUploadSuccess()) {
            printAndSave("没有需要上传的用户开关设置数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的用户开关设置数据");
            return;
        }
        ArrayList arrayList = new ArrayList();
        boolean savePrivateData = ((PrivateSafeSetting) objectRef.element).getSavePrivateData();
        String str = SavePrivateSafeSettingBean.ON;
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_PRIVATE, savePrivateData ? SavePrivateSafeSettingBean.ON : SavePrivateSafeSettingBean.OFF, ((PrivateSafeSetting) objectRef.element).getUpdateTime()));
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_HEALTH, ((PrivateSafeSetting) objectRef.element).getSaveHealthData() ? SavePrivateSafeSettingBean.ON : SavePrivateSafeSettingBean.OFF, ((PrivateSafeSetting) objectRef.element).getUpdateTime()));
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_SPORT, ((PrivateSafeSetting) objectRef.element).getSaveSportData() ? SavePrivateSafeSettingBean.ON : SavePrivateSafeSettingBean.OFF, ((PrivateSafeSetting) objectRef.element).getUpdateTime()));
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_GOOGLE_FIT, ((PrivateSafeSetting) objectRef.element).getSaveToGoogleFit() ? SavePrivateSafeSettingBean.ON : SavePrivateSafeSettingBean.OFF, ((PrivateSafeSetting) objectRef.element).getUpdateTime()));
        if (!((PrivateSafeSetting) objectRef.element).getSaveToStrava()) {
            str = SavePrivateSafeSettingBean.OFF;
        }
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_STRAVA, str, ((PrivateSafeSetting) objectRef.element).getUpdateTime()));
        SavePrivateSafeSettingBean savePrivateSafeSettingBean = new SavePrivateSafeSettingBean(arrayList);
        savePrivateSafeSettingBean.setTimeStamp(((PrivateSafeSetting) objectRef.element).getUpdateTime());
        List<Task> list = this.mTaskList;
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), GsonUtil.toJson(savePrivateSafeSettingBean));
        Intrinsics.checkExpressionValueIsNotNull(requestBodyCreate, "RequestBody.create(\n    …st)\n                    )");
        list.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/config/save", requestBodyCreate)), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadPrivateSafeSetting.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户开关设置数据上传成功");
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "用户开关设置数据上传成功");
                if (((PrivateSafeSetting) objectRef.element) != null) {
                    try {
                        ((PrivateSafeSetting) objectRef.element).setUploadSuccess(true);
                        ((PrivateSafeSetting) objectRef.element).update();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户开关设置数据上传失败:" + taskInfo);
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "用户开关设置数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    private final void uploadUserHomeCardList() {
        final HomeCard homeCardQueryHomeCardInfo = GreenDaoUtil.queryHomeCardInfo(this.mUserId);
        if (homeCardQueryHomeCardInfo == null || homeCardQueryHomeCardInfo.getUploadSuccess()) {
            printAndSave("没有需要上传的用户首页卡片设置数据");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<Integer> valueList = homeCardQueryHomeCardInfo.getValueList();
        if (!(valueList == null || valueList.isEmpty())) {
            int size = homeCardQueryHomeCardInfo.getValueList().size();
            for (int i = 0; i < size; i++) {
                Integer num = homeCardQueryHomeCardInfo.getValueList().get(i);
                Intrinsics.checkExpressionValueIsNotNull(num, "homeCard.valueList[i]");
                String homeCardKey = getHomeCardKey(num.intValue());
                String str = homeCardKey;
                if (!(str == null || str.length() == 0)) {
                    arrayList.add(homeCardKey);
                }
            }
        }
        List<Integer> hideValueList = homeCardQueryHomeCardInfo.getHideValueList();
        if (!(hideValueList == null || hideValueList.isEmpty())) {
            int size2 = homeCardQueryHomeCardInfo.getHideValueList().size();
            for (int i2 = 0; i2 < size2; i2++) {
                Integer num2 = homeCardQueryHomeCardInfo.getHideValueList().get(i2);
                Intrinsics.checkExpressionValueIsNotNull(num2, "homeCard.hideValueList[i]");
                String homeCardKey2 = getHomeCardKey(num2.intValue());
                String str2 = homeCardKey2;
                if (!(str2 == null || str2.length() == 0)) {
                    arrayList2.add(homeCardKey2);
                }
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("timestamp", String.valueOf(homeCardQueryHomeCardInfo.getUpdateTime()));
        String json = GsonUtil.toJson(arrayList);
        Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtil.toJson(showValueList)");
        linkedHashMap.put("visiableCards", json);
        String json2 = GsonUtil.toJson(arrayList2);
        Intrinsics.checkExpressionValueIsNotNull(json2, "GsonUtil.toJson(hideValueList)");
        linkedHashMap.put("hiddenCards", json2);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/indexCard/v2/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadUserHomeCardList.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户首页卡片设置数据上传成功");
                try {
                    homeCardQueryHomeCardInfo.setUploadSuccess(true);
                    homeCardQueryHomeCardInfo.update();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户首页卡片设置数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.SportCard] */
    private final void uploadSportCardList() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.querySportCard(this.mUserId);
        if (((SportCard) objectRef.element) != null && !((SportCard) objectRef.element).getUploadSuccess()) {
            List<Integer> valueList = ((SportCard) objectRef.element).getValueList();
            boolean z = true;
            if (!(valueList == null || valueList.isEmpty())) {
                ArrayList arrayList = new ArrayList();
                List<Integer> valueList2 = ((SportCard) objectRef.element).getValueList();
                List<Integer> list = valueList2;
                if (list != null && !list.isEmpty()) {
                    z = false;
                }
                if (!z) {
                    int size = valueList2.size();
                    for (int i = 0; i < size; i++) {
                        Integer num = valueList2.get(i);
                        if (num != null && num.intValue() == 48) {
                            arrayList.add(SportCard.OUTDOOR_RUN);
                        } else if (num != null && num.intValue() == 52) {
                            arrayList.add(SportCard.OUTDOOR_WALK);
                        } else if (num != null && num.intValue() == 50) {
                            arrayList.add(SportCard.OUTDOOR_CYCLE);
                        } else if (num != null && num.intValue() == 4) {
                            arrayList.add(SportCard.HIKING);
                        } else if (num != null && num.intValue() == 49) {
                            arrayList.add(SportCard.INDOOR_RUN);
                        } else if (num != null && num.intValue() == 53) {
                            arrayList.add(SportCard.INDOOR_WALK);
                        }
                    }
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("timestamp", String.valueOf(((SportCard) objectRef.element).getUpdateTime()));
                String json = GsonUtil.toJson(arrayList);
                Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtil.toJson(valueList)");
                linkedHashMap.put("value", json);
                this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/sportType/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadSportCardList.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.ido.life.syncdownload.Task.Listenter
                    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                        DataUploadService.this.printAndSave("用户运动类型数据上传成功");
                        if (((SportCard) objectRef.element) != null) {
                            try {
                                ((SportCard) objectRef.element).setUploadSuccess(true);
                                ((SportCard) objectRef.element).update();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        return true;
                    }

                    @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
                    public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                        DataUploadService.this.printAndSave("用户运动类型数据上传失败:" + taskInfo);
                    }
                }, 0, 4, null));
                return;
            }
        }
        printAndSave("没有需要上传的用户运动类型数据");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.UnitSetting] */
    private final void uploadUnitSetting() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryUnitSetting(this.mUserId);
        if (((UnitSetting) objectRef.element) == null || ((UnitSetting) objectRef.element).getHasUpload()) {
            printAndSave("没有需要上传的用户系统单位制设置数据");
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("timestamp", String.valueOf(((UnitSetting) objectRef.element).getUpdateTime()));
        linkedHashMap.put("value", ((UnitSetting) objectRef.element).getUnit() == 1 ? UnitSetting.METRIC_SYSTEM_UNIT : UnitSetting.BRITISH_SYSTEM_UNIT);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/systemUnit/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadUnitSetting.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户系统单位制设置数据上传成功");
                if (((UnitSetting) objectRef.element) != null) {
                    try {
                        ((UnitSetting) objectRef.element).setHasUpload(true);
                        ((UnitSetting) objectRef.element).update();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户系统单位制设置数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.TimeSet] */
    private final void uploadTimeFormatSetting() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryTimeSet(this.mUserId);
        if (((TimeSet) objectRef.element) == null || ((TimeSet) objectRef.element).getHasUpload()) {
            printAndSave("没有需要上传的用户时间制式设置数据");
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("timestamp", String.valueOf(((TimeSet) objectRef.element).getUpdateTime()));
        int timeFormat = ((TimeSet) objectRef.element).getTimeFormat();
        linkedHashMap.put("value", timeFormat != 1 ? timeFormat != 2 ? TimeSet.KEY_FLOW_SYSTEM : TimeSet.KEY_HOUR_12 : TimeSet.KEY_HOUR_24);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/time/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadTimeFormatSetting.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户时间制式设置数据上传成功");
                if (((TimeSet) objectRef.element) != null) {
                    try {
                        ((TimeSet) objectRef.element).setHasUpload(true);
                        ((TimeSet) objectRef.element).update();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户时间制式设置数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.TempUnitSetting] */
    private final void uploadTempSetting() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryTempUnitSetting(this.mUserId);
        if (((TempUnitSetting) objectRef.element) == null || ((TempUnitSetting) objectRef.element).getHasUpload()) {
            printAndSave("没有需要上传的用户温度单位设置数据");
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("timestamp", String.valueOf(((TempUnitSetting) objectRef.element).getUpdateTime()));
        linkedHashMap.put("value", ((TempUnitSetting) objectRef.element).getTemp() != 1 ? TempUnitSetting.KEY_F : TempUnitSetting.KEY_C);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/temperature/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadTempSetting.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户温度单位设置数据上传成功");
                if (((TempUnitSetting) objectRef.element) != null) {
                    try {
                        ((TempUnitSetting) objectRef.element).setHasUpload(true);
                        ((TempUnitSetting) objectRef.element).update();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户温度单位设置数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.WeekStartSetting] */
    private final void uploadWeekStartSetting() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryWeekStart(this.mUserId);
        if (((WeekStartSetting) objectRef.element) == null || ((WeekStartSetting) objectRef.element).getHasUpload()) {
            printAndSave("没有需要上传的用户周首日设置数据");
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("timestamp", String.valueOf(((WeekStartSetting) objectRef.element).getUpdateTime()));
        int weekStart = ((WeekStartSetting) objectRef.element).getWeekStart();
        linkedHashMap.put("value", weekStart != 2 ? weekStart != 7 ? WeekStartSetting.SUNDAY : WeekStartSetting.SATURDAY : WeekStartSetting.MONDAY);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/weekStart/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadWeekStartSetting.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户周首日设置数据上传成功");
                if (((WeekStartSetting) objectRef.element) != null) {
                    try {
                        ((WeekStartSetting) objectRef.element).setHasUpload(true);
                        ((WeekStartSetting) objectRef.element).update();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户周首日设置数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.MapEngine] */
    private final void uploadMapEngineSetting() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryMapEngine(this.mUserId);
        if (((MapEngine) objectRef.element) == null || ((MapEngine) objectRef.element).getHasUpload()) {
            printAndSave("没有需要上传的用户地图引擎设置数据");
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("timestamp", String.valueOf(((MapEngine) objectRef.element).getUpdateTime()));
        linkedHashMap.put("value", ((MapEngine) objectRef.element).getMapEngine() != 1 ? MapEngine.TYPE_AMAP : MapEngine.TYPE_GOOGLE_MAP);
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_USER, "http://cloud.user.veryfitplus.com/userapi/prefs/mapEngine/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadMapEngineSetting.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户地图引擎设置数据上传成功");
                if (((MapEngine) objectRef.element) != null) {
                    try {
                        ((MapEngine) objectRef.element).setHasUpload(true);
                        ((MapEngine) objectRef.element).update();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户地图引擎设置数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.util.List] */
    private final void uploadWeeklyReportStatus() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryReadReportList(this.mUserId);
        List list = (List) objectRef.element;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的周报状态数据");
            return;
        }
        ArrayList arrayList = new ArrayList();
        WeeklyReportStatusUploadEntity weeklyReportStatusUploadEntity = new WeeklyReportStatusUploadEntity(arrayList);
        int size = ((List) objectRef.element).size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        for (int i = 0; i < size; i++) {
            MessageEntity item = (MessageEntity) ((List) objectRef.element).get(i);
            try {
                WeeklyReportStatusEntity weeklyReportStatusEntity = new WeeklyReportStatusEntity();
                weeklyReportStatusEntity.setStatus(1);
                Intrinsics.checkExpressionValueIsNotNull(item, "item");
                weeklyReportStatusEntity.setTimeStamp(item.getUpdateTime());
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(item.getStartDayMonday(), DateUtil.DATE_FORMAT_YMD));
                calendar.setFirstDayOfWeek(2);
                weeklyReportStatusEntity.setYear(calendar.get(1));
                int actualMaximum = calendar.get(3);
                if (actualMaximum == 1) {
                    calendar.add(5, 6);
                    if (calendar.get(1) != weeklyReportStatusEntity.getYear()) {
                        calendar.add(5, -6);
                        actualMaximum = calendar.getActualMaximum(3) + 1;
                    }
                }
                weeklyReportStatusEntity.setWeekNum(actualMaximum);
                arrayList.add(weeklyReportStatusEntity);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/weekly/status/upload", GsonUtil.toJson(weeklyReportStatusUploadEntity))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadWeeklyReportStatus.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户健康周报已读状态上传成功");
                List list2 = (List) objectRef.element;
                if (!(list2 == null || list2.isEmpty())) {
                    int size2 = ((List) objectRef.element).size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        try {
                            MessageEntity item2 = (MessageEntity) ((List) objectRef.element).get(i2);
                            Intrinsics.checkExpressionValueIsNotNull(item2, "item");
                            item2.setHasUpload(true);
                            item2.update();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户健康周报已读状态上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.util.List] */
    private final void uploadUserTarget() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = GreenDaoUtil.queryUnUploadUserTarget(this.mUserId);
        List list = (List) objectRef.element;
        if (list == null || list.isEmpty()) {
            printAndSave("没有需要上传的用户目标数据");
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "没有需要上传的用户目标数据");
            return;
        }
        int size = ((List) objectRef.element).size();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < size; i++) {
            UserTargetNew item = (UserTargetNew) ((List) objectRef.element).get(i);
            Intrinsics.checkExpressionValueIsNotNull(item, "item");
            int iRoundToInt = MathKt.roundToInt(item.getWeight());
            if (item.getWeightUnit() == 2) {
                iRoundToInt = MathKt.roundToInt(UnitUtil.getPound2Kg(iRoundToInt));
            }
            String date = item.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            linkedHashMap.put("date", date);
            linkedHashMap.put("numSteps", String.valueOf(item.getStep()));
            linkedHashMap.put("weight", String.valueOf(iRoundToInt));
            linkedHashMap.put("timestamp", String.valueOf(item.getUpdateTime()));
        }
        this.mTaskList.add(new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/sport/target/save", new JsonRequestBody(linkedHashMap))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.uploadUserTarget.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("用户目标数据上传成功");
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "用户目标数据上传成功");
                List list2 = (List) objectRef.element;
                if (!(list2 == null || list2.isEmpty())) {
                    int size2 = ((List) objectRef.element).size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        UserTargetNew item2 = (UserTargetNew) ((List) objectRef.element).get(i2);
                        try {
                            Intrinsics.checkExpressionValueIsNotNull(item2, "item");
                            item2.setHasUpload(true);
                            item2.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("用户目标数据上传失败:" + taskInfo);
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "用户目标数据上传失败:" + taskInfo);
            }
        }, 0, 4, null));
    }

    private final Task getActiveTimeTask(final List<? extends ActiveTimeDayData> activeTimeList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/exercise/upload", GsonUtil.toJson(new ActiveTimeDayDataUpload(activeTimeList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getActiveTimeTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("活动时长数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "活动时长数据上传成功");
                List list = activeTimeList;
                if (!(list == null || list.isEmpty())) {
                    for (ActiveTimeDayData activeTimeDayData : activeTimeList) {
                        try {
                            activeTimeDayData.setUploaded(true);
                            activeTimeDayData.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("活动时长数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "活动时长数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getWeightUploadTask(final List<? extends WeightItemBean> weightList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/weight/upload", GsonUtil.toJson(new UploadWeightBean(weightList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getWeightUploadTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("体重修改记录上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "体重修改记录上传成功");
                List list = weightList;
                if (!(list == null || list.isEmpty())) {
                    for (WeightItemBean weightItemBean : weightList) {
                        try {
                            weightItemBean.setUploadSuccess(true);
                            weightItemBean.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("体重修改记录上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "体重修改记录上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getStepTask(final List<? extends StepDayData> stepList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/steps/upload", GsonUtil.toJson(new StepDayDataUpload(stepList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getStepTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("步数数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "步数数据上传成功");
                List list = stepList;
                if (!(list == null || list.isEmpty())) {
                    for (StepDayData stepDayData : stepList) {
                        try {
                            stepDayData.setUploaded(true);
                            stepDayData.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("步数数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "步数数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getActiveTask(final List<? extends SportHealth> activeList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/sports/upload", GsonUtil.toJson(new SportHealthUpLoad(activeList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getActiveTask.1
            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("活动数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "活动数据上传失败:" + taskInfo);
            }

            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                JSONArray jSONArray;
                JSONObject jSONObject;
                DataUploadService.this.printAndSave("活动数据上传成功" + GsonUtil.toJson(new SportHealthUpLoad(activeList)));
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "活动数据上传成功");
                String str = response;
                if (!(str == null || str.length() == 0)) {
                    List list = activeList;
                    if (!(list == null || list.isEmpty())) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(response);
                            if (jSONObject2.has("result") && !jSONObject2.isNull("result") && (jSONArray = jSONObject2.getJSONArray("result")) != null && jSONArray.length() > 0) {
                                int length = jSONArray.length();
                                for (SportHealth sportHealth : activeList) {
                                    int i = 0;
                                    while (true) {
                                        if (i < length) {
                                            try {
                                                jSONObject = jSONArray.getJSONObject(i);
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                            if (jSONObject.getLong("timestamp") == sportHealth.getTimestamp()) {
                                                sportHealth.setSid(jSONObject.getString("sid"));
                                                sportHealth.setUploaded(true);
                                                sportHealth.update();
                                                break;
                                            }
                                            i++;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                return true;
            }
        }, 0, 4, null);
    }

    private final Task getDistanceTask(final List<? extends SportDistanceBean> distanceList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/distance/upload", GsonUtil.toJson(new DistenceDetailUploadBean(distanceList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getDistanceTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("距离数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "距离数据上传成功");
                List list = distanceList;
                if (!(list == null || list.isEmpty())) {
                    for (SportDistanceBean sportDistanceBean : distanceList) {
                        try {
                            sportDistanceBean.setUploadSuccess(true);
                            sportDistanceBean.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("距离数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "距离数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getHeartRateTask(final List<? extends ServerHeartRateDayData> heartList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/heartrate/V2/upload", GsonUtil.toJson(new ServerHeartRateDayUploadBean(heartList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getHeartRateTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("心率数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "心率数据上传成功");
                List list = heartList;
                if (!(list == null || list.isEmpty())) {
                    for (ServerHeartRateDayData serverHeartRateDayData : heartList) {
                        try {
                            serverHeartRateDayData.setUploadSuccess(true);
                            serverHeartRateDayData.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("心率数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "心率数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getSleepTask(final List<List<ServerSleepDayData>> sleepList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/sleep/upload", GsonUtil.toJson(convertToServerSleepUploadBean(sleepList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getSleepTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("睡眠数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "睡眠数据上传成功");
                if (!sleepList.isEmpty()) {
                    int size = sleepList.size();
                    for (int i = 0; i < size; i++) {
                        List list = (List) sleepList.get(i);
                        if (!list.isEmpty()) {
                            int size2 = list.size();
                            for (int i2 = 0; i2 < size2; i2++) {
                                ServerSleepDayData serverSleepDayData = (ServerSleepDayData) list.get(i2);
                                serverSleepDayData.setUploaded(true);
                                serverSleepDayData.update();
                            }
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("睡眠数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "睡眠数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final SleepDayUpload convertToServerSleepUploadBean(List<List<ServerSleepDayData>> sleepList) {
        if (sleepList.isEmpty()) {
            return new SleepDayUpload(null, 1, 0 == true ? 1 : 0);
        }
        ArrayList arrayList = new ArrayList();
        SleepDayUpload sleepDayUpload = new SleepDayUpload(arrayList);
        int size = sleepList.size();
        for (int i = 0; i < size; i++) {
            List<ServerSleepDayData> list = sleepList.get(i);
            if (!list.isEmpty()) {
                int size2 = list.size();
                String str = null;
                SleepDayDataUpload sleepDayDataUpload = new SleepDayDataUpload(null, 0, 0, 0, 0, 0, str, str, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, 0L, 0, 2097151, null);
                ArrayList arrayList2 = new ArrayList();
                sleepDayDataUpload.setData(arrayList2);
                sleepDayDataUpload.setDataSize(size2);
                arrayList.add(sleepDayDataUpload);
                for (int i2 = 0; i2 < size2; i2++) {
                    ServerSleepDayData serverSleepDayData = list.get(i2);
                    if (i2 == 0) {
                        sleepDayDataUpload.setDate(serverSleepDayData.getDate());
                        sleepDayDataUpload.setAwakeSeconds(serverSleepDayData.getAwakeSeconds());
                        sleepDayDataUpload.setLightlySeconds(serverSleepDayData.getLightlySeconds());
                        sleepDayDataUpload.setDeeplySeconds(serverSleepDayData.getDeeplySeconds());
                        sleepDayDataUpload.setEyeMovementSeconds(serverSleepDayData.getEyeMovementSeconds());
                        sleepDayDataUpload.setTotalSeconds(serverSleepDayData.getTotalSeconds());
                        sleepDayDataUpload.setStartTime(serverSleepDayData.getStartTime());
                        sleepDayDataUpload.setEndTime(serverSleepDayData.getEndTime());
                        sleepDayDataUpload.setItems(serverSleepDayData.getItems());
                        sleepDayDataUpload.setSourceMac(serverSleepDayData.getSourceMac());
                        sleepDayDataUpload.setDeviceName(serverSleepDayData.getDeviceName());
                        sleepDayDataUpload.setAwakeRatio(serverSleepDayData.getAwakeRatio());
                        sleepDayDataUpload.setLightlyRatio(serverSleepDayData.getLightlyRatio());
                        sleepDayDataUpload.setDeeplyRatio(serverSleepDayData.getDeeplyRatio());
                        sleepDayDataUpload.setEyeMovementRatio(serverSleepDayData.getEyeMovementRatio());
                        sleepDayDataUpload.setScore(serverSleepDayData.getScore());
                        sleepDayDataUpload.setBreathRate(serverSleepDayData.getBreathRate());
                        sleepDayDataUpload.setTimestamp(serverSleepDayData.getTimestamp());
                        sleepDayDataUpload.setType(serverSleepDayData.getType());
                    }
                    SleepDayItemUpload sleepDayItemUpload = new SleepDayItemUpload(0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, 0, 32767, null);
                    sleepDayItemUpload.setAwakeSeconds(serverSleepDayData.getAwakeSeconds());
                    sleepDayItemUpload.setLightlySeconds(serverSleepDayData.getLightlySeconds());
                    sleepDayItemUpload.setDeeplySeconds(serverSleepDayData.getDeeplySeconds());
                    sleepDayItemUpload.setEyeMovementSeconds(serverSleepDayData.getEyeMovementSeconds());
                    sleepDayItemUpload.setTotalSeconds(serverSleepDayData.getTotalSeconds());
                    sleepDayItemUpload.setStartTime(serverSleepDayData.getStartTime());
                    sleepDayItemUpload.setEndTime(serverSleepDayData.getEndTime());
                    sleepDayItemUpload.setItems(serverSleepDayData.getItems());
                    sleepDayItemUpload.setScore(serverSleepDayData.getScore());
                    sleepDayItemUpload.setBreathRate(serverSleepDayData.getBreathRate());
                    sleepDayItemUpload.setAwakeRatio(serverSleepDayData.getAwakeRatio());
                    sleepDayItemUpload.setLightlyRatio(serverSleepDayData.getLightlyRatio());
                    sleepDayItemUpload.setDeeplyRatio(serverSleepDayData.getDeeplyRatio());
                    sleepDayItemUpload.setEyeMovementRatio(serverSleepDayData.getEyeMovementRatio());
                    sleepDayItemUpload.setType(serverSleepDayData.getType());
                    arrayList2.add(sleepDayItemUpload);
                }
            }
        }
        return sleepDayUpload;
    }

    private final Task getCalorieTask(final List<? extends CalorieDayData> calorieList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/calorie/upload", GsonUtil.toJson(new CalorieDayDataUpload(calorieList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getCalorieTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("卡路里数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "卡路里数据上传成功");
                List list = calorieList;
                if (!(list == null || list.isEmpty())) {
                    for (CalorieDayData calorieDayData : calorieList) {
                        try {
                            calorieDayData.setUploaded(true);
                            calorieDayData.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("卡路里数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "卡路里数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getPressureTask(final List<? extends HealthPressure> pressureList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/pressure/upload", GsonUtil.toJson(new UploadPressureBean(pressureList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getPressureTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("压力数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "压力数据上传成功");
                List list = pressureList;
                if (!(list == null || list.isEmpty())) {
                    for (HealthPressure healthPressure : pressureList) {
                        try {
                            healthPressure.setUploadSuccess(true);
                            healthPressure.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("压力数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "压力数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getBloodOxyTask(final List<? extends ServerBloodOxyDayData> bloodOxyList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/bloodoxy/upload", GsonUtil.toJson(new ServerBloodOxyDayDataUpload(bloodOxyList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getBloodOxyTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("血氧数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "血氧数据上传成功");
                List list = bloodOxyList;
                if (!(list == null || list.isEmpty())) {
                    for (ServerBloodOxyDayData serverBloodOxyDayData : bloodOxyList) {
                        try {
                            serverBloodOxyDayData.setUploaded(true);
                            serverBloodOxyDayData.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("血氧数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "血氧数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getAmbientNoiseTask(final List<? extends HealthVolumeData> heartList) {
        new LinkedHashMap().put(BaseUrl.URL_NAME, BaseUrl.URL_NAME_HEALTH);
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/noise/upload", GsonUtil.toJson(new UploadNoiseHealthBean(heartList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getAmbientNoiseTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("环境音量数据上传成功");
                List list = heartList;
                if (!(list == null || list.isEmpty())) {
                    for (HealthVolumeData healthVolumeData : heartList) {
                        try {
                            healthVolumeData.setHasUpdate(true);
                            healthVolumeData.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("环境音量数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Task getWalkTask(final List<? extends WalkDayData> walkList) {
        return new Task(new Task.TaskInfo(0, getNormalUpLoadRequest(BaseUrl.URL_NAME_HEALTH, "http://cloud.user.veryfitplus.com/api/walk/upload", GsonUtil.toJson(new WalkDayDataUpload(walkList)))), new DataUploadListenter(this.mUserId) { // from class: com.ido.life.boatservice.DataUploadService.getWalkTask.1
            @Override // com.ido.life.syncdownload.Task.Listenter
            public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String response) {
                DataUploadService.this.printAndSave("走动数据上传成功");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "走动数据上传成功");
                List list = walkList;
                if (!(list == null || list.isEmpty())) {
                    for (WalkDayData walkDayData : walkList) {
                        try {
                            walkDayData.setUploaded(true);
                            walkDayData.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return true;
            }

            @Override // com.ido.life.boatservice.DataUploadService.DataUploadListenter, com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
            public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
                DataUploadService.this.printAndSave("走动数据上传失败:" + taskInfo);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "走动数据上传失败:" + taskInfo);
            }
        }, 0, 4, null);
    }

    private final Request getNormalUpLoadRequest(String header, String url, String body) {
        CommonLogUtil.d(this.TAG, body);
        String str = body;
        if (str == null || str.length() == 0) {
            RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), "");
            Intrinsics.checkExpressionValueIsNotNull(requestBodyCreate, "RequestBody.create(Media…(\"application/json\"), \"\")");
            return getNormalUpLoadRequest(header, url, requestBodyCreate);
        }
        RequestBody requestBodyCreate2 = RequestBody.create(MediaType.parse("application/json"), body);
        Intrinsics.checkExpressionValueIsNotNull(requestBodyCreate2, "RequestBody.create(Media…application/json\"), body)");
        return getNormalUpLoadRequest(header, url, requestBodyCreate2);
    }

    private final Request getNormalUpLoadRequest(Map<String, String> headerMap, String url, String body) {
        String str = body;
        if (str == null || str.length() == 0) {
            RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), "");
            Intrinsics.checkExpressionValueIsNotNull(requestBodyCreate, "RequestBody.create(Media…(\"application/json\"), \"\")");
            return getNormalUpLoadRequest(headerMap, url, requestBodyCreate);
        }
        RequestBody requestBodyCreate2 = RequestBody.create(MediaType.parse("application/json"), body);
        Intrinsics.checkExpressionValueIsNotNull(requestBodyCreate2, "RequestBody.create(Media…application/json\"), body)");
        return getNormalUpLoadRequest(headerMap, url, requestBodyCreate2);
    }

    private final Request getNormalUpLoadRequest(String header, String url, RequestBody body) {
        Request requestBuild = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).addHeader(BaseUrl.URL_NAME, header).post(body).url(url).build();
        Intrinsics.checkExpressionValueIsNotNull(requestBuild, "Request.Builder()\n      …url)\n            .build()");
        return requestBuild;
    }

    private final Request getNormalUpLoadRequest(Map<String, String> headerMap, String url, RequestBody body) {
        Request.Builder builderUrl = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).post(body).url(url);
        if (!(headerMap == null || headerMap.isEmpty())) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builderUrl.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request requestBuild = builderUrl.build();
        Intrinsics.checkExpressionValueIsNotNull(requestBuild, "bulder.build()");
        return requestBuild;
    }

    public final void printAndSave(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        CommonLogUtil.d(this.TAG, message);
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), message);
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b¦\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$DataUploadListenter;", "Lcom/ido/life/syncdownload/BaseTaskListener;", "userId", "", "(Lcom/ido/life/boatservice/DataUploadService;J)V", "onAllTaskComplete", "", "onSingleTaskFailed", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", "app_release"}, k = 1, mv = {1, 1, 16})
    public abstract class DataUploadListenter extends BaseTaskListener {
        public DataUploadListenter(long j) {
            super(j, 1);
        }

        @Override // com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
        public void onAllTaskComplete() {
            int iDecrementAndGet = DataUploadService.this.mAtomicCount.decrementAndGet();
            DataUploadService.this.printAndSave("数据上传成功 count=" + iDecrementAndGet);
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "数据上传成功 count=" + iDecrementAndGet);
            if (iDecrementAndGet <= 0) {
                Object obj = SPUtils.get(Constants.APP_LOGIN_OUT, false);
                if (obj != null) {
                    boolean zBooleanValue = ((Boolean) obj).booleanValue();
                    if (!DataUploadService.this.mNeedMessage && !zBooleanValue) {
                        if (DataUploadService.this.mMaualUpload) {
                            String time = TimeUtil.formatTimeNoS(new Date(System.currentTimeMillis()));
                            Intrinsics.checkExpressionValueIsNotNull(time, "time");
                            String strReplace$default = StringsKt.replace$default(time, "-", "/", false, 4, (Object) null);
                            SPUtils.put(Constants.SYNC_DATA_TIME, strReplace$default);
                            EventBusHelper.post(Constants.EventConstants.EVENT_TYPE_MANUAL_SYNC_DATA_SUCCESS);
                            DataUploadService.this.printAndSave("手动同步数据上传完成,发送通知: " + strReplace$default);
                            LogPath logPathImpl2 = LogPathImpl.getInstance();
                            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                            CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "手动同步数据上传完成,发送通知: " + strReplace$default);
                            return;
                        }
                        DataUploadService dataUploadService = DataUploadService.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append("数据上传");
                        sb.append(DataUploadService.this.mUploadSuccess ? d.P : d.Q);
                        sb.append(",不需要发送通知");
                        dataUploadService.printAndSave(sb.toString());
                        LogPath logPathImpl3 = LogPathImpl.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
                        String loginRegisterLogPath = logPathImpl3.getLoginRegisterLogPath();
                        String tag = getTAG();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("数据上传");
                        sb2.append(DataUploadService.this.mUploadSuccess ? d.P : d.Q);
                        sb2.append(",不需要发送通知");
                        CommonLogUtil.printAndSave(loginRegisterLogPath, tag, sb2.toString());
                    } else {
                        SPUtils.put(Constants.APP_LOGIN_OUT, false);
                        synchronized (DataUploadService.this.mAtomicCount) {
                            DataUploadService.this.mNeedMessage = false;
                            Unit unit = Unit.INSTANCE;
                        }
                        DataUploadService dataUploadService2 = DataUploadService.this;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("数据上传");
                        sb3.append(DataUploadService.this.mUploadSuccess ? d.P : d.Q);
                        sb3.append(",并且发送通知");
                        dataUploadService2.printAndSave(sb3.toString());
                        LogPath logPathImpl4 = LogPathImpl.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(logPathImpl4, "LogPathImpl.getInstance()");
                        String loginRegisterLogPath2 = logPathImpl4.getLoginRegisterLogPath();
                        String tag2 = getTAG();
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("数据上传");
                        sb4.append(DataUploadService.this.mUploadSuccess ? d.P : d.Q);
                        sb4.append(",并且发送通知");
                        CommonLogUtil.printAndSave(loginRegisterLogPath2, tag2, sb4.toString());
                        BaseMessage baseMessage = new BaseMessage(Constants.EventConstants.EVENT_QUIT_USER_DATA_UPLOAD_SUCCESS);
                        baseMessage.setData(Boolean.valueOf(DataUploadService.this.mUploadSuccess));
                        EventBusHelper.post(baseMessage);
                    }
                    DataUploadService.this.stopSelf();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
            }
        }

        @Override // com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
        public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
            DataUploadService.this.mUploadSuccess = false;
            int iDecrementAndGet = DataUploadService.this.mAtomicCount.decrementAndGet();
            DataUploadService.this.printAndSave("onSingleTaskFailed 数据上传失败 count=" + iDecrementAndGet);
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), getTAG(), "onSingleTaskFailed 数据上传失败 count=" + iDecrementAndGet);
            if (iDecrementAndGet <= 0 && DataUploadService.this.mMaualUpload) {
                EventBusHelper.post(Constants.EventConstants.EVENT_TYPE_MANUAL_SYNC_DATA_FAIL);
                DataUploadService.this.printAndSave("手动同步数据上传失败,发送通知");
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), getTAG(), "手动同步数据上传失败,发送通知");
            }
            Object obj = SPUtils.get(Constants.APP_LOGIN_OUT, false);
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
            }
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            if (iDecrementAndGet <= 0) {
                if (DataUploadService.this.mNeedMessage || zBooleanValue) {
                    SPUtils.put(Constants.APP_LOGIN_OUT, false);
                    LogPath logPathImpl3 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
                    CommonLogUtil.printAndSave(logPathImpl3.getLoginRegisterLogPath(), getTAG(), "没有需要上传的数据,发送通知");
                    EventBusHelper.post(Constants.EventConstants.EVENT_QUIT_USER_DATA_UPLOAD_SUCCESS);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$ActiveTimeDayDataUpload;", "", "datas", "", "Lcom/ido/life/database/model/ActiveTimeDayData;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "setDatas", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class ActiveTimeDayDataUpload {
        private List<? extends ActiveTimeDayData> datas;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ActiveTimeDayDataUpload copy$default(ActiveTimeDayDataUpload activeTimeDayDataUpload, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = activeTimeDayDataUpload.datas;
            }
            return activeTimeDayDataUpload.copy(list);
        }

        public final List<ActiveTimeDayData> component1() {
            return this.datas;
        }

        public final ActiveTimeDayDataUpload copy(List<? extends ActiveTimeDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            return new ActiveTimeDayDataUpload(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof ActiveTimeDayDataUpload) && Intrinsics.areEqual(this.datas, ((ActiveTimeDayDataUpload) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<? extends ActiveTimeDayData> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "ActiveTimeDayDataUpload(datas=" + this.datas + ")";
        }

        public ActiveTimeDayDataUpload(List<? extends ActiveTimeDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            this.datas = datas;
        }

        public final List<ActiveTimeDayData> getDatas() {
            return this.datas;
        }

        public final void setDatas(List<? extends ActiveTimeDayData> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.datas = list;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$ServerMenstrualUpload;", "", "datas", "", "Lcom/ido/life/database/model/LifeCycleItemBean;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "setDatas", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class ServerMenstrualUpload {
        private List<LifeCycleItemBean> datas;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ServerMenstrualUpload copy$default(ServerMenstrualUpload serverMenstrualUpload, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = serverMenstrualUpload.datas;
            }
            return serverMenstrualUpload.copy(list);
        }

        public final List<LifeCycleItemBean> component1() {
            return this.datas;
        }

        public final ServerMenstrualUpload copy(List<LifeCycleItemBean> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            return new ServerMenstrualUpload(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof ServerMenstrualUpload) && Intrinsics.areEqual(this.datas, ((ServerMenstrualUpload) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<LifeCycleItemBean> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "ServerMenstrualUpload(datas=" + this.datas + ")";
        }

        public ServerMenstrualUpload(List<LifeCycleItemBean> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            this.datas = datas;
        }

        public final List<LifeCycleItemBean> getDatas() {
            return this.datas;
        }

        public final void setDatas(List<LifeCycleItemBean> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.datas = list;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$StepDayDataUpload;", "", "datas", "", "Lcom/ido/life/database/model/StepDayData;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "setDatas", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class StepDayDataUpload {
        private List<? extends StepDayData> datas;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StepDayDataUpload copy$default(StepDayDataUpload stepDayDataUpload, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = stepDayDataUpload.datas;
            }
            return stepDayDataUpload.copy(list);
        }

        public final List<StepDayData> component1() {
            return this.datas;
        }

        public final StepDayDataUpload copy(List<? extends StepDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            return new StepDayDataUpload(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof StepDayDataUpload) && Intrinsics.areEqual(this.datas, ((StepDayDataUpload) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<? extends StepDayData> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "StepDayDataUpload(datas=" + this.datas + ")";
        }

        public StepDayDataUpload(List<? extends StepDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            this.datas = datas;
        }

        public final List<StepDayData> getDatas() {
            return this.datas;
        }

        public final void setDatas(List<? extends StepDayData> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.datas = list;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$CalorieDayDataUpload;", "", "datas", "", "Lcom/ido/life/database/model/CalorieDayData;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "setDatas", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class CalorieDayDataUpload {
        private List<? extends CalorieDayData> datas;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CalorieDayDataUpload copy$default(CalorieDayDataUpload calorieDayDataUpload, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = calorieDayDataUpload.datas;
            }
            return calorieDayDataUpload.copy(list);
        }

        public final List<CalorieDayData> component1() {
            return this.datas;
        }

        public final CalorieDayDataUpload copy(List<? extends CalorieDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            return new CalorieDayDataUpload(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof CalorieDayDataUpload) && Intrinsics.areEqual(this.datas, ((CalorieDayDataUpload) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<? extends CalorieDayData> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "CalorieDayDataUpload(datas=" + this.datas + ")";
        }

        public CalorieDayDataUpload(List<? extends CalorieDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            this.datas = datas;
        }

        public final List<CalorieDayData> getDatas() {
            return this.datas;
        }

        public final void setDatas(List<? extends CalorieDayData> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.datas = list;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$ServerBloodOxyDayDataUpload;", "", "datas", "", "Lcom/ido/life/database/model/ServerBloodOxyDayData;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "setDatas", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class ServerBloodOxyDayDataUpload {
        private List<? extends ServerBloodOxyDayData> datas;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ServerBloodOxyDayDataUpload copy$default(ServerBloodOxyDayDataUpload serverBloodOxyDayDataUpload, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = serverBloodOxyDayDataUpload.datas;
            }
            return serverBloodOxyDayDataUpload.copy(list);
        }

        public final List<ServerBloodOxyDayData> component1() {
            return this.datas;
        }

        public final ServerBloodOxyDayDataUpload copy(List<? extends ServerBloodOxyDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            return new ServerBloodOxyDayDataUpload(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof ServerBloodOxyDayDataUpload) && Intrinsics.areEqual(this.datas, ((ServerBloodOxyDayDataUpload) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<? extends ServerBloodOxyDayData> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "ServerBloodOxyDayDataUpload(datas=" + this.datas + ")";
        }

        public ServerBloodOxyDayDataUpload(List<? extends ServerBloodOxyDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            this.datas = datas;
        }

        public final List<ServerBloodOxyDayData> getDatas() {
            return this.datas;
        }

        public final void setDatas(List<? extends ServerBloodOxyDayData> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.datas = list;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$WalkDayDataUpload;", "", "datas", "", "Lcom/ido/life/database/model/WalkDayData;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "setDatas", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class WalkDayDataUpload {
        private List<? extends WalkDayData> datas;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ WalkDayDataUpload copy$default(WalkDayDataUpload walkDayDataUpload, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = walkDayDataUpload.datas;
            }
            return walkDayDataUpload.copy(list);
        }

        public final List<WalkDayData> component1() {
            return this.datas;
        }

        public final WalkDayDataUpload copy(List<? extends WalkDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            return new WalkDayDataUpload(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof WalkDayDataUpload) && Intrinsics.areEqual(this.datas, ((WalkDayDataUpload) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<? extends WalkDayData> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "WalkDayDataUpload(datas=" + this.datas + ")";
        }

        public WalkDayDataUpload(List<? extends WalkDayData> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            this.datas = datas;
        }

        public final List<WalkDayData> getDatas() {
            return this.datas;
        }

        public final void setDatas(List<? extends WalkDayData> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.datas = list;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$MensConfigUploadBean;", "", "mensLength", "", "mensCycle", "(II)V", "getMensCycle", "()I", "getMensLength", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class MensConfigUploadBean {
        private final int mensCycle;
        private final int mensLength;

        public static /* synthetic */ MensConfigUploadBean copy$default(MensConfigUploadBean mensConfigUploadBean, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = mensConfigUploadBean.mensLength;
            }
            if ((i3 & 2) != 0) {
                i2 = mensConfigUploadBean.mensCycle;
            }
            return mensConfigUploadBean.copy(i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMensLength() {
            return this.mensLength;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMensCycle() {
            return this.mensCycle;
        }

        public final MensConfigUploadBean copy(int mensLength, int mensCycle) {
            return new MensConfigUploadBean(mensLength, mensCycle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MensConfigUploadBean)) {
                return false;
            }
            MensConfigUploadBean mensConfigUploadBean = (MensConfigUploadBean) other;
            return this.mensLength == mensConfigUploadBean.mensLength && this.mensCycle == mensConfigUploadBean.mensCycle;
        }

        public int hashCode() {
            return (Integer.valueOf(this.mensLength).hashCode() * 31) + Integer.valueOf(this.mensCycle).hashCode();
        }

        public String toString() {
            return "MensConfigUploadBean(mensLength=" + this.mensLength + ", mensCycle=" + this.mensCycle + ")";
        }

        public MensConfigUploadBean(int i, int i2) {
            this.mensLength = i;
            this.mensCycle = i2;
        }

        public final int getMensCycle() {
            return this.mensCycle;
        }

        public final int getMensLength() {
            return this.mensLength;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$SleepDayUpload;", "", "datas", "", "Lcom/ido/life/boatservice/DataUploadService$SleepDayDataUpload;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "setDatas", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class SleepDayUpload {
        private List<SleepDayDataUpload> datas;

        public SleepDayUpload() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SleepDayUpload copy$default(SleepDayUpload sleepDayUpload, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = sleepDayUpload.datas;
            }
            return sleepDayUpload.copy(list);
        }

        public final List<SleepDayDataUpload> component1() {
            return this.datas;
        }

        public final SleepDayUpload copy(List<SleepDayDataUpload> datas) {
            return new SleepDayUpload(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof SleepDayUpload) && Intrinsics.areEqual(this.datas, ((SleepDayUpload) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<SleepDayDataUpload> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "SleepDayUpload(datas=" + this.datas + ")";
        }

        public SleepDayUpload(List<SleepDayDataUpload> list) {
            this.datas = list;
        }

        public /* synthetic */ SleepDayUpload(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? (List) null : list);
        }

        public final List<SleepDayDataUpload> getDatas() {
            return this.datas;
        }

        public final void setDatas(List<SleepDayDataUpload> list) {
            this.datas = list;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\bK\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001Bë\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0005¢\u0006\u0002\u0010\u001cJ\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010P\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0003J\t\u0010Q\u001a\u00020\u0005HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010T\u001a\u00020\u0005HÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\t\u0010X\u001a\u00020\u0005HÆ\u0003J\t\u0010Y\u001a\u00020\u0005HÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\u001aHÆ\u0003J\t\u0010\\\u001a\u00020\u0005HÆ\u0003J\t\u0010]\u001a\u00020\u0005HÆ\u0003J\t\u0010^\u001a\u00020\u0005HÆ\u0003J\t\u0010_\u001a\u00020\u0005HÆ\u0003J\t\u0010`\u001a\u00020\u0005HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jï\u0001\u0010d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010e\u001a\u00020f2\b\u0010g\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010h\u001a\u00020\u0005HÖ\u0001J\t\u0010i\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0013\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u001a\u0010\u0018\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010 R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010\u0015\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001e\"\u0004\b0\u0010 R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001e\"\u0004\b2\u0010 R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010,\"\u0004\b4\u0010.R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010,\"\u0004\b6\u0010.R\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001e\"\u0004\b8\u0010 R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001e\"\u0004\b:\u0010 R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010,\"\u0004\b<\u0010.R\u001a\u0010\u0014\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001e\"\u0004\b>\u0010 R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u001e\"\u0004\b@\u0010 R\u001a\u0010\u0017\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001e\"\u0004\bB\u0010 R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010,\"\u0004\bD\u0010.R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010,\"\u0004\bF\u0010.R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\u001e\"\u0004\bL\u0010 R\u001a\u0010\u001b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u001e\"\u0004\bN\u0010 ¨\u0006j"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$SleepDayDataUpload;", "", "date", "", "awakeSeconds", "", "lightlySeconds", "deeplySeconds", "eyeMovementSeconds", "totalSeconds", "startTime", "endTime", "items", AeUtil.ROOT_DATA_PATH_OLD_NAME, "", "Lcom/ido/life/boatservice/DataUploadService$SleepDayItemUpload;", "dataSize", "sourceMac", "deviceName", "awakeRatio", "lightlyRatio", "deeplyRatio", "eyeMovementRatio", "score", "breathRate", "timestamp", "", "type", "(Ljava/lang/String;IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;IIIIIIJI)V", "getAwakeRatio", "()I", "setAwakeRatio", "(I)V", "getAwakeSeconds", "setAwakeSeconds", "getBreathRate", "setBreathRate", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "getDataSize", "setDataSize", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "getDeeplyRatio", "setDeeplyRatio", "getDeeplySeconds", "setDeeplySeconds", "getDeviceName", "setDeviceName", "getEndTime", "setEndTime", "getEyeMovementRatio", "setEyeMovementRatio", "getEyeMovementSeconds", "setEyeMovementSeconds", "getItems", "setItems", "getLightlyRatio", "setLightlyRatio", "getLightlySeconds", "setLightlySeconds", "getScore", "setScore", "getSourceMac", "setSourceMac", "getStartTime", "setStartTime", "getTimestamp", "()J", "setTimestamp", "(J)V", "getTotalSeconds", "setTotalSeconds", "getType", "setType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class SleepDayDataUpload {
        private int awakeRatio;
        private int awakeSeconds;
        private int breathRate;
        private List<SleepDayItemUpload> data;
        private int dataSize;
        private String date;
        private int deeplyRatio;
        private int deeplySeconds;
        private String deviceName;
        private String endTime;
        private int eyeMovementRatio;
        private int eyeMovementSeconds;
        private String items;
        private int lightlyRatio;
        private int lightlySeconds;
        private int score;
        private String sourceMac;
        private String startTime;
        private long timestamp;
        private int totalSeconds;
        private int type;

        public SleepDayDataUpload() {
            this(null, 0, 0, 0, 0, 0, null, null, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, 0L, 0, 2097151, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDate() {
            return this.date;
        }

        public final List<SleepDayItemUpload> component10() {
            return this.data;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final int getDataSize() {
            return this.dataSize;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getSourceMac() {
            return this.sourceMac;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getDeviceName() {
            return this.deviceName;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final int getAwakeRatio() {
            return this.awakeRatio;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final int getLightlyRatio() {
            return this.lightlyRatio;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final int getDeeplyRatio() {
            return this.deeplyRatio;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final int getEyeMovementRatio() {
            return this.eyeMovementRatio;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final int getBreathRate() {
            return this.breathRate;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getAwakeSeconds() {
            return this.awakeSeconds;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getLightlySeconds() {
            return this.lightlySeconds;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getDeeplySeconds() {
            return this.deeplySeconds;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getEyeMovementSeconds() {
            return this.eyeMovementSeconds;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getTotalSeconds() {
            return this.totalSeconds;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getStartTime() {
            return this.startTime;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getEndTime() {
            return this.endTime;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getItems() {
            return this.items;
        }

        public final SleepDayDataUpload copy(String date, int awakeSeconds, int lightlySeconds, int deeplySeconds, int eyeMovementSeconds, int totalSeconds, String startTime, String endTime, String items, List<SleepDayItemUpload> data, int dataSize, String sourceMac, String deviceName, int awakeRatio, int lightlyRatio, int deeplyRatio, int eyeMovementRatio, int score, int breathRate, long timestamp, int type) {
            return new SleepDayDataUpload(date, awakeSeconds, lightlySeconds, deeplySeconds, eyeMovementSeconds, totalSeconds, startTime, endTime, items, data, dataSize, sourceMac, deviceName, awakeRatio, lightlyRatio, deeplyRatio, eyeMovementRatio, score, breathRate, timestamp, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SleepDayDataUpload)) {
                return false;
            }
            SleepDayDataUpload sleepDayDataUpload = (SleepDayDataUpload) other;
            return Intrinsics.areEqual(this.date, sleepDayDataUpload.date) && this.awakeSeconds == sleepDayDataUpload.awakeSeconds && this.lightlySeconds == sleepDayDataUpload.lightlySeconds && this.deeplySeconds == sleepDayDataUpload.deeplySeconds && this.eyeMovementSeconds == sleepDayDataUpload.eyeMovementSeconds && this.totalSeconds == sleepDayDataUpload.totalSeconds && Intrinsics.areEqual(this.startTime, sleepDayDataUpload.startTime) && Intrinsics.areEqual(this.endTime, sleepDayDataUpload.endTime) && Intrinsics.areEqual(this.items, sleepDayDataUpload.items) && Intrinsics.areEqual(this.data, sleepDayDataUpload.data) && this.dataSize == sleepDayDataUpload.dataSize && Intrinsics.areEqual(this.sourceMac, sleepDayDataUpload.sourceMac) && Intrinsics.areEqual(this.deviceName, sleepDayDataUpload.deviceName) && this.awakeRatio == sleepDayDataUpload.awakeRatio && this.lightlyRatio == sleepDayDataUpload.lightlyRatio && this.deeplyRatio == sleepDayDataUpload.deeplyRatio && this.eyeMovementRatio == sleepDayDataUpload.eyeMovementRatio && this.score == sleepDayDataUpload.score && this.breathRate == sleepDayDataUpload.breathRate && this.timestamp == sleepDayDataUpload.timestamp && this.type == sleepDayDataUpload.type;
        }

        public int hashCode() {
            String str = this.date;
            int iHashCode = (((((((((((str != null ? str.hashCode() : 0) * 31) + Integer.valueOf(this.awakeSeconds).hashCode()) * 31) + Integer.valueOf(this.lightlySeconds).hashCode()) * 31) + Integer.valueOf(this.deeplySeconds).hashCode()) * 31) + Integer.valueOf(this.eyeMovementSeconds).hashCode()) * 31) + Integer.valueOf(this.totalSeconds).hashCode()) * 31;
            String str2 = this.startTime;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.endTime;
            int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.items;
            int iHashCode4 = (iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            List<SleepDayItemUpload> list = this.data;
            int iHashCode5 = (((iHashCode4 + (list != null ? list.hashCode() : 0)) * 31) + Integer.valueOf(this.dataSize).hashCode()) * 31;
            String str5 = this.sourceMac;
            int iHashCode6 = (iHashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.deviceName;
            return ((((((((((((((((iHashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31) + Integer.valueOf(this.awakeRatio).hashCode()) * 31) + Integer.valueOf(this.lightlyRatio).hashCode()) * 31) + Integer.valueOf(this.deeplyRatio).hashCode()) * 31) + Integer.valueOf(this.eyeMovementRatio).hashCode()) * 31) + Integer.valueOf(this.score).hashCode()) * 31) + Integer.valueOf(this.breathRate).hashCode()) * 31) + Long.valueOf(this.timestamp).hashCode()) * 31) + Integer.valueOf(this.type).hashCode();
        }

        public String toString() {
            return "SleepDayDataUpload(date=" + this.date + ", awakeSeconds=" + this.awakeSeconds + ", lightlySeconds=" + this.lightlySeconds + ", deeplySeconds=" + this.deeplySeconds + ", eyeMovementSeconds=" + this.eyeMovementSeconds + ", totalSeconds=" + this.totalSeconds + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", items=" + this.items + ", data=" + this.data + ", dataSize=" + this.dataSize + ", sourceMac=" + this.sourceMac + ", deviceName=" + this.deviceName + ", awakeRatio=" + this.awakeRatio + ", lightlyRatio=" + this.lightlyRatio + ", deeplyRatio=" + this.deeplyRatio + ", eyeMovementRatio=" + this.eyeMovementRatio + ", score=" + this.score + ", breathRate=" + this.breathRate + ", timestamp=" + this.timestamp + ", type=" + this.type + ")";
        }

        public SleepDayDataUpload(String str, int i, int i2, int i3, int i4, int i5, String str2, String str3, String str4, List<SleepDayItemUpload> list, int i6, String str5, String str6, int i7, int i8, int i9, int i10, int i11, int i12, long j, int i13) {
            this.date = str;
            this.awakeSeconds = i;
            this.lightlySeconds = i2;
            this.deeplySeconds = i3;
            this.eyeMovementSeconds = i4;
            this.totalSeconds = i5;
            this.startTime = str2;
            this.endTime = str3;
            this.items = str4;
            this.data = list;
            this.dataSize = i6;
            this.sourceMac = str5;
            this.deviceName = str6;
            this.awakeRatio = i7;
            this.lightlyRatio = i8;
            this.deeplyRatio = i9;
            this.eyeMovementRatio = i10;
            this.score = i11;
            this.breathRate = i12;
            this.timestamp = j;
            this.type = i13;
        }

        public /* synthetic */ SleepDayDataUpload(String str, int i, int i2, int i3, int i4, int i5, String str2, String str3, String str4, List list, int i6, String str5, String str6, int i7, int i8, int i9, int i10, int i11, int i12, long j, int i13, int i14, DefaultConstructorMarker defaultConstructorMarker) {
            this((i14 & 1) != 0 ? (String) null : str, (i14 & 2) != 0 ? 0 : i, (i14 & 4) != 0 ? 0 : i2, (i14 & 8) != 0 ? 0 : i3, (i14 & 16) != 0 ? 0 : i4, (i14 & 32) != 0 ? 0 : i5, (i14 & 64) != 0 ? (String) null : str2, (i14 & 128) != 0 ? (String) null : str3, (i14 & 256) != 0 ? (String) null : str4, (i14 & 512) != 0 ? (List) null : list, (i14 & 1024) != 0 ? 0 : i6, (i14 & 2048) != 0 ? (String) null : str5, (i14 & 4096) != 0 ? (String) null : str6, (i14 & 8192) != 0 ? 0 : i7, (i14 & 16384) != 0 ? 0 : i8, (i14 & 32768) != 0 ? 0 : i9, (i14 & 65536) != 0 ? 0 : i10, (i14 & 131072) != 0 ? 0 : i11, (i14 & 262144) != 0 ? 0 : i12, (i14 & 524288) != 0 ? 0L : j, (i14 & 1048576) != 0 ? 0 : i13);
        }

        public final String getDate() {
            return this.date;
        }

        public final void setDate(String str) {
            this.date = str;
        }

        public final int getAwakeSeconds() {
            return this.awakeSeconds;
        }

        public final void setAwakeSeconds(int i) {
            this.awakeSeconds = i;
        }

        public final int getLightlySeconds() {
            return this.lightlySeconds;
        }

        public final void setLightlySeconds(int i) {
            this.lightlySeconds = i;
        }

        public final int getDeeplySeconds() {
            return this.deeplySeconds;
        }

        public final void setDeeplySeconds(int i) {
            this.deeplySeconds = i;
        }

        public final int getEyeMovementSeconds() {
            return this.eyeMovementSeconds;
        }

        public final void setEyeMovementSeconds(int i) {
            this.eyeMovementSeconds = i;
        }

        public final int getTotalSeconds() {
            return this.totalSeconds;
        }

        public final void setTotalSeconds(int i) {
            this.totalSeconds = i;
        }

        public final String getStartTime() {
            return this.startTime;
        }

        public final void setStartTime(String str) {
            this.startTime = str;
        }

        public final String getEndTime() {
            return this.endTime;
        }

        public final void setEndTime(String str) {
            this.endTime = str;
        }

        public final String getItems() {
            return this.items;
        }

        public final void setItems(String str) {
            this.items = str;
        }

        public final List<SleepDayItemUpload> getData() {
            return this.data;
        }

        public final void setData(List<SleepDayItemUpload> list) {
            this.data = list;
        }

        public final int getDataSize() {
            return this.dataSize;
        }

        public final void setDataSize(int i) {
            this.dataSize = i;
        }

        public final String getSourceMac() {
            return this.sourceMac;
        }

        public final void setSourceMac(String str) {
            this.sourceMac = str;
        }

        public final String getDeviceName() {
            return this.deviceName;
        }

        public final void setDeviceName(String str) {
            this.deviceName = str;
        }

        public final int getAwakeRatio() {
            return this.awakeRatio;
        }

        public final void setAwakeRatio(int i) {
            this.awakeRatio = i;
        }

        public final int getLightlyRatio() {
            return this.lightlyRatio;
        }

        public final void setLightlyRatio(int i) {
            this.lightlyRatio = i;
        }

        public final int getDeeplyRatio() {
            return this.deeplyRatio;
        }

        public final void setDeeplyRatio(int i) {
            this.deeplyRatio = i;
        }

        public final int getEyeMovementRatio() {
            return this.eyeMovementRatio;
        }

        public final void setEyeMovementRatio(int i) {
            this.eyeMovementRatio = i;
        }

        public final int getScore() {
            return this.score;
        }

        public final void setScore(int i) {
            this.score = i;
        }

        public final int getBreathRate() {
            return this.breathRate;
        }

        public final void setBreathRate(int i) {
            this.breathRate = i;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public final void setTimestamp(long j) {
            this.timestamp = j;
        }

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }
    }

    /* JADX INFO: compiled from: DataUploadService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b=\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B¡\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003¢\u0006\u0002\u0010\u0013J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J¥\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003HÆ\u0001J\u0013\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010I\u001a\u00020\u0003HÖ\u0001J\t\u0010J\u001a\u00020\tHÖ\u0001R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R\u001c\u0010\n\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u0011\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0015\"\u0004\b%\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0015\"\u0004\b'\u0010\u0017R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0015\"\u0004\b+\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0015\"\u0004\b-\u0010\u0017R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0015\"\u0004\b/\u0010\u0017R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010!\"\u0004\b1\u0010#R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0015\"\u0004\b3\u0010\u0017R\u001a\u0010\u0012\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0015\"\u0004\b5\u0010\u0017¨\u0006K"}, d2 = {"Lcom/ido/life/boatservice/DataUploadService$SleepDayItemUpload;", "", "awakeSeconds", "", "lightlySeconds", "deeplySeconds", "eyeMovementSeconds", "totalSeconds", "startTime", "", "endTime", "items", "score", "breathRate", "awakeRatio", "lightlyRatio", "deeplyRatio", "eyeMovementRatio", "type", "(IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIIIII)V", "getAwakeRatio", "()I", "setAwakeRatio", "(I)V", "getAwakeSeconds", "setAwakeSeconds", "getBreathRate", "setBreathRate", "getDeeplyRatio", "setDeeplyRatio", "getDeeplySeconds", "setDeeplySeconds", "getEndTime", "()Ljava/lang/String;", "setEndTime", "(Ljava/lang/String;)V", "getEyeMovementRatio", "setEyeMovementRatio", "getEyeMovementSeconds", "setEyeMovementSeconds", "getItems", "setItems", "getLightlyRatio", "setLightlyRatio", "getLightlySeconds", "setLightlySeconds", "getScore", "setScore", "getStartTime", "setStartTime", "getTotalSeconds", "setTotalSeconds", "getType", "setType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class SleepDayItemUpload {
        private int awakeRatio;
        private int awakeSeconds;
        private int breathRate;
        private int deeplyRatio;
        private int deeplySeconds;
        private String endTime;
        private int eyeMovementRatio;
        private int eyeMovementSeconds;
        private String items;
        private int lightlyRatio;
        private int lightlySeconds;
        private int score;
        private String startTime;
        private int totalSeconds;
        private int type;

        public SleepDayItemUpload() {
            this(0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, 0, 32767, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getAwakeSeconds() {
            return this.awakeSeconds;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getBreathRate() {
            return this.breathRate;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final int getAwakeRatio() {
            return this.awakeRatio;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final int getLightlyRatio() {
            return this.lightlyRatio;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getDeeplyRatio() {
            return this.deeplyRatio;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final int getEyeMovementRatio() {
            return this.eyeMovementRatio;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getLightlySeconds() {
            return this.lightlySeconds;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getDeeplySeconds() {
            return this.deeplySeconds;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getEyeMovementSeconds() {
            return this.eyeMovementSeconds;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getTotalSeconds() {
            return this.totalSeconds;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getStartTime() {
            return this.startTime;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getEndTime() {
            return this.endTime;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getItems() {
            return this.items;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        public final SleepDayItemUpload copy(int awakeSeconds, int lightlySeconds, int deeplySeconds, int eyeMovementSeconds, int totalSeconds, String startTime, String endTime, String items, int score, int breathRate, int awakeRatio, int lightlyRatio, int deeplyRatio, int eyeMovementRatio, int type) {
            return new SleepDayItemUpload(awakeSeconds, lightlySeconds, deeplySeconds, eyeMovementSeconds, totalSeconds, startTime, endTime, items, score, breathRate, awakeRatio, lightlyRatio, deeplyRatio, eyeMovementRatio, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SleepDayItemUpload)) {
                return false;
            }
            SleepDayItemUpload sleepDayItemUpload = (SleepDayItemUpload) other;
            return this.awakeSeconds == sleepDayItemUpload.awakeSeconds && this.lightlySeconds == sleepDayItemUpload.lightlySeconds && this.deeplySeconds == sleepDayItemUpload.deeplySeconds && this.eyeMovementSeconds == sleepDayItemUpload.eyeMovementSeconds && this.totalSeconds == sleepDayItemUpload.totalSeconds && Intrinsics.areEqual(this.startTime, sleepDayItemUpload.startTime) && Intrinsics.areEqual(this.endTime, sleepDayItemUpload.endTime) && Intrinsics.areEqual(this.items, sleepDayItemUpload.items) && this.score == sleepDayItemUpload.score && this.breathRate == sleepDayItemUpload.breathRate && this.awakeRatio == sleepDayItemUpload.awakeRatio && this.lightlyRatio == sleepDayItemUpload.lightlyRatio && this.deeplyRatio == sleepDayItemUpload.deeplyRatio && this.eyeMovementRatio == sleepDayItemUpload.eyeMovementRatio && this.type == sleepDayItemUpload.type;
        }

        public int hashCode() {
            int iHashCode = ((((((((Integer.valueOf(this.awakeSeconds).hashCode() * 31) + Integer.valueOf(this.lightlySeconds).hashCode()) * 31) + Integer.valueOf(this.deeplySeconds).hashCode()) * 31) + Integer.valueOf(this.eyeMovementSeconds).hashCode()) * 31) + Integer.valueOf(this.totalSeconds).hashCode()) * 31;
            String str = this.startTime;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.endTime;
            int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.items;
            return ((((((((((((((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.valueOf(this.score).hashCode()) * 31) + Integer.valueOf(this.breathRate).hashCode()) * 31) + Integer.valueOf(this.awakeRatio).hashCode()) * 31) + Integer.valueOf(this.lightlyRatio).hashCode()) * 31) + Integer.valueOf(this.deeplyRatio).hashCode()) * 31) + Integer.valueOf(this.eyeMovementRatio).hashCode()) * 31) + Integer.valueOf(this.type).hashCode();
        }

        public String toString() {
            return "SleepDayItemUpload(awakeSeconds=" + this.awakeSeconds + ", lightlySeconds=" + this.lightlySeconds + ", deeplySeconds=" + this.deeplySeconds + ", eyeMovementSeconds=" + this.eyeMovementSeconds + ", totalSeconds=" + this.totalSeconds + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", items=" + this.items + ", score=" + this.score + ", breathRate=" + this.breathRate + ", awakeRatio=" + this.awakeRatio + ", lightlyRatio=" + this.lightlyRatio + ", deeplyRatio=" + this.deeplyRatio + ", eyeMovementRatio=" + this.eyeMovementRatio + ", type=" + this.type + ")";
        }

        public SleepDayItemUpload(int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
            this.awakeSeconds = i;
            this.lightlySeconds = i2;
            this.deeplySeconds = i3;
            this.eyeMovementSeconds = i4;
            this.totalSeconds = i5;
            this.startTime = str;
            this.endTime = str2;
            this.items = str3;
            this.score = i6;
            this.breathRate = i7;
            this.awakeRatio = i8;
            this.lightlyRatio = i9;
            this.deeplyRatio = i10;
            this.eyeMovementRatio = i11;
            this.type = i12;
        }

        public final int getAwakeSeconds() {
            return this.awakeSeconds;
        }

        public final void setAwakeSeconds(int i) {
            this.awakeSeconds = i;
        }

        public final int getLightlySeconds() {
            return this.lightlySeconds;
        }

        public final void setLightlySeconds(int i) {
            this.lightlySeconds = i;
        }

        public final int getDeeplySeconds() {
            return this.deeplySeconds;
        }

        public final void setDeeplySeconds(int i) {
            this.deeplySeconds = i;
        }

        public final int getEyeMovementSeconds() {
            return this.eyeMovementSeconds;
        }

        public final void setEyeMovementSeconds(int i) {
            this.eyeMovementSeconds = i;
        }

        public final int getTotalSeconds() {
            return this.totalSeconds;
        }

        public final void setTotalSeconds(int i) {
            this.totalSeconds = i;
        }

        public /* synthetic */ SleepDayItemUpload(int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, DefaultConstructorMarker defaultConstructorMarker) {
            this((i13 & 1) != 0 ? 0 : i, (i13 & 2) != 0 ? 0 : i2, (i13 & 4) != 0 ? 0 : i3, (i13 & 8) != 0 ? 0 : i4, (i13 & 16) != 0 ? 0 : i5, (i13 & 32) != 0 ? (String) null : str, (i13 & 64) != 0 ? (String) null : str2, (i13 & 128) != 0 ? (String) null : str3, (i13 & 256) != 0 ? 0 : i6, (i13 & 512) != 0 ? 0 : i7, (i13 & 1024) != 0 ? 0 : i8, (i13 & 2048) != 0 ? 0 : i9, (i13 & 4096) != 0 ? 0 : i10, (i13 & 8192) != 0 ? 0 : i11, (i13 & 16384) == 0 ? i12 : 0);
        }

        public final String getStartTime() {
            return this.startTime;
        }

        public final void setStartTime(String str) {
            this.startTime = str;
        }

        public final String getEndTime() {
            return this.endTime;
        }

        public final void setEndTime(String str) {
            this.endTime = str;
        }

        public final String getItems() {
            return this.items;
        }

        public final void setItems(String str) {
            this.items = str;
        }

        public final int getScore() {
            return this.score;
        }

        public final void setScore(int i) {
            this.score = i;
        }

        public final int getBreathRate() {
            return this.breathRate;
        }

        public final void setBreathRate(int i) {
            this.breathRate = i;
        }

        public final int getAwakeRatio() {
            return this.awakeRatio;
        }

        public final void setAwakeRatio(int i) {
            this.awakeRatio = i;
        }

        public final int getLightlyRatio() {
            return this.lightlyRatio;
        }

        public final void setLightlyRatio(int i) {
            this.lightlyRatio = i;
        }

        public final int getDeeplyRatio() {
            return this.deeplyRatio;
        }

        public final void setDeeplyRatio(int i) {
            this.deeplyRatio = i;
        }

        public final int getEyeMovementRatio() {
            return this.eyeMovementRatio;
        }

        public final void setEyeMovementRatio(int i) {
            this.eyeMovementRatio = i;
        }

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }
    }

    @Override // com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> msgEvent) {
        if (msgEvent == null || msgEvent.getType() != 837) {
            return;
        }
        printAndSave("停止数据上传服务");
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "停止数据上传服务");
        List<Task> list = this.mTaskList;
        if (!(list == null || list.isEmpty())) {
            Iterator<T> it = this.mTaskList.iterator();
            while (it.hasNext()) {
                ((Task) it.next()).stopExecute();
            }
        }
        stopSelf();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        EventBusWrapper eventBusWrapper = this.mEventBus;
        if (eventBusWrapper != null) {
            eventBusWrapper.unregister();
        }
    }
}