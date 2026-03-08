package com.ido.life.module.home.ambientvolume;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.AmbientVolumePassedChartView;
import com.ido.life.customview.AmbientVolumeProgressBar;
import com.ido.life.enums.AmbientVolumeExposureEnum;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: AmbientVolumeDetailBottomViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\bM\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0002J\n\u0010\u0095\u0001\u001a\u00030\u0094\u0001H\u0002J\n\u0010\u0096\u0001\u001a\u00030\u0094\u0001H\u0002J\n\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0016J\n\u0010\u0099\u0001\u001a\u00030\u0098\u0001H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR\u001a\u0010#\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR\u001a\u0010&\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001fR\u001a\u0010/\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u0010\u0004R\u001a\u00103\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\b\"\u0004\b5\u0010\nR\u001a\u00106\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\b\"\u0004\b8\u0010\nR\u001a\u00109\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\b\"\u0004\b;\u0010\nR\u001a\u0010<\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u00101\"\u0004\b>\u0010\u0004R\u001a\u0010?\u001a\u00020@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001a\u0010E\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010H\"\u0004\bM\u0010JR\u001a\u0010N\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010H\"\u0004\bP\u0010JR\u001a\u0010Q\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010H\"\u0004\bS\u0010JR\u001a\u0010T\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010H\"\u0004\bV\u0010JR\u001a\u0010W\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010H\"\u0004\bY\u0010JR\u001a\u0010Z\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010H\"\u0004\b\\\u0010JR\u001a\u0010]\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010H\"\u0004\b_\u0010JR\u001a\u0010`\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010H\"\u0004\bb\u0010JR\u001a\u0010c\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010H\"\u0004\be\u0010JR\u001a\u0010f\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010H\"\u0004\bh\u0010JR\u001a\u0010i\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010H\"\u0004\bk\u0010JR\u001a\u0010l\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010H\"\u0004\bn\u0010JR\u001a\u0010o\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010H\"\u0004\bq\u0010JR\u001a\u0010r\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010H\"\u0004\bt\u0010JR\u001a\u0010u\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010H\"\u0004\bw\u0010JR\u001a\u0010x\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010H\"\u0004\bz\u0010JR\u001a\u0010{\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010H\"\u0004\b}\u0010JR\u001b\u0010~\u001a\u00020FX\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010H\"\u0005\b\u0080\u0001\u0010JR\u001d\u0010\u0081\u0001\u001a\u00020FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010H\"\u0005\b\u0083\u0001\u0010JR\u001d\u0010\u0084\u0001\u001a\u00020FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010H\"\u0005\b\u0086\u0001\u0010JR\u001d\u0010\u0087\u0001\u001a\u00020FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010H\"\u0005\b\u0089\u0001\u0010JR\u001d\u0010\u008a\u0001\u001a\u00020FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010H\"\u0005\b\u008c\u0001\u0010JR\u001d\u0010\u008d\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010\b\"\u0005\b\u008f\u0001\u0010\nR\u001d\u0010\u0090\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010\b\"\u0005\b\u0092\u0001\u0010\n¨\u0006\u009a\u0001"}, d2 = {"Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailBottomViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mDayLevelCompareTodayProgress", "Lcom/ido/life/customview/AmbientVolumeProgressBar;", "getMDayLevelCompareTodayProgress", "()Lcom/ido/life/customview/AmbientVolumeProgressBar;", "setMDayLevelCompareTodayProgress", "(Lcom/ido/life/customview/AmbientVolumeProgressBar;)V", "mDayLevelCompareYesterdayProgress", "getMDayLevelCompareYesterdayProgress", "setMDayLevelCompareYesterdayProgress", "mImgRecentVolumeState", "Landroid/widget/ImageView;", "getMImgRecentVolumeState", "()Landroid/widget/ImageView;", "setMImgRecentVolumeState", "(Landroid/widget/ImageView;)V", "mLayAbortAmbientVolume", "Landroidx/appcompat/widget/LinearLayoutCompat;", "getMLayAbortAmbientVolume", "()Landroidx/appcompat/widget/LinearLayoutCompat;", "setMLayAbortAmbientVolume", "(Landroidx/appcompat/widget/LinearLayoutCompat;)V", "mLayCompare", "Landroid/widget/LinearLayout;", "getMLayCompare", "()Landroid/widget/LinearLayout;", "setMLayCompare", "(Landroid/widget/LinearLayout;)V", "mLayDayVolumeLevelCompare", "getMLayDayVolumeLevelCompare", "setMLayDayVolumeLevelCompare", "mLayExposure", "getMLayExposure", "setMLayExposure", "mLayRecent", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getMLayRecent", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setMLayRecent", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "mLayWeekCompare", "getMLayWeekCompare", "setMLayWeekCompare", "mLineWeekCompare", "getMLineWeekCompare", "()Landroid/view/View;", "setMLineWeekCompare", "mProgressHighExposure", "getMProgressHighExposure", "setMProgressHighExposure", "mProgressMediumExposure", "getMProgressMediumExposure", "setMProgressMediumExposure", "mProgressNormalExposure", "getMProgressNormalExposure", "setMProgressNormalExposure", "mRecentGuideLine", "getMRecentGuideLine", "setMRecentGuideLine", "mRecentVolumeChartView", "Lcom/ido/life/customview/AmbientVolumePassedChartView;", "getMRecentVolumeChartView", "()Lcom/ido/life/customview/AmbientVolumePassedChartView;", "setMRecentVolumeChartView", "(Lcom/ido/life/customview/AmbientVolumePassedChartView;)V", "mTvAbortVolume", "Landroid/widget/TextView;", "getMTvAbortVolume", "()Landroid/widget/TextView;", "setMTvAbortVolume", "(Landroid/widget/TextView;)V", "mTvAbortVolumeDesc", "getMTvAbortVolumeDesc", "setMTvAbortVolumeDesc", "mTvDayLevelCompareTodayVolume", "getMTvDayLevelCompareTodayVolume", "setMTvDayLevelCompareTodayVolume", "mTvDayLevelCompareTodayVolumeUnit", "getMTvDayLevelCompareTodayVolumeUnit", "setMTvDayLevelCompareTodayVolumeUnit", "mTvDayLevelCompareYesterdayVolume", "getMTvDayLevelCompareYesterdayVolume", "setMTvDayLevelCompareYesterdayVolume", "mTvDayLevelCompareYesterdayVolumeUnit", "getMTvDayLevelCompareYesterdayVolumeUnit", "setMTvDayLevelCompareYesterdayVolumeUnit", "mTvDayVolumeLevelCompareDesc", "getMTvDayVolumeLevelCompareDesc", "setMTvDayVolumeLevelCompareDesc", "mTvRecentVolumeAvg", "getMTvRecentVolumeAvg", "setMTvRecentVolumeAvg", "mTvRecentVolumeDuration", "getMTvRecentVolumeDuration", "setMTvRecentVolumeDuration", "mTvRecentVolumeState", "getMTvRecentVolumeState", "setMTvRecentVolumeState", "mTvTitleAvg", "getMTvTitleAvg", "setMTvTitleAvg", "mTvTitleDayVolumeLevelCompare", "getMTvTitleDayVolumeLevelCompare", "setMTvTitleDayVolumeLevelCompare", "mTvTitleRecentSevenDays", "getMTvTitleRecentSevenDays", "setMTvTitleRecentSevenDays", "mTvTitleVolumeLevelExposure", "getMTvTitleVolumeLevelExposure", "setMTvTitleVolumeLevelExposure", "mTvTitleWeekLevelCompare", "getMTvTitleWeekLevelCompare", "setMTvTitleWeekLevelCompare", "mTvVolumeExposureLevelHigh", "getMTvVolumeExposureLevelHigh", "setMTvVolumeExposureLevelHigh", "mTvVolumeExposureLevelMedium", "getMTvVolumeExposureLevelMedium", "setMTvVolumeExposureLevelMedium", "mTvVolumeExposureLevelNormal", "getMTvVolumeExposureLevelNormal", "setMTvVolumeExposureLevelNormal", "mTvWeekLevelCompareCurrentVolume", "getMTvWeekLevelCompareCurrentVolume", "setMTvWeekLevelCompareCurrentVolume", "mTvWeekLevelCompareCurrentVolumeUnit", "getMTvWeekLevelCompareCurrentVolumeUnit", "setMTvWeekLevelCompareCurrentVolumeUnit", "mTvWeekLevelCompareDesc", "getMTvWeekLevelCompareDesc", "setMTvWeekLevelCompareDesc", "mTvWeekLevelComparePreviousVolume", "getMTvWeekLevelComparePreviousVolume", "setMTvWeekLevelComparePreviousVolume", "mTvWeekLevelComparePreviousVolumeUnit", "getMTvWeekLevelComparePreviousVolumeUnit", "setMTvWeekLevelComparePreviousVolumeUnit", "mWeekLevelCompareCurrentProgress", "getMWeekLevelCompareCurrentProgress", "setMWeekLevelCompareCurrentProgress", "mWeekLevelComparePreviousProgress", "getMWeekLevelComparePreviousProgress", "setMWeekLevelComparePreviousProgress", "getVolumeExposureHigh", "", "getVolumeExposureMedium", "getVolumeExposureNormal", "refreshLanguage", "", "setDefaultValue", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeDetailBottomViewHolder extends BaseDetailViewHolder {
    private AmbientVolumeProgressBar mDayLevelCompareTodayProgress;
    private AmbientVolumeProgressBar mDayLevelCompareYesterdayProgress;
    private ImageView mImgRecentVolumeState;
    private LinearLayoutCompat mLayAbortAmbientVolume;
    private LinearLayout mLayCompare;
    private LinearLayout mLayDayVolumeLevelCompare;
    private LinearLayout mLayExposure;
    private ConstraintLayout mLayRecent;
    private LinearLayout mLayWeekCompare;
    private View mLineWeekCompare;
    private AmbientVolumeProgressBar mProgressHighExposure;
    private AmbientVolumeProgressBar mProgressMediumExposure;
    private AmbientVolumeProgressBar mProgressNormalExposure;
    private View mRecentGuideLine;
    private AmbientVolumePassedChartView mRecentVolumeChartView;
    private TextView mTvAbortVolume;
    private TextView mTvAbortVolumeDesc;
    private TextView mTvDayLevelCompareTodayVolume;
    private TextView mTvDayLevelCompareTodayVolumeUnit;
    private TextView mTvDayLevelCompareYesterdayVolume;
    private TextView mTvDayLevelCompareYesterdayVolumeUnit;
    private TextView mTvDayVolumeLevelCompareDesc;
    private TextView mTvRecentVolumeAvg;
    private TextView mTvRecentVolumeDuration;
    private TextView mTvRecentVolumeState;
    private TextView mTvTitleAvg;
    private TextView mTvTitleDayVolumeLevelCompare;
    private TextView mTvTitleRecentSevenDays;
    private TextView mTvTitleVolumeLevelExposure;
    private TextView mTvTitleWeekLevelCompare;
    private TextView mTvVolumeExposureLevelHigh;
    private TextView mTvVolumeExposureLevelMedium;
    private TextView mTvVolumeExposureLevelNormal;
    private TextView mTvWeekLevelCompareCurrentVolume;
    private TextView mTvWeekLevelCompareCurrentVolumeUnit;
    private TextView mTvWeekLevelCompareDesc;
    private TextView mTvWeekLevelComparePreviousVolume;
    private TextView mTvWeekLevelComparePreviousVolumeUnit;
    private AmbientVolumeProgressBar mWeekLevelCompareCurrentProgress;
    private AmbientVolumeProgressBar mWeekLevelComparePreviousProgress;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmbientVolumeDetailBottomViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.lay_recent);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_recent)");
        this.mLayRecent = (ConstraintLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.tv_title_recent_seven_days);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.…_title_recent_seven_days)");
        this.mTvTitleRecentSevenDays = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.recent_view);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.recent_view)");
        this.mRecentVolumeChartView = (AmbientVolumePassedChartView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.guide_line_recent);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.guide_line_recent)");
        this.mRecentGuideLine = viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_title_avg);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_title_avg)");
        this.mTvTitleAvg = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.img_recent_volume_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.….img_recent_volume_state)");
        this.mImgRecentVolumeState = (ImageView) viewFindViewById6;
        View viewFindViewById7 = itemView.findViewById(R.id.tv_recent_volume_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "itemView.findViewById(R.id.tv_recent_volume_state)");
        this.mTvRecentVolumeState = (TextView) viewFindViewById7;
        View viewFindViewById8 = itemView.findViewById(R.id.tv_recent_volume_duration);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "itemView.findViewById(R.…v_recent_volume_duration)");
        this.mTvRecentVolumeDuration = (TextView) viewFindViewById8;
        View viewFindViewById9 = itemView.findViewById(R.id.tv_recent_volume_avg);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "itemView.findViewById(R.id.tv_recent_volume_avg)");
        this.mTvRecentVolumeAvg = (TextView) viewFindViewById9;
        View viewFindViewById10 = itemView.findViewById(R.id.lay_exposure);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById10, "itemView.findViewById(R.id.lay_exposure)");
        this.mLayExposure = (LinearLayout) viewFindViewById10;
        View viewFindViewById11 = itemView.findViewById(R.id.tv_title_volume_level_exposure);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById11, "itemView.findViewById(R.…le_volume_level_exposure)");
        this.mTvTitleVolumeLevelExposure = (TextView) viewFindViewById11;
        View viewFindViewById12 = itemView.findViewById(R.id.tv_title_volume_level_exposure_high);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById12, "itemView.findViewById(R.…lume_level_exposure_high)");
        this.mTvVolumeExposureLevelHigh = (TextView) viewFindViewById12;
        View viewFindViewById13 = itemView.findViewById(R.id.progress_high_exposure);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById13, "itemView.findViewById(R.id.progress_high_exposure)");
        this.mProgressHighExposure = (AmbientVolumeProgressBar) viewFindViewById13;
        View viewFindViewById14 = itemView.findViewById(R.id.tv_title_volume_level_exposure_medium);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById14, "itemView.findViewById(R.…me_level_exposure_medium)");
        this.mTvVolumeExposureLevelMedium = (TextView) viewFindViewById14;
        View viewFindViewById15 = itemView.findViewById(R.id.progress_medium_exposure);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById15, "itemView.findViewById(R.…progress_medium_exposure)");
        this.mProgressMediumExposure = (AmbientVolumeProgressBar) viewFindViewById15;
        View viewFindViewById16 = itemView.findViewById(R.id.tv_title_volume_level_exposure_normal);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById16, "itemView.findViewById(R.…me_level_exposure_normal)");
        this.mTvVolumeExposureLevelNormal = (TextView) viewFindViewById16;
        View viewFindViewById17 = itemView.findViewById(R.id.progress_normal_exposure);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById17, "itemView.findViewById(R.…progress_normal_exposure)");
        this.mProgressNormalExposure = (AmbientVolumeProgressBar) viewFindViewById17;
        View viewFindViewById18 = itemView.findViewById(R.id.lay_day_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById18, "itemView.findViewById(R.id.lay_day_compare)");
        this.mLayDayVolumeLevelCompare = (LinearLayout) viewFindViewById18;
        View viewFindViewById19 = itemView.findViewById(R.id.tv_title_day_volume_level_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById19, "itemView.findViewById(R.…day_volume_level_compare)");
        this.mTvTitleDayVolumeLevelCompare = (TextView) viewFindViewById19;
        View viewFindViewById20 = itemView.findViewById(R.id.tv_day_volume_level_compare_desc);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById20, "itemView.findViewById(R.…olume_level_compare_desc)");
        this.mTvDayVolumeLevelCompareDesc = (TextView) viewFindViewById20;
        View viewFindViewById21 = itemView.findViewById(R.id.tv_day_today_volume);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById21, "itemView.findViewById(R.id.tv_day_today_volume)");
        this.mTvDayLevelCompareTodayVolume = (TextView) viewFindViewById21;
        View viewFindViewById22 = itemView.findViewById(R.id.tv_day_today_volume_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById22, "itemView.findViewById(R.…tv_day_today_volume_unit)");
        this.mTvDayLevelCompareTodayVolumeUnit = (TextView) viewFindViewById22;
        View viewFindViewById23 = itemView.findViewById(R.id.progress_day_volume_compare_today);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById23, "itemView.findViewById(R.…day_volume_compare_today)");
        this.mDayLevelCompareTodayProgress = (AmbientVolumeProgressBar) viewFindViewById23;
        View viewFindViewById24 = itemView.findViewById(R.id.tv_yesterday_volume);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById24, "itemView.findViewById(R.id.tv_yesterday_volume)");
        this.mTvDayLevelCompareYesterdayVolume = (TextView) viewFindViewById24;
        View viewFindViewById25 = itemView.findViewById(R.id.tv_yesterday_volume_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById25, "itemView.findViewById(R.…tv_yesterday_volume_unit)");
        this.mTvDayLevelCompareYesterdayVolumeUnit = (TextView) viewFindViewById25;
        View viewFindViewById26 = itemView.findViewById(R.id.progress_day_volume_compare_yesterday);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById26, "itemView.findViewById(R.…volume_compare_yesterday)");
        this.mDayLevelCompareYesterdayProgress = (AmbientVolumeProgressBar) viewFindViewById26;
        View viewFindViewById27 = itemView.findViewById(R.id.lay_week_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById27, "itemView.findViewById(R.id.lay_week_compare)");
        this.mLayWeekCompare = (LinearLayout) viewFindViewById27;
        View viewFindViewById28 = itemView.findViewById(R.id.tv_title_week_volume_level_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById28, "itemView.findViewById(R.…eek_volume_level_compare)");
        this.mTvTitleWeekLevelCompare = (TextView) viewFindViewById28;
        View viewFindViewById29 = itemView.findViewById(R.id.tv_week_volume_level_desc);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById29, "itemView.findViewById(R.…v_week_volume_level_desc)");
        this.mTvWeekLevelCompareDesc = (TextView) viewFindViewById29;
        View viewFindViewById30 = itemView.findViewById(R.id.tv_current_week_volume);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById30, "itemView.findViewById(R.id.tv_current_week_volume)");
        this.mTvWeekLevelCompareCurrentVolume = (TextView) viewFindViewById30;
        View viewFindViewById31 = itemView.findViewById(R.id.tv_current_week_volume_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById31, "itemView.findViewById(R.…current_week_volume_unit)");
        this.mTvWeekLevelCompareCurrentVolumeUnit = (TextView) viewFindViewById31;
        View viewFindViewById32 = itemView.findViewById(R.id.progress_week_compare_current);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById32, "itemView.findViewById(R.…ess_week_compare_current)");
        this.mWeekLevelCompareCurrentProgress = (AmbientVolumeProgressBar) viewFindViewById32;
        View viewFindViewById33 = itemView.findViewById(R.id.tv_previous_week_volume);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById33, "itemView.findViewById(R.….tv_previous_week_volume)");
        this.mTvWeekLevelComparePreviousVolume = (TextView) viewFindViewById33;
        View viewFindViewById34 = itemView.findViewById(R.id.tv_previous_week_volume_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById34, "itemView.findViewById(R.…revious_week_volume_unit)");
        this.mTvWeekLevelComparePreviousVolumeUnit = (TextView) viewFindViewById34;
        View viewFindViewById35 = itemView.findViewById(R.id.progress_week_compare_previous);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById35, "itemView.findViewById(R.…ss_week_compare_previous)");
        this.mWeekLevelComparePreviousProgress = (AmbientVolumeProgressBar) viewFindViewById35;
        View viewFindViewById36 = itemView.findViewById(R.id.tv_abort_volume);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById36, "itemView.findViewById(R.id.tv_abort_volume)");
        this.mTvAbortVolume = (TextView) viewFindViewById36;
        View viewFindViewById37 = itemView.findViewById(R.id.tv_abort_volume_desc);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById37, "itemView.findViewById(R.id.tv_abort_volume_desc)");
        this.mTvAbortVolumeDesc = (TextView) viewFindViewById37;
        View viewFindViewById38 = itemView.findViewById(R.id.line_week_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById38, "itemView.findViewById(R.id.line_week_compare)");
        this.mLineWeekCompare = viewFindViewById38;
        View viewFindViewById39 = itemView.findViewById(R.id.lay_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById39, "itemView.findViewById(R.id.lay_compare)");
        this.mLayCompare = (LinearLayout) viewFindViewById39;
        View viewFindViewById40 = itemView.findViewById(R.id.lay_abort_ambient_volume);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById40, "itemView.findViewById(R.…lay_abort_ambient_volume)");
        this.mLayAbortAmbientVolume = (LinearLayoutCompat) viewFindViewById40;
        setDefaultValue();
        refreshLanguage();
    }

    public final ConstraintLayout getMLayRecent() {
        return this.mLayRecent;
    }

    public final void setMLayRecent(ConstraintLayout constraintLayout) {
        Intrinsics.checkParameterIsNotNull(constraintLayout, "<set-?>");
        this.mLayRecent = constraintLayout;
    }

    public final TextView getMTvTitleRecentSevenDays() {
        return this.mTvTitleRecentSevenDays;
    }

    public final void setMTvTitleRecentSevenDays(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleRecentSevenDays = textView;
    }

    public final AmbientVolumePassedChartView getMRecentVolumeChartView() {
        return this.mRecentVolumeChartView;
    }

    public final void setMRecentVolumeChartView(AmbientVolumePassedChartView ambientVolumePassedChartView) {
        Intrinsics.checkParameterIsNotNull(ambientVolumePassedChartView, "<set-?>");
        this.mRecentVolumeChartView = ambientVolumePassedChartView;
    }

    public final View getMRecentGuideLine() {
        return this.mRecentGuideLine;
    }

    public final void setMRecentGuideLine(View view) {
        Intrinsics.checkParameterIsNotNull(view, "<set-?>");
        this.mRecentGuideLine = view;
    }

    public final TextView getMTvTitleAvg() {
        return this.mTvTitleAvg;
    }

    public final void setMTvTitleAvg(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleAvg = textView;
    }

    public final ImageView getMImgRecentVolumeState() {
        return this.mImgRecentVolumeState;
    }

    public final void setMImgRecentVolumeState(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
        this.mImgRecentVolumeState = imageView;
    }

    public final TextView getMTvRecentVolumeState() {
        return this.mTvRecentVolumeState;
    }

    public final void setMTvRecentVolumeState(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvRecentVolumeState = textView;
    }

    public final TextView getMTvRecentVolumeDuration() {
        return this.mTvRecentVolumeDuration;
    }

    public final void setMTvRecentVolumeDuration(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvRecentVolumeDuration = textView;
    }

    public final TextView getMTvRecentVolumeAvg() {
        return this.mTvRecentVolumeAvg;
    }

    public final void setMTvRecentVolumeAvg(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvRecentVolumeAvg = textView;
    }

    public final LinearLayout getMLayExposure() {
        return this.mLayExposure;
    }

    public final void setMLayExposure(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayExposure = linearLayout;
    }

    public final TextView getMTvTitleVolumeLevelExposure() {
        return this.mTvTitleVolumeLevelExposure;
    }

    public final void setMTvTitleVolumeLevelExposure(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleVolumeLevelExposure = textView;
    }

    public final TextView getMTvVolumeExposureLevelHigh() {
        return this.mTvVolumeExposureLevelHigh;
    }

    public final void setMTvVolumeExposureLevelHigh(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeExposureLevelHigh = textView;
    }

    public final AmbientVolumeProgressBar getMProgressHighExposure() {
        return this.mProgressHighExposure;
    }

    public final void setMProgressHighExposure(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mProgressHighExposure = ambientVolumeProgressBar;
    }

    public final TextView getMTvVolumeExposureLevelMedium() {
        return this.mTvVolumeExposureLevelMedium;
    }

    public final void setMTvVolumeExposureLevelMedium(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeExposureLevelMedium = textView;
    }

    public final AmbientVolumeProgressBar getMProgressMediumExposure() {
        return this.mProgressMediumExposure;
    }

    public final void setMProgressMediumExposure(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mProgressMediumExposure = ambientVolumeProgressBar;
    }

    public final TextView getMTvVolumeExposureLevelNormal() {
        return this.mTvVolumeExposureLevelNormal;
    }

    public final void setMTvVolumeExposureLevelNormal(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeExposureLevelNormal = textView;
    }

    public final AmbientVolumeProgressBar getMProgressNormalExposure() {
        return this.mProgressNormalExposure;
    }

    public final void setMProgressNormalExposure(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mProgressNormalExposure = ambientVolumeProgressBar;
    }

    public final LinearLayout getMLayDayVolumeLevelCompare() {
        return this.mLayDayVolumeLevelCompare;
    }

    public final void setMLayDayVolumeLevelCompare(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayDayVolumeLevelCompare = linearLayout;
    }

    public final TextView getMTvTitleDayVolumeLevelCompare() {
        return this.mTvTitleDayVolumeLevelCompare;
    }

    public final void setMTvTitleDayVolumeLevelCompare(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleDayVolumeLevelCompare = textView;
    }

    public final TextView getMTvDayVolumeLevelCompareDesc() {
        return this.mTvDayVolumeLevelCompareDesc;
    }

    public final void setMTvDayVolumeLevelCompareDesc(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDayVolumeLevelCompareDesc = textView;
    }

    public final TextView getMTvDayLevelCompareTodayVolume() {
        return this.mTvDayLevelCompareTodayVolume;
    }

    public final void setMTvDayLevelCompareTodayVolume(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDayLevelCompareTodayVolume = textView;
    }

    public final TextView getMTvDayLevelCompareTodayVolumeUnit() {
        return this.mTvDayLevelCompareTodayVolumeUnit;
    }

    public final void setMTvDayLevelCompareTodayVolumeUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDayLevelCompareTodayVolumeUnit = textView;
    }

    public final AmbientVolumeProgressBar getMDayLevelCompareTodayProgress() {
        return this.mDayLevelCompareTodayProgress;
    }

    public final void setMDayLevelCompareTodayProgress(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mDayLevelCompareTodayProgress = ambientVolumeProgressBar;
    }

    public final TextView getMTvDayLevelCompareYesterdayVolume() {
        return this.mTvDayLevelCompareYesterdayVolume;
    }

    public final void setMTvDayLevelCompareYesterdayVolume(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDayLevelCompareYesterdayVolume = textView;
    }

    public final TextView getMTvDayLevelCompareYesterdayVolumeUnit() {
        return this.mTvDayLevelCompareYesterdayVolumeUnit;
    }

    public final void setMTvDayLevelCompareYesterdayVolumeUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDayLevelCompareYesterdayVolumeUnit = textView;
    }

    public final AmbientVolumeProgressBar getMDayLevelCompareYesterdayProgress() {
        return this.mDayLevelCompareYesterdayProgress;
    }

    public final void setMDayLevelCompareYesterdayProgress(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mDayLevelCompareYesterdayProgress = ambientVolumeProgressBar;
    }

    public final LinearLayout getMLayWeekCompare() {
        return this.mLayWeekCompare;
    }

    public final void setMLayWeekCompare(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayWeekCompare = linearLayout;
    }

    public final TextView getMTvTitleWeekLevelCompare() {
        return this.mTvTitleWeekLevelCompare;
    }

    public final void setMTvTitleWeekLevelCompare(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleWeekLevelCompare = textView;
    }

    public final TextView getMTvWeekLevelCompareDesc() {
        return this.mTvWeekLevelCompareDesc;
    }

    public final void setMTvWeekLevelCompareDesc(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWeekLevelCompareDesc = textView;
    }

    public final TextView getMTvWeekLevelCompareCurrentVolume() {
        return this.mTvWeekLevelCompareCurrentVolume;
    }

    public final void setMTvWeekLevelCompareCurrentVolume(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWeekLevelCompareCurrentVolume = textView;
    }

    public final TextView getMTvWeekLevelCompareCurrentVolumeUnit() {
        return this.mTvWeekLevelCompareCurrentVolumeUnit;
    }

    public final void setMTvWeekLevelCompareCurrentVolumeUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWeekLevelCompareCurrentVolumeUnit = textView;
    }

    public final AmbientVolumeProgressBar getMWeekLevelCompareCurrentProgress() {
        return this.mWeekLevelCompareCurrentProgress;
    }

    public final void setMWeekLevelCompareCurrentProgress(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mWeekLevelCompareCurrentProgress = ambientVolumeProgressBar;
    }

    public final TextView getMTvWeekLevelComparePreviousVolume() {
        return this.mTvWeekLevelComparePreviousVolume;
    }

    public final void setMTvWeekLevelComparePreviousVolume(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWeekLevelComparePreviousVolume = textView;
    }

    public final TextView getMTvWeekLevelComparePreviousVolumeUnit() {
        return this.mTvWeekLevelComparePreviousVolumeUnit;
    }

    public final void setMTvWeekLevelComparePreviousVolumeUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWeekLevelComparePreviousVolumeUnit = textView;
    }

    public final AmbientVolumeProgressBar getMWeekLevelComparePreviousProgress() {
        return this.mWeekLevelComparePreviousProgress;
    }

    public final void setMWeekLevelComparePreviousProgress(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mWeekLevelComparePreviousProgress = ambientVolumeProgressBar;
    }

    public final TextView getMTvAbortVolume() {
        return this.mTvAbortVolume;
    }

    public final void setMTvAbortVolume(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvAbortVolume = textView;
    }

    public final TextView getMTvAbortVolumeDesc() {
        return this.mTvAbortVolumeDesc;
    }

    public final void setMTvAbortVolumeDesc(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvAbortVolumeDesc = textView;
    }

    public final View getMLineWeekCompare() {
        return this.mLineWeekCompare;
    }

    public final void setMLineWeekCompare(View view) {
        Intrinsics.checkParameterIsNotNull(view, "<set-?>");
        this.mLineWeekCompare = view;
    }

    public final LinearLayout getMLayCompare() {
        return this.mLayCompare;
    }

    public final void setMLayCompare(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayCompare = linearLayout;
    }

    public final LinearLayoutCompat getMLayAbortAmbientVolume() {
        return this.mLayAbortAmbientVolume;
    }

    public final void setMLayAbortAmbientVolume(LinearLayoutCompat linearLayoutCompat) {
        Intrinsics.checkParameterIsNotNull(linearLayoutCompat, "<set-?>");
        this.mLayAbortAmbientVolume = linearLayoutCompat;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleRecentSevenDays.setText(LanguageUtil.getLanguageText(R.string.title_volume_passed_seven_days));
        this.mTvTitleAvg.setText(LanguageUtil.getLanguageText(R.string.title_volume_avg));
        this.mTvTitleVolumeLevelExposure.setText(LanguageUtil.getLanguageText(R.string.title_volume_exposure_level));
        this.mTvTitleDayVolumeLevelCompare.setText(LanguageUtil.getLanguageText(R.string.title_volume_level_compare));
        this.mTvTitleWeekLevelCompare.setText(LanguageUtil.getLanguageText(R.string.title_volume_level_compare));
        this.mTvAbortVolume.setText(LanguageUtil.getLanguageText(R.string.abort_ambient_volume));
        this.mTvAbortVolumeDesc.setText(LanguageUtil.getLanguageText(R.string.abort_ambient_volume_desc));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        TextView textView = this.mTvRecentVolumeDuration;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {LanguageUtil.getLanguageText(R.string.public_time_hour), LanguageUtil.getLanguageText(R.string.public_time_minute)};
        String str = String.format("0%s0$s", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        textView.setText(str);
        TextView textView2 = this.mTvRecentVolumeAvg;
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = new Object[2];
        objArr2[0] = LanguageUtil.getLanguageText(R.string.home_detail_ave_ios);
        String languageText = LanguageUtil.getLanguageText(R.string.public_volume_unit);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…tring.public_volume_unit)");
        Locale locale = Locale.CHINA;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.CHINA");
        if (languageText != null) {
            String lowerCase = languageText.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            objArr2[1] = lowerCase;
            String str2 = String.format("%s0%s", Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
            textView2.setText(str2);
            this.mTvVolumeExposureLevelHigh.setText(getVolumeExposureHigh());
            this.mTvVolumeExposureLevelMedium.setText(getVolumeExposureMedium());
            this.mTvVolumeExposureLevelNormal.setText(getVolumeExposureNormal());
            this.mProgressHighExposure.setLeftLabel(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.public_time_minute));
            this.mProgressMediumExposure.setLeftLabel(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.public_time_minute));
            this.mProgressNormalExposure.setLeftLabel(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.public_time_minute));
            this.mTvDayLevelCompareTodayVolume.setText("-");
            this.mDayLevelCompareTodayProgress.setProgress(0);
            this.mTvDayLevelCompareYesterdayVolume.setText("-");
            this.mDayLevelCompareYesterdayProgress.setProgress(0);
            this.mTvWeekLevelCompareCurrentVolume.setText("-");
            this.mWeekLevelCompareCurrentProgress.setProgress(0);
            this.mTvWeekLevelComparePreviousVolume.setText("-");
            this.mWeekLevelComparePreviousProgress.setProgress(0);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    private final String getVolumeExposureHigh() {
        return ">=" + AmbientVolumeExposureEnum.HIGH.getMinVolume() + LanguageUtil.getLanguageText(R.string.public_volume_unit) + " (0" + LanguageUtil.getLanguageText(R.string.public_time_hour) + "/7" + LanguageUtil.getLanguageText(R.string.public_unit_day) + ")";
    }

    private final String getVolumeExposureMedium() {
        return String.valueOf(AmbientVolumeExposureEnum.MEDIUM.getMinVolume()) + "-" + AmbientVolumeExposureEnum.MEDIUM.getMaxVolume() + LanguageUtil.getLanguageText(R.string.public_volume_unit) + " (0" + LanguageUtil.getLanguageText(R.string.public_time_hour) + "/7" + LanguageUtil.getLanguageText(R.string.public_unit_day) + ")";
    }

    private final String getVolumeExposureNormal() {
        return String.valueOf(AmbientVolumeExposureEnum.NORMAL.getMinVolume()) + "-" + AmbientVolumeExposureEnum.NORMAL.getMaxVolume() + LanguageUtil.getLanguageText(R.string.public_volume_unit) + " (0" + LanguageUtil.getLanguageText(R.string.public_time_hour) + "/7" + LanguageUtil.getLanguageText(R.string.public_unit_day) + ")";
    }
}