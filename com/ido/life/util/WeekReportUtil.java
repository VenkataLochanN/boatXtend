package com.ido.life.util;

import com.ido.common.log.CommonLogUtil;
import com.ido.life.constants.Constants;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.database.model.StepDayData;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: WeekReportUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ido/life/util/WeekReportUtil;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "generateHealthWeekReport", "", "userId", "", "generateHealthWeekReportNewThread", "healthWeekReport", "app_release"}, k = 1, mv = {1, 1, 16})
public class WeekReportUtil {
    private final String TAG = WeekReportUtil.class.getSimpleName();

    /* JADX INFO: renamed from: com.ido.life.util.WeekReportUtil$generateHealthWeekReportNewThread$1, reason: invalid class name */
    /* JADX INFO: compiled from: WeekReportUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.util.WeekReportUtil$generateHealthWeekReportNewThread$1", f = "WeekReportUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $userId;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, Continuation continuation) {
            super(2, continuation);
            this.$userId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = WeekReportUtil.this.new AnonymousClass1(this.$userId, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.util.WeekReportUtil$generateHealthWeekReportNewThread$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: WeekReportUtil.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.util.WeekReportUtil$generateHealthWeekReportNewThread$1$1", f = "WeekReportUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01301(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01301 c01301 = AnonymousClass1.this.new C01301(completion);
                c01301.p$ = (CoroutineScope) obj;
                return c01301;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                WeekReportUtil.this.healthWeekReport(AnonymousClass1.this.$userId);
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
            BuildersKt__BuildersKt.runBlocking$default(null, new C01301(null), 1, null);
            return Unit.INSTANCE;
        }
    }

    public final synchronized void generateHealthWeekReportNewThread(long userId) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(userId, null), 2, null);
    }

    public final synchronized void generateHealthWeekReport(long userId) {
        healthWeekReport(userId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void healthWeekReport(long userId) {
        boolean z;
        boolean z2;
        boolean z3;
        String str;
        MessageEntity messageEntityQueryHealthReportByMonday;
        Calendar calendar;
        int i;
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        StepDayData oldestStepData = localHealthDataManager.getOldestStepData();
        int i2 = 3;
        if (oldestStepData == null) {
            GreenDaoUtil.deleteMessageByType(userId, 3);
            return;
        }
        Calendar installCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(installCalendar, "installCalendar");
        installCalendar.setTimeInMillis(oldestStepData.getTimeStamp());
        installCalendar.set(11, 1);
        boolean z4 = false;
        installCalendar.set(12, 0);
        installCalendar.set(13, 0);
        installCalendar.set(14, 0);
        Calendar currentCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(currentCalendar, "currentCalendar");
        currentCalendar.setFirstDayOfWeek(2);
        currentCalendar.set(11, 1);
        currentCalendar.set(12, 0);
        currentCalendar.set(13, 0);
        currentCalendar.set(14, 0);
        long timeInMillis = currentCalendar.getTimeInMillis();
        currentCalendar.set(7, 2);
        int i3 = -7;
        currentCalendar.add(5, -7);
        Calendar lastWeekLastDay = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(lastWeekLastDay, "lastWeekLastDay");
        lastWeekLastDay.setFirstDayOfWeek(2);
        lastWeekLastDay.add(5, -1);
        String str2 = (String) null;
        MessageEntity messageEntity = (MessageEntity) null;
        while (installCalendar.compareTo(lastWeekLastDay) <= 0 && (currentCalendar.compareTo(installCalendar) >= 0 || lastWeekLastDay.compareTo(installCalendar) >= 0)) {
            String str3 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            MessageEntity messageEntityQueryHealthReportByMonday2 = GreenDaoUtil.queryHealthReportByMonday(userId, str3);
            if (messageEntityQueryHealthReportByMonday2 == null) {
                MessageEntity messageEntity2 = new MessageEntity();
                messageEntity2.setHasRead(z4);
                messageEntity2.setType(i2);
                messageEntity2.setUserId(userId);
                calendar = installCalendar;
                messageEntity2.setCreateTime(currentCalendar.getTimeInMillis());
                currentCalendar.add(5, 6);
                String str4 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
                messageEntity2.setStartDayMonday(str3);
                messageEntity2.setMondayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, str3, str4) > 0 ? true : z4);
                currentCalendar.add(5, i3);
                String str5 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
                currentCalendar.add(5, 6);
                String str6 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
                messageEntity2.setStartDaySunday(str5);
                messageEntity2.setSundayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, str5, str6) > 0 ? true : z4);
                currentCalendar.add(5, i3);
                String str7 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
                currentCalendar.add(5, 6);
                String str8 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
                messageEntity2.setStartDaySaturday(str7);
                messageEntity2.setSaturdayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, str7, str8) > 0 ? true : z4);
                if (messageEntity2.getMondayGenerate() || messageEntity2.getSaturdayGenerate() || messageEntity2.getSundayGenerate()) {
                    GreenDaoUtil.addMessageEntity(messageEntity2);
                }
                currentCalendar.add(5, -4);
                str2 = str7;
                i = i3;
            } else {
                calendar = installCalendar;
                if (timeInMillis - messageEntityQueryHealthReportByMonday2.getCreateTime() < 1123200000) {
                    CommonLogUtil.d(this.TAG, "刷新健康周报数据状态");
                    Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
                    Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
                    calendar2.setTime(DateUtil.string2Date(messageEntityQueryHealthReportByMonday2.getStartDayMonday(), DateUtil.DATE_FORMAT_YMD));
                    calendar2.add(5, 6);
                    messageEntityQueryHealthReportByMonday2.setMondayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, str3, DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_YMD)) > 0);
                    calendar2.setTime(DateUtil.string2Date(messageEntityQueryHealthReportByMonday2.getStartDaySunday(), DateUtil.DATE_FORMAT_YMD));
                    calendar2.add(5, 6);
                    messageEntityQueryHealthReportByMonday2.setSundayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, str3, DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_YMD)) > 0);
                    calendar2.setTime(DateUtil.string2Date(messageEntityQueryHealthReportByMonday2.getStartDaySaturday(), DateUtil.DATE_FORMAT_YMD));
                    calendar2.add(5, 6);
                    messageEntityQueryHealthReportByMonday2.setSaturdayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, str3, DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_YMD)) > 0);
                    if (messageEntityQueryHealthReportByMonday2.getMondayGenerate() || messageEntityQueryHealthReportByMonday2.getSaturdayGenerate() || messageEntityQueryHealthReportByMonday2.getSundayGenerate()) {
                        messageEntityQueryHealthReportByMonday2.update();
                    } else {
                        messageEntityQueryHealthReportByMonday2.delete();
                    }
                }
                str2 = str3;
                i = -7;
            }
            currentCalendar.add(5, i);
            lastWeekLastDay.add(5, i);
            i3 = i;
            installCalendar = calendar;
            i2 = 3;
            z4 = false;
        }
        currentCalendar.setTimeInMillis(timeInMillis);
        currentCalendar.setFirstDayOfWeek(2);
        int i4 = currentCalendar.get(7);
        if (currentCalendar.after(installCalendar)) {
            if (i4 == 7) {
                CommonLogUtil.d(this.TAG, "今天是周六,生成健康周报");
                currentCalendar.add(5, -5);
                String str9 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
                messageEntityQueryHealthReportByMonday = GreenDaoUtil.queryHealthReportByMonday(userId, str9);
                if (messageEntityQueryHealthReportByMonday == null) {
                    messageEntityQueryHealthReportByMonday = new MessageEntity();
                    messageEntityQueryHealthReportByMonday.setCreateTime(currentCalendar.getTimeInMillis());
                    messageEntityQueryHealthReportByMonday.setHasRead(false);
                    messageEntityQueryHealthReportByMonday.setType(3);
                    messageEntityQueryHealthReportByMonday.setUserId(userId);
                    messageEntityQueryHealthReportByMonday.setStartDayMonday(str9);
                    currentCalendar.add(5, -1);
                    messageEntityQueryHealthReportByMonday.setStartDaySunday(DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD));
                    currentCalendar.add(5, -1);
                    messageEntityQueryHealthReportByMonday.setStartDaySaturday(DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD));
                    str = str9;
                    z = true;
                    z2 = false;
                    z3 = false;
                } else {
                    messageEntityQueryHealthReportByMonday.setUpdateTime(System.currentTimeMillis());
                    str = str9;
                    z = true;
                    z2 = false;
                    z3 = true;
                }
            } else {
                z = true;
                if (i4 == 1) {
                    CommonLogUtil.d(this.TAG, "今天是周日,生成健康周报");
                    currentCalendar.add(5, -6);
                    String str10 = DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
                    MessageEntity messageEntityQueryHealthReportByMonday3 = GreenDaoUtil.queryHealthReportByMonday(userId, str10);
                    if (messageEntityQueryHealthReportByMonday3 == null) {
                        messageEntityQueryHealthReportByMonday3 = new MessageEntity();
                        messageEntityQueryHealthReportByMonday3.setCreateTime(currentCalendar.getTimeInMillis());
                        z2 = false;
                        messageEntityQueryHealthReportByMonday3.setHasRead(false);
                        messageEntityQueryHealthReportByMonday3.setType(3);
                        messageEntityQueryHealthReportByMonday3.setUserId(userId);
                        messageEntityQueryHealthReportByMonday3.setStartDayMonday(str10);
                        currentCalendar.add(5, -1);
                        messageEntityQueryHealthReportByMonday3.setStartDaySunday(DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD));
                        currentCalendar.add(5, -1);
                        messageEntityQueryHealthReportByMonday3.setStartDaySaturday(DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD));
                        z3 = false;
                    } else {
                        z2 = false;
                        messageEntityQueryHealthReportByMonday3.setUpdateTime(System.currentTimeMillis());
                        z3 = true;
                    }
                    MessageEntity messageEntity3 = messageEntityQueryHealthReportByMonday3;
                    str = str10;
                    messageEntityQueryHealthReportByMonday = messageEntity3;
                } else {
                    z2 = false;
                    z3 = false;
                    str = str2;
                    messageEntityQueryHealthReportByMonday = messageEntity;
                }
            }
            if (messageEntityQueryHealthReportByMonday != null) {
                currentCalendar.setTime(DateUtil.string2Date(messageEntityQueryHealthReportByMonday.getStartDayMonday(), DateUtil.DATE_FORMAT_YMD));
                currentCalendar.add(5, 6);
                messageEntityQueryHealthReportByMonday.setMondayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, messageEntityQueryHealthReportByMonday.getStartDayMonday(), DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD)) > 0 ? z : z2);
                currentCalendar.setTime(DateUtil.string2Date(messageEntityQueryHealthReportByMonday.getStartDaySunday(), DateUtil.DATE_FORMAT_YMD));
                currentCalendar.add(5, 6);
                messageEntityQueryHealthReportByMonday.setSundayGenerate(LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, messageEntityQueryHealthReportByMonday.getStartDaySunday(), DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD)) > 0 ? z : z2);
                currentCalendar.setTime(DateUtil.string2Date(messageEntityQueryHealthReportByMonday.getStartDaySaturday(), DateUtil.DATE_FORMAT_YMD));
                currentCalendar.add(5, 6);
                if (LocalHealthDataManager.getInstance().getStepDailyDataListNonNull(userId, str, DateUtil.format(currentCalendar.getTime(), DateUtil.DATE_FORMAT_YMD)) <= 0) {
                    z = z2;
                }
                messageEntityQueryHealthReportByMonday.setSaturdayGenerate(z);
                if (messageEntityQueryHealthReportByMonday.getMondayGenerate() || messageEntityQueryHealthReportByMonday.getSaturdayGenerate() || messageEntityQueryHealthReportByMonday.getSundayGenerate()) {
                    if (z3) {
                        messageEntityQueryHealthReportByMonday.update();
                    } else {
                        GreenDaoUtil.addMessageEntity(messageEntityQueryHealthReportByMonday);
                    }
                } else if (z3) {
                    messageEntityQueryHealthReportByMonday.delete();
                }
            }
        }
        EventBusHelper.post(Constants.EventConstants.EVENT_REFRESH_WEEK_REPORT);
    }
}