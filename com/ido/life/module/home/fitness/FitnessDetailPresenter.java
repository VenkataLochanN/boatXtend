package com.ido.life.module.home.fitness;

import android.graphics.Color;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.GradientBarPoint;
import com.ido.life.constants.Constants;
import com.ido.life.customview.RadiusProgressBar;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.enums.StageInfoEnum;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: FitnessDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u0000 ®\u000128\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001:\u0004®\u0001¯\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010X\u001a\u00020\b2\u0006\u0010Y\u001a\u00020\bH\u0002J\u0010\u0010Z\u001a\u00020\b2\u0006\u0010Y\u001a\u00020\bH\u0002J\b\u0010[\u001a\u00020\\H\u0002J\b\u0010]\u001a\u00020\\H\u0002J\b\u0010^\u001a\u00020\\H\u0002J\u0011\u0010_\u001a\u00020\\H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010`J\u0010\u0010a\u001a\u00020\b2\u0006\u0010b\u001a\u00020\bH\u0002J\u0010\u0010c\u001a\u00020\b2\u0006\u0010d\u001a\u00020\bH\u0002J\b\u0010e\u001a\u00020\\H\u0014J&\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00030g2\u0006\u0010h\u001a\u00020\b2\u0006\u0010i\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0002J(\u0010k\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u000e2\u0006\u0010m\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0002J(\u0010o\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u000e2\u0006\u0010m\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0002J(\u0010p\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u000e2\u0006\u0010m\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0002J(\u0010q\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u000e2\u0006\u0010m\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0002J\u0006\u0010r\u001a\u00020\bJ\u0006\u0010s\u001a\u00020\bJ\u0006\u0010t\u001a\u00020\bJ\u0006\u0010u\u001a\u00020\bJ\u0006\u0010v\u001a\u00020\bJ\u0006\u0010w\u001a\u00020\bJ\u0006\u0010x\u001a\u00020\bJ\u0006\u0010y\u001a\u00020\bJ\f\u0010z\u001a\b\u0012\u0004\u0012\u00020{0gJ\f\u0010|\u001a\b\u0012\u0004\u0012\u00020{0gJ\b\u0010}\u001a\u00020\u0012H\u0002J\u0017\u0010~\u001a\u0011\u0012\r\u0012\u000b \u0080\u0001*\u0004\u0018\u00010\u007f0\u007f0gH\u0014J\t\u0010\u0081\u0001\u001a\u00020\bH\u0002J\t\u0010\u0082\u0001\u001a\u00020\\H\u0016J\n\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002J\u0007\u0010\u0085\u0001\u001a\u00020\bJ\u0007\u0010\u0086\u0001\u001a\u00020\bJ\u0007\u0010\u0087\u0001\u001a\u00020\bJ\u0007\u0010\u0088\u0001\u001a\u00020\bJ\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020{0gJ)\u0010\u008a\u0001\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u000e2\u0006\u0010m\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0002J)\u0010\u008b\u0001\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u000e2\u0006\u0010m\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0002J\u0012\u0010\u008c\u0001\u001a\u00020@2\u0007\u0010\u008d\u0001\u001a\u00020\u007fH\u0002J\u0012\u0010\u008e\u0001\u001a\u00020@2\u0007\u0010\u008d\u0001\u001a\u00020\u007fH\u0002J\u0014\u0010\u008f\u0001\u001a\u00020@2\t\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u007fH\u0016J\u0012\u0010\u0090\u0001\u001a\u00020@2\u0007\u0010\u008d\u0001\u001a\u00020\u007fH\u0002J\t\u0010\u0091\u0001\u001a\u00020\\H\u0016J\u0013\u0010\u0092\u0001\u001a\u00020\\2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0016J\u0019\u0010\u0095\u0001\u001a\u00020\\2\u000e\u0010\u0096\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u0097\u0001H\u0016J\u001c\u0010\u0098\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010\u009b\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010\u009c\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010\u009d\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0094@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010\u009e\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0094@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010\u009f\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0094@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010 \u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0094@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010¡\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010¢\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010£\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010¤\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010¥\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010¦\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010§\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010¨\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u001c\u0010©\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0001J\u0012\u0010ª\u0001\u001a\u00020\\H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010`J\t\u0010«\u0001\u001a\u00020\\H\u0016J\t\u0010¬\u0001\u001a\u00020\\H\u0002J\t\u0010\u00ad\u0001\u001a\u00020\\H\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR\u001a\u0010\u0016\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\n\"\u0004\b\u0018\u0010\fR\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\n\"\u0004\b\u001f\u0010\fR\u001a\u0010 \u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\n\"\u0004\b\"\u0010\fR\u001a\u0010#\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\n\"\u0004\b%\u0010\fR\u001a\u0010&\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\n\"\u0004\b(\u0010\fR\u001a\u0010)\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\n\"\u0004\b+\u0010\fR\u000e\u0010,\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\n\"\u0004\b0\u0010\fR\u000e\u00101\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\n\"\u0004\b5\u0010\fR\u000e\u00106\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\n\"\u0004\b>\u0010\fR\u001a\u0010?\u001a\u00020@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001a\u0010E\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\n\"\u0004\bG\u0010\fR\u001a\u0010H\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\n\"\u0004\bJ\u0010\fR\u001a\u0010K\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\n\"\u0004\bM\u0010\fR\u000e\u0010N\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010T\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\n\"\u0004\bV\u0010\fR\u000e\u0010W\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006°\u0001"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/GradientBarPoint;", "Lcom/ido/life/module/home/fitness/IFitnessDetailDetailView;", "iView", "(Lcom/ido/life/module/home/fitness/IFitnessDetailDetailView;)V", "mActiveCalorieHasDataDayCount", "", "getMActiveCalorieHasDataDayCount", "()I", "setMActiveCalorieHasDataDayCount", "(I)V", "mActiveCalorieScore", "", "mActiveCalorieSituationAvg", "mActiveCalorieSituationCompareState", "mActiveCalorieSituationStage", "Lcom/ido/life/enums/StageInfoEnum;", "mActiveCalorieTargetDayCount", "getMActiveCalorieTargetDayCount", "setMActiveCalorieTargetDayCount", "mActiveTimeHasDataDayCount", "getMActiveTimeHasDataDayCount", "setMActiveTimeHasDataDayCount", "mActiveTimeScore", "mActiveTimeSituationAvg", "mActiveTimeSituationCompareState", "mActiveTimeSituationStage", "mActiveTimeTargetDayCount", "getMActiveTimeTargetDayCount", "setMActiveTimeTargetDayCount", "mActiveTimeTargetTime", "getMActiveTimeTargetTime", "setMActiveTimeTargetTime", "mActiveTimeTotalDuration", "getMActiveTimeTotalDuration", "setMActiveTimeTotalDuration", "mActiveTimeTotalTarget", "getMActiveTimeTotalTarget", "setMActiveTimeTotalTarget", "mActivityCalorieAvg", "getMActivityCalorieAvg", "setMActivityCalorieAvg", "mActivityCalorieProgressMax", "mActivityCalorieProgressMin", "mActivityTimeAvg", "getMActivityTimeAvg", "setMActivityTimeAvg", "mActivityTimeProgressMax", "mActivityTimeProgressMin", "mAllDayActivityCalorie", "getMAllDayActivityCalorie", "setMAllDayActivityCalorie", "mBmr", "mDefaultBarColor", "mDefaultBarRadius", "mICallBack", "Lcom/ido/life/base/BaseDeviceParaCallBack;", "mNormalRoundAngle", "mSedentary", "getMSedentary", "setMSedentary", "mShowWearDuration", "", "getMShowWearDuration", "()Z", "setMShowWearDuration", "(Z)V", "mValidWearDuration", "getMValidWearDuration", "setMValidWearDuration", "mWalkAvg", "getMWalkAvg", "setMWalkAvg", "mWalkHasDataDayCount", "getMWalkHasDataDayCount", "setMWalkHasDataDayCount", "mWalkProgressMax", "mWalkProgressMin", "mWalkScore", "mWalkSituationAvg", "mWalkSituationCompareState", "mWalkSitutionStage", "mWalkTargetDayCount", "getMWalkTargetDayCount", "setMWalkTargetDayCount", "mWeekRoundAngle", "ajustCalorieMax", "calorie", "ajustCalorieMin", "calculateWalkSituation", "", "caluteActiveCalorieSituation", "caluteActiveTimeSituation", "caluteRecentScore", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "caluteYMaxActiviteTime", "activeTimeMax", "caluteYMaxCalorie", "maxCalorie", "clearData", "generateDefaultChartData", "", "count", "defaultValue", "roundAnglePercent", "getActiveCalorieTargetChartBean", "x", "y", "actualValue", "getActiveCalorieUnTargetChartBean", "getActiveTimeTargetChartBean", "getActiveTimeUnTargetChartBean", "getActivityCalorieDefaultXMax", "getActivityCalorieDefaultXMin", "getActivityCalorieDefaultYMax", "getActivityCalorieDefaultYMin", "getActivityTimeDefaultXMax", "getActivityTimeDefaultXMin", "getActivityTimeDefaultYMax", "getActivityTimeDefaultYMin", "getActivityTimePropertyList", "Lcom/ido/life/customview/RadiusProgressBar$DividerProperty;", "getCaloriePropertyList", "getComplexStage", "getDataDownloadType", "", "kotlin.jvm.PlatformType", "getDayCount", "getDetailData", "getUserTarget", "Lcom/ido/life/database/model/UserTargetNew;", "getWalkDefaultXMax", "getWalkDefaultXMin", "getWalkDefaultYMax", "getWalkDefaultYMin", "getWalkPropertyList", "getWalkTargetChartBean", "getWalkUnTargetChartBean", "hasActiveTimeData", "date", "hasCalorieData", "hasData", "hasWalkData", "onTaskComplete", "onTaskExecutedSuccess", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "processEventBusMessage", "message", "Lcom/ido/life/base/BaseMessage;", "readDayActiveTimeFromLocal", "showChartAnimator", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDayCalorieFromLocal", "readDayWalkFromLocal", "readLocalDayData", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "readMonthActiveTimeFromLocal", "readMonthCalorieFromLocal", "readMonthWalkFromLocal", "readWeekActiveTimeFromLocal", "readWeekCalorieFromLocal", "readWeekWalkFromLocal", "readYearActiveTimeFromLocal", "readYearCalorieFromLocal", "readYearWalkFromLocal", "refreshBottom", "requestPullData", "resetCache", "updateTarget", "Companion", "WalkYearBean", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FitnessDetailPresenter extends BaseDetailPresenter<List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, IFitnessDetailDetailView> {
    private int mActiveCalorieHasDataDayCount;
    private float mActiveCalorieScore;
    private float mActiveCalorieSituationAvg;
    private int mActiveCalorieSituationCompareState;
    private StageInfoEnum mActiveCalorieSituationStage;
    private int mActiveCalorieTargetDayCount;
    private int mActiveTimeHasDataDayCount;
    private float mActiveTimeScore;
    private float mActiveTimeSituationAvg;
    private int mActiveTimeSituationCompareState;
    private StageInfoEnum mActiveTimeSituationStage;
    private int mActiveTimeTargetDayCount;
    private int mActiveTimeTargetTime;
    private int mActiveTimeTotalDuration;
    private int mActiveTimeTotalTarget;
    private int mActivityCalorieAvg;
    private int mActivityCalorieProgressMax;
    private int mActivityCalorieProgressMin;
    private int mActivityTimeAvg;
    private int mActivityTimeProgressMax;
    private int mActivityTimeProgressMin;
    private int mAllDayActivityCalorie;
    private int mBmr;
    private final int mDefaultBarColor;
    private final float mDefaultBarRadius;
    private final BaseDeviceParaCallBack mICallBack;
    private final float mNormalRoundAngle;
    private int mSedentary;
    private boolean mShowWearDuration;
    private int mValidWearDuration;
    private int mWalkAvg;
    private int mWalkHasDataDayCount;
    private int mWalkProgressMax;
    private int mWalkProgressMin;
    private float mWalkScore;
    private float mWalkSituationAvg;
    private int mWalkSituationCompareState;
    private StageInfoEnum mWalkSitutionStage;
    private int mWalkTargetDayCount;
    private final float mWeekRoundAngle;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int FLAT = 3;

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$caluteRecentScore$1, reason: invalid class name */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, d2 = {"caluteRecentScore", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {2636, 2770}, m = "caluteRecentScore", n = {"this", "score", "totalDesc", "this", "score", "totalDesc", "activityCalorieDesc", "activityTimeDesc", "walkDesc"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.caluteRecentScore(this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayCalorieFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readDayCalorieFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {763, 787, Constants.EventConstants.EVENT_ACTIVE_TIME_TARGET_CHANGED}, m = "readDayCalorieFromLocal", n = {"this", "showChartAnimator", "calorieDayData", "this", "showChartAnimator", "calorieDayData", "sourceArray", "array", "listCalorie", "this", "showChartAnimator", "calorieDayData", "sourceArray", "array", "listCalorie", "maxCalorie", "listCalorieSize", "calorie", "map", "totalActicityCalorie", "totalCalorie", "yMax", "yDefaultHeight", "result", "dayCount", "calorieTarget"}, s = {"L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "I$2", "L$5", "L$6", "L$7", "L$8", "F$0", "L$9", "I$3", "L$10"})
    static final class C03121 extends ContinuationImpl {
        float F$0;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03121(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readDayCalorieFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalDayData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 2, 2, 3, 3}, l = {692, 693, 694, 701}, m = "readLocalDayData", n = {"this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0"})
    static final class C03171 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03171(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readLocalDayData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalMonthData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 2, 2, 3, 3}, l = {724, 725, 726, 733}, m = "readLocalMonthData", n = {"this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0"})
    static final class C03191 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03191(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readLocalMonthData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalWeekData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 2, 2, 3, 3}, l = {708, 709, 710, 717}, m = "readLocalWeekData", n = {"this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0"})
    static final class C03211 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03211(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readLocalWeekData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalYearData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 2, 2, 3, 3}, l = {740, 741, 742, 749}, m = "readLocalYearData", n = {"this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator", "this", "showChartAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0"})
    static final class C03231 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03231(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readLocalYearData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthActiveTimeFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readMonthActiveTimeFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1652, 1769}, m = "readMonthActiveTimeFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "result", "monthList", "yMax", "activetimeAvg", "activeTimeTotal", "hasTargetDayCount", "hasDataDayCount", "showAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$5", "L$6", "L$7"})
    static final class C03251 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03251(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readMonthActiveTimeFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthCalorieFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readMonthCalorieFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1049, 1167}, m = "readMonthCalorieFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "showAnimator", "monthList", "result", Constants.AppPackage.CALENDAR, "yMax", "avgActivityCalorie", "activeCalorieTotal", "hasTargetDayCount", "hasDataDayCount"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "L$7", "L$8"})
    static final class C03281 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03281(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readMonthCalorieFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthWalkFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readMonthWalkFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {2174, 2277}, m = "readMonthWalkFromLocal", n = {"this", "showChartAnimator", "startCalendar", "dayCount", "this", "showChartAnimator", "startCalendar", "dayCount", "monthList", "result", "map", "showAnimator", "walkAvg", "walkMax", "hasTargetDayCount", "hasDataDayCount"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10"})
    static final class C03311 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03311(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readMonthWalkFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekActiveTimeFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readWeekActiveTimeFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1479, 1632}, m = "readWeekActiveTimeFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "result", "weekList", "yMax", "activetimeAvg", "activeTimeTotal", "hasDataDayCount", "totalTimeTarget", "showAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
    static final class C03341 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03341(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readWeekActiveTimeFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekCalorieFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readWeekCalorieFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {Constants.EventConstants.EVENT_CHOOSE_ACCOUNT_NAME, Constants.EventConstants.EVENT_PRESET_APPS_LOADED, GLMapStaticValue.MAP_PARAMETERNAME_SCENIC}, m = "readWeekCalorieFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "dataList", "this", "showChartAnimator", "dataList", "startCalendar", "dataSize", Constants.AppPackage.CALENDAR, "weekStart", "dayOfWeek", "activeCalorieTotal", "activeCalorieCount", "activeCalorieMax", "avgActivityCalorie", "hasTargetDayCount", "hasDataDayCount", "map", "targetList", "yMax", "yDefaultHeight", "result", "dayCount"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "L$2", "I$0", "L$3", "I$1", "I$2", "I$3", "I$4", "I$5", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "F$0", "L$10", "I$6"})
    static final class C03371 extends ContinuationImpl {
        float F$0;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03371(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readWeekCalorieFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekWalkFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readWeekWalkFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {2049, 2152}, m = "readWeekWalkFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "result", "map", "walkAvg", "walkMax", "hasTargetDayCount", "hasDataDayCount", "dataList", "defaultValue", "showAnimator", "dayCount", Constants.AppPackage.CALENDAR}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "F$0", "L$8", "I$0", "L$9"})
    static final class C03411 extends ContinuationImpl {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03411(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readWeekWalkFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearActiveTimeFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readYearActiveTimeFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1788, 1917}, m = "readYearActiveTimeFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "result", "yearList", "yMax", "activetimeAvg", "activeTimeTotal", "hasTargetDayCout", "hasDataDayCount", "showAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$5", "L$6", "L$7"})
    static final class C03441 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03441(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readYearActiveTimeFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearCalorieFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readYearCalorieFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1186, 1314}, m = "readYearCalorieFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "result", "yMax", "avgActivityCalorie", "activeCalorieTotal", "hasTargetDayCount", "hasDataDayCount", "yearList", "showAnimator"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "L$6", "L$7"})
    static final class C03471 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03471(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readYearCalorieFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearWalkFromLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"readYearWalkFromLocal", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {2296, 2416}, m = "readYearWalkFromLocal", n = {"this", "showChartAnimator", "this", "showChartAnimator", "yearList", "result", "map", "startDate", "startCalendar", "endDate", "endCalendar", "walkAvg", "walkMax", "hasDataDayCount", "hasTargetDayCount", "defaultYHeight", "dayCount"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "F$0", "I$0"})
    static final class C03501 extends ContinuationImpl {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03501(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.readYearWalkFromLocal(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$refreshBottom$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, d2 = {"refreshBottom", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter", f = "FitnessDetailPresenter.kt", i = {0, 0, 1, 1}, l = {583, 584}, m = "refreshBottom", n = {"this", "functionInfo", "this", "functionInfo"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C03531 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03531(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FitnessDetailPresenter.this.refreshBottom(this);
        }
    }

    public static final int getDOWN() {
        Companion companion = INSTANCE;
        return DOWN;
    }

    public static final int getFLAT() {
        Companion companion = INSTANCE;
        return FLAT;
    }

    public static final int getUP() {
        Companion companion = INSTANCE;
        return UP;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
    }

    public final int getActivityCalorieDefaultYMax() {
        return 1;
    }

    public final int getActivityCalorieDefaultYMin() {
        return 0;
    }

    public final int getActivityTimeDefaultYMax() {
        return 1;
    }

    public final int getActivityTimeDefaultYMin() {
        return 0;
    }

    public final int getWalkDefaultYMax() {
        return 1;
    }

    public final int getWalkDefaultYMin() {
        return 0;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessDetailPresenter(IFitnessDetailDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mDefaultBarRadius = 0.05f;
        this.mDefaultBarColor = Color.parseColor("#F2F2F6");
        this.mActiveCalorieSituationStage = StageInfoEnum.NODATA;
        this.mActiveCalorieSituationCompareState = FLAT;
        this.mActiveTimeSituationStage = StageInfoEnum.NODATA;
        this.mActiveTimeSituationCompareState = FLAT;
        this.mWalkSitutionStage = StageInfoEnum.NODATA;
        this.mWalkSituationCompareState = FLAT;
        this.mShowWearDuration = true;
        this.mNormalRoundAngle = 0.5f;
        this.mWeekRoundAngle = 0.2f;
        this.mBmr = FitnessHelperKt.caluteBMR(getUserId());
        DataDownLoadService.INSTANCE.addTaskExecutedCallback(this);
        this.mICallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.home.fitness.FitnessDetailPresenter$mICallBack$1
            @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetSportThreeCircleGoal(CalorieAndDistanceGoal calorieAndDistanceGoal, String s) {
                super.onGetSportThreeCircleGoal(calorieAndDistanceGoal, s);
                HomeHelperKt.printAndSave("onGetSportThreeCircleGoal: " + calorieAndDistanceGoal, this.this$0.getTAG());
                UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(this.this$0.getUserId());
                if (calorieAndDistanceGoal == null || userTargetNewQueryUserLatestTarget == null) {
                    return;
                }
                userTargetNewQueryUserLatestTarget.setCalories(calorieAndDistanceGoal.calorie);
                userTargetNewQueryUserLatestTarget.setActivityTime((int) calorieAndDistanceGoal.mid_high_time_goal);
                userTargetNewQueryUserLatestTarget.setWalk(calorieAndDistanceGoal.walk_goal_time * 3600);
                GreenDaoUtil.addUserTarget(userTargetNewQueryUserLatestTarget);
                this.this$0.updateTarget();
            }
        };
    }

    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailPresenter$Companion;", "", "()V", "DOWN", "", "DOWN$annotations", "getDOWN", "()I", "FLAT", "FLAT$annotations", "getFLAT", "UP", "UP$annotations", "getUP", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void DOWN$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void FLAT$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void UP$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getUP() {
            return FitnessDetailPresenter.UP;
        }

        public final int getDOWN() {
            return FitnessDetailPresenter.DOWN;
        }

        public final int getFLAT() {
            return FitnessDetailPresenter.FLAT;
        }
    }

    public final int getMActivityCalorieAvg() {
        return this.mActivityCalorieAvg;
    }

    public final void setMActivityCalorieAvg(int i) {
        this.mActivityCalorieAvg = i;
    }

    public final int getMActivityTimeAvg() {
        return this.mActivityTimeAvg;
    }

    public final void setMActivityTimeAvg(int i) {
        this.mActivityTimeAvg = i;
    }

    public final int getMWalkAvg() {
        return this.mWalkAvg;
    }

    public final void setMWalkAvg(int i) {
        this.mWalkAvg = i;
    }

    public final int getMAllDayActivityCalorie() {
        return this.mAllDayActivityCalorie;
    }

    public final void setMAllDayActivityCalorie(int i) {
        this.mAllDayActivityCalorie = i;
    }

    public final int getMValidWearDuration() {
        return this.mValidWearDuration;
    }

    public final void setMValidWearDuration(int i) {
        this.mValidWearDuration = i;
    }

    public final int getMSedentary() {
        return this.mSedentary;
    }

    public final void setMSedentary(int i) {
        this.mSedentary = i;
    }

    public final int getMActiveCalorieTargetDayCount() {
        return this.mActiveCalorieTargetDayCount;
    }

    public final void setMActiveCalorieTargetDayCount(int i) {
        this.mActiveCalorieTargetDayCount = i;
    }

    public final int getMActiveCalorieHasDataDayCount() {
        return this.mActiveCalorieHasDataDayCount;
    }

    public final void setMActiveCalorieHasDataDayCount(int i) {
        this.mActiveCalorieHasDataDayCount = i;
    }

    public final int getMActiveTimeTargetDayCount() {
        return this.mActiveTimeTargetDayCount;
    }

    public final void setMActiveTimeTargetDayCount(int i) {
        this.mActiveTimeTargetDayCount = i;
    }

    public final int getMActiveTimeHasDataDayCount() {
        return this.mActiveTimeHasDataDayCount;
    }

    public final void setMActiveTimeHasDataDayCount(int i) {
        this.mActiveTimeHasDataDayCount = i;
    }

    public final int getMActiveTimeTotalTarget() {
        return this.mActiveTimeTotalTarget;
    }

    public final void setMActiveTimeTotalTarget(int i) {
        this.mActiveTimeTotalTarget = i;
    }

    public final int getMWalkTargetDayCount() {
        return this.mWalkTargetDayCount;
    }

    public final void setMWalkTargetDayCount(int i) {
        this.mWalkTargetDayCount = i;
    }

    public final int getMWalkHasDataDayCount() {
        return this.mWalkHasDataDayCount;
    }

    public final void setMWalkHasDataDayCount(int i) {
        this.mWalkHasDataDayCount = i;
    }

    public final int getMActiveTimeTargetTime() {
        return this.mActiveTimeTargetTime;
    }

    public final void setMActiveTimeTargetTime(int i) {
        this.mActiveTimeTargetTime = i;
    }

    public final int getMActiveTimeTotalDuration() {
        return this.mActiveTimeTotalDuration;
    }

    public final void setMActiveTimeTotalDuration(int i) {
        this.mActiveTimeTotalDuration = i;
    }

    public final boolean getMShowWearDuration() {
        return this.mShowWearDuration;
    }

    public final void setMShowWearDuration(boolean z) {
        this.mShowWearDuration = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f6 A[PHI: r7
  0x00f6: PHI (r7v19 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>) = 
  (r7v2 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r7v2 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r7v22 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
 binds: [B:30:0x00d4, B:35:0x00f2, B:28:0x00b1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01ba A[PHI: r1
  0x01ba: PHI (r1v131 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>) = 
  (r1v79 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r1v79 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r1v77 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
 binds: [B:65:0x0196, B:70:0x01b5, B:62:0x0172] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getDetailData() {
        /*
            Method dump skipped, instruction units count: 1664
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.getDetailData():void");
    }

    private final void resetCache() {
        this.mActiveTimeSituationStage = StageInfoEnum.NODATA;
        this.mActiveTimeSituationAvg = 0.0f;
        this.mActiveTimeSituationCompareState = FLAT;
        this.mActiveCalorieSituationStage = StageInfoEnum.NODATA;
        this.mActiveCalorieSituationAvg = 0.0f;
        this.mActiveCalorieSituationCompareState = FLAT;
        this.mWalkSitutionStage = StageInfoEnum.NODATA;
        this.mWalkSituationAvg = 0.0f;
        this.mWalkSituationCompareState = FLAT;
        this.mActivityCalorieAvg = 0;
        this.mAllDayActivityCalorie = 0;
        this.mActivityTimeAvg = 0;
        this.mWalkAvg = 0;
        this.mActiveCalorieTargetDayCount = 0;
        this.mActiveCalorieHasDataDayCount = 0;
        this.mActiveTimeTargetTime = 0;
        this.mActiveTimeTargetDayCount = 0;
        this.mActiveTimeHasDataDayCount = 0;
        this.mWalkTargetDayCount = 0;
        this.mWalkHasDataDayCount = 0;
        this.mShowWearDuration = true;
        this.mActiveTimeTotalDuration = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object refreshBottom(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.refreshBottom(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$refreshBottom$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$refreshBottom$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03542 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03542(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03542 c03542 = FitnessDetailPresenter.this.new C03542(completion);
            c03542.p$ = (CoroutineScope) obj;
            return c03542;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03542) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateRecentStage(FitnessDetailPresenter.this.getComplexStage());
            FitnessDetailPresenter.this.getMView().updateActivityCalorieProgressMaxmin(FitnessDetailPresenter.this.mActivityCalorieProgressMax, FitnessDetailPresenter.this.mActivityCalorieProgressMin, Math.max(0, MathKt.roundToInt(FitnessDetailPresenter.this.mActiveCalorieSituationAvg)));
            FitnessDetailPresenter.this.getMView().updateActivityTimeProgressMaxmin(FitnessDetailPresenter.this.mActivityTimeProgressMax, FitnessDetailPresenter.this.mActivityTimeProgressMin, Math.max(0, MathKt.roundToInt(FitnessDetailPresenter.this.mActiveTimeSituationAvg)));
            FitnessDetailPresenter.this.getMView().updateWalkProgressMaxmin(FitnessDetailPresenter.this.mWalkProgressMax, FitnessDetailPresenter.this.mWalkProgressMin, Math.max(0, MathKt.roundToInt(FitnessDetailPresenter.this.mWalkSituationAvg)));
            FitnessDetailPresenter.this.getMView().updateCalorieRecentSituation(FitnessDetailPresenter.this.mActiveCalorieSituationStage, FitnessDetailPresenter.this.mActiveCalorieSituationAvg, FitnessDetailPresenter.this.mActiveCalorieSituationCompareState);
            FitnessDetailPresenter.this.getMView().updateActiveRecentSituation(FitnessDetailPresenter.this.mActiveTimeSituationStage, FitnessDetailPresenter.this.mActiveTimeSituationAvg, FitnessDetailPresenter.this.mActiveTimeSituationCompareState);
            FitnessDetailPresenter.this.getMView().updateWalkRecentSituation(FitnessDetailPresenter.this.mWalkSitutionStage, FitnessDetailPresenter.this.mWalkSituationAvg, FitnessDetailPresenter.this.mWalkSituationCompareState);
            FitnessDetailPresenter.this.updateTarget();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StageInfoEnum getComplexStage() {
        StageInfoEnum stageInfoEnum = this.mActiveCalorieSituationStage;
        if (this.mActiveTimeSituationStage.ordinal() > stageInfoEnum.ordinal()) {
            stageInfoEnum = this.mActiveTimeSituationStage;
        }
        return this.mWalkSitutionStage.ordinal() > stageInfoEnum.ordinal() ? this.mWalkSitutionStage : stageInfoEnum;
    }

    private final int ajustCalorieMin(int calorie) {
        return calorie % 50 != 0 ? (calorie / 50) * 50 : calorie;
    }

    private final int ajustCalorieMax(int calorie) {
        return calorie % 50 != 0 ? ((calorie / 50) + 1) * 50 : calorie;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTarget() {
        UserTargetNew userTarget = getUserTarget();
        int calories = userTarget.getCalories();
        int activityTime = userTarget.getActivityTime() >= 60 ? userTarget.getActivityTime() / 60 : 30;
        int walk = userTarget.getWalk() >= 3600 ? userTarget.getWalk() / 3600 : 12;
        getMView().updateActivityCalorieTarget(calories);
        getMView().updateActivityTimeTarget(activityTime);
        getMView().updateWalkTarget(walk);
    }

    private final boolean hasActiveTimeData(String date) {
        return GreenDaoUtil.hasActiveTimeDayData(getUserId(), date);
    }

    private final boolean hasCalorieData(String date) {
        return GreenDaoUtil.hasCalorieDayData(getUserId(), date);
    }

    private final boolean hasWalkData(String date) {
        return GreenDaoUtil.hasWalkData(getUserId(), date);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalDayData(boolean r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.ido.life.module.home.fitness.FitnessDetailPresenter.C03171
            if (r0 == 0) goto L14
            r0 = r12
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$1 r0 = (com.ido.life.module.home.fitness.FitnessDetailPresenter.C03171) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$1 r0 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L60
            if (r2 == r6) goto L56
            if (r2 == r5) goto L4c
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            boolean r11 = r0.Z$0
            java.lang.Object r11 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r11 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lbc
        L3a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L42:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L8b
        L4c:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L7e
        L56:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L71
        L60:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r10
            r0.Z$0 = r11
            r0.label = r6
            java.lang.Object r12 = r10.readDayCalorieFromLocal(r11, r0)
            if (r12 != r1) goto L70
            return r1
        L70:
            r2 = r10
        L71:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r5
            java.lang.Object r12 = r2.readDayActiveTimeFromLocal(r11, r0)
            if (r12 != r1) goto L7e
            return r1
        L7e:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r4
            java.lang.Object r12 = r2.readDayWalkFromLocal(r11, r0)
            if (r12 != r1) goto L8b
            return r1
        L8b:
            kotlinx.coroutines.GlobalScope r12 = kotlinx.coroutines.GlobalScope.INSTANCE
            r4 = r12
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlinx.coroutines.MainCoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getMain()
            r5 = r12
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            r6 = 0
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$2 r12 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$2
            r7 = 0
            r12.<init>(r7)
            r7 = r12
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 2
            r9 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
            r2.caluteActiveTimeSituation()
            r2.caluteActiveCalorieSituation()
            r2.calculateWalkSituation()
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r3
            java.lang.Object r11 = r2.refreshBottom(r0)
            if (r11 != r1) goto Lbc
            return r1
        Lbc:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readLocalDayData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalDayData$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03182 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03182(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03182 c03182 = FitnessDetailPresenter.this.new C03182(completion);
            c03182.p$ = (CoroutineScope) obj;
            return c03182;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03182) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().refreshImageFitness();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalWeekData(boolean r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.ido.life.module.home.fitness.FitnessDetailPresenter.C03211
            if (r0 == 0) goto L14
            r0 = r12
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$1 r0 = (com.ido.life.module.home.fitness.FitnessDetailPresenter.C03211) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$1 r0 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L60
            if (r2 == r6) goto L56
            if (r2 == r5) goto L4c
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            boolean r11 = r0.Z$0
            java.lang.Object r11 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r11 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lbc
        L3a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L42:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L8b
        L4c:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L7e
        L56:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L71
        L60:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r10
            r0.Z$0 = r11
            r0.label = r6
            java.lang.Object r12 = r10.readWeekCalorieFromLocal(r11, r0)
            if (r12 != r1) goto L70
            return r1
        L70:
            r2 = r10
        L71:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r5
            java.lang.Object r12 = r2.readWeekActiveTimeFromLocal(r11, r0)
            if (r12 != r1) goto L7e
            return r1
        L7e:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r4
            java.lang.Object r12 = r2.readWeekWalkFromLocal(r11, r0)
            if (r12 != r1) goto L8b
            return r1
        L8b:
            kotlinx.coroutines.GlobalScope r12 = kotlinx.coroutines.GlobalScope.INSTANCE
            r4 = r12
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlinx.coroutines.MainCoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getMain()
            r5 = r12
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            r6 = 0
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$2 r12 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$2
            r7 = 0
            r12.<init>(r7)
            r7 = r12
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 2
            r9 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
            r2.caluteActiveTimeSituation()
            r2.caluteActiveCalorieSituation()
            r2.calculateWalkSituation()
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r3
            java.lang.Object r11 = r2.refreshBottom(r0)
            if (r11 != r1) goto Lbc
            return r1
        Lbc:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readLocalWeekData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalWeekData$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03222 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03222(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03222 c03222 = FitnessDetailPresenter.this.new C03222(completion);
            c03222.p$ = (CoroutineScope) obj;
            return c03222;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03222) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().refreshImageFitness();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalMonthData(boolean r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.ido.life.module.home.fitness.FitnessDetailPresenter.C03191
            if (r0 == 0) goto L14
            r0 = r12
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$1 r0 = (com.ido.life.module.home.fitness.FitnessDetailPresenter.C03191) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$1 r0 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L60
            if (r2 == r6) goto L56
            if (r2 == r5) goto L4c
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            boolean r11 = r0.Z$0
            java.lang.Object r11 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r11 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lbc
        L3a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L42:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L8b
        L4c:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L7e
        L56:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L71
        L60:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r10
            r0.Z$0 = r11
            r0.label = r6
            java.lang.Object r12 = r10.readMonthCalorieFromLocal(r11, r0)
            if (r12 != r1) goto L70
            return r1
        L70:
            r2 = r10
        L71:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r5
            java.lang.Object r12 = r2.readMonthActiveTimeFromLocal(r11, r0)
            if (r12 != r1) goto L7e
            return r1
        L7e:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r4
            java.lang.Object r12 = r2.readMonthWalkFromLocal(r11, r0)
            if (r12 != r1) goto L8b
            return r1
        L8b:
            kotlinx.coroutines.GlobalScope r12 = kotlinx.coroutines.GlobalScope.INSTANCE
            r4 = r12
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlinx.coroutines.MainCoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getMain()
            r5 = r12
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            r6 = 0
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$2 r12 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$2
            r7 = 0
            r12.<init>(r7)
            r7 = r12
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 2
            r9 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
            r2.caluteActiveTimeSituation()
            r2.caluteActiveCalorieSituation()
            r2.calculateWalkSituation()
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r3
            java.lang.Object r11 = r2.refreshBottom(r0)
            if (r11 != r1) goto Lbc
            return r1
        Lbc:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readLocalMonthData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalMonthData$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03202 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03202(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03202 c03202 = FitnessDetailPresenter.this.new C03202(completion);
            c03202.p$ = (CoroutineScope) obj;
            return c03202;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03202) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().refreshImageFitness();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalYearData(boolean r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.ido.life.module.home.fitness.FitnessDetailPresenter.C03231
            if (r0 == 0) goto L14
            r0 = r12
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$1 r0 = (com.ido.life.module.home.fitness.FitnessDetailPresenter.C03231) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$1 r0 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L60
            if (r2 == r6) goto L56
            if (r2 == r5) goto L4c
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            boolean r11 = r0.Z$0
            java.lang.Object r11 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r11 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lbc
        L3a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L42:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L8b
        L4c:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L7e
        L56:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            com.ido.life.module.home.fitness.FitnessDetailPresenter r2 = (com.ido.life.module.home.fitness.FitnessDetailPresenter) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L71
        L60:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r10
            r0.Z$0 = r11
            r0.label = r6
            java.lang.Object r12 = r10.readYearCalorieFromLocal(r11, r0)
            if (r12 != r1) goto L70
            return r1
        L70:
            r2 = r10
        L71:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r5
            java.lang.Object r12 = r2.readYearActiveTimeFromLocal(r11, r0)
            if (r12 != r1) goto L7e
            return r1
        L7e:
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r4
            java.lang.Object r12 = r2.readYearWalkFromLocal(r11, r0)
            if (r12 != r1) goto L8b
            return r1
        L8b:
            kotlinx.coroutines.GlobalScope r12 = kotlinx.coroutines.GlobalScope.INSTANCE
            r4 = r12
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlinx.coroutines.MainCoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getMain()
            r5 = r12
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            r6 = 0
            com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$2 r12 = new com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$2
            r7 = 0
            r12.<init>(r7)
            r7 = r12
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 2
            r9 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
            r2.caluteActiveTimeSituation()
            r2.caluteActiveCalorieSituation()
            r2.calculateWalkSituation()
            r0.L$0 = r2
            r0.Z$0 = r11
            r0.label = r3
            java.lang.Object r11 = r2.refreshBottom(r0)
            if (r11 != r1) goto Lbc
            return r1
        Lbc:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readLocalYearData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readLocalYearData$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03242 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03242(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03242 c03242 = FitnessDetailPresenter.this.new C03242(completion);
            c03242.p$ = (CoroutineScope) obj;
            return c03242;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03242) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().refreshImageFitness();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r1v29, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readDayCalorieFromLocal(boolean r41, kotlin.coroutines.Continuation<? super kotlin.Unit> r42) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 957
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readDayCalorieFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayCalorieFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayCalorieFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03132 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03132(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03132 c03132 = FitnessDetailPresenter.this.new C03132(completion);
            c03132.p$ = (CoroutineScope) obj;
            return c03132;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03132) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveCalorieDayDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            long userId = FitnessDetailPresenter.this.getUserId();
            String mStartDate = FitnessDetailPresenter.this.getMStartDate();
            if (mStartDate == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(0, FitnessHelperKt.getUserTarget(userId, mStartDate).getCalories());
            FitnessDetailPresenter.this.getMView().updateCaloriePer(0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayCalorieFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayCalorieFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03143 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03143(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03143 c03143 = FitnessDetailPresenter.this.new C03143(completion);
            c03143.p$ = (CoroutineScope) obj;
            return c03143;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03143) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveCalorieDayDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            long userId = FitnessDetailPresenter.this.getUserId();
            String mStartDate = FitnessDetailPresenter.this.getMStartDate();
            if (mStartDate == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(0, FitnessHelperKt.getUserTarget(userId, mStartDate).getCalories());
            FitnessDetailPresenter.this.getMView().updateCaloriePer(0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayCalorieFromLocal$4, reason: invalid class name */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayCalorieFromLocal$4", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $calorieTarget;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ boolean $showChartAnimator;
        final /* synthetic */ Ref.IntRef $totalActicityCalorie;
        final /* synthetic */ Ref.IntRef $totalCalorie;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.ObjectRef objectRef, boolean z, Ref.IntRef intRef4, Continuation continuation) {
            super(2, continuation);
            this.$totalActicityCalorie = intRef;
            this.$totalCalorie = intRef2;
            this.$yMax = intRef3;
            this.$result = objectRef;
            this.$showChartAnimator = z;
            this.$calorieTarget = intRef4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass4 anonymousClass4 = FitnessDetailPresenter.this.new AnonymousClass4(this.$totalActicityCalorie, this.$totalCalorie, this.$yMax, this.$result, this.$showChartAnimator, this.$calorieTarget, completion);
            anonymousClass4.p$ = (CoroutineScope) obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMActivityCalorieAvg(this.$totalActicityCalorie.element);
            FitnessDetailPresenter.this.setMAllDayActivityCalorie(this.$totalCalorie.element);
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveCalorieDayDataLoadSuccess((List) this.$result.element, this.$showChartAnimator);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(this.$totalActicityCalorie.element, this.$calorieTarget.element);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(this.$totalActicityCalorie.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r1v18, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readWeekCalorieFromLocal(boolean r44, kotlin.coroutines.Continuation<? super kotlin.Unit> r45) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readWeekCalorieFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekCalorieFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekCalorieFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03382 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03382(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03382 c03382 = FitnessDetailPresenter.this.new C03382(completion);
            c03382.p$ = (CoroutineScope) obj;
            return c03382;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03382) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveCalorieWeekDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mWeekRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(0);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(0, 0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekCalorieFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekCalorieFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03393 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03393(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03393 c03393 = FitnessDetailPresenter.this.new C03393(completion);
            c03393.p$ = (CoroutineScope) obj;
            return c03393;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03393) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveCalorieWeekDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mWeekRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(0);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(0, 0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekCalorieFromLocal$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekCalorieFromLocal$4", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03404 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $avgActivityCalorie;
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ boolean $showChartAnimator;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03404(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, boolean z, Continuation continuation) {
            super(2, continuation);
            this.$avgActivityCalorie = intRef;
            this.$hasDataDayCount = intRef2;
            this.$hasTargetDayCount = intRef3;
            this.$yMax = intRef4;
            this.$result = objectRef;
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03404 c03404 = FitnessDetailPresenter.this.new C03404(this.$avgActivityCalorie, this.$hasDataDayCount, this.$hasTargetDayCount, this.$yMax, this.$result, this.$showChartAnimator, completion);
            c03404.p$ = (CoroutineScope) obj;
            return c03404;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03404) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMActivityCalorieAvg(this.$avgActivityCalorie.element);
            FitnessDetailPresenter.this.setMActiveCalorieHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.setMActiveCalorieTargetDayCount(this.$hasTargetDayCount.element);
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveCalorieWeekDataLoadSuccess((List) this.$result.element, this.$showChartAnimator);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(this.$avgActivityCalorie.element);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(this.$hasTargetDayCount.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v23, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v35, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readMonthCalorieFromLocal(boolean r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1093
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readMonthCalorieFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthCalorieFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthCalorieFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03292 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03292(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03292 c03292 = FitnessDetailPresenter.this.new C03292(completion);
            c03292.p$ = (CoroutineScope) obj;
            return c03292;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03292) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveCalorieMonthDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(0);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(0, 0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthCalorieFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthCalorieFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03303 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $avgActivityCalorie;
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03303(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$avgActivityCalorie = intRef;
            this.$hasTargetDayCount = intRef2;
            this.$hasDataDayCount = intRef3;
            this.$yMax = intRef4;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03303 c03303 = FitnessDetailPresenter.this.new C03303(this.$avgActivityCalorie, this.$hasTargetDayCount, this.$hasDataDayCount, this.$yMax, this.$result, this.$showAnimator, completion);
            c03303.p$ = (CoroutineScope) obj;
            return c03303;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03303) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMActivityCalorieAvg(this.$avgActivityCalorie.element);
            FitnessDetailPresenter.this.setMActiveCalorieTargetDayCount(this.$hasTargetDayCount.element);
            FitnessDetailPresenter.this.setMActiveCalorieHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveCalorieMonthDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(this.$avgActivityCalorie.element);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(this.$hasTargetDayCount.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /* JADX WARN: Type inference failed for: r0v18, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v9, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readYearCalorieFromLocal(boolean r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1021
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readYearCalorieFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearCalorieFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearCalorieFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03482 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03482(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03482 c03482 = FitnessDetailPresenter.this.new C03482(completion);
            c03482.p$ = (CoroutineScope) obj;
            return c03482;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03482) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveCalorieYearDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(0);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(0, 0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearCalorieFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearCalorieFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03493 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $avgActivityCalorie;
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03493(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$avgActivityCalorie = intRef;
            this.$hasTargetDayCount = intRef2;
            this.$hasDataDayCount = intRef3;
            this.$yMax = intRef4;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03493 c03493 = FitnessDetailPresenter.this.new C03493(this.$avgActivityCalorie, this.$hasTargetDayCount, this.$hasDataDayCount, this.$yMax, this.$result, this.$showAnimator, completion);
            c03493.p$ = (CoroutineScope) obj;
            return c03493;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03493) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMActivityCalorieAvg(this.$avgActivityCalorie.element);
            FitnessDetailPresenter.this.setMActiveCalorieTargetDayCount(this.$hasTargetDayCount.element);
            FitnessDetailPresenter.this.setMActiveCalorieHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.getMView().updateActiveCalorieYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveCalorieYearDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateCaloriePer(this.$avgActivityCalorie.element);
            FitnessDetailPresenter.this.getMView().updateLeftActivityCalorie(this.$hasTargetDayCount.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b4  */
    /* JADX WARN: Type inference failed for: r0v20, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v41, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r15v9, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v5 */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readDayActiveTimeFromLocal(boolean r29, kotlin.coroutines.Continuation<? super kotlin.Unit> r30) {
        /*
            Method dump skipped, instruction units count: 904
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readDayActiveTimeFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayActiveTimeFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayActiveTimeFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03102 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03102(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03102 c03102 = FitnessDetailPresenter.this.new C03102(completion);
            c03102.p$ = (CoroutineScope) obj;
            return c03102;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03102) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveTimeDayDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateActivePer(0);
            long userId = FitnessDetailPresenter.this.getUserId();
            String mStartDate = FitnessDetailPresenter.this.getMStartDate();
            if (mStartDate == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(0, FitnessHelperKt.getUserTarget(userId, mStartDate).getActivityTime() / 60);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayActiveTimeFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayActiveTimeFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03113 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $activeTimeTotal;
        final /* synthetic */ Ref.ObjectRef $chartList;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.BooleanRef $showWearDuration;
        final /* synthetic */ Ref.IntRef $timeTarget;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03113(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef2, Ref.IntRef intRef2, Ref.IntRef intRef3, Continuation continuation) {
            super(2, continuation);
            this.$showWearDuration = booleanRef;
            this.$yMax = intRef;
            this.$chartList = objectRef;
            this.$showAnimator = booleanRef2;
            this.$activeTimeTotal = intRef2;
            this.$timeTarget = intRef3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03113 c03113 = FitnessDetailPresenter.this.new C03113(this.$showWearDuration, this.$yMax, this.$chartList, this.$showAnimator, this.$activeTimeTotal, this.$timeTarget, completion);
            c03113.p$ = (CoroutineScope) obj;
            return c03113;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03113) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMShowWearDuration(this.$showWearDuration.element);
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveTimeDayDataLoadSuccess((List) this.$chartList.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateActivePer(this.$activeTimeTotal.element);
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(this.$activeTimeTotal.element, this.$timeTarget.element / 60);
            FitnessDetailPresenter.this.getMView().showWearTime(this.$showWearDuration.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v20, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v27, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readWeekActiveTimeFromLocal(boolean r39, kotlin.coroutines.Continuation<? super kotlin.Unit> r40) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readWeekActiveTimeFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekActiveTimeFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekActiveTimeFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03352 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03352(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03352 c03352 = FitnessDetailPresenter.this.new C03352(completion);
            c03352.p$ = (CoroutineScope) obj;
            return c03352;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03352) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveTimeWeekDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mWeekRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateActivePer(0);
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(0, 0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekActiveTimeFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekActiveTimeFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03363 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $activeTimeTotal;
        final /* synthetic */ Ref.IntRef $activetimeAvg;
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $totalTimeTarget;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03363(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.IntRef intRef5, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$activetimeAvg = intRef;
            this.$activeTimeTotal = intRef2;
            this.$hasDataDayCount = intRef3;
            this.$totalTimeTarget = intRef4;
            this.$yMax = intRef5;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03363 c03363 = FitnessDetailPresenter.this.new C03363(this.$activetimeAvg, this.$activeTimeTotal, this.$hasDataDayCount, this.$totalTimeTarget, this.$yMax, this.$result, this.$showAnimator, completion);
            c03363.p$ = (CoroutineScope) obj;
            return c03363;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03363) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMActivityTimeAvg(this.$activetimeAvg.element);
            FitnessDetailPresenter.this.setMActiveTimeTotalDuration(this.$activeTimeTotal.element);
            FitnessDetailPresenter.this.setMActiveTimeHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.setMActiveTimeTotalTarget(this.$totalTimeTarget.element);
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveTimeWeekDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateActivePer(this.$activetimeAvg.element);
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(this.$activeTimeTotal.element, this.$totalTimeTarget.element / 60);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x022f  */
    /* JADX WARN: Type inference failed for: r0v18, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v24, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readMonthActiveTimeFromLocal(boolean r39, kotlin.coroutines.Continuation<? super kotlin.Unit> r40) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1071
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readMonthActiveTimeFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthActiveTimeFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthActiveTimeFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03262 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03262(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03262 c03262 = FitnessDetailPresenter.this.new C03262(completion);
            c03262.p$ = (CoroutineScope) obj;
            return c03262;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03262) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveTimeMonthDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateActivePer(0);
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(0, 0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthActiveTimeFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthActiveTimeFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03273 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $activetimeAvg;
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03273(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$activetimeAvg = intRef;
            this.$hasTargetDayCount = intRef2;
            this.$hasDataDayCount = intRef3;
            this.$yMax = intRef4;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03273 c03273 = FitnessDetailPresenter.this.new C03273(this.$activetimeAvg, this.$hasTargetDayCount, this.$hasDataDayCount, this.$yMax, this.$result, this.$showAnimator, completion);
            c03273.p$ = (CoroutineScope) obj;
            return c03273;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03273) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMActivityTimeAvg(this.$activetimeAvg.element);
            FitnessDetailPresenter.this.setMActiveTimeTargetDayCount(this.$hasTargetDayCount.element);
            FitnessDetailPresenter.this.setMActiveTimeHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveTimeMonthDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateActivePer(this.$activetimeAvg.element);
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(this.$hasTargetDayCount.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v18, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v25, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readYearActiveTimeFromLocal(boolean r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readYearActiveTimeFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearActiveTimeFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearActiveTimeFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03452 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03452(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03452 c03452 = FitnessDetailPresenter.this.new C03452(completion);
            c03452.p$ = (CoroutineScope) obj;
            return c03452;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03452) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(4, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onActiveTimeYearDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 4 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateActivePer(0);
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(0, 0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearActiveTimeFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearActiveTimeFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03463 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $activetimeAvg;
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCout;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $yMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03463(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$activetimeAvg = intRef;
            this.$hasDataDayCount = intRef2;
            this.$hasTargetDayCout = intRef3;
            this.$yMax = intRef4;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03463 c03463 = FitnessDetailPresenter.this.new C03463(this.$activetimeAvg, this.$hasDataDayCount, this.$hasTargetDayCout, this.$yMax, this.$result, this.$showAnimator, completion);
            c03463.p$ = (CoroutineScope) obj;
            return c03463;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03463) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMActivityTimeAvg(this.$activetimeAvg.element);
            FitnessDetailPresenter.this.setMActiveTimeHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.setMActiveTimeTargetDayCount(this.$hasTargetDayCout.element);
            FitnessDetailPresenter.this.getMView().updateActiveTimeYMaxmin(this.$yMax.element, 0);
            FitnessDetailPresenter.this.getMView().onActiveTimeYearDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateActivePer(this.$activetimeAvg.element);
            FitnessDetailPresenter.this.getMView().updateLeftActiveTime(this.$hasTargetDayCout.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0163  */
    /* JADX WARN: Type inference failed for: r0v17, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readDayWalkFromLocal(boolean r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            Method dump skipped, instruction units count: 691
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readDayWalkFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayWalkFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayWalkFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03152(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03152 c03152 = FitnessDetailPresenter.this.new C03152(completion);
            c03152.p$ = (CoroutineScope) obj;
            return c03152;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03152) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(1, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onWalkDayDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            FitnessDetailPresenter.this.getMView().updateWalkPer(0);
            long userId = FitnessDetailPresenter.this.getUserId();
            String mStartDate = FitnessDetailPresenter.this.getMStartDate();
            if (mStartDate == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailPresenter.this.getMView().updateLeftWalk(0, FitnessHelperKt.getUserTarget(userId, mStartDate).getWalk() / 3600);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayWalkFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readDayWalkFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03163 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $walkTarget;
        final /* synthetic */ Ref.IntRef $walkTotal;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03163(Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Ref.IntRef intRef2, Continuation continuation) {
            super(2, continuation);
            this.$walkTotal = intRef;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
            this.$walkTarget = intRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03163 c03163 = FitnessDetailPresenter.this.new C03163(this.$walkTotal, this.$result, this.$showAnimator, this.$walkTarget, completion);
            c03163.p$ = (CoroutineScope) obj;
            return c03163;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03163) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMWalkAvg(this.$walkTotal.element);
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(1, 0);
            FitnessDetailPresenter.this.getMView().onWalkDayDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateWalkPer(this.$walkTotal.element);
            FitnessDetailPresenter.this.getMView().updateLeftWalk(this.$walkTotal.element, this.$walkTarget.element / 3600);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v15, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readWeekWalkFromLocal(boolean r36, kotlin.coroutines.Continuation<? super kotlin.Unit> r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 968
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readWeekWalkFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekWalkFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekWalkFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03422 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03422(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03422 c03422 = FitnessDetailPresenter.this.new C03422(completion);
            c03422.p$ = (CoroutineScope) obj;
            return c03422;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03422) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            long userId = FitnessDetailPresenter.this.getUserId();
            String mEndDate = FitnessDetailPresenter.this.getMEndDate();
            if (mEndDate == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(FitnessHelperKt.getUserTarget(userId, mEndDate).getWalk() / 3600, 0);
            FitnessDetailPresenter.this.getMView().updateWalkPer(0);
            FitnessDetailPresenter.this.getMView().updateLeftWalk(0, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onWalkWeekDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), (r6.getWalk() / 3600) * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mWeekRoundAngle), false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekWalkFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readWeekWalkFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03433 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $walkAvg;
        final /* synthetic */ Ref.IntRef $walkMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03433(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$walkAvg = intRef;
            this.$hasTargetDayCount = intRef2;
            this.$hasDataDayCount = intRef3;
            this.$walkMax = intRef4;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03433 c03433 = FitnessDetailPresenter.this.new C03433(this.$walkAvg, this.$hasTargetDayCount, this.$hasDataDayCount, this.$walkMax, this.$result, this.$showAnimator, completion);
            c03433.p$ = (CoroutineScope) obj;
            return c03433;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03433) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMWalkAvg(this.$walkAvg.element);
            FitnessDetailPresenter.this.setMWalkTargetDayCount(this.$hasTargetDayCount.element);
            FitnessDetailPresenter.this.setMWalkHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(this.$walkMax.element, 0);
            FitnessDetailPresenter.this.getMView().onWalkWeekDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateWalkPer(this.$walkAvg.element);
            FitnessDetailPresenter.this.getMView().updateLeftWalk(this.$hasTargetDayCount.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v22, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v30, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readMonthWalkFromLocal(boolean r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1106
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readMonthWalkFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthWalkFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthWalkFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03322 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $dayCount;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03322(Ref.IntRef intRef, Continuation continuation) {
            super(2, continuation);
            this.$dayCount = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03322 c03322 = FitnessDetailPresenter.this.new C03322(this.$dayCount, completion);
            c03322.p$ = (CoroutineScope) obj;
            return c03322;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03322) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            long userId = FitnessDetailPresenter.this.getUserId();
            String mEndDate = FitnessDetailPresenter.this.getMEndDate();
            if (mEndDate == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(FitnessHelperKt.getUserTarget(userId, mEndDate).getWalk() / 3600, 0);
            FitnessDetailPresenter.this.getMView().updateWalkPer(0);
            FitnessDetailPresenter.this.getMView().updateLeftWalk(0, 0);
            FitnessDetailPresenter.this.getMView().onWalkMonthDataLoadSuccess(FitnessDetailPresenter.this.generateDefaultChartData(this.$dayCount.element, (r6.getWalk() / 3600) * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthWalkFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readMonthWalkFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03333 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ Ref.BooleanRef $showAnimator;
        final /* synthetic */ Ref.IntRef $walkAvg;
        final /* synthetic */ Ref.IntRef $walkMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03333(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$walkAvg = intRef;
            this.$hasDataDayCount = intRef2;
            this.$hasTargetDayCount = intRef3;
            this.$walkMax = intRef4;
            this.$result = objectRef;
            this.$showAnimator = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03333 c03333 = FitnessDetailPresenter.this.new C03333(this.$walkAvg, this.$hasDataDayCount, this.$hasTargetDayCount, this.$walkMax, this.$result, this.$showAnimator, completion);
            c03333.p$ = (CoroutineScope) obj;
            return c03333;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03333) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMWalkAvg(this.$walkAvg.element);
            FitnessDetailPresenter.this.setMWalkHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.setMWalkTargetDayCount(this.$hasTargetDayCount.element);
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(this.$walkMax.element, 0);
            FitnessDetailPresenter.this.getMView().onWalkMonthDataLoadSuccess((List) this.$result.element, this.$showAnimator.element);
            FitnessDetailPresenter.this.getMView().updateWalkPer(this.$walkAvg.element);
            FitnessDetailPresenter.this.getMView().updateLeftWalk(this.$hasTargetDayCount.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v19, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object readYearWalkFromLocal(boolean r42, kotlin.coroutines.Continuation<? super kotlin.Unit> r43) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1100
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.readYearWalkFromLocal(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearWalkFromLocal$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearWalkFromLocal$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03512 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03512(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03512 c03512 = FitnessDetailPresenter.this.new C03512(completion);
            c03512.p$ = (CoroutineScope) obj;
            return c03512;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03512) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(12, 0);
            FitnessDetailPresenter.this.getMView().updateWalkPer(0);
            FitnessDetailPresenter.this.getMView().updateLeftWalk(0, 0);
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            FitnessDetailPresenter fitnessDetailPresenter = FitnessDetailPresenter.this;
            mView.onWalkYearDataLoadSuccess(fitnessDetailPresenter.generateDefaultChartData(fitnessDetailPresenter.getDayCount(), 12 * FitnessDetailPresenter.this.mDefaultBarRadius, FitnessDetailPresenter.this.mNormalRoundAngle), false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearWalkFromLocal$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$readYearWalkFromLocal$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03523 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $hasDataDayCount;
        final /* synthetic */ Ref.IntRef $hasTargetDayCount;
        final /* synthetic */ Ref.ObjectRef $result;
        final /* synthetic */ boolean $showChartAnimator;
        final /* synthetic */ Ref.IntRef $walkAvg;
        final /* synthetic */ Ref.IntRef $walkMax;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03523(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.ObjectRef objectRef, boolean z, Continuation continuation) {
            super(2, continuation);
            this.$walkAvg = intRef;
            this.$hasTargetDayCount = intRef2;
            this.$hasDataDayCount = intRef3;
            this.$walkMax = intRef4;
            this.$result = objectRef;
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03523 c03523 = FitnessDetailPresenter.this.new C03523(this.$walkAvg, this.$hasTargetDayCount, this.$hasDataDayCount, this.$walkMax, this.$result, this.$showChartAnimator, completion);
            c03523.p$ = (CoroutineScope) obj;
            return c03523;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03523) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.setMWalkAvg(this.$walkAvg.element);
            FitnessDetailPresenter.this.setMWalkTargetDayCount(this.$hasTargetDayCount.element);
            FitnessDetailPresenter.this.setMWalkHasDataDayCount(this.$hasDataDayCount.element);
            FitnessDetailPresenter.this.getMView().updateWalkYMaxmin(this.$walkMax.element, 0);
            FitnessDetailPresenter.this.getMView().onWalkYearDataLoadSuccess((List) this.$result.element, this.$showChartAnimator);
            FitnessDetailPresenter.this.getMView().updateWalkPer(this.$walkAvg.element);
            FitnessDetailPresenter.this.getMView().updateLeftWalk(this.$hasTargetDayCount.element, this.$hasDataDayCount.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailPresenter$WalkYearBean;", "", "monthCount", "", "monthValue", "monthTotalTarget", "(III)V", "getMonthCount", "()I", "setMonthCount", "(I)V", "getMonthTotalTarget", "setMonthTotalTarget", "getMonthValue", "setMonthValue", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class WalkYearBean {
        private int monthCount;
        private int monthTotalTarget;
        private int monthValue;

        public static /* synthetic */ WalkYearBean copy$default(WalkYearBean walkYearBean, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = walkYearBean.monthCount;
            }
            if ((i4 & 2) != 0) {
                i2 = walkYearBean.monthValue;
            }
            if ((i4 & 4) != 0) {
                i3 = walkYearBean.monthTotalTarget;
            }
            return walkYearBean.copy(i, i2, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMonthCount() {
            return this.monthCount;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMonthValue() {
            return this.monthValue;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getMonthTotalTarget() {
            return this.monthTotalTarget;
        }

        public final WalkYearBean copy(int monthCount, int monthValue, int monthTotalTarget) {
            return new WalkYearBean(monthCount, monthValue, monthTotalTarget);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WalkYearBean)) {
                return false;
            }
            WalkYearBean walkYearBean = (WalkYearBean) other;
            return this.monthCount == walkYearBean.monthCount && this.monthValue == walkYearBean.monthValue && this.monthTotalTarget == walkYearBean.monthTotalTarget;
        }

        public int hashCode() {
            return (((Integer.valueOf(this.monthCount).hashCode() * 31) + Integer.valueOf(this.monthValue).hashCode()) * 31) + Integer.valueOf(this.monthTotalTarget).hashCode();
        }

        public String toString() {
            return "WalkYearBean(monthCount=" + this.monthCount + ", monthValue=" + this.monthValue + ", monthTotalTarget=" + this.monthTotalTarget + ")";
        }

        public WalkYearBean(int i, int i2, int i3) {
            this.monthCount = i;
            this.monthValue = i2;
            this.monthTotalTarget = i3;
        }

        public final int getMonthCount() {
            return this.monthCount;
        }

        public final int getMonthTotalTarget() {
            return this.monthTotalTarget;
        }

        public final int getMonthValue() {
            return this.monthValue;
        }

        public final void setMonthCount(int i) {
            this.monthCount = i;
        }

        public final void setMonthTotalTarget(int i) {
            this.monthTotalTarget = i;
        }

        public final void setMonthValue(int i) {
            this.monthValue = i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c9 A[PHI: r6
  0x00c9: PHI (r6v14 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>) = 
  (r6v2 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r6v2 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r6v17 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
 binds: [B:28:0x00a7, B:33:0x00c5, B:26:0x0084] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0128 A[PHI: r12
  0x0128: PHI (r12v4 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>) = 
  (r12v2 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r12v2 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r12v7 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
 binds: [B:45:0x0106, B:50:0x0124, B:43:0x00e3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x018a A[PHI: r2
  0x018a: PHI (r2v42 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>) = 
  (r2v21 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r2v21 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
  (r2v19 android.util.Pair<java.util.List<java.lang.Long>, java.util.List<java.lang.Long>>)
 binds: [B:62:0x0166, B:67:0x0185, B:59:0x0142] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void requestPullData() {
        /*
            Method dump skipped, instruction units count: 920
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.requestPullData():void");
    }

    private final int caluteYMaxCalorie(int maxCalorie) {
        if (maxCalorie < 4) {
            return 4;
        }
        if (maxCalorie < 10) {
            return 10;
        }
        if (10 <= maxCalorie && 99 >= maxCalorie) {
            return ((maxCalorie / 10) + 1) * 10;
        }
        if (100 <= maxCalorie && 999 >= maxCalorie) {
            return ((maxCalorie / 100) + 1) * 100;
        }
        return ((maxCalorie / 1000) + 1) * 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<GradientBarPoint> generateDefaultChartData(int count, float defaultValue, float roundAnglePercent) {
        ArrayList arrayList = new ArrayList();
        int i = count + 1;
        for (int i2 = 1; i2 < i; i2++) {
            arrayList.add(new GradientBarPoint(-1, i2, defaultValue, 0.0f, this.mDefaultBarColor, roundAnglePercent));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0611 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x026b  */
    /* JADX WARN: Type inference failed for: r1v102, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v19, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v23, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v29, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v34, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v38, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v46, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v51, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v59, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v64, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v71, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v76, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v83, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v88, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v93, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v20, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v28, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v36, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v44, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v10, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v12, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v14, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v47, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v51, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v55, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v93, types: [T, java.lang.Object, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object caluteRecentScore(kotlin.coroutines.Continuation<? super kotlin.Unit> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailPresenter.caluteRecentScore(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$caluteRecentScore$2, reason: invalid class name */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$caluteRecentScore$2", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = FitnessDetailPresenter.this.new AnonymousClass2(completion);
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
            IFitnessDetailDetailView mView = FitnessDetailPresenter.this.getMView();
            String languageText = LanguageUtil.getLanguageText(R.string.fitness_no_score_desc);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ng.fitness_no_score_desc)");
            mView.updateRecentScore(-1, languageText);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.fitness.FitnessDetailPresenter$caluteRecentScore$3, reason: invalid class name */
    /* JADX INFO: compiled from: FitnessDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.fitness.FitnessDetailPresenter$caluteRecentScore$3", f = "FitnessDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $score;
        final /* synthetic */ Ref.ObjectRef $totalDesc;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.IntRef intRef, Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$score = intRef;
            this.$totalDesc = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = FitnessDetailPresenter.this.new AnonymousClass3(this.$score, this.$totalDesc, completion);
            anonymousClass3.p$ = (CoroutineScope) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            FitnessDetailPresenter.this.getMView().updateRecentScore(this.$score.element, (String) this.$totalDesc.element);
            return Unit.INSTANCE;
        }
    }

    private final void caluteActiveCalorieSituation() {
        HomeHelperKt.printAndSave("开始计算活动卡路里阶段", getTAG());
        this.mActiveCalorieSituationStage = StageInfoEnum.PRIMARY;
        this.mActiveCalorieSituationAvg = 0.0f;
        this.mActiveCalorieSituationCompareState = FLAT;
        for (StageInfoEnum stageInfoEnum : FitnessHelperKt.getSiturationStageList()) {
            long userId = getUserId();
            String simpleName = CalorieDayData.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "CalorieDayData::class.java.simpleName");
            if (FitnessHelperKt.recentSatisfyPrimaryStage(userId, simpleName, stageInfoEnum)) {
                this.mActiveCalorieSituationStage = stageInfoEnum;
            }
        }
        Pair<String, String> pairCaluteStageDate = FitnessHelperKt.caluteStageDate(this.mActiveCalorieSituationStage.getMRecentStartDayCount(), this.mActiveCalorieSituationStage.getMRecentEndDayCount() - this.mActiveCalorieSituationStage.getMRecentStartDayCount());
        Pair<String, String> pairCaluteStageDate2 = FitnessHelperKt.caluteStageDate(this.mActiveCalorieSituationStage.getMPastStartDayCount(), this.mActiveCalorieSituationStage.getMPastEndDayCount() - this.mActiveCalorieSituationStage.getMPastStartDayCount());
        this.mActiveCalorieSituationAvg = FitnessHelperKt.calumetRetentivityActiveCalorieAvg(getUserId(), pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond());
        float fCalumetRetentivityActiveCalorieAvg = FitnessHelperKt.calumetRetentivityActiveCalorieAvg(getUserId(), pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond()) - FitnessHelperKt.calumetRetentivityActiveCalorieAvg(getUserId(), pairCaluteStageDate2.getFirst(), pairCaluteStageDate2.getSecond());
        if (fCalumetRetentivityActiveCalorieAvg >= 1.0f) {
            this.mActiveCalorieSituationCompareState = UP;
        } else if (fCalumetRetentivityActiveCalorieAvg <= -1.0f) {
            this.mActiveCalorieSituationCompareState = DOWN;
        }
    }

    private final void caluteActiveTimeSituation() {
        HomeHelperKt.printAndSave("开始计算中高强度阶段", getTAG());
        this.mActiveTimeSituationStage = StageInfoEnum.PRIMARY;
        this.mActiveTimeSituationAvg = 0.0f;
        this.mActiveTimeSituationCompareState = FLAT;
        for (StageInfoEnum stageInfoEnum : FitnessHelperKt.getSiturationStageList()) {
            long userId = getUserId();
            String simpleName = ActiveTimeDayData.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "ActiveTimeDayData::class.java.simpleName");
            if (FitnessHelperKt.recentSatisfyPrimaryStage(userId, simpleName, stageInfoEnum)) {
                this.mActiveTimeSituationStage = stageInfoEnum;
            }
        }
        Pair<String, String> pairCaluteStageDate = FitnessHelperKt.caluteStageDate(this.mActiveTimeSituationStage.getMRecentStartDayCount(), this.mActiveTimeSituationStage.getMRecentEndDayCount() - this.mActiveTimeSituationStage.getMRecentStartDayCount());
        Pair<String, String> pairCaluteStageDate2 = FitnessHelperKt.caluteStageDate(this.mActiveTimeSituationStage.getMPastStartDayCount(), this.mActiveTimeSituationStage.getMPastEndDayCount() - this.mActiveTimeSituationStage.getMPastStartDayCount());
        this.mActiveTimeSituationAvg = FitnessHelperKt.caluteRecentSituationActiveTimeAvg(getUserId(), pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond());
        float fCaluteRecentSituationActiveTimeAvg = FitnessHelperKt.caluteRecentSituationActiveTimeAvg(getUserId(), pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond()) - FitnessHelperKt.caluteRecentSituationActiveTimeAvg(getUserId(), pairCaluteStageDate2.getFirst(), pairCaluteStageDate2.getSecond());
        if (fCaluteRecentSituationActiveTimeAvg >= 1.0f) {
            this.mActiveTimeSituationCompareState = UP;
        } else if (fCaluteRecentSituationActiveTimeAvg <= -1.0f) {
            this.mActiveTimeSituationCompareState = DOWN;
        }
    }

    private final void calculateWalkSituation() {
        HomeHelperKt.printAndSave("开始计算走动阶段", getTAG());
        this.mWalkSitutionStage = StageInfoEnum.PRIMARY;
        List<StageInfoEnum> siturationStageList = FitnessHelperKt.getSiturationStageList();
        this.mWalkSituationAvg = 0.0f;
        this.mWalkSituationCompareState = FLAT;
        for (StageInfoEnum stageInfoEnum : siturationStageList) {
            long userId = getUserId();
            String simpleName = WalkDayData.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "WalkDayData::class.java.simpleName");
            if (FitnessHelperKt.recentSatisfyPrimaryStage(userId, simpleName, stageInfoEnum)) {
                this.mWalkSitutionStage = stageInfoEnum;
            }
        }
        Pair<String, String> pairCaluteStageDate = FitnessHelperKt.caluteStageDate(this.mWalkSitutionStage.getMRecentStartDayCount(), this.mWalkSitutionStage.getMRecentEndDayCount() - this.mWalkSitutionStage.getMRecentStartDayCount());
        Pair<String, String> pairCaluteStageDate2 = FitnessHelperKt.caluteStageDate(this.mWalkSitutionStage.getMPastStartDayCount(), this.mWalkSitutionStage.getMPastEndDayCount() - this.mWalkSitutionStage.getMPastStartDayCount());
        this.mWalkSituationAvg = FitnessHelperKt.caluteRecentSituationWalkAvg(getUserId(), pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond());
        float fCaluteRecentSituationWalkAvg = FitnessHelperKt.caluteRecentSituationWalkAvg(getUserId(), pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond()) - FitnessHelperKt.caluteRecentSituationWalkAvg(getUserId(), pairCaluteStageDate2.getFirst(), pairCaluteStageDate2.getSecond());
        if (fCaluteRecentSituationWalkAvg >= 0.5f) {
            this.mWalkSituationCompareState = UP;
        } else if (fCaluteRecentSituationWalkAvg <= -0.5f) {
            this.mWalkSituationCompareState = DOWN;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter, com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo taskInfo) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        CommonLogUtil.d(getTAG(), "数据下拉成功taskInfo=" + taskInfo);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter, com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskComplete() {
        String mStartDate = getMStartDate();
        if (mStartDate == null || mStartDate.length() == 0) {
            return;
        }
        String mEndDate = getMEndDate();
        if (mEndDate == null || mEndDate.length() == 0) {
            return;
        }
        if (getDataDownloadState() == 4 || getDataDownloadState() == 3) {
            HomeHelperKt.printAndSave('(' + getMStartDate() + '-' + getMEndDate() + ")任务执行完成，但是dataDownloadState=" + getDataDownloadState(), getTAG());
            return;
        }
        setDataDownloadState(3);
        getMView().showLoadSuccessView();
        getMFocusList().clear();
        HomeHelperKt.printAndSave('(' + getMStartDate() + '-' + getMEndDate() + ")任务执行完成，开始从本地读取数据。", getTAG());
        getDetailData();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public void processEventBusMessage(BaseMessage<?> message) {
        super.processEventBusMessage(message);
        if (message == null) {
            return;
        }
        int type = message.getType();
        if (type != -101 && type != -100) {
            switch (type) {
            }
            return;
        }
        HomeHelperKt.printAndSave("设备连接状态发生改变，需要更新目标显示值。", getTAG());
        updateTarget();
    }

    private final int caluteYMaxActiviteTime(int activeTimeMax) {
        if (activeTimeMax < 4) {
            return 4;
        }
        if (activeTimeMax < 10) {
            return 10;
        }
        if (10 <= activeTimeMax && 99 >= activeTimeMax) {
            return ((activeTimeMax / 10) + 1) * 10;
        }
        return (100 <= activeTimeMax && 999 >= activeTimeMax) ? ((activeTimeMax / 100) + 1) * 100 : ((activeTimeMax / 1000) + 1) * 1000;
    }

    private final GradientBarPoint getActiveCalorieTargetChartBean(float x, float y, float actualValue, float roundAnglePercent) {
        return new GradientBarPoint(-1, x, y, actualValue, Color.parseColor("#FF6835"), Color.parseColor("#E60320"), roundAnglePercent);
    }

    private final GradientBarPoint getActiveCalorieUnTargetChartBean(float x, float y, float actualValue, float roundAnglePercent) {
        return new GradientBarPoint(-1, x, y, actualValue, Color.parseColor("#FFE5E0"), roundAnglePercent);
    }

    private final GradientBarPoint getActiveTimeTargetChartBean(float x, float y, float actualValue, float roundAnglePercent) {
        return new GradientBarPoint(-1, x, y, actualValue, Color.parseColor("#8BF43E"), Color.parseColor("#00C84B"), roundAnglePercent);
    }

    private final GradientBarPoint getActiveTimeUnTargetChartBean(float x, float y, float actualValue, float roundAnglePercent) {
        return new GradientBarPoint(-1, x, y, actualValue, Color.parseColor("#D8F8D6"), roundAnglePercent);
    }

    private final GradientBarPoint getWalkTargetChartBean(float x, float y, float actualValue, float roundAnglePercent) {
        return new GradientBarPoint(-1, x, y, actualValue, Color.parseColor("#3AE5FF"), Color.parseColor("#0BA9FF"), roundAnglePercent);
    }

    private final GradientBarPoint getWalkUnTargetChartBean(float x, float y, float actualValue, float roundAnglePercent) {
        return new GradientBarPoint(-1, x, y, actualValue, Color.parseColor("#DCFBFB"), roundAnglePercent);
    }

    public final List<RadiusProgressBar.DividerProperty> getCaloriePropertyList() {
        ArrayList arrayList = new ArrayList();
        int iMax = Math.max(0, FitnessHelperKt.caluteBMR(getUserId()));
        if (MathKt.roundToInt(((double) iMax) * 0.8d) >= 50) {
            float f2 = iMax;
            float f3 = 0.25f * f2;
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#FEC776"), ajustCalorieMin(MathKt.roundToInt(0.15f * f2)), f3, 0.25f, null, null, null, 112, null));
            float f4 = 0.35f * f2;
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#FF6F36"), f3, f4, 0.25f, null, null, null, 112, null));
            float f5 = 0.55f * f2;
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#F8112E"), f4, f5, 0.25f, null, null, null, 112, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#CB003C"), f5, ajustCalorieMax(MathKt.roundToInt(f2 * 0.8f)), 0.25f, null, null, null, 112, null));
        } else {
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#FEC776"), 10.0f, 20.0f, 0.25f, null, null, null, 112, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#FF6F36"), 20.0f, 30.0f, 0.25f, null, null, null, 112, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#F8112E"), 30.0f, 40.0f, 0.25f, null, null, null, 112, null));
            arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#CB003C"), 40.0f, 50.0f, 0.25f, null, null, null, 112, null));
        }
        return arrayList;
    }

    public final List<RadiusProgressBar.DividerProperty> getActivityTimePropertyList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#AEFC92"), 5.0f, 10.0f, 0.25f, null, null, null, 112, null));
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#09DF59"), 10.0f, 20.0f, 0.25f, null, null, null, 112, null));
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00BE47"), 20.0f, 40.0f, 0.25f, null, null, null, 112, null));
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#00905F"), 40.0f, 60.0f, 0.25f, null, null, null, 112, null));
        return arrayList;
    }

    public final List<RadiusProgressBar.DividerProperty> getWalkPropertyList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#A6DFFF"), 6.0f, 8.0f, 0.25f, null, null, null, 112, null));
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#3FBBFF"), 8.0f, 10.0f, 0.25f, null, null, null, 112, null));
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#049BEB"), 10.0f, 12.0f, 0.25f, null, null, null, 112, null));
        arrayList.add(new RadiusProgressBar.DividerProperty(Color.parseColor("#0062BD"), 12.0f, 14.0f, 0.25f, null, null, null, 112, null));
        return arrayList;
    }

    private final UserTargetNew getUserTarget() {
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(getUserId());
        if (userTargetNewQueryUserLatestTarget != null) {
            return userTargetNewQueryUserLatestTarget;
        }
        UserTargetNew userTargetNewGenerateDefaultUserTargetNew = RunTimeUtil.generateDefaultUserTargetNew(getUserId());
        Intrinsics.checkExpressionValueIsNotNull(userTargetNewGenerateDefaultUserTargetNew, "RunTimeUtil.generateDefa…serTargetNew(getUserId())");
        return userTargetNewGenerateDefaultUserTargetNew;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDayCount() {
        return (getMXMaxValue() - getMXMinValue()) + 1;
    }

    public final int getActivityCalorieDefaultXMin() {
        return getMXMinValue();
    }

    public final int getActivityTimeDefaultXMin() {
        return getMXMinValue();
    }

    public final int getWalkDefaultXMin() {
        return getMXMinValue();
    }

    public final int getActivityCalorieDefaultXMax() {
        return getMXMaxValue();
    }

    public final int getActivityTimeDefaultXMax() {
        return getMXMaxValue();
    }

    public final int getWalkDefaultXMax() {
        return getMXMaxValue();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected List<String> getDataDownloadType() {
        return CollectionsKt.mutableListOf(CalorieDayData.class.getSimpleName(), ActiveTimeDayData.class.getSimpleName(), WalkDayData.class.getSimpleName(), ServerSleepDayData.class.getSimpleName());
    }
}