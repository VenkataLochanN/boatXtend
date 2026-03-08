package com.ido.life.ble;

import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.protocol.model.Sport100Type;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.IDeviceDataListener;
import com.ido.life.constants.Constants;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.util.DateUtil;
import com.ido.life.util.HealthDataUtil;
import com.ido.life.util.MedalCaluteUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 N2\u00020\u0001:\u0001NB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\u0006\u0010\u0011\u001a\u00020\u000fJ\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\"\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\rJ\"\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001d\u0018\u00010\rJ\u0010\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u0010\u0010!\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010#J\"\u0010$\u001a\u00020\u000f2\b\u0010%\u001a\u0004\u0018\u00010&2\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010'\u0018\u00010\rJ\"\u0010(\u001a\u00020\u000f2\b\u0010)\u001a\u0004\u0018\u00010*2\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010+\u0018\u00010\rJ\u0010\u0010,\u001a\u00020\u000f2\b\u0010-\u001a\u0004\u0018\u00010.J\u0010\u0010/\u001a\u00020\u000f2\b\u00100\u001a\u0004\u0018\u000101J\"\u00102\u001a\u00020\u000f2\b\u00103\u001a\u0004\u0018\u0001042\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u000105\u0018\u00010\rJ\"\u00106\u001a\u00020\u000f2\b\u00107\u001a\u0004\u0018\u0001082\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u000109\u0018\u00010\rJ\"\u0010:\u001a\u00020\u000f2\b\u0010;\u001a\u0004\u0018\u00010<2\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010=\u0018\u00010\rJ\u0010\u0010>\u001a\u00020\u000f2\b\u0010?\u001a\u0004\u0018\u00010@J\u0010\u0010A\u001a\u00020\u000f2\b\u0010B\u001a\u0004\u0018\u00010CJ\u0010\u0010D\u001a\u00020\u000f2\b\u0010E\u001a\u0004\u0018\u00010FJ\u0010\u0010G\u001a\u00020\u000f2\b\u0010H\u001a\u0004\u0018\u00010IJ\u000e\u0010J\u001a\u00020\u000f2\u0006\u0010K\u001a\u00020\fJ\u0006\u0010L\u001a\u00020\u000fJ\u000e\u0010M\u001a\u00020\u000f2\u0006\u0010K\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000RN\u0010\u0005\u001aB\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b \u0007* \u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b\u0018\u00010\t0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\n\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\f0\f \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\f0\f\u0018\u00010\r0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/ido/life/ble/SyncDeviceDataProxy;", "", "()V", "TAG", "", "mJobMap", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/Job;", "", "mListenerList", "", "Lcom/ido/life/base/IDeviceDataListener;", "", "cancelJob", "", "key", "clearDeviceDataListener", "generateJobKey", "date", AppMeasurementSdk.ConditionalUserProperty.NAME, "processBloodPressureData", "healthBloodPressed", "Lcom/ido/ble/data/manage/database/HealthBloodPressed;", "list", "Lcom/ido/ble/data/manage/database/HealthBloodPressedItem;", "processGpsData", "healthGps", "Lcom/ido/ble/gps/database/HealthGps;", "Lcom/ido/ble/gps/database/HealthGpsItem;", "processGpsDataV3", "healthGpsV3", "Lcom/ido/ble/data/manage/database/HealthGpsV3;", "processHealthNoiseData", "healthNoise", "Lcom/ido/ble/data/manage/database/HealthNoise;", "processHealthPressureData", "healthPressure", "Lcom/ido/ble/data/manage/database/HealthPressure;", "Lcom/ido/ble/data/manage/database/HealthPressureItem;", "processHealthSpO2Data", "healthSpO2", "Lcom/ido/ble/data/manage/database/HealthSpO2;", "Lcom/ido/ble/data/manage/database/HealthSpO2Item;", "processHealthSwimmingData", "healthSwimming", "Lcom/ido/ble/data/manage/database/HealthSwimming;", "processV2ActivityData", "healthActivity", "Lcom/ido/ble/data/manage/database/HealthActivity;", "processV2HeartRateData", "healthHeartRate", "Lcom/ido/ble/data/manage/database/HealthHeartRate;", "Lcom/ido/ble/data/manage/database/HealthHeartRateItem;", "processV2SleepData", "healthSleep", "Lcom/ido/ble/data/manage/database/HealthSleep;", "Lcom/ido/ble/data/manage/database/HealthSleepItem;", "processV2SportData", "healthSport", "Lcom/ido/ble/data/manage/database/HealthSport;", "Lcom/ido/ble/data/manage/database/HealthSportItem;", "processV3HealthActivityV3Data", "healthActivityV3", "Lcom/ido/ble/data/manage/database/HealthActivityV3;", "processV3HealthHeartRateSecondData", "healthHeartRateSecond", "Lcom/ido/ble/data/manage/database/HealthHeartRateSecond;", "processV3HealthSleepV3Data", "healthSleepV3", "Lcom/ido/ble/data/manage/database/HealthSleepV3;", "processV3HealthSportV3Data", "healthSportV3", "Lcom/ido/ble/data/manage/database/HealthSportV3;", "registerDeviceDataListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "stopAllJob", "unregisterDeviceDataListener", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SyncDeviceDataProxy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SyncDeviceDataProxy mInstance = new SyncDeviceDataProxy();
    private String TAG = "SyncDeviceDataProxy";
    private Map<String, Job> mJobMap = Collections.synchronizedMap(new LinkedHashMap());
    private List<IDeviceDataListener> mListenerList = Collections.synchronizedList(new ArrayList());

    public static final SyncDeviceDataProxy getInstance() {
        Companion companion = INSTANCE;
        return mInstance;
    }

    public final void processBloodPressureData(HealthBloodPressed healthBloodPressed, List<? extends HealthBloodPressedItem> list) {
    }

    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ido/life/ble/SyncDeviceDataProxy$Companion;", "", "()V", "mInstance", "Lcom/ido/life/ble/SyncDeviceDataProxy;", "mInstance$annotations", "getInstance", "()Lcom/ido/life/ble/SyncDeviceDataProxy;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void mInstance$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SyncDeviceDataProxy getInstance() {
            return SyncDeviceDataProxy.mInstance;
        }
    }

    public final void processV2SportData(HealthSport healthSport, List<? extends HealthSportItem> list) {
        if (healthSport != null) {
            List<? extends HealthSportItem> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            HomeHelperKt.printAndSave("处理V2活动数据", this.TAG);
            String str = DateUtil.format(healthSport.getDate(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthSp…DateUtil.DATE_FORMAT_YMD)");
            String simpleName = HealthSport.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthSport::class.java.simpleName");
            String strGenerateJobKey = generateJobKey(str, simpleName);
            cancelJob(strGenerateJobKey);
            Map<String, Job> mJobMap = this.mJobMap;
            Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
            mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01751(healthSport, list, null), 3, null));
        }
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2SportData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2SportData$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {46}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthSport $healthSport;
        final /* synthetic */ List $list;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01751(HealthSport healthSport, List list, Continuation continuation) {
            super(2, continuation);
            this.$healthSport = healthSport;
            this.$list = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01751 c01751 = SyncDeviceDataProxy.this.new C01751(this.$healthSport, this.$list, completion);
            c01751.p$ = (CoroutineScope) obj;
            return c01751;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2SportData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2SportData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00991(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00991 c00991 = C01751.this.new C00991(completion);
                c00991.p$ = (CoroutineScope) obj;
                return c00991;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveSportData(C01751.this.$healthSport, C01751.this.$list);
                MedalCaluteUtil.caluteStep();
                MedalCaluteUtil.caluteCalorie();
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C00991(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2SportData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2SportData$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01751.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncActiveDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processV2SleepData(HealthSleep healthSleep, List<? extends HealthSleepItem> list) {
        if (healthSleep != null) {
            List<? extends HealthSleepItem> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            HomeHelperKt.printAndSave("处理V2睡眠数据", this.TAG);
            String str = DateUtil.format(healthSleep.getDate(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthSl…DateUtil.DATE_FORMAT_YMD)");
            String simpleName = HealthSleep.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthSleep::class.java.simpleName");
            String strGenerateJobKey = generateJobKey(str, simpleName);
            cancelJob(strGenerateJobKey);
            Map<String, Job> mJobMap = this.mJobMap;
            Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
            mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01741(healthSleep, list, null), 3, null));
        }
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2SleepData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2SleepData$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {71}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthSleep $healthSleep;
        final /* synthetic */ List $list;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01741(HealthSleep healthSleep, List list, Continuation continuation) {
            super(2, continuation);
            this.$healthSleep = healthSleep;
            this.$list = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01741 c01741 = SyncDeviceDataProxy.this.new C01741(this.$healthSleep, this.$list, completion);
            c01741.p$ = (CoroutineScope) obj;
            return c01741;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2SleepData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2SleepData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00981(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00981 c00981 = C01741.this.new C00981(completion);
                c00981.p$ = (CoroutineScope) obj;
                return c00981;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveSleepData(C01741.this.$healthSleep, C01741.this.$list);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C00981(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2SleepData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2SleepData$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01741.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncSleepDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processV2HeartRateData(HealthHeartRate healthHeartRate, List<? extends HealthHeartRateItem> list) {
        if (healthHeartRate != null) {
            List<? extends HealthHeartRateItem> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            HomeHelperKt.printAndSave("处理V2心率数据", this.TAG);
            String str = DateUtil.format(healthHeartRate.getDate(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthHe…DateUtil.DATE_FORMAT_YMD)");
            String simpleName = HealthHeartRate.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthHeartRate::class.java.simpleName");
            String strGenerateJobKey = generateJobKey(str, simpleName);
            cancelJob(strGenerateJobKey);
            Map<String, Job> mJobMap = this.mJobMap;
            Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
            mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01731(healthHeartRate, list, null), 3, null));
        }
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2HeartRateData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2HeartRateData$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {96}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthHeartRate $healthHeartRate;
        final /* synthetic */ List $list;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01731(HealthHeartRate healthHeartRate, List list, Continuation continuation) {
            super(2, continuation);
            this.$healthHeartRate = healthHeartRate;
            this.$list = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01731 c01731 = SyncDeviceDataProxy.this.new C01731(this.$healthHeartRate, this.$list, completion);
            c01731.p$ = (CoroutineScope) obj;
            return c01731;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2HeartRateData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2HeartRateData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00971(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00971 c00971 = C01731.this.new C00971(completion);
                c00971.p$ = (CoroutineScope) obj;
                return c00971;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveHeartRateData(C01731.this.$healthHeartRate, C01731.this.$list);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C00971(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2HeartRateData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2HeartRateData$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01731.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncHeartRateDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processV2ActivityData(HealthActivity healthActivity) {
        if (healthActivity == null) {
            return;
        }
        HomeHelperKt.printAndSave("处理V2运动数据", this.TAG);
        StringBuilder sb = new StringBuilder();
        sb.append(healthActivity.year);
        sb.append('-');
        sb.append(healthActivity.month);
        sb.append('-');
        sb.append(healthActivity.day);
        sb.append('-');
        sb.append(healthActivity.hour);
        sb.append('-');
        sb.append(healthActivity.minute);
        sb.append('-');
        sb.append(healthActivity.second);
        String string = sb.toString();
        String simpleName = HealthActivity.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthActivity::class.java.simpleName");
        cancelJob(generateJobKey(string, simpleName));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01721(healthActivity, null), 3, null);
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2ActivityData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2ActivityData$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {Sport100Type.SPORT_TYPE_MOUNTAINEERING_MACHINE}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthActivity $healthActivity;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01721(HealthActivity healthActivity, Continuation continuation) {
            super(2, continuation);
            this.$healthActivity = healthActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01721 c01721 = SyncDeviceDataProxy.this.new C01721(this.$healthActivity, completion);
            c01721.p$ = (CoroutineScope) obj;
            return c01721;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2ActivityData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2ActivityData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00961(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00961 c00961 = C01721.this.new C00961(completion);
                c00961.p$ = (CoroutineScope) obj;
                return c00961;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveActivityData(C01721.this.$healthActivity);
                MedalCaluteUtil.caluteHealthSport(C01721.this.$healthActivity.getType());
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C00961(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV2ActivityData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV2ActivityData$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01721.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncSportDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processGpsData(HealthGps healthGps, List<? extends HealthGpsItem> list) {
        CommonLogUtil.d(this.TAG, "processGpsData: ");
        Log.d(this.TAG, "processGpsData: " + String.valueOf(healthGps) + AppInfo.DELIM + String.valueOf(list));
        if (healthGps != null) {
            List<? extends HealthGpsItem> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {healthGps.getYear(), healthGps.getMonth(), healthGps.getDay(), healthGps.getHour(), healthGps.getMinute(), healthGps.getSecond()};
            String str = String.format("%d-%02d-%02d %02d %02d %02d", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            String simpleName = HealthGps.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthGps::class.java.simpleName");
            String strGenerateJobKey = generateJobKey(str, simpleName);
            cancelJob(strGenerateJobKey);
            Map<String, Job> mJobMap = this.mJobMap;
            Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
            mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(healthGps, list, null), 3, null));
        }
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processGpsData$1, reason: invalid class name */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processGpsData$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthGps $healthGps;
        final /* synthetic */ List $list;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HealthGps healthGps, List list, Continuation continuation) {
            super(2, continuation);
            this.$healthGps = healthGps;
            this.$list = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$healthGps, this.$list, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processGpsData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processGpsData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00901(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00901 c00901 = AnonymousClass1.this.new C00901(completion);
                c00901.p$ = (CoroutineScope) obj;
                return c00901;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveGpsData(AnonymousClass1.this.$healthGps, AnonymousClass1.this.$list);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            BuildersKt__BuildersKt.runBlocking$default(null, new C00901(null), 1, null);
            return Unit.INSTANCE;
        }
    }

    public final void processGpsDataV3(HealthGpsV3 healthGpsV3) {
        if (healthGpsV3 == null) {
            return;
        }
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getSportLogPath(), this.TAG, "processGpsData: " + healthGpsV3);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(healthGpsV3.year), Integer.valueOf(healthGpsV3.month), Integer.valueOf(healthGpsV3.day), Integer.valueOf(healthGpsV3.hour), Integer.valueOf(healthGpsV3.minute), Integer.valueOf(healthGpsV3.second)};
        String str = String.format("%d-%02d-%02d %02d:%02d:%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        String simpleName = HealthGpsV3.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthGpsV3::class.java.simpleName");
        String strGenerateJobKey = generateJobKey(str, simpleName);
        cancelJob(strGenerateJobKey);
        Map<String, Job> mJobMap = this.mJobMap;
        Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
        mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01671(healthGpsV3, null), 3, null));
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processGpsDataV3$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processGpsDataV3$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthGpsV3 $healthGpsV3;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01671(HealthGpsV3 healthGpsV3, Continuation continuation) {
            super(2, continuation);
            this.$healthGpsV3 = healthGpsV3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01671 c01671 = new C01671(this.$healthGpsV3, completion);
            c01671.p$ = (CoroutineScope) obj;
            return c01671;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processGpsDataV3$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processGpsDataV3$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00911(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00911 c00911 = C01671.this.new C00911(completion);
                c00911.p$ = (CoroutineScope) obj;
                return c00911;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveGpsDataV3(C01671.this.$healthGpsV3);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            BuildersKt__BuildersKt.runBlocking$default(null, new C00911(null), 1, null);
            return Unit.INSTANCE;
        }
    }

    public final void processHealthNoiseData(HealthNoise healthNoise) {
        HomeHelperKt.printAndSave("processHealthNoiseData healthNoise = " + healthNoise, this.TAG);
        if (healthNoise == null) {
            return;
        }
        CommonLogUtil.printAndSave(this.TAG, "processHealthNoiseData: " + healthNoise);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(healthNoise.year), Integer.valueOf(healthNoise.month), Integer.valueOf(healthNoise.day), Integer.valueOf(healthNoise.hour), Integer.valueOf(healthNoise.minute), Integer.valueOf(healthNoise.second)};
        String str = String.format("%d-%02d-%02d %02d:%02d:%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        String simpleName = HealthGpsV3.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthGpsV3::class.java.simpleName");
        String strGenerateJobKey = generateJobKey(str, simpleName);
        cancelJob(strGenerateJobKey);
        Map<String, Job> mJobMap = this.mJobMap;
        Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
        mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01681(healthNoise, null), 3, null));
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthNoiseData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthNoiseData$1", f = "SyncDeviceDataProxy.kt", i = {0, 0}, l = {210}, m = "invokeSuspend", n = {"$this$launch", "processSuccess"}, s = {"L$0", "Z$0"})
    static final class C01681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthNoise $healthNoise;
        Object L$0;
        boolean Z$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01681(HealthNoise healthNoise, Continuation continuation) {
            super(2, continuation);
            this.$healthNoise = healthNoise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01681 c01681 = SyncDeviceDataProxy.this.new C01681(this.$healthNoise, completion);
            c01681.p$ = (CoroutineScope) obj;
            return c01681;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                boolean zSaveHealthNoiseData = HealthDataUtil.saveHealthNoiseData(this.$healthNoise);
                if (zSaveHealthNoiseData) {
                    if (!HealthDataUtil.hasHistoryHealthVolume()) {
                        HomeHelperKt.printAndSave("第一份噪音数据，首页增加噪音卡片", SyncDeviceDataProxy.this.TAG);
                        EventBusHelper.postSticky(Constants.EventConstants.EVENT_FIRST_NOISE_DATA);
                    }
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C00921 c00921 = new C00921(null);
                    this.L$0 = coroutineScope;
                    this.Z$0 = zSaveHealthNoiseData;
                    this.label = 1;
                    if (BuildersKt.withContext(main, c00921, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                boolean z = this.Z$0;
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthNoiseData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthNoiseData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00921(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00921 c00921 = C01681.this.new C00921(completion);
                c00921.p$ = (CoroutineScope) obj;
                return c00921;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncVolumeDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processHealthSpO2Data(HealthSpO2 healthSpO2, List<? extends HealthSpO2Item> list) {
        if (healthSpO2 != null) {
            List<? extends HealthSpO2Item> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            HomeHelperKt.printAndSave("处理血氧数据", this.TAG);
            String str = DateUtil.format(healthSpO2.date, DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthSp…DateUtil.DATE_FORMAT_YMD)");
            String simpleName = HealthSpO2.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthSpO2::class.java.simpleName");
            String strGenerateJobKey = generateJobKey(str, simpleName);
            cancelJob(strGenerateJobKey);
            Map<String, Job> mJobMap = this.mJobMap;
            Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
            mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01701(healthSpO2, list, null), 3, null));
        }
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthSpO2Data$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthSpO2Data$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {237}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthSpO2 $healthSpO2;
        final /* synthetic */ List $list;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01701(HealthSpO2 healthSpO2, List list, Continuation continuation) {
            super(2, continuation);
            this.$healthSpO2 = healthSpO2;
            this.$list = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01701 c01701 = SyncDeviceDataProxy.this.new C01701(this.$healthSpO2, this.$list, completion);
            c01701.p$ = (CoroutineScope) obj;
            return c01701;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01701) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthSpO2Data$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthSpO2Data$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00941(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00941 c00941 = C01701.this.new C00941(completion);
                c00941.p$ = (CoroutineScope) obj;
                return c00941;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveHealthSpO2Data(C01701.this.$healthSpO2, C01701.this.$list);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C00941(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthSpO2Data$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthSpO2Data$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01701.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncBloodDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processHealthPressureData(HealthPressure healthPressure, List<? extends HealthPressureItem> list) {
        if (healthPressure != null) {
            List<? extends HealthPressureItem> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            HomeHelperKt.printAndSave("处理压力数据", this.TAG);
            String str = DateUtil.format(healthPressure.date, DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthPr…DateUtil.DATE_FORMAT_YMD)");
            String simpleName = HealthPressure.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthPressure::class.java.simpleName");
            cancelJob(generateJobKey(str, simpleName));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01691(healthPressure, list, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthPressureData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthPressureData$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {261}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthPressure $healthPressure;
        final /* synthetic */ List $list;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01691(HealthPressure healthPressure, List list, Continuation continuation) {
            super(2, continuation);
            this.$healthPressure = healthPressure;
            this.$list = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01691 c01691 = SyncDeviceDataProxy.this.new C01691(this.$healthPressure, this.$list, completion);
            c01691.p$ = (CoroutineScope) obj;
            return c01691;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthPressureData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthPressureData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00931(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00931 c00931 = C01691.this.new C00931(completion);
                c00931.p$ = (CoroutineScope) obj;
                return c00931;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.savePressure(C01691.this.$healthPressure, C01691.this.$list);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C00931(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthPressureData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthPressureData$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01691.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncPressureDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processV3HealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond) {
        if (healthHeartRateSecond == null) {
            return;
        }
        HomeHelperKt.printAndSave("处理V3心率数据", this.TAG);
        String str = DateUtil.format(healthHeartRateSecond.getDate(), DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthHe…DateUtil.DATE_FORMAT_YMD)");
        String simpleName = HealthHeartRateSecond.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthHeartRateSecond::class.java.simpleName");
        cancelJob(generateJobKey(str, simpleName));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01771(healthHeartRateSecond, null), 3, null);
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthHeartRateSecondData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthHeartRateSecondData$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {285}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthHeartRateSecond $healthHeartRateSecond;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01771(HealthHeartRateSecond healthHeartRateSecond, Continuation continuation) {
            super(2, continuation);
            this.$healthHeartRateSecond = healthHeartRateSecond;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01771 c01771 = SyncDeviceDataProxy.this.new C01771(this.$healthHeartRateSecond, completion);
            c01771.p$ = (CoroutineScope) obj;
            return c01771;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthHeartRateSecondData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthHeartRateSecondData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01011(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01011 c01011 = C01771.this.new C01011(completion);
                c01011.p$ = (CoroutineScope) obj;
                return c01011;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveHeartRateV3Data(C01771.this.$healthHeartRateSecond);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C01011(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthHeartRateSecondData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthHeartRateSecondData$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01771.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncHeartRateDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processHealthSwimmingData(HealthSwimming healthSwimming) {
        if (healthSwimming == null) {
            return;
        }
        HomeHelperKt.printAndSave("处理游泳数据", this.TAG);
        StringBuilder sb = new StringBuilder();
        sb.append(healthSwimming.year);
        sb.append('-');
        sb.append(healthSwimming.month);
        sb.append('-');
        sb.append(healthSwimming.day);
        sb.append('-');
        sb.append(healthSwimming.hour);
        sb.append('-');
        sb.append(healthSwimming.minute);
        sb.append('-');
        sb.append(healthSwimming.second);
        String string = sb.toString();
        String simpleName = HealthSwimming.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthSwimming::class.java.simpleName");
        String strGenerateJobKey = generateJobKey(string, simpleName);
        cancelJob(strGenerateJobKey);
        Map<String, Job> mJobMap = this.mJobMap;
        Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
        mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01711(healthSwimming, null), 3, null));
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthSwimmingData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthSwimmingData$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {b.W0}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthSwimming $healthSwimming;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01711(HealthSwimming healthSwimming, Continuation continuation) {
            super(2, continuation);
            this.$healthSwimming = healthSwimming;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01711 c01711 = SyncDeviceDataProxy.this.new C01711(this.$healthSwimming, completion);
            c01711.p$ = (CoroutineScope) obj;
            return c01711;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthSwimmingData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthSwimmingData$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00951(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00951 c00951 = C01711.this.new C00951(completion);
                c00951.p$ = (CoroutineScope) obj;
                return c00951;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveSwimData(C01711.this.$healthSwimming);
                MedalCaluteUtil.caluteHealthSport(C01711.this.$healthSwimming.type);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C00951(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processHealthSwimmingData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processHealthSwimmingData$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01711.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncSwimmingDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processV3HealthActivityV3Data(HealthActivityV3 healthActivityV3) {
        if (healthActivityV3 == null) {
            return;
        }
        HomeHelperKt.printAndSave("处理V3运动数据", this.TAG);
        StringBuilder sb = new StringBuilder();
        sb.append(healthActivityV3.year);
        sb.append('-');
        sb.append(healthActivityV3.month);
        sb.append('-');
        sb.append(healthActivityV3.day);
        sb.append('-');
        sb.append(healthActivityV3.hour);
        sb.append('-');
        sb.append(healthActivityV3.minute);
        sb.append('-');
        sb.append(healthActivityV3.second);
        String string = sb.toString();
        String simpleName = HealthActivityV3.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthActivityV3::class.java.simpleName");
        cancelJob(generateJobKey(string, simpleName));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01761(healthActivityV3, null), 3, null);
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthActivityV3Data$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthActivityV3Data$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {338}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthActivityV3 $healthActivityV3;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01761(HealthActivityV3 healthActivityV3, Continuation continuation) {
            super(2, continuation);
            this.$healthActivityV3 = healthActivityV3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01761 c01761 = SyncDeviceDataProxy.this.new C01761(this.$healthActivityV3, completion);
            c01761.p$ = (CoroutineScope) obj;
            return c01761;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthActivityV3Data$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthActivityV3Data$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01001(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01001 c01001 = C01761.this.new C01001(completion);
                c01001.p$ = (CoroutineScope) obj;
                return c01001;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveActivityV3Data(C01761.this.$healthActivityV3);
                MedalCaluteUtil.caluteHealthSport(C01761.this.$healthActivityV3.type);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C01001(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthActivityV3Data$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthActivityV3Data$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01761.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncSportDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processV3HealthSportV3Data(HealthSportV3 healthSportV3) {
        if (healthSportV3 == null) {
            return;
        }
        HomeHelperKt.printAndSave("处理V3活动数据", this.TAG);
        String str = DateUtil.format(healthSportV3.year, healthSportV3.month, healthSportV3.day);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthSp…month, healthSportV3.day)");
        String simpleName = HealthSportV3.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthSportV3::class.java.simpleName");
        String strGenerateJobKey = generateJobKey(str, simpleName);
        cancelJob(strGenerateJobKey);
        Map<String, Job> mJobMap = this.mJobMap;
        Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
        mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01791(healthSportV3, null), 3, null));
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSportV3Data$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSportV3Data$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {365}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthSportV3 $healthSportV3;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01791(HealthSportV3 healthSportV3, Continuation continuation) {
            super(2, continuation);
            this.$healthSportV3 = healthSportV3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01791 c01791 = SyncDeviceDataProxy.this.new C01791(this.$healthSportV3, completion);
            c01791.p$ = (CoroutineScope) obj;
            return c01791;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSportV3Data$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSportV3Data$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01031(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01031 c01031 = C01791.this.new C01031(completion);
                c01031.p$ = (CoroutineScope) obj;
                return c01031;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveSportV3Data(C01791.this.$healthSportV3, C01791.this.$healthSportV3.items);
                MedalCaluteUtil.caluteStep();
                MedalCaluteUtil.caluteCalorie();
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C01031(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSportV3Data$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSportV3Data$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01791.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncActiveDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void processV3HealthSleepV3Data(HealthSleepV3 healthSleepV3) {
        if (healthSleepV3 == null) {
            return;
        }
        HomeHelperKt.printAndSave("处理V3睡眠数据", this.TAG);
        String str = DateUtil.format(healthSleepV3.get_up_year, healthSleepV3.get_up_month, healthSleepV3.get_up_day);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(healthSl…healthSleepV3.get_up_day)");
        String simpleName = HealthSleepV3.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HealthSleepV3::class.java.simpleName");
        String strGenerateJobKey = generateJobKey(str, simpleName);
        cancelJob(strGenerateJobKey);
        Map<String, Job> mJobMap = this.mJobMap;
        Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
        mJobMap.put(strGenerateJobKey, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01781(healthSleepV3, null), 3, null));
    }

    /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSleepV3Data$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSleepV3Data$1", f = "SyncDeviceDataProxy.kt", i = {0}, l = {390}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C01781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HealthSleepV3 $healthSleepV3;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01781(HealthSleepV3 healthSleepV3, Continuation continuation) {
            super(2, continuation);
            this.$healthSleepV3 = healthSleepV3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01781 c01781 = SyncDeviceDataProxy.this.new C01781(this.$healthSleepV3, completion);
            c01781.p$ = (CoroutineScope) obj;
            return c01781;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSleepV3Data$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSleepV3Data$1$1", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01021(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01021 c01021 = C01781.this.new C01021(completion);
                c01021.p$ = (CoroutineScope) obj;
                return c01021;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                HealthDataUtil.saveSleepV3Data(C01781.this.$healthSleepV3);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C01021(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSleepV3Data$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SyncDeviceDataProxy.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.ble.SyncDeviceDataProxy$processV3HealthSleepV3Data$1$2", f = "SyncDeviceDataProxy.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C01781.this.new AnonymousClass2(completion);
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
                List mListenerList = SyncDeviceDataProxy.this.mListenerList;
                Intrinsics.checkExpressionValueIsNotNull(mListenerList, "mListenerList");
                if (!mListenerList.isEmpty()) {
                    int size = SyncDeviceDataProxy.this.mListenerList.size();
                    for (int i = 0; i < size; i++) {
                        ((IDeviceDataListener) SyncDeviceDataProxy.this.mListenerList.get(i)).syncSleepDataSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    private final void cancelJob(String key) {
        if (this.mJobMap.containsKey(key)) {
            Job job = this.mJobMap.get(key);
            if (job != null && job.isActive()) {
                job.cancel(new CancellationException("取消任务key=" + key));
            }
            this.mJobMap.remove(key);
        }
    }

    private final String generateJobKey(String date, String name) {
        return name + '[' + date + ']';
    }

    public final void registerDeviceDataListener(IDeviceDataListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.mListenerList.contains(listener)) {
            return;
        }
        this.mListenerList.add(listener);
    }

    public final void unregisterDeviceDataListener(IDeviceDataListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.mListenerList.contains(listener)) {
            this.mListenerList.remove(listener);
        }
    }

    public final void clearDeviceDataListener() {
        this.mListenerList.clear();
    }

    public final void stopAllJob() {
        Map<String, Job> mJobMap = this.mJobMap;
        Intrinsics.checkExpressionValueIsNotNull(mJobMap, "mJobMap");
        if (!mJobMap.isEmpty()) {
            Map<String, Job> mJobMap2 = this.mJobMap;
            Intrinsics.checkExpressionValueIsNotNull(mJobMap2, "mJobMap");
            for (Map.Entry<String, Job> entry : mJobMap2.entrySet()) {
                if (entry.getValue().isActive()) {
                    entry.getValue().cancel(new CancellationException("用户手动取消任务"));
                }
            }
            this.mJobMap.clear();
        }
    }
}